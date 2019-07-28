package com.tomatolive.library.model;

import android.text.TextUtils;
import com.tomatolive.library.utils.p;

public class SocketMessageEvent {
    public String code;
    public String message;
    public String messageType;
    public ResultData resultData;

    public static class ResultData {
        public String action;
        public String activeTime;
        public String afterGrade;
        public String anchorGuardCount;
        public String anchorId;
        public String anchorName;
        public String animalType;
        public String animalUrl;
        public String avatar;
        public String balance;
        public String banPostAllStatus;
        public String banPostStatus;
        public String carIcon;
        public String carId;
        public String carName;
        public String carOnlineUrl;
        public String carResUrl;
        public String changeType;
        public String changeValue;
        public String clickEvent;
        public String content;
        public String count;
        public String coverUrl;
        public String createTime;
        public String date;
        public String duration;
        public String effectType;
        public String expGrade;
        public String expirationTime;
        public String forwardText;
        public String giftBoxUniqueCode;
        public String giftId;
        public String giftName;
        public String giftNum;
        public String giftOnlineUrl;
        public String guardType = "";
        public String id;
        public String isPlayCarAnim;
        public String liveCount;
        public String liveId;
        public String openDanmu;
        public String openTime;
        public String postIntervalTimes;
        public String presenterAvatar;
        public String presenterId;
        public String presenterName;
        public String price;
        public String propId;
        public String propName;
        public String propNum;
        public String role;
        public String sex;
        public String status;
        public String targetAvatar;
        public String targetId;
        public String targetName;
        public String tomatoPrice;
        public String type;
        public String typeMsg;
        public String userId;
        public String userName = "";

        public boolean isOnPlayCarAnim() {
            return TextUtils.equals(this.isPlayCarAnim, "1");
        }

        public boolean isOpenDanmu() {
            return TextUtils.equals(this.openDanmu, "1");
        }

        public boolean isEnterGuardType() {
            return !TextUtils.isEmpty(this.guardType) && p.a(this.guardType) > p.a("0");
        }

        public boolean isSomeoneBanPost() {
            return TextUtils.equals(this.banPostStatus, "1");
        }

        public boolean isBanAll() {
            return TextUtils.equals(this.banPostAllStatus, "1");
        }

        public boolean isManager() {
            return TextUtils.equals(this.action, "1");
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SocketMessageEvent{code='");
        stringBuilder.append(this.code);
        stringBuilder.append('\'');
        stringBuilder.append(", message='");
        stringBuilder.append(this.message);
        stringBuilder.append('\'');
        stringBuilder.append(", messageType='");
        stringBuilder.append(this.messageType);
        stringBuilder.append('\'');
        stringBuilder.append(", resultData=");
        stringBuilder.append(this.resultData);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
