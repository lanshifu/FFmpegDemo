package okhttp3.internal.connection;

import java.io.IOException;
import okhttp3.internal.ws.RealWebSocket$Streams;
import okio.d;
import okio.e;

class RealConnection$1 extends RealWebSocket$Streams {
    final /* synthetic */ RealConnection this$0;
    final /* synthetic */ StreamAllocation val$streamAllocation;

    RealConnection$1(RealConnection realConnection, boolean z, e eVar, d dVar, StreamAllocation streamAllocation) {
        this.this$0 = realConnection;
        this.val$streamAllocation = streamAllocation;
        super(z, eVar, dVar);
    }

    public void close() throws IOException {
        this.val$streamAllocation.streamFinished(true, this.val$streamAllocation.codec(), -1, null);
    }
}
