package defpackage;

import android.graphics.Color;
import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.text.h;
import com.google.android.exoplayer2.text.i;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.m;
import com.google.android.exoplayer2.util.n;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: Cea708Decoder */
/* renamed from: kp */
public final class kp extends kq {
    private final n a = new n();
    private final m b = new m();
    private final int c;
    private final a[] d;
    private a e;
    private List<b> f;
    private List<b> g;
    private b h;
    private int i;

    /* compiled from: Cea708Decoder */
    /* renamed from: kp$a */
    private static final class a {
        public static final int a = a.a(2, 2, 2, 0);
        public static final int b = a.a(0, 0, 0, 0);
        public static final int c = a.a(0, 0, 0, 3);
        private static final int[] d = new int[]{0, 0, 0, 0, 0, 2, 0};
        private static final int[] e = new int[]{0, 0, 0, 0, 0, 0, 2};
        private static final int[] f = new int[]{3, 3, 3, 3, 3, 3, 1};
        private static final boolean[] g = new boolean[]{false, false, false, true, true, true, false};
        private static final int[] h = new int[]{b, c, b, b, c, b, b};
        private static final int[] i = new int[]{0, 1, 2, 3, 4, 3, 4};
        private static final int[] j = new int[]{0, 0, 0, 0, 0, 3, 3};
        private static final int[] k = new int[]{b, b, b, b, b, c, c};
        private int A;
        private int B;
        private int C;
        private int D;
        private int E;
        private int F;
        private int G;
        private final List<SpannableString> l = new ArrayList();
        private final SpannableStringBuilder m = new SpannableStringBuilder();
        private boolean n;
        private boolean o;
        private int p;
        private boolean q;
        private int r;
        private int s;
        private int t;
        private int u;
        private boolean v;
        private int w;
        private int x;
        private int y;
        private int z;

        public a() {
            b();
        }

        public boolean a() {
            return !d() || (this.l.isEmpty() && this.m.length() == 0);
        }

        public void b() {
            c();
            this.n = false;
            this.o = false;
            this.p = 4;
            this.q = false;
            this.r = 0;
            this.s = 0;
            this.t = 0;
            this.u = 15;
            this.v = true;
            this.w = 0;
            this.x = 0;
            this.y = 0;
            this.z = b;
            this.D = a;
            this.F = b;
        }

        public void c() {
            this.l.clear();
            this.m.clear();
            this.A = -1;
            this.B = -1;
            this.C = -1;
            this.E = -1;
            this.G = 0;
        }

        public boolean d() {
            return this.n;
        }

        public void a(boolean z) {
            this.o = z;
        }

        public boolean e() {
            return this.o;
        }

        public void a(boolean z, boolean z2, boolean z3, int i, boolean z4, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            int i9;
            boolean z5 = z2;
            int i10 = i7;
            int i11 = i8;
            this.n = true;
            this.o = z;
            this.v = z5;
            this.p = i;
            this.q = z4;
            this.r = i2;
            this.s = i3;
            this.t = i6;
            int i12 = i4 + 1;
            if (this.u != i12) {
                this.u = i12;
                while (true) {
                    if ((!z5 || this.l.size() < this.u) && this.l.size() < 15) {
                        break;
                    }
                    this.l.remove(0);
                }
            }
            if (!(i10 == 0 || this.x == i10)) {
                this.x = i10;
                i9 = i10 - 1;
                a(h[i9], c, g[i9], 0, e[i9], f[i9], d[i9]);
            }
            if (i11 != 0 && this.y != i11) {
                this.y = i11;
                i9 = i11 - 1;
                a(0, 1, 1, false, false, j[i9], i[i9]);
                a(a, k[i9], b);
            }
        }

        public void a(int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
            this.z = i;
            this.w = i6;
        }

