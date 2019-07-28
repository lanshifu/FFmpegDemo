package org.slf4j.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.b;

/* compiled from: AndroidLoggerFactory */
class a implements org.slf4j.a {
    private final ConcurrentMap<String, b> a = new ConcurrentHashMap();

    a() {
    }

    public b a(String str) {
        str = b(str);
        b bVar = (b) this.a.get(str);
        if (bVar != null) {
            return bVar;
        }
        bVar = new AndroidLoggerAdapter(str);
        b bVar2 = (b) this.a.putIfAbsent(str, bVar);
        return bVar2 == null ? bVar : bVar2;
    }

    static String b(String str) {
        if (str == null) {
            return "null";
        }
        int length = str.length();
        if (length <= 23) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(26);
        int i = 0;
        int i2 = 0;
        do {
            int indexOf = str.indexOf(46, i);
            if (indexOf != -1) {
                stringBuilder.append(str.charAt(i));
                if (indexOf - i > 1) {
                    stringBuilder.append('*');
                }
                stringBuilder.append('.');
                i = indexOf + 1;
                i2 = stringBuilder.length();
            } else {
                int i3 = length - i;
                if (i2 == 0 || i2 + i3 > 23) {
                    return c(str);
                }
                stringBuilder.append(str, i, length);
                return stringBuilder.toString();
            }
        } while (i2 <= 23);
        return c(str);
    }

    private static String c(String str) {
        int length = str.length();
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            lastIndexOf++;
            if (length - lastIndexOf <= 23) {
                return str.substring(lastIndexOf);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('*');
        stringBuilder.append(str.substring((length - 23) + 1));
        return stringBuilder.toString();
    }
}
