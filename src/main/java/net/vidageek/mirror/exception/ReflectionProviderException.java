package net.vidageek.mirror.exception;

public class ReflectionProviderException extends MirrorException {
    public ReflectionProviderException(String str) {
        super(str);
    }

    public ReflectionProviderException(String str, Throwable th) {
        super(str, th);
    }
}
