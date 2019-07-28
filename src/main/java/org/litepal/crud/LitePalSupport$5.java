package org.litepal.crud;

import org.litepal.Operator;
import org.litepal.crud.async.SaveExecutor;

class LitePalSupport$5 implements Runnable {
    final /* synthetic */ LitePalSupport this$0;
    final /* synthetic */ String[] val$conditions;
    final /* synthetic */ SaveExecutor val$executor;

    LitePalSupport$5(LitePalSupport litePalSupport, String[] strArr, SaveExecutor saveExecutor) {
        this.this$0 = litePalSupport;
        this.val$conditions = strArr;
        this.val$executor = saveExecutor;
    }

    public void run() {
        synchronized (LitePalSupport.class) {
            final boolean saveOrUpdate = this.this$0.saveOrUpdate(this.val$conditions);
            if (this.val$executor.getListener() != null) {
                Operator.getHandler().post(new Runnable() {
                    public void run() {
                        LitePalSupport$5.this.val$executor.getListener().onFinish(saveOrUpdate);
                    }
                });
            }
        }
    }
}
