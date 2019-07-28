package io.reactivex.internal.operators.observable;

import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.k;
import io.reactivex.r;

public final class ObservableRange extends k<Integer> {
    private final int a;
    private final long b;

    static final class RangeDisposable extends BasicIntQueueDisposable<Integer> {
        private static final long serialVersionUID = 396518478098735504L;
        final r<? super Integer> downstream;
        final long end;
        boolean fused;
        long index;

        RangeDisposable(r<? super Integer> rVar, long j, long j2) {
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
                    rVar.onNext(Integer.valueOf((int) j2));
                }
                if (get() == 0) {
                    lazySet(1);
                    rVar.onComplete();
                }
            }
        }

        public Integer poll() throws Exception {
            long j = this.index;
            if (j != this.end) {
                this.index = 1 + j;
                return Integer.valueOf((int) j);
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

    public ObservableRange(int i, int i2) {
        this.a = i;
        this.b = ((long) i) + ((long) i2);
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super Integer> rVar) {
        RangeDisposable rangeDisposable = new RangeDisposable(rVar, (long) this.a, this.b);
        rVar.onSubscribe(rangeDisposable);
        rangeDisposable.run();
    }
}
