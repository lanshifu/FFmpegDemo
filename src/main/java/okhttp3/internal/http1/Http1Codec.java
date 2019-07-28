package okhttp3.internal.http1;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.Headers$Builder;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Response.Builder;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.c;
import okio.d;
import okio.e;
import okio.i;
import okio.l;
import okio.q;
import okio.r;
import okio.s;

public final class Http1Codec implements HttpCodec {
    private static final int HEADER_LIMIT = 262144;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    final OkHttpClient client;
    private long headerLimit = 262144;
    final d sink;
    final e source;
    int state = 0;
    final StreamAllocation streamAllocation;

    private abstract class AbstractSource implements r {
        protected long bytesRead;
        protected boolean closed;
        protected final i timeout;

        private AbstractSource() {
            this.timeout = new i(Http1Codec.this.source.timeout());
            this.bytesRead = 0;
        }

        public s timeout() {
            return this.timeout;
        }

        public long read(c cVar, long j) throws IOException {
            try {
                long read = Http1Codec.this.source.read(cVar, j);
                if (read > 0) {
                    this.bytesRead += read;
                }
                return read;
            } catch (IOException e) {
                endOfInput(false, e);
                throw e;
            }
        }

        /* Access modifiers changed, original: protected|final */
        public final void endOfInput(boolean z, IOException iOException) throws IOException {
            if (Http1Codec.this.state != 6) {
                if (Http1Codec.this.state == 5) {
                    Http1Codec.this.detachTimeout(this.timeout);
                    Http1Codec.this.state = 6;
                    if (Http1Codec.this.streamAllocation != null) {
                        Http1Codec.this.streamAllocation.streamFinished(z ^ 1, Http1Codec.this, this.bytesRead, iOException);
                    }
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("state: ");
                stringBuilder.append(Http1Codec.this.state);
                throw new IllegalStateException(stringBuilder.toString());
            }
        }
    }

    private final class ChunkedSink implements q {
        private boolean closed;
        private final i timeout = new i(Http1Codec.this.sink.timeout());

        ChunkedSink() {
        }

        public s timeout() {
            return this.timeout;
        }

        public void write(c cVar, long j) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                Http1Codec.this.sink.n(j);
                Http1Codec.this.sink.b("\r\n");
                Http1Codec.this.sink.write(cVar, j);
                Http1Codec.this.sink.b("\r\n");
            }
        }

        public synchronized void flush() throws IOException {
            if (!this.closed) {
                Http1Codec.this.sink.flush();
            }
        }

