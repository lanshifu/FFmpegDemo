package defpackage;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import com.zzhoujay.richtext.a;
import com.zzhoujay.richtext.c;
import com.zzhoujay.richtext.spans.LongClickableURLSpan;
import com.zzhoujay.richtext.spans.b;
import java.util.ArrayList;

/* compiled from: CachedSpannedParser */
/* renamed from: va */
public class va {

    /* compiled from: CachedSpannedParser */
    /* renamed from: va$a */
    public static class a {
    }

    public int a(SpannableStringBuilder spannableStringBuilder, vc vcVar, c cVar) {
        boolean a = a(spannableStringBuilder);
        a(spannableStringBuilder, cVar, a);
        return a(spannableStringBuilder, vcVar, cVar, a);
    }

    private void a(SpannableStringBuilder spannableStringBuilder, c cVar, boolean z) {
        int i = 0;
        int length;
        if (z) {
            LongClickableURLSpan[] longClickableURLSpanArr = (LongClickableURLSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), LongClickableURLSpan.class);
            if (longClickableURLSpanArr != null && longClickableURLSpanArr.length > 0) {
                length = longClickableURLSpanArr.length;
                while (i < length) {
                    a(spannableStringBuilder, cVar, longClickableURLSpanArr[i]);
                    i++;
                }
            }
        } else if (cVar.m >= 0) {
            URLSpan[] uRLSpanArr = (URLSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), URLSpan.class);
            if (uRLSpanArr == null) {
                length = 0;
            } else {
                length = uRLSpanArr.length;
            }
            while (i < length) {
                a(spannableStringBuilder, cVar, uRLSpanArr[i]);
                i++;
            }
        } else {
            int i2;
            URLSpan[] uRLSpanArr2 = (URLSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), URLSpan.class);
            if (uRLSpanArr2 == null) {
                i2 = 0;
            } else {
                i2 = uRLSpanArr2.length;
            }
            while (i < i2) {
                spannableStringBuilder.removeSpan(uRLSpanArr2[i]);
                i++;
            }
        }
    }

    private void a(SpannableStringBuilder spannableStringBuilder, c cVar, URLSpan uRLSpan) {
        int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
        int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
        spannableStringBuilder.removeSpan(uRLSpan);
        a aVar = new a(uRLSpan.getURL());
        if (cVar.k != null) {
            cVar.k.a(aVar);
        }
        spannableStringBuilder.setSpan(new LongClickableURLSpan(aVar, cVar.o, cVar.q), spanStart, spanEnd, 33);
    }

    private int a(SpannableStringBuilder spannableStringBuilder, vc vcVar, c cVar, boolean z) {
        SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        vc vcVar2 = vcVar;
        c cVar2 = cVar;
        int i = 0;
        int spanStart;
        int spanEnd;
        tw twVar;
        if (z) {
            b[] bVarArr = (b[]) spannableStringBuilder2.getSpans(0, spannableStringBuilder.length(), b.class);
            if (bVarArr != null && bVarArr.length > 0) {
                for (Object obj : bVarArr) {
                    tx txVar;
                    spanStart = spannableStringBuilder2.getSpanStart(obj);
                    spanEnd = spannableStringBuilder2.getSpanEnd(obj);
                    spannableStringBuilder2.removeSpan(obj);
                    if (cVar2.m > 0) {
                        twVar = cVar2.n;
                        txVar = cVar2.p;
                    } else {
                        twVar = null;
                        txVar = null;
                    }
                    Drawable c = vcVar2.c(obj.getSource());
                    if (c == null) {
                        c = new ColorDrawable(0);
                    }
                    spannableStringBuilder2.setSpan(new b(c, obj, twVar, txVar), spanStart, spanEnd, 33);
                }
                return bVarArr.length;
            }
        } else if (!cVar2.l) {
            ImageSpan[] imageSpanArr = (ImageSpan[]) spannableStringBuilder2.getSpans(0, spannableStringBuilder.length(), ImageSpan.class);
            if (imageSpanArr != null && imageSpanArr.length > 0) {
                ArrayList arrayList = new ArrayList(imageSpanArr.length);
                int i2 = 0;
                while (i2 < imageSpanArr.length) {
                    tw twVar2;
                    tx txVar2;
                    ImageSpan imageSpan = imageSpanArr[i2];
                    String source = imageSpan.getSource();
                    arrayList.add(source);
                    int spanStart2 = spannableStringBuilder2.getSpanStart(imageSpan);
                    int spanEnd2 = spannableStringBuilder2.getSpanEnd(imageSpan);
                    ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spannableStringBuilder2.getSpans(spanStart2, spanEnd2, ClickableSpan.class);
                    if (!(clickableSpanArr == null || clickableSpanArr.length == 0)) {
                        for (Object removeSpan : clickableSpanArr) {
                            spannableStringBuilder2.removeSpan(removeSpan);
                        }
                    }
                    if (cVar2.m > 0) {
                        twVar2 = cVar2.n;
                        txVar2 = cVar2.p;
                    } else {
                        twVar2 = null;
                        txVar2 = null;
                    }
                    Drawable c2 = vcVar2.c(source);
                    if (c2 == null) {
                        c2 = new ColorDrawable(i);
                    }
                    b bVar = r8;
                    int i3 = spanEnd2;
                    twVar = twVar2;
                    int i4 = spanStart2;
                    b bVar2 = new b(c2, arrayList, i2, twVar, txVar2);
                    spannableStringBuilder2.removeSpan(imageSpan);
                    spannableStringBuilder2.setSpan(bVar, i4, i3, 33);
                    i2++;
                    i = 0;
                }
                return imageSpanArr.length;
            }
        }
        return 0;
    }

    private boolean a(SpannableStringBuilder spannableStringBuilder) {
        a[] aVarArr = (a[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), a.class);
        if (aVarArr == null || aVarArr.length <= 0) {
            return false;
        }
        return true;
    }
}
