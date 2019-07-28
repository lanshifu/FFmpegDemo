package io.reactivex.internal.operators.observable;

import defpackage.wm;
import defpackage.wy;
import defpackage.xd;
import defpackage.xk;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.queue.a;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.p;
import io.reactivex.r;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSwitchMap<T, R> extends a<T, R> {
    final wm<? super T, ? extends p<? extends R>> b;
    final int c;
    final boolean d;

    static final class SwitchMapInnerObserver<T, R> extends AtomicReference<b> implements r<R> {
        private static final long serialVersionUID = 3837284832786408377L;
        final int bufferSize;
        volatile boolean done;
        final long index;
        final SwitchMapObserver<T, R> parent;
        volatile xd<R> queue;

        SwitchMapInnerObserver(SwitchMapObserver<T, R> switchMapObserver, long j, int i) {
            this.parent = switchMapObserver;
            this.index = j;
            this.bufferSize = i;
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                if (bVar instanceof wy) {
                    wy wyVar = (wy) bVar;
                    int requestFusion = wyVar.requestFusion(7);
                    if (requestFusion == 1) {
                        this.queue = wyVar;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.queue = wyVar;
                        return;
                    }
                }
                this.queue = new a(this.bufferSize);
            }
        }

        public void onNext(R r) {
            if (this.index == this.parent.unique) {
                if (r != null) {
                    this.queue.offer(r);
                }
                this.parent.drain();
            }
        }

        public void onError(Throwable th) {
            this.parent.innerError(this, th);
        }

        public void onComplete() {
            if (this.index == this.parent.unique) {
                this.done = true;
                this.parent.drain();
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this);
        }
    }

    static final class SwitchMapObserver<T, R> extends AtomicInteger implements b, r<T> {
        static final SwitchMapInnerObserver<Object, Object> CANCELLED = new SwitchMapInnerObserver(null, -1, 1);
        private static final long serialVersionUID = -3491074160481096299L;
        final AtomicReference<SwitchMapInnerObserver<T, R>> active = new AtomicReference();
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final r<? super R> downstream;
        final AtomicThrowable errors;
        final wm<? super T, ? extends p<? extends R>> mapper;
        volatile long unique;
        b upstream;

        static {
            CANCELLED.cancel();
        }

        SwitchMapObserver(r<? super R> rVar, wm<? super T, ? extends p<? extends R>> wmVar, int i, boolean z) {
            this.downstream = rVar;
            this.mapper = wmVar;
            this.bufferSize = i;
            this.delayErrors = z;
            this.errors = new AtomicThrowable();
        }

        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long j = this.unique + 1;
            this.unique = j;
            SwitchMapInnerObserver switchMapInnerObserver = (SwitchMapInnerObserver) this.active.get();
            if (switchMapInnerObserver != null) {
                switchMapInnerObserver.cancel();
            }
            try {
                p pVar = (p) io.reactivex.internal.functions.a.a(this.mapper.apply(t), "The ObservableSource returned is null");
                switchMapInnerObserver = new SwitchMapInnerObserver(this, j, this.bufferSize);
                SwitchMapInnerObserver switchMapInnerObserver2;
                do {
                    switchMapInnerObserver2 = (SwitchMapInnerObserver) this.active.get();
                    if (switchMapInnerObserver2 == CANCELLED) {
                        break;
                    }
                } while (!this.active.compareAndSet(switchMapInnerObserver2, switchMapInnerObserver));
                pVar.subscribe(switchMapInnerObserver);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.upstream.dispose();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (this.done || !this.errors.addThrowable(th)) {
                xk.a(th);
                return;
            }
            if (!this.delayErrors) {
                disposeInner();
            }
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                disposeInner();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* Access modifiers changed, original: 0000 */
        public void disposeInner() {
            if (((SwitchMapInnerObserver) this.active.get()) != CANCELLED) {
                SwitchMapInnerObserver switchMapInnerObserver = (SwitchMapInnerObserver) this.active.getAndSet(CANCELLED);
                if (switchMapInnerObserver != CANCELLED && switchMapInnerObserver != null) {
                    switchMapInnerObserver.cancel();
                }
            }
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x00e3  */
        public void drain() {
            /*
            r13 = this;
            r0 = r13.getAndIncrement();
            if (r0 == 0) goto L_0x0007;
        L_0x0006:
            return;
        L_0x0007:
            r0 = r13.downstream;
            r1 = r13.active;
            r2 = r13.delayErrors;
            r3 = 1;
            r4 = 1;
        L_0x000f:
            r5 = r13.cancelled;
            if (r5 == 0) goto L_0x0014;
        L_0x0013:
            return;
        L_0x0014:
            r5 = r13.done;
            r6 = 0;
            if (r5 == 0) goto L_0x0052;
        L_0x0019:
            r5 = r1.get();
            if (r5 != 0) goto L_0x0021;
        L_0x001f:
            r5 = 1;
            goto L_0x0022;
        L_0x0021:
            r5 = 0;
        L_0x0022:
            if (r2 == 0) goto L_0x0038;
        L_0x0024:
            if (r5 == 0) goto L_0x0052;
        L_0x0026:
            r1 = r13.errors;
            r1 = r1.get();
            r1 = (java.lang.Throwable) r1;
            if (r1 == 0) goto L_0x0034;
        L_0x0030:
            r0.onError(r1);
            goto L_0x0037;
        L_0x0034:
            r0.onComplete();
        L_0x0037:
            return;
        L_0x0038:
            r7 = r13.errors;
            r7 = r7.get();
            r7 = (java.lang.Throwable) r7;
            if (r7 == 0) goto L_0x004c;
        L_0x0042:
            r1 = r13.errors;
            r1 = r1.terminate();
            r0.onError(r1);
            return;
        L_0x004c:
            if (r5 == 0) goto L_0x0052;
        L_0x004e:
            r0.onComplete();
            return;
        L_0x0052:
            r5 = r1.get();
            r5 = (io.reactivex.internal.operators.observable.ObservableSwitchMap.SwitchMapInnerObserver) r5;
            if (r5 == 0) goto L_0x00e9;
        L_0x005a:
            r7 = r5.queue;
            if (r7 == 0) goto L_0x00e9;
        L_0x005e:
            r8 = r5.done;
            r9 = 0;
            if (r8 == 0) goto L_0x0089;
        L_0x0063:
            r8 = r7.isEmpty();
            if (r2 == 0) goto L_0x006f;
        L_0x0069:
            if (r8 == 0) goto L_0x0089;
        L_0x006b:
            r1.compareAndSet(r5, r9);
            goto L_0x000f;
        L_0x006f:
            r10 = r13.errors;
            r10 = r10.get();
            r10 = (java.lang.Throwable) r10;
            if (r10 == 0) goto L_0x0083;
        L_0x0079:
            r1 = r13.errors;
            r1 = r1.terminate();
            r0.onError(r1);
            return;
        L_0x0083:
            if (r8 == 0) goto L_0x0089;
        L_0x0085:
            r1.compareAndSet(r5, r9);
            goto L_0x000f;
        L_0x0089:
            r8 = 0;
        L_0x008a:
            r10 = r13.cancelled;
            if (r10 == 0) goto L_0x008f;
        L_0x008e:
            return;
        L_0x008f:
            r10 = r1.get();
            if (r5 == r10) goto L_0x0097;
        L_0x0095:
            r8 = 1;
            goto L_0x00e1;
        L_0x0097:
            if (r2 != 0) goto L_0x00ad;
        L_0x0099:
            r10 = r13.errors;
            r10 = r10.get();
            r10 = (java.lang.Throwable) r10;
            if (r10 == 0) goto L_0x00ad;
        L_0x00a3:
            r1 = r13.errors;
            r1 = r1.terminate();
            r0.onError(r1);
            return;
        L_0x00ad:
            r10 = r5.done;
            r11 = r7.poll();	 Catch:{ Throwable -> 0x00b4 }
            goto L_0x00d2;
        L_0x00b4:
            r8 = move-exception;
            io.reactivex.exceptions.a.b(r8);
            r11 = r13.errors;
            r11.addThrowable(r8);
            r1.compareAndSet(r5, r9);
            if (r2 != 0) goto L_0x00cd;
        L_0x00c2:
            r13.disposeInner();
            r8 = r13.upstream;
            r8.dispose();
            r13.done = r3;
            goto L_0x00d0;
        L_0x00cd:
            r5.cancel();
        L_0x00d0:
            r11 = r9;
            r8 = 1;
        L_0x00d2:
            if (r11 != 0) goto L_0x00d6;
        L_0x00d4:
            r12 = 1;
            goto L_0x00d7;
        L_0x00d6:
            r12 = 0;
        L_0x00d7:
            if (r10 == 0) goto L_0x00df;
        L_0x00d9:
            if (r12 == 0) goto L_0x00df;
        L_0x00db:
            r1.compareAndSet(r5, r9);
            goto L_0x0095;
        L_0x00df:
            if (r12 == 0) goto L_0x00e5;
        L_0x00e1:
            if (r8 == 0) goto L_0x00e9;
        L_0x00e3:
            goto L_0x000f;
        L_0x00e5:
            r0.onNext(r11);
            goto L_0x008a;
        L_0x00e9:
            r4 = -r4;
            r4 = r13.addAndGet(r4);
            if (r4 != 0) goto L_0x000f;
        L_0x00f0:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableSwitchMap$SwitchMapObserver.drain():void");
        }

        /* Access modifiers changed, original: 0000 */
        public void innerError(SwitchMapInnerObserver<T, R> switchMapInnerObserver, Throwable th) {
            if (switchMapInnerObserver.index == this.unique && this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.upstream.dispose();
                }
                switchMapInnerObserver.done = true;
                drain();
                return;
            }
            xk.a(th);
        }
    }

    public ObservableSwitchMap(p<T> pVar, wm<? super T, ? extends p<? extends R>> wmVar, int i, boolean z) {
        super(pVar);
        this.b = wmVar;
        this.c = i;
        this.d = z;
    }

    public void subscribeActual(r<? super R> rVar) {
        if (!ObservableScalarXMap.a(this.a, rVar, this.b)) {
            this.a.subscribe(new SwitchMapObserver(rVar, this.b, this.c, this.d));
        }
    }
}
