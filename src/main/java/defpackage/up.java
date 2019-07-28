package defpackage;

import android.widget.TextView;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.c;
import com.zzhoujay.richtext.exceptions.ImageDecodeException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: CallbackImageLoader */
/* renamed from: up */
class up extends um<InputStream> {
    up(ImageHolder imageHolder, c cVar, TextView textView, uc ucVar, tu tuVar) {
        super(imageHolder, cVar, textView, ucVar, tuVar, uz.c);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str, Exception exception) {
        if (exception != null) {
            a(exception);
            return;
        }
        try {
            InputStream c = tm.a().c(str);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(c);
            a((Object) bufferedInputStream);
            bufferedInputStream.close();
            c.close();
        } catch (IOException e) {
            a((Exception) e);
        } catch (OutOfMemoryError e2) {
            a((Exception) new ImageDecodeException(e2));
        }
    }
}
