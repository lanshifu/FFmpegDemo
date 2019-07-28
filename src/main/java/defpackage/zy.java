package defpackage;

import master.flame.danmaku.danmaku.model.android.DanmakuContext;

/* compiled from: BaseDanmakuParser */
/* renamed from: zy */
public abstract class zy {
    protected DanmakuContext mContext;
    private zn mDanmakus;
    protected zz<?> mDataSource;
    protected zo mDisp;
    protected float mDispDensity;
    protected int mDispHeight;
    protected int mDispWidth;
    protected a mListener;
    protected float mScaledDensity;
    protected zh mTimer;

    /* compiled from: BaseDanmakuParser */
    /* renamed from: zy$a */
    public interface a {
    }

    public abstract zn parse();

    public zy setDisplayer(zo zoVar) {
        this.mDisp = zoVar;
        this.mDispWidth = zoVar.e();
        this.mDispHeight = zoVar.f();
        this.mDispDensity = zoVar.g();
        this.mScaledDensity = zoVar.i();
        this.mContext.t.a((float) this.mDispWidth, (float) this.mDispHeight, getViewportSizeFactor());
        this.mContext.t.c();
        return this;
    }

    public zo getDisplayer() {
        return this.mDisp;
    }

    public zy setListener(a aVar) {
        this.mListener = aVar;
        return this;
    }

    /* Access modifiers changed, original: protected */
    public float getViewportSizeFactor() {
        return 1.0f / (this.mDispDensity - 0.6f);
    }

    public zy load(zz<?> zzVar) {
        this.mDataSource = zzVar;
        return this;
    }

    public zy setTimer(zh zhVar) {
        this.mTimer = zhVar;
        return this;
    }

    public zh getTimer() {
        return this.mTimer;
    }

    public zn getDanmakus() {
        if (this.mDanmakus != null) {
            return this.mDanmakus;
        }
        this.mContext.t.b();
        this.mDanmakus = parse();
        releaseDataSource();
        this.mContext.t.c();
        return this.mDanmakus;
    }

    /* Access modifiers changed, original: protected */
    public void releaseDataSource() {
        if (this.mDataSource != null) {
            this.mDataSource.a();
        }
        this.mDataSource = null;
    }

    public void release() {
        releaseDataSource();
    }

    public zy setConfig(DanmakuContext danmakuContext) {
        this.mContext = danmakuContext;
        return this;
    }
}
