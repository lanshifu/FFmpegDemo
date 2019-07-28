package okhttp3.internal.ws;

import okio.ByteString;

final class RealWebSocket$Close {
    final long cancelAfterCloseMillis;
    final int code;
    final ByteString reason;

    RealWebSocket$Close(int i, ByteString byteString, long j) {
        this.code = i;
        this.reason = byteString;
        this.cancelAfterCloseMillis = j;
    }
}
