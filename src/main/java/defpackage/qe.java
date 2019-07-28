package defpackage;

import android.content.Context;
import android.support.annotation.Nullable;
import com.one.tomato.entity.LookTimes;
import com.one.tomato.entity.PostList;
import com.one.tomato.entity.db.LookTimeBean;
import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.mvp.base.okhttp.a;
import com.one.tomato.net.ResponseObserver;
import com.one.tomato.net.TomatoCallback;
import com.one.tomato.net.TomatoParams;
import com.one.tomato.utils.g;
import com.one.tomato.utils.y;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VideoPlayCountUtils */
/* renamed from: qe */
public class qe {
    private static qe a;

    /* compiled from: VideoPlayCountUtils */
    /* renamed from: qe$a */
    public interface a {
        void a(LookTimes lookTimes);

        void a(ResponseThrowable responseThrowable);
    }

    private qe() {
    }

    public static qe a() {
        if (a == null) {
            synchronized (qe.class) {
                if (a == null) {
                    a = new qe();
                }
            }
        }
        return a;
    }

    public boolean b() {
        return g.m().isHasPay();
    }

    public void a(boolean z) {
        LookTimeBean lookTimeBean = new LookTimeBean();
        lookTimeBean.setHasPay(z);
        g.a(lookTimeBean);
    }

    public int c() {
        int remainTimes = g.m().getRemainTimes();
        return remainTimes < 0 ? 0 : remainTimes;
    }

    public int d() {
        Object obj;
        LookTimeBean m = g.m();
        int freeTimes = m.getFreeTimes();
        int remainTimes = m.getRemainTimes();
        Object obj2 = 1;
        if (freeTimes == 0) {
            freeTimes = 30;
            obj = 1;
        } else {
            obj = null;
        }
        if (freeTimes < remainTimes) {
            freeTimes = remainTimes;
        } else {
            obj2 = obj;
        }
        if (obj2 != null) {
            m.setFreeTimes(freeTimes);
            g.a(m);
        }
        return freeTimes;
    }

    public boolean a(PostList postList) {
        List lookedIds = g.m().getLookedIds();
        if (postList == null) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(postList.getId());
        stringBuilder.append("");
        String stringBuilder2 = stringBuilder.toString();
        StringBuilder stringBuilder3 = new StringBuilder();
        stringBuilder3.append(postList.getMemberId());
        stringBuilder3.append("");
        String stringBuilder4 = stringBuilder3.toString();
        stringBuilder3 = new StringBuilder();
        stringBuilder3.append(g.d());
        stringBuilder3.append("");
        if (stringBuilder4.equals(stringBuilder3.toString())) {
            return true;
        }
        if (lookedIds == null || lookedIds.isEmpty() || !lookedIds.contains(stringBuilder2)) {
            return false;
        }
        return true;
    }

    public void b(PostList postList) {
        LookTimeBean m = g.m();
        List lookedIds = m.getLookedIds();
        if (postList != null) {
            if (lookedIds == null) {
                lookedIds = new ArrayList();
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(postList.getId());
            stringBuilder.append("");
            if (!lookedIds.contains(stringBuilder.toString())) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(postList.getId());
                stringBuilder.append("");
                lookedIds.add(stringBuilder.toString());
            }
            m.setLookedIds(lookedIds);
            m.setRemainTimes(c() - 1);
            g.a(m);
        }
    }

    public void a(PostList postList, ResponseObserver responseObserver) {
        if (postList != null && !postList.isAd() && !b() && !a(postList)) {
            if (c() == 0) {
                a(responseObserver);
            } else {
                b(postList);
            }
        }
    }

    public void a(ResponseObserver responseObserver) {
        TomatoParams tomatoParams = new TomatoParams(pd.a().b(), "app/video/payInfo");
        tomatoParams.addParameter("memberId", Integer.valueOf(g.d()));
        tomatoParams.post(new TomatoCallback(responseObserver, 95, LookTimes.class));
    }

    public void a(@Nullable Context context, final a aVar) {
        if (context != null) {
            of.a.a().e(g.d()).compose(y.a(context)).compose(y.a()).subscribe(new a<LookTimes>() {
                public void a(LookTimes lookTimes) {
                    aVar.a(lookTimes);
                }

                public void a(ResponseThrowable responseThrowable) {
                    aVar.a(responseThrowable);
                }
            });
        }
    }
}
