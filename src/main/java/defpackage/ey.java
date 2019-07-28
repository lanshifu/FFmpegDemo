package defpackage;

/* compiled from: StorageWatch */
/* renamed from: ey */
public class ey {
    private int a;
    private long b;
    private long c;
    private ew<Long> d;

    /* compiled from: StorageWatch */
    /* renamed from: ey$a */
    public static class a {
        private int a;
        private ff b;
        private fg c;

        public a(int i, ff ffVar, fg fgVar) {
            this.a = i;
            this.b = ffVar;
            this.c = fgVar;
        }

        public et a() {
            switch (this.a) {
                case 1:
                    return new eu(this.b, this.c);
                case 2:
                    return new fc(this.b, this.c);
                case 3:
                    return new ez(this.b, this.c);
                case 4:
                    return new fa(this.b, this.c);
                case 5:
                    return new fb(this.b, this.c);
                case 6:
                    return new fd(this.b, this.c);
                case 7:
                    return new fe(this.b, this.c);
                case 8:
                    return new ev(this.b, this.c);
                default:
                    return new eu(this.b, this.c);
            }
        }
    }

    public ey(int i, long j, long j2) {
        this.a = i;
        this.b = j;
        this.c = j;
        this.d = new ew(Long.valueOf(j), Long.valueOf(j2));
    }

    public int a() {
        return this.a;
    }

    public ew<Long> b() {
        return this.d;
    }
}
