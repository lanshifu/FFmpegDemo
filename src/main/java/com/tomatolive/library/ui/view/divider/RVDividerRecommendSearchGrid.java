package com.tomatolive.library.ui.view.divider;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import com.tomatolive.library.ui.view.divider.decoration.Y_Divider;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerBuilder;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerItemDecoration;
import com.yalantis.ucrop.view.CropImageView;

public class RVDividerRecommendSearchGrid extends Y_DividerItemDecoration {
    private int colorRes;
    private Context context;
    private final float widthDp = 10.0f;

    public RVDividerRecommendSearchGrid(Context context, @ColorRes int i) {
        super(context);
        this.context = context;
        this.colorRes = i;
    }

    public Y_Divider getDivider(int i) {
        switch (i % 3) {
            case 0:
                return new Y_DividerBuilder().setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
            case 1:
                return new Y_DividerBuilder().setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
            case 2:
                return new Y_DividerBuilder().setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
            default:
                return null;
        }
    }
}
