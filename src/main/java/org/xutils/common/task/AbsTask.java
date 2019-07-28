package org.xutils.common.task;

import java.util.concurrent.Executor;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;

public abstract class AbsTask<ResultType> implements Cancelable {
    private b a;
    private final Cancelable b;
    private volatile boolean c;
    private volatile State d;
    private ResultType e;

    public enum State {
        IDLE(0),
        WAITING(1),
        STARTED(2),
        SUCCESS(3),
        CANCELLED(4),
        ERROR(5);
        
        private final int value;

        private State(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }

    /* Access modifiers changed, original: protected */
    public void cancelWorks() {
    }

    public abstract ResultType doBackground() throws Throwable;

    public Executor getExecutor() {
        return null;
    }

    public Priority getPriority() {
        return null;
    }

    /* Access modifiers changed, original: protected */
    public boolean isCancelFast() {
        return false;
    }

    /* Access modifiers changed, original: protected */
    public void onCancelled(CancelledException cancelledException) {
    }

    public abstract void onError(Throwable th, boolean z);

    /* Access modifiers changed, original: protected */
    public void onFinished() {
    }

    /* Access modifiers changed, original: protected */
    public void onStarted() {
    }

    public abstract void onSuccess(ResultType resultType);

    /* Access modifiers changed, original: protected|varargs */
    public void onUpdate(int i, Object... objArr) {
    }

    /* Access modifiers changed, original: protected */
    public void onWaiting() {
    }

    public AbsTask() {
        this(null);
    }

    public AbsTask(Cancelable cancelable) {
        this.a = null;
        this.c = false;
        this.d = State.IDLE;
        this.b = cancelable;
    }

    /* Access modifiers changed, original: protected|final|varargs */
    public final void update(int i, Object... objArr) {
        if (this.a != null) {
            this.a.onUpdate(i, objArr);
        }
    }

    public final synchronized void cancel() {
        if (!this.c) {
            this.c = true;
            cancelWorks();
            if (!(this.b == null || this.b.isCancelled())) {
                this.b.cancel();
            }
            if (this.d == State.WAITING || (this.d == State.STARTED && isCancelFast())) {
                if (this.a != null) {
                    this.a.onCancelled(new CancelledException("cancelled by user"));
                    this.a.onFinished();
                } else if (this instanceof b) {
                    onCancelled(new CancelledException("cancelled by user"));
                    onFinished();
                }
            }
        }
    }

    public final boolean isCancelled() {
        return this.c || this.d == State.CANCELLED || (this.b != null && this.b.isCancelled());
    }

    public final boolean isFinished() {
        return this.d.value() > State.STARTED.value();
    }

    public final State getState() {
        return this.d;
    }

    public final ResultType getResult() {
        return this.e;
    }

    /* Access modifiers changed, original: 0000 */
    public void a(State state) {
        this.d = state;
    }

    /* Access modifiers changed, original: final */
    public final void a(b bVar) {
        this.a = bVar;
    }

    /* Access modifiers changed, original: final */
    public final void a(ResultType resultType) {
        this.e = resultType;
    }
}
