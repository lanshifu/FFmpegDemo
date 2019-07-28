package com.tomatolive.library.http;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.blankj.utilcode.util.o;
import com.tomatolive.library.http.exception.ApiException;
import com.tomatolive.library.service.TokenDialogService;
import com.tomatolive.library.ui.activity.live.PrepareLiveActivity;
import com.tomatolive.library.ui.activity.live.TomatoLiveActivity;
import com.tomatolive.library.ui.view.dialog.LoadingDialog;
import com.tomatolive.library.ui.view.dialog.TokenInvalidDialog;
import com.tomatolive.library.ui.view.widget.StateView;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.z;
import io.reactivex.r;
import java.lang.ref.WeakReference;

public class HttpRxObserver<T> implements r<T> {
    private ResultCallBack callBack;
    private WeakReference<Context> context;
    private LoadingDialog dialog;
    private boolean isShowLoadingLayout = true;
    private int progressType = 0;
    private StateView stateLayout;

    public HttpRxObserver(Context context, ResultCallBack<T> resultCallBack) {
        this.context = new WeakReference(context);
        this.callBack = resultCallBack;
    }

    public HttpRxObserver(Context context, ResultCallBack<T> resultCallBack, boolean z) {
        this.context = new WeakReference(context);
        this.callBack = resultCallBack;
        if (z) {
            initProgressDialog();
        }
    }

    public HttpRxObserver(Context context, ResultCallBack<T> resultCallBack, String str) {
        this.context = new WeakReference(context);
        this.callBack = resultCallBack;
        initProgressDialog(str);
    }

    public HttpRxObserver(Context context, ResultCallBack<T> resultCallBack, StateView stateView, boolean z) {
        this.context = new WeakReference(context);
        this.callBack = resultCallBack;
        this.stateLayout = stateView;
        this.progressType = 1;
        this.isShowLoadingLayout = z;
    }

    public void onError(Throwable th) {
        th.printStackTrace();
        try {
            ApiException apiException = (ApiException) th;
            if (this.callBack != null) {
                this.callBack.onError(apiException.getCode(), apiException.getMsg());
            }
            if (apiException.getCode() == 101001) {
                showStateLayoutContent();
                dismissProgressDialog();
                Context context = (Context) this.context.get();
                if (context != null && (context instanceof FragmentActivity)) {
                    if (context instanceof PrepareLiveActivity) {
                        b.a(context, TokenDialogService.class);
                        ((PrepareLiveActivity) context).finish();
                        return;
                    } else if (context instanceof TomatoLiveActivity) {
                        b.a(context, TokenDialogService.class);
                        ((TomatoLiveActivity) context).finish();
                        return;
                    } else if (!TextUtils.isEmpty(z.a().b())) {
                        TokenInvalidDialog.newInstance().show(((FragmentActivity) context).getSupportFragmentManager());
                    }
                }
                return;
            }
            showErrorView();
            if (!(apiException.getCode() == 30000 || apiException.getCode() == 30001 || apiException.getCode() == 200023)) {
                if (apiException.getCode() != 200002) {
                    if (!TextUtils.isEmpty(apiException.getMsg())) {
                        o.a(apiException.getMsg());
                    }
                }
            }
        } catch (Exception unused) {
            onComplete();
        }
    }

    public void onComplete() {
        if (this.progressType == 0) {
            dismissProgressDialog();
        } else {
            showStateLayoutContent();
        }
    }

    public void onNext(T t) {
        if (this.callBack != null) {
            this.callBack.onSuccess(t);
        }
    }

    public void onSubscribe(io.reactivex.disposables.b bVar) {
        if (this.progressType == 0) {
            showProgressDialog();
        } else {
            showStateLayoutLoading();
        }
    }

    private void initProgressDialog() {
        Context context = (Context) this.context.get();
        if (context != null && this.dialog == null) {
            this.dialog = new LoadingDialog(context);
        }
    }

    private void initProgressDialog(String str) {
        Context context = (Context) this.context.get();
        if (context != null && this.dialog == null) {
            this.dialog = new LoadingDialog(context, str);
        }
    }

    private void showProgressDialog() {
        Context context = (Context) this.context.get();
        if (context != null) {
            try {
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;
                    if (!activity.isDestroyed()) {
                        if (!activity.isFinishing()) {
                            if (!(this.dialog == null || this.dialog.isShowing())) {
                                this.dialog.show();
                            }
                            return;
                        }
                    }
                    return;
                }
                if (!(this.dialog == null || this.dialog.isShowing())) {
                    this.dialog.show();
                }
            } catch (Exception unused) {
            }
        }
    }

    private void dismissProgressDialog() {
        try {
            if (this.dialog != null && this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARNING: Missing block: B:7:0x0019, code skipped:
            return;
     */
    private void showStateLayoutLoading() {
        /*
        r2 = this;
        r0 = r2.context;
        r0 = r0.get();
        r0 = (android.content.Context) r0;
        r1 = r2.stateLayout;
        if (r1 == 0) goto L_0x0019;
    L_0x000c:
        if (r0 != 0) goto L_0x000f;
    L_0x000e:
        goto L_0x0019;
    L_0x000f:
        r0 = r2.isShowLoadingLayout;
        if (r0 == 0) goto L_0x0018;
    L_0x0013:
        r0 = r2.stateLayout;
        r0.showLoading();
    L_0x0018:
        return;
    L_0x0019:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.http.HttpRxObserver.showStateLayoutLoading():void");
    }

    private void showStateLayoutContent() {
        Context context = (Context) this.context.get();
        if (this.stateLayout != null && context != null) {
            this.stateLayout.showContent();
        }
    }

    private void showStateLayoutError() {
        Context context = (Context) this.context.get();
        if (this.stateLayout != null && context != null) {
            this.stateLayout.showRetry();
        }
    }

    private void showErrorView() {
        if (this.progressType == 0) {
            dismissProgressDialog();
        } else {
            showStateLayoutError();
        }
    }
}
