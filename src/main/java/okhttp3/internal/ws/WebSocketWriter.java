package okhttp3.internal.ws;

import java.io.IOException;
import java.util.Random;
import okio.ByteString;
import okio.c;
import okio.c$a;
import okio.d;
import okio.q;
import okio.s;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

final class WebSocketWriter {
    boolean activeWriter;
    final c buffer = new c();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    private final c$a maskCursor;
    private final byte[] maskKey;
    final Random random;
    final d sink;
    final c sinkBuffer;
    boolean writerClosed;

    final class FrameSink implements q {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;

        FrameSink() {
        }

        public void write(c cVar, long j) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(cVar, j);
            Object obj = (!this.isFirstFrame || this.contentLength == -1 || WebSocketWriter.this.buffer.a() <= this.contentLength - IjkMediaMeta.AV_CH_TOP_FRONT_CENTER) ? null : 1;
            long h = WebSocketWriter.this.buffer.h();
            if (h > 0 && obj == null) {
                WebSocketWriter.this.writeMessageFrame(this.formatOpcode, h, this.isFirstFrame, false);
                this.isFirstFrame = false;
            }
        }

        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, WebSocketWriter.this.buffer.a(), this.isFirstFrame, false);
            this.isFirstFrame = false;
        }

        public s timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        public void close() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, WebSocketWriter.this.buffer.a(), this.isFirstFrame, true);
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }
    }

    WebSocketWriter(boolean z, d dVar, Random random) {
        if (dVar == null) {
            throw new NullPointerException("sink == null");
        } else if (random != null) {
            this.isClient = z;
            this.sink = dVar;
            this.sinkBuffer = dVar.b();
            this.random = random;
            c$a c_a = null;
            this.maskKey = z ? new byte[4] : null;
            if (z) {
                c_a = new c$a();
            }
            this.maskCursor = c_a;
        } else {
            throw new NullPointerException("random == null");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void writePing(ByteString byteString) throws IOException {
        writeControlFrame(9, byteString);
    }

    /* Access modifiers changed, original: 0000 */
    public void writePong(ByteString byteString) throws IOException {
        writeControlFrame(10, byteString);
    }

    /* Access modifiers changed, original: 0000 */
    public void writeClose(int i, ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (!(i == 0 && byteString == null)) {
            if (i != 0) {
                WebSocketProtocol.validateCloseCode(i);
            }
            c cVar = new c();
            cVar.c(i);
            if (byteString != null) {
                cVar.a(byteString);
            }
            byteString2 = cVar.r();
        }
        try {
            writeControlFrame(8, byteString2);
        } finally {
            this.writerClosed = true;
        }
    }

    private void writeControlFrame(int i, ByteString byteString) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int size = byteString.size();
        if (((long) size) <= 125) {
            this.sinkBuffer.b(i | 128);
            if (this.isClient) {
                this.sinkBuffer.b(size | 128);
                this.random.nextBytes(this.maskKey);
                this.sinkBuffer.b(this.maskKey);
                if (size > 0) {
                    long a = this.sinkBuffer.a();
                    this.sinkBuffer.a(byteString);
                    this.sinkBuffer.a(this.maskCursor);
                    this.maskCursor.a(a);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            } else {
                this.sinkBuffer.b(size);
                this.sinkBuffer.a(byteString);
            }
            this.sink.flush();
            return;
        }
        throw new IllegalArgumentException("Payload size must be less than or equal to 125");
    }

    /* Access modifiers changed, original: 0000 */
    public q newMessageSink(int i, long j) {
        if (this.activeWriter) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.activeWriter = true;
        this.frameSink.formatOpcode = i;
        this.frameSink.contentLength = j;
        this.frameSink.isFirstFrame = true;
        this.frameSink.closed = false;
        return this.frameSink;
    }

    /* Access modifiers changed, original: 0000 */
    public void writeMessageFrame(int i, long j, boolean z, boolean z2) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int i2 = 0;
        if (!z) {
            i = 0;
        }
        if (z2) {
            i |= 128;
        }
        this.sinkBuffer.b(i);
        if (this.isClient) {
            i2 = 128;
        }
        if (j <= 125) {
            this.sinkBuffer.b(((int) j) | i2);
        } else if (j <= 65535) {
            this.sinkBuffer.b(i2 | 126);
            this.sinkBuffer.c((int) j);
        } else {
            this.sinkBuffer.b(i2 | 127);
            this.sinkBuffer.j(j);
        }
        if (this.isClient) {
            this.random.nextBytes(this.maskKey);
            this.sinkBuffer.b(this.maskKey);
            if (j > 0) {
                long a = this.sinkBuffer.a();
                this.sinkBuffer.write(this.buffer, j);
                this.sinkBuffer.a(this.maskCursor);
                this.maskCursor.a(a);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        } else {
            this.sinkBuffer.write(this.buffer, j);
        }
        this.sink.e();
    }
}
