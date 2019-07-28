package defpackage;

import android.support.annotation.ColorInt;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: DrawableBorderHolder */
/* renamed from: ua */
public class ua {
    private boolean a;
    private float b;
    @ColorInt
    private int c;
    private float d;

    ua(boolean z, float f, @ColorInt int i, float f2) {
        this.a = z;
        this.b = f;
        this.c = i;
        this.d = f2;
    }

    public ua() {
        this(false, 5.0f, -16777216, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public ua(ua uaVar) {
        this(uaVar.a, uaVar.b, uaVar.c, uaVar.d);
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a() {
        return this.a;
    }

    /* Access modifiers changed, original: 0000 */
    public float b() {
        return this.b;
    }

    /* Access modifiers changed, original: 0000 */
    @ColorInt
    public int c() {
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public float d() {
        return this.d;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(ua uaVar) {
        this.a = uaVar.a;
        this.b = uaVar.b;
        this.c = uaVar.c;
        this.d = uaVar.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ua)) {
            return false;
        }
        ua uaVar = (ua) obj;
        return this.a == uaVar.a && Float.compare(uaVar.b, this.b) == 0 && this.c == uaVar.c && Float.compare(uaVar.d, this.d) == 0;
    }

    public int hashCode() {
        int i = 0;
        int floatToIntBits = ((((this.a * 31) + (this.b != CropImageView.DEFAULT_ASPECT_RATIO ? Float.floatToIntBits(this.b) : 0)) * 31) + this.c) * 31;
        if (this.d != CropImageView.DEFAULT_ASPECT_RATIO) {
            i = Float.floatToIntBits(this.d);
        }
        return floatToIntBits + i;
    }
}
