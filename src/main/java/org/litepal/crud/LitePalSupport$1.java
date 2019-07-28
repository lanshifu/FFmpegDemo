package org.litepal.crud;

import org.litepal.Operator;
import org.litepal.crud.async.UpdateOrDeleteExecutor;

class LitePalSupport$1 implements Runnable {
    final /* synthetic */ LitePalSupport this$0;
    final /* synthetic */ UpdateOrDeleteExecutor val$executor;

    LitePalSupport$1(LitePalSupport litePalSupport, UpdateOrDeleteExecutor updateOrDeleteExecutor) {
        this.this$0 = litePalSupport;
        this.val$executor = updateOrDeleteExecutor;
    }

    public void run() {
        synchronized (LitePalSupport.class) {
            final int delete = this.this$0.delete();
            if (this.val$executor.getListener() != null) {
                Operator.getHandler().post(new Runnable() {
                    public void run() {
                        LitePalSupport$1.this.val$executor.getListener().onFinish(delete);
                    }
                });
            }
        }
    }
}
