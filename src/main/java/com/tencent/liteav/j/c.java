package com.tencent.liteav.j;

import com.tencent.liteav.basic.log.TXCLog;
import org.slf4j.Marker;

/* compiled from: TXResolutionUtils */
public class c {
    public static int[] a(int i, int i2, int i3) {
        StringBuilder stringBuilder;
        if (i2 <= 0 || i3 <= 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("no input size. ");
            stringBuilder.append(i2);
            stringBuilder.append(Marker.ANY_MARKER);
            stringBuilder.append(i3);
            TXCLog.w("GlUtil", stringBuilder.toString());
            return new int[]{i2, i3};
        }
        float f = (((float) i2) * 1.0f) / ((float) i3);
        if (i == 0) {
            if ((i2 <= 640 && i3 <= 360) || (i2 <= 360 && i3 <= 640)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("target size: ");
                stringBuilder.append(i2);
                stringBuilder.append(Marker.ANY_MARKER);
                stringBuilder.append(i3);
                TXCLog.d("GlUtil", stringBuilder.toString());
                return new int[]{i2, i3};
            } else if (i2 >= i3) {
                i = (int) (360.0f * f);
                i2 = i >= 640 ? 640 : i;
                i3 = (int) (((float) i2) / f);
            } else {
                i2 = (int) (640.0f * f);
                if (i2 >= 360) {
                    i2 = 360;
                }
                i3 = (int) (((float) i2) / f);
            }
        } else if (i == 1) {
            if ((i2 <= 640 && i3 <= 480) || (i2 <= 480 && i3 <= 640)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("target size: ");
                stringBuilder.append(i2);
                stringBuilder.append(Marker.ANY_MARKER);
                stringBuilder.append(i3);
                TXCLog.d("GlUtil", stringBuilder.toString());
                return new int[]{i2, i3};
            } else if (i2 >= i3) {
                i = (int) (480.0f * f);
                i2 = i >= 640 ? 640 : i;
                i3 = (int) (((float) i2) / f);
            } else {
                i2 = (int) (640.0f * f);
                if (i2 >= 480) {
                    i2 = 480;
                }
                i3 = (int) (((float) i2) / f);
            }
        } else if (i == 2) {
            if ((i2 <= 960 && i3 <= 544) || (i2 <= 544 && i3 <= 960)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("target size: ");
                stringBuilder.append(i2);
                stringBuilder.append(Marker.ANY_MARKER);
                stringBuilder.append(i3);
                TXCLog.d("GlUtil", stringBuilder.toString());
                return new int[]{i2, i3};
            } else if (i2 >= i3) {
                i = (int) (544.0f * f);
                i2 = i >= 960 ? 960 : i;
                i3 = (int) (((float) i2) / f);
            } else {
                i2 = (int) (960.0f * f);
                if (i2 >= 544) {
                    i2 = 544;
                }
                i3 = (int) (((float) i2) / f);
            }
        } else if (i == 3) {
            if ((i2 <= 1280 && i3 <= 720) || (i2 <= 720 && i3 <= 1280)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("target size: ");
                stringBuilder.append(i2);
                stringBuilder.append(Marker.ANY_MARKER);
                stringBuilder.append(i3);
                TXCLog.d("GlUtil", stringBuilder.toString());
                return new int[]{i2, i3};
            } else if (i2 >= i3) {
                i = (int) (720.0f * f);
                i2 = i >= 1280 ? 1280 : i;
                i3 = (int) (((float) i2) / f);
            } else {
                i2 = (int) (1280.0f * f);
                if (i2 >= 720) {
                    i2 = 720;
                }
                i3 = (int) (((float) i2) / f);
            }
        }
        i2 = ((i3 + 1) >> 1) << 1;
        return new int[]{((i2 + 1) >> 1) << 1, i2};
    }
}
