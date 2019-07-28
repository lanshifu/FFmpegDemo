package com.wj.rebound.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.sk;
import defpackage.sm;
import defpackage.sn;
import defpackage.so;
import defpackage.sp;
import defpackage.sr;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SpringConfiguratorView extends FrameLayout {
    private static final DecimalFormat a = new DecimalFormat("#.#");
    private final d b;
    private final List<sn> c;
    private final sm d;
    private final float e;
    private final float f;
    private final so g;
    private final int h;
    private SeekBar i;
    private SeekBar j;
    private Spinner k;
    private TextView l;
    private TextView m;
    private sn n;

    private class a implements OnTouchListener {
        private a() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                SpringConfiguratorView.this.c();
            }
            return true;
        }
    }

    private class c implements OnSeekBarChangeListener {
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        private c() {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (seekBar == SpringConfiguratorView.this.i) {
                float f = ((((float) i) * 200.0f) / 100000.0f) + CropImageView.DEFAULT_ASPECT_RATIO;
                double d = (double) f;
                SpringConfiguratorView.this.n.b = sk.a(d);
                String format = SpringConfiguratorView.a.format(d);
                TextView d2 = SpringConfiguratorView.this.m;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("T:");
                stringBuilder.append(format);
                d2.setText(stringBuilder.toString());
            }
            if (seekBar == SpringConfiguratorView.this.j) {
                float f2 = ((((float) i) * 50.0f) / 100000.0f) + CropImageView.DEFAULT_ASPECT_RATIO;
                double d3 = (double) f2;
                SpringConfiguratorView.this.n.a = sk.c(d3);
                String format2 = SpringConfiguratorView.a.format(d3);
                TextView f3 = SpringConfiguratorView.this.l;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("F:");
                stringBuilder2.append(format2);
                f3.setText(stringBuilder2.toString());
            }
        }
    }

    private class d extends BaseAdapter {
        private final Context b;
        private final List<String> c = new ArrayList();

        public long getItemId(int i) {
            return (long) i;
        }

        public d(Context context) {
            this.b = context;
        }

        public int getCount() {
            return this.c.size();
        }

        public Object getItem(int i) {
            return this.c.get(i);
        }

        public void a(String str) {
            this.c.add(str);
            notifyDataSetChanged();
        }

        public void a() {
            this.c.clear();
            notifyDataSetChanged();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView;
            if (view == null) {
                textView = new TextView(this.b);
                textView.setLayoutParams(new LayoutParams(-1, -1));
                int a = a.a(12.0f, SpringConfiguratorView.this.getResources());
                textView.setPadding(a, a, a, a);
                textView.setTextColor(SpringConfiguratorView.this.h);
            } else {
                textView = (TextView) view;
            }
            textView.setText((CharSequence) this.c.get(i));
            return textView;
        }
    }

    private class e implements OnItemSelectedListener {
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        private e() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            SpringConfiguratorView.this.n = (sn) SpringConfiguratorView.this.c.get(i);
            SpringConfiguratorView.this.a(SpringConfiguratorView.this.n);
        }
    }

    private class b implements sp {
        public void b(sm smVar) {
        }

        public void c(sm smVar) {
        }

        public void d(sm smVar) {
        }

        private b() {
        }

        public void a(sm smVar) {
            float b = (float) smVar.b();
            float h = SpringConfiguratorView.this.f;
            SpringConfiguratorView.this.setTranslationY((b * (SpringConfiguratorView.this.e - h)) + h);
        }
    }

    public SpringConfiguratorView(Context context) {
        this(context, null);
    }

    public SpringConfiguratorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @TargetApi(11)
    public SpringConfiguratorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ArrayList();
        this.h = Color.argb(255, 225, 225, 225);
        sr c = sr.c();
        this.g = so.a();
        this.b = new d(context);
        Resources resources = getResources();
        this.f = (float) a.a(40.0f, resources);
        this.e = (float) a.a(280.0f, resources);
        this.d = c.b();
        this.d.a(1.0d).b(1.0d).a(new b());
        addView(a(context));
        c cVar = new c();
        this.i.setMax(100000);
        this.i.setOnSeekBarChangeListener(cVar);
        this.j.setMax(100000);
        this.j.setOnSeekBarChangeListener(cVar);
        this.k.setAdapter(this.b);
        this.k.setOnItemSelectedListener(new e());
        a();
        setTranslationY(this.e);
    }

    private View a(Context context) {
        Resources resources = getResources();
        int a = a.a(5.0f, resources);
        int a2 = a.a(10.0f, resources);
        int a3 = a.a(20.0f, resources);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(0, -2, 1.0f);
        layoutParams.setMargins(0, 0, a, 0);
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(a.a(-1, a.a(300.0f, resources)));
        FrameLayout frameLayout2 = new FrameLayout(context);
        FrameLayout.LayoutParams a4 = a.a();
        a4.setMargins(0, a3, 0, 0);
        frameLayout2.setLayoutParams(a4);
        frameLayout2.setBackgroundColor(Color.argb(100, 0, 0, 0));
        frameLayout.addView(frameLayout2);
        this.k = new Spinner(context, 0);
        a4 = a.b();
        a4.gravity = 48;
        a4.setMargins(a2, a2, a2, 0);
        this.k.setLayoutParams(a4);
        frameLayout2.addView(this.k);
        LinearLayout linearLayout = new LinearLayout(context);
        FrameLayout.LayoutParams b = a.b();
        b.setMargins(0, 0, 0, a.a(80.0f, resources));
        b.gravity = 80;
        linearLayout.setLayoutParams(b);
        linearLayout.setOrientation(1);
        frameLayout2.addView(linearLayout);
        LinearLayout linearLayout2 = new LinearLayout(context);
        FrameLayout.LayoutParams b2 = a.b();
        b2.setMargins(a2, a2, a2, a3);
        linearLayout2.setPadding(a2, a2, a2, a2);
        linearLayout2.setLayoutParams(b2);
        linearLayout2.setOrientation(0);
        linearLayout.addView(linearLayout2);
        this.i = new SeekBar(context);
        this.i.setLayoutParams(layoutParams);
        linearLayout2.addView(this.i);
        this.m = new TextView(getContext());
        this.m.setTextColor(this.h);
        FrameLayout.LayoutParams a5 = a.a(a.a(50.0f, resources), -1);
        this.m.setGravity(19);
        this.m.setLayoutParams(a5);
        this.m.setMaxLines(1);
        linearLayout2.addView(this.m);
        linearLayout2 = new LinearLayout(context);
        a5 = a.b();
        a5.setMargins(a2, a2, a2, a3);
        linearLayout2.setPadding(a2, a2, a2, a2);
        linearLayout2.setLayoutParams(a5);
        linearLayout2.setOrientation(0);
        linearLayout.addView(linearLayout2);
        this.j = new SeekBar(context);
        this.j.setLayoutParams(layoutParams);
        linearLayout2.addView(this.j);
        this.l = new TextView(getContext());
        this.l.setTextColor(this.h);
        FrameLayout.LayoutParams a6 = a.a(a.a(50.0f, resources), -1);
        this.l.setGravity(19);
        this.l.setLayoutParams(a6);
        this.l.setMaxLines(1);
        linearLayout2.addView(this.l);
        View view = new View(context);
        FrameLayout.LayoutParams a7 = a.a(a.a(60.0f, resources), a.a(40.0f, resources));
        a7.gravity = 49;
        view.setLayoutParams(a7);
        view.setOnTouchListener(new a());
        view.setBackgroundColor(Color.argb(255, 0, 164, 209));
        frameLayout.addView(view);
        return frameLayout;
    }

    public void a() {
        Map b = this.g.b();
        this.b.a();
        this.c.clear();
        for (Entry entry : b.entrySet()) {
            if (entry.getKey() != sn.c) {
                this.c.add(entry.getKey());
                this.b.a((String) entry.getValue());
            }
        }
        this.c.add(sn.c);
        this.b.a((String) b.get(sn.c));
        this.b.notifyDataSetChanged();
        if (this.c.size() > 0) {
            this.k.setSelection(0);
        }
    }

    private void a(sn snVar) {
        int round = Math.round(((((float) sk.b(snVar.b)) - CropImageView.DEFAULT_ASPECT_RATIO) * 100000.0f) / 200.0f);
        int round2 = Math.round(((((float) sk.d(snVar.a)) - CropImageView.DEFAULT_ASPECT_RATIO) * 100000.0f) / 50.0f);
        this.i.setProgress(round);
        this.j.setProgress(round2);
    }

    private void c() {
        double c = this.d.c();
        sm smVar = this.d;
        double d = 1.0d;
        if (c == 1.0d) {
            d = 0.0d;
        }
        smVar.b(d);
    }
}
