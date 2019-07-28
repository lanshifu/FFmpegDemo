package com.tencent.liteav.txcvodplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.tencent.ijk.media.player.IMediaPlayer;
import com.tencent.ijk.media.player.ISurfaceTextureHolder;
import com.tencent.liteav.basic.log.TXCLog;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SurfaceRenderView extends SurfaceView implements a {
    private b a;
    private b b;

    private static final class b implements Callback {
        private SurfaceHolder a;
        private boolean b;
        private int c;
        private int d;
        private int e;
        private WeakReference<SurfaceRenderView> f;
        private Map<com.tencent.liteav.txcvodplayer.a.a, Object> g = new ConcurrentHashMap();

        public b(@NonNull SurfaceRenderView surfaceRenderView) {
            this.f = new WeakReference(surfaceRenderView);
        }

        public void a(@NonNull com.tencent.liteav.txcvodplayer.a.a aVar) {
            com.tencent.liteav.txcvodplayer.a.b aVar2;
            this.g.put(aVar, aVar);
            if (this.a != null) {
                aVar2 = new a((SurfaceRenderView) this.f.get(), this.a);
                aVar.a(aVar2, this.d, this.e);
            } else {
                aVar2 = null;
            }
            if (this.b) {
                if (aVar2 == null) {
                    aVar2 = new a((SurfaceRenderView) this.f.get(), this.a);
                }
                aVar.a(aVar2, this.c, this.d, this.e);
            }
        }

        public void b(@NonNull com.tencent.liteav.txcvodplayer.a.a aVar) {
            this.g.remove(aVar);
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.a = surfaceHolder;
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            a aVar = new a((SurfaceRenderView) this.f.get(), this.a);
            for (com.tencent.liteav.txcvodplayer.a.a a : this.g.keySet()) {
                a.a(aVar, 0, 0);
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.a = null;
            this.b = false;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            a aVar = new a((SurfaceRenderView) this.f.get(), this.a);
            for (com.tencent.liteav.txcvodplayer.a.a a : this.g.keySet()) {
                a.a(aVar);
            }
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            this.a = surfaceHolder;
            this.b = true;
            this.c = i;
            this.d = i2;
            this.e = i3;
            a aVar = new a((SurfaceRenderView) this.f.get(), this.a);
            for (com.tencent.liteav.txcvodplayer.a.a a : this.g.keySet()) {
                a.a(aVar, i, i2, i3);
            }
        }
    }

    private static final class a implements com.tencent.liteav.txcvodplayer.a.b {
        private SurfaceRenderView a;
        private SurfaceHolder b;

        public a(@NonNull SurfaceRenderView surfaceRenderView, @Nullable SurfaceHolder surfaceHolder) {
            this.a = surfaceRenderView;
            this.b = surfaceHolder;
        }

        public void a(IMediaPlayer iMediaPlayer) {
            if (iMediaPlayer != null) {
                if (VERSION.SDK_INT >= 16 && (iMediaPlayer instanceof ISurfaceTextureHolder)) {
                    ((ISurfaceTextureHolder) iMediaPlayer).setSurfaceTexture(null);
                }
                iMediaPlayer.setDisplay(this.b);
            }
        }

        @NonNull
        public a a() {
            return this.a;
        }
    }

    public View getView() {
        return this;
    }

    public boolean shouldWaitForResize() {
        return true;
    }

    public SurfaceRenderView(Context context) {
        super(context);
        a(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SurfaceRenderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.a = new b(this);
        this.b = new b(this);
        getHolder().addCallback(this.b);
        getHolder().setType(0);
    }

    public void setVideoSize(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.a.a(i, i2);
            getHolder().setFixedSize(i, i2);
            requestLayout();
        }
    }

    public void setVideoSampleAspectRatio(int i, int i2) {
        if (i > 0 && i2 > 0) {
            this.a.b(i, i2);
            requestLayout();
        }
    }

    public void setVideoRotation(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SurfaceView doesn't support rotation (");
        stringBuilder.append(i);
        stringBuilder.append(")!\n");
        TXCLog.e("", stringBuilder.toString());
    }

    public void setAspectRatio(int i) {
        this.a.b(i);
        requestLayout();
    }

    /* Access modifiers changed, original: protected */
    public void onMeasure(int i, int i2) {
        this.a.c(i, i2);
        setMeasuredDimension(this.a.a(), this.a.b());
    }

    public void addRenderCallback(com.tencent.liteav.txcvodplayer.a.a aVar) {
        this.b.a(aVar);
    }

    public void removeRenderCallback(com.tencent.liteav.txcvodplayer.a.a aVar) {
        this.b.b(aVar);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(SurfaceRenderView.class.getName());
    }

    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (VERSION.SDK_INT >= 14) {
            accessibilityNodeInfo.setClassName(SurfaceRenderView.class.getName());
        }
    }
}
