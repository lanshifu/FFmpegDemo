package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.z;
import com.tencent.ugc.TXRecordCommon;
import defpackage.hh.b;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: AmrExtractor */
/* renamed from: hk */
public final class hk implements gz {
    public static final hc a = new 1();
    private static final int[] b = new int[]{13, 14, 16, 18, 20, 21, 27, 32, 6, 7, 6, 6, 1, 1, 1, 1};
    private static final int[] c = new int[]{18, 24, 33, 37, 41, 47, 51, 59, 61, 6, 1, 1, 1, 1, 1, 1};
    private static final byte[] d = z.c("#!AMR\n");
    private static final byte[] e = z.c("#!AMR-WB\n");
    private static final int f = c[8];
    private final byte[] g = new byte[1];
    private boolean h;
    private long i;
    private int j;
    private int k;
    private hj l;
    private boolean m;

    /* compiled from: AmrExtractor */
    /* renamed from: hk$1 */
    static class 1 implements hc {
        1() {
        }

        public gz[] a() {
            return new gz[]{new hk()};
        }
    }

    public void c() {
    }

    public boolean a(ha haVar) throws IOException, InterruptedException {
        return b(haVar);
    }

    public void a(hb hbVar) {
        hbVar.a(new b(-9223372036854775807L));
        this.l = hbVar.a(0, 1);
        hbVar.a();
    }

    public int a(ha haVar, hg hgVar) throws IOException, InterruptedException {
        if (haVar.c() != 0 || b(haVar)) {
            a();
            return c(haVar);
        }
        throw new ParserException("Could not find AMR header.");
    }

    public void a(long j, long j2) {
        this.i = 0;
        this.j = 0;
        this.k = 0;
    }

    private boolean b(ha haVar) throws IOException, InterruptedException {
        if (a(haVar, d)) {
            this.h = false;
            haVar.b(d.length);
            return true;
        } else if (!a(haVar, e)) {
            return false;
        } else {
            this.h = true;
            haVar.b(e.length);
            return true;
        }
    }

    private boolean a(ha haVar, byte[] bArr) throws IOException, InterruptedException {
        haVar.a();
        byte[] bArr2 = new byte[bArr.length];
        haVar.c(bArr2, 0, bArr.length);
        return Arrays.equals(bArr2, bArr);
    }

    private void a() {
        if (!this.m) {
            this.m = true;
            this.l.a(Format.a(null, this.h ? "audio/amr-wb" : "audio/3gpp", null, -1, f, 1, this.h ? TXRecordCommon.AUDIO_SAMPLERATE_16000 : TXRecordCommon.AUDIO_SAMPLERATE_8000, -1, null, null, 0, null));
        }
    }

    private int c(ha haVar) throws IOException, InterruptedException {
        if (this.k == 0) {
            try {
                this.j = d(haVar);
                this.k = this.j;
            } catch (EOFException unused) {
                return -1;
            }
        }
        int a = this.l.a(haVar, this.k, true);
        if (a == -1) {
            return -1;
        }
        this.k -= a;
        if (this.k > 0) {
            return 0;
        }
        this.l.a(this.i, 1, this.j, 0, null);
        this.i += 20000;
        return 0;
    }

    private int d(ha haVar) throws IOException, InterruptedException {
        haVar.a();
        haVar.c(this.g, 0, 1);
        byte b = this.g[0];
        if ((b & 131) <= 0) {
            return a((b >> 3) & 15);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid padding bits for frame header ");
        stringBuilder.append(b);
        throw new ParserException(stringBuilder.toString());
    }

    private int a(int i) throws ParserException {
        if (b(i)) {
            return this.h ? c[i] : b[i];
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Illegal AMR ");
            stringBuilder.append(this.h ? "WB" : "NB");
            stringBuilder.append(" frame type ");
            stringBuilder.append(i);
            throw new ParserException(stringBuilder.toString());
        }
    }

    private boolean b(int i) {
        return i >= 0 && i <= 15 && (c(i) || d(i));
    }

    private boolean c(int i) {
        return this.h && (i < 10 || i > 13);
    }

    private boolean d(int i) {
        return !this.h && (i < 12 || i > 14);
    }
}
