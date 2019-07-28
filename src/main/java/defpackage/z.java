package defpackage;

import java.util.List;

/* compiled from: FloatKeyframeAnimation */
/* renamed from: z */
public class z extends ac<Float> {
    public z(List<db<Float>> list) {
        super(list);
    }

    /* Access modifiers changed, original: 0000 */
    /* renamed from: b */
    public Float a(db<Float> dbVar, float f) {
        if (dbVar.a == null || dbVar.b == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        } else if (this.b == null) {
            return Float.valueOf(cz.a(((Float) dbVar.a).floatValue(), ((Float) dbVar.b).floatValue(), f));
        } else {
            return (Float) this.b.a(dbVar.d, dbVar.e.floatValue(), dbVar.a, dbVar.b, f, c(), f());
        }
    }
}
