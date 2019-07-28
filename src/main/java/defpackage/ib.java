package defpackage;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import com.tencent.ugc.TXRecordCommon;
import defpackage.ic.a;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: OpusReader */
/* renamed from: ib */
final class ib extends ic {
    private static final int a = z.g("Opus");
    private static final byte[] b = new byte[]{(byte) 79, (byte) 112, (byte) 117, (byte) 115, (byte) 72, (byte) 101, (byte) 97, (byte) 100};
    private boolean c;

    ib() {
    }

    public static boolean a(n nVar) {
        if (nVar.b() < b.length) {
            return false;
        }
        byte[] bArr = new byte[b.length];
        nVar.a(bArr, 0, b.length);
        return Arrays.equals(bArr, b);
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z) {
        super.a(z);
        if (z) {
            this.c = false;
        }
    }

    /* Access modifiers changed, original: protected */
    public long b(n nVar) {
        return b(a(nVar.a));
    }

    /* Access modifiers changed, original: protected */
    public boolean a(n nVar, long j, a aVar) throws IOException, InterruptedException {
        boolean z = true;
        if (this.c) {
            if (nVar.o() != a) {
                z = false;
            }
            nVar.c(0);
            return z;
        }
        byte[] copyOf = Arrays.copyOf(nVar.a, nVar.c());
        int i = copyOf[9] & 255;
        int i2 = ((copyOf[11] & 255) << 8) | (copyOf[10] & 255);
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(copyOf);
        a(arrayList, i2);
        a(arrayList, 3840);
        aVar.a = Format.a(null, "audio/opus", null, -1, -1, i, TXRecordCommon.AUDIO_SAMPLERATE_48000, arrayList, null, 0, null);
        this.c = true;
        return true;
    }

    private void a(List<byte[]> list, int i) {
        list.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong((((long) i) * 1000000000) / 48000).array());
    }

    private long a(byte[] bArr) {
        int i;
        int i2 = bArr[0] & 255;
        switch (i2 & 3) {
            case 0:
                i = 1;
                break;
            case 1:
            case 2:
                i = 2;
                break;
            default:
                i = bArr[1] & 63;
                break;
        }
        i2 >>= 3;
        int i3 = i2 & 3;
        i2 = i2 >= 16 ? 2500 << i3 : i2 >= 12 ? 10000 << (i3 & 1) : i3 == 3 ? 60000 : 10000 << i3;
        return ((long) i) * ((long) i2);
    }
}
