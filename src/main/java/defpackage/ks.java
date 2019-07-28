package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;

/* compiled from: CeaUtil */
/* renamed from: ks */
public final class ks {
    private static final int a = z.g("GA94");
    private static final int b = z.g("DTG1");

    public static void a(long j, n nVar, hj[] hjVarArr) {
        n nVar2 = nVar;
        hj[] hjVarArr2 = hjVarArr;
        while (nVar.b() > 1) {
            int a = ks.a(nVar);
            int a2 = ks.a(nVar);
            int d = nVar.d() + a2;
            if (a2 == -1 || a2 > nVar.b()) {
                Log.w("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                d = nVar.c();
            } else if (a == 4 && a2 >= 8) {
                a = nVar.g();
                a2 = nVar.h();
                int o = a2 == 49 ? nVar.o() : 0;
                int g = nVar.g();
                if (a2 == 47) {
                    nVar2.d(1);
                }
                a = (a == 181 && ((a2 == 49 || a2 == 47) && g == 3)) ? 1 : 0;
                if (a2 == 49) {
                    a2 = (o == a || o == b) ? 1 : 0;
                    a &= a2;
                }
                if (a != 0) {
                    a = nVar.g() & 31;
                    nVar2.d(1);
                    a *= 3;
                    int d2 = nVar.d();
                    for (hj hjVar : hjVarArr2) {
                        nVar2.c(d2);
                        hjVar.a(nVar2, a);
                        hjVar.a(j, 1, a, 0, null);
                    }
                }
            }
            nVar2.c(d);
        }
    }

    private static int a(n nVar) {
        int i = 0;
        while (nVar.b() != 0) {
            int g = nVar.g();
            i += g;
            if (g != 255) {
                return i;
            }
        }
        return -1;
    }
}
