package com.tomatolive.library.ui.view.divider;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import com.tomatolive.library.ui.view.divider.decoration.Y_Divider;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerBuilder;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerItemDecoration;
import com.yalantis.ucrop.view.CropImageView;

public class RVDividerGiftAdapter extends Y_DividerItemDecoration {
    private int colorRes;
    private Context context;
    private float widthDp = 0.8f;

    public RVDividerGiftAdapter(Context context, @ColorRes int i) {
        super(context);
        this.context = context;
        this.colorRes = i;
    }

    public Y_Divider getDivider(int i) {
        Y_Divider create;
        if (i == 0 || i == 1 || i == 2 || i == 3) {
            if (i == 0) {
                create = new Y_DividerBuilder().setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
            } else {
                create = new Y_DividerBuilder().setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
            }
            return create;
        }
        switch (i % 4) {
            case 0:
                create = new Y_DividerBuilder().setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
                break;
            case 1:
            case 2:
            case 3:
                create = new Y_DividerBuilder().setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), this.widthDp, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
                break;
            default:
                create = null;
                break;
        }
        return create;
    }
}
