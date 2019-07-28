package tv.danmaku.ijk.media.player;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.yalantis.ucrop.view.CropImageView;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import tv.danmaku.ijk.media.player.IjkMediaMeta.IjkStreamMeta;
import tv.danmaku.ijk.media.player.annotations.AccessedByNative;
import tv.danmaku.ijk.media.player.annotations.CalledByNative;
import tv.danmaku.ijk.media.player.misc.IAndroidIO;
import tv.danmaku.ijk.media.player.misc.IMediaDataSource;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;
import tv.danmaku.ijk.media.player.pragma.DebugLog;

public final class IjkMediaPlayer extends AbstractMediaPlayer {
    public static final int FFP_PROPV_DECODER_AVCODEC = 1;
    public static final int FFP_PROPV_DECODER_MEDIACODEC = 2;
    public static final int FFP_PROPV_DECODER_UNKNOWN = 0;
    public static final int FFP_PROPV_DECODER_VIDEOTOOLBOX = 3;
    public static final int FFP_PROP_FLOAT_DROP_FRAME_RATE = 10007;
    public static final int FFP_PROP_FLOAT_PLAYBACK_RATE = 10003;
    public static final int FFP_PROP_INT64_ASYNC_STATISTIC_BUF_BACKWARDS = 20201;
    public static final int FFP_PROP_INT64_ASYNC_STATISTIC_BUF_CAPACITY = 20203;
    public static final int FFP_PROP_INT64_ASYNC_STATISTIC_BUF_FORWARDS = 20202;
    public static final int FFP_PROP_INT64_AUDIO_CACHED_BYTES = 20008;
    public static final int FFP_PROP_INT64_AUDIO_CACHED_DURATION = 20006;
    public static final int FFP_PROP_INT64_AUDIO_CACHED_PACKETS = 20010;
    public static final int FFP_PROP_INT64_AUDIO_DECODER = 20004;
    public static final int FFP_PROP_INT64_BIT_RATE = 20100;
    public static final int FFP_PROP_INT64_CACHE_STATISTIC_COUNT_BYTES = 20208;
    public static final int FFP_PROP_INT64_CACHE_STATISTIC_FILE_FORWARDS = 20206;
    public static final int FFP_PROP_INT64_CACHE_STATISTIC_FILE_POS = 20207;
    public static final int FFP_PROP_INT64_CACHE_STATISTIC_PHYSICAL_POS = 20205;
    public static final int FFP_PROP_INT64_IMMEDIATE_RECONNECT = 20211;
    public static final int FFP_PROP_INT64_LATEST_SEEK_LOAD_DURATION = 20300;
    public static final int FFP_PROP_INT64_LOGICAL_FILE_SIZE = 20209;
    public static final int FFP_PROP_INT64_SELECTED_AUDIO_STREAM = 20002;
    public static final int FFP_PROP_INT64_SELECTED_TIMEDTEXT_STREAM = 20011;
    public static final int FFP_PROP_INT64_SELECTED_VIDEO_STREAM = 20001;
    public static final int FFP_PROP_INT64_SHARE_CACHE_DATA = 20210;
    public static final int FFP_PROP_INT64_TCP_SPEED = 20200;
    public static final int FFP_PROP_INT64_TRAFFIC_STATISTIC_BYTE_COUNT = 20204;
    public static final int FFP_PROP_INT64_VIDEO_CACHED_BYTES = 20007;
    public static final int FFP_PROP_INT64_VIDEO_CACHED_DURATION = 20005;
    public static final int FFP_PROP_INT64_VIDEO_CACHED_PACKETS = 20009;
    public static final int FFP_PROP_INT64_VIDEO_DECODER = 20003;
    public static final int IJK_LOG_DEBUG = 3;
    public static final int IJK_LOG_DEFAULT = 1;
    public static final int IJK_LOG_ERROR = 6;
    public static final int IJK_LOG_FATAL = 7;
    public static final int IJK_LOG_INFO = 4;
    public static final int IJK_LOG_SILENT = 8;
    public static final int IJK_LOG_UNKNOWN = 0;
    public static final int IJK_LOG_VERBOSE = 2;
    public static final int IJK_LOG_WARN = 5;
    private static final int MEDIA_BUFFERING_UPDATE = 3;
    private static final int MEDIA_ERROR = 100;
    private static final int MEDIA_INFO = 200;
    private static final int MEDIA_NOP = 0;
    private static final int MEDIA_PLAYBACK_COMPLETE = 2;
    private static final int MEDIA_PREPARED = 1;
    private static final int MEDIA_SEEK_COMPLETE = 4;
    protected static final int MEDIA_SET_VIDEO_SAR = 10001;
    private static final int MEDIA_SET_VIDEO_SIZE = 5;
    private static final int MEDIA_TIMED_TEXT = 99;
    public static final int OPT_CATEGORY_CODEC = 2;
    public static final int OPT_CATEGORY_FORMAT = 1;
    public static final int OPT_CATEGORY_PLAYER = 4;
    public static final int OPT_CATEGORY_SWS = 3;
    public static final int PROP_FLOAT_VIDEO_DECODE_FRAMES_PER_SECOND = 10001;
    public static final int PROP_FLOAT_VIDEO_OUTPUT_FRAMES_PER_SECOND = 10002;
    public static final int SDL_FCC_RV16 = 909203026;
    public static final int SDL_FCC_RV32 = 842225234;
    public static final int SDL_FCC_YV12 = 842094169;
    private static final String TAG = "tv.danmaku.ijk.media.player.IjkMediaPlayer";
    private static volatile boolean mIsLibLoaded = false;
    private static volatile boolean mIsNativeInitialized = false;
    private static final IjkLibLoader sLocalLibLoader = new IjkLibLoader() {
        public void loadLibrary(String str) throws UnsatisfiedLinkError, SecurityException {
            System.loadLibrary(str);
        }
    };
    private String mDataSource;
    private EventHandler mEventHandler;
    @AccessedByNative
    private int mListenerContext;
    @AccessedByNative
    private long mNativeAndroidIO;
    @AccessedByNative
    private long mNativeMediaDataSource;
    @AccessedByNative
    private long mNativeMediaPlayer;
    @AccessedByNative
    private int mNativeSurfaceTexture;
    private OnControlMessageListener mOnControlMessageListener;
    private OnMediaCodecSelectListener mOnMediaCodecSelectListener;
    private OnNativeInvokeListener mOnNativeInvokeListener;
    private boolean mScreenOnWhilePlaying;
    private boolean mStayAwake;
    private SurfaceHolder mSurfaceHolder;
    private int mVideoHeight;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private WakeLock mWakeLock;

