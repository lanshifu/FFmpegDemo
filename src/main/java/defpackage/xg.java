package defpackage;

import io.reactivex.a;
import io.reactivex.b;
import io.reactivex.internal.disposables.EmptyDisposable;

/* compiled from: CompletableError */
/* renamed from: xg */
public final class xg extends a {
    final Throwable a;

    public xg(Throwable th) {
        this.a = th;
    }

    /* Access modifiers changed, original: protected */
    public void b(b bVar) {
        EmptyDisposable.error(this.a, bVar);
    }
}
