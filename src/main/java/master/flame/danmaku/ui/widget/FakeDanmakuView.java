package master.flame.danmaku.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import defpackage.ze;
import defpackage.zf;
import defpackage.zh;
import defpackage.zi;
import defpackage.zn;
import defpackage.zo;
import defpackage.zs;
import defpackage.zy;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.e;

public class FakeDanmakuView extends DanmakuView implements defpackage.yv.a {
    private zh e;
    private boolean f;
    private b g;
    private int h = 0;
    private int i = 0;
    private float j = 1.0f;
    private zh k;
    private long l;
    private long m = 16;
    private long n;
    private Bitmap o;
    private Canvas p;
    private int q = 0;
    private long r = 0;

    public interface b {
        void a(int i, String str);

        void a(long j);

        void a(long j, Bitmap bitmap);

        void a(DanmakuContext danmakuContext);
    }

    private class a extends zy {
        private final zy b;
        private final long c;
        private final long d;
        private float e;
        private float f;
        private int g;

        public a(zy zyVar, long j, long j2) {
            this.b = zyVar;
            this.c = j;
            this.d = j2;
        }

        /* Access modifiers changed, original: protected */
        public zn parse() {
            zn a;
            final e eVar = new e();
            try {
                a = this.b.getDanmakus().a(this.c, this.d);
            } catch (Exception unused) {
                a = this.b.getDanmakus();
            }
            if (a == null) {
                return eVar;
            }
            a.b(new defpackage.zn.b<zf, Object>() {
                public int a(zf zfVar) {
                    zf zfVar2 = zfVar;
                    long r = zfVar.r();
                    if (r < a.this.c) {
                        return 0;
                    }
                    if (r > a.this.d) {
                        return 1;
                    }
                    zf a = a.this.mContext.t.a(zfVar.o(), a.this.mContext);
                    if (a != null) {
                        a.d(zfVar.r());
                        aae.a(a, zfVar2.b);
                        a.j = zfVar2.j;
                        a.e = zfVar2.e;
                        a.h = zfVar2.h;
                        if (zfVar2 instanceof zs) {
                            zs zsVar = (zs) zfVar2;
                            a.q = zfVar2.q;
                            a.p = new zi(zsVar.a());
                            a.f = zsVar.V;
                            a.g = zsVar.g;
                            ((zs) a).Q = zsVar.Q;
                            zf zfVar3 = a;
                            a.this.mContext.t.a(zfVar3, zsVar.I, zsVar.J, zsVar.K, zsVar.L, zsVar.O, zsVar.P, a.this.e, a.this.f);
                            a.this.mContext.t.a(zfVar3, zsVar.R, zsVar.S, a.a());
                            return 0;
                        }
                        a.a(a.this.mTimer);
                        a.D = zfVar2.D;
                        a.E = zfVar2.E;
                        a.F = a.this.mContext.r;
                        synchronized (eVar.f()) {
                            eVar.a(a);
                        }
                    }
                    return 0;
                }
            });
            return eVar;
        }

        public zy setDisplayer(zo zoVar) {
            super.setDisplayer(zoVar);
            if (this.b == null || this.b.getDisplayer() == null) {
                return this;
            }
            this.e = ((float) this.mDispWidth) / ((float) this.b.getDisplayer().e());
            this.f = ((float) this.mDispHeight) / ((float) this.b.getDisplayer().f());
            if (this.g <= 1) {
                this.g = zoVar.e();
            }
            return this;
        }

        /* Access modifiers changed, original: protected */
        public float getViewportSizeFactor() {
            return (((float) this.mContext.t.d) * 1.1f) / (((float) (((long) this.g) * 3800)) / 682.0f);
        }
    }

    public boolean a() {
        return true;
    }

    public void danmakuShown(zf zfVar) {
    }

    public void drawingFinished() {
    }

    public boolean isShown() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public void onDraw(Canvas canvas) {
    }

    public void prepared() {
    }

    public FakeDanmakuView(Context context) {
        super(context);
    }

