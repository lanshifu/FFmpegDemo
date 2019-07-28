package master.flame.danmaku.danmaku.model.android;

import android.annotation.SuppressLint;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.TextPaint;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.zd;
import defpackage.ze;
import defpackage.zf;
import java.util.HashMap;
import java.util.Map;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: AndroidDisplayer */
public class a extends zd<Canvas, Typeface> {
    public Canvas a;
    private Camera b = new Camera();
    private Matrix c = new Matrix();
    private final a d = new a();
    private b e = new i();
    private int f;
    private int g;
    private float h;
    private float i = 1.0f;
    private int j = 160;
    private float k = 1.0f;
    private int l = 0;
    private boolean m = true;
    private int n = IjkMediaMeta.FF_PROFILE_H264_INTRA;
    private int o = IjkMediaMeta.FF_PROFILE_H264_INTRA;

    /* compiled from: AndroidDisplayer */
    public static class a {
        private int A = 0;
        public final TextPaint a = new TextPaint();
        public final TextPaint b;
        public int c = 4;
        public float d = 1.0f;
        public float e = 1.0f;
        public boolean f = false;
        public boolean g = true;
        public boolean h = false;
        public boolean i = this.h;
        public boolean j = true;
        private float k;
        private final Map<Float, Float> l = new HashMap(10);
        private Paint m;
        private Paint n;
        private Paint o;
        private float p = 4.0f;
        private float q = 3.5f;
        private int r = 204;
        private boolean s = this.f;
        private boolean t = this.g;
        private boolean u = this.j;
        private boolean v;
        private int w = ze.a;
        private float x = 1.0f;
        private boolean y = false;
        private int z = 0;

        public a() {
            this.a.setStrokeWidth(this.q);
            this.b = new TextPaint(this.a);
            this.m = new Paint();
            this.n = new Paint();
            this.n.setStrokeWidth((float) this.c);
            this.n.setStyle(Style.STROKE);
            this.o = new Paint();
            this.o.setStyle(Style.STROKE);
            this.o.setStrokeWidth(4.0f);
        }

        public void a(boolean z) {
            this.a.setFakeBoldText(z);
        }

        public void a(int i) {
            this.v = i != ze.a;
            this.w = i;
        }

        public void a(float f) {
            this.y = f != 1.0f;
            this.x = f;
        }

        private void a(zf zfVar, Paint paint) {
            if (this.y) {
                Float f = (Float) this.l.get(Float.valueOf(zfVar.j));
                if (f == null || this.k != this.x) {
                    this.k = this.x;
                    f = Float.valueOf(zfVar.j * this.x);
                    this.l.put(Float.valueOf(zfVar.j), f);
                }
                paint.setTextSize(f.floatValue());
            }
        }

        public boolean a(zf zfVar) {
            return (this.t || this.i) && this.q > CropImageView.DEFAULT_ASPECT_RATIO && zfVar.h != 0;
        }

        public Paint b(zf zfVar) {
            this.o.setColor(zfVar.k);
            return this.o;
        }

        public Paint c(zf zfVar) {
            this.n.setColor(zfVar.i);
            return this.n;
        }

        public TextPaint a(zf zfVar, boolean z) {
            Paint paint;
            if (z) {
                paint = this.a;
            } else {
                paint = this.b;
                paint.set(this.a);
            }
            paint.setTextSize(zfVar.j);
            a(zfVar, paint);
            if (!this.s || this.p <= CropImageView.DEFAULT_ASPECT_RATIO || zfVar.h == 0) {
                paint.clearShadowLayer();
            } else {
                paint.setShadowLayer(this.p, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, zfVar.h);
            }
            paint.setAntiAlias(this.u);
            return paint;
        }

        public void a(zf zfVar, Paint paint, boolean z) {
            if (this.v) {
                if (z) {
                    paint.setStyle(this.i ? Style.FILL : Style.FILL_AND_STROKE);
                    paint.setColor(zfVar.h & 16777215);
                    paint.setAlpha(this.i ? (int) (((float) this.r) * (((float) this.w) / ((float) ze.a))) : this.w);
                } else {
                    paint.setStyle(Style.FILL);
                    paint.setColor(zfVar.e & 16777215);
                    paint.setAlpha(this.w);
                }
            } else if (z) {
                paint.setStyle(this.i ? Style.FILL : Style.FILL_AND_STROKE);
                paint.setColor(zfVar.h & 16777215);
                paint.setAlpha(this.i ? this.r : ze.a);
            } else {
                paint.setStyle(Style.FILL);
                paint.setColor(zfVar.e & 16777215);
                paint.setAlpha(ze.a);
            }
            if (zfVar.o() == 7) {
                paint.setAlpha(zfVar.q());
            }
        }

        public void a() {
            this.l.clear();
        }

        public float b() {
            if (this.s && this.t) {
                return Math.max(this.p, this.q);
            }
            if (this.s) {
                return this.p;
            }
            return this.t ? this.q : CropImageView.DEFAULT_ASPECT_RATIO;
        }

        public void b(boolean z) {
            this.t = this.g;
            this.s = this.f;
            this.i = this.h;
            this.u = this.j;
        }
    }

    @SuppressLint({"NewApi"})
    private static final int b(Canvas canvas) {
        if (VERSION.SDK_INT >= 14) {
            return canvas.getMaximumBitmapWidth();
        }
        return canvas.getWidth();
    }

    @SuppressLint({"NewApi"})
    private static final int c(Canvas canvas) {
        if (VERSION.SDK_INT >= 14) {
            return canvas.getMaximumBitmapHeight();
        }
        return canvas.getHeight();
    }

