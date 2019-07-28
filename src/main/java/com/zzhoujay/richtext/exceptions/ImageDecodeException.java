package com.zzhoujay.richtext.exceptions;

import android.annotation.TargetApi;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ImageDecodeException extends Exception {
    private OutOfMemoryError error;

    public ImageDecodeException() {
        super("Image Decode Failure");
    }

    public ImageDecodeException(Throwable th) {
        super("Image Decode Failure", th);
        if (th instanceof OutOfMemoryError) {
            this.error = (OutOfMemoryError) th;
        }
    }

    @TargetApi(24)
    public ImageDecodeException(Throwable th, boolean z, boolean z2) {
        super("Image Decode Failure", th, z, z2);
        if (th instanceof OutOfMemoryError) {
            this.error = (OutOfMemoryError) th;
        }
    }

    public void printStackTrace() {
        if (this.error != null) {
            this.error.printStackTrace();
        } else {
            super.printStackTrace();
        }
    }

    public void printStackTrace(PrintStream printStream) {
        if (this.error != null) {
            this.error.printStackTrace(printStream);
        } else {
            super.printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        if (this.error != null) {
            this.error.printStackTrace(printWriter);
        } else {
            super.printStackTrace(printWriter);
        }
    }
}
