package defpackage;

import com.contrarywind.view.WheelView;
import com.tomatolive.library.http.exception.ExceptionEngine;
import com.yalantis.ucrop.view.CropImageView;
import java.util.TimerTask;

/* compiled from: InertiaTimerTask */
/* renamed from: ec */
public final class ec extends TimerTask {
    private float a = 2.14748365E9f;
    private final float b;
    private final WheelView c;

    public ec(WheelView wheelView, float f) {
        this.c = wheelView;
        this.b = f;
    }

    public final void run() {
        if (this.a == 2.14748365E9f) {
            float f = 2000.0f;
            if (Math.abs(this.b) > 2000.0f) {
                if (this.b <= CropImageView.DEFAULT_ASPECT_RATIO) {
                    f = -2000.0f;
                }
                this.a = f;
            } else {
                this.a = this.b;
            }
        }
        if (Math.abs(this.a) < CropImageView.DEFAULT_ASPECT_RATIO || Math.abs(this.a) > 20.0f) {
            float f2 = (float) ((int) (this.a / 100.0f));
            this.c.setTotalScrollY(this.c.getTotalScrollY() - f2);
            if (!this.c.c()) {
                float itemHeight = this.c.getItemHeight();
                float f3 = ((float) (-this.c.getInitPosition())) * itemHeight;
                float itemsCount = ((float) ((this.c.getItemsCount() - 1) - this.c.getInitPosition())) * itemHeight;
                double totalScrollY = (double) this.c.getTotalScrollY();
                double d = (double) itemHeight;
                Double.isNaN(d);
                d *= 0.25d;
                Double.isNaN(totalScrollY);
                if (totalScrollY - d < ((double) f3)) {
                    f3 = this.c.getTotalScrollY() + f2;
                } else {
                    totalScrollY = (double) this.c.getTotalScrollY();
                    Double.isNaN(totalScrollY);
                    if (totalScrollY + d > ((double) itemsCount)) {
                        itemsCount = this.c.getTotalScrollY() + f2;
                    }
                }
                if (this.c.getTotalScrollY() <= f3) {
                    this.a = 40.0f;
                    this.c.setTotalScrollY((float) ((int) f3));
                } else if (this.c.getTotalScrollY() >= itemsCount) {
                    this.c.setTotalScrollY((float) ((int) itemsCount));
                    this.a = -40.0f;
                }
            }
            if (this.a < CropImageView.DEFAULT_ASPECT_RATIO) {
                this.a += 20.0f;
            } else {
                this.a -= 20.0f;
            }
            this.c.getHandler().sendEmptyMessage(1000);
            return;
        }
        this.c.a();
        this.c.getHandler().sendEmptyMessage(ExceptionEngine.SERVER_ERROR);
    }
}
