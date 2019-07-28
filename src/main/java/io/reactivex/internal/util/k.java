package io.reactivex.internal.util;

import defpackage.xc;
import defpackage.xd;
import io.reactivex.disposables.b;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.a;
import io.reactivex.r;

/* compiled from: QueueDrainHelper */
public final class k {
    public static <T, U> void a(xc<T> xcVar, r<? super U> rVar, boolean z, b bVar, h<T, U> hVar) {
        int i = 1;
        while (!a(hVar.b(), xcVar.isEmpty(), rVar, z, xcVar, bVar, hVar)) {
            while (true) {
                boolean b = hVar.b();
                Object poll = xcVar.poll();
                boolean z2 = poll == null;
                if (!a(b, z2, rVar, z, xcVar, bVar, hVar)) {
                    if (z2) {
                        i = hVar.a(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                    hVar.a(rVar, poll);
                } else {
                    return;
                }
            }
        }
    }

    public static <T, U> boolean a(boolean z, boolean z2, r<?> rVar, boolean z3, xd<?> xdVar, b bVar, h<T, U> hVar) {
        if (hVar.a()) {
            xdVar.clear();
            bVar.dispose();
            return true;
        }
        if (z) {
            Throwable e;
            if (!z3) {
                e = hVar.e();
                if (e != null) {
                    xdVar.clear();
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    rVar.onError(e);
                    return true;
                } else if (z2) {
                    if (bVar != null) {
                        bVar.dispose();
                    }
                    rVar.onComplete();
                    return true;
                }
            } else if (z2) {
                if (bVar != null) {
                    bVar.dispose();
                }
                e = hVar.e();
                if (e != null) {
                    rVar.onError(e);
                } else {
                    rVar.onComplete();
                }
                return true;
            }
        }
        return false;
    }

    public static <T> xd<T> a(int i) {
        if (i < 0) {
            return new a(-i);
        }
        return new SpscArrayQueue(i);
    }
}
