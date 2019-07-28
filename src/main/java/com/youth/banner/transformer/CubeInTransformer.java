package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class CubeInTransformer extends ABaseTransformer {
    public boolean b() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        view.setPivotX(f > CropImageView.DEFAULT_ASPECT_RATIO ? CropImageView.DEFAULT_ASPECT_RATIO : (float) view.getWidth());
        view.setPivotY(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setRotationY(f * -90.0f);
    }
}
