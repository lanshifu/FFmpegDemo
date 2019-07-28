package com.tomatolive.library.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.widget.TextView;
import com.blankj.utilcode.util.e;
import com.blankj.utilcode.util.i;
import com.blankj.utilcode.util.o;
import com.tomatolive.library.R;
import com.tomatolive.library.a;
import com.tomatolive.library.download.CarDownLoadManager;
import com.tomatolive.library.download.DownLoadRetrofit;
import com.tomatolive.library.download.GiftDownLoadManager;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.utils.EncryptUtil;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.CarDownloadEntity;
import com.tomatolive.library.model.ChatEntity;
import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.SocketMessageEvent.ResultData;
import com.tomatolive.library.ui.activity.live.TomatoLiveActivity;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Builder;
import okhttp3.RequestBody;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

/* compiled from: AppUtils */
public class b {
    public static final int a = i();
    private static final SimpleArrayMap<String, Typeface> b = new SimpleArrayMap();

    public static int b(int i) {
        return (i < 1 || i > 5) ? (i < 6 || i > 10) ? (i < 11 || i > 15) ? (i < 16 || i > 20) ? (i < 21 || i > 25) ? (i < 26 || i > 30) ? 1 : 6 : 5 : 4 : 3 : 2 : 1;
    }

    public static boolean d(int i) {
        return i == 3;
    }

    public static String a() {
        String str = a.a().b;
        return TextUtils.isEmpty(str) ? "https://api.test.kltbj.com/" : str;
    }

    public static String b() {
        String str = a.a().d;
        return TextUtils.isEmpty(str) ? "https://download.kltbj.com/" : str;
    }

    public static String c() {
        String str = "v2/stream_upload_chunk";
        String str2 = a.a().c;
        StringBuilder stringBuilder = new StringBuilder();
        if (TextUtils.isEmpty(str2)) {
            stringBuilder.append("https://upload.kltbj.com/");
            stringBuilder.append(str);
            return stringBuilder.toString();
        }
        stringBuilder.append(str2);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }

    public static boolean d() {
        return ApiRetrofit.getInstance().isApiService();
    }

    public static boolean e() {
        return DownLoadRetrofit.getInstance().isApiService();
    }

    public static boolean a(String str) {
        return TextUtils.equals("0", str);
    }

    public static boolean b(String str) {
        return TextUtils.equals("1", str);
    }

    public static boolean a(LiveEntity liveEntity) {
        return TextUtils.equals("1", liveEntity.liveStatus) || TextUtils.equals("1", liveEntity.isLiving);
    }

    public static boolean f() {
        return z.a().i();
    }

    public static boolean a(Context context) {
        if (f()) {
            return true;
        }
        d(context);
        return false;
    }

    private static void d(Context context) {
        if (a.a().a != null) {
            a.a().a.b(context);
        }
    }

