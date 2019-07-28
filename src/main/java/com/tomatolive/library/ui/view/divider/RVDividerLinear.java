package com.tomatolive.library.ui.view.divider;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import com.tomatolive.library.ui.view.divider.decoration.Y_Divider;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerBuilder;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerItemDecoration;
import com.yalantis.ucrop.view.CropImageView;

public class RVDividerLinear extends Y_DividerItemDecoration {
    private int colorRes;
    private Context context;
    private float widthDp = 0.8f;

    private float getPaddingWidthDp() {
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public RVDividerLinear(Context context, @ColorRes int i) {
        super(context);
        this.context = context;
        this.colorRes = i;
    }

    public RVDividerLinear(Context context, @ColorRes int i, boolean z) {
        super(context);
        this.context = context;
        this.colorRes = i;
    }

    public Y_Divider getDivider(int i) {
        if (i == 0) {
            return new Y_DividerBuilder().create();
        }
        if (i == 1) {
            return new Y_DividerBuilder().setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, getPaddingWidthDp(), getPaddingWidthDp()).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, getPaddingWidthDp(), getPaddingWidthDp()).create();
        }
        return new Y_DividerBuilder().setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, getPaddingWidthDp(), getPaddingWidthDp()).create();
    }
}
