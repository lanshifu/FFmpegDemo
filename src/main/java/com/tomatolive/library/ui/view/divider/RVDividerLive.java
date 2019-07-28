package com.tomatolive.library.ui.view.divider;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import com.tomatolive.library.ui.view.divider.decoration.Y_Divider;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerBuilder;
import com.tomatolive.library.ui.view.divider.decoration.Y_DividerItemDecoration;
import com.yalantis.ucrop.view.CropImageView;

public class RVDividerLive extends Y_DividerItemDecoration {
    private int colorRes;
    private Context context;
    private boolean isHeadView = false;
    private boolean isHeadViewWidth = true;
    private final float widthDp = 10.0f;

    public RVDividerLive(Context context, @ColorRes int i) {
        super(context);
        this.context = context;
        this.colorRes = i;
    }

    public RVDividerLive(Context context, @ColorRes int i, boolean z, boolean z2) {
        super(context);
        this.context = context;
        this.colorRes = i;
        this.isHeadView = z;
        this.isHeadViewWidth = z2;
    }

    public Y_Divider getDivider(int i) {
        Y_Divider create;
        if (this.isHeadView) {
            if (i != 0) {
                switch (i % 2) {
                    case 0:
                        create = new Y_DividerBuilder().setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
                        break;
                    case 1:
                        create = new Y_DividerBuilder().setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
                        break;
                }
            }
            return new Y_DividerBuilder().setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getHeadViewWidthDp(), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getHeadViewWidthDp(), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getHeadViewWidthDp(), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(false, ContextCompat.getColor(this.context, this.colorRes), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
        }
        switch (i % 2) {
            case 0:
                create = new Y_DividerBuilder().setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
                break;
            case 1:
                create = new Y_DividerBuilder().setLeftSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 5.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setRightSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setBottomSideLine(true, ContextCompat.getColor(this.context, this.colorRes), 10.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).setTopSideLine(true, ContextCompat.getColor(this.context, this.colorRes), getTopDp(i), CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO).create();
                break;
        }
        create = null;
        return create;
    }

    private float getTopDp(int i) {
        if (this.isHeadView) {
            if (i == 1 || i == 2) {
                return 10.0f;
            }
        } else if (i == 0 || i == 1) {
            return 10.0f;
        }
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }

    private float getHeadViewWidthDp() {
        return this.isHeadViewWidth ? 10.0f : CropImageView.DEFAULT_ASPECT_RATIO;
    }
}
