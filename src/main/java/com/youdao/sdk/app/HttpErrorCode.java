package com.youdao.sdk.app;

public enum HttpErrorCode {
    EMPTY_RESPONSE("Server returned empty response."),
    UNSPECIFICERROR("unspecified error occured."),
    REQUEST_ERROR("http request error.");
    
    private final int code;
    private final String message;

    public int getCode() {
        return this.code;
    }

    private HttpErrorCode(String str) {
        this.message = str;
        this.code = 0;
    }

    public final String toString() {
        return this.message;
    }
}
