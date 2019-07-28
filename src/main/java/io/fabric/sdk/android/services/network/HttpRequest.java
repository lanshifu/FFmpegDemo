package io.fabric.sdk.android.services.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
import org.slf4j.Marker;

public class HttpRequest {
    private static final String[] b = new String[0];
    private static b c = b.a;
    public final URL a;
    private HttpURLConnection d = null;
    private final String e;
    private d f;
    private boolean g;
    private boolean h = true;
    private boolean i = false;
    private int j = 8192;
    private String k;
    private int l;

    public static class HttpRequestException extends RuntimeException {
        private static final long serialVersionUID = -1170466989781746231L;

        protected HttpRequestException(IOException iOException) {
            super(iOException);
        }

        public IOException getCause() {
            return (IOException) super.getCause();
        }
    }

    public interface b {
        public static final b a = new b() {
            public HttpURLConnection a(URL url) throws IOException {
                return (HttpURLConnection) url.openConnection();
            }

            public HttpURLConnection a(URL url, Proxy proxy) throws IOException {
                return (HttpURLConnection) url.openConnection(proxy);
            }
        };

        HttpURLConnection a(URL url) throws IOException;

        HttpURLConnection a(URL url, Proxy proxy) throws IOException;
    }

    protected static abstract class c<V> implements Callable<V> {
        public abstract V b() throws HttpRequestException, IOException;

        public abstract void c() throws IOException;

        protected c() {
        }

        public V call() throws HttpRequestException {
            Throwable th;
            Object obj = 1;
            try {
                Object b = b();
                try {
                    c();
                    return b;
                } catch (IOException e) {
                    throw new HttpRequestException(e);
                }
            } catch (HttpRequestException e2) {
                throw e2;
            } catch (IOException e3) {
                throw new HttpRequestException(e3);
            } catch (Throwable th2) {
                th = th2;
                c();
                throw th;
            }
        }
    }

    public static class d extends BufferedOutputStream {
        private final CharsetEncoder a;

        public d(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.a = Charset.forName(HttpRequest.f(str)).newEncoder();
        }

        public d a(String str) throws IOException {
            ByteBuffer encode = this.a.encode(CharBuffer.wrap(str));
            super.write(encode.array(), 0, encode.limit());
            return this;
        }
    }

    protected static abstract class a<V> extends c<V> {
        private final Closeable a;
        private final boolean b;

        protected a(Closeable closeable, boolean z) {
            this.a = closeable;
            this.b = z;
        }

        /* Access modifiers changed, original: protected */
        public void c() throws IOException {
            if (this.a instanceof Flushable) {
                ((Flushable) this.a).flush();
            }
            if (this.b) {
                try {
                    this.a.close();
                    return;
                } catch (IOException unused) {
                    return;
                }
            }
            this.a.close();
        }
    }

    private static String f(String str) {
        return (str == null || str.length() <= 0) ? "UTF-8" : str;
    }

    private static StringBuilder a(String str, StringBuilder stringBuilder) {
        if (str.indexOf(58) + 2 == str.lastIndexOf(47)) {
            stringBuilder.append('/');
        }
        return stringBuilder;
    }

    private static StringBuilder b(String str, StringBuilder stringBuilder) {
        int indexOf = str.indexOf(63);
        int length = stringBuilder.length() - 1;
        if (indexOf == -1) {
            stringBuilder.append('?');
        } else if (indexOf < length && str.charAt(length) != '&') {
            stringBuilder.append('&');
        }
        return stringBuilder;
    }

