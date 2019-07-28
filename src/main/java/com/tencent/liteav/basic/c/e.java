package com.tencent.liteav.basic.c;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import com.tencent.liteav.basic.util.a;
import java.io.File;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LicenceCheck */
public class e {
    private static e l;
    private Context a;
    private String b;
    private String c;
    private String d = "TXUgcSDK.licence";
    private String e = "tmp.licence";
    private String f = "YTFaceSDK.licence";
    private String g = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq4teqkW/TUruU89ElNVd\nKrpSL+HCITruyb6BS9mW6M4mqmxDhazDmQgMKNfsA0d2kxFucCsXTyesFNajaisk\nrAzVJpNGO75bQFap4jYzJYskIuas6fgIS7zSmGXgRcp6i0ZBH3pkVCXcgfLfsVCO\n+sN01jFhFgOC0LY2f1pJ+3jqktAlMIxy8Q9t7XwwL5/n8/Sledp7TwuRdnl2OPl3\nycCTRkXtOIoRNB9vgd9XooTKiEdCXC7W9ryvtwCiAB82vEfHWXXgzhsPC13URuFy\n1JqbWJtTCCcfsCVxuBplhVJAQ7JsF5SMntdJDkp7rJLhprgsaim2CRjcVseNmw97\nbwIDAQAB";
    private String h;
    private String i;
    private String j;
    private boolean k = false;
    private boolean m = false;
    private int n = -1;

    public static e a() {
        if (l == null) {
            l = new e();
        }
        return l;
    }

    private e() {
    }