    public static void a(Context context, int i, LiveEntity liveEntity) {
        if (a(context)) {
            Intent intent = new Intent(context, TomatoLiveActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("resultItem", liveEntity);
            intent.putExtra("liveTypeStr", i);
            context.startActivity(intent);
        }
    }

    public static LiveEntity a(AnchorEntity anchorEntity) {
        LiveEntity liveEntity = new LiveEntity();
        liveEntity.anchorId = anchorEntity.userId;
        liveEntity.liveId = anchorEntity.liveId;
        liveEntity.isLiving = anchorEntity.isLiving;
        liveEntity.liveStatus = anchorEntity.liveStatus;
        liveEntity.streamName = anchorEntity.streamName;
        return liveEntity;
    }

    public static boolean a(Context context, String str) {
        if (!a(context)) {
            return false;
        }
        if (!TextUtils.equals(z.a().c(), str)) {
            return true;
        }
        o.a(R.string.fq_text_no_attention_oneself);
        return false;
    }

    public static String g() {
        Application c = a.a().c();
        return c != null ? c.getResources().getString(R.string.fq_system_msg) : "";
    }

    public static int h() {
        return g().length();
    }

    public static RequestBody a(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append("");
        stringBuilder = new StringBuilder();
        stringBuilder.append(file.length());
        stringBuilder.append("");
        stringBuilder = new StringBuilder();
        stringBuilder.append(file.length());
        stringBuilder.append("");
        return new Builder().setType(MultipartBody.FORM).addFormDataPart(OnNativeInvokeListener.ARG_OFFSET, "0").addFormDataPart("clienttime", stringBuilder.toString()).addFormDataPart("clientver", "1001").addFormDataPart("md5", u.a(file)).addFormDataPart("mid", "1f76c6e987e4bc406ac6092fb127e2ae370dd078").addFormDataPart("size", stringBuilder.toString()).addFormDataPart("key", n.a(file.getName())).addFormDataPart("type", "cover").addFormDataPart("extend_name", s(file.getName())).addFormDataPart("appid", "1000").addFormDataPart("chunk_size", stringBuilder.toString()).addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file)).build();
    }

    private static String s(String str) {
        try {
            return str.split("\\.")[1];
        } catch (Exception unused) {
            return "jpg";
        }
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        long b = p.b(str);
        if (b < 10000) {
            return String.valueOf(b);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new DecimalFormat("#.#").format(b / 10000));
        stringBuilder.append(w.a(R.string.fq_ten_thousand));
        return stringBuilder.toString();
    }

    public static String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0";
        }
        long b = p.b(str);
        if (b < 10000) {
            return String.valueOf(b);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new DecimalFormat("#.#").format(b / 10000));
        stringBuilder.append(w.a(R.string.fq_ten_thousand));
        return stringBuilder.toString();
    }

    private static Typeface c(Context context, String str) {
        Typeface typeface;
        synchronized (b) {
            if (!b.containsKey(str)) {
                try {
                    b.put(str, Typeface.createFromAsset(context.getAssets(), str));
                } catch (Exception unused) {
                    return null;
                }
            }
            typeface = (Typeface) b.get(str);
        }
        return typeface;
    }

    public static void a(Context context, TextView textView, String str) {
        try {
            Typeface c = c(context, "fonts/gradeFont.ttf");
            if (c != null) {
                textView.setTypeface(c);
            }
            textView.setText(str);
        } catch (Exception unused) {
            textView.setText(str);
        }
    }

    @DrawableRes
    public static int e(String str) {
        if (TextUtils.equals("1", str)) {
            return R.drawable.fq_ic_gender_male;
        }
        if (TextUtils.equals(ConnectSocketParams.EFFECT_TYPE_BIG, str)) {
            return R.drawable.fq_ic_gender_female;
        }
        return R.drawable.fq_ic_gender_female;
    }

    public static int i() {
        return z.a().j();
    }

    public static int a(int i) {
        int i2 = i;
        if (j()) {
            if (i2 >= 1 && i2 <= 9) {
                return 1;
            }
            if (i2 >= 10 && i2 <= 29) {
                return 2;
            }
            if (i2 >= 30 && i2 <= 39) {
                return 3;
            }
            if (i2 >= 40 && i2 <= 49) {
                return 4;
            }
            if (i2 >= 50 && i2 <= 59) {
                return 5;
            }
            if (i2 >= a) {
                return 6;
            }
        } else if (i2 >= 1 && i2 <= 14) {
            return 1;
        } else {
            if (i2 >= 15 && i2 <= 29) {
                return 2;
            }
            if (i2 >= 30 && i2 <= 39) {
                return 3;
            }
            if (i2 >= 40 && i2 <= 49) {
                return 4;
            }
            if (i2 >= 50 && i2 <= 59) {
                return 5;
            }
            if (i2 >= 60 && i2 <= 69) {
                return 6;
            }
            if (i2 >= 70 && i2 <= 79) {
                return 7;
            }
            if (i2 >= 80 && i2 <= 89) {
                return 8;
            }
            if (i2 >= 90 && i2 <= 99) {
                return 9;
            }
            if (i2 >= 100 && i2 <= 109) {
                return 10;
            }
            if (i2 >= 110 && i2 <= 119) {
                return 11;
            }
            if (i2 >= a) {
                return 12;
            }
        }
        return 1;
    }

    @DrawableRes
    public static int a(boolean z, int i) {
        i = a(i);
        int i2;
        if (j()) {
            switch (i) {
                case 1:
                    if (z) {
                        i2 = R.drawable.fq_shape_user_grade_bg_white_1;
                    } else {
                        i2 = R.drawable.fq_shape_user_grade_bg_1;
                    }
                    return i2;
                case 2:
                    return z ? R.drawable.fq_shape_user_grade_bg_white_2 : R.drawable.fq_shape_user_grade_bg_2;
                case 3:
                    return z ? R.drawable.fq_shape_user_grade_bg_white_3 : R.drawable.fq_shape_user_grade_bg_3;
                case 4:
                    return z ? R.drawable.fq_shape_user_grade_bg_white_4 : R.drawable.fq_shape_user_grade_bg_4;
                case 5:
                    return z ? R.drawable.fq_shape_user_grade_bg_white_5 : R.drawable.fq_shape_user_grade_bg_5;
                case 6:
                    return z ? R.drawable.fq_shape_user_grade_bg_white_6 : R.drawable.fq_shape_user_grade_bg_6;
                default:
                    return z ? R.drawable.fq_shape_user_grade_bg_white_1 : R.drawable.fq_shape_user_grade_bg_1;
            }
        }
        switch (i) {
            case 1:
                if (z) {
                    i2 = R.drawable.fq_shape_user_grade_second_bg_white_1;
                } else {
                    i2 = R.drawable.fq_shape_user_grade_second_bg_1;
                }
                return i2;
            case 2:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_2 : R.drawable.fq_shape_user_grade_second_bg_2;
            case 3:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_3 : R.drawable.fq_shape_user_grade_second_bg_3;
            case 4:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_4 : R.drawable.fq_shape_user_grade_second_bg_4;
            case 5:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_5 : R.drawable.fq_shape_user_grade_second_bg_5;
            case 6:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_6 : R.drawable.fq_shape_user_grade_second_bg_6;
            case 7:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_7 : R.drawable.fq_shape_user_grade_second_bg_7;
            case 8:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_8 : R.drawable.fq_shape_user_grade_second_bg_8;
            case 9:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_9 : R.drawable.fq_shape_user_grade_second_bg_9;
            case 10:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_10 : R.drawable.fq_shape_user_grade_second_bg_10;
            case 11:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_11 : R.drawable.fq_shape_user_grade_second_bg_11;
            case 12:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_12 : R.drawable.fq_shape_user_grade_second_bg_12;
            default:
                return z ? R.drawable.fq_shape_user_grade_second_bg_white_1 : R.drawable.fq_shape_user_grade_second_bg_1;
        }
    }

    @DrawableRes
    public static int b(boolean z, int i) {
        i = a(i);
        int i2;
        if (j()) {
            switch (i) {
                case 1:
                    if (z) {
                        i2 = R.drawable.fq_ic_grade_user_1_small;
                    } else {
                        i2 = R.drawable.fq_ic_grade_user_1;
                    }
                    return i2;
                case 2:
                    return z ? R.drawable.fq_ic_grade_user_2_small : R.drawable.fq_ic_grade_user_2;
                case 3:
                    return z ? R.drawable.fq_ic_grade_user_3_small : R.drawable.fq_ic_grade_user_3;
                case 4:
                    return z ? R.drawable.fq_ic_grade_user_4_small : R.drawable.fq_ic_grade_user_4;
                case 5:
                    return z ? R.drawable.fq_ic_grade_user_5_small : R.drawable.fq_ic_grade_user_5;
                case 6:
                    return z ? R.drawable.fq_ic_grade_user_6_small : R.drawable.fq_ic_grade_user_6;
                default:
                    return z ? R.drawable.fq_ic_grade_user_1_small : R.drawable.fq_ic_grade_user_1;
            }
        }
        switch (i) {
            case 1:
                if (z) {
                    i2 = R.drawable.fq_ic_grade_user_second_1_small;
                } else {
                    i2 = R.drawable.fq_ic_grade_user_second_1;
                }
                return i2;
            case 2:
                return z ? R.drawable.fq_ic_grade_user_second_2_small : R.drawable.fq_ic_grade_user_second_2;
            case 3:
                return z ? R.drawable.fq_ic_grade_user_second_3_small : R.drawable.fq_ic_grade_user_second_3;
            case 4:
                return z ? R.drawable.fq_ic_grade_user_second_4_small : R.drawable.fq_ic_grade_user_second_4;
            case 5:
                return z ? R.drawable.fq_ic_grade_user_second_5_small : R.drawable.fq_ic_grade_user_second_5;
            case 6:
                return z ? R.drawable.fq_ic_grade_user_second_6_small : R.drawable.fq_ic_grade_user_second_6;
            case 7:
                return z ? R.drawable.fq_ic_grade_user_second_7_small : R.drawable.fq_ic_grade_user_second_7;
            case 8:
                return z ? R.drawable.fq_ic_grade_user_second_8_small : R.drawable.fq_ic_grade_user_second_8;
            case 9:
                return z ? R.drawable.fq_ic_grade_user_second_9_small : R.drawable.fq_ic_grade_user_second_9;
            case 10:
                return z ? R.drawable.fq_ic_grade_user_second_10_small : R.drawable.fq_ic_grade_user_second_10;
            case 11:
                return z ? R.drawable.fq_ic_grade_user_second_11_small : R.drawable.fq_ic_grade_user_second_11;
            case 12:
                return z ? R.drawable.fq_ic_grade_user_second_12_small : R.drawable.fq_ic_grade_user_second_12;
            default:
                return z ? R.drawable.fq_ic_grade_user_second_1_small : R.drawable.fq_ic_grade_user_second_1;
        }
    }

    @DrawableRes
    public static int f(String str) {
        int a = a(p.a(str));
        if (j()) {
            switch (a) {
                case 1:
                    return R.drawable.fq_ic_grade_user_bg_1;
                case 2:
                    return R.drawable.fq_ic_grade_user_bg_2;
                case 3:
                    return R.drawable.fq_ic_grade_user_bg_3;
                case 4:
                    return R.drawable.fq_ic_grade_user_bg_4;
                case 5:
                    return R.drawable.fq_ic_grade_user_bg_5;
                case 6:
                    return R.drawable.fq_ic_grade_user_bg_6;
                default:
                    return R.drawable.fq_ic_grade_user_bg_1;
            }
        }
        switch (a) {
            case 1:
                return R.drawable.fq_ic_grade_user_second_bg_1;
            case 2:
                return R.drawable.fq_ic_grade_user_second_bg_2;
            case 3:
                return R.drawable.fq_ic_grade_user_second_bg_3;
            case 4:
                return R.drawable.fq_ic_grade_user_second_bg_4;
            case 5:
                return R.drawable.fq_ic_grade_user_second_bg_5;
            case 6:
                return R.drawable.fq_ic_grade_user_second_bg_6;
            case 7:
                return R.drawable.fq_ic_grade_user_second_bg_7;
            case 8:
                return R.drawable.fq_ic_grade_user_second_bg_8;
            case 9:
                return R.drawable.fq_ic_grade_user_second_bg_9;
            case 10:
                return R.drawable.fq_ic_grade_user_second_bg_10;
            case 11:
                return R.drawable.fq_ic_grade_user_second_bg_11;
            case 12:
                return R.drawable.fq_ic_grade_user_second_bg_12;
            default:
                return R.drawable.fq_ic_grade_user_second_bg_1;
        }
    }

    public static boolean g(String str) {
        return p.a(h(str)) >= 30;
    }

    public static boolean j() {
        return i() == 60;
    }

    public static boolean k() {
        return i() == 120;
    }

    public static List<BannerEntity> a(List<BannerEntity> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || list.size() == 0) {
            return arrayList;
        }
        for (BannerEntity bannerEntity : list) {
            if (TextUtils.equals("all", bannerEntity.terminal) || TextUtils.equals("android", bannerEntity.terminal)) {
                arrayList.add(bannerEntity);
            }
        }
        return arrayList;
    }

    public static List<String> b(List<BannerEntity> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || list.size() == 0) {
            return arrayList;
        }
        for (BannerEntity bannerEntity : list) {
            if (TextUtils.equals("all", bannerEntity.terminal) || TextUtils.equals("android", bannerEntity.terminal)) {
                arrayList.add(bannerEntity.img);
            }
        }
        return arrayList;
    }

    public static String h(String str) {
        return TextUtils.isEmpty(str) ? "1" : str;
    }

    public static String i(String str) {
        return TextUtils.isEmpty(str) ? "0" : str;
    }

    public static long b(LiveEntity liveEntity) {
        long j = 10;
        if (liveEntity == null || liveEntity.sysParamInfo == null || TextUtils.isEmpty(liveEntity.sysParamInfo.onlineCountSynInterval)) {
            return 10;
        }
        long b = p.b(liveEntity.sysParamInfo.onlineCountSynInterval);
        if (b > 0) {
            j = b;
        }
        return j;
    }

    public static long c(LiveEntity liveEntity) {
        long j = 9000;
        if (liveEntity == null || liveEntity.sysParamInfo == null || TextUtils.isEmpty(liveEntity.sysParamInfo.giftTrumpetPlayPeriod)) {
            return 9000;
        }
        long b = p.b(liveEntity.sysParamInfo.giftTrumpetPlayPeriod);
        if (b > 0) {
            j = b;
        }
        return j;
    }

    public static String d(LiveEntity liveEntity) {
        return (liveEntity == null || liveEntity.sysParamInfo == null) ? "1" : liveEntity.sysParamInfo.enableTranslationLevel;
    }

    public static String e(LiveEntity liveEntity) {
        if (liveEntity == null || liveEntity.sysParamInfo == null) {
            return "10";
        }
        String str = liveEntity.sysParamInfo.entryNoticeLevelThreshold;
        if (TextUtils.isEmpty(str)) {
            str = "10";
        }
        return str;
    }

    public static String b(Context context) {
        return context.getString(R.string.fq_tomato_money_str);
    }

    public static LiveEntity a(BannerEntity bannerEntity) {
        LiveEntity liveEntity = new LiveEntity();
        if (bannerEntity == null) {
            return null;
        }
        liveEntity.liveId = bannerEntity.url;
        return liveEntity;
    }

    public static String a(String str, String str2, String str3, String str4, String str5) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str2);
        stringBuilder.append("&");
        stringBuilder.append(str3);
        stringBuilder.append("&");
        stringBuilder.append(str4);
        stringBuilder.append("&");
        stringBuilder.append(z.a().e());
        stringBuilder.append("/");
        stringBuilder.append(z.a().b());
        stringBuilder.append("/");
        stringBuilder.append(str5);
        try {
            str2 = EncryptUtil.DESEncrypt(a.a().j, stringBuilder.toString());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append("/");
            stringBuffer.append(str2);
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(str);
            stringBuffer2.append("/");
            stringBuffer2.append(stringBuilder.toString());
            return stringBuffer2.toString();
        }
    }

    public static java.lang.String b(android.content.Context r1, java.lang.String r2) {
        /*
        r0 = r2.hashCode();
        switch(r0) {
            case 49: goto L_0x001c;
            case 50: goto L_0x0012;
            case 51: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0026;
    L_0x0008:
        r0 = "3";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0026;
    L_0x0010:
        r2 = 2;
        goto L_0x0027;
    L_0x0012:
        r0 = "2";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0026;
    L_0x001a:
        r2 = 1;
        goto L_0x0027;
    L_0x001c:
        r0 = "1";
        r2 = r2.equals(r0);
        if (r2 == 0) goto L_0x0026;
    L_0x0024:
        r2 = 0;
        goto L_0x0027;
    L_0x0026:
        r2 = -1;
    L_0x0027:
        switch(r2) {
            case 0: goto L_0x003b;
            case 1: goto L_0x0034;
            case 2: goto L_0x002d;
            default: goto L_0x002a;
        };
    L_0x002a:
        r1 = "";
        return r1;
    L_0x002d:
        r2 = com.tomatolive.library.R.string.fq_guard_year;
        r1 = r1.getString(r2);
        return r1;
    L_0x0034:
        r2 = com.tomatolive.library.R.string.fq_guard_month;
        r1 = r1.getString(r2);
        return r1;
    L_0x003b:
        r2 = com.tomatolive.library.R.string.fq_guard_week;
        r1 = r1.getString(r2);
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.utils.b.b(android.content.Context, java.lang.String):java.lang.String");
    }

    public static boolean a(ChatEntity chatEntity) {
        boolean z = false;
        if (chatEntity == null) {
            return false;
        }
        if (chatEntity.getMsgType() == 3 || chatEntity.getMsgType() == 1 || chatEntity.getMsgType() == 0) {
            z = true;
        }
        return z;
    }

    public static void a(Context context, Class<? extends IntentService> cls) {
        if (!c(context)) {
            if (!(context instanceof Activity) || !((Activity) context).isFinishing()) {
                context.startService(new Intent(context, cls));
            }
        }
    }

    public static boolean c(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<RunningAppProcessInfo> runningAppProcesses = activityManager != null ? activityManager.getRunningAppProcesses() : null;
        boolean z = false;
        if (runningAppProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                if (runningAppProcessInfo.importance == 400) {
                    z = true;
                }
                return z;
            }
        }
        return false;
    }

    public static boolean j(String str) {
        return TextUtils.equals(str, "3");
    }

    public static boolean k(String str) {
        return TextUtils.equals(str, "5");
    }

    public static boolean l(String str) {
        return TextUtils.equals(str, ConnectSocketParams.EFFECT_TYPE_BIG);
    }

    public static boolean m(String str) {
        return TextUtils.equals(str, "1");
    }

    public static boolean c(int i) {
        return i > p.a("0");
    }

    public static boolean n(String str) {
        return TextUtils.equals(str, "1");
    }

    public static boolean o(String str) {
        return TextUtils.equals(str, "200500");
    }

    public static boolean p(String str) {
        return (TextUtils.equals(str, "-1") || TextUtils.isEmpty(str)) ? false : true;
    }

    public static File q(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        List a = e.a(str, new f("svga"));
        if (a == null || a.isEmpty()) {
            return null;
        }
        return (File) a.get(0);
    }

    public static InputStream r(String str) throws Exception {
        CarDownloadEntity carItemEntity = CarDownLoadManager.getInstance().getCarItemEntity(str);
        if (carItemEntity == null || TextUtils.isEmpty(carItemEntity.animLocalPath)) {
            return null;
        }
        File q = q(carItemEntity.animLocalPath);
        if (q != null && q.exists()) {
            return new FileInputStream(q);
        }
        return null;
    }

    public static String l() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i.b());
        stringBuilder.append(File.separator);
        stringBuilder.append("waterLogo");
        File file = new File(stringBuilder.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        stringBuilder.append(File.separator);
        stringBuilder.append("waterLogo");
        stringBuilder.append(z.a().c());
        stringBuilder.append(".png");
        return stringBuilder.toString();
    }

    public static String a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(File.separator);
        stringBuilder.append(str2);
        stringBuilder.append(".svga");
        return stringBuilder.toString();
    }

    public static String a(ResultData resultData) {
        if (!TextUtils.isEmpty(resultData.tomatoPrice)) {
            return resultData.tomatoPrice;
        }
        String str;
        GiftItemEntity giftItemEntity = GiftDownLoadManager.getInstance().getGiftItemEntity(resultData.giftId);
        if (giftItemEntity == null) {
            str = "";
        } else {
            str = giftItemEntity.tomatoPrice;
        }
        return str;
    }
}
