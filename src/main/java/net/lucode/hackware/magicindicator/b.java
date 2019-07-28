package net.lucode.hackware.magicindicator;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: NavigatorHelper */
public class b {
    private SparseBooleanArray a = new SparseBooleanArray();
    private SparseArray<Float> b = new SparseArray();
    private int c;
    private int d;
    private int e;
    private float f;
    private int g;
    private boolean h;
    private a i;

    /* compiled from: NavigatorHelper */
    public interface a {
        void a(int i, int i2);

        void a(int i, int i2, float f, boolean z);

        void b(int i, int i2);

        void b(int i, int i2, float f, boolean z);
    }

    public void a(int i, float f, int i2) {
        float f2 = ((float) i) + f;
        boolean z = this.f <= f2;
        if (this.g == 0) {
            for (i = 0; i < this.c; i++) {
                if (i != this.d) {
                    if (!this.a.get(i)) {
                        e(i);
                    }
                    if (((Float) this.b.get(i, Float.valueOf(CropImageView.DEFAULT_ASPECT_RATIO))).floatValue() != 1.0f) {
                        b(i, 1.0f, false, true);
                    }
                }
            }
            a(this.d, 1.0f, false, true);
            d(this.d);
        } else if (f2 != this.f) {
            Object obj;
            int i3 = i + 1;
            if (f == CropImageView.DEFAULT_ASPECT_RATIO && z) {
                i3 = i - 1;
                obj = null;
            } else {
                obj = 1;
            }
            int i4 = 0;
            while (i4 < this.c) {
                if (!(i4 == i || i4 == i3 || ((Float) this.b.get(i4, Float.valueOf(CropImageView.DEFAULT_ASPECT_RATIO))).floatValue() == 1.0f)) {
                    b(i4, 1.0f, z, true);
                }
                i4++;
            }
            float f3;
            if (obj == null) {
                f3 = 1.0f - f;
                b(i3, f3, true, false);
                a(i, f3, true, false);
            } else if (z) {
                b(i, f, true, false);
                a(i3, f, true, false);
            } else {
                f3 = 1.0f - f;
                b(i3, f3, false, false);
                a(i, f3, false, false);
            }
        } else {
            return;
        }
        this.f = f2;
    }

    private void a(int i, float f, boolean z, boolean z2) {
        if (this.h || i == this.d || this.g == 1 || z2) {
            if (this.i != null) {
                this.i.a(i, this.c, f, z);
            }
            this.b.put(i, Float.valueOf(1.0f - f));
        }
    }

    private void b(int i, float f, boolean z, boolean z2) {
        if (this.h || i == this.e || this.g == 1 || (((i == this.d - 1 || i == this.d + 1) && ((Float) this.b.get(i, Float.valueOf(CropImageView.DEFAULT_ASPECT_RATIO))).floatValue() != 1.0f) || z2)) {
            if (this.i != null) {
                this.i.b(i, this.c, f, z);
            }
            this.b.put(i, Float.valueOf(f));
        }
    }

    private void d(int i) {
        if (this.i != null) {
            this.i.a(i, this.c);
        }
        this.a.put(i, false);
    }

    private void e(int i) {
        if (this.i != null) {
            this.i.b(i, this.c);
        }
        this.a.put(i, true);
    }

    public void a(int i) {
        this.e = this.d;
        this.d = i;
        d(this.d);
        i = 0;
        while (i < this.c) {
            if (!(i == this.d || this.a.get(i))) {
                e(i);
            }
            i++;
        }
    }

    public void b(int i) {
        this.g = i;
    }

    public void setNavigatorScrollListener(a aVar) {
        this.i = aVar;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public int a() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
        this.a.clear();
        this.b.clear();
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.g;
    }
}
