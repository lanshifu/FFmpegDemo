package com.tencent.liteav.b;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.util.Log;
import com.tencent.liteav.videoediter.audio.TXSkpResample;
import com.tencent.liteav.videoediter.audio.e;

@TargetApi(16)
/* compiled from: TXCombineAudioMixer */
public class b {
    private volatile float a = 1.0f;
    private volatile float b = 1.0f;
    private int c;
    private MediaFormat d;
    private MediaFormat e;
    private e f;
    private TXSkpResample g;
    private e h;
    private TXSkpResample i;
    private short[] j = null;

    public void a(int i) {
        this.c = i;
    }

    public void a(MediaFormat mediaFormat) {
        this.d = mediaFormat;
    }

    public void b(MediaFormat mediaFormat) {
        this.e = mediaFormat;
    }

    public void a() {
        if (this.d == null || this.e == null) {
            String str = "TXCombineAudioMixer";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("AudioFormat1 :");
            stringBuilder.append(this.d == null ? "not set!!!" : this.d);
            stringBuilder.append(",AudioFormat2:");
            stringBuilder.append(this.e == null ? "not set!!!" : this.e);
            Log.e(str, stringBuilder.toString());
        } else if (this.c == 0) {
            Log.e("TXCombineAudioMixer", "Target Audio SampleRate is not set!!!");
        } else {
            int integer;
            if (this.d.containsKey("channel-count")) {
                integer = this.d.getInteger("channel-count");
                if (integer != 1) {
                    this.f = new e();
                    this.f.a(integer, 1);
                }
            }
            if (this.d.containsKey("sample-rate")) {
                integer = this.d.getInteger("sample-rate");
                if (integer != this.c) {
                    this.g = new TXSkpResample();
                    this.g.init(integer, this.c);
                }
            }
            if (this.e.containsKey("channel-count")) {
                integer = this.e.getInteger("channel-count");
                if (integer != 1) {
                    this.h = new e();
                    this.h.a(integer, 1);
                }
            }
            if (this.e.containsKey("sample-rate")) {
                integer = this.e.getInteger("sample-rate");
                if (integer != this.c) {
                    this.i = new TXSkpResample();
                    this.i.init(integer, this.c);
                }
            }
        }
    }

    public void b() {
        this.j = null;
    }

    public com.tencent.liteav.d.e a(com.tencent.liteav.d.e eVar, com.tencent.liteav.d.e eVar2) {
        short[] a = com.tencent.liteav.videoediter.audio.b.a(eVar.b(), eVar.g());
        if (this.f != null) {
            a = this.f.a(a);
        }
        if (this.g != null) {
            a = this.g.doResample(a);
        }
        short[] a2 = com.tencent.liteav.videoediter.audio.b.a(eVar2.b(), eVar2.g());
        if (this.h != null) {
            a2 = this.h.a(a2);
        }
        if (this.i != null) {
            a2 = this.i.doResample(a2);
        }
        short[] b;
        if (a.length == a2.length) {
            b = b(a, a2);
            eVar.a(com.tencent.liteav.videoediter.audio.b.a(eVar.b(), b));
            eVar.d(b.length * 2);
            return eVar;
        } else if (a.length > a2.length) {
            short[] a3 = a(this.j, a);
            a = b(a3, a2);
            this.j = a(a3, a2.length, a3.length - a2.length);
            eVar2.a(com.tencent.liteav.videoediter.audio.b.a(eVar2.b(), a));
            eVar2.d(a.length * 2);
            return eVar2;
        } else {
            b = a(this.j, a2);
            a2 = b(b, a);
            this.j = a(b, a.length, b.length - a.length);
            eVar.a(com.tencent.liteav.videoediter.audio.b.a(eVar.b(), a2));
            eVar.d(a2.length * 2);
            return eVar;
        }
    }

    private short[] a(short[] sArr, short[] sArr2) {
        if (sArr == null || sArr.length == 0) {
            return sArr2;
        }
        short[] sArr3 = new short[(sArr.length + sArr2.length)];
        System.arraycopy(sArr, 0, sArr3, 0, sArr.length);
        System.arraycopy(sArr2, 0, sArr3, sArr.length, sArr2.length);
        return sArr3;
    }

    private short[] a(short[] sArr, int i, int i2) {
        short[] sArr2 = new short[i2];
        System.arraycopy(sArr, i, sArr2, 0, i2);
        return sArr2;
    }

    private short[] b(short[] sArr, short[] sArr2) {
        int length;
        short[] sArr3;
        if (sArr.length > sArr2.length) {
            length = sArr2.length;
            sArr3 = sArr2;
        } else {
            length = sArr.length;
            sArr3 = sArr;
        }
        for (int i = 0; i < length; i++) {
            int i2 = (int) ((((float) sArr[i]) * this.b) + (((float) sArr2[i]) * this.a));
            short s = Short.MIN_VALUE;
            if (i2 > 32767) {
                s = Short.MAX_VALUE;
            } else if (i2 >= -32768) {
                s = (short) i2;
            }
            sArr3[i] = s;
        }
        return sArr3;
    }
}
