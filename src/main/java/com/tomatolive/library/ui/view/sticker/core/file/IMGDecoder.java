package com.tomatolive.library.ui.view.sticker.core.file;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;

public abstract class IMGDecoder {
    private Uri uri;

    public abstract Bitmap decode(Options options);

    public IMGDecoder(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return this.uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Bitmap decode() {
        return decode(null);
    }
}
