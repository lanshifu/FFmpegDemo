package net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class BadgePagerTitleView extends FrameLayout implements aak {
    private aam a;
    private View b;
    private boolean c = true;
    private a d;
    private a e;

    public BadgePagerTitleView(Context context) {
        super(context);
    }

    public void onSelected(int i, int i2) {
        if (this.a != null) {
            this.a.onSelected(i, i2);
        }
        if (this.c) {
            setBadgeView(null);
        }
    }

    public void onDeselected(int i, int i2) {
        if (this.a != null) {
            this.a.onDeselected(i, i2);
        }
    }

    public void onLeave(int i, int i2, float f, boolean z) {
        if (this.a != null) {
            this.a.onLeave(i, i2, f, z);
        }
    }

    public void onEnter(int i, int i2, float f, boolean z) {
        if (this.a != null) {
            this.a.onEnter(i, i2, f, z);
        }
    }

    public aam getInnerPagerTitleView() {
        return this.a;
    }

    public void setInnerPagerTitleView(aam aam) {
        if (this.a != aam) {
            this.a = aam;
            removeAllViews();
            if (this.a instanceof View) {
                addView((View) this.a, new LayoutParams(-1, -1));
            }
            if (this.b != null) {
                addView(this.b, new LayoutParams(-2, -2));
            }
        }
    }

    public View getBadgeView() {
        return this.b;
    }

    public void setBadgeView(View view) {
        if (this.b != view) {
            this.b = view;
            removeAllViews();
            if (this.a instanceof View) {
                addView((View) this.a, new LayoutParams(-1, -1));
            }
            if (this.b != null) {
                addView(this.b, new LayoutParams(-2, -2));
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if ((this.a instanceof View) && this.b != null) {
            int[] iArr = new int[14];
            View view = (View) this.a;
            iArr[0] = view.getLeft();
            iArr[1] = view.getTop();
            iArr[2] = view.getRight();
            iArr[3] = view.getBottom();
            if (this.a instanceof aak) {
                aak aak = (aak) this.a;
                iArr[4] = aak.getContentLeft();
                iArr[5] = aak.getContentTop();
                iArr[6] = aak.getContentRight();
                iArr[7] = aak.getContentBottom();
            } else {
                for (i2 = 4; i2 < 8; i2++) {
                    iArr[i2] = iArr[i2 - 4];
                }
            }
            iArr[8] = view.getWidth() / 2;
            iArr[9] = view.getHeight() / 2;
            iArr[10] = iArr[4] / 2;
            iArr[11] = iArr[5] / 2;
            iArr[12] = iArr[6] + ((iArr[2] - iArr[6]) / 2);
            iArr[13] = iArr[7] + ((iArr[3] - iArr[7]) / 2);
            if (this.d != null) {
                this.b.offsetLeftAndRight((iArr[this.d.a().ordinal()] + this.d.b()) - this.b.getLeft());
            }
            if (this.e != null) {
                this.b.offsetTopAndBottom((iArr[this.e.a().ordinal()] + this.e.b()) - this.b.getTop());
            }
        }
    }

    public int getContentLeft() {
        if (this.a instanceof aak) {
            return getLeft() + ((aak) this.a).getContentLeft();
        }
        return getLeft();
    }

    public int getContentTop() {
        if (this.a instanceof aak) {
            return ((aak) this.a).getContentTop();
        }
        return getTop();
    }

    public int getContentRight() {
        if (this.a instanceof aak) {
            return getLeft() + ((aak) this.a).getContentRight();
        }
        return getRight();
    }

    public int getContentBottom() {
        if (this.a instanceof aak) {
            return ((aak) this.a).getContentBottom();
        }
        return getBottom();
    }

    public a getXBadgeRule() {
        return this.d;
    }

    public void setXBadgeRule(a aVar) {
        if (aVar != null) {
            BadgeAnchor a = aVar.a();
            if (!(a == BadgeAnchor.LEFT || a == BadgeAnchor.RIGHT || a == BadgeAnchor.CONTENT_LEFT || a == BadgeAnchor.CONTENT_RIGHT || a == BadgeAnchor.CENTER_X || a == BadgeAnchor.LEFT_EDGE_CENTER_X || a == BadgeAnchor.RIGHT_EDGE_CENTER_X)) {
                throw new IllegalArgumentException("x badge rule is wrong.");
            }
        }
        this.d = aVar;
    }

    public a getYBadgeRule() {
        return this.e;
    }

    public void setYBadgeRule(a aVar) {
        if (aVar != null) {
            BadgeAnchor a = aVar.a();
            if (!(a == BadgeAnchor.TOP || a == BadgeAnchor.BOTTOM || a == BadgeAnchor.CONTENT_TOP || a == BadgeAnchor.CONTENT_BOTTOM || a == BadgeAnchor.CENTER_Y || a == BadgeAnchor.TOP_EDGE_CENTER_Y || a == BadgeAnchor.BOTTOM_EDGE_CENTER_Y)) {
                throw new IllegalArgumentException("y badge rule is wrong.");
            }
        }
        this.e = aVar;
    }

    public void setAutoCancelBadge(boolean z) {
        this.c = z;
    }
}
