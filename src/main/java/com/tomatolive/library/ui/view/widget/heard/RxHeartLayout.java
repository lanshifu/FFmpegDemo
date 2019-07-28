package com.tomatolive.library.ui.view.widget.heard;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.heard.animation.RxAbstractPathAnimator;
import com.tomatolive.library.ui.view.widget.heard.animation.RxAbstractPathAnimator.Config;
import com.tomatolive.library.ui.view.widget.heard.animation.RxPathAnimator;

public class RxHeartLayout extends RelativeLayout {
    private RxAbstractPathAnimator mAnimator;

    public RxHeartLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public RxHeartLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public RxHeartLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    private void init(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RxHeartLayout, i, 0);
        this.mAnimator = new RxPathAnimator(Config.fromTypeArray(obtainStyledAttributes));
        obtainStyledAttributes.recycle();
    }

    public RxAbstractPathAnimator getAnimator() {
        return this.mAnimator;
    }

    public void setAnimator(RxAbstractPathAnimator rxAbstractPathAnimator) {
        clearAnimation();
        this.mAnimator = rxAbstractPathAnimator;
    }

    public void clearAnimation() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).clearAnimation();
        }
        removeAllViews();
    }

    public void addHeart(int i) {
        RxHeartView rxHeartView = new RxHeartView(getContext());
        rxHeartView.setColor(i);
        this.mAnimator.start(rxHeartView, this);
    }

    public void addHeart(int i, int i2, int i3) {
        RxHeartView rxHeartView = new RxHeartView(getContext());
        rxHeartView.setColorAndDrawables(i, i2, i3);
        this.mAnimator.start(rxHeartView, this);
    }
}
