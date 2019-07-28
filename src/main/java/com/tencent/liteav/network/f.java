package com.tencent.liteav.network;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.liteav.basic.log.TXCLog;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TXCVodPlayerNetApi */
public class f {
    protected g a;
    protected i b;
    private final String c = "http://playvideo.qcloud.com/getplayinfo/v2";
    private final String d = "https://playvideo.qcloud.com/getplayinfo/v2";
    private final int e = 0;
    private final int f = 1;
    private Thread g;
    private boolean h;
    private Handler i = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (f.this.a != null) {
                switch (message.what) {
                    case 0:
                        f.this.a.onNetSuccess(f.this);
                        break;
                    case 1:
                        f.this.a.onNetFailed(f.this, (String) message.obj, message.arg1);
                        break;
                }
            }
        }
    };
    private String j = "{\"code\":0,\"message\":\"\",\"playerInfo\":{\"playerId\":\"0\",\"name\":\"初始播放器\",\"defaultVideoClassification\":\"SD\",\"videoClassification\":[{\"id\":\"FLU\",\"name\":\"流畅\",\"definitionList\":[10,510,210,610,10046,710]},{\"id\":\"SD\",\"name\":\"标清\",\"definitionList\":[20,520,220,620,10047,720]},{\"id\":\"HD\",\"name\":\"高清\",\"definitionList\":[30,530,230,630,10048,730]},{\"id\":\"FHD\",\"name\":\"全高清\",\"definitionList\":[40,540,240,640,10049,740]},{\"id\":\"2K\",\"name\":\"2K\",\"definitionList\":[70,570,270,670,370,770]},{\"id\":\"4K\",\"name\":\"4K\",\"definitionList\":[80,580,280,680,380,780]}],\"logoLocation\":\"1\",\"logoPic\":\"\",\"logoUrl\":\"\"},\"coverInfo\":{\"coverUrl\":\"http://1255566655.vod2.myqcloud.com/7e9cee55vodtransgzp1255566655/8f5fbff14564972818519602447/coverBySnapshot/1513156403_1311093072.100_0.jpg?t=5c08d9fa&us=someus&sign=95f34beb353fe32cfe7f8b5e79cc28b1\"},\"imageSpriteInfo\":{\"imageSpriteList\":[{\"definition\":10,\"height\":80,\"width\":142,\"totalCount\":4,\"imageUrls\":[\"http://1255566655.vod2.myqcloud.com/ca754badvodgzp1255566655/8f5fbff14564972818519602447/imageSprite/1513156058_533711271_00001.jpg?t=5c08d9fa&us=someus&sign=79449db4e1fb05a3becfa096613659c3\"],\"webVttUrl\":\"http://1255566655.vod2.myqcloud.com/ca754badvodgzp1255566655/8f5fbff14564972818519602447/imageSprite/1513156058_533711271.vtt?t=5c08d9fa&us=someus&sign=79449db4e1fb05a3becfa096613659c3\"}]},\"videoInfo\":{\"sourceVideo\":{\"url\":\"http://1255566655.vod2.myqcloud.com/ca754badvodgzp1255566655/8f5fbff14564972818519602447/uAnXX0OMLSAA.wmv?t=5c08d9fa&us=someus&sign=659af5dd3f27eb92dc4ed74eb561daa4\",\"definition\":0,\"duration\":30,\"floatDuration\":30.093000411987305,\"size\":26246026,\"bitrate\":6134170,\"height\":720,\"width\":1280,\"container\":\"asf\",\"md5\":\"\",\"videoStreamList\":[{\"bitrate\":5942130,\"height\":720,\"width\":1280,\"codec\":\"vc1\",\"fps\":29}],\"audioStreamList\":[{\"samplingRate\":44100,\"bitrate\":192040,\"codec\":\"wmav2\"}]},\"mas©terPlayList1\":{\"idrAligned\":false,\"url\":\"http://1255566655.vod2.myqcloud.com/7e9cee55vodtransgzp1255566655/8f5fbff14564972818519602447/master_playlist.m3u8?t=5c08d9fa&us=someus&sign=66290475b7182c89193f03b8f74a979d\",\"definition\":10000,\"md5\":\"23ecc2cfe4cb7c8f87af41993ba8c09c\"},\"transcodeList\":[{\"url\":\"http://1255566655.vod2.myqcloud.com/7e9cee55vodtransgzp1255566655/8f5fbff14564972818519602447/v.f220.m3u8?t=5c08d9fa&us=someus&sign=66290475b7182c89193f03b8f74a979d\",\"definition\":220,\"duration\":30,\"floatDuration\":30.08329963684082,\"size\":796,\"bitrate\":646036,\"height\":360,\"width\":640,\"container\":\"hls,applehttp\",\"md5\":\"dce044407826b4d809c16b6d1a9e9f13\",\"videoStreamList\":[{\"bitrate\":607449,\"height\":360,\"width\":640,\"codec\":\"h264\",\"fps\":24}],\"audioStreamList\":[{\"samplingRate\":44100,\"bitrate\":38587,\"codec\":\"aac\"}]},{\"url\":\"http://1255566655.vod2.myqcloud.com/7e9cee55vodtransgzp1255566655/8f5fbff14564972818519602447/v.f230.m3u8?t=5c08d9fa&us=someus&sign=66290475b7182c89193f03b8f74a979d\",\"definition\":230,\"duration\":30,\"floatDuration\":30.04170036315918,\"size\":802,\"bitrate\":1224776,\"height\":720,\"width\":1280,\"container\":\"hls,applehttp\",\"md5\":\"f07bb0be9e2fee967d87b6c310d3c036\",\"videoStreamList\":[{\"bitrate\":1186189,\"height\":720,\"width\":1280,\"codec\":\"h264\",\"fps\":24}],\"audioStreamList\":[{\"samplingRate\":44100,\"bitrate\":38587,\"codec\":\"aac\"}]},{\"url\":\"http://1255566655.vod2.myqcloud.com/7e9cee55vodtransgzp1255566655/8f5fbff14564972818519602447/v.f240.m3u8?t=5c08d9fa&us=someus&sign=66290475b7182c89193f03b8f74a979d\",\"definition\":240,\"duration\":30,\"floatDuration\":0,\"size\":809,\"bitrate\":2866685,\"height\":1080,\"width\":1920,\"container\":\"hls,applehttp\",\"md5\":\"ff8190cf07afceb8ed053b198453e954\",\"videoStreamList\":[{\"bitrate\":2828098,\"height\":1080,\"width\":1920,\"codec\":\"h264\",\"fps\":24}],\"audioStreamList\":[{\"samplingRate\":44100,\"bitrate\":38587,\"codec\":\"aac\"}]},{\"url\":\"http://1255566655.vod2.myqcloud.com/7e9cee55vodtransgzp1255566655/8f5fbff14564972818519602447/v.f210.m3u8?t=5c08d9fa&us=someus&sign=66290475b7182c89193f03b8f74a979d\",\"definition\":210,\"duration\":30,\"floatDuration\":30.08329963684082,\"size\":788,\"bitrate\":358908,\"height\":180,\"width\":320,\"container\":\"hls,applehttp\",\"md5\":\"5fa784e0a588c51dc2d7428ad6787079\",\"videoStreamList\":[{\"bitrate\":320321,\"height\":180,\"width\":320,\"codec\":\"h264\",\"fps\":24}],\"audioStreamList\":[{\"samplingRate\":44100,\"bitrate\":38587,\"codec\":\"aac\"}]},{\"url\":\"http://1255566655.vod2.myqcloud.com/7e9cee55vodtransgzp1255566655/8f5fbff14564972818519602447/v.f10.mp4?t=5c08d9fa&us=someus&sign=66290475b7182c89193f03b8f74a979d\",\"definition\":10,\"duration\":30,\"floatDuration\":30.139400482177734,\"size\":1169168,\"bitrate\":303916,\"height\":180,\"width\":320,\"container\":\"mov,mp4,m4a,3gp,3g2,mj2\",\"md5\":\"85002ed20125acf150d014b192cf39a0\",\"videoStreamList\":[{\"bitrate\":255698,\"height\":180,\"width\":320,\"codec\":\"h264\",\"fps\":24}],\"audioStreamList\":[{\"samplingRate\":44100,\"bitrate\":48218,\"codec\":\"aac\"}]},{\"url\":\"http://1255566655.vod2.myqcloud.com/7e9cee55vodtransgzp1255566655/8f5fbff14564972818519602447/v.f20.mp4?t=5c08d9fa&us=someus&sign=66290475b7182c89193f03b8f74a979d\",\"definition\":20,\"duration\":30,\"floatDuration\":30.139400482177734,\"size\":2158411,\"bitrate\":566647,\"height\":360,\"width\":640,\"container\":\"mov,mp4,m4a,3gp,3g2,mj2\",\"md5\":\"cba3630e5f92325041da7fee336246b6\",\"videoStreamList\":[{\"bitrate\":518429,\"height\":360,\"width\":640,\"codec\":\"h264\",\"fps\":24}],\"audioStreamList\":[{\"samplingRate\":44100,\"bitrate\":48218,\"codec\":\"aac\"}]}]}}";

    public int a(int i, String str, String str2, String str3, int i2, String str4) {
        if (i == 0 || str == null) {
            return -1;
        }
        if ((str2 != null || i2 > 0) && str4 == null) {
            return -1;
        }
        final int i3 = i;
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final int i4 = i2;
        final String str8 = str4;
        this.g = new Thread("getPlayInfo") {
            public void run() {
                try {
                    String format;
                    Looper.prepare();
                    if (f.this.h) {
                        format = String.format("%s/%d/%s", new Object[]{"https://playvideo.qcloud.com/getplayinfo/v2", Integer.valueOf(i3), str5});
                    } else {
                        format = String.format("%s/%d/%s", new Object[]{"http://playvideo.qcloud.com/getplayinfo/v2", Integer.valueOf(i3), str5});
                    }
                    String a = f.this.a(str6, str7, i4, str8);
                    if (a != null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(format);
                        stringBuilder.append("?");
                        stringBuilder.append(a);
                        format = stringBuilder.toString();
                    }
                    URL url = new URL(format);
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("getplayinfo: ");
                    stringBuilder2.append(format);
                    TXCLog.d("TXCVodPlayerNetApi", stringBuilder2.toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    if (httpURLConnection.getResponseCode() == 200) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuilder stringBuilder3 = new StringBuilder();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                stringBuilder3.append(readLine);
                            } else {
                                f.this.a(stringBuilder3.toString());
                                return;
                            }
                        }
                    }
                    f.this.a("请求失败", -1);
                } catch (JSONException unused) {
                    f.this.a("格式错误", -2);
                } catch (Exception e) {
                    e.printStackTrace();
                    f.this.a("请求失败", -2);
                }
            }
        };
        this.g.start();
        return 0;
    }

    private String a(String str, String str2, int i, String str3) {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        if (str != null) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("t=");
            stringBuilder3.append(str);
            stringBuilder3.append("&");
            stringBuilder2.append(stringBuilder3.toString());
        }
        if (str2 != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("us=");
            stringBuilder.append(str2);
            stringBuilder.append("&");
            stringBuilder2.append(stringBuilder.toString());
        }
        if (str3 != null) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("sign=");
            stringBuilder.append(str3);
            stringBuilder.append("&");
            stringBuilder2.append(stringBuilder.toString());
        }
        if (i >= 0) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("exper=");
            stringBuilder.append(i);
            stringBuilder.append("&");
            stringBuilder2.append(stringBuilder.toString());
        }
        if (stringBuilder2.length() > 1) {
            stringBuilder2.deleteCharAt(stringBuilder2.length() - 1);
        }
        return stringBuilder2.toString();
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, int i) {
        Message message = new Message();
        message.what = 1;
        message.arg1 = i;
        message.obj = str;
        this.i.sendMessage(message);
    }

    private void a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        int i = jSONObject.getInt("code");
        if (i != 0) {
            String string = jSONObject.getString("message");
            TXCLog.e("TXCVodPlayerNetApi", string);
            a(string, i);
            return;
        }
        this.b = new i(jSONObject);
        if (this.b.a() == null) {
            a("无播放地址", -3);
        } else {
            this.i.sendEmptyMessage(0);
        }
    }

    public void a(g gVar) {
        this.a = gVar;
    }

    public i a() {
        return this.b;
    }

    public void a(boolean z) {
        this.h = z;
    }
}