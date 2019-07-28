package com.tomatolive.library.ui.view.divider;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import com.tomatolive.library.ui.view.divider.decoration.Y_Divider;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerBuilder;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerItemDecoration;
import com.yalantis.ucrop.view.CropImageView;

public class RVDividerListMsg extends Y_DividerItemDecoration {
    private int colorRes;
    private Context context;

    public RVDividerListMsg(Context context, @ColorRes int i) {
        super(context);
        this.context = context;
        this.colorRes = i;
    }

    public Y_Divider getDivider(int i) {
        return new Y_DividerBuilder().setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 6.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
    }
}
