package com.tomatolive.library.ui.view.gift;

import android.text.TextUtils;
import com.tomatolive.library.ui.view.gift.GiftFrameLayout.BarrageEndAnimationListener;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;

public class GiftAnimModel {
    private BarrageEndAnimationListener animationListener;
    private boolean currentStart;
    private String effectType;
    private int giftCount;
    private String giftDirPath = "";
    private String giftId;
    private String giftName;
    private String giftPic;
    private String giftPrice;
    private int giftShowTime = 3000;
    private int giftTotalCount = 0;
    private String giftTypeId;
    private int hitCombo;
    public boolean isProp;
    private String jsonName = "";
    private int jumpCombo;
    public String onLineUrl;
    private Long sendGiftTime;
    private int sendIndex;
    private String sendUserId;
    private String sendUserName;
    private String sendUserPic;

    public boolean isPropPlay() {
        return TextUtils.equals(ConnectSocketParams.EFFECT_TYPE_BIG, this.effectType) ^ 1;
    }

    public GiftAnimModel setOnLineUrl(String str) {
        this.onLineUrl = str;
        return this;
    }

    public String getEffectType() {
        return this.effectType == null ? "" : this.effectType;
    }

    public GiftAnimModel setEffectType(String str) {
        this.effectType = str;
        return this;
    }

    public int getSendIndex() {
        return this.sendIndex;
    }

    public GiftAnimModel setSendIndex(int i) {
        this.sendIndex = i;
        return this;
    }

    public GiftAnimModel setProp(boolean z) {
        this.isProp = z;
        return this;
    }

    public String getGiftId() {
        return this.giftId;
    }

    public GiftAnimModel setGiftId(String str) {
        this.giftId = str;
        return this;
    }

    public String getGiftName() {
        return this.giftName;
    }

    public GiftAnimModel setGiftName(String str) {
        this.giftName = str;
        return this;
    }

    public int getGiftCount() {
        return this.giftCount;
    }

    public GiftAnimModel setGiftCount(int i) {
        this.giftCount = i;
        return this;
    }

    public String getSendUserId() {
        return this.sendUserId;
    }

    public GiftAnimModel setSendUserId(String str) {
        this.sendUserId = str;
        return this;
    }

    public String getSendUserName() {
        return this.sendUserName;
    }

    public GiftAnimModel setSendUserName(String str) {
        this.sendUserName = str;
        return this;
    }

    public String getSendUserPic() {
        return this.sendUserPic;
    }

    public GiftAnimModel setSendUserPic(String str) {
        this.sendUserPic = str;
        return this;
    }

    public String getGiftPic() {
        return this.giftPic;
    }

    public GiftAnimModel setGiftPic(String str) {
        this.giftPic = str;
        return this;
    }

    public String getGiftPrice() {
        return this.giftPrice;
    }

    public GiftAnimModel setGiftPrice(String str) {
        this.giftPrice = str;
        return this;
    }

    public int getHitCombo() {
        return this.hitCombo;
    }

    public GiftAnimModel setHitCombo(int i) {
        this.hitCombo = i;
        return this;
    }

    public Long getSendGiftTime() {
        return this.sendGiftTime;
    }

    public GiftAnimModel setSendGiftTime(Long l) {
        this.sendGiftTime = l;
        return this;
    }

    public boolean isCurrentStart() {
        return this.currentStart;
    }

    public GiftAnimModel setCurrentStart(boolean z) {
        this.currentStart = z;
        return this;
    }

    public int getJumpCombo() {
        return this.jumpCombo;
    }

    public void setJumpCombo(int i) {
        this.jumpCombo = i;
    }

    public String getGiftDirPath() {
        return this.giftDirPath;
    }

    public GiftAnimModel setGiftDirPath(String str) {
        this.giftDirPath = str;
        return this;
    }

    public String getJsonName() {
        return this.jsonName;
    }

    public GiftAnimModel setJsonName(String str) {
        this.jsonName = str;
        return this;
    }

    public int getGiftShowTime() {
        return this.giftShowTime;
    }

    public GiftAnimModel setGiftShowTime(int i) {
        this.giftShowTime = i;
        return this;
    }

    public BarrageEndAnimationListener getAnimationListener() {
        return this.animationListener;
    }

    public GiftAnimModel setAnimationListener(BarrageEndAnimationListener barrageEndAnimationListener) {
        this.animationListener = barrageEndAnimationListener;
        return this;
    }

    public String getGiftTypeId() {
        return this.giftTypeId;
    }

    public GiftAnimModel setGiftTypeId(String str) {
        this.giftTypeId = str;
        return this;
    }

    public int getGiftTotalCount() {
        return this.giftTotalCount;
    }

    public void setGiftTotalCount(int i) {
        this.giftTotalCount = i;
    }

    public boolean isStartLottieAnim() {
        return TextUtils.isEmpty(this.giftDirPath) ^ 1;
    }
}
