package io.fabric.sdk.android.services.settings;

import android.annotation.SuppressLint;
import android.content.SharedPreferences.Editor;
import defpackage.vz;
import defpackage.wa;
import io.fabric.sdk.android.c;
import io.fabric.sdk.android.h;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.k;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DefaultSettingsController */
class j implements r {
    private final v a;
    private final u b;
    private final io.fabric.sdk.android.services.common.j c;
    private final g d;
    private final w e;
    private final h f;
    private final vz g = new wa(this.f);
    private final k h;

    public j(h hVar, v vVar, io.fabric.sdk.android.services.common.j jVar, u uVar, g gVar, w wVar, k kVar) {
        this.f = hVar;
        this.a = vVar;
        this.c = jVar;
        this.b = uVar;
        this.d = gVar;
        this.e = wVar;
        this.h = kVar;
    }

    public s a() {
        return a(SettingsCacheBehavior.USE_CACHE);
    }

    public s a(SettingsCacheBehavior settingsCacheBehavior) {
        Throwable e;
        s sVar;
        s sVar2 = null;
        if (this.h.a()) {
            try {
                if (!(c.h() || d())) {
                    sVar2 = b(settingsCacheBehavior);
                }
                if (sVar2 == null) {
                    JSONObject a = this.e.a(this.a);
                    if (a != null) {
                        s a2 = this.b.a(this.c, a);
                        try {
                            this.d.a(a2.g, a);
                            a(a, "Loaded settings: ");
                            a(b());
                            sVar2 = a2;
                        } catch (Exception e2) {
                            e = e2;
                            sVar2 = a2;
                            c.g().e("Fabric", "Unknown error while loading Crashlytics settings. Crashes will be cached until settings can be retrieved.", e);
                            sVar = sVar2;
                            return sVar;
                        }
                    }
                }
                if (sVar2 == null) {
                    sVar = b(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
                    return sVar;
                }
            } catch (Exception e3) {
                e = e3;
            }
            sVar = sVar2;
            return sVar;
        }
        c.g().a("Fabric", "Not fetching settings, because data collection is disabled by Firebase.");
        return null;
    }

    private s b(SettingsCacheBehavior settingsCacheBehavior) {
        Throwable e;
        s sVar = null;
        try {
            if (SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(settingsCacheBehavior)) {
                return null;
            }
            JSONObject a = this.d.a();
            if (a != null) {
                s a2 = this.b.a(this.c, a);
                if (a2 != null) {
                    a(a, "Loaded cached settings: ");
                    long a3 = this.c.a();
                    if (!SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(settingsCacheBehavior)) {
                        if (a2.a(a3)) {
                            c.g().a("Fabric", "Cached settings have expired.");
                            return null;
                        }
                    }
                    try {
                        c.g().a("Fabric", "Returning cached settings.");
                        return a2;
                    } catch (Exception e2) {
                        e = e2;
                        sVar = a2;
                        c.g().e("Fabric", "Failed to get cached settings", e);
                        return sVar;
                    }
                }
                c.g().e("Fabric", "Failed to transform cached settings data.", null);
                return null;
            }
            c.g().a("Fabric", "No cached settings data found.");
            return null;
        } catch (Exception e3) {
            e = e3;
            c.g().e("Fabric", "Failed to get cached settings", e);
            return sVar;
        }
    }

    private void a(JSONObject jSONObject, String str) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(jSONObject.toString());
        c.g().a("Fabric", stringBuilder.toString());
    }

    /* Access modifiers changed, original: 0000 */
    public String b() {
        return CommonUtils.a(CommonUtils.m(this.f.r()));
    }

    /* Access modifiers changed, original: 0000 */
    public String c() {
        return this.g.a().getString("existing_instance_identifier", "");
    }

    /* Access modifiers changed, original: 0000 */
    @SuppressLint({"CommitPrefEdits"})
    public boolean a(String str) {
        Editor b = this.g.b();
        b.putString("existing_instance_identifier", str);
        return this.g.a(b);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean d() {
        return c().equals(b()) ^ 1;
    }
}
