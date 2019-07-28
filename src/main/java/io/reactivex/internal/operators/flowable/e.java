package io.reactivex.internal.operators.flowable;

import defpackage.wm;
import defpackage.ww;

/* compiled from: FlowableMap */
public final class e<T, U> extends a<T, U> {
    final wm<? super T, ? extends U> c;

    /* compiled from: FlowableMap */
    static final class a<T, U> extends io.reactivex.internal.subscribers.a<T, U> {
        final wm<? super T, ? extends U> a;

        a(ww<? super U> wwVar, wm<? super T, ? extends U> wmVar) {
            super(wwVar);
            this.a = wmVar;
        }

        public void onNext(T t) {
            if (!this.e) {
                if (this.f != 0) {
                    this.b.onNext(null);
                    return;
                }
                try {
                    this.b.onNext(io.reactivex.internal.functions.a.a(this.a.apply(t), "The mapper function returned a null value."));
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        public boolean a(T t) {
            if (this.e) {
                return false;
            }
            try {
                return this.b.a(io.reactivex.internal.functions.a.a(this.a.apply(t), "The mapper function returned a null value."));
            } catch (Throwable th) {
                a(th);
                return true;
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public U poll() throws Exception {
            Object poll = this.d.poll();
            return poll != null ? io.reactivex.internal.functions.a.a(this.a.apply(poll), "The mapper function returned a null value.") : null;
        }
    }

    /* compiled from: FlowableMap */
    static final class b<T, U> extends io.reactivex.internal.subscribers.b<T, U> {
        final wm<? super T, ? extends U> a;

        b(aar<? super U> aar, wm<? super T, ? extends U> wmVar) {
            super(aar);
            this.a = wmVar;
        }

        public void onNext(T t) {
            if (!this.e) {
                if (this.f != 0) {
                    this.b.onNext(null);
                    return;
                }
                try {
                    this.b.onNext(io.reactivex.internal.functions.a.a(this.a.apply(t), "The mapper function returned a null value."));
                } catch (Throwable th) {
                    a(th);
                }
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public U poll() throws Exception {
            Object poll = this.d.poll();
            return poll != null ? io.reactivex.internal.functions.a.a(this.a.apply(poll), "The mapper function returned a null value.") : null;
        }
    }

    public e(io.reactivex.e<T> eVar, wm<? super T, ? extends U> wmVar) {
        super(eVar);
        this.c = wmVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(aar<? super U> aar) {
        if (aar instanceof ww) {
            this.b.a(new a((ww) aar, this.c));
        } else {
            this.b.a(new b(aar, this.c));
        }
    }
}
