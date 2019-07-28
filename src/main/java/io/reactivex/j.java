package io.reactivex;

import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.NotificationLite;

/* compiled from: Notification */
public final class j<T> {
    static final j<Object> b = new j(null);
    final Object a;

    private j(Object obj) {
        this.a = obj;
    }

    public boolean a() {
        return this.a == null;
    }

    public boolean b() {
        return NotificationLite.isError(this.a);
    }

    public boolean c() {
        Object obj = this.a;
        return (obj == null || NotificationLite.isError(obj)) ? false : true;
    }

    public T d() {
        Object obj = this.a;
        return (obj == null || NotificationLite.isError(obj)) ? null : this.a;
    }

    public Throwable e() {
        Object obj = this.a;
        return NotificationLite.isError(obj) ? NotificationLite.getError(obj) : null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof j)) {
            return false;
        }
        return a.a(this.a, ((j) obj).a);
    }

    public int hashCode() {
        Object obj = this.a;
        return obj != null ? obj.hashCode() : 0;
    }

    public String toString() {
        Object obj = this.a;
        if (obj == null) {
            return "OnCompleteNotification";
        }
        if (NotificationLite.isError(obj)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("OnErrorNotification[");
            stringBuilder.append(NotificationLite.getError(obj));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("OnNextNotification[");
        stringBuilder2.append(this.a);
        stringBuilder2.append("]");
        return stringBuilder2.toString();
    }

    public static <T> j<T> a(T t) {
        a.a((Object) t, "value is null");
        return new j(t);
    }

    public static <T> j<T> a(Throwable th) {
        a.a((Object) th, "error is null");
        return new j(NotificationLite.error(th));
    }

    public static <T> j<T> f() {
        return b;
    }
}
