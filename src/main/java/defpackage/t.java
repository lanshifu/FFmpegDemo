package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.f;
import com.airbnb.lottie.h;
import com.airbnb.lottie.model.content.g;
import defpackage.x.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/* compiled from: RepeaterContent */
/* renamed from: t */
public class t implements i, n, o, q, a {
    private final Matrix a = new Matrix();
    private final Path b = new Path();
    private final f c;
    private final com.airbnb.lottie.model.layer.a d;
    private final String e;
    private final x<Float, Float> f;
    private final x<Float, Float> g;
    private final al h;
    private h i;

    public t(f fVar, com.airbnb.lottie.model.layer.a aVar, g gVar) {
        this.c = fVar;
        this.d = aVar;
        this.e = gVar.a();
        this.f = gVar.b().a();
        aVar.a(this.f);
        this.f.a((a) this);
        this.g = gVar.c().a();
        aVar.a(this.g);
        this.g.a((a) this);
        this.h = gVar.d().h();
        this.h.a(aVar);
        this.h.a(this);
    }

    public void a(ListIterator<g> listIterator) {
        if (this.i == null) {
            while (listIterator.hasPrevious() && listIterator.previous() != this) {
            }
            ArrayList arrayList = new ArrayList();
            while (listIterator.hasPrevious()) {
                arrayList.add(listIterator.previous());
                listIterator.remove();
            }
            Collections.reverse(arrayList);
            this.i = new h(this.c, this.d, "Repeater", arrayList, null);
        }
    }

    public String b() {
        return this.e;
    }

    public void a(List<g> list, List<g> list2) {
        this.i.a((List) list, (List) list2);
    }

    public Path e() {
        Path e = this.i.e();
        this.b.reset();
        float floatValue = ((Float) this.f.e()).floatValue();
        float floatValue2 = ((Float) this.g.e()).floatValue();
        for (int i = ((int) floatValue) - 1; i >= 0; i--) {
            this.a.set(this.h.b(((float) i) + floatValue2));
            this.b.addPath(e, this.a);
        }
        return this.b;
    }

    public void a(Canvas canvas, Matrix matrix, int i) {
        float floatValue = ((Float) this.f.e()).floatValue();
        float floatValue2 = ((Float) this.g.e()).floatValue();
        float floatValue3 = ((Float) this.h.b().e()).floatValue() / 100.0f;
        float floatValue4 = ((Float) this.h.c().e()).floatValue() / 100.0f;
        for (int i2 = ((int) floatValue) - 1; i2 >= 0; i2--) {
            this.a.set(matrix);
            float f = (float) i2;
            this.a.preConcat(this.h.b(f + floatValue2));
            this.i.a(canvas, this.a, (int) (((float) i) * cz.a(floatValue3, floatValue4, f / floatValue)));
        }
    }

    public void a(RectF rectF, Matrix matrix) {
        this.i.a(rectF, matrix);
    }

    public void a() {
        this.c.invalidateSelf();
    }

    public void a(at atVar, int i, List<at> list, at atVar2) {
        cz.a(atVar, i, list, atVar2, this);
    }

    public <T> void a(T t, @Nullable dd<T> ddVar) {
        if (!this.h.a(t, ddVar)) {
            if (t == h.m) {
                this.f.a((dd) ddVar);
            } else if (t == h.n) {
                this.g.a((dd) ddVar);
            }
        }
    }
}
