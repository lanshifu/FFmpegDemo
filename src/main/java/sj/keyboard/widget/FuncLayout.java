package sj.keyboard.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import java.util.ArrayList;
import java.util.List;
import sj.keyboard.utils.EmoticonsKeyboardUtils;

public class FuncLayout extends LinearLayout {
    public final int DEF_KEY = CheckView.UNCHECKED;
    private int mCurrentFuncKey = CheckView.UNCHECKED;
    private final SparseArray<View> mFuncViewArrayMap = new SparseArray();
    protected int mHeight = 0;
    private List<OnFuncKeyBoardListener> mListenerList;
    private OnFuncChangeListener onFuncChangeListener;

    public interface OnFuncChangeListener {
        void onFuncChange(int i);
    }

    public interface OnFuncKeyBoardListener {
        void OnFuncClose();

        void OnFuncPop(int i);
    }

    public FuncLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
    }

    public void addFuncView(int i, View view) {
        if (this.mFuncViewArrayMap.get(i) == null) {
            this.mFuncViewArrayMap.put(i, view);
            addView(view, new LayoutParams(-1, -1));
            view.setVisibility(8);
        }
    }

    public void hideAllFuncView() {
        for (int i = 0; i < this.mFuncViewArrayMap.size(); i++) {
            ((View) this.mFuncViewArrayMap.get(this.mFuncViewArrayMap.keyAt(i))).setVisibility(8);
        }
        this.mCurrentFuncKey = CheckView.UNCHECKED;
        setVisibility(false);
    }

    public void toggleFuncView(int i, boolean z, EditText editText) {
        if (getCurrentFuncKey() != i) {
            if (z) {
                EmoticonsKeyboardUtils.closeSoftKeyboard((View) editText);
            }
            showFuncView(i);
        } else if (z) {
            EmoticonsKeyboardUtils.closeSoftKeyboard((View) editText);
        } else {
            EmoticonsKeyboardUtils.openSoftKeyboard(editText);
        }
    }

    public void showFuncView(int i) {
        if (this.mFuncViewArrayMap.get(i) != null) {
            for (int i2 = 0; i2 < this.mFuncViewArrayMap.size(); i2++) {
                int keyAt = this.mFuncViewArrayMap.keyAt(i2);
                if (keyAt == i) {
                    ((View) this.mFuncViewArrayMap.get(keyAt)).setVisibility(0);
                } else {
                    ((View) this.mFuncViewArrayMap.get(keyAt)).setVisibility(8);
                }
            }
            this.mCurrentFuncKey = i;
            setVisibility(true);
            if (this.onFuncChangeListener != null) {
                this.onFuncChangeListener.onFuncChange(this.mCurrentFuncKey);
            }
        }
    }

    public int getCurrentFuncKey() {
        return this.mCurrentFuncKey;
    }

    public void updateHeight(int i) {
        this.mHeight = i;
    }

    public void setVisibility(boolean z) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        if (z) {
            setVisibility(0);
            layoutParams.height = this.mHeight;
            if (this.mListenerList != null) {
                for (OnFuncKeyBoardListener OnFuncPop : this.mListenerList) {
                    OnFuncPop.OnFuncPop(this.mHeight);
                }
            }
        } else {
            setVisibility(8);
            layoutParams.height = 0;
            if (this.mListenerList != null) {
                for (OnFuncKeyBoardListener OnFuncPop2 : this.mListenerList) {
                    OnFuncPop2.OnFuncClose();
                }
            }
        }
        setLayoutParams(layoutParams);
    }

    public boolean isOnlyShowSoftKeyboard() {
        return this.mCurrentFuncKey == CheckView.UNCHECKED;
    }

    public void addOnKeyBoardListener(OnFuncKeyBoardListener onFuncKeyBoardListener) {
        if (this.mListenerList == null) {
            this.mListenerList = new ArrayList();
        }
        this.mListenerList.add(onFuncKeyBoardListener);
    }

    public void setOnFuncChangeListener(OnFuncChangeListener onFuncChangeListener) {
        this.onFuncChangeListener = onFuncChangeListener;
    }
}
