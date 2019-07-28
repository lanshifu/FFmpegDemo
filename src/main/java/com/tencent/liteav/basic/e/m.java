package com.tencent.liteav.basic.e;

import android.graphics.SurfaceTexture;

/* compiled from: TXIGLSurfaceTextureListener */
public interface m {
    void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture);

    void onSurfaceTextureDestroy(SurfaceTexture surfaceTexture);

    int onTextureProcess(int i, float[] fArr);
}
