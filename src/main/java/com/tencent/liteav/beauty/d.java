package com.tencent.liteav.beauty;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import com.tencent.liteav.basic.e.i;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.TXLiveConstants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* compiled from: TXCVideoPreprocessor */
public class d extends com.tencent.liteav.basic.module.a implements e {
    protected Context a;
    protected boolean b = true;
    protected boolean c = false;
    protected int d = 0;
    protected int e = 0;
    protected int f = 0;
    protected com.tencent.liteav.basic.e.a g = null;
    protected b h;
    protected b i = new b();
    protected c j = null;
    f k;
    private boolean l = false;
    private long m = 0;
    private long n = 0;
    private long o = 0;
    private a p = new a(this);

    /* compiled from: TXCVideoPreprocessor */
    protected static class a {
        WeakReference<d> a;
        private HashMap<String, String> b = new HashMap();

        public a(d dVar) {
            this.a = new WeakReference(dVar);
        }

        public void a(String str, int i) {
            this.b.put(str, String.valueOf(i));
            d dVar = (d) this.a.get();
            if (dVar != null) {
                String id = dVar.getID();
                if (id != null && id.length() > 0) {
                    dVar.setStatusValue(3001, a());
                }
            }
        }

        public String a() {
            String str = "";
            for (String str2 : this.b.keySet()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(str2);
                stringBuilder.append(":");
                stringBuilder.append((String) this.b.get(str2));
                stringBuilder.append(" ");
                str = stringBuilder.toString();
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("{");
            stringBuilder2.append(str);
            stringBuilder2.append("}");
            return stringBuilder2.toString();
        }
    }

    /* compiled from: TXCVideoPreprocessor */
    static class b {
        int a;
        int b;
        int c;
        int d;
        int e;
        int f;
        int g;
        int h;
        boolean i;
        boolean j;
        public int k = 5;
        public int l = 0;
        com.tencent.liteav.basic.e.a m = null;

        b() {
        }
    }

    /* compiled from: TXCVideoPreprocessor */
    private static class c {
        public boolean a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public boolean g;
        public int h;
        public int i;
        public int j;
        public int k;
        public int l;
        public com.tencent.liteav.basic.e.a m;

        private c() {
            this.g = false;
            this.k = 5;
            this.l = 0;
            this.m = null;
        }
    }

    /* compiled from: TXCVideoPreprocessor */
    public static class d {
        public Bitmap a;
        public float b;
        public float c;
        public float d;
        public int e;
        public int f;
        public int g;
    }

    private int m(int i) {
        switch (i) {
            case 1:
                return 90;
            case 2:
                return TXLiveConstants.RENDER_ROTATION_180;
            case 3:
                return 270;
            default:
                return i;
        }
    }

    public int willAddWatermark(int i, int i2, int i3) {
        boolean z = false;
        if (this.k == null) {
            return 0;
        }
        com.tencent.liteav.basic.g.c cVar = new com.tencent.liteav.basic.g.c();
        cVar.d = i2;
        cVar.e = i3;
        cVar.i = this.j != null ? this.j.j : 0;
        if (this.j != null) {
            z = this.j.g;
        }
        cVar.h = z;
        cVar.a = i;
        return this.k.a(cVar);
    }

    public void didProcessFrame(int i, int i2, int i3, long j) {
        b();
        if (this.k != null) {
            com.tencent.liteav.basic.g.c cVar = new com.tencent.liteav.basic.g.c();
            cVar.d = i2;
            cVar.e = i3;
            boolean z = false;
            cVar.i = this.j != null ? this.j.j : 0;
            if (this.j != null) {
                z = this.j.g;
            }
            cVar.h = z;
            cVar.a = i;
            this.k.a(cVar, j);
        }
    }

    public void didProcessFrame(byte[] bArr, int i, int i2, int i3, long j) {
        if (this.k != null) {
            this.k.a(bArr, i, i2, i3, j);
        }
    }

    private void b() {
        if (this.m != 0) {
            setStatusValue(3002, Long.valueOf(System.currentTimeMillis() - this.m));
        }
        this.n++;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > this.o + 2000) {
            double d = (double) this.n;
            Double.isNaN(d);
            d *= 1000.0d;
            double d2 = (double) (currentTimeMillis - this.o);
            Double.isNaN(d2);
            setStatusValue(3003, Double.valueOf(d / d2));
            this.n = 0;
            this.o = currentTimeMillis;
        }
    }

