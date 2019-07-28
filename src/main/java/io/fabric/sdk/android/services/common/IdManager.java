package io.fabric.sdk.android.services.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import io.fabric.sdk.android.c;
import io.fabric.sdk.android.h;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public class IdManager {
    private static final Pattern e = Pattern.compile("[^\\p{Alnum}]");
    private static final String f = Pattern.quote("/");
    c a;
    b b;
    boolean c;
    p d;
    private final ReentrantLock g = new ReentrantLock();
    private final q h;
    private final boolean i;
    private final boolean j;
    private final Context k;
    private final String l;
    private final String m;
    private final Collection<h> n;

    public enum DeviceIdentifierType {
        WIFI_MAC_ADDRESS(1),
        BLUETOOTH_MAC_ADDRESS(2),
        FONT_TOKEN(53),
        ANDROID_ID(100),
        ANDROID_DEVICE_ID(101),
        ANDROID_SERIAL(102),
        ANDROID_ADVERTISING_ID(103);
        
        public final int protobufIndex;

        private DeviceIdentifierType(int i) {
            this.protobufIndex = i;
        }
    }

    public IdManager(Context context, String str, String str2, Collection<h> collection) {
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        } else if (str == null) {
            throw new IllegalArgumentException("appIdentifier must not be null");
        } else if (collection != null) {
            this.k = context;
            this.l = str;
            this.m = str2;
            this.n = collection;
            this.h = new q();
            this.a = new c(context);
            this.d = new p();
            this.i = CommonUtils.a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
            if (!this.i) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Device ID collection disabled for ");
                stringBuilder.append(context.getPackageName());
                c.g().a("Fabric", stringBuilder.toString());
            }
            this.j = CommonUtils.a(context, "com.crashlytics.CollectUserIdentifiers", true);
            if (!this.j) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("User information collection disabled for ");
                stringBuilder2.append(context.getPackageName());
                c.g().a("Fabric", stringBuilder2.toString());
            }
        } else {
            throw new IllegalArgumentException("kits must not be null");
        }
    }

    public boolean a() {
        return this.j;
    }

    private String a(String str) {
        return str == null ? null : e.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    public String b() {
        String str = this.m;
        if (str != null) {
            return str;
        }
        SharedPreferences a = CommonUtils.a(this.k);
        b(a);
        String string = a.getString("crashlytics.installation.id", null);
        return string == null ? a(a) : string;
    }

    public String c() {
        return this.l;
    }

    public String d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e());
        stringBuilder.append("/");
        stringBuilder.append(f());
        return stringBuilder.toString();
    }

    public String e() {
        return b(VERSION.RELEASE);
    }

    public String f() {
        return b(VERSION.INCREMENTAL);
    }

    public String g() {
        return String.format(Locale.US, "%s/%s", new Object[]{b(Build.MANUFACTURER), b(Build.MODEL)});
    }

    private String b(String str) {
        return str.replaceAll(f, "");
    }

    @SuppressLint({"CommitPrefEdits"})
    private String a(SharedPreferences sharedPreferences) {
        this.g.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", null);
            if (string == null) {
                string = a(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            this.g.unlock();
            return string;
        } catch (Throwable th) {
            this.g.unlock();
        }
    }

    private void b(SharedPreferences sharedPreferences) {
        b l = l();
        if (l != null) {
            a(sharedPreferences, l.a);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private void a(SharedPreferences sharedPreferences, String str) {
        this.g.lock();
        try {
            if (!TextUtils.isEmpty(str)) {
                String string = sharedPreferences.getString("crashlytics.advertising.id", null);
                if (TextUtils.isEmpty(string)) {
                    sharedPreferences.edit().putString("crashlytics.advertising.id", str).commit();
                } else if (!string.equals(str)) {
                    sharedPreferences.edit().remove("crashlytics.installation.id").putString("crashlytics.advertising.id", str).commit();
                }
                this.g.unlock();
            }
        } finally {
            this.g.unlock();
        }
    }

    public Map<DeviceIdentifierType, String> h() {
        HashMap hashMap = new HashMap();
        for (h hVar : this.n) {
            if (hVar instanceof l) {
                for (Entry entry : ((l) hVar).f().entrySet()) {
                    a(hashMap, (DeviceIdentifierType) entry.getKey(), (String) entry.getValue());
                }
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    public String i() {
        return this.h.a(this.k);
    }

    public Boolean j() {
        return k() ? m() : null;
    }

    private void a(Map<DeviceIdentifierType, String> map, DeviceIdentifierType deviceIdentifierType, String str) {
        if (str != null) {
            map.put(deviceIdentifierType, str);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean k() {
        return this.i && !this.d.b(this.k);
    }

    /* Access modifiers changed, original: declared_synchronized */
    public synchronized b l() {
        if (!this.c) {
            this.b = this.a.a();
            this.c = true;
        }
        return this.b;
    }

    private Boolean m() {
        b l = l();
        return l != null ? Boolean.valueOf(l.b) : null;
    }
}
