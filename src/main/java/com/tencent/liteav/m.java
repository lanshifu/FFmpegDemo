package com.tencent.liteav;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: TXCTimeShiftUtil */
public class m {
    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private long e = 0;
    private long f = 0;
    private long g = 0;

    /* compiled from: TXCTimeShiftUtil */
    public interface a {
        void onLiveTime(long j);
    }

    public m(Context context) {
    }

    public long a() {
        this.g = System.currentTimeMillis() - this.e;
        return this.g;
    }

    public String a(long j) {
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(this.e + (j * 1000)));
        return String.format("http://%s/%s/%s/timeshift.m3u8?starttime=%s&appid=%s&txKbps=0", new Object[]{this.a, this.c, this.b, format, this.d});
    }

    public int a(String str, String str2, int i, a aVar) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        this.d = TXCCommonUtil.getAppID();
        if (TextUtils.isEmpty(this.d)) {
            return -2;
        }
        final int i2 = i;
        final String str3 = str2;
        final String str4 = str;
        final a aVar2 = aVar;
        AsyncTask.execute(new Runnable() {
            public void run() {
                m.this.f = 0;
                m.this.e = 0;
                m.this.g = 0;
                m.this.b = "";
                m.this.c = "";
                if (i2 > 0) {
                    m.this.c = String.valueOf(i2);
                }
                m.this.a = str3;
                m.this.b = TXCCommonUtil.getStreamIDByStreamUrl(str4);
                try {
                    String readLine;
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(String.format("http://%s/%s/%s/timeshift.m3u8?delay=0&appid=%s&txKbps=0", new Object[]{m.this.a, m.this.c, m.this.b, m.this.d})).openConnection();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Charsert", "UTF-8");
                    httpURLConnection.setRequestProperty("Content-Type", "text/plain;");
                    String str = "";
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    while (true) {
                        readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(str);
                        stringBuilder.append(readLine);
                        str = stringBuilder.toString();
                    }
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("prepareSeekTime: receive response, strResponse = ");
                    stringBuilder2.append(str);
                    TXCLog.e("TXCTimeShiftUtil", stringBuilder2.toString());
                    readLine = m.this.a(str);
                    long parseLong = Long.parseLong(readLine);
                    long currentTimeMillis = System.currentTimeMillis();
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("time:");
                    stringBuilder3.append(readLine);
                    stringBuilder3.append(",currentTime:");
                    stringBuilder3.append(currentTimeMillis);
                    stringBuilder3.append(",diff:");
                    stringBuilder3.append(currentTimeMillis - parseLong);
                    TXCLog.i("TXCTimeShiftUtil", stringBuilder3.toString());
                    m.this.e = parseLong * 1000;
                    m.this.g = currentTimeMillis - m.this.e;
                    if (aVar2 != null) {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                aVar2.onLiveTime(m.this.g);
                            }
                        });
                    }
                } catch (Exception e) {
                    m.this.e = 0;
                    e.printStackTrace();
                }
            }
        });
        return 0;
    }

    private String a(String str) {
        if (str.contains("#EXT-TX-TS-START-TIME")) {
            int indexOf = str.indexOf("#EXT-TX-TS-START-TIME:") + 22;
            if (indexOf > 0) {
                str = str.substring(indexOf);
                indexOf = str.indexOf("#");
                if (indexOf > 0) {
                    return str.substring(0, indexOf).replaceAll("\r\n", "");
                }
            }
        }
        return null;
    }
}
