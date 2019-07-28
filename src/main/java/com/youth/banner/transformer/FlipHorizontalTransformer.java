package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class FlipHorizontalTransformer extends ABaseTransformer {
    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        f *= 180.0f;
        float f2 = (f > 90.0f || f < -90.0f) ? CropImageView.DEFAULT_ASPECT_RATIO : 1.0f;
        view.setAlpha(f2);
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        view.setRotationY(f);
    }

    /* Access modifiers changed, original: protected */
    public void c(View view, float f) {
        super.c(view, f);
        if (f <= -0.5f || f >= 0.5f) {
            view.setVisibility(4);
        } else {
            view.setVisibility(0);
        }
    }
}
