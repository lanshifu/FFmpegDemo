package okio;

import java.io.IOException;

/* compiled from: ForwardingSource */
public abstract class h implements r {
    private final r delegate;

    public h(r rVar) {
        if (rVar != null) {
            this.delegate = rVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final r delegate() {
        return this.delegate;
    }

    public long read(c cVar, long j) throws IOException {
        return this.delegate.read(cVar, j);
    }

    public s timeout() {
        return this.delegate.timeout();
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append("(");
        stringBuilder.append(this.delegate.toString());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
