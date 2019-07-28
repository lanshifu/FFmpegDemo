package org.xutils.common.task;

import android.os.Looper;
import java.util.concurrent.atomic.AtomicInteger;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CancelledException;
import org.xutils.common.Callback.GroupCallback;
import org.xutils.common.TaskController;
import org.xutils.common.util.LogUtil;
import org.xutils.x.Ext;

public final class TaskControllerImpl implements TaskController {
    private static volatile TaskController a;

    private TaskControllerImpl() {
    }

    public static void registerInstance() {
        if (a == null) {
            synchronized (TaskController.class) {
                if (a == null) {
                    a = new TaskControllerImpl();
                }
            }
        }
        Ext.setTaskController(a);
    }

    public <T> AbsTask<T> start(AbsTask<T> absTask) {
        b bVar;
        if (absTask instanceof b) {
            bVar = (b) absTask;
        } else {
            bVar = new b(absTask);
        }
        try {
            bVar.doBackground();
        } catch (Throwable th) {
            LogUtil.e(th.getMessage(), th);
        }
        return bVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x001a A:{ExcHandler: Throwable (r0_5 'th' java.lang.Throwable), Splitter:B:1:0x0001} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:6:0x0013, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:7:0x0014, code skipped:
            r2 = r1;
            r1 = r0;
            r0 = r2;
     */
    /* JADX WARNING: Missing block: B:9:0x001a, code skipped:
            r0 = move-exception;
     */
    /* JADX WARNING: Missing block: B:12:?, code skipped:
            r4.onError(r0, false);
     */
    /* JADX WARNING: Missing block: B:17:0x0028, code skipped:
            r4.onFinished();
     */
    public <T> T startSync(org.xutils.common.task.AbsTask<T> r4) throws java.lang.Throwable {
        /*
        r3 = this;
        r0 = 0;
        r4.onWaiting();	 Catch:{ CancelledException -> 0x0020, Throwable -> 0x001a }
        r4.onStarted();	 Catch:{ CancelledException -> 0x0020, Throwable -> 0x001a }
        r1 = r4.doBackground();	 Catch:{ CancelledException -> 0x0020, Throwable -> 0x001a }
        r4.onSuccess(r1);	 Catch:{ CancelledException -> 0x0013, Throwable -> 0x001a }
        r4.onFinished();
        r0 = r1;
        goto L_0x0027;
    L_0x0013:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r2;
        goto L_0x0021;
    L_0x0018:
        r0 = move-exception;
        goto L_0x0028;
    L_0x001a:
        r0 = move-exception;
        r1 = 0;
        r4.onError(r0, r1);	 Catch:{ all -> 0x0018 }
        throw r0;	 Catch:{ all -> 0x0018 }
    L_0x0020:
        r1 = move-exception;
    L_0x0021:
        r4.onCancelled(r1);	 Catch:{ all -> 0x0018 }
        r4.onFinished();
    L_0x0027:
        return r0;
    L_0x0028:
        r4.onFinished();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.common.task.TaskControllerImpl.startSync(org.xutils.common.task.AbsTask):java.lang.Object");
    }

    public <T extends AbsTask<?>> Cancelable startTasks(final GroupCallback<T> groupCallback, final T... tArr) {
        if (tArr != null) {
            AnonymousClass1 anonymousClass1 = new Runnable() {
                private final int d = tArr.length;
                private final AtomicInteger e = new AtomicInteger(0);

                public void run() {
                    if (this.e.incrementAndGet() == this.d && groupCallback != null) {
                        groupCallback.onAllFinished();
                    }
                }
            };
            for (final AbsTask absTask : tArr) {
                final GroupCallback<T> groupCallback2 = groupCallback;
                final AnonymousClass1 anonymousClass12 = anonymousClass1;
                start(new b(absTask) {
                    /* Access modifiers changed, original: protected */
                    public void onSuccess(Object obj) {
                        super.onSuccess(obj);
                        TaskControllerImpl.this.post(new Runnable() {
                            public void run() {
                                if (groupCallback2 != null) {
                                    groupCallback2.onSuccess(absTask);
                                }
                            }
                        });
                    }

                    /* Access modifiers changed, original: protected */
                    public void onCancelled(final CancelledException cancelledException) {
                        super.onCancelled(cancelledException);
                        TaskControllerImpl.this.post(new Runnable() {
                            public void run() {
                                if (groupCallback2 != null) {
                                    groupCallback2.onCancelled(absTask, cancelledException);
                                }
                            }
                        });
                    }

                    /* Access modifiers changed, original: protected */
                    public void onError(final Throwable th, final boolean z) {
                        super.onError(th, z);
                        TaskControllerImpl.this.post(new Runnable() {
                            public void run() {
                                if (groupCallback2 != null) {
                                    groupCallback2.onError(absTask, th, z);
                                }
                            }
                        });
                    }

                    /* Access modifiers changed, original: protected */
                    public void onFinished() {
                        super.onFinished();
                        TaskControllerImpl.this.post(new Runnable() {
                            public void run() {
                                if (groupCallback2 != null) {
                                    groupCallback2.onFinished(absTask);
                                }
                                anonymousClass12.run();
                            }
                        });
                    }
                });
            }
            return new Cancelable() {
                public void cancel() {
                    for (AbsTask cancel : tArr) {
                        cancel.cancel();
                    }
                }

                public boolean isCancelled() {
                    boolean z = true;
                    for (AbsTask isCancelled : tArr) {
                        if (!isCancelled.isCancelled()) {
                            z = false;
                        }
                    }
                    return z;
                }
            };
        }
        throw new IllegalArgumentException("task must not be null");
    }

    public void autoPost(Runnable runnable) {
        if (runnable != null) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                runnable.run();
            } else {
                b.e.post(runnable);
            }
        }
    }

    public void post(Runnable runnable) {
        if (runnable != null) {
            b.e.post(runnable);
        }
    }

    public void postDelayed(Runnable runnable, long j) {
        if (runnable != null) {
            b.e.postDelayed(runnable, j);
        }
    }

    public void run(Runnable runnable) {
        if (b.f.isBusy()) {
            new Thread(runnable).start();
        } else {
            b.f.execute(runnable);
        }
    }

    public void removeCallbacks(Runnable runnable) {
        b.e.removeCallbacks(runnable);
    }
}
