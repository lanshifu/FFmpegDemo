package defpackage;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Region.Op;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.util.m;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: DvbParser */
/* renamed from: ku */
final class ku {
    private static final byte[] a = new byte[]{(byte) 0, (byte) 7, (byte) 8, (byte) 15};
    private static final byte[] b = new byte[]{(byte) 0, (byte) 119, (byte) -120, (byte) -1};
    private static final byte[] c = new byte[]{(byte) 0, (byte) 17, (byte) 34, (byte) 51, (byte) 68, (byte) 85, (byte) 102, (byte) 119, (byte) -120, (byte) -103, (byte) -86, (byte) -69, (byte) -52, (byte) -35, (byte) -18, (byte) -1};
    private final Paint d = new Paint();
    private final Paint e;
    private final Canvas f;
    private final b g;
    private final a h;
    private final h i;
    private Bitmap j;

    /* compiled from: DvbParser */
    /* renamed from: ku$a */
    private static final class a {
        public final int a;
        public final int[] b;
        public final int[] c;
        public final int[] d;

        public a(int i, int[] iArr, int[] iArr2, int[] iArr3) {
            this.a = i;
            this.b = iArr;
            this.c = iArr2;
            this.d = iArr3;
        }
    }

    /* compiled from: DvbParser */
    /* renamed from: ku$b */
    private static final class b {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;

        public b(int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
        }
    }

    /* compiled from: DvbParser */
    /* renamed from: ku$c */
    private static final class c {
        public final int a;
        public final boolean b;
        public final byte[] c;
        public final byte[] d;

        public c(int i, boolean z, byte[] bArr, byte[] bArr2) {
            this.a = i;
            this.b = z;
            this.c = bArr;
            this.d = bArr2;
        }
    }

    /* compiled from: DvbParser */
    /* renamed from: ku$d */
    private static final class d {
        public final int a;
        public final int b;
        public final int c;
        public final SparseArray<e> d;

