package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

/* compiled from: SeekBarCompat */
/* renamed from: fx */
public class fx {
    public static void a(View view, ga gaVar) {
        if (VERSION.SDK_INT >= 21) {
            fy.a(view, gaVar);
        }
    }

    public static Drawable a(ColorStateList colorStateList) {
        if (VERSION.SDK_INT >= 21) {
            return fy.a(colorStateList);
        }
        return new fz(colorStateList);
    }

    public static void a(@NonNull Drawable drawable, ColorStateList colorStateList) {
        if (VERSION.SDK_INT >= 21) {
            ((RippleDrawable) drawable).setColor(colorStateList);
        } else {
            ((fz) drawable).a(colorStateList);
        }
    }

    public static void a(Drawable drawable, int i, int i2, int i3, int i4) {
        if (VERSION.SDK_INT >= 21) {
            int i5 = (i3 - i) / 8;
            DrawableCompat.setHotspotBounds(drawable, i + i5, i2 + i5, i3 - i5, i4 - i5);
            return;
        }
        drawable.setBounds(i, i2, i3, i4);
    }

    public static void a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            fy.a(view, drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void a(TextView textView, int i) {
        if (VERSION.SDK_INT >= 17) {
            fy.a(textView, i);
        }
    }

    public static boolean a(ViewParent viewParent) {
        return VERSION.SDK_INT >= 14 ? fy.a(viewParent) : false;
    }

    public static boolean a(View view) {
        return VERSION.SDK_INT >= 11 ? fy.a(view) : false;
    }
}
