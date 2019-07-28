package org.slf4j.helpers;

import java.io.PrintStream;

/* compiled from: Util */
public final class f {
    private static boolean a = false;

    public static String a(String str) {
        if (str != null) {
            try {
                return System.getProperty(str);
            } catch (SecurityException unused) {
                return null;
            }
        }
        throw new IllegalArgumentException("null input");
    }

    public static boolean b(String str) {
        str = a(str);
        if (str == null) {
            return false;
        }
        return str.equalsIgnoreCase("true");
    }

    public static final void a(String str, Throwable th) {
        System.err.println(str);
        System.err.println("Reported exception:");
        th.printStackTrace();
    }

    public static final void c(String str) {
        PrintStream printStream = System.err;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SLF4J: ");
        stringBuilder.append(str);
        printStream.println(stringBuilder.toString());
    }
}
