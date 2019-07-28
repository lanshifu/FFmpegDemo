package com.tomatolive.library.utils;

import java.io.Closeable;

/* compiled from: IOUtils */
public class l {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
