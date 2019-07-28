package com.youth.banner.transformer;

import android.view.View;

public class RotateDownTransformer extends ABaseTransformer {
    /* Access modifiers changed, original: protected */
    public boolean b() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        float height = (float) view.getHeight();
        f = (f * -15.0f) * -1.25f;
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(height);
        view.setRotation(f);
    }
}