    private static class EventHandler extends Handler {
        private final WeakReference<IjkMediaPlayer> mWeakPlayer;

        public EventHandler(IjkMediaPlayer ijkMediaPlayer, Looper looper) {
            super(looper);
            this.mWeakPlayer = new WeakReference(ijkMediaPlayer);
        }

        public void handleMessage(Message message) {
            IjkMediaPlayer ijkMediaPlayer = (IjkMediaPlayer) this.mWeakPlayer.get();
            if (ijkMediaPlayer != null) {
                long j = 0;
                if (ijkMediaPlayer.mNativeMediaPlayer != 0) {
                    int i = message.what;
                    if (i != 200) {
                        if (i != 10001) {
                            switch (i) {
                                case 0:
                                    break;
                                case 1:
                                    ijkMediaPlayer.notifyOnPrepared();
                                    return;
                                case 2:
                                    ijkMediaPlayer.stayAwake(false);
                                    ijkMediaPlayer.notifyOnCompletion();
                                    return;
                                case 3:
                                    long j2 = (long) message.arg1;
                                    if (j2 < 0) {
                                        j2 = 0;
                                    }
                                    long duration = ijkMediaPlayer.getDuration();
                                    if (duration > 0) {
                                        j = (j2 * 100) / duration;
                                    }
                                    if (j >= 100) {
                                        j = 100;
                                    }
                                    ijkMediaPlayer.notifyOnBufferingUpdate((int) j);
                                    return;
                                case 4:
                                    ijkMediaPlayer.notifyOnSeekComplete();
                                    return;
                                case 5:
                                    ijkMediaPlayer.mVideoWidth = message.arg1;
                                    ijkMediaPlayer.mVideoHeight = message.arg2;
                                    ijkMediaPlayer.notifyOnVideoSizeChanged(ijkMediaPlayer.mVideoWidth, ijkMediaPlayer.mVideoHeight, ijkMediaPlayer.mVideoSarNum, ijkMediaPlayer.mVideoSarDen);
                                    return;
                                default:
                                    switch (i) {
                                        case 99:
                                            if (message.obj == null) {
                                                ijkMediaPlayer.notifyOnTimedText(null);
                                            } else {
                                                ijkMediaPlayer.notifyOnTimedText(new IjkTimedText(new Rect(0, 0, 1, 1), (String) message.obj));
                                            }
                                            return;
                                        case 100:
                                            String access$100 = IjkMediaPlayer.TAG;
                                            StringBuilder stringBuilder = new StringBuilder();
                                            stringBuilder.append("Error (");
                                            stringBuilder.append(message.arg1);
                                            stringBuilder.append(",");
                                            stringBuilder.append(message.arg2);
                                            stringBuilder.append(")");
                                            DebugLog.e(access$100, stringBuilder.toString());
                                            if (!ijkMediaPlayer.notifyOnError(message.arg1, message.arg2)) {
                                                ijkMediaPlayer.notifyOnCompletion();
                                            }
                                            ijkMediaPlayer.stayAwake(false);
                                            return;
                                        default:
                                            String access$1002 = IjkMediaPlayer.TAG;
                                            StringBuilder stringBuilder2 = new StringBuilder();
                                            stringBuilder2.append("Unknown message type ");
                                            stringBuilder2.append(message.what);
                                            DebugLog.e(access$1002, stringBuilder2.toString());
                                            break;
                                    }
                            }
                        }
                        ijkMediaPlayer.mVideoSarNum = message.arg1;
                        ijkMediaPlayer.mVideoSarDen = message.arg2;
                        ijkMediaPlayer.notifyOnVideoSizeChanged(ijkMediaPlayer.mVideoWidth, ijkMediaPlayer.mVideoHeight, ijkMediaPlayer.mVideoSarNum, ijkMediaPlayer.mVideoSarDen);
                        return;
                    }
                    if (message.arg1 == 3) {
                        DebugLog.i(IjkMediaPlayer.TAG, "Info: MEDIA_INFO_VIDEO_RENDERING_START\n");
                    }
                    ijkMediaPlayer.notifyOnInfo(message.arg1, message.arg2);
                    return;
                }
            }
            DebugLog.w(IjkMediaPlayer.TAG, "IjkMediaPlayer went away with unhandled events");
        }
    }

