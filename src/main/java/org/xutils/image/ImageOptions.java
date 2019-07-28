package org.xutils.image;

import android.graphics.Bitmap.Config;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.lang.reflect.Field;
import org.xutils.common.util.DensityUtil;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;

public class ImageOptions {
    public static final ImageOptions DEFAULT = new ImageOptions();
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private boolean e = false;
    private int f = 0;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private boolean j = true;
    private Config k = Config.RGB_565;
    private boolean l = true;
    private int m = 0;
    private int n = 0;
    private Drawable o = null;
    private Drawable p = null;
    private boolean q = true;
    private ScaleType r = ScaleType.CENTER_INSIDE;
    private ScaleType s = ScaleType.CENTER_CROP;
    private boolean t = false;
    private Animation u = null;
    private boolean v = true;
    private ParamsBuilder w;

    public static class Builder {
        protected ImageOptions options;

        public Builder() {
            newImageOptions();
        }

        /* Access modifiers changed, original: protected */
        public void newImageOptions() {
            this.options = new ImageOptions();
        }

        public ImageOptions build() {
            return this.options;
        }

        public Builder setSize(int i, int i2) {
            this.options.c = i;
            this.options.d = i2;
            return this;
        }

        public Builder setCrop(boolean z) {
            this.options.e = z;
            return this;
        }

        public Builder setRadius(int i) {
            this.options.f = i;
            return this;
        }

        public Builder setSquare(boolean z) {
            this.options.g = z;
            return this;
        }

        public Builder setCircular(boolean z) {
            this.options.h = z;
            return this;
        }

        public Builder setAutoRotate(boolean z) {
            this.options.i = z;
            return this;
        }

        public Builder setConfig(Config config) {
            this.options.k = config;
            return this;
        }

        public Builder setIgnoreGif(boolean z) {
            this.options.l = z;
            return this;
        }

        public Builder setLoadingDrawableId(int i) {
            this.options.m = i;
            return this;
        }

        public Builder setLoadingDrawable(Drawable drawable) {
            this.options.o = drawable;
            return this;
        }

        public Builder setFailureDrawableId(int i) {
            this.options.n = i;
            return this;
        }

        public Builder setFailureDrawable(Drawable drawable) {
            this.options.p = drawable;
            return this;
        }

        public Builder setFadeIn(boolean z) {
            this.options.t = z;
            return this;
        }

        public Builder setAnimation(Animation animation) {
            this.options.u = animation;
            return this;
        }

        public Builder setPlaceholderScaleType(ScaleType scaleType) {
            this.options.r = scaleType;
            return this;
        }

        public Builder setImageScaleType(ScaleType scaleType) {
            this.options.s = scaleType;
            return this;
        }

        public Builder setForceLoadingDrawable(boolean z) {
            this.options.q = z;
            return this;
        }

        public Builder setUseMemCache(boolean z) {
            this.options.v = z;
            return this;
        }

        public Builder setParamsBuilder(ParamsBuilder paramsBuilder) {
            this.options.w = paramsBuilder;
            return this;
        }
    }

    public interface ParamsBuilder {
        RequestParams buildParams(RequestParams requestParams, ImageOptions imageOptions);
    }

    protected ImageOptions() {
    }

    /* Access modifiers changed, original: final */
    public final void a(ImageView imageView) {
        if (this.c <= 0 || this.d <= 0) {
            int screenWidth = DensityUtil.getScreenWidth();
            int screenHeight = DensityUtil.getScreenHeight();
            if (this.c < 0) {
                this.a = (screenWidth * 3) / 2;
                this.j = false;
            }
            if (this.d < 0) {
                this.b = (screenHeight * 3) / 2;
                this.j = false;
            }
            if (imageView != null || this.a > 0 || this.b > 0) {
                int i = this.a;
                int i2 = this.b;
                if (imageView != null) {
                    LayoutParams layoutParams = imageView.getLayoutParams();
                    if (layoutParams != null) {
                        if (i <= 0) {
                            if (layoutParams.width > 0) {
                                i = layoutParams.width;
                                if (this.c <= 0) {
                                    this.c = i;
                                }
                            } else if (layoutParams.width != -2) {
                                i = imageView.getWidth();
                            }
                        }
                        if (i2 <= 0) {
                            if (layoutParams.height > 0) {
                                i2 = layoutParams.height;
                                if (this.d <= 0) {
                                    this.d = i2;
                                }
                            } else if (layoutParams.height != -2) {
                                i2 = imageView.getHeight();
                            }
                        }
                    }
                    if (i <= 0) {
                        i = a(imageView, "mMaxWidth");
                    }
                    if (i2 <= 0) {
                        i2 = a(imageView, "mMaxHeight");
                    }
                }
                if (i > 0) {
                    screenWidth = i;
                }
                if (i2 > 0) {
                    screenHeight = i2;
                }
                this.a = screenWidth;
                this.b = screenHeight;
            } else {
                this.a = screenWidth;
                this.b = screenHeight;
            }
            return;
        }
        this.a = this.c;
        this.b = this.d;
    }