    public d(Context context, boolean z) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        TXCLog.i("TXCVideoPreprocessor", "TXCVideoPreprocessor version: VideoPreprocessor-v1.1");
        ConfigurationInfo deviceConfigurationInfo = activityManager.getDeviceConfigurationInfo();
        if (deviceConfigurationInfo != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("opengl es version ");
            stringBuilder.append(deviceConfigurationInfo.reqGlEsVersion);
            TXCLog.i("TXCVideoPreprocessor", stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("set GLContext ");
            stringBuilder.append(z);
            TXCLog.i("TXCVideoPreprocessor", stringBuilder.toString());
            if (deviceConfigurationInfo.reqGlEsVersion > 131072) {
                TXCLog.i("TXCVideoPreprocessor", "This devices is OpenGlUtils.OPENGL_ES_3");
                i.a(3);
            } else {
                TXCLog.i("TXCVideoPreprocessor", "This devices is OpenGlUtils.OPENGL_ES_2");
                i.a(2);
            }
        } else {
            TXCLog.e("TXCVideoPreprocessor", "getDeviceConfigurationInfo opengl Info failed!");
        }
        this.a = context;
        this.b = z;
        this.h = new b(this.a, this.b);
        a.a().a(context);
    }

    public void a(float[] fArr) {
        if (this.h != null) {
            this.h.a(fArr);
        }
    }

    public synchronized int a(byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        a(i, i2, m(i3), i4, i5);
        this.h.b(this.i);
        return this.h.a(bArr, i4);
    }

    public synchronized int a(int i, int i2, int i3, int i4, int i5, int i6) {
        a(i2, i3, m(i4), i5, i6);
        this.h.b(this.i);
        return this.h.a(i, i5);
    }

    public synchronized int a(com.tencent.liteav.basic.g.c cVar, int i, int i2) {
        this.m = System.currentTimeMillis();
        a(cVar.k);
        a(cVar.f, cVar.g);
        a(cVar.h);
        a(cVar.i);
        a(cVar.c);
        return a(cVar.a, cVar.d, cVar.e, cVar.i, i, i2);
    }

    public synchronized void a(com.tencent.liteav.basic.e.a aVar) {
        this.g = aVar;
    }

    public synchronized void a(int i, int i2) {
        this.d = i;
        this.e = i2;
    }

    public synchronized void a(int i) {
        this.f = i;
    }

