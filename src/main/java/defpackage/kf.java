package defpackage;

import java.util.Collections;
import java.util.List;

/* compiled from: HlsPlaylist */
/* renamed from: kf */
public abstract class kf {
    public final String o;
    public final List<String> p;

    protected kf(String str, List<String> list) {
        this.o = str;
        this.p = Collections.unmodifiableList(list);
    }
}
