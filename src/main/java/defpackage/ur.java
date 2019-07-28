package defpackage;

import android.annotation.SuppressLint;
import com.zzhoujay.richtext.exceptions.HttpResponseCodeException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* compiled from: DefaultImageDownloader */
/* renamed from: ur */
public class ur implements uu {
    public static final String a = "ur";
    private static SSLContext b;
    private static HostnameVerifier c = new 1();

    /* compiled from: DefaultImageDownloader */
    /* renamed from: ur$1 */
    static class 1 implements HostnameVerifier {
        @SuppressLint({"BadHostnameVerifier"})
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }

        1() {
        }
    }

    /* compiled from: DefaultImageDownloader */
    /* renamed from: ur$2 */
    static class 2 implements X509TrustManager {
        @SuppressLint({"TrustAllX509TrustManager"})
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @SuppressLint({"TrustAllX509TrustManager"})
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        2() {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* compiled from: DefaultImageDownloader */
    /* renamed from: ur$a */
    private static class a implements to {
        private final String a;
        private HttpURLConnection b;
        private InputStream c;

        /* synthetic */ a(String str, 1 1) {
            this(str);
        }

        private a(String str) {
            this.a = str;
        }

        public void b() throws IOException {
            if (this.c != null) {
                this.c.close();
            }
            if (this.b != null) {
                this.b.disconnect();
            }
        }

        public InputStream a() throws IOException {
            this.b = (HttpURLConnection) new URL(this.a).openConnection();
            this.b.setConnectTimeout(10000);
            this.b.setDoInput(true);
            this.b.addRequestProperty("Connection", "Keep-Alive");
            if (this.b instanceof HttpsURLConnection) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) this.b;
                httpsURLConnection.setHostnameVerifier(ur.c);
                httpsURLConnection.setSSLSocketFactory(ur.b.getSocketFactory());
            }
            this.b.connect();
            int responseCode = this.b.getResponseCode();
            if (responseCode == 200) {
                this.c = this.b.getInputStream();
                return this.c;
            }
            throw new HttpResponseCodeException(responseCode);
        }
    }

    public to a(String str) throws IOException {
        return new a(str, null);
    }

    static {
        2 2 = new 2();
        try {
            b = SSLContext.getInstance("SSL");
            b.init(null, new TrustManager[]{2}, new SecureRandom());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
