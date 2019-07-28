package com.tomatolive.library.ui.view.widget.swipeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Scroller;
import com.tomatolive.library.R;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;

public class EasySwipeMenuLayout extends ViewGroup {
    private static final String TAG = "EasySwipeMenuLayout";
    private static State mStateCache;
    private static EasySwipeMenuLayout mViewCache;
    private float distanceX;
    private float finalyDistanceX;
    private boolean isSwipeing;
    private boolean mCanLeftSwipe;
    private boolean mCanRightSwipe;
    private View mContentView;
    private MarginLayoutParams mContentViewLp;
    private int mContentViewResID;
    private PointF mFirstP;
    private float mFraction;
    private PointF mLastP;
    private View mLeftView;
    private int mLeftViewResID;
    private final ArrayList<View> mMatchParentChildren;
    private View mRightView;
    private int mRightViewResID;
    private int mScaledTouchSlop;
    private Scroller mScroller;
    State result;

    public EasySwipeMenuLayout(Context context) {
        this(context, null);
    }

    public EasySwipeMenuLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EasySwipeMenuLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMatchParentChildren = new ArrayList(1);
        this.mFraction = 0.3f;
        this.mCanLeftSwipe = true;
        this.mCanRightSwipe = true;
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        this.mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mScroller = new Scroller(context);
        int i2 = 0;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.EasySwipeMenuLayout, i, 0);
        try {
            int indexCount = obtainStyledAttributes.getIndexCount();
            while (i2 < indexCount) {
                i = obtainStyledAttributes.getIndex(i2);
                if (i == R.styleable.EasySwipeMenuLayout_leftMenuView) {
                    this.mLeftViewResID = obtainStyledAttributes.getResourceId(R.styleable.EasySwipeMenuLayout_leftMenuView, -1);
                } else if (i == R.styleable.EasySwipeMenuLayout_rightMenuView) {
                    this.mRightViewResID = obtainStyledAttributes.getResourceId(R.styleable.EasySwipeMenuLayout_rightMenuView, -1);
                } else if (i == R.styleable.EasySwipeMenuLayout_contentView) {
                    this.mContentViewResID = obtainStyledAttributes.getResourceId(R.styleable.EasySwipeMenuLayout_contentView, -1);
                } else if (i == R.styleable.EasySwipeMenuLayout_canLeftSwipe) {
                    this.mCanLeftSwipe = obtainStyledAttributes.getBoolean(R.styleable.EasySwipeMenuLayout_canLeftSwipe, true);
                } else if (i == R.styleable.EasySwipeMenuLayout_canRightSwipe) {
                    this.mCanRightSwipe = obtainStyledAttributes.getBoolean(R.styleable.EasySwipeMenuLayout_canRightSwipe, true);
                } else if (i == R.styleable.EasySwipeMenuLayout_fraction) {
                    this.mFraction = obtainStyledAttributes.getFloat(R.styleable.EasySwipeMenuLayout_fraction, 0.5f);
                }
                i2++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
        }
        obtainStyledAttributes.recycle();
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        View childAt;
        int i3;
        int max;
        int combineMeasuredStates;
        int i4 = i;
        int i5 = i2;
        super.onMeasure(i, i2);
        setClickable(true);
        int childCount = getChildCount();
        Object obj = (MeasureSpec.getMode(i) == 1073741824 && MeasureSpec.getMode(i2) == 1073741824) ? null : 1;
        this.mMatchParentChildren.clear();
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            childAt = getChildAt(i9);
            int i10;
            if (childAt.getVisibility() != 8) {
                View view = childAt;
                i3 = i6;
                i10 = i7;
                measureChildWithMargins(childAt, i, 0, i2, 0);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
                max = Math.max(i10, (view.getMeasuredWidth() + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin);
                int max2 = Math.max(i8, (view.getMeasuredHeight() + marginLayoutParams.topMargin) + marginLayoutParams.bottomMargin);
                combineMeasuredStates = combineMeasuredStates(i3, view.getMeasuredState());
                if (obj != null && (marginLayoutParams.width == -1 || marginLayoutParams.height == -1)) {
                    this.mMatchParentChildren.add(view);
                }
                i7 = max;
                i8 = max2;
                i6 = combineMeasuredStates;
            } else {
                i3 = i6;
                i10 = i7;
            }
        }
        i3 = i6;
        setMeasuredDimension(resolveSizeAndState(Math.max(i7, getSuggestedMinimumWidth()), i4, i3), resolveSizeAndState(Math.max(i8, getSuggestedMinimumHeight()), i5, i3 << 16));
        int size = this.mMatchParentChildren.size();
        if (size > 1) {
            for (max = 0; max < size; max++) {
                childAt = (View) this.mMatchParentChildren.get(max);
                MarginLayoutParams marginLayoutParams2 = (MarginLayoutParams) childAt.getLayoutParams();
                if (marginLayoutParams2.width == -1) {
                    i6 = MeasureSpec.makeMeasureSpec(Math.max(0, (getMeasuredWidth() - marginLayoutParams2.leftMargin) - marginLayoutParams2.rightMargin), 1073741824);
                } else {
                    i6 = getChildMeasureSpec(i4, marginLayoutParams2.leftMargin + marginLayoutParams2.rightMargin, marginLayoutParams2.width);
                }
                if (marginLayoutParams2.height == -1) {
                    combineMeasuredStates = MeasureSpec.makeMeasureSpec(Math.max(0, (getMeasuredHeight() - marginLayoutParams2.topMargin) - marginLayoutParams2.bottomMargin), 1073741824);
                } else {
                    combineMeasuredStates = getChildMeasureSpec(i5, marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin, marginLayoutParams2.height);
                }
                childAt.measure(i6, combineMeasuredStates);
            }
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        MarginLayoutParams marginLayoutParams;
        int childCount = getChildCount();
        i = getPaddingLeft() + 0;
        getPaddingLeft();
        i3 = getPaddingTop() + 0;
        getPaddingTop();
        for (i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (this.mLeftView == null && childAt.getId() == this.mLeftViewResID) {
                this.mLeftView = childAt;
                this.mLeftView.setClickable(true);
            } else if (this.mRightView == null && childAt.getId() == this.mRightViewResID) {
                this.mRightView = childAt;
                this.mRightView.setClickable(true);
            } else if (this.mContentView == null && childAt.getId() == this.mContentViewResID) {
                this.mContentView = childAt;
                this.mContentView.setClickable(true);
            }
        }
        if (this.mContentView != null) {
            this.mContentViewLp = (MarginLayoutParams) this.mContentView.getLayoutParams();
            childCount = this.mContentViewLp.topMargin + i3;
            this.mContentView.layout(this.mContentViewLp.leftMargin + i, childCount, (i + this.mContentViewLp.leftMargin) + this.mContentView.getMeasuredWidth(), this.mContentView.getMeasuredHeight() + childCount);
        }
        if (this.mLeftView != null) {
            marginLayoutParams = (MarginLayoutParams) this.mLeftView.getLayoutParams();
            i = marginLayoutParams.topMargin + i3;
            this.mLeftView.layout(((0 - this.mLeftView.getMeasuredWidth()) + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin, i, 0 - marginLayoutParams.rightMargin, this.mLeftView.getMeasuredHeight() + i);
        }
        if (this.mRightView != null) {
            marginLayoutParams = (MarginLayoutParams) this.mRightView.getLayoutParams();
            i3 += marginLayoutParams.topMargin;
            i = (this.mContentView.getRight() + this.mContentViewLp.rightMargin) + marginLayoutParams.leftMargin;
            this.mRightView.layout(i, i3, this.mRightView.getMeasuredWidth() + i, this.mRightView.getMeasuredHeight() + i3);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.isSwipeing = false;
                if (this.mLastP == null) {
                    this.mLastP = new PointF();
                }
                this.mLastP.set(motionEvent.getRawX(), motionEvent.getRawY());
                if (this.mFirstP == null) {
                    this.mFirstP = new PointF();
                }
                this.mFirstP.set(motionEvent.getRawX(), motionEvent.getRawY());
                if (mViewCache != null) {
                    if (mViewCache != this) {
                        mViewCache.handlerSwipeMenu(State.CLOSE);
                    }
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
            case 1:
            case 3:
                this.finalyDistanceX = this.mFirstP.x - motionEvent.getRawX();
                if (Math.abs(this.finalyDistanceX) > ((float) this.mScaledTouchSlop)) {
                    this.isSwipeing = true;
                }
                this.result = isShouldOpen(getScrollX());
                handlerSwipeMenu(this.result);
                break;
            case 2:
                float rawX = this.mLastP.x - motionEvent.getRawX();
                float rawY = this.mLastP.y - motionEvent.getRawY();
                if (Math.abs(rawY) <= ((float) this.mScaledTouchSlop) || Math.abs(rawY) <= Math.abs(rawX)) {
                    scrollBy((int) rawX, 0);
                    if (getScrollX() < 0) {
                        if (!this.mCanRightSwipe || this.mLeftView == null) {
                            scrollTo(0, 0);
                        } else if (getScrollX() < this.mLeftView.getLeft()) {
                            scrollTo(this.mLeftView.getLeft(), 0);
                        }
                    } else if (getScrollX() > 0) {
                        if (!this.mCanLeftSwipe || this.mRightView == null) {
                            scrollTo(0, 0);
                        } else if (getScrollX() > (this.mRightView.getRight() - this.mContentView.getRight()) - this.mContentViewLp.rightMargin) {
                            scrollTo((this.mRightView.getRight() - this.mContentView.getRight()) - this.mContentViewLp.rightMargin, 0);
                        }
                    }
                    if (Math.abs(rawX) > ((float) this.mScaledTouchSlop)) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    this.mLastP.set(motionEvent.getRawX(), motionEvent.getRawY());
                    break;
                }
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                if (this.isSwipeing) {
                    this.isSwipeing = false;
                    this.finalyDistanceX = CropImageView.DEFAULT_ASPECT_RATIO;
                    return true;
                }
                break;
            case 2:
                if (Math.abs(this.finalyDistanceX) > ((float) this.mScaledTouchSlop)) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private void handlerSwipeMenu(State state) {
        if (state == State.LEFTOPEN) {
            this.mScroller.startScroll(getScrollX(), 0, this.mLeftView.getLeft() - getScrollX(), 0);
            mViewCache = this;
            mStateCache = state;
        } else if (state == State.RIGHTOPEN) {
            mViewCache = this;
            this.mScroller.startScroll(getScrollX(), 0, ((this.mRightView.getRight() - this.mContentView.getRight()) - this.mContentViewLp.rightMargin) - getScrollX(), 0);
            mStateCache = state;
        } else {
            this.mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0);
            mViewCache = null;
            mStateCache = null;
        }
        invalidate();
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
            invalidate();
        }
    }

    private State isShouldOpen(int i) {
        if (((float) this.mScaledTouchSlop) >= Math.abs(this.finalyDistanceX)) {
            return mStateCache;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(">>>finalyDistanceX:");
        stringBuilder.append(this.finalyDistanceX);
        Log.i(str, stringBuilder.toString());
        if (this.finalyDistanceX < CropImageView.DEFAULT_ASPECT_RATIO) {
            if (getScrollX() < 0 && this.mLeftView != null && Math.abs(((float) this.mLeftView.getWidth()) * this.mFraction) < ((float) Math.abs(getScrollX()))) {
                return State.LEFTOPEN;
            }
            if (getScrollX() > 0 && this.mRightView != null) {
                return State.CLOSE;
            }
        } else if (this.finalyDistanceX > CropImageView.DEFAULT_ASPECT_RATIO) {
            if (getScrollX() > 0 && this.mRightView != null && Math.abs(((float) this.mRightView.getWidth()) * this.mFraction) < ((float) Math.abs(getScrollX()))) {
                return State.RIGHTOPEN;
            }
            if (getScrollX() < 0 && this.mLeftView != null) {
                return State.CLOSE;
            }
        }
        return State.CLOSE;
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        if (this == mViewCache) {
            mViewCache.handlerSwipeMenu(State.CLOSE);
        }
        super.onDetachedFromWindow();
    }

    /* Access modifiers changed, original: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this == mViewCache) {
            mViewCache.handlerSwipeMenu(mStateCache);
        }
    }

    public void resetStatus() {
        if (mViewCache != null && mStateCache != null && mStateCache != State.CLOSE && this.mScroller != null) {
            this.mScroller.startScroll(mViewCache.getScrollX(), 0, -mViewCache.getScrollX(), 0);
            mViewCache.invalidate();
            mViewCache = null;
            mStateCache = null;
        }
    }

    public float getFraction() {
        return this.mFraction;
    }

    public void setFraction(float f) {
        this.mFraction = f;
    }

    public boolean isCanLeftSwipe() {
        return this.mCanLeftSwipe;
    }

    public void setCanLeftSwipe(boolean z) {
        this.mCanLeftSwipe = z;
    }

    public boolean isCanRightSwipe() {
        return this.mCanRightSwipe;
    }

    public void setCanRightSwipe(boolean z) {
        this.mCanRightSwipe = z;
    }

    public static EasySwipeMenuLayout getViewCache() {
        return mViewCache;
    }

    public static State getStateCache() {
        return mStateCache;
    }

    private boolean isLeftToRight() {
        return this.distanceX < CropImageView.DEFAULT_ASPECT_RATIO;
    }
}
