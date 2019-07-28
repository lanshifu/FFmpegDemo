package defpackage;

/* compiled from: TotalSizeLruDiskUsage */
/* renamed from: eq */
public class eq extends en {
    private final long a;

    public eq(long j) {
        if (j > 0) {
            this.a = j;
            return;
        }
        throw new IllegalArgumentException("Max size must be positive number!");
    }
}
