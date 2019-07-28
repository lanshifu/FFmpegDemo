package defpackage;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.ad;
import com.google.android.exoplayer2.ad.a;
import com.google.android.exoplayer2.source.j;
import java.util.ArrayList;

/* compiled from: AnalyticsCollector */
/* renamed from: gm$b */
final class gm$b {
    private final ArrayList<gm$c> a = new ArrayList();
    private final a b = new a();
    private gm$c c;
    private gm$c d;
    private ad e = ad.a;
    private boolean f;

    @Nullable
    public gm$c a() {
        return (this.a.isEmpty() || this.e.a() || this.f) ? null : (gm$c) this.a.get(0);
    }

    @Nullable
    public gm$c b() {
        return this.c;
    }

    @Nullable
    public gm$c c() {
        return this.d;
    }

    @Nullable
    public gm$c d() {
        if (this.a.isEmpty()) {
            return null;
        }
        return (gm$c) this.a.get(this.a.size() - 1);
    }

    public boolean e() {
        return this.f;
    }

    @Nullable
    public j.a a(int i) {
        j.a aVar = null;
        if (this.e != null) {
            int c = this.e.c();
            j.a aVar2 = null;
            for (int i2 = 0; i2 < this.a.size(); i2++) {
                gm$c gm_c = (gm$c) this.a.get(i2);
                int i3 = gm_c.b.a;
                if (i3 < c && this.e.a(i3, this.b).c == i) {
                    if (aVar2 != null) {
                        return null;
                    }
                    aVar2 = gm_c.b;
                }
            }
            aVar = aVar2;
        }
        return aVar;
    }

    public void b(int i) {
        h();
    }

    public void a(ad adVar) {
        for (int i = 0; i < this.a.size(); i++) {
            this.a.set(i, a((gm$c) this.a.get(i), adVar));
        }
        if (this.d != null) {
            this.d = a(this.d, adVar);
        }
        this.e = adVar;
        h();
    }

    public void f() {
        this.f = true;
    }

    public void g() {
        this.f = false;
        h();
    }

    public void a(int i, j.a aVar) {
        this.a.add(new gm$c(i, aVar));
        if (this.a.size() == 1 && !this.e.a()) {
            h();
        }
    }

    public void b(int i, j.a aVar) {
        gm$c gm_c = new gm$c(i, aVar);
        this.a.remove(gm_c);
        if (gm_c.equals(this.d)) {
            this.d = this.a.isEmpty() ? null : (gm$c) this.a.get(0);
        }
    }

    public void c(int i, j.a aVar) {
        this.d = new gm$c(i, aVar);
    }

    private void h() {
        if (!this.a.isEmpty()) {
            this.c = (gm$c) this.a.get(0);
        }
    }

    private gm$c a(gm$c gm_c, ad adVar) {
        if (adVar.a() || this.e.a()) {
            return gm_c;
        }
        int a = adVar.a(this.e.a(gm_c.b.a, this.b, true).b);
        if (a == -1) {
            return gm_c;
        }
        return new gm$c(adVar.a(a, this.b).c, gm_c.b.a(a));
    }
}
