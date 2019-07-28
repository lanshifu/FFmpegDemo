package com.tomatolive.library.faceunity;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.k;
import com.faceunity.beautycontrolview.FilterEnum;
import com.faceunity.wrapper.faceunity;
import com.tomatolive.library.http.utils.EncryptUtil;
import com.tomatolive.library.utils.u;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.fu;
import defpackage.fv;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: FURenderer */
public class a implements com.faceunity.beautycontrolview.a {
    private static boolean Z = false;
    private static final String a = "a";
    private fu A;
    private int B;
    private boolean C;
    private int D;
    private int E;
    private boolean F;
    private int G;
    private int H;
    private float[] I;
    private float[] J;
    private float[] K;
    private float[] L;
    private float[] M;
    private ArrayList<Runnable> N;
    private int O;
    private f P;
    private e Q;
    private float R;
    private c S;
    private int T;
    private long U;
    private long V;
    private boolean W;
    private long X;
    private d Y;
    private Context b;
    private boolean c;
    private float d;
    private fv e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private float q;
    private float r;
    private float s;
    private float t;
    private int u;
    private final int[] v;
    private HandlerThread w;
    private Handler x;
    private boolean y;
    private boolean z;

    /* compiled from: FURenderer */
    /* renamed from: com.tomatolive.library.faceunity.a$1 */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ a b;

