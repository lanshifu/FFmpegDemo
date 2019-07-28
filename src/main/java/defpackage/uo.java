package defpackage;

import android.widget.TextView;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.c;
import com.zzhoujay.richtext.exceptions.ImageDecodeException;

/* compiled from: Base64ImageLoader */
/* renamed from: uo */
class uo extends um<byte[]> implements Runnable {
    uo(ImageHolder imageHolder, c cVar, TextView textView, uc ucVar, tu tuVar) {
        super(imageHolder, cVar, textView, ucVar, tuVar, uz.a);
    }

    public void run() {
        try {
            a((Object) ue.b(this.a.d()));
        } catch (Exception e) {
            a((Exception) new ImageDecodeException(e));
        } catch (OutOfMemoryError e2) {
            a((Exception) new ImageDecodeException(e2));
        }
    }
}
