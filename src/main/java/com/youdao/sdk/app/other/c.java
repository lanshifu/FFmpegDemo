package com.youdao.sdk.app.other;

import com.youdao.sdk.app.c.a;
import java.util.Map;

public final class c implements Runnable {
    final /* synthetic */ Map a;
    final /* synthetic */ String b;
    final /* synthetic */ int c;
    final /* synthetic */ a d;

    public c(Map map, String str, int i, a aVar) {
        this.a = map;
        this.b = str;
        this.c = i;
        this.d = aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0086  */
    public void run() {
        /*
        r5 = this;
        r0 = new java.util.ArrayList;
        r0.<init>();
        r1 = r5.a;
        r1 = r1.entrySet();
        r1 = r1.iterator();
    L_0x000f:
        r2 = r1.hasNext();
        if (r2 == 0) goto L_0x0032;
    L_0x0015:
        r2 = r1.next();
        r2 = (java.util.Map.Entry) r2;
        r3 = r2.getKey();
        r3 = (java.lang.String) r3;
        r2 = r2.getValue();
        r2 = (java.lang.String) r2;
        if (r2 == 0) goto L_0x000f;
    L_0x0029:
        r4 = new org.apache.http.message.BasicNameValuePair;
        r4.<init>(r3, r2);
        r0.add(r4);
        goto L_0x000f;
    L_0x0032:
        r1 = new org.apache.http.client.methods.HttpPost;
        r2 = r5.b;
        r1.<init>(r2);
        r2 = "Content-Type";
        r3 = "application/x-www-form-urlencoded; charset=utf-8";
        r1.setHeader(r2, r3);
        r2 = 0;
        r3 = new org.apache.http.client.entity.UrlEncodedFormEntity;	 Catch:{ Exception -> 0x0071 }
        r4 = "utf-8";
        r3.<init>(r0, r4);	 Catch:{ Exception -> 0x0071 }
        r1.setEntity(r3);	 Catch:{ Exception -> 0x0071 }
        r0 = r5.c;	 Catch:{ Exception -> 0x0071 }
        r0 = defpackage.sv.a(r0);	 Catch:{ Exception -> 0x0071 }
        r1 = r0.execute(r1);	 Catch:{ Exception -> 0x006c, all -> 0x0069 }
        r2 = new su;	 Catch:{ Exception -> 0x006c, all -> 0x0069 }
        r2.<init>(r1);	 Catch:{ Exception -> 0x006c, all -> 0x0069 }
        if (r0 == 0) goto L_0x005f;
    L_0x005c:
        r0.close();
    L_0x005f:
        r0 = r5.d;
        r1 = defpackage.sw.a(r2);
        r0.onResult(r1);
        return;
    L_0x0069:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0084;
    L_0x006c:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0072;
    L_0x006f:
        r1 = move-exception;
        goto L_0x0084;
    L_0x0071:
        r1 = move-exception;
    L_0x0072:
        r0 = "Download task threw an internal exception";
        defpackage.sy.c(r0, r1);	 Catch:{ all -> 0x006f }
        r0 = r5.d;	 Catch:{ all -> 0x006f }
        r1 = com.youdao.sdk.app.HttpErrorCode.REQUEST_ERROR;	 Catch:{ all -> 0x006f }
        r0.onError(r1);	 Catch:{ all -> 0x006f }
        if (r2 == 0) goto L_0x0083;
    L_0x0080:
        r2.close();
    L_0x0083:
        return;
    L_0x0084:
        if (r2 == 0) goto L_0x0089;
    L_0x0086:
        r2.close();
    L_0x0089:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.youdao.sdk.app.other.c.run():void");
    }
}
