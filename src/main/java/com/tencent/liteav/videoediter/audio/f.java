package com.tencent.liteav.videoediter.audio;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* compiled from: TXMixerHelper */
public class f {
    private volatile float a = 1.0f;
    private volatile float b = 1.0f;

    private short a(int i) {
        return i > 32767 ? Short.MAX_VALUE : i < -32768 ? Short.MIN_VALUE : (short) i;
    }

    public void a(float f) {
        this.a = f;
    }

    public void b(float f) {
        this.b = f;
    }

    public short[] a(@NonNull short[] sArr, @Nullable short[] sArr2) {
        int i = 0;
        if (sArr2 != null && sArr2.length != 0) {
            while (i < sArr.length) {
                sArr[i] = a((int) ((((float) sArr[i]) * this.b) + (((float) sArr2[i]) * this.a)));
                i++;
            }
            return sArr;
        } else if (this.b == 1.0f) {
            return sArr;
        } else {
            while (i < sArr.length) {
                sArr[i] = a((int) (((float) sArr[i]) * this.b));
                i++;
            }
            return sArr;
        }
    }
}
