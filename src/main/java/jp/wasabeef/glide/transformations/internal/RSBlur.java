package jp.wasabeef.glide.transformations.internal;

@Deprecated
public class RSBlur {
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0079  */
    @android.annotation.TargetApi(18)
    public static android.graphics.Bitmap blur(android.content.Context r5, android.graphics.Bitmap r6, int r7) throws android.renderscript.RSRuntimeException {
        /*
        r0 = 23;
        r1 = 0;
        r5 = android.renderscript.RenderScript.create(r5);	 Catch:{ all -> 0x005c }
        r2 = new android.renderscript.RenderScript$RSMessageHandler;	 Catch:{ all -> 0x0059 }
        r2.<init>();	 Catch:{ all -> 0x0059 }
        r5.setMessageHandler(r2);	 Catch:{ all -> 0x0059 }
        r2 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE;	 Catch:{ all -> 0x0059 }
        r3 = 1;
        r2 = android.renderscript.Allocation.createFromBitmap(r5, r6, r2, r3);	 Catch:{ all -> 0x0059 }
        r3 = r2.getType();	 Catch:{ all -> 0x0056 }
        r3 = android.renderscript.Allocation.createTyped(r5, r3);	 Catch:{ all -> 0x0056 }
        r4 = android.renderscript.Element.U8_4(r5);	 Catch:{ all -> 0x0052 }
        r4 = android.renderscript.ScriptIntrinsicBlur.create(r5, r4);	 Catch:{ all -> 0x0052 }
        r4.setInput(r2);	 Catch:{ all -> 0x0050 }
        r7 = (float) r7;	 Catch:{ all -> 0x0050 }
        r4.setRadius(r7);	 Catch:{ all -> 0x0050 }
        r4.forEach(r3);	 Catch:{ all -> 0x0050 }
        r3.copyTo(r6);	 Catch:{ all -> 0x0050 }
        if (r5 == 0) goto L_0x0040;
    L_0x0035:
        r7 = android.os.Build.VERSION.SDK_INT;
        if (r7 < r0) goto L_0x003d;
    L_0x0039:
        android.renderscript.RenderScript.releaseAllContexts();
        goto L_0x0040;
    L_0x003d:
        r5.destroy();
    L_0x0040:
        if (r2 == 0) goto L_0x0045;
    L_0x0042:
        r2.destroy();
    L_0x0045:
        if (r3 == 0) goto L_0x004a;
    L_0x0047:
        r3.destroy();
    L_0x004a:
        if (r4 == 0) goto L_0x004f;
    L_0x004c:
        r4.destroy();
    L_0x004f:
        return r6;
    L_0x0050:
        r6 = move-exception;
        goto L_0x0054;
    L_0x0052:
        r6 = move-exception;
        r4 = r1;
    L_0x0054:
        r1 = r3;
        goto L_0x0060;
    L_0x0056:
        r6 = move-exception;
        r4 = r1;
        goto L_0x0060;
    L_0x0059:
        r6 = move-exception;
        r2 = r1;
        goto L_0x005f;
    L_0x005c:
        r6 = move-exception;
        r5 = r1;
        r2 = r5;
    L_0x005f:
        r4 = r2;
    L_0x0060:
        if (r5 == 0) goto L_0x006d;
    L_0x0062:
        r7 = android.os.Build.VERSION.SDK_INT;
        if (r7 < r0) goto L_0x006a;
    L_0x0066:
        android.renderscript.RenderScript.releaseAllContexts();
        goto L_0x006d;
    L_0x006a:
        r5.destroy();
    L_0x006d:
        if (r2 == 0) goto L_0x0072;
    L_0x006f:
        r2.destroy();
    L_0x0072:
        if (r1 == 0) goto L_0x0077;
    L_0x0074:
        r1.destroy();
    L_0x0077:
        if (r4 == 0) goto L_0x007c;
    L_0x0079:
        r4.destroy();
    L_0x007c:
        throw r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: jp.wasabeef.glide.transformations.internal.RSBlur.blur(android.content.Context, android.graphics.Bitmap, int):android.graphics.Bitmap");
    }
}
