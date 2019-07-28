package com.youth.banner.transformer;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class TabletTransformer extends ABaseTransformer {
    private static final Matrix a = new Matrix();
    private static final Camera b = new Camera();
    private static final float[] c = new float[2];

    /* Access modifiers changed, original: protected */
    public void a(View view, float f) {
        float abs = (f < CropImageView.DEFAULT_ASPECT_RATIO ? 30.0f : -30.0f) * Math.abs(f);
        view.setTranslationX(a(abs, view.getWidth(), view.getHeight()));
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(CropImageView.DEFAULT_ASPECT_RATIO);
        view.setRotationY(abs);
    }

    protected static final float a(float f, int i, int i2) {
        a.reset();
        b.save();
        b.rotateY(Math.abs(f));
        b.getMatrix(a);
        b.restore();
        a.preTranslate(((float) (-i)) * 0.5f, ((float) (-i2)) * 0.5f);
        float f2 = (float) i;
        float f3 = (float) i2;
        a.postTranslate(f2 * 0.5f, 0.5f * f3);
        c[0] = f2;
        c[1] = f3;
        a.mapPoints(c);
        return (f2 - c[0]) * (f > CropImageView.DEFAULT_ASPECT_RATIO ? 1.0f : -1.0f);
    }
}
