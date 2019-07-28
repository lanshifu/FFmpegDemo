package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AndroidRuntimeException;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.nh.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: ValueAnimator */
/* renamed from: np */
public class np extends nh {
    private static ThreadLocal<a> h = new ThreadLocal();
    private static final ThreadLocal<ArrayList<np>> i = new 1();
    private static final ThreadLocal<ArrayList<np>> j = new 2();
    private static final ThreadLocal<ArrayList<np>> k = new 3();
    private static final ThreadLocal<ArrayList<np>> l = new 4();
    private static final ThreadLocal<ArrayList<np>> m = new 5();
    private static final Interpolator n = new AccelerateDecelerateInterpolator();
    private static final no o = new nk();
    private static final no p = new ni();
    private static long z = 10;
    private int A = 0;
    private int B = 1;
    private Interpolator C = n;
    private ArrayList<b> D = null;
    long b;
    long c = -1;
    int d = 0;
    boolean e = false;
    nn[] f;
    HashMap<String, nn> g;
    private boolean q = false;
    private int r = 0;
    private float s = CropImageView.DEFAULT_ASPECT_RATIO;
    private boolean t = false;
    private long u;
    private boolean v = false;
    private boolean w = false;
    private long x = 300;
    private long y = 0;

    /* compiled from: ValueAnimator */
    /* renamed from: np$1 */
    static class 1 extends ThreadLocal<ArrayList<np>> {
        1() {
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public ArrayList<np> initialValue() {
            return new ArrayList();
        }
    }

    /* compiled from: ValueAnimator */
    /* renamed from: np$2 */
    static class 2 extends ThreadLocal<ArrayList<np>> {
        2() {
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public ArrayList<np> initialValue() {
            return new ArrayList();
        }
    }

    /* compiled from: ValueAnimator */
    /* renamed from: np$3 */
    static class 3 extends ThreadLocal<ArrayList<np>> {
        3() {
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public ArrayList<np> initialValue() {
            return new ArrayList();
        }
    }

    /* compiled from: ValueAnimator */
    /* renamed from: np$4 */
    static class 4 extends ThreadLocal<ArrayList<np>> {
        4() {
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public ArrayList<np> initialValue() {
            return new ArrayList();
        }
    }

    /* compiled from: ValueAnimator */
    /* renamed from: np$5 */
    static class 5 extends ThreadLocal<ArrayList<np>> {
        5() {
        }

        /* Access modifiers changed, original: protected */
        /* renamed from: a */
        public ArrayList<np> initialValue() {
            return new ArrayList();
        }
    }

    /* compiled from: ValueAnimator */
    /* renamed from: np$a */
    private static class a extends Handler {
        private a() {
        }

        /* synthetic */ a(1 1) {
            this();
        }

