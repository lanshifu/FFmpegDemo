package defpackage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: BaseSpringSystem */
/* renamed from: sj */
public class sj {
    private final Map<String, sm> a = new HashMap();
    private final Set<sm> b = new CopyOnWriteArraySet();
    private final sq c;
    private final CopyOnWriteArraySet<ss> d = new CopyOnWriteArraySet();
    private boolean e = true;

    public sj(sq sqVar) {
        if (sqVar != null) {
            this.c = sqVar;
            this.c.a(this);
            return;
        }
        throw new IllegalArgumentException("springLooper is required");
    }

    public boolean a() {
        return this.e;
    }

    public sm b() {
        sm smVar = new sm(this);
        a(smVar);
        return smVar;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(sm smVar) {
        if (smVar == null) {
            throw new IllegalArgumentException("spring is required");
        } else if (this.a.containsKey(smVar.a())) {
            throw new IllegalArgumentException("spring is already registered");
        } else {
            this.a.put(smVar.a(), smVar);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(double d) {
        for (sm smVar : this.b) {
            if (smVar.e()) {
                smVar.d(d / 1000.0d);
            } else {
                this.b.remove(smVar);
            }
        }
    }

    public void b(double d) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            ((ss) it.next()).a(this);
        }
        a(d);
        if (this.b.isEmpty()) {
            this.e = true;
        }
        Iterator it2 = this.d.iterator();
        while (it2.hasNext()) {
            ((ss) it2.next()).b(this);
        }
        if (this.e) {
            this.c.c();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        sm smVar = (sm) this.a.get(str);
        if (smVar != null) {
            this.b.add(smVar);
            if (a()) {
                this.e = false;
                this.c.b();
                return;
            }
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("springId ");
        stringBuilder.append(str);
        stringBuilder.append(" does not reference a registered spring");
        throw new IllegalArgumentException(stringBuilder.toString());
    }
}
