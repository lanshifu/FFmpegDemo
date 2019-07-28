package com.tencent.liteav.j;

import android.util.Log;

/* compiled from: FrameCounter */
public class b {
    private static boolean a = false;
    private static int b = 0;
    private static int c = 0;
    private static int d = 0;
    private static int e = 0;
    private static int f = 0;
    private static int g = 0;
    private static int h = 0;
    private static boolean i = false;

    public static void a() {
        b++;
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("decodeVideoCount:");
            stringBuilder.append(b);
            Log.w("FrameCounter", stringBuilder.toString());
        }
    }

    public static void b() {
        c++;
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("decodeAudioCount:");
            stringBuilder.append(c);
            Log.w("FrameCounter", stringBuilder.toString());
        }
    }

    public static void c() {
        d++;
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("processVideoCount:");
            stringBuilder.append(d);
            Log.w("FrameCounter", stringBuilder.toString());
        }
    }

    public static void d() {
        e++;
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("processAudioCount:");
            stringBuilder.append(e);
            Log.w("FrameCounter", stringBuilder.toString());
        }
    }

    public static void e() {
        f++;
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("renderVideoCount:");
            stringBuilder.append(f);
            Log.w("FrameCounter", stringBuilder.toString());
        }
    }

    public static void f() {
        g++;
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("encodeVideoCount:");
            stringBuilder.append(g);
            Log.w("FrameCounter", stringBuilder.toString());
        }
    }

    public static void g() {
        h++;
        if (a) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("encodeAudioCount:");
            stringBuilder.append(h);
            Log.w("FrameCounter", stringBuilder.toString());
        }
    }

    public static void h() {
        i = true;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        g = 0;
        h = 0;
    }
}
