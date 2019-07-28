package com.tomatolive.library.ui.view.widget;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;
import com.blankj.utilcode.util.b;
import com.tomatolive.library.R;
import com.yalantis.ucrop.view.CropImageView;

public class ScrollTextView extends TextSwitcher implements ViewFactory {
    private Context context;
    private ScrollAnimation inAnimation;
    private ScrollAnimation outAnimation;
    private CharSequence temp;
    private float textSize;

    class ScrollAnimation extends Animation {
        private Camera mCamera;
        private float mCenterX;
        private float mCenterY;
        private final float mFromDegrees;
        private final float mToDegrees;
        private final boolean mTurnIn;
        private final boolean mTurnUp;

        private ScrollAnimation(float f, float f2, boolean z, boolean z2) {
            this.mFromDegrees = f;
            this.mToDegrees = f2;
            this.mTurnIn = z;
            this.mTurnUp = z2;
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.mCamera = new Camera();
            this.mCenterY = ((float) ScrollTextView.this.getHeight()) / 2.0f;
            this.mCenterX = ((float) ScrollTextView.this.getWidth()) / 2.0f;
        }

        /* Access modifiers changed, original: protected */
        public void applyTransformation(float f, Transformation transformation) {
            float f2 = this.mFromDegrees;
            f2 = this.mToDegrees;
            f2 = this.mCenterX;
            float f3 = this.mCenterY;
            Camera camera = this.mCamera;
            int i = this.mTurnUp ? 1 : -1;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.mTurnIn) {
                camera.translate(CropImageView.DEFAULT_ASPECT_RATIO, (((float) i) * this.mCenterY) * (f - 1.0f), CropImageView.DEFAULT_ASPECT_RATIO);
            } else {
                camera.translate(CropImageView.DEFAULT_ASPECT_RATIO, (((float) i) * this.mCenterY) * f, CropImageView.DEFAULT_ASPECT_RATIO);
            }
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f2, -f3);
            matrix.postTranslate(f2, f3);
        }
    }

    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textSize = 12.0f;
        this.context = context;
        init();
    }

    private void init() {
        setFactory(this);
        this.inAnimation = createAnim(-90.0f, CropImageView.DEFAULT_ASPECT_RATIO, true);
        this.outAnimation = createAnim(CropImageView.DEFAULT_ASPECT_RATIO, 90.0f, false);
        setInAnimation(this.inAnimation);
        setOutAnimation(this.outAnimation);
    }

    private ScrollAnimation createAnim(float f, float f2, boolean z) {
        ScrollAnimation scrollAnimation = new ScrollAnimation(f, f2, z, true);
        scrollAnimation.setDuration(250);
        scrollAnimation.setFillAfter(false);
        scrollAnimation.setInterpolator(new AccelerateInterpolator());
        return scrollAnimation;
    }

    public void updateSize(float f) {
        removeAllViews();
        this.textSize = f;
        setFactory(this);
        if (!TextUtils.isEmpty(this.temp)) {
            setText(this.temp);
        }
    }

    public void setText(CharSequence charSequence) {
        this.temp = charSequence;
        super.setText(charSequence);
    }

    public View makeView() {
        TextView textView = new TextView(this.context);
        textView.setGravity(8388611);
        textView.setPadding(b.a(5.0f), b.a(5.0f), b.a(5.0f), b.a(5.0f));
        textView.setTextSize(2, this.textSize);
        textView.setGravity(16);
        textView.setSingleLine(true);
        textView.setTextColor(-1);
        textView.setEllipsize(TruncateAt.END);
        textView.setShadowLayer(4.0f, CropImageView.DEFAULT_ASPECT_RATIO, 5.0f, ContextCompat.getColor(this.context, R.color.fq_text_shadow_color));
        return textView;
    }

    public void next() {
        if (getInAnimation() != this.inAnimation) {
            setInAnimation(this.inAnimation);
        }
        if (getOutAnimation() != this.outAnimation) {
            setOutAnimation(this.outAnimation);
        }
    }
}
