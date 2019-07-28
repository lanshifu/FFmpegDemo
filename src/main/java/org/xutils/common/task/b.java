package org.xutils.common.task;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.Executor;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.task.AbsTask.State;
import org.xutils.common.util.LogUtil;
import org.xutils.x;

/* compiled from: TaskProxy */
class b<ResultType> extends AbsTask<ResultType> {
    static final b e = new b();
    static final PriorityExecutor f = new PriorityExecutor(true);
    private final AbsTask<ResultType> a;
    private final Executor b;
    private volatile boolean c = false;
    private volatile boolean d = false;

    /* compiled from: TaskProxy */
    private static class a {
        final b a;
        final Object[] b;

        public a(b bVar, Object... objArr) {
            this.a = bVar;
            this.b = objArr;
        }
    }

    /* compiled from: TaskProxy */
    static final class b extends Handler {
        static final /* synthetic */ boolean a = (b.class.desiredAssertionStatus() ^ 1);

        /* synthetic */ b(AnonymousClass1 anonymousClass1) {
            this();
        }

        private b() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            if (message.obj != null) {
                Object[] objArr;
                b bVar = null;
                if (message.obj instanceof b) {
                    bVar = (b) message.obj;
                    objArr = null;
                } else if (message.obj instanceof a) {
                    a aVar = (a) message.obj;
                    bVar = aVar.a;
                    objArr = aVar.b;
                } else {
                    objArr = null;
                }
                if (bVar != null) {
                    Throwable th;
                    try {
                        switch (message.what) {
                            case 1000000001:
                                bVar.a.onWaiting();
                                break;
                            case 1000000002:
                                bVar.a.onStarted();
                                break;
                            case 1000000003:
                                bVar.a.onSuccess(bVar.getResult());
                                break;
                            case 1000000004:
                                if (!a) {
                                    if (objArr == null) {
                                        throw new AssertionError();
                                    }
                                }
                                th = (Throwable) objArr[0];
                                LogUtil.d(th.getMessage(), th);
                                bVar.a.onError(th, false);
                                break;
                            case 1000000005:
                                bVar.a.onUpdate(message.arg1, objArr);
                                break;
                            case 1000000006:
                                if (!bVar.c) {
                                    bVar.c = true;
                                    if (!a) {
                                        if (objArr == null) {
                                            throw new AssertionError();
                                        }
                                    }
                                    bVar.a.onCancelled((CancelledException) objArr[0]);
                                    break;
                                }
                                return;
                            case 1000000007:
                                if (!bVar.d) {
                                    bVar.d = true;
                                    bVar.a.onFinished();
                                    break;
                                }
                                return;
                            default:
                                break;
                        }
                    } catch (Throwable th2) {
                        bVar.a(State.ERROR);
                        if (message.what != 1000000004) {
                            bVar.a.onError(th2, true);
                        } else if (x.isDebug()) {
                            RuntimeException runtimeException = new RuntimeException(th2);
                        }
                    }
                    return;
                }
                throw new RuntimeException("msg.obj not instanceof TaskProxy");
            }
            throw new IllegalArgumentException("msg must not be null");
        }
    }

    b(AbsTask<ResultType> absTask) {
        super(absTask);
        this.a = absTask;
        this.a.a(this);
        a(null);
        Executor executor = absTask.getExecutor();
        if (executor == null) {
            executor = f;
        }
        this.b = executor;
    }

    /* Access modifiers changed, original: protected|final */
    public final ResultType doBackground() throws Throwable {
        onWaiting();
        this.b.execute(new a(this.a.getPriority(), new Runnable() {
            public void run() {
                try {
                    if (b.this.c || b.this.isCancelled()) {
                        throw new CancelledException("");
                    }
                    b.this.onStarted();
                    if (b.this.isCancelled()) {
                        throw new CancelledException("");
                    }
                    b.this.a.a(b.this.a.doBackground());
                    b.this.a(b.this.a.getResult());
                    if (b.this.isCancelled()) {
                        throw new CancelledException("");
                    }
                    b.this.onSuccess(b.this.a.getResult());
                    b.this.onFinished();
                } catch (CancelledException e) {
                    b.this.onCancelled(e);
                } catch (Throwable th) {
                    b.this.onFinished();
                }
            }
        }));
        return null;
    }

    /* Access modifiers changed, original: protected */
    public void onWaiting() {
        a(State.WAITING);
        e.obtainMessage(1000000001, this).sendToTarget();
    }

    /* Access modifiers changed, original: protected */
    public void onStarted() {
        a(State.STARTED);
        e.obtainMessage(1000000002, this).sendToTarget();
    }

    /* Access modifiers changed, original: protected */
    public void onSuccess(ResultType resultType) {
        a(State.SUCCESS);
        e.obtainMessage(1000000003, this).sendToTarget();
    }

    /* Access modifiers changed, original: protected */
    public void onError(Throwable th, boolean z) {
        a(State.ERROR);
        e.obtainMessage(1000000004, new a(this, th)).sendToTarget();
    }

    /* Access modifiers changed, original: protected|varargs */
    public void onUpdate(int i, Object... objArr) {
        e.obtainMessage(1000000005, i, i, new a(this, objArr)).sendToTarget();
    }

    /* Access modifiers changed, original: protected */
    public void onCancelled(CancelledException cancelledException) {
        a(State.CANCELLED);
        e.obtainMessage(1000000006, new a(this, cancelledException)).sendToTarget();
    }

    /* Access modifiers changed, original: protected */
    public void onFinished() {
        e.obtainMessage(1000000007, this).sendToTarget();
    }

    /* Access modifiers changed, original: final */
    public final void a(State state) {
        super.a(state);
        this.a.a(state);
    }

    public final Priority getPriority() {
        return this.a.getPriority();
    }

    public final Executor getExecutor() {
        return this.b;
    }
}
