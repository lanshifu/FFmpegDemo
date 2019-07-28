package defpackage;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

/* compiled from: TrackRectDrawable */
/* renamed from: gd */
public class gd extends gb {
    public gd(@NonNull ColorStateList colorStateList) {
        super(colorStateList);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Canvas canvas, Paint paint) {
        canvas.drawRect(getBounds(), paint);
    }
}
