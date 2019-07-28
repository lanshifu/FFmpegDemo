package defpackage;

import com.google.android.exoplayer2.text.c;
import com.google.android.exoplayer2.util.n;
import java.util.List;

/* compiled from: DvbDecoder */
/* renamed from: kt */
public final class kt extends c {
    private final ku a;

    public kt(List<byte[]> list) {
        super("DvbDecoder");
        n nVar = new n((byte[]) list.get(0));
        this.a = new ku(nVar.h(), nVar.h());
    }

    /* Access modifiers changed, original: protected */
    /* renamed from: b */
    public kv a(byte[] bArr, int i, boolean z) {
        if (z) {
            this.a.a();
        }
        return new kv(this.a.a(bArr, i));
    }
}
