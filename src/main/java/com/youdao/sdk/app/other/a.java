package com.youdao.sdk.app.other;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import defpackage.sx;
import java.util.Map;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public class a {
    private Context a;

    public String a() {
        return "1.1";
    }

    public a(Context context) {
        this.a = context;
    }

    public Map<String, String> b() {
        return new sx(this.a).c();
    }

    public String c() {
        String ssid = ((WifiManager) this.a.getSystemService("wifi")).getConnectionInfo().getSSID();
        if (ssid == null) {
            return "";
        }
        if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        return ssid;
    }

    public String d() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
        if (connectivityManager == null) {
            return IjkMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return IjkMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
                switch (activeNetworkInfo.getSubtype()) {
                    case 0:
                        return IjkMediaMeta.IJKM_VAL_TYPE__UNKNOWN;
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2g";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return "3g";
                    case 13:
                        return "4g";
                    default:
                        return activeNetworkInfo.getTypeName();
                }
            case 1:
                return "wifi";
            default:
                return activeNetworkInfo.getTypeName();
        }
    }
}
