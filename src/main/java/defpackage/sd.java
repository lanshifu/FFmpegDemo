package defpackage;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.LocaleList;
import com.blankj.utilcode.util.k;
import java.util.Locale;
import org.greenrobot.eventbus.c;

/* compiled from: MultiLanguageUtil */
/* renamed from: sd */
public class sd {

    /* compiled from: MultiLanguageUtil */
    /* renamed from: sd$a */
    private static class a {
        private static final sd a = new sd();
    }

    private sd() {
    }

    public static sd a() {
        return a.a;
    }

    public void a(Context context) {
        Locale c = c();
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(c);
        Resources resources = context.getResources();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private Locale c() {
        int c = k.a().c("MultiLanguageSave", 0);
        if (c == 0) {
            return b();
        }
        if (c == 1) {
            return Locale.ENGLISH;
        }
        if (c == 2) {
            return Locale.SIMPLIFIED_CHINESE;
        }
        if (c == 3) {
            return Locale.TAIWAN;
        }
        if (c == 5) {
            return Locale.JAPAN;
        }
        if (c == 4) {
            return Locale.KOREA;
        }
        a(b());
        return Locale.SIMPLIFIED_CHINESE;
    }

    private String a(Locale locale) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(locale.getLanguage());
        stringBuilder.append("_");
        stringBuilder.append(locale.getCountry());
        return stringBuilder.toString();
    }

    public Locale b() {
        if (VERSION.SDK_INT < 24) {
            return Locale.getDefault();
        }
        LocaleList localeList = LocaleList.getDefault();
        if (k.a().c("MultiLanguageSave", 0) == 0 || localeList.size() <= 1) {
            return localeList.get(0);
        }
        return localeList.get(1);
    }

    public void a(Context context, int i) {
        k.a().b("MultiLanguageSave", i);
        a(context);
        c.a().c(new se(i));
    }

    public Context b(Context context) {
        if (VERSION.SDK_INT >= 24) {
            return sd.c(context);
        }
        a(context);
        return context;
    }

    @TargetApi(24)
    private static Context c(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(sd.a().c());
        return context.createConfigurationContext(configuration);
    }
}
