package defpackage;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: AESUtil */
/* renamed from: pt */
public class pt {
    @NonNull
    public static String a(String str, String str2) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte[] bytes = str.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec("16-Bytes--String".getBytes());
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, ivParameterSpec);
        return pu.a(instance.doFinal(bytes));
    }

    @NonNull
    public static String b(String str, String str2) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte[] b = pu.b(str);
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec("16-Bytes--String".getBytes());
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, ivParameterSpec);
        return new String(instance.doFinal(b), "UTF-8");
    }
}
