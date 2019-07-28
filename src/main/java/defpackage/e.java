package defpackage;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: SafeIterableMap */
/* renamed from: e */
public class e<K, V> implements Iterable<Entry<K, V>> {
    private c<K, V> a;
    private c<K, V> b;
    private WeakHashMap<f<K, V>, Boolean> c = new WeakHashMap();
    private int d = 0;

    /* compiled from: SafeIterableMap */
    /* renamed from: e$c */
    static class c<K, V> implements Entry<K, V> {
        @NonNull
        final K a;
        @NonNull
        final V b;
        c<K, V> c;
        c<K, V> d;

        c(@NonNull K k, @NonNull V v) {
            this.a = k;
            this.b = v;
        }

        @NonNull
        public K getKey() {
            return this.a;
        }

        @NonNull
        public V getValue() {
            return this.b;
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append("=");
            stringBuilder.append(this.b);
            return stringBuilder.toString();
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            if (!(this.a.equals(cVar.a) && this.b.equals(cVar.b))) {
                z = false;
            }
            return z;
        }
    }

    /* compiled from: SafeIterableMap */
    /* renamed from: e$f */
    interface f<K, V> {
        void a_(@NonNull c<K, V> cVar);
    }

    /* compiled from: SafeIterableMap */
    /* renamed from: e$d */
    private class d implements f<K, V>, Iterator<Entry<K, V>> {
        private c<K, V> b;
        private boolean c;

        private d() {
            this.c = true;
        }

        public void a_(@NonNull c<K, V> cVar) {
            if (cVar == this.b) {
                this.b = this.b.d;
                this.c = this.b == null;
            }
        }

        public boolean hasNext() {
            boolean z = false;
            if (this.c) {
                if (e.this.a != null) {
                    z = true;
                }
                return z;
            }
            if (!(this.b == null || this.b.c == null)) {
                z = true;
            }
            return z;
        }

        /* renamed from: a */
        public Entry<K, V> next() {
            if (this.c) {
                this.c = false;
                this.b = e.this.a;
            } else {
                this.b = this.b != null ? this.b.c : null;
            }
            return this.b;
        }
    }

    /* compiled from: SafeIterableMap */
    /* renamed from: e$e */
    private static abstract class e<K, V> implements f<K, V>, Iterator<Entry<K, V>> {
        c<K, V> a;
        c<K, V> b;

        public abstract c<K, V> a(c<K, V> cVar);

        public abstract c<K, V> b(c<K, V> cVar);

        e(c<K, V> cVar, c<K, V> cVar2) {
            this.a = cVar2;
            this.b = cVar;
        }

        public boolean hasNext() {
            return this.b != null;
        }

        public void a_(@NonNull c<K, V> cVar) {
            if (this.a == cVar && cVar == this.b) {
                this.b = null;
                this.a = null;
            }
            if (this.a == cVar) {
                this.a = b(this.a);
            }
            if (this.b == cVar) {
                this.b = b();
            }
        }

        private c<K, V> b() {
            return (this.b == this.a || this.a == null) ? null : a(this.b);
        }

        /* renamed from: a */
        public Entry<K, V> next() {
            c cVar = this.b;
            this.b = b();
            return cVar;
        }
    }

    /* compiled from: SafeIterableMap */
    /* renamed from: e$a */
    static class a<K, V> extends e<K, V> {
        a(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        /* Access modifiers changed, original: 0000 */
        public c<K, V> a(c<K, V> cVar) {
            return cVar.c;
        }

        /* Access modifiers changed, original: 0000 */
        public c<K, V> b(c<K, V> cVar) {
            return cVar.d;
        }
    }

    /* compiled from: SafeIterableMap */
    /* renamed from: e$b */
    private static class b<K, V> extends e<K, V> {
        b(c<K, V> cVar, c<K, V> cVar2) {
            super(cVar, cVar2);
        }

        /* Access modifiers changed, original: 0000 */
        public c<K, V> a(c<K, V> cVar) {
            return cVar.d;
        }

        /* Access modifiers changed, original: 0000 */
        public c<K, V> b(c<K, V> cVar) {
            return cVar.c;
        }
    }

    /* Access modifiers changed, original: protected */
    public c<K, V> a(K k) {
        c cVar = this.a;
        while (cVar != null && !cVar.a.equals(k)) {
            cVar = cVar.c;
        }
        return cVar;
    }

    public V a(@NonNull K k, @NonNull V v) {
        c a = a((Object) k);
        if (a != null) {
            return a.b;
        }
        b(k, v);
        return null;
    }

    /* Access modifiers changed, original: protected */
    public c<K, V> b(@NonNull K k, @NonNull V v) {
        c cVar = new c(k, v);
        this.d++;
        if (this.b == null) {
            this.a = cVar;
            this.b = this.a;
            return cVar;
        }
        this.b.c = cVar;
        cVar.d = this.b;
        this.b = cVar;
        return cVar;
    }

    public V b(@NonNull K k) {
        c a = a((Object) k);
        if (a == null) {
            return null;
        }
        this.d--;
        if (!this.c.isEmpty()) {
            for (f a_ : this.c.keySet()) {
                a_.a_(a);
            }
        }
        if (a.d != null) {
            a.d.c = a.c;
        } else {
            this.a = a.c;
        }
        if (a.c != null) {
            a.c.d = a.d;
        } else {
            this.b = a.d;
        }
        a.c = null;
        a.d = null;
        return a.b;
    }

    public int a() {
        return this.d;
    }

    @NonNull
    public Iterator<Entry<K, V>> iterator() {
        a aVar = new a(this.a, this.b);
        this.c.put(aVar, Boolean.valueOf(false));
        return aVar;
    }

    public Iterator<Entry<K, V>> b() {
        b bVar = new b(this.b, this.a);
        this.c.put(bVar, Boolean.valueOf(false));
        return bVar;
    }

    public d c() {
        d dVar = new d();
        this.c.put(dVar, Boolean.valueOf(false));
        return dVar;
    }

    public Entry<K, V> d() {
        return this.a;
    }

    public Entry<K, V> e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (a() != eVar.a()) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = eVar.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Entry entry = (Entry) it.next();
            Object next = it2.next();
            if ((entry == null && next != null) || (entry != null && !entry.equals(next))) {
                return false;
            }
        }
        if (it.hasNext() || it2.hasNext()) {
            z = false;
        }
        return z;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            stringBuilder.append(((Entry) it.next()).toString());
            if (it.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
