package com.tencent.liteav.beauty.b;

import android.opengl.GLES20;
import com.tencent.liteav.basic.e.g;
import com.yalantis.ucrop.view.CropImageView;

/* compiled from: TXCGPUPurlColorFilter */
public class u extends g {
    private int r = -1;

    public boolean a() {
        boolean a = super.a();
        this.r = GLES20.glGetUniformLocation(q(), "purlColor");
        c(this.r, new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f});
        return a;
    }

    public void b(float[] fArr) {
        c(this.r, fArr);
    }
}
