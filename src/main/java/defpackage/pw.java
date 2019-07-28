package defpackage;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* compiled from: RSAEncryptor3 */
/* renamed from: pw */
public class pw {
    public static pw a;
    private static final char[] d = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private RSAPrivateKey b;
    private RSAPublicKey c;

    public pw(String str, String str2) {
        try {
            b(str);
            c(str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String a(String str) throws Exception {
        return new String(a(a(), pu.b(str)));
    }

    public RSAPrivateKey a() {
        return this.b;
    }

    public void b(String str) throws Exception {
        try {
            this.c = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(pu.b(str)));
        } catch (NoSuchAlgorithmException unused) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException unused2) {
            throw new Exception("公钥非法");
        } catch (NullPointerException unused3) {
            throw new Exception("公钥数据为空");
        }
    }

    public void c(String str) throws Exception {
        try {
            this.b = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(pu.b(str)));
        } catch (NoSuchAlgorithmException unused) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new Exception("私钥非法");
        } catch (NullPointerException unused2) {
            throw new Exception("私钥数据为空");
        }
    }

    public byte[] a(RSAPrivateKey rSAPrivateKey, byte[] bArr) throws Exception {
        if (rSAPrivateKey != null) {
            try {
                Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                instance.init(2, rSAPrivateKey);
                return instance.doFinal(bArr);
            } catch (NoSuchAlgorithmException unused) {
                throw new Exception("无此解密算法");
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
                return null;
            } catch (InvalidKeyException unused2) {
                throw new Exception("解密私钥非法,请检查");
            } catch (IllegalBlockSizeException unused3) {
                throw new Exception("密文长度非法");
            } catch (BadPaddingException unused4) {
                throw new Exception("密文数据已损坏");
            }
        }
        throw new Exception("解密私钥为空, 请设置");
    }
}
