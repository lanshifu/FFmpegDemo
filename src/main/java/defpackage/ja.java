package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.n;
import defpackage.jd.d;
import java.util.List;

/* compiled from: SeiReader */
/* renamed from: ja */
final class ja {
    private final List<Format> a;
    private final hj[] b;

    public ja(List<Format> list) {
        this.a = list;
        this.b = new hj[list.size()];
    }

    public void a(hb hbVar, d dVar) {
        for (int i = 0; i < this.b.length; i++) {
            dVar.a();
            hj a = hbVar.a(dVar.b(), 3);
            Format format = (Format) this.a.get(i);
            String str = format.f;
            boolean z = "application/cea-608".equals(str) || "application/cea-708".equals(str);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Invalid closed caption mime type provided: ");
            stringBuilder.append(str);
            a.a(z, stringBuilder.toString());
            a.a(Format.a(format.a != null ? format.a : dVar.c(), str, null, -1, format.x, format.y, format.z, null));
            this.b[i] = a;
        }
    }

    public void a(long j, n nVar) {
        ks.a(j, nVar, this.b);
    }
}
