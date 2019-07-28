package com.tencent.liteav.renderer;

import android.graphics.SurfaceTexture;

/* compiled from: TXCTextureViewRender */
public class d extends f {
    /* Access modifiers changed, original: protected */
    public void a(SurfaceTexture surfaceTexture) {
        try {
            if (this.l != null) {
                this.l.a(surfaceTexture);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(SurfaceTexture surfaceTexture) {
        try {
            if (this.l != null) {
                this.l.b(surfaceTexture);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SurfaceTexture a() {
        return (this.c == null || !this.c.isAvailable()) ? null : this.c.getSurfaceTexture();
    }
}
