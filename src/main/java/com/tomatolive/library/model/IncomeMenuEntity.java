package com.tomatolive.library.model;

import android.text.TextUtils;

public class IncomeMenuEntity {
    private String giftIncome = "";
    private String guardIncome = "";
    private String propIncome = "";
    private String totalIncome = "";

    public IncomeMenuEntity(String str, String str2, String str3) {
        this.totalIncome = str;
        this.giftIncome = str2;
        this.guardIncome = str3;
    }

    public String getTotalIcome() {
        if (TextUtils.isEmpty(this.totalIncome)) {
            return "0";
        }
        return this.totalIncome;
    }

    public void setTotalIncome(String str) {
        this.totalIncome = str;
    }

    public String getGiftIncome() {
        if (TextUtils.isEmpty(this.giftIncome)) {
            return "0";
        }
        return this.giftIncome;
    }

    public void setGiftIncome(String str) {
        this.giftIncome = str;
    }

    public String getGuardIncome() {
        if (TextUtils.isEmpty(this.guardIncome)) {
            return "0";
        }
        return this.guardIncome;
    }

    public void setGuardIncome(String str) {
        this.guardIncome = str;
    }

    public String getPropsIncome() {
        return TextUtils.isEmpty(this.propIncome) ? "0" : this.propIncome;
    }

    public void setPropsIncome(String str) {
        this.propIncome = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("IncomeMenuEntity{totalIncome='");
        stringBuilder.append(this.totalIncome);
        stringBuilder.append('\'');
        stringBuilder.append(", giftIncome='");
        stringBuilder.append(this.giftIncome);
        stringBuilder.append('\'');
        stringBuilder.append(", guardIncome='");
        stringBuilder.append(this.guardIncome);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
