package com.tencent.liteav.avprotocol;

import android.util.Log;
import com.tencent.liteav.basic.g.a;
import com.tencent.liteav.basic.g.b;
import java.util.ArrayList;

public class TXCAVProtocol {
    public static byte AV_STATE_ENTER_AUDIO = (byte) 1;
    public static byte AV_STATE_ENTER_VIDEO = (byte) 3;
    public static byte AV_STATE_EXIT_AUDIO = (byte) 2;
    public static byte AV_STATE_EXIT_VIDEO = (byte) 4;
    public static byte AV_STATE_NONE = (byte) 0;
    private static final String TAG = "TXCAVProtocol";
    public static final int TXE_AVPROTO_CONNECT_ACC_FAIL = 6605;
    public static final int TXE_AVPROTO_CONNECT_ACC_SUCCESS = 6604;
    public static final int TXE_AVPROTO_ENTER_ROOM_FAIL = 6607;
    public static final int TXE_AVPROTO_ENTER_ROOM_SUCC = 6606;
    public static final int TXE_AVPROTO_EXIT_ROOM_FAIL = 6609;
    public static final int TXE_AVPROTO_EXIT_ROOM_SUCC = 6608;
    public static final int TXE_AVPROTO_GET_ACC_IP_FAIL = 6603;
    public static final int TXE_AVPROTO_GET_ACC_IP_SUCCESS = 6602;
    public static final int TXE_AVPROTO_HEARTBEAT_FAIL = 6601;
    public static final int TXE_AVPROTO_REQUEST_AVSEAT_FAIL = 6611;
    public static final int TXE_AVPROTO_REQUEST_AVSEAT_SUCC = 6610;
    private long mInstance;
    private TXIAVListener mListener;

    public class DownloadStats {
        public long afterParseAudioBytes;
        public long afterParseVideoBytes;
        public long beforeParseAudioBytes;
        public long beforeParseVideoBytes;
        public long connTS;
        public long dnsTS;
        public long firstAudioTS;
        public long firstVideoTS;
        public String serverIP;
        public long startTS;
    }

    public class TXCAVProtoParam {
        public int authBits;
        public byte[] authBuffer;
        public long roomID;
        public int sdkAppid;
        public int sdkVersion;
        public long userID;
    }

    public interface TXIAVCompletionCallback {
        void onComplete(int i);
    }

    public interface TXIAVListener {
        void onMemberChange(long j, boolean z);

        void onPullAudio(TXSAVProtoAudioPacket tXSAVProtoAudioPacket);

        void onPullNAL(TXSAVProtoNALPacket tXSAVProtoNALPacket);

        void onVideoStateChange(long j, boolean z);

        void sendNotifyEvent(int i, String str);
    }

    public class TXSAVRoomView {
        public int height;
        public long tinyID;
        public int width;
    }

    public class UploadStats {
        public long audioCacheLen;
        public long audioDropCount;
        public long channelType;
        public long connTS;
        public long dnsTS;
        public long inAudioBytes;
        public long inVideoBytes;
        public long outAudioBytes;
        public long outVideoBytes;
        public String serverIP;
        public long startTS;
        public long videoCacheLen;
        public long videoDropCount;
    }

    public class TXSAVProtoAudioPacket extends a {
        public int roomID;
        public long tinyID;
    }

    public class TXSAVProtoNALPacket extends b {
        public int roomID;
        public long tinyID;
    }

    private native void nativeChangeAVState(long j, TXIAVCompletionCallback tXIAVCompletionCallback, byte b);

    private native void nativeEnterRoom(long j, TXIAVCompletionCallback tXIAVCompletionCallback, long j2, long j3, long j4, long j5, byte[] bArr, long j6, int i);

    private native void nativeExitRoom(long j, TXIAVCompletionCallback tXIAVCompletionCallback);

    private native DownloadStats nativeGetDownloadStats(long j);

    private native long[] nativeGetRoomMemberList(long j);

    private native long[] nativeGetRoomVideoList(long j);

    private native UploadStats nativeGetUploadStats(long j);

    private native long nativeInitAVProtocol();

    private native void nativePushAAC(long j, byte[] bArr, int i, int i2, int i3, int i4, long j2);

    private native void nativePushNAL(long j, byte[] bArr, int i, long j2, long j3, long j4, long j5, long j6, long j7);

    private native void nativeRequestViews(long j, TXIAVCompletionCallback tXIAVCompletionCallback, long[] jArr, int[] iArr, int[] iArr2);

    private native void nativeUninitAVProtocol(long j);

    public native String nativeNAT64Compatable(String str, short s);

    static {
        com.tencent.liteav.basic.util.b.e();
    }

    public TXCAVProtocol() {
        this.mInstance = 0;
        this.mListener = null;
        this.mInstance = nativeInitAVProtocol();
    }

    public void destory() {
        if (this.mInstance != 0) {
            nativeUninitAVProtocol(this.mInstance);
            this.mInstance = 0;
        }
    }

    public void setListener(TXIAVListener tXIAVListener) {
        this.mListener = tXIAVListener;
    }

