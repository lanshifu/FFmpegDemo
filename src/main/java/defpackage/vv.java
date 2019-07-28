package defpackage;

import android.content.Context;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.common.r;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: QueueFileEventStorage */
/* renamed from: vv */
public class vv implements vq {
    private final Context a;
    private final File b;
    private final String c;
    private final File d;
    private r e = new r(this.d);
    private File f;

    public vv(Context context, File file, String str, String str2) throws IOException {
        this.a = context;
        this.b = file;
        this.c = str2;
        this.d = new File(this.b, str);
        e();
    }

    private void e() {
        this.f = new File(this.b, this.c);
        if (!this.f.exists()) {
            this.f.mkdirs();
        }
    }

    public void a(byte[] bArr) throws IOException {
        this.e.a(bArr);
    }

    public int a() {
        return this.e.a();
    }

    public void a(String str) throws IOException {
        this.e.close();
        a(this.d, new File(this.f, str));
        this.e = new r(this.d);
    }

    private void a(File file, File file2) throws IOException {
        Closeable fileInputStream;
        Throwable th;
        Closeable closeable = null;
        try {
            Closeable a;
            fileInputStream = new FileInputStream(file);
            try {
                a = a(file2);
            } catch (Throwable th2) {
                th = th2;
                CommonUtils.a(fileInputStream, "Failed to close file input stream");
                CommonUtils.a(closeable, "Failed to close output stream");
                file.delete();
                throw th;
            }
            try {
                CommonUtils.a((InputStream) fileInputStream, (OutputStream) a, new byte[Filter.K]);
                CommonUtils.a(fileInputStream, "Failed to close file input stream");
                CommonUtils.a(a, "Failed to close output stream");
                file.delete();
            } catch (Throwable th3) {
                Throwable th4 = th3;
                closeable = a;
                th = th4;
                CommonUtils.a(fileInputStream, "Failed to close file input stream");
                CommonUtils.a(closeable, "Failed to close output stream");
                file.delete();
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            fileInputStream = null;
            CommonUtils.a(fileInputStream, "Failed to close file input stream");
            CommonUtils.a(closeable, "Failed to close output stream");
            file.delete();
            throw th;
        }
    }

    public OutputStream a(File file) throws IOException {
        return new FileOutputStream(file);
    }

    public List<File> a(int i) {
        ArrayList arrayList = new ArrayList();
        for (Object add : this.f.listFiles()) {
            arrayList.add(add);
            if (arrayList.size() >= i) {
                break;
            }
        }
        return arrayList;
    }

    public void a(List<File> list) {
        for (File file : list) {
            CommonUtils.a(this.a, String.format("deleting sent analytics file %s", new Object[]{file.getName()}));
            file.delete();
        }
    }

    public List<File> c() {
        return Arrays.asList(this.f.listFiles());
    }

    public void d() {
        try {
            this.e.close();
        } catch (IOException unused) {
        }
        this.d.delete();
    }

    public boolean b() {
        return this.e.b();
    }

    public boolean a(int i, int i2) {
        return this.e.a(i, i2);
    }
}
