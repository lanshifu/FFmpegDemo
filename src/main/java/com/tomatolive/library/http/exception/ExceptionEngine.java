package com.tomatolive.library.http.exception;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.tomatolive.library.R;
import com.tomatolive.library.utils.w;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import org.json.JSONException;
import retrofit2.HttpException;

public class ExceptionEngine {
    public static final int ANALYTIC_SERVER_DATA_ERROR = 1001;
    public static final int CONNECT_ERROR = 1003;
    public static final int SERVER_ERROR = 2000;
    public static final int TIME_OUT_ERROR = 1004;
    public static final int UN_KNOWN_ERROR = 1000;

    public static boolean isExceptionErrorCode(int i) {
        return i == 1000 || i == 1001 || i == 1003 || i == 1004 || i == SERVER_ERROR;
    }

    public static ApiException handleException(Throwable th) {
        ApiException apiException;
        if (th instanceof HttpException) {
            apiException = new ApiException(th, (int) SERVER_ERROR);
            apiException.setMsg(w.a(R.string.fq_net_poor_retry));
            return apiException;
        } else if (th instanceof ServerException) {
            th = (ServerException) th;
            apiException = new ApiException(th, th.getCode());
            apiException.setMsg(th.getMsg());
            return apiException;
        } else if ((th instanceof JsonParseException) || (th instanceof JSONException) || (th instanceof ParseException) || (th instanceof MalformedJsonException)) {
            apiException = new ApiException(th, 1001);
            apiException.setMsg("");
            return apiException;
        } else if (th instanceof ConnectException) {
            apiException = new ApiException(th, 1003);
            apiException.setMsg(w.a(R.string.fq_text_no_network));
            return apiException;
        } else if (th instanceof SocketTimeoutException) {
            apiException = new ApiException(th, 1004);
            apiException.setMsg(w.a(R.string.fq_net_timeout_retry));
            return apiException;
        } else {
            apiException = new ApiException(th, 1000);
            apiException.setMsg(w.a(R.string.fq_net_poor_retry));
            return apiException;
        }
    }
}
