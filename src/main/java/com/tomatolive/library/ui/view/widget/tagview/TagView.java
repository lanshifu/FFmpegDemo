package com.tomatolive.library.ui.view.widget.tagview;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;

public class TagView extends View {
    private float bdDistance;
    private float fontH;
    private float fontW;
    private boolean isExecLongClick;
    private boolean isMoved;
    private boolean isUp;
    private boolean isViewClickable;
    private boolean isViewSelectable;
    private boolean isViewSelected;
    private String mAbstractText;
    private int mBackgroundColor;
    private Bitmap mBitmapImage;
    private int mBorderColor;
    private float mBorderRadius;
    private float mBorderWidth;
    private float mCrossAreaPadding;
    private float mCrossAreaWidth;
    private int mCrossColor;
    private float mCrossLineWidth;
    private boolean mEnableCross;
    private int mHorizontalPadding;
    private int mLastX;
    private int mLastY;
    private Runnable mLongClickHandle = new Runnable() {
        public void run() {
            if (!TagView.this.isMoved && !TagView.this.isUp && ((TagContainerLayout) TagView.this.getParent()).getTagViewState() == 0) {
                TagView.this.isExecLongClick = true;
                TagView.this.mOnTagClickListener.onTagLongClick(((Integer) TagView.this.getTag()).intValue(), TagView.this.getText());
            }
        }
    };
    private int mLongPressTime = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    private int mMoveSlop = 5;
    private OnTagClickListener mOnTagClickListener;
    private String mOriginText;
    private Paint mPaint;
    private Path mPath;
    private RectF mRectF;
    private int mRippleAlpha;
    private int mRippleColor;
    private int mRippleDuration = 1000;
    private Paint mRipplePaint;
    private float mRippleRadius;
    private ValueAnimator mRippleValueAnimator;
    private int mSelectedBackgroundColor;
    private int mSlopThreshold = 4;
    private int mTagMaxLength;
    private boolean mTagSupportLettersRTL = false;
    private int mTextColor;
    private int mTextDirection = 3;
    private float mTextSize;
    private float mTouchX;
    private float mTouchY;
    private Typeface mTypeface;
    private int mVerticalPadding;
    private boolean unSupportedClipPath = false;

    public interface OnTagClickListener {
        void onSelectedTagDrag(int i, String str);

        void onTagClick(int i, String str);

        void onTagCrossClick(int i);

        void onTagLongClick(int i, String str);
    }

    public TagView(Context context, String str) {
        super(context);
        init(context, str);
    }

    public TagView(Context context, String str, int i) {
        super(context);
        init(context, str);
        this.mBitmapImage = BitmapFactory.decodeResource(getResources(), i);
    }

    private void init(Context context, String str) {
        this.mPaint = new Paint(1);
        this.mRipplePaint = new Paint(1);
        this.mRipplePaint.setStyle(Style.FILL);
        this.mRectF = new RectF();
        this.mPath = new Path();
        if (str == null) {
            str = "";
        }
        this.mOriginText = str;
        this.mMoveSlop = (int) Utils.dp2px(context, (float) this.mMoveSlop);
        this.mSlopThreshold = (int) Utils.dp2px(context, (float) this.mSlopThreshold);
    }

