package defpackage;

import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.TextView;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import com.zzhoujay.richtext.CacheType;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.ImageHolder.a;
import com.zzhoujay.richtext.c;
import com.zzhoujay.richtext.exceptions.ImageDecodeException;
import java.lang.ref.WeakReference;

/* compiled from: AbstractImageLoader */
/* renamed from: um */
abstract class um<T> {
    final ImageHolder a;
    private final c b;
    private final WeakReference<uc> c;
    private final uz<T> d;
    private final WeakReference<TextView> e;
    private final WeakReference<tu> f;
    private WeakReference<uw> g;

    um(ImageHolder imageHolder, c cVar, TextView textView, uc ucVar, tu tuVar, uz<T> uzVar) {
        this.a = imageHolder;
        this.b = cVar;
        this.d = uzVar;
        this.e = new WeakReference(textView);
        this.c = new WeakReference(ucVar);
        this.f = new WeakReference(tuVar);
        a();
    }

    public void a() {
        if (b()) {
            uc ucVar = (uc) this.c.get();
            if (ucVar != null) {
                this.a.c(1);
                Drawable j = this.a.j();
                Rect bounds = j.getBounds();
                ucVar.a(j);
                if (this.b.j != null) {
                    this.b.j.b(this.a);
                }
                if (ucVar.c()) {
                    j.setBounds(ucVar.getBounds());
                } else {
                    ucVar.a(this.a.e());
                    ucVar.a(this.a.i());
                    ucVar.setBounds(0, 0, a(bounds.width()), b(bounds.height()));
                    ucVar.b();
                }
                c();
            }
        }
    }

    public int a(int i, int i2) {
        this.a.c(4);
        a aVar = new a(i, i2);
        if (this.b.j != null) {
            this.b.j.a(this.a, i, i2, aVar);
        }
        if (aVar.c()) {
            i = um.a(i, i2, aVar.a(), aVar.b());
        } else {
            i = um.a(i, i2, e(), Filter.MAX);
        }
        if (i == 0) {
            i = 0;
        } else {
            i = Integer.highestOneBit(i);
        }
        return Math.max(1, i);
    }

    public void a(Exception exception) {
        if (b()) {
            uc ucVar = (uc) this.c.get();
            if (ucVar != null) {
                this.a.c(3);
                Drawable k = this.a.k();
                Rect bounds = k.getBounds();
                ucVar.a(k);
                if (this.b.j != null) {
                    this.b.j.a(this.a, exception);
                }
                if (ucVar.c()) {
                    k.setBounds(ucVar.getBounds());
                } else {
                    ucVar.a(this.a.e());
                    ucVar.setBounds(0, 0, a(bounds.width()), b(bounds.height()));
                    ucVar.a(this.a.i());
                    ucVar.b();
                }
                c();
                d();
            }
        }
    }

    public void a(uw uwVar) {
        if (uwVar == null) {
            a(new ImageDecodeException());
            return;
        }
        uc ucVar = (uc) this.c.get();
        if (ucVar != null) {
            TextView textView = (TextView) this.e.get();
            if (textView != null) {
                this.g = new WeakReference(uwVar);
                this.a.c(2);
                Drawable a = uwVar.a(textView.getResources());
                ucVar.a(a);
                int e = uwVar.e();
                int d = uwVar.d();
                if (this.b.j != null) {
                    this.b.j.a(this.a, e, d);
                }
                if (ucVar.c()) {
                    a.setBounds(ucVar.getBounds());
                } else {
                    ucVar.a(this.a.e());
                    ucVar.setBounds(0, 0, a(e), b(d));
                    ucVar.a(this.a.i());
                    ucVar.b();
                }
                if (uwVar.a() && this.a.g()) {
                    uwVar.b().a(textView);
                }
                tm a2 = tm.a();
                String a3 = this.a.a();
                if (this.b.g.intValue() > CacheType.none.intValue() && !ucVar.c()) {
                    a2.a(a3, ucVar.a());
                }
                if (this.b.g.intValue() > CacheType.layout.intValue() && !uwVar.a()) {
                    a2.a(a3, uwVar.c());
                }
                c();
                d();
            }
        }
    }

    private int[] a(T t, Options options) {
        options.inJustDecodeBounds = true;
        this.d.b(t, options);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    private static int a(int i, int i2, int i3, int i4) {
        i = (int) Math.ceil((double) Math.max(((float) i2) / ((float) i4), ((float) i) / ((float) i3)));
        i3 = 1;
        i2 = Math.max(1, Integer.highestOneBit(i));
        if (i2 >= i) {
            i3 = 0;
        }
        return i2 << i3;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(T t) {
        Options options = new Options();
        int[] a = a((Object) t, options);
        options.inSampleSize = a(a[0], a[1]);
        options.inPreferredConfig = Config.RGB_565;
        a(this.d.a(this.a, t, options));
    }

    private boolean b() {
        TextView textView = (TextView) this.e.get();
        if (textView == null) {
            return false;
        }
        return uf.b(textView.getContext());
    }

    private void c() {
        final TextView textView = (TextView) this.e.get();
        if (textView != null) {
            textView.post(new Runnable() {
                public void run() {
                    textView.setText(textView.getText());
                }
            });
        }
    }

    private void d() {
        tu tuVar = (tu) this.f.get();
        if (tuVar != null) {
            tuVar.a(this);
        }
    }

    private int e() {
        TextView textView = (TextView) this.e.get();
        if (textView == null) {
            return 0;
        }
        return (textView.getWidth() - textView.getPaddingRight()) - textView.getPaddingLeft();
    }

    private int f() {
        TextView textView = (TextView) this.e.get();
        if (textView == null) {
            return 0;
        }
        return (textView.getHeight() - textView.getPaddingTop()) - textView.getPaddingBottom();
    }

    private int a(int i) {
        int c = this.a.c();
        if (c == Filter.MAX) {
            return e();
        }
        return c == CheckView.UNCHECKED ? i : c;
    }

    private int b(int i) {
        int b = this.a.b();
        if (b == Filter.MAX) {
            return f();
        }
        return b == CheckView.UNCHECKED ? i : b;
    }
}
