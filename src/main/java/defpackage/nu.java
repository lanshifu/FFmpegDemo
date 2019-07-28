package defpackage;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* compiled from: ViewPropertyAnimatorICS */
/* renamed from: nu */
class nu extends ns {
    private final WeakReference<ViewPropertyAnimator> a;

    nu(View view) {
        this.a = new WeakReference(view.animate());
    }

    public ns a(long j) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.a.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.setDuration(j);
        }
        return this;
    }

    public ns a(Interpolator interpolator) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.a.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.setInterpolator(interpolator);
        }
        return this;
    }

    public ns a(float f) {
        ViewPropertyAnimator viewPropertyAnimator = (ViewPropertyAnimator) this.a.get();
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.translationY(f);
        }
        return this;
    }
}
