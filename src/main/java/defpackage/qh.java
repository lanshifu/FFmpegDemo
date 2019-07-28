package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.one.tomato.utils.c;
import java.util.regex.Pattern;

/* compiled from: LinkSpannableUtils */
/* renamed from: qh */
public class qh {
    public static qh a;
    public static final Pattern e = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
    public static final Pattern f = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?(\\([0-9]+\\)[\\- \\.]*)?([0-9][0-9\\- \\.][0-9\\- \\.]+[0-9])");
    public final String b = "(?:(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])|(?:biz|b[abdefghijmnorstvwyz])|(?:cat|com|coop|c[acdfghiklmnoruvxyz])|d[ejkmoz]|(?:edu|e[cegrstu])|f[ijkmor]|(?:gov|g[abdefghilmnpqrstuwy])|h[kmnrtu]|(?:info|int|i[delmnoqrst])|(?:jobs|j[emop])|k[eghimnprwyz]|l[abcikrstuvy]|(?:mil|mobi|museum|m[acdeghklmnopqrstuvwxyz])|(?:name|net|n[acefgilopruz])|(?:org|om)|(?:pro|p[aefghklmnrstwy])|qa|r[eosuw]|s[abcdeghijklmnortuvyz]|(?:tel|travel|t[cdfghjklmnoprtvwz])|u[agksyz]|v[aceginu]|w[fs]|(?:δοκιμή|испытание|рф|срб|טעסט|آزمایشی|إختبار|الاردن|الجزائر|السعودية|المغرب|امارات|بھارت|تونس|سورية|فلسطين|قطر|مصر|परीक्षा|भारत|ভারত|ਭਾਰਤ|ભારત|இந்தியா|இலங்கை|சிங்கப்பூர்|பரிட்சை|భారత్|ලංකා|ไทย|テスト|中国|中國|台湾|台灣|新加坡|测试|測試|香港|테스트|한국|xn\\-\\-0zwm56d|xn\\-\\-11b5bs3a9aj6g|xn\\-\\-3e0b707e|xn\\-\\-45brj9c|xn\\-\\-80akhbyknj4f|xn\\-\\-90a3ac|xn\\-\\-9t4b11yi5a|xn\\-\\-clchc0ea0b2g2a9gcd|xn\\-\\-deba0ad|xn\\-\\-fiqs8s|xn\\-\\-fiqz9s|xn\\-\\-fpcrj9c3d|xn\\-\\-fzc2c9e2c|xn\\-\\-g6w251d|xn\\-\\-gecrj9c|xn\\-\\-h2brj9c|xn\\-\\-hgbk6aj7f53bba|xn\\-\\-hlcj6aya9esc7a|xn\\-\\-j6w193g|xn\\-\\-jxalpdlp|xn\\-\\-kgbechtv|xn\\-\\-kprw13d|xn\\-\\-kpry57d|xn\\-\\-lgbbat1ad8j|xn\\-\\-mgbaam7a8h|xn\\-\\-mgbayh7gpa|xn\\-\\-mgbbh1a71e|xn\\-\\-mgbc0a9azcg|xn\\-\\-mgberp4a5d4ar|xn\\-\\-o3cw4h|xn\\-\\-ogbpf8fl|xn\\-\\-p1ai|xn\\-\\-pgbs0dh|xn\\-\\-s9brj9c|xn\\-\\-wgbh1c|xn\\-\\-wgbl6a|xn\\-\\-xkc2al3hye2a|xn\\-\\-xkc2dl3a5ee0h|xn\\-\\-yfro4i67o|xn\\-\\-ygbi2ammx|xn\\-\\-zckzah|xxx)|y[et]|z[amw]))";
    public final String c = "a-zA-Z0-9 -퟿豈-﷏ﷰ-￯";
    public final Pattern d = Pattern.compile("((?:(http|https|Http|Https|rtsp|Rtsp):\\/\\/(?:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,64}(?:\\:(?:[a-zA-Z0-9\\$\\-\\_\\.\\+\\!\\*\\'\\(\\)\\,\\;\\?\\&\\=]|(?:\\%[a-fA-F0-9]{2})){1,25})?\\@)?)?((?:(?:[a-zA-Z0-9 -퟿豈-﷏ﷰ-￯][a-zA-Z0-9 -퟿豈-﷏ﷰ-￯\\-]{0,64}\\.)+(?:(?:aero|arpa|asia|a[cdefgilmnoqrstuwxz])|(?:biz|b[abdefghijmnorstvwyz])|(?:cat|com|coop|c[acdfghiklmnoruvxyz])|d[ejkmoz]|(?:edu|e[cegrstu])|f[ijkmor]|(?:gov|g[abdefghilmnpqrstuwy])|h[kmnrtu]|(?:info|int|i[delmnoqrst])|(?:jobs|j[emop])|k[eghimnprwyz]|l[abcikrstuvy]|(?:mil|mobi|museum|m[acdeghklmnopqrstuvwxyz])|(?:name|net|n[acefgilopruz])|(?:org|om)|(?:pro|p[aefghklmnrstwy])|qa|r[eosuw]|s[abcdeghijklmnortuvyz]|(?:tel|travel|t[cdfghjklmnoprtvwz])|u[agksyz]|v[aceginu]|w[fs]|(?:δοκιμή|испытание|рф|срб|טעסט|آزمایشی|إختبار|الاردن|الجزائر|السعودية|المغرب|امارات|بھارت|تونس|سورية|فلسطين|قطر|مصر|परीक्षा|भारत|ভারত|ਭਾਰਤ|ભારત|இந்தியா|இலங்கை|சிங்கப்பூர்|பரிட்சை|భారత్|ලංකා|ไทย|テスト|中国|中國|台湾|台灣|新加坡|测试|測試|香港|테스트|한국|xn\\-\\-0zwm56d|xn\\-\\-11b5bs3a9aj6g|xn\\-\\-3e0b707e|xn\\-\\-45brj9c|xn\\-\\-80akhbyknj4f|xn\\-\\-90a3ac|xn\\-\\-9t4b11yi5a|xn\\-\\-clchc0ea0b2g2a9gcd|xn\\-\\-deba0ad|xn\\-\\-fiqs8s|xn\\-\\-fiqz9s|xn\\-\\-fpcrj9c3d|xn\\-\\-fzc2c9e2c|xn\\-\\-g6w251d|xn\\-\\-gecrj9c|xn\\-\\-h2brj9c|xn\\-\\-hgbk6aj7f53bba|xn\\-\\-hlcj6aya9esc7a|xn\\-\\-j6w193g|xn\\-\\-jxalpdlp|xn\\-\\-kgbechtv|xn\\-\\-kprw13d|xn\\-\\-kpry57d|xn\\-\\-lgbbat1ad8j|xn\\-\\-mgbaam7a8h|xn\\-\\-mgbayh7gpa|xn\\-\\-mgbbh1a71e|xn\\-\\-mgbc0a9azcg|xn\\-\\-mgberp4a5d4ar|xn\\-\\-o3cw4h|xn\\-\\-ogbpf8fl|xn\\-\\-p1ai|xn\\-\\-pgbs0dh|xn\\-\\-s9brj9c|xn\\-\\-wgbh1c|xn\\-\\-wgbl6a|xn\\-\\-xkc2al3hye2a|xn\\-\\-xkc2dl3a5ee0h|xn\\-\\-yfro4i67o|xn\\-\\-ygbi2ammx|xn\\-\\-zckzah|xxx)|y[et]|z[amw]))|(?:(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(?:25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[0-9])))(?:\\:\\d{1,5})?)(\\/(?:(?:[a-zA-Z0-9 -퟿豈-﷏ﷰ-￯\\;\\/\\?\\:\\@\\&\\=\\#\\~\\-\\.\\+\\!\\*\\'\\(\\)\\,\\_])|(?:\\%[a-fA-F0-9]{2}))*)?(?:\\b|$)");
    private Context g;
    private TextView h;
    private SpannableStringBuilder i;
    private int j;