        public void handleMessage(Message message) {
            ArrayList arrayList;
            Object obj;
            int i;
            np npVar;
            ArrayList arrayList2 = (ArrayList) np.i.get();
            ArrayList arrayList3 = (ArrayList) np.k.get();
            switch (message.what) {
                case 0:
                    arrayList = (ArrayList) np.j.get();
                    obj = (arrayList2.size() > 0 || arrayList3.size() > 0) ? null : 1;
                    while (arrayList.size() > 0) {
                        ArrayList arrayList4 = (ArrayList) arrayList.clone();
                        arrayList.clear();
                        int size = arrayList4.size();
                        for (int i2 = 0; i2 < size; i2++) {
                            np npVar2 = (np) arrayList4.get(i2);
                            if (npVar2.y == 0) {
                                npVar2.o();
                            } else {
                                arrayList3.add(npVar2);
                            }
                        }
                    }
                    break;
                case 1:
                    obj = 1;
                    break;
                default:
                    return;
            }
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            arrayList = (ArrayList) np.m.get();
            ArrayList arrayList5 = (ArrayList) np.l.get();
            int size2 = arrayList3.size();
            for (i = 0; i < size2; i++) {
                npVar = (np) arrayList3.get(i);
                if (npVar.e(currentAnimationTimeMillis)) {
                    arrayList.add(npVar);
                }
            }
            size2 = arrayList.size();
            if (size2 > 0) {
                for (i = 0; i < size2; i++) {
                    npVar = (np) arrayList.get(i);
                    npVar.o();
                    npVar.v = true;
                    arrayList3.remove(npVar);
                }
                arrayList.clear();
            }
            size2 = arrayList2.size();
            int i3 = 0;
            while (i3 < size2) {
                np npVar3 = (np) arrayList2.get(i3);
                if (npVar3.d(currentAnimationTimeMillis)) {
                    arrayList5.add(npVar3);
                }
                if (arrayList2.size() == size2) {
                    i3++;
                } else {
                    size2--;
                    arrayList5.remove(npVar3);
                }
            }
            if (arrayList5.size() > 0) {
                for (int i4 = 0; i4 < arrayList5.size(); i4++) {
                    ((np) arrayList5.get(i4)).n();
                }
                arrayList5.clear();
            }
            if (obj == null) {
                return;
            }
            if (!arrayList2.isEmpty() || !arrayList3.isEmpty()) {
                sendEmptyMessageDelayed(1, Math.max(0, np.z - (AnimationUtils.currentAnimationTimeMillis() - currentAnimationTimeMillis)));
            }
        }
    }

    /* compiled from: ValueAnimator */
    /* renamed from: np$b */
    public interface b {
        void a(np npVar);
    }

    public static np a(float... fArr) {
        np npVar = new np();
        npVar.b(fArr);
        return npVar;
    }

    public void b(float... fArr) {
        if (fArr != null && fArr.length != 0) {
            if (this.f == null || this.f.length == 0) {
                a(nn.a("", fArr));
            } else {
                this.f[0].a(fArr);
            }
            this.e = false;
        }
    }

    public void a(nn... nnVarArr) {
        this.f = nnVarArr;
        this.g = new HashMap(r0);
        for (nn nnVar : nnVarArr) {
            this.g.put(nnVar.c(), nnVar);
        }
        this.e = false;
    }

    /* Access modifiers changed, original: 0000 */
    public void d() {
        if (!this.e) {
            for (nn b : this.f) {
                b.b();
            }
            this.e = true;
        }
    }

    public np a(long j) {
        if (j >= 0) {
            this.x = j;
            return this;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Animators cannot have negative duration: ");
        stringBuilder.append(j);
        throw new IllegalArgumentException(stringBuilder.toString());
    }

    public void b(long j) {
        d();
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (this.d != 1) {
            this.c = j;
            this.d = 2;
        }
        this.b = currentAnimationTimeMillis - j;
        d(currentAnimationTimeMillis);
    }

    public long e() {
        return (!this.e || this.d == 0) ? 0 : AnimationUtils.currentAnimationTimeMillis() - this.b;
    }

    public void c(long j) {
        this.y = j;
    }

    public void a(b bVar) {
        if (this.D == null) {
            this.D = new ArrayList();
        }
        this.D.add(bVar);
    }

    public void a(Interpolator interpolator) {
        if (interpolator != null) {
            this.C = interpolator;
        } else {
            this.C = new LinearInterpolator();
        }
    }

    private void a(boolean z) {
        if (Looper.myLooper() != null) {
            this.q = z;
            this.r = 0;
            this.d = 0;
            this.w = true;
            this.t = false;
            ((ArrayList) j.get()).add(this);
            if (this.y == 0) {
                b(e());
                this.d = 0;
                this.v = true;
                if (this.a != null) {
                    ArrayList arrayList = (ArrayList) this.a.clone();
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        ((a) arrayList.get(i)).a(this);
                    }
                }
            }
            a aVar = (a) h.get();
            if (aVar == null) {
                aVar = new a();
                h.set(aVar);
            }
            aVar.sendEmptyMessage(0);
            return;
        }
        throw new AndroidRuntimeException("Animators may only be run on Looper threads");
    }

    public void a() {
        a(false);
    }

