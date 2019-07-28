package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import okio.a;
import okio.c;
import okio.e;
import okio.q;
import okio.r;
import okio.s;

public final class Http2Stream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    long bytesLeftInWriteWindow;
    final Http2Connection connection;
    ErrorCode errorCode = null;
    private boolean hasResponseHeaders;
    final int id;
    final StreamTimeout readTimeout = new StreamTimeout();
    private final List<Header> requestHeaders;
    private List<Header> responseHeaders;
    final FramingSink sink;
    private final FramingSource source;
    long unacknowledgedBytesRead = 0;
    final StreamTimeout writeTimeout = new StreamTimeout();

    final class FramingSink implements q {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long EMIT_BUFFER_SIZE = 16384;
        boolean closed;
        boolean finished;
        private final c sendBuffer = new c();

        static {
            Class cls = Http2Stream.class;
        }

        FramingSink() {
        }

        public void write(c cVar, long j) throws IOException {
            this.sendBuffer.write(cVar, j);
            while (this.sendBuffer.a() >= 16384) {
                emitFrame(false);
            }
        }

        private void emitFrame(boolean z) throws IOException {
            long min;
            synchronized (Http2Stream.this) {
                Http2Stream.this.writeTimeout.enter();
                while (Http2Stream.this.bytesLeftInWriteWindow <= 0 && !this.finished && !this.closed && Http2Stream.this.errorCode == null) {
                    try {
                        Http2Stream.this.waitForIo();
                    } finally {
                        Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
                    }
                }
                Http2Stream.this.checkOutNotClosed();
                min = Math.min(Http2Stream.this.bytesLeftInWriteWindow, this.sendBuffer.a());
                Http2Stream http2Stream = Http2Stream.this;
                http2Stream.bytesLeftInWriteWindow -= min;
            }
            Http2Stream.this.writeTimeout.enter();
            try {
                Http2Connection http2Connection = Http2Stream.this.connection;
                int i = Http2Stream.this.id;
                boolean z2 = z && min == this.sendBuffer.a();
                http2Connection.writeData(i, z2, this.sendBuffer, min);
            } finally {
                Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
            }
        }

        public void flush() throws IOException {
            synchronized (Http2Stream.this) {
                Http2Stream.this.checkOutNotClosed();
            }
            while (this.sendBuffer.a() > 0) {
                emitFrame(false);
                Http2Stream.this.connection.flush();
            }
        }

        public s timeout() {
            return Http2Stream.this.writeTimeout;
        }

        /* JADX WARNING: Missing block: B:9:0x0011, code skipped:
            if (r8.this$0.sink.finished != false) goto L_0x003c;
     */
        /* JADX WARNING: Missing block: B:11:0x001d, code skipped:
            if (r8.sendBuffer.a() <= 0) goto L_0x002d;
     */
        /* JADX WARNING: Missing block: B:13:0x0027, code skipped:
            if (r8.sendBuffer.a() <= 0) goto L_0x003c;
     */
        /* JADX WARNING: Missing block: B:14:0x0029, code skipped:
            emitFrame(true);
     */
        /* JADX WARNING: Missing block: B:15:0x002d, code skipped:
            r8.this$0.connection.writeData(r8.this$0.id, true, null, 0);
     */
        /* JADX WARNING: Missing block: B:16:0x003c, code skipped:
            r2 = r8.this$0;
     */
        /* JADX WARNING: Missing block: B:17:0x003e, code skipped:
            monitor-enter(r2);
     */
        /* JADX WARNING: Missing block: B:19:?, code skipped:
            r8.closed = true;
     */
        /* JADX WARNING: Missing block: B:20:0x0041, code skipped:
            monitor-exit(r2);
     */
        /* JADX WARNING: Missing block: B:21:0x0042, code skipped:
            r8.this$0.connection.flush();
            r8.this$0.cancelStreamIfNecessary();
     */
        /* JADX WARNING: Missing block: B:22:0x004e, code skipped:
            return;
     */
        public void close() throws java.io.IOException {
            /*
            r8 = this;
            r0 = okhttp3.internal.http2.Http2Stream.this;
            monitor-enter(r0);
            r1 = r8.closed;	 Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0009;
        L_0x0007:
            monitor-exit(r0);	 Catch:{ all -> 0x0052 }
            return;
        L_0x0009:
            monitor-exit(r0);	 Catch:{ all -> 0x0052 }
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0 = r0.sink;
            r0 = r0.finished;
            r1 = 1;
            if (r0 != 0) goto L_0x003c;
        L_0x0013:
            r0 = r8.sendBuffer;
            r2 = r0.a();
            r4 = 0;
            r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x002d;
        L_0x001f:
            r0 = r8.sendBuffer;
            r2 = r0.a();
            r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
            if (r0 <= 0) goto L_0x003c;
        L_0x0029:
            r8.emitFrame(r1);
            goto L_0x001f;
        L_0x002d:
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r2 = r0.connection;
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r3 = r0.id;
            r4 = 1;
            r5 = 0;
            r6 = 0;
            r2.writeData(r3, r4, r5, r6);
        L_0x003c:
            r2 = okhttp3.internal.http2.Http2Stream.this;
            monitor-enter(r2);
            r8.closed = r1;	 Catch:{ all -> 0x004f }
            monitor-exit(r2);	 Catch:{ all -> 0x004f }
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0 = r0.connection;
            r0.flush();
            r0 = okhttp3.internal.http2.Http2Stream.this;
            r0.cancelStreamIfNecessary();
            return;
        L_0x004f:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x004f }
            throw r0;
        L_0x0052:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0052 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream$FramingSink.close():void");
        }
    }

    private final class FramingSource implements r {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        boolean closed;
        boolean finished;
        private final long maxByteCount;
        private final c readBuffer = new c();
        private final c receiveBuffer = new c();

        static {
            Class cls = Http2Stream.class;
        }

        FramingSource(long j) {
            this.maxByteCount = j;
        }

        public long read(c cVar, long j) throws IOException {
            if (j >= 0) {
                ErrorCode errorCode;
                long read;
                synchronized (Http2Stream.this) {
                    waitUntilReadable();
                    if (this.closed) {
                        throw new IOException("stream closed");
                    }
                    errorCode = Http2Stream.this.errorCode;
                    if (this.readBuffer.a() > 0) {
                        read = this.readBuffer.read(cVar, Math.min(j, this.readBuffer.a()));
                        Http2Stream http2Stream = Http2Stream.this;
                        http2Stream.unacknowledgedBytesRead += read;
                    } else {
                        read = -1;
                    }
                    if (errorCode == null && Http2Stream.this.unacknowledgedBytesRead >= ((long) (Http2Stream.this.connection.okHttpSettings.getInitialWindowSize() / 2))) {
                        Http2Stream.this.connection.writeWindowUpdateLater(Http2Stream.this.id, Http2Stream.this.unacknowledgedBytesRead);
                        Http2Stream.this.unacknowledgedBytesRead = 0;
                    }
                }
                if (read != -1) {
                    updateConnectionFlowControl(read);
                    return read;
                } else if (errorCode == null) {
                    return -1;
                } else {
                    throw new StreamResetException(errorCode);
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("byteCount < 0: ");
            stringBuilder.append(j);
            throw new IllegalArgumentException(stringBuilder.toString());
        }

        private void updateConnectionFlowControl(long j) {
            Http2Stream.this.connection.updateConnectionFlowControl(j);
        }

        private void waitUntilReadable() throws IOException {
            Http2Stream.this.readTimeout.enter();
            while (this.readBuffer.a() == 0 && !this.finished && !this.closed && Http2Stream.this.errorCode == null) {
                try {
                    Http2Stream.this.waitForIo();
                } catch (Throwable th) {
                    Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
                }
            }
            Http2Stream.this.readTimeout.exitAndThrowIfTimedOut();
        }

        /* Access modifiers changed, original: 0000 */
        public void receive(e eVar, long j) throws IOException {
            while (j > 0) {
                boolean z;
                Object obj;
                Object obj2;
                synchronized (Http2Stream.this) {
                    z = this.finished;
                    obj = null;
                    obj2 = this.readBuffer.a() + j > this.maxByteCount ? 1 : null;
                }
                if (obj2 != null) {
                    eVar.i(j);
                    Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                } else if (z) {
                    eVar.i(j);
                    return;
                } else {
                    long read = eVar.read(this.receiveBuffer, j);
                    if (read != -1) {
                        j -= read;
                        synchronized (Http2Stream.this) {
                            if (this.readBuffer.a() == 0) {
                                obj = 1;
                            }
                            this.readBuffer.a(this.receiveBuffer);
                            if (obj != null) {
                                Http2Stream.this.notifyAll();
                            }
                        }
                    } else {
                        throw new EOFException();
                    }
                }
            }
        }

        public s timeout() {
            return Http2Stream.this.readTimeout;
        }

        public void close() throws IOException {
            long a;
            synchronized (Http2Stream.this) {
                this.closed = true;
                a = this.readBuffer.a();
                this.readBuffer.w();
                Http2Stream.this.notifyAll();
            }
            if (a > 0) {
                updateConnectionFlowControl(a);
            }
            Http2Stream.this.cancelStreamIfNecessary();
        }
    }

    class StreamTimeout extends a {
        StreamTimeout() {
        }

        /* Access modifiers changed, original: protected */
        public void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
        }

        /* Access modifiers changed, original: protected */
        public IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }
    }

    Http2Stream(int i, Http2Connection http2Connection, boolean z, boolean z2, List<Header> list) {
        if (http2Connection == null) {
            throw new NullPointerException("connection == null");
        } else if (list != null) {
            this.id = i;
            this.connection = http2Connection;
            this.bytesLeftInWriteWindow = (long) http2Connection.peerSettings.getInitialWindowSize();
            this.source = new FramingSource((long) http2Connection.okHttpSettings.getInitialWindowSize());
            this.sink = new FramingSink();
            this.source.finished = z2;
            this.sink.finished = z;
            this.requestHeaders = list;
        } else {
            throw new NullPointerException("requestHeaders == null");
        }
    }

    public int getId() {
        return this.id;
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        if ((this.source.finished || this.source.closed) && ((this.sink.finished || this.sink.closed) && this.hasResponseHeaders)) {
            return false;
        }
        return true;
    }

    public boolean isLocallyInitiated() {
        if (this.connection.client == ((this.id & 1) == 1)) {
            return true;
        }
        return false;
    }

    public Http2Connection getConnection() {
        return this.connection;
    }

    public List<Header> getRequestHeaders() {
        return this.requestHeaders;
    }

    public synchronized List<Header> takeResponseHeaders() throws IOException {
        List list;
        if (isLocallyInitiated()) {
            this.readTimeout.enter();
            while (this.responseHeaders == null && this.errorCode == null) {
                try {
                    waitForIo();
                } finally {
                    this.readTimeout.exitAndThrowIfTimedOut();
                }
            }
            list = this.responseHeaders;
            if (list != null) {
                this.responseHeaders = null;
            } else {
                throw new StreamResetException(this.errorCode);
            }
        }
        throw new IllegalStateException("servers cannot read response headers");
        return list;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public void sendResponseHeaders(List<Header> list, boolean z) throws IOException {
        if (list != null) {
            Object obj;
            boolean z2;
            synchronized (this) {
                this.hasResponseHeaders = true;
                if (z) {
                    obj = null;
                    z2 = false;
                } else {
                    this.sink.finished = true;
                    obj = 1;
                    z2 = true;
                }
            }
            if (obj == null) {
                synchronized (this.connection) {
                    obj = this.connection.bytesLeftInWriteWindow == 0 ? 1 : null;
                }
            }
            this.connection.writeSynReply(this.id, z2, list);
            if (obj != null) {
                this.connection.flush();
                return;
            }
            return;
        }
        throw new NullPointerException("responseHeaders == null");
    }

    public s readTimeout() {
        return this.readTimeout;
    }

    public s writeTimeout() {
        return this.writeTimeout;
    }

    public r getSource() {
        return this.source;
    }

    public q getSink() {
        synchronized (this) {
            if (!this.hasResponseHeaders) {
                if (!isLocallyInitiated()) {
                    throw new IllegalStateException("reply before requesting the sink");
                }
            }
        }
        return this.sink;
    }

    public void close(ErrorCode errorCode) throws IOException {
        if (closeInternal(errorCode)) {
            this.connection.writeSynReset(this.id, errorCode);
        }
    }

    public void closeLater(ErrorCode errorCode) {
        if (closeInternal(errorCode)) {
            this.connection.writeSynResetLater(this.id, errorCode);
        }
    }

    private boolean closeInternal(ErrorCode errorCode) {
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            } else if (this.source.finished && this.sink.finished) {
                return false;
            } else {
                this.errorCode = errorCode;
                notifyAll();
                this.connection.removeStream(this.id);
                return true;
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void receiveHeaders(List<Header> list) {
        boolean z;
        synchronized (this) {
            z = true;
            this.hasResponseHeaders = true;
            if (this.responseHeaders == null) {
                this.responseHeaders = list;
                z = isOpen();
                notifyAll();
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.responseHeaders);
                arrayList.add(null);
                arrayList.addAll(list);
                this.responseHeaders = arrayList;
            }
        }
        if (!z) {
            this.connection.removeStream(this.id);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void receiveData(e eVar, int i) throws IOException {
        this.source.receive(eVar, (long) i);
    }

    /* Access modifiers changed, original: 0000 */
    public void receiveFin() {
        boolean isOpen;
        synchronized (this) {
            this.source.finished = true;
            isOpen = isOpen();
            notifyAll();
        }
        if (!isOpen) {
            this.connection.removeStream(this.id);
        }
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void cancelStreamIfNecessary() throws IOException {
        Object obj;
        boolean isOpen;
        synchronized (this) {
            obj = (!this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed)) ? 1 : null;
            isOpen = isOpen();
        }
        if (obj != null) {
            close(ErrorCode.CANCEL);
        } else if (!isOpen) {
            this.connection.removeStream(this.id);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void checkOutNotClosed() throws IOException {
        if (this.sink.closed) {
            throw new IOException("stream closed");
        } else if (this.sink.finished) {
            throw new IOException("stream finished");
        } else if (this.errorCode != null) {
            throw new StreamResetException(this.errorCode);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }
}
