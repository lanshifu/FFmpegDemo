package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import defpackage.hh.b;
import java.io.IOException;

/* compiled from: RawCcExtractor */
/* renamed from: ig */
public final class ig implements gz {
    private static final int a = z.g("RCC\u0001");
    private final Format b;
    private final n c = new n(9);
    private hj d;
    private int e = 0;
    private int f;
    private long g;
    private int h;
    private int i;

    public void c() {
    }

    public ig(Format format) {
        this.b = format;
    }

    public void a(hb hbVar) {
        hbVar.a(new b(-9223372036854775807L));
        this.d = hbVar.a(0, 3);
        hbVar.a();
        this.d.a(this.b);
    }

    public boolean a(ha haVar) throws IOException, InterruptedException {
        this.c.a();
        haVar.c(this.c.a, 0, 8);
        if (this.c.o() == a) {
            return true;
        }
        return false;
    }

    public int a(ha haVar, hg hgVar) throws IOException, InterruptedException {
        while (true) {
            switch (this.e) {
                case 0:
                    if (b(haVar)) {
                        this.e = 1;
                        break;
                    }
                    return -1;
                case 1:
                    if (c(haVar)) {
                        this.e = 2;
                        break;
                    }
                    this.e = 0;
                    return -1;
                case 2:
                    d(haVar);
                    this.e = 1;
                    return 0;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    public void a(long j, long j2) {
        this.e = 0;
    }

    private boolean b(ha haVar) throws IOException, InterruptedException {
        this.c.a();
        if (!haVar.a(this.c.a, 0, 8, true)) {
            return false;
        }
        if (this.c.o() == a) {
            this.f = this.c.g();
            return true;
        }
        throw new IOException("Input not RawCC");
    }

    private boolean c(ha haVar) throws IOException, InterruptedException {
        this.c.a();
        if (this.f == 0) {
            if (!haVar.a(this.c.a, 0, 5, true)) {
                return false;
            }
            this.g = (this.c.m() * 1000) / 45;
        } else if (this.f != 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unsupported version number: ");
            stringBuilder.append(this.f);
            throw new ParserException(stringBuilder.toString());
        } else if (!haVar.a(this.c.a, 0, 9, true)) {
            return false;
        } else {
            this.g = this.c.q();
        }
        this.h = this.c.g();
        this.i = 0;
        return true;
    }

    private void d(ha haVar) throws IOException, InterruptedException {
        while (this.h > 0) {
            this.c.a();
            haVar.b(this.c.a, 0, 3);
            this.d.a(this.c, 3);
            this.i += 3;
            this.h--;
        }
        if (this.i > 0) {
            this.d.a(this.g, 1, this.i, 0, null);
        }
    }
}
