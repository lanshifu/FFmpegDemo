package defpackage;

/* compiled from: ExponentialBackoff */
/* renamed from: vl */
public class vl implements vj {
    private final long a;
    private final int b;

    public vl(long j, int i) {
        this.a = j;
        this.b = i;
    }

    public long a(int i) {
        double d = (double) this.a;
        double pow = Math.pow((double) this.b, (double) i);
        Double.isNaN(d);
        return (long) (d * pow);
    }
}
