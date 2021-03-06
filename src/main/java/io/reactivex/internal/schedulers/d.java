package io.reactivex.internal.schedulers;

import io.reactivex.s;
import io.reactivex.s.c;
import java.util.concurrent.ThreadFactory;

/* compiled from: NewThreadScheduler */
public final class d extends s {
    private static final RxThreadFactory c = new RxThreadFactory("RxNewThreadScheduler", Math.max(1, Math.min(10, Integer.getInteger("rx2.newthread-priority", 5).intValue())));
    final ThreadFactory b;

    public d() {
        this(c);
    }

    public d(ThreadFactory threadFactory) {
        this.b = threadFactory;
    }

    public c a() {
        return new e(this.b);
    }
}
