package okio;

import java.io.IOException;

/* compiled from: ForwardingSink */
public abstract class g implements q {
    private final q delegate;

    public g(q qVar) {
        if (qVar != null) {
            this.delegate = qVar;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final q delegate() {
        return this.delegate;
    }

    public void write(c cVar, long j) throws IOException {
        this.delegate.write(cVar, j);
    }

    public void flush() throws IOException {
        this.delegate.flush();
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
