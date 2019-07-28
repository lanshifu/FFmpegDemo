package com.tomatolive.library.ui.view.gift;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.yalantis.ucrop.view.CropImageView;

public class GiftAnimationUtil {
    public static ObjectAnimator createFlyFromLtoR(View view, float f, float f2, int i, TimeInterpolator timeInterpolator) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", new float[]{f, f2});
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.setDuration((long) i);
        return ofFloat;
    }

    public static ObjectAnimator createFlyFromRtoL(View view, float f, float f2, int i, TimeInterpolator timeInterpolator) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationX", new float[]{f, f2});
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.setDuration((long) i);
        return ofFloat;
    }

    public static AnimationDrawable startAnimationDrawable(ImageView imageView) {
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        if (animationDrawable != null) {
            imageView.setVisibility(0);
            animationDrawable.start();
        }
        return animationDrawable;
    }

    public static void setAnimationDrawable(ImageView imageView, AnimationDrawable animationDrawable) {
        imageView.setBackground(animationDrawable);
    }

    public static ObjectAnimator scaleGiftNum(TextView textView) {
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("scaleX", new float[]{1.2f, 0.8f, 1.0f});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("scaleY", new float[]{1.2f, 0.8f, 1.0f});
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f});
        return ObjectAnimator.ofPropertyValuesHolder(textView, new PropertyValuesHolder[]{ofFloat, ofFloat2, ofFloat3}).setDuration(150);
    }

    public static ObjectAnimator createFadeAnimator(View view, float f, float f2, int i, int i2) {
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("translationY", new float[]{f, f2});
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{ofFloat, ofFloat2});
        ofPropertyValuesHolder.setStartDelay((long) i2);
        ofPropertyValuesHolder.setDuration((long) i);
        return ofPropertyValuesHolder;
    }

    public static AnimatorSet startAnimation(ObjectAnimator objectAnimator, ObjectAnimator objectAnimator2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator).before(objectAnimator2);
        animatorSet.start();
        return animatorSet;
    }

    public static AnimatorSet startAnimation(ObjectAnimator objectAnimator, ObjectAnimator objectAnimator2, ObjectAnimator objectAnimator3, ObjectAnimator objectAnimator4, ObjectAnimator objectAnimator5) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator).before(objectAnimator2);
        animatorSet.play(objectAnimator3).after(objectAnimator2);
        animatorSet.play(objectAnimator4).after(objectAnimator3);
        animatorSet.play(objectAnimator5).after(objectAnimator4);
        animatorSet.start();
        return animatorSet;
    }
}
