package com.tomatolive.library.http.utils;

import com.tomatolive.library.http.exception.ApiException;
import defpackage.wm;
import io.reactivex.k;
import io.reactivex.p;
import java.util.concurrent.TimeUnit;

public class RetryWithDelayUtils implements wm<k<? extends Throwable>, k<?>> {
    private int maxRetries;
    private int retryCount = 0;
    private int retryDelaySecond;

    public RetryWithDelayUtils(int i, int i2) {
        this.maxRetries = i;
        this.retryDelaySecond = i2;
    }

    public k<?> apply(k<? extends Throwable> kVar) {
        return kVar.flatMap(new -$$Lambda$RetryWithDelayUtils$ke03zLUb9sJHYfie2asOKI9Q7rE(this));
    }

    public static /* synthetic */ p lambda$apply$0(RetryWithDelayUtils retryWithDelayUtils, Throwable th) throws Exception {
        if ((th instanceof ApiException) && ((ApiException) th).getCode() == 200023) {
            return k.error(th);
        }
        int i = retryWithDelayUtils.retryCount + 1;
        retryWithDelayUtils.retryCount = i;
        if (i <= retryWithDelayUtils.maxRetries) {
            return k.timer((long) retryWithDelayUtils.retryDelaySecond, TimeUnit.SECONDS);
        }
        return k.error(th);
    }
}
