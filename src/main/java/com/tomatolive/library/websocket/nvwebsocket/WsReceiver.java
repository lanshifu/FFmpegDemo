package com.tomatolive.library.websocket.nvwebsocket;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.model.SocketMessageEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WsReceiver implements Callback {
    private static final int LOOP_GET_ANIM = 0;
    private static final int LOOP_GET_BIG_ANIM = 4;
    private static final int LOOP_GET_DATA = 2;
    private static final int LOOP_GET_LOCAL_ANIM = 3;
    private ConcurrentLinkedQueue<GiftItemEntity> localAnimQueue = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<String> msgQueue = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<SocketMessageEvent> receiveAnim = new ConcurrentLinkedQueue();
    private ConcurrentLinkedQueue<GiftItemEntity> receiveBigAnim = new ConcurrentLinkedQueue();
    private Handler receiveMsgHandler;
    private HandlerThread receiveMsgHandlerThread;
    private WsManager wsManager;

    public WsReceiver(WsManager wsManager) {
        this.wsManager = wsManager;
    }

    /* Access modifiers changed, original: 0000 */
    public void startReceiveThread() {
        if (this.receiveMsgHandlerThread == null || !this.receiveMsgHandlerThread.isAlive()) {
            this.receiveMsgHandlerThread = new HandlerThread("receiveMsgHandler");
            this.receiveMsgHandlerThread.setPriority(4);
            this.receiveMsgHandlerThread.start();
            this.receiveMsgHandler = new Handler(this.receiveMsgHandlerThread.getLooper(), this);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void stopReceiveThread() {
        if (this.receiveMsgHandler != null) {
            this.receiveMsgHandler.removeCallbacksAndMessages(null);
        }
        if (this.receiveMsgHandlerThread != null) {
            try {
                if (VERSION.SDK_INT >= 18) {
                    this.receiveMsgHandlerThread.quitSafely();
                } else {
                    this.receiveMsgHandlerThread.quit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.msgQueue.clear();
        this.localAnimQueue.clear();
        this.receiveBigAnim.clear();
        this.receiveAnim.clear();
    }

    public void putMsg(String str) {
        if (str != null && str.length() != 0) {
            this.msgQueue.add(str);
            notifyReceiveMsg(2);
        }
    }

    private void notifyReceiveMsg(int i) {
        if (this.receiveMsgHandler != null && !this.receiveMsgHandler.hasMessages(i) && this.receiveMsgHandlerThread != null && this.receiveMsgHandlerThread.isAlive()) {
            this.receiveMsgHandler.sendEmptyMessage(i);
        }
    }

    public void putLocalAnimMsg(GiftItemEntity giftItemEntity) {
        if (this.localAnimQueue != null && giftItemEntity != null) {
            this.localAnimQueue.add(giftItemEntity);
            notifyReceiveMsg(3);
        }
    }

    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i != 0) {
            GiftItemEntity giftItemEntity;
            switch (i) {
                case 2:
                    dealMsgOnLoop();
                    break;
                case 3:
                    if (this.wsManager.isBigAnimFinished()) {
                        giftItemEntity = (GiftItemEntity) this.localAnimQueue.poll();
                        if (giftItemEntity != null) {
                            this.wsManager.setAnimFinish(false);
                            if (this.wsManager.getBackgroundSocketCallBack() != null) {
                                this.wsManager.getBackgroundSocketCallBack().onBackThreadReceiveBigAnimMsg(giftItemEntity);
                                break;
                            }
                        }
                    }
                    break;
                case 4:
                    if (this.wsManager.isBigAnimFinished()) {
                        if (this.localAnimQueue.size() > 0) {
                            giftItemEntity = null;
                            notifyReceiveMsg(3);
                        } else {
                            giftItemEntity = (GiftItemEntity) this.receiveBigAnim.poll();
                        }
                        if (giftItemEntity != null) {
                            this.wsManager.setAnimFinish(false);
                            if (this.wsManager.getBackgroundSocketCallBack() != null) {
                                this.wsManager.getBackgroundSocketCallBack().onBackThreadReceiveBigAnimMsg(giftItemEntity);
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        SocketMessageEvent socketMessageEvent = (SocketMessageEvent) this.receiveAnim.poll();
        if (!(socketMessageEvent == null || this.wsManager.getBackgroundSocketCallBack() == null)) {
            this.wsManager.getBackgroundSocketCallBack().onBackThreadReceiveMessage(socketMessageEvent);
        }
        return true;
    }

    private void dealMsgOnLoop() {
        /*
        r6 = this;
        r0 = r6.msgQueue;
        r0 = r0.poll();
        r0 = (java.lang.String) r0;
        r0 = com.tomatolive.library.websocket.nvwebsocket.MessageHelper.parseSocketData(r0);
        r1 = 2;
        if (r0 == 0) goto L_0x016e;
    L_0x000f:
        r2 = r0.messageType;
        r3 = -1;
        r4 = r2.hashCode();
        r5 = 0;
        switch(r4) {
            case -1598856750: goto L_0x0145;
            case -1302490523: goto L_0x013a;
            case -1256385881: goto L_0x012f;
            case -1039689911: goto L_0x0124;
            case -993690229: goto L_0x0119;
            case -992867598: goto L_0x010e;
            case -941691210: goto L_0x0103;
            case -903340183: goto L_0x00f8;
            case -634778976: goto L_0x00ed;
            case -339185956: goto L_0x00e2;
            case -337843889: goto L_0x00d7;
            case -236148015: goto L_0x00cb;
            case -21216891: goto L_0x00c0;
            case 3052376: goto L_0x00b5;
            case 3172656: goto L_0x00aa;
            case 3641990: goto L_0x009f;
            case 96667762: goto L_0x0094;
            case 98509126: goto L_0x0088;
            case 99368259: goto L_0x007c;
            case 102846135: goto L_0x0070;
            case 129232527: goto L_0x0064;
            case 317295308: goto L_0x0058;
            case 441119852: goto L_0x004c;
            case 487782924: goto L_0x0040;
            case 798249924: goto L_0x0034;
            case 1680327801: goto L_0x0028;
            case 2021199175: goto L_0x001c;
            default: goto L_0x001a;
        };
    L_0x001a:
        goto L_0x014f;
    L_0x001c:
        r4 = "grabGiftBoxNotified";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0024:
        r2 = 24;
        goto L_0x0150;
    L_0x0028:
        r4 = "giftTrumpet";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0030:
        r2 = 17;
        goto L_0x0150;
    L_0x0034:
        r4 = "liveSetting";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x003c:
        r2 = 14;
        goto L_0x0150;
    L_0x0040:
        r4 = "liveAdminBanPost";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0048:
        r2 = 20;
        goto L_0x0150;
    L_0x004c:
        r4 = "putGiftBox";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0054:
        r2 = 22;
        goto L_0x0150;
    L_0x0058:
        r4 = "userGrade";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0060:
        r2 = 16;
        goto L_0x0150;
    L_0x0064:
        r4 = "grabGiftBox";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x006c:
        r2 = 23;
        goto L_0x0150;
    L_0x0070:
        r4 = "leave";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0078:
        r2 = 9;
        goto L_0x0150;
    L_0x007c:
        r4 = "liveAdminGoOut";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0084:
        r2 = 21;
        goto L_0x0150;
    L_0x0088:
        r4 = "goOut";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0090:
        r2 = 10;
        goto L_0x0150;
    L_0x0094:
        r4 = "entry";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x009c:
        r2 = 1;
        goto L_0x0150;
    L_0x009f:
        r4 = "warn";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x00a7:
        r2 = 4;
        goto L_0x0150;
    L_0x00aa:
        r4 = "gift";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x00b2:
        r2 = 0;
        goto L_0x0150;
    L_0x00b5:
        r4 = "chat";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x00bd:
        r2 = 2;
        goto L_0x0150;
    L_0x00c0:
        r4 = "postInterval";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x00c8:
        r2 = 7;
        goto L_0x0150;
    L_0x00cb:
        r4 = "liveControl";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x00d3:
        r2 = 11;
        goto L_0x0150;
    L_0x00d7:
        r4 = "banPost";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x00df:
        r2 = 5;
        goto L_0x0150;
    L_0x00e2:
        r4 = "balance";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x00ea:
        r2 = 3;
        goto L_0x0150;
    L_0x00ed:
        r4 = "forbidLive";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x00f5:
        r2 = 13;
        goto L_0x0150;
    L_0x00f8:
        r4 = "shield";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0100:
        r2 = 8;
        goto L_0x0150;
    L_0x0103:
        r4 = "universalBroadcast";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x010b:
        r2 = 18;
        goto L_0x0150;
    L_0x010e:
        r4 = "grabGiftBoxBroadcast";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0116:
        r2 = 25;
        goto L_0x0150;
    L_0x0119:
        r4 = "propSend";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0121:
        r2 = 26;
        goto L_0x0150;
    L_0x0124:
        r4 = "notify";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x012c:
        r2 = 12;
        goto L_0x0150;
    L_0x012f:
        r4 = "tokenInvalidNotify";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0137:
        r2 = 15;
        goto L_0x0150;
    L_0x013a:
        r4 = "consumeNotify";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x0142:
        r2 = 19;
        goto L_0x0150;
    L_0x0145:
        r4 = "banPostAll";
        r2 = r2.equals(r4);
        if (r2 == 0) goto L_0x014f;
    L_0x014d:
        r2 = 6;
        goto L_0x0150;
    L_0x014f:
        r2 = -1;
    L_0x0150:
        switch(r2) {
            case 0: goto L_0x0166;
            case 1: goto L_0x0154;
            case 2: goto L_0x0154;
            case 3: goto L_0x0154;
            case 4: goto L_0x0154;
            case 5: goto L_0x0154;
            case 6: goto L_0x0154;
            case 7: goto L_0x0154;
            case 8: goto L_0x0154;
            case 9: goto L_0x0154;
            case 10: goto L_0x0154;
            case 11: goto L_0x0154;
            case 12: goto L_0x0154;
            case 13: goto L_0x0154;
            case 14: goto L_0x0154;
            case 15: goto L_0x0154;
            case 16: goto L_0x0154;
            case 17: goto L_0x0154;
            case 18: goto L_0x0154;
            case 19: goto L_0x0154;
            case 20: goto L_0x0154;
            case 21: goto L_0x0154;
            case 22: goto L_0x0154;
            case 23: goto L_0x0154;
            case 24: goto L_0x0154;
            case 25: goto L_0x0154;
            case 26: goto L_0x0154;
            default: goto L_0x0153;
        };
    L_0x0153:
        goto L_0x016e;
    L_0x0154:
        r2 = r6.wsManager;
        r2 = r2.getBackgroundSocketCallBack();
        if (r2 == 0) goto L_0x016e;
    L_0x015c:
        r2 = r6.wsManager;
        r2 = r2.getBackgroundSocketCallBack();
        r2.onBackThreadReceiveMessage(r0);
        goto L_0x016e;
    L_0x0166:
        r2 = r6.receiveAnim;
        r2.add(r0);
        r6.notifyReceiveMsg(r5);
    L_0x016e:
        r0 = r6.msgQueue;
        r0 = r0.size();
        if (r0 == 0) goto L_0x0179;
    L_0x0176:
        r6.notifyReceiveMsg(r1);
    L_0x0179:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tomatolive.library.websocket.nvwebsocket.WsReceiver.dealMsgOnLoop():void");
    }

    public void notifyBigAnim() {
        notifyReceiveMsg(4);
    }

    public void notifyAnim() {
        notifyReceiveMsg(0);
    }

    public void putReceiveBigAnim(GiftItemEntity giftItemEntity) {
        if (this.receiveBigAnim != null && giftItemEntity != null) {
            this.receiveBigAnim.add(giftItemEntity);
            notifyReceiveMsg(4);
        }
    }
}
