package io.fabric.sdk.android.services.concurrency;

import io.fabric.sdk.android.services.concurrency.AsyncTask.Status;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* compiled from: PriorityAsyncTask */
public abstract class c<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> implements a<i>, f, i {
    private final g a = new g();

    /* compiled from: PriorityAsyncTask */
    private static class a<Result> implements Executor {
        private final Executor a;
        private final c b;

        public a(Executor executor, c cVar) {
            this.a = executor;
            this.b = cVar;
        }

        public void execute(Runnable runnable) {
            this.a.execute(new e<Result>(runnable, null) {
                public <T extends a<i> & f & i> T a() {
                    return a.this.b;
                }
            });
        }
    }

    public final void a(ExecutorService executorService, Params... paramsArr) {
        super.a(new a(executorService, this), (Object[]) paramsArr);
    }

    public int compareTo(Object obj) {
        return Priority.compareTo(this, obj);
    }

    /* renamed from: a */
    public void c(i iVar) {
        if (h_() == Status.PENDING) {
            ((a) ((f) g())).c(iVar);
            return;
        }
        throw new IllegalStateException("Must not add Dependency after task is running");
    }

    public Collection<i> c() {
        return ((a) ((f) g())).c();
    }

    public boolean d() {
        return ((a) ((f) g())).d();
    }

    public Priority b() {
        return ((f) g()).b();
    }

    public void b(boolean z) {
        ((i) ((f) g())).b(z);
    }

    public boolean f() {
        return ((i) ((f) g())).f();
    }

    public void a(Throwable th) {
        ((i) ((f) g())).a(th);
    }

    public <T extends a<i> & f & i> T g() {
        return this.a;
    }
}