    public interface OnControlMessageListener {
        String onControlResolveSegmentUrl(int i);
    }

    public interface OnMediaCodecSelectListener {
        String onMediaCodecSelect(IMediaPlayer iMediaPlayer, String str, int i, int i2);
    }

    public interface OnNativeInvokeListener {
        public static final String ARG_ERROR = "error";
        public static final String ARG_FAMILIY = "family";
        public static final String ARG_FD = "fd";
        public static final String ARG_FILE_SIZE = "file_size";
        public static final String ARG_HTTP_CODE = "http_code";
        public static final String ARG_IP = "ip";
        public static final String ARG_OFFSET = "offset";
        public static final String ARG_PORT = "port";
        public static final String ARG_RETRY_COUNTER = "retry_counter";
        public static final String ARG_SEGMENT_INDEX = "segment_index";
        public static final String ARG_URL = "url";
        public static final int CTRL_DID_TCP_OPEN = 131074;
        public static final int CTRL_WILL_CONCAT_RESOLVE_SEGMENT = 131079;
        public static final int CTRL_WILL_HTTP_OPEN = 131075;
        public static final int CTRL_WILL_LIVE_OPEN = 131077;
        public static final int CTRL_WILL_TCP_OPEN = 131073;
        public static final int EVENT_DID_HTTP_OPEN = 2;
        public static final int EVENT_DID_HTTP_SEEK = 4;
        public static final int EVENT_WILL_HTTP_OPEN = 1;
        public static final int EVENT_WILL_HTTP_SEEK = 3;

        boolean onNativeInvoke(int i, Bundle bundle);
    }

    public static class DefaultMediaCodecSelector implements OnMediaCodecSelectListener {
        public static final DefaultMediaCodecSelector sInstance = new DefaultMediaCodecSelector();

