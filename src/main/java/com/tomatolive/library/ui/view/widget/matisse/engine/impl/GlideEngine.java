package com.tomatolive.library.ui.view.widget.matisse.engine.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Priority;
import com.tomatolive.library.ui.view.widget.matisse.GlideApp;
import com.tomatolive.library.ui.view.widget.matisse.engine.ImageEngine;

public class GlideEngine implements ImageEngine {
    public boolean supportAnimatedGif() {
        return true;
    }

    public void loadThumbnail(Context context, int i, Drawable drawable, ImageView imageView, Uri uri) {
        GlideApp.with(context).asBitmap().load(uri).placeholder(drawable).override(i, i).centerCrop().into(imageView);
    }

    public void loadGifThumbnail(Context context, int i, Drawable drawable, ImageView imageView, Uri uri) {
        GlideApp.with(context).asBitmap().load(uri).placeholder(drawable).override(i, i).centerCrop().into(imageView);
    }

    public void loadImage(Context context, int i, int i2, ImageView imageView, Uri uri) {
        GlideApp.with(context).asBitmap().load(uri).override(i, i2).priority(Priority.HIGH).fitCenter().into(imageView);
    }

    public void loadGifImage(Context context, int i, int i2, ImageView imageView, Uri uri) {
        GlideApp.with(context).asGif().load(uri).override(i, i2).priority(Priority.HIGH).into(imageView);
    }
}
