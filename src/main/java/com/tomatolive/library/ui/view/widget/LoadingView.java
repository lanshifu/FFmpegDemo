package com.tomatolive.library.ui.view.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.tomatolive.library.R;

public class LoadingView extends AppCompatImageView {
    private AnimationDrawable frameAnimation;

    public LoadingView(Context context) {
        super(context);
        initView(context);
    }

    public LoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fq_live_loading_animation));
        this.frameAnimation = (AnimationDrawable) getDrawable();
    }

    public void showLoading() {
        if (this.frameAnimation != null) {
            post(new Runnable() {
                public void run() {
                    LoadingView.this.frameAnimation.start();
                }
            });
        }
    }

    public void stopLoading() {
        if (this.frameAnimation != null && this.frameAnimation.isRunning()) {
            this.frameAnimation.stop();
        }
    }

    public void release() {
        if (this.frameAnimation != null && this.frameAnimation.isRunning()) {
            this.frameAnimation.stop();
        }
        this.frameAnimation = null;
        setImageDrawable(null);
    }
}
