package com.tencent.liteav.basic.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.liteav.basic.d.a;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.ugc.TXRecordCommon;
import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import com.yalantis.ucrop.view.CropImageView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.UUID;

/* compiled from: TXCSystemUtil */
public class b {
    public static long a = 0;
    private static float b = 0.0f;
    private static float c = 0.0f;
    private static float d = 0.0f;
    private static float e = 0.0f;
    private static float f = 0.0f;
    private static float g = 0.0f;
    private static boolean h = true;
    private static int[] i = new int[2];
    private static long j = 0;
    private static String k = "";
    private static int l = 0;
    private static long m = 0;
    private static final Object n = new Object();
    private static boolean o = false;
    private static int[] p = new int[]{96000, 88200, 64000, TXRecordCommon.AUDIO_SAMPLERATE_48000, TXRecordCommon.AUDIO_SAMPLERATE_44100, TXRecordCommon.AUDIO_SAMPLERATE_32000, 24000, 22050, TXRecordCommon.AUDIO_SAMPLERATE_16000, 12000, 11025, TXRecordCommon.AUDIO_SAMPLERATE_8000, 7350};

    private static long h() {
        try {
            int myPid = Process.myPid();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("/proc/");
            stringBuilder.append(myPid);
            stringBuilder.append("/stat");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(stringBuilder.toString())), 1000);
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            String[] split = readLine.split(" ");
            if (split == null || TextUtils.isEmpty(split[13])) {
                return 0;
            }
            return ((Long.parseLong(split[13]) + Long.parseLong(split[14])) + Long.parseLong(split[15])) + Long.parseLong(split[16]);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x008a  */
    private static void i() {
        /*
        r0 = android.os.Build.VERSION.SDK_INT;
        r1 = 0;
        r3 = 26;
        if (r0 >= r3) goto L_0x0085;
    L_0x0008:
        r0 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x007f }
        r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x007f }
        r4 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x007f }
        r5 = "/proc/stat";
        r4.<init>(r5);	 Catch:{ Exception -> 0x007f }
        r3.<init>(r4);	 Catch:{ Exception -> 0x007f }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0.<init>(r3, r4);	 Catch:{ Exception -> 0x007f }
        r3 = r0.readLine();	 Catch:{ Exception -> 0x007f }
        r0.close();	 Catch:{ Exception -> 0x007f }
        r0 = " ";
        r0 = r3.split(r0);	 Catch:{ Exception -> 0x007f }
        if (r0 == 0) goto L_0x0085;
    L_0x002a:
        r3 = r0.length;	 Catch:{ Exception -> 0x007f }
        r4 = 9;
        if (r3 < r4) goto L_0x0085;
    L_0x002f:
        r3 = 2;
        r3 = r0[r3];	 Catch:{ Exception -> 0x007f }
        r3 = java.lang.Long.parseLong(r3);	 Catch:{ Exception -> 0x007f }
        r5 = 3;
        r5 = r0[r5];	 Catch:{ Exception -> 0x007f }
        r5 = java.lang.Long.parseLong(r5);	 Catch:{ Exception -> 0x007f }
        r7 = 0;
        r3 = r3 + r5;
        r5 = 4;
        r5 = r0[r5];	 Catch:{ Exception -> 0x007f }
        r5 = java.lang.Long.parseLong(r5);	 Catch:{ Exception -> 0x007f }
        r7 = 0;
        r3 = r3 + r5;
        r5 = 6;
        r6 = r0[r5];	 Catch:{ Exception -> 0x007f }
        r6 = java.lang.Long.parseLong(r6);	 Catch:{ Exception -> 0x007f }
        r8 = 0;
        r3 = r3 + r6;
        r6 = 5;
        r7 = r0[r6];	 Catch:{ Exception -> 0x007f }
        r7 = java.lang.Long.parseLong(r7);	 Catch:{ Exception -> 0x007f }
        r9 = 0;
        r3 = r3 + r7;
        r7 = 7;
        r7 = r0[r7];	 Catch:{ Exception -> 0x007f }
        r7 = java.lang.Long.parseLong(r7);	 Catch:{ Exception -> 0x007f }
        r9 = 0;
        r3 = r3 + r7;
        r7 = 8;
        r7 = r0[r7];	 Catch:{ Exception -> 0x007f }
        r7 = java.lang.Long.parseLong(r7);	 Catch:{ Exception -> 0x007f }
        r9 = 0;
        r3 = r3 + r7;
        r6 = r0[r6];	 Catch:{ Exception -> 0x007d }
        r6 = java.lang.Long.parseLong(r6);	 Catch:{ Exception -> 0x007d }
        r0 = r0[r5];	 Catch:{ Exception -> 0x007d }
        r8 = java.lang.Long.parseLong(r0);	 Catch:{ Exception -> 0x007d }
        r0 = 0;
        r6 = r6 + r8;
        r1 = r6;
        goto L_0x0086;
    L_0x007d:
        r0 = move-exception;
        goto L_0x0081;
    L_0x007f:
        r0 = move-exception;
        r3 = r1;
    L_0x0081:
        r0.printStackTrace();
        goto L_0x0086;
    L_0x0085:
        r3 = r1;
    L_0x0086:
        r0 = h;
        if (r0 == 0) goto L_0x0091;
    L_0x008a:
        r0 = (float) r3;
        b = r0;
        r0 = (float) r1;
        f = r0;
        goto L_0x0097;
    L_0x0091:
        r0 = (float) r3;
        c = r0;
        r0 = (float) r1;
        g = r0;
    L_0x0097:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.util.b.i():void");
    }

    public static int[] a() {
        if (j != 0 && TXCTimeUtil.getTimeTick() - j < 2000) {
            return i;
        }
        int[] iArr = new int[2];
        if (h) {
            d = (float) h();
            i();
            h = false;
            iArr[0] = 0;
            iArr[1] = 0;
            return iArr;
        }
        float f;
        e = (float) h();
        i();
        int i = (c > b ? 1 : (c == b ? 0 : -1));
        float f2 = CropImageView.DEFAULT_ASPECT_RATIO;
        if (i != 0) {
            f = ((e - d) * 100.0f) / (c - b);
            f2 = (((c - b) - (g - f)) * 100.0f) / (c - b);
        } else {
            f = CropImageView.DEFAULT_ASPECT_RATIO;
        }
        b = c;
        d = e;
        f = g;
        iArr[0] = (int) (f * 10.0f);
        iArr[1] = (int) (f2 * 10.0f);
        i[0] = iArr[0];
        i[1] = iArr[1];
        j = TXCTimeUtil.getTimeTick();
        return iArr;
    }

    public static int b() {
        try {
            if (m != 0 && TXCTimeUtil.getTimeTick() - m < 10000) {
                return l;
            }
            MemoryInfo memoryInfo = new MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            int totalPss = memoryInfo.getTotalPss();
            m = TXCTimeUtil.getTimeTick();
            l = totalPss / Filter.K;
            return l;
        } catch (Exception unused) {
        }
    }

    public static String a(Context context) {
        return TXCDRApi.getSimulateIDFA(context);
    }

    public static String b(Context context) {
        String str = "";
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static int c(Context context) {
        if (context == null) {
            return 255;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return 255;
        }
        if (activeNetworkInfo.getType() == 9) {
            return 5;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 1;
        }
        if (activeNetworkInfo.getType() != 0) {
            return 255;
        }
        switch (telephonyManager.getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return 4;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return 3;
            case 13:
                return 2;
            default:
                return 2;
        }
    }

    public static String c() {
        return Build.MODEL;
    }

    public static String d() {
        return UUID.randomUUID().toString();
    }

    public static String d(Context context) {
        return TXCDRApi.getDevUUID(context, TXCDRApi.getSimulateIDFA(context));
    }

    public static void a(WeakReference<a> weakReference, long j, int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("EVT_USERID", j);
        bundle.putInt("EVT_ID", i);
        bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
        if (str != null) {
            bundle.putCharSequence(TXLiveConstants.EVT_DESCRIPTION, str);
        }
        a((WeakReference) weakReference, i, bundle);
    }

    public static void a(WeakReference<a> weakReference, int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putInt("EVT_ID", i);
        bundle.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
        if (str != null) {
            bundle.putCharSequence(TXLiveConstants.EVT_DESCRIPTION, str);
        }
        a((WeakReference) weakReference, i, bundle);
    }

    public static void a(WeakReference<a> weakReference, int i, Bundle bundle) {
        if (weakReference != null) {
            a aVar = (a) weakReference.get();
            if (aVar != null) {
                aVar.onNotifyEvent(i, bundle);
            }
        }
    }

    public static void a(WeakReference<a> weakReference, long j, int i, Bundle bundle) {
        if (weakReference != null) {
            a aVar = (a) weakReference.get();
            if (aVar != null) {
                bundle.putLong("EVT_USERID", j);
                aVar.onNotifyEvent(i, bundle);
            }
        }
    }

    public static com.tencent.liteav.basic.e.a a(int i, int i2, int i3, int i4) {
        int i5 = i * i4;
        int i6 = i2 * i3;
        if (i5 >= i6) {
            i3 = i6 / i4;
            i4 = i2;
        } else {
            i4 = i5 / i3;
            i3 = i;
        }
        i5 = 0;
        i = i > i3 ? (i - i3) >> 1 : 0;
        if (i2 > i4) {
            i5 = (i2 - i4) >> 1;
        }
        return new com.tencent.liteav.basic.e.a(i, i5, i3, i4);
    }

    public static void e() {
        synchronized (n) {
            if (!o) {
                a("stlport_shared");
                a("saturn");
                a("txffmpeg");
                a("liteavsdk");
                o = true;
            }
        }
    }

    public static void a(String str) {
        StringBuilder stringBuilder;
        try {
            System.loadLibrary(str);
        } catch (Error e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("load library : ");
            stringBuilder.append(e.toString());
            Log.d("NativeLoad", stringBuilder.toString());
            b(k, str);
        } catch (Exception e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("load library : ");
            stringBuilder.append(e2.toString());
            Log.d("NativeLoad", stringBuilder.toString());
            b(k, str);
        }
    }

    private static void b(String str, String str2) {
        StringBuilder stringBuilder;
        try {
            if (!TextUtils.isEmpty(str)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("/lib");
                stringBuilder.append(str2);
                stringBuilder.append(".so");
                System.load(stringBuilder.toString());
            }
        } catch (Error e) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("load library : ");
            stringBuilder.append(e.toString());
            Log.d("NativeLoad", stringBuilder.toString());
        } catch (Exception e2) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("load library : ");
            stringBuilder.append(e2.toString());
            Log.d("NativeLoad", stringBuilder.toString());
        }
    }

    public static void b(String str) {
        k = str;
    }

    public static String f() {
        return k;
    }

    public static int a(int i) {
        int i2 = 0;
        while (i2 < p.length && p[i2] != i) {
            i2++;
        }
        return i2 >= p.length ? -1 : i2;
    }

    @TargetApi(16)
    public static MediaFormat a(int i, int i2, int i3) {
        int a = a(i);
        ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.put(0, (byte) ((i3 << 3) | (a >> 1)));
        allocate.put(1, (byte) (((a & 1) << 7) | (i2 << 3)));
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", i, i2);
        createAudioFormat.setInteger("channel-count", i2);
        createAudioFormat.setInteger("sample-rate", i);
        createAudioFormat.setByteBuffer("csd-0", allocate);
        return createAudioFormat;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x005c A:{SYNTHETIC, Splitter:B:34:0x005c} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005c A:{SYNTHETIC, Splitter:B:34:0x005c} */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x006d A:{SYNTHETIC, Splitter:B:43:0x006d} */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x006d A:{SYNTHETIC, Splitter:B:43:0x006d} */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0077  */
    public static boolean a(java.lang.String r4, java.lang.String r5) {
        /*
        r0 = 0;
        if (r4 == 0) goto L_0x007b;
    L_0x0003:
        if (r5 != 0) goto L_0x0007;
    L_0x0005:
        goto L_0x007b;
    L_0x0007:
        r1 = 0;
        r2 = new java.io.File;	 Catch:{ Exception -> 0x0055, all -> 0x0052 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x0055, all -> 0x0052 }
        r2 = r2.exists();	 Catch:{ Exception -> 0x0055, all -> 0x0052 }
        if (r2 != 0) goto L_0x0014;
    L_0x0013:
        return r0;
    L_0x0014:
        r2 = new android.media.MediaMetadataRetriever;	 Catch:{ Exception -> 0x0055, all -> 0x0052 }
        r2.<init>();	 Catch:{ Exception -> 0x0055, all -> 0x0052 }
        r2.setDataSource(r4);	 Catch:{ Exception -> 0x0050 }
        r4 = r2.getFrameAtTime();	 Catch:{ Exception -> 0x0050 }
        r3 = new java.io.File;	 Catch:{ Exception -> 0x0050 }
        r3.<init>(r5);	 Catch:{ Exception -> 0x0050 }
        r5 = r3.exists();	 Catch:{ Exception -> 0x0050 }
        if (r5 == 0) goto L_0x002e;
    L_0x002b:
        r3.delete();	 Catch:{ Exception -> 0x0050 }
    L_0x002e:
        r5 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0050 }
        r5.<init>(r3);	 Catch:{ Exception -> 0x0050 }
        r1 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ Exception -> 0x004d, all -> 0x004a }
        r3 = 100;
        r4.compress(r1, r3, r5);	 Catch:{ Exception -> 0x004d, all -> 0x004a }
        r5.flush();	 Catch:{ Exception -> 0x004d, all -> 0x004a }
        r5.close();	 Catch:{ IOException -> 0x0041 }
        goto L_0x0045;
    L_0x0041:
        r4 = move-exception;
        r4.printStackTrace();
    L_0x0045:
        r2.release();
        r4 = 1;
        return r4;
    L_0x004a:
        r4 = move-exception;
        r1 = r5;
        goto L_0x006b;
    L_0x004d:
        r4 = move-exception;
        r1 = r5;
        goto L_0x0057;
    L_0x0050:
        r4 = move-exception;
        goto L_0x0057;
    L_0x0052:
        r4 = move-exception;
        r2 = r1;
        goto L_0x006b;
    L_0x0055:
        r4 = move-exception;
        r2 = r1;
    L_0x0057:
        r4.printStackTrace();	 Catch:{ all -> 0x006a }
        if (r1 == 0) goto L_0x0064;
    L_0x005c:
        r1.close();	 Catch:{ IOException -> 0x0060 }
        goto L_0x0064;
    L_0x0060:
        r4 = move-exception;
        r4.printStackTrace();
    L_0x0064:
        if (r2 == 0) goto L_0x0069;
    L_0x0066:
        r2.release();
    L_0x0069:
        return r0;
    L_0x006a:
        r4 = move-exception;
    L_0x006b:
        if (r1 == 0) goto L_0x0075;
    L_0x006d:
        r1.close();	 Catch:{ IOException -> 0x0071 }
        goto L_0x0075;
    L_0x0071:
        r5 = move-exception;
        r5.printStackTrace();
    L_0x0075:
        if (r2 == 0) goto L_0x007a;
    L_0x0077:
        r2.release();
    L_0x007a:
        throw r4;
    L_0x007b:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.liteav.basic.util.b.a(java.lang.String, java.lang.String):boolean");
    }

    public static boolean g() {
        return Build.MANUFACTURER.equalsIgnoreCase("HUAWEI") && Build.MODEL.equalsIgnoreCase("NEM-L22");
    }

    private static void a(String str, MediaFormat mediaFormat, byte[] bArr, int i, int i2) {
        i2 -= i;
        ByteBuffer allocate = ByteBuffer.allocate(i2);
        allocate.put(bArr, i, i2);
        allocate.position(0);
        mediaFormat.setByteBuffer(str, allocate);
    }

    @TargetApi(16)
    public static MediaFormat a(byte[] bArr, int i, int i2) {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", i, i2);
        int i3 = 0;
        int i4 = 0;
        Object obj = null;
        Object obj2 = null;
        int i5 = 0;
        while (true) {
            int i6 = i3 + 3;
            if (i6 < bArr.length) {
                int i7 = (bArr[i3] == (byte) 0 && bArr[i3 + 1] == (byte) 0 && bArr[i3 + 2] == (byte) 1) ? 3 : 0;
                if (bArr[i3] == (byte) 0 && bArr[i3 + 1] == (byte) 0 && bArr[i3 + 2] == (byte) 0 && bArr[i6] == (byte) 1) {
                    i7 = 4;
                }
                if (i7 > 0) {
                    if (i4 == 0) {
                        i3 += i7;
                        i4 = i7;
                    } else {
                        i5 = bArr[i4] & 31;
                        if (i5 == 7) {
                            a("csd-0", createVideoFormat, bArr, i4, i3);
                            obj = 1;
                        } else if (i5 == 8) {
                            a("csd-1", createVideoFormat, bArr, i4, i3);
                            obj2 = 1;
                        }
                        i4 = i3 + i7;
                        if (obj != null && obj2 != null) {
                            return createVideoFormat;
                        }
                        i5 = i3;
                        i3 = i4;
                    }
                }
                i3++;
            } else {
                i2 = bArr[i4] & 31;
                if (obj != null && i2 == 8) {
                    a("csd-1", createVideoFormat, bArr, i4, i5);
                    return createVideoFormat;
                } else if (obj2 == null || i2 != 7) {
                    return null;
                } else {
                    a("csd-0", createVideoFormat, bArr, i4, i5);
                    return createVideoFormat;
                }
            }
        }
    }
}
