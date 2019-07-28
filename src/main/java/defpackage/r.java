package defpackage;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.f;
import com.airbnb.lottie.h;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.PolystarShape.Type;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.x.a;
import java.util.List;

/* compiled from: PolystarContent */
/* renamed from: r */
public class r implements o, q, a {
    private final Path a = new Path();
    private final String b;
    private final f c;
    private final Type d;
    private final x<?, Float> e;
    private final x<?, PointF> f;
    private final x<?, Float> g;
    @Nullable
    private final x<?, Float> h;
    private final x<?, Float> i;
    @Nullable
    private final x<?, Float> j;
    private final x<?, Float> k;
    @Nullable
    private w l;
    private boolean m;

    /* compiled from: PolystarContent */
    /* renamed from: r$1 */
    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a = new int[Type.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Missing block: B:7:?, code skipped:
            return;
     */
        static {
            /*
            r0 = com.airbnb.lottie.model.content.PolystarShape.Type.values();
            r0 = r0.length;
            r0 = new int[r0];
            a = r0;
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = com.airbnb.lottie.model.content.PolystarShape.Type.Star;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = 1;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = com.airbnb.lottie.model.content.PolystarShape.Type.Polygon;	 Catch:{ NoSuchFieldError -> 0x001f }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2 = 2;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.r$1.<clinit>():void");
        }
    }

    public r(f fVar, com.airbnb.lottie.model.layer.a aVar, PolystarShape polystarShape) {
        this.c = fVar;
        this.b = polystarShape.a();
        this.d = polystarShape.b();
        this.e = polystarShape.c().a();
        this.f = polystarShape.d().a();
        this.g = polystarShape.e().a();
        this.i = polystarShape.g().a();
        this.k = polystarShape.i().a();
        if (this.d == Type.Star) {
            this.h = polystarShape.f().a();
            this.j = polystarShape.h().a();
        } else {
            this.h = null;
            this.j = null;
        }
        aVar.a(this.e);
        aVar.a(this.f);
        aVar.a(this.g);
        aVar.a(this.i);
        aVar.a(this.k);
        if (this.d == Type.Star) {
            aVar.a(this.h);
            aVar.a(this.j);
        }
        this.e.a((a) this);
        this.f.a((a) this);
        this.g.a((a) this);
        this.i.a((a) this);
        this.k.a((a) this);
        if (this.d == Type.Star) {
            this.i.a((a) this);
            this.k.a((a) this);
        }
    }

    public void a() {
        c();
    }

    private void c() {
        this.m = false;
        this.c.invalidateSelf();
    }

    public void a(List<g> list, List<g> list2) {
        for (int i = 0; i < list.size(); i++) {
            g gVar = (g) list.get(i);
            if (gVar instanceof w) {
                w wVar = (w) gVar;
                if (wVar.c() == ShapeTrimPath.Type.Simultaneously) {
                    this.l = wVar;
                    this.l.a(this);
                }
            }
        }
    }

    public Path e() {
        if (this.m) {
            return this.a;
        }
        this.a.reset();
        switch (1.a[this.d.ordinal()]) {
            case 1:
                d();
                break;
            case 2:
                f();
                break;
        }
        this.a.close();
        da.a(this.a, this.l);
        this.m = true;
        return this.a;
    }

    public String b() {
        return this.b;
    }

    private void d() {
        float f;
        double d;
        double d2;
        float f2;
        float f3;
        float f4;
        double d3;
        float floatValue = ((Float) this.e.e()).floatValue();
        double toRadians = Math.toRadians((this.g == null ? 0.0d : (double) ((Float) this.g.e()).floatValue()) - 90.0d);
        double d4 = (double) floatValue;
        Double.isNaN(d4);
        float f5 = (float) (6.283185307179586d / d4);
        float f6 = f5 / 2.0f;
        floatValue -= (float) ((int) floatValue);
        if (floatValue != CropImageView.DEFAULT_ASPECT_RATIO) {
            double d5 = (double) ((1.0f - floatValue) * f6);
            Double.isNaN(d5);
            toRadians += d5;
        }
        float floatValue2 = ((Float) this.i.e()).floatValue();
        float floatValue3 = ((Float) this.h.e()).floatValue();
        float floatValue4 = this.j != null ? ((Float) this.j.e()).floatValue() / 100.0f : CropImageView.DEFAULT_ASPECT_RATIO;
        float floatValue5 = this.k != null ? ((Float) this.k.e()).floatValue() / 100.0f : CropImageView.DEFAULT_ASPECT_RATIO;
        if (floatValue != CropImageView.DEFAULT_ASPECT_RATIO) {
            f = ((floatValue2 - floatValue3) * floatValue) + floatValue3;
            float f7 = floatValue2;
            d = (double) f;
            double cos = Math.cos(toRadians);
            Double.isNaN(d);
            d2 = d4;
            f2 = (float) (d * cos);
            d4 = Math.sin(toRadians);
            Double.isNaN(d);
            f3 = (float) (d * d4);
            this.a.moveTo(f2, f3);
            d = (double) ((f5 * floatValue) / 2.0f);
            Double.isNaN(d);
            toRadians += d;
            f4 = f7;
        } else {
            d2 = d4;
            f2 = floatValue2;
            d4 = (double) f2;
            d = Math.cos(toRadians);
            Double.isNaN(d4);
            float f8 = (float) (d * d4);
            double sin = Math.sin(toRadians);
            Double.isNaN(d4);
            f3 = (float) (d4 * sin);
            this.a.moveTo(f8, f3);
            f4 = f2;
            float f9 = f3;
            d3 = (double) f6;
            Double.isNaN(d3);
            toRadians += d3;
            f2 = f8;
            f3 = f9;
            f = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        d = Math.ceil(d2) * 2.0d;
        int i = 0;
        double d6 = toRadians;
        float f10 = f2;
        float f11 = f3;
        int i2 = 0;
        while (true) {
            d3 = (double) i;
            if (d3 < d) {
                float f12;
                float f13;
                float f14;
                float f15;
                float f16;
                float f17;
                float f18;
                float f19 = i2 != 0 ? f4 : floatValue3;
                if (f == CropImageView.DEFAULT_ASPECT_RATIO || d3 != d - 2.0d) {
                    f12 = f19;
                    f19 = f6;
                } else {
                    f12 = f19;
                    f19 = (f5 * floatValue) / 2.0f;
                }
                if (f == CropImageView.DEFAULT_ASPECT_RATIO || d3 != d - 1.0d) {
                    f13 = f;
                    f14 = f12;
                } else {
                    f14 = f;
                    f13 = f14;
                }
                double d7 = (double) f14;
                double cos2 = Math.cos(d6);
                Double.isNaN(d7);
                float f20 = f19;
                float f21 = f5;
                f19 = (float) (d7 * cos2);
                cos2 = Math.sin(d6);
                Double.isNaN(d7);
                f5 = (float) (d7 * cos2);
                if (floatValue4 == CropImageView.DEFAULT_ASPECT_RATIO && floatValue5 == CropImageView.DEFAULT_ASPECT_RATIO) {
                    this.a.lineTo(f19, f5);
                    f15 = f5;
                    f16 = floatValue3;
                    f17 = floatValue4;
                    f18 = floatValue5;
                    f2 = f20;
                } else {
                    f16 = floatValue3;
                    f17 = floatValue4;
                    f = f11;
                    f18 = floatValue5;
                    float f22 = f;
                    f14 = f10;
                    double atan2 = (double) ((float) (Math.atan2((double) f, (double) f14) - 1.5707963267948966d));
                    floatValue5 = (float) Math.cos(atan2);
                    floatValue3 = (float) Math.sin(atan2);
                    float f23 = f14;
                    f15 = f5;
                    double d8 = d3;
                    double atan22 = (double) ((float) (Math.atan2((double) f5, (double) f19) - 1.5707963267948966d));
                    f3 = (float) Math.cos(atan22);
                    f5 = (float) Math.sin(atan22);
                    f2 = i2 != 0 ? f17 : f18;
                    f = ((i2 != 0 ? f16 : f4) * f2) * 0.47829f;
                    floatValue5 *= f;
                    f *= floatValue3;
                    f14 = ((i2 != 0 ? f4 : f16) * (i2 != 0 ? f18 : f17)) * 0.47829f;
                    f3 *= f14;
                    f14 *= f5;
                    if (floatValue != CropImageView.DEFAULT_ASPECT_RATIO) {
                        if (i == 0) {
                            floatValue5 *= floatValue;
                            f *= floatValue;
                        } else if (d8 == d - 1.0d) {
                            f3 *= floatValue;
                            f14 *= floatValue;
                        }
                    }
                    this.a.cubicTo(f23 - floatValue5, f22 - f, f19 + f3, f15 + f14, f19, f15);
                    f2 = f20;
                }
                d3 = (double) f2;
                Double.isNaN(d3);
                d6 += d3;
                i2 ^= 1;
                i++;
                f10 = f19;
                f = f13;
                f5 = f21;
                floatValue3 = f16;
                floatValue4 = f17;
                floatValue5 = f18;
                f11 = f15;
            } else {
                PointF pointF = (PointF) this.f.e();
                this.a.offset(pointF.x, pointF.y);
                this.a.close();
                return;
            }
        }
    }

    private void f() {
        int floor = (int) Math.floor((double) ((Float) this.e.e()).floatValue());
        double toRadians = Math.toRadians((this.g == null ? 0.0d : (double) ((Float) this.g.e()).floatValue()) - 90.0d);
        double d = (double) floor;
        Double.isNaN(d);
        float f = (float) (6.283185307179586d / d);
        float floatValue = ((Float) this.k.e()).floatValue() / 100.0f;
        float floatValue2 = ((Float) this.i.e()).floatValue();
        double d2 = (double) floatValue2;
        double cos = Math.cos(toRadians);
        Double.isNaN(d2);
        float f2 = (float) (cos * d2);
        double sin = Math.sin(toRadians);
        Double.isNaN(d2);
        float f3 = (float) (sin * d2);
        this.a.moveTo(f2, f3);
        double d3 = (double) f;
        Double.isNaN(d3);
        toRadians += d3;
        d = Math.ceil(d);
        floor = 0;
        while (((double) floor) < d) {
            double d4;
            int i;
            double d5;
            double d6;
            double cos2 = Math.cos(toRadians);
            Double.isNaN(d2);
            float f4 = (float) (cos2 * d2);
            double sin2 = Math.sin(toRadians);
            Double.isNaN(d2);
            double d7 = d;
            float f5 = (float) (d2 * sin2);
            if (floatValue != CropImageView.DEFAULT_ASPECT_RATIO) {
                d4 = d2;
                i = floor;
                d5 = toRadians;
                double atan2 = (double) ((float) (Math.atan2((double) f3, (double) f2) - 1.5707963267948966d));
                d6 = d3;
                double atan22 = (double) ((float) (Math.atan2((double) f5, (double) f4) - 1.5707963267948966d));
                float f6 = (floatValue2 * floatValue) * 0.25f;
                this.a.cubicTo(f2 - (((float) Math.cos(atan2)) * f6), f3 - (((float) Math.sin(atan2)) * f6), f4 + (((float) Math.cos(atan22)) * f6), f5 + (f6 * ((float) Math.sin(atan22))), f4, f5);
            } else {
                i = floor;
                d5 = toRadians;
                d4 = d2;
                d6 = d3;
                this.a.lineTo(f4, f5);
            }
            Double.isNaN(d6);
            toRadians = d5 + d6;
            floor = i + 1;
            f3 = f5;
            f2 = f4;
            d = d7;
            d2 = d4;
            d3 = d6;
        }
        PointF pointF = (PointF) this.f.e();
        this.a.offset(pointF.x, pointF.y);
        this.a.close();
    }

    public void a(at atVar, int i, List<at> list, at atVar2) {
        cz.a(atVar, i, list, atVar2, this);
    }

    public <T> void a(T t, @Nullable dd<T> ddVar) {
        if (t == h.o) {
            this.e.a((dd) ddVar);
        } else if (t == h.p) {
            this.g.a((dd) ddVar);
        } else if (t == h.h) {
            this.f.a((dd) ddVar);
        } else if (t == h.q && this.h != null) {
            this.h.a((dd) ddVar);
        } else if (t == h.r) {
            this.i.a((dd) ddVar);
        } else if (t == h.s && this.j != null) {
            this.j.a((dd) ddVar);
        } else if (t == h.t) {
            this.k.a((dd) ddVar);
        }
    }
}
