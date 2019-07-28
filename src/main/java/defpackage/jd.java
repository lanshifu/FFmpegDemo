package defpackage;

import android.util.SparseArray;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.util.n;
import com.google.android.exoplayer2.util.w;
import com.tomatolive.library.ui.view.widget.matisse.internal.ui.widget.CheckView;
import java.util.Collections;
import java.util.List;

/* compiled from: TsPayloadReader */
/* renamed from: jd */
public interface jd {

    /* compiled from: TsPayloadReader */
    /* renamed from: jd$a */
    public static final class a {
        public final String a;
        public final int b;
        public final byte[] c;

        public a(String str, int i, byte[] bArr) {
            this.a = str;
            this.b = i;
            this.c = bArr;
        }
    }

    /* compiled from: TsPayloadReader */
    /* renamed from: jd$b */
    public static final class b {
        public final int a;
        public final String b;
        public final List<a> c;
        public final byte[] d;

        public b(int i, String str, List<a> list, byte[] bArr) {
            List emptyList;
            this.a = i;
            this.b = str;
            if (list == null) {
                emptyList = Collections.emptyList();
            } else {
                emptyList = Collections.unmodifiableList(list);
            }
            this.c = emptyList;
            this.d = bArr;
        }
    }

    /* compiled from: TsPayloadReader */
    /* renamed from: jd$c */
    public interface c {
        SparseArray<jd> a();

        jd a(int i, b bVar);
    }

    /* compiled from: TsPayloadReader */
    /* renamed from: jd$d */
    public static final class d {
        private final String a;
        private final int b;
        private final int c;
        private int d;
        private String e;

        public d(int i, int i2) {
            this(CheckView.UNCHECKED, i, i2);
        }

        public d(int i, int i2, int i3) {
            String stringBuilder;
            if (i != CheckView.UNCHECKED) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(i);
                stringBuilder2.append("/");
                stringBuilder = stringBuilder2.toString();
            } else {
                stringBuilder = "";
            }
            this.a = stringBuilder;
            this.b = i2;
            this.c = i3;
            this.d = CheckView.UNCHECKED;
        }

        public void a() {
            this.d = this.d == CheckView.UNCHECKED ? this.b : this.d + this.c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append(this.d);
            this.e = stringBuilder.toString();
        }

        public int b() {
            d();
            return this.d;
        }

        public String c() {
            d();
            return this.e;
        }

        private void d() {
            if (this.d == CheckView.UNCHECKED) {
                throw new IllegalStateException("generateNewId() must be called before retrieving ids.");
            }
        }
    }

    void a();

    void a(n nVar, boolean z) throws ParserException;

    void a(w wVar, hb hbVar, d dVar);
}
