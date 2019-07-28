package io.fabric.sdk.android.services.common;

import android.content.Context;
import defpackage.vg;
import defpackage.vi;
import io.fabric.sdk.android.c;

/* compiled from: InstallerPackageNameProvider */
public class q {
    private final vi<String> a = new vi<String>() {
        /* renamed from: b */
        public String a(Context context) throws Exception {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            return installerPackageName == null ? "" : installerPackageName;
        }
    };
    private final vg<String> b = new vg();

    public String a(Context context) {
        try {
            String str = (String) this.b.a(context, this.a);
            if ("".equals(str)) {
                str = null;
            }
            return str;
        } catch (Exception e) {
            c.g().e("Fabric", "Failed to determine installer package name", e);
            return null;
        }
    }
}
