package org.xutils.ex;

import android.text.TextUtils;

public class HttpException extends BaseException {
    private int code;
    private String customMessage;
    private String errorCode;
    private String result;

    public HttpException(int i, String str) {
        super(str);
        this.code = i;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMessage(String str) {
        this.customMessage = str;
    }

    public int getCode() {
        return this.code;
    }

    public String getErrorCode() {
        return this.errorCode == null ? String.valueOf(this.code) : this.errorCode;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public String getMessage() {
        if (TextUtils.isEmpty(this.customMessage)) {
            return super.getMessage();
        }
        return this.customMessage;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("errorCode: ");
        stringBuilder.append(getErrorCode());
        stringBuilder.append(", msg: ");
        stringBuilder.append(getMessage());
        stringBuilder.append(", result: ");
        stringBuilder.append(this.result);
        return stringBuilder.toString();
    }
}
