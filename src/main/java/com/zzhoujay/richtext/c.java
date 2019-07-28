package com.zzhoujay.richtext;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.Pair;
import android.widget.TextView;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.zzhoujay.richtext.ImageHolder.ScaleType;
import defpackage.tp;
import defpackage.tr;
import defpackage.ts;
import defpackage.tt;
import defpackage.tv;
import defpackage.tw;
import defpackage.tx;
import defpackage.ty;
import defpackage.tz;
import defpackage.ua;
import defpackage.ur;
import defpackage.us;
import defpackage.uu;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* compiled from: RichTextConfig */
public final class c {
    public final String a;
    public final RichType b;
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final ScaleType f;
    public final CacheType g;
    public final int h;
    public final int i;
    public final ts j;
    public final tv k;
    public final boolean l;
    public final int m;
    public final tw n;
    public final ty o;
    public final tx p;
    public final tz q;
    public final tp r;
    public final ua s;
    final tt t;
    public final boolean u;
    public final uu v;
    public final tr w;
    public final tr x;
    private WeakReference<b> y;
    private final HashMap<String, Object> z;

    /* compiled from: RichTextConfig */
    public static final class a {
        private static final tr A = new tr() {
            public Drawable a(ImageHolder imageHolder, c cVar, TextView textView) {
                ColorDrawable colorDrawable = new ColorDrawable(-3355444);
                int width = textView.getWidth();
                colorDrawable.setBounds(0, 0, width, width / 2);
                a.z.obtainMessage(9, Pair.create(colorDrawable, textView)).sendToTarget();
                return colorDrawable;
            }
        };
        private static final tr B = new tr() {
            public Drawable a(ImageHolder imageHolder, c cVar, TextView textView) {
                ColorDrawable colorDrawable = new ColorDrawable(-12303292);
                int width = textView.getWidth();
                colorDrawable.setBounds(0, 0, width, width / 2);
                a.z.obtainMessage(9, Pair.create(colorDrawable, textView)).sendToTarget();
                return colorDrawable;
            }
        };
        private static final Handler z = new Handler(Looper.getMainLooper()) {
            public void dispatchMessage(Message message) {
                if (message.what == 9) {
                    Pair pair = (Pair) message.obj;
                    Drawable drawable = (Drawable) pair.first;
                    TextView textView = (TextView) pair.second;
                    int width = (textView.getWidth() - textView.getPaddingLeft()) - textView.getPaddingRight();
                    drawable.setBounds(0, 0, width, width / 2);
                }
            }
        };
        final String a;
        RichType b;
        boolean c = true;
        boolean d = false;
        CacheType e = CacheType.all;
        ts f;
        tv g;
        boolean h = false;
        int i = 0;
        tw j;
        ty k;
        tx l;
        tz m;
        tt n;
        tp o;
        WeakReference<Object> p;
        boolean q = false;
        ScaleType r = ScaleType.none;
        int s = CheckView.UNCHECKED;
        int t = CheckView.UNCHECKED;
        ua u = new ua();
        boolean v = true;
        uu w;
        tr x = A;
        tr y = B;

        a(String str, RichType richType) {
            this.a = str;
            this.b = richType;
        }

        public b a(TextView textView) {
            if (this.n == null) {
                this.n = new us();
            }
            if ((this.n instanceof us) && this.w == null) {
                try {
                    Class cls = Class.forName("com.zzhoujay.okhttpimagedownloader.OkHttpImageDownloader");
                    uu uuVar = (uu) b.a("com.zzhoujay.okhttpimagedownloader.OkHttpImageDownloader");
                    if (uuVar == null) {
                        uuVar = (uu) cls.newInstance();
                        b.a("com.zzhoujay.okhttpimagedownloader.OkHttpImageDownloader", (Object) uuVar);
                    }
                    this.w = uuVar;
                } catch (Exception unused) {
                    uu uuVar2 = (ur) b.a(ur.a);
                    if (uuVar2 == null) {
                        uuVar2 = new ur();
                        b.a(ur.a, (Object) uuVar2);
                    }
                    this.w = uuVar2;
                }
            }
            b bVar = new b(new c(this), textView);
            if (this.p != null) {
                b.a(this.p.get(), bVar);
            }
            this.p = null;
            bVar.b();
            return bVar;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(b bVar) {
        if (this.y == null) {
            this.y = new WeakReference(bVar);
        }
    }

    private c(a aVar) {
        a aVar2 = aVar;
        this(aVar2.a, aVar2.b, aVar2.c, aVar2.d, aVar2.e, aVar2.f, aVar2.g, aVar2.h, aVar2.i, aVar2.j, aVar2.k, aVar2.l, aVar2.m, aVar2.n, aVar2.o, aVar2.q, aVar2.r, aVar2.s, aVar2.t, aVar2.u, aVar2.v, aVar2.w, aVar2.x, aVar2.y);
    }

    private c(String str, RichType richType, boolean z, boolean z2, CacheType cacheType, ts tsVar, tv tvVar, boolean z3, int i, tw twVar, ty tyVar, tx txVar, tz tzVar, tt ttVar, tp tpVar, boolean z4, ScaleType scaleType, int i2, int i3, ua uaVar, boolean z5, uu uuVar, tr trVar, tr trVar2) {
        tw twVar2 = twVar;
        ty tyVar2 = tyVar;
        tx txVar2 = txVar;
        tz tzVar2 = tzVar;
        this.a = str;
        this.b = richType;
        this.c = z;
        this.d = z2;
        this.j = tsVar;
        this.k = tvVar;
        this.l = z3;
        this.g = cacheType;
        this.n = twVar2;
        this.o = tyVar2;
        this.p = txVar2;
        this.q = tzVar2;
        this.t = ttVar;
        this.r = tpVar;
        this.f = scaleType;
        this.e = z4;
        this.h = i2;
        this.i = i3;
        this.s = uaVar;
        this.u = z5;
        this.v = uuVar;
        this.w = trVar;
        this.x = trVar2;
        int i4 = (i != 0 || (txVar2 == null && tzVar2 == null && twVar2 == null && tyVar2 == null)) ? i : 1;
        this.m = i4;
        this.z = new HashMap();
    }
}
