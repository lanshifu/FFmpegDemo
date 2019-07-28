package defpackage;

/* compiled from: DelayedRunnable */
/* renamed from: ri */
public class ri implements Runnable {
    public long a;
    private Runnable b;

    public ri(Runnable runnable, long j) {
        this.b = runnable;
        this.a = j;
    }

    public void run() {
        try {
            if (this.b != null) {
                this.b.run();
                this.b = null;
            }
        } catch (Throwable th) {
            if (!(th instanceof NoClassDefFoundError)) {
                th.printStackTrace();
            }
        }
    }
}
