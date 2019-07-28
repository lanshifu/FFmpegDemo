package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.text.TextUtils;
import io.fabric.sdk.android.c;

/* compiled from: FirebaseInfo */
public class p {
    /* Access modifiers changed, original: 0000 */
    public String a(Context context) {
        int a = CommonUtils.a(context, "google_app_id", "string");
        if (a == 0) {
            return null;
        }
        c.g().a("Fabric", "Generating Crashlytics ApiKey from google_app_id in Strings");
        return a(context.getResources().getString(a));
    }

    /* Access modifiers changed, original: 0000 */
    public String a(String str) {
        return CommonUtils.b(str).substring(0, 40);
    }

    public boolean b(Context context) {
        boolean z = false;
        if (CommonUtils.a(context, "com.crashlytics.useFirebaseAppId", false)) {
            return true;
        }
        if (e(context) && !c(context)) {
            z = true;
        }
        return z;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean c(Context context) {
        if (TextUtils.isEmpty(new g().c(context))) {
            return TextUtils.isEmpty(new g().d(context)) ^ 1;
        }
        return true;
    }

    public boolean d(Context context) {
        int a = CommonUtils.a(context, "io.fabric.auto_initialize", "bool");
        if (a == 0) {
            return false;
        }
        boolean z = context.getResources().getBoolean(a);
        if (z) {
            c.g().a("Fabric", "Found Fabric auto-initialization flag for joint Firebase/Fabric customers");
        }
        return z;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean e(Context context) {
        int a = CommonUtils.a(context, "google_app_id", "string");
        if (a == 0) {
            return false;
        }
        return TextUtils.isEmpty(context.getResources().getString(a)) ^ 1;
    }
}
