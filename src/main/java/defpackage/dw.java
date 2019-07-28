package defpackage;

import android.graphics.Typeface;
import android.view.View;
import com.bigkoo.pickerview.R;
import com.contrarywind.view.WheelView;
import com.contrarywind.view.WheelView.DividerType;
import java.util.List;

/* compiled from: WheelOptions */
/* renamed from: dw */
public class dw<T> {
    private View a;
    private WheelView b;
    private WheelView c;
    private WheelView d;
    private List<T> e;
    private List<List<T>> f;
    private List<List<List<T>>> g;
    private boolean h = true;
    private boolean i;
    private eb j;
    private eb k;
    private dm l;
    private int m;
    private int n;
    private int o;
    private DividerType p;
    private float q;

    /* compiled from: WheelOptions */
    /* renamed from: dw$1 */
    class 1 implements eb {
        1() {
        }

        public void a(int i) {
            if (dw.this.f != null) {
                int i2;
                if (dw.this.i) {
                    i2 = 0;
                } else {
                    i2 = dw.this.c.getCurrentItem();
                    if (i2 >= ((List) dw.this.f.get(i)).size() - 1) {
                        i2 = ((List) dw.this.f.get(i)).size() - 1;
                    }
                }
                dw.this.c.setAdapter(new df((List) dw.this.f.get(i)));
                dw.this.c.setCurrentItem(i2);
                if (dw.this.g != null) {
                    dw.this.k.a(i2);
                } else if (dw.this.l != null) {
                    dw.this.l.a(i, i2, 0);
                }
            } else if (dw.this.l != null) {
                dw.this.l.a(dw.this.b.getCurrentItem(), 0, 0);
            }
        }
    }

    /* compiled from: WheelOptions */
    /* renamed from: dw$2 */
    class 2 implements eb {
        2() {
        }

        public void a(int i) {
            int i2 = 0;
            if (dw.this.g != null) {
                int currentItem = dw.this.b.getCurrentItem();
                if (currentItem >= dw.this.g.size() - 1) {
                    currentItem = dw.this.g.size() - 1;
                }
                if (i >= ((List) dw.this.f.get(currentItem)).size() - 1) {
                    i = ((List) dw.this.f.get(currentItem)).size() - 1;
                }
                if (!dw.this.i) {
                    i2 = dw.this.d.getCurrentItem() >= ((List) ((List) dw.this.g.get(currentItem)).get(i)).size() + -1 ? ((List) ((List) dw.this.g.get(currentItem)).get(i)).size() - 1 : dw.this.d.getCurrentItem();
                }
                dw.this.d.setAdapter(new df((List) ((List) dw.this.g.get(dw.this.b.getCurrentItem())).get(i)));
                dw.this.d.setCurrentItem(i2);
                if (dw.this.l != null) {
                    dw.this.l.a(dw.this.b.getCurrentItem(), i, i2);
                }
            } else if (dw.this.l != null) {
                dw.this.l.a(dw.this.b.getCurrentItem(), i, 0);
            }
        }
    }

    /* compiled from: WheelOptions */
    /* renamed from: dw$3 */
    class 3 implements eb {
        3() {
        }

        public void a(int i) {
            dw.this.l.a(dw.this.b.getCurrentItem(), dw.this.c.getCurrentItem(), i);
        }
    }

    public dw(View view, boolean z) {
        this.i = z;
        this.a = view;
        this.b = (WheelView) view.findViewById(R.id.options1);
        this.c = (WheelView) view.findViewById(R.id.options2);
        this.d = (WheelView) view.findViewById(R.id.options3);
    }

