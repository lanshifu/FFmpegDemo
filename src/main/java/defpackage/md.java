package defpackage;

import com.tomatolive.library.http.exception.ExceptionEngine;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* compiled from: DiskLruCache */
/* renamed from: md */
public final class md implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,64}");
    private static final OutputStream p = new 2();
    final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final File c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private long h;
    private final int i;
    private long j = 0;
    private Writer k;
    private final LinkedHashMap<String, b> l = new LinkedHashMap(0, 0.75f, true);
    private int m;
    private long n = 0;
    private final Callable<Void> o = new 1();

    /* compiled from: DiskLruCache */
    /* renamed from: md$1 */
    class 1 implements Callable<Void> {
        1() {
        }

        /* JADX WARNING: Missing block: B:11:0x0027, code skipped:
            return null;
     */
        /* renamed from: a */
        public java.lang.Void call() throws java.lang.Exception {
            /*
            r4 = this;
            r0 = defpackage.md.this;
            monitor-enter(r0);
            r1 = defpackage.md.this;	 Catch:{ all -> 0x0028 }
            r1 = r1.k;	 Catch:{ all -> 0x0028 }
            r2 = 0;
            if (r1 != 0) goto L_0x000e;
        L_0x000c:
            monitor-exit(r0);	 Catch:{ all -> 0x0028 }
            return r2;
        L_0x000e:
            r1 = defpackage.md.this;	 Catch:{ all -> 0x0028 }
            r1.h();	 Catch:{ all -> 0x0028 }
            r1 = defpackage.md.this;	 Catch:{ all -> 0x0028 }
            r1 = r1.f();	 Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0026;
        L_0x001b:
            r1 = defpackage.md.this;	 Catch:{ all -> 0x0028 }
            r1.e();	 Catch:{ all -> 0x0028 }
            r1 = defpackage.md.this;	 Catch:{ all -> 0x0028 }
            r3 = 0;
            r1.m = r3;	 Catch:{ all -> 0x0028 }
        L_0x0026:
            monitor-exit(r0);	 Catch:{ all -> 0x0028 }
            return r2;
        L_0x0028:
            r1 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0028 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.md$1.call():java.lang.Void");
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: md$2 */
    static class 2 extends OutputStream {
        public void write(int i) throws IOException {
        }

        2() {
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: md$a */
    public final class a {
        private final b b;
        private final boolean[] c;
        private boolean d;
        private boolean e;

        /* compiled from: DiskLruCache */
        /* renamed from: md$a$a */
        private class a extends FilterOutputStream {
            /* synthetic */ a(a aVar, OutputStream outputStream, 1 1) {
                this(outputStream);
            }

            private a(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    a.this.d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    a.this.d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    a.this.d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    a.this.d = true;
                }
            }
        }

        /* synthetic */ a(md mdVar, b bVar, 1 1) {
            this(bVar);
        }

        private a(b bVar) {
            this.b = bVar;
            this.c = bVar.d ? null : new boolean[md.this.i];
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0024 */
        /* JADX WARNING: Can't wrap try/catch for region: R(12:5|(1:7)|8|9|10|11|12|13|14|15|16|17) */
        public java.io.OutputStream a(int r4) throws java.io.IOException {
            /*
            r3 = this;
            r0 = defpackage.md.this;
            monitor-enter(r0);
            r1 = r3.b;	 Catch:{ all -> 0x0046 }
            r1 = r1.e;	 Catch:{ all -> 0x0046 }
            if (r1 != r3) goto L_0x0040;
        L_0x000b:
            r1 = r3.b;	 Catch:{ all -> 0x0046 }
            r1 = r1.d;	 Catch:{ all -> 0x0046 }
            if (r1 != 0) goto L_0x0018;
        L_0x0013:
            r1 = r3.c;	 Catch:{ all -> 0x0046 }
            r2 = 1;
            r1[r4] = r2;	 Catch:{ all -> 0x0046 }
        L_0x0018:
            r1 = r3.b;	 Catch:{ all -> 0x0046 }
            r4 = r1.b(r4);	 Catch:{ all -> 0x0046 }
            r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x0024 }
            r1.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0024 }
            goto L_0x0032;
        L_0x0024:
            r1 = defpackage.md.this;	 Catch:{ all -> 0x0046 }
            r1 = r1.c;	 Catch:{ all -> 0x0046 }
            r1.mkdirs();	 Catch:{ all -> 0x0046 }
            r1 = new java.io.FileOutputStream;	 Catch:{ FileNotFoundException -> 0x003a }
            r1.<init>(r4);	 Catch:{ FileNotFoundException -> 0x003a }
        L_0x0032:
            r4 = new md$a$a;	 Catch:{ all -> 0x0046 }
            r2 = 0;
            r4.<init>(r3, r1, r2);	 Catch:{ all -> 0x0046 }
            monitor-exit(r0);	 Catch:{ all -> 0x0046 }
            return r4;
        L_0x003a:
            r4 = defpackage.md.p;	 Catch:{ all -> 0x0046 }
            monitor-exit(r0);	 Catch:{ all -> 0x0046 }
            return r4;
        L_0x0040:
            r4 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0046 }
            r4.<init>();	 Catch:{ all -> 0x0046 }
            throw r4;	 Catch:{ all -> 0x0046 }
        L_0x0046:
            r4 = move-exception;
            monitor-exit(r0);	 Catch:{ all -> 0x0046 }
            throw r4;
            */
            throw new UnsupportedOperationException("Method not decompiled: defpackage.md$a.a(int):java.io.OutputStream");
        }

        public void a() throws IOException {
            if (this.d) {
                md.this.a(this, false);
                md.this.c(this.b.b);
            } else {
                md.this.a(this, true);
            }
            this.e = true;
        }

        public void b() throws IOException {
            md.this.a(this, false);
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: md$b */
    private final class b {
        private final String b;
        private final long[] c;
        private boolean d;
        private a e;
        private long f;

        /* synthetic */ b(md mdVar, String str, 1 1) {
            this(str);
        }

        private b(String str) {
            this.b = str;
            this.c = new long[md.this.i];
        }

        public String a() throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (long j : this.c) {
                stringBuilder.append(' ');
                stringBuilder.append(j);
            }
            return stringBuilder.toString();
        }

        private void a(String[] strArr) throws IOException {
            if (strArr.length == md.this.i) {
                int i = 0;
                while (i < strArr.length) {
                    try {
                        this.c[i] = Long.parseLong(strArr[i]);
                        i++;
                    } catch (NumberFormatException unused) {
                        throw b(strArr);
                    }
                }
                return;
            }
            throw b(strArr);
        }

        private IOException b(String[] strArr) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("unexpected journal line: ");
            stringBuilder.append(Arrays.toString(strArr));
            throw new IOException(stringBuilder.toString());
        }

        public File a(int i) {
            File f = md.this.c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.b);
            stringBuilder.append(".");
            stringBuilder.append(i);
            return new File(f, stringBuilder.toString());
        }

        public File b(int i) {
            File f = md.this.c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.b);
            stringBuilder.append(".");
            stringBuilder.append(i);
            stringBuilder.append(".tmp");
            return new File(f, stringBuilder.toString());
        }
    }

    /* compiled from: DiskLruCache */
    /* renamed from: md$c */
    public final class c implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        /* synthetic */ c(md mdVar, String str, long j, InputStream[] inputStreamArr, long[] jArr, 1 1) {
            this(str, j, inputStreamArr, jArr);
        }

        private c(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public InputStream a(int i) {
            return this.d[i];
        }

        public void close() {
            for (Closeable a : this.d) {
                mf.a(a);
            }
        }
    }

    private md(File file, int i, int i2, long j) {
        File file2 = file;
        this.c = file2;
        this.g = i;
        this.d = new File(file2, "journal");
        this.e = new File(file2, "journal.tmp");
        this.f = new File(file2, "journal.bkp");
        this.i = i2;
        this.h = j;
    }

    public static md a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 > 0) {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    md.a(file2, file3, false);
                }
            }
            md mdVar = new md(file, i, i2, j);
            if (mdVar.d.exists()) {
                try {
                    mdVar.c();
                    mdVar.d();
                    mdVar.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mdVar.d, true), mf.a));
                    return mdVar;
                } catch (IOException e) {
                    PrintStream printStream = System.out;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("DiskLruCache ");
                    stringBuilder.append(file);
                    stringBuilder.append(" is corrupt: ");
                    stringBuilder.append(e.getMessage());
                    stringBuilder.append(", removing");
                    printStream.println(stringBuilder.toString());
                    mdVar.a();
                }
            }
            file.mkdirs();
            mdVar = new md(file, i, i2, j);
            mdVar.e();
            return mdVar;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x006a=Splitter:B:19:0x006a, B:16:0x005d=Splitter:B:16:0x005d} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005d */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:16|17|24) */
    /* JADX WARNING: Missing block: B:17:?, code skipped:
            r8.m = r1 - r8.l.size();
     */
    private void c() throws java.io.IOException {
        /*
        r8 = this;
        r0 = new me;
        r1 = new java.io.FileInputStream;
        r2 = r8.d;
        r1.<init>(r2);
        r2 = defpackage.mf.a;
        r0.<init>(r1, r2);
        r1 = r0.a();	 Catch:{ all -> 0x009e }
        r2 = r0.a();	 Catch:{ all -> 0x009e }
        r3 = r0.a();	 Catch:{ all -> 0x009e }
        r4 = r0.a();	 Catch:{ all -> 0x009e }
        r5 = r0.a();	 Catch:{ all -> 0x009e }
        r6 = "libcore.io.DiskLruCache";
        r6 = r6.equals(r1);	 Catch:{ all -> 0x009e }
        if (r6 == 0) goto L_0x006a;
    L_0x002a:
        r6 = "1";
        r6 = r6.equals(r2);	 Catch:{ all -> 0x009e }
        if (r6 == 0) goto L_0x006a;
    L_0x0032:
        r6 = r8.g;	 Catch:{ all -> 0x009e }
        r6 = java.lang.Integer.toString(r6);	 Catch:{ all -> 0x009e }
        r3 = r6.equals(r3);	 Catch:{ all -> 0x009e }
        if (r3 == 0) goto L_0x006a;
    L_0x003e:
        r3 = r8.i;	 Catch:{ all -> 0x009e }
        r3 = java.lang.Integer.toString(r3);	 Catch:{ all -> 0x009e }
        r3 = r3.equals(r4);	 Catch:{ all -> 0x009e }
        if (r3 == 0) goto L_0x006a;
    L_0x004a:
        r3 = "";
        r3 = r3.equals(r5);	 Catch:{ all -> 0x009e }
        if (r3 == 0) goto L_0x006a;
    L_0x0052:
        r1 = 0;
    L_0x0053:
        r2 = r0.a();	 Catch:{ EOFException -> 0x005d }
        r8.d(r2);	 Catch:{ EOFException -> 0x005d }
        r1 = r1 + 1;
        goto L_0x0053;
    L_0x005d:
        r2 = r8.l;	 Catch:{ all -> 0x009e }
        r2 = r2.size();	 Catch:{ all -> 0x009e }
        r1 = r1 - r2;
        r8.m = r1;	 Catch:{ all -> 0x009e }
        defpackage.mf.a(r0);
        return;
    L_0x006a:
        r3 = new java.io.IOException;	 Catch:{ all -> 0x009e }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x009e }
        r6.<init>();	 Catch:{ all -> 0x009e }
        r7 = "unexpected journal header: [";
        r6.append(r7);	 Catch:{ all -> 0x009e }
        r6.append(r1);	 Catch:{ all -> 0x009e }
        r1 = ", ";
        r6.append(r1);	 Catch:{ all -> 0x009e }
        r6.append(r2);	 Catch:{ all -> 0x009e }
        r1 = ", ";
        r6.append(r1);	 Catch:{ all -> 0x009e }
        r6.append(r4);	 Catch:{ all -> 0x009e }
        r1 = ", ";
        r6.append(r1);	 Catch:{ all -> 0x009e }
        r6.append(r5);	 Catch:{ all -> 0x009e }
        r1 = "]";
        r6.append(r1);	 Catch:{ all -> 0x009e }
        r1 = r6.toString();	 Catch:{ all -> 0x009e }
        r3.<init>(r1);	 Catch:{ all -> 0x009e }
        throw r3;	 Catch:{ all -> 0x009e }
    L_0x009e:
        r1 = move-exception;
        defpackage.mf.a(r0);
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.md.c():void");
    }

    private void d(String str) throws IOException {
        int indexOf = str.indexOf(32);
        StringBuilder stringBuilder;
        if (indexOf != -1) {
            Object substring;
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(32, i);
            if (indexOf2 == -1) {
                substring = str.substring(i);
                if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                    this.l.remove(substring);
                    return;
                }
            }
            substring = str.substring(i, indexOf2);
            b bVar = (b) this.l.get(substring);
            if (bVar == null) {
                bVar = new b(this, substring, null);
                this.l.put(substring, bVar);
            }
            if (indexOf2 != -1 && indexOf == "CLEAN".length() && str.startsWith("CLEAN")) {
                String[] split = str.substring(indexOf2 + 1).split(" ");
                bVar.d = true;
                bVar.e = null;
                bVar.a(split);
            } else if (indexOf2 == -1 && indexOf == "DIRTY".length() && str.startsWith("DIRTY")) {
                bVar.e = new a(this, bVar, null);
            } else if (!(indexOf2 == -1 && indexOf == "READ".length() && str.startsWith("READ"))) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("unexpected journal line: ");
                stringBuilder.append(str);
                throw new IOException(stringBuilder.toString());
            }
            return;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("unexpected journal line: ");
        stringBuilder.append(str);
        throw new IOException(stringBuilder.toString());
    }

    private void d() throws IOException {
        md.a(this.e);
        Iterator it = this.l.values().iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            int i = 0;
            if (bVar.e == null) {
                while (i < this.i) {
                    this.j += bVar.c[i];
                    i++;
                }
            } else {
                bVar.e = null;
                while (i < this.i) {
                    md.a(bVar.a(i));
                    md.a(bVar.b(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    private synchronized void e() throws IOException {
        if (this.k != null) {
            this.k.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), mf.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.i));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (b bVar : this.l.values()) {
                StringBuilder stringBuilder;
                if (bVar.e != null) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("DIRTY ");
                    stringBuilder.append(bVar.b);
                    stringBuilder.append(10);
                    bufferedWriter.write(stringBuilder.toString());
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("CLEAN ");
                    stringBuilder.append(bVar.b);
                    stringBuilder.append(bVar.a());
                    stringBuilder.append(10);
                    bufferedWriter.write(stringBuilder.toString());
                }
            }
            if (this.d.exists()) {
                md.a(this.d, this.f, true);
            }
            md.a(this.e, this.d, false);
            this.f.delete();
            this.k = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), mf.a));
        } finally {
            bufferedWriter.close();
        }
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            md.a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0075 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:32|33|28|27) */
    /* JADX WARNING: Missing block: B:21:?, code skipped:
            r11.m++;
            r1 = r11.k;
            r2 = new java.lang.StringBuilder();
            r2.append("READ ");
            r2.append(r12);
            r2.append(10);
            r1.append(r2.toString());
     */
    /* JADX WARNING: Missing block: B:22:0x0059, code skipped:
            if (f() == false) goto L_0x0062;
     */
    /* JADX WARNING: Missing block: B:23:0x005b, code skipped:
            r11.b.submit(r11.o);
     */
    /* JADX WARNING: Missing block: B:26:0x0074, code skipped:
            return new defpackage.md.c(r11, r12, defpackage.md.b.e(r0), r8, defpackage.md.b.b(r0), null);
     */
    /* JADX WARNING: Missing block: B:35:0x0086, code skipped:
            return null;
     */
    public synchronized defpackage.md.c a(java.lang.String r12) throws java.io.IOException {
        /*
        r11 = this;
        monitor-enter(r11);
        r11.g();	 Catch:{ all -> 0x0087 }
        r11.e(r12);	 Catch:{ all -> 0x0087 }
        r0 = r11.l;	 Catch:{ all -> 0x0087 }
        r0 = r0.get(r12);	 Catch:{ all -> 0x0087 }
        r0 = (defpackage.md.b) r0;	 Catch:{ all -> 0x0087 }
        r1 = 0;
        if (r0 != 0) goto L_0x0014;
    L_0x0012:
        monitor-exit(r11);
        return r1;
    L_0x0014:
        r2 = r0.d;	 Catch:{ all -> 0x0087 }
        if (r2 != 0) goto L_0x001c;
    L_0x001a:
        monitor-exit(r11);
        return r1;
    L_0x001c:
        r2 = r11.i;	 Catch:{ all -> 0x0087 }
        r8 = new java.io.InputStream[r2];	 Catch:{ all -> 0x0087 }
        r2 = 0;
        r3 = 0;
    L_0x0022:
        r4 = r11.i;	 Catch:{ FileNotFoundException -> 0x0075 }
        if (r3 >= r4) goto L_0x0034;
    L_0x0026:
        r4 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0075 }
        r5 = r0.a(r3);	 Catch:{ FileNotFoundException -> 0x0075 }
        r4.<init>(r5);	 Catch:{ FileNotFoundException -> 0x0075 }
        r8[r3] = r4;	 Catch:{ FileNotFoundException -> 0x0075 }
        r3 = r3 + 1;
        goto L_0x0022;
    L_0x0034:
        r1 = r11.m;	 Catch:{ all -> 0x0087 }
        r1 = r1 + 1;
        r11.m = r1;	 Catch:{ all -> 0x0087 }
        r1 = r11.k;	 Catch:{ all -> 0x0087 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0087 }
        r2.<init>();	 Catch:{ all -> 0x0087 }
        r3 = "READ ";
        r2.append(r3);	 Catch:{ all -> 0x0087 }
        r2.append(r12);	 Catch:{ all -> 0x0087 }
        r3 = 10;
        r2.append(r3);	 Catch:{ all -> 0x0087 }
        r2 = r2.toString();	 Catch:{ all -> 0x0087 }
        r1.append(r2);	 Catch:{ all -> 0x0087 }
        r1 = r11.f();	 Catch:{ all -> 0x0087 }
        if (r1 == 0) goto L_0x0062;
    L_0x005b:
        r1 = r11.b;	 Catch:{ all -> 0x0087 }
        r2 = r11.o;	 Catch:{ all -> 0x0087 }
        r1.submit(r2);	 Catch:{ all -> 0x0087 }
    L_0x0062:
        r1 = new md$c;	 Catch:{ all -> 0x0087 }
        r6 = r0.f;	 Catch:{ all -> 0x0087 }
        r9 = r0.c;	 Catch:{ all -> 0x0087 }
        r10 = 0;
        r3 = r1;
        r4 = r11;
        r5 = r12;
        r3.<init>(r4, r5, r6, r8, r9, r10);	 Catch:{ all -> 0x0087 }
        monitor-exit(r11);
        return r1;
    L_0x0075:
        r12 = r11.i;	 Catch:{ all -> 0x0087 }
        if (r2 >= r12) goto L_0x0085;
    L_0x0079:
        r12 = r8[r2];	 Catch:{ all -> 0x0087 }
        if (r12 == 0) goto L_0x0085;
    L_0x007d:
        r12 = r8[r2];	 Catch:{ all -> 0x0087 }
        defpackage.mf.a(r12);	 Catch:{ all -> 0x0087 }
        r2 = r2 + 1;
        goto L_0x0075;
    L_0x0085:
        monitor-exit(r11);
        return r1;
    L_0x0087:
        r12 = move-exception;
        monitor-exit(r11);
        throw r12;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.md.a(java.lang.String):md$c");
    }

    public a b(String str) throws IOException {
        return a(str, -1);
    }

    /* JADX WARNING: Missing block: B:9:0x0021, code skipped:
            return null;
     */
    private synchronized defpackage.md.a a(java.lang.String r6, long r7) throws java.io.IOException {
        /*
        r5 = this;
        monitor-enter(r5);
        r5.g();	 Catch:{ all -> 0x0061 }
        r5.e(r6);	 Catch:{ all -> 0x0061 }
        r0 = r5.l;	 Catch:{ all -> 0x0061 }
        r0 = r0.get(r6);	 Catch:{ all -> 0x0061 }
        r0 = (defpackage.md.b) r0;	 Catch:{ all -> 0x0061 }
        r1 = -1;
        r3 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1));
        r1 = 0;
        if (r3 == 0) goto L_0x0022;
    L_0x0016:
        if (r0 == 0) goto L_0x0020;
    L_0x0018:
        r2 = r0.f;	 Catch:{ all -> 0x0061 }
        r4 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1));
        if (r4 == 0) goto L_0x0022;
    L_0x0020:
        monitor-exit(r5);
        return r1;
    L_0x0022:
        if (r0 != 0) goto L_0x002f;
    L_0x0024:
        r0 = new md$b;	 Catch:{ all -> 0x0061 }
        r0.<init>(r5, r6, r1);	 Catch:{ all -> 0x0061 }
        r7 = r5.l;	 Catch:{ all -> 0x0061 }
        r7.put(r6, r0);	 Catch:{ all -> 0x0061 }
        goto L_0x0037;
    L_0x002f:
        r7 = r0.e;	 Catch:{ all -> 0x0061 }
        if (r7 == 0) goto L_0x0037;
    L_0x0035:
        monitor-exit(r5);
        return r1;
    L_0x0037:
        r7 = new md$a;	 Catch:{ all -> 0x0061 }
        r7.<init>(r5, r0, r1);	 Catch:{ all -> 0x0061 }
        r0.e = r7;	 Catch:{ all -> 0x0061 }
        r8 = r5.k;	 Catch:{ all -> 0x0061 }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0061 }
        r0.<init>();	 Catch:{ all -> 0x0061 }
        r1 = "DIRTY ";
        r0.append(r1);	 Catch:{ all -> 0x0061 }
        r0.append(r6);	 Catch:{ all -> 0x0061 }
        r6 = 10;
        r0.append(r6);	 Catch:{ all -> 0x0061 }
        r6 = r0.toString();	 Catch:{ all -> 0x0061 }
        r8.write(r6);	 Catch:{ all -> 0x0061 }
        r6 = r5.k;	 Catch:{ all -> 0x0061 }
        r6.flush();	 Catch:{ all -> 0x0061 }
        monitor-exit(r5);
        return r7;
    L_0x0061:
        r6 = move-exception;
        monitor-exit(r5);
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.md.a(java.lang.String, long):md$a");
    }

    /* JADX WARNING: Missing block: B:43:0x010a, code skipped:
            return;
     */
    private synchronized void a(defpackage.md.a r10, boolean r11) throws java.io.IOException {
        /*
        r9 = this;
        monitor-enter(r9);
        r0 = r10.b;	 Catch:{ all -> 0x0111 }
        r1 = r0.e;	 Catch:{ all -> 0x0111 }
        if (r1 != r10) goto L_0x010b;
    L_0x000b:
        r1 = 0;
        if (r11 == 0) goto L_0x004d;
    L_0x000e:
        r2 = r0.d;	 Catch:{ all -> 0x0111 }
        if (r2 != 0) goto L_0x004d;
    L_0x0014:
        r2 = 0;
    L_0x0015:
        r3 = r9.i;	 Catch:{ all -> 0x0111 }
        if (r2 >= r3) goto L_0x004d;
    L_0x0019:
        r3 = r10.c;	 Catch:{ all -> 0x0111 }
        r3 = r3[r2];	 Catch:{ all -> 0x0111 }
        if (r3 == 0) goto L_0x0033;
    L_0x0021:
        r3 = r0.b(r2);	 Catch:{ all -> 0x0111 }
        r3 = r3.exists();	 Catch:{ all -> 0x0111 }
        if (r3 != 0) goto L_0x0030;
    L_0x002b:
        r10.b();	 Catch:{ all -> 0x0111 }
        monitor-exit(r9);
        return;
    L_0x0030:
        r2 = r2 + 1;
        goto L_0x0015;
    L_0x0033:
        r10.b();	 Catch:{ all -> 0x0111 }
        r10 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0111 }
        r11 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0111 }
        r11.<init>();	 Catch:{ all -> 0x0111 }
        r0 = "Newly created entry didn't create value for index ";
        r11.append(r0);	 Catch:{ all -> 0x0111 }
        r11.append(r2);	 Catch:{ all -> 0x0111 }
        r11 = r11.toString();	 Catch:{ all -> 0x0111 }
        r10.<init>(r11);	 Catch:{ all -> 0x0111 }
        throw r10;	 Catch:{ all -> 0x0111 }
    L_0x004d:
        r10 = r9.i;	 Catch:{ all -> 0x0111 }
        if (r1 >= r10) goto L_0x0082;
    L_0x0051:
        r10 = r0.b(r1);	 Catch:{ all -> 0x0111 }
        if (r11 == 0) goto L_0x007c;
    L_0x0057:
        r2 = r10.exists();	 Catch:{ all -> 0x0111 }
        if (r2 == 0) goto L_0x007f;
    L_0x005d:
        r2 = r0.a(r1);	 Catch:{ all -> 0x0111 }
        r10.renameTo(r2);	 Catch:{ all -> 0x0111 }
        r10 = r0.c;	 Catch:{ all -> 0x0111 }
        r3 = r10[r1];	 Catch:{ all -> 0x0111 }
        r5 = r2.length();	 Catch:{ all -> 0x0111 }
        r10 = r0.c;	 Catch:{ all -> 0x0111 }
        r10[r1] = r5;	 Catch:{ all -> 0x0111 }
        r7 = r9.j;	 Catch:{ all -> 0x0111 }
        r10 = 0;
        r7 = r7 - r3;
        r7 = r7 + r5;
        r9.j = r7;	 Catch:{ all -> 0x0111 }
        goto L_0x007f;
    L_0x007c:
        defpackage.md.a(r10);	 Catch:{ all -> 0x0111 }
    L_0x007f:
        r1 = r1 + 1;
        goto L_0x004d;
    L_0x0082:
        r10 = r9.m;	 Catch:{ all -> 0x0111 }
        r1 = 1;
        r10 = r10 + r1;
        r9.m = r10;	 Catch:{ all -> 0x0111 }
        r10 = 0;
        r0.e = r10;	 Catch:{ all -> 0x0111 }
        r10 = r0.d;	 Catch:{ all -> 0x0111 }
        r10 = r10 | r11;
        r2 = 10;
        if (r10 == 0) goto L_0x00c9;
    L_0x0095:
        r0.d = r1;	 Catch:{ all -> 0x0111 }
        r10 = r9.k;	 Catch:{ all -> 0x0111 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0111 }
        r1.<init>();	 Catch:{ all -> 0x0111 }
        r3 = "CLEAN ";
        r1.append(r3);	 Catch:{ all -> 0x0111 }
        r3 = r0.b;	 Catch:{ all -> 0x0111 }
        r1.append(r3);	 Catch:{ all -> 0x0111 }
        r3 = r0.a();	 Catch:{ all -> 0x0111 }
        r1.append(r3);	 Catch:{ all -> 0x0111 }
        r1.append(r2);	 Catch:{ all -> 0x0111 }
        r1 = r1.toString();	 Catch:{ all -> 0x0111 }
        r10.write(r1);	 Catch:{ all -> 0x0111 }
        if (r11 == 0) goto L_0x00ef;
    L_0x00be:
        r10 = r9.n;	 Catch:{ all -> 0x0111 }
        r1 = 1;
        r1 = r1 + r10;
        r9.n = r1;	 Catch:{ all -> 0x0111 }
        r0.f = r10;	 Catch:{ all -> 0x0111 }
        goto L_0x00ef;
    L_0x00c9:
        r10 = r9.l;	 Catch:{ all -> 0x0111 }
        r11 = r0.b;	 Catch:{ all -> 0x0111 }
        r10.remove(r11);	 Catch:{ all -> 0x0111 }
        r10 = r9.k;	 Catch:{ all -> 0x0111 }
        r11 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0111 }
        r11.<init>();	 Catch:{ all -> 0x0111 }
        r1 = "REMOVE ";
        r11.append(r1);	 Catch:{ all -> 0x0111 }
        r0 = r0.b;	 Catch:{ all -> 0x0111 }
        r11.append(r0);	 Catch:{ all -> 0x0111 }
        r11.append(r2);	 Catch:{ all -> 0x0111 }
        r11 = r11.toString();	 Catch:{ all -> 0x0111 }
        r10.write(r11);	 Catch:{ all -> 0x0111 }
    L_0x00ef:
        r10 = r9.k;	 Catch:{ all -> 0x0111 }
        r10.flush();	 Catch:{ all -> 0x0111 }
        r10 = r9.j;	 Catch:{ all -> 0x0111 }
        r0 = r9.h;	 Catch:{ all -> 0x0111 }
        r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1));
        if (r2 > 0) goto L_0x0102;
    L_0x00fc:
        r10 = r9.f();	 Catch:{ all -> 0x0111 }
        if (r10 == 0) goto L_0x0109;
    L_0x0102:
        r10 = r9.b;	 Catch:{ all -> 0x0111 }
        r11 = r9.o;	 Catch:{ all -> 0x0111 }
        r10.submit(r11);	 Catch:{ all -> 0x0111 }
    L_0x0109:
        monitor-exit(r9);
        return;
    L_0x010b:
        r10 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0111 }
        r10.<init>();	 Catch:{ all -> 0x0111 }
        throw r10;	 Catch:{ all -> 0x0111 }
    L_0x0111:
        r10 = move-exception;
        monitor-exit(r9);
        throw r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.md.a(md$a, boolean):void");
    }

    private boolean f() {
        return this.m >= ExceptionEngine.SERVER_ERROR && this.m >= this.l.size();
    }

    /* JADX WARNING: Missing block: B:21:0x0091, code skipped:
            return true;
     */
    /* JADX WARNING: Missing block: B:23:0x0093, code skipped:
            return false;
     */
    public synchronized boolean c(java.lang.String r8) throws java.io.IOException {
        /*
        r7 = this;
        monitor-enter(r7);
        r7.g();	 Catch:{ all -> 0x0094 }
        r7.e(r8);	 Catch:{ all -> 0x0094 }
        r0 = r7.l;	 Catch:{ all -> 0x0094 }
        r0 = r0.get(r8);	 Catch:{ all -> 0x0094 }
        r0 = (defpackage.md.b) r0;	 Catch:{ all -> 0x0094 }
        r1 = 0;
        if (r0 == 0) goto L_0x0092;
    L_0x0012:
        r2 = r0.e;	 Catch:{ all -> 0x0094 }
        if (r2 == 0) goto L_0x001a;
    L_0x0018:
        goto L_0x0092;
    L_0x001a:
        r2 = r7.i;	 Catch:{ all -> 0x0094 }
        if (r1 >= r2) goto L_0x005d;
    L_0x001e:
        r2 = r0.a(r1);	 Catch:{ all -> 0x0094 }
        r3 = r2.exists();	 Catch:{ all -> 0x0094 }
        if (r3 == 0) goto L_0x0046;
    L_0x0028:
        r3 = r2.delete();	 Catch:{ all -> 0x0094 }
        if (r3 == 0) goto L_0x002f;
    L_0x002e:
        goto L_0x0046;
    L_0x002f:
        r8 = new java.io.IOException;	 Catch:{ all -> 0x0094 }
        r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0094 }
        r0.<init>();	 Catch:{ all -> 0x0094 }
        r1 = "failed to delete ";
        r0.append(r1);	 Catch:{ all -> 0x0094 }
        r0.append(r2);	 Catch:{ all -> 0x0094 }
        r0 = r0.toString();	 Catch:{ all -> 0x0094 }
        r8.<init>(r0);	 Catch:{ all -> 0x0094 }
        throw r8;	 Catch:{ all -> 0x0094 }
    L_0x0046:
        r2 = r7.j;	 Catch:{ all -> 0x0094 }
        r4 = r0.c;	 Catch:{ all -> 0x0094 }
        r5 = r4[r1];	 Catch:{ all -> 0x0094 }
        r4 = 0;
        r2 = r2 - r5;
        r7.j = r2;	 Catch:{ all -> 0x0094 }
        r2 = r0.c;	 Catch:{ all -> 0x0094 }
        r3 = 0;
        r2[r1] = r3;	 Catch:{ all -> 0x0094 }
        r1 = r1 + 1;
        goto L_0x001a;
    L_0x005d:
        r0 = r7.m;	 Catch:{ all -> 0x0094 }
        r1 = 1;
        r0 = r0 + r1;
        r7.m = r0;	 Catch:{ all -> 0x0094 }
        r0 = r7.k;	 Catch:{ all -> 0x0094 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0094 }
        r2.<init>();	 Catch:{ all -> 0x0094 }
        r3 = "REMOVE ";
        r2.append(r3);	 Catch:{ all -> 0x0094 }
        r2.append(r8);	 Catch:{ all -> 0x0094 }
        r3 = 10;
        r2.append(r3);	 Catch:{ all -> 0x0094 }
        r2 = r2.toString();	 Catch:{ all -> 0x0094 }
        r0.append(r2);	 Catch:{ all -> 0x0094 }
        r0 = r7.l;	 Catch:{ all -> 0x0094 }
        r0.remove(r8);	 Catch:{ all -> 0x0094 }
        r8 = r7.f();	 Catch:{ all -> 0x0094 }
        if (r8 == 0) goto L_0x0090;
    L_0x0089:
        r8 = r7.b;	 Catch:{ all -> 0x0094 }
        r0 = r7.o;	 Catch:{ all -> 0x0094 }
        r8.submit(r0);	 Catch:{ all -> 0x0094 }
    L_0x0090:
        monitor-exit(r7);
        return r1;
    L_0x0092:
        monitor-exit(r7);
        return r1;
    L_0x0094:
        r8 = move-exception;
        monitor-exit(r7);
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.md.c(java.lang.String):boolean");
    }

    private void g() {
        if (this.k == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() throws IOException {
        if (this.k != null) {
            Iterator it = new ArrayList(this.l.values()).iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.e != null) {
                    bVar.e.b();
                }
            }
            h();
            this.k.close();
            this.k = null;
        }
    }

    private void h() throws IOException {
        while (this.j > this.h) {
            c((String) ((Entry) this.l.entrySet().iterator().next()).getKey());
        }
    }

    public void a() throws IOException {
        close();
        mf.a(this.c);
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("keys must match regex [a-z0-9_-]{1,64}: \"");
            stringBuilder.append(str);
            stringBuilder.append("\"");
            throw new IllegalArgumentException(stringBuilder.toString());
        }
    }
}
