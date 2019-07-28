package defpackage;

import io.reactivex.k;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.c;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: RxBus */
/* renamed from: nx */
public class nx {
    private static volatile nx a;
    private final c<Object> b = PublishSubject.a().b();
    private final Map<Class<?>, Object> c = new ConcurrentHashMap();

    public static nx a() {
        if (a == null) {
            synchronized (nx.class) {
                if (a == null) {
                    a = new nx();
                }
            }
        }
        return a;
    }

    public void a(Object obj) {
        this.b.onNext(obj);
    }

    public <T> k<T> a(Class<T> cls) {
        return this.b.ofType(cls);
    }
}
