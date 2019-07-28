package com.zzhoujay.richtext.exceptions;

public class BitmapInputStreamNullPointException extends RuntimeException {
    public BitmapInputStreamNullPointException() {
        super("Bitmap InputStream cannot be null");
    }
}
