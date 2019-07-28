package defpackage;

import android.annotation.TargetApi;
import android.media.MediaCodec.CryptoInfo;
import android.media.MediaCodec.CryptoInfo.Pattern;
import com.google.android.exoplayer2.util.z;

/* compiled from: CryptoInfo */
/* renamed from: gp */
public final class gp {
    public byte[] a;
    public byte[] b;
    public int c;
    public int[] d;
    public int[] e;
    public int f;
    public int g;
    public int h;
    private final CryptoInfo i;
    private final a j;

    @TargetApi(24)
    /* compiled from: CryptoInfo */
    /* renamed from: gp$a */
    private static final class a {
        private final CryptoInfo a;
        private final Pattern b;

        private a(CryptoInfo cryptoInfo) {
            this.a = cryptoInfo;
            this.b = new Pattern(0, 0);
        }

        private void a(int i, int i2) {
            this.b.set(i, i2);
            this.a.setPattern(this.b);
        }
    }

    public gp() {
        this.i = z.a >= 16 ? b() : null;
        this.j = z.a >= 24 ? new a(this.i) : null;
    }

    public void a(int i, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int i2, int i3, int i4) {
        this.f = i;
        this.d = iArr;
        this.e = iArr2;
        this.b = bArr;
        this.a = bArr2;
        this.c = i2;
        this.g = i3;
        this.h = i4;
        if (z.a >= 16) {
            c();
        }
    }

    @TargetApi(16)
    public CryptoInfo a() {
        return this.i;
    }

    @TargetApi(16)
    private CryptoInfo b() {
        return new CryptoInfo();
    }

    @TargetApi(16)
    private void c() {
        this.i.numSubSamples = this.f;
        this.i.numBytesOfClearData = this.d;
        this.i.numBytesOfEncryptedData = this.e;
        this.i.key = this.b;
        this.i.iv = this.a;
        this.i.mode = this.c;
        if (z.a >= 24) {
            this.j.a(this.g, this.h);
        }
    }
}
