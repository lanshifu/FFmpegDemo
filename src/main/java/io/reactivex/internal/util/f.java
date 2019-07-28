package io.reactivex.internal.util;

import defpackage.xk;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: HalfSerializer */
public final class f {
    public static <T> void a(aar<? super T> aar, T t, AtomicInteger atomicInteger, AtomicThrowable atomicThrowable) {
        if (atomicInteger.get() == 0 && atomicInteger.compareAndSet(0, 1)) {
            aar.onNext(t);
            if (atomicInteger.decrementAndGet() != 0) {
                Throwable terminate = atomicThrowable.terminate();
                if (terminate != null) {
                    aar.onError(terminate);
                } else {
                    aar.onComplete();
                }
            }
        }
    }

    public static void a(aar<?> aar, Throwable th, AtomicInteger atomicInteger, AtomicThrowable atomicThrowable) {
        if (!atomicThrowable.addThrowable(th)) {
            xk.a(th);
        } else if (atomicInteger.getAndIncrement() == 0) {
            aar.onError(atomicThrowable.terminate());
        }
    }

    public static void a(aar<?> aar, AtomicInteger atomicInteger, AtomicThrowable atomicThrowable) {
        if (atomicInteger.getAndIncrement() == 0) {
            Throwable terminate = atomicThrowable.terminate();
            if (terminate != null) {
                aar.onError(terminate);
            } else {
                aar.onComplete();
            }
        }
    }

    public static <T> void a(r<? super T> rVar, T t, AtomicInteger atomicInteger, AtomicThrowable atomicThrowable) {
        if (atomicInteger.get() == 0 && atomicInteger.compareAndSet(0, 1)) {
            rVar.onNext(t);
            if (atomicInteger.decrementAndGet() != 0) {
                Throwable terminate = atomicThrowable.terminate();
                if (terminate != null) {
                    rVar.onError(terminate);
                } else {
                    rVar.onComplete();
                }
            }
        }
    }

    public static void a(r<?> rVar, Throwable th, AtomicInteger atomicInteger, AtomicThrowable atomicThrowable) {
        if (!atomicThrowable.addThrowable(th)) {
            xk.a(th);
        } else if (atomicInteger.getAndIncrement() == 0) {
            rVar.onError(atomicThrowable.terminate());
        }
    }

    public static void a(r<?> rVar, AtomicInteger atomicInteger, AtomicThrowable atomicThrowable) {
        if (atomicInteger.getAndIncrement() == 0) {
            Throwable terminate = atomicThrowable.terminate();
            if (terminate != null) {
                rVar.onError(terminate);
            } else {
                rVar.onComplete();
            }
        }
    }
}
