package defpackage;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONObject;

/* renamed from: su */
public class su {
    private byte[] a = new byte[0];
    private final int b;
    private final long c;
    private final Header[] d;
    private final JSONObject e;

    public su(HttpResponse httpResponse) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Closeable closeable = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpResponse.getEntity().getContent());
            try {
                sz.a(bufferedInputStream, byteArrayOutputStream);
                this.a = byteArrayOutputStream.toByteArray();
                sz.a(bufferedInputStream);
                sz.a(byteArrayOutputStream);
                if (httpResponse.getStatusLine() != null) {
                    this.b = httpResponse.getStatusLine().getStatusCode();
                } else {
                    this.b = 0;
                }
                this.c = (long) this.a.length;
                this.d = httpResponse.getAllHeaders();
                this.e = null;
            } catch (Throwable th2) {
                th = th2;
                closeable = bufferedInputStream;
                sz.a(closeable);
                sz.a(byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            sz.a(closeable);
            sz.a(byteArrayOutputStream);
            throw th;
        }
    }

    public byte[] a() {
        return this.a;
    }
}
