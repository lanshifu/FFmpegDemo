package net.lucode.hackware.magicindicator.buildins.commonnavigator;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.R;
import net.lucode.hackware.magicindicator.b;
import net.lucode.hackware.magicindicator.b.a;

public class CommonNavigator extends FrameLayout implements aag, a {
    private HorizontalScrollView a;
    private LinearLayout b;
    private LinearLayout c;
    private aal d;
    private aaj e;
    private b f = new b();
    private boolean g;
    private boolean h;
    private float i = 0.5f;
    private boolean j = true;
    private boolean k = true;
    private int l;
    private int m;
    private boolean n;
    private boolean o;
    private boolean p = true;
    private List<aan> q = new ArrayList();
    private DataSetObserver r = new DataSetObserver() {
        public void onInvalidated() {
        }

        public void onChanged() {
            CommonNavigator.this.f.c(CommonNavigator.this.e.getCount());
            CommonNavigator.this.c();
        }
    };

    public void b() {
    }

    public CommonNavigator(Context context) {
        super(context);
        this.f.setNavigatorScrollListener(this);
    }

    public void setAdjustMode(boolean z) {
        this.g = z;
    }

    public aaj getAdapter() {
        return this.e;
    }

    public void setAdapter(aaj aaj) {
        if (this.e != aaj) {
            if (this.e != null) {
                this.e.unregisterDataSetObserver(this.r);
            }
            this.e = aaj;
            if (this.e != null) {
                this.e.registerDataSetObserver(this.r);
                this.f.c(this.e.getCount());
                if (this.b != null) {
                    this.e.notifyDataSetChanged();
                }
            } else {
                this.f.c(0);
                c();
            }
        }
    }

