package net.vidageek.mirror.thirdparty.org.objenesis;

public class ObjenesisException extends RuntimeException {
    private static final boolean jdk14 = (Double.parseDouble(System.getProperty("java.specification.version")) > 1.3d);
    private static final long serialVersionUID = -2677230016262426968L;

    public ObjenesisException(String str) {
        super(str);
    }

    public ObjenesisException(Throwable th) {
        super(th == null ? null : th.toString());
        if (jdk14) {
            initCause(th);
        }
    }

    public ObjenesisException(String str, Throwable th) {
        super(str);
        if (jdk14) {
            initCause(th);
        }
    }
}
