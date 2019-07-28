package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class ScaleInOutTransformer extends ABaseTransformer {
    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        view.setPivotX(f < CropImageView.DEFAULT_ASPECT_RATIO ? CropImageView.DEFAULT_ASPECT_RATIO : (float) view.getWidth());
        view.setPivotY(((float) view.getHeight()) / 2.0f);
        f = f < CropImageView.DEFAULT_ASPECT_RATIO ? f + 1.0f : 1.0f - f;
        view.setScaleX(f);
        view.setScaleY(f);
    }
}
