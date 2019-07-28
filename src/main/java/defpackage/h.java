package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import com.airbnb.lottie.f;
import com.airbnb.lottie.model.content.b;
import com.airbnb.lottie.model.content.j;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.x.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ContentGroup */
/* renamed from: h */
public class h implements au, i, q, a {
    private final Matrix a;
    private final Path b;
    private final RectF c;
    private final String d;
    private final List<g> e;
    private final f f;
    @Nullable
    private List<q> g;
    @Nullable
    private al h;

    private static List<g> a(f fVar, com.airbnb.lottie.model.layer.a aVar, List<b> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            g a = ((b) list.get(i)).a(fVar, aVar);
            if (a != null) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    @Nullable
    static bh a(List<b> list) {
        for (int i = 0; i < list.size(); i++) {
            b bVar = (b) list.get(i);
            if (bVar instanceof bh) {
                return (bh) bVar;
            }
        }
        return null;
    }

    public h(f fVar, com.airbnb.lottie.model.layer.a aVar, j jVar) {
        this(fVar, aVar, jVar.a(), h.a(fVar, aVar, jVar.b()), h.a(jVar.b()));
    }

    h(f fVar, com.airbnb.lottie.model.layer.a aVar, String str, List<g> list, @Nullable bh bhVar) {
        int size;
        this.a = new Matrix();
        this.b = new Path();
        this.c = new RectF();
        this.d = str;
        this.f = fVar;
        this.e = list;
        if (bhVar != null) {
            this.h = bhVar.h();
            this.h.a(aVar);
            this.h.a(this);
        }
        ArrayList arrayList = new ArrayList();
        for (size = list.size() - 1; size >= 0; size--) {
            g gVar = (g) list.get(size);
            if (gVar instanceof n) {
                arrayList.add((n) gVar);
            }
        }
        for (size = arrayList.size() - 1; size >= 0; size--) {
            ((n) arrayList.get(size)).a(list.listIterator(list.size()));
        }
    }

    public void a() {
        this.f.invalidateSelf();
    }

    public String b() {
        return this.d;
    }

    public void a(List<g> list, List<g> list2) {
        ArrayList arrayList = new ArrayList(list.size() + this.e.size());
        arrayList.addAll(list);
        for (int size = this.e.size() - 1; size >= 0; size--) {
            g gVar = (g) this.e.get(size);
            gVar.a(arrayList, this.e.subList(0, size));
            arrayList.add(gVar);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public List<q> c() {
        if (this.g == null) {
            this.g = new ArrayList();
            for (int i = 0; i < this.e.size(); i++) {
                g gVar = (g) this.e.get(i);
                if (gVar instanceof q) {
                    this.g.add((q) gVar);
                }
            }
        }
        return this.g;
    }

    /* Access modifiers changed, original: 0000 */
    public Matrix d() {
        if (this.h != null) {
            return this.h.d();
        }
        this.a.reset();
        return this.a;
    }

    public Path e() {
        this.a.reset();
        if (this.h != null) {
            this.a.set(this.h.d());
        }
        this.b.reset();
        for (int size = this.e.size() - 1; size >= 0; size--) {
            g gVar = (g) this.e.get(size);
            if (gVar instanceof q) {
                this.b.addPath(((q) gVar).e(), this.a);
            }
        }
        return this.b;
    }

    public void a(Canvas canvas, Matrix matrix, int i) {
        this.a.set(matrix);
        if (this.h != null) {
            this.a.preConcat(this.h.d());
            i = (int) ((((((float) ((Integer) this.h.a().e()).intValue()) / 100.0f) * ((float) i)) / 255.0f) * 255.0f);
        }
        for (int size = this.e.size() - 1; size >= 0; size--) {
            Object obj = this.e.get(size);
            if (obj instanceof i) {
                ((i) obj).a(canvas, this.a, i);
            }
        }
    }

    public void a(RectF rectF, Matrix matrix) {
        this.a.set(matrix);
        if (this.h != null) {
            this.a.preConcat(this.h.d());
        }
        this.c.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        for (int size = this.e.size() - 1; size >= 0; size--) {
            g gVar = (g) this.e.get(size);
            if (gVar instanceof i) {
                ((i) gVar).a(this.c, this.a);
                if (rectF.isEmpty()) {
                    rectF.set(this.c);
                } else {
                    rectF.set(Math.min(rectF.left, this.c.left), Math.min(rectF.top, this.c.top), Math.max(rectF.right, this.c.right), Math.max(rectF.bottom, this.c.bottom));
                }
            }
        }
    }

    public void a(at atVar, int i, List<at> list, at atVar2) {
        if (atVar.a(b(), i)) {
            if (!"__container".equals(b())) {
                atVar2 = atVar2.a(b());
                if (atVar.c(b(), i)) {
                    list.add(atVar2.a(this));
                }
            }
            if (atVar.d(b(), i)) {
                i += atVar.b(b(), i);
                for (int i2 = 0; i2 < this.e.size(); i2++) {
                    g gVar = (g) this.e.get(i2);
                    if (gVar instanceof au) {
                        ((au) gVar).a(atVar, i, list, atVar2);
                    }
                }
            }
        }
    }

    public <T> void a(T t, @Nullable dd<T> ddVar) {
        if (this.h != null) {
            this.h.a(t, ddVar);
        }
    }
}
