package defpackage;

import android.view.View;
import com.bigkoo.pickerview.R;
import com.contrarywind.view.WheelView;
import com.contrarywind.view.WheelView.DividerType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/* compiled from: WheelTime */
/* renamed from: dx */
public class dx {
    public static DateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private View b;
    private WheelView c;
    private WheelView d;
    private WheelView e;
    private WheelView f;
    private WheelView g;
    private WheelView h;
    private int i;
    private boolean[] j;
    private int k = 1900;
    private int l = 2100;
    private int m = 1;
    private int n = 12;
    private int o = 1;
    private int p = 31;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private float v;
    private DividerType w;
    private boolean x = false;
    private dk y;

    /* compiled from: WheelTime */
    /* renamed from: dx$1 */
    class 1 implements eb {
        1() {
        }

        public void a(int i) {
            i += dx.this.k;
            dx.this.d.setAdapter(new df(dq.e(i)));
            if (dq.b(i) == 0 || dx.this.d.getCurrentItem() <= dq.b(i) - 1) {
                dx.this.d.setCurrentItem(dx.this.d.getCurrentItem());
            } else {
                dx.this.d.setCurrentItem(dx.this.d.getCurrentItem() + 1);
            }
            if (dq.b(i) == 0 || dx.this.d.getCurrentItem() <= dq.b(i) - 1) {
                dx.this.e.setAdapter(new df(dq.f(dq.a(i, dx.this.d.getCurrentItem() + 1))));
                i = dq.a(i, dx.this.d.getCurrentItem() + 1);
            } else if (dx.this.d.getCurrentItem() == dq.b(i) + 1) {
                dx.this.e.setAdapter(new df(dq.f(dq.a(i))));
                i = dq.a(i);
            } else {
                dx.this.e.setAdapter(new df(dq.f(dq.a(i, dx.this.d.getCurrentItem()))));
                i = dq.a(i, dx.this.d.getCurrentItem());
            }
            i--;
            if (dx.this.e.getCurrentItem() > i) {
                dx.this.e.setCurrentItem(i);
            }
            if (dx.this.y != null) {
                dx.this.y.a();
            }
        }
    }

    /* compiled from: WheelTime */
    /* renamed from: dx$2 */
    class 2 implements eb {
        2() {
        }

        public void a(int i) {
            int currentItem = dx.this.c.getCurrentItem() + dx.this.k;
            if (dq.b(currentItem) == 0 || i <= dq.b(currentItem) - 1) {
                i++;
                dx.this.e.setAdapter(new df(dq.f(dq.a(currentItem, i))));
                i = dq.a(currentItem, i);
            } else if (dx.this.d.getCurrentItem() == dq.b(currentItem) + 1) {
                dx.this.e.setAdapter(new df(dq.f(dq.a(currentItem))));
                i = dq.a(currentItem);
            } else {
                dx.this.e.setAdapter(new df(dq.f(dq.a(currentItem, i))));
                i = dq.a(currentItem, i);
            }
            i--;
            if (dx.this.e.getCurrentItem() > i) {
                dx.this.e.setCurrentItem(i);
            }
            if (dx.this.y != null) {
                dx.this.y.a();
            }
        }
    }

    /* compiled from: WheelTime */
    /* renamed from: dx$5 */
    class 5 implements eb {
        5() {
        }

        public void a(int i) {
            dx.this.y.a();
        }
    }

    public dx(View view, boolean[] zArr, int i, int i2) {
        this.b = view;
        this.j = zArr;
        this.i = i;
        this.r = i2;
    }

    public void a(boolean z) {
        this.x = z;
    }

    public void a(int i, int i2, int i3, int i4, int i5, int i6) {
        if (this.x) {
            int[] a = dr.a(i, i2 + 1, i3);
            a(a[0], a[1] - 1, a[2], a[3] == 1, i4, i5, i6);
            return;
        }
        c(i, i2, i3, i4, i5, i6);
    }

