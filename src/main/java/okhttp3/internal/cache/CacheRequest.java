package okhttp3.internal.cache;

import java.io.IOException;
import okio.q;

public interface CacheRequest {
    void abort();

    q body() throws IOException;
}
