package defpackage;

import android.support.annotation.Nullable;
import java.util.List;

/* compiled from: Period */
/* renamed from: jz */
public class jz {
    @Nullable
    public final String a;
    public final long b;
    public final List<jv> c;
    public final List<jy> d;

    public int a(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((jv) this.c.get(i2)).b == i) {
                return i2;
            }
        }
        return -1;
    }
}
