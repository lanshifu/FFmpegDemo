package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import com.tencent.ugc.TXRecordCommon;
import defpackage.hs.a;

/* compiled from: VbriSeeker */
/* renamed from: ht */
final class ht implements a {
    private final long[] a;
    private final long[] b;
    private final long c;

    public boolean a() {
        return true;
    }

    public static ht a(long j, long j2, hf hfVar, n nVar) {
        long j3 = j;
        hf hfVar2 = hfVar;
        n nVar2 = nVar;
        nVar2.d(10);
        int o = nVar.o();
        if (o <= 0) {
            return null;
        }
        long j4;
        int i = hfVar2.d;
        long d = z.d((long) o, 1000000 * ((long) (i >= TXRecordCommon.AUDIO_SAMPLERATE_32000 ? 1152 : 576)), (long) i);
        o = nVar.h();
        int h = nVar.h();
        int h2 = nVar.h();
        nVar2.d(2);
        long j5 = j2 + ((long) hfVar2.c);
        long[] jArr = new long[o];
        long[] jArr2 = new long[o];
        int i2 = 0;
        long j6 = j2;
        while (i2 < o) {
            int g;
            j4 = d;
            jArr[i2] = (((long) i2) * d) / ((long) o);
            jArr2[i2] = Math.max(j6, j5);
            switch (h2) {
                case 1:
                    g = nVar.g();
                    break;
                case 2:
                    g = nVar.h();
                    break;
                case 3:
                    g = nVar.k();
                    break;
                case 4:
                    g = nVar.u();
                    break;
                default:
                    return null;
            }
            j6 += (long) (g * h);
            i2++;
            d = j4;
            j3 = j;
        }
        j4 = d;
        long j7 = j;
        if (!(j7 == -1 || j7 == j6)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("VBRI data size mismatch: ");
            stringBuilder.append(j7);
            stringBuilder.append(", ");
            stringBuilder.append(j6);
            Log.w("VbriSeeker", stringBuilder.toString());
        }
        return new ht(jArr, jArr2, j4);
    }

    private ht(long[] jArr, long[] jArr2, long j) {
        this.a = jArr;
        this.b = jArr2;
        this.c = j;
    }

    public hh.a b(long j) {
        int a = z.a(this.a, j, true, true);
        hi hiVar = new hi(this.a[a], this.b[a]);
        if (hiVar.b >= j || a == this.a.length - 1) {
            return new hh.a(hiVar);
        }
        a++;
        return new hh.a(hiVar, new hi(this.a[a], this.b[a]));
    }

    public long a(long j) {
        return this.a[z.a(this.b, j, true, true)];
    }

    public long b() {
        return this.c;
    }
}
