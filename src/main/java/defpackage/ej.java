package defpackage;

import com.danikula.videocache.ProxyCacheException;
import com.danikula.videocache.a;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* compiled from: FileCache */
/* renamed from: ej */
public class ej implements a {
    public File a;
    private final ei b;
    private RandomAccessFile c;

    public ej(File file, ei eiVar) throws ProxyCacheException {
        if (eiVar != null) {
            try {
                File file2;
                this.b = eiVar;
                em.a(file.getParentFile());
                boolean exists = file.exists();
                if (exists) {
                    file2 = file;
                } else {
                    File parentFile = file.getParentFile();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(file.getName());
                    stringBuilder.append(".download");
                    file2 = new File(parentFile, stringBuilder.toString());
                }
                this.a = file2;
                this.c = new RandomAccessFile(this.a, exists ? "r" : "rw");
                return;
            } catch (IOException e) {
                e.printStackTrace();
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Error using file ");
                stringBuilder2.append(file);
                stringBuilder2.append(" as disc cache");
                throw new ProxyCacheException(stringBuilder2.toString(), e);
            }
        }
        throw new NullPointerException();
    }

    public synchronized long a() throws ProxyCacheException {
        try {
        } catch (IOException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error reading length of file ");
            stringBuilder.append(this.a);
            throw new ProxyCacheException(stringBuilder.toString(), e);
        }
        return (long) ((int) this.c.length());
    }

    public synchronized int a(byte[] bArr, long j, int i) throws ProxyCacheException {
        try {
            this.c.seek(j);
        } catch (IOException e) {
            throw new ProxyCacheException(String.format("Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", new Object[]{Integer.valueOf(i), Long.valueOf(j), Long.valueOf(a()), Integer.valueOf(bArr.length)}), e);
        }
        return this.c.read(bArr, 0, i);
    }

    public synchronized void a(byte[] bArr, int i) throws ProxyCacheException {
        try {
            if (d()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Error append cache: cache file ");
                stringBuilder.append(this.a);
                stringBuilder.append(" is completed!");
                throw new ProxyCacheException(stringBuilder.toString());
            }
            this.c.seek(a());
            this.c.write(bArr, 0, i);
        } catch (IOException e) {
            throw new ProxyCacheException(String.format("Error writing %d bytes to %s from buffer with size %d", new Object[]{Integer.valueOf(i), this.c, Integer.valueOf(bArr.length)}), e);
        }
    }

    public synchronized void b() throws ProxyCacheException {
        try {
            this.c.close();
            this.b.a(this.a);
        } catch (IOException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Error closing file ");
            stringBuilder.append(this.a);
            throw new ProxyCacheException(stringBuilder.toString(), e);
        }
    }

    public synchronized void c() throws ProxyCacheException {
        StringBuilder stringBuilder;
        if (!d()) {
            b();
            File file = new File(this.a.getParentFile(), this.a.getName().substring(0, this.a.getName().length() - ".download".length()));
            if (this.a.renameTo(file)) {
                this.a = file;
                try {
                    this.c = new RandomAccessFile(this.a, "r");
                    this.b.a(this.a);
                    return;
                } catch (IOException e) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("Error opening ");
                    stringBuilder.append(this.a);
                    stringBuilder.append(" as disc cache");
                    throw new ProxyCacheException(stringBuilder.toString(), e);
                }
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Error renaming file ");
            stringBuilder.append(this.a);
            stringBuilder.append(" to ");
            stringBuilder.append(file);
            stringBuilder.append(" for completion!");
            throw new ProxyCacheException(stringBuilder.toString());
        }
    }

    public synchronized boolean d() {
        return a(this.a) ^ 1;
    }

    public File e() {
        return this.a;
    }

    private boolean a(File file) {
        return file.getName().endsWith(".download");
    }
}
