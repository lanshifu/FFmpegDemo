package defpackage;

import java.util.ArrayList;
import java.util.List;

/* compiled from: ReedSolomonEncoder */
/* renamed from: lv */
public final class lv {
    private final lt a;
    private final List<lu> b;

    public lv(lt ltVar) {
        if (lt.e.equals(ltVar)) {
            this.a = ltVar;
            this.b = new ArrayList();
            this.b.add(new lu(ltVar, new int[]{1}));
            return;
        }
        throw new IllegalArgumentException("Only QR Code is supported at this time");
    }

    private lu a(int i) {
        if (i >= this.b.size()) {
            lu luVar = (lu) this.b.get(this.b.size() - 1);
            for (int size = this.b.size(); size <= i; size++) {
                luVar = luVar.b(new lu(this.a, new int[]{1, this.a.a(size - 1)}));
                this.b.add(luVar);
            }
        }
        return (lu) this.b.get(i);
    }

    public void a(int[] iArr, int i) {
        if (i != 0) {
            int length = iArr.length - i;
            if (length > 0) {
                lu a = a(i);
                int[] iArr2 = new int[length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                int[] a2 = new lu(this.a, iArr2).a(i, 1).c(a)[1].a();
                i -= a2.length;
                for (int i2 = 0; i2 < i; i2++) {
                    iArr[length + i2] = 0;
                }
                System.arraycopy(a2, 0, iArr, length + i, a2.length);
                return;
            }
            throw new IllegalArgumentException("No data bytes provided");
        }
        throw new IllegalArgumentException("No error correction bytes");
    }
}