    public void enterRoom(TXCAVProtoParam tXCAVProtoParam, TXIAVCompletionCallback tXIAVCompletionCallback) {
        TXCAVProtoParam tXCAVProtoParam2 = tXCAVProtoParam;
        if (this.mInstance != 0) {
            int a = (int) com.tencent.liteav.basic.f.b.a().a("QUICMode", "AVRoom");
            int i = a;
            nativeEnterRoom(this.mInstance, tXIAVCompletionCallback, (long) tXCAVProtoParam2.sdkAppid, (long) tXCAVProtoParam2.sdkVersion, tXCAVProtoParam2.roomID, (long) tXCAVProtoParam2.authBits, tXCAVProtoParam2.authBuffer, tXCAVProtoParam2.userID, i);
        }
    }

    public void exitRoom(final TXIAVCompletionCallback tXIAVCompletionCallback) {
        if (this.mInstance != 0) {
            nativeExitRoom(this.mInstance, new TXIAVCompletionCallback() {
                public void onComplete(int i) {
                    tXIAVCompletionCallback.onComplete(i);
                }
            });
        }
    }

    public void changeAVState(byte b, TXIAVCompletionCallback tXIAVCompletionCallback) {
        if (this.mInstance != 0) {
            nativeChangeAVState(this.mInstance, tXIAVCompletionCallback, b);
        }
    }

    public void requestViews(ArrayList<TXSAVRoomView> arrayList, TXIAVCompletionCallback tXIAVCompletionCallback) {
        if (this.mInstance != 0) {
            long[] jArr = new long[arrayList.size()];
            int[] iArr = new int[arrayList.size()];
            int[] iArr2 = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                jArr[i] = ((TXSAVRoomView) arrayList.get(i)).tinyID;
                iArr[i] = ((TXSAVRoomView) arrayList.get(i)).width;
                iArr2[i] = ((TXSAVRoomView) arrayList.get(i)).height;
            }
            nativeRequestViews(this.mInstance, tXIAVCompletionCallback, jArr, iArr, iArr2);
        }
    }

    public void pushAAC(byte[] bArr, long j, int i, int i2) {
        if (this.mInstance != 0) {
            nativePushAAC(this.mInstance, bArr, i, i2, com.tencent.liteav.basic.a.a.h, com.tencent.liteav.basic.a.a.l, j);
        }
    }

    public void pushNAL(b bVar) {
        b bVar2 = bVar;
        if (this.mInstance != 0) {
            long j = this.mInstance;
            long j2 = j;
            nativePushNAL(j2, bVar2.nalData, bVar2.nalType, bVar2.gopIndex, bVar2.gopFrameIndex, bVar2.frameIndex, bVar2.refFremeIndex, bVar2.pts, bVar2.dts);
        }
    }

    private void onPullAudio(int i, long j, byte[] bArr, long j2, int i2, int i3, int i4, int i5) {
        if (this.mListener != null) {
            TXSAVProtoAudioPacket tXSAVProtoAudioPacket = new TXSAVProtoAudioPacket();
            tXSAVProtoAudioPacket.roomID = i;
            tXSAVProtoAudioPacket.tinyID = j;
            tXSAVProtoAudioPacket.audioData = bArr;
            tXSAVProtoAudioPacket.timestamp = j2;
            tXSAVProtoAudioPacket.sampleRate = i2;
            tXSAVProtoAudioPacket.channelsPerSample = i3;
            tXSAVProtoAudioPacket.bitsPerChannel = i4;
            tXSAVProtoAudioPacket.packetType = i5;
            this.mListener.onPullAudio(tXSAVProtoAudioPacket);
        }
    }

    private void onPullVideo(int i, long j, byte[] bArr, int i2, long j2, long j3, int i3, int i4, int i5, int i6) {
        if (this.mListener != null) {
            TXSAVProtoNALPacket tXSAVProtoNALPacket = new TXSAVProtoNALPacket();
            tXSAVProtoNALPacket.roomID = i;
            tXSAVProtoNALPacket.tinyID = j;
            tXSAVProtoNALPacket.nalType = i2;
            tXSAVProtoNALPacket.nalData = bArr;
            tXSAVProtoNALPacket.pts = j2;
            tXSAVProtoNALPacket.dts = j3;
            tXSAVProtoNALPacket.gopIndex = (long) i3;
            tXSAVProtoNALPacket.gopFrameIndex = (long) i4;
            tXSAVProtoNALPacket.frameIndex = (long) i5;
            tXSAVProtoNALPacket.refFremeIndex = (long) i6;
            this.mListener.onPullNAL(tXSAVProtoNALPacket);
        }
    }

    private void onMemberChange(long j, boolean z) {
        this.mListener.onMemberChange(j, z);
    }

    private void onVideoStateChange(long j, boolean z) {
        this.mListener.onVideoStateChange(j, z);
    }

    private void sendNotifyEvent(int i, String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("event");
        stringBuilder.append(i);
        Log.i(str2, stringBuilder.toString());
        this.mListener.sendNotifyEvent(i, str);
    }

    public long[] getRoomMemberList() {
        if (this.mInstance == 0) {
            return null;
        }
        return nativeGetRoomMemberList(this.mInstance);
    }

    public long[] getRoomVideoList() {
        if (this.mInstance == 0) {
            return null;
        }
        return nativeGetRoomVideoList(this.mInstance);
    }

    public UploadStats getUploadStats() {
        return this.mInstance != 0 ? nativeGetUploadStats(this.mInstance) : null;
    }

    public DownloadStats getDownloadStats() {
        return this.mInstance != 0 ? nativeGetDownloadStats(this.mInstance) : null;
    }
}
