package org.xutils.common.util;

import com.yalantis.ucrop.view.CropImageView;
import org.xutils.x;

public final class DensityUtil {
    private static float a = -1.0f;
    private static int b = -1;
    private static int c = -1;

    private DensityUtil() {
    }

    public static float getDensity() {
        if (a <= CropImageView.DEFAULT_ASPECT_RATIO) {
            a = x.app().getResources().getDisplayMetrics().density;
        }
        return a;
    }

    public static int dip2px(float f) {
        return (int) ((f * getDensity()) + 0.5f);
    }

    public static int px2dip(float f) {
        return (int) ((f / getDensity()) + 0.5f);
    }

    public static int getScreenWidth() {
        if (b <= 0) {
            b = x.app().getResources().getDisplayMetrics().widthPixels;
        }
        return b;
    }

    public static int getScreenHeight() {
        if (c <= 0) {
            c = x.app().getResources().getDisplayMetrics().heightPixels;
        }
        return c;
    }
}
