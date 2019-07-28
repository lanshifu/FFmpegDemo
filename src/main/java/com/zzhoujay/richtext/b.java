package com.zzhoujay.richtext;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import defpackage.tm;
import defpackage.tu;
import defpackage.uf;
import defpackage.uh;
import defpackage.uj;
import defpackage.va;
import defpackage.vb;
import defpackage.vc;
import defpackage.vd;
import defpackage.ve;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: RichText */
public class b implements tu, vc {
    public static boolean a = false;
    private static Pattern b = Pattern.compile("<(img|IMG)(.*?)>");
    private static Pattern c = Pattern.compile("(width|WIDTH)=\"(.*?)\"");
    private static Pattern d = Pattern.compile("(height|HEIGHT)=\"(.*?)\"");
    private static Pattern e = Pattern.compile("(src|SRC)=\"(.*?)\"");
    private static final HashMap<String, Object> f = new HashMap();
    private HashMap<String, ImageHolder> g;
    private RichState h = RichState.ready;
    private final ve i;
    private final va j;
    private final WeakReference<TextView> k;
    private final c l;
    private int m;
    private int n;
    private SoftReference<SpannableStringBuilder> o;

    /* compiled from: RichText */
    private static class a extends AsyncTask<Void, Void, CharSequence> {
        private WeakReference<TextView> a;
        private b b;

        a(b bVar, TextView textView) {
            this.b = bVar;
            this.a = new WeakReference(textView);
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public CharSequence doInBackground(Void[] voidArr) {
            if (((TextView) this.a.get()) == null) {
                return null;
            }
            return this.b.c();
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public void onPostExecute(CharSequence charSequence) {
            if (this.a != null) {
                TextView textView = (TextView) this.a.get();
                if (textView != null && charSequence != null) {
                    textView.setText(charSequence);
                    if (this.b.l.r != null) {
                        this.b.l.r.a(false);
                    }
                }
            }
        }
    }

    static void a(Object obj, b bVar) {
        d.a().a(obj, bVar);
    }

    public static void a() {
        tm.a().b();
        d.a().b();
    }

    static void a(String str, Object obj) {
        synchronized (f) {
            f.put(str, obj);
        }
    }

    static Object a(String str) {
        Object obj;
        synchronized (f) {
            obj = f.get(str);
        }
        return obj;
    }

    b(c cVar, TextView textView) {
        this.l = cVar;
        this.k = new WeakReference(textView);
        if (cVar.b == RichType.markdown) {
            this.i = new vd(textView);
        } else {
            this.i = new vb(new uh(textView));
        }
        if (cVar.m > 0) {
            textView.setMovementMethod(new uj());
        } else if (cVar.m == 0) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        this.j = new va();
        cVar.a(this);
    }

    public static com.zzhoujay.richtext.c.a b(String str) {
        return a(str, RichType.html);
    }

    public static com.zzhoujay.richtext.c.a a(String str, RichType richType) {
        return new com.zzhoujay.richtext.c.a(str, richType);
    }

    /* Access modifiers changed, original: 0000 */
    public void b() {
        final TextView textView = (TextView) this.k.get();
        if (textView != null) {
            textView.post(new Runnable() {
                public void run() {
                    b.this.a(textView);
                }
            });
        }
    }

    private void a(TextView textView) {
        a aVar = new a(this, textView);
        WeakReference weakReference = new WeakReference(textView);
        if (this.l.u) {
            aVar.execute(new Void[0]);
        } else {
            aVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private CharSequence c() {
        SpannableStringBuilder spannableStringBuilder = null;
        if (((TextView) this.k.get()) == null) {
            return null;
        }
        if (this.l.b != RichType.markdown) {
            d(this.l.a);
        } else {
            this.g = new HashMap();
        }
        this.h = RichState.loading;
        if (this.l.g.intValue() > CacheType.none.intValue() + 100) {
            spannableStringBuilder = d.a().a(this.l.a);
        }
        if (spannableStringBuilder == null) {
            spannableStringBuilder = d();
        }
        this.o = new SoftReference(spannableStringBuilder);
        this.l.t.a(this);
        this.m = this.j.a(spannableStringBuilder, (vc) this, this.l);
        return spannableStringBuilder;
    }

    @NonNull
    private SpannableStringBuilder d() {
        CharSequence a = this.i.a(this.l.a);
        if (a instanceof SpannableStringBuilder) {
            return (SpannableStringBuilder) a;
        }
        if (a == null) {
            a = new SpannableString("");
        }
        return new SpannableStringBuilder(a);
    }

    private synchronized void d(String str) {
        this.g = new HashMap();
        int i = 0;
        Matcher matcher = b.matcher(str);
        while (matcher.find()) {
            String trim = matcher.group(2).trim();
            Matcher matcher2 = e.matcher(trim);
            CharSequence charSequence = null;
            if (matcher2.find()) {
                charSequence = matcher2.group(2).trim();
            }
            if (!TextUtils.isEmpty(charSequence)) {
                ImageHolder imageHolder = new ImageHolder(charSequence, i, this.l, (TextView) this.k.get());
                imageHolder.a(f(charSequence));
                if (!(this.l.c || this.l.d)) {
                    Matcher matcher3 = c.matcher(trim);
                    if (matcher3.find()) {
                        imageHolder.a(e(matcher3.group(2).trim()));
                    }
                    Matcher matcher4 = d.matcher(trim);
                    if (matcher4.find()) {
                        imageHolder.b(e(matcher4.group(2).trim()));
                    }
                }
                this.g.put(imageHolder.d(), imageHolder);
                i++;
            }
        }
    }

    private static int e(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.parseInt(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private static boolean f(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf <= 0 || !"gif".toUpperCase().equals(str.substring(lastIndexOf + 1).toUpperCase())) {
            return false;
        }
        return true;
    }

    public Drawable c(String str) {
        this.n++;
        if (this.l.t == null || this.l.l) {
            return null;
        }
        TextView textView = (TextView) this.k.get();
        if (textView == null || !uf.b(textView.getContext())) {
            return null;
        }
        ImageHolder imageHolder;
        if (this.l.b == RichType.markdown) {
            imageHolder = new ImageHolder(str, this.n - 1, this.l, textView);
            this.g.put(str, imageHolder);
        } else {
            imageHolder = (ImageHolder) this.g.get(str);
            if (imageHolder == null) {
                imageHolder = new ImageHolder(str, this.n - 1, this.l, textView);
                this.g.put(str, imageHolder);
            }
        }
        imageHolder.c(0);
        if (this.l.j != null) {
            this.l.j.a(imageHolder);
            if (!imageHolder.h()) {
                return null;
            }
        }
        return this.l.t.a(imageHolder, this.l, textView);
    }

    public void a(Object obj) {
        if ((obj instanceof Integer) && ((Integer) obj).intValue() >= this.m) {
            this.h = RichState.loaded;
            TextView textView = (TextView) this.k.get();
            if (this.l.g.intValue() >= CacheType.layout.intValue()) {
                SpannableStringBuilder spannableStringBuilder = (SpannableStringBuilder) this.o.get();
                if (spannableStringBuilder != null) {
                    d.a().a(this.l.a, spannableStringBuilder);
                }
            }
            if (this.l.r != null && textView != null) {
                textView.post(new Runnable() {
                    public void run() {
                        b.this.l.r.a(true);
                    }
                });
            }
        }
    }
}
