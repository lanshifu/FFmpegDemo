package master.flame.danmaku.ui.widget;

import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import defpackage.yy;
import defpackage.zf;
import defpackage.zn;
import defpackage.zn.c;
import master.flame.danmaku.danmaku.model.android.e;

/* compiled from: DanmakuTouchHelper */
public class a {
    private final GestureDetector a;
    private yy b;
    private RectF c;
    private float d;
    private float e;
    private final OnGestureListener f = new SimpleOnGestureListener() {
        public boolean onDown(MotionEvent motionEvent) {
            if (a.this.b == null || a.this.b.getOnDanmakuClickListener() == null) {
                return false;
            }
            a.this.d = a.this.b.getXOff();
            a.this.e = a.this.b.getYOff();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            zn a = a.this.a(motionEvent.getX(), motionEvent.getY());
            boolean z = false;
            if (!(a == null || a.e())) {
                z = a.this.a(a, false);
            }
            return !z ? a.this.a() : z;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (a.this.b.getOnDanmakuClickListener() != null) {
                a.this.d = a.this.b.getXOff();
                a.this.e = a.this.b.getYOff();
                zn a = a.this.a(motionEvent.getX(), motionEvent.getY());
                if (!(a == null || a.e())) {
                    a.this.a(a, true);
                }
            }
        }
    };

    private a(yy yyVar) {
        this.b = yyVar;
        this.c = new RectF();
        this.a = new GestureDetector(((View) yyVar).getContext(), this.f);
    }

    public static synchronized a a(yy yyVar) {
        a aVar;
        synchronized (a.class) {
            aVar = new a(yyVar);
        }
        return aVar;
    }

    public boolean a(MotionEvent motionEvent) {
        return this.a.onTouchEvent(motionEvent);
    }

    private boolean a(zn znVar, boolean z) {
        defpackage.yy.a onDanmakuClickListener = this.b.getOnDanmakuClickListener();
        if (onDanmakuClickListener == null) {
            return false;
        }
        if (z) {
            return onDanmakuClickListener.b(znVar);
        }
        return onDanmakuClickListener.a(znVar);
    }

    private boolean a() {
        defpackage.yy.a onDanmakuClickListener = this.b.getOnDanmakuClickListener();
        return onDanmakuClickListener != null ? onDanmakuClickListener.a(this.b) : false;
    }

    private zn a(final float f, final float f2) {
        final e eVar = new e();
        this.c.setEmpty();
        zn currentVisibleDanmakus = this.b.getCurrentVisibleDanmakus();
        if (!(currentVisibleDanmakus == null || currentVisibleDanmakus.e())) {
            currentVisibleDanmakus.a(new c<zf>() {
                public int a(zf zfVar) {
                    if (zfVar != null) {
                        a.this.c.set(zfVar.k(), zfVar.l(), zfVar.m(), zfVar.n());
                        if (a.this.c.intersect(f - a.this.d, f2 - a.this.e, f + a.this.d, f2 + a.this.e)) {
                            eVar.a(zfVar);
                        }
                    }
                    return 0;
                }
            });
        }
        return eVar;
    }
}
