package com.tomatolive.library.websocket.nvwebsocket;

import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.blankj.utilcode.util.h;
import com.example.websocket.com.neovisionaries.ws.client.WebSocket;
import com.example.websocket.com.neovisionaries.ws.client.WebSocketAdapter;
import com.example.websocket.com.neovisionaries.ws.client.WebSocketException;
import com.example.websocket.com.neovisionaries.ws.client.WebSocketFactory;
import com.example.websocket.com.neovisionaries.ws.client.WebSocketFrame;
import com.tomatolive.library.a;
import com.tomatolive.library.http.utils.EncryptUtil;
import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.model.SendMessageEntity;
import com.tomatolive.library.utils.m;
import com.tomatolive.library.websocket.interfaces.BackgroundSocketCallBack;
import com.tomatolive.library.websocket.interfaces.OnWebSocketStatusListener;
import defpackage.xl;
import io.reactivex.disposables.b;
import io.reactivex.k;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WsManager {
    private static final int CONNECT_TIMEOUT = 30000;
    private static final int FRAME_QUEUE_SIZE = 5;
    private static final String SEND_HEAT = "ping";
    private volatile long HEART_BEAT_TIME;
    private BackgroundSocketCallBack backgroundSocketCallBack;
    private boolean isAnimFinished;
    private boolean isClose;
    private boolean isReconnect;
    private Runnable mReconnectTask;
    private WsStatus mStatus;
    private WebSocket mWebSocket;
    private Handler reConnHandler;
    private HandlerThread reConnThread;
    private AtomicInteger reconnectCount;
    private String socketUrl;
    private b timeOut;
    private Timer timer;
    private OnWebSocketStatusListener webSocketStatusListener;
    private WsReceiver wsReceiver;
    private WsSender wsSender;

    private static class SingletonHolder {
        private static final WsManager INSTANCE = new WsManager();

        private SingletonHolder() {
        }
    }

    class WsListener extends WebSocketAdapter {
        WsListener() {
        }

        public void onTextMessage(WebSocket webSocket, String str) throws Exception {
            super.onTextMessage(webSocket, str);
            if (WsManager.this.wsReceiver != null) {
                String DESDecrypt = EncryptUtil.DESDecrypt(a.a().j, str);
                WsManager.this.wsReceiver.putMsg(DESDecrypt);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("socket 接收：");
                stringBuilder.append(DESDecrypt);
                m.a(stringBuilder.toString());
            }
        }

        public void onConnected(WebSocket webSocket, Map<String, List<String>> map) throws Exception {
            super.onConnected(webSocket, map);
            WsManager.this.sendHeartBeat();
            WsManager.this.setStatus(WsStatus.CONNECT_SUCCESS);
            WsManager.this.cancelReconnect();
            if (WsManager.this.webSocketStatusListener != null) {
                WsManager.this.webSocketStatusListener.onOpen(WsManager.this.isReconnect);
            }
        }

        public void onConnectError(WebSocket webSocket, WebSocketException webSocketException) throws Exception {
            super.onConnectError(webSocket, webSocketException);
            WsManager.this.setStatus(WsStatus.CONNECT_FAIL);
            if (WsManager.this.webSocketStatusListener != null) {
                WsManager.this.webSocketStatusListener.onError(WsManager.this.isReconnect, webSocketException.getLocalizedMessage());
            }
            if (!WsManager.this.isClose) {
                k.timer(2, TimeUnit.SECONDS).subscribe(new -$$Lambda$WsManager$WsListener$szrwktAH5K495vRTKA_Ui39vN28(this));
            }
        }

        public void onDisconnected(WebSocket webSocket, WebSocketFrame webSocketFrame, WebSocketFrame webSocketFrame2, boolean z) throws Exception {
            WsManager.this.setStatus(WsStatus.CONNECT_FAIL);
            if (!WsManager.this.isClose) {
                if (WsManager.this.webSocketStatusListener != null) {
                    WsManager.this.webSocketStatusListener.onError(WsManager.this.isReconnect, "onDisconnected");
                }
                k.timer(2, TimeUnit.SECONDS).subscribe(new -$$Lambda$WsManager$WsListener$-QhNa8Xaac3Ua3gU5UpWB-CUGDM(this));
            } else if (WsManager.this.webSocketStatusListener != null) {
                WsManager.this.webSocketStatusListener.onClose();
            }
            super.onDisconnected(webSocket, webSocketFrame, webSocketFrame2, z);
        }
    }

    /* synthetic */ WsManager(AnonymousClass1 anonymousClass1) {
        this();
    }

    private WsManager() {
        this.HEART_BEAT_TIME = 30000;
        this.mStatus = WsStatus.INIT;
        this.reconnectCount = new AtomicInteger(0);
        this.socketUrl = "";
        this.isClose = false;
        this.mReconnectTask = new Runnable() {
            public void run() {
                try {
                    WsManager.this.mWebSocket = new WebSocketFactory().createSocket(WsManager.this.socketUrl, WsManager.CONNECT_TIMEOUT).setFrameQueueSize(5).addListener(new WsListener()).connectAsynchronously();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        };
    }

    public static WsManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setOnWebSocketListener(OnWebSocketStatusListener onWebSocketStatusListener) {
        this.webSocketStatusListener = onWebSocketStatusListener;
    }

    public WsStatus getSocketStatus() {
        return this.mStatus;
    }

    public void setOnBackgroundSocketCallBack(BackgroundSocketCallBack backgroundSocketCallBack) {
        this.backgroundSocketCallBack = backgroundSocketCallBack;
    }

    public void init(BackgroundSocketCallBack backgroundSocketCallBack, String str, long j) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.socketUrl = str;
            }
            this.isAnimFinished = true;
            this.isClose = false;
            this.isReconnect = false;
            this.HEART_BEAT_TIME = j;
            this.backgroundSocketCallBack = backgroundSocketCallBack;
            this.mWebSocket = new WebSocketFactory().createSocket(this.socketUrl, CONNECT_TIMEOUT).setFrameQueueSize(5).addListener(new WsListener()).connectAsynchronously();
            setStatus(WsStatus.CONNECTING);
            startService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendHeartBeat() {
        this.timer = new Timer();
        this.timer.schedule(new TimerTask() {
            public void run() {
                if (WsManager.this.wsSender != null && WsManager.this.getSocketStatus() == WsStatus.CONNECT_SUCCESS) {
                    WsManager.this.wsSender.sendSocketMsg(WsManager.SEND_HEAT);
                }
            }
        }, 0, this.HEART_BEAT_TIME);
    }

    private void stopHeartBeat() {
        if (this.timer != null) {
            this.timer.cancel();
            this.timer.purge();
            this.timer = null;
        }
    }

    public void setStatus(WsStatus wsStatus) {
        this.mStatus = wsStatus;
    }

    public void reconnect() {
        if (h.a()) {
            if (getSocketStatus() == WsStatus.CONNECT_FAIL) {
                stopHeartBeat();
                this.reconnectCount.incrementAndGet();
                if (this.reconnectCount.get() > 1) {
                    if (this.webSocketStatusListener != null) {
                        this.webSocketStatusListener.reConnectCountOver();
                    }
                    return;
                }
                setStatus(WsStatus.CONNECTING);
                this.isReconnect = true;
                if (this.webSocketStatusListener != null) {
                    closeSocket();
                    this.webSocketStatusListener.reConnecting();
                }
            }
            return;
        }
        this.reconnectCount.set(0);
        setStatus(WsStatus.CONNECT_FAIL);
        if (this.webSocketStatusListener != null) {
            this.webSocketStatusListener.reConnectCountOver();
        }
    }

    public void reconnect(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.socketUrl = str;
        }
        if (this.timeOut == null) {
            this.timeOut = k.timer(5, TimeUnit.SECONDS).observeOn(xl.b()).subscribe(new -$$Lambda$WsManager$yzi3rWEBQg8NmnQaS9_bUwJXFMc(this));
        }
        this.reConnHandler.post(this.mReconnectTask);
    }

    public static /* synthetic */ void lambda$reconnect$0(WsManager wsManager, Long l) throws Exception {
        if (wsManager.getSocketStatus() == WsStatus.CONNECTING) {
            wsManager.reconnectCount.set(0);
            wsManager.stopHeartBeat();
            wsManager.setStatus(WsStatus.CONNECT_FAIL);
            if (wsManager.webSocketStatusListener != null) {
                wsManager.webSocketStatusListener.reConnectCountOver();
            }
        }
        wsManager.timeOut = null;
    }

    private void startService() {
        if (this.wsSender == null) {
            this.wsSender = new WsSender(this);
            this.wsSender.startSendThread();
        }
        if (this.wsReceiver == null) {
            this.wsReceiver = new WsReceiver(this);
            this.wsReceiver.startReceiveThread();
        }
        if (this.reConnThread == null || !this.reConnThread.isAlive()) {
            this.reConnThread = new HandlerThread("reConn");
            this.reConnThread.setPriority(4);
            this.reConnThread.start();
            this.reConnHandler = new Handler(this.reConnThread.getLooper());
        }
    }

    private void cancelReconnect() {
        this.reconnectCount.set(0);
        this.reConnHandler.removeCallbacks(this.mReconnectTask);
    }

    public void doSendMessage(String str) {
        if (!(str == null || this.mWebSocket == null || !this.mWebSocket.isOpen())) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("socket 发送：");
                stringBuilder.append(str);
                m.a(stringBuilder.toString());
                this.mWebSocket.sendText(EncryptUtil.DESEncrypt(a.a().j, str));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeSocket() {
        if (this.mWebSocket != null) {
            setStatus(WsStatus.CLOSE);
            this.mWebSocket.clearListeners();
            this.mWebSocket.disconnect();
            this.mWebSocket = null;
        }
    }

    public BackgroundSocketCallBack getBackgroundSocketCallBack() {
        return this.backgroundSocketCallBack;
    }

    public void setAnimFinish(boolean z) {
        this.isAnimFinished = z;
    }

    public boolean isBigAnimFinished() {
        return this.isAnimFinished;
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessVariables
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.regions.ProcessVariables.addToUsageMap(ProcessVariables.java:271)
        	at jadx.core.dex.visitors.regions.ProcessVariables.access$000(ProcessVariables.java:31)
        	at jadx.core.dex.visitors.regions.ProcessVariables$CollectUsageRegionVisitor.processInsn(ProcessVariables.java:163)
        	at jadx.core.dex.visitors.regions.ProcessVariables$CollectUsageRegionVisitor.processBlockTraced(ProcessVariables.java:129)
        	at jadx.core.dex.visitors.regions.TracedRegionVisitor.processBlock(TracedRegionVisitor.java:23)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:53)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:57)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:57)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:57)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1080)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:57)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:57)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:57)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:57)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:57)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:57)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1080)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:57)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:57)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:57)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:18)
        	at jadx.core.dex.visitors.regions.ProcessVariables.visit(ProcessVariables.java:183)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.util.ArrayList.forEach(ArrayList.java:1249)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007b  */
    /* JADX WARNING: Missing block: B:37:0x007b, code skipped:
            r4.webSocketStatusListener = null;
     */
    public void stopService() {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        r2 = r4.timeOut;	 Catch:{ Exception -> 0x005b }
        if (r2 == 0) goto L_0x0015;	 Catch:{ Exception -> 0x005b }
    L_0x0006:
        r2 = r4.timeOut;	 Catch:{ Exception -> 0x005b }
        r2 = r2.isDisposed();	 Catch:{ Exception -> 0x005b }
        if (r2 != 0) goto L_0x0015;	 Catch:{ Exception -> 0x005b }
    L_0x000e:
        r2 = r4.timeOut;	 Catch:{ Exception -> 0x005b }
        r2.dispose();	 Catch:{ Exception -> 0x005b }
        r4.timeOut = r1;	 Catch:{ Exception -> 0x005b }
    L_0x0015:
        r2 = r4.reConnThread;	 Catch:{ Exception -> 0x005b }
        if (r2 == 0) goto L_0x002a;	 Catch:{ Exception -> 0x005b }
    L_0x0019:
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x005b }
        r3 = 18;	 Catch:{ Exception -> 0x005b }
        if (r2 < r3) goto L_0x0025;	 Catch:{ Exception -> 0x005b }
    L_0x001f:
        r2 = r4.reConnThread;	 Catch:{ Exception -> 0x005b }
        r2.quitSafely();	 Catch:{ Exception -> 0x005b }
        goto L_0x002a;	 Catch:{ Exception -> 0x005b }
    L_0x0025:
        r2 = r4.reConnThread;	 Catch:{ Exception -> 0x005b }
        r2.quit();	 Catch:{ Exception -> 0x005b }
    L_0x002a:
        r2 = r4.reConnHandler;	 Catch:{ Exception -> 0x005b }
        if (r2 == 0) goto L_0x0035;	 Catch:{ Exception -> 0x005b }
    L_0x002e:
        r2 = r4.reConnHandler;	 Catch:{ Exception -> 0x005b }
        r2.removeCallbacksAndMessages(r1);	 Catch:{ Exception -> 0x005b }
        r4.reConnHandler = r1;	 Catch:{ Exception -> 0x005b }
    L_0x0035:
        r4.stopHeartBeat();	 Catch:{ Exception -> 0x005b }
        r2 = r4.wsSender;	 Catch:{ Exception -> 0x005b }
        if (r2 == 0) goto L_0x0043;	 Catch:{ Exception -> 0x005b }
    L_0x003c:
        r2 = r4.wsSender;	 Catch:{ Exception -> 0x005b }
        r2.stopSendThread();	 Catch:{ Exception -> 0x005b }
        r4.wsSender = r1;	 Catch:{ Exception -> 0x005b }
    L_0x0043:
        r2 = r4.wsReceiver;	 Catch:{ Exception -> 0x005b }
        if (r2 == 0) goto L_0x004e;	 Catch:{ Exception -> 0x005b }
    L_0x0047:
        r2 = r4.wsReceiver;	 Catch:{ Exception -> 0x005b }
        r2.stopReceiveThread();	 Catch:{ Exception -> 0x005b }
        r4.wsReceiver = r1;	 Catch:{ Exception -> 0x005b }
    L_0x004e:
        r2 = r4.backgroundSocketCallBack;
        if (r2 == 0) goto L_0x0054;
    L_0x0052:
        r4.backgroundSocketCallBack = r1;
    L_0x0054:
        r2 = r4.webSocketStatusListener;
        if (r2 == 0) goto L_0x006b;
    L_0x0058:
        goto L_0x0069;
    L_0x0059:
        r2 = move-exception;
        goto L_0x0071;
    L_0x005b:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x0059 }
        r2 = r4.backgroundSocketCallBack;
        if (r2 == 0) goto L_0x0065;
    L_0x0063:
        r4.backgroundSocketCallBack = r1;
    L_0x0065:
        r2 = r4.webSocketStatusListener;
        if (r2 == 0) goto L_0x006b;
    L_0x0069:
        r4.webSocketStatusListener = r1;
    L_0x006b:
        r4.closeSocket();
        r4.isClose = r0;
        return;
    L_0x0071:
        r3 = r4.backgroundSocketCallBack;
        if (r3 == 0) goto L_0x0077;
    L_0x0075:
        r4.backgroundSocketCallBack = r1;
    L_0x0077:
        r3 = r4.webSocketStatusListener;
        if (r3 == 0) goto L_0x007d;
    L_0x007b:
        r4.webSocketStatusListener = r1;
    L_0x007d:
        r4.closeSocket();
        r4.isClose = r0;
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.websocket.nvwebsocket.WsManager.stopService():void");
    }

    public void addReceiveBigAnim(GiftItemEntity giftItemEntity) {
        if (this.wsReceiver != null) {
            this.wsReceiver.putReceiveBigAnim(giftItemEntity);
        }
    }

    public void addLocalAnim(GiftItemEntity giftItemEntity) {
        if (this.wsReceiver != null) {
            this.wsReceiver.putLocalAnimMsg(giftItemEntity);
        }
    }

    public void notifyAnim() {
        if (this.wsReceiver != null) {
            this.wsReceiver.notifyAnim();
        }
    }

    public void notifyBigAnim() {
        this.isAnimFinished = true;
        if (this.wsReceiver != null) {
            this.wsReceiver.notifyBigAnim();
        }
    }

    public void resetCount() {
        this.reconnectCount.set(0);
    }

    public void sendChatMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeChatMessage(sendMessageEntity));
        }
    }

    public void sendNotifyMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeNotifyMessage(sendMessageEntity));
        }
    }

    public void sendGiftMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeGiftMessage(sendMessageEntity));
        }
    }

    public void sendEnterMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeEnterMessage(sendMessageEntity));
        }
    }

    public void sendLeaveMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeLeaveMessage(sendMessageEntity));
        }
    }

    public void sendShieldMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeShieldMessage(sendMessageEntity));
        }
    }

    public void sendPostIntervalMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makePostIntervalMessage(sendMessageEntity));
        }
    }

    public void sendSpeakLevelMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeSpeakLevelMessage(sendMessageEntity));
        }
    }

    public void sendBannedAllMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeBannedAllMessage(sendMessageEntity));
        }
    }

    public void sendBannedMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeBannedMessage(sendMessageEntity));
        }
    }

    public void sendSuperBannedMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeSuperBannedMessage(sendMessageEntity));
        }
    }

    public void sendSuperGoOutMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeSuperGoOutMessage(sendMessageEntity));
        }
    }

    public void sendGrabGiftBoxMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeGrabGiftBoxMessage(sendMessageEntity));
        }
    }

    public void sendCtrlMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeCtrlMessage(sendMessageEntity));
        }
    }

    public void sendKickOutMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makeGoOutMessage(sendMessageEntity));
        }
    }

    public void sendPropSendMessage(SendMessageEntity sendMessageEntity) {
        if (this.wsSender != null && sendMessageEntity != null) {
            this.wsSender.sendSocketMsg(MessageHelper.makePropSendMessage(sendMessageEntity));
        }
    }
}
