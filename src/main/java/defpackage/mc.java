package defpackage;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;

/* compiled from: QRCode */
/* renamed from: mc */
public final class mc {
    private Mode a = null;
    private ErrorCorrectionLevel b = null;
    private int c = -1;
    private int d = -1;
    private int e = -1;
    private int f = -1;
    private int g = -1;
    private int h = -1;
    private int i = -1;
    private ly j = null;

    public static boolean h(int i) {
        return i >= 0 && i < 8;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }

    public int e() {
        return this.g;
    }

    public int f() {
        return this.i;
    }

    public ly g() {
        return this.j;
    }

    public boolean h() {
        return (this.a == null || this.b == null || this.c == -1 || this.d == -1 || this.e == -1 || this.f == -1 || this.g == -1 || this.h == -1 || this.i == -1 || !mc.h(this.e) || this.f != this.g + this.h || this.j == null || this.d != this.j.b() || this.j.b() != this.j.a()) ? false : true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(200);
        stringBuilder.append("<<\n");
        stringBuilder.append(" mode: ");
        stringBuilder.append(this.a);
        stringBuilder.append("\n ecLevel: ");
        stringBuilder.append(this.b);
        stringBuilder.append("\n version: ");
        stringBuilder.append(this.c);
        stringBuilder.append("\n matrixWidth: ");
        stringBuilder.append(this.d);
        stringBuilder.append("\n maskPattern: ");
        stringBuilder.append(this.e);
        stringBuilder.append("\n numTotalBytes: ");
        stringBuilder.append(this.f);
        stringBuilder.append("\n numDataBytes: ");
        stringBuilder.append(this.g);
        stringBuilder.append("\n numECBytes: ");
        stringBuilder.append(this.h);
        stringBuilder.append("\n numRSBlocks: ");
        stringBuilder.append(this.i);
        if (this.j == null) {
            stringBuilder.append("\n matrix: null\n");
        } else {
            stringBuilder.append("\n matrix:\n");
            stringBuilder.append(this.j.toString());
        }
        stringBuilder.append(">>\n");
        return stringBuilder.toString();
    }

    public void a(Mode mode) {
        this.a = mode;
    }

    public void a(ErrorCorrectionLevel errorCorrectionLevel) {
        this.b = errorCorrectionLevel;
    }

    public void a(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public void c(int i) {
        this.e = i;
    }

    public void d(int i) {
        this.f = i;
    }

    public void e(int i) {
        this.g = i;
    }

    public void f(int i) {
        this.h = i;
    }

    public void g(int i) {
        this.i = i;
    }

    public void a(ly lyVar) {
        this.j = lyVar;
    }
}
