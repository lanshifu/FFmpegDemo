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

/* compiled from: ViewPropertyAnimatorHC */
/* renamed from: nt */
class nt extends ns {
    ArrayList<b> a = new ArrayList();
    private final WeakReference<View> b;
    private long c;
    private boolean d = false;
    private long e = 0;
    private boolean f = false;
    private Interpolator g;
    private boolean h = false;
    private a i = null;
    private a j = new a(this, null);
    private Runnable k = new 1();
    private HashMap<nh, c> l = new HashMap();

    /* compiled from: ViewPropertyAnimatorHC */
    /* renamed from: nt$1 */
    class 1 implements Runnable {
        1() {
        }

        public void run() {
            nt.this.a();
        }
    }

    /* compiled from: ViewPropertyAnimatorHC */
    /* renamed from: nt$b */
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

    /* compiled from: ViewPropertyAnimatorHC */
    /* renamed from: nt$c */
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

    /* compiled from: ViewPropertyAnimatorHC */
    /* renamed from: nt$a */
    private class a implements defpackage.nh.a, b {
        private a() {
        }

        /* synthetic */ a(nt ntVar, 1 1) {
            this();
        }

        public void a(nh nhVar) {
            if (nt.this.i != null) {
                nt.this.i.a(nhVar);
            }
        }

        public void c(nh nhVar) {
            if (nt.this.i != null) {
                nt.this.i.c(nhVar);
            }
        }

        public void d(nh nhVar) {
            if (nt.this.i != null) {
                nt.this.i.d(nhVar);
            }
        }

        public void b(nh nhVar) {
            if (nt.this.i != null) {
                nt.this.i.b(nhVar);
            }
            nt.this.l.remove(nhVar);
            if (nt.this.l.isEmpty()) {
                nt.this.i = null;
            }
        }

        public void a(np npVar) {
            float f = npVar.f();
            c cVar = (c) nt.this.l.get(npVar);
            if ((cVar.a & 511) != 0) {
                View view = (View) nt.this.b.get();
                if (view != null) {
                    view.invalidate();
                }
            }
            ArrayList arrayList = cVar.b;
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    b bVar = (b) arrayList.get(i);
                    nt.this.b(bVar.a, bVar.b + (bVar.c * f));
                }
            }
            View view2 = (View) nt.this.b.get();
            if (view2 != null) {
                view2.invalidate();
            }
        }
    }

    nt(View view) {
        this.b = new WeakReference(view);
    }

    public ns a(long j) {
        if (j >= 0) {
            this.d = true;
            this.c = j;
            return this;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Animators cannot have negative duration: ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public ns a(Interpolator interpolator) {
        this.h = true;
        this.g = interpolator;
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
        this.l.put(a, new c(i2, arrayList));
        a.a(this.j);
        a.a(this.j);
        if (this.f) {
            a.c(this.e);
        }
        if (this.d) {
            a.a(this.c);
        }
        if (this.h) {
            a.a(this.g);
        }
        a.a();
    }

    private void a(int i, float f) {
        float a = a(i);
        a(i, a, f - a);
    }

    private void a(int i, float f, float f2) {
        if (this.l.size() > 0) {
            nh nhVar = null;
            for (nh nhVar2 : this.l.keySet()) {
                c cVar = (c) this.l.get(nhVar2);
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
        View view = (View) this.b.get();
        if (view != null) {
            view.removeCallbacks(this.k);
            view.post(this.k);
        }
    }

    private void b(int i, float f) {
        View view = (View) this.b.get();
        if (view == null) {
            return;
        }
        if (i == 4) {
            view.setScaleX(f);
        } else if (i == 8) {
            view.setScaleY(f);
        } else if (i == 16) {
            view.setRotation(f);
        } else if (i == 32) {
            view.setRotationX(f);
        } else if (i == 64) {
            view.setRotationY(f);
        } else if (i == 128) {
            view.setX(f);
        } else if (i == 256) {
            view.setY(f);
        } else if (i != IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED) {
            switch (i) {
                case 1:
                    view.setTranslationX(f);
                    return;
                case 2:
                    view.setTranslationY(f);
                    return;
                default:
                    return;
            }
        } else {
            view.setAlpha(f);
        }
    }

    private float a(int i) {
        View view = (View) this.b.get();
        if (view != null) {
            if (i == 4) {
                return view.getScaleX();
            }
            if (i == 8) {
                return view.getScaleY();
            }
            if (i == 16) {
                return view.getRotation();
            }
            if (i == 32) {
                return view.getRotationX();
            }
            if (i == 64) {
                return view.getRotationY();
            }
            if (i == 128) {
                return view.getX();
            }
            if (i == 256) {
                return view.getY();
            }
            if (i == IjkMediaMeta.FF_PROFILE_H264_CONSTRAINED) {
                return view.getAlpha();
            }
            switch (i) {
                case 1:
                    return view.getTranslationX();
                case 2:
                    return view.getTranslationY();
            }
        }
        return CropImageView.DEFAULT_ASPECT_RATIO;
    }
}
