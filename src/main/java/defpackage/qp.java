package defpackage;

import android.widget.ImageView.ScaleType;
import com.yalantis.ucrop.view.CropImageView;
import kotlin.jvm.internal.f;

/* compiled from: SVGAScaleInfo.kt */
/* renamed from: qp */
public final class qp {
    private float a;
    private float b;
    private float c = 1.0f;
    private float d = 1.0f;
    private float e = 1.0f;
    private boolean f;

    public final float a() {
        return this.a;
    }

    public final float b() {
        return this.b;
    }

    public final float c() {
        return this.c;
    }

    public final float d() {
        return this.d;
    }

    public final boolean e() {
        return this.f;
    }

    private final void f() {
        this.a = CropImageView.DEFAULT_ASPECT_RATIO;
        this.b = CropImageView.DEFAULT_ASPECT_RATIO;
        this.c = 1.0f;
        this.d = 1.0f;
        this.e = 1.0f;
        this.f = false;
    }

    public final void a(float f, float f2, float f3, float f4, ScaleType scaleType) {
        f.b(scaleType, "scaleType");
        if (f != CropImageView.DEFAULT_ASPECT_RATIO && f2 != CropImageView.DEFAULT_ASPECT_RATIO && f3 != CropImageView.DEFAULT_ASPECT_RATIO && f4 != CropImageView.DEFAULT_ASPECT_RATIO) {
            f();
            float f5 = (f - f3) / 2.0f;
            float f6 = (f2 - f4) / 2.0f;
            float f7 = f3 / f4;
            float f8 = f / f2;
            float f9 = f2 / f4;
            float f10 = f / f3;
            boolean z = false;
            switch (qq.a[scaleType.ordinal()]) {
                case 1:
                    this.a = f5;
                    this.b = f6;
                    break;
                case 2:
                    if (f7 <= f8) {
                        this.e = f10;
                        this.f = true;
                        this.c = f10;
                        this.d = f10;
                        this.b = (f2 - (f4 * f10)) / 2.0f;
                        break;
                    }
                    this.e = f9;
                    this.f = false;
                    this.c = f9;
                    this.d = f9;
                    this.a = (f - (f3 * f9)) / 2.0f;
                    break;
                case 3:
                    if (f3 >= f || f4 >= f2) {
                        if (f7 <= f8) {
                            this.e = f9;
                            this.f = false;
                            this.c = f9;
                            this.d = f9;
                            this.a = (f - (f3 * f9)) / 2.0f;
                            break;
                        }
                        this.e = f10;
                        this.f = true;
                        this.c = f10;
                        this.d = f10;
                        this.b = (f2 - (f4 * f10)) / 2.0f;
                        break;
                    }
                    this.a = f5;
                    this.b = f6;
                    break;
                    break;
                case 4:
                    if (f7 <= f8) {
                        this.e = f9;
                        this.f = false;
                        this.c = f9;
                        this.d = f9;
                        this.a = (f - (f3 * f9)) / 2.0f;
                        break;
                    }
                    this.e = f10;
                    this.f = true;
                    this.c = f10;
                    this.d = f10;
                    this.b = (f2 - (f4 * f10)) / 2.0f;
                    break;
                case 5:
                    if (f7 <= f8) {
                        this.e = f9;
                        this.f = false;
                        this.c = f9;
                        this.d = f9;
                        break;
                    }
                    this.e = f10;
                    this.f = true;
                    this.c = f10;
                    this.d = f10;
                    break;
                case 6:
                    if (f7 <= f8) {
                        this.e = f9;
                        this.f = false;
                        this.c = f9;
                        this.d = f9;
                        this.a = f - (f3 * f9);
                        break;
                    }
                    this.e = f10;
                    this.f = true;
                    this.c = f10;
                    this.d = f10;
                    this.b = f2 - (f4 * f10);
                    break;
                case 7:
                    this.e = Math.max(f10, f9);
                    if (f10 > f9) {
                        z = true;
                    }
                    this.f = z;
                    this.c = f10;
                    this.d = f9;
                    break;
                default:
                    this.e = f10;
                    this.f = true;
                    this.c = f10;
                    this.d = f10;
                    break;
            }
        }
    }
}