        public d(int i, int i2, int i3, SparseArray<e> sparseArray) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = sparseArray;
        }
    }

    /* compiled from: DvbParser */
    /* renamed from: ku$e */
    private static final class e {
        public final int a;
        public final int b;

        public e(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    /* compiled from: DvbParser */
    /* renamed from: ku$f */
    private static final class f {
        public final int a;
        public final boolean b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;
        public final int h;
        public final int i;
        public final int j;
        public final SparseArray<g> k;

        public f(int i, boolean z, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, SparseArray<g> sparseArray) {
            this.a = i;
            this.b = z;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            this.g = i6;
            this.h = i7;
            this.i = i8;
            this.j = i9;
            this.k = sparseArray;
        }

        public void a(f fVar) {
            if (fVar != null) {
                SparseArray sparseArray = fVar.k;
                for (int i = 0; i < sparseArray.size(); i++) {
                    this.k.put(sparseArray.keyAt(i), sparseArray.valueAt(i));
                }
            }
        }
    }

    /* compiled from: DvbParser */
    /* renamed from: ku$g */
    private static final class g {
        public final int a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;

        public g(int i, int i2, int i3, int i4, int i5, int i6) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
            this.f = i6;
        }
    }

    /* compiled from: DvbParser */
    /* renamed from: ku$h */
    private static final class h {
        public final int a;
        public final int b;
        public final SparseArray<f> c = new SparseArray();
        public final SparseArray<a> d = new SparseArray();
        public final SparseArray<c> e = new SparseArray();
        public final SparseArray<a> f = new SparseArray();
        public final SparseArray<c> g = new SparseArray();
        public b h;
        public d i;

        public h(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public void a() {
            this.c.clear();
            this.d.clear();
            this.e.clear();
            this.f.clear();
            this.g.clear();
            this.h = null;
            this.i = null;
        }
    }

    private static int a(int i, int i2, int i3, int i4) {
        return (((i << 24) | (i2 << 16)) | (i3 << 8)) | i4;
    }

    public ku(int i, int i2) {
        this.d.setStyle(Style.FILL_AND_STROKE);
        this.d.setXfermode(new PorterDuffXfermode(Mode.SRC));
        this.d.setPathEffect(null);
        this.e = new Paint();
        this.e.setStyle(Style.FILL);
        this.e.setXfermode(new PorterDuffXfermode(Mode.DST_OVER));
        this.e.setPathEffect(null);
        this.f = new Canvas();
        this.g = new b(719, 575, 0, 719, 0, 575);
        this.h = new a(0, ku.b(), ku.c(), ku.d());
        this.i = new h(i, i2);
    }

    public void a() {
        this.i.a();
    }

    public List<b> a(byte[] bArr, int i) {
        m mVar = new m(bArr, i);
        while (mVar.a() >= 48 && mVar.c(8) == 15) {
            ku.a(mVar, this.i);
        }
        if (this.i.i == null) {
            return Collections.emptyList();
        }
        b bVar = this.i.h != null ? this.i.h : this.g;
        if (!(this.j != null && bVar.a + 1 == this.j.getWidth() && bVar.b + 1 == this.j.getHeight())) {
            this.j = Bitmap.createBitmap(bVar.a + 1, bVar.b + 1, Config.ARGB_8888);
            this.f.setBitmap(this.j);
        }
        ArrayList arrayList = new ArrayList();
        SparseArray sparseArray = this.i.i.d;
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            e eVar = (e) sparseArray.valueAt(i2);
            f fVar = (f) this.i.c.get(sparseArray.keyAt(i2));
            int i3 = eVar.a + bVar.c;
            int i4 = eVar.b + bVar.e;
            float f = (float) i3;
            float f2 = (float) i4;
            float f3 = f2;
            float f4 = f;
            this.f.clipRect(f, f2, (float) Math.min(fVar.c + i3, bVar.d), (float) Math.min(fVar.d + i4, bVar.f), Op.REPLACE);
            a aVar = (a) this.i.d.get(fVar.g);
            if (aVar == null) {
                aVar = (a) this.i.f.get(fVar.g);
                if (aVar == null) {
                    aVar = this.h;
                }
            }
            SparseArray sparseArray2 = fVar.k;
            int i5 = 0;
            while (i5 < sparseArray2.size()) {
                int i6;
                SparseArray sparseArray3;
                int keyAt = sparseArray2.keyAt(i5);
                g gVar = (g) sparseArray2.valueAt(i5);
                c cVar = (c) this.i.e.get(keyAt);
                c cVar2 = cVar == null ? (c) this.i.g.get(keyAt) : cVar;
                if (cVar2 != null) {
                    i6 = i5;
                    sparseArray3 = sparseArray2;
                    ku.a(cVar2, aVar, fVar.f, gVar.c + i3, i4 + gVar.d, cVar2.b ? null : this.d, this.f);
                } else {
                    i6 = i5;
                    sparseArray3 = sparseArray2;
                }
                i5 = i6 + 1;
                sparseArray2 = sparseArray3;
            }
            if (fVar.b) {
                int i7;
                if (fVar.f == 3) {
                    i7 = aVar.d[fVar.h];
                } else if (fVar.f == 2) {
                    i7 = aVar.c[fVar.i];
                } else {
                    i7 = aVar.b[fVar.j];
                }
                this.e.setColor(i7);
                this.f.drawRect(f4, f3, (float) (fVar.c + i3), (float) (fVar.d + i4), this.e);
            }
            arrayList.add(new b(Bitmap.createBitmap(this.j, i3, i4, fVar.c, fVar.d), f4 / ((float) bVar.a), 0, f3 / ((float) bVar.b), 0, ((float) fVar.c) / ((float) bVar.a), ((float) fVar.d) / ((float) bVar.b)));
            this.f.drawColor(0, Mode.CLEAR);
        }
        return arrayList;
    }

    private static void a(m mVar, h hVar) {
        int c = mVar.c(8);
        int c2 = mVar.c(16);
        int c3 = mVar.c(16);
        int c4 = mVar.c() + c3;
        if (c3 * 8 > mVar.a()) {
            Log.w("DvbParser", "Data field length exceeds limit");
            mVar.b(mVar.a());
            return;
        }
        d dVar;
        switch (c) {
            case 16:
                if (c2 == hVar.a) {
                    dVar = hVar.i;
                    d a = ku.a(mVar, c3);
                    if (a.c == 0) {
                        if (!(dVar == null || dVar.b == a.b)) {
                            hVar.i = a;
                            break;
                        }
                    }
                    hVar.i = a;
                    hVar.c.clear();
                    hVar.d.clear();
                    hVar.e.clear();
                    break;
                }
                break;
            case 17:
                dVar = hVar.i;
                if (c2 == hVar.a && dVar != null) {
                    f b = ku.b(mVar, c3);
                    if (dVar.c == 0) {
                        b.a((f) hVar.c.get(b.a));
                    }
                    hVar.c.put(b.a, b);
                    break;
                }
            case 18:
                a c5;
                if (c2 != hVar.a) {
                    if (c2 == hVar.b) {
                        c5 = ku.c(mVar, c3);
                        hVar.f.put(c5.a, c5);
                        break;
                    }
                }
                c5 = ku.c(mVar, c3);
                hVar.d.put(c5.a, c5);
                break;
                break;
            case 19:
                c b2;
                if (c2 != hVar.a) {
                    if (c2 == hVar.b) {
                        b2 = ku.b(mVar);
                        hVar.g.put(b2.a, b2);
                        break;
                    }
                }
                b2 = ku.b(mVar);
                hVar.e.put(b2.a, b2);
                break;
                break;
            case 20:
                if (c2 == hVar.a) {
                    hVar.h = ku.a(mVar);
                    break;
                }
                break;
        }
        mVar.d(c4 - mVar.c());
    }

    private static b a(m mVar) {
        int c;
        int c2;
        int i;
        int i2;
        mVar.b(4);
        boolean e = mVar.e();
        mVar.b(3);
        int c3 = mVar.c(16);
        int c4 = mVar.c(16);
        if (e) {
            int c5 = mVar.c(16);
            int c6 = mVar.c(16);
            c = mVar.c(16);
            c2 = mVar.c(16);
            i = c6;
            i2 = c;
            c = c5;
        } else {
            i = c3;
            c2 = c4;
            c = 0;
            i2 = 0;
        }
        return new b(c3, c4, c, i, i2, c2);
    }

    private static d a(m mVar, int i) {
        int c = mVar.c(8);
        int c2 = mVar.c(4);
        int c3 = mVar.c(2);
        mVar.b(2);
        i -= 2;
        SparseArray sparseArray = new SparseArray();
        while (i > 0) {
            int c4 = mVar.c(8);
            mVar.b(8);
            i -= 6;
            sparseArray.put(c4, new e(mVar.c(16), mVar.c(16)));
        }
        return new d(c, c2, c3, sparseArray);
    }

    private static f b(m mVar, int i) {
        m mVar2 = mVar;
        int c = mVar2.c(8);
        mVar2.b(4);
        boolean e = mVar.e();
        mVar2.b(3);
        int i2 = 16;
        int c2 = mVar2.c(16);
        int c3 = mVar2.c(16);
        int c4 = mVar2.c(3);
        int c5 = mVar2.c(3);
        int i3 = 2;
        mVar2.b(2);
        int c6 = mVar2.c(8);
        int c7 = mVar2.c(8);
        int c8 = mVar2.c(4);
        int c9 = mVar2.c(2);
        mVar2.b(2);
        int i4 = i - 10;
        SparseArray sparseArray = new SparseArray();
        while (i4 > 0) {
            int c10;
            int c11;
            int c12 = mVar2.c(i2);
            i2 = mVar2.c(i3);
            int c13 = mVar2.c(i3);
            int c14 = mVar2.c(12);
            int i5 = c9;
            mVar2.b(4);
            int c15 = mVar2.c(12);
            i4 -= 6;
            if (i2 == 1 || i2 == 2) {
                i4 -= 2;
                c10 = mVar2.c(8);
                c11 = mVar2.c(8);
            } else {
                c10 = 0;
                c11 = 0;
            }
            sparseArray.put(c12, new g(i2, c13, c14, c15, c10, c11));
            c9 = i5;
            i3 = 2;
            i2 = 16;
        }
        return new f(c, e, c2, c3, c4, c5, c6, c7, c8, c9, sparseArray);
    }

    private static a c(m mVar, int i) {
        m mVar2 = mVar;
        int i2 = 8;
        int c = mVar2.c(8);
        mVar2.b(8);
        int i3 = 2;
        int i4 = i - 2;
        int[] b = ku.b();
        int[] c2 = ku.c();
        int[] d = ku.d();
        while (i4 > 0) {
            int c3;
            int c4;
            int c5;
            int c6 = mVar2.c(i2);
            int c7 = mVar2.c(i2);
            i4 -= 2;
            int[] iArr = (c7 & 128) != 0 ? b : (c7 & 64) != 0 ? c2 : d;
            if ((c7 & 1) != 0) {
                c7 = mVar2.c(i2);
                c3 = mVar2.c(i2);
                c4 = mVar2.c(i2);
                c5 = mVar2.c(i2);
                i4 -= 4;
            } else {
                c4 = mVar2.c(4) << 4;
                i4 -= 2;
                c5 = mVar2.c(i3) << 6;
                c7 = mVar2.c(6) << i3;
                c3 = mVar2.c(4) << 4;
            }
            if (c7 == 0) {
                c3 = 0;
                c4 = 0;
                c5 = 255;
            }
            byte b2 = (byte) (255 - (c5 & 255));
            int i5 = i4;
            double d2 = (double) c7;
            int i6 = c;
            double d3 = (double) (c3 - 128);
            Double.isNaN(d3);
            double d4 = 1.402d * d3;
            Double.isNaN(d2);
            c7 = (int) (d2 + d4);
            double d5 = (double) (c4 - 128);
            Double.isNaN(d5);
            double d6 = 0.34414d * d5;
            Double.isNaN(d2);
            d6 = d2 - d6;
            Double.isNaN(d3);
            i2 = (int) (d6 - (d3 * 0.71414d));
            Double.isNaN(d5);
            d5 *= 1.772d;
            Double.isNaN(d2);
            iArr[c6] = ku.a(b2, z.a(c7, 0, 255), z.a(i2, 0, 255), z.a((int) (d2 + d5), 0, 255));
            i4 = i5;
            c = i6;
            i2 = 8;
            i3 = 2;
        }
        return new a(c, b, c2, d);
    }

    private static c b(m mVar) {
        byte[] bArr;
        int c = mVar.c(16);
        mVar.b(4);
        int c2 = mVar.c(2);
        boolean e = mVar.e();
        mVar.b(1);
        byte[] bArr2 = null;
        if (c2 == 1) {
            mVar.b(mVar.c(8) * 16);
        } else if (c2 == 0) {
            c2 = mVar.c(16);
            int c3 = mVar.c(16);
            if (c2 > 0) {
                bArr2 = new byte[c2];
                mVar.b(bArr2, 0, c2);
            }
            if (c3 > 0) {
                bArr = new byte[c3];
                mVar.b(bArr, 0, c3);
                return new c(c, e, bArr2, bArr);
            }
        }
        bArr = bArr2;
        return new c(c, e, bArr2, bArr);
    }

    private static int[] b() {
        return new int[]{0, -1, -16777216, -8421505};
    }

    private static int[] c() {
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i = 1; i < iArr.length; i++) {
            if (i < 8) {
                iArr[i] = ku.a(255, (i & 1) != 0 ? 255 : 0, (i & 2) != 0 ? 255 : 0, (i & 4) != 0 ? 255 : 0);
            } else {
                int i2 = 127;
                int i3 = (i & 1) != 0 ? 127 : 0;
                int i4 = (i & 2) != 0 ? 127 : 0;
                if ((i & 4) == 0) {
                    i2 = 0;
                }
                iArr[i] = ku.a(255, i3, i4, i2);
            }
        }
        return iArr;
    }

    private static int[] d() {
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i = 0; i < iArr.length; i++) {
            int i2 = 255;
            int i3;
            int i4;
            if (i < 8) {
                i3 = (i & 1) != 0 ? 255 : 0;
                i4 = (i & 2) != 0 ? 255 : 0;
                if ((i & 4) == 0) {
                    i2 = 0;
                }
                iArr[i] = ku.a(63, i3, i4, i2);
            } else {
                i3 = i & 136;
                i4 = 170;
                int i5 = 85;
                int i6;
                if (i3 == 0) {
                    i6 = ((i & 1) != 0 ? 85 : 0) + ((i & 16) != 0 ? 170 : 0);
                    i3 = ((i & 2) != 0 ? 85 : 0) + ((i & 32) != 0 ? 170 : 0);
                    if ((i & 4) == 0) {
                        i5 = 0;
                    }
                    if ((i & 64) == 0) {
                        i4 = 0;
                    }
                    iArr[i] = ku.a(255, i6, i3, i5 + i4);
                } else if (i3 != 8) {
                    i4 = 43;
                    if (i3 == 128) {
                        i6 = (((i & 1) != 0 ? 43 : 0) + 127) + ((i & 16) != 0 ? 85 : 0);
                        i3 = (((i & 2) != 0 ? 43 : 0) + 127) + ((i & 32) != 0 ? 85 : 0);
                        if ((i & 4) == 0) {
                            i4 = 0;
                        }
                        i4 += 127;
                        if ((i & 64) == 0) {
                            i5 = 0;
                        }
                        iArr[i] = ku.a(255, i6, i3, i4 + i5);
                    } else if (i3 == 136) {
                        i6 = ((i & 1) != 0 ? 43 : 0) + ((i & 16) != 0 ? 85 : 0);
                        i3 = ((i & 2) != 0 ? 43 : 0) + ((i & 32) != 0 ? 85 : 0);
                        if ((i & 4) == 0) {
                            i4 = 0;
                        }
                        if ((i & 64) == 0) {
                            i5 = 0;
                        }
                        iArr[i] = ku.a(255, i6, i3, i4 + i5);
                    }
                } else {
                    i6 = ((i & 1) != 0 ? 85 : 0) + ((i & 16) != 0 ? 170 : 0);
                    i2 = ((i & 2) != 0 ? 85 : 0) + ((i & 32) != 0 ? 170 : 0);
                    if ((i & 4) == 0) {
                        i5 = 0;
                    }
                    if ((i & 64) == 0) {
                        i4 = 0;
                    }
                    iArr[i] = ku.a(127, i6, i2, i5 + i4);
                }
            }
        }
        return iArr;
    }

    private static void a(c cVar, a aVar, int i, int i2, int i3, Paint paint, Canvas canvas) {
        int[] iArr;
        if (i == 3) {
            iArr = aVar.d;
        } else if (i == 2) {
            iArr = aVar.c;
        } else {
            iArr = aVar.b;
        }
        int[] iArr2 = iArr;
        int i4 = i;
        int i5 = i2;
        Paint paint2 = paint;
        Canvas canvas2 = canvas;
        ku.a(cVar.c, iArr2, i4, i5, i3, paint2, canvas2);
        ku.a(cVar.d, iArr2, i4, i5, i3 + 1, paint2, canvas2);
    }

    private static void a(byte[] bArr, int[] iArr, int i, int i2, int i3, Paint paint, Canvas canvas) {
        int i4 = i;
        byte[] bArr2 = bArr;
        m mVar = new m(bArr);
        int i5 = i2;
        int i6 = i3;
        byte[] bArr3 = null;
        byte[] bArr4 = bArr3;
        while (mVar.a() != 0) {
            int c = mVar.c(8);
            if (c != 240) {
                int a;
                switch (c) {
                    case 16:
                        byte[] bArr5;
                        if (i4 != 3) {
                            if (i4 != 2) {
                                bArr5 = null;
                                a = ku.a(mVar, iArr, bArr5, i5, i6, paint, canvas);
                                mVar.f();
                                break;
                            }
                            bArr2 = bArr4 == null ? a : bArr4;
                        } else {
                            bArr2 = bArr3 == null ? b : bArr3;
                        }
                        bArr5 = bArr2;
                        a = ku.a(mVar, iArr, bArr5, i5, i6, paint, canvas);
                        mVar.f();
                    case 17:
                        a = ku.b(mVar, iArr, i4 == 3 ? c : null, i5, i6, paint, canvas);
                        mVar.f();
                        break;
                    case 18:
                        a = ku.c(mVar, iArr, null, i5, i6, paint, canvas);
                        break;
                    default:
                        switch (c) {
                            case 32:
                                bArr4 = ku.a(4, 4, mVar);
                                continue;
                            case 33:
                                bArr2 = ku.a(4, 8, mVar);
                                break;
                            case 34:
                                bArr2 = ku.a(16, 8, mVar);
                                break;
                            default:
                                continue;
                                continue;
                        }
                        bArr3 = bArr2;
                        break;
                }
                i5 = a;
            } else {
                i6 += 2;
                i5 = i2;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x007e A:{LOOP_END, LOOP:0: B:1:0x0009->B:27:0x007e} */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x007d A:{SYNTHETIC} */
    private static int a(com.google.android.exoplayer2.util.m r13, int[] r14, byte[] r15, int r16, int r17, android.graphics.Paint r18, android.graphics.Canvas r19) {
        /*
        r0 = r13;
        r1 = r17;
        r8 = r18;
        r9 = 0;
        r10 = r16;
        r2 = 0;
    L_0x0009:
        r3 = 2;
        r4 = r13.c(r3);
        r5 = 1;
        if (r4 == 0) goto L_0x0015;
    L_0x0011:
        r12 = r2;
        r3 = r4;
    L_0x0013:
        r11 = 1;
        goto L_0x005c;
    L_0x0015:
        r4 = r13.e();
        if (r4 == 0) goto L_0x0028;
    L_0x001b:
        r4 = 3;
        r6 = r13.c(r4);
        r4 = r4 + r6;
        r3 = r13.c(r3);
    L_0x0025:
        r12 = r2;
        r11 = r4;
        goto L_0x005c;
    L_0x0028:
        r4 = r13.e();
        if (r4 == 0) goto L_0x0031;
    L_0x002e:
        r12 = r2;
        r3 = 0;
        goto L_0x0013;
    L_0x0031:
        r4 = r13.c(r3);
        switch(r4) {
            case 0: goto L_0x0059;
            case 1: goto L_0x0055;
            case 2: goto L_0x0049;
            case 3: goto L_0x003c;
            default: goto L_0x0038;
        };
    L_0x0038:
        r12 = r2;
        r3 = 0;
        r11 = 0;
        goto L_0x005c;
    L_0x003c:
        r4 = 8;
        r4 = r13.c(r4);
        r4 = r4 + 29;
        r3 = r13.c(r3);
        goto L_0x0025;
    L_0x0049:
        r4 = 4;
        r4 = r13.c(r4);
        r4 = r4 + 12;
        r3 = r13.c(r3);
        goto L_0x0025;
    L_0x0055:
        r12 = r2;
        r3 = 0;
        r11 = 2;
        goto L_0x005c;
    L_0x0059:
        r3 = 0;
        r11 = 0;
        r12 = 1;
    L_0x005c:
        if (r11 == 0) goto L_0x007a;
    L_0x005e:
        if (r8 == 0) goto L_0x007a;
    L_0x0060:
        if (r15 == 0) goto L_0x0064;
    L_0x0062:
        r3 = r15[r3];
    L_0x0064:
        r2 = r14[r3];
        r8.setColor(r2);
        r3 = (float) r10;
        r4 = (float) r1;
        r2 = r10 + r11;
        r6 = (float) r2;
        r2 = r1 + 1;
        r7 = (float) r2;
        r2 = r19;
        r5 = r6;
        r6 = r7;
        r7 = r18;
        r2.drawRect(r3, r4, r5, r6, r7);
    L_0x007a:
        r10 = r10 + r11;
        if (r12 == 0) goto L_0x007e;
    L_0x007d:
        return r10;
    L_0x007e:
        r2 = r12;
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ku.a(com.google.android.exoplayer2.util.m, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0089 A:{LOOP_END, LOOP:0: B:1:0x0009->B:30:0x0089} */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0088 A:{SYNTHETIC} */
    private static int b(com.google.android.exoplayer2.util.m r13, int[] r14, byte[] r15, int r16, int r17, android.graphics.Paint r18, android.graphics.Canvas r19) {
        /*
        r0 = r13;
        r1 = r17;
        r8 = r18;
        r9 = 0;
        r10 = r16;
        r2 = 0;
    L_0x0009:
        r3 = 4;
        r4 = r13.c(r3);
        r5 = 2;
        r6 = 1;
        if (r4 == 0) goto L_0x0016;
    L_0x0012:
        r12 = r2;
        r3 = r4;
    L_0x0014:
        r11 = 1;
        goto L_0x0069;
    L_0x0016:
        r4 = r13.e();
        if (r4 != 0) goto L_0x002d;
    L_0x001c:
        r3 = 3;
        r3 = r13.c(r3);
        if (r3 == 0) goto L_0x0029;
    L_0x0023:
        r3 = r3 + 2;
        r12 = r2;
        r11 = r3;
        r3 = 0;
        goto L_0x0069;
    L_0x0029:
        r3 = 0;
        r11 = 0;
        r12 = 1;
        goto L_0x0069;
    L_0x002d:
        r4 = r13.e();
        if (r4 != 0) goto L_0x003f;
    L_0x0033:
        r4 = r13.c(r5);
        r4 = r4 + r3;
        r3 = r13.c(r3);
    L_0x003c:
        r12 = r2;
        r11 = r4;
        goto L_0x0069;
    L_0x003f:
        r4 = r13.c(r5);
        switch(r4) {
            case 0: goto L_0x0066;
            case 1: goto L_0x0062;
            case 2: goto L_0x0057;
            case 3: goto L_0x004a;
            default: goto L_0x0046;
        };
    L_0x0046:
        r12 = r2;
        r3 = 0;
        r11 = 0;
        goto L_0x0069;
    L_0x004a:
        r4 = 8;
        r4 = r13.c(r4);
        r4 = r4 + 25;
        r3 = r13.c(r3);
        goto L_0x003c;
    L_0x0057:
        r4 = r13.c(r3);
        r4 = r4 + 9;
        r3 = r13.c(r3);
        goto L_0x003c;
    L_0x0062:
        r12 = r2;
        r3 = 0;
        r11 = 2;
        goto L_0x0069;
    L_0x0066:
        r12 = r2;
        r3 = 0;
        goto L_0x0014;
    L_0x0069:
        if (r11 == 0) goto L_0x0085;
    L_0x006b:
        if (r8 == 0) goto L_0x0085;
    L_0x006d:
        if (r15 == 0) goto L_0x0071;
    L_0x006f:
        r3 = r15[r3];
    L_0x0071:
        r2 = r14[r3];
        r8.setColor(r2);
        r3 = (float) r10;
        r4 = (float) r1;
        r2 = r10 + r11;
        r5 = (float) r2;
        r2 = r1 + 1;
        r6 = (float) r2;
        r2 = r19;
        r7 = r18;
        r2.drawRect(r3, r4, r5, r6, r7);
    L_0x0085:
        r10 = r10 + r11;
        if (r12 == 0) goto L_0x0089;
    L_0x0088:
        return r10;
    L_0x0089:
        r2 = r12;
        goto L_0x0009;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ku.b(com.google.android.exoplayer2.util.m, int[], byte[], int, int, android.graphics.Paint, android.graphics.Canvas):int");
    }

    private static int c(m mVar, int[] iArr, byte[] bArr, int i, int i2, Paint paint, Canvas canvas) {
        m mVar2 = mVar;
        int i3 = i2;
        Paint paint2 = paint;
        int i4 = i;
        Object obj = null;
        while (true) {
            Object obj2;
            int i5;
            int i6;
            int c = mVar.c(8);
            if (c != 0) {
                obj2 = obj;
                i5 = c;
                i6 = 1;
            } else if (mVar.e()) {
                c = mVar.c(7);
                i5 = mVar.c(8);
                obj2 = obj;
                i6 = c;
            } else {
                i5 = mVar.c(7);
                if (i5 != 0) {
                    obj2 = obj;
                    i6 = i5;
                    i5 = 0;
                } else {
                    i5 = 0;
                    i6 = 0;
                    obj2 = 1;
                }
            }
            if (!(i6 == 0 || paint2 == null)) {
                if (bArr != null) {
                    i5 = bArr[i5];
                }
                paint2.setColor(iArr[i5]);
                Canvas canvas2 = canvas;
                canvas2.drawRect((float) i4, (float) i3, (float) (i4 + i6), (float) (i3 + 1), paint);
            }
            i4 += i6;
            if (obj2 != null) {
                return i4;
            }
            obj = obj2;
        }
    }

    private static byte[] a(int i, int i2, m mVar) {
        byte[] bArr = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) mVar.c(i2);
        }
        return bArr;
    }
}
