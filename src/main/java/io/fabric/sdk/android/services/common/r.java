package io.fabric.sdk.android.services.common;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: QueueFile */
public class r implements Closeable {
    private static final Logger b = Logger.getLogger(r.class.getName());
    int a;
    private final RandomAccessFile c;
    private int d;
    private a e;
    private a f;
    private final byte[] g = new byte[16];

    /* compiled from: QueueFile */
    static class a {
        static final a a = new a(0, 0);
        final int b;
        final int c;

        a(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getClass().getSimpleName());
            stringBuilder.append("[position = ");
            stringBuilder.append(this.b);
            stringBuilder.append(", length = ");
            stringBuilder.append(this.c);
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    /* compiled from: QueueFile */
    private final class b extends InputStream {
        private int b;
        private int c;

        /* synthetic */ b(r rVar, a aVar, AnonymousClass1 anonymousClass1) {
            this(aVar);
        }

        private b(a aVar) {
            this.b = r.this.b(aVar.b + 4);
            this.c = aVar.c;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            r.b(bArr, "buffer");
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (this.c <= 0) {
                return -1;
            } else {
                if (i2 > this.c) {
                    i2 = this.c;
                }
                r.this.b(this.b, bArr, i, i2);
                this.b = r.this.b(this.b + i2);
                this.c -= i2;
                return i2;
            }
        }

        public int read() throws IOException {
            if (this.c == 0) {
                return -1;
            }
            r.this.c.seek((long) this.b);
            int read = r.this.c.read();
            this.b = r.this.b(this.b + 1);
            this.c--;
            return read;
        }
    }

    /* compiled from: QueueFile */
    public interface c {
        void a(InputStream inputStream, int i) throws IOException;
    }

    public r(File file) throws IOException {
        if (!file.exists()) {
            a(file);
        }
        this.c = b(file);
        e();
    }

