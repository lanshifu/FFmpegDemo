package com.tencent.liteav.videoediter.audio;

import com.tencent.liteav.basic.log.TXCLog;

/* compiled from: TXChannelResample */
public class e {
    private int a;
    private int b;
    private volatile boolean c;

    public void a(int i, int i2) {
        if (this.a != i || this.b != i2) {
            this.c = true;
            this.a = i;
            this.b = i2;
            a();
        }
    }

    public short[] a(short[] sArr) {
        if (sArr == null || a() || this.a == this.b) {
            return sArr;
        }
        if (this.a == 2 && this.b == 1) {
            return c(sArr);
        }
        return (this.a == 1 && this.b == 2) ? b(sArr) : sArr;
    }

    private boolean a() {
        if (!this.c) {
            TXCLog.e("FaceDetect", "you must set target channel count first");
            return true;
        } else if (this.a >= 1 && this.a <= 2 && this.b >= 1 && this.b <= 2) {
            return false;
        } else {
            TXCLog.e("FaceDetect", "channel count must between 1 and 2");
            return true;
        }
    }

    private short[] b(short[] sArr) {
        short[] sArr2 = new short[(sArr.length * 2)];
        for (int i = 0; i < sArr.length; i++) {
            int i2 = i * 2;
            sArr2[i2] = sArr[i];
            sArr2[i2 + 1] = sArr[i];
        }
        return sArr2;
    }

    private short[] c(short[] sArr) {
        int length = sArr.length / 2;
        short[] sArr2 = new short[length];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            sArr2[i] = sArr[i2];
            i++;
            i2 = (i2 + 1) + 1;
        }
        return sArr2;
    }
}
