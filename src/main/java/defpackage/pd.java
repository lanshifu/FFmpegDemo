package defpackage;

/* compiled from: DomainServer */
/* renamed from: pd */
public class pd {
    private static pd a;

    public static pd a() {
        if (a == null) {
            a = new pd();
        }
        return a;
    }

    public synchronized String a(String str) {
        String a = pb.a(str);
        if (!a.endsWith("/")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a);
            stringBuilder.append("/");
            a = stringBuilder.toString();
        }
        if (!"ttViewPicture".equals(str) && !"ttViewVideo".equals(str)) {
            return a;
        }
        return b(a);
    }

    public synchronized String b(String str) {
        if (!str.endsWith("/")) {
            return str;
        }
        return b(str.substring(0, str.length() - 1));
    }

    public synchronized String b() {
        String a;
        a = pb.a();
        if (!a.endsWith("/")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(a);
            stringBuilder.append("/");
            a = stringBuilder.toString();
        }
        return a;
    }

    public synchronized String c() {
        return a("image");
    }

    public synchronized String d() {
        return a("video");
    }

    public synchronized String e() {
        return a("share");
    }

    public synchronized String f() {
        return a("liveServer");
    }

    public synchronized String g() {
        return a("livePicture");
    }

    public synchronized String h() {
        return a("liveUpload");
    }

    public synchronized String i() {
        return a("reportServer");
    }

    public synchronized String j() {
        return a("website");
    }

    public synchronized String k() {
        return a("ttUpload");
    }

    public synchronized String l() {
        return a("ttViewPicture");
    }

    public synchronized String m() {
        return a("ttViewVideo");
    }
}
