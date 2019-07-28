package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import defpackage.vz;
import defpackage.wa;

/* compiled from: AdvertisingInfoProvider */
class c {
    private final Context a;
    private final vz b;

    public c(Context context) {
        this.a = context.getApplicationContext();
        this.b = new wa(context, "TwitterAdvertisingInfoPreferences");
    }

    public b a() {
        b b = b();
        if (c(b)) {
            io.fabric.sdk.android.c.g().a("Fabric", "Using AdvertisingInfo from Preference Store");
            a(b);
            return b;
        }
        b = e();
        b(b);
        return b;
    }

    private void a(final b bVar) {
        new Thread(new h() {
            public void a() {
                b a = c.this.e();
                if (!bVar.equals(a)) {
                    io.fabric.sdk.android.c.g().a("Fabric", "Asychronously getting Advertising Info and storing it to preferences");
                    c.this.b(a);
                }
            }
        }).start();
    }

    @SuppressLint({"CommitPrefEdits"})
    private void b(b bVar) {
        if (c(bVar)) {
            this.b.a(this.b.b().putString("advertising_id", bVar.a).putBoolean("limit_ad_tracking_enabled", bVar.b));
        } else {
            this.b.a(this.b.b().remove("advertising_id").remove("limit_ad_tracking_enabled"));
        }
    }

    /* Access modifiers changed, original: protected */
    public b b() {
        return new b(this.b.a().getString("advertising_id", ""), this.b.a().getBoolean("limit_ad_tracking_enabled", false));
    }

    public f c() {
        return new d(this.a);
    }

    public f d() {
        return new e(this.a);
    }

    private boolean c(b bVar) {
        return (bVar == null || TextUtils.isEmpty(bVar.a)) ? false : true;
    }

    private b e() {
        b a = c().a();
        if (c(a)) {
            io.fabric.sdk.android.c.g().a("Fabric", "Using AdvertisingInfo from Reflection Provider");
        } else {
            a = d().a();
            if (c(a)) {
                io.fabric.sdk.android.c.g().a("Fabric", "Using AdvertisingInfo from Service Provider");
            } else {
                io.fabric.sdk.android.c.g().a("Fabric", "AdvertisingInfo not present");
            }
        }
        return a;
    }
}
