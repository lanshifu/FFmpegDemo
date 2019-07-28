package com.youdao.sdk.app.other;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.youdao.sdk.app.other.h.a;
import defpackage.sy;
import java.net.NetworkInterface;
import java.net.SocketException;

public abstract class l extends g {
    protected Context b;
    protected String c;
    protected String d;
    protected TelephonyManager e = ((TelephonyManager) this.b.getSystemService("phone"));
    protected WifiManager f;

    public l(Context context) {
        this.b = context;
        this.f = (WifiManager) context.getSystemService("wifi");
    }

    /* Access modifiers changed, original: protected */
    public void e(String str) {
        a("appKey", str);
    }

    /* Access modifiers changed, original: protected */
    public void f(String str) {
        a("nv", str);
    }

    /* Access modifiers changed, original: protected */
    public void g(String str) {
        a("q", str);
    }

    /* Access modifiers changed, original: protected */
    public void h(String str) {
        a("z", str);
    }

    /* Access modifiers changed, original: protected */
    public void i(String str) {
        a("o", str);
    }

    /* Access modifiers changed, original: protected */
    public void a(float f) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(f);
        a("sc_a", stringBuilder.toString());
    }

    /* Access modifiers changed, original: protected */
    public void j(String str) {
        if (str == null) {
            str = "";
        } else {
            str = str.substring(0, p(str));
        }
        a("mcc", str);
    }

    /* Access modifiers changed, original: protected */
    public void k(String str) {
        a("mnc", str == null ? "" : str.substring(p(str)));
    }

    /* Access modifiers changed, original: protected */
    public void l(String str) {
        a("iso", str);
    }

    /* Access modifiers changed, original: protected */
    public void m(String str) {
        a("cn", str);
    }

    /* Access modifiers changed, original: protected */
    public void a(a aVar) {
        a("ct", aVar);
    }

    /* Access modifiers changed, original: protected */
    public void a(int i) {
        a("dct", String.valueOf(i));
    }

    /* Access modifiers changed, original: protected */
    public void n(String str) {
        a("imei", str);
    }

    private void a(String str, a aVar) {
        a(str, aVar.toString());
    }

    private int p(String str) {
        return Math.min(3, str.length());
    }

    public void a() {
        if (this.f != null) {
            WifiInfo wifiInfo = null;
            try {
                wifiInfo = this.f.getConnectionInfo();
            } catch (Exception e) {
                sy.a("Unable to fectch connection wifi info", e);
            }
            if (wifiInfo != null) {
                StringBuilder stringBuilder = new StringBuilder("");
                String macAddress = wifiInfo.getMacAddress();
                if (TextUtils.isEmpty(macAddress) || "02:00:00:00:00:00".equals(macAddress)) {
                    macAddress = b();
                }
                stringBuilder.append(macAddress);
                stringBuilder.append(",");
                stringBuilder.append(wifiInfo.getSSID() == null ? "" : wifiInfo.getSSID());
                a("wifi", stringBuilder.toString());
            }
        }
    }

    public static String b() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth1");
            if (byName == null) {
                byName = NetworkInterface.getByName("wlan0");
            }
            if (byName == null) {
                return "02:00:00:00:00:00";
            }
            byte[] hardwareAddress = byName.getHardwareAddress();
            if (hardwareAddress != null) {
                if (hardwareAddress.length != 0) {
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        stringBuffer.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (stringBuffer.length() > 0) {
                        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    }
                    return stringBuffer.toString();
                }
            }
            return "02:00:00:00:00:00";
        } catch (SocketException e) {
            sy.c("Exception encounted", e);
            return "02:00:00:00:00:00";
        }
    }

    public void o(String str) {
        a("pkn", str);
    }
}
