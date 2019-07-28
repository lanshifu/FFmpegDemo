package defpackage;

import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.l;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.z;
import com.tencent.ugc.TXRecordCommon;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import defpackage.hh.b;
import defpackage.hj.a;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* compiled from: MatroskaExtractor */
/* renamed from: ho */
public final class ho implements gz {
    public static final hc a = new 1();
    private static final byte[] b = new byte[]{(byte) 49, (byte) 10, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 48, (byte) 48, (byte) 32, (byte) 45, (byte) 45, (byte) 62, (byte) 32, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 48, (byte) 48, (byte) 10};
    private static final byte[] c = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
    private static final byte[] d = z.c("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] e = new byte[]{(byte) 68, (byte) 105, (byte) 97, (byte) 108, (byte) 111, (byte) 103, (byte) 117, (byte) 101, (byte) 58, (byte) 32, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 58, (byte) 48, (byte) 48, (byte) 44};
    private static final byte[] f = new byte[]{(byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32};
    private static final UUID g = new UUID(72057594037932032L, -9223371306706625679L);
    private b A;
    private boolean B;
    private int C;
    private long D;
    private boolean E;
    private long F;
    private long G;
    private long H;
    private i I;
    private i J;
    private boolean K;
    private int L;
    private long M;
    private long N;
    private int O;
    private int P;
    private int[] Q;
    private int R;
    private int S;
    private int T;
    private int U;
    private boolean V;
    private boolean W;
    private boolean X;
    private boolean Y;
    private byte Z;
    private int aa;
    private int ab;
    private int ac;
    private boolean ad;
    private boolean ae;
    private hb af;
    private final hm h;
    private final hq i;
    private final SparseArray<b> j;
    private final boolean k;
    private final n l;
    private final n m;
    private final n n;
    private final n o;
    private final n p;
    private final n q;
    private final n r;
    private final n s;
    private final n t;
    private ByteBuffer u;
    private long v;
    private long w;
    private long x;
    private long y;
    private long z;

    /* compiled from: MatroskaExtractor */
    /* renamed from: ho$b */
    private static final class b {
        public float A;
        public float B;
        public float C;
        public float D;
        public float E;
        public float F;
        public int G;
        public int H;
        public int I;
        public long J;
        public long K;
        @Nullable
        public c L;
        public boolean M;
        public boolean N;
        public hj O;
        public int P;
        private String Q;
        public String a;
        public int b;
        public int c;
        public int d;
        public boolean e;
        public byte[] f;
        public a g;
        public byte[] h;
        public DrmInitData i;
        public int j;
        public int k;
        public int l;
        public int m;
        public int n;
        public byte[] o;
        public int p;
        public boolean q;
        public int r;
        public int s;
        public int t;
        public int u;
        public int v;
        public float w;
        public float x;
        public float y;
        public float z;

        private b() {
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = null;
            this.p = -1;
            this.q = false;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = 1000;
            this.v = 200;
            this.w = -1.0f;
            this.x = -1.0f;
            this.y = -1.0f;
            this.z = -1.0f;
            this.A = -1.0f;
            this.B = -1.0f;
            this.C = -1.0f;
            this.D = -1.0f;
            this.E = -1.0f;
            this.F = -1.0f;
            this.G = 1;
            this.H = -1;
            this.I = TXRecordCommon.AUDIO_SAMPLERATE_8000;
            this.J = 0;
            this.K = 0;
            this.N = true;
            this.Q = "eng";
        }

        /* synthetic */ b(1 1) {
            this();
        }

        /* JADX WARNING: Missing block: B:102:0x01d5, code skipped:
            r12 = r1;
            r18 = r2;
            r2 = null;
            r15 = -1;
     */
        /* JADX WARNING: Missing block: B:117:0x025a, code skipped:
            r12 = r1;
            r2 = null;
            r15 = 4096;
     */
        /* JADX WARNING: Missing block: B:119:0x0268, code skipped:
            r12 = r1;
     */
        /* JADX WARNING: Missing block: B:126:0x0304, code skipped:
            r12 = r1;
            r2 = r3;
     */
        /* JADX WARNING: Missing block: B:134:0x0320, code skipped:
            r12 = r1;
            r2 = null;
     */
        /* JADX WARNING: Missing block: B:135:0x0322, code skipped:
            r15 = -1;
     */
        /* JADX WARNING: Missing block: B:136:0x0323, code skipped:
            r18 = -1;
     */
        /* JADX WARNING: Missing block: B:137:0x0325, code skipped:
            r1 = r0.N | 0;
     */
        /* JADX WARNING: Missing block: B:138:0x032a, code skipped:
            if (r0.M == false) goto L_0x032d;
     */
        /* JADX WARNING: Missing block: B:139:0x032c, code skipped:
            r6 = 2;
     */
        /* JADX WARNING: Missing block: B:140:0x032d, code skipped:
            r1 = r1 | r6;
     */
        /* JADX WARNING: Missing block: B:141:0x0332, code skipped:
            if (com.google.android.exoplayer2.util.k.a(r12) == false) goto L_0x0355;
     */
        /* JADX WARNING: Missing block: B:142:0x0334, code skipped:
            r1 = com.google.android.exoplayer2.Format.a(java.lang.Integer.toString(r28), r12, null, -1, r15, r0.G, r0.I, r18, r2, r0.i, r1, r0.Q);
            r8 = 1;
     */
        /* JADX WARNING: Missing block: B:144:0x0359, code skipped:
            if (com.google.android.exoplayer2.util.k.b(r12) == false) goto L_0x03cf;
     */
        /* JADX WARNING: Missing block: B:146:0x035d, code skipped:
            if (r0.n != 0) goto L_0x0375;
     */
        /* JADX WARNING: Missing block: B:148:0x0361, code skipped:
            if (r0.l != -1) goto L_0x0366;
     */
        /* JADX WARNING: Missing block: B:149:0x0363, code skipped:
            r1 = r0.j;
     */
        /* JADX WARNING: Missing block: B:150:0x0366, code skipped:
            r1 = r0.l;
     */
        /* JADX WARNING: Missing block: B:151:0x0368, code skipped:
            r0.l = r1;
     */
        /* JADX WARNING: Missing block: B:152:0x036c, code skipped:
            if (r0.m != -1) goto L_0x0371;
     */
        /* JADX WARNING: Missing block: B:153:0x036e, code skipped:
            r1 = r0.k;
     */
        /* JADX WARNING: Missing block: B:154:0x0371, code skipped:
            r1 = r0.m;
     */
        /* JADX WARNING: Missing block: B:155:0x0373, code skipped:
            r0.m = r1;
     */
        /* JADX WARNING: Missing block: B:157:0x0379, code skipped:
            if (r0.l == -1) goto L_0x0391;
     */
        /* JADX WARNING: Missing block: B:159:0x037d, code skipped:
            if (r0.m == -1) goto L_0x0391;
     */
        /* JADX WARNING: Missing block: B:160:0x037f, code skipped:
            r21 = ((float) (r0.k * r0.l)) / ((float) (r0.j * r0.m));
     */
        /* JADX WARNING: Missing block: B:161:0x0391, code skipped:
            r21 = -1.0f;
     */
        /* JADX WARNING: Missing block: B:163:0x0395, code skipped:
            if (r0.q == false) goto L_0x03a6;
     */
        /* JADX WARNING: Missing block: B:164:0x0397, code skipped:
            r10 = new com.google.android.exoplayer2.video.ColorInfo(r0.r, r0.t, r0.s, c());
     */
        /* JADX WARNING: Missing block: B:165:0x03a6, code skipped:
            r24 = r10;
            r1 = com.google.android.exoplayer2.Format.a(java.lang.Integer.toString(r28), r12, null, -1, r15, r0.j, r0.k, -1.0f, r2, -1, r21, r0.o, r0.p, r24, r0.i);
            r8 = 2;
     */
        /* JADX WARNING: Missing block: B:167:0x03d5, code skipped:
            if ("application/x-subrip".equals(r12) == false) goto L_0x03e5;
     */
        /* JADX WARNING: Missing block: B:168:0x03d7, code skipped:
            r1 = com.google.android.exoplayer2.Format.a(java.lang.Integer.toString(r28), r12, r1, r0.Q, r0.i);
     */
        /* JADX WARNING: Missing block: B:170:0x03eb, code skipped:
            if ("text/x-ssa".equals(r12) == false) goto L_0x041b;
     */
        /* JADX WARNING: Missing block: B:171:0x03ed, code skipped:
            r2 = new java.util.ArrayList(2);
            r2.add(defpackage.ho.a());
            r2.add(r0.h);
            r1 = com.google.android.exoplayer2.Format.a(java.lang.Integer.toString(r28), r12, null, -1, r1, r0.Q, -1, r0.i, Long.MAX_VALUE, r2);
     */
        /* JADX WARNING: Missing block: B:173:0x0421, code skipped:
            if ("application/vobsub".equals(r12) != false) goto L_0x043c;
     */
        /* JADX WARNING: Missing block: B:175:0x0429, code skipped:
            if ("application/pgs".equals(r12) != false) goto L_0x043c;
     */
        /* JADX WARNING: Missing block: B:177:0x0431, code skipped:
            if ("application/dvbsubs".equals(r12) == false) goto L_0x0434;
     */
        /* JADX WARNING: Missing block: B:179:0x043b, code skipped:
            throw new com.google.android.exoplayer2.ParserException("Unexpected MIME type.");
     */
        /* JADX WARNING: Missing block: B:180:0x043c, code skipped:
            r1 = com.google.android.exoplayer2.Format.a(java.lang.Integer.toString(r28), r12, null, -1, r1, r2, r0.Q, r0.i);
     */
        /* JADX WARNING: Missing block: B:181:0x0451, code skipped:
            r0.O = r27.a(r0.b, r8);
            r0.O.a(r1);
     */
        /* JADX WARNING: Missing block: B:182:0x0460, code skipped:
            return;
     */
        public void a(defpackage.hb r27, int r28) throws com.google.android.exoplayer2.ParserException {
            /*
            r26 = this;
            r0 = r26;
            r1 = r0.a;
            r2 = r1.hashCode();
            r3 = 4;
            r4 = 8;
            r5 = 1;
            r6 = 0;
            r7 = 2;
            r8 = 3;
            r9 = -1;
            switch(r2) {
                case -2095576542: goto L_0x0156;
                case -2095575984: goto L_0x014c;
                case -1985379776: goto L_0x0141;
                case -1784763192: goto L_0x0136;
                case -1730367663: goto L_0x012b;
                case -1482641358: goto L_0x0120;
                case -1482641357: goto L_0x0115;
                case -1373388978: goto L_0x010a;
                case -933872740: goto L_0x00ff;
                case -538363189: goto L_0x00f4;
                case -538363109: goto L_0x00e9;
                case -425012669: goto L_0x00dd;
                case -356037306: goto L_0x00d1;
                case 62923557: goto L_0x00c5;
                case 62923603: goto L_0x00b9;
                case 62927045: goto L_0x00ad;
                case 82338133: goto L_0x00a2;
                case 82338134: goto L_0x0097;
                case 99146302: goto L_0x008b;
                case 444813526: goto L_0x007f;
                case 542569478: goto L_0x0073;
                case 725957860: goto L_0x0067;
                case 738597099: goto L_0x005b;
                case 855502857: goto L_0x0050;
                case 1422270023: goto L_0x0044;
                case 1809237540: goto L_0x0039;
                case 1950749482: goto L_0x002d;
                case 1950789798: goto L_0x0021;
                case 1951062397: goto L_0x0015;
                default: goto L_0x0013;
            };
        L_0x0013:
            goto L_0x0160;
        L_0x0015:
            r2 = "A_OPUS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x001d:
            r1 = 11;
            goto L_0x0161;
        L_0x0021:
            r2 = "A_FLAC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0029:
            r1 = 21;
            goto L_0x0161;
        L_0x002d:
            r2 = "A_EAC3";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0035:
            r1 = 16;
            goto L_0x0161;
        L_0x0039:
            r2 = "V_MPEG2";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0041:
            r1 = 2;
            goto L_0x0161;
        L_0x0044:
            r2 = "S_TEXT/UTF8";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x004c:
            r1 = 24;
            goto L_0x0161;
        L_0x0050:
            r2 = "V_MPEGH/ISO/HEVC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0058:
            r1 = 7;
            goto L_0x0161;
        L_0x005b:
            r2 = "S_TEXT/ASS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0063:
            r1 = 25;
            goto L_0x0161;
        L_0x0067:
            r2 = "A_PCM/INT/LIT";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x006f:
            r1 = 23;
            goto L_0x0161;
        L_0x0073:
            r2 = "A_DTS/EXPRESS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x007b:
            r1 = 19;
            goto L_0x0161;
        L_0x007f:
            r2 = "V_THEORA";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0087:
            r1 = 9;
            goto L_0x0161;
        L_0x008b:
            r2 = "S_HDMV/PGS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0093:
            r1 = 27;
            goto L_0x0161;
        L_0x0097:
            r2 = "V_VP9";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x009f:
            r1 = 1;
            goto L_0x0161;
        L_0x00a2:
            r2 = "V_VP8";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x00aa:
            r1 = 0;
            goto L_0x0161;
        L_0x00ad:
            r2 = "A_DTS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x00b5:
            r1 = 18;
            goto L_0x0161;
        L_0x00b9:
            r2 = "A_AC3";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x00c1:
            r1 = 15;
            goto L_0x0161;
        L_0x00c5:
            r2 = "A_AAC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x00cd:
            r1 = 12;
            goto L_0x0161;
        L_0x00d1:
            r2 = "A_DTS/LOSSLESS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x00d9:
            r1 = 20;
            goto L_0x0161;
        L_0x00dd:
            r2 = "S_VOBSUB";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x00e5:
            r1 = 26;
            goto L_0x0161;
        L_0x00e9:
            r2 = "V_MPEG4/ISO/AVC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x00f1:
            r1 = 6;
            goto L_0x0161;
        L_0x00f4:
            r2 = "V_MPEG4/ISO/ASP";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x00fc:
            r1 = 4;
            goto L_0x0161;
        L_0x00ff:
            r2 = "S_DVBSUB";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0107:
            r1 = 28;
            goto L_0x0161;
        L_0x010a:
            r2 = "V_MS/VFW/FOURCC";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0112:
            r1 = 8;
            goto L_0x0161;
        L_0x0115:
            r2 = "A_MPEG/L3";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x011d:
            r1 = 14;
            goto L_0x0161;
        L_0x0120:
            r2 = "A_MPEG/L2";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0128:
            r1 = 13;
            goto L_0x0161;
        L_0x012b:
            r2 = "A_VORBIS";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0133:
            r1 = 10;
            goto L_0x0161;
        L_0x0136:
            r2 = "A_TRUEHD";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x013e:
            r1 = 17;
            goto L_0x0161;
        L_0x0141:
            r2 = "A_MS/ACM";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0149:
            r1 = 22;
            goto L_0x0161;
        L_0x014c:
            r2 = "V_MPEG4/ISO/SP";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x0154:
            r1 = 3;
            goto L_0x0161;
        L_0x0156:
            r2 = "V_MPEG4/ISO/AP";
            r1 = r1.equals(r2);
            if (r1 == 0) goto L_0x0160;
        L_0x015e:
            r1 = 5;
            goto L_0x0161;
        L_0x0160:
            r1 = -1;
        L_0x0161:
            r2 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
            r10 = 0;
            switch(r1) {
                case 0: goto L_0x031e;
                case 1: goto L_0x031b;
                case 2: goto L_0x0318;
                case 3: goto L_0x0307;
                case 4: goto L_0x0307;
                case 5: goto L_0x0307;
                case 6: goto L_0x02f1;
                case 7: goto L_0x02dd;
                case 8: goto L_0x02c4;
                case 9: goto L_0x02c1;
                case 10: goto L_0x02b1;
                case 11: goto L_0x026b;
                case 12: goto L_0x0260;
                case 13: goto L_0x0258;
                case 14: goto L_0x0255;
                case 15: goto L_0x0251;
                case 16: goto L_0x024d;
                case 17: goto L_0x0242;
                case 18: goto L_0x023e;
                case 19: goto L_0x023e;
                case 20: goto L_0x023a;
                case 21: goto L_0x0231;
                case 22: goto L_0x01dc;
                case 23: goto L_0x01a7;
                case 24: goto L_0x01a3;
                case 25: goto L_0x019f;
                case 26: goto L_0x0195;
                case 27: goto L_0x0191;
                case 28: goto L_0x016f;
                default: goto L_0x0167;
            };
        L_0x0167:
            r1 = new com.google.android.exoplayer2.ParserException;
            r2 = "Unrecognized codec identifier.";
            r1.<init>(r2);
            throw r1;
        L_0x016f:
            r1 = "application/dvbsubs";
            r2 = new byte[r3];
            r3 = r0.h;
            r3 = r3[r6];
            r2[r6] = r3;
            r3 = r0.h;
            r3 = r3[r5];
            r2[r5] = r3;
            r3 = r0.h;
            r3 = r3[r7];
            r2[r7] = r3;
            r3 = r0.h;
            r3 = r3[r8];
            r2[r8] = r3;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0268;
        L_0x0191:
            r1 = "application/pgs";
            goto L_0x0320;
        L_0x0195:
            r1 = "application/vobsub";
            r2 = r0.h;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0268;
        L_0x019f:
            r1 = "text/x-ssa";
            goto L_0x0320;
        L_0x01a3:
            r1 = "application/x-subrip";
            goto L_0x0320;
        L_0x01a7:
            r1 = "audio/raw";
            r2 = r0.H;
            r2 = com.google.android.exoplayer2.util.z.b(r2);
            if (r2 != 0) goto L_0x01d5;
        L_0x01b1:
            r1 = "audio/x-unknown";
            r2 = "MatroskaExtractor";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Unsupported PCM bit depth: ";
            r3.append(r4);
            r4 = r0.H;
            r3.append(r4);
            r4 = ". Setting mimeType to ";
            r3.append(r4);
            r3.append(r1);
            r3 = r3.toString();
            android.util.Log.w(r2, r3);
            goto L_0x0320;
        L_0x01d5:
            r12 = r1;
            r18 = r2;
            r2 = r10;
            r15 = -1;
            goto L_0x0325;
        L_0x01dc:
            r1 = "audio/raw";
            r2 = new com.google.android.exoplayer2.util.n;
            r3 = r0.h;
            r2.<init>(r3);
            r2 = defpackage.ho.b.b(r2);
            if (r2 == 0) goto L_0x0217;
        L_0x01eb:
            r2 = r0.H;
            r2 = com.google.android.exoplayer2.util.z.b(r2);
            if (r2 != 0) goto L_0x01d5;
        L_0x01f3:
            r1 = "audio/x-unknown";
            r2 = "MatroskaExtractor";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Unsupported PCM bit depth: ";
            r3.append(r4);
            r4 = r0.H;
            r3.append(r4);
            r4 = ". Setting mimeType to ";
            r3.append(r4);
            r3.append(r1);
            r3 = r3.toString();
            android.util.Log.w(r2, r3);
            goto L_0x0320;
        L_0x0217:
            r1 = "audio/x-unknown";
            r2 = "MatroskaExtractor";
            r3 = new java.lang.StringBuilder;
            r3.<init>();
            r4 = "Non-PCM MS/ACM is unsupported. Setting mimeType to ";
            r3.append(r4);
            r3.append(r1);
            r3 = r3.toString();
            android.util.Log.w(r2, r3);
            goto L_0x0320;
        L_0x0231:
            r1 = "audio/flac";
            r2 = r0.h;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0268;
        L_0x023a:
            r1 = "audio/vnd.dts.hd";
            goto L_0x0320;
        L_0x023e:
            r1 = "audio/vnd.dts";
            goto L_0x0320;
        L_0x0242:
            r1 = "audio/true-hd";
            r2 = new ho$c;
            r2.<init>();
            r0.L = r2;
            goto L_0x0320;
        L_0x024d:
            r1 = "audio/eac3";
            goto L_0x0320;
        L_0x0251:
            r1 = "audio/ac3";
            goto L_0x0320;
        L_0x0255:
            r1 = "audio/mpeg";
            goto L_0x025a;
        L_0x0258:
            r1 = "audio/mpeg-L2";
        L_0x025a:
            r12 = r1;
            r2 = r10;
            r15 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
            goto L_0x0323;
        L_0x0260:
            r1 = "audio/mp4a-latm";
            r2 = r0.h;
            r2 = java.util.Collections.singletonList(r2);
        L_0x0268:
            r12 = r1;
            goto L_0x0322;
        L_0x026b:
            r1 = "audio/opus";
            r2 = 5760; // 0x1680 float:8.071E-42 double:2.846E-320;
            r3 = new java.util.ArrayList;
            r3.<init>(r8);
            r11 = r0.h;
            r3.add(r11);
            r11 = java.nio.ByteBuffer.allocate(r4);
            r12 = java.nio.ByteOrder.nativeOrder();
            r11 = r11.order(r12);
            r12 = r0.J;
            r11 = r11.putLong(r12);
            r11 = r11.array();
            r3.add(r11);
            r4 = java.nio.ByteBuffer.allocate(r4);
            r11 = java.nio.ByteOrder.nativeOrder();
            r4 = r4.order(r11);
            r11 = r0.K;
            r4 = r4.putLong(r11);
            r4 = r4.array();
            r3.add(r4);
            r12 = r1;
            r2 = r3;
            r15 = 5760; // 0x1680 float:8.071E-42 double:2.846E-320;
            goto L_0x0323;
        L_0x02b1:
            r1 = "audio/vorbis";
            r2 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
            r3 = r0.h;
            r3 = defpackage.ho.b.a(r3);
            r12 = r1;
            r2 = r3;
            r15 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
            goto L_0x0323;
        L_0x02c1:
            r1 = "video/x-unknown";
            goto L_0x0320;
        L_0x02c4:
            r1 = new com.google.android.exoplayer2.util.n;
            r2 = r0.h;
            r1.<init>(r2);
            r1 = defpackage.ho.b.a(r1);
            r2 = r1.first;
            r2 = (java.lang.String) r2;
            r1 = r1.second;
            r1 = (java.util.List) r1;
            r12 = r2;
            r15 = -1;
            r18 = -1;
            r2 = r1;
            goto L_0x0325;
        L_0x02dd:
            r1 = "video/hevc";
            r2 = new com.google.android.exoplayer2.util.n;
            r3 = r0.h;
            r2.<init>(r3);
            r2 = com.google.android.exoplayer2.video.b.a(r2);
            r3 = r2.a;
            r2 = r2.b;
            r0.P = r2;
            goto L_0x0304;
        L_0x02f1:
            r1 = "video/avc";
            r2 = new com.google.android.exoplayer2.util.n;
            r3 = r0.h;
            r2.<init>(r3);
            r2 = com.google.android.exoplayer2.video.a.a(r2);
            r3 = r2.a;
            r2 = r2.b;
            r0.P = r2;
        L_0x0304:
            r12 = r1;
            r2 = r3;
            goto L_0x0322;
        L_0x0307:
            r1 = "video/mp4v-es";
            r2 = r0.h;
            if (r2 != 0) goto L_0x0310;
        L_0x030d:
            r2 = r10;
            goto L_0x0268;
        L_0x0310:
            r2 = r0.h;
            r2 = java.util.Collections.singletonList(r2);
            goto L_0x0268;
        L_0x0318:
            r1 = "video/mpeg2";
            goto L_0x0320;
        L_0x031b:
            r1 = "video/x-vnd.on2.vp9";
            goto L_0x0320;
        L_0x031e:
            r1 = "video/x-vnd.on2.vp8";
        L_0x0320:
            r12 = r1;
            r2 = r10;
        L_0x0322:
            r15 = -1;
        L_0x0323:
            r18 = -1;
        L_0x0325:
            r1 = r0.N;
            r1 = r1 | r6;
            r3 = r0.M;
            if (r3 == 0) goto L_0x032d;
        L_0x032c:
            r6 = 2;
        L_0x032d:
            r1 = r1 | r6;
            r3 = com.google.android.exoplayer2.util.k.a(r12);
            if (r3 == 0) goto L_0x0355;
        L_0x0334:
            r11 = java.lang.Integer.toString(r28);
            r13 = 0;
            r14 = -1;
            r3 = r0.G;
            r4 = r0.I;
            r6 = r0.i;
            r7 = r0.Q;
            r16 = r3;
            r17 = r4;
            r19 = r2;
            r20 = r6;
            r21 = r1;
            r22 = r7;
            r1 = com.google.android.exoplayer2.Format.a(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22);
            r8 = 1;
            goto L_0x0451;
        L_0x0355:
            r3 = com.google.android.exoplayer2.util.k.b(r12);
            if (r3 == 0) goto L_0x03cf;
        L_0x035b:
            r1 = r0.n;
            if (r1 != 0) goto L_0x0375;
        L_0x035f:
            r1 = r0.l;
            if (r1 != r9) goto L_0x0366;
        L_0x0363:
            r1 = r0.j;
            goto L_0x0368;
        L_0x0366:
            r1 = r0.l;
        L_0x0368:
            r0.l = r1;
            r1 = r0.m;
            if (r1 != r9) goto L_0x0371;
        L_0x036e:
            r1 = r0.k;
            goto L_0x0373;
        L_0x0371:
            r1 = r0.m;
        L_0x0373:
            r0.m = r1;
        L_0x0375:
            r1 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r3 = r0.l;
            if (r3 == r9) goto L_0x0391;
        L_0x037b:
            r3 = r0.m;
            if (r3 == r9) goto L_0x0391;
        L_0x037f:
            r1 = r0.k;
            r3 = r0.l;
            r1 = r1 * r3;
            r1 = (float) r1;
            r3 = r0.j;
            r4 = r0.m;
            r3 = r3 * r4;
            r3 = (float) r3;
            r1 = r1 / r3;
            r21 = r1;
            goto L_0x0393;
        L_0x0391:
            r21 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        L_0x0393:
            r1 = r0.q;
            if (r1 == 0) goto L_0x03a6;
        L_0x0397:
            r1 = r26.c();
            r10 = new com.google.android.exoplayer2.video.ColorInfo;
            r3 = r0.r;
            r4 = r0.t;
            r5 = r0.s;
            r10.<init>(r3, r4, r5, r1);
        L_0x03a6:
            r24 = r10;
            r11 = java.lang.Integer.toString(r28);
            r13 = 0;
            r14 = -1;
            r1 = r0.j;
            r3 = r0.k;
            r18 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
            r20 = -1;
            r4 = r0.o;
            r5 = r0.p;
            r6 = r0.i;
            r16 = r1;
            r17 = r3;
            r19 = r2;
            r22 = r4;
            r23 = r5;
            r25 = r6;
            r1 = com.google.android.exoplayer2.Format.a(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25);
            r8 = 2;
            goto L_0x0451;
        L_0x03cf:
            r3 = "application/x-subrip";
            r3 = r3.equals(r12);
            if (r3 == 0) goto L_0x03e5;
        L_0x03d7:
            r2 = java.lang.Integer.toString(r28);
            r3 = r0.Q;
            r4 = r0.i;
            r1 = com.google.android.exoplayer2.Format.a(r2, r12, r1, r3, r4);
            goto L_0x0451;
        L_0x03e5:
            r3 = "text/x-ssa";
            r3 = r3.equals(r12);
            if (r3 == 0) goto L_0x041b;
        L_0x03ed:
            r2 = new java.util.ArrayList;
            r2.<init>(r7);
            r3 = defpackage.ho.d;
            r2.add(r3);
            r3 = r0.h;
            r2.add(r3);
            r11 = java.lang.Integer.toString(r28);
            r13 = 0;
            r14 = -1;
            r3 = r0.Q;
            r17 = -1;
            r4 = r0.i;
            r19 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r15 = r1;
            r16 = r3;
            r18 = r4;
            r21 = r2;
            r1 = com.google.android.exoplayer2.Format.a(r11, r12, r13, r14, r15, r16, r17, r18, r19, r21);
            goto L_0x0451;
        L_0x041b:
            r3 = "application/vobsub";
            r3 = r3.equals(r12);
            if (r3 != 0) goto L_0x043c;
        L_0x0423:
            r3 = "application/pgs";
            r3 = r3.equals(r12);
            if (r3 != 0) goto L_0x043c;
        L_0x042b:
            r3 = "application/dvbsubs";
            r3 = r3.equals(r12);
            if (r3 == 0) goto L_0x0434;
        L_0x0433:
            goto L_0x043c;
        L_0x0434:
            r1 = new com.google.android.exoplayer2.ParserException;
            r2 = "Unexpected MIME type.";
            r1.<init>(r2);
            throw r1;
        L_0x043c:
            r11 = java.lang.Integer.toString(r28);
            r13 = 0;
            r14 = -1;
            r3 = r0.Q;
            r4 = r0.i;
            r15 = r1;
            r16 = r2;
            r17 = r3;
            r18 = r4;
            r1 = com.google.android.exoplayer2.Format.a(r11, r12, r13, r14, r15, r16, r17, r18);
        L_0x0451:
            r2 = r0.b;
            r3 = r27;
            r2 = r3.a(r2, r8);
            r0.O = r2;
            r2 = r0.O;
            r2.a(r1);
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.ho$b.a(hb, int):void");
        }

        public void a() {
            if (this.L != null) {
                this.L.a(this);
            }
        }

        public void b() {
            if (this.L != null) {
                this.L.a();
            }
        }

        private byte[] c() {
            if (this.w == -1.0f || this.x == -1.0f || this.y == -1.0f || this.z == -1.0f || this.A == -1.0f || this.B == -1.0f || this.C == -1.0f || this.D == -1.0f || this.E == -1.0f || this.F == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.put((byte) 0);
            wrap.putShort((short) ((int) ((this.w * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.x * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.y * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.z * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.A * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.B * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.C * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.D * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) (this.E + 0.5f)));
            wrap.putShort((short) ((int) (this.F + 0.5f)));
            wrap.putShort((short) this.u);
            wrap.putShort((short) this.v);
            return bArr;
        }

        private static Pair<String, List<byte[]>> a(n nVar) throws ParserException {
            try {
                nVar.d(16);
                long n = nVar.n();
                if (n == 1482049860) {
                    return new Pair("video/3gpp", null);
                }
                if (n == 826496599) {
                    int d = nVar.d() + 20;
                    byte[] bArr = nVar.a;
                    while (d < bArr.length - 4) {
                        if (bArr[d] == (byte) 0 && bArr[d + 1] == (byte) 0 && bArr[d + 2] == (byte) 1 && bArr[d + 3] == (byte) 15) {
                            return new Pair("video/wvc1", Collections.singletonList(Arrays.copyOfRange(bArr, d, bArr.length)));
                        }
                        d++;
                    }
                    throw new ParserException("Failed to find FourCC VC1 initialization data");
                }
                Log.w("MatroskaExtractor", "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair("video/x-unknown", null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC private data");
            }
        }

        private static List<byte[]> a(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] == (byte) 2) {
                    int i = 1;
                    int i2 = 0;
                    while (bArr[i] == (byte) -1) {
                        i2 += 255;
                        i++;
                    }
                    int i3 = i + 1;
                    i2 += bArr[i];
                    i = 0;
                    while (bArr[i3] == (byte) -1) {
                        i += 255;
                        i3++;
                    }
                    int i4 = i3 + 1;
                    i += bArr[i3];
                    if (bArr[i4] == (byte) 1) {
                        byte[] bArr2 = new byte[i2];
                        System.arraycopy(bArr, i4, bArr2, 0, i2);
                        i4 += i2;
                        if (bArr[i4] == (byte) 3) {
                            i4 += i;
                            if (bArr[i4] == (byte) 5) {
                                byte[] bArr3 = new byte[(bArr.length - i4)];
                                System.arraycopy(bArr, i4, bArr3, 0, bArr.length - i4);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw new ParserException("Error parsing vorbis codec private");
                        }
                        throw new ParserException("Error parsing vorbis codec private");
                    }
                    throw new ParserException("Error parsing vorbis codec private");
                }
                throw new ParserException("Error parsing vorbis codec private");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean b(n nVar) throws ParserException {
            try {
                int i = nVar.i();
                boolean z = true;
                if (i == 1) {
                    return true;
                }
                if (i != 65534) {
                    return false;
                }
                nVar.c(24);
                if (!(nVar.q() == ho.g.getMostSignificantBits() && nVar.q() == ho.g.getLeastSignificantBits())) {
                    z = false;
                }
                return z;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }

    /* compiled from: MatroskaExtractor */
    /* renamed from: ho$c */
    private static final class c {
        private final byte[] a = new byte[10];
        private boolean b;
        private int c;
        private int d;
        private long e;
        private int f;

        public void a() {
            this.b = false;
        }

        public void a(ha haVar, int i, int i2) throws IOException, InterruptedException {
            if (!this.b) {
                haVar.c(this.a, 0, 10);
                haVar.a();
                if (com.google.android.exoplayer2.audio.a.b(this.a) != -1) {
                    this.b = true;
                    this.c = 0;
                } else {
                    return;
                }
            }
            if (this.c == 0) {
                this.f = i;
                this.d = 0;
            }
            this.d += i2;
        }

        public void a(b bVar, long j) {
            if (this.b) {
                int i = this.c;
                this.c = i + 1;
                if (i == 0) {
                    this.e = j;
                }
                if (this.c >= 16) {
                    bVar.O.a(this.e, this.f, this.d, 0, bVar.g);
                    this.c = 0;
                }
            }
        }

        public void a(b bVar) {
            if (this.b && this.c > 0) {
                bVar.O.a(this.e, this.f, this.d, 0, bVar.g);
                this.c = 0;
            }
        }
    }

    /* compiled from: MatroskaExtractor */
    /* renamed from: ho$1 */
    static class 1 implements hc {
        1() {
        }

        public gz[] a() {
            return new gz[]{new ho()};
        }
    }

    /* compiled from: MatroskaExtractor */
    /* renamed from: ho$a */
    private final class a implements hn {
        public int a(int i) {
            switch (i) {
                case 131:
                case 136:
                case 155:
                case 159:
                case 176:
                case 179:
                case 186:
                case 215:
                case 231:
                case 241:
                case 251:
                case 16980:
                case 17029:
                case 17143:
                case 18401:
                case 18408:
                case 20529:
                case 20530:
                case 21420:
                case 21432:
                case 21680:
                case 21682:
                case 21690:
                case 21930:
                case 21945:
                case 21946:
                case 21947:
                case 21948:
                case 21949:
                case 22186:
                case 22203:
                case 25188:
                case 2352003:
                case 2807729:
                    return 2;
                case 134:
                case 17026:
                case 2274716:
                    return 3;
                case 160:
                case 174:
                case 183:
                case 187:
                case 224:
                case 225:
                case 18407:
                case 19899:
                case 20532:
                case 20533:
                case 21936:
                case 21968:
                case 25152:
                case 28032:
                case 30320:
                case 290298740:
                case 357149030:
                case 374648427:
                case 408125543:
                case 440786851:
                case 475249515:
                case 524531317:
                    return 1;
                case 161:
                case 163:
                case 16981:
                case 18402:
                case 21419:
                case 25506:
                case 30322:
                    return 4;
                case 181:
                case 17545:
                case 21969:
                case 21970:
                case 21971:
                case 21972:
                case 21973:
                case 21974:
                case 21975:
                case 21976:
                case 21977:
                case 21978:
                    return 5;
                default:
                    return 0;
            }
        }

        public boolean b(int i) {
            return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
        }

        private a() {
        }

        /* synthetic */ a(ho hoVar, 1 1) {
            this();
        }

        public void a(int i, long j, long j2) throws ParserException {
            ho.this.a(i, j, j2);
        }

        public void c(int i) throws ParserException {
            ho.this.a(i);
        }

        public void a(int i, long j) throws ParserException {
            ho.this.a(i, j);
        }

        public void a(int i, double d) throws ParserException {
            ho.this.a(i, d);
        }

        public void a(int i, String str) throws ParserException {
            ho.this.a(i, str);
        }

        public void a(int i, int i2, ha haVar) throws IOException, InterruptedException {
            ho.this.a(i, i2, haVar);
        }
    }

    public void c() {
    }

    public ho() {
        this(0);
    }

    public ho(int i) {
        this(new hl(), i);
    }

    ho(hm hmVar, int i) {
        this.w = -1;
        this.x = -9223372036854775807L;
        this.y = -9223372036854775807L;
        this.z = -9223372036854775807L;
        this.F = -1;
        this.G = -1;
        this.H = -9223372036854775807L;
        this.h = hmVar;
        this.h.a(new a(this, null));
        boolean z = true;
        if ((i & 1) != 0) {
            z = false;
        }
        this.k = z;
        this.i = new hq();
        this.j = new SparseArray();
        this.n = new n(4);
        this.o = new n(ByteBuffer.allocate(4).putInt(-1).array());
        this.p = new n(4);
        this.l = new n(l.a);
        this.m = new n(4);
        this.q = new n();
        this.r = new n();
        this.s = new n(8);
        this.t = new n();
    }

    public boolean a(ha haVar) throws IOException, InterruptedException {
        return new hp().a(haVar);
    }

    public void a(hb hbVar) {
        this.af = hbVar;
    }

    public void a(long j, long j2) {
        this.H = -9223372036854775807L;
        int i = 0;
        this.L = 0;
        this.h.a();
        this.i.a();
        d();
        while (i < this.j.size()) {
            ((b) this.j.valueAt(i)).b();
            i++;
        }
    }

    public int a(ha haVar, hg hgVar) throws IOException, InterruptedException {
        int i = 0;
        this.ad = false;
        boolean z = true;
        while (z && !this.ad) {
            z = this.h.a(haVar);
            if (z && a(hgVar, haVar.c())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        while (i < this.j.size()) {
            ((b) this.j.valueAt(i)).a();
            i++;
        }
        return -1;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, long j, long j2) throws ParserException {
        if (i == 160) {
            this.ae = false;
        } else if (i == 174) {
            this.A = new b();
        } else if (i == 187) {
            this.K = false;
        } else if (i == 19899) {
            this.C = -1;
            this.D = -1;
        } else if (i == 20533) {
            this.A.e = true;
        } else if (i == 21968) {
            this.A.q = true;
        } else if (i == 25152) {
        } else {
            if (i != 408125543) {
                if (i == 475249515) {
                    this.I = new i();
                    this.J = new i();
                } else if (i != 524531317 || this.B) {
                } else {
                    if (!this.k || this.F == -1) {
                        this.af.a(new b(this.z));
                        this.B = true;
                        return;
                    }
                    this.E = true;
                }
            } else if (this.w == -1 || this.w == j) {
                this.w = j;
                this.v = j2;
            } else {
                throw new ParserException("Multiple Segment elements not supported");
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i) throws ParserException {
        if (i != 160) {
            if (i == 174) {
                if (ho.a(this.A.a)) {
                    this.A.a(this.af, this.A.b);
                    this.j.put(this.A.b, this.A);
                }
                this.A = null;
            } else if (i != 19899) {
                if (i != 25152) {
                    if (i != 28032) {
                        if (i == 357149030) {
                            if (this.x == -9223372036854775807L) {
                                this.x = 1000000;
                            }
                            if (this.y != -9223372036854775807L) {
                                this.z = a(this.y);
                            }
                        } else if (i != 374648427) {
                            if (i == 475249515 && !this.B) {
                                this.af.a(e());
                                this.B = true;
                            }
                        } else if (this.j.size() != 0) {
                            this.af.a();
                        } else {
                            throw new ParserException("No valid tracks were found");
                        }
                    } else if (this.A.e && this.A.f != null) {
                        throw new ParserException("Combining encryption and compression is not supported");
                    }
                } else if (this.A.e) {
                    if (this.A.g != null) {
                        this.A.i = new DrmInitData(new SchemeData[]{new SchemeData(com.google.android.exoplayer2.b.b, "video/webm", this.A.g.b)});
                    } else {
                        throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
                    }
                }
            } else if (this.C == -1 || this.D == -1) {
                throw new ParserException("Mandatory element SeekID or SeekPosition not found");
            } else if (this.C == 475249515) {
                this.F = this.D;
            }
        } else if (this.L == 2) {
            if (!this.ae) {
                this.T |= 1;
            }
            a((b) this.j.get(this.R), this.M);
            this.L = 0;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, long j) throws ParserException {
        boolean z = false;
        b bVar;
        StringBuilder stringBuilder;
        switch (i) {
            case 131:
                this.A.c = (int) j;
                return;
            case 136:
                bVar = this.A;
                if (j == 1) {
                    z = true;
                }
                bVar.N = z;
                return;
            case 155:
                this.N = a(j);
                return;
            case 159:
                this.A.G = (int) j;
                return;
            case 176:
                this.A.j = (int) j;
                return;
            case 179:
                this.I.a(a(j));
                return;
            case 186:
                this.A.k = (int) j;
                return;
            case 215:
                this.A.b = (int) j;
                return;
            case 231:
                this.H = a(j);
                return;
            case 241:
                if (!this.K) {
                    this.J.a(j);
                    this.K = true;
                    return;
                }
                return;
            case 251:
                this.ae = true;
                return;
            case 16980:
                if (j != 3) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ContentCompAlgo ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case 17029:
                if (j < 1 || j > 2) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("DocTypeReadVersion ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case 17143:
                if (j != 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("EBMLReadVersion ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case 18401:
                if (j != 5) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ContentEncAlgo ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case 18408:
                if (j != 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("AESSettingsCipherMode ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case 20529:
                if (j != 0) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ContentEncodingOrder ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case 20530:
                if (j != 1) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("ContentEncodingScope ");
                    stringBuilder.append(j);
                    stringBuilder.append(" not supported");
                    throw new ParserException(stringBuilder.toString());
                }
                return;
            case 21420:
                this.D = j + this.w;
                return;
            case 21432:
                i = (int) j;
                if (i == 3) {
                    this.A.p = 1;
                    return;
                } else if (i != 15) {
                    switch (i) {
                        case 0:
                            this.A.p = 0;
                            return;
                        case 1:
                            this.A.p = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.A.p = 3;
                    return;
                }
            case 21680:
                this.A.l = (int) j;
                return;
            case 21682:
                this.A.n = (int) j;
                return;
            case 21690:
                this.A.m = (int) j;
                return;
            case 21930:
                bVar = this.A;
                if (j == 1) {
                    z = true;
                }
                bVar.M = z;
                return;
            case 21945:
                switch ((int) j) {
                    case 1:
                        this.A.t = 2;
                        return;
                    case 2:
                        this.A.t = 1;
                        return;
                    default:
                        return;
                }
            case 21946:
                i = (int) j;
                if (i != 1) {
                    if (i == 16) {
                        this.A.s = 6;
                        return;
                    } else if (i != 18) {
                        switch (i) {
                            case 6:
                            case 7:
                                break;
                            default:
                                return;
                        }
                    } else {
                        this.A.s = 7;
                        return;
                    }
                }
                this.A.s = 3;
                return;
            case 21947:
                this.A.q = true;
                i = (int) j;
                if (i == 1) {
                    this.A.r = 1;
                    return;
                } else if (i != 9) {
                    switch (i) {
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            this.A.r = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.A.r = 6;
                    return;
                }
            case 21948:
                this.A.u = (int) j;
                return;
            case 21949:
                this.A.v = (int) j;
                return;
            case 22186:
                this.A.J = j;
                return;
            case 22203:
                this.A.K = j;
                return;
            case 25188:
                this.A.H = (int) j;
                return;
            case 2352003:
                this.A.d = (int) j;
                return;
            case 2807729:
                this.x = j;
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, double d) {
        if (i == 181) {
            this.A.I = (int) d;
        } else if (i != 17545) {
            switch (i) {
                case 21969:
                    this.A.w = (float) d;
                    return;
                case 21970:
                    this.A.x = (float) d;
                    return;
                case 21971:
                    this.A.y = (float) d;
                    return;
                case 21972:
                    this.A.z = (float) d;
                    return;
                case 21973:
                    this.A.A = (float) d;
                    return;
                case 21974:
                    this.A.B = (float) d;
                    return;
                case 21975:
                    this.A.C = (float) d;
                    return;
                case 21976:
                    this.A.D = (float) d;
                    return;
                case 21977:
                    this.A.E = (float) d;
                    return;
                case 21978:
                    this.A.F = (float) d;
                    return;
                default:
                    return;
            }
        } else {
            this.y = (long) d;
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, String str) throws ParserException {
        if (i == 134) {
            this.A.a = str;
        } else if (i != 17026) {
            if (i == 2274716) {
                this.A.Q = str;
            }
        } else if (!"webm".equals(str) && !"matroska".equals(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("DocType ");
            stringBuilder.append(str);
            stringBuilder.append(" not supported");
            throw new ParserException(stringBuilder.toString());
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(int i, int i2, ha haVar) throws IOException, InterruptedException {
        int i3 = i;
        int i4 = i2;
        ha haVar2 = haVar;
        int i5 = 0;
        int i6 = 1;
        if (i3 == 161 || i3 == 163) {
            if (this.L == 0) {
                this.R = (int) this.i.a(haVar2, false, true, 8);
                this.S = this.i.b();
                this.N = -9223372036854775807L;
                this.L = 1;
                this.n.a();
            }
            b bVar = (b) this.j.get(this.R);
            if (bVar == null) {
                haVar2.b(i4 - this.S);
                this.L = 0;
                return;
            }
            if (this.L == 1) {
                int i7;
                a(haVar2, 3);
                int i8 = (this.n.a[2] & 6) >> 1;
                int i9 = 255;
                if (i8 == 0) {
                    this.P = 1;
                    this.Q = ho.a(this.Q, 1);
                    this.Q[0] = (i4 - this.S) - 3;
                } else if (i3 == 163) {
                    a(haVar2, 4);
                    this.P = (this.n.a[3] & 255) + 1;
                    this.Q = ho.a(this.Q, this.P);
                    int i10;
                    int i11;
                    int i12;
                    if (i8 == 2) {
                        Arrays.fill(this.Q, 0, this.P, ((i4 - this.S) - 4) / this.P);
                    } else if (i8 == 1) {
                        i10 = 4;
                        i8 = 0;
                        for (i11 = 0; i11 < this.P - 1; i11++) {
                            this.Q[i11] = 0;
                            do {
                                i10++;
                                a(haVar2, i10);
                                i12 = this.n.a[i10 - 1] & 255;
                                int[] iArr = this.Q;
                                iArr[i11] = iArr[i11] + i12;
                            } while (i12 == 255);
                            i8 += this.Q[i11];
                        }
                        this.Q[this.P - 1] = ((i4 - this.S) - i10) - i8;
                    } else if (i8 == 3) {
                        i11 = 0;
                        i10 = 4;
                        i8 = 0;
                        while (i11 < this.P - i6) {
                            this.Q[i11] = i5;
                            i10++;
                            a(haVar2, i10);
                            int i13 = i10 - 1;
                            if (this.n.a[i13] != (byte) 0) {
                                long j;
                                long j2 = 0;
                                i12 = 0;
                                while (i12 < 8) {
                                    int i14 = i6 << (7 - i12);
                                    if ((this.n.a[i13] & i14) != 0) {
                                        i10 += i12;
                                        a(haVar2, i10);
                                        j = (long) ((this.n.a[i13] & i9) & (i14 ^ -1));
                                        i7 = i13 + 1;
                                        while (true) {
                                            j2 = j;
                                            if (i7 >= i10) {
                                                break;
                                            }
                                            j = (j2 << 8) | ((long) (this.n.a[i7] & i9));
                                            i7++;
                                            i9 = 255;
                                        }
                                        if (i11 > 0) {
                                            j2 -= (1 << ((i12 * 7) + 6)) - 1;
                                        }
                                        j = j2;
                                        if (j >= -2147483648L || j > 2147483647L) {
                                            throw new ParserException("EBML lacing sample size out of range.");
                                        }
                                        i7 = (int) j;
                                        int[] iArr2 = this.Q;
                                        if (i11 != 0) {
                                            i7 += this.Q[i11 - 1];
                                        }
                                        iArr2[i11] = i7;
                                        i8 += this.Q[i11];
                                        i11++;
                                        i5 = 0;
                                        i6 = 1;
                                        i9 = 255;
                                    } else {
                                        i12++;
                                        i6 = 1;
                                        i9 = 255;
                                    }
                                }
                                j = j2;
                                if (j >= -2147483648L) {
                                }
                                throw new ParserException("EBML lacing sample size out of range.");
                            }
                            throw new ParserException("No valid varint length mask found");
                        }
                        this.Q[this.P - 1] = ((i4 - this.S) - i10) - i8;
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Unexpected lacing value: ");
                        stringBuilder.append(i8);
                        throw new ParserException(stringBuilder.toString());
                    }
                } else {
                    throw new ParserException("Lacing only supported in SimpleBlocks.");
                }
                this.M = this.H + a((long) ((this.n.a[0] << 8) | (this.n.a[1] & 255)));
                Object obj = (this.n.a[2] & 8) == 8 ? 1 : null;
                i7 = (bVar.c == 2 || (i3 == 163 && (this.n.a[2] & 128) == 128)) ? 1 : 0;
                this.T = i7 | (obj != null ? CheckView.UNCHECKED : 0);
                this.L = 2;
                this.O = 0;
            }
            if (i3 == 163) {
                while (this.O < this.P) {
                    a(haVar2, bVar, this.Q[this.O]);
                    a(bVar, this.M + ((long) ((this.O * bVar.d) / 1000)));
                    this.O++;
                }
                this.L = 0;
            } else {
                a(haVar2, bVar, this.Q[0]);
            }
        } else if (i3 == 16981) {
            this.A.f = new byte[i4];
            haVar2.b(this.A.f, 0, i4);
        } else if (i3 == 18402) {
            byte[] bArr = new byte[i4];
            haVar2.b(bArr, 0, i4);
            this.A.g = new a(1, bArr, 0, 0);
        } else if (i3 == 21419) {
            Arrays.fill(this.p.a, (byte) 0);
            haVar2.b(this.p.a, 4 - i4, i4);
            this.p.c(0);
            this.C = (int) this.p.m();
        } else if (i3 == 25506) {
            this.A.h = new byte[i4];
            haVar2.b(this.A.h, 0, i4);
        } else if (i3 == 30322) {
            this.A.o = new byte[i4];
            haVar2.b(this.A.o, 0, i4);
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Unexpected id: ");
            stringBuilder2.append(i3);
            throw new ParserException(stringBuilder2.toString());
        }
    }

    private void a(b bVar, long j) {
        b bVar2 = bVar;
        if (bVar2.L != null) {
            bVar2.L.a(bVar2, j);
        } else {
            long j2 = j;
            if ("S_TEXT/UTF8".equals(bVar2.a)) {
                a(bVar, "%02d:%02d:%02d,%03d", 19, 1000, c);
            } else if ("S_TEXT/ASS".equals(bVar2.a)) {
                a(bVar, "%01d:%02d:%02d:%02d", 21, 10000, f);
            }
            bVar2.O.a(j, this.T, this.ac, 0, bVar2.g);
        }
        this.ad = true;
        d();
    }

    private void d() {
        this.U = 0;
        this.ac = 0;
        this.ab = 0;
        this.V = false;
        this.W = false;
        this.Y = false;
        this.aa = 0;
        this.Z = (byte) 0;
        this.X = false;
        this.q.a();
    }

    private void a(ha haVar, int i) throws IOException, InterruptedException {
        if (this.n.c() < i) {
            if (this.n.e() < i) {
                this.n.a(Arrays.copyOf(this.n.a, Math.max(this.n.a.length * 2, i)), this.n.c());
            }
            haVar.b(this.n.a, this.n.c(), i - this.n.c());
            this.n.b(i);
        }
    }

    private void a(ha haVar, b bVar, int i) throws IOException, InterruptedException {
        if ("S_TEXT/UTF8".equals(bVar.a)) {
            a(haVar, b, i);
        } else if ("S_TEXT/ASS".equals(bVar.a)) {
            a(haVar, e, i);
        } else {
            hj hjVar = bVar.O;
            boolean z = true;
            if (!this.V) {
                if (bVar.e) {
                    this.T &= -1073741825;
                    int i2 = 128;
                    if (!this.W) {
                        haVar.b(this.n.a, 0, 1);
                        this.U++;
                        if ((this.n.a[0] & 128) != 128) {
                            this.Z = this.n.a[0];
                            this.W = true;
                        } else {
                            throw new ParserException("Extension bit is set in signal byte");
                        }
                    }
                    if (((this.Z & 1) == 1 ? 1 : null) != null) {
                        Object obj = (this.Z & 2) == 2 ? 1 : null;
                        this.T |= 1073741824;
                        if (!this.X) {
                            haVar.b(this.s.a, 0, 8);
                            this.U += 8;
                            this.X = true;
                            byte[] bArr = this.n.a;
                            if (obj == null) {
                                i2 = 0;
                            }
                            bArr[0] = (byte) (i2 | 8);
                            this.n.c(0);
                            hjVar.a(this.n, 1);
                            this.ac++;
                            this.s.c(0);
                            hjVar.a(this.s, 8);
                            this.ac += 8;
                        }
                        if (obj != null) {
                            if (!this.Y) {
                                haVar.b(this.n.a, 0, 1);
                                this.U++;
                                this.n.c(0);
                                this.aa = this.n.g();
                                this.Y = true;
                            }
                            int i3 = this.aa * 4;
                            this.n.a(i3);
                            haVar.b(this.n.a, 0, i3);
                            this.U += i3;
                            short s = (short) ((this.aa / 2) + 1);
                            i2 = (s * 6) + 2;
                            if (this.u == null || this.u.capacity() < i2) {
                                this.u = ByteBuffer.allocate(i2);
                            }
                            this.u.position(0);
                            this.u.putShort(s);
                            i3 = 0;
                            int i4 = 0;
                            while (i3 < this.aa) {
                                int u = this.n.u();
                                if (i3 % 2 == 0) {
                                    this.u.putShort((short) (u - i4));
                                } else {
                                    this.u.putInt(u - i4);
                                }
                                i3++;
                                i4 = u;
                            }
                            i3 = (i - this.U) - i4;
                            if (this.aa % 2 == 1) {
                                this.u.putInt(i3);
                            } else {
                                this.u.putShort((short) i3);
                                this.u.putInt(0);
                            }
                            this.t.a(this.u.array(), i2);
                            hjVar.a(this.t, i2);
                            this.ac += i2;
                        }
                    }
                } else if (bVar.f != null) {
                    this.q.a(bVar.f, bVar.f.length);
                }
                this.V = true;
            }
            i += this.q.c();
            if ("V_MPEG4/ISO/AVC".equals(bVar.a) || "V_MPEGH/ISO/HEVC".equals(bVar.a)) {
                byte[] bArr2 = this.m.a;
                bArr2[0] = (byte) 0;
                bArr2[1] = (byte) 0;
                bArr2[2] = (byte) 0;
                int i5 = bVar.P;
                int i6 = 4 - bVar.P;
                while (this.U < i) {
                    if (this.ab == 0) {
                        a(haVar, bArr2, i6, i5);
                        this.m.c(0);
                        this.ab = this.m.u();
                        this.l.c(0);
                        hjVar.a(this.l, 4);
                        this.ac += 4;
                    } else {
                        this.ab -= a(haVar, hjVar, this.ab);
                    }
                }
            } else {
                if (bVar.L != null) {
                    if (this.q.c() != 0) {
                        z = false;
                    }
                    com.google.android.exoplayer2.util.a.b(z);
                    bVar.L.a(haVar, this.T, i);
                }
                while (this.U < i) {
                    a(haVar, hjVar, i - this.U);
                }
            }
            if ("A_VORBIS".equals(bVar.a)) {
                this.o.c(0);
                hjVar.a(this.o, 4);
                this.ac += 4;
            }
        }
    }

    private void a(ha haVar, byte[] bArr, int i) throws IOException, InterruptedException {
        int length = bArr.length + i;
        if (this.r.e() < length) {
            this.r.a = Arrays.copyOf(bArr, length + i);
        } else {
            System.arraycopy(bArr, 0, this.r.a, 0, bArr.length);
        }
        haVar.b(this.r.a, bArr.length, i);
        this.r.a(length);
    }

    private void a(b bVar, String str, int i, long j, byte[] bArr) {
        ho.a(this.r.a, this.N, str, i, j, bArr);
        bVar.O.a(this.r, this.r.c());
        this.ac += this.r.c();
    }

    private static void a(byte[] bArr, long j, String str, int i, long j2, byte[] bArr2) {
        Object obj;
        if (j == -9223372036854775807L) {
            obj = bArr2;
        } else {
            j -= ((long) (((int) (j / 3600000000L)) * 3600)) * 1000000;
            j -= ((long) (((int) (j / 60000000)) * 60)) * 1000000;
            int i2 = (int) ((j - (((long) ((int) (j / 1000000))) * 1000000)) / j2);
            obj = z.c(String.format(Locale.US, str, new Object[]{Integer.valueOf(r1), Integer.valueOf(r2), Integer.valueOf(r3), Integer.valueOf(i2)}));
        }
        System.arraycopy(obj, 0, bArr, i, bArr2.length);
    }

    private void a(ha haVar, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int min = Math.min(i2, this.q.b());
        haVar.b(bArr, i + min, i2 - min);
        if (min > 0) {
            this.q.a(bArr, i, min);
        }
        this.U += i2;
    }

    private int a(ha haVar, hj hjVar, int i) throws IOException, InterruptedException {
        int min;
        int b = this.q.b();
        if (b > 0) {
            min = Math.min(i, b);
            hjVar.a(this.q, min);
        } else {
            min = hjVar.a(haVar, i, false);
        }
        this.U += min;
        this.ac += min;
        return min;
    }

    private hh e() {
        if (this.w == -1 || this.z == -9223372036854775807L || this.I == null || this.I.a() == 0 || this.J == null || this.J.a() != this.I.a()) {
            this.I = null;
            this.J = null;
            return new b(this.z);
        }
        int i;
        int a = this.I.a();
        int[] iArr = new int[a];
        long[] jArr = new long[a];
        long[] jArr2 = new long[a];
        long[] jArr3 = new long[a];
        int i2 = 0;
        for (i = 0; i < a; i++) {
            jArr3[i] = this.I.a(i);
            jArr[i] = this.w + this.J.a(i);
        }
        while (true) {
            i = a - 1;
            if (i2 < i) {
                i = i2 + 1;
                iArr[i2] = (int) (jArr[i] - jArr[i2]);
                jArr2[i2] = jArr3[i] - jArr3[i2];
                i2 = i;
            } else {
                iArr[i] = (int) ((this.w + this.v) - jArr[i]);
                jArr2[i] = this.z - jArr3[i];
                this.I = null;
                this.J = null;
                return new gv(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean a(hg hgVar, long j) {
        if (this.E) {
            this.G = j;
            hgVar.a = this.F;
            this.E = false;
            return true;
        } else if (!this.B || this.G == -1) {
            return false;
        } else {
            hgVar.a = this.G;
            this.G = -1;
            return true;
        }
    }

    private long a(long j) throws ParserException {
        if (this.x != -9223372036854775807L) {
            return z.d(j, this.x, 1000);
        }
        throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
    }

    private static boolean a(String str) {
        return "V_VP8".equals(str) || "V_VP9".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_TEXT/ASS".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str);
    }

    private static int[] a(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }
}
