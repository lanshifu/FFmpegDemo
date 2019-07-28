package defpackage;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.f;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.x.a;
import java.util.List;

/* compiled from: RectangleContent */
/* renamed from: s */
public class s implements o, q, a {
    private final Path a = new Path();
    private final RectF b = new RectF();
    private final String c;
    private final f d;
    private final x<?, PointF> e;
    private final x<?, PointF> f;
    private final x<?, Float> g;
    @Nullable
    private w h;
    private boolean i;

    public <T> void a(T t, @Nullable dd<T> ddVar) {
    }

    public s(f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.f fVar2) {
        this.c = fVar2.a();
        this.d = fVar;
        this.e = fVar2.d().a();
        this.f = fVar2.c().a();
        this.g = fVar2.b().a();
        aVar.a(this.e);
        aVar.a(this.f);
        aVar.a(this.g);
        this.e.a((a) this);
        this.f.a((a) this);
        this.g.a((a) this);
    }

    public String b() {
        return this.c;
    }

    public void a() {
        c();
    }

    private void c() {
        this.i = false;
        this.d.invalidateSelf();
    }

    public void a(List<g> list, List<g> list2) {
        for (int i = 0; i < list.size(); i++) {
            g gVar = (g) list.get(i);
            if (gVar instanceof w) {
                w wVar = (w) gVar;
                if (wVar.c() == Type.Simultaneously) {
                    this.h = wVar;
                    this.h.a(this);
                }
            }
        }
    }

    public Path e() {
        if (this.i) {
            return this.a;
        }
        this.a.reset();
        PointF pointF = (PointF) this.f.e();
        float f = pointF.x / 2.0f;
        float f2 = pointF.y / 2.0f;
        float floatValue = this.g == null ? CropImageView.DEFAULT_ASPECT_RATIO : ((Float) this.g.e()).floatValue();
        float min = Math.min(f, f2);
        if (floatValue > min) {
            floatValue = min;
        }
        PointF pointF2 = (PointF) this.e.e();
        this.a.moveTo(pointF2.x + f, (pointF2.y - f2) + floatValue);
        this.a.lineTo(pointF2.x + f, (pointF2.y + f2) - floatValue);
        if (floatValue > CropImageView.DEFAULT_ASPECT_RATIO) {
            float f3 = floatValue * 2.0f;
            this.b.set((pointF2.x + f) - f3, (pointF2.y + f2) - f3, pointF2.x + f, pointF2.y + f2);
            this.a.arcTo(this.b, CropImageView.DEFAULT_ASPECT_RATIO, 90.0f, false);
        }
        this.a.lineTo((pointF2.x - f) + floatValue, pointF2.y + f2);
        if (floatValue > CropImageView.DEFAULT_ASPECT_RATIO) {
            float f4 = floatValue * 2.0f;
            this.b.set(pointF2.x - f, (pointF2.y + f2) - f4, (pointF2.x - f) + f4, pointF2.y + f2);
            this.a.arcTo(this.b, 90.0f, 90.0f, false);
        }
        this.a.lineTo(pointF2.x - f, (pointF2.y - f2) + floatValue);
        if (floatValue > CropImageView.DEFAULT_ASPECT_RATIO) {
            float f5 = floatValue * 2.0f;
            this.b.set(pointF2.x - f, pointF2.y - f2, (pointF2.x - f) + f5, (pointF2.y - f2) + f5);
            this.a.arcTo(this.b, 180.0f, 90.0f, false);
        }
        this.a.lineTo((pointF2.x + f) - floatValue, pointF2.y - f2);
        if (floatValue > CropImageView.DEFAULT_ASPECT_RATIO) {
            floatValue *= 2.0f;
            this.b.set((pointF2.x + f) - floatValue, pointF2.y - f2, pointF2.x + f, (pointF2.y - f2) + floatValue);
            this.a.arcTo(this.b, 270.0f, 90.0f, false);
        }
        this.a.close();
        da.a(this.a, this.h);
        this.i = true;
        return this.a;
    }

    public void a(at atVar, int i, List<at> list, at atVar2) {
        cz.a(atVar, i, list, atVar2, this);
    }
}
