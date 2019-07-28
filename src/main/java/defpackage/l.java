package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;
import com.airbnb.lottie.f;
import com.airbnb.lottie.h;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.content.c;
import com.airbnb.lottie.model.content.d;
import defpackage.x.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: GradientFillContent */
/* renamed from: l */
public class l implements i, o, a {
    @NonNull
    private final String a;
    private final com.airbnb.lottie.model.layer.a b;
    private final LongSparseArray<LinearGradient> c = new LongSparseArray();
    private final LongSparseArray<RadialGradient> d = new LongSparseArray();
    private final Matrix e = new Matrix();
    private final Path f = new Path();
    private final Paint g = new Paint(1);
    private final RectF h = new RectF();
    private final List<q> i = new ArrayList();
    private final GradientType j;
    private final x<c, c> k;
    private final x<Integer, Integer> l;
    private final x<PointF, PointF> m;
    private final x<PointF, PointF> n;
    @Nullable
    private x<ColorFilter, ColorFilter> o;
    private final f p;
    private final int q;

    public l(f fVar, com.airbnb.lottie.model.layer.a aVar, d dVar) {
        this.b = aVar;
        this.a = dVar.a();
        this.p = fVar;
        this.j = dVar.b();
        this.f.setFillType(dVar.c());
        this.q = (int) (fVar.r().c() / 32.0f);
        this.k = dVar.d().a();
        this.k.a((a) this);
        aVar.a(this.k);
        this.l = dVar.e().a();
        this.l.a((a) this);
        aVar.a(this.l);
        this.m = dVar.f().a();
        this.m.a((a) this);
        aVar.a(this.m);
        this.n = dVar.g().a();
        this.n.a((a) this);
        aVar.a(this.n);
    }

    public void a() {
        this.p.invalidateSelf();
    }

    public void a(List<g> list, List<g> list2) {
        for (int i = 0; i < list2.size(); i++) {
            g gVar = (g) list2.get(i);
            if (gVar instanceof q) {
                this.i.add((q) gVar);
            }
        }
    }

    public void a(Canvas canvas, Matrix matrix, int i) {
        Shader c;
        com.airbnb.lottie.d.b("GradientFillContent#draw");
        this.f.reset();
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            this.f.addPath(((q) this.i.get(i2)).e(), matrix);
        }
        this.f.computeBounds(this.h, false);
        if (this.j == GradientType.Linear) {
            c = c();
        } else {
            c = d();
        }
        this.e.set(matrix);
        c.setLocalMatrix(this.e);
        this.g.setShader(c);
        if (this.o != null) {
            this.g.setColorFilter((ColorFilter) this.o.e());
        }
        this.g.setAlpha(cz.a((int) ((((((float) i) / 255.0f) * ((float) ((Integer) this.l.e()).intValue())) / 100.0f) * 255.0f), 0, 255));
        canvas.drawPath(this.f, this.g);
        com.airbnb.lottie.d.c("GradientFillContent#draw");
    }

    public void a(RectF rectF, Matrix matrix) {
        this.f.reset();
        for (int i = 0; i < this.i.size(); i++) {
            this.f.addPath(((q) this.i.get(i)).e(), matrix);
        }
        this.f.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    public String b() {
        return this.a;
    }

    private LinearGradient c() {
        long e = (long) e();
        LinearGradient linearGradient = (LinearGradient) this.c.get(e);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF pointF = (PointF) this.m.e();
        PointF pointF2 = (PointF) this.n.e();
        c cVar = (c) this.k.e();
        LinearGradient linearGradient2 = new LinearGradient(pointF.x, pointF.y, pointF2.x, pointF2.y, cVar.b(), cVar.a(), TileMode.CLAMP);
        this.c.put(e, linearGradient2);
        return linearGradient2;
    }

    private RadialGradient d() {
        long e = (long) e();
        RadialGradient radialGradient = (RadialGradient) this.d.get(e);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF pointF = (PointF) this.m.e();
        PointF pointF2 = (PointF) this.n.e();
        c cVar = (c) this.k.e();
        int[] b = cVar.b();
        float[] a = cVar.a();
        float f = pointF.x;
        float f2 = pointF.y;
        RadialGradient radialGradient2 = new RadialGradient(f, f2, (float) Math.hypot((double) (pointF2.x - f), (double) (pointF2.y - f2)), b, a, TileMode.CLAMP);
        this.d.put(e, radialGradient2);
        return radialGradient2;
    }

    private int e() {
        int round = Math.round(this.m.f() * ((float) this.q));
        int round2 = Math.round(this.n.f() * ((float) this.q));
        int round3 = Math.round(this.k.f() * ((float) this.q));
        int i = round != 0 ? 527 * round : 17;
        if (round2 != 0) {
            i = (i * 31) * round2;
        }
        return round3 != 0 ? (i * 31) * round3 : i;
    }

    public void a(at atVar, int i, List<at> list, at atVar2) {
        cz.a(atVar, i, list, atVar2, this);
    }

    public <T> void a(T t, @Nullable dd<T> ddVar) {
        if (t != h.x) {
            return;
        }
        if (ddVar == null) {
            this.o = null;
            return;
        }
        this.o = new am(ddVar);
        this.o.a((a) this);
        this.b.a(this.o);
    }
}
