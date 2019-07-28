package defpackage;

import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import defpackage.lo.a;
import java.util.ArrayList;
import java.util.Collections;

/* compiled from: Mp4WebvttDecoder */
/* renamed from: ll */
public final class ll extends c {
    private static final int a = z.g("payl");
    private static final int b = z.g("sttg");
    private static final int c = z.g("vttc");
    private final n d = new n();
    private final a e = new a();

    public ll() {
        super("Mp4WebvttDecoder");
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public lm a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.d.a(bArr, i);
        ArrayList arrayList = new ArrayList();
        while (this.d.b() > 0) {
            if (this.d.b() >= 8) {
                i = this.d.o();
                if (this.d.o() == c) {
                    arrayList.add(ll.a(this.d, this.e, i - 8));
                } else {
                    this.d.d(i - 8);
                }
            } else {
                throw new SubtitleDecoderException("Incomplete Mp4Webvtt Top Level box header found.");
            }
        }
        return new lm(arrayList);
    }

    private static b a(n nVar, a aVar, int i) throws SubtitleDecoderException {
        aVar.a();
        while (i > 0) {
            if (i >= 8) {
                int o = nVar.o();
                int o2 = nVar.o();
                i -= 8;
                o -= 8;
                String a = z.a(nVar.a, nVar.d(), o);
                nVar.d(o);
                i -= o;
                if (o2 == b) {
                    lp.a(a, aVar);
                } else if (o2 == a) {
                    lp.a(null, a.trim(), aVar, Collections.emptyList());
                }
            } else {
                throw new SubtitleDecoderException("Incomplete vtt cue box header found.");
            }
        }
        return aVar.b();
    }
}
