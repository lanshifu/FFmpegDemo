package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: DrawHelper */
/* renamed from: yw */
public class yw {
    public static Paint a = new Paint();
    public static Paint b = null;
    public static RectF c = new RectF();
    private static boolean d = true;
    private static boolean e = true;

    static {
        a.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        a.setColor(0);
    }

    public static void a(boolean z, boolean z2) {
        d = z;
        e = z2;
    }

    public static void a(Canvas canvas, String str) {
        if (b == null) {
            b = new Paint();
            b.setColor(-65536);
            b.setTextSize(30.0f);
        }
        int height = canvas.getHeight() - 50;
        yw.a(canvas, 10.0f, (float) (height - 50), (float) ((int) (b.measureText(str) + 20.0f)), (float) canvas.getHeight());
        canvas.drawText(str, 10.0f, (float) height, b);
    }

    public static void a(Canvas canvas) {
        if (!d) {
            c.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, (float) canvas.getWidth(), (float) canvas.getHeight());
            yw.a(canvas, c);
        } else if (e) {
            canvas.drawColor(0, Mode.CLEAR);
        } else {
            canvas.drawColor(0);
        }
    }

    public static void a(Canvas canvas, float f, float f2, float f3, float f4) {
        c.set(f, f2, f3, f4);
        yw.a(canvas, c);
    }

    private static void a(Canvas canvas, RectF rectF) {
        if (rectF.width() > CropImageView.DEFAULT_ASPECT_RATIO && rectF.height() > CropImageView.DEFAULT_ASPECT_RATIO) {
            canvas.drawRect(rectF, a);
        }
    }
}
