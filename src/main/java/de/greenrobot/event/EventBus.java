package de.greenrobot.event;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

public class EventBus {
    private static final EventBusBuilder DEFAULT_BUILDER = new EventBusBuilder();
    public static String TAG = "Event";
    static volatile EventBus defaultInstance;
    private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap();
    private final AsyncPoster asyncPoster;
    private final BackgroundPoster backgroundPoster;
    private final ThreadLocal<PostingThreadState> currentPostingThreadState;
    private final boolean eventInheritance;
    private final ExecutorService executorService;
    private final boolean logNoSubscriberMessages;
    private final boolean logSubscriberExceptions;
    private final HandlerPoster mainThreadPoster;
    private final boolean sendNoSubscriberEvent;
    private final boolean sendSubscriberExceptionEvent;
    private final Map<Class<?>, Object> stickyEvents;
    private final SubscriberMethodFinder subscriberMethodFinder;
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;
    private final boolean throwSubscriberException;
    private final Map<Object, List<Class<?>>> typesBySubscriber;

    interface PostCallback {
        void onPostCompleted(List<SubscriberExceptionEvent> list);
    }

    static final class PostingThreadState {
        boolean canceled;
        Object event;
        final List<Object> eventQueue = new ArrayList();
        boolean isMainThread;
        boolean isPosting;
        Subscription subscription;

