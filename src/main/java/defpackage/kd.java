package defpackage;

import com.google.android.exoplayer2.Format;
import java.util.Collections;
import java.util.List;

/* compiled from: HlsMasterPlaylist */
/* renamed from: kd */
public final class kd extends kf {
    public final List<a> a;
    public final List<a> b;
    public final List<a> c;
    public final Format d;
    public final List<Format> e;

    /* compiled from: HlsMasterPlaylist */
    /* renamed from: kd$a */
    public static final class a {
        public final String a;
        public final Format b;

        public a(String str, Format format) {
            this.a = str;
            this.b = format;
        }
    }

    public kd(String str, List<String> list, List<a> list2, List<a> list3, List<a> list4, Format format, List<Format> list5) {
        super(str, list);
        this.a = Collections.unmodifiableList(list2);
        this.b = Collections.unmodifiableList(list3);
        this.c = Collections.unmodifiableList(list4);
        this.d = format;
        this.e = list5 != null ? Collections.unmodifiableList(list5) : null;
    }
}