    public void a(boolean z) {
        this.d.a(z);
    }

    public void a(int i) {
        this.d.a(i);
    }

    public void a(float f) {
        this.d.a(f);
    }

    public void a(b bVar) {
        if (bVar != this.e) {
            this.e = bVar;
        }
    }

    public b d() {
        return this.e;
    }

    public int m() {
        return this.d.z;
    }

    public int n() {
        return this.d.A;
    }

    private void d(Canvas canvas) {
        this.a = canvas;
        if (canvas != null) {
            this.f = canvas.getWidth();
            this.g = canvas.getHeight();
            if (this.m) {
                this.n = b(canvas);
                this.o = c(canvas);
            }
        }
    }

    public int e() {
        return this.f;
    }

    public int f() {
        return this.g;
    }

    public float g() {
        return this.i;
    }

    public int h() {
        return this.j;
    }

    public int a(zf zfVar) {
        float l = zfVar.l();
        float k = zfVar.k();
        if (this.a == null) {
            return 0;
        }
        Paint paint;
        Object obj;
        Paint paint2 = null;
        int i = 1;
        if (zfVar.o() != 7) {
            paint = null;
            obj = null;
        } else if (zfVar.q() == ze.b) {
            return 0;
        } else {
            Object obj2;
            if (zfVar.f == CropImageView.DEFAULT_ASPECT_RATIO && zfVar.g == CropImageView.DEFAULT_ASPECT_RATIO) {
                obj2 = null;
            } else {
                a(zfVar, this.a, k, l);
                obj2 = 1;
            }
            if (zfVar.q() != ze.a) {
                paint2 = this.d.m;
                paint2.setAlpha(zfVar.q());
            }
            paint = paint2;
            obj = obj2;
        }
        if (paint != null && paint.getAlpha() == ze.b) {
            return 0;
        }
        if (!this.e.drawCache(zfVar, this.a, k, l, paint, this.d.a)) {
            if (paint != null) {
                this.d.a.setAlpha(paint.getAlpha());
                this.d.b.setAlpha(paint.getAlpha());
            } else {
                a(this.d.a);
            }
            a(zfVar, this.a, k, l, false);
            i = 2;
        }
        if (obj != null) {
            e(this.a);
        }
        return i;
    }

    public void b(zf zfVar) {
        if (this.e != null) {
            this.e.releaseResource(zfVar);
        }
    }

    private void a(Paint paint) {
        if (paint.getAlpha() != ze.a) {
            paint.setAlpha(ze.a);
        }
    }

    private void e(Canvas canvas) {
        canvas.restore();
    }

    private int a(zf zfVar, Canvas canvas, float f, float f2) {
        this.b.save();
        if (this.h != CropImageView.DEFAULT_ASPECT_RATIO && VERSION.SDK_INT >= 12) {
            this.b.setLocation(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, this.h);
        }
        this.b.rotateY(-zfVar.g);
        this.b.rotateZ(-zfVar.f);
        this.b.getMatrix(this.c);
        this.c.preTranslate(-f, -f2);
        this.c.postTranslate(f, f2);
        this.b.restore();
        int save = canvas.save();
        canvas.concat(this.c);
        return save;
    }

    public synchronized void a(zf zfVar, Canvas canvas, float f, float f2, boolean z) {
        if (this.e != null) {
            this.e.drawDanmaku(zfVar, canvas, f, f2, z, this.d);
        }
    }

    private synchronized TextPaint c(zf zfVar, boolean z) {
        return this.d.a(zfVar, z);
    }

    public void a(zf zfVar, boolean z) {
        if (this.e != null) {
            this.e.prepare(zfVar, z);
        }
    }

    public void b(zf zfVar, boolean z) {
        TextPaint c = c(zfVar, z);
        if (this.d.t) {
            this.d.a(zfVar, c, true);
        }
        a(zfVar, c, z);
        if (this.d.t) {
            this.d.a(zfVar, c, false);
        }
    }

    private void a(zf zfVar, TextPaint textPaint, boolean z) {
        this.e.measure(zfVar, textPaint, z);
        a(zfVar, zfVar.n, zfVar.o);
    }

    private void a(zf zfVar, float f, float f2) {
        f += (float) (zfVar.l * 2);
        f2 += (float) (zfVar.l * 2);
        if (zfVar.k != 0) {
            a aVar = this.d;
            float f3 = (float) 8;
            f += f3;
            a aVar2 = this.d;
            f2 += f3;
        }
        zfVar.n = f + p();
        zfVar.o = f2;
    }

    public void c() {
        this.e.clearCaches();
        this.d.a();
    }

    public float i() {
        return this.k;
    }

    public void b(float f) {
        float max = Math.max(f, ((float) e()) / 682.0f) * 25.0f;
        this.l = (int) max;
        if (f > 1.0f) {
            this.l = (int) (max * f);
        }
    }

    public int j() {
        return this.l;
    }

    public void a(float f, int i, float f2) {
        this.i = f;
        this.j = i;
        this.k = f2;
    }

    public void a(int i, int i2) {
        this.f = i;
        this.g = i2;
        double d = (double) (((float) i) / 2.0f);
        double tan = Math.tan(0.4799655442984406d);
        Double.isNaN(d);
        this.h = (float) (d / tan);
    }

    public void a(Canvas canvas) {
        d(canvas);
    }

    /* renamed from: o */
    public Canvas a() {
        return this.a;
    }

    public float p() {
        return this.d.b();
    }

    public void b(boolean z) {
        this.m = z;
    }

    public boolean b() {
        return this.m;
    }

    public int k() {
        return this.n;
    }

    public int l() {
        return this.o;
    }
}
