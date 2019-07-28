package org.greenrobot.eventbus;

/* compiled from: AsyncPoster */
class a implements Runnable, k {
    private final j a = new j();
    private final c b;

    a(c cVar) {
        this.b = cVar;
    }

    public void a(p pVar, Object obj) {
        this.a.a(i.a(pVar, obj));
        this.b.b().execute(this);
    }

    public void run() {
        i a = this.a.a();
        if (a != null) {
            this.b.a(a);
            return;
        }
        throw new IllegalStateException("No pending post available");
    }
}
