package defpackage;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.yalantis.ucrop.view.CropImageView;
import java.nio.charset.Charset;
import java.util.List;

/* compiled from: Tx3gDecoder */
/* renamed from: li */
public final class li extends c {
    private static final int a = z.g("styl");
    private static final int b = z.g("tbox");
    private final n c = new n();
    private boolean d;
    private int e;
    private int f;
    private String g;
    private float h;
    private int i;

    public li(List<byte[]> list) {
        super("Tx3gDecoder");
        a((List) list);
    }

    private void a(List<byte[]> list) {
        boolean z = false;
        if (list != null && list.size() == 1 && (((byte[]) list.get(0)).length == 48 || ((byte[]) list.get(0)).length == 53)) {
            byte[] bArr = (byte[]) list.get(0);
            this.e = bArr[24];
            this.f = ((((bArr[26] & 255) << 24) | ((bArr[27] & 255) << 16)) | ((bArr[28] & 255) << 8)) | (bArr[29] & 255);
            this.g = "Serif".equals(z.a(bArr, 43, bArr.length - 43)) ? "serif" : "sans-serif";
            this.i = bArr[25] * 20;
            if ((bArr[0] & 32) != 0) {
                z = true;
            }
            this.d = z;
            if (this.d) {
                this.h = ((float) ((bArr[11] & 255) | ((bArr[10] & 255) << 8))) / ((float) this.i);
                this.h = z.a(this.h, CropImageView.DEFAULT_ASPECT_RATIO, 0.95f);
                return;
            }
            this.h = 0.85f;
            return;
        }
        this.e = 0;
        this.f = -1;
        this.g = "sans-serif";
        this.d = false;
        this.h = 0.85f;
    }

    /* Access modifiers changed, original: protected */
    public e a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.c.a(bArr, i);
        String a = li.a(this.c);
        if (a.isEmpty()) {
            return lj.a;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(a);
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        li.a(spannableStringBuilder2, this.e, 0, 0, spannableStringBuilder.length(), 16711680);
        li.b(spannableStringBuilder2, this.f, -1, 0, spannableStringBuilder.length(), 16711680);
        li.a(spannableStringBuilder2, this.g, "sans-serif", 0, spannableStringBuilder.length(), 16711680);
        float f = this.h;
        while (this.c.b() >= 8) {
            int d = this.c.d();
            int o = this.c.o();
            int o2 = this.c.o();
            boolean z2 = false;
            boolean z3 = true;
            if (o2 == a) {
                if (this.c.b() < 2) {
                    z3 = false;
                }
                li.a(z3);
                o2 = this.c.h();
                int i2;
                while (i2 < o2) {
                    a(this.c, spannableStringBuilder);
                    i2++;
                }
            } else if (o2 == b && this.d) {
                if (this.c.b() >= 2) {
                    z2 = true;
                }
                li.a(z2);
                f = z.a(((float) this.c.h()) / ((float) this.i), CropImageView.DEFAULT_ASPECT_RATIO, 0.95f);
            }
            this.c.c(d + o);
        }
        return new lj(new b(spannableStringBuilder, null, f, 0, 0, Float.MIN_VALUE, CheckView.UNCHECKED, Float.MIN_VALUE));
    }

    private static String a(n nVar) throws SubtitleDecoderException {
        li.a(nVar.b() >= 2);
        int h = nVar.h();
        if (h == 0) {
            return "";
        }
        if (nVar.b() >= 2) {
            char f = nVar.f();
            if (f == 65279 || f == 65534) {
                return nVar.a(h, Charset.forName("UTF-16"));
            }
        }
        return nVar.a(h, Charset.forName("UTF-8"));
    }

    private void a(n nVar, SpannableStringBuilder spannableStringBuilder) throws SubtitleDecoderException {
        li.a(nVar.b() >= 12);
        int h = nVar.h();
        int h2 = nVar.h();
        nVar.d(2);
        int g = nVar.g();
        nVar.d(1);
        int o = nVar.o();
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        int i = h;
        int i2 = h2;
        li.a(spannableStringBuilder2, g, this.e, i, i2, 0);
        li.b(spannableStringBuilder2, o, this.f, i, i2, 0);
    }

    private static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            i2 = i5 | 33;
            int i6 = 1;
            Object obj = (i & 1) != 0 ? 1 : null;
            Object obj2 = (i & 2) != 0 ? 1 : null;
            if (obj != null) {
                if (obj2 != null) {
                    spannableStringBuilder.setSpan(new StyleSpan(3), i3, i4, i2);
                } else {
                    spannableStringBuilder.setSpan(new StyleSpan(1), i3, i4, i2);
                }
            } else if (obj2 != null) {
                spannableStringBuilder.setSpan(new StyleSpan(2), i3, i4, i2);
            }
            if ((i & 4) == 0) {
                i6 = 0;
            }
            if (i6 != 0) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i3, i4, i2);
            }
            if (i6 == 0 && obj == null && obj2 == null) {
                spannableStringBuilder.setSpan(new StyleSpan(0), i3, i4, i2);
            }
        }
    }

    private static void b(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3, int i4, int i5) {
        if (i != i2) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i >>> 8) | ((i & 255) << 24)), i3, i4, i5 | 33);
        }
    }

    private static void a(SpannableStringBuilder spannableStringBuilder, String str, String str2, int i, int i2, int i3) {
        if (str != str2) {
            spannableStringBuilder.setSpan(new TypefaceSpan(str), i, i2, i3 | 33);
        }
    }

    private static void a(boolean z) throws SubtitleDecoderException {
        if (!z) {
            throw new SubtitleDecoderException("Unexpected subtitle format.");
        }
    }
}
