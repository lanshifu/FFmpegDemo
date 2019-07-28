package master.flame.danmaku.danmaku.model.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import defpackage.zf;
import defpackage.zp;

/* compiled from: BaseCacheStuffer */
public abstract class b {
    protected a mProxy;

    /* compiled from: BaseCacheStuffer */
    public static abstract class a {
        public abstract void a(zf zfVar);

        public abstract void a(zf zfVar, boolean z);
    }

    public void clearCache(zf zfVar) {
    }

    public abstract void clearCaches();

    public abstract void drawDanmaku(zf zfVar, Canvas canvas, float f, float f2, boolean z, master.flame.danmaku.danmaku.model.android.a.a aVar);

    public abstract void measure(zf zfVar, TextPaint textPaint, boolean z);

    public void prepare(zf zfVar, boolean z) {
        if (this.mProxy != null) {
            this.mProxy.a(zfVar, z);
        }
    }

    public boolean drawCache(zf zfVar, Canvas canvas, float f, float f2, Paint paint, TextPaint textPaint) {
        zp d = zfVar.d();
        if (d != null) {
            g gVar = (g) d.a();
            if (gVar != null) {
                return gVar.a(canvas, f, f2, paint);
            }
        }
        return false;
    }

    public void setProxy(a aVar) {
        this.mProxy = aVar;
    }

    public void releaseResource(zf zfVar) {
        if (this.mProxy != null) {
            this.mProxy.a(zfVar);
        }
    }
}