        public void run() {
            this.b.B = this.a;
            faceunity.fuSetMaxFaces(this.a);
        }
    }

    /* compiled from: FURenderer */
    public static class a {
        private boolean a = false;
        private fu b;
        private int c = 4;
        private Context d;
        private int e = 0;
        private boolean f = false;
        private int g = 0;
        private int h = 90;
        private boolean i = false;
        private boolean j = true;
        private d k;
        private f l;
        private c m;
        private e n;

        public a(@NonNull Context context) {
            this.d = context;
        }

        public a a(boolean z) {
            this.a = z;
            return this;
        }

        public a a(int i) {
            this.e = i;
            return this;
        }

        public a b(boolean z) {
            this.f = z;
            return this;
        }

        public a c(boolean z) {
            this.j = z;
            return this;
        }

        public a a() {
            a aVar = new a(this.d, this.a, null);
            aVar.B = this.c;
            aVar.D = this.e;
            aVar.F = this.f;
            aVar.E = this.g;
            aVar.G = this.h;
            aVar.A = this.b;
            aVar.z = this.i;
            aVar.y = this.j;
            aVar.Y = this.k;
            aVar.P = this.l;
            aVar.S = this.m;
            aVar.Q = this.n;
            return aVar;
        }
    }

    /* compiled from: FURenderer */
    class b extends Handler {
        b(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 2:
                    try {
                        InputStream open = a.this.b.getAssets().open("face_beautification.bundle");
                        byte[] bArr = new byte[open.available()];
                        open.read(bArr);
                        open.close();
                        a.this.v[0] = faceunity.fuCreateItemFromPackage(bArr);
                        a.this.c = true;
                        String d = a.a;
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("face beauty item handle ");
                        stringBuilder.append(a.this.v[0]);
                        Log.e(d, stringBuilder.toString());
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    /* compiled from: FURenderer */
    public interface c {
        void a(float f);
    }

    /* compiled from: FURenderer */
    public interface d {
        void a(double d, double d2);
    }

    /* compiled from: FURenderer */
    public interface e {
        void a(String str);
    }

    /* compiled from: FURenderer */
    public interface f {
        void a(int i);
    }

    /* synthetic */ a(Context context, boolean z, AnonymousClass1 anonymousClass1) {
        this(context, z);
    }

    private void e() {
        String b = k.a().b("mFilterName", "ziran");
        this.d = k.a().b(b, 1.0f);
        if (TextUtils.equals(b, "origin")) {
            b = "ziran";
        }
        this.e = FilterEnum.valueOf(b).filter();
        this.f = k.a().b("mFaceBeautyALLBlurLevel", 1.0f);
        this.g = k.a().b("mFaceBeautyType", CropImageView.DEFAULT_ASPECT_RATIO);
        this.h = k.a().b("mFaceBeautyBlurLevel", 0.7f);
        this.i = k.a().b("mFaceBeautyColorLevel", 0.5f);
        this.j = k.a().b("mFaceBeautyRedLevel", 0.5f);
        this.k = k.a().b("mBrightEyesLevel", CropImageView.DEFAULT_ASPECT_RATIO);
        this.l = k.a().b("mBeautyTeethLevel", CropImageView.DEFAULT_ASPECT_RATIO);
        this.m = k.a().b("mFaceBeautyFaceShape", 4.0f);
        this.o = k.a().b("mFaceBeautyEnlargeEye", 0.4f);
        this.p = k.a().b("mFaceBeautyCheekThin", 0.4f);
        this.q = k.a().b("mChinLevel", 0.3f);
        this.r = k.a().b("mForeheadLevel", 0.3f);
        this.s = k.a().b("mThinNoseLevel", 0.5f);
        this.t = k.a().b("mMouthShape", 0.4f);
    }

    public static void a(Context context) {
        try {
            String DESDecrypt = EncryptUtil.DESDecrypt("246887c3-ee20-4fe8-a320-1fde4a8d10b6", u.d(k.a().b("authEncryptStr")));
            InputStream open = context.getAssets().open("v3.bundle");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            faceunity.fuSetup(bArr, null, u.b(DESDecrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private a(Context context, boolean z) {
        this.c = true;
        this.d = 1.0f;
        this.e = FilterEnum.ziran.filter();
        this.f = 1.0f;
        this.g = CropImageView.DEFAULT_ASPECT_RATIO;
        this.h = 0.7f;
        this.i = 0.5f;
        this.j = 0.5f;
        this.k = CropImageView.DEFAULT_ASPECT_RATIO;
        this.l = CropImageView.DEFAULT_ASPECT_RATIO;
        this.m = 4.0f;
        this.n = 1.0f;
        this.o = 0.4f;
        this.p = 0.4f;
        this.q = 0.3f;
        this.r = 0.3f;
        this.s = 0.5f;
        this.t = 0.4f;
        this.u = 0;
        this.v = new int[3];
        this.y = true;
        this.z = false;
        this.B = 4;
        this.D = 0;
        this.E = 0;
        this.F = false;
        this.G = 0;
        this.H = 1;
        this.I = new float[150];
        this.J = new float[46];
        this.K = new float[4];
        this.L = new float[2];
        this.M = new float[1];
        this.N = new ArrayList();
        this.O = 0;
        this.R = CropImageView.DEFAULT_ASPECT_RATIO;
        this.T = 0;
        this.U = 0;
        this.V = 0;
        this.W = true;
        this.X = 0;
        this.b = context;
        this.C = z;
        this.w = new HandlerThread("FUItemHandlerThread");
        this.w.start();
        this.x = new b(this.w.getLooper());
        e();
    }

    public void a() {
        Log.e(a, "onSurfaceCreated");
        if (this.C) {
            faceunity.fuCreateEGLContext();
        }
        this.u = 0;
        faceunity.fuSetExpressionCalibration(2);
        faceunity.fuSetMaxFaces(this.B);
        if (this.y) {
            this.x.sendEmptyMessage(2);
        }
    }

    public int a(int i, int i2, int i3) {
        if (i <= 0 || i2 <= 0 || i3 <= 0) {
            Log.e(a, "onDrawFrame date null");
            return 0;
        }
        f();
        int i4 = this.D;
        if (this.W) {
            this.X = System.nanoTime();
        }
        int i5 = this.u;
        this.u = i5 + 1;
        i = faceunity.fuBeautifyImage(i, i4, i2, i3, i5, this.v);
        if (this.W) {
            this.V += System.nanoTime() - this.X;
        }
        return i;
    }

    public void b() {
        this.x.removeMessages(1);
        this.u = 0;
        this.c = true;
        Arrays.fill(this.v, 0);
        faceunity.fuDestroyAllItems();
        faceunity.fuOnDeviceLost();
        this.N.clear();
        if (this.C) {
            faceunity.fuReleaseEGLContext();
        }
    }

    private void f() {
        g();
        int fuIsTracking = faceunity.fuIsTracking();
        if (!(this.P == null || this.O == fuIsTracking)) {
            f fVar = this.P;
            this.O = fuIsTracking;
            fVar.a(fuIsTracking);
        }
        fuIsTracking = faceunity.fuGetSystemError();
        if (!(this.Q == null || fuIsTracking == 0)) {
            this.Q.a(faceunity.fuGetSystemErrorString(fuIsTracking));
        }
        float[] fArr = new float[1];
        faceunity.fuGetFaceInfo(0, "is_calibrating", fArr);
        if (!(this.S == null || fArr[0] == this.R)) {
            c cVar = this.S;
            float f = fArr[0];
            this.R = f;
            cVar.a(f);
        }
        if (this.c && this.v[0] != 0) {
            faceunity.fuItemSetParam(this.v[0], "filter_level", (double) this.d);
            faceunity.fuItemSetParam(this.v[0], "filter_name", this.e.a());
            faceunity.fuItemSetParam(this.v[0], "skin_detect", (double) this.f);
            faceunity.fuItemSetParam(this.v[0], "heavy_blur", (double) this.g);
            faceunity.fuItemSetParam(this.v[0], "blur_level", (double) (this.h * 6.0f));
            faceunity.fuItemSetParam(this.v[0], "color_level", (double) this.i);
            faceunity.fuItemSetParam(this.v[0], "red_level", (double) this.j);
            faceunity.fuItemSetParam(this.v[0], "eye_bright", (double) this.k);
            faceunity.fuItemSetParam(this.v[0], "tooth_whiten", (double) this.l);
            faceunity.fuItemSetParam(this.v[0], "face_shape_level", (double) this.n);
            faceunity.fuItemSetParam(this.v[0], "face_shape", (double) this.m);
            faceunity.fuItemSetParam(this.v[0], "eye_enlarging", (double) this.o);
            faceunity.fuItemSetParam(this.v[0], "cheek_thinning", (double) this.p);
            faceunity.fuItemSetParam(this.v[0], "intensity_chin", (double) this.q);
            faceunity.fuItemSetParam(this.v[0], "intensity_forehead", (double) this.r);
            faceunity.fuItemSetParam(this.v[0], "intensity_nose", (double) this.s);
            faceunity.fuItemSetParam(this.v[0], "intensity_mouth", (double) this.t);
            this.c = false;
        }
        while (!this.N.isEmpty()) {
            ((Runnable) this.N.remove(0)).run();
        }
    }

    public void a(Runnable runnable) {
        this.N.add(runnable);
    }

    public void a(final int i, final int i2) {
        if (this.H != i || this.G != i2) {
            a(new Runnable() {
                public void run() {
                    a.this.H = i;
                    a.this.G = i2;
                    faceunity.fuClearReadbackRelated();
                    faceunity.fuOnCameraChange();
                    a.this.u = 0;
                }
            });
        }
    }

    public void a(float f) {
        this.c = true;
        this.d = f;
    }

    public void a(fv fvVar) {
        this.c = true;
        this.e = fvVar;
        k.a().a("mFilterName", this.e.a());
    }

    public void b(float f) {
        this.c = true;
        this.f = f;
        k.a().a("mFaceBeautyALLBlurLevel", this.f);
    }

    public void c(float f) {
        this.c = true;
        this.g = f;
        k.a().a("mFaceBeautyType", this.g);
    }

    public void d(float f) {
        this.c = true;
        this.h = f;
        k.a().a("mFaceBeautyBlurLevel", this.h);
    }

    public void e(float f) {
        this.c = true;
        this.i = f;
        k.a().a("mFaceBeautyColorLevel", this.i);
    }

    public void f(float f) {
        this.c = true;
        this.j = f;
        k.a().a("mFaceBeautyRedLevel", this.j);
    }

    public void g(float f) {
        this.c = true;
        this.k = f;
        k.a().a("mBrightEyesLevel", this.k);
    }

    public void h(float f) {
        this.c = true;
        this.l = f;
        k.a().a("mBeautyTeethLevel", this.l);
    }

    public void i(float f) {
        this.c = true;
        this.m = f;
        k.a().a("mFaceBeautyFaceShape", this.m);
    }

    public void j(float f) {
        this.c = true;
        this.o = f;
        k.a().a("mFaceBeautyEnlargeEye", this.o);
    }

    public void k(float f) {
        this.c = true;
        this.p = f;
        k.a().a("mFaceBeautyCheekThin", this.p);
    }

    public void l(float f) {
        this.c = true;
        this.q = f;
        k.a().a("mChinLevel", this.q);
    }

    public void m(float f) {
        this.c = true;
        this.r = f;
        k.a().a("mForeheadLevel", this.r);
    }

    public void n(float f) {
        this.c = true;
        this.s = f;
        k.a().a("mThinNoseLevel", this.s);
    }

    public void o(float f) {
        this.c = true;
        this.t = f;
        k.a().a("mMouthShape", this.t);
    }

    private void g() {
        if (this.W) {
            int i = this.T + 1;
            this.T = i;
            if (((float) i) == 5.0f) {
                this.T = 0;
                long nanoTime = System.nanoTime();
                double d = (double) (1.0E9f / (((float) (nanoTime - this.U)) / 5.0f));
                this.U = nanoTime;
                double d2 = (double) ((((float) this.V) / 5.0f) / 1000000.0f);
                this.V = 0;
                if (this.Y != null) {
                    this.Y.a(d, d2);
                }
            }
        }
    }

    public void c() {
        if (!Z) {
            Z = true;
            a(this.b);
        }
        a();
    }
}
