package com.tomatolive.library.ui.view.sticker.core.util;

import android.graphics.Matrix;
import android.graphics.RectF;
import com.tomatolive.library.ui.view.sticker.core.homing.IMGHoming;
import com.yalantis.ucrop.view.CropImageView;

public class IMGUtils {
    private static final Matrix M = new Matrix();

    public static int inSampleSize(int i) {
        int i2 = 1;
        for (int i3 = i; i3 > 1; i3 >>= 1) {
            i2 <<= 1;
        }
        return i2 != i ? i2 << 1 : i2;
    }

    private IMGUtils() {
    }

    public static void center(RectF rectF, RectF rectF2) {
        rectF2.offset(rectF.centerX() - rectF2.centerX(), rectF.centerY() - rectF2.centerY());
    }

    public static void fitCenter(RectF rectF, RectF rectF2) {
        fitCenter(rectF, rectF2, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public static void fitCenter(RectF rectF, RectF rectF2, float f) {
        fitCenter(rectF, rectF2, f, f, f, f);
    }

    public static void fitCenter(RectF rectF, RectF rectF2, float f, float f2, float f3, float f4) {
        if (!rectF.isEmpty() && !rectF2.isEmpty()) {
            if (rectF.width() < f + f3) {
                f = CropImageView.DEFAULT_ASPECT_RATIO;
                f3 = CropImageView.DEFAULT_ASPECT_RATIO;
            }
            if (rectF.height() < f2 + f4) {
                f2 = CropImageView.DEFAULT_ASPECT_RATIO;
                f4 = CropImageView.DEFAULT_ASPECT_RATIO;
            }
            float min = Math.min(((rectF.width() - f) - f3) / rectF2.width(), ((rectF.height() - f2) - f4) / rectF2.height());
            rectF2.set(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, rectF2.width() * min, rectF2.height() * min);
            rectF2.offset((rectF.centerX() + ((f - f3) / 2.0f)) - rectF2.centerX(), (rectF.centerY() + ((f2 - f4) / 2.0f)) - rectF2.centerY());
        }
    }

    public static IMGHoming fitHoming(RectF rectF, RectF rectF2) {
        IMGHoming iMGHoming = new IMGHoming(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        if (rectF2.contains(rectF)) {
            return iMGHoming;
        }
        if (rectF2.width() < rectF.width() && rectF2.height() < rectF.height()) {
            iMGHoming.scale = Math.min(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        }
        RectF rectF3 = new RectF();
        M.setScale(iMGHoming.scale, iMGHoming.scale, rectF2.centerX(), rectF2.centerY());
        M.mapRect(rectF3, rectF2);
        if (rectF3.width() < rectF.width()) {
            iMGHoming.x += rectF.centerX() - rectF3.centerX();
        } else if (rectF3.left > rectF.left) {
            iMGHoming.x += rectF.left - rectF3.left;
        } else if (rectF3.right < rectF.right) {
            iMGHoming.x += rectF.right - rectF3.right;
        }
        if (rectF3.height() < rectF.height()) {
            iMGHoming.y += rectF.centerY() - rectF3.centerY();
        } else if (rectF3.top > rectF.top) {
            iMGHoming.y += rectF.top - rectF3.top;
        } else if (rectF3.bottom < rectF.bottom) {
            iMGHoming.y += rectF.bottom - rectF3.bottom;
        }
        return iMGHoming;
    }

    public static IMGHoming fitHoming(RectF rectF, RectF rectF2, float f, float f2) {
        IMGHoming iMGHoming = new IMGHoming(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        if (rectF2.contains(rectF)) {
            return iMGHoming;
        }
        if (rectF2.width() < rectF.width() && rectF2.height() < rectF.height()) {
            iMGHoming.scale = Math.min(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        }
        RectF rectF3 = new RectF();
        M.setScale(iMGHoming.scale, iMGHoming.scale, f, f2);
        M.mapRect(rectF3, rectF2);
        if (rectF3.width() < rectF.width()) {
            iMGHoming.x += rectF.centerX() - rectF3.centerX();
        } else if (rectF3.left > rectF.left) {
            iMGHoming.x += rectF.left - rectF3.left;
        } else if (rectF3.right < rectF.right) {
            iMGHoming.x += rectF.right - rectF3.right;
        }
        if (rectF3.height() < rectF.height()) {
            iMGHoming.y += rectF.centerY() - rectF3.centerY();
        } else if (rectF3.top > rectF.top) {
            iMGHoming.y += rectF.top - rectF3.top;
        } else if (rectF3.bottom < rectF.bottom) {
            iMGHoming.y += rectF.bottom - rectF3.bottom;
        }
        return iMGHoming;
    }

    public static IMGHoming fitHoming(RectF rectF, RectF rectF2, boolean z) {
        IMGHoming iMGHoming = new IMGHoming(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        if (rectF2.contains(rectF) && !z) {
            return iMGHoming;
        }
        if (z || (rectF2.width() < rectF.width() && rectF2.height() < rectF.height())) {
            iMGHoming.scale = Math.min(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        }
        RectF rectF3 = new RectF();
        M.setScale(iMGHoming.scale, iMGHoming.scale, rectF2.centerX(), rectF2.centerY());
        M.mapRect(rectF3, rectF2);
        if (rectF3.width() < rectF.width()) {
            iMGHoming.x += rectF.centerX() - rectF3.centerX();
        } else if (rectF3.left > rectF.left) {
            iMGHoming.x += rectF.left - rectF3.left;
        } else if (rectF3.right < rectF.right) {
            iMGHoming.x += rectF.right - rectF3.right;
        }
        if (rectF3.height() < rectF.height()) {
            iMGHoming.y += rectF.centerY() - rectF3.centerY();
        } else if (rectF3.top > rectF.top) {
            iMGHoming.y += rectF.top - rectF3.top;
        } else if (rectF3.bottom < rectF.bottom) {
            iMGHoming.y += rectF.bottom - rectF3.bottom;
        }
        return iMGHoming;
    }

    public static IMGHoming fillHoming(RectF rectF, RectF rectF2) {
        IMGHoming iMGHoming = new IMGHoming(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        if (rectF2.contains(rectF)) {
            return iMGHoming;
        }
        if (rectF2.width() < rectF.width() || rectF2.height() < rectF.height()) {
            iMGHoming.scale = Math.max(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        }
        RectF rectF3 = new RectF();
        M.setScale(iMGHoming.scale, iMGHoming.scale, rectF2.centerX(), rectF2.centerY());
        M.mapRect(rectF3, rectF2);
        if (rectF3.left > rectF.left) {
            iMGHoming.x += rectF.left - rectF3.left;
        } else if (rectF3.right < rectF.right) {
            iMGHoming.x += rectF.right - rectF3.right;
        }
        if (rectF3.top > rectF.top) {
            iMGHoming.y += rectF.top - rectF3.top;
        } else if (rectF3.bottom < rectF.bottom) {
            iMGHoming.y += rectF.bottom - rectF3.bottom;
        }
        return iMGHoming;
    }

    public static IMGHoming fillHoming(RectF rectF, RectF rectF2, float f, float f2) {
        IMGHoming iMGHoming = new IMGHoming(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        if (rectF2.contains(rectF)) {
            return iMGHoming;
        }
        if (rectF2.width() < rectF.width() || rectF2.height() < rectF.height()) {
            iMGHoming.scale = Math.max(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        }
        RectF rectF3 = new RectF();
        M.setScale(iMGHoming.scale, iMGHoming.scale, f, f2);
        M.mapRect(rectF3, rectF2);
        if (rectF3.left > rectF.left) {
            iMGHoming.x += rectF.left - rectF3.left;
        } else if (rectF3.right < rectF.right) {
            iMGHoming.x += rectF.right - rectF3.right;
        }
        if (rectF3.top > rectF.top) {
            iMGHoming.y += rectF.top - rectF3.top;
        } else if (rectF3.bottom < rectF.bottom) {
            iMGHoming.y += rectF.bottom - rectF3.bottom;
        }
        return iMGHoming;
    }

    public static IMGHoming fill(RectF rectF, RectF rectF2) {
        IMGHoming iMGHoming = new IMGHoming(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO);
        if (rectF.equals(rectF2)) {
            return iMGHoming;
        }
        iMGHoming.scale = Math.max(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
        RectF rectF3 = new RectF();
        M.setScale(iMGHoming.scale, iMGHoming.scale, rectF2.centerX(), rectF2.centerY());
        M.mapRect(rectF3, rectF2);
        iMGHoming.x += rectF.centerX() - rectF3.centerX();
        iMGHoming.y += rectF.centerY() - rectF3.centerY();
        return iMGHoming;
    }

    public static void rectFill(RectF rectF, RectF rectF2) {
        if (!rectF.equals(rectF2)) {
            float max = Math.max(rectF.width() / rectF2.width(), rectF.height() / rectF2.height());
            M.setScale(max, max, rectF2.centerX(), rectF2.centerY());
            M.mapRect(rectF2);
            if (rectF2.left > rectF.left) {
                rectF2.left = rectF.left;
            } else if (rectF2.right < rectF.right) {
                rectF2.right = rectF.right;
            }
            if (rectF2.top > rectF.top) {
                rectF2.top = rectF.top;
            } else if (rectF2.bottom < rectF.bottom) {
                rectF2.bottom = rectF.bottom;
            }
        }
    }
}