    private void c() {
        View inflate;
        removeAllViews();
        if (this.g) {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout_no_scroll, this);
        } else {
            inflate = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout, this);
        }
        this.a = (HorizontalScrollView) inflate.findViewById(R.id.scroll_view);
        this.b = (LinearLayout) inflate.findViewById(R.id.title_container);
        this.b.setPadding(this.m, 0, this.l, 0);
        this.c = (LinearLayout) inflate.findViewById(R.id.indicator_container);
        if (this.n) {
            this.c.getParent().bringChildToFront(this.c);
        }
        d();
    }

    private void d() {
        int a = this.f.a();
        for (int i = 0; i < a; i++) {
            aam titleView = this.e.getTitleView(getContext(), i);
            if (titleView instanceof View) {
                LayoutParams layoutParams;
                View view = (View) titleView;
                if (this.g) {
                    layoutParams = new LinearLayout.LayoutParams(0, -1);
                    layoutParams.weight = this.e.getTitleWeight(getContext(), i);
                } else {
                    layoutParams = new LinearLayout.LayoutParams(-2, -1);
                }
                this.b.addView(view, layoutParams);
            }
        }
        if (this.e != null) {
            this.d = this.e.getIndicator(getContext());
            if (this.d instanceof View) {
                this.c.addView((View) this.d, new FrameLayout.LayoutParams(-1, -1));
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.e != null) {
            e();
            if (this.d != null) {
                this.d.a(this.q);
            }
            if (this.p && this.f.c() == 0) {
                a(this.f.b());
                a(this.f.b(), CropImageView.DEFAULT_ASPECT_RATIO, 0);
            }
        }
    }

    private void e() {
        this.q.clear();
        int a = this.f.a();
        for (int i = 0; i < a; i++) {
            aan aan = new aan();
            View childAt = this.b.getChildAt(i);
            if (childAt != null) {
                aan.a = childAt.getLeft();
                aan.b = childAt.getTop();
                aan.c = childAt.getRight();
                aan.d = childAt.getBottom();
                if (childAt instanceof aak) {
                    aak aak = (aak) childAt;
                    aan.e = aak.getContentLeft();
                    aan.f = aak.getContentTop();
                    aan.g = aak.getContentRight();
                    aan.h = aak.getContentBottom();
                } else {
                    aan.e = aan.a;
                    aan.f = aan.b;
                    aan.g = aan.c;
                    aan.h = aan.d;
                }
            }
            this.q.add(aan);
        }
    }

    public void a(int i, float f, int i2) {
        if (this.e != null) {
            this.f.a(i, f, i2);
            if (this.d != null) {
                this.d.a(i, f, i2);
            }
            if (this.a != null && this.q.size() > 0 && i >= 0 && i < this.q.size()) {
                if (this.k) {
                    i2 = Math.min(this.q.size() - 1, i);
                    float b = ((float) ((aan) this.q.get(i2)).b()) - (((float) this.a.getWidth()) * this.i);
                    this.a.scrollTo((int) (b + (((((float) ((aan) this.q.get(Math.min(this.q.size() - 1, i + 1))).b()) - (((float) this.a.getWidth()) * this.i)) - b) * f)), 0);
                    return;
                }
                boolean z = this.h;
            }
        }
    }

    public float getScrollPivotX() {
        return this.i;
    }

    public void setScrollPivotX(float f) {
        this.i = f;
    }

    public void a(int i) {
        if (this.e != null) {
            this.f.a(i);
            if (this.d != null) {
                this.d.a(i);
            }
        }
    }

    public void b(int i) {
        if (this.e != null) {
            this.f.b(i);
            if (this.d != null) {
                this.d.b(i);
            }
        }
    }

    public void a() {
        c();
    }

    public aal getPagerIndicator() {
        return this.d;
    }

    public void setEnablePivotScroll(boolean z) {
        this.h = z;
    }

    public void a(int i, int i2, float f, boolean z) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof aam) {
                ((aam) childAt).onEnter(i, i2, f, z);
            }
        }
    }

    public void b(int i, int i2, float f, boolean z) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof aam) {
                ((aam) childAt).onLeave(i, i2, f, z);
            }
        }
    }

    public void setSmoothScroll(boolean z) {
        this.j = z;
    }

    public void setFollowTouch(boolean z) {
        this.k = z;
    }

    public void setSkimOver(boolean z) {
        this.o = z;
        this.f.a(z);
    }

    public void a(int i, int i2) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof aam) {
                ((aam) childAt).onSelected(i, i2);
            }
            if (!(this.g || this.k || this.a == null || this.q.size() <= 0)) {
                aan aan = (aan) this.q.get(Math.min(this.q.size() - 1, i));
                if (this.h) {
                    float b = ((float) aan.b()) - (((float) this.a.getWidth()) * this.i);
                    if (this.j) {
                        this.a.smoothScrollTo((int) b, 0);
                    } else {
                        this.a.scrollTo((int) b, 0);
                    }
                } else if (this.a.getScrollX() > aan.a) {
                    if (this.j) {
                        this.a.smoothScrollTo(aan.a, 0);
                    } else {
                        this.a.scrollTo(aan.a, 0);
                    }
                } else if (this.a.getScrollX() + getWidth() < aan.c) {
                    if (this.j) {
                        this.a.smoothScrollTo(aan.c - getWidth(), 0);
                    } else {
                        this.a.scrollTo(aan.c - getWidth(), 0);
                    }
                }
            }
        }
    }

    public void b(int i, int i2) {
        if (this.b != null) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof aam) {
                ((aam) childAt).onDeselected(i, i2);
            }
        }
    }

    public LinearLayout getTitleContainer() {
        return this.b;
    }

    public int getRightPadding() {
        return this.l;
    }

    public void setRightPadding(int i) {
        this.l = i;
    }

    public int getLeftPadding() {
        return this.m;
    }

    public void setLeftPadding(int i) {
        this.m = i;
    }

    public void setIndicatorOnTop(boolean z) {
        this.n = z;
    }

    public void setReselectWhenLayout(boolean z) {
        this.p = z;
    }
}
