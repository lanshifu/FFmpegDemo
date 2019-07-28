package com.tomatolive.library.model;

import android.text.TextUtils;

public class ExpenseMenuEntity {
    private String carExpense = "";
    private String giftExpense = "";
    private String guardExpense = "";
    private String propExpense = "";
    private String totalExpense = "";

    public String getTotalExpense() {
        if (TextUtils.isEmpty(this.totalExpense)) {
            return "0";
        }
        return this.totalExpense;
    }

    public void setTotalExpense(String str) {
        this.totalExpense = str;
    }

    public String getGiftExpense() {
        if (TextUtils.isEmpty(this.giftExpense)) {
            return "0";
        }
        return this.giftExpense;
    }

    public void setGiftExpense(String str) {
        this.giftExpense = str;
    }

    public String getCarExpense() {
        if (TextUtils.isEmpty(this.carExpense)) {
            return "0";
        }
        return this.carExpense;
    }

    public void setCarExpense(String str) {
        this.carExpense = str;
    }

    public String getGuardExpense() {
        if (TextUtils.isEmpty(this.guardExpense)) {
            return "0";
        }
        return this.guardExpense;
    }

    public void setGuardExpense(String str) {
        this.guardExpense = str;
    }

    public String getPropsExpense() {
        return TextUtils.isEmpty(this.propExpense) ? "0" : this.propExpense;
    }

    public void setPropsExpense(String str) {
        this.propExpense = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ExpenseMenuEntity{totalExpense='");
        stringBuilder.append(this.totalExpense);
        stringBuilder.append('\'');
        stringBuilder.append(", giftExpense='");
        stringBuilder.append(this.giftExpense);
        stringBuilder.append('\'');
        stringBuilder.append(", carExpense='");
        stringBuilder.append(this.carExpense);
        stringBuilder.append('\'');
        stringBuilder.append(", guardExpense='");
        stringBuilder.append(this.guardExpense);
        stringBuilder.append('\'');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
