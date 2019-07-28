package com.tomatolive.library.utils;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import com.tomatolive.library.R;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: SwipeAnimationController */
public class v {
    private static final String c = "v";
    Animation a;
    LayoutAnimationController b;
    private Context d;
    private RelativeLayout e;
    private float f;
    private float g;
    private ValueAnimator h;
    private boolean i = false;
    private float j;
    private float k;

    public v(Context context) {
        this.d = context;
        this.k = (float) context.getResources().getDisplayMetrics().widthPixels;
        this.j = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        this.h = new ValueAnimator();
        this.h.setInterpolator(new AnticipateOvershootInterpolator());
        this.h.setDuration(200);
        this.h.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                v.this.e.setTranslationX((float) ((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        this.a = AnimationUtils.loadAnimation(this.d, R.anim.fq_anim_slice_in_right);
        this.a.setDuration(150);
        this.a.setInterpolator(new AccelerateDecelerateInterpolator());
        this.b = new LayoutAnimationController(this.a);
        this.b.setOrder(1);
    }

    public void a(RelativeLayout relativeLayout) {
        this.e = relativeLayout;
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.h.isRunning()) {
            return true;
        }
        VelocityTracker obtain = VelocityTracker.obtain();
        float xVelocity;
        switch (motionEvent.getAction()) {
            case 0:
                this.f = motionEvent.getRawX();
                this.g = motionEvent.getRawY();
                obtain.addMovement(motionEvent);
                break;
            case 1:
            case 3:
                int rawX = (int) (motionEvent.getRawX() - this.f);
                obtain.addMovement(motionEvent);
                obtain.computeCurrentVelocity(100);
                xVelocity = obtain.getXVelocity(-1);
                if (this.i) {
                    if (rawX >= this.d.getResources().getDisplayMetrics().widthPixels / 5 || xVelocity > 1000.0f) {
                        if (this.e.getTranslationX() < CropImageView.DEFAULT_ASPECT_RATIO) {
                            this.e.setLayoutAnimation(null);
                            this.e.setTranslationX((float) ((int) this.k));
                            this.e.setLayoutAnimation(this.b);
                            this.e.startLayoutAnimation();
                            this.e.setTranslationX(CropImageView.DEFAULT_ASPECT_RATIO);
                        }
                    } else if (rawX < 0 - (this.d.getResources().getDisplayMetrics().widthPixels / 5) && this.e.getTranslationX() == CropImageView.DEFAULT_ASPECT_RATIO) {
                        this.h.setIntValues(new int[]{0, -((int) this.k)});
                        this.h.start();
                    }
                    this.i = false;
                }
                this.f = CropImageView.DEFAULT_ASPECT_RATIO;
                this.g = CropImageView.DEFAULT_ASPECT_RATIO;
                obtain.clear();
                obtain.recycle();
                break;
            case 2:
                float rawX2 = motionEvent.getRawX() - this.f;
                xVelocity = motionEvent.getRawY() - this.g;
                if (!this.i && Math.abs(rawX2) > this.j && Math.abs(rawX2) > Math.abs(xVelocity)) {
                    this.i = true;
                    break;
                }
        }
        return true;
    }
}
