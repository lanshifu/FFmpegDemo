package io.reactivex.internal.util;

import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.schedulers.f;
import java.util.concurrent.CountDownLatch;

/* compiled from: BlockingHelper */
public final class c {
    public static void a(CountDownLatch countDownLatch, b bVar) {
        if (countDownLatch.getCount() != 0) {
            try {
                a();
                countDownLatch.await();
            } catch (InterruptedException e) {
                bVar.dispose();
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while waiting for subscription to complete.", e);
            }
        }
    }

    public static void a() {
        if (!xk.a()) {
            return;
        }
        if ((Thread.currentThread() instanceof f) || xk.b()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Attempt to block on a Scheduler ");
            stringBuilder.append(Thread.currentThread().getName());
            stringBuilder.append(" that doesn't support blocking operators as they may lead to deadlock");
            throw new IllegalStateException(stringBuilder.toString());
        }
    }
}
