package defpackage;

import com.google.android.exoplayer2.util.z;
import defpackage.hh.a;
import java.util.Arrays;

/* compiled from: ChunkIndex */
/* renamed from: gv */
public final class gv implements hh {
    public final int a;
    public final int[] b;
    public final long[] c;
    public final long[] d;
    public final long[] e;
    private final long f;

    public boolean a() {
        return true;
    }

    public gv(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.b = iArr;
        this.c = jArr;
        this.d = jArr2;
        this.e = jArr3;
        this.a = iArr.length;
        if (this.a > 0) {
            this.f = jArr2[this.a - 1] + jArr3[this.a - 1];
        } else {
            this.f = 0;
        }
    }

    public int a(long j) {
        return z.a(this.e, j, true, true);
    }

    public long b() {
        return this.f;
    }

    public a b(long j) {
        int a = a(j);
        hi hiVar = new hi(this.e[a], this.c[a]);
        if (hiVar.b >= j || a == this.a - 1) {
            return new a(hiVar);
        }
        a++;
        return new a(hiVar, new hi(this.e[a], this.c[a]));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ChunkIndex(length=");
        stringBuilder.append(this.a);
        stringBuilder.append(", sizes=");
        stringBuilder.append(Arrays.toString(this.b));
        stringBuilder.append(", offsets=");
        stringBuilder.append(Arrays.toString(this.c));
        stringBuilder.append(", timeUs=");
        stringBuilder.append(Arrays.toString(this.e));
        stringBuilder.append(", durationsUs=");
        stringBuilder.append(Arrays.toString(this.d));
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
