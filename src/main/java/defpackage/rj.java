package defpackage;

import android.content.res.Resources;

/* compiled from: DensityUtil */
/* renamed from: rj */
public class rj {
    public float a = Resources.getSystem().getDisplayMetrics().density;

    public static int a(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static float a(int i) {
        return ((float) i) / Resources.getSystem().getDisplayMetrics().density;
    }

    public int b(float f) {
        return (int) ((f * this.a) + 0.5f);
    }
}
