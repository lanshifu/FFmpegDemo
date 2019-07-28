package defpackage;

import android.support.v7.widget.RecyclerView.RecycledViewPool;
import com.one.tomato.entity.BaseResponse;
import com.one.tomato.entity.PostList;
import com.one.tomato.utils.g;
import com.one.tomato.utils.o;
import com.one.tomato.utils.y;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PostUtils.kt */
/* renamed from: ov */
public final class ov {
    public static final ov a = new ov();
    private static final int[] b = new int[]{2131099737, 2131099727, 2131099738, 2131099728};
    private static boolean c;
    private static ArrayList<a> d = new ArrayList();
    private static final RecycledViewPool e = new RecycledViewPool();
    private static final RecycledViewPool f = new RecycledViewPool();

    /* compiled from: PostUtils.kt */
    /* renamed from: ov$a */
    public interface a {
        void a(Boolean bool);
    }

    /* compiled from: PostUtils.kt */
    /* renamed from: ov$b */
    static final class b<T> implements wl<BaseResponse<Object>> {
        public static final b a = new b();

        b() {
        }

        /* renamed from: a */
        public final void accept(BaseResponse<Object> baseResponse) {
            o.a("yan", "更新帖子浏览次数成功");
        }
    }

    private ov() {
    }

    public final void a(boolean z) {
        c = z;
    }

    public final boolean a() {
        return c;
    }

    public final void a(PostList postList) {
        if (postList != null) {
            of.a.a().d(postList.getId(), g.d()).compose(y.a()).subscribe((wl) b.a);
        }
    }

    public final int b() {
        int a = yj.a(new yf(0, 3), (yb) yb.b);
        if (a < b.length) {
            return b[a];
        }
        return b[0];
    }

    public final void a(a aVar) {
        if (aVar != null) {
            d.add(aVar);
        }
    }

    public final boolean b(a aVar) {
        if (aVar == null || !d.contains(aVar)) {
            return false;
        }
        d.remove(aVar);
        return true;
    }

    public final boolean b(boolean z) {
        if (d.size() <= 0) {
            return false;
        }
        Iterator it = d.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(Boolean.valueOf(z));
        }
        return true;
    }
}