        public synchronized void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                Http1Codec.this.sink.b("0\r\n\r\n");
                Http1Codec.this.detachTimeout(this.timeout);
                Http1Codec.this.state = 3;
            }
        }
    }

    private final class FixedLengthSink implements q {
        private long bytesRemaining;
        private boolean closed;
        private final i timeout = new i(Http1Codec.this.sink.timeout());

        FixedLengthSink(long j) {
            this.bytesRemaining = j;
        }

        public s timeout() {
            return this.timeout;
        }

        public void write(c cVar, long j) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            Util.checkOffsetAndCount(cVar.a(), 0, j);
            if (j <= this.bytesRemaining) {
                Http1Codec.this.sink.write(cVar, j);
                this.bytesRemaining -= j;
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("expected ");
            stringBuilder.append(this.bytesRemaining);
            stringBuilder.append(" bytes but received ");
            stringBuilder.append(j);
            throw new ProtocolException(stringBuilder.toString());
        }

        public void flush() throws IOException {
            if (!this.closed) {
                Http1Codec.this.sink.flush();
            }
        }

        public void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                if (this.bytesRemaining <= 0) {
                    Http1Codec.this.detachTimeout(this.timeout);
                    Http1Codec.this.state = 3;
                    return;
                }
                throw new ProtocolException("unexpected end of stream");
            }
        }
    }

    private class ChunkedSource extends AbstractSource {
        private static final long NO_CHUNK_YET = -1;
        private long bytesRemainingInChunk = -1;
        private boolean hasMoreChunks = true;
        private final HttpUrl url;

        ChunkedSource(HttpUrl httpUrl) {
            super();
            this.url = httpUrl;
        }

        public long read(c cVar, long j) throws IOException {
            if (j < 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("byteCount < 0: ");
                stringBuilder.append(j);
                throw new IllegalArgumentException(stringBuilder.toString());
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (!this.hasMoreChunks) {
                return -1;
            } else {
                if (this.bytesRemainingInChunk == 0 || this.bytesRemainingInChunk == -1) {
                    readChunkSize();
                    if (!this.hasMoreChunks) {
                        return -1;
                    }
                }
                long read = super.read(cVar, Math.min(j, this.bytesRemainingInChunk));
                if (read != -1) {
                    this.bytesRemainingInChunk -= read;
                    return read;
                }
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                endOfInput(false, protocolException);
                throw protocolException;
            }
        }

        private void readChunkSize() throws IOException {
            if (this.bytesRemainingInChunk != -1) {
                Http1Codec.this.source.t();
            }
            try {
                this.bytesRemainingInChunk = Http1Codec.this.source.q();
                String trim = Http1Codec.this.source.t().trim();
                if (this.bytesRemainingInChunk < 0 || !(trim.isEmpty() || trim.startsWith(";"))) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("expected chunk size and optional extensions but was \"");
                    stringBuilder.append(this.bytesRemainingInChunk);
                    stringBuilder.append(trim);
                    stringBuilder.append("\"");
                    throw new ProtocolException(stringBuilder.toString());
                } else if (this.bytesRemainingInChunk == 0) {
                    this.hasMoreChunks = false;
                    HttpHeaders.receiveHeaders(Http1Codec.this.client.cookieJar(), this.url, Http1Codec.this.readHeaders());
                    endOfInput(true, null);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public void close() throws IOException {
            if (!this.closed) {
                if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    endOfInput(false, null);
                }
                this.closed = true;
            }
        }
    }

    private class FixedLengthSource extends AbstractSource {
        private long bytesRemaining;

        FixedLengthSource(long j) throws IOException {
            super();
            this.bytesRemaining = j;
            if (this.bytesRemaining == 0) {
                endOfInput(true, null);
            }
        }

        public long read(c cVar, long j) throws IOException {
            if (j < 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("byteCount < 0: ");
                stringBuilder.append(j);
                throw new IllegalArgumentException(stringBuilder.toString());
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (this.bytesRemaining == 0) {
                return -1;
            } else {
                long read = super.read(cVar, Math.min(this.bytesRemaining, j));
                if (read != -1) {
                    this.bytesRemaining -= read;
                    if (this.bytesRemaining == 0) {
                        endOfInput(true, null);
                    }
                    return read;
                }
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                endOfInput(false, protocolException);
                throw protocolException;
            }
        }

        public void close() throws IOException {
            if (!this.closed) {
                if (!(this.bytesRemaining == 0 || Util.discard(this, 100, TimeUnit.MILLISECONDS))) {
                    endOfInput(false, null);
                }
                this.closed = true;
            }
        }
    }

    private class UnknownLengthSource extends AbstractSource {
        private boolean inputExhausted;

        UnknownLengthSource() {
            super();
        }

        public long read(c cVar, long j) throws IOException {
            if (j < 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("byteCount < 0: ");
                stringBuilder.append(j);
                throw new IllegalArgumentException(stringBuilder.toString());
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (this.inputExhausted) {
                return -1;
            } else {
                long read = super.read(cVar, j);
                if (read != -1) {
                    return read;
                }
                this.inputExhausted = true;
                endOfInput(true, null);
                return -1;
            }
        }

        public void close() throws IOException {
            if (!this.closed) {
                if (!this.inputExhausted) {
                    endOfInput(false, null);
                }
                this.closed = true;
            }
        }
    }

    public Http1Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation, e eVar, d dVar) {
        this.client = okHttpClient;
        this.streamAllocation = streamAllocation;
        this.source = eVar;
        this.sink = dVar;
    }

    public q createRequestBody(Request request, long j) {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return newChunkedSink();
        }
        if (j != -1) {
            return newFixedLengthSink(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void cancel() {
        RealConnection connection = this.streamAllocation.connection();
        if (connection != null) {
            connection.cancel();
        }
    }

    public void writeRequestHeaders(Request request) throws IOException {
        writeRequest(request.headers(), RequestLine.get(request, this.streamAllocation.connection().route().proxy().type()));
    }

    public ResponseBody openResponseBody(Response response) throws IOException {
        this.streamAllocation.eventListener.responseBodyStart(this.streamAllocation.call);
        String header = response.header("Content-Type");
        if (!HttpHeaders.hasBody(response)) {
            return new RealResponseBody(header, 0, l.a(newFixedLengthSource(0)));
        }
        if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return new RealResponseBody(header, -1, l.a(newChunkedSource(response.request().url())));
        }
        long contentLength = HttpHeaders.contentLength(response);
        if (contentLength != -1) {
            return new RealResponseBody(header, contentLength, l.a(newFixedLengthSource(contentLength)));
        }
        return new RealResponseBody(header, -1, l.a(newUnknownLengthSource()));
    }

    public boolean isClosed() {
        return this.state == 6;
    }

    public void flushRequest() throws IOException {
        this.sink.flush();
    }

    public void finishRequest() throws IOException {
        this.sink.flush();
    }

    public void writeRequest(Headers headers, String str) throws IOException {
        if (this.state == 0) {
            this.sink.b(str).b("\r\n");
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                this.sink.b(headers.name(i)).b(": ").b(headers.value(i)).b("\r\n");
            }
            this.sink.b("\r\n");
            this.state = 1;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("state: ");
        stringBuilder.append(this.state);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public Builder readResponseHeaders(boolean z) throws IOException {
        if (this.state == 1 || this.state == 3) {
            try {
                StatusLine parse = StatusLine.parse(readHeaderLine());
                Builder headers = new Builder().protocol(parse.protocol).code(parse.code).message(parse.message).headers(readHeaders());
                if (z && parse.code == 100) {
                    return null;
                }
                if (parse.code == 100) {
                    this.state = 3;
                    return headers;
                }
                this.state = 4;
                return headers;
            } catch (EOFException e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("unexpected end of stream on ");
                stringBuilder.append(this.streamAllocation);
                IOException iOException = new IOException(stringBuilder.toString());
                iOException.initCause(e);
                throw iOException;
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("state: ");
        stringBuilder2.append(this.state);
        throw new IllegalStateException(stringBuilder2.toString());
    }

    private String readHeaderLine() throws IOException {
        String f = this.source.f(this.headerLimit);
        this.headerLimit -= (long) f.length();
        return f;
    }

    public Headers readHeaders() throws IOException {
        Headers$Builder headers$Builder = new Headers$Builder();
        while (true) {
            String readHeaderLine = readHeaderLine();
            if (readHeaderLine.length() == 0) {
                return headers$Builder.build();
            }
            Internal.instance.addLenient(headers$Builder, readHeaderLine);
        }
    }

    public q newChunkedSink() {
        if (this.state == 1) {
            this.state = 2;
            return new ChunkedSink();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("state: ");
        stringBuilder.append(this.state);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public q newFixedLengthSink(long j) {
        if (this.state == 1) {
            this.state = 2;
            return new FixedLengthSink(j);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("state: ");
        stringBuilder.append(this.state);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public r newFixedLengthSource(long j) throws IOException {
        if (this.state == 4) {
            this.state = 5;
            return new FixedLengthSource(j);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("state: ");
        stringBuilder.append(this.state);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public r newChunkedSource(HttpUrl httpUrl) throws IOException {
        if (this.state == 4) {
            this.state = 5;
            return new ChunkedSource(httpUrl);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("state: ");
        stringBuilder.append(this.state);
        throw new IllegalStateException(stringBuilder.toString());
    }

    public r newUnknownLengthSource() throws IOException {
        if (this.state != 4) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("state: ");
            stringBuilder.append(this.state);
            throw new IllegalStateException(stringBuilder.toString());
        } else if (this.streamAllocation != null) {
            this.state = 5;
            this.streamAllocation.noNewStreams();
            return new UnknownLengthSource();
        } else {
            throw new IllegalStateException("streamAllocation == null");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void detachTimeout(i iVar) {
        s a = iVar.a();
        iVar.a(s.NONE);
        a.clearDeadline();
        a.clearTimeout();
    }
}
