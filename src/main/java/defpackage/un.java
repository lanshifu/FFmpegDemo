package defpackage;

import android.widget.TextView;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.c;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: AssetsImageLoader */
/* renamed from: un */
class un extends ux implements Runnable {
    un(ImageHolder imageHolder, c cVar, TextView textView, uc ucVar, tu tuVar) {
        super(imageHolder, cVar, textView, ucVar, tuVar, un.a(imageHolder, textView));
    }

    private static InputStream a(ImageHolder imageHolder, TextView textView) {
        try {
            return textView.getContext().getAssets().open(un.a(imageHolder.d()));
        } catch (IOException e) {
            ug.a(e);
            return null;
        }
    }

    private static String a(String str) {
        return (str == null || !str.startsWith("file:///android_asset/")) ? null : str.replace("file:///android_asset/", "");
    }
}
