package com.tencent.rtmp;

import android.content.Context;
import android.util.Log;
import com.tencent.liteav.basic.c.e;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import com.tencent.liteav.basic.util.b;
import com.tencent.liteav.u;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TXLiveBase {
    private static final String FILE_MD5_FFMPEG = "___md5_libtxffmpeg_md5__________";
    private static final String FILE_MD5_LITEAV = "___md5_libliteavsdk_md5_________";
    private static final String FILE_MD5_SATURN = "___md5_libsaturn_md5____________";
    private static final String FILE_MD5_TRAE = "___md5_libtraeimp_md5___________";
    private static final String TAG = "TXLiveBase";
    private static TXLiveBase instance = new TXLiveBase();
    private static ITXLiveBaseListener listener;

    private static class a implements com.tencent.liteav.basic.log.TXCLog.a {
        private a() {
        }

        public void a(int i, String str, String str2) {
            if (TXLiveBase.listener != null) {
                TXLiveBase.listener.OnLog(i, str, str2);
            }
        }
    }

    private TXLiveBase() {
    }

    public static TXLiveBase getInstance() {
        return instance;
    }

    public void setLicence(Context context, String str, String str2) {
        e.a().a(context, str, str2);
    }

    public static void setListener(ITXLiveBaseListener iTXLiveBaseListener) {
        TXCLog.setListener(new a());
        listener = iTXLiveBaseListener;
    }

    public static void setLogLevel(int i) {
        TXCLog.setLevel(i);
    }

    public static void setConsoleEnabled(boolean z) {
        TXCLog.setConsoleEnabled(z);
    }

    public static void setAppVersion(String str) {
        TXCDRApi.txSetAppVersion(str);
        TXCCommonUtil.setAppVersion(str);
    }

    public static void setLibraryPath(String str) {
        b.b(str);
    }

    public static boolean isLibraryPathValid(String str) {
        String fileMD5 = getFileMD5(str, "libliteavsdk.so");
        String fileMD52 = getFileMD5(str, "libsaturn.so");
        String fileMD53 = getFileMD5(str, "libtxffmpeg.so");
        str = getFileMD5(str, "libtraeimp-rtmp-armeabi.so");
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MD5-Check libliteavsdk = ");
        stringBuilder.append(fileMD5);
        stringBuilder.append(" FILE_MD5_LITEAV = ");
        stringBuilder.append(FILE_MD5_LITEAV);
        Log.e(str2, stringBuilder.toString());
        str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("MD5-Check libsaturn = ");
        stringBuilder.append(fileMD52);
        stringBuilder.append(" FILE_MD5_SATURN = ");
        stringBuilder.append(FILE_MD5_SATURN);
        Log.e(str2, stringBuilder.toString());
        str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("MD5-Check libtxffmpeg = ");
        stringBuilder.append(fileMD53);
        stringBuilder.append(" FILE_MD5_FFMPEG = ");
        stringBuilder.append(FILE_MD5_FFMPEG);
        Log.e(str2, stringBuilder.toString());
        str2 = TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("MD5-Check libtraeimpl = ");
        stringBuilder.append(str);
        stringBuilder.append(" FILE_MD5_TRAE = ");
        stringBuilder.append(FILE_MD5_TRAE);
        Log.e(str2, stringBuilder.toString());
        return fileMD5 != null && fileMD5.equalsIgnoreCase(FILE_MD5_LITEAV) && fileMD52 != null && fileMD52.equalsIgnoreCase(FILE_MD5_SATURN) && fileMD53 != null && fileMD53.equalsIgnoreCase(FILE_MD5_FFMPEG) && str != null && str.equalsIgnoreCase(FILE_MD5_TRAE);
    }

    public static String getSDKVersionStr() {
        return TXCCommonUtil.getSDKVersionStr();
    }

    public static void setPituLicencePath(String str) {
        TXCCommonUtil.setPituLicencePath(str);
    }

    public static String getPituSDKVersion() {
        return u.a();
    }

    public static void setAppID(String str) {
        TXCCommonUtil.setAppID(str);
    }

    private static String getFileMD5(String str, String str2) {
        NoSuchAlgorithmException e;
        FileNotFoundException e2;
        IOException e3;
        File file = new File(str, str2);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        MessageDigest instance;
        byte[] bArr = new byte[Filter.K];
        try {
            instance = MessageDigest.getInstance("MD5");
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                while (true) {
                    int read = fileInputStream.read(bArr, 0, Filter.K);
                    if (read == -1) {
                        break;
                    }
                    instance.update(bArr, 0, read);
                }
                fileInputStream.close();
            } catch (NoSuchAlgorithmException e4) {
                e = e4;
                e.printStackTrace();
                return new BigInteger(1, instance.digest()).toString(16);
            } catch (FileNotFoundException e5) {
                e2 = e5;
                e2.printStackTrace();
                return new BigInteger(1, instance.digest()).toString(16);
            } catch (IOException e6) {
                e3 = e6;
                e3.printStackTrace();
                return new BigInteger(1, instance.digest()).toString(16);
            }
        } catch (NoSuchAlgorithmException e7) {
            e = e7;
            instance = null;
            e.printStackTrace();
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (FileNotFoundException e8) {
            e2 = e8;
            instance = null;
            e2.printStackTrace();
            return new BigInteger(1, instance.digest()).toString(16);
        } catch (IOException e9) {
            e3 = e9;
            instance = null;
            e3.printStackTrace();
            return new BigInteger(1, instance.digest()).toString(16);
        }
        return new BigInteger(1, instance.digest()).toString(16);
    }
}
