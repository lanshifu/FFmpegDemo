package retrofit2;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.c;
import okio.e;
import okio.h;
import okio.l;

final class OkHttpCall$ExceptionCatchingRequestBody extends ResponseBody {
    private final ResponseBody delegate;
    IOException thrownException;

    OkHttpCall$ExceptionCatchingRequestBody(ResponseBody responseBody) {
        this.delegate = responseBody;
    }

    public MediaType contentType() {
        return this.delegate.contentType();
    }

    public long contentLength() {
        return this.delegate.contentLength();
    }

    public e source() {
        return l.a(new h(this.delegate.source()) {
            public long read(c cVar, long j) throws IOException {
                try {
                    return super.read(cVar, j);
                } catch (IOException e) {
                    OkHttpCall$ExceptionCatchingRequestBody.this.thrownException = e;
                    throw e;
                }
            }
        });
    }

    public void close() {
        this.delegate.close();
    }

    /* Access modifiers changed, original: 0000 */
    public void throwIfCaught() throws IOException {
        if (this.thrownException != null) {
            throw this.thrownException;
        }
    }
}
