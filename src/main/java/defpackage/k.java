package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.d;
import com.airbnb.lottie.f;
import com.airbnb.lottie.h;
import com.airbnb.lottie.model.content.i;
import defpackage.x.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FillContent */
/* renamed from: k */
public class k implements i, o, a {
    private final Path a = new Path();
    private final Paint b = new Paint(1);
    private final com.airbnb.lottie.model.layer.a c;
    private final String d;
    private final List<q> e = new ArrayList();
    private final x<Integer, Integer> f;
    private final x<Integer, Integer> g;
    @Nullable
    private x<ColorFilter, ColorFilter> h;
    private final f i;

    public k(f fVar, com.airbnb.lottie.model.layer.a aVar, i iVar) {
        this.c = aVar;
        this.d = iVar.a();
        this.i = fVar;
        if (iVar.b() == null || iVar.c() == null) {
            this.f = null;
            this.g = null;
            return;
        }
        this.a.setFillType(iVar.d());
        this.f = iVar.b().a();
        this.f.a((a) this);
        aVar.a(this.f);
        this.g = iVar.c().a();
        this.g.a((a) this);
        aVar.a(this.g);
    }

    public void a() {
        this.i.invalidateSelf();
    }

    public void a(List<g> list, List<g> list2) {
        for (int i = 0; i < list2.size(); i++) {
            g gVar = (g) list2.get(i);
            if (gVar instanceof q) {
                this.e.add((q) gVar);
            }
        }
    }

    public String b() {
        return this.d;
    }

    public void a(Canvas canvas, Matrix matrix, int i) {
        d.b("FillContent#draw");
        this.b.setColor(((Integer) this.f.e()).intValue());
        int i2 = 0;
        this.b.setAlpha(cz.a((int) ((((((float) i) / 255.0f) * ((float) ((Integer) this.g.e()).intValue())) / 100.0f) * 255.0f), 0, 255));
        if (this.h != null) {
            this.b.setColorFilter((ColorFilter) this.h.e());
        }
        this.a.reset();
        while (i2 < this.e.size()) {
            this.a.addPath(((q) this.e.get(i2)).e(), matrix);
            i2++;
        }
        canvas.drawPath(this.a, this.b);
        d.c("FillContent#draw");
    }

    public void a(RectF rectF, Matrix matrix) {
        this.a.reset();
        for (int i = 0; i < this.e.size(); i++) {
            this.a.addPath(((q) this.e.get(i)).e(), matrix);
        }
        this.a.computeBounds(rectF, false);
        rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
    }

    public void a(at atVar, int i, List<at> list, at atVar2) {
        cz.a(atVar, i, list, atVar2, this);
    }

    public <T> void a(T t, @Nullable dd<T> ddVar) {
        if (t == h.a) {
            this.f.a((dd) ddVar);
        } else if (t == h.d) {
            this.g.a((dd) ddVar);
        } else if (t != h.x) {
        } else {
            if (ddVar == null) {
                this.h = null;
                return;
            }
            this.h = new am(ddVar);
            this.h.a((a) this);
            this.c.a(this.h);
        }
    }
}
