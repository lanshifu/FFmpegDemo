package defpackage;

import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.Spanned;
import android.util.Log;
import java.lang.reflect.Method;

/* compiled from: Html2SpannedParser */
/* renamed from: vb */
public class vb implements ve {
    private static final Method a;
    private TagHandler b;

    static {
        Method method;
        try {
            method = Class.forName("tc").getMethod("fromHtml", new Class[]{String.class, ImageGetter.class, TagHandler.class});
        } catch (Exception unused) {
            method = null;
        }
        a = method;
    }

    public vb(TagHandler tagHandler) {
        this.b = tagHandler;
    }

    public Spanned a(String str) {
        if (a != null) {
            try {
                return (Spanned) a.invoke(null, new Object[]{str, null, this.b});
            } catch (Exception e) {
                Log.d("Html2SpannedParser", "Z_FROM_HTML_METHOD invoke failure", e);
            }
        }
        return Html.fromHtml(str, null, this.b);
    }
}
