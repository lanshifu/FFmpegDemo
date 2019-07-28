package defpackage;

import android.view.animation.Interpolator;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: ViscousFluidInterpolator */
/* renamed from: rn */
public class rn implements Interpolator {
    private static final float a = (1.0f / rn.a(1.0f));
    private static final float b = (1.0f - (a * rn.a(1.0f)));

    private static float a(float f) {
        f *= 8.0f;
        if (f < 1.0f) {
            return f - (1.0f - ((float) Math.exp((double) (-f))));
        }
        return ((1.0f - ((float) Math.exp((double) (1.0f - f)))) * 0.63212055f) + 0.36787945f;
    }

    public float getInterpolation(float f) {
        float a = a * rn.a(f);
        return a > CropImageView.DEFAULT_ASPECT_RATIO ? a + b : a;
    }
}
