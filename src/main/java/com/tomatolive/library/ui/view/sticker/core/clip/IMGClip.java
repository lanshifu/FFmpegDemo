package com.tomatolive.library.ui.view.sticker.core.clip;

import android.graphics.RectF;
import com.yalantis.ucrop.view.CropImageView;

public interface IMGClip {
    public static final int CLIP_CELL_STRIDES = 1935858840;
    public static final byte[] CLIP_CORNERS = new byte[]{(byte) 8, (byte) 8, (byte) 9, (byte) 8, (byte) 6, (byte) 8, (byte) 4, (byte) 8, (byte) 4, (byte) 8, (byte) 4, (byte) 1, (byte) 4, (byte) 10, (byte) 4, (byte) 8, (byte) 4, (byte) 4, (byte) 6, (byte) 4, (byte) 9, (byte) 4, (byte) 8, (byte) 4, (byte) 8, (byte) 4, (byte) 8, (byte) 6, (byte) 8, (byte) 9, (byte) 8, (byte) 8};
    public static final float CLIP_CORNER_SIZE = 48.0f;
    public static final float[] CLIP_CORNER_SIZES = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 48.0f, -48.0f};
    public static final float[] CLIP_CORNER_STEPS = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 3.0f, -3.0f};
    public static final int CLIP_CORNER_STRIDES = 179303760;
    public static final float CLIP_FRAME_MIN = 150.72f;
    public static final float CLIP_MARGIN = 60.0f;
    public static final float[] CLIP_SIZE_RATIO = new float[]{CropImageView.DEFAULT_ASPECT_RATIO, 1.0f, 0.33f, 0.66f};
    public static final float CLIP_THICKNESS_CELL = 3.0f;
    public static final float CLIP_THICKNESS_FRAME = 8.0f;
    public static final float CLIP_THICKNESS_SEWING = 14.0f;

    public enum Anchor {
        LEFT(1),
        RIGHT(2),
        TOP(4),
        BOTTOM(8),
        LEFT_TOP(5),
        RIGHT_TOP(6),
        LEFT_BOTTOM(9),
        RIGHT_BOTTOM(10);
        
        static final int[] PN = null;
        int v;

        static {
            PN = new int[]{1, -1};
        }

        private Anchor(int i) {
            this.v = i;
        }

        public void move(RectF rectF, RectF rectF2, float f, float f2) {
            float[] cohesion = cohesion(rectF, 60.0f);
            float[] cohesion2 = cohesion(rectF2, 150.72f);
            float[] cohesion3 = cohesion(rectF2, CropImageView.DEFAULT_ASPECT_RATIO);
            float[] fArr = new float[]{f, CropImageView.DEFAULT_ASPECT_RATIO, f2};
            for (int i = 0; i < 4; i++) {
                if (((1 << i) & this.v) != 0) {
                    int i2 = i & 1;
                    float f3 = (float) PN[i2];
                    cohesion3[i] = f3 * revise((cohesion3[i] + fArr[i & 2]) * f3, cohesion[i] * f3, cohesion2[PN[i2] + i] * f3);
                }
            }
            rectF2.set(cohesion3[0], cohesion3[2], cohesion3[1], cohesion3[3]);
        }

        public static float revise(float f, float f2, float f3) {
            return Math.min(Math.max(f, f2), f3);
        }

        public static float[] cohesion(RectF rectF, float f) {
            return new float[]{rectF.left + f, rectF.right - f, rectF.top + f, rectF.bottom - f};
        }

        public static boolean isCohesionContains(RectF rectF, float f, float f2, float f3) {
            return rectF.left + f < f2 && rectF.right - f > f2 && rectF.top + f < f3 && rectF.bottom - f > f3;
        }

        public static Anchor valueOf(int i) {
            for (Anchor anchor : values()) {
                if (anchor.v == i) {
                    return anchor;
                }
            }
            return null;
        }
    }
}
