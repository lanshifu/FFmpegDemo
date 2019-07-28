package com.zzhoujay.richtext;

import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.ua;
import defpackage.uk;

public class ImageHolder {
    private String a;
    private String b;
    private final int c;
    private int d;
    private int e;
    private ScaleType f;
    private int g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k = false;
    private ua l;
    private Drawable m;
    private Drawable n;
    private String o;

    public enum ScaleType {
        none(0),
        center(1),
        center_crop(2),
        center_inside(3),
        fit_center(4),
        fit_start(5),
        fit_end(6),
        fit_xy(7),
        fit_auto(8);
        
        int value;

        private ScaleType(int i) {
            this.value = i;
        }

        public int intValue() {
            return this.value;
        }

        public static ScaleType valueOf(int i) {
            return values()[i];
        }
    }

    public static class a {
        private int a;
        private int b;
        private float c = 1.0f;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public int a() {
            return (int) (this.c * ((float) this.a));
        }

        public int b() {
            return (int) (this.c * ((float) this.b));
        }

        public boolean c() {
            return this.c > CropImageView.DEFAULT_ASPECT_RATIO && this.a > 0 && this.b > 0;
        }
    }

    public ImageHolder(String str, int i, c cVar, TextView textView) {
        this.a = str;
        this.c = i;
        this.o = cVar.v == null ? "" : cVar.v.getClass().getName();
        l();
        this.i = cVar.e;
        if (cVar.c) {
            this.d = Filter.MAX;
            this.e = CheckView.UNCHECKED;
            this.f = ScaleType.fit_auto;
        } else {
            this.f = cVar.f;
            this.d = cVar.h;
            this.e = cVar.i;
        }
        this.j = cVar.l ^ 1;
        this.l = new ua(cVar.s);
        this.m = cVar.w.a(this, cVar, textView);
        this.n = cVar.x.a(this, cVar, textView);
    }

    private void l() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.o);
        stringBuilder.append(this.a);
        this.b = uk.a(stringBuilder.toString());
    }

    public String a() {
        return this.b;
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public void b(int i) {
        this.e = i;
    }

    public String d() {
        return this.a;
    }

    public ScaleType e() {
        return this.f;
    }

    public boolean f() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public boolean g() {
        return this.i;
    }

    public boolean h() {
        return this.j;
    }

    public void c(int i) {
        this.g = i;
    }

    public ua i() {
        return this.l;
    }

    public Drawable j() {
        return this.m;
    }

    public Drawable k() {
        return this.n;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageHolder)) {
            return false;
        }
        ImageHolder imageHolder = (ImageHolder) obj;
        if (this.c != imageHolder.c || this.d != imageHolder.d || this.e != imageHolder.e || this.f != imageHolder.f || this.g != imageHolder.g || this.h != imageHolder.h || this.i != imageHolder.i || this.j != imageHolder.j || this.k != imageHolder.k || !this.o.equals(imageHolder.o) || !this.a.equals(imageHolder.a) || !this.b.equals(imageHolder.b) || !this.l.equals(imageHolder.l)) {
            return false;
        }
        if (!this.m == null ? this.m.equals(imageHolder.m) : imageHolder.m == null) {
            return false;
        }
        if (this.n != null) {
            z = this.n.equals(imageHolder.n);
        } else if (imageHolder.n != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f.hashCode()) * 31) + this.g) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31) + this.k) * 31) + (this.l != null ? this.l.hashCode() : 0)) * 31) + (this.m != null ? this.m.hashCode() : 0)) * 31;
        if (this.n != null) {
            i = this.n.hashCode();
        }
        return ((hashCode + i) * 31) + this.o.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ImageHolder{source='");
        stringBuilder.append(this.a);
        stringBuilder.append('\'');
        stringBuilder.append(", key='");
        stringBuilder.append(this.b);
        stringBuilder.append('\'');
        stringBuilder.append(", position=");
        stringBuilder.append(this.c);
        stringBuilder.append(", width=");
        stringBuilder.append(this.d);
        stringBuilder.append(", height=");
        stringBuilder.append(this.e);
        stringBuilder.append(", scaleType=");
        stringBuilder.append(this.f);
        stringBuilder.append(", imageState=");
        stringBuilder.append(this.g);
        stringBuilder.append(", autoFix=");
        stringBuilder.append(this.h);
        stringBuilder.append(", autoPlay=");
        stringBuilder.append(this.i);
        stringBuilder.append(", show=");
        stringBuilder.append(this.j);
        stringBuilder.append(", isGif=");
        stringBuilder.append(this.k);
        stringBuilder.append(", borderHolder=");
        stringBuilder.append(this.l);
        stringBuilder.append(", placeHolder=");
        stringBuilder.append(this.m);
        stringBuilder.append(", errorImage=");
        stringBuilder.append(this.n);
        stringBuilder.append(", prefixCode=");
        stringBuilder.append(this.o);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
