package defpackage;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: MD5Util */
/* renamed from: pv */
public class pv {
    public static String a(@NonNull String str) {
        return TextUtils.isEmpty(str) ? "" : pv.a(str, "");
    }

    public static String a(@NonNull String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(str2);
            str2 = "";
            for (byte b : instance.digest(stringBuilder.toString().getBytes())) {
                StringBuilder stringBuilder2;
                String toHexString = Integer.toHexString(b & 255);
                if (toHexString.length() == 1) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("0");
                    stringBuilder2.append(toHexString);
                    toHexString = stringBuilder2.toString();
                }
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(str2);
                stringBuilder2.append(toHexString);
                str2 = stringBuilder2.toString();
            }
            return str2;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String a(@NonNull File file) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[Filter.K];
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, Filter.K);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return new BigInteger(1, instance.digest()).toString(16);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
