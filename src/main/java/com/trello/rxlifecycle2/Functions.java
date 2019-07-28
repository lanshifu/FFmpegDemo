package com.trello.rxlifecycle2;

import defpackage.wm;
import defpackage.wv;
import io.reactivex.a;
import java.util.concurrent.CancellationException;

final class Functions {
    static final wm<Object, a> CANCEL_COMPLETABLE = new wm<Object, a>() {
        public a apply(Object obj) throws Exception {
            return a.a(new CancellationException());
        }
    };
    static final wm<Throwable, Boolean> RESUME_FUNCTION = new wm<Throwable, Boolean>() {
        public Boolean apply(Throwable th) throws Exception {
            if (th instanceof OutsideLifecycleException) {
                return Boolean.valueOf(true);
            }
            io.reactivex.exceptions.a.a(th);
            return Boolean.valueOf(false);
        }
    };
    static final wv<Boolean> SHOULD_COMPLETE = new wv<Boolean>() {
        public boolean test(Boolean bool) throws Exception {
            return bool.booleanValue();
        }
    };

    private Functions() {
        throw new AssertionError("No instances!");
    }
}
