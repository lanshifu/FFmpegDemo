package com.tomatolive.library.utils;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.util.Property;
import android.view.View;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.sl;
import defpackage.sm;
import defpackage.sn;
import defpackage.sr;

/* compiled from: AnimUtils */
public class a {
    public static void a(final View view) {
        sm b = sr.c().b();
        b.a(1.100000023841858d);
        b.a(new sn(40.0d, 7.0d));
        b.a(new sl() {
            public void a(sm smVar) {
                super.a(smVar);
                float b = (float) smVar.b();
                view.setScaleX(b);
                view.setScaleY(b);
            }
        });
        b.b(1.0d);
    }

    public static void b(final View view) {
        sm b = sr.c().b();
        b.a(1.5d);
        b.a(new sn(50.0d, 3.0d));
        b.a(new sl() {
            public void a(sm smVar) {
                super.a(smVar);
                float b = (float) smVar.b();
                view.setScaleX(b);
                view.setScaleY(b);
            }
        });
        b.b(1.0d);
    }

    public static void c(View view) {
        d(view).start();
    }

    public static ObjectAnimator d(View view) {
        return a(view, 1.0f);
    }

    public static ObjectAnimator a(View view, float f) {
        PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe(View.SCALE_X, new Keyframe[]{Keyframe.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f), Keyframe.ofFloat(0.1f, 0.9f), Keyframe.ofFloat(0.2f, 0.9f), Keyframe.ofFloat(0.3f, 1.1f), Keyframe.ofFloat(0.4f, 1.1f), Keyframe.ofFloat(0.5f, 1.1f), Keyframe.ofFloat(0.6f, 1.1f), Keyframe.ofFloat(0.7f, 1.1f), Keyframe.ofFloat(0.8f, 1.1f), Keyframe.ofFloat(0.9f, 1.1f), Keyframe.ofFloat(1.0f, 1.0f)});
        PropertyValuesHolder ofKeyframe2 = PropertyValuesHolder.ofKeyframe(View.SCALE_Y, new Keyframe[]{Keyframe.ofFloat(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f), Keyframe.ofFloat(0.1f, 0.9f), Keyframe.ofFloat(0.2f, 0.9f), Keyframe.ofFloat(0.3f, 1.1f), Keyframe.ofFloat(0.4f, 1.1f), Keyframe.ofFloat(0.5f, 1.1f), Keyframe.ofFloat(0.6f, 1.1f), Keyframe.ofFloat(0.7f, 1.1f), Keyframe.ofFloat(0.8f, 1.1f), Keyframe.ofFloat(0.9f, 1.1f), Keyframe.ofFloat(1.0f, 1.0f)});
        Property property = View.ROTATION;
        r2 = new Keyframe[11];
        float f2 = -3.0f * f;
        r2[1] = Keyframe.ofFloat(0.1f, f2);
        r2[2] = Keyframe.ofFloat(0.2f, f2);
        float f3 = f * 3.0f;
        r2[3] = Keyframe.ofFloat(0.3f, f3);
        r2[4] = Keyframe.ofFloat(0.4f, f2);
        r2[5] = Keyframe.ofFloat(0.5f, f3);
        r2[6] = Keyframe.ofFloat(0.6f, f2);
        r2[7] = Keyframe.ofFloat(0.7f, f3);
        r2[8] = Keyframe.ofFloat(0.8f, f2);
        r2[9] = Keyframe.ofFloat(0.9f, f3);
        r2[10] = Keyframe.ofFloat(1.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        PropertyValuesHolder ofKeyframe3 = PropertyValuesHolder.ofKeyframe(property, r2);
        return ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{ofKeyframe, ofKeyframe2, ofKeyframe3}).setDuration(1000);
    }
}
