package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.Util;
import okhttp3.internal.tls.TrustRootIndex;

final class AndroidPlatform$AndroidTrustRootIndex implements TrustRootIndex {
    private final Method findByIssuerAndSignatureMethod;
    private final X509TrustManager trustManager;

    AndroidPlatform$AndroidTrustRootIndex(X509TrustManager x509TrustManager, Method method) {
        this.findByIssuerAndSignatureMethod = method;
        this.trustManager = x509TrustManager;
    }

    public X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate) {
        try {
            TrustAnchor trustAnchor = (TrustAnchor) this.findByIssuerAndSignatureMethod.invoke(this.trustManager, new Object[]{x509Certificate});
            return trustAnchor != null ? trustAnchor.getTrustedCert() : null;
        } catch (IllegalAccessException e) {
            throw Util.assertionError("unable to get issues and signature", e);
        } catch (InvocationTargetException unused) {
            return null;
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AndroidPlatform$AndroidTrustRootIndex)) {
            return false;
        }
        AndroidPlatform$AndroidTrustRootIndex androidPlatform$AndroidTrustRootIndex = (AndroidPlatform$AndroidTrustRootIndex) obj;
        if (!(this.trustManager.equals(androidPlatform$AndroidTrustRootIndex.trustManager) && this.findByIssuerAndSignatureMethod.equals(androidPlatform$AndroidTrustRootIndex.findByIssuerAndSignatureMethod))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.trustManager.hashCode() + (this.findByIssuerAndSignatureMethod.hashCode() * 31);
    }
}
