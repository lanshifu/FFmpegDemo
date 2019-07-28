package com.tomatolive.library.ui.view.divider.decoration;

import android.support.annotation.ColorInt;
import com.yalantis.ucrop.view.CropImageView;

public class Y_DividerBuilder {
    private Y_SideLine bottomSideLine;
    private Y_SideLine leftSideLine;
    private Y_SideLine rightSideLine;
    private Y_SideLine topSideLine;

    public Y_DividerBuilder setLeftSideLine(boolean z, @ColorInt int i, float f, float f2, float f3) {
        this.leftSideLine = new Y_SideLine(z, i, f, f2, f3);
        return this;
    }

    public Y_DividerBuilder setTopSideLine(boolean z, @ColorInt int i, float f, float f2, float f3) {
        this.topSideLine = new Y_SideLine(z, i, f, f2, f3);
        return this;
    }

    public Y_DividerBuilder setRightSideLine(boolean z, @ColorInt int i, float f, float f2, float f3) {
        this.rightSideLine = new Y_SideLine(z, i, f, f2, f3);
        return this;
    }

    public Y_DividerBuilder setBottomSideLine(boolean z, @ColorInt int i, float f, float f2, float f3) {
        this.bottomSideLine = new Y_SideLine(z, i, f, f2, f3);
        return this;
    }

    public Y_Divider create() {
        Y_SideLine y_SideLine;
        Y_SideLine y_SideLine2 = new Y_SideLine(false, -10066330, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
        this.leftSideLine = this.leftSideLine != null ? this.leftSideLine : y_SideLine2;
        this.topSideLine = this.topSideLine != null ? this.topSideLine : y_SideLine2;
        this.rightSideLine = this.rightSideLine != null ? this.rightSideLine : y_SideLine2;
        if (this.bottomSideLine != null) {
            y_SideLine = this.bottomSideLine;
        }
        this.bottomSideLine = y_SideLine;
        return new Y_Divider(this.leftSideLine, this.topSideLine, this.rightSideLine, this.bottomSideLine);
    }
}
