package defpackage;

import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.content.ShapeTrimPath.Type;
import defpackage.x.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: TrimPathContent */
/* renamed from: w */
public class w implements g, a {
    private final String a;
    private final List<a> b = new ArrayList();
    private final Type c;
    private final x<?, Float> d;
    private final x<?, Float> e;
    private final x<?, Float> f;

    public void a(List<g> list, List<g> list2) {
    }

    public w(com.airbnb.lottie.model.layer.a aVar, ShapeTrimPath shapeTrimPath) {
        this.a = shapeTrimPath.a();
        this.c = shapeTrimPath.b();
        this.d = shapeTrimPath.d().a();
        this.e = shapeTrimPath.c().a();
        this.f = shapeTrimPath.e().a();
        aVar.a(this.d);
        aVar.a(this.e);
        aVar.a(this.f);
        this.d.a((a) this);
        this.e.a((a) this);
        this.f.a((a) this);
    }

    public void a() {
        for (int i = 0; i < this.b.size(); i++) {
            ((a) this.b.get(i)).a();
        }
    }

    public String b() {
        return this.a;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(a aVar) {
        this.b.add(aVar);
    }

    /* Access modifiers changed, original: 0000 */
    public Type c() {
        return this.c;
    }

    public x<?, Float> d() {
        return this.d;
    }

    public x<?, Float> e() {
        return this.e;
    }

    public x<?, Float> f() {
        return this.f;
    }
}
