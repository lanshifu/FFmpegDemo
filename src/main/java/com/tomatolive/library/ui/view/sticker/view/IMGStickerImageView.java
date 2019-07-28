package com.tomatolive.library.ui.view.sticker.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.tomatolive.library.R;

public class IMGStickerImageView extends IMGStickerView {
    private ImageView mImageView;

    public IMGStickerImageView(Context context) {
        super(context);
    }

    public IMGStickerImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public IMGStickerImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public View onCreateContentView(Context context) {
        this.mImageView = new ImageView(context);
        this.mImageView.setImageResource(R.drawable.fq_ic_home_top_avatar_def);
        return this.mImageView;
    }
}
