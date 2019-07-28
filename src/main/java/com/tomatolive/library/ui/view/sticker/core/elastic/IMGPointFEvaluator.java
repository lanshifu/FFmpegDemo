package com.tomatolive.library.ui.view.sticker.core.elastic;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class IMGPointFEvaluator implements TypeEvaluator<PointF> {
    private PointF mPoint;

    public IMGPointFEvaluator(PointF pointF) {
        this.mPoint = pointF;
    }

    public PointF evaluate(float f, PointF pointF, PointF pointF2) {
        float f2 = pointF.x + ((pointF2.x - pointF.x) * f);
        float f3 = pointF.y + (f * (pointF2.y - pointF.y));
        if (this.mPoint == null) {
            return new PointF(f2, f3);
        }
        this.mPoint.set(f2, f3);
        return this.mPoint;
    }
}
