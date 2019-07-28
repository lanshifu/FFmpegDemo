package com.trello.rxlifecycle2.android;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.OutsideLifecycleException;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.internal.Preconditions;
import defpackage.wm;
import io.reactivex.k;

public class RxLifecycleAndroid {
    private static final wm<ActivityEvent, ActivityEvent> ACTIVITY_LIFECYCLE = new wm<ActivityEvent, ActivityEvent>() {
        public ActivityEvent apply(ActivityEvent activityEvent) throws Exception {
            switch (activityEvent) {
                case CREATE:
                    return ActivityEvent.DESTROY;
                case START:
                    return ActivityEvent.STOP;
                case RESUME:
                    return ActivityEvent.PAUSE;
                case PAUSE:
                    return ActivityEvent.STOP;
                case STOP:
                    return ActivityEvent.DESTROY;
                case DESTROY:
                    throw new OutsideLifecycleException("Cannot bind to Activity lifecycle when outside of it.");
                default:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Binding to ");
                    stringBuilder.append(activityEvent);
                    stringBuilder.append(" not yet implemented");
                    throw new UnsupportedOperationException(stringBuilder.toString());
            }
        }
    };
    private static final wm<FragmentEvent, FragmentEvent> FRAGMENT_LIFECYCLE = new wm<FragmentEvent, FragmentEvent>() {
        public FragmentEvent apply(FragmentEvent fragmentEvent) throws Exception {
            switch (fragmentEvent) {
                case ATTACH:
                    return FragmentEvent.DETACH;
                case CREATE:
                    return FragmentEvent.DESTROY;
                case CREATE_VIEW:
                    return FragmentEvent.DESTROY_VIEW;
                case START:
                    return FragmentEvent.STOP;
                case RESUME:
                    return FragmentEvent.PAUSE;
                case PAUSE:
                    return FragmentEvent.STOP;
                case STOP:
                    return FragmentEvent.DESTROY_VIEW;
                case DESTROY_VIEW:
                    return FragmentEvent.DESTROY;
                case DESTROY:
                    return FragmentEvent.DETACH;
                case DETACH:
                    throw new OutsideLifecycleException("Cannot bind to Fragment lifecycle when outside of it.");
                default:
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Binding to ");
                    stringBuilder.append(fragmentEvent);
                    stringBuilder.append(" not yet implemented");
                    throw new UnsupportedOperationException(stringBuilder.toString());
            }
        }
    };

    private RxLifecycleAndroid() {
        throw new AssertionError("No instances");
    }

    @CheckResult
    @NonNull
    public static <T> LifecycleTransformer<T> bindActivity(@NonNull k<ActivityEvent> kVar) {
        return RxLifecycle.bind(kVar, ACTIVITY_LIFECYCLE);
    }

    @CheckResult
    @NonNull
    public static <T> LifecycleTransformer<T> bindFragment(@NonNull k<FragmentEvent> kVar) {
        return RxLifecycle.bind(kVar, FRAGMENT_LIFECYCLE);
    }

    @CheckResult
    @NonNull
    public static <T> LifecycleTransformer<T> bindView(@NonNull View view) {
        Preconditions.checkNotNull(view, "view == null");
        return RxLifecycle.bind(k.create(new ViewDetachesOnSubscribe(view)));
    }
}
