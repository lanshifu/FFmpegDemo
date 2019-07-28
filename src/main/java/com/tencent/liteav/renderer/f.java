package com.tencent.liteav.renderer;

import android.graphics.SurfaceTexture;
import android.os.Build.VERSION;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.tencent.liteav.basic.e.d;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.basic.util.b;
import com.tencent.rtmp.TXLiveConstants;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;
import javax.microedition.khronos.egl.EGLContext;

/* compiled from: TXCVideoRender */
public class f extends com.tencent.liteav.basic.module.a implements SurfaceTextureListener {
    private static final float[] a = new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, -1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f};
    private SurfaceTexture b;
    protected TextureView c;
    protected e d;
    protected int e = 0;
    protected int f = 0;
    protected int g = 0;
    protected int h = 0;
    protected volatile int i = -1;
    protected int j = 0;
    protected int k = 0;
    protected g l;
    WeakReference<com.tencent.liteav.basic.d.a> m;
    private long n;
    private int o = 800;
    private d p;
    private i q;
    private Surface r;
    private int s = 0;
    private int t;
    private int[] u;
    private boolean v = false;
    private boolean w = false;
    private a x = new a();

    /* compiled from: TXCVideoRender */
    public static class a {
        public long a;
        public long b;
        public long c;
        public long d;
        public long e;
        public long f;
        public long g;
        public long h;
        public long i;
        public long j;
        public int k;
        public int l;
    }

    public SurfaceTexture a() {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public void a(SurfaceTexture surfaceTexture) {
    }

    /* Access modifiers changed, original: protected */
    public void b(SurfaceTexture surfaceTexture) {
    }

    /* Access modifiers changed, original: protected */
    public void g() {
    }

    /* Access modifiers changed, original: protected */
    public void h() {
    }

    /* Access modifiers changed, original: protected */
    public void o() {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void a(long j) {
        this.n = j;
    }

    public void a(int i) {
        if (i > 0) {
            this.o = i;
        }
    }

    public void a(g gVar) {
        this.l = gVar;
    }

    public void a(com.tencent.liteav.basic.d.a aVar) {
        this.m = new WeakReference(aVar);
    }

    public void a(TextureView textureView) {
        b(textureView);
    }

    public void a(Surface surface) {
        b(surface);
    }

    public void a(long j, int i, int i2) {
        a(i, i2);
        b();
    }

    public void a(SurfaceTexture surfaceTexture, int i, int i2) {
        a(i, i2);
        b();
    }

    public void a(int i, int i2, int i3, boolean z, int i4) {
        a(i2, i3);
    }

    public void i() {
        if (VERSION.SDK_INT >= 21) {
            this.w = true;
        } else {
            this.w = false;
        }
        this.v = false;
        r();
    }

    public void j() {
        this.v = false;
        this.w = false;
        this.u = null;
        if (this.i == 1) {
            this.i = -1;
            h();
            synchronized (this) {
                if (this.p != null) {
                    this.p.a();
                    this.p = null;
                }
            }
        }
    }

    public void b(int i, int i2) {
        a(i, i2);
    }

    public void b(int i) {
        this.t = i;
        if (this.d != null) {
            this.d.a(i);
        }
    }

    public void c(int i) {
        this.s = i;
        if (this.d != null) {
            this.d.c(i);
        }
    }

    public int k() {
        if (this.c != null) {
            return this.c.getWidth();
        }
        return this.r != null ? this.j : 0;
    }

    public int l() {
        if (this.c != null) {
            return this.c.getHeight();
        }
        return this.r != null ? this.k : 0;
    }

    public int m() {
        return this.g;
    }

    public int n() {
        return this.h;
    }

    private void b(TextureView textureView) {
        int i = 0;
        this.i = 0;
        if ((this.c == null && textureView != null) || !(this.c == null || this.c.equals(textureView))) {
            i = 1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("play:vrender: set video view @old=");
        stringBuilder.append(this.c);
        stringBuilder.append(",new=");
        stringBuilder.append(textureView);
        TXCLog.w("TXCVideoRender", stringBuilder.toString());
        if (i != 0) {
            if (this.c != null && this.b == null) {
                b(this.c.getSurfaceTexture());
            }
            this.c = textureView;
            if (this.c != null) {
                this.e = this.c.getWidth();
                this.f = this.c.getHeight();
                this.d = new e(this.c);
                this.d.b(this.g, this.h);
                this.d.a(this.e, this.f);
                this.c.setSurfaceTextureListener(this);
                if (this.b != null) {
                    if (VERSION.SDK_INT >= 16 && this.c.getSurfaceTexture() != this.b) {
                        this.c.setSurfaceTexture(this.b);
                    }
                } else if (this.c.isAvailable()) {
                    a(this.c.getSurfaceTexture());
                }
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(int i, int i2) {
        if (this.g != i || this.h != i2) {
            if (this.g != i || this.h != i2) {
                this.g = i;
                this.h = i2;
                if (this.d != null) {
                    this.d.b(this.g, this.h);
                }
            }
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("play:vrender: texture available @");
        stringBuilder.append(surfaceTexture);
        TXCLog.w("TXCVideoRender", stringBuilder.toString());
        this.e = i;
        this.f = i2;
        if (this.d != null) {
            this.d.a(this.e, this.f);
        }
        if (this.b != null) {
            if (VERSION.SDK_INT >= 16 && this.c.getSurfaceTexture() != this.b) {
                this.c.setSurfaceTexture(this.b);
            }
            this.b = null;
            return;
        }
        a(surfaceTexture);
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("play:vrender: texture size change new:");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        stringBuilder.append(" old:");
        stringBuilder.append(this.e);
        stringBuilder.append(",");
        stringBuilder.append(this.f);
        TXCLog.w("TXCVideoRender", stringBuilder.toString());
        this.e = i;
        this.f = i2;
        if (this.d != null) {
            this.d.a(this.e, this.f);
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("play:vrender:  onSurfaceTextureDestroyed when need save texture : ");
            stringBuilder.append(this.w);
            TXCLog.w("TXCVideoRender", stringBuilder.toString());
            if (this.w) {
                this.b = surfaceTexture;
            } else {
                this.x.a = 0;
                b(surfaceTexture);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.b == null;
    }

    private void b(Surface surface) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("surface-render: set surface ");
        stringBuilder.append(surface);
        TXCLog.i("TXCVideoRender", stringBuilder.toString());
        this.r = surface;
        this.i = 1;
        if (surface != null) {
            g();
            return;
        }
        synchronized (this) {
            if (this.p != null) {
                this.p.a();
                this.p = null;
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void p() {
        synchronized (this) {
            if (this.p != null) {
                this.p.a();
                this.p = null;
            }
        }
        if (this.q != null) {
            this.q.c();
            this.q = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(EGLContext eGLContext, int i, float[] fArr, boolean z) {
        if (this.i == 1) {
            int[] a = a(i, this.g, this.h, z);
            this.u = a;
            int i2 = a[0];
            int i3 = a[1];
            int i4 = a[2];
            synchronized (this) {
                if (this.r != null) {
                    if (!(this.p == null || this.p.b() == this.r)) {
                        this.p.a();
                        this.p = null;
                    }
                    if (this.p == null && this.i == 1) {
                        this.p = new d();
                        this.p.a(eGLContext, this.r);
                    }
                    if (this.p != null && this.i == 1) {
                        this.p.a(i2, false, 0, this.j, this.k, i3, i4, false);
                    }
                } else if (this.p != null) {
                    this.p.a();
                    this.p = null;
                }
            }
        }
    }

    private int[] a(int i, int i2, int i3, boolean z) {
        if (!(this.q == null || this.q.a() == z)) {
            this.q.c();
            this.q = null;
        }
        if (this.q == null) {
            this.q = new i(Boolean.valueOf(z));
            this.q.b();
        }
        if (!z) {
            this.q.a(a);
        }
        int i4 = this.j;
        int i5 = this.k;
        if (this.t == 0) {
            this.q.a(i.a);
        } else {
            this.q.a(i.b);
        }
        this.q.b(this.s);
        this.q.b(i2, i3);
        this.q.a(i4, i5);
        return new int[]{this.q.d(i), i4, i5};
    }

    public void c(final int i, final int i2) {
        if (i != this.j || i2 != this.k) {
            if (this.p == null || this.i != 1 || this.u == null) {
                this.j = i;
                this.k = i2;
                return;
            }
            this.p.a(new Runnable() {
                public void run() {
                    f.this.j = i;
                    f.this.k = i2;
                    if (f.this.p != null) {
                        f.this.p.a(f.this.u[0], false, 0, f.this.j, f.this.k, f.this.u[1], f.this.u[2], true);
                    }
                }
            });
        }
    }

    public a q() {
        return this.x;
    }

    public void r() {
        this.x.a = 0;
        this.x.b = 0;
        this.x.c = 0;
        this.x.d = 0;
        this.x.e = 0;
        this.x.f = 0;
        this.x.g = 0;
        this.x.h = 0;
        this.x.i = 0;
        this.x.k = 0;
        this.x.l = 0;
        setStatusValue(6001, Long.valueOf(0));
        setStatusValue(6002, Double.valueOf(0.0d));
        setStatusValue(6003, Long.valueOf(0));
        setStatusValue(6005, Long.valueOf(0));
        setStatusValue(6006, Long.valueOf(0));
        setStatusValue(6004, Long.valueOf(0));
    }

    private long b(long j) {
        long timeTick = TXCTimeUtil.getTimeTick();
        return j > timeTick ? 0 : timeTick - j;
    }

    private void b() {
        a aVar;
        if (!this.v) {
            b.a(this.m, this.n, (int) TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME, "渲染首个视频数据包(IDR)");
            setStatusValue(6001, Long.valueOf(TXCTimeUtil.getTimeTick()));
            this.v = true;
        }
        a aVar2 = this.x;
        aVar2.c++;
        if (this.x.a == 0) {
            this.x.a = TXCTimeUtil.getTimeTick();
        } else {
            long timeTick = TXCTimeUtil.getTimeTick() - this.x.a;
            if (timeTick >= 1000) {
                double d = (double) (this.x.c - this.x.b);
                Double.isNaN(d);
                d *= 1000.0d;
                double d2 = (double) timeTick;
                Double.isNaN(d2);
                setStatusValue(6002, Double.valueOf(d / d2));
                this.x.b = this.x.c;
                aVar = this.x;
                aVar.a += timeTick;
            }
        }
        if (this.x.d != 0) {
            StringBuilder stringBuilder;
            this.x.i = b(this.x.d);
            if (this.x.i > 500) {
                aVar = this.x;
                aVar.e++;
                setStatusValue(6003, Long.valueOf(this.x.e));
                if (this.x.i > this.x.h) {
                    this.x.h = this.x.i;
                    setStatusValue(6005, Long.valueOf(this.x.h));
                }
                aVar = this.x;
                aVar.g += this.x.i;
                setStatusValue(6006, Long.valueOf(this.x.g));
                stringBuilder = new StringBuilder();
                stringBuilder.append("render frame count:");
                stringBuilder.append(this.x.c);
                stringBuilder.append(" block time:");
                stringBuilder.append(this.x.i);
                stringBuilder.append("> 500");
                TXCLog.w("TXCVideoRender", stringBuilder.toString());
            }
            if (this.x.i > ((long) this.o)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("render frame count:");
                stringBuilder.append(this.x.c);
                stringBuilder.append(" block time:");
                stringBuilder.append(this.x.i);
                stringBuilder.append("> ");
                stringBuilder.append(this.o);
                TXCLog.w("TXCVideoRender", stringBuilder.toString());
            }
            if (this.x.i > 1000) {
                aVar2 = this.x;
                aVar2.f++;
                setStatusValue(6004, Long.valueOf(this.x.f));
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("render frame count:");
                stringBuilder2.append(this.x.c);
                stringBuilder2.append(" block time:");
                stringBuilder2.append(this.x.i);
                stringBuilder2.append("> 1000");
                TXCLog.w("TXCVideoRender", stringBuilder2.toString());
                WeakReference weakReference = this.m;
                long j = this.n;
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("当前视频播放出现卡顿");
                stringBuilder3.append(this.x.i);
                stringBuilder3.append("ms");
                b.a(weakReference, j, (int) TXLiveConstants.PLAY_WARNING_VIDEO_PLAY_LAG, stringBuilder3.toString());
            }
        }
        this.x.d = TXCTimeUtil.getTimeTick();
        this.x.l = this.h;
        this.x.k = this.g;
    }
}
