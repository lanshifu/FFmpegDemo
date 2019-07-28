package defpackage;

import android.text.Layout.Alignment;
import com.google.android.exoplayer2.util.z;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: WebvttCssStyle */
/* renamed from: ln */
public final class ln {
    private String a;
    private String b;
    private List<String> c;
    private String d;
    private String e;
    private int f;
    private boolean g;
    private int h;
    private boolean i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private float o;
    private Alignment p;

    public ln() {
        a();
    }

    public void a() {
        this.a = "";
        this.b = "";
        this.c = Collections.emptyList();
        this.d = "";
        this.e = null;
        this.g = false;
        this.i = false;
        this.j = -1;
        this.k = -1;
        this.l = -1;
        this.m = -1;
        this.n = -1;
        this.p = null;
    }

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void a(String[] strArr) {
        this.c = Arrays.asList(strArr);
    }

    public void c(String str) {
        this.d = str;
    }

    public int a(String str, String str2, String[] strArr, String str3) {
        if (this.a.isEmpty() && this.b.isEmpty() && this.c.isEmpty() && this.d.isEmpty()) {
            return str2.isEmpty();
        }
        int a = ln.a(ln.a(ln.a(0, this.a, str, 1073741824), this.b, str2, 2), this.d, str3, 4);
        if (a == -1 || !Arrays.asList(strArr).containsAll(this.c)) {
            return 0;
        }
        return a + (this.c.size() * 4);
    }

    public int b() {
        if (this.l == -1 && this.m == -1) {
            return -1;
        }
        int i = 0;
        int i2 = this.l == 1 ? 1 : 0;
        if (this.m == 1) {
            i = 2;
        }
        return i2 | i;
    }

    public boolean c() {
        return this.j == 1;
    }

    public boolean d() {
        return this.k == 1;
    }

    public ln a(boolean z) {
        this.k = z;
        return this;
    }

    public ln b(boolean z) {
        this.l = z;
        return this;
    }

    public ln c(boolean z) {
        this.m = z;
        return this;
    }

    public String e() {
        return this.e;
    }

    public ln d(String str) {
        this.e = z.d(str);
        return this;
    }

    public int f() {
        if (this.g) {
            return this.f;
        }
        throw new IllegalStateException("Font color not defined");
    }

    public ln a(int i) {
        this.f = i;
        this.g = true;
        return this;
    }

    public boolean g() {
        return this.g;
    }

    public int h() {
        if (this.i) {
            return this.h;
        }
        throw new IllegalStateException("Background color not defined.");
    }

    public ln b(int i) {
        this.h = i;
        this.i = true;
        return this;
    }

    public boolean i() {
        return this.i;
    }

    public Alignment j() {
        return this.p;
    }

    public int k() {
        return this.n;
    }

    public float l() {
        return this.o;
    }

    private static int a(int i, String str, String str2, int i2) {
        if (!str.isEmpty()) {
            int i3 = -1;
            if (i != -1) {
                if (str.equals(str2)) {
                    i3 = i + i2;
                }
                return i3;
            }
        }
        return i;
    }
}
