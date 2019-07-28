package org.xutils.common.task;

/* compiled from: PriorityRunnable */
class a implements Runnable {
    long a;
    public final Priority b;
    private final Runnable c;

    public a(Priority priority, Runnable runnable) {
        if (priority == null) {
            priority = Priority.DEFAULT;
        }
        this.b = priority;
        this.c = runnable;
    }

    public final void run() {
        this.c.run();
    }
}
