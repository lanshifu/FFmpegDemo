package com.youth.banner.transformer;

import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class CubeOutTransformer extends ABaseTransformer {
    public boolean b() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (f < CropImageView.DEFAULT_ASPECT_RATIO) {
            f2 = (float) view.getWidth();
        }
        view.setPivotX(f2);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        view.setRotationY(f * 90.0f);
    }
}