    private void a(int i, int i2, int i3, boolean z, int i4, int i5, int i6) {
        this.c = (WheelView) this.b.findViewById(R.id.year);
        this.c.setAdapter(new df(dq.b(this.k, this.l)));
        this.c.setLabel("");
        this.c.setCurrentItem(i - this.k);
        this.c.setGravity(this.i);
        this.d = (WheelView) this.b.findViewById(R.id.month);
        this.d.setAdapter(new df(dq.e(i)));
        this.d.setLabel("");
        i6 = dq.b(i);
        if (i6 == 0 || (i2 <= i6 - 1 && !z)) {
            this.d.setCurrentItem(i2);
        } else {
            this.d.setCurrentItem(i2 + 1);
        }
        this.d.setGravity(this.i);
        this.e = (WheelView) this.b.findViewById(R.id.day);
        if (dq.b(i) == 0) {
            this.e.setAdapter(new df(dq.f(dq.a(i, i2))));
        } else {
            this.e.setAdapter(new df(dq.f(dq.a(i))));
        }
        this.e.setLabel("");
        this.e.setCurrentItem(i3 - 1);
        this.e.setGravity(this.i);
        this.f = (WheelView) this.b.findViewById(R.id.hour);
        this.f.setAdapter(new dg(0, 23));
        this.f.setCurrentItem(i4);
        this.f.setGravity(this.i);
        this.g = (WheelView) this.b.findViewById(R.id.min);
        this.g.setAdapter(new dg(0, 59));
        this.g.setCurrentItem(i5);
        this.g.setGravity(this.i);
        this.h = (WheelView) this.b.findViewById(R.id.second);
        this.h.setAdapter(new dg(0, 59));
        this.h.setCurrentItem(i5);
        this.h.setGravity(this.i);
        this.c.setOnItemSelectedListener(new 1());
        this.d.setOnItemSelectedListener(new 2());
        a(this.e);
        a(this.f);
        a(this.g);
        a(this.h);
        if (this.j.length == 6) {
            i3 = 8;
            this.c.setVisibility(this.j[0] ? 0 : 8);
            this.d.setVisibility(this.j[1] ? 0 : 8);
            this.e.setVisibility(this.j[2] ? 0 : 8);
            this.f.setVisibility(this.j[3] ? 0 : 8);
            this.g.setVisibility(this.j[4] ? 0 : 8);
            WheelView wheelView = this.h;
            if (this.j[5]) {
                i3 = 0;
            }
            wheelView.setVisibility(i3);
            b();
            return;
        }
        throw new RuntimeException("type[] length is not 6");
    }

