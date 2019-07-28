package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.http2.Http2Connection.Listener;

class Http2Connection$Listener$1 extends Listener {
    Http2Connection$Listener$1() {
    }

    public void onStream(Http2Stream http2Stream) throws IOException {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }
}
