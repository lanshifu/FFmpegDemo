package com.tomatolive.library.ui.view.sticker.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class IMGStickerContainer extends ViewGroup {
    public IMGStickerContainer(Context context) {
        super(context);
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount() == 1) {
            View childAt = getChildAt(0);
            if (childAt != null) {
                i += i3;
                i2 += i4;
                childAt.layout(i >> (1 - (childAt.getMeasuredWidth() >> 1)), i2 >> (1 - (childAt.getMeasuredHeight() >> 1)), i >> ((childAt.getMeasuredWidth() >> 1) + 1), i2 >> ((childAt.getMeasuredHeight() >> 1) + 1));
            }
        }
    }
}
