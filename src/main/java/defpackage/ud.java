package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.widget.TextView;

/* compiled from: GifDrawable */
/* renamed from: ud */
public class ud extends Drawable {
    private Movie a;
    private long b;
    private int c;
    private int d;
    private boolean e;
    private TextView f;
    private float g = 1.0f;
    private float h = 1.0f;
    private Paint i = new Paint();
    private Handler j = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 855 && ud.this.e && ud.this.f != null) {
                ud.this.f.invalidate();
                sendEmptyMessageDelayed(855, 33);
            }
        }
    };

    public int getOpacity() {
        return -3;
    }

    public ud(Movie movie, int i, int i2) {
        this.a = movie;
        this.c = i;
        this.d = i2;
        setBounds(0, 0, i2, i);
    }

    public void draw(@NonNull Canvas canvas) {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.b == 0) {
            this.b = uptimeMillis;
        }
        if (this.a != null) {
            int duration = this.a.duration();
            if (duration == 0) {
                duration = 1000;
            }
            this.a.setTime((int) ((uptimeMillis - this.b) % ((long) duration)));
            Rect bounds = getBounds();
            canvas.scale(this.g, this.h);
            this.a.draw(canvas, (float) bounds.left, (float) bounds.top);
        }
    }

    public void setBounds(@NonNull Rect rect) {
        super.setBounds(rect);
        c();
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        c();
    }

    private void c() {
        this.g = ((float) getBounds().width()) / ((float) this.d);
        this.h = ((float) getBounds().height()) / ((float) this.c);
    }

    public void a(TextView textView) {
        this.e = true;
        this.f = textView;
        this.j.sendEmptyMessage(855);
    }

    public void setAlpha(int i) {
        this.i.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.i.setColorFilter(colorFilter);
    }

    public int getIntrinsicHeight() {
        return this.c;
    }

    public int getIntrinsicWidth() {
        return this.d;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }
}
