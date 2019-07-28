package org.xutils.image;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.yalantis.ucrop.view.CropImageView;
import org.xutils.common.util.LogUtil;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

public class GifDrawable extends Drawable implements Animatable, Runnable {
    private int a;
    private int b = IjkMediaCodecInfo.RANK_SECURE;
    private volatile boolean c;
    private final Movie d;
    private final int e;
    private final long f = SystemClock.uptimeMillis();

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public GifDrawable(Movie movie, int i) {
        this.d = movie;
        this.a = i;
        this.e = movie.duration();
    }

    public int getDuration() {
        return this.e;
    }

    public Movie getMovie() {
        return this.d;
    }

    public int getByteCount() {
        if (this.a == 0) {
            this.a = ((this.d.width() * this.d.height()) * 3) * 5;
        }
        return this.a;
    }

    public int getRate() {
        return this.b;
    }

    public void setRate(int i) {
        this.b = i;
    }

    public void draw(Canvas canvas) {
        try {
            this.d.setTime(this.e > 0 ? ((int) (SystemClock.uptimeMillis() - this.f)) % this.e : 0);
            this.d.draw(canvas, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
            start();
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }

    public void start() {
        if (!isRunning()) {
            this.c = true;
            run();
        }
    }

    public void stop() {
        if (isRunning()) {
            unscheduleSelf(this);
        }
    }

    public boolean isRunning() {
        return this.c && this.e > 0;
    }

    public void run() {
        if (this.e > 0) {
            invalidateSelf();
            scheduleSelf(this, SystemClock.uptimeMillis() + ((long) this.b));
        }
    }

    public int getIntrinsicWidth() {
        return this.d.width();
    }

    public int getIntrinsicHeight() {
        return this.d.height();
    }

    public int getOpacity() {
        return this.d.isOpaque() ? -1 : -3;
    }
}
