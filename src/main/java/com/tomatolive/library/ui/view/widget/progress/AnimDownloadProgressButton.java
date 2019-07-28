package com.tomatolive.library.ui.view.widget.progress;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View.BaseSavedState;
import com.tomatolive.library.R;
import com.yalantis.ucrop.view.CropImageView;

public class AnimDownloadProgressButton extends AppCompatTextView {
    public static final int DOWNLOADING = 1;
    public static final int INSTALLING = 2;
    public static final int NORMAL = 0;
    private LoadingEndListener loadingEndListener;
    private float mAboveTextSize;
    private RectF mBackgroundBounds;
    private int[] mBackgroundColor;
    private Paint mBackgroundPaint;
    private int mBackgroundSecondColor;
    private float mButtonRadius;
    private Context mContext;
    private CharSequence mCurrentText;
    private ButtonController mCustomerController;
    private ButtonController mDefaultController;
    private Paint mDot1Paint;
    private float mDot1transX;
    private Paint mDot2Paint;
    private float mDot2transX;
    private AnimatorSet mDotAnimationSet;
    private LinearGradient mFillBgGradient;
    private int mMaxProgress;
    private int mMinProgress;
    private int[] mOriginBackgroundColor;
    private float mProgress;
    private ValueAnimator mProgressAnimation;
    private LinearGradient mProgressBgGradient;
    private float mProgressPercent;
    private LinearGradient mProgressTextGradient;
    private int mState;
    private int mTextColor;
    private int mTextCoverColor;
    private volatile Paint mTextPaint;
    private float mToProgress;

    public interface LoadingEndListener {
        void onLoadingEnd();
    }

    public static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private String currentText;
        private int progress;
        private int state;

        /* synthetic */ SavedState(Parcel parcel, AnonymousClass1 anonymousClass1) {
            this(parcel);
        }

