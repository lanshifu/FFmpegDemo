package defpackage;

/* compiled from: GenericGFPoly */
/* renamed from: lu */
final class lu {
    private final lt a;
    private final int[] b;

    lu(lt ltVar, int[] iArr) {
        if (iArr.length != 0) {
            this.a = ltVar;
            int length = iArr.length;
            int i = 1;
            if (length <= 1 || iArr[0] != 0) {
                this.b = iArr;
                return;
            }
            while (i < length && iArr[i] == 0) {
                i++;
            }
            if (i == length) {
                this.b = ltVar.a().b;
                return;
            }
            this.b = new int[(length - i)];
            System.arraycopy(iArr, i, this.b, 0, this.b.length);
            return;
        }
        throw new IllegalArgumentException();
    }

    /* Access modifiers changed, original: 0000 */
    public int[] a() {
        return this.b;
    }

    /* Access modifiers changed, original: 0000 */
    public int b() {
        return this.b.length - 1;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean c() {
        return this.b[0] == 0;
    }

    /* Access modifiers changed, original: 0000 */
    public int a(int i) {
        return this.b[(this.b.length - 1) - i];
    }

    /* Access modifiers changed, original: 0000 */
    public lu a(lu luVar) {
        if (!this.a.equals(luVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (c()) {
            return luVar;
        } else {
            if (luVar.c()) {
                return this;
            }
            int[] iArr = this.b;
            Object obj = luVar.b;
            if (iArr.length > obj.length) {
                Object obj2 = iArr;
                iArr = obj;
                obj = obj2;
            }
            int[] iArr2 = new int[obj.length];
            int length = obj.length - iArr.length;
            System.arraycopy(obj, 0, iArr2, 0, length);
            for (int i = length; i < obj.length; i++) {
                iArr2[i] = lt.b(iArr[i - length], obj[i]);
            }
            return new lu(this.a, iArr2);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public lu b(lu luVar) {
        if (!this.a.equals(luVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (c() || luVar.c()) {
            return this.a.a();
        } else {
            int[] iArr = this.b;
            int length = iArr.length;
            int[] iArr2 = luVar.b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = lt.b(iArr3[i4], this.a.c(i2, iArr2[i3]));
                }
            }
            return new lu(this.a, iArr3);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public lu a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.a.a();
        } else {
            int length = this.b.length;
            int[] iArr = new int[(i + length)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.a.c(this.b[i3], i2);
            }
            return new lu(this.a, iArr);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public lu[] c(lu luVar) {
        if (!this.a.equals(luVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (luVar.c()) {
            throw new IllegalArgumentException("Divide by 0");
        } else {
            lu a = this.a.a();
            int c = this.a.c(luVar.a(luVar.b()));
            lu luVar2 = a;
            a = this;
            while (a.b() >= luVar.b() && !a.c()) {
                int b = a.b() - luVar.b();
                int c2 = this.a.c(a.a(a.b()), c);
                lu a2 = luVar.a(b, c2);
                luVar2 = luVar2.a(this.a.a(b, c2));
                a = a.a(a2);
            }
            return new lu[]{luVar2, a};
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(b() * 8);
        for (int b = b(); b >= 0; b--) {
            int a = a(b);
            if (a != 0) {
                if (a < 0) {
                    stringBuilder.append(" - ");
                    a = -a;
                } else if (stringBuilder.length() > 0) {
                    stringBuilder.append(" + ");
                }
                if (b == 0 || a != 1) {
                    a = this.a.b(a);
                    if (a == 0) {
                        stringBuilder.append('1');
                    } else if (a == 1) {
                        stringBuilder.append('a');
                    } else {
                        stringBuilder.append("a^");
                        stringBuilder.append(a);
                    }
                }
                if (b != 0) {
                    if (b == 1) {
                        stringBuilder.append('x');
                    } else {
                        stringBuilder.append("x^");
                        stringBuilder.append(b);
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
