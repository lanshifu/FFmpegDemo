package master.flame.danmaku.danmaku.model.android;

import defpackage.zf;
import defpackage.zg;
import defpackage.zn;
import defpackage.zn.a;
import defpackage.zn.b;
import defpackage.zn.d;
import defpackage.zn.f;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Danmakus */
public class e implements zn {
    public Collection<zf> a;
    private e b;
    private zf c;
    private zf d;
    private zf e;
    private zf f;
    private volatile AtomicInteger g;
    private int h;
    private a i;
    private boolean j;
    private Object k;

    public e() {
        this(0, false);
    }

    public e(int i) {
        this(i, false);
    }

    public e(int i, boolean z) {
        this(i, z, null);
    }

    public e(int i, boolean z, a aVar) {
        this.g = new AtomicInteger(0);
        this.h = 0;
        this.k = new Object();
        if (i != 0) {
            aVar = i == 1 ? new defpackage.zn.e(z) : i == 2 ? new f(z) : null;
        } else if (aVar == null) {
            aVar = new d(z);
        }
        if (i == 4) {
            this.a = new LinkedList();
        } else {
            this.j = z;
            aVar.a(z);
            this.a = new TreeSet(aVar);
            this.i = aVar;
        }
        this.h = i;
        this.g.set(0);
    }

    public e(Collection<zf> collection) {
        this.g = new AtomicInteger(0);
        this.h = 0;
        this.k = new Object();
        a((Collection) collection);
    }

    public e(boolean z) {
        this(0, z);
    }

    public void a(Collection<zf> collection) {
        Collection collection2;
        if (!this.j || this.h == 4) {
            this.a = collection2;
        } else {
            synchronized (this.k) {
                this.a.clear();
                this.a.addAll(collection2);
                collection2 = this.a;
            }
        }
        if (collection2 instanceof List) {
            this.h = 4;
        }
        this.g.set(collection2 == null ? 0 : collection2.size());
    }

    public boolean a(zf zfVar) {
        synchronized (this.k) {
            if (this.a != null) {
                try {
                    if (this.a.add(zfVar)) {
                        this.g.incrementAndGet();
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean b(zf zfVar) {
        if (zfVar == null) {
            return false;
        }
        if (zfVar.g()) {
            zfVar.a(false);
        }
        synchronized (this.k) {
            if (this.a.remove(zfVar)) {
                this.g.decrementAndGet();
                return true;
            }
            return false;
        }
    }

    private Collection<zf> c(long j, long j2) {
        if (this.h == 4 || this.a == null || this.a.size() == 0) {
            return null;
        }
        if (this.b == null) {
            this.b = new e(this.j);
            this.b.k = this.k;
        }
        if (this.f == null) {
            this.f = a("start");
        }
        if (this.e == null) {
            this.e = a("end");
        }
        this.f.d(j);
        this.e.d(j2);
        return ((SortedSet) this.a).subSet(this.f, this.e);
    }

    public zn a(long j, long j2) {
        Collection c = c(j, j2);
        return (c == null || c.isEmpty()) ? null : new e(new LinkedList(c));
    }

    public zn b(long j, long j2) {
        if (this.a == null || this.a.size() == 0) {
            return null;
        }
        if (this.b == null) {
            if (this.h == 4) {
                this.b = new e(4);
                this.b.k = this.k;
                synchronized (this.k) {
                    this.b.a(this.a);
                }
            } else {
                this.b = new e(this.j);
                this.b.k = this.k;
            }
        }
        if (this.h == 4) {
            return this.b;
        }
        if (this.c == null) {
            this.c = a("start");
        }
        if (this.d == null) {
            this.d = a("end");
        }
        if (this.b != null && j - this.c.s() >= 0 && j2 <= this.d.s()) {
            return this.b;
        }
        this.c.d(j);
        this.d.d(j2);
        synchronized (this.k) {
            this.b.a(((SortedSet) this.a).subSet(this.c, this.d));
        }
        return this.b;
    }

    private zf a(String str) {
        return new zg(str);
    }

    public int a() {
        return this.g.get();
    }

    public void b() {
        synchronized (this.k) {
            if (this.a != null) {
                this.a.clear();
                this.g.set(0);
            }
        }
        if (this.b != null) {
            this.b = null;
            this.c = a("start");
            this.d = a("end");
        }
    }

    public zf c() {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        if (this.h == 4) {
            return (zf) ((LinkedList) this.a).peek();
        }
        return (zf) ((SortedSet) this.a).first();
    }

    public zf d() {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        if (this.h == 4) {
            return (zf) ((LinkedList) this.a).peekLast();
        }
        return (zf) ((SortedSet) this.a).last();
    }

    public boolean c(zf zfVar) {
        return this.a != null && this.a.contains(zfVar);
    }

    public boolean e() {
        return this.a == null || this.a.isEmpty();
    }

    public void a(b<? super zf, ?> bVar) {
        synchronized (this.k) {
            b((b) bVar);
        }
    }

    public void b(b<? super zf, ?> bVar) {
        bVar.c();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            zf zfVar = (zf) it.next();
            if (zfVar != null) {
                int a = bVar.a(zfVar);
                if (a == 1) {
                    break;
                } else if (a == 2) {
                    it.remove();
                    this.g.decrementAndGet();
                } else if (a == 3) {
                    it.remove();
                    this.g.decrementAndGet();
                    break;
                }
            }
        }
        bVar.d();
    }

    public Object f() {
        return this.k;
    }
}
