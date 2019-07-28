package defpackage;

import io.reactivex.disposables.b;
import io.reactivex.r;

/* compiled from: SimpleRxObserver */
/* renamed from: sh */
public abstract class sh<T> implements r<T> {
    public abstract void accept(T t);

    public void onComplete() {
    }

    public void onSubscribe(b bVar) {
    }

    public void onError(Throwable th) {
        th.printStackTrace();
    }

    public void onNext(T t) {
        accept(t);
    }
}
