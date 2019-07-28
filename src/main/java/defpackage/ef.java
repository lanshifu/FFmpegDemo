package defpackage;

import io.fabric.sdk.android.c;
import io.fabric.sdk.android.h;
import io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import io.fabric.sdk.android.services.common.l;
import java.util.Collections;
import java.util.Map;

/* compiled from: Beta */
/* renamed from: ef */
public class ef extends h<Boolean> implements l {
    public String a() {
        return "1.2.10.27";
    }

    public String b() {
        return "com.crashlytics.sdk.android:beta";
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: d */
    public Boolean e() {
        c.g().a("Beta", "Beta kit initializing...");
        return Boolean.valueOf(true);
    }

    public Map<DeviceIdentifierType, String> f() {
        return Collections.emptyMap();
    }
}
