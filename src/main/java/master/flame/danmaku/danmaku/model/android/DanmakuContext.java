package master.flame.danmaku.danmaku.model.android;

import android.graphics.Typeface;
import defpackage.yu;
import defpackage.zc;
import defpackage.zd;
import defpackage.ze;
import defpackage.zl;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DanmakuContext implements Cloneable {
    private b A;
    private boolean B;
    private boolean C;
    public Typeface a = null;
    public int b = ze.a;
    public float c = 1.0f;
    public int d = 0;
    public boolean e = true;
    public boolean f = true;
    public boolean g = true;
    public boolean h = true;
    public boolean i = true;
    List<Integer> j = new ArrayList();
    public int k = -1;
    public float l = 1.0f;
    public zc m;
    List<Integer> n = new ArrayList();
    List<Integer> o = new ArrayList();
    List<String> p = new ArrayList();
    public zd q = new a();
    public zl r = new zl();
    public yu s = new yu();
    public d t = d.a();
    public c u = c.c;
    public byte v = (byte) 0;
    private List<WeakReference<a>> w;
    private boolean x = false;
    private boolean y = false;
    private boolean z = false;

    public enum DanmakuConfigTag {
        FT_DANMAKU_VISIBILITY,
        FB_DANMAKU_VISIBILITY,
        L2R_DANMAKU_VISIBILITY,
        R2L_DANMAKU_VISIBILIY,
        SPECIAL_DANMAKU_VISIBILITY,
        TYPEFACE,
        TRANSPARENCY,
        SCALE_TEXTSIZE,
        MAXIMUM_NUMS_IN_SCREEN,
        DANMAKU_STYLE,
        DANMAKU_BOLD,
        COLOR_VALUE_WHITE_LIST,
        USER_ID_BLACK_LIST,
        USER_HASH_BLACK_LIST,
        SCROLL_SPEED_FACTOR,
        BLOCK_GUEST_DANMAKU,
        DUPLICATE_MERGING_ENABLED,
        MAXIMUN_LINES,
        OVERLAPPING_ENABLE,
        ALIGN_BOTTOM,
        DANMAKU_MARGIN,
        DANMAKU_SYNC;

        public boolean isVisibilityRelatedTag() {
            return equals(FT_DANMAKU_VISIBILITY) || equals(FB_DANMAKU_VISIBILITY) || equals(L2R_DANMAKU_VISIBILITY) || equals(R2L_DANMAKU_VISIBILIY) || equals(SPECIAL_DANMAKU_VISIBILITY) || equals(COLOR_VALUE_WHITE_LIST) || equals(USER_ID_BLACK_LIST);
        }
    }

    public interface a {
        boolean a(DanmakuContext danmakuContext, DanmakuConfigTag danmakuConfigTag, Object... objArr);
    }

    public static DanmakuContext a() {
        return new DanmakuContext();
    }

    public zd b() {
        return this.q;
    }

    public DanmakuContext a(float f) {
        int i = (int) (((float) ze.a) * f);
        if (i != this.b) {
            this.b = i;
            this.q.a(i);
            a(DanmakuConfigTag.TRANSPARENCY, Float.valueOf(f));
        }
        return this;
    }

    public DanmakuContext b(float f) {
        if (this.c != f) {
            this.c = f;
            this.q.c();
            this.q.a(f);
            this.r.d();
            this.r.c();
            a(DanmakuConfigTag.SCALE_TEXTSIZE, Float.valueOf(f));
        }
        return this;
    }

    private <T> void a(String str, T t, boolean z) {
        this.s.a(str, z).a(t);
    }

    public DanmakuContext a(boolean z) {
        this.q.a(z);
        a(DanmakuConfigTag.DANMAKU_BOLD, Boolean.valueOf(z));
        return this;
    }

    public DanmakuContext c(float f) {
        if (this.l != f) {
            this.l = f;
            this.t.a(f);
            this.r.d();
            this.r.c();
            a(DanmakuConfigTag.SCROLL_SPEED_FACTOR, Float.valueOf(f));
        }
        return this;
    }

    public DanmakuContext b(boolean z) {
        if (this.y != z) {
            this.y = z;
            this.r.e();
            a(DanmakuConfigTag.DUPLICATE_MERGING_ENABLED, Boolean.valueOf(z));
        }
        return this;
    }

    public boolean c() {
        return this.y;
    }

    public boolean d() {
        return this.z;
    }

    public DanmakuContext a(Map<Integer, Integer> map) {
        this.B = map != null;
        if (map == null) {
            this.s.c("1018_Filter", false);
        } else {
            a("1018_Filter", map, false);
        }
        this.r.e();
        a(DanmakuConfigTag.MAXIMUN_LINES, map);
        return this;
    }

    public DanmakuContext b(Map<Integer, Boolean> map) {
        this.C = map != null;
        if (map == null) {
            this.s.c("1019_Filter", false);
        } else {
            a("1019_Filter", map, false);
        }
        this.r.e();
        a(DanmakuConfigTag.OVERLAPPING_ENABLE, map);
        return this;
    }

    public boolean e() {
        return this.B;
    }

    public boolean f() {
        return this.C;
    }

    public DanmakuContext a(b bVar, master.flame.danmaku.danmaku.model.android.b.a aVar) {
        this.A = bVar;
        if (this.A != null) {
            this.A.setProxy(aVar);
            this.q.a(this.A);
        }
        return this;
    }

    public DanmakuContext a(zc zcVar) {
        this.m = zcVar;
        return this;
    }

    public void a(a aVar) {
        if (aVar == null || this.w == null) {
            this.w = Collections.synchronizedList(new ArrayList());
        }
        for (WeakReference weakReference : this.w) {
            if (aVar.equals(weakReference.get())) {
                return;
            }
        }
        this.w.add(new WeakReference(aVar));
    }

    public void g() {
        if (this.w != null) {
            this.w.clear();
            this.w = null;
        }
    }

    private void a(DanmakuConfigTag danmakuConfigTag, Object... objArr) {
        if (this.w != null) {
            for (WeakReference weakReference : this.w) {
                a aVar = (a) weakReference.get();
                if (aVar != null) {
                    aVar.a(this, danmakuConfigTag, objArr);
                }
            }
        }
    }

    public DanmakuContext h() {
        this.q = new a();
        this.r = new zl();
        this.s.a();
        this.t = d.a();
        return this;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
