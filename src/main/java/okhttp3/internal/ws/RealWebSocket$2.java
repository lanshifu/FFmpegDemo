package okhttp3.internal.ws;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;

class RealWebSocket$2 implements Callback {
    final /* synthetic */ RealWebSocket this$0;
    final /* synthetic */ Request val$request;

    RealWebSocket$2(RealWebSocket realWebSocket, Request request) {
        this.this$0 = realWebSocket;
        this.val$request = request;
    }

    public void onResponse(Call call, Response response) {
        try {
            this.this$0.checkResponse(response);
            StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
            streamAllocation.noNewStreams();
            RealWebSocket$Streams newWebSocketStreams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
            try {
                this.this$0.listener.onOpen(this.this$0, response);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("OkHttp WebSocket ");
                stringBuilder.append(this.val$request.url().redact());
                this.this$0.initReaderAndWriter(stringBuilder.toString(), newWebSocketStreams);
                streamAllocation.connection().socket().setSoTimeout(0);
                this.this$0.loopReader();
            } catch (Exception e) {
                this.this$0.failWebSocket(e, null);
            }
        } catch (ProtocolException e2) {
            this.this$0.failWebSocket(e2, response);
            Util.closeQuietly(response);
        }
    }

    public void onFailure(Call call, IOException iOException) {
        this.this$0.failWebSocket(iOException, null);
    }
}
