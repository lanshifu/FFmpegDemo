package com.tomatolive.library.websocket.nvwebsocket;

import defpackage.wl;

/* compiled from: lambda */
public final /* synthetic */ class -$$Lambda$WsManager$WsListener$szrwktAH5K495vRTKA_Ui39vN28 implements wl {
    private final /* synthetic */ WsListener f$0;

    public /* synthetic */ -$$Lambda$WsManager$WsListener$szrwktAH5K495vRTKA_Ui39vN28(WsListener wsListener) {
        this.f$0 = wsListener;
    }

    public final void accept(Object obj) {
        WsManager.this.reconnect();
    }
}
