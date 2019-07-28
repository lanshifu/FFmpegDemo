package org.greenrobot.eventbus;

import java.util.logging.Level;

/* compiled from: BackgroundPoster */
final class b implements Runnable, k {
    private final j a = new j();
    private final c b;
    private volatile boolean c;

    b(c cVar) {
        this.b = cVar;
    }

    public void a(p pVar, Object obj) {
        i a = i.a(pVar, obj);
        synchronized (this) {
            this.a.a(a);
            if (!this.c) {
                this.c = true;
                this.b.b().execute(this);
            }
        }
    }

    public void run() {
        while (true) {
            try {
                i a = this.a.a(1000);
                if (a == null) {
                    synchronized (this) {
                        a = this.a.a();
                        if (a == null) {
                            this.c = false;
                            this.c = false;
                            return;
                        }
                    }
                }
                this.b.a(a);
            } catch (InterruptedException e) {
                try {
                    f c = this.b.c();
                    Level level = Level.WARNING;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(Thread.currentThread().getName());
                    stringBuilder.append(" was interruppted");
                    c.a(level, stringBuilder.toString(), e);
                    return;
                } finally {
                    this.c = false;
                }
            }
        }
    }
}
