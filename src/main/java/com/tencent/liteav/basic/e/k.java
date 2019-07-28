package com.tencent.liteav.basic.e;

import com.yalantis.ucrop.view.CropImageView;

/* compiled from: TXCTextureRotationUtil */
public class k {
    public static final float[] a = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO};
    public static final float[] b = new float[]{1.0f, 1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO};
    public static final float[] c = new float[]{1.0f, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f};
    public static final float[] d = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 1.0f};
    public static final float[] e = new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};

    private static float a(float f) {
        return f == CropImageView.DEFAULT_ASPECT_RATIO ? 1.0f : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public static float[] a(j jVar, boolean z, boolean z2) {
        float[] fArr;
        switch (jVar) {
            case ROTATION_90:
                fArr = (float[]) b.clone();
                break;
            case ROTATION_180:
                fArr = (float[]) c.clone();
                break;
            case ROTATION_270:
                fArr = (float[]) d.clone();
                break;
            default:
                fArr = (float[]) a.clone();
                break;
        }
        if (z) {
            fArr = new float[]{a(fArr[0]), fArr[1], a(fArr[2]), fArr[3], a(fArr[4]), fArr[5], a(fArr[6]), fArr[7]};
        }
        if (!z2) {
            return fArr;
        }
        return new float[]{fArr[0], a(fArr[1]), fArr[2], a(fArr[3]), fArr[4], a(fArr[5]), fArr[6], a(fArr[7])};
    }
}
