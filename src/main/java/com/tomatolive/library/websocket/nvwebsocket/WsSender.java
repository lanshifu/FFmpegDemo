package com.tomatolive.library.websocket.nvwebsocket;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WsSender implements Callback {
    private static final String SEND_HEAT = "ping";
    private Handler handler;
    private HandlerThread handlerThread;
    private boolean hasHeartBeatMessage;
    private ConcurrentLinkedQueue<String> sendQueue;
    private WsManager wsManager;

    public WsSender(WsManager wsManager) {
        if (wsManager != null) {
            this.wsManager = wsManager;
            this.sendQueue = new ConcurrentLinkedQueue();
            return;
        }
        throw new NullPointerException("SocketController can not be null!");
    }

    private void notifyMessage(int i) {
        if (!this.sendQueue.isEmpty() && this.handler != null) {
            if (i == 0) {
                this.handler.sendEmptyMessage(0);
            } else {
                this.handler.sendEmptyMessageDelayed(0, (long) i);
            }
        }
    }

    public void startSendThread() {
        if (this.handlerThread == null || !this.handlerThread.isAlive()) {
            this.handlerThread = new HandlerThread("Socket-SendThread");
            this.handlerThread.setPriority(4);
            this.handlerThread.start();
            this.handler = new Handler(this.handlerThread.getLooper(), this);
        }
    }

    public void stopSendThread() {
        this.sendQueue.clear();
        if (this.handler != null) {
            this.handler.removeCallbacksAndMessages(null);
        }
        try {
            if (this.handlerThread == null) {
                return;
            }
            if (VERSION.SDK_INT >= 18) {
                this.handlerThread.quitSafely();
            } else {
                this.handlerThread.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendSocketMsg(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (TextUtils.equals(SEND_HEAT, str)) {
                if (!this.hasHeartBeatMessage) {
                    this.hasHeartBeatMessage = true;
                } else {
                    return;
                }
            }
            this.sendQueue.offer(str);
            notifyMessage(0);
        }
    }

    public boolean handleMessage(Message message) {
        try {
            if (this.wsManager.getSocketStatus() == WsStatus.CONNECT_SUCCESS) {
                String str = (String) this.sendQueue.poll();
                if (TextUtils.equals(SEND_HEAT, str)) {
                    this.hasHeartBeatMessage = false;
                }
                this.wsManager.doSendMessage(str);
                notifyMessage(0);
            } else {
                this.sendQueue.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
