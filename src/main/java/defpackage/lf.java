package defpackage;

import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import java.util.Map;

/* compiled from: TtmlRenderUtil */
/* renamed from: lf */
final class lf {
    public static lg a(lg lgVar, String[] strArr, Map<String, lg> map) {
        if (lgVar == null && strArr == null) {
            return null;
        }
        int i = 0;
        if (lgVar == null && strArr.length == 1) {
            return (lg) map.get(strArr[0]);
        }
        int length;
        if (lgVar == null && strArr.length > 1) {
            lgVar = new lg();
            length = strArr.length;
            while (i < length) {
                lgVar.a((lg) map.get(strArr[i]));
                i++;
            }
            return lgVar;
        } else if (lgVar != null && strArr != null && strArr.length == 1) {
            return lgVar.a((lg) map.get(strArr[0]));
        } else {
            if (lgVar == null || strArr == null || strArr.length <= 1) {
                return lgVar;
            }
            length = strArr.length;
            while (i < length) {
                lgVar.a((lg) map.get(strArr[i]));
                i++;
            }
            return lgVar;
        }
    }

    public static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2, lg lgVar) {
        if (lgVar.a() != -1) {
            spannableStringBuilder.setSpan(new StyleSpan(lgVar.a()), i, i2, 33);
        }
        if (lgVar.b()) {
            spannableStringBuilder.setSpan(new StrikethroughSpan(), i, i2, 33);
        }
        if (lgVar.c()) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }
        if (lgVar.f()) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(lgVar.e()), i, i2, 33);
        }
        if (lgVar.h()) {
            spannableStringBuilder.setSpan(new BackgroundColorSpan(lgVar.g()), i, i2, 33);
        }
        if (lgVar.d() != null) {
            spannableStringBuilder.setSpan(new TypefaceSpan(lgVar.d()), i, i2, 33);
        }
        if (lgVar.j() != null) {
            spannableStringBuilder.setSpan(new Standard(lgVar.j()), i, i2, 33);
        }
        switch (lgVar.k()) {
            case 1:
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan((int) lgVar.l(), true), i, i2, 33);
                return;
            case 2:
                spannableStringBuilder.setSpan(new RelativeSizeSpan(lgVar.l()), i, i2, 33);
                return;
            case 3:
                spannableStringBuilder.setSpan(new RelativeSizeSpan(lgVar.l() / 100.0f), i, i2, 33);
                return;
            default:
                return;
        }
    }

    static void a(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length() - 1;
        while (length >= 0 && spannableStringBuilder.charAt(length) == ' ') {
            length--;
        }
        if (length >= 0 && spannableStringBuilder.charAt(length) != 10) {
            spannableStringBuilder.append(10);
        }
    }

    static String a(String str) {
        return str.replaceAll("\r\n", "\n").replaceAll(" *\n *", "\n").replaceAll("\n", " ").replaceAll("[ \t\\x0B\f\r]+", " ");
    }
}
