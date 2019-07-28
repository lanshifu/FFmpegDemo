package defpackage;

import android.text.Layout.Alignment;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.text.e;
import com.google.android.exoplayer2.text.h;
import com.google.android.exoplayer2.text.i;
import com.google.android.exoplayer2.util.n;
import java.util.ArrayList;
import java.util.List;
import jp.wasabeef.glide.transformations.BuildConfig;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: Cea608Decoder */
/* renamed from: kn */
public final class kn extends kq {
    private static final int[] a = new int[]{11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] b = new int[]{0, 4, 8, 12, 16, 20, 24, 28};
    private static final int[] c = new int[]{-1, -16711936, -16776961, -16711681, -65536, -256, -65281};
    private static final int[] d = new int[]{32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, IjkMediaMeta.FF_PROFILE_H264_HIGH_422, 231, 247, 209, 241, 9632};
    private static final int[] e = new int[]{174, 176, 189, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, 251};
    private static final int[] f = new int[]{193, 201, 211, 218, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, 192, 194, BuildConfig.VERSION_CODE, 200, 202, 203, 235, 206, 207, 239, 212, 217, 249, 219, 171, 187};
    private static final int[] g = new int[]{195, 227, 205, 204, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    private final n h = new n();
    private final int i;
    private final int j;
    private final ArrayList<a> k = new ArrayList();
    private a l = new a(0, 4);
    private List<b> m;
    private List<b> n;
    private int o;
    private int p;
    private boolean q;
    private byte r;
    private byte s;

    /* compiled from: Cea608Decoder */
    /* renamed from: kn$a */
    private static class a {
        private final List<a> a = new ArrayList();
        private final List<SpannableString> b = new ArrayList();
        private final StringBuilder c = new StringBuilder();
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;

        /* compiled from: Cea608Decoder */
        /* renamed from: kn$a$a */
        private static class a {
            public final int a;
            public final boolean b;
            public int c;

            public a(int i, boolean z, int i2) {
                this.a = i;
                this.b = z;
                this.c = i2;
            }
        }

        public a(int i, int i2) {
            a(i);
            b(i2);
        }

        public void a(int i) {
            this.g = i;
            this.a.clear();
            this.b.clear();
            this.c.setLength(0);
            this.d = 15;
            this.e = 0;
            this.f = 0;
        }

        public void b(int i) {
            this.h = i;
        }

        public boolean a() {
            return this.a.isEmpty() && this.b.isEmpty() && this.c.length() == 0;
        }

        public void b() {
            int length = this.c.length();
            if (length > 0) {
                this.c.delete(length - 1, length);
                int size = this.a.size() - 1;
                while (size >= 0) {
                    a aVar = (a) this.a.get(size);
                    if (aVar.c == length) {
                        aVar.c--;
                        size--;
                    } else {
                        return;
                    }
                }
            }
        }

        public int c() {
            return this.d;
        }

        public void c(int i) {
            this.d = i;
        }

        public void d() {
            this.b.add(e());
            this.c.setLength(0);
            this.a.clear();
            int min = Math.min(this.h, this.d);
            while (this.b.size() >= min) {
                this.b.remove(0);
            }
        }

        public void d(int i) {
            this.e = i;
        }

        public void e(int i) {
            this.f = i;
        }

        public void a(int i, boolean z) {
            this.a.add(new a(i, z, this.c.length()));
        }

        public void a(char c) {
            this.c.append(c);
        }

        public SpannableString e() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.c);
            int length = spannableStringBuilder.length();
            int i = 0;
            int i2 = -1;
            int i3 = -1;
            int i4 = 0;
            int i5 = -1;
            int i6 = -1;
            Object obj = null;
            while (i < this.a.size()) {
                a aVar = (a) this.a.get(i);
                boolean z = aVar.b;
                int i7 = aVar.a;
                if (i7 != 8) {
                    Object obj2 = i7 == 7 ? 1 : null;
                    if (i7 != 7) {
                        i6 = kn.c[i7];
                    }
                    obj = obj2;
                }
                int i8 = aVar.c;
                i++;
                if (i8 != (i < this.a.size() ? ((a) this.a.get(i)).c : length)) {
                    if (i2 != -1 && !z) {
                        a.a(spannableStringBuilder, i2, i8);
                        i2 = -1;
                    } else if (i2 == -1 && z) {
                        i2 = i8;
                    }
                    if (i3 != -1 && obj == null) {
                        a.b(spannableStringBuilder, i3, i8);
                        i3 = -1;
                    } else if (i3 == -1 && obj != null) {
                        i3 = i8;
                    }
                    if (i6 != i5) {
                        a.a(spannableStringBuilder, i4, i8, i5);
                        i5 = i6;
                        i4 = i8;
                    }
                }
            }
            if (!(i2 == -1 || i2 == length)) {
                a.a(spannableStringBuilder, i2, length);
            }
            if (!(i3 == -1 || i3 == length)) {
                a.b(spannableStringBuilder, i3, length);
            }
            if (i4 != length) {
                a.a(spannableStringBuilder, i4, length, i5);
            }
            return new SpannableString(spannableStringBuilder);
        }