        public SavedState(Parcelable parcelable, int i, int i2, String str) {
            super(parcelable);
            this.progress = i;
            this.state = i2;
            this.currentText = str;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.progress = parcel.readInt();
            this.state = parcel.readInt();
            this.currentText = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.progress);
            parcel.writeInt(this.state);
            parcel.writeString(this.currentText);
        }
    }

    public AnimDownloadProgressButton(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public AnimDownloadProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAboveTextSize = 50.0f;
        this.mProgress = -1.0f;
        if (isInEditMode()) {
            initController();
            return;
        }
        initController();
        initAttrs(context, attributeSet);
        init();
        setupAnimations();
    }

    private void initController() {
        this.mDefaultController = new DefaultButtonController();
    }

    /* Access modifiers changed, original: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        ButtonController switchController = switchController();
        if (switchController.enablePress()) {
            if (this.mOriginBackgroundColor == null) {
                this.mOriginBackgroundColor = new int[2];
                this.mOriginBackgroundColor[0] = this.mBackgroundColor[0];
                this.mOriginBackgroundColor[1] = this.mBackgroundColor[1];
            }
            if (isPressed()) {
                int pressedColor = switchController.getPressedColor(this.mBackgroundColor[0]);
                int pressedColor2 = switchController.getPressedColor(this.mBackgroundColor[1]);
                if (switchController.enableGradient()) {
                    initGradientColor(pressedColor, pressedColor2);
                } else {
                    initGradientColor(pressedColor, pressedColor);
                }
            } else if (switchController.enableGradient()) {
                initGradientColor(this.mOriginBackgroundColor[0], this.mOriginBackgroundColor[1]);
            } else {
                initGradientColor(this.mOriginBackgroundColor[0], this.mOriginBackgroundColor[0]);
            }
            invalidate();
        }
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AnimDownloadProgressButton);
        int color = obtainStyledAttributes.getColor(R.styleable.AnimDownloadProgressButton_progressbtn_background_color, Color.parseColor("#6699ff"));
        initGradientColor(color, color);
        this.mBackgroundSecondColor = obtainStyledAttributes.getColor(R.styleable.AnimDownloadProgressButton_progressbtn_background_second_color, -3355444);
        this.mButtonRadius = obtainStyledAttributes.getFloat(R.styleable.AnimDownloadProgressButton_progressbtn_radius, (float) (getMeasuredHeight() / 2));
        this.mAboveTextSize = obtainStyledAttributes.getFloat(R.styleable.AnimDownloadProgressButton_progressbtn_text_size, 50.0f);
        this.mTextColor = obtainStyledAttributes.getColor(R.styleable.AnimDownloadProgressButton_progressbtn_text_color, color);
        this.mTextCoverColor = obtainStyledAttributes.getColor(R.styleable.AnimDownloadProgressButton_progressbtn_text_covercolor, -1);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.AnimDownloadProgressButton_progressbtn_enable_gradient, false);
        ((DefaultButtonController) this.mDefaultController).setEnableGradient(z).setEnablePress(obtainStyledAttributes.getBoolean(R.styleable.AnimDownloadProgressButton_progressbtn_enable_press, false));
        if (z) {
            initGradientColor(this.mDefaultController.getLighterColor(this.mBackgroundColor[0]), this.mBackgroundColor[0]);
        }
        obtainStyledAttributes.recycle();
    }

    private void init() {
        this.mMaxProgress = 100;
        this.mMinProgress = 0;
        this.mProgress = CropImageView.DEFAULT_ASPECT_RATIO;
        this.mBackgroundPaint = new Paint();
        this.mBackgroundPaint.setAntiAlias(true);
        this.mBackgroundPaint.setStyle(Style.FILL);
        this.mTextPaint = new Paint();
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setTextSize(this.mAboveTextSize);
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, this.mTextPaint);
        }
        this.mDot1Paint = new Paint();
        this.mDot1Paint.setAntiAlias(true);
        this.mDot1Paint.setTextSize(this.mAboveTextSize);
        this.mDot2Paint = new Paint();
        this.mDot2Paint.setAntiAlias(true);
        this.mDot2Paint.setTextSize(this.mAboveTextSize);
        this.mState = 1;
        invalidate();
    }

    private int[] initGradientColor(int i, int i2) {
        this.mBackgroundColor = new int[2];
        this.mBackgroundColor[0] = i;
        this.mBackgroundColor[1] = i2;
        return this.mBackgroundColor;
    }

    private void setupAnimations() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 20.0f});
        ofFloat.setInterpolator(PathInterpolatorCompat.create(0.11f, CropImageView.DEFAULT_ASPECT_RATIO, 0.12f, 1.0f));
        ofFloat.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                AnimDownloadProgressButton.this.mDot1transX = floatValue;
                AnimDownloadProgressButton.this.mDot2transX = floatValue;
                AnimDownloadProgressButton.this.invalidate();
            }
        });
        ofFloat.setDuration(1243);
        ofFloat.setRepeatMode(1);
        ofFloat.setRepeatCount(-1);
        final ValueAnimator duration = ValueAnimator.ofInt(new int[]{0, 1243}).setDuration(1243);
        duration.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) duration.getAnimatedValue()).intValue();
                int access$200 = AnimDownloadProgressButton.this.calculateDot1AlphaByTime(intValue);
                intValue = AnimDownloadProgressButton.this.calculateDot2AlphaByTime(intValue);
                AnimDownloadProgressButton.this.mDot1Paint.setColor(AnimDownloadProgressButton.this.mTextCoverColor);
                AnimDownloadProgressButton.this.mDot2Paint.setColor(AnimDownloadProgressButton.this.mTextCoverColor);
                AnimDownloadProgressButton.this.mDot1Paint.setAlpha(access$200);
                AnimDownloadProgressButton.this.mDot2Paint.setAlpha(intValue);
            }
        });
        duration.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
                AnimDownloadProgressButton.this.mDot1Paint.setAlpha(0);
                AnimDownloadProgressButton.this.mDot2Paint.setAlpha(0);
            }
        });
        duration.setRepeatMode(1);
        duration.setRepeatCount(-1);
        this.mDotAnimationSet = new AnimatorSet();
        this.mDotAnimationSet.playTogether(new Animator[]{duration, ofFloat});
        this.mProgressAnimation = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 0.3f, 0.5f, 0.8f, 1.0f}).setDuration(3000);
        this.mProgressAnimation.addUpdateListener(new -$$Lambda$AnimDownloadProgressButton$i1CwGM1Mf95wYksKM2r0K6PK9wg(this));
    }

    public static /* synthetic */ void lambda$setupAnimations$0(AnimDownloadProgressButton animDownloadProgressButton, ValueAnimator valueAnimator) {
        animDownloadProgressButton.mProgress = ((Float) valueAnimator.getAnimatedValue()).floatValue() * 100.0f;
        if (animDownloadProgressButton.mProgress >= 99.0f) {
            animDownloadProgressButton.mProgressAnimation.cancel();
            if (animDownloadProgressButton.loadingEndListener != null) {
                animDownloadProgressButton.loadingEndListener.onLoadingEnd();
            }
        }
        animDownloadProgressButton.invalidate();
    }

    public void cancelAnimaiton() {
        if (this.mProgressAnimation != null && this.mProgressAnimation.isRunning()) {
            this.mProgressAnimation.cancel();
        }
    }

    public void setLoadingEndListener(LoadingEndListener loadingEndListener) {
        this.loadingEndListener = loadingEndListener;
    }

    private int calculateDot2AlphaByTime(int i) {
        if (i >= 0 && i <= 83) {
            double d = (double) i;
            Double.isNaN(d);
            return (int) (d * 3.072289156626506d);
        } else if (83 < i && i <= 1000) {
            return 255;
        } else {
            if (1000 < i && i <= 1083) {
                double d2 = (double) (i - 1083);
                Double.isNaN(d2);
                return (int) (d2 * -3.072289156626506d);
            } else if (1083 >= i || i > 1243) {
                return 255;
            } else {
                return 0;
            }
        }
    }

    private int calculateDot1AlphaByTime(int i) {
        if (i >= 0 && i <= 160) {
            return 0;
        }
        double d;
        if (160 < i && i <= 243) {
            d = (double) (i - 160);
            Double.isNaN(d);
            return (int) (d * 3.072289156626506d);
        } else if ((243 < i && i <= 1160) || 1160 >= i || i > 1243) {
            return 255;
        } else {
            d = (double) (i - 1243);
            Double.isNaN(d);
            return (int) (d * -3.072289156626506d);
        }
    }

    private ValueAnimator createDotAlphaAnimation(int i, Paint paint, int i2, int i3, int i4) {
        return new ValueAnimator();
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode()) {
            drawing(canvas);
        }
    }

    private void drawing(Canvas canvas) {
        drawBackground(canvas);
        drawTextAbove(canvas);
    }

    private void drawBackground(Canvas canvas) {
        Canvas canvas2 = canvas;
        this.mBackgroundBounds = new RectF();
        if (this.mButtonRadius == CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mButtonRadius = (float) (getMeasuredHeight() / 2);
        }
        this.mBackgroundBounds.left = 2.0f;
        this.mBackgroundBounds.top = 2.0f;
        this.mBackgroundBounds.right = (float) (getMeasuredWidth() - 2);
        this.mBackgroundBounds.bottom = (float) (getMeasuredHeight() - 2);
        ButtonController switchController = switchController();
        switch (this.mState) {
            case 0:
                if (switchController.enableGradient()) {
                    this.mFillBgGradient = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, (float) (getMeasuredHeight() / 2), (float) getMeasuredWidth(), (float) (getMeasuredHeight() / 2), this.mBackgroundColor, null, TileMode.CLAMP);
                    this.mBackgroundPaint.setShader(this.mFillBgGradient);
                } else {
                    if (this.mBackgroundPaint.getShader() != null) {
                        this.mBackgroundPaint.setShader(null);
                    }
                    this.mBackgroundPaint.setColor(this.mBackgroundColor[0]);
                }
                canvas2.drawRoundRect(this.mBackgroundBounds, this.mButtonRadius, this.mButtonRadius, this.mBackgroundPaint);
                return;
            case 1:
                if (switchController.enableGradient()) {
                    this.mProgressPercent = this.mProgress / (((float) this.mMaxProgress) + CropImageView.DEFAULT_ASPECT_RATIO);
                    float measuredWidth = (float) getMeasuredWidth();
                    LinearGradient linearGradient = r8;
                    LinearGradient linearGradient2 = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, measuredWidth, CropImageView.DEFAULT_ASPECT_RATIO, new int[]{this.mBackgroundColor[0], this.mBackgroundColor[1], this.mBackgroundSecondColor}, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, this.mProgressPercent, this.mProgressPercent + 0.001f}, TileMode.CLAMP);
                    this.mProgressBgGradient = linearGradient;
                    this.mBackgroundPaint.setShader(this.mProgressBgGradient);
                } else {
                    this.mProgressPercent = this.mProgress / (((float) this.mMaxProgress) + CropImageView.DEFAULT_ASPECT_RATIO);
                    this.mProgressBgGradient = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) getMeasuredWidth(), CropImageView.DEFAULT_ASPECT_RATIO, new int[]{this.mBackgroundColor[0], this.mBackgroundSecondColor}, new float[]{this.mProgressPercent, this.mProgressPercent + 0.001f}, TileMode.CLAMP);
                    this.mBackgroundPaint.setColor(this.mBackgroundColor[0]);
                    this.mBackgroundPaint.setShader(this.mProgressBgGradient);
                }
                canvas2.drawRoundRect(this.mBackgroundBounds, this.mButtonRadius, this.mButtonRadius, this.mBackgroundPaint);
                return;
            case 2:
                if (switchController.enableGradient()) {
                    this.mFillBgGradient = new LinearGradient(CropImageView.DEFAULT_ASPECT_RATIO, (float) (getMeasuredHeight() / 2), (float) getMeasuredWidth(), (float) (getMeasuredHeight() / 2), this.mBackgroundColor, null, TileMode.CLAMP);
                    this.mBackgroundPaint.setShader(this.mFillBgGradient);
                } else {
                    this.mBackgroundPaint.setShader(null);
                    this.mBackgroundPaint.setColor(this.mBackgroundColor[0]);
                }
                canvas2.drawRoundRect(this.mBackgroundBounds, this.mButtonRadius, this.mButtonRadius, this.mBackgroundPaint);
                return;
            default:
                return;
        }
    }

    private void drawTextAbove(Canvas canvas) {
        Canvas canvas2 = canvas;
        float height = ((float) (canvas.getHeight() / 2)) - ((this.mTextPaint.descent() / 2.0f) + (this.mTextPaint.ascent() / 2.0f));
        if (this.mCurrentText == null) {
            this.mCurrentText = "";
        }
        float measureText = this.mTextPaint.measureText(this.mCurrentText.toString());
        switch (this.mState) {
            case 0:
                this.mTextPaint.setShader(null);
                this.mTextPaint.setColor(this.mTextCoverColor);
                canvas2.drawText(this.mCurrentText.toString(), (((float) getMeasuredWidth()) - measureText) / 2.0f, height, this.mTextPaint);
                return;
            case 1:
                float measuredWidth = ((float) getMeasuredWidth()) * this.mProgressPercent;
                float f = measureText / 2.0f;
                float measuredWidth2 = ((float) (getMeasuredWidth() / 2)) - f;
                float measuredWidth3 = ((float) (getMeasuredWidth() / 2)) + f;
                f = ((f - ((float) (getMeasuredWidth() / 2))) + measuredWidth) / measureText;
                if (measuredWidth <= measuredWidth2) {
                    this.mTextPaint.setShader(null);
                    this.mTextPaint.setColor(this.mTextColor);
                } else if (measuredWidth2 >= measuredWidth || measuredWidth > measuredWidth3) {
                    this.mTextPaint.setShader(null);
                    this.mTextPaint.setColor(this.mTextCoverColor);
                } else {
                    this.mProgressTextGradient = new LinearGradient((((float) getMeasuredWidth()) - measureText) / 2.0f, CropImageView.DEFAULT_ASPECT_RATIO, (((float) getMeasuredWidth()) + measureText) / 2.0f, CropImageView.DEFAULT_ASPECT_RATIO, new int[]{this.mTextCoverColor, this.mTextColor}, new float[]{f, f + 0.001f}, TileMode.CLAMP);
                    this.mTextPaint.setColor(this.mTextColor);
                    this.mTextPaint.setShader(this.mProgressTextGradient);
                }
                canvas2.drawText(this.mCurrentText.toString(), (((float) getMeasuredWidth()) - measureText) / 2.0f, height, this.mTextPaint);
                return;
            case 2:
                this.mTextPaint.setColor(this.mTextCoverColor);
                canvas2.drawText(this.mCurrentText.toString(), (((float) getMeasuredWidth()) - measureText) / 2.0f, height, this.mTextPaint);
                canvas2.drawCircle((((((float) getMeasuredWidth()) + measureText) / 2.0f) + 4.0f) + this.mDot1transX, height, 4.0f, this.mDot1Paint);
                canvas2.drawCircle((((((float) getMeasuredWidth()) + measureText) / 2.0f) + 24.0f) + this.mDot2transX, height, 4.0f, this.mDot2Paint);
                return;
            default:
                return;
        }
    }

    private ButtonController switchController() {
        if (this.mCustomerController != null) {
            return this.mCustomerController;
        }
        return this.mDefaultController;
    }

    public int getState() {
        return this.mState;
    }

    public void setState(int i) {
        if (this.mState != i) {
            this.mState = i;
            invalidate();
            if (i == 2) {
                this.mDotAnimationSet.start();
            } else if (i == 0) {
                this.mDotAnimationSet.cancel();
            } else if (i == 1) {
                this.mDotAnimationSet.cancel();
            }
        }
    }

    public void setCurrentText(CharSequence charSequence) {
        this.mCurrentText = charSequence;
        invalidate();
    }

    @TargetApi(19)
    public void setProgressText(String str, float f) {
        if (f >= ((float) this.mMinProgress) && f <= ((float) this.mMaxProgress)) {
            this.mCurrentText = str;
            this.mToProgress = f;
            if (this.mProgressAnimation.isRunning()) {
                this.mProgressAnimation.start();
            } else {
                this.mProgressAnimation.start();
            }
        } else if (f < ((float) this.mMinProgress)) {
            this.mProgress = CropImageView.DEFAULT_ASPECT_RATIO;
        }
    }

    public float getProgress() {
        return this.mProgress;
    }

    public void setProgress(float f) {
        this.mProgress = f;
    }

    public void removeAllAnim() {
        this.mDotAnimationSet.cancel();
        this.mDotAnimationSet.removeAllListeners();
        this.mProgressAnimation.cancel();
        this.mProgressAnimation.removeAllListeners();
    }

    public void setProgressBtnBackgroundColor(int i) {
        initGradientColor(i, i);
    }

    public void setProgressBtnBackgroundSecondColor(int i) {
        this.mBackgroundSecondColor = i;
    }

    public float getButtonRadius() {
        return this.mButtonRadius;
    }

    public void setButtonRadius(float f) {
        this.mButtonRadius = f;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public void setTextColor(int i) {
        this.mTextColor = i;
    }

    public int getTextCoverColor() {
        return this.mTextCoverColor;
    }

    public void setTextCoverColor(int i) {
        this.mTextCoverColor = i;
    }

    public int getMinProgress() {
        return this.mMinProgress;
    }

    public void setMinProgress(int i) {
        this.mMinProgress = i;
    }

    public int getMaxProgress() {
        return this.mMaxProgress;
    }

    public void setMaxProgress(int i) {
        this.mMaxProgress = i;
    }

    public void enabelDefaultPress(boolean z) {
        if (this.mDefaultController != null) {
            ((DefaultButtonController) this.mDefaultController).setEnablePress(z);
        }
    }

    public void enabelDefaultGradient(boolean z) {
        if (this.mDefaultController != null) {
            ((DefaultButtonController) this.mDefaultController).setEnableGradient(z);
            initGradientColor(this.mDefaultController.getLighterColor(this.mBackgroundColor[0]), this.mBackgroundColor[0]);
        }
    }

    public void setTextSize(float f) {
        this.mAboveTextSize = f;
        this.mTextPaint.setTextSize(f);
    }

    public float getTextSize() {
        return this.mAboveTextSize;
    }

    public AnimDownloadProgressButton setCustomerController(ButtonController buttonController) {
        this.mCustomerController = buttonController;
        return this;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.mState = savedState.state;
            this.mProgress = (float) savedState.progress;
            this.mCurrentText = savedState.currentText;
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (this.mCurrentText != null) {
            return new SavedState(onSaveInstanceState, (int) this.mProgress, this.mState, this.mCurrentText.toString());
        }
        return super.onSaveInstanceState();
    }
}
