package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.internal.tls.CertificateChainCleaner;

final class AndroidPlatform$AndroidCertificateChainCleaner extends CertificateChainCleaner {
    private final Method checkServerTrusted;
    private final Object x509TrustManagerExtensions;

    public int hashCode() {
        return 0;
    }

    AndroidPlatform$AndroidCertificateChainCleaner(Object obj, Method method) {
        this.x509TrustManagerExtensions = obj;
        this.checkServerTrusted = method;
    }

    public List<Certificate> clean(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
        try {
            X509Certificate[] x509CertificateArr = (X509Certificate[]) list.toArray(new X509Certificate[list.size()]);
            return (List) this.checkServerTrusted.invoke(this.x509TrustManagerExtensions, new Object[]{x509CertificateArr, "RSA", str});
        } catch (InvocationTargetException e) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e.getMessage());
            sSLPeerUnverifiedException.initCause(e);
            throw sSLPeerUnverifiedException;
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean equals(Object obj) {
        return obj instanceof AndroidPlatform$AndroidCertificateChainCleaner;
    }
}
