package okhttp3.internal.ws;

final class RealWebSocket$CancelRunnable implements Runnable {
    final /* synthetic */ RealWebSocket this$0;

    RealWebSocket$CancelRunnable(RealWebSocket realWebSocket) {
        this.this$0 = realWebSocket;
    }

    public void run() {
        this.this$0.cancel();
    }
}
