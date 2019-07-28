package defpackage;

import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BaseKeyframeAnimation */
/* renamed from: x */
public abstract class x<K, A> {
    final List<a> a = new ArrayList();
    @Nullable
    protected dd<A> b;
    private boolean c = false;
    private final List<? extends db<K>> d;
    private float e = CropImageView.DEFAULT_ASPECT_RATIO;
    @Nullable
    private db<K> f;

    /* compiled from: BaseKeyframeAnimation */
    /* renamed from: x$a */
    public interface a {
        void a();
    }

    public abstract A a(db<K> dbVar, float f);

    x(List<? extends db<K>> list) {
        this.d = list;
    }

    public void a() {
        this.c = true;
    }

    public void a(a aVar) {
        this.a.add(aVar);
    }

    public void a(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (f < i()) {
            f = i();
        } else if (f > d()) {
            f = d();
        }
        if (f != this.e) {
            this.e = f;
            b();
        }
    }

    public void b() {
        for (int i = 0; i < this.a.size(); i++) {
            ((a) this.a.get(i)).a();
        }
    }

    private db<K> g() {
        if (this.f != null && this.f.a(this.e)) {
            return this.f;
        }
        db dbVar = (db) this.d.get(this.d.size() - 1);
        if (this.e < dbVar.b()) {
            for (int size = this.d.size() - 1; size >= 0; size--) {
                dbVar = (db) this.d.get(size);
                if (dbVar.a(this.e)) {
                    break;
                }
            }
        }
        this.f = dbVar;
        return dbVar;
    }

    /* Access modifiers changed, original: 0000 */
    public float c() {
        if (this.c) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        db g = g();
        if (g.d()) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        return (this.e - g.b()) / (g.c() - g.b());
    }

    private float h() {
        db g = g();
        if (g.d()) {
            return CropImageView.DEFAULT_ASPECT_RATIO;
        }
        return g.c.getInterpolation(c());
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    private float i() {
        return this.d.isEmpty() ? CropImageView.DEFAULT_ASPECT_RATIO : ((db) this.d.get(0)).b();
    }

    /* Access modifiers changed, original: 0000 */
    @FloatRange(from = 0.0d, to = 1.0d)
    public float d() {
        return this.d.isEmpty() ? 1.0f : ((db) this.d.get(this.d.size() - 1)).c();
    }

    public A e() {
        return a(g(), h());
    }

    public float f() {
        return this.e;
    }

    public void a(@Nullable dd<A> ddVar) {
        if (this.b != null) {
            this.b.a(null);
        }
        this.b = ddVar;
        if (ddVar != null) {
            ddVar.a(this);
        }
    }
}
