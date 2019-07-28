package defpackage;

import com.tomatolive.library.ui.view.widget.matisse.filter.Filter;
import defpackage.md.a;
import defpackage.md.c;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: CacheIOHelper */
/* renamed from: tn */
interface tn<INPUT, OUTPUT> {
    public static final tn<ub, ub> a = new 1();
    public static final tn<InputStream, InputStream> b = new 2();

    /* compiled from: CacheIOHelper */
    /* renamed from: tn$1 */
    static class 1 implements tn<ub, ub> {
        1() {
        }

        public void a(String str, ub ubVar, md mdVar) {
            if (mdVar != null) {
                try {
                    a b = mdVar.b(str);
                    if (b != null) {
                        OutputStream a = b.a(0);
                        ubVar.a(a);
                        a.flush();
                        a.close();
                        b.a();
                    }
                } catch (IOException e) {
                    ug.a(e);
                }
            }
        }

        /* renamed from: c */
        public ub a(String str, md mdVar) {
            if (mdVar != null) {
                try {
                    c a = mdVar.a(str);
                    if (a == null) {
                        return null;
                    }
                    InputStream a2 = a.a(0);
                    ub a3 = ub.a(a2, str);
                    a2.close();
                    return a3;
                } catch (IOException e) {
                    ug.a(e);
                }
            }
            return null;
        }

        public boolean b(String str, md mdVar) {
            boolean z = false;
            if (mdVar != null) {
                try {
                    if (mdVar.a(str) != null) {
                        z = true;
                    }
                    return z;
                } catch (IOException e) {
                    ug.a(e);
                }
            }
            return false;
        }
    }

    /* compiled from: CacheIOHelper */
    /* renamed from: tn$2 */
    static class 2 implements tn<InputStream, InputStream> {
        2() {
        }

        public void a(String str, InputStream inputStream, md mdVar) {
            if (mdVar != null) {
                try {
                    a b = mdVar.b(str);
                    if (b != null) {
                        OutputStream a = b.a(0);
                        byte[] bArr = new byte[Filter.K];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            a.write(bArr, 0, read);
                        }
                        a.flush();
                        a.close();
                        inputStream.close();
                        b.a();
                    }
                } catch (IOException e) {
                    ug.a(e);
                }
            }
        }

        /* renamed from: c */
        public InputStream a(String str, md mdVar) {
            InputStream inputStream = null;
            if (mdVar == null) {
                return null;
            }
            c a;
            try {
                a = mdVar.a(str);
            } catch (IOException e) {
                ug.a(e);
                a = null;
            }
            if (a != null) {
                inputStream = a.a(0);
            }
            return inputStream;
        }

        public boolean b(String str, md mdVar) {
            boolean z = false;
            if (mdVar != null) {
                try {
                    if (mdVar.a(str) != null) {
                        z = true;
                    }
                    return z;
                } catch (IOException e) {
                    ug.a(e);
                }
            }
            return false;
        }
    }

    OUTPUT a(String str, md mdVar);

    void a(String str, INPUT input, md mdVar);

    boolean b(String str, md mdVar);
}
