package com.tencent.liteav.l;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.beauty.b.x;
import com.tencent.liteav.beauty.e;
import java.util.LinkedList;
import java.util.Queue;

/* compiled from: TXCCombineProcessor */
public class a {
    private x[] a = null;
    private b b = null;
    private com.tencent.liteav.basic.h.a[] c = null;
    private float[] d = null;
    private int e = 0;
    private int f = 0;
    private Context g = null;
    private final Queue<Runnable> h = new LinkedList();
    private String i = "CombineProcessor";
    private e j = new e() {
        public void didProcessFrame(byte[] bArr, int i, int i2, int i3, long j) {
        }

        public int willAddWatermark(int i, int i2, int i3) {
            return 0;
        }

        public void didProcessFrame(int i, int i2, int i3, long j) {
            a.this.c[a.this.f].b = 0;
            a.this.c[a.this.f].a = i;
            a.this.c[a.this.f].c = i2;
            a.this.c[a.this.f].d = i3;
        }
    };

    public a(Context context) {
        this.g = context.getApplicationContext();
    }

    public void a(final int i, final int i2) {
        a(new Runnable() {
            public void run() {
                if (a.this.b != null) {
                    a.this.b.a(i, i2);
                }
            }
        });
    }

    public void b(final int i, final int i2) {
        a(new Runnable() {
            public void run() {
                if (a.this.b != null) {
                    a.this.b.b(i, i2);
                }
            }
        });
    }

    public void a(final com.tencent.liteav.basic.e.a aVar) {
        a(new Runnable() {
            public void run() {
                if (a.this.b != null) {
                    a.this.b.a(aVar);
                }
            }
        });
    }

    public int a(com.tencent.liteav.basic.h.a[] aVarArr, int i) {
        if (aVarArr == null || aVarArr.length <= 0) {
            Log.e(this.i, "frames is null or no frames!");
            return -1;
        }
        if (this.e < aVarArr.length) {
            this.e = aVarArr.length;
            b();
        }
        a(aVarArr);
        a(this.h);
        this.c = (com.tencent.liteav.basic.h.a[]) aVarArr.clone();
        int i2 = 0;
        while (i2 < aVarArr.length) {
            if (!(this.a[i2] == null || aVarArr[i2].e == null)) {
                this.a[i2].a((float) aVarArr[i2].e.b, aVarArr[i2].e.a);
                this.a[i2].b(aVarArr[i2].e.c);
                GLES20.glViewport(0, 0, aVarArr[i2].g.c, aVarArr[i2].g.d);
                this.c[i2].a = this.a[i2].a(this.c[i2].a);
            }
            i2++;
        }
        return this.b.a(this.c, i);
    }

    private void a(Runnable runnable) {
        synchronized (this.h) {
            this.h.add(runnable);
        }
    }

    private void a(Queue<Runnable> queue) {
        while (true) {
            Runnable runnable = null;
            synchronized (queue) {
                if (!queue.isEmpty()) {
                    runnable = (Runnable) queue.poll();
                }
            }
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
        while (true) {
        }
    }

    private void a(com.tencent.liteav.basic.h.a[] aVarArr) {
        if (this.a == null) {
            this.a = new x[aVarArr.length];
            int i = 0;
            while (i < aVarArr.length) {
                this.a[i] = new x();
                this.a[i].a(true);
                if (this.a[i].c()) {
                    i++;
                } else {
                    TXCLog.e(this.i, "mRotateScaleFilter[i] failed!");
                    return;
                }
            }
        }
        if (this.a != null) {
            for (int i2 = 0; i2 < aVarArr.length; i2++) {
                if (this.a[i2] != null) {
                    this.a[i2].a(aVarArr[i2].c, aVarArr[i2].d);
                }
            }
        }
        if (this.b == null) {
            this.b = new b();
        }
    }

    private void b() {
        if (this.a != null) {
            for (int i = 0; i < this.a.length; i++) {
                if (this.a[i] != null) {
                    this.a[i].e();
                    this.a[i] = null;
                }
            }
            this.a = null;
        }
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
    }

    public void a() {
        b();
    }
}
