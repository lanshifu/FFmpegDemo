package com.zzhoujay.richtext.exceptions;

public class HttpResponseCodeException extends RuntimeException {
    public HttpResponseCodeException(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Http Response Code is :");
        stringBuilder.append(i);
        super(stringBuilder.toString());
    }
}
