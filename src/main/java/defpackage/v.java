package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import com.airbnb.lottie.f;
import com.airbnb.lottie.h;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.model.layer.a;

/* compiled from: StrokeContent */
/* renamed from: v */
public class v extends f {
    private final a b;
    private final String c;
    private final x<Integer, Integer> d;
    @Nullable
    private x<ColorFilter, ColorFilter> e;

    public v(f fVar, a aVar, ShapeStroke shapeStroke) {
        super(fVar, aVar, shapeStroke.g().toPaintCap(), shapeStroke.h().toPaintJoin(), shapeStroke.c(), shapeStroke.d(), shapeStroke.e(), shapeStroke.f());
        this.b = aVar;
        this.c = shapeStroke.a();
        this.d = shapeStroke.b().a();
        this.d.a((x.a) this);
        aVar.a(this.d);
    }

    public void a(Canvas canvas, Matrix matrix, int i) {
        this.a.setColor(((Integer) this.d.e()).intValue());
        if (this.e != null) {
            this.a.setColorFilter((ColorFilter) this.e.e());
        }
        super.a(canvas, matrix, i);
    }

    public String b() {
        return this.c;
    }

    public <T> void a(T t, @Nullable dd<T> ddVar) {
        super.a((Object) t, (dd) ddVar);
        if (t == h.b) {
            this.d.a((dd) ddVar);
        } else if (t != h.x) {
        } else {
            if (ddVar == null) {
                this.e = null;
                return;
            }
            this.e = new am(ddVar);
            this.e.a((x.a) this);
            this.b.a(this.d);
        }
    }
}
