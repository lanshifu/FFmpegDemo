package com.tencent.liteav.c;

import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.g;
import com.tencent.liteav.i.a.k;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ThumbnailConfig */
public class h {
    private static h a;
    private ArrayList<Long> b;
    private int c;
    private k d;
    private int e;
    private int f;
    private boolean g;

    public static h a() {
        if (a == null) {
            a = new h();
        }
        return a;
    }

    public void a(k kVar) {
        this.d = kVar;
    }

    public void a(ArrayList<Long> arrayList) {
        this.b = arrayList;
    }

    public void a(long j) {
        long j2 = 0;
        if (j >= 0) {
            this.b = new ArrayList();
            if (this.d != null && this.d.a > 0) {
                c a = c.a();
                long f = a.f();
                long g = a.g();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("calculateThumbnailList startTimeUs : ");
                stringBuilder.append(f);
                stringBuilder.append(", endTimeUs : ");
                stringBuilder.append(g);
                TXCLog.i("ThumbnailConfig", stringBuilder.toString());
                long j3 = g - f;
                if (j3 <= 0) {
                    j3 = j;
                }
                j3 /= (long) this.d.a;
                int i = 0;
                while (i < this.d.a) {
                    long j4 = (((long) i) * j3) + f;
                    if (g <= j2 || g >= j) {
                        if (j4 > j) {
                            j4 = j;
                        }
                    } else if (j4 > g) {
                        j4 = g;
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("calculateThumbnailList frameTime : ");
                    stringBuilder2.append(j4);
                    TXCLog.i("ThumbnailConfig", stringBuilder2.toString());
                    this.b.add(Long.valueOf(j4));
                    i++;
                    j2 = 0;
                }
            }
        }
    }

    public List<Long> b() {
        return this.b;
    }

    public int c() {
        if (this.d == null) {
            return 0;
        }
        return this.d.a;
    }

    public g d() {
        g gVar = new g();
        if (this.d != null) {
            gVar.a = this.d.b;
            gVar.b = this.d.c;
        } else if (!(this.f == 0 || this.e == 0)) {
            gVar.b = this.f;
            gVar.a = this.e;
        }
        return gVar;
    }

    public boolean e() {
        return this.b == null || this.b.size() <= 0;
    }

    public long f() {
        return ((Long) this.b.get(0)).longValue();
    }

    public long g() {
        this.c++;
        return ((Long) this.b.remove(0)).longValue();
    }

    public int h() {
        return this.c;
    }

    public void i() {
        this.c = 0;
        this.b = null;
        this.g = false;
    }

    public void j() {
        i();
        this.d = null;
    }

    public void a(int i) {
        this.e = i;
        if (this.d != null) {
            this.d.b = i;
        }
    }

    public void b(int i) {
        this.f = i;
        if (this.d != null) {
            this.d.c = i;
        }
    }

    public boolean k() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }
}
