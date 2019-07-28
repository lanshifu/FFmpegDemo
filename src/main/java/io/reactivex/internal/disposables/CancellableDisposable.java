package io.reactivex.internal.disposables;

import defpackage.wk;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.a;
import java.util.concurrent.atomic.AtomicReference;

public final class CancellableDisposable extends AtomicReference<wk> implements b {
    private static final long serialVersionUID = 5718521705281392066L;

    public CancellableDisposable(wk wkVar) {
        super(wkVar);
    }

    public boolean isDisposed() {
        return get() == null;
    }

    public void dispose() {
        if (get() != null) {
            wk wkVar = (wk) getAndSet(null);
            if (wkVar != null) {
                try {
                    wkVar.a();
                } catch (Exception e) {
                    a.b(e);
                    xk.a(e);
                }
            }
        }
    }
}
