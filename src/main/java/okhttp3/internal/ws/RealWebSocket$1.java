package okhttp3.internal.ws;

import java.io.IOException;

class RealWebSocket$1 implements Runnable {
    final /* synthetic */ RealWebSocket this$0;

    RealWebSocket$1(RealWebSocket realWebSocket) {
        this.this$0 = realWebSocket;
    }

    public void run() {
        while (this.this$0.writeOneFrame()) {
            try {
            } catch (IOException e) {
                this.this$0.failWebSocket(e, null);
                return;
            }
        }
    }
}
