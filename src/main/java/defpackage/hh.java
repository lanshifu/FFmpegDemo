package defpackage;

import android.support.annotation.Nullable;

/* compiled from: SeekMap */
/* renamed from: hh */
public interface hh {

    /* compiled from: SeekMap */
    /* renamed from: hh$a */
    public static final class a {
        public final hi a;
        public final hi b;

        public a(hi hiVar) {
            this(hiVar, hiVar);
        }

        public a(hi hiVar, hi hiVar2) {
            this.a = (hi) com.google.android.exoplayer2.util.a.a(hiVar);
            this.b = (hi) com.google.android.exoplayer2.util.a.a(hiVar2);
        }

        public String toString() {
            String str;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append(this.a);
            if (this.a.equals(this.b)) {
                str = "";
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(", ");
                stringBuilder2.append(this.b);
                str = stringBuilder2.toString();
            }
            stringBuilder.append(str);
            stringBuilder.append("]");
            return stringBuilder.toString();
        }

        public boolean equals(@Nullable Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (!(this.a.equals(aVar.a) && this.b.equals(aVar.b))) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return (this.a.hashCode() * 31) + this.b.hashCode();
        }
    }

    /* compiled from: SeekMap */
    /* renamed from: hh$b */
    public static final class b implements hh {
        private final long a;
        private final a b;

        public boolean a() {
            return false;
        }

        public b(long j) {
            this(j, 0);
        }

        public b(long j, long j2) {
            this.a = j;
            this.b = new a(j2 == 0 ? hi.a : new hi(0, j2));
        }

        public long b() {
            return this.a;
        }

        public a b(long j) {
            return this.b;
        }
    }

    boolean a();

    long b();

    a b(long j);
}
