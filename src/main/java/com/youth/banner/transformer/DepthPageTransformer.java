package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class DepthPageTransformer extends ABaseTransformer {
    /* Access modifiers changed, original: protected */
    public boolean b() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        if (f <= CropImageView.DEFAULT_ASPECT_RATIO) {
            view.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        } else if (f <= 1.0f) {
            float abs = ((1.0f - Math.abs(f)) * 0.25f) + 0.75f;
            view.setAlpha(1.0f - f);
            view.setPivotY(((float) view.getHeight()) * 0.5f);
            view.setTranslationX(((float) view.getWidth()) * (-f));
            view.setScaleX(abs);
            view.setScaleY(abs);
        }
    }
}
