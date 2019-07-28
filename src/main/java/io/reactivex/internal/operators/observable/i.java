package io.reactivex.internal.operators.observable;

import defpackage.wf;
import defpackage.wl;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.observers.BlockingObserver;
import io.reactivex.internal.observers.LambdaObserver;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.c;
import io.reactivex.internal.util.d;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: ObservableBlockingSubscribe */
public final class i {
    public static <T> void a(p<? extends T> pVar, r<? super T> rVar) {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        BlockingObserver blockingObserver = new BlockingObserver(linkedBlockingQueue);
        rVar.onSubscribe(blockingObserver);
        pVar.subscribe(blockingObserver);
        while (!blockingObserver.isDisposed()) {
            Object poll = linkedBlockingQueue.poll();
            if (poll == null) {
                try {
                    poll = linkedBlockingQueue.take();
                } catch (InterruptedException e) {
                    blockingObserver.dispose();
                    rVar.onError(e);
                    return;
                }
            }
            if (!blockingObserver.isDisposed() && pVar != BlockingObserver.TERMINATED) {
                if (NotificationLite.acceptFull(poll, (r) rVar)) {
                    break;
                }
            }
            break;
        }
    }

    public static <T> void a(p<? extends T> pVar) {
        d dVar = new d();
        LambdaObserver lambdaObserver = new LambdaObserver(Functions.b(), dVar, dVar, Functions.b());
        pVar.subscribe(lambdaObserver);
        c.a(dVar, lambdaObserver);
        Throwable th = dVar.a;
        if (th != null) {
            throw ExceptionHelper.a(th);
        }
    }

    public static <T> void a(p<? extends T> pVar, wl<? super T> wlVar, wl<? super Throwable> wlVar2, wf wfVar) {
        a.a((Object) wlVar, "onNext is null");
        a.a((Object) wlVar2, "onError is null");
        a.a((Object) wfVar, "onComplete is null");
        a(pVar, new LambdaObserver(wlVar, wlVar2, wfVar, Functions.b()));
    }
}
