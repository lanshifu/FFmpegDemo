package io.reactivex.internal.operators.flowable;

import defpackage.wl;

public final class FlowableInternalHelper {

    public enum RequestMax implements wl<aas> {
        INSTANCE;

        public void accept(aas aas) throws Exception {
            aas.request(Long.MAX_VALUE);
        }
    }
}
