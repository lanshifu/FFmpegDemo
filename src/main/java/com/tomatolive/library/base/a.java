package com.tomatolive.library.base;

import android.content.Context;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.ApiService;
import com.tomatolive.library.http.HttpRxObservable;
import com.trello.rxlifecycle2.LifecycleProvider;
import io.reactivex.k;
import io.reactivex.r;
import java.lang.ref.WeakReference;

/* compiled from: BasePresenter */
public class a<V> {
    protected ApiService mApiService = ApiRetrofit.getInstance().getApiService();
    protected WeakReference<Context> mContextRef;
    protected WeakReference<V> mViewRef;

    public a(Context context, V v) {
        attachView(context, v);
    }

    public void attachView(Context context, V v) {
        this.mContextRef = new WeakReference(context);
        this.mViewRef = new WeakReference(v);
    }

    public void detachView() {
        if (this.mContextRef != null) {
            this.mContextRef.clear();
            this.mContextRef = null;
        }
        if (this.mViewRef != null) {
            this.mViewRef.clear();
            this.mViewRef = null;
        }
    }

    public boolean isAttached() {
        return (this.mViewRef == null || this.mViewRef.get() == null) ? false : true;
    }

    /* Access modifiers changed, original: protected */
    public Context getContext() {
        return this.mContextRef != null ? (Context) this.mContextRef.get() : null;
    }

    /* Access modifiers changed, original: protected */
    public V getView() {
        return this.mViewRef != null ? this.mViewRef.get() : null;
    }

    public LifecycleProvider getLifecycleProvider() {
        Object view = getView();
        return (view == null || !(view instanceof LifecycleProvider)) ? null : (LifecycleProvider) view;
    }

    public boolean isApiService() {
        return this.mApiService != null;
    }

    public void addMapSubscription(k kVar, r rVar) {
        if (isAttached()) {
            HttpRxObservable.getObservable(kVar, getLifecycleProvider()).subscribe(rVar);
        }
    }

    public void addMapSubscription(k kVar, r rVar, int i, int i2) {
        if (isAttached()) {
            HttpRxObservable.getObservable(kVar, getLifecycleProvider(), i, i2).subscribe(rVar);
        }
    }
}
