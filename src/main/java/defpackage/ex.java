package defpackage;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import defpackage.ey.a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: StorageCacheManager */
/* renamed from: ex */
public class ex {
    private static ex a;
    private List<ey> b = new ArrayList();
    private ff c;
    private et d;
    private final String[] e = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    public static ex a() {
        if (a == null) {
            a = new ex();
        }
        return a;
    }

    public ex() {
        e();
    }

    public void a(File file, String str) {
        try {
            d();
            this.c = new ff(file, str);
            b();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void e() {
        ey eyVar = new ey(1, 0, 524288000);
        ey eyVar2 = new ey(2, 524288001, IjkMediaMeta.AV_CH_STEREO_RIGHT);
        ey eyVar3 = new ey(3, 1073741825, IjkMediaMeta.AV_CH_WIDE_LEFT);
        eyVar2 = new ey(4, 2147483649L, IjkMediaMeta.AV_CH_WIDE_RIGHT);
        eyVar3 = new ey(5, 4294967297L, 6442450944L);
        eyVar2 = new ey(6, 6442450945L, IjkMediaMeta.AV_CH_SURROUND_DIRECT_LEFT);
        eyVar3 = new ey(7, 8589934593L, 10737418240L);
        eyVar2 = new ey(8, 10737418241L, 1099511627776L);
        this.b.add(eyVar);
        this.b.add(eyVar2);
        this.b.add(eyVar3);
        this.b.add(eyVar2);
        this.b.add(eyVar3);
        this.b.add(eyVar2);
        this.b.add(eyVar3);
        this.b.add(eyVar2);
    }

    public void b() {
        try {
            boolean d = this.c.d();
            b a = aat.a("TTT");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("checkCache hasCachePlay: ");
            stringBuilder.append(d);
            a.a(stringBuilder.toString(), new Object[0]);
            if (d) {
                this.d = a(this.c, (fg) this.c.e().getFirst());
                return;
            }
            this.c.a();
            fg fgVar = (fg) this.c.e().getFirst();
            this.d = a(this.c, fgVar);
            this.d.c();
            this.d = a(this.c, fgVar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str) {
        if (this.d != null) {
            try {
                this.d.a(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean c() {
        if (this.d != null && (this.d instanceof eu)) {
            return true;
        }
        return false;
    }

    public boolean a(Context context) {
        String[] strArr = this.e;
        int length = strArr.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            if (!a(context, strArr[i])) {
                return false;
            }
            i++;
            z = true;
        }
        return z;
    }

    private boolean a(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public void d() {
        this.c = null;
        this.d = null;
    }

    private et a(ff ffVar, fg fgVar) {
        int a = a(ep.a());
        b a2 = aat.a("TTT");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("strategyType:");
        stringBuilder.append(a);
        a2.a(stringBuilder.toString(), new Object[0]);
        return new a(a, ffVar, fgVar).a();
    }

    private int a(long j) {
        for (ey eyVar : this.b) {
            if (eyVar.b().a(Long.valueOf(j))) {
                return eyVar.a();
            }
        }
        return 1;
    }
}
