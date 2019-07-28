package defpackage;

import android.util.Log;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;

/* compiled from: WavHeaderReader */
/* renamed from: jg */
final class jg {

    /* compiled from: WavHeaderReader */
    /* renamed from: jg$a */
    private static final class a {
        public final int a;
        public final long b;

        private a(int i, long j) {
            this.a = i;
            this.b = j;
        }

        public static a a(ha haVar, n nVar) throws IOException, InterruptedException {
            haVar.c(nVar.a, 0, 8);
            nVar.c(0);
            return new a(nVar.o(), nVar.n());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00c5  */
    public static defpackage.jf a(defpackage.ha r15) throws java.io.IOException, java.lang.InterruptedException {
        /*
        com.google.android.exoplayer2.util.a.a(r15);
        r0 = new com.google.android.exoplayer2.util.n;
        r1 = 16;
        r0.<init>(r1);
        r2 = defpackage.jg.a.a(r15, r0);
        r2 = r2.a;
        r3 = "RIFF";
        r3 = com.google.android.exoplayer2.util.z.g(r3);
        r4 = 0;
        if (r2 == r3) goto L_0x001a;
    L_0x0019:
        return r4;
    L_0x001a:
        r2 = r0.a;
        r3 = 4;
        r5 = 0;
        r15.c(r2, r5, r3);
        r0.c(r5);
        r2 = r0.o();
        r6 = "WAVE";
        r6 = com.google.android.exoplayer2.util.z.g(r6);
        if (r2 == r6) goto L_0x0047;
    L_0x0030:
        r15 = "WavHeaderReader";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Unsupported RIFF format: ";
        r0.append(r1);
        r0.append(r2);
        r0 = r0.toString();
        android.util.Log.e(r15, r0);
        return r4;
    L_0x0047:
        r2 = defpackage.jg.a.a(r15, r0);
    L_0x004b:
        r6 = r2.a;
        r7 = "fmt ";
        r7 = com.google.android.exoplayer2.util.z.g(r7);
        if (r6 == r7) goto L_0x0060;
    L_0x0055:
        r6 = r2.b;
        r2 = (int) r6;
        r15.c(r2);
        r2 = defpackage.jg.a.a(r15, r0);
        goto L_0x004b;
    L_0x0060:
        r6 = r2.b;
        r8 = 16;
        r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        r6 = 1;
        if (r10 < 0) goto L_0x006b;
    L_0x0069:
        r7 = 1;
        goto L_0x006c;
    L_0x006b:
        r7 = 0;
    L_0x006c:
        com.google.android.exoplayer2.util.a.b(r7);
        r7 = r0.a;
        r15.c(r7, r5, r1);
        r0.c(r5);
        r7 = r0.i();
        r9 = r0.i();
        r10 = r0.v();
        r11 = r0.v();
        r12 = r0.i();
        r13 = r0.i();
        r0 = r9 * r13;
        r0 = r0 / 8;
        if (r12 != r0) goto L_0x00f2;
    L_0x0095:
        if (r7 == r6) goto L_0x00be;
    L_0x0097:
        r0 = 3;
        if (r7 == r0) goto L_0x00b6;
    L_0x009a:
        r0 = 65534; // 0xfffe float:9.1833E-41 double:3.2378E-319;
        if (r7 == r0) goto L_0x00be;
    L_0x009f:
        r15 = "WavHeaderReader";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Unsupported WAV format type: ";
        r0.append(r1);
        r0.append(r7);
        r0 = r0.toString();
        android.util.Log.e(r15, r0);
        return r4;
    L_0x00b6:
        r0 = 32;
        if (r13 != r0) goto L_0x00bb;
    L_0x00ba:
        goto L_0x00bc;
    L_0x00bb:
        r3 = 0;
    L_0x00bc:
        r14 = r3;
        goto L_0x00c3;
    L_0x00be:
        r0 = com.google.android.exoplayer2.util.z.b(r13);
        r14 = r0;
    L_0x00c3:
        if (r14 != 0) goto L_0x00e4;
    L_0x00c5:
        r15 = "WavHeaderReader";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Unsupported WAV bit depth ";
        r0.append(r1);
        r0.append(r13);
        r1 = " for type ";
        r0.append(r1);
        r0.append(r7);
        r0 = r0.toString();
        android.util.Log.e(r15, r0);
        return r4;
    L_0x00e4:
        r2 = r2.b;
        r0 = (int) r2;
        r0 = r0 - r1;
        r15.c(r0);
        r15 = new jf;
        r8 = r15;
        r8.<init>(r9, r10, r11, r12, r13, r14);
        return r15;
    L_0x00f2:
        r15 = new com.google.android.exoplayer2.ParserException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Expected block alignment: ";
        r1.append(r2);
        r1.append(r0);
        r0 = "; got: ";
        r1.append(r0);
        r1.append(r12);
        r0 = r1.toString();
        r15.<init>(r0);
        throw r15;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jg.a(ha):jf");
    }

    public static void a(ha haVar, jf jfVar) throws IOException, InterruptedException {
        a.a(haVar);
        a.a(jfVar);
        haVar.a();
        n nVar = new n(8);
        a a = a.a(haVar, nVar);
        while (a.a != z.g("data")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Ignoring unknown WAV chunk: ");
            stringBuilder.append(a.a);
            Log.w("WavHeaderReader", stringBuilder.toString());
            long j = a.b + 8;
            if (a.a == z.g("RIFF")) {
                j = 12;
            }
            if (j <= 2147483647L) {
                haVar.b((int) j);
                a = a.a(haVar, nVar);
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Chunk is too large (~2GB+) to skip; id: ");
                stringBuilder2.append(a.a);
                throw new ParserException(stringBuilder2.toString());
            }
        }
        haVar.b(8);
        jfVar.a(haVar.c(), a.b);
    }
}
