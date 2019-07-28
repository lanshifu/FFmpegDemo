package defpackage;

import android.text.SpannableStringBuilder;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: WebvttSubtitle */
/* renamed from: ls */
final class ls implements e {
    private final List<lo> a;
    private final int b;
    private final long[] c = new long[(this.b * 2)];
    private final long[] d;

    public ls(List<lo> list) {
        this.a = list;
        this.b = list.size();
        for (int i = 0; i < this.b; i++) {
            lo loVar = (lo) list.get(i);
            int i2 = i * 2;
            this.c[i2] = loVar.o;
            this.c[i2 + 1] = loVar.p;
        }
        this.d = Arrays.copyOf(this.c, this.c.length);
        Arrays.sort(this.d);
    }

    public int a(long j) {
        int b = z.b(this.d, j, false, false);
        return b < this.d.length ? b : -1;
    }

    public int b() {
        return this.d.length;
    }

    public long a(int i) {
        boolean z = false;
        a.a(i >= 0);
        if (i < this.d.length) {
            z = true;
        }
        a.a(z);
        return this.d[i];
    }

    public List<b> b(long j) {
        SpannableStringBuilder spannableStringBuilder = null;
        ArrayList arrayList = null;
        lo loVar = arrayList;
        for (int i = 0; i < this.b; i++) {
            int i2 = i * 2;
            if (this.c[i2] <= j && j < this.c[i2 + 1]) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                lo loVar2 = (lo) this.a.get(i);
                if (!loVar2.a()) {
                    arrayList.add(loVar2);
                } else if (loVar == null) {
                    loVar = loVar2;
                } else if (spannableStringBuilder == null) {
                    spannableStringBuilder = new SpannableStringBuilder();
                    spannableStringBuilder.append(loVar.a).append("\n").append(loVar2.a);
                } else {
                    spannableStringBuilder.append("\n").append(loVar2.a);
                }
            }
        }
        if (spannableStringBuilder != null) {
            arrayList.add(new lo(spannableStringBuilder));
        } else if (loVar != null) {
            arrayList.add(loVar);
        }
        if (arrayList != null) {
            return arrayList;
        }
        return Collections.emptyList();
    }
}