    public void a(Context context, String str, String str2) {
        this.a = context.getApplicationContext();
        this.b = str;
        this.c = str2;
        if (this.a != null && d()) {
            this.h = this.a.getExternalFilesDir(null).getAbsolutePath();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.h);
            stringBuilder.append(File.separator);
            stringBuilder.append(this.d);
            if (!b(stringBuilder.toString())) {
                TXCLog.d("LicenceCheck", "setLicense, sdcard file not exist, to download");
                b();
            }
        }
    }

    public void b() {
        if (TextUtils.isEmpty(this.b)) {
            TXCLog.e("LicenceCheck", "downloadLicense, mUrl is empty, ignore!");
        } else if (this.k) {
            TXCLog.i("LicenceCheck", "downloadLicense, in downloading, ignore");
        } else {
            AnonymousClass1 anonymousClass1 = new b() {
                public void a(File file) {
                    TXCLog.i("LicenceCheck", "downloadLicense, onSaveSuccess");
                    String a = e.this.h();
                    if (TextUtils.isEmpty(a)) {
                        TXCLog.e("LicenceCheck", "downloadLicense, readDownloadTempLicence is empty!");
                        e.this.k = false;
                        return;
                    }
                    if (e.this.b(a, null) == 0) {
                        e.this.f();
                    }
                }

                public void a(File file, Exception exception) {
                    TXCLog.i("LicenceCheck", "downloadLicense, onSaveFailed");
                }

                public void a(int i) {
                    TXCLog.i("LicenceCheck", "downloadLicense, onProgressUpdate");
                }

                public void a() {
                    TXCLog.i("LicenceCheck", "downloadLicense, onProcessEnd");
                    e.this.k = false;
                }
            };
            if (this.a == null) {
                TXCLog.e("LicenceCheck", "context is NULL !!! Please set context in method:setLicense(Context context, String url, String key)");
                return;
            }
            File externalFilesDir = this.a.getExternalFilesDir(null);
            if (externalFilesDir == null) {
                TXCLog.e("LicenceCheck", "Please check permission WRITE_EXTERNAL_STORAGE permission has been set !!!");
                return;
            }
            this.h = externalFilesDir.getAbsolutePath();
            new Thread(new c(this.a, this.b, this.h, this.e, anonymousClass1, false)).start();
            this.k = true;
        }
    }

    public int a(f fVar, Context context) {
        int b = b(fVar, context);
        if (b != 0) {
            b();
        }
        return b;
    }

    private int b(f fVar, Context context) {
        if (this.m) {
            return 0;
        }
        if (this.a == null) {
            this.a = context;
        }
        if (b(fVar) == 0) {
            this.m = true;
            return 0;
        }
        int a = a(fVar);
        if (a != 0) {
            return a;
        }
        this.m = true;
        return 0;
    }

    private int a(f fVar) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File externalFilesDir = this.a.getExternalFilesDir(null);
            if (externalFilesDir == null) {
                TXCLog.e("LicenceCheck", "checkSdcardLicence, mContext.getExternalFilesDir is null!");
                return -10;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(externalFilesDir.getAbsolutePath());
            stringBuilder.append(File.separator);
            stringBuilder.append(this.d);
            String stringBuilder2 = stringBuilder.toString();
            if (!b(stringBuilder2)) {
                return -7;
            }
            stringBuilder2 = a.b(stringBuilder2);
            if (!TextUtils.isEmpty(stringBuilder2)) {
                return a(stringBuilder2, fVar);
            }
            TXCLog.e("LicenceCheck", "checkSdcardLicence, licenceSdcardStr is empty");
            return -8;
        }
        TXCLog.e("LicenceCheck", "checkSdcardLicence, sdcard not mounted yet!");
        return -10;
    }

    private int b(f fVar) {
        if (!e()) {
            return -6;
        }
        String b = a.b(this.a, this.d);
        if (!TextUtils.isEmpty(b)) {
            return a(b, fVar);
        }
        TXCLog.e("LicenceCheck", "checkAssetLicence, licenceSdcardStr is empty");
        return -8;
    }

    public int a(String str, f fVar) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return b(str, fVar);
        } catch (JSONException unused) {
            return c(str, fVar);
        }
    }

    private boolean d() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            TXCLog.e("LicenceCheck", "checkSdcardLicence, sdcard not mounted yet!");
            return false;
        } else if (this.a.getExternalFilesDir(null) != null) {
            return true;
        } else {
            TXCLog.e("LicenceCheck", "checkSdcardLicence, mContext.getExternalFilesDir is null!");
            return false;
        }
    }

    private boolean e() {
        return a.a(this.a, this.d);
    }

    private boolean b(String str) {
        return a.a(str);
    }

    private void f() {
        boolean delete;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a.getExternalFilesDir(null).getAbsolutePath());
        stringBuilder.append(File.separator);
        stringBuilder.append(this.d);
        File file = new File(stringBuilder.toString());
        if (file.exists()) {
            delete = file.delete();
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("delete dst file:");
            stringBuilder2.append(delete);
            TXCLog.i("LicenceCheck", stringBuilder2.toString());
        }
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(this.h);
        stringBuilder3.append(File.separator);
        stringBuilder3.append(this.e);
        File file2 = new File(stringBuilder3.toString());
        if (file2.exists()) {
            delete = file2.renameTo(file);
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("rename file:");
            stringBuilder3.append(delete);
            TXCLog.i("LicenceCheck", stringBuilder3.toString());
        }
        this.m = true;
    }

    private static long c(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public PublicKey a(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
    }

    private String g() {
        if (TextUtils.isEmpty(this.c)) {
            TXCLog.e("LicenceCheck", "decodeLicence, mKey is empty!!!");
            return "";
        }
        String str;
        byte[] bytes = this.c.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");
        byte[] bArr = new byte[16];
        int i = 0;
        while (i < bytes.length && i < bArr.length) {
            bArr[i] = bytes[i];
            i++;
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
        bArr = Base64.decode(this.i, 0);
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            str = new String(instance.doFinal(bArr), "UTF-8");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("decodeLicence : ");
            stringBuilder.append(str);
            TXCLog.i("LicenceCheck", stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        return str;
    }

    private int b(String str, f fVar) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("appId");
            this.i = jSONObject.getString("encryptedLicense");
            this.j = jSONObject.getString("signature");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("appid:");
            stringBuilder.append(optInt);
            TXCLog.i("LicenceCheck", stringBuilder.toString());
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("encryptedLicense:");
            stringBuilder2.append(this.i);
            TXCLog.i("LicenceCheck", stringBuilder2.toString());
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("signature:");
            stringBuilder2.append(this.j);
            TXCLog.i("LicenceCheck", stringBuilder2.toString());
            return c(fVar);
        } catch (JSONException e) {
            this.i = null;
            this.j = null;
            e.printStackTrace();
            a(-1);
            return -1;
        }
    }

    private int c(f fVar) {
        boolean a;
        try {
            a = a(Base64.decode(this.i, 0), Base64.decode(this.j, 0), a(this.g));
        } catch (Exception e) {
            e.printStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("verifyLicence, exception is : ");
            stringBuilder.append(e);
            TXCLog.e("LicenceCheck", stringBuilder.toString());
            a = false;
        }
        if (a) {
            String g = g();
            if (TextUtils.isEmpty(g)) {
                a(-3);
                TXCLog.e("LicenceCheck", "verifyLicence, decodeValue is empty!");
                return -3;
            }
            if (fVar != null) {
                fVar.a = g;
            }
            try {
                JSONObject jSONObject = new JSONObject(g);
                g = jSONObject.getString("pituLicense");
                JSONArray optJSONArray = jSONObject.optJSONArray("appData");
                if (optJSONArray == null) {
                    TXCLog.e("LicenceCheck", "verifyLicence, appDataArray is null!");
                    a(-1);
                    return -1;
                }
                Object obj;
                int i = 0;
                Object obj2 = null;
                while (true) {
                    obj = 1;
                    if (i >= optJSONArray.length()) {
                        obj = null;
                        break;
                    }
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                    String optString = jSONObject2.optString("packageName");
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("verifyLicence, packageName:");
                    stringBuilder2.append(optString);
                    TXCLog.i("LicenceCheck", stringBuilder2.toString());
                    if (optString.equals(this.a.getPackageName())) {
                        if (!d(jSONObject2.optString("endDate"))) {
                            a(jSONObject2, g);
                            obj2 = 1;
                            break;
                        }
                        obj2 = 1;
                    } else {
                        TXCLog.e("LicenceCheck", "verifyLicence, packageName not match!");
                        obj2 = null;
                    }
                    i++;
                }
                if (obj2 == null) {
                    a(-4);
                    return -4;
                } else if (obj == null) {
                    a(-5);
                    return -5;
                } else {
                    if (!TextUtils.isEmpty(g)) {
                        byte[] decode = Base64.decode(g, 0);
                        String absolutePath = this.a.getExternalFilesDir(null).getAbsolutePath();
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append(absolutePath);
                        stringBuilder3.append(File.separator);
                        stringBuilder3.append(this.f);
                        File file = new File(stringBuilder3.toString());
                        a.a(file.getAbsolutePath(), decode);
                        TXCCommonUtil.setPituLicencePath(file.getAbsolutePath());
                    }
                    TXCDRApi.txReportDAU(this.a, com.tencent.liteav.basic.datareport.a.aI);
                    return 0;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("verifyLicence, json format error ! exception = ");
                stringBuilder4.append(e2);
                TXCLog.e("LicenceCheck", stringBuilder4.toString());
                a(-1);
                return -1;
            }
        }
        a(-2);
        TXCLog.e("LicenceCheck", "verifyLicence, signature not pass!");
        return -2;
    }

    private void a(JSONObject jSONObject, String str) {
        int optInt = jSONObject.optInt("feature");
        StringBuilder stringBuilder;
        if (optInt <= 1) {
            if (TextUtils.isEmpty(str)) {
                this.n = 3;
            } else {
                this.n = 5;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("parseVersionType, licence is old, mLicenceVersionType = ");
            stringBuilder.append(this.n);
            TXCLog.i("LicenceCheck", stringBuilder.toString());
            return;
        }
        this.n = optInt & 15;
        stringBuilder = new StringBuilder();
        stringBuilder.append("parseVersionType, mLicenceVersionType = ");
        stringBuilder.append(this.n);
        TXCLog.i("LicenceCheck", stringBuilder.toString());
    }

    public int c() {
        return this.n;
    }

    private void a(int i) {
        TXCDRApi.txReportDAU(this.a, com.tencent.liteav.basic.datareport.a.aJ, i, "");
    }

    private boolean d(String str) {
        long c = c(str);
        if (c < 0) {
            TXCLog.e("LicenceCheck", "checkEndDate, end date millis < 0!");
            return true;
        } else if (c >= System.currentTimeMillis()) {
            return false;
        } else {
            TXCLog.e("LicenceCheck", "checkEndDate, end date expire!");
            return true;
        }
    }

    private int c(String str, f fVar) {
        str = e(str);
        if (TextUtils.isEmpty(str)) {
            TXCLog.e("LicenceCheck", "verifyOldLicence, decryptStr is empty");
            return -3;
        }
        if (fVar != null) {
            fVar.a = str;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.getString("packagename").equals(a(this.a))) {
                TXCLog.e("LicenceCheck", "packagename not match!");
                a(-4);
                return -4;
            } else if (d(jSONObject.getString("enddate"))) {
                return -5;
            } else {
                this.n = 5;
                TXCDRApi.txReportDAU(this.a, com.tencent.liteav.basic.datareport.a.aI);
                return 0;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            TXCLog.e("LicenceCheck", "verifyOldLicence, json format error !");
            a(-1);
            return -1;
        }
    }

    private static String a(Context context) {
        int myPid = Process.myPid();
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    private String e(String str) {
        try {
            return new String(g.b(Base64.decode(str, 0), Base64.decode("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKfMXaF6wx9lev2U\nIzkk6ydI2sdaSQAD2ZvDBLq+5Fm6nGwSSWawl03D4vHcWIUa3wnz6f19/y8wzrj4\nnTfcEnT94SPdB6GhGsqPwbwRp9MHAqd/2gWZxSb005il2yiOZafk6X4NGKCn2tGd\nyNaCF+m9rLykuLdZHB0Z53ivgseNAgMBAAECgYAvXI2pAH+Goxwd6uwuOu9svTGT\nRzaHnI6VWmxBUZQeh3+TOW4iYAG03291GN6bY0RFCOWouSGH7lzK9NFbbPCAQ/hx\ncO48PqioHoq7K8sqzd3XaYBv39HrRnM8JvZsqv0PLJwX/LGm2y/MRaKAC6bcHtse\npgh+NNmUxXNRcTMRAQJBANezmenBcR8HTcY5YaEk3SQRzOo+QhIXuuD4T/FESpVJ\nmVQGxJjLsEBua1j38WG2QuepE5JiVbkQ0jQSvhUiZK0CQQDHJa+vWu6l72lQAvIx\nwmRISorvLb/tnu5bH0Ele42oX+w4p/tm03awdVjhVANnpDjYS2H6EzrF/pfis7k9\nV2phAkB4E4gz47bYYhV+qsTZkw70HGCpab0YG1OyFylRkwW983nCl/3rXUChrZZe\nsbATCAZYtfuqOsmju2R5DpH4a+wFAkBmHlcWbmSNxlSUaM5U4b+WqlLQDv+qE6Na\nKo63b8HWI0n4S3tI4QqttZ7b/L66OKXFk/Ir0AyFVuX/o/VLFTZBAkAdSTEkGwE5\nGQmhxu95sKxmdlUY6Q0Gwwpi06C1BPBrj2VkGXpBP0twhPVAq/3xVjjb+2KXVTUW\nIpRLc06M4vhv", 0)));
        } catch (Exception e) {
            e.printStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("decryptLicenceStr, exception is : ");
            stringBuilder.append(e);
            TXCLog.e("LicenceCheck", stringBuilder.toString());
            return null;
        }
    }

    private String h() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.h);
        stringBuilder.append(File.separator);
        stringBuilder.append(this.e);
        return a.b(new File(stringBuilder.toString()).getAbsolutePath());
    }

    public static boolean a(byte[] bArr, byte[] bArr2, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature instance = Signature.getInstance("SHA256WithRSA");
        instance.initVerify(publicKey);
        instance.update(bArr);
        return instance.verify(bArr2);
    }
}
