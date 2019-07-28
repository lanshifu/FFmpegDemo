package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class RotateUpTransformer extends ABaseTransformer {
    /* Access modifiers changed, original: protected */
    public boolean b() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        f *= -15.0f;
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setRotation(f);
    }
}