        @TargetApi(16)
        public String onMediaCodecSelect(IMediaPlayer iMediaPlayer, String str, int i, int i2) {
            String str2 = str;
            if (VERSION.SDK_INT < 16 || TextUtils.isEmpty(str)) {
                return null;
            }
            IjkMediaCodecInfo ijkMediaCodecInfo;
            String access$100 = IjkMediaPlayer.TAG;
            Object[] objArr = new Object[3];
            objArr[0] = str2;
            objArr[1] = Integer.valueOf(i);
            int i3 = 2;
            objArr[2] = Integer.valueOf(i2);
            Log.i(access$100, String.format(Locale.US, "onSelectCodec: mime=%s, profile=%d, level=%d", objArr));
            ArrayList arrayList = new ArrayList();
            int codecCount = MediaCodecList.getCodecCount();
            int i4 = 0;
            while (i4 < codecCount) {
                MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i4);
                Log.d(IjkMediaPlayer.TAG, String.format(Locale.US, "  found codec: %s", new Object[]{codecInfoAt.getName()}));
                if (!codecInfoAt.isEncoder()) {
                    String[] supportedTypes = codecInfoAt.getSupportedTypes();
                    if (supportedTypes != null) {
                        int length = supportedTypes.length;
                        int i5 = 0;
                        while (i5 < length) {
                            CharSequence charSequence = supportedTypes[i5];
                            if (!TextUtils.isEmpty(charSequence)) {
                                Log.d(IjkMediaPlayer.TAG, String.format(Locale.US, "    mime: %s", new Object[]{charSequence}));
                                if (charSequence.equalsIgnoreCase(str2)) {
                                    ijkMediaCodecInfo = IjkMediaCodecInfo.setupCandidate(codecInfoAt, str2);
                                    if (ijkMediaCodecInfo != null) {
                                        arrayList.add(ijkMediaCodecInfo);
                                        String access$1002 = IjkMediaPlayer.TAG;
                                        Object[] objArr2 = new Object[i3];
                                        objArr2[0] = codecInfoAt.getName();
                                        objArr2[1] = Integer.valueOf(ijkMediaCodecInfo.mRank);
                                        Log.i(access$1002, String.format(Locale.US, "candidate codec: %s rank=%d", objArr2));
                                        ijkMediaCodecInfo.dumpProfileLevels(str2);
                                    }
                                }
                            }
                            i5++;
                            i3 = 2;
                        }
                    }
                }
                i4++;
                i3 = 2;
            }
            if (arrayList.isEmpty()) {
                return null;
            }
            IjkMediaCodecInfo ijkMediaCodecInfo2 = (IjkMediaCodecInfo) arrayList.get(0);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ijkMediaCodecInfo = (IjkMediaCodecInfo) it.next();
                if (ijkMediaCodecInfo.mRank > ijkMediaCodecInfo2.mRank) {
                    ijkMediaCodecInfo2 = ijkMediaCodecInfo;
                }
            }
            if (ijkMediaCodecInfo2.mRank < IjkMediaCodecInfo.RANK_LAST_CHANCE) {
                Log.w(IjkMediaPlayer.TAG, String.format(Locale.US, "unaccetable codec: %s", new Object[]{ijkMediaCodecInfo2.mCodecInfo.getName()}));
                return null;
            }
            Log.i(IjkMediaPlayer.TAG, String.format(Locale.US, "selected codec: %s rank=%d", new Object[]{ijkMediaCodecInfo2.mCodecInfo.getName(), Integer.valueOf(ijkMediaCodecInfo2.mRank)}));
            return ijkMediaCodecInfo2.mCodecInfo.getName();
        }
    }

    private native String _getAudioCodecInfo();

    private static native String _getColorFormatName(int i);

    private native int _getLoopCount();

    private native Bundle _getMediaMeta();

    private native float _getPropertyFloat(int i, float f);

    private native long _getPropertyLong(int i, long j);

    private native String _getVideoCodecInfo();

    private native void _pause() throws IllegalStateException;

    private native void _release();

    private native void _reset();

    private native void _setAndroidIOCallback(IAndroidIO iAndroidIO) throws IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setDataSource(String str, String[] strArr, String[] strArr2) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setDataSource(IMediaDataSource iMediaDataSource) throws IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setDataSourceFd(int i) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setFrameAtTime(String str, long j, long j2, int i, int i2) throws IllegalArgumentException, IllegalStateException;

    private native void _setLoopCount(int i);

    private native void _setOption(int i, String str, long j);

    private native void _setOption(int i, String str, String str2);

    private native void _setPropertyFloat(int i, float f);

    private native void _setPropertyLong(int i, long j);

    private native void _setStreamSelected(int i, boolean z);

    private native void _setVideoSurface(Surface surface);

    private native void _start() throws IllegalStateException;

    private native void _stop() throws IllegalStateException;

    private native void native_finalize();

    private static native void native_init();

    private native void native_message_loop(Object obj);

    public static native void native_profileBegin(String str);

    public static native void native_profileEnd();

    public static native void native_setLogLevel(int i);

    private native void native_setup(Object obj);

    public native void _prepareAsync() throws IllegalStateException;

    public native int getAudioSessionId();

    public native long getCurrentPosition();

    public native long getDuration();

    public boolean isPlayable() {
        return true;
    }

    public native boolean isPlaying();

    public native void seekTo(long j) throws IllegalStateException;

    public void setAudioStreamType(int i) {
    }

    public void setKeepInBackground(boolean z) {
    }

    public void setLogEnabled(boolean z) {
    }

    public native void setVolume(float f, float f2);

    public static void loadLibrariesOnce(IjkLibLoader ijkLibLoader) {
        synchronized (IjkMediaPlayer.class) {
            if (!mIsLibLoaded) {
                if (ijkLibLoader == null) {
                    ijkLibLoader = sLocalLibLoader;
                }
                ijkLibLoader.loadLibrary("ijkffmpeg");
                ijkLibLoader.loadLibrary("ijksdl");
                ijkLibLoader.loadLibrary("ijkplayer");
                mIsLibLoaded = true;
            }
        }
    }

    private static void initNativeOnce() {
        synchronized (IjkMediaPlayer.class) {
            if (!mIsNativeInitialized) {
                native_init();
                mIsNativeInitialized = true;
            }
        }
    }

    public IjkMediaPlayer() {
        this(sLocalLibLoader);
    }

    public IjkMediaPlayer(IjkLibLoader ijkLibLoader) {
        this.mWakeLock = null;
        initPlayer(ijkLibLoader);
    }

    private void initPlayer(IjkLibLoader ijkLibLoader) {
        loadLibrariesOnce(ijkLibLoader);
        initNativeOnce();
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new EventHandler(this, myLooper);
        } else {
            myLooper = Looper.getMainLooper();
            if (myLooper != null) {
                this.mEventHandler = new EventHandler(this, myLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        native_setup(new WeakReference(this));
    }

    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        _setVideoSurface(surfaceHolder != null ? surfaceHolder.getSurface() : null);
        updateSurfaceScreenOn();
    }

    public void setSurface(Surface surface) {
        if (this.mScreenOnWhilePlaying && surface != null) {
            DebugLog.w(TAG, "setScreenOnWhilePlaying(true) is ineffective for Surface");
        }
        this.mSurfaceHolder = null;
        _setVideoSurface(surface);
        updateSurfaceScreenOn();
    }

    public void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        setDataSource(context, uri, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007c  */
    /* JADX WARNING: Missing block: B:35:0x0081, code skipped:
            if (r7 != null) goto L_0x0087;
     */
    /* JADX WARNING: Missing block: B:39:0x0085, code skipped:
            if (r7 != null) goto L_0x0087;
     */
    /* JADX WARNING: Missing block: B:41:0x0087, code skipped:
            r7.close();
     */
    /* JADX WARNING: Missing block: B:42:0x008a, code skipped:
            android.util.Log.d(TAG, "Couldn't open file on client side, trying server side");
            setDataSource(r8.toString(), (java.util.Map) r9);
     */
    /* JADX WARNING: Missing block: B:43:0x0098, code skipped:
            return;
     */
    @android.annotation.TargetApi(14)
    public void setDataSource(android.content.Context r7, android.net.Uri r8, java.util.Map<java.lang.String, java.lang.String> r9) throws java.io.IOException, java.lang.IllegalArgumentException, java.lang.SecurityException, java.lang.IllegalStateException {
        /*
        r6 = this;
        r0 = r8.getScheme();
        r1 = "file";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0014;
    L_0x000c:
        r7 = r8.getPath();
        r6.setDataSource(r7);
        return;
    L_0x0014:
        r1 = "content";
        r0 = r1.equals(r0);
        if (r0 == 0) goto L_0x003b;
    L_0x001c:
        r0 = "settings";
        r1 = r8.getAuthority();
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x003b;
    L_0x0028:
        r8 = android.media.RingtoneManager.getDefaultType(r8);
        r8 = android.media.RingtoneManager.getActualDefaultRingtoneUri(r7, r8);
        if (r8 == 0) goto L_0x0033;
    L_0x0032:
        goto L_0x003b;
    L_0x0033:
        r7 = new java.io.FileNotFoundException;
        r8 = "Failed to resolve default ringtone";
        r7.<init>(r8);
        throw r7;
    L_0x003b:
        r0 = 0;
        r7 = r7.getContentResolver();	 Catch:{ SecurityException -> 0x0084, IOException -> 0x0080, all -> 0x0078 }
        r1 = "r";
        r7 = r7.openAssetFileDescriptor(r8, r1);	 Catch:{ SecurityException -> 0x0084, IOException -> 0x0080, all -> 0x0078 }
        if (r7 != 0) goto L_0x004e;
    L_0x0048:
        if (r7 == 0) goto L_0x004d;
    L_0x004a:
        r7.close();
    L_0x004d:
        return;
    L_0x004e:
        r0 = r7.getDeclaredLength();	 Catch:{ SecurityException -> 0x0085, IOException -> 0x0081, all -> 0x0076 }
        r2 = 0;
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 >= 0) goto L_0x0060;
    L_0x0058:
        r0 = r7.getFileDescriptor();	 Catch:{ SecurityException -> 0x0085, IOException -> 0x0081, all -> 0x0076 }
        r6.setDataSource(r0);	 Catch:{ SecurityException -> 0x0085, IOException -> 0x0081, all -> 0x0076 }
        goto L_0x0070;
    L_0x0060:
        r1 = r7.getFileDescriptor();	 Catch:{ SecurityException -> 0x0085, IOException -> 0x0081, all -> 0x0076 }
        r2 = r7.getStartOffset();	 Catch:{ SecurityException -> 0x0085, IOException -> 0x0081, all -> 0x0076 }
        r4 = r7.getDeclaredLength();	 Catch:{ SecurityException -> 0x0085, IOException -> 0x0081, all -> 0x0076 }
        r0 = r6;
        r0.setDataSource(r1, r2, r4);	 Catch:{ SecurityException -> 0x0085, IOException -> 0x0081, all -> 0x0076 }
    L_0x0070:
        if (r7 == 0) goto L_0x0075;
    L_0x0072:
        r7.close();
    L_0x0075:
        return;
    L_0x0076:
        r8 = move-exception;
        goto L_0x007a;
    L_0x0078:
        r8 = move-exception;
        r7 = r0;
    L_0x007a:
        if (r7 == 0) goto L_0x007f;
    L_0x007c:
        r7.close();
    L_0x007f:
        throw r8;
    L_0x0080:
        r7 = r0;
    L_0x0081:
        if (r7 == 0) goto L_0x008a;
    L_0x0083:
        goto L_0x0087;
    L_0x0084:
        r7 = r0;
    L_0x0085:
        if (r7 == 0) goto L_0x008a;
    L_0x0087:
        r7.close();
    L_0x008a:
        r7 = TAG;
        r0 = "Couldn't open file on client side, trying server side";
        android.util.Log.d(r7, r0);
        r7 = r8.toString();
        r6.setDataSource(r7, r9);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.player.IjkMediaPlayer.setDataSource(android.content.Context, android.net.Uri, java.util.Map):void");
    }

    public void setDataSource(String str) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        this.mDataSource = str;
        _setDataSource(str, null, null);
    }

    public void setDataSource(String str, Map<String, String> map) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        if (!(map == null || map.isEmpty())) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Entry entry : map.entrySet()) {
                stringBuilder.append((String) entry.getKey());
                stringBuilder.append(":");
                if (!TextUtils.isEmpty((String) entry.getValue())) {
                    stringBuilder.append((String) entry.getValue());
                }
                stringBuilder.append("\r\n");
                setOption(1, "headers", stringBuilder.toString());
                setOption(1, "protocol_whitelist", "async,cache,crypto,file,http,https,ijkhttphook,ijkinject,ijklivehook,ijklongurl,ijksegment,ijktcphook,pipe,rtp,tcp,tls,udp,ijkurlhook,data");
            }
        }
        setDataSource(str);
    }

    @TargetApi(13)
    public void setDataSource(FileDescriptor fileDescriptor) throws IOException, IllegalArgumentException, IllegalStateException {
        if (VERSION.SDK_INT < 12) {
            try {
                Field declaredField = fileDescriptor.getClass().getDeclaredField("descriptor");
                declaredField.setAccessible(true);
                _setDataSourceFd(declaredField.getInt(fileDescriptor));
                return;
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            }
        }
        ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileDescriptor);
        try {
            _setDataSourceFd(dup.getFd());
        } finally {
            dup.close();
        }
    }

    private void setDataSource(FileDescriptor fileDescriptor, long j, long j2) throws IOException, IllegalArgumentException, IllegalStateException {
        setDataSource(fileDescriptor);
    }

    public void setDataSource(IMediaDataSource iMediaDataSource) throws IllegalArgumentException, SecurityException, IllegalStateException {
        _setDataSource(iMediaDataSource);
    }

    public void setAndroidIOCallback(IAndroidIO iAndroidIO) throws IllegalArgumentException, SecurityException, IllegalStateException {
        _setAndroidIOCallback(iAndroidIO);
    }

    public String getDataSource() {
        return this.mDataSource;
    }

    public void prepareAsync() throws IllegalStateException {
        _prepareAsync();
    }

    public void start() throws IllegalStateException {
        stayAwake(true);
        _start();
    }

    public void stop() throws IllegalStateException {
        stayAwake(false);
        _stop();
    }

    public void pause() throws IllegalStateException {
        stayAwake(false);
        _pause();
    }

    @SuppressLint({"Wakelock"})
    public void setWakeMode(Context context, int i) {
        Object obj;
        if (this.mWakeLock != null) {
            if (this.mWakeLock.isHeld()) {
                obj = 1;
                this.mWakeLock.release();
            } else {
                obj = null;
            }
            this.mWakeLock = null;
        } else {
            obj = null;
        }
        this.mWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(i | 536870912, IjkMediaPlayer.class.getName());
        this.mWakeLock.setReferenceCounted(false);
        if (obj != null) {
            this.mWakeLock.acquire();
        }
    }

    public void setScreenOnWhilePlaying(boolean z) {
        if (this.mScreenOnWhilePlaying != z) {
            if (z && this.mSurfaceHolder == null) {
                DebugLog.w(TAG, "setScreenOnWhilePlaying(true) is ineffective without a SurfaceHolder");
            }
            this.mScreenOnWhilePlaying = z;
            updateSurfaceScreenOn();
        }
    }

    @SuppressLint({"Wakelock"})
    private void stayAwake(boolean z) {
        if (this.mWakeLock != null) {
            if (z && !this.mWakeLock.isHeld()) {
                this.mWakeLock.acquire();
            } else if (!z && this.mWakeLock.isHeld()) {
                this.mWakeLock.release();
            }
        }
        this.mStayAwake = z;
        updateSurfaceScreenOn();
    }

    private void updateSurfaceScreenOn() {
        if (this.mSurfaceHolder != null) {
            SurfaceHolder surfaceHolder = this.mSurfaceHolder;
            boolean z = this.mScreenOnWhilePlaying && this.mStayAwake;
            surfaceHolder.setKeepScreenOn(z);
        }
    }

    public IjkTrackInfo[] getTrackInfo() {
        Bundle mediaMeta = getMediaMeta();
        if (mediaMeta == null) {
            return null;
        }
        IjkMediaMeta parse = IjkMediaMeta.parse(mediaMeta);
        if (parse == null || parse.mStreams == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = parse.mStreams.iterator();
        while (it.hasNext()) {
            IjkStreamMeta ijkStreamMeta = (IjkStreamMeta) it.next();
            IjkTrackInfo ijkTrackInfo = new IjkTrackInfo(ijkStreamMeta);
            if (ijkStreamMeta.mType.equalsIgnoreCase("video")) {
                ijkTrackInfo.setTrackType(1);
            } else if (ijkStreamMeta.mType.equalsIgnoreCase("audio")) {
                ijkTrackInfo.setTrackType(2);
            } else if (ijkStreamMeta.mType.equalsIgnoreCase("timedtext")) {
                ijkTrackInfo.setTrackType(3);
            }
            arrayList.add(ijkTrackInfo);
        }
        return (IjkTrackInfo[]) arrayList.toArray(new IjkTrackInfo[arrayList.size()]);
    }

    public int getSelectedTrack(int i) {
        switch (i) {
            case 1:
                return (int) _getPropertyLong(FFP_PROP_INT64_SELECTED_VIDEO_STREAM, -1);
            case 2:
                return (int) _getPropertyLong(FFP_PROP_INT64_SELECTED_AUDIO_STREAM, -1);
            case 3:
                return (int) _getPropertyLong(FFP_PROP_INT64_SELECTED_TIMEDTEXT_STREAM, -1);
            default:
                return -1;
        }
    }

    public void selectTrack(int i) {
        _setStreamSelected(i, true);
    }

    public void deselectTrack(int i) {
        _setStreamSelected(i, false);
    }

    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    public int getVideoSarNum() {
        return this.mVideoSarNum;
    }

    public int getVideoSarDen() {
        return this.mVideoSarDen;
    }

    public void release() {
        stayAwake(false);
        updateSurfaceScreenOn();
        resetListeners();
        _release();
    }

    public void reset() {
        stayAwake(false);
        _reset();
        this.mEventHandler.removeCallbacksAndMessages(null);
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
    }

    public void setLooping(boolean z) {
        int i = z ^ 1;
        setOption(4, "loop", (long) i);
        _setLoopCount(i);
    }

    public boolean isLooping() {
        return _getLoopCount() != 1;
    }

    public void setSpeed(float f) {
        _setPropertyFloat(10003, f);
    }

    public float getSpeed(float f) {
        return _getPropertyFloat(10003, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public int getVideoDecoder() {
        return (int) _getPropertyLong(FFP_PROP_INT64_VIDEO_DECODER, 0);
    }

    public float getVideoOutputFramesPerSecond() {
        return _getPropertyFloat(10002, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public float getVideoDecodeFramesPerSecond() {
        return _getPropertyFloat(10001, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public long getVideoCachedDuration() {
        return _getPropertyLong(FFP_PROP_INT64_VIDEO_CACHED_DURATION, 0);
    }

    public long getAudioCachedDuration() {
        return _getPropertyLong(FFP_PROP_INT64_AUDIO_CACHED_DURATION, 0);
    }

    public long getVideoCachedBytes() {
        return _getPropertyLong(FFP_PROP_INT64_VIDEO_CACHED_BYTES, 0);
    }

    public long getAudioCachedBytes() {
        return _getPropertyLong(FFP_PROP_INT64_AUDIO_CACHED_BYTES, 0);
    }

    public long getVideoCachedPackets() {
        return _getPropertyLong(FFP_PROP_INT64_VIDEO_CACHED_PACKETS, 0);
    }

    public long getAudioCachedPackets() {
        return _getPropertyLong(FFP_PROP_INT64_AUDIO_CACHED_PACKETS, 0);
    }

    public long getAsyncStatisticBufBackwards() {
        return _getPropertyLong(FFP_PROP_INT64_ASYNC_STATISTIC_BUF_BACKWARDS, 0);
    }

    public long getAsyncStatisticBufForwards() {
        return _getPropertyLong(FFP_PROP_INT64_ASYNC_STATISTIC_BUF_FORWARDS, 0);
    }

    public long getAsyncStatisticBufCapacity() {
        return _getPropertyLong(FFP_PROP_INT64_ASYNC_STATISTIC_BUF_CAPACITY, 0);
    }

    public long getTrafficStatisticByteCount() {
        return _getPropertyLong(FFP_PROP_INT64_TRAFFIC_STATISTIC_BYTE_COUNT, 0);
    }

    public long getCacheStatisticPhysicalPos() {
        return _getPropertyLong(FFP_PROP_INT64_CACHE_STATISTIC_PHYSICAL_POS, 0);
    }

    public long getCacheStatisticFileForwards() {
        return _getPropertyLong(FFP_PROP_INT64_CACHE_STATISTIC_FILE_FORWARDS, 0);
    }

    public long getCacheStatisticFilePos() {
        return _getPropertyLong(FFP_PROP_INT64_CACHE_STATISTIC_FILE_POS, 0);
    }

    public long getCacheStatisticCountBytes() {
        return _getPropertyLong(FFP_PROP_INT64_CACHE_STATISTIC_COUNT_BYTES, 0);
    }

    public long getFileSize() {
        return _getPropertyLong(FFP_PROP_INT64_LOGICAL_FILE_SIZE, 0);
    }

    public long getBitRate() {
        return _getPropertyLong(FFP_PROP_INT64_BIT_RATE, 0);
    }

    public long getTcpSpeed() {
        return _getPropertyLong(FFP_PROP_INT64_TCP_SPEED, 0);
    }

    public long getSeekLoadDuration() {
        return _getPropertyLong(FFP_PROP_INT64_LATEST_SEEK_LOAD_DURATION, 0);
    }

    public float getDropFrameRate() {
        return _getPropertyFloat(10007, CropImageView.DEFAULT_ASPECT_RATIO);
    }

    public MediaInfo getMediaInfo() {
        String[] split;
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.mMediaPlayerName = "ijkplayer";
        String _getVideoCodecInfo = _getVideoCodecInfo();
        if (!TextUtils.isEmpty(_getVideoCodecInfo)) {
            split = _getVideoCodecInfo.split(",");
            if (split.length >= 2) {
                mediaInfo.mVideoDecoder = split[0];
                mediaInfo.mVideoDecoderImpl = split[1];
            } else if (split.length >= 1) {
                mediaInfo.mVideoDecoder = split[0];
                mediaInfo.mVideoDecoderImpl = "";
            }
        }
        _getVideoCodecInfo = _getAudioCodecInfo();
        if (!TextUtils.isEmpty(_getVideoCodecInfo)) {
            split = _getVideoCodecInfo.split(",");
            if (split.length >= 2) {
                mediaInfo.mAudioDecoder = split[0];
                mediaInfo.mAudioDecoderImpl = split[1];
            } else if (split.length >= 1) {
                mediaInfo.mAudioDecoder = split[0];
                mediaInfo.mAudioDecoderImpl = "";
            }
        }
        try {
            mediaInfo.mMeta = IjkMediaMeta.parse(_getMediaMeta());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return mediaInfo;
    }

    public void setOption(int i, String str, String str2) {
        _setOption(i, str, str2);
    }

    public void setOption(int i, String str, long j) {
        _setOption(i, str, j);
    }

    public Bundle getMediaMeta() {
        return _getMediaMeta();
    }

    public static String getColorFormatName(int i) {
        return _getColorFormatName(i);
    }

    /* Access modifiers changed, original: protected */
    public void finalize() throws Throwable {
        super.finalize();
        native_finalize();
    }

    public void httphookReconnect() {
        _setPropertyLong(FFP_PROP_INT64_IMMEDIATE_RECONNECT, 1);
    }

    public void setCacheShare(int i) {
        _setPropertyLong(FFP_PROP_INT64_SHARE_CACHE_DATA, (long) i);
    }

    @CalledByNative
    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        if (obj != null) {
            IjkMediaPlayer ijkMediaPlayer = (IjkMediaPlayer) ((WeakReference) obj).get();
            if (ijkMediaPlayer != null) {
                if (i == 200 && i2 == 2) {
                    ijkMediaPlayer.start();
                }
                if (ijkMediaPlayer.mEventHandler != null) {
                    ijkMediaPlayer.mEventHandler.sendMessage(ijkMediaPlayer.mEventHandler.obtainMessage(i, i2, i3, obj2));
                }
            }
        }
    }

    public void setOnControlMessageListener(OnControlMessageListener onControlMessageListener) {
        this.mOnControlMessageListener = onControlMessageListener;
    }

    public void setOnNativeInvokeListener(OnNativeInvokeListener onNativeInvokeListener) {
        this.mOnNativeInvokeListener = onNativeInvokeListener;
    }

    @CalledByNative
    private static boolean onNativeInvoke(Object obj, int i, Bundle bundle) {
        DebugLog.ifmt(TAG, "onNativeInvoke %d", Integer.valueOf(i));
        if (obj == null || !(obj instanceof WeakReference)) {
            throw new IllegalStateException("<null weakThiz>.onNativeInvoke()");
        }
        IjkMediaPlayer ijkMediaPlayer = (IjkMediaPlayer) ((WeakReference) obj).get();
        if (ijkMediaPlayer != null) {
            OnNativeInvokeListener onNativeInvokeListener = ijkMediaPlayer.mOnNativeInvokeListener;
            if (onNativeInvokeListener != null && onNativeInvokeListener.onNativeInvoke(i, bundle)) {
                return true;
            }
            if (i != OnNativeInvokeListener.CTRL_WILL_CONCAT_RESOLVE_SEGMENT) {
                return false;
            }
            OnControlMessageListener onControlMessageListener = ijkMediaPlayer.mOnControlMessageListener;
            if (onControlMessageListener == null) {
                return false;
            }
            i = bundle.getInt(OnNativeInvokeListener.ARG_SEGMENT_INDEX, -1);
            if (i >= 0) {
                String onControlResolveSegmentUrl = onControlMessageListener.onControlResolveSegmentUrl(i);
                if (onControlResolveSegmentUrl != null) {
                    bundle.putString(OnNativeInvokeListener.ARG_URL, onControlResolveSegmentUrl);
                    return true;
                }
                throw new RuntimeException(new IOException("onNativeInvoke() = <NULL newUrl>"));
            }
            throw new InvalidParameterException("onNativeInvoke(invalid segment index)");
        }
        throw new IllegalStateException("<null weakPlayer>.onNativeInvoke()");
    }

    public void setOnMediaCodecSelectListener(OnMediaCodecSelectListener onMediaCodecSelectListener) {
        this.mOnMediaCodecSelectListener = onMediaCodecSelectListener;
    }

    public void resetListeners() {
        super.resetListeners();
        this.mOnMediaCodecSelectListener = null;
    }

    @CalledByNative
    private static String onSelectCodec(Object obj, String str, int i, int i2) {
        if (obj == null || !(obj instanceof WeakReference)) {
            return null;
        }
        IjkMediaPlayer ijkMediaPlayer = (IjkMediaPlayer) ((WeakReference) obj).get();
        if (ijkMediaPlayer == null) {
            return null;
        }
        OnMediaCodecSelectListener onMediaCodecSelectListener = ijkMediaPlayer.mOnMediaCodecSelectListener;
        if (onMediaCodecSelectListener == null) {
            onMediaCodecSelectListener = DefaultMediaCodecSelector.sInstance;
        }
        return onMediaCodecSelectListener.onMediaCodecSelect(ijkMediaPlayer, str, i, i2);
    }
}
