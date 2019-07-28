package defpackage;

import android.view.View;
import android.view.animation.Interpolator;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.nh.a;
import defpackage.np.b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: ViewPropertyAnimatorPreHC */
/* renamed from: nv */
class nv extends ns {
    ArrayList<b> a = new ArrayList();
    private final nw b;
    private final WeakReference<View> c;
    private long d;
    private boolean e = false;
    private long f = 0;
    private boolean g = false;
    private Interpolator h;
    private boolean i = false;
    private a j = null;
    private a k = new a(this, null);
    private Runnable l = new 1();
    private HashMap<nh, c> m = new HashMap();

    /* compiled from: ViewPropertyAnimatorPreHC */
    /* renamed from: nv$1 */
    class 1 implements Runnable {
        1() {
        }

        public void run() {
            nv.this.a();
        }
    }

    /* compiled from: ViewPropertyAnimatorPreHC */
    /* renamed from: nv$b */
    private static class b {
        int a;
        float b;
        float c;

        b(int i, float f, float f2) {
            this.a = i;
            this.b = f;
            this.c = f2;
        }
    }

    /* compiled from: ViewPropertyAnimatorPreHC */
    /* renamed from: nv$c */
    private static class c {
        int a;
        ArrayList<b> b;

        c(int i, ArrayList<b> arrayList) {
            this.a = i;
            this.b = arrayList;
        }

        /* Access modifiers changed, original: 0000 */
        public boolean a(int i) {
            if (!((this.a & i) == 0 || this.b == null)) {
                int size = this.b.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (((b) this.b.get(i2)).a == i) {
                        this.b.remove(i2);
                        this.a = (i ^ -1) & this.a;
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* compiled from: ViewPropertyAnimatorPreHC */
    /* renamed from: nv$a */
    private class a implements defpackage.nh.a, b {
        private a() {
        }

        /* synthetic */ a(nv nvVar, 1 1) {
            this();
        }

        public void a(nh nhVar) {
            if (nv.this.j != null) {
                nv.this.j.a(nhVar);
            }
        }

        public void c(nh nhVar) {
            if (nv.this.j != null) {
                nv.this.j.c(nhVar);
            }
        }

        public void d(nh nhVar) {
            if (nv.this.j != null) {
                nv.this.j.d(nhVar);
            }
        }

        public void b(nh nhVar) {
            if (nv.this.j != null) {
                nv.this.j.b(nhVar);
            }
            nv.this.m.remove(nhVar);
            if (nv.this.m.isEmpty()) {
                nv.this.j = null;
            }
        }

        public void a(np npVar) {
            float f = npVar.f();
            c cVar = (c) nv.this.m.get(npVar);
            if ((cVar.a & 511) != 0) {
                View view = (View) nv.this.c.get();
                if (view != null) {
                    view.invalidate();
                }
            }
            ArrayList arrayList = cVar.b;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    b bVar = (b) arrayList.get(i);
                    nv.this.b(bVar.a, bVar.b + (bVar.c * f));
                }
            }
            View view2 = (View) nv.this.c.get();
            if (view2 != null) {
                view2.invalidate();
            }
        }
    }

    nv(View view) {
        this.c = new WeakReference(view);
        this.b = nw.a(view);
    }

    public ns a(long j) {
        if (j >= 0) {
            this.e = true;
            this.d = j;
            return this;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Animators cannot have negative duration: ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public ns a(Interpolator interpolator) {
        this.i = true;
        this.h = interpolator;
        return this;
    }

    public ns a(float f) {
        a(2, f);
        return this;
    }

    private void a() {
        float[] fArr = new float[1];
        int i = 0;
        fArr[0] = 1.0f;
        np a = np.a(fArr);
        ArrayList arrayList = (ArrayList) this.a.clone();
        this.a.clear();
        int i2 = 0;
        while (i < arrayList.size()) {
            i2 |= ((b) arrayList.get(i)).a;
            i++;
        }
        this.m.put(a, new c(i2, arrayList));
        a.a(this.k);
        a.a(this.k);
        if (this.g) {
            a.c(this.f);
        }
        if (this.e) {
            a.a(this.d);
        }
        if (this.i) {
            a.a(this.h);
        }
        a.a();
    }

    private void a(int i, float f) {
        float a = a(i);
        a(i, a, f - a);
    }

    private void a(int i, float f, float f2) {
        if (this.m.size() > 0) {
            nh nhVar = null;
            for (nh nhVar2 : this.m.keySet()) {
                c cVar = (c) this.m.get(nhVar2);
                if (cVar.a(i) && cVar.a == 0) {
                    nhVar = nhVar2;
                    break;
                }
            }
            if (nhVar != null) {
                nhVar.b();
            }
        }
        this.a.add(new b(i, f, f2));
        View view = (View) this.c.get();
        if (view != null) {
            view.removeCallbacks(this.l);
            view.post(this.l);
        }
    }

    private void b(int i, float f) {
        if (i == 4) {
            this.b.e(f);
        } else if (i == 8) {
            this.b.f(f);
        } else if (i == 16) {
            this.b.b(f);
        } else if (i == 32) {
            this.b.c(f);
        } else if (i == 64) {
            this.b.d(f);
        } else if (i == 128) {
            this.b.i(f);
        } else if (i == 256) {
            this.b.j(f);
        } else if (i != IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED) {
            switch (i) {
                case 1:
                    this.b.g(f);
                    return;
                case 2:
                    this.b.h(f);
                    return;
                default:
                    return;
            }
        } else {
            this.b.a(f);
        }
    }

    private float a(int i) {
        if (i == 4) {
            return this.b.e();
        }
        if (i == 8) {
            return this.b.f();
        }
        if (i == 16) {
            return this.b.b();
        }
        if (i == 32) {
            return this.b.c();
        }
        if (i == 64) {
            return this.b.d();
        }
        if (i == 128) {
            return this.b.i();
        }
        if (i == 256) {
            return this.b.j();
        }
        if (i == IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED) {
            return this.b.a();
        }
        switch (i) {
            case 1:
                return this.b.g();
            case 2:
                return this.b.h();
            default:
                return CropImageView.DEFAULT_ASPECT_RATIO;
        }
    }
}
