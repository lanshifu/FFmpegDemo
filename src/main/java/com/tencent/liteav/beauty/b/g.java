package com.tencent.liteav.beauty.b;

import android.opengl.GLES20;

/* compiled from: TXCGPUColorScreenFilter */
public class g extends ab {
    private int A;
    private float[] B;
    private int x;
    private int y;
    private int z;

    public void d() {
        super.d();
        this.x = GLES20.glGetUniformLocation(q(), "screenMode");
        this.y = GLES20.glGetUniformLocation(q(), "screenReplaceColor");
        this.z = GLES20.glGetUniformLocation(q(), "screenMirrorX");
        this.A = GLES20.glGetUniformLocation(q(), "screenMirrorY");
        b(this.B);
    }

    public void b(float[] fArr) {
        float[] fArr2 = new float[3];
        double d = (double) fArr[0];
        Double.isNaN(d);
        d *= 0.2989d;
        double d2 = (double) fArr[1];
        Double.isNaN(d2);
        d += d2 * 0.5866d;
        double d3 = (double) fArr[2];
        Double.isNaN(d3);
        fArr2[0] = (float) (d + (d3 * 0.1145d));
        d = (double) (fArr[0] - fArr2[0]);
        Double.isNaN(d);
        fArr2[1] = (float) (d * 0.7132d);
        double d4 = (double) (fArr[2] - fArr2[0]);
        Double.isNaN(d4);
        fArr2[2] = (float) (d4 * 0.5647d);
        b(this.y, fArr2);
    }
}
