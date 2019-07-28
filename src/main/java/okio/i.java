package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* compiled from: ForwardingTimeout */
public class i extends s {
    private s a;

    public i(s sVar) {
        if (sVar != null) {
            this.a = sVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final s a() {
        return this.a;
    }

    public final i a(s sVar) {
        if (sVar != null) {
            this.a = sVar;
            return this;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public s timeout(long j, TimeUnit timeUnit) {
        return this.a.timeout(j, timeUnit);
    }

    public long timeoutNanos() {
        return this.a.timeoutNanos();
    }

    public boolean hasDeadline() {
        return this.a.hasDeadline();
    }

    public long deadlineNanoTime() {
        return this.a.deadlineNanoTime();
    }

    public s deadlineNanoTime(long j) {
        return this.a.deadlineNanoTime(j);
    }

    public s clearTimeout() {
        return this.a.clearTimeout();
    }

    public s clearDeadline() {
        return this.a.clearDeadline();
    }

    public void throwIfReached() throws IOException {
        this.a.throwIfReached();
    }
}
