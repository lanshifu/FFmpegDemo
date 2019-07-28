package io.reactivex.internal.util;

import defpackage.wv;
import io.reactivex.r;

/* compiled from: AppendOnlyLinkedArrayList */
public class a<T> {
    final int a;
    final Object[] b;
    Object[] c = this.b;
    int d;

    /* compiled from: AppendOnlyLinkedArrayList */
    public interface a<T> extends wv<T> {
        boolean test(T t);
    }

    public a(int i) {
        this.a = i;
        this.b = new Object[(i + 1)];
    }

    public void a(T t) {
        int i = this.a;
        int i2 = this.d;
        if (i2 == i) {
            Object[] objArr = new Object[(i + 1)];
            this.c[i] = objArr;
            this.c = objArr;
            i2 = 0;
        }
        this.c[i2] = t;
        this.d = i2 + 1;
    }

    public void b(T t) {
        this.b[0] = t;
    }

    public void a(a<? super T> aVar) {
        int i = this.a;
        for (Object[] objArr = this.b; objArr != null; objArr = (Object[]) objArr[i]) {
            int i2 = 0;
            while (i2 < i) {
                Object obj = objArr[i2];
                if (obj == null) {
                    continue;
                    break;
                } else if (!aVar.test(obj)) {
                    i2++;
                } else {
                    return;
                }
            }
        }
    }

    public <U> boolean a(r<? super U> rVar) {
        Object[] objArr = this.b;
        int i = this.a;
        while (true) {
            int i2 = 0;
            if (objArr == null) {
                return false;
            }
            while (i2 < i) {
                Object obj = objArr[i2];
                if (obj == null) {
                    continue;
                    break;
                } else if (NotificationLite.acceptFull(obj, (r) rVar)) {
                    return true;
                } else {
                    i2++;
                }
            }
            objArr = (Object[]) objArr[i];
        }
    }
}
