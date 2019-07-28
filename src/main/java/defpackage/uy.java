package defpackage;

import android.widget.TextView;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.c;
import com.zzhoujay.richtext.exceptions.ImageDecodeException;

/* compiled from: LocalFileImageLoader */
/* renamed from: uy */
class uy extends um<String> implements Runnable {
    uy(ImageHolder imageHolder, c cVar, TextView textView, uc ucVar, tu tuVar) {
        super(imageHolder, cVar, textView, ucVar, tuVar, uz.b);
    }

    public void run() {
        try {
            a((Object) this.a.d());
        } catch (Exception e) {
            a((Exception) new ImageDecodeException(e));
        } catch (OutOfMemoryError e2) {
            a((Exception) new ImageDecodeException(e2));
        }
    }
}
