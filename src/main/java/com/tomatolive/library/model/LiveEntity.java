package com.tomatolive.library.model;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LiveEntity implements Serializable {
    public String amount;
    public String anchorContribution = "";
    public String anchorGuardCount = "0";
    public String anchorId;
    public String audienceExpGrade;
    public String avatar = "";
    public String banPostAllStatus;
    public List<String> banPostList;
    public String banPostStatus;
    public String banPostTimeLeft;
    public String carId;
    public String createTime = "0";
    public String endTime = "0";
    public String expGrade;
    public String font;
    public String format = "";
    public String free = "";
    public String giftId;
    public String giftIncome = "0";
    public String giftIncomeCurrentLive = "0";
    public String gpsAddress;
    public String herald = "";
    public String id;
    public int inBanGroup = 0;
    public String isFollowed = "0";
    public String isLiving = "0";
    public String isOpenWeekGuard = "";
    public boolean isWarnLive;
    public String k = "";
    public String label = "";
    public String liveAdminBanPost;
    public String liveCount;
    public String liveCoverUrl = "";
    public String liveId;
    public ArrayList<String> liveRankConfig;
    public String liveStatus = "0";
    public String markerUrl = "";
    public String maxPopularity = "0";
    public String nickname = "";
    public String onlineUserCount = "1";
    public String onlineUserCountPeekValue = "0";
    public String popularity = "0";
    public String postIntervalTimes;
    public String publishTime = "";
    public String pullStreamUrl = "";
    public String pushStreamUrl;
    public String role;
    public String sex;
    public List<String> shieldUserList;
    public String socketHeartBeatInterval;
    public String socketUrl;
    public String speakLevel;
    public String startTime = "0";
    public String streamName;
    public SysParamInfo sysParamInfo;
    public String tag;
    public long tempTime;
    public String title;
    public String topic = "";
    public String userGuardExpireTime = "";
    public String userGuardType = "0";
    public String userId;
    public String warnStatus;
    public String watchCount = "0";
    public String wsServerAddress;

    public static class SysParamInfo implements Serializable {
        public String enableTranslationLevel = "1";
        public String entryNoticeLevelThreshold = "";
        public String giftTrumpetPlayPeriod;
        public String onlineCountSynInterval;
    }

    public boolean isBanBySuperManager() {
        return TextUtils.equals(this.liveAdminBanPost, "1");
    }

    public boolean isBanStatus() {
        return TextUtils.equals(this.banPostStatus, "1");
    }

    public boolean isBanAll() {
        return TextUtils.equals(this.banPostAllStatus, "1");
    }

    public boolean isAttention() {
        return TextUtils.equals("1", this.isFollowed);
    }

    public boolean isOnLiving() {
        return TextUtils.equals("1", this.liveStatus) || TextUtils.equals("1", this.isLiving);
    }

    public boolean isOnOpenWeekGuard() {
        return TextUtils.equals(this.isOpenWeekGuard, "1");
    }

    public boolean isBanGroup() {
        return this.inBanGroup == 1;
    }

    public String getDefPullStreamUrlStr() {
        List<String> pullStreamUrlList = getPullStreamUrlList();
        if (pullStreamUrlList.isEmpty()) {
            return "";
        }
        for (String str : pullStreamUrlList) {
            if (str.startsWith("rtmp://")) {
                return str;
            }
        }
        return (String) pullStreamUrlList.get(0);
    }

    public List<String> getPullStreamUrlList() {
        if (TextUtils.isEmpty(this.pullStreamUrl)) {
            return new ArrayList();
        }
        return Arrays.asList(this.pullStreamUrl.split(","));
    }
}
