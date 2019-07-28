package defpackage;

import android.text.TextUtils;
import com.one.tomato.utils.u;
import io.reactivex.k;

/* compiled from: DomainCallback */
/* renamed from: oz */
public class oz implements pe {
    private static oz a;
    private int b = 0;
    private int c = 0;

    /* compiled from: DomainCallback */
    /* renamed from: oz$1 */
    class 1 implements wl<String> {
        1() {
        }

        /* renamed from: a */
        public void accept(String str) throws Exception {
            pm.a().b();
        }
    }

    private oz() {
    }

    public static oz a() {
        if (a == null) {
            synchronized (oz.class) {
                if (a == null) {
                    a = new oz();
                }
            }
        }
        return a;
    }

    public synchronized void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                this.b = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                this.b = 0;
            }
            if (this.b > u.a().a("domainVersion") && this.c != 1) {
                pc.a().a((pe) this);
                pb.c("server");
            }
        }
    }

    public void a(int i) {
        this.c = i;
        if (2 == this.c) {
            u.a().a("domainVersion", this.b);
            k.just("").observeOn(wd.a()).subscribe(new 1());
        }
    }
}
