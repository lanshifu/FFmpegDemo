package com.tencent.liteav.network.a.a;

import com.tencent.liteav.network.a.b;
import com.tencent.liteav.network.a.c;
import com.tencent.liteav.network.a.d;
import com.tencent.liteav.network.a.e;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: AndroidDnsServer */
public final class a {
    public static InetAddress[] a() {
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("getprop").getInputStream()));
            ArrayList arrayList = new ArrayList(5);
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                }
                int indexOf = readLine.indexOf("]: [");
                if (indexOf != -1) {
                    String substring = readLine.substring(1, indexOf);
                    readLine = readLine.substring(indexOf + 4, readLine.length() - 1);
                    if (substring.endsWith(".dns") || substring.endsWith(".dns1") || substring.endsWith(".dns2") || substring.endsWith(".dns3") || substring.endsWith(".dns4")) {
                        InetAddress byName = InetAddress.getByName(readLine);
                        if (byName != null) {
                            String hostAddress = byName.getHostAddress();
                            if (hostAddress != null) {
                                if (hostAddress.length() != 0) {
                                    arrayList.add(byName);
                                }
                            }
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
            }
        } catch (IOException e) {
            Logger.getLogger("AndroidDnsServer").log(Level.WARNING, "Exception in findDNSByExec", e);
        }
        return null;
    }

    public static InetAddress[] b() {
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class});
            ArrayList arrayList = new ArrayList(5);
            int length = new String[]{"net.dns1", "net.dns2", "net.dns3", "net.dns4"}.length;
            for (int i = 0; i < length; i++) {
                String str = (String) method.invoke(null, new Object[]{r4[i]});
                if (str != null) {
                    if (str.length() != 0) {
                        InetAddress byName = InetAddress.getByName(str);
                        if (byName != null) {
                            String hostAddress = byName.getHostAddress();
                            if (hostAddress != null) {
                                if (hostAddress.length() != 0) {
                                    if (!arrayList.contains(byName)) {
                                        arrayList.add(byName);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
            }
        } catch (Exception e) {
            Logger.getLogger("AndroidDnsServer").log(Level.WARNING, "Exception in findDNSByReflection", e);
        }
        return null;
    }

    public static c c() {
        return new c() {
            public e[] a(b bVar, d dVar) throws IOException {
                InetAddress[] b = a.b();
                if (b == null) {
                    b = a.a();
                }
                if (b != null) {
                    return new c(b[0]).a(bVar, dVar);
                }
                throw new IOException("cant get local dns server");
            }
        };
    }
}
