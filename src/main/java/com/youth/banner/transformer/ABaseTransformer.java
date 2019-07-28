package com.youth.banner.transformer;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public abstract class ABaseTransformer implements PageTransformer {
    protected static final float a(float f, float f2) {
        return f < f2 ? f2 : f;
    }

    public abstract void a(View view, float f);

    /* Access modifiers changed, original: protected */
    public boolean a() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public boolean b() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void c(View view, float f) {
    }

    public void transformPage(View view, float f) {
        b(view, f);
        a(view, f);
        c(view, f);
    }

    /* Access modifiers changed, original: protected */
    public void b(View view, float f) {
        float width = (float) view.getWidth();
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        view.setRotationX(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setRotationY(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setRotation(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setPivotX(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setPivotY(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setTranslationY(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setTranslationX(b() ? CropImageView.DEFAULT_ASPECT_RATIO : (-width) * f);
        if (a()) {
            if (f > -1.0f && f < 1.0f) {
                f2 = 1.0f;
            }
            view.setAlpha(f2);
            return;
        }
        view.setAlpha(1.0f);
    }
}
