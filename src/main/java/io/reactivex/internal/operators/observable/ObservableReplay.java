package io.reactivex.internal.operators.observable;

import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import defpackage.wl;
import defpackage.wm;
import defpackage.xi;
import defpackage.xk;
import defpackage.xm;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.k;
import io.reactivex.p;
import io.reactivex.r;
import io.reactivex.s;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableReplay<T> extends xi<T> implements io.reactivex.internal.disposables.c {
    static final a e = new i();
    final p<T> a;
    final AtomicReference<ReplayObserver<T>> b;
    final a<T> c;
    final p<T> d;

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final Object value;

        Node(Object obj) {
            this.value = obj;
        }
    }

    interface a<T> {
        e<T> a();
    }

    interface e<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerDisposable<T> innerDisposable);
    }

    static abstract class BoundedReplayBuffer<T> extends AtomicReference<Node> implements e<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        int size;
        Node tail;

        /* Access modifiers changed, original: 0000 */
        public Object enterTransform(Object obj) {
            return obj;
        }

        /* Access modifiers changed, original: 0000 */
        public Object leaveTransform(Object obj) {
            return obj;
        }

        public abstract void truncate();

        BoundedReplayBuffer() {
            Node node = new Node(null);
            this.tail = node;
            set(node);
        }

        /* Access modifiers changed, original: final */
        public final void addLast(Node node) {
            this.tail.set(node);
            this.tail = node;
            this.size++;
        }

        /* Access modifiers changed, original: final */
        public final void removeFirst() {
            Node node = (Node) ((Node) get()).get();
            this.size--;
            setFirst(node);
        }

        /* Access modifiers changed, original: final */
        public final void trimHead() {
            Node node = (Node) get();
            if (node.value != null) {
                Node node2 = new Node(null);
                node2.lazySet(node.get());
                set(node2);
            }
        }

        /* Access modifiers changed, original: final */
        public final void removeSome(int i) {
            Node node = (Node) get();
            while (i > 0) {
                node = (Node) node.get();
                i--;
                this.size--;
            }
            setFirst(node);
        }

        /* Access modifiers changed, original: final */
        public final void setFirst(Node node) {
            set(node);
        }

        public final void next(T t) {
            addLast(new Node(enterTransform(NotificationLite.next(t))));
            truncate();
        }

        public final void error(Throwable th) {
            addLast(new Node(enterTransform(NotificationLite.error(th))));
            truncateFinal();
        }

        public final void complete() {
            addLast(new Node(enterTransform(NotificationLite.complete())));
            truncateFinal();
        }

        public final void replay(InnerDisposable<T> innerDisposable) {
            if (innerDisposable.getAndIncrement() == 0) {
                int i = 1;
                do {
                    Node node = (Node) innerDisposable.index();
                    if (node == null) {
                        node = getHead();
                        innerDisposable.index = node;
                    }
                    while (!innerDisposable.isDisposed()) {
                        Node node2 = (Node) node.get();
                        if (node2 == null) {
                            innerDisposable.index = node;
                            i = innerDisposable.addAndGet(-i);
                        } else if (NotificationLite.accept(leaveTransform(node2.value), innerDisposable.child)) {
                            innerDisposable.index = null;
                            return;
                        } else {
                            node = node2;
                        }
                    }
                    return;
                } while (i != 0);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void truncateFinal() {
            trimHead();
        }

        /* Access modifiers changed, original: final */
        public final void collect(Collection<? super T> collection) {
            Node head = getHead();
            while (true) {
                head = (Node) head.get();
                if (head != null) {
                    Object leaveTransform = leaveTransform(head.value);
                    if (!NotificationLite.isComplete(leaveTransform) && !NotificationLite.isError(leaveTransform)) {
                        collection.add(NotificationLite.getValue(leaveTransform));
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        /* Access modifiers changed, original: 0000 */
        public boolean hasError() {
            return this.tail.value != null && NotificationLite.isError(leaveTransform(this.tail.value));
        }

        /* Access modifiers changed, original: 0000 */
        public boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.isComplete(leaveTransform(this.tail.value));
        }

        /* Access modifiers changed, original: 0000 */
        public Node getHead() {
            return (Node) get();
        }
    }

    static final class InnerDisposable<T> extends AtomicInteger implements io.reactivex.disposables.b {
        private static final long serialVersionUID = 2728361546769921047L;
        volatile boolean cancelled;
        final r<? super T> child;
        Object index;
        final ReplayObserver<T> parent;

        InnerDisposable(ReplayObserver<T> replayObserver, r<? super T> rVar) {
            this.parent = replayObserver;
            this.child = rVar;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.parent.remove(this);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public <U> U index() {
            return this.index;
        }
    }

    static final class ReplayObserver<T> extends AtomicReference<io.reactivex.disposables.b> implements io.reactivex.disposables.b, r<T> {
        static final InnerDisposable[] EMPTY = new InnerDisposable[0];
        static final InnerDisposable[] TERMINATED = new InnerDisposable[0];
        private static final long serialVersionUID = -533785617179540163L;
        final e<T> buffer;
        boolean done;
        final AtomicReference<InnerDisposable[]> observers = new AtomicReference(EMPTY);
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        ReplayObserver(e<T> eVar) {
            this.buffer = eVar;
        }

        public boolean isDisposed() {
            return this.observers.get() == TERMINATED;
        }

        public void dispose() {
            this.observers.set(TERMINATED);
            DisposableHelper.dispose(this);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean add(InnerDisposable<T> innerDisposable) {
            InnerDisposable[] innerDisposableArr;
            InnerDisposable[] innerDisposableArr2;
            do {
                innerDisposableArr = (InnerDisposable[]) this.observers.get();
                if (innerDisposableArr == TERMINATED) {
                    return false;
                }
                int length = innerDisposableArr.length;
                innerDisposableArr2 = new InnerDisposable[(length + 1)];
                System.arraycopy(innerDisposableArr, 0, innerDisposableArr2, 0, length);
                innerDisposableArr2[length] = innerDisposable;
            } while (!this.observers.compareAndSet(innerDisposableArr, innerDisposableArr2));
            return true;
        }

        /* Access modifiers changed, original: 0000 */
        public void remove(InnerDisposable<T> innerDisposable) {
            InnerDisposable[] innerDisposableArr;
            Object obj;
            do {
                innerDisposableArr = (InnerDisposable[]) this.observers.get();
                int length = innerDisposableArr.length;
                if (length != 0) {
                    int i = -1;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (innerDisposableArr[i2].equals(innerDisposable)) {
                            i = i2;
                            break;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            obj = EMPTY;
                        } else {
                            Object obj2 = new InnerDisposable[(length - 1)];
                            System.arraycopy(innerDisposableArr, 0, obj2, 0, i);
                            System.arraycopy(innerDisposableArr, i + 1, obj2, i, (length - i) - 1);
                            obj = obj2;
                        }
                    } else {
                        return;
                    }
                }
                return;
            } while (!this.observers.compareAndSet(innerDisposableArr, obj));
        }

        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.setOnce(this, bVar)) {
                replay();
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                replay();
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                xk.a(th);
                return;
            }
            this.done = true;
            this.buffer.error(th);
            replayFinal();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.buffer.complete();
                replayFinal();
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void replay() {
            for (InnerDisposable replay : (InnerDisposable[]) this.observers.get()) {
                this.buffer.replay(replay);
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void replayFinal() {
            for (InnerDisposable replay : (InnerDisposable[]) this.observers.getAndSet(TERMINATED)) {
                this.buffer.replay(replay);
            }
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements e<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        UnboundedReplayBuffer(int i) {
            super(i);
        }

        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        public void replay(InnerDisposable<T> innerDisposable) {
            if (innerDisposable.getAndIncrement() == 0) {
                r rVar = innerDisposable.child;
                int i = 1;
                while (!innerDisposable.isDisposed()) {
                    int i2 = this.size;
                    Integer num = (Integer) innerDisposable.index();
                    int intValue = num != null ? num.intValue() : 0;
                    while (intValue < i2) {
                        if (!NotificationLite.accept(get(intValue), rVar) && !innerDisposable.isDisposed()) {
                            intValue++;
                        } else {
                            return;
                        }
                    }
                    innerDisposable.index = Integer.valueOf(intValue);
                    i = innerDisposable.addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class b<R> implements wl<io.reactivex.disposables.b> {
        private final ObserverResourceWrapper<R> a;

        b(ObserverResourceWrapper<R> observerResourceWrapper) {
            this.a = observerResourceWrapper;
        }

        /* renamed from: a */
        public void accept(io.reactivex.disposables.b bVar) {
            this.a.setResource(bVar);
        }
    }

    static final class f<T> implements a<T> {
        private final int a;

        f(int i) {
            this.a = i;
        }

        public e<T> a() {
            return new SizeBoundReplayBuffer(this.a);
        }
    }

    static final class g<T> implements p<T> {
        private final AtomicReference<ReplayObserver<T>> a;
        private final a<T> b;

        g(AtomicReference<ReplayObserver<T>> atomicReference, a<T> aVar) {
            this.a = atomicReference;
            this.b = aVar;
        }

        public void subscribe(r<? super T> rVar) {
            ReplayObserver replayObserver;
            while (true) {
                replayObserver = (ReplayObserver) this.a.get();
                if (replayObserver != null) {
                    break;
                }
                ReplayObserver replayObserver2 = new ReplayObserver(this.b.a());
                if (this.a.compareAndSet(null, replayObserver2)) {
                    replayObserver = replayObserver2;
                    break;
                }
            }
            InnerDisposable innerDisposable = new InnerDisposable(replayObserver, rVar);
            rVar.onSubscribe(innerDisposable);
            replayObserver.add(innerDisposable);
            if (innerDisposable.isDisposed()) {
                replayObserver.remove(innerDisposable);
            } else {
                replayObserver.buffer.replay(innerDisposable);
            }
        }
    }

    static final class h<T> implements a<T> {
        private final int a;
        private final long b;
        private final TimeUnit c;
        private final s d;

        h(int i, long j, TimeUnit timeUnit, s sVar) {
            this.a = i;
            this.b = j;
            this.c = timeUnit;
            this.d = sVar;
        }

        public e<T> a() {
            return new SizeAndTimeBoundReplayBuffer(this.a, this.b, this.c, this.d);
        }
    }

    static final class i implements a<Object> {
        i() {
        }

        public e<Object> a() {
            return new UnboundedReplayBuffer(16);
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAge;
        final s scheduler;
        final TimeUnit unit;

        SizeAndTimeBoundReplayBuffer(int i, long j, TimeUnit timeUnit, s sVar) {
            this.scheduler = sVar;
            this.limit = i;
            this.maxAge = j;
            this.unit = timeUnit;
        }

        /* Access modifiers changed, original: 0000 */
        public Object enterTransform(Object obj) {
            return new xm(obj, this.scheduler.a(this.unit), this.unit);
        }

        /* Access modifiers changed, original: 0000 */
        public Object leaveTransform(Object obj) {
            return ((xm) obj).a();
        }

        /* Access modifiers changed, original: 0000 */
        public void truncate() {
            long a = this.scheduler.a(this.unit) - this.maxAge;
            Node node = (Node) get();
            Node node2 = (Node) node.get();
            int i = 0;
            while (true) {
                Node node3 = node2;
                node2 = node;
                node = node3;
                if (node != null) {
                    if (this.size <= this.limit) {
                        if (((xm) node.value).b() > a) {
                            break;
                        }
                        i++;
                        this.size--;
                        node2 = (Node) node.get();
                    } else {
                        i++;
                        this.size--;
                        node2 = (Node) node.get();
                    }
                } else {
                    break;
                }
            }
            if (i != 0) {
                setFirst(node2);
            }
        }

        /* Access modifiers changed, original: 0000 */
        /* JADX WARNING: Removed duplicated region for block: B:14:? A:{SYNTHETIC, RETURN} */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x003e  */
        public void truncateFinal() {
            /*
            r10 = this;
            r0 = r10.scheduler;
            r1 = r10.unit;
            r0 = r0.a(r1);
            r2 = r10.maxAge;
            r0 = r0 - r2;
            r2 = r10.get();
            r2 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r2;
            r3 = r2.get();
            r3 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r3;
            r4 = 0;
        L_0x0018:
            r9 = r3;
            r3 = r2;
            r2 = r9;
            if (r2 == 0) goto L_0x003c;
        L_0x001d:
            r5 = r10.size;
            r6 = 1;
            if (r5 <= r6) goto L_0x003c;
        L_0x0022:
            r5 = r2.value;
            r5 = (defpackage.xm) r5;
            r7 = r5.b();
            r5 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
            if (r5 > 0) goto L_0x003c;
        L_0x002e:
            r4 = r4 + 1;
            r3 = r10.size;
            r3 = r3 - r6;
            r10.size = r3;
            r3 = r2.get();
            r3 = (io.reactivex.internal.operators.observable.ObservableReplay.Node) r3;
            goto L_0x0018;
        L_0x003c:
            if (r4 == 0) goto L_0x0041;
        L_0x003e:
            r10.setFirst(r3);
        L_0x0041:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableReplay$SizeAndTimeBoundReplayBuffer.truncateFinal():void");
        }

        /* Access modifiers changed, original: 0000 */
        public Node getHead() {
            Node node;
            long a = this.scheduler.a(this.unit) - this.maxAge;
            Node node2 = (Node) get();
            Object obj = node2.get();
            while (true) {
                Node node3 = (Node) obj;
                node = node2;
                node2 = node3;
                if (node2 != null) {
                    xm xmVar = (xm) node2.value;
                    if (NotificationLite.isComplete(xmVar.a()) || NotificationLite.isError(xmVar.a()) || xmVar.b() > a) {
                        break;
                    }
                    obj = node2.get();
                } else {
                    break;
                }
            }
            return node;
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        SizeBoundReplayBuffer(int i) {
            this.limit = i;
        }

        /* Access modifiers changed, original: 0000 */
        public void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class c<R, U> extends k<R> {
        private final Callable<? extends xi<U>> a;
        private final wm<? super k<U>, ? extends p<R>> b;

        c(Callable<? extends xi<U>> callable, wm<? super k<U>, ? extends p<R>> wmVar) {
            this.a = callable;
            this.b = wmVar;
        }

        /* Access modifiers changed, original: protected */
        public void subscribeActual(r<? super R> rVar) {
            try {
                xi xiVar = (xi) io.reactivex.internal.functions.a.a(this.a.call(), "The connectableFactory returned a null ConnectableObservable");
                p pVar = (p) io.reactivex.internal.functions.a.a(this.b.apply(xiVar), "The selector returned a null ObservableSource");
                ObserverResourceWrapper observerResourceWrapper = new ObserverResourceWrapper(rVar);
                pVar.subscribe(observerResourceWrapper);
                xiVar.a(new b(observerResourceWrapper));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                EmptyDisposable.error(th, (r) rVar);
            }
        }
    }

    static final class d<T> extends xi<T> {
        private final xi<T> a;
        private final k<T> b;

        d(xi<T> xiVar, k<T> kVar) {
            this.a = xiVar;
            this.b = kVar;
        }

        public void a(wl<? super io.reactivex.disposables.b> wlVar) {
            this.a.a(wlVar);
        }

        /* Access modifiers changed, original: protected */
        public void subscribeActual(r<? super T> rVar) {
            this.b.subscribe((r) rVar);
        }
    }

    public static <U, R> k<R> a(Callable<? extends xi<U>> callable, wm<? super k<U>, ? extends p<R>> wmVar) {
        return xk.a(new c(callable, wmVar));
    }

    public static <T> xi<T> a(xi<T> xiVar, s sVar) {
        return xk.a(new d(xiVar, xiVar.observeOn(sVar)));
    }

    public static <T> xi<T> a(p<? extends T> pVar) {
        return a((p) pVar, e);
    }

    public static <T> xi<T> a(p<T> pVar, int i) {
        if (i == Filter.MAX) {
            return a((p) pVar);
        }
        return a((p) pVar, new f(i));
    }

    public static <T> xi<T> a(p<T> pVar, long j, TimeUnit timeUnit, s sVar) {
        return a(pVar, j, timeUnit, sVar, Filter.MAX);
    }

    public static <T> xi<T> a(p<T> pVar, long j, TimeUnit timeUnit, s sVar, int i) {
        return a((p) pVar, new h(i, j, timeUnit, sVar));
    }

    static <T> xi<T> a(p<T> pVar, a<T> aVar) {
        AtomicReference atomicReference = new AtomicReference();
        return xk.a(new ObservableReplay(new g(atomicReference, aVar), pVar, atomicReference, aVar));
    }

    private ObservableReplay(p<T> pVar, p<T> pVar2, AtomicReference<ReplayObserver<T>> atomicReference, a<T> aVar) {
        this.d = pVar;
        this.a = pVar2;
        this.b = atomicReference;
        this.c = aVar;
    }

    public void a(io.reactivex.disposables.b bVar) {
        this.b.compareAndSet((ReplayObserver) bVar, null);
    }

    /* Access modifiers changed, original: protected */
    public void subscribeActual(r<? super T> rVar) {
        this.d.subscribe(rVar);
    }

    public void a(wl<? super io.reactivex.disposables.b> wlVar) {
        ReplayObserver replayObserver;
        while (true) {
            replayObserver = (ReplayObserver) this.b.get();
            if (replayObserver != null && !replayObserver.isDisposed()) {
                break;
            }
            ReplayObserver replayObserver2 = new ReplayObserver(this.c.a());
            if (this.b.compareAndSet(replayObserver, replayObserver2)) {
                replayObserver = replayObserver2;
                break;
            }
        }
        Object obj = (replayObserver.shouldConnect.get() || !replayObserver.shouldConnect.compareAndSet(false, true)) ? null : 1;
        try {
            wlVar.accept(replayObserver);
            if (obj != null) {
                this.a.subscribe(replayObserver);
            }
        } catch (Throwable th) {
            if (obj != null) {
                replayObserver.shouldConnect.compareAndSet(true, false);
            }
            io.reactivex.exceptions.a.b(th);
            RuntimeException a = ExceptionHelper.a(th);
        }
    }
}
