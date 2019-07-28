package com.tomatolive.library.http.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class HttpsUtils {

    private static class MyTrustManager implements X509TrustManager {
        private X509TrustManager defaultTrustManager;
        private X509TrustManager localTrustManager;

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public MyTrustManager(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyStoreException {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            this.defaultTrustManager = HttpsUtils.chooseTrustManager(instance.getTrustManagers());
            this.localTrustManager = x509TrustManager;
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            try {
                this.defaultTrustManager.checkServerTrusted(x509CertificateArr, str);
            } catch (CertificateException unused) {
                this.localTrustManager.checkServerTrusted(x509CertificateArr, str);
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static class SSLParams {
        public SSLSocketFactory sSLSocketFactory;
        public X509TrustManager trustManager;
    }

    private static class UnSafeTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        private UnSafeTrustManager() {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static SSLParams getSslSocketFactory(InputStream[] inputStreamArr, InputStream inputStream, String str) {
        SSLParams sSLParams = new SSLParams();
        try {
            X509TrustManager myTrustManager;
            TrustManager[] prepareTrustManager = prepareTrustManager(inputStreamArr);
            KeyManager[] prepareKeyManager = prepareKeyManager(inputStream, str);
            SSLContext instance = SSLContext.getInstance("TLS");
            if (prepareTrustManager != null) {
                myTrustManager = new MyTrustManager(chooseTrustManager(prepareTrustManager));
            } else {
                myTrustManager = new UnSafeTrustManager();
            }
            instance.init(prepareKeyManager, new TrustManager[]{myTrustManager}, new SecureRandom());
            sSLParams.sSLSocketFactory = instance.getSocketFactory();
            sSLParams.trustManager = myTrustManager;
            return sSLParams;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return sSLParams;
        } catch (KeyManagementException e2) {
            e2.printStackTrace();
            return sSLParams;
        } catch (KeyStoreException e3) {
            e3.printStackTrace();
            return sSLParams;
        } catch (Exception e4) {
            e4.printStackTrace();
            return sSLParams;
        }
    }

    private static TrustManager[] prepareTrustManager(InputStream... inputStreamArr) {
        if (inputStreamArr == null || inputStreamArr.length <= 0) {
            return null;
        }
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            KeyStore instance2 = KeyStore.getInstance(KeyStore.getDefaultType());
            instance2.load(null);
            int length = inputStreamArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                InputStream inputStream = inputStreamArr[i];
                int i3 = i2 + 1;
                instance2.setCertificateEntry(Integer.toString(i2), instance.generateCertificate(inputStream));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused) {
                    }
                }
                i++;
                i2 = i3;
            }
            TrustManagerFactory instance3 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance3.init(instance2);
            return instance3.getTrustManagers();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (CertificateException e2) {
            e2.printStackTrace();
            return null;
        } catch (KeyStoreException e3) {
            e3.printStackTrace();
            return null;
        } catch (Exception e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private static KeyManager[] prepareKeyManager(InputStream inputStream, String str) {
        if (inputStream == null || str == null) {
            return null;
        }
        try {
            KeyStore instance = KeyStore.getInstance("BKS");
            instance.load(inputStream, str.toCharArray());
            KeyManagerFactory instance2 = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            instance2.init(instance, str.toCharArray());
            return instance2.getKeyManagers();
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        } catch (UnrecoverableKeyException e3) {
            e3.printStackTrace();
            return null;
        } catch (CertificateException e4) {
            e4.printStackTrace();
            return null;
        } catch (IOException e5) {
            e5.printStackTrace();
            return null;
        } catch (Exception e6) {
            e6.printStackTrace();
            return null;
        }
    }

    private static X509TrustManager chooseTrustManager(TrustManager[] trustManagerArr) {
        for (TrustManager trustManager : trustManagerArr) {
            if (trustManager instanceof X509TrustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        return null;
    }
}
