package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import com.airbnb.lottie.d;
import com.airbnb.lottie.h;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.x.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BaseStrokeContent */
/* renamed from: f */
public abstract class f implements i, o, a {
    final Paint a = new Paint(1);
    private final PathMeasure b = new PathMeasure();
    private final Path c = new Path();
    private final Path d = new Path();
    private final RectF e = new RectF();
    private final com.airbnb.lottie.f f;
    private final com.airbnb.lottie.model.layer.a g;
    private final List<a> h = new ArrayList();
    private final float[] i;
    private final x<?, Float> j;
    private final x<?, Integer> k;
    private final List<x<?, Float>> l;
    @Nullable
    private final x<?, Float> m;
    @Nullable
    private x<ColorFilter, ColorFilter> n;

    /* compiled from: BaseStrokeContent */
    /* renamed from: f$a */
    private static final class a {
        private final List<q> a;
        @Nullable
        private final w b;

        private a(@Nullable w wVar) {
            this.a = new ArrayList();
            this.b = wVar;
        }
    }

    f(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, Cap cap, Join join, az azVar, ax axVar, List<ax> list, ax axVar2) {
        int i;
        this.f = fVar;
        this.g = aVar;
        this.a.setStyle(Style.STROKE);
        this.a.setStrokeCap(cap);
        this.a.setStrokeJoin(join);
        this.k = azVar.a();
        this.j = axVar.a();
        if (axVar2 == null) {
            this.m = null;
        } else {
            this.m = axVar2.a();
        }
        this.l = new ArrayList(list.size());
        this.i = new float[list.size()];
        for (i = 0; i < list.size(); i++) {
            this.l.add(((ax) list.get(i)).a());
        }
        aVar.a(this.k);
        aVar.a(this.j);
        for (i = 0; i < this.l.size(); i++) {
            aVar.a((x) this.l.get(i));
        }
        if (this.m != null) {
            aVar.a(this.m);
        }
        this.k.a((a) this);
        this.j.a((a) this);
        for (int i2 = 0; i2 < list.size(); i2++) {
            ((x) this.l.get(i2)).a((a) this);
        }
        if (this.m != null) {
            this.m.a((a) this);
        }
    }

    public void a() {
        this.f.invalidateSelf();
    }

