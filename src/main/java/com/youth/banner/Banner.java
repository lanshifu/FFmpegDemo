package com.youth.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.tomatolive.library.http.exception.ExceptionEngine;
import com.youth.banner.loader.ImageLoaderInterface;
import com.youth.banner.view.BannerViewPager;
import defpackage.ta;
import defpackage.tb;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Banner extends FrameLayout implements OnPageChangeListener {
    private List<ImageView> A;
    private Context B;
    private BannerViewPager C;
    private TextView D;
    private TextView E;
    private TextView F;
    private LinearLayout G;
    private LinearLayout H;
    private LinearLayout I;
    private ImageView J;
    private ImageLoaderInterface K;
    private a L;
    private OnPageChangeListener M;
    private a N;
    private ta O;
    private tb P;
    private DisplayMetrics Q;
    private b R;
    private final Runnable S;
    public String a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private List<String> x;
    private List y;
    private List<View> z;

    class a extends PagerAdapter {
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        a() {
        }

        public int getCount() {
            return Banner.this.z.size();
        }

        public Object instantiateItem(ViewGroup viewGroup, final int i) {
            viewGroup.addView((View) Banner.this.z.get(i));
            View view = (View) Banner.this.z.get(i);
            if (Banner.this.O != null) {
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Log.e(Banner.this.a, "你正在使用旧版点击事件接口，下标是从1开始，为了体验请更换为setOnBannerListener，下标从0开始计算");
                        Banner.this.O.a(i);
                    }
                });
            }
            if (Banner.this.P != null) {
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        Banner.this.P.OnBannerClick(Banner.this.b(i));
                    }
                });
            }
            return view;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    public Banner(Context context) {
        this(context, null);
    }

    public Banner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Banner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = "banner";
        this.b = 5;
        this.g = 1;
        this.h = ExceptionEngine.SERVER_ERROR;
        this.i = 800;
        this.j = true;
        this.k = true;
        this.l = R.drawable.gray_radius;
        this.m = R.drawable.white_radius;
        this.n = R.layout.banner;
        this.s = 0;
        this.u = -1;
        this.v = 1;
        this.w = 1;
        this.R = new b();
        this.S = new Runnable() {
            public void run() {
                if (Banner.this.s > 1 && Banner.this.j) {
                    Banner.this.t = (Banner.this.t % (Banner.this.s + 1)) + 1;
                    if (Banner.this.t == 1) {
                        Banner.this.C.setCurrentItem(Banner.this.t, false);
                        Banner.this.R.a(Banner.this.S);
                        return;
                    }
                    Banner.this.C.setCurrentItem(Banner.this.t);
                    Banner.this.R.a(Banner.this.S, (long) Banner.this.h);
                }
            }
        };
        this.B = context;
        this.x = new ArrayList();
        this.y = new ArrayList();
        this.z = new ArrayList();
        this.A = new ArrayList();
        this.Q = context.getResources().getDisplayMetrics();
        this.e = this.Q.widthPixels / 80;
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        this.z.clear();
        b(context, attributeSet);
        View inflate = LayoutInflater.from(context).inflate(this.n, this, true);
        this.J = (ImageView) inflate.findViewById(R.id.bannerDefaultImage);
        this.C = (BannerViewPager) inflate.findViewById(R.id.bannerViewPager);
        this.I = (LinearLayout) inflate.findViewById(R.id.titleView);
        this.G = (LinearLayout) inflate.findViewById(R.id.circleIndicator);
        this.H = (LinearLayout) inflate.findViewById(R.id.indicatorInside);
        this.D = (TextView) inflate.findViewById(R.id.bannerTitle);
        this.F = (TextView) inflate.findViewById(R.id.numIndicator);
        this.E = (TextView) inflate.findViewById(R.id.numIndicatorInside);
        this.J.setImageResource(this.f);
        d();
    }

    private void b(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Banner);
            this.c = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_indicator_width, this.e);
            this.d = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_indicator_height, this.e);
            this.b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_indicator_margin, 5);
            this.l = obtainStyledAttributes.getResourceId(R.styleable.Banner_indicator_drawable_selected, R.drawable.gray_radius);
            this.m = obtainStyledAttributes.getResourceId(R.styleable.Banner_indicator_drawable_unselected, R.drawable.white_radius);
            this.w = obtainStyledAttributes.getInt(R.styleable.Banner_image_scale_type, this.w);
            this.h = obtainStyledAttributes.getInt(R.styleable.Banner_delay_time, ExceptionEngine.SERVER_ERROR);
            this.i = obtainStyledAttributes.getInt(R.styleable.Banner_scroll_time, 800);
            this.j = obtainStyledAttributes.getBoolean(R.styleable.Banner_is_auto_play, true);
            this.p = obtainStyledAttributes.getColor(R.styleable.Banner_title_background, -1);
            this.o = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_title_height, -1);
            this.q = obtainStyledAttributes.getColor(R.styleable.Banner_title_textcolor, -1);
            this.r = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Banner_title_textsize, -1);
            this.n = obtainStyledAttributes.getResourceId(R.styleable.Banner_banner_layout, this.n);
            this.f = obtainStyledAttributes.getResourceId(R.styleable.Banner_banner_default_image, R.drawable.no_banner);
            obtainStyledAttributes.recycle();
        }
    }

    private void d() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.N = new a(this.C.getContext());
            this.N.a(this.i);
            declaredField.set(this.C, this.N);
        } catch (Exception e) {
            Log.e(this.a, e.getMessage());
        }
    }

    public Banner a(ImageLoaderInterface imageLoaderInterface) {
        this.K = imageLoaderInterface;
        return this;
    }

    public Banner a(int i) {
        switch (i) {
            case 5:
                this.u = 19;
                break;
            case 6:
                this.u = 17;
                break;
            case 7:
                this.u = 21;
                break;
        }
        return this;
    }

    public Banner a(List<?> list) {
        this.y = list;
        this.s = list.size();
        return this;
    }

    public Banner a() {
        f();
        setImageList(this.y);
        i();
        return this;
    }

    private void e() {
        if (this.x.size() == this.y.size()) {
            if (this.p != -1) {
                this.I.setBackgroundColor(this.p);
            }
            if (this.o != -1) {
                this.I.setLayoutParams(new LayoutParams(-1, this.o));
            }
            if (this.q != -1) {
                this.D.setTextColor(this.q);
            }
            if (this.r != -1) {
                this.D.setTextSize(0, (float) this.r);
            }
            if (this.x != null && this.x.size() > 0) {
                this.D.setText((CharSequence) this.x.get(0));
                this.D.setVisibility(0);
                this.I.setVisibility(0);
                return;
            }
            return;
        }
        throw new RuntimeException("[Banner] --> The number of titles and images is different");
    }

    private void f() {
        int i = this.s > 1 ? 0 : 8;
        switch (this.g) {
            case 1:
                this.G.setVisibility(i);
                return;
            case 2:
                this.F.setVisibility(i);
                return;
            case 3:
                this.E.setVisibility(i);
                e();
                return;
            case 4:
                this.G.setVisibility(i);
                e();
                return;
            case 5:
                this.H.setVisibility(i);
                e();
                return;
            default:
                return;
        }
    }

    private void g() {
        this.z.clear();
        TextView textView;
        StringBuilder stringBuilder;
        if (this.g == 1 || this.g == 4 || this.g == 5) {
            h();
        } else if (this.g == 3) {
            textView = this.E;
            stringBuilder = new StringBuilder();
            stringBuilder.append("1/");
            stringBuilder.append(this.s);
            textView.setText(stringBuilder.toString());
        } else if (this.g == 2) {
            textView = this.F;
            stringBuilder = new StringBuilder();
            stringBuilder.append("1/");
            stringBuilder.append(this.s);
            textView.setText(stringBuilder.toString());
        }
    }

    private void setImageList(List<?> list) {
        if (list == null || list.size() <= 0) {
            this.J.setVisibility(0);
            Log.e(this.a, "The image data set is empty.");
            return;
        }
        this.J.setVisibility(8);
        g();
        for (int i = 0; i <= this.s + 1; i++) {
            Object obj;
            View view = null;
            if (this.K != null) {
                view = this.K.createImageView(this.B);
            }
            if (view == null) {
                view = new ImageView(this.B);
            }
            setScaleType(view);
            if (i == 0) {
                obj = list.get(this.s - 1);
            } else if (i == this.s + 1) {
                obj = list.get(0);
            } else {
                obj = list.get(i - 1);
            }
            this.z.add(view);
            if (this.K != null) {
                this.K.displayImage(this.B, obj, view);
            } else {
                Log.e(this.a, "Please set images loader.");
            }
        }
    }

    private void setScaleType(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            switch (this.w) {
                case 0:
                    imageView.setScaleType(ScaleType.CENTER);
                    return;
                case 1:
                    imageView.setScaleType(ScaleType.CENTER_CROP);
                    return;
                case 2:
                    imageView.setScaleType(ScaleType.CENTER_INSIDE);
                    return;
                case 3:
                    imageView.setScaleType(ScaleType.FIT_CENTER);
                    return;
                case 4:
                    imageView.setScaleType(ScaleType.FIT_END);
                    return;
                case 5:
                    imageView.setScaleType(ScaleType.FIT_START);
                    return;
                case 6:
                    imageView.setScaleType(ScaleType.FIT_XY);
                    return;
                case 7:
                    imageView.setScaleType(ScaleType.MATRIX);
                    return;
                default:
                    return;
            }
        }
    }

    private void h() {
        this.A.clear();
        this.G.removeAllViews();
        this.H.removeAllViews();
        for (int i = 0; i < this.s; i++) {
            ImageView imageView = new ImageView(this.B);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.c, this.d);
            layoutParams.leftMargin = this.b;
            layoutParams.rightMargin = this.b;
            if (i == 0) {
                imageView.setImageResource(this.l);
            } else {
                imageView.setImageResource(this.m);
            }
            this.A.add(imageView);
            if (this.g == 1 || this.g == 4) {
                this.G.addView(imageView, layoutParams);
            } else if (this.g == 5) {
                this.H.addView(imageView, layoutParams);
            }
        }
    }

    private void i() {
        this.t = 1;
        if (this.L == null) {
            this.L = new a();
            this.C.addOnPageChangeListener(this);
        }
        this.C.setAdapter(this.L);
        this.C.setFocusable(true);
        this.C.setCurrentItem(1);
        if (this.u != -1) {
            this.G.setGravity(this.u);
        }
        if (!this.k || this.s <= 1) {
            this.C.setScrollable(false);
        } else {
            this.C.setScrollable(true);
        }
        if (this.j) {
            b();
        }
    }

    public void b() {
        this.R.b(this.S);
        this.R.a(this.S, (long) this.h);
    }

    public void c() {
        this.R.b(this.S);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.j) {
            int action = motionEvent.getAction();
            if (action == 1 || action == 3 || action == 4) {
                b();
            } else if (action == 0) {
                c();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int b(int i) {
        i = (i - 1) % this.s;
        return i < 0 ? i + this.s : i;
    }

    public void onPageScrollStateChanged(int i) {
        if (this.M != null) {
            this.M.onPageScrollStateChanged(i);
        }
        switch (i) {
            case 0:
                if (this.t == 0) {
                    this.C.setCurrentItem(this.s, false);
                    return;
                } else if (this.t == this.s + 1) {
                    this.C.setCurrentItem(1, false);
                    return;
                } else {
                    return;
                }
            case 1:
                if (this.t == this.s + 1) {
                    this.C.setCurrentItem(1, false);
                    return;
                } else if (this.t == 0) {
                    this.C.setCurrentItem(this.s, false);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.M != null) {
            this.M.onPageScrolled(b(i), f, i2);
        }
    }

    public void onPageSelected(int i) {
        this.t = i;
        if (this.M != null) {
            this.M.onPageSelected(b(i));
        }
        if (this.g == 1 || this.g == 4 || this.g == 5) {
            ((ImageView) this.A.get(((this.v - 1) + this.s) % this.s)).setImageResource(this.m);
            ((ImageView) this.A.get(((i - 1) + this.s) % this.s)).setImageResource(this.l);
            this.v = i;
        }
        if (i == 0) {
            i = this.s;
        }
        if (i > this.s) {
            i = 1;
        }
        TextView textView;
        switch (this.g) {
            case 2:
                textView = this.F;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(i);
                stringBuilder.append("/");
                stringBuilder.append(this.s);
                textView.setText(stringBuilder.toString());
                return;
            case 3:
                textView = this.E;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(i);
                stringBuilder2.append("/");
                stringBuilder2.append(this.s);
                textView.setText(stringBuilder2.toString());
                this.D.setText((CharSequence) this.x.get(i - 1));
                return;
            case 4:
                this.D.setText((CharSequence) this.x.get(i - 1));
                return;
            case 5:
                this.D.setText((CharSequence) this.x.get(i - 1));
                return;
            default:
                return;
        }
    }

    public Banner a(tb tbVar) {
        this.P = tbVar;
        return this;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.M = onPageChangeListener;
    }
}