    /* compiled from: LinkSpannableUtils */
    /* renamed from: qh$a */
    private class a extends ClickableSpan {
        private String b;

        a(String str) {
            this.b = str;
        }

        public void onClick(View view) {
            if (qh.this.j == 1) {
                c.b(this.b, c.a(2131755065));
            } else if (qh.e.matcher(this.b).find()) {
                qh.this.b(this.b);
            } else if (qh.this.d.matcher(this.b).find()) {
                c.b(this.b);
            } else if (qh.f.matcher(this.b).find()) {
                qh.this.a(this.b);
            } else if (!this.b.contains(".")) {
            } else {
                if (this.b.startsWith("http")) {
                    c.b(this.b);
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("http://");
                stringBuilder.append(this.b);
                c.b(stringBuilder.toString());
            }
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(true);
            textPaint.setColor(Color.parseColor("#149eff"));
        }
    }

    private qh() {
    }

    public static qh a() {
        if (a == null) {
            a = new qh();
        }
        return a;
    }

    public void a(Context context, TextView textView, int i) {
        this.g = context;
        this.h = textView;
        this.j = i;
        b();
    }

    private void b() {
        Linkify.addLinks(this.h, 15);
        Linkify.addLinks(this.h, f, null);
        if (this.h.getText() instanceof Spannable) {
            Spannable spannable = (Spannable) this.h.getText();
            int i = 0;
            URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, this.h.getText().length(), URLSpan.class);
            this.i = new SpannableStringBuilder(spannable);
            this.i.clearSpans();
            int length = uRLSpanArr.length;
            while (i < length) {
                URLSpan uRLSpan = uRLSpanArr[i];
                String url = uRLSpan.getURL();
                a aVar = new a(url);
                if (url != null && url.length() > 0) {
                    try {
                        this.i.setSpan(aVar, spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 34);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                i++;
            }
            this.h.setText(this.i);
        }
    }

    private void a(String str) {
        if (str != null && str.length() > 0) {
            a(str, this.g);
        }
    }

    private void b(String str) {
        String[] strArr = new String[]{str};
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("plain/text");
        intent.putExtra("android.intent.extra.EMAIL", strArr);
        this.g.startActivity(Intent.createChooser(intent, ""));
    }

    private void a(String str, Context context) {
        if (str == null || str.length() == 0) {
            Toast.makeText(context, "电话号码为空", 0).show();
            return;
        }
        String toLowerCase = str.toLowerCase();
        if (!toLowerCase.startsWith("tel:")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("tel:");
            stringBuilder.append(str);
            toLowerCase = stringBuilder.toString();
        }
        if (ActivityCompat.checkSelfPermission(context, "android.permission.CALL_PHONE") != 0) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{"android.permission.CALL_PHONE"}, 110);
        } else {
            qh.a(context, toLowerCase);
        }
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse(str));
        if (ActivityCompat.checkSelfPermission(context, "android.permission.CALL_PHONE") == 0) {
            context.startActivity(intent);
        }
    }
}
