package io.reactivex.internal.util;

/* compiled from: OpenHashSet */
public final class i<T> {
    final float a;
    int b;
    int c;
    int d;
    T[] e;

    static int a(int i) {
        i *= -1640531527;
        return i ^ (i >>> 16);
    }

    public i() {
        this(16, 0.75f);
    }

    public i(int i, float f) {
        this.a = f;
        i = j.a(i);
        this.b = i - 1;
        this.d = (int) (f * ((float) i));
        this.e = new Object[i];
    }

    public boolean a(T t) {
        Object[] objArr = this.e;
        int i = this.b;
        int a = a(t.hashCode()) & i;
        Object obj = objArr[a];
        if (obj != null) {
            if (obj.equals(t)) {
                return false;
            }
            do {
                a = (a + 1) & i;
                obj = objArr[a];
                if (obj == null) {
                }
            } while (!obj.equals(t));
            return false;
        }
        objArr[a] = t;
        int i2 = this.c + 1;
        this.c = i2;
        if (i2 >= this.d) {
            a();
        }
        return true;
    }

    public boolean b(T t) {
        Object[] objArr = this.e;
        int i = this.b;
        int a = a(t.hashCode()) & i;
        Object obj = objArr[a];
        if (obj == null) {
            return false;
        }
        if (obj.equals(t)) {
            return a(a, objArr, i);
        }
        do {
            a = (a + 1) & i;
            obj = objArr[a];
            if (obj == null) {
                return false;
            }
        } while (!obj.equals(t));
        return a(a, objArr, i);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a(int i, T[] tArr, int i2) {
        this.c--;
        while (true) {
            Object obj;
            int i3 = i + 1;
            while (true) {
                i3 &= i2;
                obj = tArr[i3];
                if (obj == null) {
                    tArr[i] = null;
                    return true;
                }
                int a = a(obj.hashCode()) & i2;
                if (i <= i3) {
                    if (i >= a || a > i3) {
                        break;
                    }
                    i3++;
                } else {
                    if (i >= a && a > i3) {
                        break;
                    }
                    i3++;
                }
            }
            tArr[i] = obj;
            i = i3;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        Object[] objArr = this.e;
        int length = objArr.length;
        int i = length << 1;
        int i2 = i - 1;
        Object[] objArr2 = new Object[i];
        int i3 = this.c;
        while (true) {
            int i4 = i3 - 1;
            if (i3 != 0) {
                while (true) {
                    length--;
                    if (objArr[length] != null) {
                        break;
                    }
                }
                i3 = a(objArr[length].hashCode()) & i2;
                if (objArr2[i3] != null) {
                    do {
                        i3 = (i3 + 1) & i2;
                    } while (objArr2[i3] != null);
                }
                objArr2[i3] = objArr[length];
                i3 = i4;
            } else {
                this.b = i2;
                this.d = (int) (((float) i) * this.a);
                this.e = objArr2;
                return;
            }
        }
    }

    public Object[] b() {
        return this.e;
    }

    public int c() {
        return this.c;
    }
}
