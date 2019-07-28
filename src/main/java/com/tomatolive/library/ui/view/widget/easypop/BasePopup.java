package com.tomatolive.library.ui.view.widget.easypop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.widget.PopupWindowCompat;
import android.transition.Transition;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public abstract class BasePopup<T extends BasePopup> implements OnDismissListener {
    private static final float DEFAULT_DIM = 0.7f;
    private static final String TAG = "EasyPopup";
    private boolean isAtAnchorViewMethod = false;
    private boolean isBackgroundDim;
    private boolean isNeedReMeasureWH = false;
    private boolean isRealWHAlready = false;
    private View mAnchorView;
    private int mAnimationStyle;
    private View mContentView;
    private Context mContext;
    @ColorInt
    private int mDimColor = -16777216;
    private float mDimValue = DEFAULT_DIM;
    @NonNull
    private ViewGroup mDimView;
    private Transition mEnterTransition;
    private Transition mExitTransition;
    private boolean mFocusAndOutsideEnable = true;
    private boolean mFocusable = true;
    private int mHeight = -2;
    private int mInputMethodMode = 0;
    private int mLayoutId;
    private int mOffsetX;
    private int mOffsetY;
    private OnDismissListener mOnDismissListener;
    private OnRealWHAlreadyListener mOnRealWHAlreadyListener;
    private boolean mOutsideTouchable = true;
    private PopupWindow mPopupWindow;
    private int mSoftInputMode = 1;
    private int mWidth = -2;
    private int mXGravity = 1;
    private int mYGravity = 2;

    public interface OnRealWHAlreadyListener {
        void onRealWHAlready(BasePopup basePopup, int i, int i2, int i3, int i4);
    }

    public abstract void initAttributes();

    public abstract void initViews(View view, T t);

    /* Access modifiers changed, original: protected */
    public void onPopupWindowDismiss() {
    }

    /* Access modifiers changed, original: protected */
    public T self() {
        return this;
    }

    public T apply() {
        if (this.mPopupWindow == null) {
            this.mPopupWindow = new PopupWindow();
        }
        onPopupWindowCreated();
        initContentViewAndWH();
        onPopupWindowViewCreated(this.mContentView);
        if (this.mAnimationStyle != 0) {
            this.mPopupWindow.setAnimationStyle(this.mAnimationStyle);
        }
        initFocusAndBack();
        this.mPopupWindow.setOnDismissListener(this);
        if (VERSION.SDK_INT >= 23) {
            if (this.mEnterTransition != null) {
                this.mPopupWindow.setEnterTransition(this.mEnterTransition);
            }
            if (this.mExitTransition != null) {
                this.mPopupWindow.setExitTransition(this.mExitTransition);
            }
        }
        return self();
    }

    private void initContentViewAndWH() {
        if (this.mContentView == null) {
            if (this.mLayoutId == 0 || this.mContext == null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("The content view is null,the layoutId=");
                stringBuilder.append(this.mLayoutId);
                stringBuilder.append(",context=");
                stringBuilder.append(this.mContext);
                throw new IllegalArgumentException(stringBuilder.toString());
            }
            this.mContentView = LayoutInflater.from(this.mContext).inflate(this.mLayoutId, null);
        }
        this.mPopupWindow.setContentView(this.mContentView);
        if (this.mWidth > 0 || this.mWidth == -2 || this.mWidth == -1) {
            this.mPopupWindow.setWidth(this.mWidth);
        } else {
            this.mPopupWindow.setWidth(-2);
        }
        if (this.mHeight > 0 || this.mHeight == -2 || this.mHeight == -1) {
            this.mPopupWindow.setHeight(this.mHeight);
        } else {
            this.mPopupWindow.setHeight(-2);
        }
        measureContentView();
        registerOnGlobalLayoutListener();
        this.mPopupWindow.setInputMethodMode(this.mInputMethodMode);
        this.mPopupWindow.setSoftInputMode(this.mSoftInputMode);
    }

    private void initFocusAndBack() {
        if (this.mFocusAndOutsideEnable) {
            this.mPopupWindow.setFocusable(this.mFocusable);
            this.mPopupWindow.setOutsideTouchable(this.mOutsideTouchable);
            this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
            return;
        }
        this.mPopupWindow.setFocusable(true);
        this.mPopupWindow.setOutsideTouchable(false);
        this.mPopupWindow.setBackgroundDrawable(null);
        this.mPopupWindow.getContentView().setFocusable(true);
        this.mPopupWindow.getContentView().setFocusableInTouchMode(true);
        this.mPopupWindow.getContentView().setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return false;
                }
                BasePopup.this.mPopupWindow.dismiss();
                return true;
            }
        });
        this.mPopupWindow.setTouchInterceptor(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                String str;
                StringBuilder stringBuilder;
                if (motionEvent.getAction() == 0 && (x < 0 || x >= BasePopup.this.mWidth || y < 0 || y >= BasePopup.this.mHeight)) {
                    str = BasePopup.TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("onTouch outside:mWidth=");
                    stringBuilder.append(BasePopup.this.mWidth);
                    stringBuilder.append(",mHeight=");
                    stringBuilder.append(BasePopup.this.mHeight);
                    Log.d(str, stringBuilder.toString());
                    return true;
                } else if (motionEvent.getAction() != 4) {
                    return false;
                } else {
                    str = BasePopup.TAG;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("onTouch outside event:mWidth=");
                    stringBuilder.append(BasePopup.this.mWidth);
                    stringBuilder.append(",mHeight=");
                    stringBuilder.append(BasePopup.this.mHeight);
                    Log.d(str, stringBuilder.toString());
                    return true;
                }
            }
        });
    }

    /* Access modifiers changed, original: protected */
    public void onPopupWindowCreated() {
        initAttributes();
    }

    /* Access modifiers changed, original: protected */
    public void onPopupWindowViewCreated(View view) {
        initViews(view, self());
    }

    private void measureContentView() {
        View contentView = getContentView();
        if (this.mWidth <= 0 || this.mHeight <= 0) {
            contentView.measure(0, 0);
            if (this.mWidth <= 0) {
                this.mWidth = contentView.getMeasuredWidth();
            }
            if (this.mHeight <= 0) {
                this.mHeight = contentView.getMeasuredHeight();
            }
        }
    }

    private void registerOnGlobalLayoutListener() {
        getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                BasePopup.this.getContentView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                BasePopup.this.mWidth = BasePopup.this.getContentView().getWidth();
                BasePopup.this.mHeight = BasePopup.this.getContentView().getHeight();
                BasePopup.this.isRealWHAlready = true;
                BasePopup.this.isNeedReMeasureWH = false;
                if (BasePopup.this.mOnRealWHAlreadyListener != null) {
                    BasePopup.this.mOnRealWHAlreadyListener.onRealWHAlready(BasePopup.this, BasePopup.this.mWidth, BasePopup.this.mHeight, BasePopup.this.mAnchorView == null ? 0 : BasePopup.this.mAnchorView.getWidth(), BasePopup.this.mAnchorView == null ? 0 : BasePopup.this.mAnchorView.getHeight());
                }
                if (BasePopup.this.isShowing() && BasePopup.this.isAtAnchorViewMethod) {
                    BasePopup.this.updateLocation(BasePopup.this.mWidth, BasePopup.this.mHeight, BasePopup.this.mAnchorView, BasePopup.this.mYGravity, BasePopup.this.mXGravity, BasePopup.this.mOffsetX, BasePopup.this.mOffsetY);
                }
            }
        });
    }

    private void updateLocation(int i, int i2, @NonNull View view, int i3, int i4, int i5, int i6) {
        if (this.mPopupWindow != null) {
            this.mPopupWindow.update(view, calculateX(view, i4, i, i5), calculateY(view, i3, i2, i6), i, i2);
        }
    }

    public T setContext(Context context) {
        this.mContext = context;
        return self();
    }

    public T setContentView(View view) {
        this.mContentView = view;
        this.mLayoutId = 0;
        return self();
    }

    public T setContentView(@LayoutRes int i) {
        this.mContentView = null;
        this.mLayoutId = i;
        return self();
    }

    public T setContentView(Context context, @LayoutRes int i) {
        this.mContext = context;
        this.mContentView = null;
        this.mLayoutId = i;
        return self();
    }

    public T setContentView(View view, int i, int i2) {
        this.mContentView = view;
        this.mLayoutId = 0;
        this.mWidth = i;
        this.mHeight = i2;
        return self();
    }

    public T setContentView(@LayoutRes int i, int i2, int i3) {
        this.mContentView = null;
        this.mLayoutId = i;
        this.mWidth = i2;
        this.mHeight = i3;
        return self();
    }

    public T setContentView(Context context, @LayoutRes int i, int i2, int i3) {
        this.mContext = context;
        this.mContentView = null;
        this.mLayoutId = i;
        this.mWidth = i2;
        this.mHeight = i3;
        return self();
    }

    public T setWidth(int i) {
        this.mWidth = i;
        return self();
    }

    public T setHeight(int i) {
        this.mHeight = i;
        return self();
    }

    public T setAnchorView(View view) {
        this.mAnchorView = view;
        return self();
    }

    public T setYGravity(int i) {
        this.mYGravity = i;
        return self();
    }

    public T setXGravity(int i) {
        this.mXGravity = i;
        return self();
    }

    public T setOffsetX(int i) {
        this.mOffsetX = i;
        return self();
    }

    public T setOffsetY(int i) {
        this.mOffsetY = i;
        return self();
    }

    public T setAnimationStyle(@StyleRes int i) {
        this.mAnimationStyle = i;
        return self();
    }

    public T setFocusable(boolean z) {
        this.mFocusable = z;
        return self();
    }

    public T setOutsideTouchable(boolean z) {
        this.mOutsideTouchable = z;
        return self();
    }

    public T setFocusAndOutsideEnable(boolean z) {
        this.mFocusAndOutsideEnable = z;
        return self();
    }

    public T setBackgroundDimEnable(boolean z) {
        this.isBackgroundDim = z;
        return self();
    }

    public T setDimValue(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.mDimValue = f;
        return self();
    }

    public T setDimColor(@ColorInt int i) {
        this.mDimColor = i;
        return self();
    }

    public T setDimView(@NonNull ViewGroup viewGroup) {
        this.mDimView = viewGroup;
        return self();
    }

    @RequiresApi(api = 23)
    public T setEnterTransition(Transition transition) {
        this.mEnterTransition = transition;
        return self();
    }

    @RequiresApi(api = 23)
    public T setExitTransition(Transition transition) {
        this.mExitTransition = transition;
        return self();
    }

    public T setInputMethodMode(int i) {
        this.mInputMethodMode = i;
        return self();
    }

    public T setSoftInputMode(int i) {
        this.mSoftInputMode = i;
        return self();
    }

    public T setNeedReMeasureWH(boolean z) {
        this.isNeedReMeasureWH = z;
        return self();
    }

    private void checkIsApply(boolean z) {
        if (this.isAtAnchorViewMethod != z) {
            this.isAtAnchorViewMethod = z;
        }
        if (this.mPopupWindow == null) {
            apply();
        }
    }

    public void showAsDropDown() {
        if (this.mAnchorView != null) {
            showAsDropDown(this.mAnchorView, this.mOffsetX, this.mOffsetY);
        }
    }

    public void showAsDropDown(View view, int i, int i2) {
        checkIsApply(false);
        handleBackgroundDim();
        this.mAnchorView = view;
        this.mOffsetX = i;
        this.mOffsetY = i2;
        if (this.isNeedReMeasureWH) {
            registerOnGlobalLayoutListener();
        }
        this.mPopupWindow.showAsDropDown(view, this.mOffsetX, this.mOffsetY);
    }

    public void showAsDropDown(View view) {
        checkIsApply(false);
        handleBackgroundDim();
        this.mAnchorView = view;
        if (this.isNeedReMeasureWH) {
            registerOnGlobalLayoutListener();
        }
        this.mPopupWindow.showAsDropDown(view);
    }

    @RequiresApi(api = 19)
    public void showAsDropDown(View view, int i, int i2, int i3) {
        checkIsApply(false);
        handleBackgroundDim();
        this.mAnchorView = view;
        this.mOffsetX = i;
        this.mOffsetY = i2;
        if (this.isNeedReMeasureWH) {
            registerOnGlobalLayoutListener();
        }
        PopupWindowCompat.showAsDropDown(this.mPopupWindow, view, this.mOffsetX, this.mOffsetY, i3);
    }

    public void showAtLocation(View view, int i, int i2, int i3) {
        checkIsApply(false);
        handleBackgroundDim();
        this.mAnchorView = view;
        this.mOffsetX = i2;
        this.mOffsetY = i3;
        if (this.isNeedReMeasureWH) {
            registerOnGlobalLayoutListener();
        }
        this.mPopupWindow.showAtLocation(view, i, this.mOffsetX, this.mOffsetY);
    }

    public void showAtAnchorView() {
        if (this.mAnchorView != null) {
            showAtAnchorView(this.mAnchorView, this.mYGravity, this.mXGravity);
        }
    }

    public void showAtAnchorView(@NonNull View view, int i, int i2) {
        showAtAnchorView(view, i, i2, 0, 0);
    }

    public void showAtAnchorView(@NonNull View view, int i, int i2, int i3, int i4) {
        checkIsApply(true);
        this.mAnchorView = view;
        this.mOffsetX = i3;
        this.mOffsetY = i4;
        this.mYGravity = i;
        this.mXGravity = i2;
        handleBackgroundDim();
        i2 = calculateX(view, i2, this.mWidth, this.mOffsetX);
        i = calculateY(view, i, this.mHeight, this.mOffsetY);
        if (this.isNeedReMeasureWH) {
            registerOnGlobalLayoutListener();
        }
        PopupWindowCompat.showAsDropDown(this.mPopupWindow, view, i2, i, 0);
    }

    private int calculateY(View view, int i, int i2, int i3) {
        switch (i) {
            case 0:
                return i3 - ((view.getHeight() / 2) + (i2 / 2));
            case 1:
                return i3 - (i2 + view.getHeight());
            case 3:
                return i3 - view.getHeight();
            case 4:
                return i3 - i2;
            default:
                return i3;
        }
    }

    private int calculateX(View view, int i, int i2, int i3) {
        switch (i) {
            case 0:
                return i3 + ((view.getWidth() / 2) - (i2 / 2));
            case 1:
                return i3 - i2;
            case 2:
                return i3 + view.getWidth();
            case 4:
                return i3 - (i2 - view.getWidth());
            default:
                return i3;
        }
    }

    public T setOnDismissListener(OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return self();
    }

    public T setOnRealWHAlreadyListener(OnRealWHAlreadyListener onRealWHAlreadyListener) {
        this.mOnRealWHAlreadyListener = onRealWHAlreadyListener;
        return self();
    }

    private void handleBackgroundDim() {
        if (VERSION.SDK_INT >= 18 && this.isBackgroundDim) {
            if (this.mDimView != null) {
                applyDim(this.mDimView);
            } else if (!(getContentView() == null || getContentView().getContext() == null || !(getContentView().getContext() instanceof Activity))) {
                applyDim((Activity) getContentView().getContext());
            }
        }
    }

    @RequiresApi(api = 18)
    private void applyDim(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView().getRootView();
        ColorDrawable colorDrawable = new ColorDrawable(this.mDimColor);
        colorDrawable.setBounds(0, 0, viewGroup.getWidth(), viewGroup.getHeight());
        colorDrawable.setAlpha((int) (this.mDimValue * 255.0f));
        viewGroup.getOverlay().add(colorDrawable);
    }

    @RequiresApi(api = 18)
    private void applyDim(ViewGroup viewGroup) {
        ColorDrawable colorDrawable = new ColorDrawable(this.mDimColor);
        colorDrawable.setBounds(0, 0, viewGroup.getWidth(), viewGroup.getHeight());
        colorDrawable.setAlpha((int) (this.mDimValue * 255.0f));
        viewGroup.getOverlay().add(colorDrawable);
    }

    private void clearBackgroundDim() {
        if (VERSION.SDK_INT >= 18 && this.isBackgroundDim) {
            if (this.mDimView != null) {
                clearDim(this.mDimView);
            } else if (getContentView() != null) {
                Activity activity = (Activity) getContentView().getContext();
                if (activity != null) {
                    clearDim(activity);
                }
            }
        }
    }

    @RequiresApi(api = 18)
    private void clearDim(Activity activity) {
        ((ViewGroup) activity.getWindow().getDecorView().getRootView()).getOverlay().clear();
    }

    @RequiresApi(api = 18)
    private void clearDim(ViewGroup viewGroup) {
        viewGroup.getOverlay().clear();
    }

    public View getContentView() {
        return this.mPopupWindow != null ? this.mPopupWindow.getContentView() : null;
    }

    public PopupWindow getPopupWindow() {
        return this.mPopupWindow;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getXGravity() {
        return this.mXGravity;
    }

    public int getYGravity() {
        return this.mYGravity;
    }

    public int getOffsetX() {
        return this.mOffsetX;
    }

    public int getOffsetY() {
        return this.mOffsetY;
    }

    public boolean isShowing() {
        return this.mPopupWindow != null && this.mPopupWindow.isShowing();
    }

    public boolean isRealWHAlready() {
        return this.isRealWHAlready;
    }

    public <T extends View> T findViewById(@IdRes int i) {
        return getContentView() != null ? getContentView().findViewById(i) : null;
    }

    public void dismiss() {
        if (this.mPopupWindow != null) {
            this.mPopupWindow.dismiss();
        }
    }

    public void onDismiss() {
        handleDismiss();
    }

    private void handleDismiss() {
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss();
        }
        clearBackgroundDim();
        if (this.mPopupWindow != null && this.mPopupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
        }
        onPopupWindowDismiss();
    }
}
