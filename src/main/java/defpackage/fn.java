package defpackage;

import android.text.TextUtils;
import com.danikula.videocache.InterruptedProxyCacheException;
import com.danikula.videocache.ProxyCacheException;
import com.danikula.videocache.j;
import com.danikula.videocache.l;
import com.danikula.videocache.m;
import com.danikula.videocache.n;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import okhttp3.Call;
import okhttp3.Response;

/* compiled from: OkHttpUrlSource */
/* renamed from: fn */
public class fn implements m {
    private static final String b = "fn";
    public final fs a;
    private fi c;
    private n d;
    private Call e;
    private InputStream f;
    private boolean g;

    public fn(String str) {
        this(str, ft.a());
    }

    public fn(String str, fs fsVar) {
        this.e = null;
        this.a = (fs) j.a(fsVar);
        n a = fsVar.a(str);
        if (a == null) {
            n nVar = new n(str, -2147483648L, System.currentTimeMillis(), l.a(str));
        }
        this.d = a;
    }

    public fn(String str, fs fsVar, fi fiVar) {
        this.e = null;
        this.a = (fs) j.a(fsVar);
        this.c = (fi) j.a(fiVar);
        n a = fsVar.a(str);
        if (a == null) {
            n nVar = new n(str, -2147483648L, System.currentTimeMillis(), l.a(str));
        }
        this.d = a;
    }

    public fn(fn fnVar) {
        this.e = null;
        this.d = fnVar.d;
        this.a = fnVar.a;
    }

    public synchronized long a() throws ProxyCacheException {
        if (this.d.b == -2147483648L) {
            f();
        }
        return this.d.b;
    }

    public void a(long j) throws ProxyCacheException {
        try {
            b a = aat.a("TTT");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("offset:");
            stringBuilder.append(j);
            stringBuilder.append(" url:");
            stringBuilder.append(d());
            a.a(stringBuilder.toString(), new Object[0]);
            Response a2 = a(j, -1);
            String header = a2.header("Content-Type");
            this.f = new BufferedInputStream(a2.body().byteStream(), 20480);
            this.d = new n(this.d.a, a(a2, j, a2.code()), System.currentTimeMillis(), header);
            this.a.a(this.d.a, this.d);
        } catch (IOException e) {
            e.printStackTrace();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Error opening okHttpClient for ");
            stringBuilder2.append(this.d.a);
            stringBuilder2.append(" with offset ");
            stringBuilder2.append(j);
            throw new ProxyCacheException(stringBuilder2.toString(), e);
        }
    }

    private long a(Response response, long j, int i) throws IOException {
        long a = a(response);
        if (i == 200) {
            return a;
        }
        if (i == 206) {
            return a + j;
        }
        return this.d.b;
    }

    private long a(Response response) {
        String header = response.header("Content-Length");
        response.header("Content-Encoding");
        if (header == null) {
            this.g = true;
        }
        if (header == null) {
            return -1;
        }
        return Long.parseLong(header);
    }

    public void b() throws ProxyCacheException {
        if (this.e != null) {
            try {
                this.e.cancel();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        if (this.f != null) {
            try {
                this.f.close();
            } catch (IOException e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2.getMessage(), e2);
            }
        }
    }

    public int a(byte[] bArr) throws ProxyCacheException {
        StringBuilder stringBuilder;
        if (this.f != null) {
            try {
                return this.f.read(bArr, 0, bArr.length);
            } catch (InterruptedIOException e) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Reading source ");
                stringBuilder.append(this.d.a);
                stringBuilder.append(" is interrupted");
                throw new InterruptedProxyCacheException(stringBuilder.toString(), e);
            } catch (IOException e2) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Error reading data from ");
                stringBuilder.append(this.d.a);
                throw new ProxyCacheException(stringBuilder.toString(), e2);
            }
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Error reading data from ");
        stringBuilder2.append(this.d.a);
        stringBuilder2.append(": okHttpClient is absent!");
        throw new ProxyCacheException(stringBuilder2.toString());
    }

    private void f() throws ProxyCacheException {
        InputStream inputStream;
        IOException e;
        Throwable th;
        Closeable closeable = null;
        Response a;
        try {
            b a2 = aat.a("TTT");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("fetchContentInfo: url:");
            stringBuilder.append(d());
            a2.a(stringBuilder.toString(), new Object[0]);
            a = a(20000);
            if (a != null) {
                try {
                    if (a.isSuccessful()) {
                        long a3 = a(a);
                        String header = a.header("Content-Type", "application/mp4");
                        InputStream byteStream = a.body().byteStream();
                        try {
                            this.d = new n(this.d.a, a3, System.currentTimeMillis(), header);
                            this.a.a(this.d.a, this.d);
                            l.a(byteStream);
                            if (a == null || this.e == null) {
                                return;
                            }
                        } catch (IOException e2) {
                            inputStream = byteStream;
                            e = e2;
                            closeable = inputStream;
                            try {
                                e.printStackTrace();
                                l.a(closeable);
                            } catch (Throwable th2) {
                                th = th2;
                                l.a(closeable);
                                if (!(a == null || this.e == null)) {
                                    this.e.cancel();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            inputStream = byteStream;
                            th = th3;
                            closeable = inputStream;
                            l.a(closeable);
                            this.e.cancel();
                            throw th;
                        }
                        this.e.cancel();
                        return;
                    }
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    l.a(closeable);
                    if (a == null || this.e == null) {
                        return;
                    }
                    this.e.cancel();
                    return;
                }
            }
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Fail to fetchContentInfo: ");
            stringBuilder2.append(this.d.a);
            throw new ProxyCacheException(stringBuilder2.toString());
        } catch (IOException e4) {
            e = e4;
            a = null;
            e.printStackTrace();
            l.a(closeable);
        } catch (Throwable th4) {
            th = th4;
            a = null;
            l.a(closeable);
            this.e.cancel();
            throw th;
        }
    }

    private Response a(int i) throws IOException, ProxyCacheException {
        return fm.a().a(this.d.a);
    }

    private Response a(long j, int i) throws IOException, ProxyCacheException {
        this.e = fm.a().a(this.d.a, j);
        return this.e.execute();
    }

    public synchronized String c() throws ProxyCacheException {
        if (TextUtils.isEmpty(this.d.d)) {
            f();
        }
        return this.d.d;
    }

    public String d() {
        return this.d.a;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OkHttpUrlSource{sourceInfo='");
        stringBuilder.append(this.d);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public boolean e() {
        return this.g;
    }
}
