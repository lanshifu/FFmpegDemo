package com.tomatolive.library.ui.view.divider;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import com.tomatolive.library.ui.view.divider.decoration.Y_Divider;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerBuilder;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerItemDecoration;
import com.yalantis.ucrop.view.CropImageView;

public class RVDividerRecommendGrid extends Y_DividerItemDecoration {
    private int colorRes;
    private Context context;

    public RVDividerRecommendGrid(Context context, @ColorRes int i) {
        super(context);
        this.context = context;
        this.colorRes = i;
    }

    public Y_Divider getDivider(int i) {
        if (i == 0) {
            return new Y_DividerBuilder().setRightSideLine(false, ContextCompat.getColor(this.context, this.colorRes), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setLeftSideLine(false, ContextCompat.getColor(this.context, this.colorRes), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setTopSideLine(false, ContextCompat.getColor(this.context, this.colorRes), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(false, ContextCompat.getColor(this.context, this.colorRes), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
        }
        Y_Divider create;
        switch (i % 3) {
            case 0:
                create = new Y_DividerBuilder().setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 16.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
                break;
            case 1:
                create = new Y_DividerBuilder().setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 16.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
                break;
            case 2:
                create = new Y_DividerBuilder().setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 8.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 8.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
                break;
            default:
                return new Y_DividerBuilder().setRightSideLine(false, ContextCompat.getColor(this.context, this.colorRes), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setLeftSideLine(false, ContextCompat.getColor(this.context, this.colorRes), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setTopSideLine(false, ContextCompat.getColor(this.context, this.colorRes), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(false, ContextCompat.getColor(this.context, this.colorRes), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
        }
        return create;
    }
}
