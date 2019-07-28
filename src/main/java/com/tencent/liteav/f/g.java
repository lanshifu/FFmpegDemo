package com.tencent.liteav.f;

import com.tencent.liteav.i.a.i;
import java.util.List;

/* compiled from: SpeedFilterChain */
public class g {
    private static g a;
    private List<i> b;

    public float a(int i) {
        switch (i) {
            case 0:
                return 0.25f;
            case 1:
                return 0.5f;
            case 2:
                return 1.0f;
            case 3:
                return 1.5f;
            case 4:
                return 2.0f;
            default:
                return 1.0f;
        }
    }

    public static g a() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    private g() {
    }

    public void a(List<i> list) {
        this.b = list;
    }

    public List<i> b() {
        return this.b;
    }

    public boolean c() {
        if (this.b == null || this.b.size() == 0) {
            return false;
        }
        for (i iVar : this.b) {
            if (iVar.a != 2) {
                return true;
            }
        }
        return false;
    }

    public float a(long j) {
        float f = 1.0f;
        if (this.b == null || this.b.size() == 0) {
            return 1.0f;
        }
        for (i iVar : this.b) {
            if (j > iVar.b * 1000 && j < iVar.c * 1000) {
                f = a(iVar.a);
                break;
            }
        }
        return f;
    }

    public void d() {
        if (this.b != null) {
            this.b.clear();
        }
        this.b = null;
    }

    public long b(long j) {
        List b = a().b();
        for (int i = 0; i < b.size(); i++) {
            i iVar = (i) b.get(i);
            long j2 = (iVar.c - iVar.b) * 1000;
            j = (j + ((long) (((float) j2) / a(iVar.a)))) - j2;
        }
        return j;
    }
}
