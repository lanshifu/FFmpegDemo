package defpackage;

import android.text.Spanned;
import android.widget.TextView;
import com.zzhoujay.markdown.a;

/* compiled from: Markdown2SpannedParser */
/* renamed from: vd */
public class vd implements ve {
    private TextView a;

    public vd(TextView textView) {
        this.a = textView;
    }

    public Spanned a(String str) {
        return a.a(str, null, this.a);
    }
}