    public static String a(CharSequence charSequence) throws HttpRequestException {
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(host);
                stringBuilder.append(':');
                stringBuilder.append(Integer.toString(port));
                host = stringBuilder.toString();
            }
            try {
                host = new URI(url.getProtocol(), host, url.getPath(), url.getQuery(), url.getRef()).toASCIIString();
                int indexOf = host.indexOf(63);
                if (indexOf <= 0) {
                    return host;
                }
                indexOf++;
                if (indexOf >= host.length()) {
                    return host;
                }
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(host.substring(0, indexOf));
                stringBuilder2.append(host.substring(indexOf).replace(Marker.ANY_NON_NULL_MARKER, "%2B").replace("#", "%23"));
                return stringBuilder2.toString();
            } catch (URISyntaxException e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new HttpRequestException(iOException);
            }
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public static String a(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder stringBuilder = new StringBuilder(charSequence2);
        a(charSequence2, stringBuilder);
        b(charSequence2, stringBuilder);
        Iterator it = map.entrySet().iterator();
        Entry entry = (Entry) it.next();
        stringBuilder.append(entry.getKey().toString());
        stringBuilder.append('=');
        Object value = entry.getValue();
        if (value != null) {
            stringBuilder.append(value);
        }
        while (it.hasNext()) {
            stringBuilder.append('&');
            entry = (Entry) it.next();
            stringBuilder.append(entry.getKey().toString());
            stringBuilder.append('=');
            value = entry.getValue();
            if (value != null) {
                stringBuilder.append(value);
            }
        }
        return stringBuilder.toString();
    }

