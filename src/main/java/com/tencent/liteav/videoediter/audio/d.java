package com.tencent.liteav.videoediter.audio;

/* compiled from: TXAudioVolumer */
public class d {
    private volatile float a = 1.0f;

    public void a(float f) {
        this.a = f;
    }

    public short[] a(short[] sArr) {
        if (this.a == 1.0f) {
            return sArr;
        }
        for (int i = 0; i < sArr.length; i++) {
            int i2 = (int) (((float) sArr[i]) * this.a);
            short s = Short.MIN_VALUE;
            if (i2 > 32767) {
                s = Short.MAX_VALUE;
            } else if (i2 >= -32768) {
                s = (short) i2;
            }
            sArr[i] = s;
        }
        return sArr;
    }
}
