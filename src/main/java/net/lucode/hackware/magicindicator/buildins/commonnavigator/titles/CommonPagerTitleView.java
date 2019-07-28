package net.lucode.hackware.magicindicator.buildins.commonnavigator.titles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class CommonPagerTitleView extends FrameLayout implements aak {
    private b a;
    private a b;

    public interface a {
        int a();

        int b();

        int c();

        int d();
    }

    public interface b {
        void a(int i, int i2);

        void a(int i, int i2, float f, boolean z);

        void b(int i, int i2);

        void b(int i, int i2, float f, boolean z);
    }

    public CommonPagerTitleView(Context context) {
        super(context);
    }

    public void onSelected(int i, int i2) {
        if (this.a != null) {
            this.a.a(i, i2);
        }
    }

    public void onDeselected(int i, int i2) {
        if (this.a != null) {
            this.a.b(i, i2);
        }
    }

    public void onLeave(int i, int i2, float f, boolean z) {
        if (this.a != null) {
            this.a.a(i, i2, f, z);
        }
    }

    public void onEnter(int i, int i2, float f, boolean z) {
        if (this.a != null) {
            this.a.b(i, i2, f, z);
        }
    }

    public int getContentLeft() {
        if (this.b != null) {
            return this.b.a();
        }
        return getLeft();
    }

    public int getContentTop() {
        if (this.b != null) {
            return this.b.b();
        }
        return getTop();
    }

    public int getContentRight() {
        if (this.b != null) {
            return this.b.c();
        }
        return getRight();
    }

    public int getContentBottom() {
        if (this.b != null) {
            return this.b.d();
        }
        return getBottom();
    }

    public void setContentView(View view) {
        setContentView(view, null);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        removeAllViews();
        if (view != null) {
            ViewGroup.LayoutParams layoutParams2;
            if (layoutParams2 == null) {
                layoutParams2 = new LayoutParams(-1, -1);
            }
            addView(view, layoutParams2);
        }
    }

    public void setContentView(int i) {
        setContentView(LayoutInflater.from(getContext()).inflate(i, null), null);
    }

    public b getOnPagerTitleChangeListener() {
        return this.a;
    }

    public void setOnPagerTitleChangeListener(b bVar) {
        this.a = bVar;
    }

    public a getContentPositionDataProvider() {
        return this.b;
    }

    public void setContentPositionDataProvider(a aVar) {
        this.b = aVar;
    }
}
