package org.litepal.crud;

import org.litepal.Operator;
import org.litepal.crud.async.UpdateOrDeleteExecutor;

class LitePalSupport$2 implements Runnable {
    final /* synthetic */ LitePalSupport this$0;
    final /* synthetic */ UpdateOrDeleteExecutor val$executor;
    final /* synthetic */ long val$id;

    LitePalSupport$2(LitePalSupport litePalSupport, long j, UpdateOrDeleteExecutor updateOrDeleteExecutor) {
        this.this$0 = litePalSupport;
        this.val$id = j;
        this.val$executor = updateOrDeleteExecutor;
    }

    public void run() {
        synchronized (LitePalSupport.class) {
            final int update = this.this$0.update(this.val$id);
            if (this.val$executor.getListener() != null) {
                Operator.getHandler().post(new Runnable() {
                    public void run() {
                        LitePalSupport$2.this.val$executor.getListener().onFinish(update);
                    }
                });
            }
        }
    }
}