    private void onDealText() {
        int i = 0;
        if (TextUtils.isEmpty(this.mOriginText)) {
            this.mAbstractText = "";
        } else {
            String str;
            if (this.mOriginText.length() <= this.mTagMaxLength) {
                str = this.mOriginText;
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.mOriginText.substring(0, this.mTagMaxLength - 3));
                stringBuilder.append("...");
                str = stringBuilder.toString();
            }
            this.mAbstractText = str;
        }
        this.mPaint.setTypeface(this.mTypeface);
        this.mPaint.setTextSize(this.mTextSize);
        FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        this.fontH = fontMetrics.descent - fontMetrics.ascent;
        if (this.mTextDirection == 4) {
            this.fontW = CropImageView.DEFAULT_ASPECT_RATIO;
            char[] toCharArray = this.mAbstractText.toCharArray();
            int length = toCharArray.length;
            while (i < length) {
                this.fontW += this.mPaint.measureText(String.valueOf(toCharArray[i]));
                i++;
            }
            return;
        }
        this.fontW = this.mPaint.measureText(this.mAbstractText);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        i = (this.mVerticalPadding * 2) + ((int) this.fontH);
        int i3 = 0;
        i2 = ((this.mHorizontalPadding * 2) + ((int) this.fontW)) + (isEnableCross() ? i : 0);
        if (isEnableImage()) {
            i3 = i;
        }
        i2 += i3;
        this.mCrossAreaWidth = Math.min(Math.max(this.mCrossAreaWidth, (float) i), (float) i2);
        setMeasuredDimension(i2, i);
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mRectF.set(this.mBorderWidth, this.mBorderWidth, ((float) i) - this.mBorderWidth, ((float) i2) - this.mBorderWidth);
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setColor(getIsViewSelected() ? this.mSelectedBackgroundColor : this.mBackgroundColor);
        canvas.drawRoundRect(this.mRectF, this.mBorderRadius, this.mBorderRadius, this.mPaint);
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeWidth(this.mBorderWidth);
        this.mPaint.setColor(this.mBorderColor);
        canvas.drawRoundRect(this.mRectF, this.mBorderRadius, this.mBorderRadius, this.mPaint);
        drawRipple(canvas);
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setColor(this.mTextColor);
        int i = 0;
        if (this.mTextDirection != 4) {
            String str = this.mAbstractText;
            float width = ((float) ((isEnableCross() ? getWidth() - getHeight() : getWidth()) / 2)) - (this.fontW / 2.0f);
            if (isEnableImage()) {
                i = getHeight() / 2;
            }
            canvas.drawText(str, width + ((float) i), (((float) (getHeight() / 2)) + (this.fontH / 2.0f)) - this.bdDistance, this.mPaint);
        } else if (this.mTagSupportLettersRTL) {
            float width2 = ((float) ((isEnableCross() ? getWidth() + getHeight() : getWidth()) / 2)) + (this.fontW / 2.0f);
            char[] toCharArray = this.mAbstractText.toCharArray();
            int length = toCharArray.length;
            while (i < length) {
                String valueOf = String.valueOf(toCharArray[i]);
                width2 -= this.mPaint.measureText(valueOf);
                canvas.drawText(valueOf, width2, (((float) (getHeight() / 2)) + (this.fontH / 2.0f)) - this.bdDistance, this.mPaint);
                i++;
            }
        } else {
            canvas.drawText(this.mAbstractText, ((isEnableCross() ? ((float) getWidth()) + this.fontW : (float) getWidth()) / 2.0f) - (this.fontW / 2.0f), (((float) (getHeight() / 2)) + (this.fontH / 2.0f)) - this.bdDistance, this.mPaint);
        }
        drawCross(canvas);
        drawImage(canvas);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.isViewClickable) {
            int y = (int) motionEvent.getY();
            int x = (int) motionEvent.getX();
            int action = motionEvent.getAction();
            if (action == 0) {
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                this.mLastY = y;
                this.mLastX = x;
            } else if (action == 2 && !this.isViewSelected && (Math.abs(this.mLastY - y) > this.mSlopThreshold || Math.abs(this.mLastX - x) > this.mSlopThreshold)) {
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                this.isMoved = true;
                return false;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mRippleRadius = CropImageView.DEFAULT_ASPECT_RATIO;
            this.mTouchX = motionEvent.getX();
            this.mTouchY = motionEvent.getY();
            splashRipple();
        }
        if (isEnableCross() && isClickCrossArea(motionEvent) && this.mOnTagClickListener != null) {
            if (action == 1) {
                this.mOnTagClickListener.onTagCrossClick(((Integer) getTag()).intValue());
            }
            return true;
        } else if (!this.isViewClickable || this.mOnTagClickListener == null) {
            return super.onTouchEvent(motionEvent);
        } else {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            switch (action) {
                case 0:
                    this.mLastY = y;
                    this.mLastX = x;
                    this.isMoved = false;
                    this.isUp = false;
                    this.isExecLongClick = false;
                    postDelayed(this.mLongClickHandle, (long) this.mLongPressTime);
                    break;
                case 1:
                    this.isUp = true;
                    if (!(this.isExecLongClick || this.isMoved)) {
                        this.mOnTagClickListener.onTagClick(((Integer) getTag()).intValue(), getText());
                        break;
                    }
                case 2:
                    if (!this.isMoved && (Math.abs(this.mLastX - x) > this.mMoveSlop || Math.abs(this.mLastY - y) > this.mMoveSlop)) {
                        this.isMoved = true;
                        if (this.isViewSelected) {
                            this.mOnTagClickListener.onSelectedTagDrag(((Integer) getTag()).intValue(), getText());
                            break;
                        }
                    }
                    break;
            }
            return true;
        }
    }

    private boolean isClickCrossArea(MotionEvent motionEvent) {
        boolean z = false;
        if (this.mTextDirection == 4) {
            if (motionEvent.getX() <= this.mCrossAreaWidth) {
                z = true;
            }
            return z;
        }
        if (motionEvent.getX() >= ((float) getWidth()) - this.mCrossAreaWidth) {
            z = true;
        }
        return z;
    }

    private void drawImage(Canvas canvas) {
        if (isEnableImage()) {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(this.mBitmapImage, Math.round(((float) getHeight()) - this.mBorderWidth), Math.round(((float) getHeight()) - this.mBorderWidth), false);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(createScaledBitmap, TileMode.CLAMP, TileMode.CLAMP));
            RectF rectF = new RectF(this.mBorderWidth, this.mBorderWidth, ((float) getHeight()) - this.mBorderWidth, ((float) getHeight()) - this.mBorderWidth);
            canvas.drawRoundRect(rectF, rectF.height() / 2.0f, rectF.height() / 2.0f, paint);
        }
    }

    private void drawCross(Canvas canvas) {
        if (isEnableCross()) {
            int i;
            int i2;
            this.mCrossAreaPadding = this.mCrossAreaPadding > ((float) (getHeight() / 2)) ? (float) (getHeight() / 2) : this.mCrossAreaPadding;
            if (this.mTextDirection == 4) {
                i = (int) this.mCrossAreaPadding;
            } else {
                i = (int) (((float) (getWidth() - getHeight())) + this.mCrossAreaPadding);
            }
            int i3 = this.mTextDirection;
            i3 = (int) this.mCrossAreaPadding;
            if (this.mTextDirection == 4) {
                i2 = (int) this.mCrossAreaPadding;
            } else {
                i2 = (int) (((float) (getWidth() - getHeight())) + this.mCrossAreaPadding);
            }
            int i4 = this.mTextDirection;
            i4 = (int) (((float) getHeight()) - this.mCrossAreaPadding);
            int height = (int) (((float) (this.mTextDirection == 4 ? getHeight() : getWidth())) - this.mCrossAreaPadding);
            int i5 = this.mTextDirection;
            i5 = (int) this.mCrossAreaPadding;
            int height2 = (int) (((float) (this.mTextDirection == 4 ? getHeight() : getWidth())) - this.mCrossAreaPadding);
            int i6 = this.mTextDirection;
            int height3 = (int) (((float) getHeight()) - this.mCrossAreaPadding);
            this.mPaint.setStyle(Style.STROKE);
            this.mPaint.setColor(this.mCrossColor);
            this.mPaint.setStrokeWidth(this.mCrossLineWidth);
            canvas.drawLine((float) i, (float) i3, (float) height2, (float) height3, this.mPaint);
            canvas.drawLine((float) i2, (float) i4, (float) height, (float) i5, this.mPaint);
        }
    }

    @TargetApi(11)
    private void drawRipple(Canvas canvas) {
        if (this.isViewClickable && VERSION.SDK_INT >= 11 && canvas != null && !this.unSupportedClipPath) {
            if (VERSION.SDK_INT < 18) {
                setLayerType(1, null);
            }
            try {
                canvas.save();
                this.mPath.reset();
                canvas.clipPath(this.mPath);
                this.mPath.addRoundRect(this.mRectF, this.mBorderRadius, this.mBorderRadius, Direction.CCW);
                if (VERSION.SDK_INT >= 26) {
                    canvas.clipPath(this.mPath);
                } else {
                    canvas.clipPath(this.mPath, Op.REPLACE);
                }
                canvas.drawCircle(this.mTouchX, this.mTouchY, this.mRippleRadius, this.mRipplePaint);
                canvas.restore();
            } catch (UnsupportedOperationException unused) {
                this.unSupportedClipPath = true;
            }
        }
    }

    @TargetApi(11)
    private void splashRipple() {
        if (VERSION.SDK_INT >= 11 && this.mTouchX > CropImageView.DEFAULT_ASPECT_RATIO && this.mTouchY > CropImageView.DEFAULT_ASPECT_RATIO) {
            this.mRipplePaint.setColor(this.mRippleColor);
            this.mRipplePaint.setAlpha(this.mRippleAlpha);
            final float max = Math.max(Math.max(Math.max(this.mTouchX, this.mTouchY), Math.abs(((float) getMeasuredWidth()) - this.mTouchX)), Math.abs(((float) getMeasuredHeight()) - this.mTouchY));
            this.mRippleValueAnimator = ValueAnimator.ofFloat(new float[]{CropImageView.DEFAULT_ASPECT_RATIO, max}).setDuration((long) this.mRippleDuration);
            this.mRippleValueAnimator.addUpdateListener(new AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    TagView tagView = TagView.this;
                    if (floatValue >= max) {
                        floatValue = CropImageView.DEFAULT_ASPECT_RATIO;
                    }
                    tagView.mRippleRadius = floatValue;
                    TagView.this.postInvalidate();
                }
            });
            this.mRippleValueAnimator.start();
        }
    }

    public String getText() {
        return this.mOriginText;
    }

    public boolean getIsViewClickable() {
        return this.isViewClickable;
    }

    public boolean getIsViewSelected() {
        return this.isViewSelected;
    }

    public void setTagMaxLength(int i) {
        this.mTagMaxLength = i;
        onDealText();
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.mOnTagClickListener = onTagClickListener;
    }

    public int getTagBackgroundColor() {
        return this.mBackgroundColor;
    }

    public int getTagSelectedBackgroundColor() {
        return this.mSelectedBackgroundColor;
    }

    public void setTagBackgroundColor(int i) {
        this.mBackgroundColor = i;
    }

    public void setTagSelectedBackgroundColor(int i) {
        this.mSelectedBackgroundColor = i;
    }

    public void setTagBorderColor(int i) {
        this.mBorderColor = i;
    }

    public void setTagTextColor(int i) {
        this.mTextColor = i;
    }

    public void setBorderWidth(float f) {
        this.mBorderWidth = f;
    }

    public void setBorderRadius(float f) {
        this.mBorderRadius = f;
    }

    public void setTextSize(float f) {
        this.mTextSize = f;
        onDealText();
    }

    public void setHorizontalPadding(int i) {
        this.mHorizontalPadding = i;
    }

    public void setVerticalPadding(int i) {
        this.mVerticalPadding = i;
    }

    public void setIsViewClickable(boolean z) {
        this.isViewClickable = z;
    }

    public void setImage(Bitmap bitmap) {
        this.mBitmapImage = bitmap;
        invalidate();
    }

    public void setIsViewSelectable(boolean z) {
        this.isViewSelectable = z;
    }

    public void selectView() {
        if (this.isViewSelectable && !getIsViewSelected()) {
            this.isViewSelected = true;
            postInvalidate();
        }
    }

    public void deselectView() {
        if (this.isViewSelectable && getIsViewSelected()) {
            this.isViewSelected = false;
            postInvalidate();
        }
    }

    public int getTextDirection() {
        return this.mTextDirection;
    }

    public void setTextDirection(int i) {
        this.mTextDirection = i;
    }

    public void setTypeface(Typeface typeface) {
        this.mTypeface = typeface;
        onDealText();
    }

    public void setRippleAlpha(int i) {
        this.mRippleAlpha = i;
    }

    public void setRippleColor(int i) {
        this.mRippleColor = i;
    }

    public void setRippleDuration(int i) {
        this.mRippleDuration = i;
    }

    public void setBdDistance(float f) {
        this.bdDistance = f;
    }

    public boolean isEnableImage() {
        return (this.mBitmapImage == null || this.mTextDirection == 4) ? false : true;
    }

    public boolean isEnableCross() {
        return this.mEnableCross;
    }

    public void setEnableCross(boolean z) {
        this.mEnableCross = z;
    }

    public float getCrossAreaWidth() {
        return this.mCrossAreaWidth;
    }

    public void setCrossAreaWidth(float f) {
        this.mCrossAreaWidth = f;
    }

    public float getCrossLineWidth() {
        return this.mCrossLineWidth;
    }

    public void setCrossLineWidth(float f) {
        this.mCrossLineWidth = f;
    }

    public float getCrossAreaPadding() {
        return this.mCrossAreaPadding;
    }

    public void setCrossAreaPadding(float f) {
        this.mCrossAreaPadding = f;
    }

    public int getCrossColor() {
        return this.mCrossColor;
    }

    public void setCrossColor(int i) {
        this.mCrossColor = i;
    }

    public boolean isTagSupportLettersRTL() {
        return this.mTagSupportLettersRTL;
    }

    public void setTagSupportLettersRTL(boolean z) {
        this.mTagSupportLettersRTL = z;
    }
}
