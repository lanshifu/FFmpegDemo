package io.fabric.sdk.android.services.network;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* compiled from: NetworkUtils */
public final class d {
    public static final SSLSocketFactory a(e eVar) throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext instance = SSLContext.getInstance("TLS");
        f fVar = new f(new g(eVar.a(), eVar.b()), eVar);
        instance.init(null, new TrustManager[]{fVar}, null);
        return instance.getSocketFactory();
    }
}
