package com.tomatolive.library.websocket.interfaces;

public interface OnWebSocketStatusListener {
    void onClose();

    void onError(boolean z, String str);

    void onOpen(boolean z);

    void reConnectCountOver();

    void reConnecting();
}