    private void c(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7 = i;
        int i8 = i2;
        String[] strArr = new String[]{"4", "6", "9", "11"};
        final List asList = Arrays.asList(new String[]{"1", "3", "5", "7", "8", "10", "12"});
        final List asList2 = Arrays.asList(strArr);
        this.q = i7;
        this.c = (WheelView) this.b.findViewById(R.id.year);
        this.c.setAdapter(new dg(this.k, this.l));
        this.c.setCurrentItem(i7 - this.k);
        this.c.setGravity(this.i);
        this.d = (WheelView) this.b.findViewById(R.id.month);
        if (this.k == this.l) {
            this.d.setAdapter(new dg(this.m, this.n));
            this.d.setCurrentItem((i8 + 1) - this.m);
        } else if (i7 == this.k) {
            this.d.setAdapter(new dg(this.m, 12));
            this.d.setCurrentItem((i8 + 1) - this.m);
        } else if (i7 == this.l) {
            this.d.setAdapter(new dg(1, this.n));
            this.d.setCurrentItem(i8);
        } else {
            this.d.setAdapter(new dg(1, 12));
            this.d.setCurrentItem(i8);
        }
        this.d.setGravity(this.i);
        this.e = (WheelView) this.b.findViewById(R.id.day);
        if (this.k == this.l && this.m == this.n) {
            i8++;
            if (asList.contains(String.valueOf(i8))) {
                if (this.p > 31) {
                    this.p = 31;
                }
                this.e.setAdapter(new dg(this.o, this.p));
            } else if (asList2.contains(String.valueOf(i8))) {
                if (this.p > 30) {
                    this.p = 30;
                }
                this.e.setAdapter(new dg(this.o, this.p));
            } else if ((i7 % 4 != 0 || i7 % 100 == 0) && i7 % 400 != 0) {
                if (this.p > 28) {
                    this.p = 28;
                }
                this.e.setAdapter(new dg(this.o, this.p));
            } else {
                if (this.p > 29) {
                    this.p = 29;
                }
                this.e.setAdapter(new dg(this.o, this.p));
            }
            this.e.setCurrentItem(i3 - this.o);
        } else {
            int i9;
            if (i7 == this.k) {
                i9 = i8 + 1;
                if (i9 == this.m) {
                    if (asList.contains(String.valueOf(i9))) {
                        this.e.setAdapter(new dg(this.o, 31));
                    } else if (asList2.contains(String.valueOf(i9))) {
                        this.e.setAdapter(new dg(this.o, 30));
                    } else if ((i7 % 4 != 0 || i7 % 100 == 0) && i7 % 400 != 0) {
                        this.e.setAdapter(new dg(this.o, 28));
                    } else {
                        this.e.setAdapter(new dg(this.o, 29));
                    }
                    this.e.setCurrentItem(i3 - this.o);
                }
            }
            if (i7 == this.l) {
                i9 = i8 + 1;
                if (i9 == this.n) {
                    if (asList.contains(String.valueOf(i9))) {
                        if (this.p > 31) {
                            this.p = 31;
                        }
                        this.e.setAdapter(new dg(1, this.p));
                    } else if (asList2.contains(String.valueOf(i9))) {
                        if (this.p > 30) {
                            this.p = 30;
                        }
                        this.e.setAdapter(new dg(1, this.p));
                    } else if ((i7 % 4 != 0 || i7 % 100 == 0) && i7 % 400 != 0) {
                        if (this.p > 28) {
                            this.p = 28;
                        }
                        this.e.setAdapter(new dg(1, this.p));
                    } else {
                        if (this.p > 29) {
                            this.p = 29;
                        }
                        this.e.setAdapter(new dg(1, this.p));
                    }
                    this.e.setCurrentItem(i3 - 1);
                }
            }
            i8++;
            if (asList.contains(String.valueOf(i8))) {
                this.e.setAdapter(new dg(1, 31));
            } else if (asList2.contains(String.valueOf(i8))) {
                this.e.setAdapter(new dg(1, 30));
            } else if ((i7 % 4 != 0 || i7 % 100 == 0) && i7 % 400 != 0) {
                this.e.setAdapter(new dg(1, 28));
            } else {
                this.e.setAdapter(new dg(1, 29));
            }
            this.e.setCurrentItem(i3 - 1);
        }
        this.e.setGravity(this.i);
        this.f = (WheelView) this.b.findViewById(R.id.hour);
        this.f.setAdapter(new dg(0, 23));
        this.f.setCurrentItem(i4);
        this.f.setGravity(this.i);
        this.g = (WheelView) this.b.findViewById(R.id.min);
        this.g.setAdapter(new dg(0, 59));
        this.g.setCurrentItem(i5);
        this.g.setGravity(this.i);
        this.h = (WheelView) this.b.findViewById(R.id.second);
        this.h.setAdapter(new dg(0, 59));
        this.h.setCurrentItem(i6);
        this.h.setGravity(this.i);
        this.c.setOnItemSelectedListener(new eb() {
            public void a(int i) {
                int a = i + dx.this.k;
                dx.this.q = a;
                i = dx.this.d.getCurrentItem();
                int g;
                if (dx.this.k == dx.this.l) {
                    dx.this.d.setAdapter(new dg(dx.this.m, dx.this.n));
                    if (i > dx.this.d.getAdapter().a() - 1) {
                        i = dx.this.d.getAdapter().a() - 1;
                        dx.this.d.setCurrentItem(i);
                    }
                    g = i + dx.this.m;
                    if (dx.this.m == dx.this.n) {
                        dx.this.a(a, g, dx.this.o, dx.this.p, asList, asList2);
                    } else if (g == dx.this.m) {
                        dx.this.a(a, g, dx.this.o, 31, asList, asList2);
                    } else if (g == dx.this.n) {
                        dx.this.a(a, g, 1, dx.this.p, asList, asList2);
                    } else {
                        dx.this.a(a, g, 1, 31, asList, asList2);
                    }
                } else if (a == dx.this.k) {
                    dx.this.d.setAdapter(new dg(dx.this.m, 12));
                    if (i > dx.this.d.getAdapter().a() - 1) {
                        i = dx.this.d.getAdapter().a() - 1;
                        dx.this.d.setCurrentItem(i);
                    }
                    g = i + dx.this.m;
                    if (g == dx.this.m) {
                        dx.this.a(a, g, dx.this.o, 31, asList, asList2);
                    } else {
                        dx.this.a(a, g, 1, 31, asList, asList2);
                    }
                } else if (a == dx.this.l) {
                    dx.this.d.setAdapter(new dg(1, dx.this.n));
                    if (i > dx.this.d.getAdapter().a() - 1) {
                        i = dx.this.d.getAdapter().a() - 1;
                        dx.this.d.setCurrentItem(i);
                    }
                    g = 1 + i;
                    if (g == dx.this.n) {
                        dx.this.a(a, g, 1, dx.this.p, asList, asList2);
                    } else {
                        dx.this.a(a, g, 1, 31, asList, asList2);
                    }
                } else {
                    dx.this.d.setAdapter(new dg(1, 12));
                    dx.this.a(a, 1 + dx.this.d.getCurrentItem(), 1, 31, asList, asList2);
                }
                if (dx.this.y != null) {
                    dx.this.y.a();
                }
            }
        });
        this.d.setOnItemSelectedListener(new eb() {
            public void a(int i) {
                int i2 = i + 1;
                int g;
                if (dx.this.k == dx.this.l) {
                    g = (i2 + dx.this.m) - 1;
                    if (dx.this.m == dx.this.n) {
                        dx.this.a(dx.this.q, g, dx.this.o, dx.this.p, asList, asList2);
                    } else if (dx.this.m == g) {
                        dx.this.a(dx.this.q, g, dx.this.o, 31, asList, asList2);
                    } else if (dx.this.n == g) {
                        dx.this.a(dx.this.q, g, 1, dx.this.p, asList, asList2);
                    } else {
                        dx.this.a(dx.this.q, g, 1, 31, asList, asList2);
                    }
                } else if (dx.this.q == dx.this.k) {
                    g = (i2 + dx.this.m) - 1;
                    if (g == dx.this.m) {
                        dx.this.a(dx.this.q, g, dx.this.o, 31, asList, asList2);
                    } else {
                        dx.this.a(dx.this.q, g, 1, 31, asList, asList2);
                    }
                } else if (dx.this.q != dx.this.l) {
                    dx.this.a(dx.this.q, i2, 1, 31, asList, asList2);
                } else if (i2 == dx.this.n) {
                    dx.this.a(dx.this.q, dx.this.d.getCurrentItem() + 1, 1, dx.this.p, asList, asList2);
                } else {
                    dx.this.a(dx.this.q, dx.this.d.getCurrentItem() + 1, 1, 31, asList, asList2);
                }
                if (dx.this.y != null) {
                    dx.this.y.a();
                }
            }
        });
        a(this.e);
        a(this.f);
        a(this.g);
        a(this.h);
        if (this.j.length == 6) {
            int i10 = 8;
            this.c.setVisibility(this.j[0] ? 0 : 8);
            this.d.setVisibility(this.j[1] ? 0 : 8);
            this.e.setVisibility(this.j[2] ? 0 : 8);
            this.f.setVisibility(this.j[3] ? 0 : 8);
            this.g.setVisibility(this.j[4] ? 0 : 8);
            WheelView wheelView = this.h;
            if (this.j[5]) {
                i10 = 0;
            }
            wheelView.setVisibility(i10);
            b();
            return;
        }
        throw new IllegalArgumentException("type[] length is not 6");
    }

