package defpackage;

import android.graphics.Path;
import android.graphics.Path.FillType;
import android.support.annotation.Nullable;
import com.airbnb.lottie.f;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import com.airbnb.lottie.model.content.k;
import defpackage.x.a;
import java.util.List;

/* compiled from: ShapeContent */
/* renamed from: u */
public class u implements q, a {
    private final Path a = new Path();
    private final String b;
    private final f c;
    private final x<?, Path> d;
    private boolean e;
    @Nullable
    private w f;

    public u(f fVar, com.airbnb.lottie.model.layer.a aVar, k kVar) {
        this.b = kVar.a();
        this.c = fVar;
        this.d = kVar.b().a();
        aVar.a(this.d);
        this.d.a((a) this);
    }

    public void a() {
        c();
    }

    private void c() {
        this.e = false;
        this.c.invalidateSelf();
    }

    public void a(List<g> list, List<g> list2) {
        for (int i = 0; i < list.size(); i++) {
            g gVar = (g) list.get(i);
            if (gVar instanceof w) {
                w wVar = (w) gVar;
                if (wVar.c() == Type.Simultaneously) {
                    this.f = wVar;
                    this.f.a(this);
                }
            }
        }
    }

    public Path e() {
        if (this.e) {
            return this.a;
        }
        this.a.reset();
        this.a.set((Path) this.d.e());
        this.a.setFillType(FillType.EVEN_ODD);
        da.a(this.a, this.f);
        this.e = true;
        return this.a;
    }

    public String b() {
        return this.b;
    }
}
