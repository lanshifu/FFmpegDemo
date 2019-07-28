package com.tomatolive.library.http;

import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.http.utils.RetryWithDelayUtils;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import defpackage.wd;
import defpackage.xl;
import io.reactivex.k;

public class HttpRxObservable {
    public static k getObservable(k kVar) {
        return kVar.map(new ServerResultFunction()).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a());
    }

    public static k getObservable(k kVar, int i, int i2) {
        return kVar.map(new ServerResultFunction()).onErrorResumeNext(new HttpResultFunction()).retryWhen(new RetryWithDelayUtils(i, i2)).subscribeOn(xl.b()).observeOn(wd.a());
    }

    public static k getObservableBody(k kVar, LifecycleProvider lifecycleProvider) {
        if (lifecycleProvider != null) {
            return kVar.compose(lifecycleProvider.bindToLifecycle()).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a());
        }
        return getObservable(kVar);
    }

    public static k getObservable(k kVar, LifecycleProvider lifecycleProvider) {
        if (lifecycleProvider != null) {
            return kVar.map(new ServerResultFunction()).compose(lifecycleProvider.bindToLifecycle()).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a());
        }
        return getObservable(kVar);
    }

    public static k getObservable(k kVar, LifecycleProvider lifecycleProvider, int i, int i2) {
        if (lifecycleProvider != null) {
            return kVar.map(new ServerResultFunction()).compose(lifecycleProvider.bindToLifecycle()).onErrorResumeNext(new HttpResultFunction()).retryWhen(new RetryWithDelayUtils(i, i2)).subscribeOn(xl.b()).observeOn(wd.a());
        }
        return getObservable(kVar, i, i2);
    }

    public static k getObservable(k kVar, LifecycleProvider<ActivityEvent> lifecycleProvider, ActivityEvent activityEvent) {
        if (lifecycleProvider != null) {
            return kVar.map(new ServerResultFunction()).compose(lifecycleProvider.bindUntilEvent(activityEvent)).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a());
        }
        return getObservable(kVar);
    }

    public static k getObservable(k kVar, LifecycleProvider<FragmentEvent> lifecycleProvider, FragmentEvent fragmentEvent) {
        if (lifecycleProvider != null) {
            return kVar.map(new ServerResultFunction()).compose(lifecycleProvider.bindUntilEvent(fragmentEvent)).onErrorResumeNext(new HttpResultFunction()).subscribeOn(xl.b()).observeOn(wd.a());
        }
        return getObservable(kVar);
    }
}
