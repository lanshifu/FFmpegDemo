package defpackage;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Outline;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.widget.TextView;

@TargetApi(21)
/* compiled from: SeekBarCompatDontCrash */
/* renamed from: fy */
class fy {
    public static void a(View view, final ga gaVar) {
        view.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                outline.setConvexPath(gaVar.a());
            }
        });
    }

    public static Drawable a(ColorStateList colorStateList) {
        return new RippleDrawable(colorStateList, null, null);
    }

    public static void a(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void a(TextView textView, int i) {
        textView.setTextDirection(i);
    }

    public static boolean a(ViewParent viewParent) {
        while (viewParent != null && (viewParent instanceof ViewGroup)) {
            if (((ViewGroup) viewParent).shouldDelayChildPressedState()) {
                return true;
            }
            viewParent = viewParent.getParent();
        }
        return false;
    }

    public static boolean a(View view) {
        return view.isHardwareAccelerated();
    }
}