    public void a(List<g> list, List<g> list2) {
        g gVar;
        w wVar = null;
        for (int size = list.size() - 1; size >= 0; size--) {
            gVar = (g) list.get(size);
            if (gVar instanceof w) {
                w wVar2 = (w) gVar;
                if (wVar2.c() == Type.Individually) {
                    wVar = wVar2;
                }
            }
        }
        if (wVar != null) {
            wVar.a(this);
        }
        Object obj = null;
        for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
            gVar = (g) list2.get(size2);
            if (gVar instanceof w) {
                w wVar3 = (w) gVar;
                if (wVar3.c() == Type.Individually) {
                    if (obj != null) {
                        this.h.add(obj);
                    }
                    obj = new a(wVar3);
                    wVar3.a(this);
                }
            }
            if (gVar instanceof q) {
                if (obj == null) {
                    obj = new a(wVar);
                }
                obj.a.add((q) gVar);
            }
        }
        if (obj != null) {
            this.h.add(obj);
        }
    }

    public void a(Canvas canvas, Matrix matrix, int i) {
        d.b("StrokeContent#draw");
        int i2 = 0;
        this.a.setAlpha(cz.a((int) ((((((float) i) / 255.0f) * ((float) ((Integer) this.k.e()).intValue())) / 100.0f) * 255.0f), 0, 255));
        this.a.setStrokeWidth(((Float) this.j.e()).floatValue() * da.a(matrix));
        if (this.a.getStrokeWidth() <= CropImageView.DEFAULT_ASPECT_RATIO) {
            d.c("StrokeContent#draw");
            return;
        }
        a(matrix);
        if (this.n != null) {
            this.a.setColorFilter((ColorFilter) this.n.e());
        }
        while (i2 < this.h.size()) {
            a aVar = (a) this.h.get(i2);
            if (aVar.b != null) {
                a(canvas, aVar, matrix);
            } else {
                d.b("StrokeContent#buildPath");
                this.c.reset();
                for (int size = aVar.a.size() - 1; size >= 0; size--) {
                    this.c.addPath(((q) aVar.a.get(size)).e(), matrix);
                }
                d.c("StrokeContent#buildPath");
                d.b("StrokeContent#drawPath");
                canvas.drawPath(this.c, this.a);
                d.c("StrokeContent#drawPath");
            }
            i2++;
        }
        d.c("StrokeContent#draw");
    }

    private void a(Canvas canvas, a aVar, Matrix matrix) {
        d.b("StrokeContent#applyTrimPath");
        if (aVar.b == null) {
            d.c("StrokeContent#applyTrimPath");
            return;
        }
        this.c.reset();
        for (int size = aVar.a.size() - 1; size >= 0; size--) {
            this.c.addPath(((q) aVar.a.get(size)).e(), matrix);
        }
        this.b.setPath(this.c, false);
        float length = this.b.getLength();
        while (this.b.nextContour()) {
            length += this.b.getLength();
        }
        float floatValue = (((Float) aVar.b.f().e()).floatValue() * length) / 360.0f;
        float floatValue2 = ((((Float) aVar.b.d().e()).floatValue() * length) / 100.0f) + floatValue;
        float floatValue3 = ((((Float) aVar.b.e().e()).floatValue() * length) / 100.0f) + floatValue;
        float f = CropImageView.DEFAULT_ASPECT_RATIO;
        for (int size2 = aVar.a.size() - 1; size2 >= 0; size2--) {
            float f2;
            this.d.set(((q) aVar.a.get(size2)).e());
            this.d.transform(matrix);
            this.b.setPath(this.d, false);
            float length2 = this.b.getLength();
            float f3 = 1.0f;
            if (floatValue3 > length) {
                f2 = floatValue3 - length;
                if (f2 < f + length2 && f < f2) {
                    da.a(this.d, floatValue2 > length ? (floatValue2 - length) / length2 : CropImageView.DEFAULT_ASPECT_RATIO, Math.min(f2 / length2, 1.0f), CropImageView.DEFAULT_ASPECT_RATIO);
                    canvas.drawPath(this.d, this.a);
                    f += length2;
                }
            }
            f2 = f + length2;
            if (f2 >= floatValue2 && f <= floatValue3) {
                if (f2 > floatValue3 || floatValue2 >= f) {
                    float f4 = floatValue2 < f ? CropImageView.DEFAULT_ASPECT_RATIO : (floatValue2 - f) / length2;
                    if (floatValue3 <= f2) {
                        f3 = (floatValue3 - f) / length2;
                    }
                    da.a(this.d, f4, f3, CropImageView.DEFAULT_ASPECT_RATIO);
                    canvas.drawPath(this.d, this.a);
                } else {
                    canvas.drawPath(this.d, this.a);
                }
            }
            f += length2;
        }
        d.c("StrokeContent#applyTrimPath");
    }

    public void a(RectF rectF, Matrix matrix) {
        d.b("StrokeContent#getBounds");
        this.c.reset();
        for (int i = 0; i < this.h.size(); i++) {
            a aVar = (a) this.h.get(i);
            for (int i2 = 0; i2 < aVar.a.size(); i2++) {
                this.c.addPath(((q) aVar.a.get(i2)).e(), matrix);
            }
        }
        this.c.computeBounds(this.e, false);
        float floatValue = ((Float) this.j.e()).floatValue() / 2.0f;
        this.e.set(this.e.left - floatValue, this.e.top - floatValue, this.e.right + floatValue, this.e.bottom + floatValue);
        rectF.set(this.e);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
        d.c("StrokeContent#getBounds");
    }

    private void a(Matrix matrix) {
        d.b("StrokeContent#applyDashPattern");
        if (this.l.isEmpty()) {
            d.c("StrokeContent#applyDashPattern");
            return;
        }
        float a = da.a(matrix);
        for (int i = 0; i < this.l.size(); i++) {
            this.i[i] = ((Float) ((x) this.l.get(i)).e()).floatValue();
            if (i % 2 == 0) {
                if (this.i[i] < 1.0f) {
                    this.i[i] = 1.0f;
                }
            } else if (this.i[i] < 0.1f) {
                this.i[i] = 0.1f;
            }
            float[] fArr = this.i;
            fArr[i] = fArr[i] * a;
        }
        this.a.setPathEffect(new DashPathEffect(this.i, this.m == null ? CropImageView.DEFAULT_ASPECT_RATIO : ((Float) this.m.e()).floatValue()));
        d.c("StrokeContent#applyDashPattern");
    }

    public void a(at atVar, int i, List<at> list, at atVar2) {
        cz.a(atVar, i, list, atVar2, this);
    }

    @CallSuper
    public <T> void a(T t, @Nullable dd<T> ddVar) {
        if (t == h.d) {
            this.k.a((dd) ddVar);
        } else if (t == h.k) {
            this.j.a((dd) ddVar);
        } else if (t != h.x) {
        } else {
            if (ddVar == null) {
                this.n = null;
                return;
            }
            this.n = new am(ddVar);
            this.n.a((a) this);
            this.g.a(this.n);
        }
    }
}
