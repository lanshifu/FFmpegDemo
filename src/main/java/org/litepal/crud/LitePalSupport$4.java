package org.litepal.crud;

import org.litepal.Operator;
import org.litepal.crud.async.SaveExecutor;

class LitePalSupport$4 implements Runnable {
    final /* synthetic */ LitePalSupport this$0;
    final /* synthetic */ SaveExecutor val$executor;

    LitePalSupport$4(LitePalSupport litePalSupport, SaveExecutor saveExecutor) {
        this.this$0 = litePalSupport;
        this.val$executor = saveExecutor;
    }

    public void run() {
        synchronized (LitePalSupport.class) {
            final boolean save = this.this$0.save();
            if (this.val$executor.getListener() != null) {
                Operator.getHandler().post(new Runnable() {
                    public void run() {
                        LitePalSupport$4.this.val$executor.getListener().onFinish(save);
                    }
                });
            }
        }
    }
}
