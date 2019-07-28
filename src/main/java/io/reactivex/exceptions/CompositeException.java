package io.reactivex.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

public final class CompositeException extends RuntimeException {
    private static final long serialVersionUID = 3026362227162912146L;
    private Throwable cause;
    private final List<Throwable> exceptions;
    private final String message;

    static final class CompositeExceptionCausalChain extends RuntimeException {
        static final String MESSAGE = "Chain of Causes for CompositeException In Order Received =>";
        private static final long serialVersionUID = 3875212506787802066L;

        public String getMessage() {
            return MESSAGE;
        }

        CompositeExceptionCausalChain() {
        }
    }

    static abstract class a {
        public abstract void a(Object obj);

        a() {
        }
    }

    static final class b extends a {
        private final PrintStream a;

        b(PrintStream printStream) {
            this.a = printStream;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(Object obj) {
            this.a.println(obj);
        }
    }

    static final class c extends a {
        private final PrintWriter a;

        c(PrintWriter printWriter) {
            this.a = printWriter;
        }

        /* Access modifiers changed, original: 0000 */
        public void a(Object obj) {
            this.a.println(obj);
        }
    }

    public CompositeException(Throwable... thArr) {
        this(thArr == null ? Collections.singletonList(new NullPointerException("exceptions was null")) : Arrays.asList(thArr));
    }

    public CompositeException(Iterable<? extends Throwable> iterable) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        if (iterable != null) {
            for (Throwable th : iterable) {
                if (th instanceof CompositeException) {
                    linkedHashSet.addAll(((CompositeException) th).getExceptions());
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException("Throwable was null!"));
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException("errors was null"));
        }
        if (linkedHashSet.isEmpty()) {
            throw new IllegalArgumentException("errors is empty");
        }
        arrayList.addAll(linkedHashSet);
        this.exceptions = Collections.unmodifiableList(arrayList);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.exceptions.size());
        stringBuilder.append(" exceptions occurred. ");
        this.message = stringBuilder.toString();
    }

    public List<Throwable> getExceptions() {
        return this.exceptions;
    }

    public String getMessage() {
        return this.message;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0055 */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:10|(4:13|(2:15|34)(3:16|17|35)|33|11)|18|19|20|21|32) */
    public synchronized java.lang.Throwable getCause() {
        /*
        r8 = this;
        monitor-enter(r8);
        r0 = r8.cause;	 Catch:{ all -> 0x0060 }
        if (r0 != 0) goto L_0x005c;
    L_0x0005:
        r0 = new io.reactivex.exceptions.CompositeException$CompositeExceptionCausalChain;	 Catch:{ all -> 0x0060 }
        r0.<init>();	 Catch:{ all -> 0x0060 }
        r1 = new java.util.HashSet;	 Catch:{ all -> 0x0060 }
        r1.<init>();	 Catch:{ all -> 0x0060 }
        r2 = r8.exceptions;	 Catch:{ all -> 0x0060 }
        r2 = r2.iterator();	 Catch:{ all -> 0x0060 }
        r3 = r0;
    L_0x0016:
        r4 = r2.hasNext();	 Catch:{ all -> 0x0060 }
        if (r4 == 0) goto L_0x005a;
    L_0x001c:
        r4 = r2.next();	 Catch:{ all -> 0x0060 }
        r4 = (java.lang.Throwable) r4;	 Catch:{ all -> 0x0060 }
        r5 = r1.contains(r4);	 Catch:{ all -> 0x0060 }
        if (r5 == 0) goto L_0x0029;
    L_0x0028:
        goto L_0x0016;
    L_0x0029:
        r1.add(r4);	 Catch:{ all -> 0x0060 }
        r5 = r8.a(r4);	 Catch:{ all -> 0x0060 }
        r5 = r5.iterator();	 Catch:{ all -> 0x0060 }
    L_0x0034:
        r6 = r5.hasNext();	 Catch:{ all -> 0x0060 }
        if (r6 == 0) goto L_0x0052;
    L_0x003a:
        r6 = r5.next();	 Catch:{ all -> 0x0060 }
        r6 = (java.lang.Throwable) r6;	 Catch:{ all -> 0x0060 }
        r7 = r1.contains(r6);	 Catch:{ all -> 0x0060 }
        if (r7 == 0) goto L_0x004e;
    L_0x0046:
        r4 = new java.lang.RuntimeException;	 Catch:{ all -> 0x0060 }
        r6 = "Duplicate found in causal chain so cropping to prevent loop ...";
        r4.<init>(r6);	 Catch:{ all -> 0x0060 }
        goto L_0x0034;
    L_0x004e:
        r1.add(r6);	 Catch:{ all -> 0x0060 }
        goto L_0x0034;
    L_0x0052:
        r3.initCause(r4);	 Catch:{ Throwable -> 0x0055 }
    L_0x0055:
        r3 = r8.getRootCause(r3);	 Catch:{ all -> 0x0060 }
        goto L_0x0016;
    L_0x005a:
        r8.cause = r0;	 Catch:{ all -> 0x0060 }
    L_0x005c:
        r0 = r8.cause;	 Catch:{ all -> 0x0060 }
        monitor-exit(r8);
        return r0;
    L_0x0060:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.exceptions.CompositeException.getCause():java.lang.Throwable");
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        a(new b(printStream));
    }

    public void printStackTrace(PrintWriter printWriter) {
        a(new c(printWriter));
    }

    private void a(a aVar) {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(this);
        stringBuilder.append(10);
        for (Object obj : getStackTrace()) {
            stringBuilder.append("\tat ");
            stringBuilder.append(obj);
            stringBuilder.append(10);
        }
        int i = 1;
        for (Throwable th : this.exceptions) {
            stringBuilder.append("  ComposedException ");
            stringBuilder.append(i);
            stringBuilder.append(" :\n");
            a(stringBuilder, th, "\t");
            i++;
        }
        aVar.a(stringBuilder.toString());
    }

    private void a(StringBuilder stringBuilder, Throwable th, String str) {
        stringBuilder.append(str);
        stringBuilder.append(th);
        stringBuilder.append(10);
        for (Object obj : th.getStackTrace()) {
            stringBuilder.append("\t\tat ");
            stringBuilder.append(obj);
            stringBuilder.append(10);
        }
        if (th.getCause() != null) {
            stringBuilder.append("\tCaused by: ");
            a(stringBuilder, th.getCause(), "");
        }
    }

    private List<Throwable> a(Throwable th) {
        ArrayList arrayList = new ArrayList();
        Throwable cause = th.getCause();
        if (cause == null || cause == th) {
            return arrayList;
        }
        while (true) {
            arrayList.add(cause);
            th = cause.getCause();
            if (th == null || th == cause) {
                return arrayList;
            }
            cause = th;
        }
        return arrayList;
    }

    public int size() {
        return this.exceptions.size();
    }

    /* Access modifiers changed, original: 0000 */
    public Throwable getRootCause(Throwable th) {
        Throwable cause = th.getCause();
        if (cause == null || this.cause == cause) {
            return th;
        }
        while (true) {
            th = cause.getCause();
            if (th == null || th == cause) {
                return cause;
            }
            cause = th;
        }
        return cause;
    }
}
