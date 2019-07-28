package retrofit2;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.e;

final class OkHttpCall$NoContentResponseBody extends ResponseBody {
    private final long contentLength;
    private final MediaType contentType;

    OkHttpCall$NoContentResponseBody(MediaType mediaType, long j) {
        this.contentType = mediaType;
        this.contentLength = j;
    }

    public MediaType contentType() {
        return this.contentType;
    }

    public long contentLength() {
        return this.contentLength;
    }

    public e source() {
        throw new IllegalStateException("Cannot read raw response body of a converted body.");
    }
}
