package defpackage;

import android.os.Build.VERSION;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.WeakHashMap;

/* compiled from: ViewPropertyAnimator */
/* renamed from: ns */
public abstract class ns {
    private static final WeakHashMap<View, ns> a = new WeakHashMap(0);

    public abstract ns a(float f);

    public abstract ns a(long j);

    public abstract ns a(Interpolator interpolator);

    public static ns a(View view) {
        ns nsVar = (ns) a.get(view);
        if (nsVar == null) {
            int intValue = Integer.valueOf(VERSION.SDK).intValue();
            if (intValue >= 14) {
                nsVar = new nu(view);
            } else if (intValue >= 11) {
                nsVar = new nt(view);
            } else {
                nsVar = new nv(view);
            }
            a.put(view, nsVar);
        }
        return nsVar;
    }
}
