package defpackage;

import java.io.File;

/* compiled from: BaseFileStrategy */
/* renamed from: er */
public abstract class er implements et {
    protected long a = 524288000;
    protected long b = 524288000;
    protected long c = (this.a + this.b);
    protected ff d;
    protected fg e;

    public abstract void a();

    public er(ff ffVar, fg fgVar) {
        this.d = ffVar;
        this.e = fgVar;
        a();
        b();
        b a = aat.a("TTT");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("playCache:");
        stringBuilder.append(this.a);
        stringBuilder.append(" localCache:");
        stringBuilder.append(this.b);
        stringBuilder.append(" runPlayCache:");
        stringBuilder.append(this.c);
        a.a(stringBuilder.toString(), new Object[0]);
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        this.c = this.a + this.b;
    }

    public void c() {
        if (this.d != null) {
            if (this.b == 0) {
                this.d.b();
                return;
            }
            long c = this.d.c() - this.b;
            b a = aat.a("TTT");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("doPrepared remainCacheSize:");
            stringBuilder.append(c);
            a.a(stringBuilder.toString(), new Object[0]);
        }
    }

    /* JADX WARNING: Missing block: B:11:0x0036, code skipped:
            return;
     */
    public void a(java.lang.String r4) {
        /*
        r3 = this;
        r0 = r3.d;
        if (r0 == 0) goto L_0x0036;
    L_0x0004:
        r0 = r3.e;
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        goto L_0x0036;
    L_0x0009:
        r0 = android.text.TextUtils.isEmpty(r4);
        if (r0 != 0) goto L_0x0035;
    L_0x000f:
        r0 = r4.trim();
        r0 = r0.toLowerCase();
        r1 = "ts";
        r0 = r0.endsWith(r1);
        if (r0 != 0) goto L_0x0020;
    L_0x001f:
        goto L_0x0035;
    L_0x0020:
        r0 = "TTT";
        r0 = aat.a(r0);
        r1 = "runningCacheTs";
        r2 = 0;
        r2 = new java.lang.Object[r2];
        r0.a(r1, r2);
        r3.b(r4);
        r3.d();
        return;
    L_0x0035:
        return;
    L_0x0036:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.er.a(java.lang.String):void");
    }

    private void b(String str) {
        this.d.a(this.e.a(new File(str)));
    }

    private void d() {
        long c = this.d.c();
        b a = aat.a("TTT");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("checkCacheTs remain size:");
        stringBuilder.append(c);
        a.a(stringBuilder.toString(), new Object[0]);
        if (c > this.c) {
            e();
        }
    }

    private void e() {
        fg fgVar = (fg) this.d.e().getLast();
        if (fgVar.b()) {
            b a = aat.a("TTT");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("play video before size:");
            stringBuilder.append(fgVar.e());
            a.a(stringBuilder.toString(), new Object[0]);
            this.d.b(fgVar.a());
            a = aat.a("TTT");
            stringBuilder = new StringBuilder();
            stringBuilder.append("play video after size:");
            stringBuilder.append(fgVar.e());
            stringBuilder.append(" videoCacheStorage size:");
            stringBuilder.append(this.d.c());
            a.a(stringBuilder.toString(), new Object[0]);
            d();
            return;
        }
        this.d.e().removeLast();
        es.b(fgVar.d().getAbsolutePath());
        e();
    }
}
