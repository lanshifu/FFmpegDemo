package io.fabric.sdk.android;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.common.g;
import io.fabric.sdk.android.services.common.k;
import io.fabric.sdk.android.services.network.b;
import io.fabric.sdk.android.services.network.c;
import io.fabric.sdk.android.services.settings.d;
import io.fabric.sdk.android.services.settings.e;
import io.fabric.sdk.android.services.settings.h;
import io.fabric.sdk.android.services.settings.n;
import io.fabric.sdk.android.services.settings.q;
import io.fabric.sdk.android.services.settings.s;
import io.fabric.sdk.android.services.settings.x;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/* compiled from: Onboarding */
class l extends h<Boolean> {
    private final c a = new b();
    private PackageManager b;
    private String c;
    private PackageInfo d;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private final Future<Map<String, j>> p;
    private final Collection<h> q;

    public String a() {
        return "1.4.8.32";
    }

    public String b() {
        return "io.fabric.sdk.android:fabric";
    }

    public l(Future<Map<String, j>> future, Collection<h> collection) {
        this.p = future;
        this.q = collection;
    }

    /* Access modifiers changed, original: protected */
    public boolean a_() {
        try {
            this.m = q().i();
            this.b = r().getPackageManager();
            this.c = r().getPackageName();
            this.d = this.b.getPackageInfo(this.c, 0);
            this.k = Integer.toString(this.d.versionCode);
            this.l = this.d.versionName == null ? "0.0" : this.d.versionName;
            this.n = this.b.getApplicationLabel(r().getApplicationInfo()).toString();
            this.o = Integer.toString(r().getApplicationInfo().targetSdkVersion);
            return true;
        } catch (NameNotFoundException e) {
            c.g().e("Fabric", "Failed init", e);
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: d */
    public Boolean e() {
        boolean a;
        String k = CommonUtils.k(r());
        s g = g();
        if (g != null) {
            try {
                Map map;
                if (this.p != null) {
                    map = (Map) this.p.get();
                } else {
                    map = new HashMap();
                }
                a = a(k, g.a, a(map, this.q).values());
            } catch (Exception e) {
                c.g().e("Fabric", "Error performing auto configuration.", e);
            }
            return Boolean.valueOf(a);
        }
        a = false;
        return Boolean.valueOf(a);
    }

    private s g() {
        try {
            q.a().a(this, this.i, this.a, this.k, this.l, f(), k.a(r())).c();
            return q.a().b();
        } catch (Exception e) {
            c.g().e("Fabric", "Error dealing with settings", e);
            return null;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public Map<String, j> a(Map<String, j> map, Collection<h> collection) {
        for (h hVar : collection) {
            if (!map.containsKey(hVar.b())) {
                map.put(hVar.b(), new j(hVar.b(), hVar.a(), "binary"));
            }
        }
        return map;
    }

    private boolean a(String str, e eVar, Collection<j> collection) {
        if ("new".equals(eVar.b)) {
            if (b(str, eVar, collection)) {
                return q.a().d();
            }
            c.g().e("Fabric", "Failed to create app with Crashlytics service.", null);
            return false;
        } else if ("configured".equals(eVar.b)) {
            return q.a().d();
        } else {
            if (eVar.f) {
                c.g().a("Fabric", "Server says an update is required - forcing a full App update.");
                c(str, eVar, collection);
            }
            return true;
        }
    }

    private boolean b(String str, e eVar, Collection<j> collection) {
        return new h(this, f(), eVar.c, this.a).a(a(n.a(r(), str), (Collection) collection));
    }

    private boolean c(String str, e eVar, Collection<j> collection) {
        return a(eVar, n.a(r(), str), (Collection) collection);
    }

    private boolean a(e eVar, n nVar, Collection<j> collection) {
        return new x(this, f(), eVar.c, this.a).a(a(nVar, (Collection) collection));
    }

    private d a(n nVar, Collection<j> collection) {
        return new d(new g().a(r()), q().c(), this.l, this.k, CommonUtils.a(CommonUtils.m(r1)), this.n, DeliveryMechanism.determineFrom(this.m).getId(), this.o, "0", nVar, collection);
    }

    /* Access modifiers changed, original: 0000 */
    public String f() {
        return CommonUtils.b(r(), "com.crashlytics.ApiEndpoint");
    }
}