        public b f() {
            int i;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (i = 0; i < this.b.size(); i++) {
                spannableStringBuilder.append((CharSequence) this.b.get(i));
                spannableStringBuilder.append(10);
            }
            spannableStringBuilder.append(e());
            if (spannableStringBuilder.length() == 0) {
                return null;
            }
            float f;
            int i2;
            int i3;
            i = this.e + this.f;
            int length = (32 - i) - spannableStringBuilder.length();
            int i4 = i - length;
            if (this.g == 2 && (Math.abs(i4) < 3 || length < 0)) {
                f = 0.5f;
                i2 = 1;
            } else if (this.g != 2 || i4 <= 0) {
                f = ((((float) i) / 32.0f) * 0.8f) + 0.1f;
                i2 = 0;
            } else {
                f = ((((float) (32 - length)) / 32.0f) * 0.8f) + 0.1f;
                i2 = 2;
            }
            if (this.g == 1 || this.d > 7) {
                i = (this.d - 15) - 2;
                i3 = 2;
            } else {
                i = this.d;
                i3 = 0;
            }
            return new b(spannableStringBuilder, Alignment.ALIGN_NORMAL, (float) i, 1, i3, f, i2, Float.MIN_VALUE);
        }

        public String toString() {
            return this.c.toString();
        }