    private static void b(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void a(byte[] bArr, int... iArr) {
        int i = 0;
        for (int b : iArr) {
            b(bArr, i, b);
            i += 4;
        }
    }

    private static int a(byte[] bArr, int i) {
        return ((((bArr[i] & 255) << 24) + ((bArr[i + 1] & 255) << 16)) + ((bArr[i + 2] & 255) << 8)) + (bArr[i + 3] & 255);
    }

    private void e() throws IOException {
        this.c.seek(0);
        this.c.readFully(this.g);
        this.a = a(this.g, 0);
        if (((long) this.a) <= this.c.length()) {
            this.d = a(this.g, 4);
            int a = a(this.g, 8);
            int a2 = a(this.g, 12);
            this.e = a(a);
            this.f = a(a2);
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("File is truncated. Expected length: ");
        stringBuilder.append(this.a);
        stringBuilder.append(", Actual length: ");
        stringBuilder.append(this.c.length());
        throw new IOException(stringBuilder.toString());
    }

    private void a(int i, int i2, int i3, int i4) throws IOException {
        a(this.g, i, i2, i3, i4);
        this.c.seek(0);
        this.c.write(this.g);
    }

    private a a(int i) throws IOException {
        if (i == 0) {
            return a.a;
        }
        this.c.seek((long) i);
        return new a(i, this.c.readInt());
    }

    private static void a(File file) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(file.getPath());
        stringBuilder.append(".tmp");
        File file2 = new File(stringBuilder.toString());
        RandomAccessFile b = b(file2);
        try {
            b.setLength(IjkMediaMeta.AV_CH_TOP_FRONT_LEFT);
            b.seek(0);
            byte[] bArr = new byte[16];
            a(bArr, 4096, 0, 0, 0);
            b.write(bArr);
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } finally {
            b.close();
        }
    }

    private static RandomAccessFile b(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    private int b(int i) {
        return i < this.a ? i : (i + 16) - this.a;
    }

    private void a(int i, byte[] bArr, int i2, int i3) throws IOException {
        i = b(i);
        if (i + i3 <= this.a) {
            this.c.seek((long) i);
            this.c.write(bArr, i2, i3);
            return;
        }
        int i4 = this.a - i;
        this.c.seek((long) i);
        this.c.write(bArr, i2, i4);
        this.c.seek(16);
        this.c.write(bArr, i2 + i4, i3 - i4);
    }

    private void b(int i, byte[] bArr, int i2, int i3) throws IOException {
        i = b(i);
        if (i + i3 <= this.a) {
            this.c.seek((long) i);
            this.c.readFully(bArr, i2, i3);
            return;
        }
        int i4 = this.a - i;
        this.c.seek((long) i);
        this.c.readFully(bArr, i2, i4);
        this.c.seek(16);
        this.c.readFully(bArr, i2 + i4, i3 - i4);
    }

    public void a(byte[] bArr) throws IOException {
        a(bArr, 0, bArr.length);
    }

    public synchronized void a(byte[] bArr, int i, int i2) throws IOException {
        b(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        int i3;
        c(i2);
        boolean b = b();
        if (b) {
            i3 = 16;
        } else {
            i3 = b((this.f.b + 4) + this.f.c);
        }
        a aVar = new a(i3, i2);
        b(this.g, 0, i2);
        a(aVar.b, this.g, 0, 4);
        a(aVar.b + 4, bArr, i, i2);
        a(this.a, this.d + 1, b ? aVar.b : this.e.b, aVar.b);
        this.f = aVar;
        this.d++;
        if (b) {
            this.e = this.f;
        }
    }

    public int a() {
        if (this.d == 0) {
            return 16;
        }
        if (this.f.b >= this.e.b) {
            return (((this.f.b - this.e.b) + 4) + this.f.c) + 16;
        }
        return (((this.f.b + 4) + this.f.c) + this.a) - this.e.b;
    }

    private int f() {
        return this.a - a();
    }

    public synchronized boolean b() {
        return this.d == 0;
    }

    private void c(int i) throws IOException {
        i += 4;
        int f = f();
        if (f < i) {
            int i2 = this.a;
            do {
                f += i2;
                i2 <<= 1;
            } while (f < i);
            d(i2);
            i = b((this.f.b + 4) + this.f.c);
            if (i < this.e.b) {
                FileChannel channel = this.c.getChannel();
                channel.position((long) this.a);
                long j = (long) (i - 4);
                if (channel.transferTo(16, j, channel) != j) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.f.b < this.e.b) {
                i = (this.a + this.f.b) - 16;
                a(i2, this.d, this.e.b, i);
                this.f = new a(i, this.f.c);
            } else {
                a(i2, this.d, this.e.b, this.f.b);
            }
            this.a = i2;
        }
    }

    private void d(int i) throws IOException {
        this.c.setLength((long) i);
        this.c.getChannel().force(true);
    }

    public synchronized void a(c cVar) throws IOException {
        int i = this.e.b;
        for (int i2 = 0; i2 < this.d; i2++) {
            a a = a(i);
            cVar.a(new b(this, a, null), a.c);
            i = b((a.b + 4) + a.c);
        }
    }

    private static <T> T b(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public synchronized void c() throws IOException {
        if (b()) {
            throw new NoSuchElementException();
        } else if (this.d == 1) {
            d();
        } else {
            int b = b((this.e.b + 4) + this.e.c);
            b(b, this.g, 0, 4);
            int a = a(this.g, 0);
            a(this.a, this.d - 1, b, this.f.b);
            this.d--;
            this.e = new a(b, a);
        }
    }

    public synchronized void d() throws IOException {
        a(4096, 0, 0, 0);
        this.d = 0;
        this.e = a.a;
        this.f = a.a;
        if (this.a > 4096) {
            d(4096);
        }
        this.a = 4096;
    }

    public synchronized void close() throws IOException {
        this.c.close();
    }

    public boolean a(int i, int i2) {
        return (a() + 4) + i <= i2;
    }

    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append('[');
        stringBuilder.append("fileLength=");
        stringBuilder.append(this.a);
        stringBuilder.append(", size=");
        stringBuilder.append(this.d);
        stringBuilder.append(", first=");
        stringBuilder.append(this.e);
        stringBuilder.append(", last=");
        stringBuilder.append(this.f);
        stringBuilder.append(", element lengths=[");
        try {
            a(new c() {
                boolean a = true;

                public void a(InputStream inputStream, int i) throws IOException {
                    if (this.a) {
                        this.a = false;
                    } else {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(i);
                }
            });
        } catch (IOException e) {
            b.log(Level.WARNING, "read error", e);
        }
        stringBuilder.append("]]");
        return stringBuilder.toString();
    }
}
