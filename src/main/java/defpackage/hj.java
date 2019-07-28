package defpackage;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.n;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: TrackOutput */
/* renamed from: hj */
public interface hj {

    /* compiled from: TrackOutput */
    /* renamed from: hj$a */
    public static final class a {
        public final int a;
        public final byte[] b;
        public final int c;
        public final int d;

        public a(int i, byte[] bArr, int i2, int i3) {
            this.a = i;
            this.b = bArr;
            this.c = i2;
            this.d = i3;
        }

        public boolean equals(@Nullable Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (!(this.a == aVar.a && this.c == aVar.c && this.d == aVar.d && Arrays.equals(this.b, aVar.b))) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (((((this.a * 31) + Arrays.hashCode(this.b)) * 31) + this.c) * 31) + this.d;
        }
    }

    int a(ha haVar, int i, boolean z) throws IOException, InterruptedException;

    void a(long j, int i, int i2, int i3, a aVar);

    void a(Format format);

    void a(n nVar, int i);
}