    private void a(WheelView wheelView) {
        if (this.y != null) {
            wheelView.setOnItemSelectedListener(new 5());
        }
    }

    private void a(int i, int i2, int i3, int i4, List<String> list, List<String> list2) {
        int currentItem = this.e.getCurrentItem();
        if (list.contains(String.valueOf(i2))) {
            i = 31;
            if (i4 <= 31) {
                i = i4;
            }
            this.e.setAdapter(new dg(i3, i));
        } else if (list2.contains(String.valueOf(i2))) {
            i = 30;
            if (i4 <= 30) {
                i = i4;
            }
            this.e.setAdapter(new dg(i3, i));
        } else if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
            i = 28;
            if (i4 <= 28) {
                i = i4;
            }
            this.e.setAdapter(new dg(i3, i));
        } else {
            i = 29;
            if (i4 <= 29) {
                i = i4;
            }
            this.e.setAdapter(new dg(i3, i));
        }
        if (currentItem > this.e.getAdapter().a() - 1) {
            this.e.setCurrentItem(this.e.getAdapter().a() - 1);
        }
    }

    private void b() {
        this.e.setTextSize((float) this.r);
        this.d.setTextSize((float) this.r);
        this.c.setTextSize((float) this.r);
        this.f.setTextSize((float) this.r);
        this.g.setTextSize((float) this.r);
        this.h.setTextSize((float) this.r);
    }

    private void c() {
        this.e.setTextColorOut(this.s);
        this.d.setTextColorOut(this.s);
        this.c.setTextColorOut(this.s);
        this.f.setTextColorOut(this.s);
        this.g.setTextColorOut(this.s);
        this.h.setTextColorOut(this.s);
    }

    private void d() {
        this.e.setTextColorCenter(this.t);
        this.d.setTextColorCenter(this.t);
        this.c.setTextColorCenter(this.t);
        this.f.setTextColorCenter(this.t);
        this.g.setTextColorCenter(this.t);
        this.h.setTextColorCenter(this.t);
    }

    private void e() {
        this.e.setDividerColor(this.u);
        this.d.setDividerColor(this.u);
        this.c.setDividerColor(this.u);
        this.f.setDividerColor(this.u);
        this.g.setDividerColor(this.u);
        this.h.setDividerColor(this.u);
    }

    private void f() {
        this.e.setDividerType(this.w);
        this.d.setDividerType(this.w);
        this.c.setDividerType(this.w);
        this.f.setDividerType(this.w);
        this.g.setDividerType(this.w);
        this.h.setDividerType(this.w);
    }

    private void g() {
        this.e.setLineSpacingMultiplier(this.v);
        this.d.setLineSpacingMultiplier(this.v);
        this.c.setLineSpacingMultiplier(this.v);
        this.f.setLineSpacingMultiplier(this.v);
        this.g.setLineSpacingMultiplier(this.v);
        this.h.setLineSpacingMultiplier(this.v);
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        if (!this.x) {
            if (str != null) {
                this.c.setLabel(str);
            } else {
                this.c.setLabel(this.b.getContext().getString(R.string.pickerview_year));
            }
            if (str2 != null) {
                this.d.setLabel(str2);
            } else {
                this.d.setLabel(this.b.getContext().getString(R.string.pickerview_month));
            }
            if (str3 != null) {
                this.e.setLabel(str3);
            } else {
                this.e.setLabel(this.b.getContext().getString(R.string.pickerview_day));
            }
            if (str4 != null) {
                this.f.setLabel(str4);
            } else {
                this.f.setLabel(this.b.getContext().getString(R.string.pickerview_hours));
            }
            if (str5 != null) {
                this.g.setLabel(str5);
            } else {
                this.g.setLabel(this.b.getContext().getString(R.string.pickerview_minutes));
            }
            if (str6 != null) {
                this.h.setLabel(str6);
            } else {
                this.h.setLabel(this.b.getContext().getString(R.string.pickerview_seconds));
            }
        }
    }

    public void b(int i, int i2, int i3, int i4, int i5, int i6) {
        this.e.setTextXOffset(i);
        this.d.setTextXOffset(i2);
        this.c.setTextXOffset(i3);
        this.f.setTextXOffset(i4);
        this.g.setTextXOffset(i5);
        this.h.setTextXOffset(i6);
    }

    public void b(boolean z) {
        this.c.setCyclic(z);
        this.d.setCyclic(z);
        this.e.setCyclic(z);
        this.f.setCyclic(z);
        this.g.setCyclic(z);
        this.h.setCyclic(z);
    }

    public String a() {
        if (this.x) {
            return h();
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (this.q != this.k) {
            stringBuilder.append(this.c.getCurrentItem() + this.k);
            stringBuilder.append("-");
            stringBuilder.append(this.d.getCurrentItem() + 1);
            stringBuilder.append("-");
            stringBuilder.append(this.e.getCurrentItem() + 1);
            stringBuilder.append(" ");
            stringBuilder.append(this.f.getCurrentItem());
            stringBuilder.append(":");
            stringBuilder.append(this.g.getCurrentItem());
            stringBuilder.append(":");
            stringBuilder.append(this.h.getCurrentItem());
        } else if (this.d.getCurrentItem() + this.m == this.m) {
            stringBuilder.append(this.c.getCurrentItem() + this.k);
            stringBuilder.append("-");
            stringBuilder.append(this.d.getCurrentItem() + this.m);
            stringBuilder.append("-");
            stringBuilder.append(this.e.getCurrentItem() + this.o);
            stringBuilder.append(" ");
            stringBuilder.append(this.f.getCurrentItem());
            stringBuilder.append(":");
            stringBuilder.append(this.g.getCurrentItem());
            stringBuilder.append(":");
            stringBuilder.append(this.h.getCurrentItem());
        } else {
            stringBuilder.append(this.c.getCurrentItem() + this.k);
            stringBuilder.append("-");
            stringBuilder.append(this.d.getCurrentItem() + this.m);
            stringBuilder.append("-");
            stringBuilder.append(this.e.getCurrentItem() + 1);
            stringBuilder.append(" ");
            stringBuilder.append(this.f.getCurrentItem());
            stringBuilder.append(":");
            stringBuilder.append(this.g.getCurrentItem());
            stringBuilder.append(":");
            stringBuilder.append(this.h.getCurrentItem());
        }
        return stringBuilder.toString();
    }

    private String h() {
        int currentItem;
        boolean z;
        int[] a;
        StringBuilder stringBuilder = new StringBuilder();
        int currentItem2 = this.c.getCurrentItem() + this.k;
        if (dq.b(currentItem2) == 0) {
            currentItem = this.d.getCurrentItem() + 1;
        } else if ((this.d.getCurrentItem() + 1) - dq.b(currentItem2) <= 0) {
            currentItem = this.d.getCurrentItem() + 1;
        } else if ((this.d.getCurrentItem() + 1) - dq.b(currentItem2) == 1) {
            currentItem = this.d.getCurrentItem();
            z = true;
            a = dr.a(currentItem2, currentItem, this.e.getCurrentItem() + 1, z);
            stringBuilder.append(a[0]);
            stringBuilder.append("-");
            stringBuilder.append(a[1]);
            stringBuilder.append("-");
            stringBuilder.append(a[2]);
            stringBuilder.append(" ");
            stringBuilder.append(this.f.getCurrentItem());
            stringBuilder.append(":");
            stringBuilder.append(this.g.getCurrentItem());
            stringBuilder.append(":");
            stringBuilder.append(this.h.getCurrentItem());
            return stringBuilder.toString();
        } else {
            currentItem = this.d.getCurrentItem();
        }
        z = false;
        a = dr.a(currentItem2, currentItem, this.e.getCurrentItem() + 1, z);
        stringBuilder.append(a[0]);
        stringBuilder.append("-");
        stringBuilder.append(a[1]);
        stringBuilder.append("-");
        stringBuilder.append(a[2]);
        stringBuilder.append(" ");
        stringBuilder.append(this.f.getCurrentItem());
        stringBuilder.append(":");
        stringBuilder.append(this.g.getCurrentItem());
        stringBuilder.append(":");
        stringBuilder.append(this.h.getCurrentItem());
        return stringBuilder.toString();
    }

    public void a(int i) {
        this.k = i;
    }

    public void b(int i) {
        this.l = i;
    }

    public void a(Calendar calendar, Calendar calendar2) {
        int i;
        int i2;
        int i3;
        if (calendar == null && calendar2 != null) {
            i = calendar2.get(1);
            i2 = calendar2.get(2) + 1;
            i3 = calendar2.get(5);
            if (i > this.k) {
                this.l = i;
                this.n = i2;
                this.p = i3;
            } else if (i != this.k) {
            } else {
                if (i2 > this.m) {
                    this.l = i;
                    this.n = i2;
                    this.p = i3;
                } else if (i2 == this.m && i3 > this.o) {
                    this.l = i;
                    this.n = i2;
                    this.p = i3;
                }
            }
        } else if (calendar != null && calendar2 == null) {
            i3 = calendar.get(1);
            i2 = calendar.get(2) + 1;
            i = calendar.get(5);
            if (i3 < this.l) {
                this.m = i2;
                this.o = i;
                this.k = i3;
            } else if (i3 != this.l) {
            } else {
                if (i2 < this.n) {
                    this.m = i2;
                    this.o = i;
                    this.k = i3;
                } else if (i2 == this.n && i < this.p) {
                    this.m = i2;
                    this.o = i;
                    this.k = i3;
                }
            }
        } else if (calendar != null && calendar2 != null) {
            this.k = calendar.get(1);
            this.l = calendar2.get(1);
            this.m = calendar.get(2) + 1;
            this.n = calendar2.get(2) + 1;
            this.o = calendar.get(5);
            this.p = calendar2.get(5);
        }
    }

    public void a(float f) {
        this.v = f;
        g();
    }

    public void c(int i) {
        this.u = i;
        e();
    }

    public void a(DividerType dividerType) {
        this.w = dividerType;
        f();
    }

    public void d(int i) {
        this.t = i;
        d();
    }

    public void e(int i) {
        this.s = i;
        c();
    }

    public void c(boolean z) {
        this.e.a(z);
        this.d.a(z);
        this.c.a(z);
        this.f.a(z);
        this.g.a(z);
        this.h.a(z);
    }

    public void a(dk dkVar) {
        this.y = dkVar;
    }
}
