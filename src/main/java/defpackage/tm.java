package defpackage;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: BitmapPool */
/* renamed from: tm */
public class tm {
    private static final int a = ((int) (Runtime.getRuntime().maxMemory() / 4));
    private static File d;
    private static md e;
    private static md f;
    private static File g;
    private static File h;
    private LruCache<String, Bitmap> b;
    private LruCache<String, ub> c;

    /* compiled from: BitmapPool */
    /* renamed from: tm$a */
    private static class a {
        private static final tm a = new tm();
    }

    /* synthetic */ tm(1 1) {
        this();
    }

    private tm() {
        this.b = new LruCache<String, Bitmap>(a) {
            /* Access modifiers changed, original: protected */
            /* renamed from: a */
            public int sizeOf(String str, Bitmap bitmap) {
                return bitmap == null ? 0 : bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
        this.c = new LruCache(100);
    }

    public void a(String str, Bitmap bitmap) {
        this.b.put(str, bitmap);
    }

    public void a(String str, ub ubVar) {
        this.c.put(str, ubVar);
        tn.a.a(str, ubVar, tm.c());
    }

    public Bitmap a(String str) {
        return (Bitmap) this.b.get(str);
    }

    public ub b(String str) {
        ub ubVar = (ub) this.c.get(str);
        return ubVar == null ? (ub) tn.a.a(str, tm.c()) : ubVar;
    }

    public void a(String str, InputStream inputStream) {
        tn.b.a(str, inputStream, tm.d());
    }

    public InputStream c(String str) {
        return (InputStream) tn.b.a(str, tm.d());
    }

    public boolean d(String str) {
        return tn.b.b(str, tm.d());
    }

    public static tm a() {
        return a.a;
    }

    public void b() {
        this.b.evictAll();
        this.c.evictAll();
    }

    private static md c() {
        if (e == null && d != null) {
            try {
                e = md.a(g, 1, 1, 1048576);
            } catch (IOException e) {
                ug.a(e);
            }
        }
        return e;
    }

    private static md d() {
        if (f == null && d != null) {
            try {
                f = md.a(h, 1, 1, 104857600);
            } catch (IOException e) {
                ug.a(e);
            }
        }
        return f;
    }
}
