package defpackage;

/* compiled from: Duration */
/* renamed from: zi */
public class zi implements Cloneable {
    public long a;
    private long b;
    private float c = 1.0f;

    public zi(long j) {
        this.b = j;
        this.a = j;
    }

    public void a(long j) {
        this.b = j;
        this.a = (long) (((float) this.b) * this.c);
    }

    public void a(float f) {
        if (this.c != f) {
            this.c = f;
            this.a = (long) (((float) this.b) * f);
        }
    }
}
