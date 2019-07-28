package retrofit2;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

class OkHttpCall$1 implements Callback {
    final /* synthetic */ OkHttpCall this$0;
    final /* synthetic */ Callback val$callback;

    OkHttpCall$1(OkHttpCall okHttpCall, Callback callback) {
        this.this$0 = okHttpCall;
        this.val$callback = callback;
    }

    public void onResponse(Call call, Response response) {
        try {
            try {
                this.val$callback.onResponse(this.this$0, this.this$0.parseResponse(response));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        } catch (Throwable th2) {
            callFailure(th2);
        }
    }

    public void onFailure(Call call, IOException iOException) {
        callFailure(iOException);
    }

    private void callFailure(Throwable th) {
        try {
            this.val$callback.onFailure(this.this$0, th);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }
}
