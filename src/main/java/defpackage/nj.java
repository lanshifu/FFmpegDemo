package defpackage;

import android.view.animation.Interpolator;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.nl.a;
import java.util.ArrayList;

/* compiled from: FloatKeyframeSet */
/* renamed from: nj */
class nj extends nm {
    private float g;
    private float h;
    private float i;
    private boolean j = true;

    public nj(a... aVarArr) {
        super(aVarArr);
    }

    public Object a(float f) {
        return Float.valueOf(b(f));
    }

    /* renamed from: a */
    public nj clone() {
        ArrayList arrayList = this.e;
        int size = this.e.size();
        a[] aVarArr = new a[size];
        for (int i = 0; i < size; i++) {
            aVarArr[i] = (a) ((nl) arrayList.get(i)).clone();
        }
        return new nj(aVarArr);
    }

    public float b(float f) {
        a aVar;
        a aVar2;
        float e;
        float e2;
        float b;
        float b2;
        Interpolator c;
        if (this.a == 2) {
            if (this.j) {
                this.j = false;
                this.g = ((a) this.e.get(0)).e();
                this.h = ((a) this.e.get(1)).e();
                this.i = this.h - this.g;
            }
            if (this.d != null) {
                f = this.d.getInterpolation(f);
            }
            if (this.f == null) {
                return this.g + (f * this.i);
            }
            return ((Number) this.f.a(f, Float.valueOf(this.g), Float.valueOf(this.h))).floatValue();
        } else if (f <= CropImageView.DEFAULT_ASPECT_RATIO) {
            aVar = (a) this.e.get(0);
            aVar2 = (a) this.e.get(1);
            e = aVar.e();
            e2 = aVar2.e();
            b = aVar.b();
            b2 = aVar2.b();
            c = aVar2.c();
            if (c != null) {
                f = c.getInterpolation(f);
            }
            f = (f - b) / (b2 - b);
            return this.f == null ? e + (f * (e2 - e)) : ((Number) this.f.a(f, Float.valueOf(e), Float.valueOf(e2))).floatValue();
        } else if (f >= 1.0f) {
            aVar = (a) this.e.get(this.a - 2);
            aVar2 = (a) this.e.get(this.a - 1);
            e = aVar.e();
            e2 = aVar2.e();
            b = aVar.b();
            b2 = aVar2.b();
            c = aVar2.c();
            if (c != null) {
                f = c.getInterpolation(f);
            }
            f = (f - b) / (b2 - b);
            return this.f == null ? e + (f * (e2 - e)) : ((Number) this.f.a(f, Float.valueOf(e), Float.valueOf(e2))).floatValue();
        } else {
            aVar2 = (a) this.e.get(0);
            int i = 1;
            while (i < this.a) {
                a aVar3 = (a) this.e.get(i);
                if (f < aVar3.b()) {
                    Interpolator c2 = aVar3.c();
                    if (c2 != null) {
                        f = c2.getInterpolation(f);
                    }
                    f = (f - aVar2.b()) / (aVar3.b() - aVar2.b());
                    b = aVar2.e();
                    float e3 = aVar3.e();
                    return this.f == null ? b + (f * (e3 - b)) : ((Number) this.f.a(f, Float.valueOf(b), Float.valueOf(e3))).floatValue();
                }
                i++;
                aVar2 = aVar3;
            }
            return ((Number) ((nl) this.e.get(this.a - 1)).a()).floatValue();
        }
    }
}
