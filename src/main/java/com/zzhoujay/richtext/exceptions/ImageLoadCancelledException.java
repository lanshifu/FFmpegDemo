package com.zzhoujay.richtext.exceptions;

public class ImageLoadCancelledException extends Exception {
    public ImageLoadCancelledException() {
        super("Image load has been cancelled");
    }
}
