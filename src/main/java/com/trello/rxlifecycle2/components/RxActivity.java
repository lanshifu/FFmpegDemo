package com.trello.rxlifecycle2.components;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import io.reactivex.k;
import io.reactivex.subjects.a;

public abstract class RxActivity extends Activity implements LifecycleProvider<ActivityEvent> {
    private final a<ActivityEvent> lifecycleSubject = a.a();

    @CheckResult
    @NonNull
    public final k<ActivityEvent> lifecycle() {
        return this.lifecycleSubject.hide();
    }

    @CheckResult
    @NonNull
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent activityEvent) {
        return RxLifecycle.bindUntilEvent(this.lifecycleSubject, activityEvent);
    }

    @CheckResult
    @NonNull
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(this.lifecycleSubject);
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.lifecycleSubject.onNext(ActivityEvent.CREATE);
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void onStart() {
        super.onStart();
        this.lifecycleSubject.onNext(ActivityEvent.START);
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void onResume() {
        super.onResume();
        this.lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void onPause() {
        this.lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void onStop() {
        this.lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    /* Access modifiers changed, original: protected */
    @CallSuper
    public void onDestroy() {
        this.lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
    }
}
