package defpackage;

import java.lang.reflect.Array;

/* compiled from: ByteMatrix */
/* renamed from: ly */
public final class ly {
    private final byte[][] a;
    private final int b;
    private final int c;

    public ly(int i, int i2) {
        this.a = (byte[][]) Array.newInstance(byte.class, new int[]{i2, i});
        this.b = i;
        this.c = i2;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public byte a(int i, int i2) {
        return this.a[i2][i];
    }

    public byte[][] c() {
        return this.a;
    }

    public void a(int i, int i2, int i3) {
        this.a[i2][i] = (byte) i3;
    }

    public void a(int i, int i2, boolean z) {
        this.a[i2][i] = (byte) z;
    }

    public void a(byte b) {
        for (int i = 0; i < this.c; i++) {
            for (int i2 = 0; i2 < this.b; i2++) {
                this.a[i][i2] = b;
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(((this.b * 2) * this.c) + 2);
        for (int i = 0; i < this.c; i++) {
            for (int i2 = 0; i2 < this.b; i2++) {
                switch (this.a[i][i2]) {
                    case (byte) 0:
                        stringBuilder.append(" 0");
                        break;
                    case (byte) 1:
                        stringBuilder.append(" 1");
                        break;
                    default:
                        stringBuilder.append("  ");
                        break;
                }
            }
            stringBuilder.append(10);
        }
        return stringBuilder.toString();
    }
}
