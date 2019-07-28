package defpackage;

import android.text.TextUtils;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.util.n;
import defpackage.lo.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: WebvttDecoder */
/* renamed from: lq */
public final class lq extends c {
    private final lp a = new lp();
    private final n b = new n();
    private final a c = new a();
    private final lk d = new lk();
    private final List<ln> e = new ArrayList();

    public lq() {
        super("WebvttDecoder");
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public ls a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.b.a(bArr, i);
        this.c.a();
        this.e.clear();
        lr.a(this.b);
        while (!TextUtils.isEmpty(this.b.z())) {
        }
        ArrayList arrayList = new ArrayList();
        while (true) {
            i = lq.a(this.b);
            if (i == 0) {
                return new ls(arrayList);
            }
            if (i == 1) {
                lq.b(this.b);
            } else if (i == 2) {
                if (arrayList.isEmpty()) {
                    this.b.z();
                    ln a = this.d.a(this.b);
                    if (a != null) {
                        this.e.add(a);
                    }
                } else {
                    throw new SubtitleDecoderException("A style block was found after the first cue.");
                }
            } else if (i == 3 && this.a.a(this.b, this.c, this.e)) {
                arrayList.add(this.c.b());
                this.c.a();
            }
        }
    }

    private static int a(n nVar) {
        int i = -1;
        int i2 = 0;
        while (i == -1) {
            i2 = nVar.d();
            String z = nVar.z();
            i = z == null ? 0 : "STYLE".equals(z) ? 2 : "NOTE".startsWith(z) ? 1 : 3;
        }
        nVar.c(i2);
        return i;
    }

    private static void b(n nVar) {
        while (!TextUtils.isEmpty(nVar.z())) {
        }
    }
}
