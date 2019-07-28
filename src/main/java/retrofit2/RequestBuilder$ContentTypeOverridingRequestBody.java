package retrofit2;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.d;

class RequestBuilder$ContentTypeOverridingRequestBody extends RequestBody {
    private final MediaType contentType;
    private final RequestBody delegate;

    RequestBuilder$ContentTypeOverridingRequestBody(RequestBody requestBody, MediaType mediaType) {
        this.delegate = requestBody;
        this.contentType = mediaType;
    }

    public MediaType contentType() {
        return this.contentType;
    }

    public long contentLength() throws IOException {
        return this.delegate.contentLength();
    }

    public void writeTo(d dVar) throws IOException {
        this.delegate.writeTo(dVar);
    }
}
