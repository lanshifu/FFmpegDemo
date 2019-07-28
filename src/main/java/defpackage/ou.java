package defpackage;

import android.app.Activity;
import com.one.tomato.entity.BaseResponse;
import com.one.tomato.entity.MainNotifyBean;
import com.one.tomato.entity.PostList;
import com.one.tomato.entity.db.AdPage;
import com.one.tomato.entity.event.MemberFocusEvent;
import com.one.tomato.entity.event.PostCollectOrThumbEvent;
import com.one.tomato.mvp.base.okhttp.ResponseThrowable;
import com.one.tomato.utils.ad;
import com.one.tomato.utils.af;
import com.one.tomato.utils.c;
import com.one.tomato.utils.g;
import com.one.tomato.utils.h;
import com.one.tomato.utils.o;
import com.one.tomato.utils.y;
import de.greenrobot.event.EventBus;
import defpackage.or.a;
import io.reactivex.disposables.b;
import io.reactivex.q;
import io.reactivex.r;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.f;

/* compiled from: NewPostTabListPresenter.kt */
/* renamed from: ou */
public final class ou extends oe<a> {
    private ps a;
    private ArrayList<AdPage> b;
    private int c;
    private int d = 10;
    private int e = 1;
    private final HashSet<PostList> f = new HashSet();
    private boolean g = true;

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$a */
    static final class a<T> implements wl<b> {
        final /* synthetic */ ou a;
        final /* synthetic */ PostList b;

        a(ou ouVar, PostList postList) {
            this.a = ouVar;
            this.b = postList;
        }

