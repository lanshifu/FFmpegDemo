package com.tomatolive.library.websocket.nvwebsocket;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tomatolive.library.a;
import com.tomatolive.library.model.BaseSocketEntity;
import com.tomatolive.library.model.GiftItemEntity;
import com.tomatolive.library.model.SendMessageEntity;
import com.tomatolive.library.model.SocketMessageEvent;
import com.tomatolive.library.utils.emoji.EmojiParser;
import com.tomatolive.library.utils.n;
import com.tomatolive.library.utils.s;
import com.tomatolive.library.utils.z;
import java.util.UUID;

public class MessageHelper {
    private MessageHelper() {
    }

    public static String makeGiftMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_GIFT_TYPE);
    }

    private static String createMsg(SendMessageEntity sendMessageEntity, String str) {
        Gson create = new GsonBuilder().disableHtmlEscaping().create();
        BaseSocketEntity baseSocketEntity = new BaseSocketEntity();
        baseSocketEntity.setMessageType(str);
        baseSocketEntity.setBusinessData(sendMessageEntity);
        baseSocketEntity.setRandomStr(s.a());
        baseSocketEntity.setTimestampStr(s.b());
        baseSocketEntity.setSign(formatSign(create, sendMessageEntity, baseSocketEntity));
        return create.toJson(baseSocketEntity);
    }

    private static String formatSign(@NonNull Gson gson, SendMessageEntity sendMessageEntity, BaseSocketEntity baseSocketEntity) {
        String toJson = gson.toJson(sendMessageEntity);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(toJson);
        stringBuilder.append(baseSocketEntity.getTimestampStr());
        stringBuilder.append(baseSocketEntity.getRandomStr());
        stringBuilder.append(a.a().h);
        return n.b(stringBuilder.toString());
    }

    public static String makeEnterMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_ENTER_TYPE);
    }

    public static SocketMessageEvent parseSocketData(String str) {
        Exception e;
        OutOfMemoryError e2;
        SocketMessageEvent socketMessageEvent;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            BaseSocketEntity baseSocketEntity = (BaseSocketEntity) new Gson().fromJson(str, new TypeToken<BaseSocketEntity<SocketMessageEvent>>() {
            }.getType());
            socketMessageEvent = (SocketMessageEvent) baseSocketEntity.getBusinessData();
            try {
                socketMessageEvent.messageType = baseSocketEntity.getMessageType();
            } catch (Exception e3) {
                e = e3;
            } catch (OutOfMemoryError e4) {
                e2 = e4;
                e2.printStackTrace();
                return socketMessageEvent;
            }
            return socketMessageEvent;
        } catch (Exception e5) {
            e = e5;
            socketMessageEvent = null;
            e.printStackTrace();
            return socketMessageEvent;
        } catch (OutOfMemoryError e6) {
            e2 = e6;
            socketMessageEvent = null;
            e2.printStackTrace();
            return socketMessageEvent;
        }
    }

    public static String makeChatMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_CHAT_TYPE);
    }

    public static String makeLeaveMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_LEAVE_TYPE);
    }

    public static String makeBannedMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_BAN_POST_TYPE);
    }

    public static String makeSuperBannedMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_LIVEADMIN_BANPOST_TYPE);
    }

    public static String makeSuperGoOutMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_LIVEADMIN_GOOUT_TYPE);
    }

    public static String makeGrabGiftBoxMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_GRAB_GIFT_BOX_TYPE);
    }

    public static String makeGoOutMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_GOOUT_TYPE);
    }

    public static String makeCtrlMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_LIVECONTROL_TYPE);
    }

    public static String makePostIntervalMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_POSTINTERVAL_TYPE);
    }

    public static String makeSpeakLevelMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_LIVE_SETTING_TYPE);
    }

    public static String makeBannedAllMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_BANPOSTALL_TYPE);
    }

    public static String makeShieldMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_SHIELD_TYPE);
    }

    public static String makeNotifyMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_NOTIFY_TYPE);
    }

    public static String makePropSendMessage(SendMessageEntity sendMessageEntity) {
        return sendMessageEntity == null ? null : createMsg(sendMessageEntity, ConnectSocketParams.MESSAGE_PROP_SEND_TYPE);
    }

    public static SendMessageEntity convertToGiftMsg(GiftItemEntity giftItemEntity) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.effectType = String.valueOf(giftItemEntity.effect_type);
        sendMessageEntity.giftId = giftItemEntity.typeid;
        sendMessageEntity.sex = z.a().h();
        sendMessageEntity.giftNum = String.valueOf(giftItemEntity.num);
        sendMessageEntity.giftName = giftItemEntity.name;
        sendMessageEntity.userName = z.a().f();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.anchorId = giftItemEntity.anchorId;
        sendMessageEntity.anchorName = giftItemEntity.anchorName;
        sendMessageEntity.role = giftItemEntity.role;
        sendMessageEntity.liveId = giftItemEntity.liveId;
        sendMessageEntity.avatar = z.a().g();
        sendMessageEntity.createTime = String.valueOf(System.currentTimeMillis());
        sendMessageEntity.price = giftItemEntity.price;
        sendMessageEntity.tomatoPrice = giftItemEntity.tomatoPrice;
        sendMessageEntity.expGrade = giftItemEntity.expGrade;
        sendMessageEntity.liveCount = giftItemEntity.liveCount;
        sendMessageEntity.clientIp = giftItemEntity.clientIp;
        sendMessageEntity.appId = z.a().e();
        sendMessageEntity.uuid = UUID.randomUUID().toString();
        sendMessageEntity.guardType = String.valueOf(giftItemEntity.guardType);
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToBanMsg(String str, String str2, String str3, String str4, String str5, String str6) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.userName = z.a().f();
        sendMessageEntity.liveId = str;
        sendMessageEntity.targetId = str2;
        sendMessageEntity.targetName = str3;
        sendMessageEntity.duration = str4;
        sendMessageEntity.role = str5;
        sendMessageEntity.banPostStatus = str6;
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToSuperBanMsg(String str, String str2, String str3) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.userName = z.a().f();
        sendMessageEntity.liveId = str;
        sendMessageEntity.targetId = str2;
        sendMessageEntity.targetName = str3;
        sendMessageEntity.banPostStatus = "1";
        sendMessageEntity.role = "5";
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToSuperGoOutMsg(String str, String str2, String str3) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.userName = z.a().f();
        sendMessageEntity.liveId = str;
        sendMessageEntity.targetId = str2;
        sendMessageEntity.targetName = str3;
        sendMessageEntity.role = "5";
        sendMessageEntity.goOutStatus = "1";
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToGrabGiftBoxMsg(String str) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.giftBoxUniqueCode = str;
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToKickOutMsg(String str, String str2, String str3) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.liveId = str;
        sendMessageEntity.userName = z.a().f();
        sendMessageEntity.targetId = str2;
        sendMessageEntity.targetName = str3;
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToCtrlMsg(String str, String str2, String str3, boolean z) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.liveId = str;
        sendMessageEntity.targetId = str2;
        sendMessageEntity.targetName = str3;
        sendMessageEntity.action = z ? "1" : ConnectSocketParams.EFFECT_TYPE_BIG;
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToShieldMsg(String str, String str2, boolean z) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.targetId = str2;
        sendMessageEntity.liveId = str;
        sendMessageEntity.shieldStatus = z ? "1" : ConnectSocketParams.EFFECT_TYPE_BIG;
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToChatMsg(String str, String str2, String str3, String str4, boolean z, String str5) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.content = str3;
        sendMessageEntity.openDanmu = z ? "1" : "0";
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToEnterMsg(String str, String str2, boolean z, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.liveId = str2;
        sendMessageEntity.enterType = str;
        sendMessageEntity.appId = z.a().e();
        sendMessageEntity.isReconnect = z ? "1" : "0";
        sendMessageEntity.expGrade = str3;
        sendMessageEntity.userName = z.a().f();
        sendMessageEntity.avatar = z.a().g();
        sendMessageEntity.sex = z.a().h();
        sendMessageEntity.role = str4;
        sendMessageEntity.guardType = str5;
        sendMessageEntity.carId = str6;
        sendMessageEntity.carName = str7;
        sendMessageEntity.carIcon = str8;
        sendMessageEntity.carOnlineUrl = str9;
        sendMessageEntity.carResUrl = str10;
        sendMessageEntity.isPlayCarAnim = str11;
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToNotifyMsg(String str, String str2) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.liveId = str;
        sendMessageEntity.type = ConnectSocketParams.EFFECT_TYPE_BIG;
        sendMessageEntity.typeMsg = EmojiParser.a(str2);
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToSpeakLevelMsg(String str, String str2) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.liveId = str;
        sendMessageEntity.changeType = ConnectSocketParams.MESSAGE_SPEAKLEVEL_TYPE;
        sendMessageEntity.changeValue = str2;
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToAllBanMsg(String str, boolean z) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.liveId = str;
        sendMessageEntity.banPostAllStatus = z ? "1" : ConnectSocketParams.EFFECT_TYPE_BIG;
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToPostInterval(String str, String str2) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.userId = z.a().c();
        sendMessageEntity.liveId = str;
        sendMessageEntity.postIntervalTimes = str2;
        return sendMessageEntity;
    }

    public static SendMessageEntity convertToPropSend(String str, String str2) {
        SendMessageEntity sendMessageEntity = new SendMessageEntity();
        sendMessageEntity.count = str;
        sendMessageEntity.propId = str2;
        return sendMessageEntity;
    }
}
