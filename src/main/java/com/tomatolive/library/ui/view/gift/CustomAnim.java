package com.tomatolive.library.ui.view.gift;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import com.tomatolive.library.R;
import com.yalantis.ucrop.view.CropImageView;

public class CustomAnim implements ICustomAnim {
    public AnimatorSet startAnim(final GiftFrameLayout giftFrameLayout, View view) {
        ObjectAnimator createFlyFromLtoR = GiftAnimationUtil.createFlyFromLtoR(giftFrameLayout, CropImageView.DEFAULT_ASPECT_RATIO, (float) giftFrameLayout.getWidth(), 800, new DecelerateInterpolator());
        createFlyFromLtoR.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                giftFrameLayout.initLayoutState();
            }

            public void onAnimationEnd(Animator animator) {
                giftFrameLayout.comboAnimation(true);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(createFlyFromLtoR);
        animatorSet.start();
        return animatorSet;
    }

    public AnimatorSet comboAnim(final GiftFrameLayout giftFrameLayout, View view, boolean z) {
        StrokeTextView strokeTextView = (StrokeTextView) view.findViewById(R.id.fq_tv_number);
        if (z) {
            strokeTextView.setVisibility(0);
            strokeTextView.setText(giftFrameLayout.getGiftNumStr(giftFrameLayout.getCombo()));
            giftFrameLayout.comboEndAnim();
        } else {
            ObjectAnimator scaleGiftNum = GiftAnimationUtil.scaleGiftNum(strokeTextView);
            scaleGiftNum.addListener(new AnimatorListenerAdapter() {
                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    giftFrameLayout.comboEndAnim();
                }
            });
            scaleGiftNum.start();
        }
        return null;
    }

    public AnimatorSet endAnim(GiftFrameLayout giftFrameLayout, View view) {
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("translationX", new float[]{CropImageView.DEFAULT_ASPECT_RATIO, -200.0f});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, 0.5f});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(giftFrameLayout, new PropertyValuesHolder[]{ofFloat, ofFloat2});
        ofPropertyValuesHolder.setStartDelay(0);
        ofPropertyValuesHolder.setDuration(800);
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat("translationX", new float[]{-200.0f, -250.0f});
        PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat("alpha", new float[]{0.5f, CropImageView.DEFAULT_ASPECT_RATIO});
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(giftFrameLayout, new PropertyValuesHolder[]{ofFloat3, ofFloat4});
        ofPropertyValuesHolder2.setStartDelay(0);
        ofPropertyValuesHolder2.setDuration(800);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofPropertyValuesHolder2).after(ofPropertyValuesHolder);
        animatorSet.start();
        return animatorSet;
    }
}
