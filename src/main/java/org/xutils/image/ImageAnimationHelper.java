package org.xutils.image;

import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.reflect.Method;
import org.xutils.common.util.LogUtil;

public final class ImageAnimationHelper {
    private static final Method a;

    static {
        Method declaredMethod;
        try {
            declaredMethod = Animation.class.getDeclaredMethod("clone", new Class[0]);
            declaredMethod.setAccessible(true);
        } catch (Throwable th) {
            LogUtil.w(th.getMessage(), th);
            declaredMethod = null;
        }
        a = declaredMethod;
    }

    private ImageAnimationHelper() {
    }

    public static void fadeInDisplay(ImageView imageView, Drawable drawable) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        imageView.setImageDrawable(drawable);
        imageView.startAnimation(alphaAnimation);
    }

    public static void animationDisplay(ImageView imageView, Drawable drawable, Animation animation) {
        imageView.setImageDrawable(drawable);
        if (a == null || animation == null) {
            imageView.startAnimation(animation);
            return;
        }
        try {
            imageView.startAnimation((Animation) a.invoke(animation, new Object[0]));
        } catch (Throwable unused) {
            imageView.startAnimation(animation);
        }
    }
}
