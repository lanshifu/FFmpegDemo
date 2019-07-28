package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.n;
import defpackage.hj.a;
import java.io.EOFException;
import java.io.IOException;

/* compiled from: DummyTrackOutput */
/* renamed from: gy */
public final class gy implements hj {
    public void a(long j, int i, int i2, int i3, a aVar) {
    }

    public void a(Format format) {
    }

    public int a(ha haVar, int i, boolean z) throws IOException, InterruptedException {
        int a = haVar.a(i);
        if (a != -1) {
            return a;
        }
        if (z) {
            return -1;
        }
        throw new EOFException();
    }

    public void a(n nVar, int i) {
        nVar.d(i);
    }
}
