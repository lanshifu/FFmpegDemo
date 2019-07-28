package io.fabric.sdk.android;

import io.fabric.sdk.android.services.common.u;
import io.fabric.sdk.android.services.concurrency.Priority;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import io.fabric.sdk.android.services.concurrency.c;

/* compiled from: InitializationTask */
class g<Result> extends c<Void, Void, Result> {
    final h<Result> a;

    public g(h<Result> hVar) {
        this.a = hVar;
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        super.a();
        u a = a("onPreExecute");
        try {
            boolean a_ = this.a.a_();
            a.b();
            if (a_) {
                return;
            }
        } catch (UnmetDependencyException e) {
            throw e;
        } catch (Exception e2) {
            c.g().e("Fabric", "Failure onPreExecute()", e2);
            a.b();
        } catch (Throwable th) {
            a.b();
            a(true);
        }
        a(true);
    }

    /* Access modifiers changed, original: protected|varargs */
    public Result a(Void... voidArr) {
        u a = a("doInBackground");
        Result e = !e() ? this.a.e() : null;
        a.b();
        return e;
    }

    /* Access modifiers changed, original: protected */
    public void a(Result result) {
        this.a.a((Object) result);
        this.a.h.a((Object) result);
    }

    /* Access modifiers changed, original: protected */
    public void b(Result result) {
        this.a.b((Object) result);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a.b());
        stringBuilder.append(" Initialization was cancelled");
        this.a.h.a(new InitializationException(stringBuilder.toString()));
    }

    public Priority b() {
        return Priority.HIGH;
    }

    private u a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a.b());
        stringBuilder.append(".");
        stringBuilder.append(str);
        u uVar = new u(stringBuilder.toString(), "KitInitialization");
        uVar.a();
        return uVar;
    }
}
