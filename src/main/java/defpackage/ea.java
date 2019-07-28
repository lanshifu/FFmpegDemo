package defpackage;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import com.contrarywind.view.WheelView;

/* compiled from: LoopViewGestureListener */
/* renamed from: ea */
public final class ea extends SimpleOnGestureListener {
    private final WheelView a;

    public ea(WheelView wheelView) {
        this.a = wheelView;
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.a.a(f2);
        return true;
    }
}
