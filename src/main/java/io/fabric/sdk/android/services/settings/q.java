package io.fabric.sdk.android.services.settings;

import android.content.Context;
import io.fabric.sdk.android.h;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.DeliveryMechanism;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.g;
import io.fabric.sdk.android.services.common.k;
import io.fabric.sdk.android.services.common.t;
import io.fabric.sdk.android.services.network.c;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: Settings */
public class q {
    private final AtomicReference<s> a;
    private final CountDownLatch b;
    private r c;
    private boolean d;

    /* compiled from: Settings */
    static class a {
        private static final q a = new q();
    }

    public static q a() {
        return a.a;
    }

    private q() {
        this.a = new AtomicReference();
        this.b = new CountDownLatch(1);
        this.d = false;
    }

    public synchronized q a(h hVar, IdManager idManager, c cVar, String str, String str2, String str3, k kVar) {
        h hVar2 = hVar;
        synchronized (this) {
            if (this.d) {
                return this;
            }
            if (this.c == null) {
                Context r = hVar.r();
                String c = idManager.c();
                String a = new g().a(r);
                String i = idManager.i();
                t tVar = new t();
                k kVar2 = new k();
                i iVar = new i(hVar2);
                String k = CommonUtils.k(r);
                String str4 = str3;
                l lVar = new l(hVar2, str4, String.format(Locale.US, "https://settings.crashlytics.com/spi/v2/platforms/android/apps/%s/settings", new Object[]{c}), cVar);
                c = idManager.g();
                String str5 = c;
                String str6 = str2;
                String str7 = str;
                h hVar3 = hVar;
                this.c = new j(hVar3, new v(a, str5, idManager.f(), idManager.e(), idManager.b(), CommonUtils.a(CommonUtils.m(r)), str6, str7, DeliveryMechanism.determineFrom(i).getId(), k), tVar, kVar2, iVar, lVar, kVar);
            }
            this.d = true;
            return this;
        }
    }

    public s b() {
        try {
            this.b.await();
            return (s) this.a.get();
        } catch (InterruptedException unused) {
            io.fabric.sdk.android.c.g().e("Fabric", "Interrupted while waiting for settings data.");
            return null;
        }
    }

    public synchronized boolean c() {
        s a;
        a = this.c.a();
        a(a);
        return a != null;
    }

    public synchronized boolean d() {
        s a;
        a = this.c.a(SettingsCacheBehavior.SKIP_CACHE_LOOKUP);
        a(a);
        if (a == null) {
            io.fabric.sdk.android.c.g().e("Fabric", "Failed to force reload of settings from Crashlytics.", null);
        }
        return a != null;
    }

    private void a(s sVar) {
        this.a.set(sVar);
        this.b.countDown();
    }
}
