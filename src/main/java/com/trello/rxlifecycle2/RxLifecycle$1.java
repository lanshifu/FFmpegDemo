package com.trello.rxlifecycle2;

import defpackage.wv;

class RxLifecycle$1 implements wv<R> {
    final /* synthetic */ Object val$event;

    RxLifecycle$1(Object obj) {
        this.val$event = obj;
    }

    public boolean test(R r) throws Exception {
        return r.equals(this.val$event);
    }
}