        private static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i, i2, 33);
        }

        private static void b(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
            spannableStringBuilder.setSpan(new StyleSpan(2), i, i2, 33);
        }

        private static void a(SpannableStringBuilder spannableStringBuilder, int i, int i2, int i3) {
            if (i3 != -1) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(i3), i, i2, 33);
            }
        }
    }

    private static boolean c(byte b, byte b2) {
        return (b & 247) == 17 && (b2 & 240) == 32;
    }

    private static boolean d(byte b, byte b2) {
        return (b & 240) == 16 && (b2 & 192) == 64;
    }

    private static boolean e(byte b, byte b2) {
        return (b & 247) == 23 && b2 >= (byte) 33 && b2 <= (byte) 35;
    }

    private static boolean f(byte b, byte b2) {
        return (b & 247) == 20 && (b2 & 240) == 32;
    }

    private static boolean g(byte b) {
        return (b & 240) == 16;
    }

    public void d() {
    }

    public /* bridge */ /* synthetic */ i g() throws SubtitleDecoderException {
        return super.b();
    }

    public /* bridge */ /* synthetic */ h h() throws SubtitleDecoderException {
        return super.a();
    }

    public kn(String str, int i) {
        this.i = "application/x-mp4-cea-608".equals(str) ? 2 : 3;
        switch (i) {
            case 3:
            case 4:
                this.j = 2;
                break;
            default:
                this.j = 1;
                break;
        }
        a(0);
        k();
    }

    public void c() {
        super.c();
        this.m = null;
        this.n = null;
        a(0);
        b(4);
        k();
        this.q = false;
        this.r = (byte) 0;
        this.s = (byte) 0;
    }

    /* Access modifiers changed, original: protected */
    public boolean e() {
        return this.m != this.n;
    }

    /* Access modifiers changed, original: protected */
    public e f() {
        this.n = this.m;
        return new kr(this.m);
    }

    /* Access modifiers changed, original: protected */
    public void a(h hVar) {
        this.h.a(hVar.b.array(), hVar.b.limit());
        Object obj = null;
        boolean z = false;
        while (this.h.b() >= this.i) {
            int i;
            if (this.i == 2) {
                i = -4;
            } else {
                i = (byte) this.h.g();
            }
            byte g = (byte) (this.h.g() & 127);
            byte g2 = (byte) (this.h.g() & 127);
            if ((i & 6) == 4) {
                if (this.j != 1 || (i & 1) == 0) {
                    if (this.j != 2 || (i & 1) == 1) {
                        if (g != (byte) 0 || g2 != (byte) 0) {
                            if ((g & 247) == 17 && (g2 & 240) == 48) {
                                this.l.a(kn.d(g2));
                            } else if ((g & 246) == 18 && (g2 & 224) == 32) {
                                this.l.b();
                                if ((g & 1) == 0) {
                                    this.l.a(kn.e(g2));
                                } else {
                                    this.l.a(kn.f(g2));
                                }
                            } else if ((g & 224) == 0) {
                                z = a(g, g2);
                            } else {
                                this.l.a(kn.c(g));
                                if ((g2 & 224) != 0) {
                                    this.l.a(kn.c(g2));
                                }
                            }
                            obj = 1;
                        }
                    }
                }
            }
        }
        if (obj != null) {
            if (!z) {
                this.q = false;
            }
            if (this.o == 1 || this.o == 3) {
                this.m = j();
            }
        }
    }

    private boolean a(byte b, byte b2) {
        boolean g = kn.g(b);
        if (g) {
            if (this.q && this.r == b && this.s == b2) {
                this.q = false;
                return true;
            }
            this.q = true;
            this.r = b;
            this.s = b2;
        }
        if (kn.c(b, b2)) {
            a(b2);
        } else if (kn.d(b, b2)) {
            b(b, b2);
        } else if (kn.e(b, b2)) {
            this.l.e(b2 - 32);
        } else if (kn.f(b, b2)) {
            b(b2);
        }
        return g;
    }

    private void a(byte b) {
        this.l.a(' ');
        this.l.a((b >> 1) & 7, (b & 1) == 1);
    }

    private void b(byte b, byte b2) {
        int i = a[b & 7];
        boolean z = false;
        if (((b2 & 32) != 0 ? 1 : null) != null) {
            i++;
        }
        if (i != this.l.c()) {
            if (!(this.o == 1 || this.l.a())) {
                this.l = new a(this.o, this.p);
                this.k.add(this.l);
            }
            this.l.c(i);
        }
        Object obj = (b2 & 16) == 16 ? 1 : null;
        if ((b2 & 1) == 1) {
            z = true;
        }
        int i2 = (b2 >> 1) & 7;
        this.l.a(obj != null ? 8 : i2, z);
        if (obj != null) {
            this.l.d(b[i2]);
        }
    }

    private void b(byte b) {
        if (b == (byte) 32) {
            a(2);
        } else if (b != (byte) 41) {
            switch (b) {
                case (byte) 37:
                    a(1);
                    b(2);
                    return;
                case (byte) 38:
                    a(1);
                    b(3);
                    return;
                case (byte) 39:
                    a(1);
                    b(4);
                    return;
                default:
                    if (this.o != 0) {
                        if (b != (byte) 33) {
                            if (b != (byte) 36) {
                                switch (b) {
                                    case (byte) 44:
                                        this.m = null;
                                        if (this.o == 1 || this.o == 3) {
                                            k();
                                            break;
                                        }
                                    case (byte) 45:
                                        if (this.o == 1 && !this.l.a()) {
                                            this.l.d();
                                            break;
                                        }
                                    case (byte) 46:
                                        k();
                                        break;
                                    case (byte) 47:
                                        this.m = j();
                                        k();
                                        break;
                                }
                            }
                        }
                        this.l.b();
                        return;
                    }
                    return;
            }
        } else {
            a(3);
        }
    }

    private List<b> j() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.k.size(); i++) {
            b f = ((a) this.k.get(i)).f();
            if (f != null) {
                arrayList.add(f);
            }
        }
        return arrayList;
    }

    private void a(int i) {
        if (this.o != i) {
            int i2 = this.o;
            this.o = i;
            k();
            if (i2 == 3 || i == 1 || i == 0) {
                this.m = null;
            }
        }
    }

    private void b(int i) {
        this.p = i;
        this.l.b(i);
    }

    private void k() {
        this.l.a(this.o);
        this.k.clear();
        this.k.add(this.l);
    }

    private static char c(byte b) {
        return (char) d[(b & 127) - 32];
    }

    private static char d(byte b) {
        return (char) e[b & 15];
    }

    private static char e(byte b) {
        return (char) f[b & 31];
    }

    private static char f(byte b) {
        return (char) g[b & 31];
    }
}
