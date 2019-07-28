package com.tencent.liteav.beauty;

import android.opengl.GLES20;
import android.util.Log;
import com.tencent.liteav.basic.e.g;
import com.tencent.liteav.basic.e.i;
import com.tencent.liteav.basic.e.i.a;
import java.util.ArrayList;

/* compiled from: TXCSavePreFrameFilter */
public class c {
    private static String e = "avePreFrame";
    private ArrayList<a> a = new ArrayList();
    private ArrayList<a> b = new ArrayList();
    private int c = 1;
    private g d = null;
    private a[] f = null;
    private int g = -1;
    private int h = -1;

    public boolean a(int i, int i2) {
        return c(i, i2);
    }

    private boolean c(int i, int i2) {
        if (this.d == null) {
            this.d = new g();
            this.d.a(true);
            if (!this.d.c()) {
                Log.e(e, "mDissolveBlendFilter init Failed!");
                return false;
            }
        }
        if (this.d != null) {
            this.d.a(i, i2);
        }
        this.g = i;
        this.h = i2;
        return true;
    }

    public void b(int i, int i2) {
        c(i, i2);
    }

    public int a(int i) {
        a aVar = null;
        int i2 = -1;
        if (this.a.size() >= this.c) {
            a aVar2 = this.a.size() > 0 ? (a) this.a.get(0) : null;
            if (aVar2 != null) {
                if (this.d != null) {
                    i2 = this.d.a(aVar2.b[0]);
                }
                this.b.add(aVar2);
                this.a.remove(0);
            }
        }
        if (this.b.size() > 0) {
            aVar = (a) this.b.get(0);
        }
        if (aVar != null) {
            GLES20.glBindFramebuffer(36160, aVar.a[0]);
            if (this.d != null) {
                this.d.b(i);
            }
            GLES20.glBindFramebuffer(36160, 0);
            this.a.add(aVar);
            this.b.remove(0);
        }
        return i2;
    }

    public void b(int i) {
        this.c = i;
        if (this.f == null || this.f.length != this.c) {
            i.a(this.f);
            a();
            this.f = i.a(this.f, this.c, this.g, this.h);
            for (Object add : this.f) {
                this.b.add(add);
            }
        }
    }

    public void a() {
        this.b.clear();
        this.a.clear();
    }

    public void b() {
        if (this.d != null) {
            this.d.e();
            this.d = null;
        }
        i.a(this.f);
        this.f = null;
        a();
    }
}
