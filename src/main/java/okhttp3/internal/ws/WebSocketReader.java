package okhttp3.internal.ws;

import com.tencent.rtmp.TXLiveConstants;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.c;
import okio.c$a;
import okio.e;

final class WebSocketReader {
    boolean closed;
    private final c controlFrameBuffer = new c();
    final FrameCallback frameCallback;
    long frameLength;
    final boolean isClient;
    boolean isControlFrame;
    boolean isFinalFrame;
    private final c$a maskCursor;
    private final byte[] maskKey;
    private final c messageFrameBuffer = new c();
    int opcode;
    final e source;

    WebSocketReader(boolean z, e eVar, FrameCallback frameCallback) {
        if (eVar == null) {
            throw new NullPointerException("source == null");
        } else if (frameCallback != null) {
            byte[] bArr;
            this.isClient = z;
            this.source = eVar;
            this.frameCallback = frameCallback;
            c$a c_a = null;
            if (z) {
                bArr = null;
            } else {
                bArr = new byte[4];
            }
            this.maskKey = bArr;
            if (!z) {
                c_a = new c$a();
            }
            this.maskCursor = c_a;
        } else {
            throw new NullPointerException("frameCallback == null");
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }

    private void readHeader() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        long timeoutNanos = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            int i = this.source.i() & 255;
            this.isFinalFrame = timeoutNanos != null;
            this.isControlFrame = (i & 8) != 0;
            if (!this.isControlFrame || this.isFinalFrame) {
                Object obj = (i & 64) != 0 ? 1 : null;
                Object obj2 = (i & 32) != 0 ? 1 : null;
                Object obj3 = (i & 16) != 0 ? 1 : null;
                if (obj == null && obj2 == null && obj3 == null) {
                    int i2 = this.source.i() & 255;
                    if ((i2 & 128) != 0) {
                    }
                    if (true == this.isClient) {
                        throw new ProtocolException(this.isClient ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
                    }
                    this.frameLength = (long) (i2 & 127);
                    if (this.frameLength == 126) {
                        this.frameLength = ((long) this.source.j()) & 65535;
                    } else if (this.frameLength == 127) {
                        this.frameLength = this.source.l();
                        if (this.frameLength < 0) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("Frame length 0x");
                            stringBuilder.append(Long.toHexString(this.frameLength));
                            stringBuilder.append(" > 0x7FFFFFFFFFFFFFFF");
                            throw new ProtocolException(stringBuilder.toString());
                        }
                    }
                    if (this.isControlFrame && this.frameLength > 125) {
                        throw new ProtocolException("Control frame must be less than 125B.");
                    } else if (1 != null) {
                        this.source.a(this.maskKey);
                        return;
                    } else {
                        return;
                    }
                }
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            throw new ProtocolException("Control frames must be final.");
        } finally {
            this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
        }
    }

    private void readControlFrame() throws IOException {
        if (this.frameLength > 0) {
            this.source.a(this.controlFrameBuffer, this.frameLength);
            if (!this.isClient) {
                this.controlFrameBuffer.a(this.maskCursor);
                this.maskCursor.a(0);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 8:
                int i = TXLiveConstants.PUSH_EVT_CHANGE_RESOLUTION;
                String str = "";
                long a = this.controlFrameBuffer.a();
                if (a != 1) {
                    if (a != 0) {
                        i = this.controlFrameBuffer.j();
                        str = this.controlFrameBuffer.s();
                        String closeCodeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(i);
                        if (closeCodeExceptionMessage != null) {
                            throw new ProtocolException(closeCodeExceptionMessage);
                        }
                    }
                    this.frameCallback.onReadClose(i, str);
                    this.closed = true;
                    return;
                }
                throw new ProtocolException("Malformed close payload length of 1.");
            case 9:
                this.frameCallback.onReadPing(this.controlFrameBuffer.r());
                return;
            case 10:
                this.frameCallback.onReadPong(this.controlFrameBuffer.r());
                return;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unknown control opcode: ");
                stringBuilder.append(Integer.toHexString(this.opcode));
                throw new ProtocolException(stringBuilder.toString());
        }
    }

    private void readMessageFrame() throws IOException {
        int i = this.opcode;
        if (i == 1 || i == 2) {
            readMessage();
            if (i == 1) {
                this.frameCallback.onReadMessage(this.messageFrameBuffer.s());
                return;
            } else {
                this.frameCallback.onReadMessage(this.messageFrameBuffer.r());
                return;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown opcode: ");
        stringBuilder.append(Integer.toHexString(i));
        throw new ProtocolException(stringBuilder.toString());
    }

    private void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (this.isControlFrame) {
                readControlFrame();
            } else {
                return;
            }
        }
    }

    private void readMessage() throws IOException {
        while (!this.closed) {
            if (this.frameLength > 0) {
                this.source.a(this.messageFrameBuffer, this.frameLength);
                if (!this.isClient) {
                    this.messageFrameBuffer.a(this.maskCursor);
                    this.maskCursor.a(this.messageFrameBuffer.a() - this.frameLength);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            }
            if (!this.isFinalFrame) {
                readUntilNonControlFrame();
                if (this.opcode != 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Expected continuation opcode. Got: ");
                    stringBuilder.append(Integer.toHexString(this.opcode));
                    throw new ProtocolException(stringBuilder.toString());
                }
            } else {
                return;
            }
        }
        throw new IOException("closed");
    }
}
