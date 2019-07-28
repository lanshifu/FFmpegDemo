package io.fabric.sdk.android.services.concurrency;

public enum Priority {
    LOW,
    NORMAL,
    HIGH,
    IMMEDIATE;

    static <Y> int compareTo(f fVar, Y y) {
        Priority b;
        if (y instanceof f) {
            b = ((f) y).b();
        } else {
            b = NORMAL;
        }
        return b.ordinal() - fVar.b().ordinal();
    }
}
