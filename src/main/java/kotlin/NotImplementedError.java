package kotlin;

import kotlin.jvm.internal.f;

/* compiled from: Standard.kt */
public final class NotImplementedError extends Error {
    public NotImplementedError() {
        this(null, 1, null);
    }

    public NotImplementedError(String str) {
        f.b(str, "message");
        super(str);
    }

    public /* synthetic */ NotImplementedError(String str, int i, d dVar) {
        if ((i & 1) != 0) {
            str = "An operation is not implemented.";
        }
        this(str);
    }
}
