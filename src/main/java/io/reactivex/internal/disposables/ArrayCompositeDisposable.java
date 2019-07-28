package io.reactivex.internal.disposables;

import io.reactivex.disposables.b;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class ArrayCompositeDisposable extends AtomicReferenceArray<b> implements b {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeDisposable(int i) {
        super(i);
    }

    public boolean setResource(int i, b bVar) {
        b bVar2;
        do {
            bVar2 = (b) get(i);
            if (bVar2 == DisposableHelper.DISPOSED) {
                bVar.dispose();
                return false;
            }
        } while (!compareAndSet(i, bVar2, bVar));
        if (bVar2 != null) {
            bVar2.dispose();
        }
        return true;
    }

    public b replaceResource(int i, b bVar) {
        b bVar2;
        do {
            bVar2 = (b) get(i);
            if (bVar2 == DisposableHelper.DISPOSED) {
                bVar.dispose();
                return null;
            }
        } while (!compareAndSet(i, bVar2, bVar));
        return bVar2;
    }

    public void dispose() {
        int i = 0;
        if (get(0) != DisposableHelper.DISPOSED) {
            int length = length();
            while (i < length) {
                if (((b) get(i)) != DisposableHelper.DISPOSED) {
                    b bVar = (b) getAndSet(i, DisposableHelper.DISPOSED);
                    if (!(bVar == DisposableHelper.DISPOSED || bVar == null)) {
                        bVar.dispose();
                    }
                }
                i++;
            }
        }
    }

    public boolean isDisposed() {
        return get(0) == DisposableHelper.DISPOSED;
    }
}
