package defpackage;

import java.util.ArrayList;

/* compiled from: Animator */
/* renamed from: nh */
public abstract class nh implements Cloneable {
    ArrayList<a> a = null;

    /* compiled from: Animator */
    /* renamed from: nh$a */
    public interface a {
        void a(nh nhVar);

        void b(nh nhVar);

        void c(nh nhVar);

        void d(nh nhVar);
    }

    public void a() {
    }

    public void b() {
    }

    public void a(a aVar) {
        if (this.a == null) {
            this.a = new ArrayList();
        }
        this.a.add(aVar);
    }

    /* renamed from: c */
    public nh clone() {
        try {
            nh nhVar = (nh) super.clone();
            if (this.a != null) {
                ArrayList arrayList = this.a;
                nhVar.a = new ArrayList();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    nhVar.a.add(arrayList.get(i));
                }
            }
            return nhVar;
        } catch (CloneNotSupportedException unused) {
            throw new AssertionError();
        }
    }
}
