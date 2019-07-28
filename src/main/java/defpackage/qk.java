package defpackage;

import android.graphics.Canvas;
import android.widget.ImageView.ScaleType;
import com.opensource.svgaplayer.entities.g;
import com.opensource.svgaplayer.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: SGVADrawer.kt */
/* renamed from: qk */
public class qk {
    private final qp a = new qp();
    private final f b;

    /* compiled from: SGVADrawer.kt */
    /* renamed from: qk$a */
    public final class a {
        final /* synthetic */ qk a;
        private final String b;
        private final g c;

        public a(qk qkVar, String str, g gVar) {
            kotlin.jvm.internal.f.b(gVar, "frameEntity");
            this.a = qkVar;
            this.b = str;
            this.c = gVar;
        }

        public final String a() {
            return this.b;
        }

        public final g b() {
            return this.c;
        }
    }

    public qk(f fVar) {
        kotlin.jvm.internal.f.b(fVar, "videoItem");
        this.b = fVar;
    }

    public final f b() {
        return this.b;
    }

    public final qp a() {
        return this.a;
    }

    public final List<a> a(int i) {
        Collection arrayList = new ArrayList();
        for (com.opensource.svgaplayer.entities.f fVar : this.b.e()) {
            Object obj = null;
            if (i >= 0 && i < fVar.b().size() && ((g) fVar.b().get(i)).a() > 0.0d) {
                obj = new a(this, fVar.a(), (g) fVar.b().get(i));
            }
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return (List) arrayList;
    }

    public void a(Canvas canvas, int i, ScaleType scaleType) {
        kotlin.jvm.internal.f.b(canvas, "canvas");
        kotlin.jvm.internal.f.b(scaleType, "scaleType");
        this.a.a((float) canvas.getWidth(), (float) canvas.getHeight(), (float) this.b.b().a(), (float) this.b.b().b(), scaleType);
    }
}
