package defpackage;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.n;
import defpackage.jd.b;
import defpackage.jd.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: DefaultTsPayloadReaderFactory */
/* renamed from: il */
public final class il implements c {
    private final int a;
    private final List<Format> b;

    public il() {
        this(0);
    }

    public il(int i) {
        this(i, Collections.emptyList());
    }

    public il(int i, List<Format> list) {
        List list2;
        this.a = i;
        if (!a(32) && list2.isEmpty()) {
            list2 = Collections.singletonList(Format.a(null, "application/cea-608", 0, null));
        }
        this.b = list2;
    }

    public SparseArray<jd> a() {
        return new SparseArray();
    }

    public jd a(int i, b bVar) {
        jd jdVar = null;
        switch (i) {
            case 2:
                return new iw(new ip());
            case 3:
            case 4:
                return new iw(new iu(bVar.b));
            case 15:
                if (!a(2)) {
                    jdVar = new iw(new ik(false, bVar.b));
                }
                return jdVar;
            case 17:
                if (!a(2)) {
                    jdVar = new iw(new it(bVar.b));
                }
                return jdVar;
            case 21:
                return new iw(new is());
            case 27:
                if (!a(4)) {
                    jdVar = new iw(new iq(a(bVar), a(1), a(8)));
                }
                return jdVar;
            case 36:
                return new iw(new ir(a(bVar)));
            case 89:
                return new iw(new in(bVar.c));
            case 129:
            case 135:
                return new iw(new ii(bVar.b));
            case 130:
            case 138:
                return new iw(new im(bVar.b));
            case 134:
                if (!a(16)) {
                    jdVar = new iz(new jb());
                }
                return jdVar;
            default:
                return null;
        }
    }

    private ja a(b bVar) {
        if (a(32)) {
            return new ja(this.b);
        }
        n nVar = new n(bVar.d);
        List list = this.b;
        while (nVar.b() > 0) {
            int d = nVar.d() + nVar.g();
            if (nVar.g() == 134) {
                list = new ArrayList();
                int g = nVar.g() & 31;
                for (int i = 0; i < g; i++) {
                    int i2;
                    String str;
                    String e = nVar.e(3);
                    int g2 = nVar.g();
                    if (((g2 & 128) != 0 ? 1 : null) != null) {
                        i2 = g2 & 63;
                        str = "application/cea-708";
                    } else {
                        str = "application/cea-608";
                        i2 = 1;
                    }
                    list.add(Format.a(null, str, null, -1, 0, e, i2, null));
                    nVar.d(2);
                }
            }
            nVar.c(d);
        }
        return new ja(list);
    }

    private boolean a(int i) {
        return (i & this.a) != 0;
    }
}
