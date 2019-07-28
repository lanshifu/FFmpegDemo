package net.vidageek.mirror.exception;

public class MirrorException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public MirrorException(String str) {
        super(str);
    }

    public MirrorException(String str, Throwable th) {
        super(str, th);
    }
}
