package io.reactivex.internal.subscriptions;

import defpackage.xk;
import io.reactivex.exceptions.ProtocolViolationException;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public enum SubscriptionHelper implements aas {
    CANCELLED;

    public void cancel() {
    }

    public void request(long j) {
    }

    public static boolean validate(aas aas, aas aas2) {
        if (aas2 == null) {
            xk.a(new NullPointerException("next is null"));
            return false;
        } else if (aas == null) {
            return true;
        } else {
            aas2.cancel();
            reportSubscriptionSet();
            return false;
        }
    }

    public static void reportSubscriptionSet() {
        xk.a(new ProtocolViolationException("Subscription already set!"));
    }

    public static boolean validate(long j) {
        if (j > 0) {
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("n > 0 required but it was ");
        stringBuilder.append(j);
        xk.a(new IllegalArgumentException(stringBuilder.toString()));
        return false;
    }

    public static void reportMoreProduced(long j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("More produced than requested: ");
        stringBuilder.append(j);
        xk.a(new ProtocolViolationException(stringBuilder.toString()));
    }

    public static boolean isCancelled(aas aas) {
        return aas == CANCELLED;
    }

    public static boolean set(AtomicReference<aas> atomicReference, aas aas) {
        aas aas2;
        do {
            aas2 = (aas) atomicReference.get();
            if (aas2 == CANCELLED) {
                if (aas != null) {
                    aas.cancel();
                }
                return false;
            }
        } while (!atomicReference.compareAndSet(aas2, aas));
        if (aas2 != null) {
            aas2.cancel();
        }
        return true;
    }

    public static boolean setOnce(AtomicReference<aas> atomicReference, aas aas) {
        a.a((Object) aas, "s is null");
        if (atomicReference.compareAndSet(null, aas)) {
            return true;
        }
        aas.cancel();
        if (atomicReference.get() != CANCELLED) {
            reportSubscriptionSet();
        }
        return false;
    }

    public static boolean replace(AtomicReference<aas> atomicReference, aas aas) {
        aas aas2;
        do {
            aas2 = (aas) atomicReference.get();
            if (aas2 == CANCELLED) {
                if (aas != null) {
                    aas.cancel();
                }
                return false;
            }
        } while (!atomicReference.compareAndSet(aas2, aas));
        return true;
    }

    public static boolean cancel(AtomicReference<aas> atomicReference) {
        if (((aas) atomicReference.get()) != CANCELLED) {
            aas aas = (aas) atomicReference.getAndSet(CANCELLED);
            if (aas != CANCELLED) {
                if (aas != null) {
                    aas.cancel();
                }
                return true;
            }
        }
        return false;
    }

    public static boolean deferredSetOnce(AtomicReference<aas> atomicReference, AtomicLong atomicLong, aas aas) {
        if (!setOnce(atomicReference, aas)) {
            return false;
        }
        long andSet = atomicLong.getAndSet(0);
        if (andSet != 0) {
            aas.request(andSet);
        }
        return true;
    }

    public static void deferredRequest(AtomicReference<aas> atomicReference, AtomicLong atomicLong, long j) {
        aas aas = (aas) atomicReference.get();
        if (aas != null) {
            aas.request(j);
        } else if (validate(j)) {
            b.a(atomicLong, j);
            aas aas2 = (aas) atomicReference.get();
            if (aas2 != null) {
                long andSet = atomicLong.getAndSet(0);
                if (andSet != 0) {
                    aas2.request(andSet);
                }
            }
        }
    }

    public static boolean setOnce(AtomicReference<aas> atomicReference, aas aas, long j) {
        if (!setOnce(atomicReference, aas)) {
            return false;
        }
        aas.request(j);
        return true;
    }
}
