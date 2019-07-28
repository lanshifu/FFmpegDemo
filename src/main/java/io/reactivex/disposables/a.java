package io.reactivex.disposables;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.i;
import java.util.ArrayList;

/* compiled from: CompositeDisposable */
public final class a implements b, io.reactivex.internal.disposables.a {
    i<b> a;
    volatile boolean b;

    public void dispose() {
        if (!this.b) {
            synchronized (this) {
                if (this.b) {
                    return;
                }
                this.b = true;
                i iVar = this.a;
                this.a = null;
                a(iVar);
            }
        }
    }

    public boolean isDisposed() {
        return this.b;
    }

    public boolean a(b bVar) {
        io.reactivex.internal.functions.a.a((Object) bVar, "d is null");
        if (!this.b) {
            synchronized (this) {
                if (!this.b) {
                    i iVar = this.a;
                    if (iVar == null) {
                        iVar = new i();
                        this.a = iVar;
                    }
                    iVar.a((Object) bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    public boolean b(b bVar) {
        if (!c(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    /* JADX WARNING: Missing block: B:18:0x0021, code skipped:
            return false;
     */
    public boolean c(io.reactivex.disposables.b r3) {
        /*
        r2 = this;
        r0 = "Disposable item is null";
        io.reactivex.internal.functions.a.a(r3, r0);
        r0 = r2.b;
        r1 = 0;
        if (r0 == 0) goto L_0x000b;
    L_0x000a:
        return r1;
    L_0x000b:
        monitor-enter(r2);
        r0 = r2.b;	 Catch:{ all -> 0x0022 }
        if (r0 == 0) goto L_0x0012;
    L_0x0010:
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
        return r1;
    L_0x0012:
        r0 = r2.a;	 Catch:{ all -> 0x0022 }
        if (r0 == 0) goto L_0x0020;
    L_0x0016:
        r3 = r0.b(r3);	 Catch:{ all -> 0x0022 }
        if (r3 != 0) goto L_0x001d;
    L_0x001c:
        goto L_0x0020;
    L_0x001d:
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
        r3 = 1;
        return r3;
    L_0x0020:
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
        return r1;
    L_0x0022:
        r3 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0022 }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.disposables.a.c(io.reactivex.disposables.b):boolean");
    }

    /* JADX WARNING: Missing block: B:13:0x0016, code skipped:
            return r1;
     */
    public int a() {
        /*
        r2 = this;
        r0 = r2.b;
        r1 = 0;
        if (r0 == 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        monitor-enter(r2);
        r0 = r2.b;	 Catch:{ all -> 0x0017 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r2);	 Catch:{ all -> 0x0017 }
        return r1;
    L_0x000d:
        r0 = r2.a;	 Catch:{ all -> 0x0017 }
        if (r0 == 0) goto L_0x0015;
    L_0x0011:
        r1 = r0.c();	 Catch:{ all -> 0x0017 }
    L_0x0015:
        monitor-exit(r2);	 Catch:{ all -> 0x0017 }
        return r1;
    L_0x0017:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0017 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.disposables.a.a():int");
    }

    /* Access modifiers changed, original: 0000 */
    public void a(i<b> iVar) {
        if (iVar != null) {
            Iterable iterable = null;
            for (Object obj : iVar.b()) {
                if (obj instanceof b) {
                    try {
                        ((b) obj).dispose();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        if (iterable == null) {
                            iterable = new ArrayList();
                        }
                        iterable.add(th);
                    }
                }
            }
            if (iterable == null) {
                return;
            }
            if (iterable.size() == 1) {
                throw ExceptionHelper.a((Throwable) iterable.get(0));
            }
            throw new CompositeException(iterable);
        }
    }
}
