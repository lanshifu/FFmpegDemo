package com.tomatolive.library.ui.view.widget.heard.animation;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.LinearInterpolator;
import com.tomatolive.library.ui.view.widget.heard.animation.RxAbstractPathAnimator.Config;
import java.util.concurrent.atomic.AtomicInteger;

public class RxPathAnimator extends RxAbstractPathAnimator {
    private final AtomicInteger mCounter = new AtomicInteger(0);
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public RxPathAnimator(Config config) {
        super(config);
    }

    public void start(final View view, final ViewGroup viewGroup) {
        viewGroup.addView(view, new LayoutParams(this.mConfig.heartWidth, this.mConfig.heartHeight));
        FloatAnimation floatAnimation = new FloatAnimation(createPath(this.mCounter, viewGroup, 2), randomRotation(), viewGroup, view);
        floatAnimation.setDuration((long) this.mConfig.animDuration);
        floatAnimation.setInterpolator(new LinearInterpolator());
        floatAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                RxPathAnimator.this.mHandler.post(new Runnable() {
                    public void run() {
                        viewGroup.removeView(view);
                    }
                });
                RxPathAnimator.this.mCounter.decrementAndGet();
            }

            public void onAnimationStart(Animation animation) {
                RxPathAnimator.this.mCounter.incrementAndGet();
            }
        });
        floatAnimation.setInterpolator(new LinearInterpolator());
        view.startAnimation(floatAnimation);
    }
}