    public void a(List<T> list, List<List<T>> list2, List<List<List<T>>> list3) {
        this.e = list;
        this.f = list2;
        this.g = list3;
        this.b.setAdapter(new df(this.e));
        this.b.setCurrentItem(0);
        if (this.f != null) {
            this.c.setAdapter(new df((List) this.f.get(0)));
        }
        this.c.setCurrentItem(this.c.getCurrentItem());
        if (this.g != null) {
            this.d.setAdapter(new df((List) ((List) this.g.get(0)).get(0)));
        }
        this.d.setCurrentItem(this.d.getCurrentItem());
        this.b.setIsOptions(true);
        this.c.setIsOptions(true);
        this.d.setIsOptions(true);
        if (this.f == null) {
            this.c.setVisibility(8);
        } else {
            this.c.setVisibility(0);
        }
        if (this.g == null) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
        }
        this.j = new 1();
        this.k = new 2();
        if (list != null && this.h) {
            this.b.setOnItemSelectedListener(this.j);
        }
        if (list2 != null && this.h) {
            this.c.setOnItemSelectedListener(this.k);
        }
        if (list3 != null && this.h && this.l != null) {
            this.d.setOnItemSelectedListener(new 3());
        }
    }

    public void a(int i) {
        float f = (float) i;
        this.b.setTextSize(f);
        this.c.setTextSize(f);
        this.d.setTextSize(f);
    }

    private void b() {
        this.b.setTextColorOut(this.m);
        this.c.setTextColorOut(this.m);
        this.d.setTextColorOut(this.m);
    }

    private void c() {
        this.b.setTextColorCenter(this.n);
        this.c.setTextColorCenter(this.n);
        this.d.setTextColorCenter(this.n);
    }

    private void d() {
        this.b.setDividerColor(this.o);
        this.c.setDividerColor(this.o);
        this.d.setDividerColor(this.o);
    }

    private void e() {
        this.b.setDividerType(this.p);
        this.c.setDividerType(this.p);
        this.d.setDividerType(this.p);
    }

    private void f() {
        this.b.setLineSpacingMultiplier(this.q);
        this.c.setLineSpacingMultiplier(this.q);
        this.d.setLineSpacingMultiplier(this.q);
    }

    public void a(String str, String str2, String str3) {
        if (str != null) {
            this.b.setLabel(str);
        }
        if (str2 != null) {
            this.c.setLabel(str2);
        }
        if (str3 != null) {
            this.d.setLabel(str3);
        }
    }

    public void a(int i, int i2, int i3) {
        this.b.setTextXOffset(i);
        this.c.setTextXOffset(i2);
        this.d.setTextXOffset(i3);
    }

    public void a(Typeface typeface) {
        this.b.setTypeface(typeface);
        this.c.setTypeface(typeface);
        this.d.setTypeface(typeface);
    }

    public void a(boolean z, boolean z2, boolean z3) {
        this.b.setCyclic(z);
        this.c.setCyclic(z2);
        this.d.setCyclic(z3);
    }

    public int[] a() {
        int[] iArr = new int[3];
        int i = 0;
        iArr[0] = this.b.getCurrentItem();
        if (this.f == null || this.f.size() <= 0) {
            iArr[1] = this.c.getCurrentItem();
        } else {
            iArr[1] = this.c.getCurrentItem() > ((List) this.f.get(iArr[0])).size() - 1 ? 0 : this.c.getCurrentItem();
        }
        if (this.g == null || this.g.size() <= 0) {
            iArr[2] = this.d.getCurrentItem();
        } else {
            if (this.d.getCurrentItem() <= ((List) ((List) this.g.get(iArr[0])).get(iArr[1])).size() - 1) {
                i = this.d.getCurrentItem();
            }
            iArr[2] = i;
        }
        return iArr;
    }

    public void b(int i, int i2, int i3) {
        if (this.h) {
            c(i, i2, i3);
            return;
        }
        this.b.setCurrentItem(i);
        this.c.setCurrentItem(i2);
        this.d.setCurrentItem(i3);
    }

    private void c(int i, int i2, int i3) {
        if (this.e != null) {
            this.b.setCurrentItem(i);
        }
        if (this.f != null) {
            this.c.setAdapter(new df((List) this.f.get(i)));
            this.c.setCurrentItem(i2);
        }
        if (this.g != null) {
            this.d.setAdapter(new df((List) ((List) this.g.get(i)).get(i2)));
            this.d.setCurrentItem(i3);
        }
    }

    public void a(float f) {
        this.q = f;
        f();
    }

    public void b(int i) {
        this.o = i;
        d();
    }

    public void a(DividerType dividerType) {
        this.p = dividerType;
        e();
    }

    public void c(int i) {
        this.n = i;
        c();
    }

    public void d(int i) {
        this.m = i;
        b();
    }

    public void a(boolean z) {
        this.b.a(z);
        this.c.a(z);
        this.d.a(z);
    }

    public void setOptionsSelectChangeListener(dm dmVar) {
        this.l = dmVar;
    }
}
