package org.xutils.http;

import org.xutils.common.util.LogUtil;
import org.xutils.http.app.RequestTracker;
import org.xutils.http.request.UriRequest;

/* compiled from: RequestTrackerWrapper */
final class b implements RequestTracker {
    private final RequestTracker a;

    public b(RequestTracker requestTracker) {
        this.a = requestTracker;
    }

    public void onWaiting(RequestParams requestParams) {
        try {
            this.a.onWaiting(requestParams);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }

    public void onStart(RequestParams requestParams) {
        try {
            this.a.onStart(requestParams);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }

    public void onRequestCreated(UriRequest uriRequest) {
        try {
            this.a.onRequestCreated(uriRequest);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }

    public void onCache(UriRequest uriRequest, Object obj) {
        try {
            this.a.onCache(uriRequest, obj);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }

    public void onSuccess(UriRequest uriRequest, Object obj) {
        try {
            this.a.onSuccess(uriRequest, obj);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }

    public void onCancelled(UriRequest uriRequest) {
        try {
            this.a.onCancelled(uriRequest);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }

    public void onError(UriRequest uriRequest, Throwable th, boolean z) {
        try {
            this.a.onError(uriRequest, th, z);
        } catch (Throwable th2) {
            LogUtil.e(th2.getMessage(), th2);
        }
    }

    public void onFinished(UriRequest uriRequest) {
        try {
            this.a.onFinished(uriRequest);
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
    }
}
