package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.n;
import defpackage.ic.a;
import defpackage.if.b;
import defpackage.if.c;
import defpackage.if.d;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: VorbisReader */
/* renamed from: ie */
final class ie extends ic {
    private a a;
    private int b;
    private boolean c;
    private d d;
    private b e;

    /* compiled from: VorbisReader */
    /* renamed from: ie$a */
    static final class a {
        public final d a;
        public final b b;
        public final byte[] c;
        public final c[] d;
        public final int e;

        public a(d dVar, b bVar, byte[] bArr, c[] cVarArr, int i) {
            this.a = dVar;
            this.b = bVar;
            this.c = bArr;
            this.d = cVarArr;
            this.e = i;
        }
    }

    static int a(byte b, int i, int i2) {
        return (b >> i2) & (255 >>> (8 - i));
    }

    ie() {
    }

    public static boolean a(n nVar) {
        try {
            return if.a(1, nVar, true);
        } catch (ParserException unused) {
            return false;
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z) {
        super.a(z);
        if (z) {
            this.a = null;
            this.d = null;
            this.e = null;
        }
        this.b = 0;
        this.c = false;
    }

    /* Access modifiers changed, original: protected */
    public void c(long j) {
        super.c(j);
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        int i2 = 0;
        this.c = i != 0;
        if (this.d != null) {
            i2 = this.d.g;
        }
        this.b = i2;
    }

    /* Access modifiers changed, original: protected */
    public long b(n nVar) {
        int i = 0;
        if ((nVar.a[0] & 1) == 1) {
            return -1;
        }
        int a = ie.a(nVar.a[0], this.a);
        if (this.c) {
            i = (this.b + a) / 4;
        }
        long j = (long) i;
        ie.a(nVar, j);
        this.c = true;
        this.b = a;
        return j;
    }

    /* Access modifiers changed, original: protected */
    public boolean a(n nVar, long j, a aVar) throws IOException, InterruptedException {
        if (this.a != null) {
            return false;
        }
        this.a = c(nVar);
        if (this.a == null) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.a.a.j);
        arrayList.add(this.a.c);
        aVar.a = Format.a(null, "audio/vorbis", null, this.a.a.e, -1, this.a.a.b, (int) this.a.a.c, arrayList, null, 0, null);
        return true;
    }

    /* Access modifiers changed, original: 0000 */
    public a c(n nVar) throws IOException {
        if (this.d == null) {
            this.d = if.a(nVar);
            return null;
        } else if (this.e == null) {
            this.e = if.b(nVar);
            return null;
        } else {
            byte[] bArr = new byte[nVar.c()];
            System.arraycopy(nVar.a, 0, bArr, 0, nVar.c());
            c[] a = if.a(nVar, this.d.b);
            return new a(this.d, this.e, bArr, a, if.a(a.length - 1));
        }
    }

    static void a(n nVar, long j) {
        nVar.b(nVar.c() + 4);
        nVar.a[nVar.c() - 4] = (byte) ((int) (j & 255));
        nVar.a[nVar.c() - 3] = (byte) ((int) ((j >>> 8) & 255));
        nVar.a[nVar.c() - 2] = (byte) ((int) ((j >>> 16) & 255));
        nVar.a[nVar.c() - 1] = (byte) ((int) ((j >>> 24) & 255));
    }

    private static int a(byte b, a aVar) {
        if (aVar.d[ie.a(b, aVar.e, 1)].a) {
            return aVar.a.h;
        }
        return aVar.a.g;
    }
}
