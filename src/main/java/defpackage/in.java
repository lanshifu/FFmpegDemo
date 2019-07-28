package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.n;
import defpackage.jd.a;
import defpackage.jd.d;
import java.util.Collections;
import java.util.List;

/* compiled from: DvbSubtitleReader */
/* renamed from: in */
public final class in implements io {
    private final List<a> a;
    private final hj[] b;
    private boolean c;
    private int d;
    private int e;
    private long f;

    public in(List<a> list) {
        this.a = list;
        this.b = new hj[list.size()];
    }

    public void a() {
        this.c = false;
    }

    public void a(hb hbVar, d dVar) {
        for (int i = 0; i < this.b.length; i++) {
            a aVar = (a) this.a.get(i);
            dVar.a();
            hj a = hbVar.a(dVar.b(), 3);
            a.a(Format.a(dVar.c(), "application/dvbsubs", null, -1, 0, Collections.singletonList(aVar.c), aVar.a, null));
            this.b[i] = a;
        }
    }

    public void a(long j, boolean z) {
        if (z) {
            this.c = true;
            this.f = j;
            this.e = 0;
            this.d = 2;
        }
    }

    public void b() {
        if (this.c) {
            for (hj a : this.b) {
                a.a(this.f, 1, this.e, 0, null);
            }
            this.c = false;
        }
    }

    public void a(n nVar) {
        if (this.c && (this.d != 2 || a(nVar, 32))) {
            int i = 0;
            if (this.d != 1 || a(nVar, 0)) {
                int d = nVar.d();
                int b = nVar.b();
                hj[] hjVarArr = this.b;
                int length = hjVarArr.length;
                while (i < length) {
                    hj hjVar = hjVarArr[i];
                    nVar.c(d);
                    hjVar.a(nVar, b);
                    i++;
                }
                this.e += b;
            }
        }
    }

    private boolean a(n nVar, int i) {
        if (nVar.b() == 0) {
            return false;
        }
        if (nVar.g() != i) {
            this.c = false;
        }
        this.d--;
        return this.c;
    }
}
