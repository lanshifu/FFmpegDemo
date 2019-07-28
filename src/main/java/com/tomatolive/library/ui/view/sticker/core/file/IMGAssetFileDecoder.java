package com.tomatolive.library.ui.view.sticker.core.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.TextUtils;
import java.io.IOException;

public class IMGAssetFileDecoder extends IMGDecoder {
    private Context mContext;

    public IMGAssetFileDecoder(Context context, Uri uri) {
        super(uri);
        this.mContext = context;
    }

    public Bitmap decode(Options options) {
        Uri uri = getUri();
        if (uri == null) {
            return null;
        }
        String path = uri.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        try {
            return BitmapFactory.decodeStream(this.mContext.getAssets().open(path.substring(1)), null, options);
        } catch (IOException unused) {
            return null;
        }
    }
}
