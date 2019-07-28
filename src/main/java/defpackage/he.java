package defpackage;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.a.a;
import com.google.android.exoplayer2.util.n;
import java.io.EOFException;
import java.io.IOException;

/* compiled from: Id3Peeker */
/* renamed from: he */
public final class he {
    private final n a = new n(10);

    @Nullable
    public Metadata a(ha haVar, @Nullable a aVar) throws IOException, InterruptedException {
        Metadata metadata = null;
        int i = 0;
        while (true) {
            try {
                haVar.c(this.a.a, 0, 10);
                this.a.c(0);
                if (this.a.k() != com.google.android.exoplayer2.metadata.id3.a.b) {
                    break;
                }
                this.a.d(3);
                int t = this.a.t();
                int i2 = t + 10;
                if (metadata == null) {
                    byte[] bArr = new byte[i2];
                    System.arraycopy(this.a.a, 0, bArr, 0, 10);
                    haVar.c(bArr, 10, t);
                    metadata = new com.google.android.exoplayer2.metadata.id3.a(aVar).a(bArr, i2);
                } else {
                    haVar.c(t);
                }
                i += i2;
            } catch (EOFException unused) {
            }
        }
        haVar.a();
        haVar.c(i);
        return metadata;
    }
}
