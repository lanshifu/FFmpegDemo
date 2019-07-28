package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.zzhoujay.richtext.exceptions.ImageWrapperMultiSourceException;

/* compiled from: ImageWrapper */
/* renamed from: uw */
class uw {
    private final ud a;
    private final Bitmap b;
    private final int c;
    private final int d;

    private uw(ud udVar, Bitmap bitmap) {
        this.a = udVar;
        this.b = bitmap;
        if (udVar == null) {
            if (bitmap != null) {
                this.c = bitmap.getHeight();
                this.d = bitmap.getWidth();
                return;
            }
            throw new ImageWrapperMultiSourceException();
        } else if (bitmap == null) {
            this.c = udVar.a();
            this.d = udVar.b();
        } else {
            throw new ImageWrapperMultiSourceException();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public boolean a() {
        return this.a != null;
    }

    /* Access modifiers changed, original: 0000 */
    public ud b() {
        return this.a;
    }

    /* Access modifiers changed, original: 0000 */
    public Bitmap c() {
        return this.b;
    }

    /* Access modifiers changed, original: 0000 */
    public int d() {
        return this.c;
    }

    /* Access modifiers changed, original: 0000 */
    public int e() {
        return this.d;
    }

    /* Access modifiers changed, original: 0000 */
    public Drawable a(Resources resources) {
        if (this.a != null) {
            return this.a;
        }
        BitmapDrawable bitmapDrawable = new BitmapDrawable(resources, this.b);
        bitmapDrawable.setBounds(0, 0, this.b.getWidth(), this.b.getHeight());
        return bitmapDrawable;
    }

    static uw a(ud udVar) {
        return new uw(udVar, null);
    }

    static uw a(Bitmap bitmap) {
        return new uw(null, bitmap);
    }
}
