package io.reactivex.internal.operators.flowable;

import defpackage.wv;
import defpackage.ww;
import defpackage.xa;
import io.reactivex.e;

/* compiled from: FlowableFilter */
public final class b<T> extends a<T, T> {
    final wv<? super T> c;

    /* compiled from: FlowableFilter */
    static final class a<T> extends io.reactivex.internal.subscribers.a<T, T> {
        final wv<? super T> a;

        a(ww<? super T> wwVar, wv<? super T> wvVar) {
            super(wwVar);
            this.a = wvVar;
        }

        public void onNext(T t) {
            if (!a(t)) {
                this.c.request(1);
            }
        }

        public boolean a(T t) {
            if (this.e) {
                return false;
            }
            if (this.f != 0) {
                return this.b.a(null);
            }
            boolean z = true;
            try {
                if (!(this.a.test(t) && this.b.a(t))) {
                    z = false;
                }
                return z;
            } catch (Throwable th) {
                a(th);
                return true;
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public T poll() throws Exception {
            xa xaVar = this.d;
            wv wvVar = this.a;
            while (true) {
                Object poll = xaVar.poll();
                if (poll == null) {
                    return null;
                }
                if (wvVar.test(poll)) {
                    return poll;
                }
                if (this.f == 2) {
                    xaVar.request(1);
                }
            }
        }
    }

    /* compiled from: FlowableFilter */
    static final class b<T> extends io.reactivex.internal.subscribers.b<T, T> implements ww<T> {
        final wv<? super T> a;

        b(aar<? super T> aar, wv<? super T> wvVar) {
            super(aar);
            this.a = wvVar;
        }

        public void onNext(T t) {
            if (!a(t)) {
                this.c.request(1);
            }
        }

        public boolean a(T t) {
            if (this.e) {
                return false;
            }
            if (this.f != 0) {
                this.b.onNext(null);
                return true;
            }
            try {
                boolean test = this.a.test(t);
                if (test) {
                    this.b.onNext(t);
                }
                return test;
            } catch (Throwable th) {
                a(th);
                return true;
            }
        }

        public int requestFusion(int i) {
            return a(i);
        }

        public T poll() throws Exception {
            xa xaVar = this.d;
            wv wvVar = this.a;
            while (true) {
                Object poll = xaVar.poll();
                if (poll == null) {
                    return null;
                }
                if (wvVar.test(poll)) {
                    return poll;
                }
                if (this.f == 2) {
                    xaVar.request(1);
                }
            }
        }
    }

    public b(e<T> eVar, wv<? super T> wvVar) {
        super(eVar);
        this.c = wvVar;
    }

    /* Access modifiers changed, original: protected */
    public void b(aar<? super T> aar) {
        if (aar instanceof ww) {
            this.b.a(new a((ww) aar, this.c));
        } else {
            this.b.a(new b(aar, this.c));
        }
    }
}
