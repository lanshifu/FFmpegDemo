package defpackage;

import com.contrarywind.view.WheelView;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.util.TimerTask;

/* compiled from: SmoothScrollTimerTask */
/* renamed from: ee */
public final class ee extends TimerTask {
    private int a = Filter.MAX;
    private int b = 0;
    private int c;
    private final WheelView d;

    public ee(WheelView wheelView, int i) {
        this.d = wheelView;
        this.c = i;
    }

    public final void run() {
        if (this.a == Filter.MAX) {
            this.a = this.c;
        }
        this.b = (int) (((float) this.a) * 0.1f);
        if (this.b == 0) {
            if (this.a < 0) {
                this.b = -1;
            } else {
                this.b = 1;
            }
        }
        if (Math.abs(this.a) <= 1) {
            this.d.a();
            this.d.getHandler().sendEmptyMessage(3000);
        } else {
            this.d.setTotalScrollY(this.d.getTotalScrollY() + ((float) this.b));
            if (!this.d.c()) {
                float itemHeight = this.d.getItemHeight();
                float itemsCount = ((float) ((this.d.getItemsCount() - 1) - this.d.getInitPosition())) * itemHeight;
                if (this.d.getTotalScrollY() <= ((float) (-this.d.getInitPosition())) * itemHeight || this.d.getTotalScrollY() >= itemsCount) {
                    this.d.setTotalScrollY(this.d.getTotalScrollY() - ((float) this.b));
                    this.d.a();
                    this.d.getHandler().sendEmptyMessage(3000);
                    return;
                }
            }
            this.d.getHandler().sendEmptyMessage(1000);
            this.a -= this.b;
        }
    }
}
