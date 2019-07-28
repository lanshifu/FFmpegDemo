package defpackage;

import android.view.animation.Interpolator;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.nl.a;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: KeyframeSet */
/* renamed from: nm */
class nm {
    int a;
    nl b;
    nl c;
    Interpolator d;
    ArrayList<nl> e = new ArrayList();
    no f;

    public nm(nl... nlVarArr) {
        this.a = nlVarArr.length;
        this.e.addAll(Arrays.asList(nlVarArr));
        this.b = (nl) this.e.get(0);
        this.c = (nl) this.e.get(this.a - 1);
        this.d = this.c.c();
    }

    public static nm a(float... fArr) {
        int length = fArr.length;
        a[] aVarArr = new a[Math.max(length, 2)];
        int i = 1;
        if (length == 1) {
            aVarArr[0] = (a) nl.a((float) CropImageView.DEFAULT_ASPECT_RATIO);
            aVarArr[1] = (a) nl.a(1.0f, fArr[0]);
        } else {
            aVarArr[0] = (a) nl.a(CropImageView.DEFAULT_ASPECT_RATIO, fArr[0]);
            while (i < length) {
                aVarArr[i] = (a) nl.a(((float) i) / ((float) (length - 1)), fArr[i]);
                i++;
            }
        }
        return new nj(aVarArr);
    }

    public void a(no noVar) {
        this.f = noVar;
    }

    /* renamed from: b */
    public nm clone() {
        ArrayList arrayList = this.e;
        int size = this.e.size();
        nl[] nlVarArr = new nl[size];
        for (int i = 0; i < size; i++) {
            nlVarArr[i] = ((nl) arrayList.get(i)).clone();
        }
        return new nm(nlVarArr);
    }

    public Object a(float f) {
        if (this.a == 2) {
            if (this.d != null) {
                f = this.d.getInterpolation(f);
            }
            return this.f.a(f, this.b.a(), this.c.a());
        }
        int i = 1;
        nl nlVar;
        Interpolator c;
        float b;
        if (f <= CropImageView.DEFAULT_ASPECT_RATIO) {
            nlVar = (nl) this.e.get(1);
            c = nlVar.c();
            if (c != null) {
                f = c.getInterpolation(f);
            }
            b = this.b.b();
            return this.f.a((f - b) / (nlVar.b() - b), this.b.a(), nlVar.a());
        } else if (f >= 1.0f) {
            nlVar = (nl) this.e.get(this.a - 2);
            c = this.c.c();
            if (c != null) {
                f = c.getInterpolation(f);
            }
            b = nlVar.b();
            return this.f.a((f - b) / (this.c.b() - b), nlVar.a(), this.c.a());
        } else {
            nlVar = this.b;
            while (i < this.a) {
                nl nlVar2 = (nl) this.e.get(i);
                if (f < nlVar2.b()) {
                    Interpolator c2 = nlVar2.c();
                    if (c2 != null) {
                        f = c2.getInterpolation(f);
                    }
                    float b2 = nlVar.b();
                    return this.f.a((f - b2) / (nlVar2.b() - b2), nlVar.a(), nlVar2.a());
                }
                i++;
                nlVar = nlVar2;
            }
            return this.c.a();
        }
    }

    public String toString() {
        String str = " ";
        for (int i = 0; i < this.a; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(((nl) this.e.get(i)).a());
            stringBuilder.append("  ");
            str = stringBuilder.toString();
        }
        return str;
    }
}
