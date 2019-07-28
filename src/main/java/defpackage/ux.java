package defpackage;

import android.widget.TextView;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.c;
import com.zzhoujay.richtext.exceptions.BitmapInputStreamNullPointException;
import com.zzhoujay.richtext.exceptions.ImageDecodeException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: InputStreamImageLoader */
/* renamed from: ux */
class ux extends um<InputStream> implements Runnable {
    private InputStream b;

    ux(ImageHolder imageHolder, c cVar, TextView textView, uc ucVar, tu tuVar, InputStream inputStream) {
        super(imageHolder, cVar, textView, ucVar, tuVar, uz.c);
        this.b = inputStream;
    }

    public void run() {
        if (this.b == null) {
            a((Exception) new BitmapInputStreamNullPointException());
            return;
        }
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(this.b);
            a((Object) bufferedInputStream);
            bufferedInputStream.close();
            this.b.close();
        } catch (IOException e) {
            a((Exception) e);
        } catch (OutOfMemoryError e2) {
            a((Exception) new ImageDecodeException(e2));
        }
    }
}
