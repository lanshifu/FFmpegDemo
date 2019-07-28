package defpackage;

import android.util.SparseArray;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.m;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.w;
import defpackage.hh.b;
import defpackage.jd.d;
import java.io.IOException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: PsExtractor */
/* renamed from: ix */
public final class ix implements gz {
    public static final hc a = new 1();
    private final w b;
    private final SparseArray<a> c;
    private final n d;
    private boolean e;
    private boolean f;
    private boolean g;
    private long h;
    private hb i;

    /* compiled from: PsExtractor */
    /* renamed from: ix$a */
    private static final class a {
        private final io a;
        private final w b;
        private final m c = new m(new byte[64]);
        private boolean d;
        private boolean e;
        private boolean f;
        private int g;
        private long h;

        public a(io ioVar, w wVar) {
            this.a = ioVar;
            this.b = wVar;
        }

        public void a() {
            this.f = false;
            this.a.a();
        }

        public void a(n nVar) throws ParserException {
            nVar.a(this.c.a, 0, 3);
            this.c.a(0);
            b();
            nVar.a(this.c.a, 0, this.g);
            this.c.a(0);
            c();
            this.a.a(this.h, true);
            this.a.a(nVar);
            this.a.b();
        }

        private void b() {
            this.c.b(8);
            this.d = this.c.e();
            this.e = this.c.e();
            this.c.b(6);
            this.g = this.c.c(8);
        }

        private void c() {
            this.h = 0;
            if (this.d) {
                this.c.b(4);
                long c = ((long) this.c.c(3)) << 30;
                this.c.b(1);
                c |= (long) (this.c.c(15) << 15);
                this.c.b(1);
                c |= (long) this.c.c(15);
                this.c.b(1);
                if (!this.f && this.e) {
                    this.c.b(4);
                    long c2 = ((long) this.c.c(3)) << 30;
                    this.c.b(1);
                    c2 |= (long) (this.c.c(15) << 15);
                    this.c.b(1);
                    c2 |= (long) this.c.c(15);
                    this.c.b(1);
                    this.b.b(c2);
                    this.f = true;
                }
                this.h = this.b.b(c);
            }
        }
    }

    /* compiled from: PsExtractor */
    /* renamed from: ix$1 */
    static class 1 implements hc {
        1() {
        }

        public gz[] a() {
            return new gz[]{new ix()};
        }
    }

    public void c() {
    }

    public ix() {
        this(new w(0));
    }

    public ix(w wVar) {
        this.b = wVar;
        this.d = new n(4096);
        this.c = new SparseArray();
    }

    public boolean a(ha haVar) throws IOException, InterruptedException {
        byte[] bArr = new byte[14];
        boolean z = false;
        haVar.c(bArr, 0, 14);
        if (442 != (((((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16)) | ((bArr[2] & 255) << 8)) | (bArr[3] & 255)) || (bArr[4] & 196) != 68 || (bArr[6] & 4) != 4 || (bArr[8] & 4) != 4 || (bArr[9] & 1) != 1 || (bArr[12] & 3) != 3) {
            return false;
        }
        haVar.c(bArr[13] & 7);
        haVar.c(bArr, 0, 3);
        if (1 == ((((bArr[0] & 255) << 16) | ((bArr[1] & 255) << 8)) | (bArr[2] & 255))) {
            z = true;
        }
        return z;
    }

    public void a(hb hbVar) {
        this.i = hbVar;
        hbVar.a(new b(-9223372036854775807L));
    }

    public void a(long j, long j2) {
        this.b.d();
        for (int i = 0; i < this.c.size(); i++) {
            ((a) this.c.valueAt(i)).a();
        }
    }

    public int a(ha haVar, hg hgVar) throws IOException, InterruptedException {
        if (!haVar.b(this.d.a, 0, 4, true)) {
            return -1;
        }
        this.d.c(0);
        int o = this.d.o();
        if (o == 441) {
            return -1;
        }
        if (o == 442) {
            haVar.c(this.d.a, 0, 10);
            this.d.c(9);
            haVar.b((this.d.g() & 7) + 14);
            return 0;
        } else if (o == 443) {
            haVar.c(this.d.a, 0, 2);
            this.d.c(0);
            haVar.b(this.d.h() + 6);
            return 0;
        } else if (((o & -256) >> 8) != 1) {
            haVar.b(1);
            return 0;
        } else {
            o &= 255;
            a aVar = (a) this.c.get(o);
            if (!this.e) {
                if (aVar == null) {
                    io ioVar = null;
                    if (o == 189) {
                        ioVar = new ii();
                        this.f = true;
                        this.h = haVar.c();
                    } else if ((o & 224) == 192) {
                        ioVar = new iu();
                        this.f = true;
                        this.h = haVar.c();
                    } else if ((o & 240) == 224) {
                        ioVar = new ip();
                        this.g = true;
                        this.h = haVar.c();
                    }
                    if (ioVar != null) {
                        ioVar.a(this.i, new d(o, 256));
                        aVar = new a(ioVar, this.b);
                        this.c.put(o, aVar);
                    }
                }
                long j = (this.f && this.g) ? this.h + IjkMediaMeta.AV_CH_TOP_FRONT_CENTER : 1048576;
                if (haVar.c() > j) {
                    this.e = true;
                    this.i.a();
                }
            }
            haVar.c(this.d.a, 0, 2);
            this.d.c(0);
            o = this.d.h() + 6;
            if (aVar == null) {
                haVar.b(o);
            } else {
                this.d.a(o);
                haVar.b(this.d.a, 0, o);
                this.d.c(6);
                aVar.a(this.d);
                this.d.b(this.d.e());
            }
            return 0;
        }
    }
}
