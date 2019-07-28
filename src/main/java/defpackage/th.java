package defpackage;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;
import com.zzhoujay.markdown.style.EmailSpan;
import com.zzhoujay.markdown.style.FontSpan;
import com.zzhoujay.markdown.style.LinkSpan;
import com.zzhoujay.markdown.style.MarkDownBulletSpan;
import com.zzhoujay.markdown.style.MarkDownQuoteSpan;
import com.zzhoujay.markdown.style.QuotaBulletSpan;
import com.zzhoujay.markdown.style.a;
import com.zzhoujay.markdown.style.b;
import com.zzhoujay.markdown.style.d;
import java.lang.ref.WeakReference;

/* compiled from: StyleBuilderImpl */
/* renamed from: th */
public class th implements tg {
    private static final int a = Color.parseColor("#333333");
    private static final int b = Color.parseColor("#777777");
    private static final int c = Color.parseColor("#DDDDDD");
    private static final int d = Color.parseColor("#F0F0F0");
    private static final int e = Color.parseColor("#4078C0");
    private static final int f = Color.parseColor("#eeeeee");
    private WeakReference<TextView> g;
    private ImageGetter h;

    public th(TextView textView, ImageGetter imageGetter) {
        this.g = new WeakReference(textView);
        this.h = imageGetter;
    }

    public SpannableStringBuilder a(CharSequence charSequence) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new FontSpan(1.0f, 1, a), 0, charSequence.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder b(CharSequence charSequence) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new FontSpan(1.0f, 2, a), 0, charSequence.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder c(CharSequence charSequence) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new FontSpan(1.0f, 3, a), 0, charSequence.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder d(CharSequence charSequence) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        valueOf.setSpan(new ForegroundColorSpan(a), 0, charSequence.length(), 33);
        valueOf.setSpan(strikethroughSpan, 0, charSequence.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder e(CharSequence charSequence) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new EmailSpan(charSequence.toString(), e), 0, charSequence.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder f(CharSequence charSequence) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new b(d), 0, charSequence.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder g(CharSequence charSequence) {
        return a(charSequence, 2.25f);
    }

    public SpannableStringBuilder h(CharSequence charSequence) {
        return a(charSequence, 1.75f);
    }

    public SpannableStringBuilder i(CharSequence charSequence) {
        return a(charSequence, 1.5f, a);
    }

    public SpannableStringBuilder j(CharSequence charSequence) {
        return a(charSequence, 1.25f, a);
    }

    public SpannableStringBuilder k(CharSequence charSequence) {
        return a(charSequence, 1.0f, a);
    }

    public SpannableStringBuilder l(CharSequence charSequence) {
        return a(charSequence, 1.0f, b);
    }

    public SpannableStringBuilder m(CharSequence charSequence) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new MarkDownQuoteSpan(c), 0, valueOf.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder a(CharSequence charSequence, int i) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new MarkDownBulletSpan(i, a, 0), 0, valueOf.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder a(CharSequence charSequence, int i, int i2) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new MarkDownBulletSpan(i, a, i2, (TextView) this.g.get()), 0, valueOf.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder b(CharSequence charSequence, int i, int i2) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new QuotaBulletSpan(i, i2, c, a, 0, (TextView) this.g.get()), 0, valueOf.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder a(CharSequence charSequence, int i, int i2, int i3) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new QuotaBulletSpan(i, i2, c, a, i3, (TextView) this.g.get()), 0, valueOf.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder a(CharSequence... charSequenceArr) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf("$");
        valueOf.setSpan(new a(b(), d, charSequenceArr), 0, valueOf.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder a(String str) {
        return a((CharSequence[]) str.split("\n"));
    }

    public SpannableStringBuilder a(CharSequence charSequence, String str, String str2) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new LinkSpan(str, e), 0, charSequence.length(), 33);
        return valueOf;
    }

    public SpannableStringBuilder b(CharSequence charSequence, String str, String str2) {
        if (charSequence == null || charSequence.length() == 0) {
            charSequence = "$";
        }
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        Drawable drawable = null;
        if (this.h != null) {
            drawable = this.h.getDrawable(str);
        }
        if (drawable == null) {
            drawable = new ColorDrawable(0);
        }
        valueOf.setSpan(new ImageSpan(drawable, str), 0, valueOf.length(), 33);
        return valueOf;
    }

    /* Access modifiers changed, original: protected */
    public SpannableStringBuilder a(CharSequence charSequence, float f, int i) {
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(charSequence);
        valueOf.setSpan(new FontSpan(f, 1, i), 0, valueOf.length(), 33);
        return valueOf;
    }

    private SpannableStringBuilder a(CharSequence charSequence, float f) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence);
        spannableStringBuilder.setSpan(new FontSpan(f, 1, a), 0, spannableStringBuilder.length(), 33);
        d dVar = new d(new ColorDrawable(f), b(), 5);
        spannableStringBuilder.append(10);
        int length = (charSequence.length() + 1) + 0;
        spannableStringBuilder.append("$");
        spannableStringBuilder.setSpan(dVar, length, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    private int b() {
        TextView textView = (TextView) this.g.get();
        return textView != null ? (textView.getWidth() - textView.getPaddingRight()) - textView.getPaddingLeft() : 0;
    }

    public SpannableStringBuilder a() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("$");
        spannableStringBuilder.setSpan(new d(new ColorDrawable(f), b(), 10), 0, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }
}
