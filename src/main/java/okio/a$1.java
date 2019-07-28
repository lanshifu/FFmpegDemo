package okio;

import java.io.IOException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: AsyncTimeout */
class a$1 implements q {
    final /* synthetic */ q a;
    final /* synthetic */ a b;

    a$1(a aVar, q qVar) {
        this.b = aVar;
        this.a = qVar;
    }

    public void write(c cVar, long j) throws IOException {
        t.a(cVar.b, 0, j);
        while (true) {
            long j2 = 0;
            if (j > 0) {
                o oVar = cVar.a;
                while (j2 < IjkMediaMeta.AV_CH_TOP_BACK_CENTER) {
                    j2 += (long) (oVar.c - oVar.b);
                    if (j2 >= j) {
                        j2 = j;
                        break;
                    }
                    oVar = oVar.f;
                }
                this.b.enter();
                try {
                    this.a.write(cVar, j2);
                    j -= j2;
                    this.b.exit(true);
                } catch (IOException e) {
                    throw this.b.exit(e);
                } catch (Throwable th) {
                    this.b.exit(false);
                }
            } else {
                return;
            }
        }
    }

    public void flush() throws IOException {
        this.b.enter();
        try {
            this.a.flush();
            this.b.exit(true);
        } catch (IOException e) {
            throw this.b.exit(e);
        } catch (Throwable th) {
            this.b.exit(false);
        }
    }

    public void close() throws IOException {
        this.b.enter();
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
        stringBuilder.append("AsyncTimeout.sink(");
        stringBuilder.append(this.a);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
