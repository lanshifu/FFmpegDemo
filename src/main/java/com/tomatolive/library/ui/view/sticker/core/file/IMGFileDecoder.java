package com.tomatolive.library.ui.view.sticker.core.file;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;

public class IMGFileDecoder extends IMGDecoder {
    public IMGFileDecoder(Uri uri) {
        super(uri);
    }

    public Bitmap decode(Options options) {
        Uri uri = getUri();
        if (uri == null) {
            return null;
        }
        String path = uri.getPath();
        if (!TextUtils.isEmpty(path) && new File(path).exists()) {
            return BitmapFactory.decodeFile(path, options);
        }
        return null;
    }
}
