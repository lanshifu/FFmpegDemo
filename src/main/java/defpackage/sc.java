package defpackage;

import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

/* compiled from: SelectedStateListDrawable */
/* renamed from: sc */
public class sc extends StateListDrawable {
    private int a;

    public boolean isStateful() {
        return true;
    }

    public sc(Drawable drawable, int i) {
        this.a = i;
        addState(new int[]{16842913}, drawable);
        addState(new int[0], drawable);
    }

    /* Access modifiers changed, original: protected */
    public boolean onStateChange(int[] iArr) {
        Object obj = null;
        for (int i : iArr) {
            if (i == 16842913) {
                obj = 1;
            }
        }
        if (obj != null) {
            super.setColorFilter(this.a, Mode.SRC_ATOP);
        } else {
            super.clearColorFilter();
        }
        return super.onStateChange(iArr);
    }
}
