package defpackage;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;

/* compiled from: VideoCacheStorage */
/* renamed from: ff */
public class ff {
    private long a;
    private File b;
    private String c;
    private Deque<fg> d = new LinkedList();
    private boolean e;

    public ff(File file, String str) {
        this.b = file;
        this.c = str;
        f();
    }

    private void f() {
        if (this.b != null) {
            File[] listFiles = this.b.listFiles();
            String d = el.d(this.c.trim());
            if (listFiles != null) {
                for (File file : new es().a(listFiles)) {
                    if (file.isDirectory()) {
                        fg fgVar = new fg(new File(file.getAbsolutePath()));
                        this.a += fgVar.e();
                        if (fgVar.c().trim().equals(d.trim())) {
                            this.e = true;
                            this.d.addFirst(fgVar);
                        } else {
                            this.d.addLast(fgVar);
                        }
                        b a = aat.a("TTT");
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("single video size:");
                        stringBuilder.append(fgVar.e());
                        a.a(stringBuilder.toString(), new Object[0]);
                    }
                }
                b a2 = aat.a("TTT");
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("totalCacheSize:");
                stringBuilder2.append(this.a);
                a2.a(stringBuilder2.toString(), new Object[0]);
            }
        }
    }

    public void a() {
        if (this.b != null) {
            String d = el.d(this.c.trim());
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.b.getAbsolutePath());
                stringBuilder.append(File.separator);
                stringBuilder.append(d);
                File file = new File(stringBuilder.toString());
                if (!file.exists()) {
                    file.mkdir();
                }
                this.d.addFirst(new fg(file));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void b() {
        if (this.b != null) {
            es.b(this.b.getAbsolutePath());
        }
    }

    public long c() {
        return this.a;
    }

    public void a(long j) {
        this.a += j;
    }

    public void b(long j) {
        this.a -= j;
    }

    public boolean d() {
        return this.e;
    }

    public Deque<fg> e() {
        return this.d;
    }
}
