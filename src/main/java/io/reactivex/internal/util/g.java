package io.reactivex.internal.util;

import java.util.ArrayList;

/* compiled from: LinkedArrayList */
public class g {
    final int h;
    Object[] i;
    Object[] j;
    volatile int k;
    int l;

    public g(int i) {
        this.h = i;
    }

    public void a(Object obj) {
        if (this.k == 0) {
            this.i = new Object[(this.h + 1)];
            this.j = this.i;
            this.i[0] = obj;
            this.l = 1;
            this.k = 1;
        } else if (this.l == this.h) {
            Object[] objArr = new Object[(this.h + 1)];
            objArr[0] = obj;
            this.j[this.h] = objArr;
            this.j = objArr;
            this.l = 1;
            this.k++;
        } else {
            this.j[this.l] = obj;
            this.l++;
            this.k++;
        }
    }

    public Object[] b() {
        return this.i;
    }

    public int c() {
        return this.k;
    }

    public String toString() {
        int i = this.h;
        int i2 = this.k;
        ArrayList arrayList = new ArrayList(i2 + 1);
        Object[] b = b();
        int i3 = 0;
        while (true) {
            int i4 = 0;
            while (i3 < i2) {
                arrayList.add(b[i4]);
                i3++;
                i4++;
                if (i4 == i) {
                    b = (Object[]) b[i];
                }
            }
            return arrayList.toString();
        }
    }
}
