package com.tencent.liteav.audio.impl;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.tencent.liteav.audio.TXCLiveBGMPlayer;
import com.tencent.liteav.audio.d;
import com.tencent.liteav.audio.impl.Play.TXCAudioBasePlayController;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.basic.util.b;
import java.io.File;
import java.lang.ref.WeakReference;

public class TXCTraeJNI {
    private static Context mContext;
    private static WeakReference<d> mTraeRecordListener;

    public static native void nativeAppendLibraryPath(String str);

    public static native void nativeCacheClassForNative();

    public static native void nativeSetAudioMode(int i);

    public static native void nativeSetTraeConfig(String str);

    public static native boolean nativeTraeIsPlaying();

    public static native boolean nativeTraeIsRecording();

    public static native void nativeTraeSetChangerType(int i, int i2);

    public static native void nativeTraeSetRecordMute(boolean z);

    public static native void nativeTraeSetRecordReverb(int i);

    public static native void nativeTraeSetVolume(float f);

    public static native void nativeTraeStartPlay(Context context);

    public static native void nativeTraeStartRecord(Context context, int i, int i2, int i3);

    public static native void nativeTraeStopPlay();

    public static native void nativeTraeStopRecord(boolean z);

    static {
        b.e();
        nativeCacheClassForNative();
    }

    public static void setContext(Context context) {
        mContext = context;
    }

    public static void setTraeRecordListener(WeakReference<d> weakReference) {
        mTraeRecordListener = weakReference;
    }

    public static void onRecordRawPcmData(byte[] bArr, int i, int i2, int i3) {
        if (mTraeRecordListener != null && mTraeRecordListener.get() != null) {
            ((d) mTraeRecordListener.get()).onRecordRawPcmData(bArr, TXCTimeUtil.getTimeTick(), i, i2, i3, false);
        }
    }

    public static void onRecordPcmData(byte[] bArr, int i, int i2, int i3) {
        if (mTraeRecordListener != null && mTraeRecordListener.get() != null) {
            ((d) mTraeRecordListener.get()).onRecordPcmData(bArr, TXCTimeUtil.getTimeTick(), i, i2, i3);
        }
    }

    public static void onRecordEncData(byte[] bArr, long j, int i, int i2, int i3) {
        if (mTraeRecordListener != null && mTraeRecordListener.get() != null) {
            ((d) mTraeRecordListener.get()).onRecordEncData(bArr, j, i, i2, i3);
        }
    }

    public static void onRecordError(int i, String str) {
        if (mTraeRecordListener != null && mTraeRecordListener.get() != null) {
            ((d) mTraeRecordListener.get()).onRecordError(i, str);
        }
    }

    public static void SetAudioMode(int i) {
        a.a().a(i);
    }

    public static void InitTraeEngineLibrary(Context context) {
        if (context == null) {
            TXCLog.e("TXCAudioJNI", "nativeInitTraeEngine failed, context is null!");
            return;
        }
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String str = applicationInfo.nativeLibraryDir;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(applicationInfo.dataDir);
            stringBuilder.append("/lib");
            String stringBuilder2 = stringBuilder.toString();
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("/data/data/");
            stringBuilder3.append(applicationInfo.packageName);
            stringBuilder3.append("/lib");
            String stringBuilder4 = stringBuilder3.toString();
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("add_libpath:");
            stringBuilder3.append(str);
            nativeAppendLibraryPath(stringBuilder3.toString());
            StringBuilder stringBuilder5 = new StringBuilder();
            stringBuilder5.append("add_libpath:");
            stringBuilder5.append(stringBuilder2);
            nativeAppendLibraryPath(stringBuilder5.toString());
            stringBuilder5 = new StringBuilder();
            stringBuilder5.append("add_libpath:");
            stringBuilder5.append(stringBuilder4);
            nativeAppendLibraryPath(stringBuilder5.toString());
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public static boolean nativeCheckTraeEngine(Context context) {
        if (context == null) {
            TXCLog.e("TXCAudioJNI", "nativeCheckTraeEngine failed, context is null!");
            return false;
        }
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String str = applicationInfo.nativeLibraryDir;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(applicationInfo.dataDir);
        stringBuilder.append("/lib");
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append("/data/data/");
        stringBuilder3.append(applicationInfo.packageName);
        stringBuilder3.append("/lib");
        String stringBuilder4 = stringBuilder3.toString();
        String f = b.f();
        if (f == null) {
            f = "";
        }
        String str2 = "/libtraeimp-rtmp-armeabi-v7a.so";
        String str3 = "/libtraeimp-rtmp-armeabi.so";
        StringBuilder stringBuilder5 = new StringBuilder();
        stringBuilder5.append(str);
        stringBuilder5.append(str2);
        if (new File(stringBuilder5.toString()).exists()) {
            return true;
        }
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append(stringBuilder2);
        stringBuilder6.append(str2);
        if (new File(stringBuilder6.toString()).exists()) {
            return true;
        }
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append(stringBuilder4);
        stringBuilder6.append(str2);
        if (new File(stringBuilder6.toString()).exists()) {
            return true;
        }
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append(f);
        stringBuilder6.append(str2);
        if (new File(stringBuilder6.toString()).exists()) {
            return true;
        }
        StringBuilder stringBuilder7 = new StringBuilder();
        stringBuilder7.append(str);
        stringBuilder7.append(str3);
        if (new File(stringBuilder7.toString()).exists()) {
            return true;
        }
        StringBuilder stringBuilder8 = new StringBuilder();
        stringBuilder8.append(stringBuilder2);
        stringBuilder8.append(str3);
        if (new File(stringBuilder8.toString()).exists()) {
            return true;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(stringBuilder4);
        stringBuilder.append(str3);
        if (new File(stringBuilder.toString()).exists()) {
            return true;
        }
        StringBuilder stringBuilder9 = new StringBuilder();
        stringBuilder9.append(f);
        stringBuilder9.append(str3);
        if (new File(stringBuilder9.toString()).exists()) {
            return true;
        }
        return false;
    }

    public static boolean traeStartPlay(Context context) {
        if (TXCAudioBasePlayController.nativeIsTracksEmpty() && !TXCLiveBGMPlayer.getInstance().isPlaying()) {
            return false;
        }
        InitTraeEngineLibrary(context);
        nativeTraeStartPlay(context);
        return true;
    }

    public static boolean traeStopPlay() {
        if (!TXCAudioBasePlayController.nativeIsTracksEmpty() || TXCLiveBGMPlayer.getInstance().isPlaying()) {
            return false;
        }
        nativeTraeStopPlay();
        return true;
    }
}
