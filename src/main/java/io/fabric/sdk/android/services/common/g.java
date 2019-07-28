package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import io.fabric.sdk.android.c;

/* compiled from: ApiKey */
public class g {
    /* Access modifiers changed, original: protected */
    public String a() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }

    public String a(Context context) {
        CharSequence c = c(context);
        if (TextUtils.isEmpty(c)) {
            c = d(context);
        }
        if (TextUtils.isEmpty(c)) {
            c = b(context);
        }
        if (TextUtils.isEmpty(c)) {
            e(context);
        }
        return c;
    }

    /* Access modifiers changed, original: protected */
    public String b(Context context) {
        return new p().a(context);
    }

    /* Access modifiers changed, original: protected */
    public String c(Context context) {
        Object e;
        StringBuilder stringBuilder;
        String str = null;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return null;
            }
            String string = bundle.getString("io.fabric.ApiKey");
            try {
                if ("@string/twitter_consumer_secret".equals(string)) {
                    c.g().a("Fabric", "Ignoring bad default value for Fabric ApiKey set by FirebaseUI-Auth");
                } else {
                    str = string;
                }
                if (str != null) {
                    return str;
                }
                c.g().a("Fabric", "Falling back to Crashlytics key lookup from Manifest");
                return bundle.getString("com.crashlytics.ApiKey");
            } catch (Exception e2) {
                e = e2;
                str = string;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Caught non-fatal exception while retrieving apiKey: ");
                stringBuilder.append(e);
                c.g().a("Fabric", stringBuilder.toString());
                return str;
            }
        } catch (Exception e3) {
            e = e3;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Caught non-fatal exception while retrieving apiKey: ");
            stringBuilder.append(e);
            c.g().a("Fabric", stringBuilder.toString());
            return str;
        }
    }

    /* Access modifiers changed, original: protected */
    public String d(Context context) {
        int a = CommonUtils.a(context, "io.fabric.ApiKey", "string");
        if (a == 0) {
            c.g().a("Fabric", "Falling back to Crashlytics key lookup from Strings");
            a = CommonUtils.a(context, "com.crashlytics.ApiKey", "string");
        }
        return a != 0 ? context.getResources().getString(a) : null;
    }

    /* Access modifiers changed, original: protected */
    public void e(Context context) {
        if (c.h() || CommonUtils.i(context)) {
            throw new IllegalArgumentException(a());
        }
        c.g().e("Fabric", a());
    }
}
