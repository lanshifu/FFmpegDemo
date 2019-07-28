package net.lucode.hackware.magicindicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class MagicIndicator extends FrameLayout {
    private aag a;

    public MagicIndicator(Context context) {
        super(context);
    }

    public MagicIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(int i, float f, int i2) {
        if (this.a != null) {
            this.a.a(i, f, i2);
        }
    }

    public void a(int i) {
        if (this.a != null) {
            this.a.a(i);
        }
    }

    public void b(int i) {
        if (this.a != null) {
            this.a.b(i);
        }
    }

    public aag getNavigator() {
        return this.a;
    }

    public void setNavigator(aag aag) {
        if (this.a != aag) {
            if (this.a != null) {
                this.a.b();
            }
            this.a = aag;
            removeAllViews();
            if (this.a instanceof View) {
                addView((View) this.a, new LayoutParams(-1, -1));
                this.a.a();
            }
        }
    }
}
