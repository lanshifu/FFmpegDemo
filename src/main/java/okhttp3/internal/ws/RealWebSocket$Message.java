package okhttp3.internal.ws;

import okio.ByteString;

final class RealWebSocket$Message {
    final ByteString data;
    final int formatOpcode;

    RealWebSocket$Message(int i, ByteString byteString) {
        this.formatOpcode = i;
        this.data = byteString;
    }
}