        /* renamed from: a */
        public final void accept(b bVar) {
            defpackage.or.a a = ((defpackage.or.a) this.a.a());
            if (a != null) {
                a.b();
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$b */
    static final class b<T> implements wl<BaseResponse<Object>> {
        final /* synthetic */ ou a;
        final /* synthetic */ PostList b;

        b(ou ouVar, PostList postList) {
            this.a = ouVar;
            this.b = postList;
        }

        /* renamed from: a */
        public final void accept(BaseResponse<Object> baseResponse) {
            a a = ((a) this.a.a());
            if (a != null) {
                a.c();
            }
            f.a((Object) baseResponse, "it");
            baseResponse.getData();
            if (baseResponse.getData() != null) {
                this.b.setIsFavor(1);
                af.c(true);
                ad.a(2131756202);
                PostCollectOrThumbEvent postCollectOrThumbEvent = new PostCollectOrThumbEvent();
                postCollectOrThumbEvent.postList = this.b;
                EventBus.getDefault().post(postCollectOrThumbEvent);
                return;
            }
            ad.b(baseResponse.getMessage());
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$c */
    static final class c<T> implements wl<b> {
        final /* synthetic */ ou a;
        final /* synthetic */ PostList b;
        final /* synthetic */ int c;

        c(ou ouVar, PostList postList, int i) {
            this.a = ouVar;
            this.b = postList;
            this.c = i;
        }

        /* renamed from: a */
        public final void accept(b bVar) {
            a a = ((a) this.a.a());
            if (a != null) {
                a.b();
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$g */
    static final class g<T> implements wl<b> {
        final /* synthetic */ ou a;

        g(ou ouVar) {
            this.a = ouVar;
        }

        /* renamed from: a */
        public final void accept(b bVar) {
            a a = ((a) this.a.a());
            if (a != null) {
                a.b();
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$k */
    static final class k<T> implements wl<BaseResponse<ArrayList<MainNotifyBean>>> {
        final /* synthetic */ ou a;

        k(ou ouVar) {
            this.a = ouVar;
        }

        /* renamed from: a */
        public final void accept(BaseResponse<ArrayList<MainNotifyBean>> baseResponse) {
            if ((baseResponse instanceof BaseResponse) && baseResponse.getData() != null) {
                this.a.g = false;
                a a = ((a) this.a.a());
                if (a != null) {
                    a.c((ArrayList) baseResponse.getData());
                }
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$d */
    public static final class d extends com.one.tomato.mvp.base.okhttp.a<Object> {
        final /* synthetic */ ou a;
        final /* synthetic */ PostList b;
        final /* synthetic */ int c;

        d(ou ouVar, PostList postList, int i) {
            this.a = ouVar;
            this.b = postList;
            this.c = i;
        }

        public void a(Object obj) {
            a a = ((a) this.a.a());
            if (a != null) {
                a.c();
            }
            if (this.c == 1) {
                af.a(true);
                this.b.setIsAttention(1);
            } else {
                af.a(false);
                this.b.setIsAttention(0);
            }
            MemberFocusEvent memberFocusEvent = new MemberFocusEvent();
            memberFocusEvent.followFlag = this.c;
            memberFocusEvent.id = this.b.getMemberId();
            EventBus.getDefault().post(memberFocusEvent);
            ad.b(c.a(2131755232));
        }

        public void a(ResponseThrowable responseThrowable) {
            a a = ((a) this.a.a());
            if (a != null) {
                a.c();
            }
            a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$e */
    public static final class e extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ ou a;

        e(ou ouVar) {
            this.a = ouVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(arrayList);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("e");
            stringBuilder.append(responseThrowable);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$f */
    public static final class f extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ ou a;

        f(ou ouVar) {
            this.a = ouVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(arrayList);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("e");
            stringBuilder.append(responseThrowable);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$h */
    public static final class h extends com.one.tomato.mvp.base.okhttp.a<Object> {
        final /* synthetic */ ou a;
        final /* synthetic */ int b;

        h(ou ouVar, int i) {
            this.a = ouVar;
            this.b = i;
        }

        public void a(Object obj) {
            a a = ((a) this.a.a());
            if (a != null) {
                a.c();
            }
            a = ((a) this.a.a());
            if (a != null) {
                a.a(this.b);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("e");
            stringBuilder.append(responseThrowable);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.c();
            }
            a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$i */
    public static final class i extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ ou a;

        i(ou ouVar) {
            this.a = ouVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(arrayList);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$j */
    public static final class j extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ ou a;

        j(ou ouVar) {
            this.a = ouVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(arrayList);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$l */
    public static final class l extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ ou a;

        l(ou ouVar) {
            this.a = ouVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(arrayList);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("e");
            stringBuilder.append(responseThrowable);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$m */
    public static final class m extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ ou a;

        m(ou ouVar) {
            this.a = ouVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(arrayList);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("e");
            stringBuilder.append(responseThrowable);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$n */
    public static final class n extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ ou a;

        n(ou ouVar) {
            this.a = ouVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(arrayList);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
            if (this.a.g) {
                this.a.f();
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("e");
            stringBuilder.append(responseThrowable);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$o */
    public static final class o extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ ou a;

        o(ou ouVar) {
            this.a = ouVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(arrayList);
            com.one.tomato.utils.o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("e");
            stringBuilder.append(responseThrowable);
            com.one.tomato.utils.o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$p */
    public static final class p extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ ou a;

        p(ou ouVar) {
            this.a = ouVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(arrayList);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("e");
            stringBuilder.append(responseThrowable);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$q */
    public static final class q extends com.one.tomato.mvp.base.okhttp.a<ArrayList<PostList>> {
        final /* synthetic */ ou a;

        q(ou ouVar) {
            this.a = ouVar;
        }

        public void a(ArrayList<PostList> arrayList) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("t");
            stringBuilder.append(arrayList);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.b(arrayList);
            }
        }

        public void a(ResponseThrowable responseThrowable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("e");
            stringBuilder.append(responseThrowable);
            o.a("yan", stringBuilder.toString());
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    /* compiled from: NewPostTabListPresenter.kt */
    /* renamed from: ou$r */
    public static final class r extends com.one.tomato.mvp.base.okhttp.a<Object> {
        final /* synthetic */ ou a;
        final /* synthetic */ PostList b;

        r(ou ouVar, PostList postList) {
            this.a = ouVar;
            this.b = postList;
        }

        public void a(Object obj) {
            PostCollectOrThumbEvent postCollectOrThumbEvent = new PostCollectOrThumbEvent();
            postCollectOrThumbEvent.postList = this.b;
            EventBus.getDefault().post(postCollectOrThumbEvent);
            o.b("yan", "點贊");
        }

        public void a(ResponseThrowable responseThrowable) {
            a a = ((a) this.a.a());
            if (a != null) {
                a.a(responseThrowable != null ? responseThrowable.getThrowableMessage() : null);
            }
        }
    }

    public void e() {
        a aVar = (a) a();
        this.a = new ps((Activity) (aVar != null ? aVar.a() : null));
        this.b = g.c("list");
        Object a = g.a();
        f.a(a, "DBUtil.getSystemParam()");
        this.d = a.getADFrequencyOnList();
    }

    public final void a(PostList postList) {
        if (postList != null && postList.isAd() && !this.f.contains(postList)) {
            this.f.add(postList);
            h.a(postList.getId(), 1);
        }
    }

    public void a(int i) {
        this.e = i;
        this.c = 0;
    }

    private final AdPage g() {
        AdPage adPage = null;
        AdPage adPage2 = null;
        if (this.b == null) {
            return adPage2;
        }
        ArrayList arrayList = this.b;
        if (arrayList != null && arrayList.isEmpty()) {
            return adPage2;
        }
        ArrayList arrayList2 = this.b;
        adPage2 = arrayList2 != null ? (AdPage) arrayList2.get(0) : null;
        if ((adPage2 != null ? adPage2.getWeight() : 0) > 0) {
            arrayList2 = this.b;
            if (arrayList2 != null) {
                adPage = (AdPage) arrayList2.get(ps.a((List) this.b));
            }
            return adPage;
        }
        int i = this.c;
        ArrayList arrayList3 = this.b;
        if (i >= (arrayList3 != null ? arrayList3.size() : 0)) {
            this.c = 0;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("adPageIndex = ");
        stringBuilder.append(this.c);
        o.b("papa视频", stringBuilder.toString());
        arrayList2 = this.b;
        if (arrayList2 != null) {
            adPage = (AdPage) arrayList2.get(this.c);
        }
        adPage2 = adPage;
        this.c++;
        return adPage2;
    }

    public void b(int i) {
        if (ps.a(g()) != null && i != 0) {
            while (((this.d * this.e) + this.e) - 1 < i + 1) {
                a aVar = (a) a();
                if (aVar != null) {
                    aVar.a(z.a(kotlin.c.a("num", Integer.valueOf(r1)), kotlin.c.a("post", r0)));
                }
                this.e++;
            }
        }
    }

    public void a(Map<String, Object> map) {
        f.b(map, "paramsMap");
        a aVar = (a) a();
        of.a.a().a((Map) map).compose(y.a()).compose((q) y.a(aVar != null ? aVar.a() : null)).subscribe((r) new n(this));
    }

    public void b(Map<String, Object> map) {
        f.b(map, "paramsMap");
        a aVar = (a) a();
        of.a.a().b((Map) map).compose((q) y.a(aVar != null ? aVar.a() : null)).compose(y.a()).subscribe((r) new i(this));
    }

    public void c(Map<String, Object> map) {
        f.b(map, "paramsMap");
        a aVar = (a) a();
        of.a.a().c((Map) map).compose(y.a()).compose((q) y.a(aVar != null ? aVar.a() : null)).subscribe((r) new j(this));
    }

    public final void b(PostList postList) {
        if (postList != null) {
            Object page = postList.getPage();
            ps psVar = this.a;
            if (psVar != null) {
                f.a(page, "page");
                psVar.a(false, page.getAdType(), page.getAdJumpModule(), page.getAdJumpDetail(), page.getAdLinkType(), page.getAdLink());
            }
            h.a(postList.getId(), 2);
        }
    }

    public final void c(PostList postList) {
        if (postList != null) {
            a aVar = (a) a();
            of.a.a().a(g.d(), postList.getId()).compose(y.a()).compose((q) y.a(aVar != null ? aVar.a() : null)).subscribe((r) new r(this, postList));
        }
    }

    public final void a(PostList postList, int i) {
        if (postList != null) {
            of.a.a().c(g.d(), postList.getMemberId(), i).compose(y.a()).compose((q) y.a(c())).doOnSubscribe(new c(this, postList, i)).subscribe((r) new d(this, postList, i));
        }
    }

    public final void d(PostList postList) {
        if (postList != null) {
            of.a.a().b(g.d(), postList.getId()).compose(y.a()).compose((q) y.a(c())).doOnSubscribe(new a(this, postList)).subscribe((wl) new b(this, postList));
        }
    }

    public final void f() {
        of.a.a().d(2).compose(y.a()).compose((q) y.a(c())).subscribe((wl) new k(this));
    }

    public final void d(Map<String, Object> map) {
        f.b(map, "paramsMap");
        of.a.a().d((Map) map).compose(y.a()).subscribe((r) new l(this));
    }

    public final void e(Map<String, Object> map) {
        f.b(map, "paramsMap");
        of.a.a().e((Map) map).compose(y.a()).compose((q) y.a(c())).subscribe((r) new o(this));
    }

    public final void f(Map<String, Object> map) {
        f.b(map, "paramsMap");
        of.a.a().f((Map) map).compose(y.a()).compose((q) y.a(c())).subscribe((r) new q(this));
    }

    public final void g(Map<String, Object> map) {
        f.b(map, "paramsMap");
        of.a.a().g(map).compose(y.a()).compose((q) y.a(c())).subscribe((r) new e(this));
    }

    public final void h(Map<String, Object> map) {
        f.b(map, "paramsMap");
        of.a.a().h(map).compose(y.a()).compose((q) y.a(c())).subscribe((r) new f(this));
    }

    public final void i(Map<String, Object> map) {
        f.b(map, "paramsMap");
        of.a.a().i(map).compose(y.a()).compose((q) y.a(c())).subscribe((r) new m(this));
    }

    public final void j(Map<String, Object> map) {
        f.b(map, "paramsMap");
        of.a.a().j(map).compose(y.a()).compose((q) y.a(c())).subscribe((r) new p(this));
    }

    public final void a(int i, int i2, int i3) {
        of.a.a().c(i, i2).compose(y.a()).compose((q) y.a(c())).doOnSubscribe(new g(this)).subscribe((r) new h(this, i3));
    }
}
