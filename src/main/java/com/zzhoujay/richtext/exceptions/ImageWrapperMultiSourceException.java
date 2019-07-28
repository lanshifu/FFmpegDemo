package com.zzhoujay.richtext.exceptions;

public class ImageWrapperMultiSourceException extends IllegalArgumentException {
    public ImageWrapperMultiSourceException() {
        super("GifDrawable和Bitmap有且只有一个为null");
    }

    public ImageWrapperMultiSourceException(Throwable th) {
        super("GifDrawable和Bitmap有且只有一个为null", th);
    }
}
