package com.tomatolive.library.ui.view.sticker.core.homing;

import android.animation.TypeEvaluator;

public class IMGHomingEvaluator implements TypeEvaluator<IMGHoming> {
    private IMGHoming homing;

    public IMGHomingEvaluator(IMGHoming iMGHoming) {
        this.homing = iMGHoming;
    }

    public IMGHoming evaluate(float f, IMGHoming iMGHoming, IMGHoming iMGHoming2) {
        float f2 = iMGHoming.x + ((iMGHoming2.x - iMGHoming.x) * f);
        float f3 = iMGHoming.y + ((iMGHoming2.y - iMGHoming.y) * f);
        float f4 = iMGHoming.scale + ((iMGHoming2.scale - iMGHoming.scale) * f);
        float f5 = iMGHoming.rotate + (f * (iMGHoming2.rotate - iMGHoming.rotate));
        if (this.homing == null) {
            this.homing = new IMGHoming(f2, f3, f4, f5);
        } else {
            this.homing.set(f2, f3, f4, f5);
        }
        return this.homing;
    }
}
