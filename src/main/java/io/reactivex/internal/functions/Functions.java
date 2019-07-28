package io.reactivex.internal.functions;

import defpackage.wf;
import defpackage.wg;
import defpackage.wh;
import defpackage.wj;
import defpackage.wl;
import defpackage.wm;
import defpackage.wn;
import defpackage.wo;
import defpackage.wp;
import defpackage.wq;
import defpackage.wr;
import defpackage.ws;
import defpackage.wt;
import defpackage.wu;
import defpackage.wv;
import defpackage.xk;
import defpackage.xm;
import io.reactivex.exceptions.OnErrorNotImplementedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public final class Functions {
    static final wm<Object, Object> a = new u();
    public static final Runnable b = new q();
    public static final wf c = new n();
    static final wl<Object> d = new o();
    public static final wl<Throwable> e = new s();
    public static final wl<Throwable> f = new ad();
    public static final wu g = new p();
    static final wv<Object> h = new ai();
    static final wv<Object> i = new t();
    static final Callable<Object> j = new ac();
    static final Comparator<Object> k = new y();
    public static final wl<aas> l = new x();

    enum HashSetCallable implements Callable<Set<Object>> {
        INSTANCE;

        public Set<Object> call() throws Exception {
            return new HashSet();
        }
    }

    enum NaturalComparator implements Comparator<Object> {
        INSTANCE;

        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    static final class ac implements Callable<Object> {
        public Object call() {
            return null;
        }

        ac() {
        }
    }

    static final class j<T> implements Callable<List<T>> {
        final int a;

        j(int i) {
            this.a = i;
        }

        /* renamed from: a */
        public List<T> call() throws Exception {
            return new ArrayList(this.a);
        }
    }

    static final class q implements Runnable {
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }

        q() {
        }
    }

    static final class y implements Comparator<Object> {
        y() {
        }

        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    static final class a<T> implements wl<T> {
        final wf a;

        a(wf wfVar) {
            this.a = wfVar;
        }

        public void accept(T t) throws Exception {
            this.a.a();
        }
    }

    static final class aa<T> implements wl<Throwable> {
        final wl<? super io.reactivex.j<T>> a;

        aa(wl<? super io.reactivex.j<T>> wlVar) {
            this.a = wlVar;
        }

        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            this.a.accept(io.reactivex.j.a(th));
        }
    }

    static final class ab<T> implements wl<T> {
        final wl<? super io.reactivex.j<T>> a;

        ab(wl<? super io.reactivex.j<T>> wlVar) {
            this.a = wlVar;
        }

        public void accept(T t) throws Exception {
            this.a.accept(io.reactivex.j.a((Object) t));
        }
    }

    static final class ad implements wl<Throwable> {
        ad() {
        }

        /* renamed from: a */
        public void accept(Throwable th) {
            xk.a(new OnErrorNotImplementedException(th));
        }
    }

    static final class ae<T> implements wm<T, xm<T>> {
        final TimeUnit a;
        final io.reactivex.s b;

        ae(TimeUnit timeUnit, io.reactivex.s sVar) {
            this.a = timeUnit;
            this.b = sVar;
        }

        /* renamed from: a */
        public xm<T> apply(T t) throws Exception {
            return new xm(t, this.b.a(this.a), this.a);
        }
    }

    static final class af<K, T> implements wg<Map<K, T>, T> {
        private final wm<? super T, ? extends K> a;

        af(wm<? super T, ? extends K> wmVar) {
            this.a = wmVar;
        }

        /* renamed from: a */
        public void accept(Map<K, T> map, T t) throws Exception {
            map.put(this.a.apply(t), t);
        }
    }

    static final class ag<K, V, T> implements wg<Map<K, V>, T> {
        private final wm<? super T, ? extends V> a;
        private final wm<? super T, ? extends K> b;

        ag(wm<? super T, ? extends V> wmVar, wm<? super T, ? extends K> wmVar2) {
            this.a = wmVar;
            this.b = wmVar2;
        }

        /* renamed from: a */
        public void accept(Map<K, V> map, T t) throws Exception {
            map.put(this.b.apply(t), this.a.apply(t));
        }
    }

    static final class ah<K, V, T> implements wg<Map<K, Collection<V>>, T> {
        private final wm<? super K, ? extends Collection<? super V>> a;
        private final wm<? super T, ? extends V> b;
        private final wm<? super T, ? extends K> c;

        ah(wm<? super K, ? extends Collection<? super V>> wmVar, wm<? super T, ? extends V> wmVar2, wm<? super T, ? extends K> wmVar3) {
            this.a = wmVar;
            this.b = wmVar2;
            this.c = wmVar3;
        }

        /* renamed from: a */
        public void accept(Map<K, Collection<V>> map, T t) throws Exception {
            Object apply = this.c.apply(t);
            Collection collection = (Collection) map.get(apply);
            if (collection == null) {
                collection = (Collection) this.a.apply(apply);
                map.put(apply, collection);
            }
            collection.add(this.b.apply(t));
        }
    }

    static final class ai implements wv<Object> {
        public boolean test(Object obj) {
            return true;
        }

        ai() {
        }
    }

    static final class b<T1, T2, R> implements wm<Object[], R> {
        final wh<? super T1, ? super T2, ? extends R> a;

        b(wh<? super T1, ? super T2, ? extends R> whVar) {
            this.a = whVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 2) {
                return this.a.apply(objArr[0], objArr[1]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Array of size 2 expected but got ");
            stringBuilder.append(objArr.length);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    static final class c<T1, T2, T3, R> implements wm<Object[], R> {
        final wn<T1, T2, T3, R> a;

        c(wn<T1, T2, T3, R> wnVar) {
            this.a = wnVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 3) {
                return this.a.a(objArr[0], objArr[1], objArr[2]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Array of size 3 expected but got ");
            stringBuilder.append(objArr.length);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    static final class d<T1, T2, T3, T4, R> implements wm<Object[], R> {
        final wo<T1, T2, T3, T4, R> a;

        d(wo<T1, T2, T3, T4, R> woVar) {
            this.a = woVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 4) {
                return this.a.apply(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Array of size 4 expected but got ");
            stringBuilder.append(objArr.length);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    static final class e<T1, T2, T3, T4, T5, R> implements wm<Object[], R> {
        private final wp<T1, T2, T3, T4, T5, R> a;

        e(wp<T1, T2, T3, T4, T5, R> wpVar) {
            this.a = wpVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 5) {
                return this.a.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Array of size 5 expected but got ");
            stringBuilder.append(objArr.length);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    static final class f<T1, T2, T3, T4, T5, T6, R> implements wm<Object[], R> {
        final wq<T1, T2, T3, T4, T5, T6, R> a;

        f(wq<T1, T2, T3, T4, T5, T6, R> wqVar) {
            this.a = wqVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 6) {
                return this.a.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Array of size 6 expected but got ");
            stringBuilder.append(objArr.length);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    static final class g<T1, T2, T3, T4, T5, T6, T7, R> implements wm<Object[], R> {
        final wr<T1, T2, T3, T4, T5, T6, T7, R> a;

        g(wr<T1, T2, T3, T4, T5, T6, T7, R> wrVar) {
            this.a = wrVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 7) {
                return this.a.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Array of size 7 expected but got ");
            stringBuilder.append(objArr.length);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    static final class h<T1, T2, T3, T4, T5, T6, T7, T8, R> implements wm<Object[], R> {
        final ws<T1, T2, T3, T4, T5, T6, T7, T8, R> a;

        h(ws<T1, T2, T3, T4, T5, T6, T7, T8, R> wsVar) {
            this.a = wsVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 8) {
                return this.a.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Array of size 8 expected but got ");
            stringBuilder.append(objArr.length);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    static final class i<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements wm<Object[], R> {
        final wt<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> a;

        i(wt<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> wtVar) {
            this.a = wtVar;
        }

        /* renamed from: a */
        public R apply(Object[] objArr) throws Exception {
            if (objArr.length == 9) {
                return this.a.a(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Array of size 9 expected but got ");
            stringBuilder.append(objArr.length);
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }

    static final class k<T> implements wv<T> {
        final wj a;

        k(wj wjVar) {
            this.a = wjVar;
        }

        public boolean test(T t) throws Exception {
            return this.a.a() ^ 1;
        }
    }

    static final class l<T, U> implements wm<T, U> {
        final Class<U> a;

        l(Class<U> cls) {
            this.a = cls;
        }

        public U apply(T t) throws Exception {
            return this.a.cast(t);
        }
    }

    static final class m<T, U> implements wv<T> {
        final Class<U> a;

        m(Class<U> cls) {
            this.a = cls;
        }

        public boolean test(T t) throws Exception {
            return this.a.isInstance(t);
        }
    }

    static final class n implements wf {
        public void a() {
        }

        public String toString() {
            return "EmptyAction";
        }

        n() {
        }
    }

    static final class o implements wl<Object> {
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }

        o() {
        }
    }

    static final class p implements wu {
        p() {
        }
    }

    static final class r<T> implements wv<T> {
        final T a;

        r(T t) {
            this.a = t;
        }

        public boolean test(T t) throws Exception {
            return a.a((Object) t, this.a);
        }
    }

    static final class s implements wl<Throwable> {
        s() {
        }

        /* renamed from: a */
        public void accept(Throwable th) {
            xk.a(th);
        }
    }

    static final class t implements wv<Object> {
        public boolean test(Object obj) {
            return false;
        }

        t() {
        }
    }

    static final class u implements wm<Object, Object> {
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }

        u() {
        }
    }

    static final class v<T, U> implements Callable<U>, wm<T, U> {
        final U a;

        v(U u) {
            this.a = u;
        }

        public U call() throws Exception {
            return this.a;
        }

        public U apply(T t) throws Exception {
            return this.a;
        }
    }

    static final class w<T> implements wm<List<T>, List<T>> {
        final Comparator<? super T> a;

        w(Comparator<? super T> comparator) {
            this.a = comparator;
        }

        /* renamed from: a */
        public List<T> apply(List<T> list) {
            Collections.sort(list, this.a);
            return list;
        }
    }

    static final class x implements wl<aas> {
        x() {
        }

        /* renamed from: a */
        public void accept(aas aas) throws Exception {
            aas.request(Long.MAX_VALUE);
        }
    }

    static final class z<T> implements wf {
        final wl<? super io.reactivex.j<T>> a;

        z(wl<? super io.reactivex.j<T>> wlVar) {
            this.a = wlVar;
        }

        public void a() throws Exception {
            this.a.accept(io.reactivex.j.f());
        }
    }

    public static <T1, T2, R> wm<Object[], R> a(wh<? super T1, ? super T2, ? extends R> whVar) {
        a.a((Object) whVar, "f is null");
        return new b(whVar);
    }

    public static <T1, T2, T3, R> wm<Object[], R> a(wn<T1, T2, T3, R> wnVar) {
        a.a((Object) wnVar, "f is null");
        return new c(wnVar);
    }

    public static <T1, T2, T3, T4, R> wm<Object[], R> a(wo<T1, T2, T3, T4, R> woVar) {
        a.a((Object) woVar, "f is null");
        return new d(woVar);
    }

    public static <T1, T2, T3, T4, T5, R> wm<Object[], R> a(wp<T1, T2, T3, T4, T5, R> wpVar) {
        a.a((Object) wpVar, "f is null");
        return new e(wpVar);
    }

    public static <T1, T2, T3, T4, T5, T6, R> wm<Object[], R> a(wq<T1, T2, T3, T4, T5, T6, R> wqVar) {
        a.a((Object) wqVar, "f is null");
        return new f(wqVar);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> wm<Object[], R> a(wr<T1, T2, T3, T4, T5, T6, T7, R> wrVar) {
        a.a((Object) wrVar, "f is null");
        return new g(wrVar);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> wm<Object[], R> a(ws<T1, T2, T3, T4, T5, T6, T7, T8, R> wsVar) {
        a.a((Object) wsVar, "f is null");
        return new h(wsVar);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> wm<Object[], R> a(wt<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> wtVar) {
        a.a((Object) wtVar, "f is null");
        return new i(wtVar);
    }

    public static <T> wm<T, T> a() {
        return a;
    }

    public static <T> wl<T> b() {
        return d;
    }

    public static <T> wv<T> c() {
        return h;
    }

    public static <T> wv<T> d() {
        return i;
    }

    public static <T> Callable<T> e() {
        return j;
    }

    public static <T> Comparator<T> f() {
        return k;
    }

    public static <T> Callable<T> a(T t) {
        return new v(t);
    }

    public static <T, U> wm<T, U> b(U u) {
        return new v(u);
    }

    public static <T, U> wm<T, U> a(Class<U> cls) {
        return new l(cls);
    }

    public static <T> Callable<List<T>> a(int i) {
        return new j(i);
    }

    public static <T> wv<T> c(T t) {
        return new r(t);
    }

    public static <T> Callable<Set<T>> g() {
        return HashSetCallable.INSTANCE;
    }

    public static <T> wl<T> a(wl<? super io.reactivex.j<T>> wlVar) {
        return new ab(wlVar);
    }

    public static <T> wl<Throwable> b(wl<? super io.reactivex.j<T>> wlVar) {
        return new aa(wlVar);
    }

    public static <T> wf c(wl<? super io.reactivex.j<T>> wlVar) {
        return new z(wlVar);
    }

    public static <T> wl<T> a(wf wfVar) {
        return new a(wfVar);
    }

    public static <T, U> wv<T> b(Class<U> cls) {
        return new m(cls);
    }

    public static <T> wv<T> a(wj wjVar) {
        return new k(wjVar);
    }

    public static <T> wm<T, xm<T>> a(TimeUnit timeUnit, io.reactivex.s sVar) {
        return new ae(timeUnit, sVar);
    }

    public static <T, K> wg<Map<K, T>, T> a(wm<? super T, ? extends K> wmVar) {
        return new af(wmVar);
    }

    public static <T, K, V> wg<Map<K, V>, T> a(wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2) {
        return new ag(wmVar2, wmVar);
    }

    public static <T, K, V> wg<Map<K, Collection<V>>, T> a(wm<? super T, ? extends K> wmVar, wm<? super T, ? extends V> wmVar2, wm<? super K, ? extends Collection<? super V>> wmVar3) {
        return new ah(wmVar3, wmVar2, wmVar);
    }

    public static <T> Comparator<T> h() {
        return NaturalComparator.INSTANCE;
    }

    public static <T> wm<List<T>, List<T>> a(Comparator<? super T> comparator) {
        return new w(comparator);
    }
}
