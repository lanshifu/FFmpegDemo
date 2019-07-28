package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

final class SegmentedByteString extends ByteString {
    final transient int[] directory;
    final transient byte[][] segments;

    SegmentedByteString(c cVar, int i) {
        super(null);
        t.a(cVar.b, 0, (long) i);
        int i2 = 0;
        o oVar = cVar.a;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (oVar.c != oVar.b) {
                i3 += oVar.c - oVar.b;
                i4++;
                oVar = oVar.f;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        this.segments = new byte[i4][];
        this.directory = new int[(i4 * 2)];
        o oVar2 = cVar.a;
        int i5 = 0;
        while (i2 < i) {
            this.segments[i5] = oVar2.a;
            i2 += oVar2.c - oVar2.b;
            if (i2 > i) {
                i2 = i;
            }
            this.directory[i5] = i2;
            this.directory[this.segments.length + i5] = oVar2.b;
            oVar2.d = true;
            i5++;
            oVar2 = oVar2.f;
        }
    }

    public String utf8() {
        return a().utf8();
    }

    public String string(Charset charset) {
        return a().string(charset);
    }

    public String base64() {
        return a().base64();
    }

    public String hex() {
        return a().hex();
    }

    public ByteString toAsciiLowercase() {
        return a().toAsciiLowercase();
    }

    public ByteString toAsciiUppercase() {
        return a().toAsciiUppercase();
    }

    public ByteString md5() {
        return a().md5();
    }

    public ByteString sha1() {
        return a().sha1();
    }

    public ByteString sha256() {
        return a().sha256();
    }

    public ByteString hmacSha1(ByteString byteString) {
        return a().hmacSha1(byteString);
    }

    public ByteString hmacSha256(ByteString byteString) {
        return a().hmacSha256(byteString);
    }

    public String base64Url() {
        return a().base64Url();
    }

    public ByteString substring(int i) {
        return a().substring(i);
    }

    public ByteString substring(int i, int i2) {
        return a().substring(i, i2);
    }

    public byte getByte(int i) {
        int i2;
        t.a((long) this.directory[this.segments.length - 1], (long) i, 1);
        int a = a(i);
        if (a == 0) {
            i2 = 0;
        } else {
            i2 = this.directory[a - 1];
        }
        return this.segments[a][(i - i2) + this.directory[this.segments.length + a]];
    }

    private int a(int i) {
        i = Arrays.binarySearch(this.directory, 0, this.segments.length, i + 1);
        return i >= 0 ? i : i ^ -1;
    }

    public int size() {
        return this.directory[this.segments.length - 1];
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[this.directory[this.segments.length - 1]];
        int length = this.segments.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.directory[length + i];
            int i4 = this.directory[i];
            System.arraycopy(this.segments[i], i3, bArr, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr;
    }

    public ByteBuffer asByteBuffer() {
        return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
    }

    public void write(OutputStream outputStream) throws IOException {
        if (outputStream != null) {
            int length = this.segments.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = this.directory[length + i];
                int i4 = this.directory[i];
                outputStream.write(this.segments[i], i3, i4 - i2);
                i++;
                i2 = i4;
            }
            return;
        }
        throw new IllegalArgumentException("out == null");
    }

    /* Access modifiers changed, original: 0000 */
    public void write(c cVar) {
        int length = this.segments.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.directory[length + i];
            int i4 = this.directory[i];
            o oVar = new o(this.segments[i], i3, (i3 + i4) - i2, true, false);
            if (cVar.a == null) {
                oVar.g = oVar;
                oVar.f = oVar;
                cVar.a = oVar;
            } else {
                cVar.a.g.a(oVar);
            }
            i++;
            i2 = i4;
        }
        cVar.b += (long) i2;
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int a = a(i);
        while (i3 > 0) {
            int i4;
            if (a == 0) {
                i4 = 0;
            } else {
                i4 = this.directory[a - 1];
            }
            int min = Math.min(i3, ((this.directory[a] - i4) + i4) - i);
            if (!byteString.rangeEquals(i2, this.segments[a], (i - i4) + this.directory[this.segments.length + a], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            a++;
        }
        return true;
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int a = a(i);
        while (i3 > 0) {
            int i4;
            if (a == 0) {
                i4 = 0;
            } else {
                i4 = this.directory[a - 1];
            }
            int min = Math.min(i3, ((this.directory[a] - i4) + i4) - i);
            if (!t.a(this.segments[a], (i - i4) + this.directory[this.segments.length + a], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            a++;
        }
        return true;
    }

    public int indexOf(byte[] bArr, int i) {
        return a().indexOf(bArr, i);
    }

    public int lastIndexOf(byte[] bArr, int i) {
        return a().lastIndexOf(bArr, i);
    }

    private ByteString a() {
        return new ByteString(toByteArray());
    }

    /* Access modifiers changed, original: 0000 */
    public byte[] internalArray() {
        return toByteArray();
    }

    /* JADX WARNING: Missing block: B:8:0x001d, code skipped:
            if (rangeEquals(0, r5, 0, size()) != false) goto L_0x0021;
     */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        if (r5 != r4) goto L_0x0004;
    L_0x0003:
        return r0;
    L_0x0004:
        r1 = r5 instanceof okio.ByteString;
        r2 = 0;
        if (r1 == 0) goto L_0x0020;
    L_0x0009:
        r5 = (okio.ByteString) r5;
        r1 = r5.size();
        r3 = r4.size();
        if (r1 != r3) goto L_0x0020;
    L_0x0015:
        r1 = r4.size();
        r5 = r4.rangeEquals(r2, r5, r2, r1);
        if (r5 == 0) goto L_0x0020;
    L_0x001f:
        goto L_0x0021;
    L_0x0020:
        r0 = 0;
    L_0x0021:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.SegmentedByteString.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        i = this.segments.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < i) {
            byte[] bArr = this.segments[i2];
            int i5 = this.directory[i + i2];
            int i6 = this.directory[i2];
            i3 = (i6 - i3) + i5;
            while (i5 < i3) {
                i4 = (i4 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i3 = i6;
        }
        this.hashCode = i4;
        return i4;
    }

    public String toString() {
        return a().toString();
    }

    private Object writeReplace() {
        return a();
    }
}