    /* JADX WARNING: Missing block: B:12:0x001c, code skipped:
            return;
     */
    public synchronized void a(android.graphics.Bitmap r6, float r7, float r8, float r9) {
        /*
        r5 = this;
        monitor-enter(r5);
        r0 = 0;
        r1 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1));
        if (r1 < 0) goto L_0x001d;
    L_0x0006:
        r0 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1));
        if (r0 < 0) goto L_0x001d;
    L_0x000a:
        r0 = (double) r9;
        r2 = 0;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 >= 0) goto L_0x0012;
    L_0x0011:
        goto L_0x001d;
    L_0x0012:
        r0 = r5.h;	 Catch:{ all -> 0x0026 }
        if (r0 == 0) goto L_0x001b;
    L_0x0016:
        r0 = r5.h;	 Catch:{ all -> 0x0026 }
        r0.a(r6, r7, r8, r9);	 Catch:{ all -> 0x0026 }
    L_0x001b:
        monitor-exit(r5);
        return;
    L_0x001d:
        r6 = "TXCVideoPreprocessor";
        r7 = "WaterMark param is Error!";
        com.tencent.liteav.basic.log.TXCLog.e(r6, r7);	 Catch:{ all -> 0x0026 }
        monitor-exit(r5);
        return;
    L_0x0026:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.beauty.d.a(android.graphics.Bitmap, float, float, float):void");
    }

    public synchronized void a(List<d> list) {
        if (this.h != null) {
            a.a().e();
            this.h.a((List) list);
        }
    }

    public synchronized void a(boolean z) {
        this.c = z;
    }

    public synchronized void a(e eVar) {
        if (this.h == null) {
            TXCLog.e("TXCVideoPreprocessor", "setListener mDrawer is null!");
        } else {
            this.h.a(eVar);
        }
    }

    /* JADX WARNING: Missing block: B:13:0x001f, code skipped:
            return;
     */
    public synchronized void a(com.tencent.liteav.beauty.f r2) {
        /*
        r1 = this;
        monitor-enter(r1);
        r0 = r1.h;	 Catch:{ all -> 0x0020 }
        if (r0 != 0) goto L_0x000e;
    L_0x0005:
        r2 = "TXCVideoPreprocessor";
        r0 = "setListener mDrawer is null!";
        com.tencent.liteav.basic.log.TXCLog.e(r2, r0);	 Catch:{ all -> 0x0020 }
        monitor-exit(r1);
        return;
    L_0x000e:
        r1.k = r2;	 Catch:{ all -> 0x0020 }
        if (r2 != 0) goto L_0x0019;
    L_0x0012:
        r2 = r1.h;	 Catch:{ all -> 0x0020 }
        r0 = 0;
        r2.a(r0);	 Catch:{ all -> 0x0020 }
        goto L_0x001e;
    L_0x0019:
        r2 = r1.h;	 Catch:{ all -> 0x0020 }
        r2.a(r1);	 Catch:{ all -> 0x0020 }
    L_0x001e:
        monitor-exit(r1);
        return;
    L_0x0020:
        r2 = move-exception;
        monitor-exit(r1);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.beauty.d.a(com.tencent.liteav.beauty.f):void");
    }

    public synchronized void a(com.tencent.liteav.basic.d.a aVar) {
        if (this.h == null) {
            TXCLog.e("TXCVideoPreprocessor", "setListener mDrawer is null!");
        } else {
            this.h.a(aVar);
        }
    }

    private boolean a(int i, int i2, int i3, int i4, int i5) {
        if (this.j == null) {
            this.j = new c();
            this.n = 0;
            this.o = System.currentTimeMillis();
        }
        if (i != this.j.b || i2 != this.j.c || i3 != this.j.f || ((this.d > 0 && this.d != this.j.h) || ((this.e > 0 && this.e != this.j.i) || ((this.f > 0 && this.f != this.j.j) || ((this.g != null && ((this.g.c > 0 && (this.j.m == null || this.g.c != this.j.m.c)) || ((this.g.d > 0 && (this.j.m == null || this.g.d != this.j.m.d)) || ((this.g.a >= 0 && (this.j.m == null || this.g.a != this.j.m.a)) || (this.g.b >= 0 && (this.j.m == null || this.g.b != this.j.m.b)))))) || this.c != this.j.g))))) {
            TXCLog.i("TXCVideoPreprocessor", "Init sdk");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Input widht ");
            stringBuilder.append(i);
            stringBuilder.append(" height ");
            stringBuilder.append(i2);
            TXCLog.i("TXCVideoPreprocessor", stringBuilder.toString());
            this.j.b = i;
            this.j.c = i2;
            if (this.g != null && this.g.a >= 0 && this.g.b >= 0 && this.g.c > 0 && this.g.d > 0) {
                TXCLog.i("TXCVideoPreprocessor", "set Crop Rect; init ");
                i = i - this.g.a > this.g.c ? this.g.c : i - this.g.a;
                i2 = i2 - this.g.b > this.g.d ? this.g.d : i2 - this.g.b;
                this.g.c = i;
                this.g.d = i2;
                i = this.g.c;
                i2 = this.g.d;
                this.j.m = this.g;
            }
            this.j.f = i3;
            this.j.a = this.b;
            this.j.k = i4;
            this.j.l = i5;
            if (true == this.l) {
                this.j.h = this.d;
                this.j.i = this.e;
            } else {
                this.j.h = 0;
                this.j.i = 0;
            }
            this.j.j = this.f;
            if (this.j.j <= 0) {
                this.j.j = 0;
            }
            if (this.j.h <= 0 || this.j.i <= 0) {
                if (90 == this.j.j || 270 == this.j.j) {
                    this.j.h = i2;
                    this.j.i = i;
                } else {
                    this.j.h = i;
                    this.j.i = i2;
                }
            }
            if (90 == this.j.j || 270 == this.j.j) {
                this.j.d = this.j.i;
                this.j.e = this.j.h;
            } else {
                this.j.d = this.j.h;
                this.j.e = this.j.i;
            }
            if (true != this.l) {
                this.j.h = this.d;
                this.j.i = this.e;
                if (this.j.h <= 0 || this.j.i <= 0) {
                    if (90 == this.j.j || 270 == this.j.j) {
                        this.j.h = i2;
                        this.j.i = i;
                    } else {
                        this.j.h = i;
                        this.j.i = i2;
                    }
                }
            }
            this.j.g = this.c;
            if (!a(this.j)) {
                TXCLog.e("TXCVideoPreprocessor", "init failed!");
                return false;
            }
        } else if (!(i4 == this.j.k && i5 == this.j.l)) {
            this.j.k = i4;
            this.i.k = i4;
            this.j.l = i5;
            this.i.l = i5;
            this.h.a(i5);
        }
        return true;
    }

    private boolean a(c cVar) {
        this.i.d = cVar.b;
        this.i.e = cVar.c;
        this.i.m = cVar.m;
        this.i.g = cVar.d;
        this.i.f = cVar.e;
        this.i.h = (cVar.f + 360) % 360;
        this.i.b = cVar.h;
        this.i.c = cVar.i;
        this.i.a = cVar.j;
        this.i.j = cVar.a;
        this.i.i = cVar.g;
        this.i.k = cVar.k;
        this.i.l = cVar.l;
        if (this.h == null) {
            this.h = new b(this.a, cVar.a);
        }
        return this.h.a(this.i);
    }

    public synchronized void a() {
        if (this.h != null) {
            this.h.a();
        }
        this.j = null;
    }

    public synchronized void b(int i) {
        if (this.h != null) {
            this.h.c(i);
        }
        this.p.a("beautyStyle", i);
    }

    public synchronized void c(int i) {
        if (i > 9) {
            try {
                TXCLog.e("TXCVideoPreprocessor", "Beauty value too large! set max value 9");
                i = 9;
            } catch (Throwable th) {
            }
        } else if (i < 0) {
            TXCLog.e("TXCVideoPreprocessor", "Beauty < 0; set 0");
            i = 0;
        }
        if (this.h != null) {
            this.h.b(i);
        }
        this.p.a("beautyLevel", i);
    }

    public synchronized void d(int i) {
        if (i > 9) {
            try {
                TXCLog.e("TXCVideoPreprocessor", "Brightness value too large! set max value 9");
                i = 9;
            } catch (Throwable th) {
            }
        } else if (i < 0) {
            TXCLog.e("TXCVideoPreprocessor", "Brightness < 0; set 0");
            i = 0;
        }
        if (this.h != null) {
            this.h.d(i);
        }
        this.p.a("whiteLevel", i);
    }

    public synchronized void e(int i) {
        if (i > 9) {
            try {
                TXCLog.e("TXCVideoPreprocessor", "Ruddy value too large! set max value 9");
                i = 9;
            } catch (Throwable th) {
            }
        } else if (i < 0) {
            TXCLog.e("TXCVideoPreprocessor", "Ruddy < 0; set 0");
            i = 0;
        }
        if (this.h != null) {
            this.h.f(i);
        }
        this.p.a("ruddyLevel", i);
    }

    public void f(int i) {
        if (i > 9) {
            TXCLog.e("TXCVideoPreprocessor", "Brightness value too large! set max value 9");
            i = 9;
        } else if (i < 0) {
            TXCLog.e("TXCVideoPreprocessor", "Brightness < 0; set 0");
            i = 0;
        }
        if (this.h != null) {
            this.h.e(i);
        }
    }

    public synchronized void a(String str) {
        if (this.h != null) {
            this.h.a(str);
        }
    }

    public synchronized void b(boolean z) {
        if (this.h != null) {
            this.h.a(z);
        }
    }

    @TargetApi(18)
    public boolean a(String str, boolean z) {
        if (VERSION.SDK_INT < 18) {
            return false;
        }
        if (this.h != null) {
            this.h.a(str, z);
        }
        return true;
    }

    public synchronized void g(int i) {
        if (this.h != null) {
            this.h.g(i);
        }
        this.p.a("eyeBigScale", i);
    }

    public synchronized void h(int i) {
        if (this.h != null) {
            this.h.h(i);
        }
        this.p.a("faceSlimLevel", i);
    }

    public void i(int i) {
        if (this.h != null) {
            this.h.i(i);
        }
        this.p.a("faceVLevel", i);
    }

    public void j(int i) {
        if (this.h != null) {
            this.h.j(i);
        }
        this.p.a("faceShortLevel", i);
    }

    public void k(int i) {
        if (this.h != null) {
            this.h.k(i);
        }
        this.p.a("chinLevel", i);
    }

    public void l(int i) {
        if (this.h != null) {
            this.h.l(i);
        }
        this.p.a("noseSlimLevel", i);
    }

    public synchronized void a(float f) {
        if (this.h != null) {
            this.h.a(f);
        }
    }

    public synchronized void a(Bitmap bitmap) {
        if (this.h != null) {
            this.h.a(bitmap);
        }
    }

    public void a(float f, Bitmap bitmap, float f2, Bitmap bitmap2, float f3) {
        if (this.h != null) {
            this.h.a(f, bitmap, f2, bitmap2, f3);
        }
    }

    public synchronized void b(float f) {
        if (this.h != null) {
            this.h.b(f);
        }
    }

    public void setID(String str) {
        super.setID(str);
        setStatusValue(3001, this.p.a());
    }
}
