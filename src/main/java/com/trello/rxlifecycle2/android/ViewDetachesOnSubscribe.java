package com.trello.rxlifecycle2.android;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import defpackage.wb;
import io.reactivex.m;
import io.reactivex.n;

final class ViewDetachesOnSubscribe implements n<Object> {
    static final Object SIGNAL = new Object();
    final View view;

    class EmitterListener extends wb implements OnAttachStateChangeListener {
        final m<Object> emitter;

        public void onViewAttachedToWindow(View view) {
        }

        public EmitterListener(m<Object> mVar) {
            this.emitter = mVar;
        }

        public void onViewDetachedFromWindow(View view) {
            this.emitter.onNext(ViewDetachesOnSubscribe.SIGNAL);
        }

        /* Access modifiers changed, original: protected */
        public void onDispose() {
            ViewDetachesOnSubscribe.this.view.removeOnAttachStateChangeListener(this);
        }
    }

    public ViewDetachesOnSubscribe(View view) {
        this.view = view;
    }

    public void subscribe(m<Object> mVar) throws Exception {
        wb.verifyMainThread();
        EmitterListener emitterListener = new EmitterListener(mVar);
        mVar.setDisposable(emitterListener);
        this.view.addOnAttachStateChangeListener(emitterListener);
    }
}
