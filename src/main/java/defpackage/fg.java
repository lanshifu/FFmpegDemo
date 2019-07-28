package defpackage;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

/* compiled from: VideoInfo */
/* renamed from: fg */
public class fg {
    private String a;
    private File b;
    private File c;
    private File d;
    private Deque<File> e = new LinkedList();
    private long f;

    public fg(File file) {
        this.b = file;
        if (file != null) {
            b(file);
            this.a = file.getName();
        }
    }

    public long a(File file) {
        if (file == null) {
            return 0;
        }
        this.e.addFirst(file);
        long length = file.length();
        this.f += length;
        return length;
    }

    public long a() {
        if (this.e == null || this.e.size() == 0) {
            return 0;
        }
        File file = (File) this.e.getLast();
        long length = file.length();
        this.f -= length;
        this.e.removeLast();
        es.a(file.getAbsolutePath());
        b a = aat.a("TTT");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("del removeLast :");
        stringBuilder.append(file.getAbsolutePath());
        a.a(stringBuilder.toString(), new Object[0]);
        return length;
    }

    public boolean b() {
        return this.e.size() != 0;
    }

    public void b(File file) {
        if (file != null && file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : new es().a(listFiles)) {
                    if (file2 != null && file2.exists()) {
                        if (file2.isFile()) {
                            if (file2.getPath().toLowerCase().endsWith("ts")) {
                                this.e.addLast(file2);
                            }
                            if (file2.getPath().toLowerCase().endsWith("m3u8")) {
                                this.c = file2;
                            }
                            if (file2.getPath().toLowerCase().endsWith("key")) {
                                this.d = file2;
                            }
                            this.f += file2.length();
                        }
                    }
                }
            }
        }
    }

    public String c() {
        return this.a;
    }

    public File d() {
        return this.b;
    }

    public long e() {
        return this.f;
    }
}
