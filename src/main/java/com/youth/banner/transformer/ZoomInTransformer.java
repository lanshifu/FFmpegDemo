package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class ZoomInTransformer extends ABaseTransformer {
    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        float abs = f < CropImageView.DEFAULT_ASPECT_RATIO ? f + 1.0f : Math.abs(1.0f - f);
        view.setScaleX(abs);
        view.setScaleY(abs);
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        if (f >= -1.0f && f <= 1.0f) {
            f2 = 1.0f - (abs - 1.0f);
        }
        view.setAlpha(f2);
    }
}