    public void b() {
        if (this.d != 0 || ((ArrayList) j.get()).contains(this) || ((ArrayList) k.get()).contains(this)) {
            if (this.v && this.a != null) {
                Iterator it = ((ArrayList) this.a.clone()).iterator();
                while (it.hasNext()) {
                    ((a) it.next()).c(this);
                }
            }
            n();
        }
    }

    private void n() {
        ((ArrayList) i.get()).remove(this);
        ((ArrayList) j.get()).remove(this);
        ((ArrayList) k.get()).remove(this);
        this.d = 0;
        if (this.v && this.a != null) {
            ArrayList arrayList = (ArrayList) this.a.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((a) arrayList.get(i)).b(this);
            }
        }
        this.v = false;
        this.w = false;
    }

    private void o() {
        d();
        ((ArrayList) i.get()).add(this);
        if (this.y > 0 && this.a != null) {
            ArrayList arrayList = (ArrayList) this.a.clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((a) arrayList.get(i)).a(this);
            }
        }
    }

    private boolean e(long j) {
        if (this.t) {
            long j2 = j - this.u;
            if (j2 > this.y) {
                this.b = j - (j2 - this.y);
                this.d = 1;
                return true;
            }
        }
        this.t = true;
        this.u = j;
        return false;
    }

    /* Access modifiers changed, original: 0000 */
    public boolean d(long j) {
        if (this.d == 0) {
            this.d = 1;
            if (this.c < 0) {
                this.b = j;
            } else {
                this.b = j - this.c;
                this.c = -1;
            }
        }
        boolean z = false;
        switch (this.d) {
            case 1:
            case 2:
                float f = this.x > 0 ? ((float) (j - this.b)) / ((float) this.x) : 1.0f;
                if (f >= 1.0f) {
                    if (this.r < this.A || this.A == -1) {
                        if (this.a != null) {
                            int size = this.a.size();
                            for (int i = 0; i < size; i++) {
                                ((a) this.a.get(i)).d(this);
                            }
                        }
                        if (this.B == 2) {
                            this.q ^= 1;
                        }
                        this.r += (int) f;
                        f %= 1.0f;
                        this.b += this.x;
                    } else {
                        f = Math.min(f, 1.0f);
                        z = true;
                    }
                }
                if (this.q) {
                    f = 1.0f - f;
                }
                a(f);
                break;
        }
        return z;
    }

    public float f() {
        return this.s;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(float f) {
        f = this.C.getInterpolation(f);
        this.s = f;
        for (nn a : this.f) {
            a.a(f);
        }
        if (this.D != null) {
            int size = this.D.size();
            for (int i = 0; i < size; i++) {
                ((b) this.D.get(i)).a(this);
            }
        }
    }

    /* renamed from: g */
    public np clone() {
        int size;
        np npVar = (np) super.clone();
        int i = 0;
        if (this.D != null) {
            ArrayList arrayList = this.D;
            npVar.D = new ArrayList();
            size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                npVar.D.add(arrayList.get(i2));
            }
        }
        npVar.c = -1;
        npVar.q = false;
        npVar.r = 0;
        npVar.e = false;
        npVar.d = 0;
        npVar.t = false;
        nn[] nnVarArr = this.f;
        if (nnVarArr != null) {
            size = nnVarArr.length;
            npVar.f = new nn[size];
            npVar.g = new HashMap(size);
            while (i < size) {
                nn a = nnVarArr[i].clone();
                npVar.f[i] = a;
                npVar.g.put(a.c(), a);
                i++;
            }
        }
        return npVar;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ValueAnimator@");
        stringBuilder.append(Integer.toHexString(hashCode()));
        String stringBuilder2 = stringBuilder.toString();
        if (this.f != null) {
            for (nn nnVar : this.f) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(stringBuilder2);
                stringBuilder3.append("\n    ");
                stringBuilder3.append(nnVar.toString());
                stringBuilder2 = stringBuilder3.toString();
            }
        }
        return stringBuilder2;
    }
}
