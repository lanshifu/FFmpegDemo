package org.litepal.crud.async;

public abstract class AsyncExecutor {
    private Runnable pendingTask;

    public void submit(Runnable runnable) {
        this.pendingTask = runnable;
    }

    /* Access modifiers changed, original: 0000 */
    public void execute() {
        if (this.pendingTask != null) {
            new Thread(this.pendingTask).start();
        }
    }
}