        public void a(int i, int i2, int i3, boolean z, boolean z2, int i4, int i5) {
            if (this.A != -1) {
                if (!z) {
                    this.m.setSpan(new StyleSpan(2), this.A, this.m.length(), 33);
                    this.A = -1;
                }
            } else if (z) {
                this.A = this.m.length();
            }
            if (this.B != -1) {
                if (!z2) {
                    this.m.setSpan(new UnderlineSpan(), this.B, this.m.length(), 33);
                    this.B = -1;
                }
            } else if (z2) {
                this.B = this.m.length();
            }
        }

        public void a(int i, int i2, int i3) {
            if (!(this.C == -1 || this.D == i)) {
                this.m.setSpan(new ForegroundColorSpan(this.D), this.C, this.m.length(), 33);
            }
            if (i != a) {
                this.C = this.m.length();
                this.D = i;
            }
            if (!(this.E == -1 || this.F == i2)) {
                this.m.setSpan(new BackgroundColorSpan(this.F), this.E, this.m.length(), 33);
            }
            if (i2 != b) {
                this.E = this.m.length();
                this.F = i2;
            }
        }

        public void a(int i, int i2) {
            if (this.G != i) {
                a(10);
            }
            this.G = i;
        }

        public void f() {
            int length = this.m.length();
            if (length > 0) {
                this.m.delete(length - 1, length);
            }
        }

        public void a(char c) {
            if (c == 10) {
                this.l.add(g());
                this.m.clear();
                if (this.A != -1) {
                    this.A = 0;
                }
                if (this.B != -1) {
                    this.B = 0;
                }
                if (this.C != -1) {
                    this.C = 0;
                }
                if (this.E != -1) {
                    this.E = 0;
                }
                while (true) {
                    if ((this.v && this.l.size() >= this.u) || this.l.size() >= 15) {
                        this.l.remove(0);
                    } else {
                        return;
                    }
                }
            }
            this.m.append(c);
        }

