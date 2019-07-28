package com.tomatolive.library.http;

import android.text.TextUtils;
import com.blankj.utilcode.util.c;
import com.blankj.utilcode.util.h;
import com.blankj.utilcode.util.k;
import com.tomatolive.library.http.interceptor.AddHeaderInterceptor;
import com.tomatolive.library.model.GuardItemEntity;
import com.tomatolive.library.utils.z;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;
import java.util.HashMap;
import java.util.Map;

public class RequestParams {
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE_SIZE = "pageSize";
    private final int PAGE_SIZE_COUNT = 20;
    private String userId = z.a().c();

    public Map<String, Object> getUserIdParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        return hashMap;
    }

    public Map<String, Object> getUserIdByIdParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.userId);
        return hashMap;
    }

    public Map<String, Object> getUserIdParams(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(20));
        return hashMap;
    }

    public Map<String, Object> getAnchorIdParams(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(20));
        return hashMap;
    }

    public Map<String, Object> getDefaultParams() {
        return new HashMap();
    }

    public Map<String, Object> getAppIdParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("appId", z.a().e());
        return hashMap;
    }

    public Map<String, Object> getSDKLoginParams() {
        String d = z.a().d();
        String f = z.a().f();
        String h = z.a().h();
        String g = z.a().g();
        String b = z.a().b();
        HashMap hashMap = new HashMap();
        hashMap.put("userId", d);
        hashMap.put("name", f);
        hashMap.put("sex", h);
        hashMap.put("avatar", g);
        hashMap.put("ntoken", b);
        hashMap.put("isLogin", String.valueOf(1));
        return hashMap;
    }

    public Map<String, Object> getUpdateAvatarParams(String str) {
        String updateUserId = getUpdateUserId();
        String f = z.a().f();
        String h = z.a().h();
        HashMap hashMap = new HashMap();
        hashMap.put("userId", updateUserId);
        hashMap.put("name", f);
        hashMap.put("sex", h);
        hashMap.put("avatar", str);
        hashMap.put("isLogin", String.valueOf(1));
        return hashMap;
    }

    public Map<String, Object> getUpdateNicknameParams(String str) {
        String updateUserId = getUpdateUserId();
        String h = z.a().h();
        String g = z.a().g();
        HashMap hashMap = new HashMap();
        hashMap.put("userId", updateUserId);
        hashMap.put("name", str);
        hashMap.put("sex", h);
        hashMap.put("avatar", g);
        hashMap.put("isLogin", String.valueOf(1));
        return hashMap;
    }

    public Map<String, Object> getUpdateSexParams(String str) {
        String updateUserId = getUpdateUserId();
        String f = z.a().f();
        String g = z.a().g();
        HashMap hashMap = new HashMap();
        hashMap.put("userId", updateUserId);
        hashMap.put("name", f);
        hashMap.put("sex", str);
        hashMap.put("avatar", g);
        hashMap.put("isLogin", String.valueOf(1));
        return hashMap;
    }

    private String getUpdateUserId() {
        return this.userId;
    }

    public Map<String, Object> getTagPageListParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("tag", str);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(20));
        return hashMap;
    }

    public Map<String, Object> getPageListByIdParams(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.userId);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(20));
        return hashMap;
    }

    public Map<String, Object> getPageListParams(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(20));
        return hashMap;
    }

    public Map<String, Object> getPageListParams(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(i2));
        return hashMap;
    }

    public Map<String, Object> getSearchAnchorListParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("key", str);
        hashMap.put("userId", this.userId);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(20));
        return hashMap;
    }

    public Map<String, Object> getPageListByKeyParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("key", str);
        hashMap.put(PAGE_NUMBER, String.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(20));
        return hashMap;
    }

    public Map<String, Object> getAttentionAnchorParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("follower", this.userId);
        hashMap.put("userId", str);
        hashMap.put("followFlag", String.valueOf(i));
        return hashMap;
    }

    public Map<String, Object> getHomeTopParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("dateType", str);
        hashMap.put("userId", this.userId);
        return hashMap;
    }

    public Map<String, Object> getHomeStrengthTopParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("dateType", str);
        return hashMap;
    }

    public Map<String, Object> getBannerListParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

    public Map<String, Object> getGiftListParams(int i) {
        return new HashMap();
    }

    public Map<String, Object> getAnchorAuthParams(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        HashMap hashMap = new HashMap();
        hashMap.put("realName", str);
        hashMap.put("idCardNo", str2);
        hashMap.put("phone", str3);
        hashMap.put("verifyCode", str4);
        hashMap.put("idCardTop", str5);
        hashMap.put("idCardButtom", str6);
        hashMap.put("countryCode", str7);
        hashMap.put("userId", this.userId);
        hashMap.put("nickname", z.a().f());
        return hashMap;
    }

    public Map<String, Object> getStartLiveParams(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("topic", str);
        hashMap.put("free", str2);
        hashMap.put("tag", str3);
        hashMap.put("gpsAddress", str4);
        hashMap.put("userId", this.userId);
        return hashMap;
    }

    public Map<String, Object> getLiveEndInfoParams(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", str);
        hashMap.put("liveId", str2);
        hashMap.put("liveCount", str3);
        return hashMap;
    }

    public Map<String, Object> getAnchorInfoParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

    public Map<String, Object> getPreStartLiveInfoParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.userId);
        return hashMap;
    }

    public Map<String, Object> getAnchorLiveInfoParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getUploadLiveCoverParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("liveCoverUrl", str);
        hashMap.put("recomCoverUrl", "");
        return hashMap;
    }

    public Map<String, Object> getUploadLiveCoverParams(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", str);
        hashMap.put("liveCoverUrl", str2);
        hashMap.put("recomCoverUrl", str3);
        return hashMap;
    }

    public Map<String, Object> getContributionListParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put("dateType", str);
        return hashMap;
    }

    public Map<String, Object> getContributionListParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str2);
        hashMap.put("dateType", str);
        return hashMap;
    }

    public Map<String, Object> getHouseSettingParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put("userIds", str);
        hashMap.put("action", String.valueOf(i));
        return hashMap;
    }

    public Map<String, Object> getBannedSettingParams(String str, String str2, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put("userId", str);
        hashMap.put("duration", str2);
        hashMap.put("action", String.valueOf(i));
        return hashMap;
    }

    public Map<String, Object> getSearchUsersParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("nickname", str);
        }
        return hashMap;
    }

    public Map<String, Object> getExitSDKParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.userId);
        return hashMap;
    }

    public Map<String, Object> getDelWatchHistoryParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

    public Map<String, Object> getSendPhoneCodeParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("phone", str);
        hashMap.put("countryCode", str2);
        hashMap.put("methodId", "001");
        return hashMap;
    }

    public Map<String, Object> getUserOverParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("memberId", this.userId);
        hashMap.put("methodId", "006");
        return hashMap;
    }

    public Map<String, Object> getDeviceParams(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(AddHeaderInterceptor.DEVICE_ID, c.b());
        hashMap.put("deviceType", Integer.valueOf(2));
        hashMap.put("deviceOS", c.a());
        hashMap.put("deviceModel", c.c());
        hashMap.put("linkType", h.b() ? ConnectSocketParams.EFFECT_TYPE_BIG : "1");
        hashMap.put("num", Integer.valueOf(i));
        return hashMap;
    }

    public Map<String, Object> getLiveStatisticsParams(String str) {
        HashMap hashMap = new HashMap(getDeviceParams(3));
        hashMap.put("roomId", str);
        return hashMap;
    }

    public Map<String, Object> getLiveTimeStatisticsParams(String str) {
        HashMap hashMap = new HashMap(getDeviceParams(4));
        hashMap.put("roomId", str);
        return hashMap;
    }

    public Map<String, Object> getExitLiveTimeStatisticsParams(String str) {
        HashMap hashMap = new HashMap(getDeviceParams(4));
        hashMap.put("roomId", str);
        hashMap.put("key", k.a().b("timeKeyRoom"));
        return hashMap;
    }

    public Map<String, Object> getGiftStatisticsParams(String str, String str2, String str3) {
        HashMap hashMap = new HashMap(getDeviceParams(5));
        hashMap.put("giftId", str);
        hashMap.put("giftTypeId", str2);
        hashMap.put("giftTypeName", str3);
        return hashMap;
    }

    public Map<String, Object> getBannerStatisticsParams(String str, String str2) {
        HashMap hashMap = new HashMap(getDeviceParams(6));
        hashMap.put("adId", str);
        hashMap.put("adName", str2);
        return hashMap;
    }

    public Map<String, Object> getCurrentOnlineUserList(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getLiveInitInfoParams(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("userId", this.userId);
        hashMap.put("enterType", str2);
        hashMap.put("isReconnect", str3);
        return hashMap;
    }

    public Map<String, Object> getReceiveGiftRecordParams(int i, int i2) {
        i2++;
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", this.userId);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put("liveCount", String.valueOf(i2));
        hashMap.put(PAGE_SIZE, Integer.valueOf(20));
        return hashMap;
    }

    public Map<String, Object> getLivePreNoticeParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("content", str);
        hashMap.put("userId", this.userId);
        return hashMap;
    }

    public Map<String, Object> getErrorReportParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getEnterLiveRoomParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("enterType", str2);
        return hashMap;
    }

    public Map<String, Object> getLeaveLiveRoomParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("enterType", str);
        return hashMap;
    }

    public Map<String, Object> getAnchorGuardListParams(String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str);
        hashMap.put(PAGE_NUMBER, Integer.valueOf(i));
        hashMap.put(PAGE_SIZE, Integer.valueOf(100));
        return hashMap;
    }

    public Map<String, Object> getOpenGuardParams(GuardItemEntity guardItemEntity) {
        HashMap hashMap = new HashMap();
        if (guardItemEntity == null) {
            return hashMap;
        }
        hashMap.put("guardId", guardItemEntity.id);
        hashMap.put("guardType", guardItemEntity.type);
        hashMap.put("anchorId", guardItemEntity.anchorId);
        hashMap.put("price", guardItemEntity.price);
        hashMap.put("tomatoPrice", guardItemEntity.tomatoPrice);
        hashMap.put("userId", this.userId);
        hashMap.put("guardRatio", guardItemEntity.guardRatio);
        hashMap.put("avatar", z.a().g());
        hashMap.put("userName", z.a().f());
        hashMap.put("expGrade", guardItemEntity.expGrade);
        return hashMap;
    }

    public Map<String, Object> getBroadcastClickParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        return hashMap;
    }

    public Map<String, Object> getPersonalGuardInfoParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("anchorId", str);
        hashMap.put("userId", this.userId);
        return hashMap;
    }

    public Map<String, Object> getWebSocketAddressParams(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        hashMap.put("userId", this.userId);
        hashMap.put("enterType", str2);
        hashMap.put("isReconnect", str3);
        return hashMap;
    }

    public Map<String, Object> getIncomeConsumeParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("date", str);
        return hashMap;
    }

    public Map<String, Object> getIncomeConsumeDetailParams(int i, String str) {
        Map userIdParams = getUserIdParams(i);
        userIdParams.put("date", str);
        return userIdParams;
    }

    public Map<String, Object> getIncomeConsumeDetailParams(int i, String str, boolean z) {
        Map userIdParams = getUserIdParams(i);
        userIdParams.put("date", str);
        userIdParams.put("isFree", z ? "1" : "0");
        return userIdParams;
    }

    public Map<String, Object> getScopeParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("scope", "0");
        return hashMap;
    }

    public Map<String, Object> getAllCarParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("scope", "1");
        return hashMap;
    }

    public Map<String, Object> getBuyCarParams(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("carId", str);
        hashMap.put("type", str2);
        hashMap.put("gold", str3);
        return hashMap;
    }

    public Map<String, Object> getUseCarParams(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("uniqueId", str);
        hashMap.put("isUsed", str2);
        return hashMap;
    }

    public Map<String, Object> getGiftBoxListParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("liveId", str);
        return hashMap;
    }

    public Map<String, Object> getTaskBoxListParams() {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("appId", z.a().e());
        return hashMap;
    }

    public Map<String, Object> getTaskChangeParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("userId", this.userId);
        hashMap.put("taskBoxId", str);
        return hashMap;
    }
}
