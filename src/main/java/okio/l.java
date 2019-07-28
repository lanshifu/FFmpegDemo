package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* compiled from: Okio */
public final class l {
    static final Logger a = Logger.getLogger(l.class.getName());

    private l() {
    }

    public static e a(r rVar) {
        return new n(rVar);
    }

    public static d a(q qVar) {
        return new m(qVar);
    }

    public static q a(OutputStream outputStream) {
        return a(outputStream, new s());
    }

    private static q a(final OutputStream outputStream, final s sVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (sVar != null) {
            return new q() {
                public void write(c cVar, long j) throws IOException {
                    t.a(cVar.b, 0, j);
                    while (j > 0) {
                        sVar.throwIfReached();
                        o oVar = cVar.a;
                        int min = (int) Math.min(j, (long) (oVar.c - oVar.b));
                        outputStream.write(oVar.a, oVar.b, min);
                        oVar.b += min;
                        long j2 = (long) min;
                        j -= j2;
                        cVar.b -= j2;
                        if (oVar.b == oVar.c) {
                            cVar.a = oVar.c();
                            p.a(oVar);
                        }
                    }
                }

                public void flush() throws IOException {
                    outputStream.flush();
                }

                public void close() throws IOException {
                    outputStream.close();
                }

                public s timeout() {
                    return sVar;
                }

                public String toString() {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("sink(");
                    stringBuilder.append(outputStream);
                    stringBuilder.append(")");
                    return stringBuilder.toString();
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static q a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getOutputStream() != null) {
            s c = c(socket);
            return c.sink(a(socket.getOutputStream(), c));
        } else {
            throw new IOException("socket's output stream == null");
        }
    }

    public static r a(InputStream inputStream) {
        return a(inputStream, new s());
    }

    private static r a(final InputStream inputStream, final s sVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (sVar != null) {
            return new r() {
                public long read(c cVar, long j) throws IOException {
                    if (j < 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("byteCount < 0: ");
                        stringBuilder.append(j);
                        throw new IllegalArgumentException(stringBuilder.toString());
                    } else if (j == 0) {
                        return 0;
                    } else {
                        try {
                            sVar.throwIfReached();
                            o f = cVar.f(1);
                            int read = inputStream.read(f.a, f.c, (int) Math.min(j, (long) (8192 - f.c)));
                            if (read == -1) {
                                return -1;
                            }
                            f.c += read;
                            j = (long) read;
                            cVar.b += j;
                            return j;
                        } catch (AssertionError e) {
                            if (l.a(e)) {
                                throw new IOException(e);
                            }
                            throw e;
                        }
                    }
                }

                public void close() throws IOException {
                    inputStream.close();
                }

                public s timeout() {
                    return sVar;
                }

                public String toString() {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("source(");
                    stringBuilder.append(inputStream);
                    stringBuilder.append(")");
                    return stringBuilder.toString();
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    public static r a(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static q b(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static q c(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static q a() {
        return new q() {
            public void close() throws IOException {
            }

            public void flush() throws IOException {
            }

            public void write(c cVar, long j) throws IOException {
                cVar.i(j);
            }

            public s timeout() {
                return s.NONE;
            }
        };
    }

    public static r b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getInputStream() != null) {
            s c = c(socket);
            return c.source(a(socket.getInputStream(), c));
        } else {
            throw new IOException("socket's input stream == null");
        }
    }

    private static a c(Socket socket) {
        return new 4(socket);
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
