package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class ZoomOutSlideTransformer extends ABaseTransformer {
    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        if (f >= -1.0f || f <= 1.0f) {
            float height = (float) view.getHeight();
            float width = (float) view.getWidth();
            float max = Math.max(0.85f, 1.0f - Math.abs(f));
            float f2 = 1.0f - max;
            float f3 = (height * f2) / 2.0f;
            f2 = (f2 * width) / 2.0f;
            view.setPivotY(height * 0.5f);
            view.setPivotX(width * 0.5f);
            if (f < CropImageView.DEFAULT_ASPECT_RATIO) {
                view.setTranslationX(f2 - (f3 / 2.0f));
            } else {
                view.setTranslationX((-f2) + (f3 / 2.0f));
            }
            view.setScaleX(max);
            view.setScaleY(max);
            view.setAlpha((((max - 0.85f) / 0.14999998f) * 0.5f) + 0.5f);
        }
    }
}
