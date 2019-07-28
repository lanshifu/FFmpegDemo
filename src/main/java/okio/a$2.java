package okio;

import java.io.IOException;

/* compiled from: AsyncTimeout */
class a$2 implements r {
    final /* synthetic */ r a;
    final /* synthetic */ a b;

    a$2(a aVar, r rVar) {
        this.b = aVar;
        this.a = rVar;
    }

    public long read(c cVar, long j) throws IOException {
        this.b.enter();
        try {
            long read = this.a.read(cVar, j);
            this.b.exit(true);
            return read;
        } catch (IOException e) {
            throw this.b.exit(e);
        } catch (Throwable th) {
            this.b.exit(false);
        }
    }

    public void close() throws IOException {
        try {
            this.a.close();
            this.b.exit(true);
        } catch (IOException e) {
            throw this.b.exit(e);
        } catch (Throwable th) {
            this.b.exit(false);
        }
    }

    public s timeout() {
        return this.b;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AsyncTimeout.source(");
        stringBuilder.append(this.a);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