        PostingThreadState() {
        }
    }

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }

    public static EventBusBuilder builder() {
        return new EventBusBuilder();
    }

    public static void clearCaches() {
        SubscriberMethodFinder.clearCaches();
        eventTypesCache.clear();
    }

    public EventBus() {
        this(DEFAULT_BUILDER);
    }

    EventBus(EventBusBuilder eventBusBuilder) {
        this.currentPostingThreadState = new ThreadLocal<PostingThreadState>() {
            /* Access modifiers changed, original: protected */
            public PostingThreadState initialValue() {
                return new PostingThreadState();
            }
        };
        this.subscriptionsByEventType = new HashMap();
        this.typesBySubscriber = new HashMap();
        this.stickyEvents = new ConcurrentHashMap();
        this.mainThreadPoster = new HandlerPoster(this, Looper.getMainLooper(), 10);
        this.backgroundPoster = new BackgroundPoster(this);
        this.asyncPoster = new AsyncPoster(this);
        this.subscriberMethodFinder = new SubscriberMethodFinder(eventBusBuilder.skipMethodVerificationForClasses);
        this.logSubscriberExceptions = eventBusBuilder.logSubscriberExceptions;
        this.logNoSubscriberMessages = eventBusBuilder.logNoSubscriberMessages;
        this.sendSubscriberExceptionEvent = eventBusBuilder.sendSubscriberExceptionEvent;
        this.sendNoSubscriberEvent = eventBusBuilder.sendNoSubscriberEvent;
        this.throwSubscriberException = eventBusBuilder.throwSubscriberException;
        this.eventInheritance = eventBusBuilder.eventInheritance;
        this.executorService = eventBusBuilder.executorService;
    }

    public void register(Object obj) {
        register(obj, false, 0);
    }

    public void register(Object obj, int i) {
        register(obj, false, i);
    }

    public void registerSticky(Object obj) {
        register(obj, true, 0);
    }

    public void registerSticky(Object obj, int i) {
        register(obj, true, i);
    }

    private synchronized void register(Object obj, boolean z, int i) {
        for (SubscriberMethod subscribe : this.subscriberMethodFinder.findSubscriberMethods(obj.getClass())) {
            subscribe(obj, subscribe, z, i);
        }
    }

    private void subscribe(Object obj, SubscriberMethod subscriberMethod, boolean z, int i) {
        Class cls = subscriberMethod.eventType;
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.subscriptionsByEventType.get(cls);
        Subscription subscription = new Subscription(obj, subscriberMethod, i);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.subscriptionsByEventType.put(cls, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(subscription)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Subscriber ");
            stringBuilder.append(obj.getClass());
            stringBuilder.append(" already registered to event ");
            stringBuilder.append(cls);
            throw new EventBusException(stringBuilder.toString());
        }
        int size = copyOnWriteArrayList.size();
        boolean z2 = false;
        int i2 = 0;
        while (i2 <= size) {
            if (i2 == size || subscription.priority > ((Subscription) copyOnWriteArrayList.get(i2)).priority) {
                copyOnWriteArrayList.add(i2, subscription);
                break;
            }
            i2++;
        }
        List list = (List) this.typesBySubscriber.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.typesBySubscriber.put(obj, list);
        }
        list.add(cls);
        if (z) {
            Object obj2;
            synchronized (this.stickyEvents) {
                obj2 = this.stickyEvents.get(cls);
            }
            if (obj2 != null) {
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    z2 = true;
                }
                postToSubscription(subscription, obj2, z2);
            }
        }
    }

    public synchronized boolean isRegistered(Object obj) {
        return this.typesBySubscriber.containsKey(obj);
    }

    private void unubscribeByEventType(Object obj, Class<?> cls) {
        List list = (List) this.subscriptionsByEventType.get(cls);
        if (list != null) {
            int size = list.size();
            int i = 0;
            while (i < size) {
                Subscription subscription = (Subscription) list.get(i);
                if (subscription.subscriber == obj) {
                    subscription.active = false;
                    list.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    public synchronized void unregister(Object obj) {
        List<Class> list = (List) this.typesBySubscriber.get(obj);
        if (list != null) {
            for (Class unubscribeByEventType : list) {
                unubscribeByEventType(obj, unubscribeByEventType);
            }
            this.typesBySubscriber.remove(obj);
        } else {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Subscriber to unregister was not registered before: ");
            stringBuilder.append(obj.getClass());
            Log.w(str, stringBuilder.toString());
        }
    }

    public void post(Object obj) {
        PostingThreadState postingThreadState = (PostingThreadState) this.currentPostingThreadState.get();
        List list = postingThreadState.eventQueue;
        list.add(obj);
        if (!postingThreadState.isPosting) {
            postingThreadState.isMainThread = Looper.getMainLooper() == Looper.myLooper();
            postingThreadState.isPosting = true;
            if (postingThreadState.canceled) {
                throw new EventBusException("Internal error. Abort state was not reset");
            }
            while (!list.isEmpty()) {
                try {
                    postSingleEvent(list.remove(0), postingThreadState);
                } finally {
                    postingThreadState.isPosting = false;
                    postingThreadState.isMainThread = false;
                }
            }
        }
    }

    public void cancelEventDelivery(Object obj) {
        PostingThreadState postingThreadState = (PostingThreadState) this.currentPostingThreadState.get();
        if (!postingThreadState.isPosting) {
            throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
        } else if (obj == null) {
            throw new EventBusException("Event may not be null");
        } else if (postingThreadState.event != obj) {
            throw new EventBusException("Only the currently handled event may be aborted");
        } else if (postingThreadState.subscription.subscriberMethod.threadMode == ThreadMode.PostThread) {
            postingThreadState.canceled = true;
        } else {
            throw new EventBusException(" event handlers may only abort the incoming event");
        }
    }

    public void postSticky(Object obj) {
        synchronized (this.stickyEvents) {
            this.stickyEvents.put(obj.getClass(), obj);
        }
        post(obj);
    }

    public <T> T getStickyEvent(Class<T> cls) {
        Object cast;
        synchronized (this.stickyEvents) {
            cast = cls.cast(this.stickyEvents.get(cls));
        }
        return cast;
    }

    public <T> T removeStickyEvent(Class<T> cls) {
        Object cast;
        synchronized (this.stickyEvents) {
            cast = cls.cast(this.stickyEvents.remove(cls));
        }
        return cast;
    }

    public boolean removeStickyEvent(Object obj) {
        synchronized (this.stickyEvents) {
            Class cls = obj.getClass();
            if (obj.equals(this.stickyEvents.get(cls))) {
                this.stickyEvents.remove(cls);
                return true;
            }
            return false;
        }
    }

    public void removeAllStickyEvents() {
        synchronized (this.stickyEvents) {
            this.stickyEvents.clear();
        }
    }

    public boolean hasSubscriberForEvent(Class<?> cls) {
        List lookupAllEventTypes = lookupAllEventTypes(cls);
        if (lookupAllEventTypes != null) {
            int size = lookupAllEventTypes.size();
            for (int i = 0; i < size; i++) {
                CopyOnWriteArrayList copyOnWriteArrayList;
                Class cls2 = (Class) lookupAllEventTypes.get(i);
                synchronized (this) {
                    copyOnWriteArrayList = (CopyOnWriteArrayList) this.subscriptionsByEventType.get(cls2);
                }
                if (copyOnWriteArrayList != null && !copyOnWriteArrayList.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void postSingleEvent(Object obj, PostingThreadState postingThreadState) throws Error {
        int i;
        Class cls = obj.getClass();
        if (this.eventInheritance) {
            List lookupAllEventTypes = lookupAllEventTypes(cls);
            i = 0;
            for (int i2 = 0; i2 < lookupAllEventTypes.size(); i2++) {
                i |= postSingleEventForEventType(obj, postingThreadState, (Class) lookupAllEventTypes.get(i2));
            }
        } else {
            i = postSingleEventForEventType(obj, postingThreadState, cls);
        }
        if (i == 0) {
            if (this.logNoSubscriberMessages) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("No subscribers registered for event ");
                stringBuilder.append(cls);
                Log.d(str, stringBuilder.toString());
            }
            if (this.sendNoSubscriberEvent && cls != NoSubscriberEvent.class && cls != SubscriberExceptionEvent.class) {
                post(new NoSubscriberEvent(this, obj));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0040 A:{SYNTHETIC, EDGE_INSN: B:24:0x0040->B:17:0x0040 ?: BREAK  } */
    private boolean postSingleEventForEventType(java.lang.Object r5, de.greenrobot.event.EventBus.PostingThreadState r6, java.lang.Class<?> r7) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.subscriptionsByEventType;	 Catch:{ all -> 0x0043 }
        r7 = r0.get(r7);	 Catch:{ all -> 0x0043 }
        r7 = (java.util.concurrent.CopyOnWriteArrayList) r7;	 Catch:{ all -> 0x0043 }
        monitor-exit(r4);	 Catch:{ all -> 0x0043 }
        r0 = 0;
        if (r7 == 0) goto L_0x0042;
    L_0x000d:
        r1 = r7.isEmpty();
        if (r1 != 0) goto L_0x0042;
    L_0x0013:
        r7 = r7.iterator();
    L_0x0017:
        r1 = r7.hasNext();
        if (r1 == 0) goto L_0x0040;
    L_0x001d:
        r1 = r7.next();
        r1 = (de.greenrobot.event.Subscription) r1;
        r6.event = r5;
        r6.subscription = r1;
        r2 = 0;
        r3 = r6.isMainThread;	 Catch:{ all -> 0x0038 }
        r4.postToSubscription(r1, r5, r3);	 Catch:{ all -> 0x0038 }
        r1 = r6.canceled;	 Catch:{ all -> 0x0038 }
        r6.event = r2;
        r6.subscription = r2;
        r6.canceled = r0;
        if (r1 == 0) goto L_0x0017;
    L_0x0037:
        goto L_0x0040;
    L_0x0038:
        r5 = move-exception;
        r6.event = r2;
        r6.subscription = r2;
        r6.canceled = r0;
        throw r5;
    L_0x0040:
        r5 = 1;
        return r5;
    L_0x0042:
        return r0;
    L_0x0043:
        r5 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0043 }
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: de.greenrobot.event.EventBus.postSingleEventForEventType(java.lang.Object, de.greenrobot.event.EventBus$PostingThreadState, java.lang.Class):boolean");
    }

    private void postToSubscription(Subscription subscription, Object obj, boolean z) {
        switch (subscription.subscriberMethod.threadMode) {
            case PostThread:
                invokeSubscriber(subscription, obj);
                return;
            case MainThread:
                if (z) {
                    invokeSubscriber(subscription, obj);
                    return;
                } else {
                    this.mainThreadPoster.enqueue(subscription, obj);
                    return;
                }
            case BackgroundThread:
                if (z) {
                    this.backgroundPoster.enqueue(subscription, obj);
                    return;
                } else {
                    invokeSubscriber(subscription, obj);
                    return;
                }
            case Async:
                this.asyncPoster.enqueue(subscription, obj);
                return;
            default:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Unknown thread mode: ");
                stringBuilder.append(subscription.subscriberMethod.threadMode);
                throw new IllegalStateException(stringBuilder.toString());
        }
    }

    private List<Class<?>> lookupAllEventTypes(Class<?> cls) {
        List<Class<?>> list;
        synchronized (eventTypesCache) {
            list = (List) eventTypesCache.get(cls);
            if (list == null) {
                list = new ArrayList();
                for (Object obj = cls; obj != null; obj = obj.getSuperclass()) {
                    list.add(obj);
                    addInterfaces(list, obj.getInterfaces());
                }
                eventTypesCache.put(cls, list);
            }
        }
        return list;
    }

    static void addInterfaces(List<Class<?>> list, Class<?>[] clsArr) {
        for (Object obj : clsArr) {
            if (!list.contains(obj)) {
                list.add(obj);
                addInterfaces(list, obj.getInterfaces());
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void invokeSubscriber(PendingPost pendingPost) {
        Object obj = pendingPost.event;
        Subscription subscription = pendingPost.subscription;
        PendingPost.releasePendingPost(pendingPost);
        if (subscription.active) {
            invokeSubscriber(subscription, obj);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void invokeSubscriber(Subscription subscription, Object obj) {
        try {
            subscription.subscriberMethod.method.invoke(subscription.subscriber, new Object[]{obj});
        } catch (InvocationTargetException e) {
            handleSubscriberException(subscription, obj, e.getCause());
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    private void handleSubscriberException(Subscription subscription, Object obj, Throwable th) {
        String str;
        StringBuilder stringBuilder;
        if (obj instanceof SubscriberExceptionEvent) {
            if (this.logSubscriberExceptions) {
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("SubscriberExceptionEvent subscriber ");
                stringBuilder.append(subscription.subscriber.getClass());
                stringBuilder.append(" threw an exception");
                Log.e(str, stringBuilder.toString(), th);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                String str2 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Initial event ");
                stringBuilder2.append(subscriberExceptionEvent.causingEvent);
                stringBuilder2.append(" caused exception in ");
                stringBuilder2.append(subscriberExceptionEvent.causingSubscriber);
                Log.e(str2, stringBuilder2.toString(), subscriberExceptionEvent.throwable);
            }
        } else if (this.throwSubscriberException) {
            throw new EventBusException("Invoking subscriber failed", th);
        } else {
            if (this.logSubscriberExceptions) {
                str = TAG;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Could not dispatch event: ");
                stringBuilder.append(obj.getClass());
                stringBuilder.append(" to subscribing class ");
                stringBuilder.append(subscription.subscriber.getClass());
                Log.e(str, stringBuilder.toString(), th);
            }
            if (this.sendSubscriberExceptionEvent) {
                post(new SubscriberExceptionEvent(this, th, obj, subscription.subscriber));
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public ExecutorService getExecutorService() {
        return this.executorService;
    }
}
