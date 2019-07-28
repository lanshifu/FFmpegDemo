package defpackage;

import android.content.Context;
import com.tencent.ugc.TXRecordCommon;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.j;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: EventsFilesManager */
/* renamed from: vp */
public abstract class vp<T> {
    protected final Context a;
    protected final vo<T> b;
    protected final j c;
    protected final vq d;
    protected volatile long e;
    protected final List<vr> f = new CopyOnWriteArrayList();
    private final int g;

    /* compiled from: EventsFilesManager */
    /* renamed from: vp$1 */
    class 1 implements Comparator<a> {
        1() {
        }

        /* renamed from: a */
        public int compare(a aVar, a aVar2) {
            return (int) (aVar.b - aVar2.b);
        }
    }

    /* compiled from: EventsFilesManager */
    /* renamed from: vp$a */
    static class a {
        final File a;
        final long b;

        public a(File file, long j) {
            this.a = file;
            this.b = j;
        }
    }

    public abstract String a();

    /* Access modifiers changed, original: protected */
    public int c() {
        return TXRecordCommon.AUDIO_SAMPLERATE_8000;
    }

    public vp(Context context, vo<T> voVar, j jVar, vq vqVar, int i) throws IOException {
        this.a = context.getApplicationContext();
        this.b = voVar;
        this.d = vqVar;
        this.c = jVar;
        this.e = this.c.a();
        this.g = i;
    }

    public void a(T t) throws IOException {
        byte[] a = this.b.a(t);
        a(a.length);
        this.d.a(a);
    }

    public void a(vr vrVar) {
        if (vrVar != null) {
            this.f.add(vrVar);
        }
    }

    public boolean d() throws IOException {
        String str;
        boolean z = true;
        if (this.d.b()) {
            str = null;
            z = false;
        } else {
            str = a();
            this.d.a(str);
            CommonUtils.a(this.a, 4, "Fabric", String.format(Locale.US, "generated new file %s", new Object[]{str}));
            this.e = this.c.a();
        }
        b(str);
        return z;
    }

    private void a(int i) throws IOException {
        if (!this.d.a(i, c())) {
            CommonUtils.a(this.a, 4, "Fabric", String.format(Locale.US, "session analytics events file is %d bytes, new event is %d bytes, this is over flush limit of %d, rolling it over", new Object[]{Integer.valueOf(this.d.a()), Integer.valueOf(i), Integer.valueOf(c())}));
            d();
        }
    }

    /* Access modifiers changed, original: protected */
    public int b() {
        return this.g;
    }

    private void b(String str) {
        for (vr a : this.f) {
            try {
                a.a(str);
            } catch (Exception e) {
                CommonUtils.a(this.a, "One of the roll over listeners threw an exception", e);
            }
        }
    }

    public List<File> e() {
        return this.d.a(1);
    }

    public void a(List<File> list) {
        this.d.a((List) list);
    }

    public void f() {
        this.d.a(this.d.c());
        this.d.d();
    }

    public void g() {
        List<File> c = this.d.c();
        int b = b();
        if (c.size() > b) {
            int size = c.size() - b;
            CommonUtils.a(this.a, String.format(Locale.US, "Found %d files in  roll over directory, this is greater than %d, deleting %d oldest files", new Object[]{Integer.valueOf(c.size()), Integer.valueOf(b), Integer.valueOf(size)}));
            TreeSet treeSet = new TreeSet(new 1());
            for (File file : c) {
                treeSet.add(new a(file, a(file.getName())));
            }
            List c2 = new ArrayList();
            Iterator it = treeSet.iterator();
            while (it.hasNext()) {
                c2.add(((a) it.next()).a);
                if (c2.size() == size) {
                    break;
                }
            }
            this.d.a(c2);
        }
    }

    public long a(String str) {
        String[] split = str.split("_");
        if (split.length != 3) {
            return 0;
        }
        try {
            return Long.valueOf(split[2]).longValue();
        } catch (NumberFormatException unused) {
            return 0;
        }
    }
}
