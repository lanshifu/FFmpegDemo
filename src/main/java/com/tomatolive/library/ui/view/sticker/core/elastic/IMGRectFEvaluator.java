package com.tomatolive.library.ui.view.sticker.core.elastic;

import android.animation.TypeEvaluator;
import android.graphics.RectF;

public class IMGRectFEvaluator implements TypeEvaluator<RectF> {
    private RectF mRect;

    public IMGRectFEvaluator(RectF rectF) {
        this.mRect = rectF;
    }

    public RectF evaluate(float f, RectF rectF, RectF rectF2) {
        float f2 = rectF.left + ((rectF2.left - rectF.left) * f);
        float f3 = rectF.top + ((rectF2.top - rectF.top) * f);
        float f4 = rectF.right + ((rectF2.right - rectF.right) * f);
        float f5 = rectF.bottom + ((rectF2.bottom - rectF.bottom) * f);
        if (this.mRect == null) {
            return new RectF(f2, f3, f4, f5);
        }
        this.mRect.set(f2, f3, f4, f5);
        return this.mRect;
    }
}
