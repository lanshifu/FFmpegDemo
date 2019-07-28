package defpackage;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* compiled from: PgsDecoder */
/* renamed from: kw */
public final class kw extends c {
    private final n a = new n();
    private final a b = new a();
    private Inflater c;
    private byte[] d;
    private int e;

    /* compiled from: PgsDecoder */
    /* renamed from: kw$a */
    private static final class a {
        private final n a = new n();
        private final int[] b = new int[256];
        private boolean c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;

        private void a(n nVar, int i) {
            if (i % 5 == 2) {
                nVar.d(2);
                Arrays.fill(this.b, 0);
                int i2 = i / 5;
                for (int i3 = 0; i3 < i2; i3++) {
                    int g = nVar.g();
                    int g2 = nVar.g();
                    int g3 = nVar.g();
                    int g4 = nVar.g();
                    int g5 = nVar.g();
                    double d = (double) g2;
                    double d2 = (double) (g3 - 128);
                    Double.isNaN(d2);
                    double d3 = 1.402d * d2;
                    Double.isNaN(d);
                    int i4 = (int) (d3 + d);
                    int i5 = g;
                    double d4 = (double) (g4 - 128);
                    Double.isNaN(d4);
                    double d5 = 0.34414d * d4;
                    Double.isNaN(d);
                    d5 = d - d5;
                    Double.isNaN(d2);
                    g2 = (int) (d5 - (d2 * 0.71414d));
                    Double.isNaN(d4);
                    d4 *= 1.772d;
                    Double.isNaN(d);
                    int i6 = (int) (d + d4);
                    this.b[i5] = z.a(i6, 0, 255) | ((z.a(g2, 0, 255) << 8) | ((g5 << 24) | (z.a(i4, 0, 255) << 16)));
                }
                this.c = true;
            }
        }

        private void b(n nVar, int i) {
            if (i >= 4) {
                int k;
                nVar.d(3);
                i -= 4;
                if (((nVar.g() & 128) != 0 ? 1 : null) != null) {
                    if (i >= 7) {
                        k = nVar.k();
                        if (k >= 4) {
                            this.h = nVar.h();
                            this.i = nVar.h();
                            this.a.a(k - 4);
                            i -= 7;
                        } else {
                            return;
                        }
                    }
                    return;
                }
                int d = this.a.d();
                k = this.a.c();
                if (d < k && i > 0) {
                    i = Math.min(i, k - d);
                    nVar.a(this.a.a, d, i);
                    this.a.c(d + i);
                }
            }
        }

        private void c(n nVar, int i) {
            if (i >= 19) {
                this.d = nVar.h();
                this.e = nVar.h();
                nVar.d(11);
                this.f = nVar.h();
                this.g = nVar.h();
            }
        }

        public b a() {
            if (this.d == 0 || this.e == 0 || this.h == 0 || this.i == 0 || this.a.c() == 0 || this.a.d() != this.a.c() || !this.c) {
                return null;
            }
            this.a.c(0);
            int[] iArr = new int[(this.h * this.i)];
            int i = 0;
            while (i < iArr.length) {
                int i2;
                int g = this.a.g();
                if (g != 0) {
                    i2 = i + 1;
                    iArr[i] = this.b[g];
                } else {
                    g = this.a.g();
                    if (g != 0) {
                        if ((g & 64) == 0) {
                            i2 = g & 63;
                        } else {
                            i2 = ((g & 63) << 8) | this.a.g();
                        }
                        if ((g & 128) == 0) {
                            g = 0;
                        } else {
                            g = this.b[this.a.g()];
                        }
                        i2 += i;
                        Arrays.fill(iArr, i, i2, g);
                    }
                }
                i = i2;
            }
            return new b(Bitmap.createBitmap(iArr, this.h, this.i, Config.ARGB_8888), ((float) this.f) / ((float) this.d), 0, ((float) this.g) / ((float) this.e), 0, ((float) this.h) / ((float) this.d), ((float) this.i) / ((float) this.e));
        }

        public void b() {
            this.d = 0;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.a.a(0);
            this.c = false;
        }
    }

    public kw() {
        super("PgsDecoder");
    }

    /* Access modifiers changed, original: protected */
    public e a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        if (a(bArr, i)) {
            this.a.a(this.d, this.e);
        } else {
            this.a.a(bArr, i);
        }
        this.b.b();
        ArrayList arrayList = new ArrayList();
        while (this.a.b() >= 3) {
            b a = kw.a(this.a, this.b);
            if (a != null) {
                arrayList.add(a);
            }
        }
        return new kx(Collections.unmodifiableList(arrayList));
    }

    private boolean a(byte[] bArr, int i) {
        if (i == 0 || bArr[0] != (byte) 120) {
            return false;
        }
        if (this.c == null) {
            this.c = new Inflater();
            this.d = new byte[i];
        }
        this.e = 0;
        this.c.setInput(bArr, 0, i);
        while (!this.c.finished() && !this.c.needsDictionary() && !this.c.needsInput()) {
            try {
                if (this.e == this.d.length) {
                    this.d = Arrays.copyOf(this.d, this.d.length * 2);
                }
                this.e += this.c.inflate(this.d, this.e, this.d.length - this.e);
            } catch (DataFormatException unused) {
                return false;
            } finally {
                this.c.reset();
                return false;
            }
        }
        boolean finished = this.c.finished();
        return finished;
    }

    private static b a(n nVar, a aVar) {
        int c = nVar.c();
        int g = nVar.g();
        int h = nVar.h();
        int d = nVar.d() + h;
        b bVar = null;
        if (d > c) {
            nVar.c(c);
            return null;
        }
        if (g != 128) {
            switch (g) {
                case 20:
                    aVar.a(nVar, h);
                    break;
                case 21:
                    aVar.b(nVar, h);
                    break;
                case 22:
                    aVar.c(nVar, h);
                    break;
            }
        }
        bVar = aVar.a();
        aVar.b();
        nVar.c(d);
        return bVar;
    }
}
