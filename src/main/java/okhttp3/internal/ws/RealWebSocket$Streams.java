package okhttp3.internal.ws;

import java.io.Closeable;
import okio.d;
import okio.e;

public abstract class RealWebSocket$Streams implements Closeable {
    public final boolean client;
    public final d sink;
    public final e source;

    public RealWebSocket$Streams(boolean z, e eVar, d dVar) {
        this.client = z;
        this.source = eVar;
        this.sink = dVar;
    }
}
