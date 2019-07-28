package sj.keyboard.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;

public class SoftKeyboardSizeWatchLayout extends RelativeLayout {
    private Context mContext;
    protected boolean mIsSoftKeyboardPop = false;
    private List<OnResizeListener> mListenerList;
    private int mNowh = -1;
    private int mOldh = -1;
    protected int mScreenHeight = 0;

    public interface OnResizeListener {
        void OnSoftClose();

        void OnSoftPop(int i);
    }

    public SoftKeyboardSizeWatchLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                Rect rect = new Rect();
                ((Activity) SoftKeyboardSizeWatchLayout.this.mContext).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                if (SoftKeyboardSizeWatchLayout.this.mScreenHeight == 0) {
                    SoftKeyboardSizeWatchLayout.this.mScreenHeight = rect.bottom;
                }
                SoftKeyboardSizeWatchLayout.this.mNowh = SoftKeyboardSizeWatchLayout.this.mScreenHeight - rect.bottom;
                if (!(SoftKeyboardSizeWatchLayout.this.mOldh == -1 || SoftKeyboardSizeWatchLayout.this.mNowh == SoftKeyboardSizeWatchLayout.this.mOldh)) {
                    if (SoftKeyboardSizeWatchLayout.this.mNowh > 0) {
                        SoftKeyboardSizeWatchLayout.this.mIsSoftKeyboardPop = true;
                        if (SoftKeyboardSizeWatchLayout.this.mListenerList != null) {
                            for (OnResizeListener OnSoftPop : SoftKeyboardSizeWatchLayout.this.mListenerList) {
                                OnSoftPop.OnSoftPop(SoftKeyboardSizeWatchLayout.this.mNowh);
                            }
                        }
                    } else {
                        SoftKeyboardSizeWatchLayout.this.mIsSoftKeyboardPop = false;
                        if (SoftKeyboardSizeWatchLayout.this.mListenerList != null) {
                            for (OnResizeListener OnSoftPop2 : SoftKeyboardSizeWatchLayout.this.mListenerList) {
                                OnSoftPop2.OnSoftClose();
                            }
                        }
                    }
                }
                SoftKeyboardSizeWatchLayout.this.mOldh = SoftKeyboardSizeWatchLayout.this.mNowh;
            }
        });
    }

    public boolean isSoftKeyboardPop() {
        return this.mIsSoftKeyboardPop;
    }

    public void addOnResizeListener(OnResizeListener onResizeListener) {
        if (this.mListenerList == null) {
            this.mListenerList = new ArrayList();
        }
        this.mListenerList.add(onResizeListener);
    }
}
