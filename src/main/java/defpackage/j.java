package defpackage;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.f;
import com.airbnb.lottie.h;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.x.a;
import java.util.List;

/* compiled from: EllipseContent */
/* renamed from: j */
public class j implements o, q, a {
    private final Path a = new Path();
    private final String b;
    private final f c;
    private final x<?, PointF> d;
    private final x<?, PointF> e;
    private final com.airbnb.lottie.model.content.a f;
    @Nullable
    private w g;
    private boolean h;

    public j(f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.a aVar2) {
        this.b = aVar2.a();
        this.c = fVar;
        this.d = aVar2.c().a();
        this.e = aVar2.b().a();
        this.f = aVar2;
        aVar.a(this.d);
        aVar.a(this.e);
        this.d.a((a) this);
        this.e.a((a) this);
    }

    public void a() {
        c();
    }

    private void c() {
        this.h = false;
        this.c.invalidateSelf();
    }

    public void a(List<g> list, List<g> list2) {
        for (int i = 0; i < list.size(); i++) {
            g gVar = (g) list.get(i);
            if (gVar instanceof w) {
                w wVar = (w) gVar;
                if (wVar.c() == Type.Simultaneously) {
                    this.g = wVar;
                    this.g.a(this);
                }
            }
        }
    }

    public String b() {
        return this.b;
    }

    public Path e() {
        if (this.h) {
            return this.a;
        }
        this.a.reset();
        PointF pointF = (PointF) this.d.e();
        float f = pointF.x / 2.0f;
        float f2 = pointF.y / 2.0f;
        float f3 = f * 0.55228f;
        float f4 = 0.55228f * f2;
        this.a.reset();
        float f5;
        if (this.f.d()) {
            float f6 = -f2;
            this.a.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, f6);
            float f7 = CropImageView.DEFAULT_ASPECT_RATIO - f3;
            float f8 = -f;
            float f9 = CropImageView.DEFAULT_ASPECT_RATIO - f4;
            this.a.cubicTo(f7, f6, f8, f9, f8, CropImageView.DEFAULT_ASPECT_RATIO);
            f4 += CropImageView.DEFAULT_ASPECT_RATIO;
            f5 = f6;
            this.a.cubicTo(f8, f4, f7, f2, CropImageView.DEFAULT_ASPECT_RATIO, f2);
            f3 += CropImageView.DEFAULT_ASPECT_RATIO;
            this.a.cubicTo(f3, f2, f, f4, f, CropImageView.DEFAULT_ASPECT_RATIO);
            this.a.cubicTo(f, f9, f3, f5, CropImageView.DEFAULT_ASPECT_RATIO, f5);
        } else {
            float f10 = -f2;
            this.a.moveTo(CropImageView.DEFAULT_ASPECT_RATIO, f10);
            f5 = f3 + CropImageView.DEFAULT_ASPECT_RATIO;
            float f11 = CropImageView.DEFAULT_ASPECT_RATIO - f4;
            this.a.cubicTo(f5, f10, f, f11, f, CropImageView.DEFAULT_ASPECT_RATIO);
            f4 += CropImageView.DEFAULT_ASPECT_RATIO;
            this.a.cubicTo(f, f4, f5, f2, CropImageView.DEFAULT_ASPECT_RATIO, f2);
            f3 = CropImageView.DEFAULT_ASPECT_RATIO - f3;
            float f12 = -f;
            this.a.cubicTo(f3, f2, f12, f4, f12, CropImageView.DEFAULT_ASPECT_RATIO);
            f2 = f10;
            this.a.cubicTo(f12, f11, f3, f2, CropImageView.DEFAULT_ASPECT_RATIO, f2);
        }
        pointF = (PointF) this.e.e();
        this.a.offset(pointF.x, pointF.y);
        this.a.close();
        da.a(this.a, this.g);
        this.h = true;
        return this.a;
    }

    public void a(at atVar, int i, List<at> list, at atVar2) {
        cz.a(atVar, i, list, atVar2, this);
    }

    public <T> void a(T t, @Nullable dd<T> ddVar) {
        if (t == h.g) {
            this.d.a((dd) ddVar);
        } else if (t == h.h) {
            this.e.a((dd) ddVar);
        }
    }
}
