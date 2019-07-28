package com.tomatolive.library.ui.view.widget.tagview;

import android.content.Context;
import android.graphics.Color;

public class Utils {
    public static float dp2px(Context context, float f) {
        return (f * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static float sp2px(Context context, float f) {
        return f * context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static int manipulateColorBrightness(int i, float f) {
        int alpha = Color.alpha(i);
        int red = Color.red(i);
        int green = Color.green(i);
        i = Color.blue(i);
        if (red > 127) {
            red = 255 - Math.round(((float) (255 - red)) * f);
        }
        if (green > 127) {
            green = 255 - Math.round(((float) (255 - green)) * f);
        }
        if (i > 127) {
            i = 255 - Math.round(((float) (255 - i)) * f);
        }
        return Color.argb(alpha, Math.min(red, 255), Math.min(green, 255), Math.min(i, 255));
    }
}
