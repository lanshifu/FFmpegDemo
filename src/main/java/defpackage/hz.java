package defpackage;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import java.io.EOFException;
import java.io.IOException;

/* compiled from: OggPageHeader */
/* renamed from: hz */
final class hz {
    private static final int k = z.g("OggS");
    public int a;
    public int b;
    public long c;
    public long d;
    public long e;
    public long f;
    public int g;
    public int h;
    public int i;
    public final int[] j = new int[255];
    private final n l = new n(255);

    hz() {
    }

    public void a() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
    }

    public boolean a(ha haVar, boolean z) throws IOException, InterruptedException {
        this.l.a();
        a();
        int i = 0;
        Object obj = (haVar.d() == -1 || haVar.d() - haVar.b() >= 27) ? 1 : null;
        if (obj == null || !haVar.b(this.l.a, 0, 27, true)) {
            if (z) {
                return false;
            }
            throw new EOFException();
        } else if (this.l.m() == ((long) k)) {
            this.a = this.l.g();
            if (this.a == 0) {
                this.b = this.l.g();
                this.c = this.l.r();
                this.d = this.l.n();
                this.e = this.l.n();
                this.f = this.l.n();
                this.g = this.l.g();
                this.h = this.g + 27;
                this.l.a();
                haVar.c(this.l.a, 0, this.g);
                while (i < this.g) {
                    this.j[i] = this.l.g();
                    this.i += this.j[i];
                    i++;
                }
                return true;
            } else if (z) {
                return false;
            } else {
                throw new ParserException("unsupported bit stream revision");
            }
        } else if (z) {
            return false;
        } else {
            throw new ParserException("expected OggS capture pattern at begin of page");
        }
    }
}
