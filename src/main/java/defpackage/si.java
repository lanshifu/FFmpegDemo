package defpackage;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;

/* compiled from: AndroidSpringLooperFactory */
/* renamed from: si */
abstract class si {

    @TargetApi(16)
    /* compiled from: AndroidSpringLooperFactory */
    /* renamed from: si$a */
    private static class a extends sq {
        private final Choreographer b;
        private final FrameCallback c = new 1();
        private boolean d;
        private long e;

        /* compiled from: AndroidSpringLooperFactory */
        /* renamed from: si$a$1 */
        class 1 implements FrameCallback {
            1() {
            }

            public void doFrame(long j) {
                if (a.this.d && a.this.a != null) {
                    j = SystemClock.uptimeMillis();
                    a.this.a.b((double) (j - a.this.e));
                    a.this.e = j;
                    a.this.b.postFrameCallback(a.this.c);
                }
            }
        }

        public static a a() {
            return new a(Choreographer.getInstance());
        }

        public a(Choreographer choreographer) {
            this.b = choreographer;
        }

        public void b() {
            if (!this.d) {
                this.d = true;
                this.e = SystemClock.uptimeMillis();
                this.b.removeFrameCallback(this.c);
                this.b.postFrameCallback(this.c);
            }
        }

        public void c() {
            this.d = false;
            this.b.removeFrameCallback(this.c);
        }
    }

    /* compiled from: AndroidSpringLooperFactory */
    /* renamed from: si$b */
    private static class b extends sq {
        private final Handler b;
        private final Runnable c = new 1();
        private boolean d;
        private long e;

        /* compiled from: AndroidSpringLooperFactory */
        /* renamed from: si$b$1 */
        class 1 implements Runnable {
            1() {
            }

            public void run() {
                if (b.this.d && b.this.a != null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    b.this.a.b((double) (uptimeMillis - b.this.e));
                    b.this.e = uptimeMillis;
                    b.this.b.post(b.this.c);
                }
            }
        }

        public static sq a() {
            return new b(new Handler());
        }

        public b(Handler handler) {
            this.b = handler;
        }

        public void b() {
            if (!this.d) {
                this.d = true;
                this.e = SystemClock.uptimeMillis();
                this.b.removeCallbacks(this.c);
                this.b.post(this.c);
            }
        }

        public void c() {
            this.d = false;
            this.b.removeCallbacks(this.c);
        }
    }

    public static sq a() {
        if (VERSION.SDK_INT >= 16) {
            return a.a();
        }
        return b.a();
    }
}
