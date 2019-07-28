package com.tomatolive.library.ui.view.sticker.core.anim;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.tomatolive.library.ui.view.sticker.core.homing.IMGHoming;
import com.tomatolive.library.ui.view.sticker.core.homing.IMGHomingEvaluator;

public class IMGHomingAnimator extends ValueAnimator {
    private boolean isRotate = false;
    private IMGHomingEvaluator mEvaluator;

    public IMGHomingAnimator() {
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void setObjectValues(Object... objArr) {
        super.setObjectValues(objArr);
        if (this.mEvaluator == null) {
            this.mEvaluator = new IMGHomingEvaluator();
        }
        setEvaluator(this.mEvaluator);
    }

    public void setHomingValues(IMGHoming iMGHoming, IMGHoming iMGHoming2) {
        setObjectValues(iMGHoming, iMGHoming2);
        this.isRotate = IMGHoming.isRotate(iMGHoming, iMGHoming2);
    }

    public boolean isRotate() {
        return this.isRotate;
    }
}
