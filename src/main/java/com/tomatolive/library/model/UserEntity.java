package com.tomatolive.library.model;

import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;
import com.tomatolive.library.utils.p;
import java.io.Serializable;

public class UserEntity implements Serializable, Comparable<UserEntity> {
    private String avatar;
    private long balance = 0;
    private String expGrade;
    private int fans = 0;
    private int follow = 0;
    private int guardType;
    private String id;
    @SerializedName("userName")
    private String name;
    private String number;
    private String phone;
    private String role;
    private String sex;
    private String status;
    private String token;
    private String totResult;
    private String type;
    private String userId;
    private String userSig;
    private String userType;

    public String getRole() {
        return this.role;
    }

    public void setRole(String str) {
        this.role = str;
    }

    public int getWeight() {
        return (this.guardType * 1000) + p.a(this.expGrade);
    }

    public int getGuardType() {
        return this.guardType;
    }

    public void setGuardType(int i) {
        this.guardType = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String str) {
        this.sex = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public String getUserSig() {
        return this.userSig;
    }

    public void setUserSig(String str) {
        this.userSig = str;
    }

    public int getAttention() {
        return this.follow;
    }

    public void setAttention(int i) {
        this.follow = i;
    }

    public int getFans() {
        return this.fans;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String str) {
        this.userType = str;
    }

    public void setFans(int i) {
        this.fans = i;
    }

    public long getBalance() {
        return this.balance;
    }

    public void setBalance(long j) {
        this.balance = j;
    }

    public String getExpGrade() {
        return this.expGrade;
    }

    public void setExpGrade(String str) {
        this.expGrade = str;
    }

    public String getTotResult() {
        return this.totResult;
    }

    public void setTotResult(String str) {
        this.totResult = str;
    }

    public int compareTo(@NonNull UserEntity userEntity) {
        return getWeight() > userEntity.getWeight() ? -1 : 1;
    }
}
