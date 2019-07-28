package com.tomatolive.library.websocket.nvwebsocket;

import defpackage.wl;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$WsManager$WsListener$-QhNa8Xaac3Ua3gU5UpWB-CUGDM implements wl {
    private final /* synthetic */ WsListener f$0;

    public /* synthetic */ -$$Lambda$WsManager$WsListener$-QhNa8Xaac3Ua3gU5UpWB-CUGDM(WsListener wsListener) {
        this.f$0 = wsListener;
    }

    public final void accept(Object obj) {
        WsManager.this.reconnect();
    }
}
