package org.litepal.crud;

import org.litepal.Operator;
import org.litepal.crud.async.UpdateOrDeleteExecutor;

class LitePalSupport$3 implements Runnable {
    final /* synthetic */ LitePalSupport this$0;
    final /* synthetic */ String[] val$conditions;
    final /* synthetic */ UpdateOrDeleteExecutor val$executor;

    LitePalSupport$3(LitePalSupport litePalSupport, String[] strArr, UpdateOrDeleteExecutor updateOrDeleteExecutor) {
        this.this$0 = litePalSupport;
        this.val$conditions = strArr;
        this.val$executor = updateOrDeleteExecutor;
    }

    public void run() {
        synchronized (LitePalSupport.class) {
            final int updateAll = this.this$0.updateAll(this.val$conditions);
            if (this.val$executor.getListener() != null) {
                Operator.getHandler().post(new Runnable() {
                    public void run() {
                        LitePalSupport$3.this.val$executor.getListener().onFinish(updateAll);
                    }
                });
            }
        }
    }
}
