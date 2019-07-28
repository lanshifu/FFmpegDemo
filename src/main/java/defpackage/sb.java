package defpackage;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: RotationGestureDetector */
/* renamed from: sb */
public class sb {
    private float a;
    private float b;
    private float c;
    private float d;
    private int e = -1;
    private int f = -1;
    private float g;
    private boolean h;
    private a i;

    /* compiled from: RotationGestureDetector */
    /* renamed from: sb$a */
    public interface a {
        boolean a(sb sbVar);
    }

    /* compiled from: RotationGestureDetector */
    /* renamed from: sb$b */
    public static class b implements a {
        public boolean a(sb sbVar) {
            return false;
        }
    }

    public sb(a aVar) {
        this.i = aVar;
    }

    public float a() {
        return this.g;
    }

    public boolean a(@NonNull MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.c = motionEvent.getX();
                this.d = motionEvent.getY();
                this.e = motionEvent2.findPointerIndex(motionEvent2.getPointerId(0));
                this.g = CropImageView.DEFAULT_ASPECT_RATIO;
                this.h = true;
                break;
            case 1:
                this.e = -1;
                break;
            case 2:
                if (!(this.e == -1 || this.f == -1 || motionEvent.getPointerCount() <= this.f)) {
                    float x = motionEvent2.getX(this.e);
                    float y = motionEvent2.getY(this.e);
                    float x2 = motionEvent2.getX(this.f);
                    float y2 = motionEvent2.getY(this.f);
                    if (this.h) {
                        this.g = CropImageView.DEFAULT_ASPECT_RATIO;
                        this.h = false;
                    } else {
                        a(this.a, this.b, this.c, this.d, x2, y2, x, y);
                    }
                    if (this.i != null) {
                        this.i.a(this);
                    }
                    this.a = x2;
                    this.b = y2;
                    this.c = x;
                    this.d = y;
                    break;
                }
            case 5:
                this.a = motionEvent.getX();
                this.b = motionEvent.getY();
                this.f = motionEvent2.findPointerIndex(motionEvent2.getPointerId(motionEvent.getActionIndex()));
                this.g = CropImageView.DEFAULT_ASPECT_RATIO;
                this.h = true;
                break;
            case 6:
                this.f = -1;
                break;
        }
        return true;
    }

    private float a(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        return a((float) Math.toDegrees((double) ((float) Math.atan2((double) (f2 - f4), (double) (f - f3)))), (float) Math.toDegrees((double) ((float) Math.atan2((double) (f6 - f8), (double) (f5 - f7)))));
    }

    private float a(float f, float f2) {
        this.g = (f2 % 360.0f) - (f % 360.0f);
        if (this.g < -180.0f) {
            this.g += 360.0f;
        } else if (this.g > 180.0f) {
            this.g -= 360.0f;
        }
        return this.g;
    }
}
