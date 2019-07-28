package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

/* compiled from: AttrsUtils */
/* renamed from: mv */
public class mv {
    public static int a(Context context, int i) {
        int[] iArr = new int[]{i};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new TypedValue().resourceId, iArr);
        i = obtainStyledAttributes.getColor(0, -1);
        obtainStyledAttributes.recycle();
        return i;
    }

    public static boolean b(Context context, int i) {
        int[] iArr = new int[]{i};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new TypedValue().resourceId, iArr);
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
        return z;
    }

    public static Drawable c(Context context, int i) {
        int[] iArr = new int[]{i};
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new TypedValue().resourceId, iArr);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
        return drawable;
    }
}
