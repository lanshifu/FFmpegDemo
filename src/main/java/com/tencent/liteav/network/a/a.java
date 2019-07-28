package com.tencent.liteav.network.a;

import java.io.IOException;

/* compiled from: DnsException */
public class a extends IOException {
    public a(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(": ");
        stringBuilder.append(str2);
        super(stringBuilder.toString());
    }
}
