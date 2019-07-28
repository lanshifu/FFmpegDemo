package io.reactivex.internal.operators.observable;

import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.k;
import io.reactivex.r;

public final class ObservableRangeLong extends k<Long> {
    private final long a;
    private final long b;

    static final class RangeDisposable extends BasicIntQueueDisposable<Long> {
        private static final long serialVersionUID = 396518478098735504L;
        final r<? super Long> downstream;
        final long end;
        boolean fused;
        long index;

        RangeDisposable(r<? super Long> rVar, long j, long j2) {
            this.downstream = rVar;
            this.index = j;
            this.end = j2;
        }

        /* Access modifiers changed, original: 0000 */
        public void run() {
            if (!this.fused) {
                r rVar = this.downstream;
                long j = this.end;
                for (long j2 = this.index; j2 != j && get() == 0; j2++) {
                    rVar.onNext(Long.valueOf(j2));
                }
                if (get() == 0) {
                    lazySet(1);
                    rVar.onComplete();
                }
            }
        }

        public Long poll() throws Exception {
            long j = this.index;
            if (j != this.end) {
                this.index = 1 + j;
                return Long.valueOf(j);
            }
            lazySet(1);
            return null;
        }

        public boolean isEmpty() {
            return this.index == this.end;
        }

        public void clear() {
            this.index = this.end;
            lazySet(1);
        }

        public void dispose() {
            set(1);
        }

        public boolean isDisposed() {
            return get() != 0;
        }

        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.fused = true;
            return 1;
        }
    }

    public ObservableRangeLong(long j, long j2) {
        this.a = j;
        this.b = j2;
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super Long> rVar) {
        r<? super Long> rVar2 = rVar;
        RangeDisposable rangeDisposable = new RangeDisposable(rVar2, this.a, this.b + this.a);
        rVar.onSubscribe(rangeDisposable);
        rangeDisposable.run();
    }
}
