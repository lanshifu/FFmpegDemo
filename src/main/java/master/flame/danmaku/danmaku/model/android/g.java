package master.flame.danmaku.danmaku.model.android;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.Array;
import tv.cjump.jni.NativeBitmapFactory;

/* compiled from: DrawingCacheHolder */
public class g {
    public Canvas a;
    public Bitmap b;
    public Bitmap[][] c;
    public Object d;
    public int e;
    public int f;
    private int g;

    public void a(int i, int i2, int i3, boolean z, int i4) {
        Object obj = 1;
        if (z ? !(i == this.e && i2 == this.f) : i > this.e || i2 > this.f) {
            obj = null;
        }
        if (obj == null || this.b == null) {
            if (this.b != null) {
                a();
            }
            this.e = i;
            this.f = i2;
            Config config = Config.ARGB_4444;
            if (i4 == 32) {
                config = Config.ARGB_8888;
            }
            this.b = NativeBitmapFactory.a(i, i2, config);
            if (i3 > 0) {
                this.g = i3;
                this.b.setDensity(i3);
            }
            if (this.a == null) {
                this.a = new Canvas(this.b);
                this.a.setDensity(i3);
            } else {
                this.a.setBitmap(this.b);
            }
            return;
        }
        this.b.eraseColor(0);
        this.a.setBitmap(this.b);
        b();
    }

    public synchronized void a() {
        Bitmap bitmap = this.b;
        this.b = null;
        this.f = 0;
        this.e = 0;
        if (bitmap != null) {
            bitmap.recycle();
        }
        b();
        this.d = null;
    }

    @SuppressLint({"NewApi"})
    public void a(int i, int i2, int i3, int i4) {
        b();
        if (this.e > 0 && this.f > 0 && this.b != null) {
            if (this.e > i3 || this.f > i4) {
                i = Math.min(i3, i);
                i2 = Math.min(i4, i2);
                i3 = this.e / i;
                i4 = this.e % i;
                i = 1;
                i3 += i4 == 0 ? 0 : 1;
                i4 = this.f / i2;
                if (this.f % i2 == 0) {
                    i = 0;
                }
                i4 += i;
                i = this.e / i3;
                i2 = this.f / i4;
                Bitmap[][] bitmapArr = (Bitmap[][]) Array.newInstance(Bitmap.class, new int[]{i4, i3});
                if (this.a == null) {
                    this.a = new Canvas();
                    if (this.g > 0) {
                        this.a.setDensity(this.g);
                    }
                }
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                for (int i5 = 0; i5 < i4; i5++) {
                    for (int i6 = 0; i6 < i3; i6++) {
                        Bitmap[] bitmapArr2 = bitmapArr[i5];
                        Bitmap a = NativeBitmapFactory.a(i, i2, Config.ARGB_8888);
                        bitmapArr2[i6] = a;
                        if (this.g > 0) {
                            a.setDensity(this.g);
                        }
                        this.a.setBitmap(a);
                        int i7 = i6 * i;
                        int i8 = i5 * i2;
                        rect.set(i7, i8, i7 + i, i8 + i2);
                        rect2.set(0, 0, a.getWidth(), a.getHeight());
                        this.a.drawBitmap(this.b, rect, rect2, null);
                    }
                }
                this.a.setBitmap(this.b);
                this.c = bitmapArr;
            }
        }
    }

    private void b() {
        Bitmap[][] bitmapArr = this.c;
        this.c = (Bitmap[][]) null;
        if (bitmapArr != null) {
            for (int i = 0; i < bitmapArr.length; i++) {
                for (int i2 = 0; i2 < bitmapArr[i].length; i2++) {
                    if (bitmapArr[i][i2] != null) {
                        bitmapArr[i][i2].recycle();
                        bitmapArr[i][i2] = null;
                    }
                }
            }
        }
    }

    public final synchronized boolean a(Canvas canvas, float f, float f2, Paint paint) {
        if (this.c != null) {
            for (int i = 0; i < this.c.length; i++) {
                for (int i2 = 0; i2 < this.c[i].length; i2++) {
                    Bitmap bitmap = this.c[i][i2];
                    if (bitmap != null) {
                        float width = ((float) (bitmap.getWidth() * i2)) + f;
                        if (width <= ((float) canvas.getWidth())) {
                            if (((float) bitmap.getWidth()) + width >= CropImageView.DEFAULT_ASPECT_RATIO) {
                                float height = ((float) (bitmap.getHeight() * i)) + f2;
                                if (height <= ((float) canvas.getHeight())) {
                                    if (((float) bitmap.getHeight()) + height >= CropImageView.DEFAULT_ASPECT_RATIO) {
                                        canvas.drawBitmap(bitmap, width, height, paint);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        } else if (this.b == null) {
            return false;
        } else {
            canvas.drawBitmap(this.b, f, f2, paint);
            return true;
        }
    }
}
