package defpackage;

import java.util.List;

/* compiled from: ColorKeyframeAnimation */
/* renamed from: y */
public class y extends ac<Integer> {
    public y(List<db<Integer>> list) {
        super(list);
    }

    /* renamed from: b */
    public Integer a(db<Integer> dbVar, float f) {
        if (dbVar.a == null || dbVar.b == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        int intValue = ((Integer) dbVar.a).intValue();
        int intValue2 = ((Integer) dbVar.b).intValue();
        if (this.b == null) {
            return Integer.valueOf(cw.a(f, intValue, intValue2));
        }
        return (Integer) this.b.a(dbVar.d, dbVar.e.floatValue(), Integer.valueOf(intValue), Integer.valueOf(intValue2), f, c(), f());
    }
}