        public SpannableString g() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.m);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.A != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.A, length, 33);
                }
                if (this.B != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.B, length, 33);
                }
                if (this.C != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.D), this.C, length, 33);
                }
                if (this.E != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.F), this.E, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        public ko h() {
            if (a()) {
                return null;
            }
            Alignment alignment;
            float f;
            float f2;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i = 0; i < this.l.size(); i++) {
                spannableStringBuilder.append((CharSequence) this.l.get(i));
                spannableStringBuilder.append(10);
            }
            spannableStringBuilder.append(g());
            switch (this.w) {
                case 0:
                case 3:
                    alignment = Alignment.ALIGN_NORMAL;
                    break;
                case 1:
                    alignment = Alignment.ALIGN_OPPOSITE;
                    break;
                case 2:
                    alignment = Alignment.ALIGN_CENTER;
                    break;
                default:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Unexpected justification value: ");
                    stringBuilder.append(this.w);
                    throw new IllegalArgumentException(stringBuilder.toString());
            }
            Alignment alignment2 = alignment;
            if (this.q) {
                f = ((float) this.s) / 99.0f;
                f2 = ((float) this.r) / 99.0f;
            } else {
                f = ((float) this.s) / 209.0f;
                f2 = ((float) this.r) / 74.0f;
            }
            float f3 = (f * 0.9f) + 0.05f;
            float f4 = (f2 * 0.9f) + 0.05f;
            int i2 = this.t % 3 == 0 ? 0 : this.t % 3 == 1 ? 1 : 2;
            int i3 = this.t / 3 == 0 ? 0 : this.t / 3 == 1 ? 1 : 2;
            return new ko(spannableStringBuilder, alignment2, f4, 0, i2, f3, i3, Float.MIN_VALUE, this.z != b, this.z, this.p);
        }

        public static int b(int i, int i2, int i3) {
            return a.a(i, i2, i3, 0);
        }

        public static int a(int i, int i2, int i3, int i4) {
            com.google.android.exoplayer2.util.a.a(i, 0, 4);
            com.google.android.exoplayer2.util.a.a(i2, 0, 4);
            com.google.android.exoplayer2.util.a.a(i3, 0, 4);
            com.google.android.exoplayer2.util.a.a(i4, 0, 4);
            int i5 = 255;
            switch (i4) {
                case 2:
                    i4 = 127;
                    break;
                case 3:
                    i4 = 0;
                    break;
                default:
                    i4 = 255;
                    break;
            }
            i = i > 1 ? 255 : 0;
            i2 = i2 > 1 ? 255 : 0;
            if (i3 <= 1) {
                i5 = 0;
            }
            return Color.argb(i4, i, i2, i5);
        }
    }

    /* compiled from: Cea708Decoder */
    /* renamed from: kp$b */
    private static final class b {
        public final int a;
        public final int b;
        public final byte[] c;
        int d = 0;

        public b(int i, int i2) {
            this.a = i;
            this.b = i2;
            this.c = new byte[((i2 * 2) - 1)];
        }
    }

    public /* bridge */ /* synthetic */ void d() {
        super.d();
    }

    public /* bridge */ /* synthetic */ i g() throws SubtitleDecoderException {
        return super.b();
    }

    public /* bridge */ /* synthetic */ h h() throws SubtitleDecoderException {
        return super.a();
    }

    public kp(int i) {
        if (i == -1) {
            i = 1;
        }
        this.c = i;
        this.d = new a[8];
        for (int i2 = 0; i2 < 8; i2++) {
            this.d[i2] = new a();
        }
        this.e = this.d[0];
        p();
    }

    public void c() {
        super.c();
        this.f = null;
        this.g = null;
        this.i = 0;
        this.e = this.d[this.i];
        p();
        this.h = null;
    }

    /* Access modifiers changed, original: protected */
    public boolean e() {
        return this.f != this.g;
    }

    /* Access modifiers changed, original: protected */
    public e f() {
        this.g = this.f;
        return new kr(this.f);
    }

    /* Access modifiers changed, original: protected */
    public void a(h hVar) {
        this.a.a(hVar.b.array(), hVar.b.limit());
        while (this.a.b() >= 3) {
            int g = this.a.g() & 7;
            int i = g & 3;
            boolean z = false;
            Object obj = (g & 4) == 4 ? 1 : null;
            byte g2 = (byte) this.a.g();
            byte g3 = (byte) this.a.g();
            if (i == 2 || i == 3) {
                if (obj != null) {
                    byte[] bArr;
                    b bVar;
                    if (i == 3) {
                        i();
                        g = (g2 & 192) >> 6;
                        int i2 = g2 & 63;
                        if (i2 == 0) {
                            i2 = 64;
                        }
                        this.h = new b(g, i2);
                        bArr = this.h.c;
                        bVar = this.h;
                        i = bVar.d;
                        bVar.d = i + 1;
                        bArr[i] = g3;
                    } else {
                        if (i == 2) {
                            z = true;
                        }
                        a.a(z);
                        if (this.h == null) {
                            Log.e("Cea708Decoder", "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                        } else {
                            bArr = this.h.c;
                            bVar = this.h;
                            i = bVar.d;
                            bVar.d = i + 1;
                            bArr[i] = g2;
                            bArr = this.h.c;
                            bVar = this.h;
                            i = bVar.d;
                            bVar.d = i + 1;
                            bArr[i] = g3;
                        }
                    }
                    if (this.h.d == (this.h.b * 2) - 1) {
                        i();
                    }
                }
            }
        }
    }

    private void i() {
        if (this.h != null) {
            j();
            this.h = null;
        }
    }

    private void j() {
        if (this.h.d != (this.h.b * 2) - 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("DtvCcPacket ended prematurely; size is ");
            stringBuilder.append((this.h.b * 2) - 1);
            stringBuilder.append(", but current index is ");
            stringBuilder.append(this.h.d);
            stringBuilder.append(" (sequence number ");
            stringBuilder.append(this.h.a);
            stringBuilder.append("); ignoring packet");
            Log.w("Cea708Decoder", stringBuilder.toString());
            return;
        }
        this.b.a(this.h.c, this.h.d);
        int c = this.b.c(3);
        int c2 = this.b.c(5);
        if (c == 7) {
            this.b.b(2);
            c += this.b.c(6);
        }
        if (c2 == 0) {
            if (c != 0) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("serviceNumber is non-zero (");
                stringBuilder2.append(c);
                stringBuilder2.append(") when blockSize is 0");
                Log.w("Cea708Decoder", stringBuilder2.toString());
            }
        } else if (c == this.c) {
            Object obj = null;
            while (this.b.a() > 0) {
                c2 = this.b.c(8);
                StringBuilder stringBuilder3;
                if (c2 == 16) {
                    c2 = this.b.c(8);
                    if (c2 <= 31) {
                        c(c2);
                    } else if (c2 <= 127) {
                        g(c2);
                    } else if (c2 <= 159) {
                        d(c2);
                    } else if (c2 <= 255) {
                        h(c2);
                    } else {
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("Invalid extended command: ");
                        stringBuilder3.append(c2);
                        Log.w("Cea708Decoder", stringBuilder3.toString());
                    }
                } else if (c2 <= 31) {
                    a(c2);
                } else if (c2 <= 127) {
                    e(c2);
                } else if (c2 <= 159) {
                    b(c2);
                } else if (c2 <= 255) {
                    f(c2);
                } else {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Invalid base command: ");
                    stringBuilder3.append(c2);
                    Log.w("Cea708Decoder", stringBuilder3.toString());
                }
                obj = 1;
            }
            if (obj != null) {
                this.f = o();
            }
        }
    }

    private void a(int i) {
        if (i == 0) {
            return;
        }
        if (i == 3) {
            this.f = o();
        } else if (i != 8) {
            switch (i) {
                case 12:
                    p();
                    return;
                case 13:
                    this.e.a(10);
                    return;
                case 14:
                    return;
                default:
                    StringBuilder stringBuilder;
                    if (i >= 17 && i <= 23) {
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append("Currently unsupported COMMAND_EXT1 Command: ");
                        stringBuilder2.append(i);
                        Log.w("Cea708Decoder", stringBuilder2.toString());
                        this.b.b(8);
                        return;
                    } else if (i < 24 || i > 31) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid C0 command: ");
                        stringBuilder.append(i);
                        Log.w("Cea708Decoder", stringBuilder.toString());
                        return;
                    } else {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Currently unsupported COMMAND_P16 Command: ");
                        stringBuilder.append(i);
                        Log.w("Cea708Decoder", stringBuilder.toString());
                        this.b.b(16);
                        return;
                    }
            }
        } else {
            this.e.f();
        }
    }

    /* JADX WARNING: Missing block: B:25:0x0096, code skipped:
            if (r2 > 8) goto L_0x011a;
     */
    /* JADX WARNING: Missing block: B:27:0x009e, code skipped:
            if (r4.b.e() == false) goto L_0x00a9;
     */
    /* JADX WARNING: Missing block: B:28:0x00a0, code skipped:
            r4.d[8 - r2].b();
     */
    /* JADX WARNING: Missing block: B:29:0x00a9, code skipped:
            r2 = r2 + 1;
     */
    /* JADX WARNING: Missing block: B:36:0x00c8, code skipped:
            if (r2 > 8) goto L_0x011a;
     */
    /* JADX WARNING: Missing block: B:38:0x00d0, code skipped:
            if (r4.b.e() == false) goto L_0x00dc;
     */
    /* JADX WARNING: Missing block: B:39:0x00d2, code skipped:
            r4.d[8 - r2].a(false);
     */
    /* JADX WARNING: Missing block: B:40:0x00dc, code skipped:
            r2 = r2 + 1;
     */
    /* JADX WARNING: Missing block: B:47:0x00f6, code skipped:
            if (r2 > 8) goto L_0x011a;
     */
    /* JADX WARNING: Missing block: B:49:0x00fe, code skipped:
            if (r4.b.e() == false) goto L_0x0109;
     */
    /* JADX WARNING: Missing block: B:50:0x0100, code skipped:
            r4.d[8 - r2].c();
     */
    /* JADX WARNING: Missing block: B:51:0x0109, code skipped:
            r2 = r2 + 1;
     */
    /* JADX WARNING: Missing block: B:86:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:88:?, code skipped:
            return;
     */
    /* JADX WARNING: Missing block: B:90:?, code skipped:
            return;
     */
    private void b(int r5) {
        /*
        r4 = this;
        r0 = 16;
        r1 = 8;
        r2 = 1;
        switch(r5) {
            case 128: goto L_0x010c;
            case 129: goto L_0x010c;
            case 130: goto L_0x010c;
            case 131: goto L_0x010c;
            case 132: goto L_0x010c;
            case 133: goto L_0x010c;
            case 134: goto L_0x010c;
            case 135: goto L_0x010c;
            case 136: goto L_0x00f6;
            case 137: goto L_0x00df;
            case 138: goto L_0x00c8;
            case 139: goto L_0x00ac;
            case 140: goto L_0x0096;
            case 141: goto L_0x008f;
            case 142: goto L_0x011a;
            case 143: goto L_0x008a;
            case 144: goto L_0x0076;
            case 145: goto L_0x0060;
            case 146: goto L_0x004c;
            default: goto L_0x0008;
        };
    L_0x0008:
        switch(r5) {
            case 151: goto L_0x0036;
            case 152: goto L_0x0023;
            case 153: goto L_0x0023;
            case 154: goto L_0x0023;
            case 155: goto L_0x0023;
            case 156: goto L_0x0023;
            case 157: goto L_0x0023;
            case 158: goto L_0x0023;
            case 159: goto L_0x0023;
            default: goto L_0x000b;
        };
    L_0x000b:
        r0 = "Cea708Decoder";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Invalid C1 command: ";
        r1.append(r2);
        r1.append(r5);
        r5 = r1.toString();
        android.util.Log.w(r0, r5);
        goto L_0x011a;
    L_0x0023:
        r5 = r5 + -152;
        r4.i(r5);
        r0 = r4.i;
        if (r0 == r5) goto L_0x011a;
    L_0x002c:
        r4.i = r5;
        r0 = r4.d;
        r5 = r0[r5];
        r4.e = r5;
        goto L_0x011a;
    L_0x0036:
        r5 = r4.e;
        r5 = r5.d();
        if (r5 != 0) goto L_0x0047;
    L_0x003e:
        r5 = r4.b;
        r0 = 32;
        r5.b(r0);
        goto L_0x011a;
    L_0x0047:
        r4.n();
        goto L_0x011a;
    L_0x004c:
        r5 = r4.e;
        r5 = r5.d();
        if (r5 != 0) goto L_0x005b;
    L_0x0054:
        r5 = r4.b;
        r5.b(r0);
        goto L_0x011a;
    L_0x005b:
        r4.m();
        goto L_0x011a;
    L_0x0060:
        r5 = r4.e;
        r5 = r5.d();
        if (r5 != 0) goto L_0x0071;
    L_0x0068:
        r5 = r4.b;
        r0 = 24;
        r5.b(r0);
        goto L_0x011a;
    L_0x0071:
        r4.l();
        goto L_0x011a;
    L_0x0076:
        r5 = r4.e;
        r5 = r5.d();
        if (r5 != 0) goto L_0x0085;
    L_0x007e:
        r5 = r4.b;
        r5.b(r0);
        goto L_0x011a;
    L_0x0085:
        r4.k();
        goto L_0x011a;
    L_0x008a:
        r4.p();
        goto L_0x011a;
    L_0x008f:
        r5 = r4.b;
        r5.b(r1);
        goto L_0x011a;
    L_0x0096:
        if (r2 > r1) goto L_0x011a;
    L_0x0098:
        r5 = r4.b;
        r5 = r5.e();
        if (r5 == 0) goto L_0x00a9;
    L_0x00a0:
        r5 = r4.d;
        r0 = 8 - r2;
        r5 = r5[r0];
        r5.b();
    L_0x00a9:
        r2 = r2 + 1;
        goto L_0x0096;
    L_0x00ac:
        r5 = 1;
    L_0x00ad:
        if (r5 > r1) goto L_0x011a;
    L_0x00af:
        r0 = r4.b;
        r0 = r0.e();
        if (r0 == 0) goto L_0x00c5;
    L_0x00b7:
        r0 = r4.d;
        r3 = 8 - r5;
        r0 = r0[r3];
        r3 = r0.e();
        r3 = r3 ^ r2;
        r0.a(r3);
    L_0x00c5:
        r5 = r5 + 1;
        goto L_0x00ad;
    L_0x00c8:
        if (r2 > r1) goto L_0x011a;
    L_0x00ca:
        r5 = r4.b;
        r5 = r5.e();
        if (r5 == 0) goto L_0x00dc;
    L_0x00d2:
        r5 = r4.d;
        r0 = 8 - r2;
        r5 = r5[r0];
        r0 = 0;
        r5.a(r0);
    L_0x00dc:
        r2 = r2 + 1;
        goto L_0x00c8;
    L_0x00df:
        r5 = 1;
    L_0x00e0:
        if (r5 > r1) goto L_0x011a;
    L_0x00e2:
        r0 = r4.b;
        r0 = r0.e();
        if (r0 == 0) goto L_0x00f3;
    L_0x00ea:
        r0 = r4.d;
        r3 = 8 - r5;
        r0 = r0[r3];
        r0.a(r2);
    L_0x00f3:
        r5 = r5 + 1;
        goto L_0x00e0;
    L_0x00f6:
        if (r2 > r1) goto L_0x011a;
    L_0x00f8:
        r5 = r4.b;
        r5 = r5.e();
        if (r5 == 0) goto L_0x0109;
    L_0x0100:
        r5 = r4.d;
        r0 = 8 - r2;
        r5 = r5[r0];
        r5.c();
    L_0x0109:
        r2 = r2 + 1;
        goto L_0x00f6;
    L_0x010c:
        r5 = r5 + -128;
        r0 = r4.i;
        if (r0 == r5) goto L_0x011a;
    L_0x0112:
        r4.i = r5;
        r0 = r4.d;
        r5 = r0[r5];
        r4.e = r5;
    L_0x011a:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kp.b(int):void");
    }

    private void c(int i) {
        if (i > 7) {
            if (i <= 15) {
                this.b.b(8);
            } else if (i <= 23) {
                this.b.b(16);
            } else if (i <= 31) {
                this.b.b(24);
            }
        }
    }

    private void d(int i) {
        if (i <= 135) {
            this.b.b(32);
        } else if (i <= 143) {
            this.b.b(40);
        } else if (i <= 159) {
            this.b.b(2);
            this.b.b(this.b.c(6) * 8);
        }
    }

    private void e(int i) {
        if (i == 127) {
            this.e.a(9835);
        } else {
            this.e.a((char) (i & 255));
        }
    }

    private void f(int i) {
        this.e.a((char) (i & 255));
    }

    private void g(int i) {
        if (i == 37) {
            this.e.a(8230);
        } else if (i == 42) {
            this.e.a(352);
        } else if (i == 44) {
            this.e.a(338);
        } else if (i != 63) {
            switch (i) {
                case 32:
                    this.e.a(' ');
                    return;
                case 33:
                    this.e.a(160);
                    return;
                default:
                    switch (i) {
                        case 48:
                            this.e.a(9608);
                            return;
                        case 49:
                            this.e.a(8216);
                            return;
                        case 50:
                            this.e.a(8217);
                            return;
                        case 51:
                            this.e.a(8220);
                            return;
                        case 52:
                            this.e.a(8221);
                            return;
                        case 53:
                            this.e.a(8226);
                            return;
                        default:
                            switch (i) {
                                case 57:
                                    this.e.a(8482);
                                    return;
                                case 58:
                                    this.e.a(353);
                                    return;
                                default:
                                    switch (i) {
                                        case 60:
                                            this.e.a(339);
                                            return;
                                        case 61:
                                            this.e.a(8480);
                                            return;
                                        default:
                                            switch (i) {
                                                case 118:
                                                    this.e.a(8539);
                                                    return;
                                                case 119:
                                                    this.e.a(8540);
                                                    return;
                                                case 120:
                                                    this.e.a(8541);
                                                    return;
                                                case 121:
                                                    this.e.a(8542);
                                                    return;
                                                case IjkMediaMeta.FF_PROFILE_H264_HIGH_422 /*122*/:
                                                    this.e.a(9474);
                                                    return;
                                                case 123:
                                                    this.e.a(9488);
                                                    return;
                                                case 124:
                                                    this.e.a(9492);
                                                    return;
                                                case 125:
                                                    this.e.a(9472);
                                                    return;
                                                case 126:
                                                    this.e.a(9496);
                                                    return;
                                                case 127:
                                                    this.e.a(9484);
                                                    return;
                                                default:
                                                    StringBuilder stringBuilder = new StringBuilder();
                                                    stringBuilder.append("Invalid G2 character: ");
                                                    stringBuilder.append(i);
                                                    Log.w("Cea708Decoder", stringBuilder.toString());
                                                    return;
                                            }
                                    }
                            }
                    }
            }
        } else {
            this.e.a(376);
        }
    }

    private void h(int i) {
        if (i == 160) {
            this.e.a(13252);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Invalid G3 character: ");
        stringBuilder.append(i);
        Log.w("Cea708Decoder", stringBuilder.toString());
        this.e.a('_');
    }

    private void k() {
        this.e.a(this.b.c(4), this.b.c(2), this.b.c(2), this.b.e(), this.b.e(), this.b.c(3), this.b.c(3));
    }

    private void l() {
        int a = a.a(this.b.c(2), this.b.c(2), this.b.c(2), this.b.c(2));
        int a2 = a.a(this.b.c(2), this.b.c(2), this.b.c(2), this.b.c(2));
        this.b.b(2);
        this.e.a(a, a2, a.b(this.b.c(2), this.b.c(2), this.b.c(2)));
    }

    private void m() {
        this.b.b(4);
        int c = this.b.c(4);
        this.b.b(2);
        this.e.a(c, this.b.c(6));
    }

    private void n() {
        int a = a.a(this.b.c(2), this.b.c(2), this.b.c(2), this.b.c(2));
        int c = this.b.c(2);
        int b = a.b(this.b.c(2), this.b.c(2), this.b.c(2));
        if (this.b.e()) {
            c |= 4;
        }
        int i = c;
        boolean e = this.b.e();
        int c2 = this.b.c(2);
        int c3 = this.b.c(2);
        int c4 = this.b.c(2);
        this.b.b(8);
        this.e.a(a, b, e, i, c2, c3, c4);
    }

    private void i(int i) {
        a aVar = this.d[i];
        this.b.b(2);
        boolean e = this.b.e();
        boolean e2 = this.b.e();
        boolean e3 = this.b.e();
        i = this.b.c(3);
        boolean e4 = this.b.e();
        int c = this.b.c(7);
        int c2 = this.b.c(8);
        int c3 = this.b.c(4);
        int c4 = this.b.c(4);
        this.b.b(2);
        int c5 = this.b.c(6);
        this.b.b(2);
        aVar.a(e, e2, e3, i, e4, c, c2, c4, c5, c3, this.b.c(3), this.b.c(3));
    }

    private List<b> o() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < 8) {
            if (!this.d[i].a() && this.d[i].e()) {
                arrayList.add(this.d[i].h());
            }
            i++;
        }
        Collections.sort(arrayList);
        return Collections.unmodifiableList(arrayList);
    }

    private void p() {
        for (int i = 0; i < 8; i++) {
            this.d[i].b();
        }
    }
}
