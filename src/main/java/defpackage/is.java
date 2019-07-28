package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.n;
import defpackage.jd.d;

/* compiled from: Id3Reader */
/* renamed from: is */
public final class is implements io {
    private final n a = new n(10);
    private hj b;
    private boolean c;
    private long d;
    private int e;
    private int f;

    public void a() {
        this.c = false;
    }

    public void a(hb hbVar, d dVar) {
        dVar.a();
        this.b = hbVar.a(dVar.b(), 4);
        this.b.a(Format.a(dVar.c(), "application/id3", null, -1, null));
    }

    public void a(long j, boolean z) {
        if (z) {
            this.c = true;
            this.d = j;
            this.e = 0;
            this.f = 0;
        }
    }

    public void a(n nVar) {
        if (this.c) {
            int b = nVar.b();
            if (this.f < 10) {
                int min = Math.min(b, 10 - this.f);
                System.arraycopy(nVar.a, nVar.d(), this.a.a, this.f, min);
                if (this.f + min == 10) {
                    this.a.c(0);
                    if (73 == this.a.g() && 68 == this.a.g() && 51 == this.a.g()) {
                        this.a.d(3);
                        this.e = this.a.t() + 10;
                    } else {
                        Log.w("Id3Reader", "Discarding invalid ID3 tag");
                        this.c = false;
                        return;
                    }
                }
            }
            b = Math.min(b, this.e - this.f);
            this.b.a(nVar, b);
            this.f += b;
        }
    }

    public void b() {
        if (this.c && this.e != 0 && this.f == this.e) {
            this.b.a(this.d, 1, this.e, 0, null);
            this.c = false;
        }
    }
}
