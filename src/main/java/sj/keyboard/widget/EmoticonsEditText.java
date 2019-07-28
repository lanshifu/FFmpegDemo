package sj.keyboard.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView.BufferType;
import java.util.ArrayList;
import java.util.List;
import sj.keyboard.interfaces.EmoticonFilter;

public class EmoticonsEditText extends EditText {
    private List<EmoticonFilter> mFilterList;
    OnBackKeyClickListener onBackKeyClickListener;
    OnSizeChangedListener onSizeChangedListener;

    public interface OnBackKeyClickListener {
        void onBackKeyClick();
    }

    public interface OnSizeChangedListener {
        void onSizeChanged(int i, int i2, int i3, int i4);
    }

    public EmoticonsEditText(Context context) {
        this(context, null);
    }

    public EmoticonsEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EmoticonsEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        try {
            super.onMeasure(i, i2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            setText(getText().toString());
            super.onMeasure(i, i2);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i4 > 0 && this.onSizeChangedListener != null) {
            this.onSizeChangedListener.onSizeChanged(i, i2, i3, i4);
        }
    }

    public void setGravity(int i) {
        try {
            super.setGravity(i);
        } catch (ArrayIndexOutOfBoundsException unused) {
            setText(getText().toString());
            super.setGravity(i);
        }
    }

    public void setText(CharSequence charSequence, BufferType bufferType) {
        try {
            super.setText(charSequence, bufferType);
        } catch (ArrayIndexOutOfBoundsException unused) {
            setText(charSequence.toString());
        }
    }

    /* Access modifiers changed, original: protected|final */
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (this.mFilterList != null) {
            for (EmoticonFilter filter : this.mFilterList) {
                filter.filter(this, charSequence, i, i2, i3);
            }
        }
    }

    public void addEmoticonFilter(EmoticonFilter emoticonFilter) {
        if (this.mFilterList == null) {
            this.mFilterList = new ArrayList();
        }
        this.mFilterList.add(emoticonFilter);
    }

    public void removedEmoticonFilter(EmoticonFilter emoticonFilter) {
        if (this.mFilterList != null && this.mFilterList.contains(emoticonFilter)) {
            this.mFilterList.remove(emoticonFilter);
        }
    }

    public boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        if (this.onBackKeyClickListener != null) {
            this.onBackKeyClickListener.onBackKeyClick();
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }

    public void setOnBackKeyClickListener(OnBackKeyClickListener onBackKeyClickListener) {
        this.onBackKeyClickListener = onBackKeyClickListener;
    }

    public void setOnSizeChangedListener(OnSizeChangedListener onSizeChangedListener) {
        this.onSizeChangedListener = onSizeChangedListener;
    }
}
