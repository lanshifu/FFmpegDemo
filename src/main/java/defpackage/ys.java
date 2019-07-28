package defpackage;

import java.util.Iterator;
import kotlin.jvm.internal.f;

/* compiled from: Sequences.kt */
/* renamed from: ys */
public final class ys<T, R> implements yl<R> {
    private final yl<T> a;
    private final xw<T, R> b;

    /* compiled from: Sequences.kt */
    /* renamed from: ys$a */
    public static final class a implements Iterator<R> {
        final /* synthetic */ ys a;
        private final Iterator<T> b;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        a(ys ysVar) {
            this.a = ysVar;
            this.b = ysVar.a.a();
        }

        public R next() {
            return this.a.b.invoke(this.b.next());
        }

        public boolean hasNext() {
            return this.b.hasNext();
        }
    }

    public ys(yl<? extends T> ylVar, xw<? super T, ? extends R> xwVar) {
        f.b(ylVar, "sequence");
        f.b(xwVar, "transformer");
        this.a = ylVar;
        this.b = xwVar;
    }

    public Iterator<R> a() {
        return new a(this);
    }
}
