package io.fabric.sdk.android;

import android.os.SystemClock;
import android.text.TextUtils;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.Closeable;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* compiled from: FabricKitsFinder */
class e implements Callable<Map<String, j>> {
    final String a;

    e(String str) {
        this.a = str;
    }

    /* renamed from: a */
    public Map<String, j> call() throws Exception {
        HashMap hashMap = new HashMap();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        hashMap.putAll(c());
        hashMap.putAll(d());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("finish scanning in ");
        stringBuilder.append(SystemClock.elapsedRealtime() - elapsedRealtime);
        c.g().b("Fabric", stringBuilder.toString());
        return hashMap;
    }

    private Map<String, j> c() {
        HashMap hashMap = new HashMap();
        try {
            Class.forName("com.google.android.gms.ads.AdView");
            j jVar = new j("com.google.firebase.firebase-ads", "0.0.0", "binary");
            hashMap.put(jVar.a(), jVar);
            c.g().b("Fabric", "Found kit: com.google.firebase.firebase-ads");
        } catch (Exception unused) {
        }
        return hashMap;
    }

    private Map<String, j> d() throws Exception {
        HashMap hashMap = new HashMap();
        ZipFile b = b();
        Enumeration entries = b.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.getName().startsWith("fabric/") && zipEntry.getName().length() > "fabric/".length()) {
                j a = a(zipEntry, b);
                if (a != null) {
                    hashMap.put(a.a(), a);
                    c.g().b("Fabric", String.format("Found kit:[%s] version:[%s]", new Object[]{a.a(), a.b()}));
                }
            }
        }
        if (b != null) {
            try {
                b.close();
            } catch (IOException unused) {
            }
        }
        return hashMap;
    }

    private j a(ZipEntry zipEntry, ZipFile zipFile) {
        Throwable e;
        StringBuilder stringBuilder;
        Throwable th;
        Closeable inputStream;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                Properties properties = new Properties();
                properties.load(inputStream);
                String property = properties.getProperty("fabric-identifier");
                String property2 = properties.getProperty("fabric-version");
                String property3 = properties.getProperty("fabric-build-type");
                if (TextUtils.isEmpty(property) || TextUtils.isEmpty(property2)) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Invalid format of fabric file,");
                    stringBuilder2.append(zipEntry.getName());
                    throw new IllegalStateException(stringBuilder2.toString());
                }
                j jVar = new j(property, property2, property3);
                CommonUtils.a(inputStream);
                return jVar;
            } catch (IOException e2) {
                e = e2;
                try {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Error when parsing fabric properties ");
                    stringBuilder.append(zipEntry.getName());
                    c.g().e("Fabric", stringBuilder.toString(), e);
                    CommonUtils.a(inputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    CommonUtils.a(inputStream);
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            inputStream = null;
            stringBuilder = new StringBuilder();
            stringBuilder.append("Error when parsing fabric properties ");
            stringBuilder.append(zipEntry.getName());
            c.g().e("Fabric", stringBuilder.toString(), e);
            CommonUtils.a(inputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            CommonUtils.a(inputStream);
            throw th;
        }
    }

    /* Access modifiers changed, original: protected */
    public ZipFile b() throws IOException {
        return new ZipFile(this.a);
    }
}
