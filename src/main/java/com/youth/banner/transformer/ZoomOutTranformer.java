package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class ZoomOutTranformer extends ABaseTransformer {
    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        float abs = Math.abs(f) + 1.0f;
        view.setScaleX(abs);
        view.setScaleY(abs);
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        float f2 = (f < -1.0f || f > 1.0f) ? CropImageView.DEFAULT_ASPECT_RATIO : 1.0f - (abs - 1.0f);
        view.setAlpha(f2);
        if (f == -1.0f) {
            view.setTranslationX((float) (view.getWidth() * -1));
        }
    }
}
