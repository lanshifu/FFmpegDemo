package org.xutils.ex;

public class HttpRedirectException extends HttpException {
    private static final long serialVersionUID = 1;

    public HttpRedirectException(int i, String str, String str2) {
        super(i, str);
        setResult(str2);
    }
}
