package com.tomatolive.library.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.tomatolive.library.R;
import com.tomatolive.library.utils.i;
import com.youth.banner.loader.ImageLoader;

public class LiveBannerImageLoaderAdapter extends ImageLoader {
    public void displayImage(Context context, Object obj, ImageView imageView) {
        i.a(context, imageView, obj, R.drawable.fq_ic_placeholder_banner);
    }
}
