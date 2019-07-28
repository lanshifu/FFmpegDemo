package defpackage;

import java.util.concurrent.Future;

/* compiled from: FutureCancelableWrapper */
/* renamed from: ut */
class ut implements uq {
    private Future a;

    ut(Future future) {
        this.a = future;
    }

    public void a() {
        if (this.a != null && !this.a.isDone() && !this.a.isCancelled()) {
            this.a.cancel(true);
            this.a = null;
        }
    }
}
