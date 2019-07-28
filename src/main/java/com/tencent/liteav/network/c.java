package com.tencent.liteav.network;

import com.tencent.liteav.basic.f.b;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCCommonUtil;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

/* compiled from: TXCIntelligentRoute */
class c {
    public b a = null;
    public int b = 5;
    private final String c = "http://tcdns.myqcloud.com/queryip";
    private final String d = "forward_stream";
    private final String e = "forward_num";
    private final String f = "request_type";
    private final String g = "sdk_version";
    private final String h = "51451748-d8f2-4629-9071-db2983aa7251";
    private Thread i = null;

    c() {
    }

    public void a(final String str, final int i) {
        this.i = new Thread("TXCPushRoute") {
            public void run() {
                Exception e;
                if (c.this.a != null) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < 5; i++) {
                        try {
                            String a = c.this.b(str, i);
                            try {
                                JSONObject jSONObject = new JSONObject(a);
                                if (jSONObject.has("use") && jSONObject.getInt("use") == 0) {
                                    break;
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            ArrayList a2 = c.this.a(a);
                            if (a2 != null) {
                                try {
                                    if (a2.size() > 0) {
                                        arrayList = a2;
                                        break;
                                    }
                                } catch (Exception e3) {
                                    ArrayList arrayList2 = a2;
                                    e = e3;
                                    arrayList = arrayList2;
                                    e.printStackTrace();
                                }
                            }
                            AnonymousClass1.sleep(1000, 0);
                            arrayList = a2;
                        } catch (Exception e4) {
                            e = e4;
                            e.printStackTrace();
                        }
                    }
                    c.this.a.onFetchDone(0, arrayList);
                }
            }
        };
        this.i.start();
    }

    private String b(String str, int i) {
        StringBuffer stringBuffer = new StringBuffer("");
        try {
            InputStream c = c(str, i);
            if (c == null) {
                return "";
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(c));
            while (true) {
                str = bufferedReader.readLine();
                if (str == null) {
                    break;
                }
                stringBuffer.append(str);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream c(String str, int i) throws IOException {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://tcdns.myqcloud.com/queryip").openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("forward_stream", str);
            httpURLConnection.setRequestProperty("forward_num", ConnectSocketParams.EFFECT_TYPE_BIG);
            httpURLConnection.setRequestProperty("sdk_version", TXCCommonUtil.getSDKVersionStr());
            if (i == 1) {
                httpURLConnection.setRequestProperty("request_type", "1");
            } else if (i == 2) {
                httpURLConnection.setRequestProperty("request_type", ConnectSocketParams.EFFECT_TYPE_BIG);
            } else {
                httpURLConnection.setRequestProperty("request_type", "3");
            }
            if (this.b > 0) {
                httpURLConnection.setConnectTimeout(this.b * 1000);
                httpURLConnection.setReadTimeout(this.b * 1000);
            }
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return httpURLConnection.getInputStream();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<a> a(String str) {
        JSONException e;
        ArrayList<a> arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.getInt("state") != 0) {
            return null;
        }
        JSONArray jSONArray = jSONObject.getJSONObject("content").getJSONArray("list");
        if (jSONArray == null) {
            return null;
        }
        int i;
        a a;
        for (i = 0; i < jSONArray.length(); i++) {
            a = a((JSONObject) jSONArray.opt(i));
            if (a != null && a.c) {
                arrayList.add(a);
            }
        }
        for (i = 0; i < jSONArray.length(); i++) {
            a = a((JSONObject) jSONArray.opt(i));
            if (!(a == null || a.c)) {
                arrayList.add(a);
            }
        }
        ArrayList<a> a2;
        if (b.a().a("Network", "EnableRouteOptimize") == 1 && m.a().c()) {
            a2 = a((ArrayList) arrayList, true);
            try {
                a((ArrayList) a2);
                return a2;
            } catch (JSONException e2) {
                JSONException jSONException = e2;
                arrayList = a2;
                e = jSONException;
                e.printStackTrace();
                return arrayList;
            }
        }
        try {
            long a3 = b.a().a("Network", "RouteSamplingMaxCount");
            if (a3 >= 1) {
                long a4 = m.a().a("51451748-d8f2-4629-9071-db2983aa7251");
                if (a4 <= a3) {
                    a2 = a((ArrayList) arrayList, false);
                    m.a().a("51451748-d8f2-4629-9071-db2983aa7251", a4 + 1);
                    arrayList = a2;
                }
            }
            a((ArrayList) arrayList);
            return arrayList;
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            return arrayList;
        }
    }

    private a a(JSONObject jSONObject) {
        a aVar = new a();
        try {
            aVar.a = jSONObject.getString(OnNativeInvokeListener.ARG_IP);
            aVar.b = jSONObject.getString(OnNativeInvokeListener.ARG_PORT);
            aVar.e = 0;
            aVar.c = false;
            aVar.d = b(aVar.a);
            if (jSONObject.has("type") && jSONObject.getInt("type") == 2) {
                aVar.c = true;
            }
            return aVar;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean b(String str) {
        if (str != null) {
            for (String c : str.split("[.]")) {
                if (!c(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean c(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    private ArrayList<a> a(ArrayList<a> arrayList, boolean z) {
        ArrayList<a> arrayList2 = null;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        ArrayList<a> arrayList3;
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        Iterator it = arrayList.iterator();
        loop0:
        while (true) {
            arrayList3 = arrayList2;
            while (it.hasNext()) {
                a arrayList22 = (a) it.next();
                if (arrayList22.c) {
                    arrayList4.add(arrayList22);
                } else if (!arrayList22.d) {
                    arrayList5.add(arrayList22);
                }
            }
            break loop0;
        }
        ArrayList arrayList6 = new ArrayList();
        while (true) {
            if (arrayList4.size() <= 0 && arrayList5.size() <= 0) {
                break;
            }
            if (z) {
                if (arrayList3 != null) {
                    arrayList6.add(arrayList3);
                }
                if (arrayList4.size() > 0) {
                    arrayList6.add(arrayList4.get(0));
                    arrayList4.remove(0);
                }
            } else {
                if (arrayList4.size() > 0) {
                    arrayList6.add(arrayList4.get(0));
                    arrayList4.remove(0);
                }
                if (arrayList3 != null) {
                    arrayList6.add(arrayList3);
                }
            }
            if (arrayList5.size() > 0) {
                arrayList6.add(arrayList5.get(0));
                arrayList5.remove(0);
            }
        }
        int size = arrayList6.size();
        if (size > 0) {
            a aVar = (a) arrayList6.get(size - 1);
            if (!(aVar == null || b(aVar.a) || arrayList3 == null)) {
                arrayList6.add(arrayList3);
            }
        }
        return arrayList6;
    }

    private void a(ArrayList<a> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            String str = "";
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(" \n Nearest IP: ");
                stringBuilder.append(aVar.a);
                stringBuilder.append(" Port: ");
                stringBuilder.append(aVar.b);
                stringBuilder.append(" Q Channel: ");
                stringBuilder.append(aVar.c);
                str = stringBuilder.toString();
            }
            TXCLog.e("TXCIntelligentRoute", str);
        }
    }
}