    public int getMaxWidth() {
        return this.a;
    }

    public int getMaxHeight() {
        return this.b;
    }

    public int getWidth() {
        return this.c;
    }

    public int getHeight() {
        return this.d;
    }

    public boolean isCrop() {
        return this.e;
    }

    public int getRadius() {
        return this.f;
    }

    public boolean isSquare() {
        return this.g;
    }

    public boolean isCircular() {
        return this.h;
    }

    public boolean isIgnoreGif() {
        return this.l;
    }

    public boolean isAutoRotate() {
        return this.i;
    }

    public boolean isCompress() {
        return this.j;
    }

    public Config getConfig() {
        return this.k;
    }

    public Drawable getLoadingDrawable(ImageView imageView) {
        if (this.o == null && this.m > 0 && imageView != null) {
            try {
                this.o = imageView.getResources().getDrawable(this.m);
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
            }
        }
        return this.o;
    }

    public Drawable getFailureDrawable(ImageView imageView) {
        if (this.p == null && this.n > 0 && imageView != null) {
            try {
                this.p = imageView.getResources().getDrawable(this.n);
            } catch (Throwable th) {
                LogUtil.e(th.getMessage(), th);
            }
        }
        return this.p;
    }

    public boolean isFadeIn() {
        return this.t;
    }

    public Animation getAnimation() {
        return this.u;
    }

    public ScaleType getPlaceholderScaleType() {
        return this.r;
    }

    public ScaleType getImageScaleType() {
        return this.s;
    }

    public boolean isForceLoadingDrawable() {
        return this.q;
    }

    public boolean isUseMemCache() {
        return this.v;
    }

    public ParamsBuilder getParamsBuilder() {
        return this.w;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ImageOptions imageOptions = (ImageOptions) obj;
        if (this.a != imageOptions.a || this.b != imageOptions.b || this.c != imageOptions.c || this.d != imageOptions.d || this.e != imageOptions.e || this.f != imageOptions.f || this.g != imageOptions.g || this.h != imageOptions.h || this.i != imageOptions.i || this.j != imageOptions.j) {
            return false;
        }
        if (this.k != imageOptions.k) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((((((((((((((((((this.a * 31) + this.b) * 31) + this.c) * 31) + this.d) * 31) + this.e) * 31) + this.f) * 31) + this.g) * 31) + this.h) * 31) + this.i) * 31) + this.j) * 31) + (this.k != null ? this.k.hashCode() : 0);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("_");
        stringBuilder.append(this.a);
        stringBuilder.append("_");
        stringBuilder.append(this.b);
        stringBuilder.append("_");
        stringBuilder.append(this.c);
        stringBuilder.append("_");
        stringBuilder.append(this.d);
        stringBuilder.append("_");
        stringBuilder.append(this.f);
        stringBuilder.append("_");
        stringBuilder.append(this.k);
        stringBuilder.append("_");
        stringBuilder.append(this.e);
        stringBuilder.append(this.g);
        stringBuilder.append(this.h);
        stringBuilder.append(this.i);
        stringBuilder.append(this.j);
        return stringBuilder.toString();
    }

    private static int a(ImageView imageView, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(imageView)).intValue();
            if (intValue <= 0 || intValue >= Filter.MAX) {
                return 0;
            }
            return intValue;
        } catch (Throwable unused) {
            return 0;
        }
    }
}
