package defpackage;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.support.v4.util.LongSparseArray;
import com.airbnb.lottie.f;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.content.c;
import com.airbnb.lottie.model.content.e;
import com.airbnb.lottie.model.layer.a;

/* compiled from: GradientStrokeContent */
/* renamed from: m */
public class m extends f {
    private final String b;
    private final LongSparseArray<LinearGradient> c = new LongSparseArray();
    private final LongSparseArray<RadialGradient> d = new LongSparseArray();
    private final RectF e = new RectF();
    private final GradientType f;
    private final int g;
    private final x<c, c> h;
    private final x<PointF, PointF> i;
    private final x<PointF, PointF> j;

    public m(f fVar, a aVar, e eVar) {
        super(fVar, aVar, eVar.h().toPaintCap(), eVar.i().toPaintJoin(), eVar.d(), eVar.g(), eVar.j(), eVar.k());
        this.b = eVar.a();
        this.f = eVar.b();
        this.g = (int) (fVar.r().c() / 32.0f);
        this.h = eVar.c().a();
        this.h.a((x.a) this);
        aVar.a(this.h);
        this.i = eVar.e().a();
        this.i.a((x.a) this);
        aVar.a(this.i);
        this.j = eVar.f().a();
        this.j.a((x.a) this);
        aVar.a(this.j);
    }

    public void a(Canvas canvas, Matrix matrix, int i) {
        a(this.e, matrix);
        if (this.f == GradientType.Linear) {
            this.a.setShader(c());
        } else {
            this.a.setShader(d());
        }
        super.a(canvas, matrix, i);
    }

    public String b() {
        return this.b;
    }

    private LinearGradient c() {
        long e = (long) e();
        LinearGradient linearGradient = (LinearGradient) this.c.get(e);
        if (linearGradient != null) {
            return linearGradient;
        }
        PointF pointF = (PointF) this.i.e();
        PointF pointF2 = (PointF) this.j.e();
        c cVar = (c) this.h.e();
        LinearGradient linearGradient2 = new LinearGradient((float) ((int) ((this.e.left + (this.e.width() / 2.0f)) + pointF.x)), (float) ((int) ((this.e.top + (this.e.height() / 2.0f)) + pointF.y)), (float) ((int) ((this.e.left + (this.e.width() / 2.0f)) + pointF2.x)), (float) ((int) ((this.e.top + (this.e.height() / 2.0f)) + pointF2.y)), cVar.b(), cVar.a(), TileMode.CLAMP);
        this.c.put(e, linearGradient2);
        return linearGradient2;
    }

    private RadialGradient d() {
        long e = (long) e();
        RadialGradient radialGradient = (RadialGradient) this.d.get(e);
        if (radialGradient != null) {
            return radialGradient;
        }
        PointF pointF = (PointF) this.i.e();
        PointF pointF2 = (PointF) this.j.e();
        c cVar = (c) this.h.e();
        int[] b = cVar.b();
        float[] a = cVar.a();
        int width = (int) ((this.e.left + (this.e.width() / 2.0f)) + pointF.x);
        int height = (int) ((this.e.top + (this.e.height() / 2.0f)) + pointF.y);
        float f = (float) width;
        float f2 = (float) height;
        RadialGradient radialGradient2 = new RadialGradient(f, f2, (float) Math.hypot((double) (((int) ((this.e.left + (this.e.width() / 2.0f)) + pointF2.x)) - width), (double) (((int) ((this.e.top + (this.e.height() / 2.0f)) + pointF2.y)) - height)), b, a, TileMode.CLAMP);
        this.d.put(e, radialGradient2);
        return radialGradient2;
    }

    private int e() {
        int round = Math.round(this.i.f() * ((float) this.g));
        int round2 = Math.round(this.j.f() * ((float) this.g));
        int round3 = Math.round(this.h.f() * ((float) this.g));
        int i = round != 0 ? 527 * round : 17;
        if (round2 != 0) {
            i = (i * 31) * round2;
        }
        return round3 != 0 ? (i * 31) * round3 : i;
    }
}