    /* JADX WARNING: Missing block: B:32:0x0073, code skipped:
            if (r10.e != null) goto L_0x0075;
     */
    /* JADX WARNING: Missing block: B:33:0x0075, code skipped:
            r10.e.a(r10.n);
     */
    /* JADX WARNING: Missing block: B:34:0x007c, code skipped:
            r0.a(r4);
     */
    /* JADX WARNING: Missing block: B:42:0x009a, code skipped:
            if (r10.e != null) goto L_0x0075;
     */
    public long b() {
        /*
        r10 = this;
        r0 = r10.f;
        r1 = 0;
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return r1;
    L_0x0007:
        r0 = r10.p;
        if (r0 != 0) goto L_0x000c;
    L_0x000b:
        return r1;
    L_0x000c:
        r3 = r10.o;
        if (r3 == 0) goto L_0x00ba;
    L_0x0010:
        r4 = r3.isRecycled();
        if (r4 == 0) goto L_0x0018;
    L_0x0016:
        goto L_0x00ba;
    L_0x0018:
        r1 = 0;
        r3.eraseColor(r1);
        r2 = r10.d;
        if (r2 == 0) goto L_0x0026;
    L_0x0020:
        defpackage.yw.a(r0);
        r10.d = r1;
        goto L_0x002f;
    L_0x0026:
        r2 = r10.a;
        if (r2 == 0) goto L_0x002f;
    L_0x002a:
        r2 = r10.a;
        r2.a(r0);
    L_0x002f:
        r0 = r10.g;
        if (r0 == 0) goto L_0x00b5;
    L_0x0033:
        r2 = r10.k;
        r4 = r2.a;
        r6 = r10.r;	 Catch:{ Exception -> 0x0082 }
        r8 = r10.m;	 Catch:{ Exception -> 0x0082 }
        r2 = 0;
        r6 = r6 - r8;
        r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r2 < 0) goto L_0x0068;
    L_0x0041:
        r2 = r10.j;	 Catch:{ Exception -> 0x0082 }
        r6 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        r6 = 1;
        if (r2 != 0) goto L_0x004c;
    L_0x004a:
        r6 = 0;
        goto L_0x0060;
    L_0x004c:
        r2 = r10.h;	 Catch:{ Exception -> 0x0082 }
        r2 = (float) r2;	 Catch:{ Exception -> 0x0082 }
        r7 = r10.j;	 Catch:{ Exception -> 0x0082 }
        r2 = r2 * r7;
        r2 = (int) r2;	 Catch:{ Exception -> 0x0082 }
        r7 = r10.i;	 Catch:{ Exception -> 0x0082 }
        r7 = (float) r7;	 Catch:{ Exception -> 0x0082 }
        r8 = r10.j;	 Catch:{ Exception -> 0x0082 }
        r7 = r7 * r8;
        r7 = (int) r7;	 Catch:{ Exception -> 0x0082 }
        r3 = android.graphics.Bitmap.createScaledBitmap(r3, r2, r7, r6);	 Catch:{ Exception -> 0x0082 }
    L_0x0060:
        r0.a(r4, r3);	 Catch:{ Exception -> 0x0082 }
        if (r6 == 0) goto L_0x0068;
    L_0x0065:
        r3.recycle();	 Catch:{ Exception -> 0x0082 }
    L_0x0068:
        r2 = r10.n;
        r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r6 < 0) goto L_0x00b5;
    L_0x006e:
        r10.e();
        r2 = r10.e;
        if (r2 == 0) goto L_0x007c;
    L_0x0075:
        r2 = r10.e;
        r6 = r10.n;
        r2.a(r6);
    L_0x007c:
        r0.a(r4);
        goto L_0x00b5;
    L_0x0080:
        r1 = move-exception;
        goto L_0x009d;
    L_0x0082:
        r2 = move-exception;
        r10.e();	 Catch:{ all -> 0x0080 }
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r2 = r2.getMessage();	 Catch:{ all -> 0x0080 }
        r0.a(r3, r2);	 Catch:{ all -> 0x0080 }
        r2 = r10.n;
        r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r6 < 0) goto L_0x00b5;
    L_0x0095:
        r10.e();
        r2 = r10.e;
        if (r2 == 0) goto L_0x007c;
    L_0x009c:
        goto L_0x0075;
    L_0x009d:
        r2 = r10.n;
        r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r6 < 0) goto L_0x00b4;
    L_0x00a3:
        r10.e();
        r2 = r10.e;
        if (r2 == 0) goto L_0x00b1;
    L_0x00aa:
        r2 = r10.e;
        r6 = r10.n;
        r2.a(r6);
    L_0x00b1:
        r0.a(r4);
    L_0x00b4:
        throw r1;
    L_0x00b5:
        r10.c = r1;
        r0 = 2;
        return r0;
    L_0x00ba:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: master.flame.danmaku.ui.widget.FakeDanmakuView.b():long");
    }

    public void e() {
        this.f = true;
        super.e();
        this.o = null;
    }

    public int getViewWidth() {
        return this.h;
    }

    public int getViewHeight() {
        return this.i;
    }

    public void a(zy zyVar, DanmakuContext danmakuContext) {
        DanmakuContext danmakuContext2;
        a aVar = new a(zyVar, this.l, this.n);
        try {
            danmakuContext2 = (DanmakuContext) danmakuContext.clone();
            danmakuContext2.h();
            danmakuContext2.b = ze.a;
            danmakuContext2.a(((float) danmakuContext.b) / ((float) ze.a));
            danmakuContext2.r.c = danmakuContext.r.c;
            danmakuContext2.a(null);
            danmakuContext2.g();
            danmakuContext2.r.b();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            danmakuContext2 = danmakuContext;
        }
        danmakuContext2.v = (byte) 1;
        if (this.g != null) {
            this.g.a(danmakuContext2);
        }
        super.a(aVar, danmakuContext2);
        this.a.a(false);
        this.a.b(true);
    }

    public void setTimeRange(long j, long j2) {
        this.r = j;
        this.l = Math.max(0, j - 30000);
        this.n = j2;
    }

    public void setOnFrameAvailableListener(b bVar) {
        this.g = bVar;
    }

    public void updateTimer(zh zhVar) {
        this.e = zhVar;
        zhVar.a(this.k.a);
        this.k.b(this.m);
        zhVar.b(this.m);
    }
}
