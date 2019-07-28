package io.reactivex.internal.util;

import defpackage.wf;
import defpackage.wl;
import java.util.concurrent.CountDownLatch;

/* compiled from: BlockingIgnoringReceiver */
public final class d extends CountDownLatch implements wf, wl<Throwable> {
    public Throwable a;

    public d() {
        super(1);
    }

    /* renamed from: a */
    public void accept(Throwable th) {
        this.a = th;
        countDown();
    }

    public void a() {
        countDown();
    }
}
