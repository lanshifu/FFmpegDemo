package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.source.m;
import defpackage.jl.b;

/* compiled from: BaseMediaChunkOutput */
/* renamed from: jj */
public final class jj implements b {
    private final int[] a;
    private final m[] b;

    public jj(int[] iArr, m[] mVarArr) {
        this.a = iArr;
        this.b = mVarArr;
    }

    public hj a(int i, int i2) {
        for (i = 0; i < this.a.length; i++) {
            if (i2 == this.a[i]) {
                return this.b[i];
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unmatched track of type: ");
        stringBuilder.append(i2);
        Log.e("BaseMediaChunkOutput", stringBuilder.toString());
        return new gy();
    }

    public int[] a() {
        int[] iArr = new int[this.b.length];
        for (int i = 0; i < this.b.length; i++) {
            if (this.b[i] != null) {
                iArr[i] = this.b[i].c();
            }
        }
        return iArr;
    }

    public void a(long j) {
        for (m mVar : this.b) {
            if (mVar != null) {
                mVar.a(j);
            }
        }
    }
}
