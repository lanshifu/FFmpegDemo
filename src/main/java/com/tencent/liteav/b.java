package com.tencent.liteav;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import com.tencent.liteav.basic.d.a;
import com.tencent.liteav.basic.e.l;
import com.tencent.liteav.basic.e.m;
import com.tencent.liteav.basic.g.c;
import com.tencent.rtmp.TXLiveConstants;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCCameraCaptureSource */
public class b implements a, m, p {
    WeakReference<a> a;
    private Context b;
    private com.tencent.liteav.capturer.a c;
    private q d;
    private boolean e;
    private h f;
    private int g;
    private boolean h;
    private int i;
    private int j;
    private l k;
    private boolean l;

    public b(Context context, h hVar, l lVar) {
        this.c = null;
        this.g = 0;
        this.h = false;
        this.i = -1;
        this.j = -1;
        this.k = null;
        this.l = false;
        this.c = new com.tencent.liteav.capturer.a();
        try {
            this.f = (h) hVar.clone();
        } catch (CloneNotSupportedException e) {
            this.f = new h();
            e.printStackTrace();
        }
        this.b = context;
        this.k = lVar;
        this.k.setSurfaceTextureListener(this);
    }

    public void a() {
        this.k.a(this.f.h);
        a(this.k.getSurfaceTexture());
    }

    public void a(boolean z) {
        c();
        this.k.a();
    }

    public void b() {
        a(this.k.getSurfaceTexture());
    }

    public void c() {
        this.c.b();
        this.e = false;
    }

    public void b(boolean z) {
        if (this.e && this.c != null) {
            h hVar = this.f;
            z = z ? !this.f.m : this.f.m;
            hVar.m = z;
            this.c.b();
            this.k.a(false);
            this.c.a(g());
            this.c.a(this.k.getSurfaceTexture());
            if (this.c.c(this.f.m) == 0) {
                this.e = true;
                a(1003, "打开摄像头成功");
            } else {
                this.e = false;
                a((int) TXLiveConstants.PUSH_ERR_OPEN_CAMERA_FAIL, "打开摄像头失败，请确认摄像头权限是否打开");
            }
            this.l = false;
        }
    }

    public boolean d() {
        return this.e;
    }

    public int e() {
        return this.c.a();
    }

    public boolean a(int i) {
        return this.c.c(i);
    }

    public void a(float f) {
        this.c.a(f);
    }

    public void b(int i) {
        this.i = i;
        h();
    }

    public void a(q qVar) {
        this.d = qVar;
    }

    public void c(final boolean z) {
        a(new Runnable() {
            public void run() {
                b.this.f.L = z;
            }
        });
    }

    public boolean d(boolean z) {
        return this.c.a(z);
    }

    public void a(c cVar) {
        if (this.k != null) {
            this.k.a(cVar.a, cVar.h, this.g, cVar.d, cVar.e);
        }
    }

    public void e(boolean z) {
        this.h = z;
    }

    public void a(Runnable runnable) {
        this.k.a(runnable);
    }

    public EGLContext f() {
        return this.k.getGLContext();
    }

    public void onNotifyEvent(int i, Bundle bundle) {
        com.tencent.liteav.basic.util.b.a(this.a, i, bundle);
    }

    public void a(a aVar) {
        this.a = new WeakReference(aVar);
    }

    public void c(int i) {
        this.j = i;
        h();
    }

    public void a(int i, int i2) {
        this.f.a = i;
        this.f.b = i2;
    }

    public void d(int i) {
        this.f.k = i;
        this.f.a();
    }

    public void a(float f, float f2) {
        if (this.c != null && this.f.D) {
            this.c.a(f, f2);
        }
    }

    private void a(SurfaceTexture surfaceTexture) {
        if (surfaceTexture != null && !this.e && this.c != null) {
            this.c.a(surfaceTexture);
            this.c.b(this.f.h);
            this.c.d(this.f.l);
            this.c.b(this.f.D);
            this.c.a(g());
            if (this.c.c(this.f.m) == 0) {
                this.e = true;
                a(1003, "打开摄像头成功");
                this.l = false;
                if (this.h && !com.tencent.liteav.audio.b.a().c()) {
                    com.tencent.liteav.audio.b.a().a(this.b);
                    this.h = false;
                    return;
                }
                return;
            }
            this.e = false;
            a((int) TXLiveConstants.PUSH_ERR_OPEN_CAMERA_FAIL, "打开摄像头失败，请确认摄像头权限是否打开");
        }
    }

    private void a(int i, String str) {
        com.tencent.liteav.basic.util.b.a(this.a, i, str);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture) {
        a(surfaceTexture);
        if (this.d != null) {
            this.d.a(surfaceTexture);
        }
    }

    public void onSurfaceTextureDestroy(SurfaceTexture surfaceTexture) {
        if (this.d != null) {
            this.d.r();
        }
    }

    public int onTextureProcess(int i, float[] fArr) {
        if (!this.e) {
            return 0;
        }
        boolean z = true;
        if (!this.l) {
            com.tencent.liteav.basic.util.b.a(this.a, (int) TXLiveConstants.PUSH_EVT_FIRST_FRAME_AVAILABLE, "首帧画面采集完成");
            this.l = true;
        }
        if (this.d != null) {
            c cVar = new c();
            cVar.d = this.c.e();
            cVar.e = this.c.f();
            cVar.f = this.f.a;
            cVar.g = this.f.b;
            cVar.i = this.c.c();
            if (!this.c.d()) {
                z = this.f.L;
            } else if (this.f.L) {
                z = false;
            }
            cVar.h = z;
            cVar.a = i;
            cVar.c = fArr;
            cVar.j = this.g;
            cVar.b = 4;
            if (cVar.i == 0 || cVar.i == TXLiveConstants.RENDER_ROTATION_180) {
                cVar.f = this.f.b;
                cVar.g = this.f.a;
            } else {
                cVar.f = this.f.a;
                cVar.g = this.f.b;
            }
            cVar.k = com.tencent.liteav.basic.util.b.a(cVar.d, cVar.e, this.f.b, this.f.a);
            this.d.b(cVar);
        }
        return 0;
    }

    private int g() {
        if (!this.f.M) {
            int i = this.f.k;
            if (i == 6) {
                return 3;
            }
            switch (i) {
                case 0:
                    return 4;
                case 1:
                    return 5;
                case 2:
                    return 6;
            }
        }
        return 7;
    }

    private void h() {
        a(new Runnable() {
            public void run() {
                if (b.this.i != -1) {
                    b.this.g = b.this.i;
                    b.this.i = -1;
                }
                if (b.this.j != -1) {
                    b.this.f.l = b.this.j;
                    b.this.c.d(b.this.f.l);
                    b.this.j = -1;
                }
            }
        });
    }
}
