package com.youdao.sdk.app.other;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

public class j {
    private static HttpClient a;

    public static class a {
        private final Context a;
        private final ConnectivityManager b;

        public a(Context context) {
            this.a = context;
            this.b = (ConnectivityManager) context.getSystemService("connectivity");
        }

        private HttpHost a() {
            NetworkInfo activeNetworkInfo = this.b.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo == null) {
                    return null;
                }
                extraInfo = extraInfo.toLowerCase();
                if (extraInfo.contains("cmnet")) {
                    return null;
                }
                if (extraInfo.contains("cmwap")) {
                    return new HttpHost("10.0.0.172");
                }
                if (extraInfo.contains("3gnet")) {
                    return null;
                }
                if (extraInfo.contains("3gwap")) {
                    return new HttpHost("10.0.0.172");
                }
                if (extraInfo.contains("uninet")) {
                    return null;
                }
                if (extraInfo.contains("uniwap")) {
                    return new HttpHost("10.0.0.172");
                }
                if (extraInfo.contains("ctnet")) {
                    return null;
                }
                if (extraInfo.contains("ctwap")) {
                    return new HttpHost("10.0.0.200");
                }
                if (extraInfo.contains("#777")) {
                    try {
                        Cursor query = this.a.getContentResolver().query(Uri.parse("content://telephony/carriers/preferapn"), new String[]{"proxy", OnNativeInvokeListener.ARG_PORT}, null, null, null);
                        if (query.moveToFirst()) {
                            String string = query.getString(0);
                            if (string.length() > 3) {
                                int parseInt;
                                try {
                                    parseInt = Integer.parseInt(query.getString(1));
                                } catch (NumberFormatException unused) {
                                    parseInt = 0;
                                }
                                if (parseInt <= 0) {
                                    parseInt = 80;
                                }
                                return new HttpHost(string, parseInt);
                            }
                        }
                        return null;
                    } catch (Exception unused2) {
                        return null;
                    }
                }
            }
            return null;
        }

        public void a(HttpClient httpClient) {
            j.a(httpClient, a());
        }
    }

    public static void a(HttpClient httpClient, HttpHost httpHost) {
        httpClient.getParams().setParameter("http.route.default-proxy", httpHost);
    }
}