    public static HttpRequest b(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "GET");
    }

    public static HttpRequest a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        charSequence = a(charSequence, (Map) map);
        if (z) {
            charSequence = a(charSequence);
        }
        return b(charSequence);
    }

    public static HttpRequest c(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "POST");
    }

    public static HttpRequest b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        charSequence = a(charSequence, (Map) map);
        if (z) {
            charSequence = a(charSequence);
        }
        return c(charSequence);
    }

    public static HttpRequest d(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "PUT");
    }

    public static HttpRequest e(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, "DELETE");
    }

    public HttpRequest(CharSequence charSequence, String str) throws HttpRequestException {
        try {
            this.a = new URL(charSequence.toString());
            this.e = str;
        } catch (MalformedURLException e) {
            throw new HttpRequestException(e);
        }
    }

    private Proxy p() {
        return new Proxy(Type.HTTP, new InetSocketAddress(this.k, this.l));
    }

    private HttpURLConnection q() {
        try {
            HttpURLConnection a;
            if (this.k != null) {
                a = c.a(this.a, p());
            } else {
                a = c.a(this.a);
            }
            a.setRequestMethod(this.e);
            return a;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(o());
        stringBuilder.append(' ');
        stringBuilder.append(n());
        return stringBuilder.toString();
    }

    public HttpURLConnection a() {
        if (this.d == null) {
            this.d = q();
        }
        return this.d;
    }

    public int b() throws HttpRequestException {
        try {
            j();
            return a().getResponseCode();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* Access modifiers changed, original: protected */
    public ByteArrayOutputStream c() {
        int i = i();
        if (i > 0) {
            return new ByteArrayOutputStream(i);
        }
        return new ByteArrayOutputStream();
    }

    public String a(String str) throws HttpRequestException {
        OutputStream c = c();
        try {
            a(e(), c);
            return c.toString(f(str));
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String d() throws HttpRequestException {
        return a(g());
    }

    public BufferedInputStream e() throws HttpRequestException {
        return new BufferedInputStream(f(), this.j);
    }

    public InputStream f() throws HttpRequestException {
        InputStream inputStream;
        if (b() < 400) {
            try {
                inputStream = a().getInputStream();
            } catch (IOException e) {
                throw new HttpRequestException(e);
            }
        }
        inputStream = a().getErrorStream();
        if (inputStream == null) {
            try {
                inputStream = a().getInputStream();
            } catch (IOException e2) {
                throw new HttpRequestException(e2);
            }
        }
        if (!this.i || !"gzip".equals(h())) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e22) {
            throw new HttpRequestException(e22);
        }
    }

    public HttpRequest a(int i) {
        a().setConnectTimeout(i);
        return this;
    }

    public HttpRequest a(String str, String str2) {
        a().setRequestProperty(str, str2);
        return this;
    }

    public HttpRequest a(Entry<String, String> entry) {
        return a((String) entry.getKey(), (String) entry.getValue());
    }

    public String b(String str) throws HttpRequestException {
        k();
        return a().getHeaderField(str);
    }

    public int c(String str) throws HttpRequestException {
        return a(str, -1);
    }

    public int a(String str, int i) throws HttpRequestException {
        k();
        return a().getHeaderFieldInt(str, i);
    }

    public String b(String str, String str2) {
        return c(b(str), str2);
    }

    /* Access modifiers changed, original: protected */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0025  */
    public java.lang.String c(java.lang.String r9, java.lang.String r10) {
        /*
        r8 = this;
        r0 = 0;
        if (r9 == 0) goto L_0x0071;
    L_0x0003:
        r1 = r9.length();
        if (r1 != 0) goto L_0x000a;
    L_0x0009:
        goto L_0x0071;
    L_0x000a:
        r1 = r9.length();
        r2 = 59;
        r3 = r9.indexOf(r2);
        r4 = 1;
        r3 = r3 + r4;
        if (r3 == 0) goto L_0x0070;
    L_0x0018:
        if (r3 != r1) goto L_0x001b;
    L_0x001a:
        goto L_0x0070;
    L_0x001b:
        r5 = r9.indexOf(r2, r3);
        r6 = -1;
        if (r5 != r6) goto L_0x0023;
    L_0x0022:
        r5 = r1;
    L_0x0023:
        if (r3 >= r5) goto L_0x006f;
    L_0x0025:
        r7 = 61;
        r7 = r9.indexOf(r7, r3);
        if (r7 == r6) goto L_0x0066;
    L_0x002d:
        if (r7 >= r5) goto L_0x0066;
    L_0x002f:
        r3 = r9.substring(r3, r7);
        r3 = r3.trim();
        r3 = r10.equals(r3);
        if (r3 == 0) goto L_0x0066;
    L_0x003d:
        r7 = r7 + 1;
        r3 = r9.substring(r7, r5);
        r3 = r3.trim();
        r7 = r3.length();
        if (r7 == 0) goto L_0x0066;
    L_0x004d:
        r9 = 2;
        if (r7 <= r9) goto L_0x0065;
    L_0x0050:
        r9 = 0;
        r9 = r3.charAt(r9);
        r10 = 34;
        if (r10 != r9) goto L_0x0065;
    L_0x0059:
        r7 = r7 - r4;
        r9 = r3.charAt(r7);
        if (r10 != r9) goto L_0x0065;
    L_0x0060:
        r9 = r3.substring(r4, r7);
        return r9;
    L_0x0065:
        return r3;
    L_0x0066:
        r3 = r5 + 1;
        r5 = r9.indexOf(r2, r3);
        if (r5 != r6) goto L_0x0023;
    L_0x006e:
        goto L_0x0022;
    L_0x006f:
        return r0;
    L_0x0070:
        return r0;
    L_0x0071:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.network.HttpRequest.c(java.lang.String, java.lang.String):java.lang.String");
    }

    public String g() {
        return b("Content-Type", "charset");
    }

    public HttpRequest a(boolean z) {
        a().setUseCaches(z);
        return this;
    }

    public String h() {
        return b("Content-Encoding");
    }

    public HttpRequest d(String str) {
        return d(str, null);
    }

    public HttpRequest d(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return a("Content-Type", str);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("; charset=");
        stringBuilder.append(str2);
        return a("Content-Type", stringBuilder.toString());
    }

    public int i() {
        return c("Content-Length");
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest a(InputStream inputStream, OutputStream outputStream) throws IOException {
        final InputStream inputStream2 = inputStream;
        final OutputStream outputStream2 = outputStream;
        return (HttpRequest) new a<HttpRequest>(inputStream, this.h) {
            /* renamed from: a */
            public HttpRequest b() throws IOException {
                byte[] bArr = new byte[HttpRequest.this.j];
                while (true) {
                    int read = inputStream2.read(bArr);
                    if (read == -1) {
                        return HttpRequest.this;
                    }
                    outputStream2.write(bArr, 0, read);
                }
            }
        }.call();
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest j() throws IOException {
        if (this.f == null) {
            return this;
        }
        if (this.g) {
            this.f.a("\r\n--00content0boundary00--\r\n");
        }
        if (this.h) {
            try {
                this.f.close();
            } catch (IOException unused) {
            }
        } else {
            this.f.close();
        }
        this.f = null;
        return this;
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest k() throws HttpRequestException {
        try {
            return j();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest l() throws IOException {
        if (this.f != null) {
            return this;
        }
        a().setDoOutput(true);
        this.f = new d(a().getOutputStream(), c(a().getRequestProperty("Content-Type"), "charset"), this.j);
        return this;
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest m() throws IOException {
        if (this.g) {
            this.f.a("\r\n--00content0boundary00\r\n");
        } else {
            this.g = true;
            d("multipart/form-data; boundary=00content0boundary00").l();
            this.f.a("--00content0boundary00\r\n");
        }
        return this;
    }

    /* Access modifiers changed, original: protected */
    public HttpRequest a(String str, String str2, String str3) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("form-data; name=\"");
        stringBuilder.append(str);
        if (str2 != null) {
            stringBuilder.append("\"; filename=\"");
            stringBuilder.append(str2);
        }
        stringBuilder.append('\"');
        f("Content-Disposition", stringBuilder.toString());
        if (str3 != null) {
            f("Content-Type", str3);
        }
        return f((CharSequence) "\r\n");
    }

    public HttpRequest e(String str, String str2) {
        return b(str, null, str2);
    }

    public HttpRequest b(String str, String str2, String str3) throws HttpRequestException {
        return a(str, str2, null, str3);
    }

    public HttpRequest a(String str, String str2, String str3, String str4) throws HttpRequestException {
        try {
            m();
            a(str, str2, str3);
            this.f.a(str4);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest a(String str, Number number) throws HttpRequestException {
        return a(str, null, number);
    }

    public HttpRequest a(String str, String str2, Number number) throws HttpRequestException {
        return b(str, str2, number != null ? number.toString() : null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0024 A:{SYNTHETIC, Splitter:B:19:0x0024} */
    public io.fabric.sdk.android.services.network.HttpRequest a(java.lang.String r4, java.lang.String r5, java.lang.String r6, java.io.File r7) throws io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException {
        /*
        r3 = this;
        r0 = 0;
        r1 = new java.io.BufferedInputStream;	 Catch:{ IOException -> 0x001b }
        r2 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x001b }
        r2.<init>(r7);	 Catch:{ IOException -> 0x001b }
        r1.<init>(r2);	 Catch:{ IOException -> 0x001b }
        r4 = r3.a(r4, r5, r6, r1);	 Catch:{ IOException -> 0x0016, all -> 0x0013 }
        r1.close();	 Catch:{ IOException -> 0x0012 }
    L_0x0012:
        return r4;
    L_0x0013:
        r4 = move-exception;
        r0 = r1;
        goto L_0x0022;
    L_0x0016:
        r4 = move-exception;
        r0 = r1;
        goto L_0x001c;
    L_0x0019:
        r4 = move-exception;
        goto L_0x0022;
    L_0x001b:
        r4 = move-exception;
    L_0x001c:
        r5 = new io.fabric.sdk.android.services.network.HttpRequest$HttpRequestException;	 Catch:{ all -> 0x0019 }
        r5.<init>(r4);	 Catch:{ all -> 0x0019 }
        throw r5;	 Catch:{ all -> 0x0019 }
    L_0x0022:
        if (r0 == 0) goto L_0x0027;
    L_0x0024:
        r0.close();	 Catch:{ IOException -> 0x0027 }
    L_0x0027:
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.network.HttpRequest.a(java.lang.String, java.lang.String, java.lang.String, java.io.File):io.fabric.sdk.android.services.network.HttpRequest");
    }

    public HttpRequest a(String str, String str2, String str3, InputStream inputStream) throws HttpRequestException {
        try {
            m();
            a(str, str2, str3);
            a(inputStream, this.f);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest f(String str, String str2) throws HttpRequestException {
        return f((CharSequence) str).f((CharSequence) ": ").f((CharSequence) str2).f((CharSequence) "\r\n");
    }

    public HttpRequest f(CharSequence charSequence) throws HttpRequestException {
        try {
            l();
            this.f.a(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public URL n() {
        return a().getURL();
    }

    public String o() {
        return a().getRequestMethod();
    }
}
