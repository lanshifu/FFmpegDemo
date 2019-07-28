package com.tencent.liteav.beauty.b;

import android.opengl.GLES20;
import com.tencent.liteav.basic.e.g;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: TXCGPUTwoPassTextureSamplingFilter */
public class ae extends ad {
    protected float u = 4.0f;

    public ae(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }

    public boolean a() {
        return super.a() && GLES20.glGetError() == 0;
    }

    /* Access modifiers changed, original: protected */
    public void v() {
        float u = u();
        g gVar = (g) this.r.get(0);
        int glGetUniformLocation = GLES20.glGetUniformLocation(gVar.q(), "texelWidthOffset");
        int glGetUniformLocation2 = GLES20.glGetUniformLocation(gVar.q(), "texelHeightOffset");
        gVar.a(glGetUniformLocation, u / ((float) this.e));
        gVar.a(glGetUniformLocation2, (float) CropImageView.DEFAULT_ASPECT_RATIO);
        float t = t();
        g gVar2 = (g) this.r.get(1);
        glGetUniformLocation2 = GLES20.glGetUniformLocation(gVar2.q(), "texelWidthOffset");
        int glGetUniformLocation3 = GLES20.glGetUniformLocation(gVar2.q(), "texelHeightOffset");
        gVar2.a(glGetUniformLocation2, (float) CropImageView.DEFAULT_ASPECT_RATIO);
        gVar2.a(glGetUniformLocation3, t / ((float) this.f));
    }

    public void a(int i, int i2) {
        super.a(i, i2);
        v();
    }

    public float t() {
        return this.u;
    }

    public float u() {
        return this.u;
    }
}
