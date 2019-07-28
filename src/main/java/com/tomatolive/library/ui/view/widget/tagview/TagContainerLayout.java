package com.tomatolive.library.ui.view.widget.tagview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.tomatolive.library.R;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.tomatolive.library.ui.view.widget.tagview.ColorFactory.PURE_COLOR;
import com.tomatolive.library.ui.view.widget.tagview.TagView.OnTagClickListener;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TagContainerLayout extends ViewGroup {
    private static final float DEFAULT_INTERVAL = 5.0f;
    private static final int TAG_MIN_LENGTH = 3;
    private boolean isTagViewClickable;
    private boolean isTagViewSelectable;
    private int mBackgroundColor;
    private int mBorderColor;
    private float mBorderRadius;
    private float mBorderWidth;
    private int mChildHeight;
    private List<View> mChildViews;
    private List<int[]> mColorArrayList;
    private float mCrossAreaPadding;
    private float mCrossAreaWidth;
    private int mCrossColor;
    private float mCrossLineWidth;
    private int mDefaultImageDrawableID;
    private boolean mDragEnable;
    private boolean mEnableCross;
    private int mGravity;
    private int mHorizontalInterval;
    private int mMaxLines;
    private OnTagClickListener mOnTagClickListener;
    private Paint mPaint;
    private RectF mRectF;
    private int mRippleAlpha;
    private int mRippleColor;
    private int mRippleDuration;
    private int mSelectedTagBackgroundColor;
    private float mSensitivity;
    private int mTagBackgroundColor;
    private int mTagBackgroundResource;
    private float mTagBdDistance;
    private int mTagBorderColor;
    private float mTagBorderRadius;
    private float mTagBorderWidth;
    private int mTagHorizontalPadding;
    private int mTagMaxLength;
    private boolean mTagSupportLettersRTL;
    private int mTagTextColor;
    private int mTagTextDirection;
    private float mTagTextSize;
    private Typeface mTagTypeface;
    private int mTagVerticalPadding;
    private int mTagViewState;
    private List<String> mTags;
    private int mTheme;
    private int mVerticalInterval;
    private ViewDragHelper mViewDragHelper;
    private int[] mViewPos;

    private class DragHelperCallBack extends Callback {
        private DragHelperCallBack() {
        }

        public void onViewDragStateChanged(int i) {
            super.onViewDragStateChanged(i);
            TagContainerLayout.this.mTagViewState = i;
        }

        public boolean tryCaptureView(View view, int i) {
            TagContainerLayout.this.requestDisallowInterceptTouchEvent(true);
            return TagContainerLayout.this.mDragEnable;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            return Math.min(Math.max(i, TagContainerLayout.this.getPaddingLeft()), (TagContainerLayout.this.getWidth() - view.getWidth()) - TagContainerLayout.this.getPaddingRight());
        }

        public int clampViewPositionVertical(View view, int i, int i2) {
            return Math.min(Math.max(i, TagContainerLayout.this.getPaddingTop()), (TagContainerLayout.this.getHeight() - view.getHeight()) - TagContainerLayout.this.getPaddingBottom());
        }

        public int getViewHorizontalDragRange(View view) {
            return TagContainerLayout.this.getMeasuredWidth() - view.getMeasuredWidth();
        }

        public int getViewVerticalDragRange(View view) {
            return TagContainerLayout.this.getMeasuredHeight() - view.getMeasuredHeight();
        }

        public void onViewReleased(View view, float f, float f2) {
            super.onViewReleased(view, f, f2);
            TagContainerLayout.this.requestDisallowInterceptTouchEvent(false);
            int[] access$300 = TagContainerLayout.this.onGetNewPosition(view);
            TagContainerLayout.this.onChangeView(view, TagContainerLayout.this.onGetCoordinateReferPos(access$300[0], access$300[1]), ((Integer) view.getTag()).intValue());
            TagContainerLayout.this.mViewDragHelper.settleCapturedViewAt(access$300[0], access$300[1]);
            TagContainerLayout.this.invalidate();
        }
    }

    public TagContainerLayout(Context context) {
        this(context, null);
    }

    public TagContainerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TagContainerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBorderWidth = 0.5f;
        this.mBorderRadius = 10.0f;
        this.mSensitivity = 1.0f;
        this.mBorderColor = Color.parseColor("#22FF0000");
        this.mBackgroundColor = Color.parseColor("#11FF0000");
        this.mGravity = 3;
        this.mMaxLines = 0;
        this.mTagMaxLength = 23;
        this.mTagBorderWidth = 0.5f;
        this.mTagBorderRadius = 15.0f;
        this.mTagTextSize = 14.0f;
        this.mTagTextDirection = 3;
        this.mTagHorizontalPadding = 10;
        this.mTagVerticalPadding = 8;
        this.mTagBorderColor = Color.parseColor("#88F44336");
        this.mTagBackgroundColor = Color.parseColor("#33F44336");
        this.mSelectedTagBackgroundColor = Color.parseColor("#33FF7669");
        this.mTagTextColor = Color.parseColor("#FF666666");
        this.mTagTypeface = Typeface.DEFAULT;
        this.mDefaultImageDrawableID = -1;
        this.mTagViewState = 0;
        this.mTagBdDistance = 2.75f;
        this.mTagSupportLettersRTL = false;
        this.mTheme = 1;
        this.mRippleDuration = 1000;
        this.mRippleAlpha = 128;
        this.mEnableCross = false;
        this.mCrossAreaWidth = CropImageView.DEFAULT_ASPECT_RATIO;
        this.mCrossAreaPadding = 10.0f;
        this.mCrossColor = -16777216;
        this.mCrossLineWidth = 1.0f;
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AndroidTagView, i, 0);
        this.mVerticalInterval = (int) obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_vertical_interval, Utils.dp2px(context, DEFAULT_INTERVAL));
        this.mHorizontalInterval = (int) obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_horizontal_interval, Utils.dp2px(context, DEFAULT_INTERVAL));
        this.mBorderWidth = obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_container_border_width, Utils.dp2px(context, this.mBorderWidth));
        this.mBorderRadius = obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_container_border_radius, Utils.dp2px(context, this.mBorderRadius));
        this.mTagBdDistance = obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_tag_bd_distance, Utils.dp2px(context, this.mTagBdDistance));
        this.mBorderColor = obtainStyledAttributes.getColor(R.styleable.AndroidTagView_container_border_color, this.mBorderColor);
        this.mBackgroundColor = obtainStyledAttributes.getColor(R.styleable.AndroidTagView_container_background_color, this.mBackgroundColor);
        this.mDragEnable = obtainStyledAttributes.getBoolean(R.styleable.AndroidTagView_container_enable_drag, false);
        this.mSensitivity = obtainStyledAttributes.getFloat(R.styleable.AndroidTagView_container_drag_sensitivity, this.mSensitivity);
        this.mGravity = obtainStyledAttributes.getInt(R.styleable.AndroidTagView_container_gravity, this.mGravity);
        this.mMaxLines = obtainStyledAttributes.getInt(R.styleable.AndroidTagView_container_max_lines, this.mMaxLines);
        this.mTagMaxLength = obtainStyledAttributes.getInt(R.styleable.AndroidTagView_tag_max_length, this.mTagMaxLength);
        this.mTheme = obtainStyledAttributes.getInt(R.styleable.AndroidTagView_tag_theme, this.mTheme);
        this.mTagBorderWidth = obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_tag_border_width, Utils.dp2px(context, this.mTagBorderWidth));
        this.mTagBorderRadius = obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_tag_corner_radius, Utils.dp2px(context, this.mTagBorderRadius));
        this.mTagHorizontalPadding = (int) obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_tag_horizontal_padding, Utils.dp2px(context, (float) this.mTagHorizontalPadding));
        this.mTagVerticalPadding = (int) obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_tag_vertical_padding, Utils.dp2px(context, (float) this.mTagVerticalPadding));
        this.mTagTextSize = obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_tag_text_size, Utils.sp2px(context, this.mTagTextSize));
        this.mTagBorderColor = obtainStyledAttributes.getColor(R.styleable.AndroidTagView_tag_border_color, this.mTagBorderColor);
        this.mTagBackgroundColor = obtainStyledAttributes.getColor(R.styleable.AndroidTagView_tag_background_color, this.mTagBackgroundColor);
        this.mTagTextColor = obtainStyledAttributes.getColor(R.styleable.AndroidTagView_tag_text_color, this.mTagTextColor);
        this.mTagTextDirection = obtainStyledAttributes.getInt(R.styleable.AndroidTagView_tag_text_direction, this.mTagTextDirection);
        this.isTagViewClickable = obtainStyledAttributes.getBoolean(R.styleable.AndroidTagView_tag_clickable, false);
        this.isTagViewSelectable = obtainStyledAttributes.getBoolean(R.styleable.AndroidTagView_tag_selectable, false);
        this.mRippleColor = obtainStyledAttributes.getColor(R.styleable.AndroidTagView_tag_ripple_color, Color.parseColor("#EEEEEE"));
        this.mRippleAlpha = obtainStyledAttributes.getInteger(R.styleable.AndroidTagView_tag_ripple_alpha, this.mRippleAlpha);
        this.mRippleDuration = obtainStyledAttributes.getInteger(R.styleable.AndroidTagView_tag_ripple_duration, this.mRippleDuration);
        this.mEnableCross = obtainStyledAttributes.getBoolean(R.styleable.AndroidTagView_tag_enable_cross, this.mEnableCross);
        this.mCrossAreaWidth = obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_tag_cross_width, Utils.dp2px(context, this.mCrossAreaWidth));
        this.mCrossAreaPadding = obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_tag_cross_area_padding, Utils.dp2px(context, this.mCrossAreaPadding));
        this.mCrossColor = obtainStyledAttributes.getColor(R.styleable.AndroidTagView_tag_cross_color, this.mCrossColor);
        this.mCrossLineWidth = obtainStyledAttributes.getDimension(R.styleable.AndroidTagView_tag_cross_line_width, Utils.dp2px(context, this.mCrossLineWidth));
        this.mTagSupportLettersRTL = obtainStyledAttributes.getBoolean(R.styleable.AndroidTagView_tag_support_letters_rlt, this.mTagSupportLettersRTL);
        this.mTagBackgroundResource = obtainStyledAttributes.getResourceId(R.styleable.AndroidTagView_tag_background, this.mTagBackgroundResource);
        obtainStyledAttributes.recycle();
        this.mPaint = new Paint(1);
        this.mRectF = new RectF();
        this.mChildViews = new ArrayList();
        this.mViewDragHelper = ViewDragHelper.create(this, this.mSensitivity, new DragHelperCallBack());
        setWillNotDraw(false);
        setTagMaxLength(this.mTagMaxLength);
        setTagHorizontalPadding(this.mTagHorizontalPadding);
        setTagVerticalPadding(this.mTagVerticalPadding);
        if (isInEditMode()) {
            addTag("sample tag");
        }
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        measureChildren(i, i2);
        int childCount = getChildCount();
        if (childCount == 0) {
            i3 = 0;
        } else {
            i3 = getChildLines(childCount);
        }
        i = MeasureSpec.getSize(i);
        int size = MeasureSpec.getSize(i2);
        i2 = MeasureSpec.getMode(i2);
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else if (i2 == CheckView.UNCHECKED || i2 == 0) {
            setMeasuredDimension(i, ((((this.mVerticalInterval + this.mChildHeight) * i3) - this.mVerticalInterval) + getPaddingTop()) + getPaddingBottom());
        } else {
            setMeasuredDimension(i, size);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mRectF.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) i, (float) i2);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        if (childCount > 0) {
            i = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            i2 = getMeasuredWidth() - getPaddingRight();
            i3 = getPaddingTop();
            i4 = getPaddingLeft();
            this.mViewPos = new int[(childCount * 2)];
            int i5 = i4;
            int i6 = 0;
            i4 = i3;
            i3 = i2;
            for (i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int i7;
                    if (this.mGravity == 5) {
                        if (i3 - measuredWidth < getPaddingLeft()) {
                            i3 = getMeasuredWidth() - getPaddingRight();
                            i4 += this.mChildHeight + this.mVerticalInterval;
                        }
                        i7 = i2 * 2;
                        this.mViewPos[i7] = i3 - measuredWidth;
                        this.mViewPos[i7 + 1] = i4;
                        i3 -= measuredWidth + this.mHorizontalInterval;
                    } else if (this.mGravity == 17) {
                        int i8;
                        if ((i5 + measuredWidth) - getPaddingLeft() > i) {
                            i8 = i2 - 1;
                            i5 = ((getMeasuredWidth() - this.mViewPos[i8 * 2]) - getChildAt(i8).getMeasuredWidth()) - getPaddingRight();
                            while (i6 < i2) {
                                i8 = i6 * 2;
                                this.mViewPos[i8] = this.mViewPos[i8] + (i5 / 2);
                                i6++;
                            }
                            i5 = getPaddingLeft();
                            i4 += this.mChildHeight + this.mVerticalInterval;
                            i6 = i2;
                        }
                        i8 = i2 * 2;
                        this.mViewPos[i8] = i5;
                        this.mViewPos[i8 + 1] = i4;
                        i5 += measuredWidth + this.mHorizontalInterval;
                        if (i2 == childCount - 1) {
                            measuredWidth = ((getMeasuredWidth() - this.mViewPos[i8]) - childAt.getMeasuredWidth()) - getPaddingRight();
                            for (int i9 = i6; i9 < childCount; i9++) {
                                i8 = i9 * 2;
                                this.mViewPos[i8] = this.mViewPos[i8] + (measuredWidth / 2);
                            }
                        }
                    } else {
                        if ((i5 + measuredWidth) - getPaddingLeft() > i) {
                            i5 = getPaddingLeft();
                            i4 += this.mChildHeight + this.mVerticalInterval;
                        }
                        i7 = i2 * 2;
                        this.mViewPos[i7] = i5;
                        this.mViewPos[i7 + 1] = i4;
                        i5 += measuredWidth + this.mHorizontalInterval;
                    }
                }
            }
            for (int i10 = 0; i10 < this.mViewPos.length / 2; i10++) {
                View childAt2 = getChildAt(i10);
                i2 = i10 * 2;
                i4 = i2 + 1;
                childAt2.layout(this.mViewPos[i2], this.mViewPos[i4], this.mViewPos[i2] + childAt2.getMeasuredWidth(), this.mViewPos[i4] + this.mChildHeight);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setStyle(Style.FILL);
        this.mPaint.setColor(this.mBackgroundColor);
        canvas.drawRoundRect(this.mRectF, this.mBorderRadius, this.mBorderRadius, this.mPaint);
        this.mPaint.setStyle(Style.STROKE);
        this.mPaint.setStrokeWidth(this.mBorderWidth);
        this.mPaint.setColor(this.mBorderColor);
        canvas.drawRoundRect(this.mRectF, this.mBorderRadius, this.mBorderRadius, this.mPaint);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mViewDragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.mViewDragHelper.processTouchEvent(motionEvent);
        return true;
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.mViewDragHelper.continueSettling(true)) {
            requestLayout();
        }
    }

    private int getChildLines(int i) {
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int i2 = 1;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            View childAt = getChildAt(i4);
            int measuredWidth2 = childAt.getMeasuredWidth() + this.mHorizontalInterval;
            int measuredHeight = childAt.getMeasuredHeight();
            if (i4 != 0) {
                measuredHeight = Math.min(this.mChildHeight, measuredHeight);
            }
            this.mChildHeight = measuredHeight;
            i3 += measuredWidth2;
            if (i3 - this.mHorizontalInterval > measuredWidth) {
                i2++;
                i3 = measuredWidth2;
            }
        }
        return this.mMaxLines <= 0 ? i2 : this.mMaxLines;
    }

    private int[] onUpdateColorFactory() {
        if (this.mTheme == 0) {
            return ColorFactory.onRandomBuild();
        }
        if (this.mTheme == 2) {
            return ColorFactory.onPureBuild(PURE_COLOR.TEAL);
        }
        if (this.mTheme == 1) {
            return ColorFactory.onPureBuild(PURE_COLOR.CYAN);
        }
        return new int[]{this.mTagBackgroundColor, this.mTagBorderColor, this.mTagTextColor, this.mSelectedTagBackgroundColor};
    }

    private void onSetTag() {
        if (this.mTags != null) {
            removeAllTags();
            if (this.mTags.size() != 0) {
                for (int i = 0; i < this.mTags.size(); i++) {
                    onAddTag((String) this.mTags.get(i), this.mChildViews.size());
                }
                postInvalidate();
                return;
            }
            return;
        }
        throw new RuntimeException("NullPointer exception!");
    }

    private void onAddTag(String str, int i) {
        if (i < 0 || i > this.mChildViews.size()) {
            throw new RuntimeException("Illegal position!");
        }
        TagView tagView;
        if (this.mDefaultImageDrawableID != -1) {
            tagView = new TagView(getContext(), str, this.mDefaultImageDrawableID);
        } else {
            tagView = new TagView(getContext(), str);
        }
        initTagView(tagView, i);
        this.mChildViews.add(i, tagView);
        if (i < this.mChildViews.size()) {
            for (int i2 = i; i2 < this.mChildViews.size(); i2++) {
                ((View) this.mChildViews.get(i2)).setTag(Integer.valueOf(i2));
            }
        } else {
            tagView.setTag(Integer.valueOf(i));
        }
        addView(tagView, i);
    }

    private void initTagView(TagView tagView, int i) {
        int[] onUpdateColorFactory;
        if (this.mColorArrayList == null || this.mColorArrayList.size() <= 0) {
            onUpdateColorFactory = onUpdateColorFactory();
        } else if (this.mColorArrayList.size() != this.mTags.size() || ((int[]) this.mColorArrayList.get(i)).length < 4) {
            throw new RuntimeException("Illegal color list!");
        } else {
            onUpdateColorFactory = (int[]) this.mColorArrayList.get(i);
        }
        tagView.setTagBackgroundColor(onUpdateColorFactory[0]);
        tagView.setTagBorderColor(onUpdateColorFactory[1]);
        tagView.setTagTextColor(onUpdateColorFactory[2]);
        tagView.setTagSelectedBackgroundColor(onUpdateColorFactory[3]);
        tagView.setTagMaxLength(this.mTagMaxLength);
        tagView.setTextDirection(this.mTagTextDirection);
        tagView.setTypeface(this.mTagTypeface);
        tagView.setBorderWidth(this.mTagBorderWidth);
        tagView.setBorderRadius(this.mTagBorderRadius);
        tagView.setTextSize(this.mTagTextSize);
        tagView.setHorizontalPadding(this.mTagHorizontalPadding);
        tagView.setVerticalPadding(this.mTagVerticalPadding);
        tagView.setIsViewClickable(this.isTagViewClickable);
        tagView.setIsViewSelectable(this.isTagViewSelectable);
        tagView.setBdDistance(this.mTagBdDistance);
        tagView.setOnTagClickListener(this.mOnTagClickListener);
        tagView.setRippleAlpha(this.mRippleAlpha);
        tagView.setRippleColor(this.mRippleColor);
        tagView.setRippleDuration(this.mRippleDuration);
        tagView.setEnableCross(this.mEnableCross);
        tagView.setCrossAreaWidth(this.mCrossAreaWidth);
        tagView.setCrossAreaPadding(this.mCrossAreaPadding);
        tagView.setCrossColor(this.mCrossColor);
        tagView.setCrossLineWidth(this.mCrossLineWidth);
        tagView.setTagSupportLettersRTL(this.mTagSupportLettersRTL);
        tagView.setBackgroundResource(this.mTagBackgroundResource);
    }

    private void invalidateTags() {
        for (View view : this.mChildViews) {
            ((TagView) view).setOnTagClickListener(this.mOnTagClickListener);
        }
    }

    private void onRemoveTag(int i) {
        if (i < 0 || i >= this.mChildViews.size()) {
            throw new RuntimeException("Illegal position!");
        }
        this.mChildViews.remove(i);
        removeViewAt(i);
        while (i < this.mChildViews.size()) {
            ((View) this.mChildViews.get(i)).setTag(Integer.valueOf(i));
            i++;
        }
    }

    private void onRemoveConsecutiveTags(List<Integer> list) {
        int intValue = ((Integer) Collections.min(list)).intValue();
        for (Integer intValue2 : list) {
            int intValue3 = intValue2.intValue();
            if (intValue3 < 0 || intValue3 >= this.mChildViews.size()) {
                throw new RuntimeException("Illegal position!");
            }
            this.mChildViews.remove(intValue);
            removeViewAt(intValue);
        }
        while (intValue < this.mChildViews.size()) {
            ((View) this.mChildViews.get(intValue)).setTag(Integer.valueOf(intValue));
            intValue++;
        }
    }

    private int[] onGetNewPosition(View view) {
        int i;
        int left = view.getLeft();
        int top = view.getTop();
        int i2 = this.mViewPos[((Integer) view.getTag()).intValue() * 2];
        int i3 = this.mViewPos[(((Integer) view.getTag()).intValue() * 2) + 1];
        int abs = Math.abs(top - i3);
        int i4 = i3;
        for (i3 = 0; i3 < this.mViewPos.length / 2; i3++) {
            i = (i3 * 2) + 1;
            if (Math.abs(top - this.mViewPos[i]) < abs) {
                abs = this.mViewPos[i];
                i4 = abs;
                abs = Math.abs(top - this.mViewPos[i]);
            }
        }
        top = 0;
        abs = 0;
        for (i3 = 0; i3 < this.mViewPos.length / 2; i3++) {
            i = i3 * 2;
            if (this.mViewPos[i + 1] == i4) {
                if (top == 0) {
                    i2 = this.mViewPos[i];
                    abs = Math.abs(left - i2);
                } else if (Math.abs(left - this.mViewPos[i]) < abs) {
                    i2 = this.mViewPos[i];
                    abs = Math.abs(left - i2);
                }
                top++;
            }
        }
        return new int[]{i2, i4};
    }

    private int onGetCoordinateReferPos(int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < this.mViewPos.length / 2; i4++) {
            int i5 = i4 * 2;
            if (i == this.mViewPos[i5] && i2 == this.mViewPos[i5 + 1]) {
                i3 = i4;
            }
        }
        return i3;
    }

    private void onChangeView(View view, int i, int i2) {
        this.mChildViews.remove(i2);
        this.mChildViews.add(i, view);
        for (View view2 : this.mChildViews) {
            view2.setTag(Integer.valueOf(this.mChildViews.indexOf(view2)));
        }
        removeViewAt(i2);
        addView(view, i);
    }

    private int ceilTagBorderWidth() {
        return (int) Math.ceil((double) this.mTagBorderWidth);
    }

    public int getTagViewState() {
        return this.mTagViewState;
    }

    public float getTagBdDistance() {
        return this.mTagBdDistance;
    }

    public void setTagBdDistance(float f) {
        this.mTagBdDistance = Utils.dp2px(getContext(), f);
    }

    public void setTags(List<String> list) {
        this.mTags = list;
        onSetTag();
    }

    public void setTags(List<String> list, List<int[]> list2) {
        this.mTags = list;
        this.mColorArrayList = list2;
        onSetTag();
    }

    public void setTags(String... strArr) {
        this.mTags = Arrays.asList(strArr);
        onSetTag();
    }

    public void addTag(String str) {
        addTag(str, this.mChildViews.size());
    }

    public void addTag(String str, int i) {
        onAddTag(str, i);
        postInvalidate();
    }

    public void removeTag(int i) {
        onRemoveTag(i);
        postInvalidate();
    }

    public void removeConsecutiveTags(List<Integer> list) {
        onRemoveConsecutiveTags(list);
        postInvalidate();
    }

    public void removeAllTags() {
        this.mChildViews.clear();
        removeAllViews();
        postInvalidate();
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.mOnTagClickListener = onTagClickListener;
        invalidateTags();
    }

    public void toggleSelectTagView(int i) {
        if (this.isTagViewSelectable) {
            TagView tagView = (TagView) this.mChildViews.get(i);
            if (tagView.getIsViewSelected()) {
                tagView.deselectView();
            } else {
                tagView.selectView();
            }
        }
    }

    public void selectTagView(int i) {
        if (this.isTagViewSelectable) {
            ((TagView) this.mChildViews.get(i)).selectView();
        }
    }

    public void deselectTagView(int i) {
        if (this.isTagViewSelectable) {
            ((TagView) this.mChildViews.get(i)).deselectView();
        }
    }

    public List<Integer> getSelectedTagViewPositions() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mChildViews.size(); i++) {
            if (((TagView) this.mChildViews.get(i)).getIsViewSelected()) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        return arrayList;
    }

    public List<String> getSelectedTagViewText() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mChildViews.size(); i++) {
            TagView tagView = (TagView) this.mChildViews.get(i);
            if (tagView.getIsViewSelected()) {
                arrayList.add(tagView.getText());
            }
        }
        return arrayList;
    }

    public int size() {
        return this.mChildViews.size();
    }

    public String getTagText(int i) {
        return ((TagView) this.mChildViews.get(i)).getText();
    }

    public List<String> getTags() {
        ArrayList arrayList = new ArrayList();
        for (View view : this.mChildViews) {
            if (view instanceof TagView) {
                arrayList.add(((TagView) view).getText());
            }
        }
        return arrayList;
    }

    public void setDragEnable(boolean z) {
        this.mDragEnable = z;
    }

    public boolean getDragEnable() {
        return this.mDragEnable;
    }

    public void setVerticalInterval(float f) {
        this.mVerticalInterval = (int) Utils.dp2px(getContext(), f);
        postInvalidate();
    }

    public int getVerticalInterval() {
        return this.mVerticalInterval;
    }

    public void setHorizontalInterval(float f) {
        this.mHorizontalInterval = (int) Utils.dp2px(getContext(), f);
        postInvalidate();
    }

    public int getHorizontalInterval() {
        return this.mHorizontalInterval;
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderWidth(float f) {
        this.mBorderWidth = f;
    }

    public float getBorderRadius() {
        return this.mBorderRadius;
    }

    public void setBorderRadius(float f) {
        this.mBorderRadius = f;
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public void setBorderColor(int i) {
        this.mBorderColor = i;
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setGravity(int i) {
        this.mGravity = i;
    }

    public float getSensitivity() {
        return this.mSensitivity;
    }

    public void setSensitivity(float f) {
        this.mSensitivity = f;
    }

    public int getDefaultImageDrawableID() {
        return this.mDefaultImageDrawableID;
    }

    public void setDefaultImageDrawableID(int i) {
        this.mDefaultImageDrawableID = i;
    }

    public void setMaxLines(int i) {
        this.mMaxLines = i;
        postInvalidate();
    }

    public int getMaxLines() {
        return this.mMaxLines;
    }

    public void setTagMaxLength(int i) {
        if (i < 3) {
            i = 3;
        }
        this.mTagMaxLength = i;
    }

    public int getTagMaxLength() {
        return this.mTagMaxLength;
    }

    public void setTheme(int i) {
        this.mTheme = i;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public boolean getIsTagViewClickable() {
        return this.isTagViewClickable;
    }

    public void setIsTagViewClickable(boolean z) {
        this.isTagViewClickable = z;
    }

    public boolean getIsTagViewSelectable() {
        return this.isTagViewSelectable;
    }

    public void setIsTagViewSelectable(boolean z) {
        this.isTagViewSelectable = z;
    }

    public float getTagBorderWidth() {
        return this.mTagBorderWidth;
    }

    public void setTagBorderWidth(float f) {
        this.mTagBorderWidth = f;
    }

    public float getTagBorderRadius() {
        return this.mTagBorderRadius;
    }

    public void setTagBorderRadius(float f) {
        this.mTagBorderRadius = f;
    }

    public float getTagTextSize() {
        return this.mTagTextSize;
    }

    public void setTagTextSize(float f) {
        this.mTagTextSize = f;
    }

    public int getTagHorizontalPadding() {
        return this.mTagHorizontalPadding;
    }

    public void setTagHorizontalPadding(int i) {
        int ceilTagBorderWidth = ceilTagBorderWidth();
        if (i < ceilTagBorderWidth) {
            i = ceilTagBorderWidth;
        }
        this.mTagHorizontalPadding = i;
    }

    public int getTagVerticalPadding() {
        return this.mTagVerticalPadding;
    }

    public void setTagVerticalPadding(int i) {
        int ceilTagBorderWidth = ceilTagBorderWidth();
        if (i < ceilTagBorderWidth) {
            i = ceilTagBorderWidth;
        }
        this.mTagVerticalPadding = i;
    }

    public int getTagBorderColor() {
        return this.mTagBorderColor;
    }

    public void setTagBorderColor(int i) {
        this.mTagBorderColor = i;
    }

    public int getTagBackgroundColor() {
        return this.mTagBackgroundColor;
    }

    public void setTagBackgroundColor(int i) {
        this.mTagBackgroundColor = i;
    }

    public int getTagTextColor() {
        return this.mTagTextColor;
    }

    public void setTagTextDirection(int i) {
        this.mTagTextDirection = i;
    }

    public Typeface getTagTypeface() {
        return this.mTagTypeface;
    }

    public void setTagTypeface(Typeface typeface) {
        this.mTagTypeface = typeface;
    }

    public int getTagTextDirection() {
        return this.mTagTextDirection;
    }

    public void setTagTextColor(int i) {
        this.mTagTextColor = i;
    }

    public int getRippleAlpha() {
        return this.mRippleAlpha;
    }

    public void setRippleAlpha(int i) {
        this.mRippleAlpha = i;
    }

    public int getRippleColor() {
        return this.mRippleColor;
    }

    public void setRippleColor(int i) {
        this.mRippleColor = i;
    }

    public int getRippleDuration() {
        return this.mRippleDuration;
    }

    public void setRippleDuration(int i) {
        this.mRippleDuration = i;
    }

    public int getCrossColor() {
        return this.mCrossColor;
    }

    public void setCrossColor(int i) {
        this.mCrossColor = i;
    }

    public float getCrossAreaPadding() {
        return this.mCrossAreaPadding;
    }

    public void setCrossAreaPadding(float f) {
        this.mCrossAreaPadding = f;
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

    public boolean isTagSupportLettersRTL() {
        return this.mTagSupportLettersRTL;
    }

    public void setTagSupportLettersRTL(boolean z) {
        this.mTagSupportLettersRTL = z;
    }

    public TagView getTagView(int i) {
        if (i >= 0 && i < this.mChildViews.size()) {
            return (TagView) this.mChildViews.get(i);
        }
        throw new RuntimeException("Illegal position!");
    }

    public int getTagBackgroundResource() {
        return this.mTagBackgroundResource;
    }

    public void setTagBackgroundResource(@DrawableRes int i) {
        this.mTagBackgroundResource = i;
    }
}
