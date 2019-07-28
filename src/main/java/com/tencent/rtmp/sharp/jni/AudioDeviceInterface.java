package com.tencent.rtmp.sharp.jni;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import com.tencent.rtmp.sharp.jni.TraeAudioSession.a;
import com.tencent.ugc.TXRecordCommon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@TargetApi(16)
public class AudioDeviceInterface {
    private static boolean _dumpEnable = false;
    private static boolean _logEnable = true;
    private TraeAudioSession _as = null;
    private AudioManager _audioManager = null;
    private AudioRecord _audioRecord = null;
    private boolean _audioRouteChanged = false;
    private int _audioSource = 0;
    private int _audioSourcePolicy = -1;
    private int _audioStreamTypePolicy = -1;
    private AudioTrack _audioTrack = null;
    private int _bufferedPlaySamples = 0;
    private int _bufferedRecSamples = 0;
    private int _channelOutType = 4;
    private String _connectedDev = TraeAudioManager.DEVICE_NONE;
    private Context _context = null;
    private boolean _doPlayInit = true;
    private boolean _doRecInit = true;
    private TraeAudioSession _init_as = null;
    private boolean _isPlaying = false;
    private boolean _isRecording = false;
    private int _modePolicy = -1;
    private ByteBuffer _playBuffer;
    private final ReentrantLock _playLock = new ReentrantLock();
    private int _playPosition = 0;
    private int _playSamplerate = TXRecordCommon.AUDIO_SAMPLERATE_8000;
    private File _play_dump = null;
    private FileOutputStream _play_out = null;
    private boolean _preDone = false;
    private Condition _precon = this._prelock.newCondition();
    private ReentrantLock _prelock = new ReentrantLock();
    private ByteBuffer _recBuffer;
    private final ReentrantLock _recLock = new ReentrantLock();
    private File _rec_dump = null;
    private FileOutputStream _rec_out = null;
    private int _sceneModeKey = 0;
    private int _sessionId = 0;
    private int _streamType = 0;
    private byte[] _tempBufPlay;
    private byte[] _tempBufRec;
    private int switchState = 0;
    private boolean usingJava = true;

    @TargetApi(16)
    private int getAudioSessionId(AudioRecord audioRecord) {
        return 0;
    }

    public AudioDeviceInterface() {
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        try {
            this._playBuffer = ByteBuffer.allocateDirect(1920);
            this._recBuffer = ByteBuffer.allocateDirect(1920);
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, e.getMessage());
            }
        }
        this._tempBufPlay = new byte[1920];
        this._tempBufRec = new byte[1920];
        int i = VERSION.SDK_INT;
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("AudioDeviceInterface apiLevel:");
            stringBuilder.append(i);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(" SDK_INT:");
            stringBuilder.append(VERSION.SDK_INT);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        if (QLog.isColorLevel()) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("manufacture:");
            stringBuilder2.append(Build.MANUFACTURER);
            QLog.w("TRAE", 2, stringBuilder2.toString());
        }
        if (QLog.isColorLevel()) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("MODEL:");
            stringBuilder2.append(Build.MODEL);
            QLog.w("TRAE", 2, stringBuilder2.toString());
        }
    }

    public void setContext(Context context) {
        this._context = context;
    }

    private int getLowlatencySamplerate() {
        if (this._context == null || VERSION.SDK_INT < 9) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("getLowlatencySamplerate err, _context:");
                stringBuilder.append(this._context);
                stringBuilder.append(" api:");
                stringBuilder.append(VERSION.SDK_INT);
                QLog.e("TRAE", 2, stringBuilder.toString());
            }
            return 0;
        }
        boolean hasSystemFeature = this._context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
        if (QLog.isColorLevel()) {
            String str = "TRAE";
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("LOW_LATENCY:");
            stringBuilder2.append(hasSystemFeature ? "Y" : "N");
            QLog.w(str, 2, stringBuilder2.toString());
        }
        if (VERSION.SDK_INT < 17) {
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 2, "API Level too low not support PROPERTY_OUTPUT_SAMPLE_RATE");
            }
            return 0;
        }
        if (QLog.isColorLevel()) {
            QLog.e("TRAE", 2, "getLowlatencySamplerate not support right now!");
        }
        return 0;
    }

    private int getLowlatencyFramesPerBuffer() {
        if (this._context == null || VERSION.SDK_INT < 9) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("getLowlatencySamplerate err, _context:");
                stringBuilder.append(this._context);
                stringBuilder.append(" api:");
                stringBuilder.append(VERSION.SDK_INT);
                QLog.e("TRAE", 2, stringBuilder.toString());
            }
            return 0;
        }
        boolean hasSystemFeature = this._context.getPackageManager().hasSystemFeature("android.hardware.audio.low_latency");
        if (QLog.isColorLevel()) {
            String str = "TRAE";
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("LOW_LATENCY:");
            stringBuilder2.append(hasSystemFeature ? "Y" : "N");
            QLog.w(str, 2, stringBuilder2.toString());
        }
        if (VERSION.SDK_INT < 17 && QLog.isColorLevel()) {
            QLog.e("TRAE", 2, "API Level too low not support PROPERTY_OUTPUT_SAMPLE_RATE");
        }
        return 0;
    }

    private int InitSetting(int i, int i2, int i3, int i4) {
        this._audioSourcePolicy = i;
        this._audioStreamTypePolicy = i2;
        this._modePolicy = i3;
        this._sceneModeKey = i4;
        if (this._sceneModeKey == 1 || this._sceneModeKey == 2 || this._sceneModeKey == 3) {
            TraeAudioManager.IsMusicScene = true;
        } else {
            TraeAudioManager.IsMusicScene = false;
        }
        TraeAudioManager.IsUpdateSceneFlag = true;
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("InitSetting: _audioSourcePolicy:");
            stringBuilder.append(this._audioSourcePolicy);
            stringBuilder.append(" _audioStreamTypePolicy:");
            stringBuilder.append(this._audioStreamTypePolicy);
            stringBuilder.append(" _modePolicy:");
            stringBuilder.append(this._modePolicy);
            stringBuilder.append(" sceneModeKey:");
            stringBuilder.append(i4);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0180  */
    private int InitRecording(int r21, int r22) {
        /*
        r20 = this;
        r1 = r20;
        r8 = r21;
        r0 = r22;
        r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        r9 = 2;
        if (r2 == 0) goto L_0x0023;
    L_0x000d:
        r2 = "TRAE";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "InitRecording entry:";
        r3.append(r4);
        r3.append(r8);
        r3 = r3.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r2, r9, r3);
    L_0x0023:
        r2 = r1._isRecording;
        r10 = -1;
        if (r2 != 0) goto L_0x01f7;
    L_0x0028:
        r2 = r1._audioRecord;
        if (r2 != 0) goto L_0x01f7;
    L_0x002c:
        if (r0 <= r9) goto L_0x0030;
    L_0x002e:
        goto L_0x01f7;
    L_0x0030:
        r2 = 16;
        if (r0 != r9) goto L_0x0039;
    L_0x0034:
        r2 = 12;
        r11 = 12;
        goto L_0x003b;
    L_0x0039:
        r11 = 16;
    L_0x003b:
        r12 = android.media.AudioRecord.getMinBufferSize(r8, r11, r9);
        r2 = r8 * 20;
        r2 = r2 * r0;
        r2 = r2 * 2;
        r13 = r2 / 1000;
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x0083;
    L_0x004d:
        r0 = "TRAE";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "InitRecording: min rec buf size is ";
        r2.append(r3);
        r2.append(r12);
        r3 = " sr:";
        r2.append(r3);
        r3 = r20.getLowlatencySamplerate();
        r2.append(r3);
        r3 = " fp";
        r2.append(r3);
        r3 = r20.getLowlatencyFramesPerBuffer();
        r2.append(r3);
        r3 = " 20msFZ:";
        r2.append(r3);
        r2.append(r13);
        r2 = r2.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r9, r2);
    L_0x0083:
        r0 = r8 * 5;
        r0 = r0 / 200;
        r1._bufferedRecSamples = r0;
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x00a7;
    L_0x008f:
        r0 = "TRAE";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "  rough rec delay set to ";
        r2.append(r3);
        r3 = r1._bufferedRecSamples;
        r2.append(r3);
        r2 = r2.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r9, r2);
    L_0x00a7:
        r0 = r1._audioRecord;
        r14 = 0;
        if (r0 == 0) goto L_0x00b3;
    L_0x00ac:
        r0 = r1._audioRecord;
        r0.release();
        r1._audioRecord = r14;
    L_0x00b3:
        r15 = 4;
        r7 = new int[r15];
        r7 = {0, 1, 5, 0};
        r0 = r1._audioSourcePolicy;
        r0 = com.tencent.rtmp.sharp.jni.TraeAudioManager.getAudioSource(r0);
        r2 = 0;
        r7[r2] = r0;
        r0 = r12;
        r6 = 0;
    L_0x00c4:
        r2 = r7.length;
        if (r6 >= r2) goto L_0x019d;
    L_0x00c7:
        r2 = r1._audioRecord;
        if (r2 != 0) goto L_0x019d;
    L_0x00cb:
        r2 = r7[r6];
        r1._audioSource = r2;
        r5 = 1;
        r4 = 1;
    L_0x00d1:
        if (r4 > r9) goto L_0x0192;
    L_0x00d3:
        r3 = r12 * r4;
        r0 = r13 * 4;
        if (r3 >= r0) goto L_0x00e4;
    L_0x00d9:
        if (r4 >= r9) goto L_0x00e4;
    L_0x00db:
        r17 = r4;
        r19 = r6;
        r16 = r7;
        r15 = 1;
        goto L_0x0187;
    L_0x00e4:
        r0 = new android.media.AudioRecord;	 Catch:{ Exception -> 0x014f }
        r2 = r1._audioSource;	 Catch:{ Exception -> 0x014f }
        r16 = 2;
        r17 = r2;
        r2 = r0;
        r18 = r3;
        r3 = r17;
        r17 = r4;
        r4 = r21;
        r15 = 1;
        r5 = r11;
        r19 = r6;
        r6 = r16;
        r16 = r7;
        r7 = r18;
        r2.<init>(r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x014b }
        r1._audioRecord = r0;	 Catch:{ Exception -> 0x014b }
        r0 = r1._audioRecord;
        r0 = r0.getState();
        if (r0 == r15) goto L_0x0147;
    L_0x010c:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x013d;
    L_0x0112:
        r0 = "TRAE";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "InitRecording:  rec not initialized,try agine,  minbufsize:";
        r2.append(r3);
        r3 = r18;
        r2.append(r3);
        r4 = " sr:";
        r2.append(r4);
        r2.append(r8);
        r4 = " as:";
        r2.append(r4);
        r4 = r1._audioSource;
        r2.append(r4);
        r2 = r2.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r9, r2);
        goto L_0x013f;
    L_0x013d:
        r3 = r18;
    L_0x013f:
        r0 = r1._audioRecord;
        r0.release();
        r1._audioRecord = r14;
        goto L_0x0187;
    L_0x0147:
        r3 = r18;
        r0 = r3;
        goto L_0x0196;
    L_0x014b:
        r0 = move-exception;
        r3 = r18;
        goto L_0x0157;
    L_0x014f:
        r0 = move-exception;
        r17 = r4;
        r19 = r6;
        r16 = r7;
        r15 = 1;
    L_0x0157:
        r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r2 == 0) goto L_0x017c;
    L_0x015d:
        r2 = "TRAE";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r0 = r0.getMessage();
        r4.append(r0);
        r0 = " _audioRecord:";
        r4.append(r0);
        r0 = r1._audioRecord;
        r4.append(r0);
        r0 = r4.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r2, r9, r0);
    L_0x017c:
        r0 = r1._audioRecord;
        if (r0 == 0) goto L_0x0185;
    L_0x0180:
        r0 = r1._audioRecord;
        r0.release();
    L_0x0185:
        r1._audioRecord = r14;
    L_0x0187:
        r4 = r17 + 1;
        r0 = r3;
        r7 = r16;
        r6 = r19;
        r5 = 1;
        r15 = 4;
        goto L_0x00d1;
    L_0x0192:
        r19 = r6;
        r16 = r7;
    L_0x0196:
        r6 = r19 + 1;
        r7 = r16;
        r15 = 4;
        goto L_0x00c4;
    L_0x019d:
        r2 = r1._audioRecord;
        if (r2 != 0) goto L_0x01af;
    L_0x01a1:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x01ae;
    L_0x01a7:
        r0 = "TRAE";
        r2 = "InitRecording fail!!!";
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r9, r2);
    L_0x01ae:
        return r10;
    L_0x01af:
        r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r2 == 0) goto L_0x01e7;
    L_0x01b5:
        r2 = "TRAE";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = " [Config] InitRecording: audioSession:";
        r3.append(r4);
        r4 = r1._sessionId;
        r3.append(r4);
        r4 = " audioSource:";
        r3.append(r4);
        r4 = r1._audioSource;
        r3.append(r4);
        r4 = " rec sample rate set to ";
        r3.append(r4);
        r3.append(r8);
        r4 = " recBufSize:";
        r3.append(r4);
        r3.append(r0);
        r0 = r3.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r2, r9, r0);
    L_0x01e7:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x01f4;
    L_0x01ed:
        r0 = "TRAE";
        r2 = "InitRecording exit";
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r9, r2);
    L_0x01f4:
        r0 = r1._bufferedRecSamples;
        return r0;
    L_0x01f7:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x0215;
    L_0x01fd:
        r0 = "TRAE";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "InitRecording _isRecording:";
        r2.append(r3);
        r3 = r1._isRecording;
        r2.append(r3);
        r2 = r2.toString();
        com.tencent.rtmp.sharp.jni.QLog.e(r0, r9, r2);
    L_0x0215:
        return r10;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.AudioDeviceInterface.InitRecording(int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:85:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01f1  */
    private int InitPlayback(int r22, int r23) {
        /*
        r21 = this;
        r1 = r21;
        r0 = r22;
        r2 = r23;
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        r4 = 2;
        if (r3 == 0) goto L_0x0023;
    L_0x000d:
        r3 = "TRAE";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "InitPlayback entry: sampleRate ";
        r5.append(r6);
        r5.append(r0);
        r5 = r5.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r3, r4, r5);
    L_0x0023:
        r3 = r1._isPlaying;
        r5 = -1;
        if (r3 != 0) goto L_0x0284;
    L_0x0028:
        r3 = r1._audioTrack;
        if (r3 != 0) goto L_0x0284;
    L_0x002c:
        if (r2 <= r4) goto L_0x0030;
    L_0x002e:
        goto L_0x0284;
    L_0x0030:
        r3 = r1._audioManager;
        if (r3 != 0) goto L_0x0052;
    L_0x0034:
        r3 = r1._context;	 Catch:{ Exception -> 0x0041 }
        r6 = "audio";
        r3 = r3.getSystemService(r6);	 Catch:{ Exception -> 0x0041 }
        r3 = (android.media.AudioManager) r3;	 Catch:{ Exception -> 0x0041 }
        r1._audioManager = r3;	 Catch:{ Exception -> 0x0041 }
        goto L_0x0052;
    L_0x0041:
        r0 = move-exception;
        r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r2 == 0) goto L_0x0051;
    L_0x0048:
        r2 = "TRAE";
        r0 = r0.getMessage();
        com.tencent.rtmp.sharp.jni.QLog.w(r2, r4, r0);
    L_0x0051:
        return r5;
    L_0x0052:
        r3 = 12;
        r6 = 4;
        if (r2 != r4) goto L_0x005a;
    L_0x0057:
        r1._channelOutType = r3;
        goto L_0x005c;
    L_0x005a:
        r1._channelOutType = r6;
    L_0x005c:
        r1._playSamplerate = r0;
        r2 = r1._channelOutType;
        r2 = android.media.AudioTrack.getMinBufferSize(r0, r2, r4);
        r7 = r1._channelOutType;
        if (r7 != r3) goto L_0x0087;
    L_0x0068:
        r7 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r7 == 0) goto L_0x0076;
    L_0x006e:
        r7 = "TRAE";
        r8 = "InitPlayback, _channelOutType stero";
        com.tencent.rtmp.sharp.jni.QLog.w(r7, r4, r8);
        goto L_0x0087;
    L_0x0076:
        r7 = r1._channelOutType;
        if (r7 != r6) goto L_0x0087;
    L_0x007a:
        r7 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r7 == 0) goto L_0x0087;
    L_0x0080:
        r7 = "TRAE";
        r8 = "InitPlayback, _channelOutType Mono";
        com.tencent.rtmp.sharp.jni.QLog.w(r7, r4, r8);
    L_0x0087:
        r0 = r0 * 20;
        r7 = 1;
        r0 = r0 * 1;
        r0 = r0 * 2;
        r0 = r0 / 1000;
        r8 = r1._channelOutType;
        if (r8 != r3) goto L_0x0096;
    L_0x0094:
        r0 = r0 * 2;
    L_0x0096:
        r3 = r0;
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x00bb;
    L_0x009d:
        r0 = "TRAE";
        r8 = new java.lang.StringBuilder;
        r8.<init>();
        r9 = "InitPlayback: minPlayBufSize:";
        r8.append(r9);
        r8.append(r2);
        r9 = " 20msFz:";
        r8.append(r9);
        r8.append(r3);
        r8 = r8.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r4, r8);
    L_0x00bb:
        r8 = 0;
        r1._bufferedPlaySamples = r8;
        r0 = r1._audioTrack;
        r9 = 0;
        if (r0 == 0) goto L_0x00ca;
    L_0x00c3:
        r0 = r1._audioTrack;
        r0.release();
        r1._audioTrack = r9;
    L_0x00ca:
        r10 = new int[r6];
        r10 = {0, 0, 3, 1};
        r0 = r1._audioStreamTypePolicy;
        r0 = com.tencent.rtmp.sharp.jni.TraeAudioManager.getAudioStreamType(r0);
        r1._streamType = r0;
        r0 = r1._audioRouteChanged;
        if (r0 != 0) goto L_0x00dc;
    L_0x00db:
        goto L_0x011e;
    L_0x00dc:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x0104;
    L_0x00e2:
        r0 = "TRAE";
        r11 = new java.lang.StringBuilder;
        r11.<init>();
        r12 = "_audioRouteChanged:";
        r11.append(r12);
        r12 = r1._audioRouteChanged;
        r11.append(r12);
        r12 = " _streamType:";
        r11.append(r12);
        r12 = r1._streamType;
        r11.append(r12);
        r11 = r11.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r4, r11);
    L_0x0104:
        r0 = r1._audioManager;
        r0 = r0.getMode();
        if (r0 != 0) goto L_0x011a;
    L_0x010c:
        r0 = r1._connectedDev;
        r11 = "DEVICE_SPEAKERPHONE";
        r0 = r0.equals(r11);
        if (r0 == 0) goto L_0x011a;
    L_0x0116:
        r0 = 3;
        r1._streamType = r0;
        goto L_0x011c;
    L_0x011a:
        r1._streamType = r8;
    L_0x011c:
        r1._audioRouteChanged = r8;
    L_0x011e:
        r0 = r1._streamType;
        r10[r8] = r0;
        r0 = r2;
        r11 = 0;
    L_0x0124:
        r12 = r10.length;
        if (r11 >= r12) goto L_0x0203;
    L_0x0127:
        r12 = r1._audioTrack;
        if (r12 != 0) goto L_0x0203;
    L_0x012b:
        r12 = r10[r11];
        r1._streamType = r12;
        r12 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r12 == 0) goto L_0x0159;
    L_0x0135:
        r12 = "TRAE";
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r14 = "InitPlayback: min play buf size is ";
        r13.append(r14);
        r13.append(r2);
        r14 = " hw_sr:";
        r13.append(r14);
        r14 = r1._streamType;
        r14 = android.media.AudioTrack.getNativeOutputSampleRate(r14);
        r13.append(r14);
        r13 = r13.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r12, r4, r13);
    L_0x0159:
        r12 = 1;
    L_0x015a:
        if (r12 > r4) goto L_0x01fe;
    L_0x015c:
        r15 = r2 * r12;
        r0 = r3 * 4;
        if (r15 >= r0) goto L_0x0167;
    L_0x0162:
        if (r12 >= r4) goto L_0x0167;
    L_0x0164:
        r13 = r15;
        goto L_0x01f8;
    L_0x0167:
        r0 = new android.media.AudioTrack;	 Catch:{ Exception -> 0x01c6 }
        r14 = r1._streamType;	 Catch:{ Exception -> 0x01c6 }
        r13 = r1._playSamplerate;	 Catch:{ Exception -> 0x01c6 }
        r6 = r1._channelOutType;	 Catch:{ Exception -> 0x01c6 }
        r17 = 2;
        r19 = 1;
        r16 = r13;
        r13 = r0;
        r20 = r15;
        r15 = r16;
        r16 = r6;
        r18 = r20;
        r13.<init>(r14, r15, r16, r17, r18, r19);	 Catch:{ Exception -> 0x01c2 }
        r1._audioTrack = r0;	 Catch:{ Exception -> 0x01c2 }
        r0 = r1._audioTrack;
        r0 = r0.getState();
        if (r0 == r7) goto L_0x01be;
    L_0x018b:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x01b4;
    L_0x0191:
        r0 = "TRAE";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r13 = "InitPlayback: play not initialized playBufSize:";
        r6.append(r13);
        r13 = r20;
        r6.append(r13);
        r14 = " sr:";
        r6.append(r14);
        r14 = r1._playSamplerate;
        r6.append(r14);
        r6 = r6.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r4, r6);
        goto L_0x01b6;
    L_0x01b4:
        r13 = r20;
    L_0x01b6:
        r0 = r1._audioTrack;
        r0.release();
        r1._audioTrack = r9;
        goto L_0x01f8;
    L_0x01be:
        r13 = r20;
        r0 = r13;
        goto L_0x01fe;
    L_0x01c2:
        r0 = move-exception;
        r13 = r20;
        goto L_0x01c8;
    L_0x01c6:
        r0 = move-exception;
        r13 = r15;
    L_0x01c8:
        r6 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r6 == 0) goto L_0x01ed;
    L_0x01ce:
        r6 = "TRAE";
        r14 = new java.lang.StringBuilder;
        r14.<init>();
        r0 = r0.getMessage();
        r14.append(r0);
        r0 = " _audioTrack:";
        r14.append(r0);
        r0 = r1._audioTrack;
        r14.append(r0);
        r0 = r14.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r6, r4, r0);
    L_0x01ed:
        r0 = r1._audioTrack;
        if (r0 == 0) goto L_0x01f6;
    L_0x01f1:
        r0 = r1._audioTrack;
        r0.release();
    L_0x01f6:
        r1._audioTrack = r9;
    L_0x01f8:
        r12 = r12 + 1;
        r0 = r13;
        r6 = 4;
        goto L_0x015a;
    L_0x01fe:
        r11 = r11 + 1;
        r6 = 4;
        goto L_0x0124;
    L_0x0203:
        r2 = r1._audioTrack;
        if (r2 != 0) goto L_0x0215;
    L_0x0207:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x0214;
    L_0x020d:
        r0 = "TRAE";
        r2 = "InitPlayback fail!!!";
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r4, r2);
    L_0x0214:
        return r5;
    L_0x0215:
        r2 = r1._as;
        if (r2 == 0) goto L_0x022a;
    L_0x0219:
        r2 = r1._audioManager;
        if (r2 == 0) goto L_0x022a;
    L_0x021d:
        r2 = r1._as;
        r3 = r1._audioManager;
        r3 = r3.getMode();
        r5 = r1._streamType;
        r2.voiceCallAudioParamChanged(r3, r5);
    L_0x022a:
        r2 = r1._audioTrack;
        r2 = r2.getPlaybackHeadPosition();
        r1._playPosition = r2;
        r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r2 == 0) goto L_0x026c;
    L_0x0238:
        r2 = "TRAE";
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r5 = "InitPlayback exit: streamType:";
        r3.append(r5);
        r5 = r1._streamType;
        r3.append(r5);
        r5 = " samplerate:";
        r3.append(r5);
        r5 = r1._playSamplerate;
        r3.append(r5);
        r5 = " _playPosition:";
        r3.append(r5);
        r5 = r1._playPosition;
        r3.append(r5);
        r5 = " playBufSize:";
        r3.append(r5);
        r3.append(r0);
        r0 = r3.toString();
        com.tencent.rtmp.sharp.jni.QLog.w(r2, r4, r0);
    L_0x026c:
        r0 = r1._audioManager;
        r2 = r1._connectedDev;
        r3 = "DEVICE_BLUETOOTHHEADSET";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x027a;
    L_0x0278:
        r2 = 6;
        goto L_0x0280;
    L_0x027a:
        r2 = r1._audioTrack;
        r2 = r2.getStreamType();
    L_0x0280:
        com.tencent.rtmp.sharp.jni.TraeAudioManager.forceVolumeControlStream(r0, r2);
        return r8;
    L_0x0284:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x02a2;
    L_0x028a:
        r0 = "TRAE";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "InitPlayback _isPlaying:";
        r2.append(r3);
        r3 = r1._isPlaying;
        r2.append(r3);
        r2 = r2.toString();
        com.tencent.rtmp.sharp.jni.QLog.e(r0, r4, r2);
    L_0x02a2:
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.AudioDeviceInterface.InitPlayback(int, int):int");
    }

    private String getDumpFilePath(String str, int i) {
        StringBuilder stringBuilder;
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("manufacture:");
            stringBuilder.append(Build.MANUFACTURER);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("MODEL:");
            stringBuilder.append(Build.MODEL);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(Environment.getExternalStorageDirectory().getPath());
        stringBuilder2.append("/MF-");
        stringBuilder2.append(Build.MANUFACTURER);
        stringBuilder2.append("-M-");
        stringBuilder2.append(Build.MODEL);
        stringBuilder2.append("-as-");
        stringBuilder2.append(TraeAudioManager.getAudioSource(this._audioSourcePolicy));
        stringBuilder2.append("-st-");
        stringBuilder2.append(this._streamType);
        stringBuilder2.append("-m-");
        stringBuilder2.append(i);
        stringBuilder2.append("-");
        stringBuilder2.append(str);
        str = stringBuilder2.toString();
        if (QLog.isColorLevel()) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("dump:");
            stringBuilder2.append(str);
            QLog.w("TRAE", 2, stringBuilder2.toString());
        }
        if (QLog.isColorLevel()) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("dump replace:");
            stringBuilder2.append(str.replace(" ", "_"));
            QLog.w("TRAE", 2, stringBuilder2.toString());
        }
        return str.replace(" ", "_");
    }

    private int StartRecording() {
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 2, "StartRecording entry");
        }
        int i = -1;
        StringBuilder stringBuilder;
        if (this._isRecording) {
            if (QLog.isColorLevel()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("StartRecording _isRecording:");
                stringBuilder.append(this._isRecording);
                QLog.e("TRAE", 2, stringBuilder.toString());
            }
            return -1;
        } else if (this._audioRecord == null) {
            if (QLog.isColorLevel()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("StartRecording _audioRecord:");
                stringBuilder.append(this._audioRecord);
                QLog.e("TRAE", 2, stringBuilder.toString());
            }
            return -1;
        } else {
            try {
                this._audioRecord.startRecording();
                if (_dumpEnable) {
                    String str = "jnirecord.pcm";
                    if (this._audioManager != null) {
                        i = this._audioManager.getMode();
                    }
                    this._rec_dump = new File(getDumpFilePath(str, i));
                    try {
                        this._rec_out = new FileOutputStream(this._rec_dump);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                this._isRecording = true;
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 2, "StartRecording ok");
                }
                return 0;
            } catch (IllegalStateException e2) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "StartRecording fail");
                }
                e2.printStackTrace();
                return -1;
            }
        }
    }

    private int StartPlayback() {
        int i = -1;
        if (this._isPlaying) {
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 2, "StartPlayback _isPlaying");
            }
            return -1;
        } else if (this._audioTrack == null) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("StartPlayback _audioTrack:");
                stringBuilder.append(this._audioTrack);
                QLog.e("TRAE", 2, stringBuilder.toString());
            }
            return -1;
        } else {
            try {
                this._audioTrack.play();
                if (_dumpEnable) {
                    String str = "jniplay.pcm";
                    if (this._audioManager != null) {
                        i = this._audioManager.getMode();
                    }
                    this._play_dump = new File(getDumpFilePath(str, i));
                    try {
                        this._play_out = new FileOutputStream(this._play_dump);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                this._isPlaying = true;
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 2, "StartPlayback ok");
                }
                return 0;
            } catch (IllegalStateException e2) {
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "StartPlayback fail");
                }
                e2.printStackTrace();
                return -1;
            }
        }
    }

    private int StopRecording() {
        int i = 2;
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", 2, "StopRecording entry");
        }
        StringBuilder stringBuilder;
        if (this._audioRecord == null) {
            if (QLog.isColorLevel()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("UnintRecord:");
                stringBuilder.append(this._audioRecord);
                QLog.e("TRAE", 2, stringBuilder.toString());
            }
            return -1;
        }
        this._recLock.lock();
        try {
            if (this._audioRecord.getRecordingState() == 3) {
                if (QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("StopRecording stop... state:");
                    stringBuilder.append(this._audioRecord.getRecordingState());
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
                this._audioRecord.stop();
            }
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("StopRecording releaseing... state:");
                stringBuilder2.append(this._audioRecord.getRecordingState());
                QLog.w("TRAE", 2, stringBuilder2.toString());
            }
            this._audioRecord.release();
            this._audioRecord = null;
            this._isRecording = false;
        } catch (IllegalStateException e) {
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 2, "StopRecording  err");
            }
            e.printStackTrace();
            return -1;
        } finally {
            i = this._recLock;
            i.unlock();
            return -1;
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", i, "StopRecording exit ok");
        }
        return 0;
    }

    private int StopPlayback() {
        StringBuilder stringBuilder;
        int i = 2;
        if (QLog.isColorLevel()) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("StopPlayback entry _isPlaying:");
            stringBuilder.append(this._isPlaying);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        if (this._audioTrack == null) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("StopPlayback _isPlaying:");
                stringBuilder2.append(this._isPlaying);
                stringBuilder2.append(" ");
                stringBuilder2.append(this._audioTrack);
                QLog.e("TRAE", 2, stringBuilder2.toString());
            }
            return -1;
        }
        this._playLock.lock();
        try {
            if (this._audioTrack.getPlayState() == 3) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 2, "StopPlayback stoping...");
                }
                this._audioTrack.stop();
                if (QLog.isColorLevel()) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("StopPlayback flushing... state:");
                    stringBuilder.append(this._audioTrack.getPlayState());
                    QLog.w("TRAE", 2, stringBuilder.toString());
                }
                this._audioTrack.flush();
            }
            if (QLog.isColorLevel()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("StopPlayback releaseing... state:");
                stringBuilder.append(this._audioTrack.getPlayState());
                QLog.w("TRAE", 2, stringBuilder.toString());
            }
            this._audioTrack.release();
            this._audioTrack = null;
            this._isPlaying = false;
        } catch (IllegalStateException e) {
            if (QLog.isColorLevel()) {
                QLog.e("TRAE", 2, "StopPlayback err");
            }
            e.printStackTrace();
            return -1;
        } finally {
            i = this._playLock;
            i.unlock();
            return -1;
        }
        if (QLog.isColorLevel()) {
            QLog.w("TRAE", i, "StopPlayback exit ok");
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:165:0x039f A:{Catch:{ all -> 0x0395 }} */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x039f A:{Catch:{ all -> 0x0395 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x017b */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x02dd A:{Catch:{ Exception -> 0x0300 }} */
    /* JADX WARNING: Missing exception handler attribute for start block: B:130:0x02d7 */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:77|78|(1:80)) */
    /* JADX WARNING: Missing block: B:79:0x017f, code skipped:
            if (com.tencent.rtmp.sharp.jni.QLog.isColorLevel() != false) goto L_0x0181;
     */
    /* JADX WARNING: Missing block: B:80:0x0181, code skipped:
            com.tencent.rtmp.sharp.jni.QLog.e("TRAE", 2, "StopPlayback err");
     */
    private int PlayAudio(int r22) {
        /*
        r21 = this;
        r1 = r21;
        r2 = r22;
        r0 = r1._isPlaying;
        r3 = 1;
        r0 = r0 ^ r3;
        r4 = r1._audioTrack;
        r5 = 0;
        if (r4 != 0) goto L_0x000f;
    L_0x000d:
        r4 = 1;
        goto L_0x0010;
    L_0x000f:
        r4 = 0;
    L_0x0010:
        r0 = r0 | r4;
        r4 = -1;
        r6 = 2;
        if (r0 == 0) goto L_0x003e;
    L_0x0015:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r0 == 0) goto L_0x003d;
    L_0x001b:
        r0 = "TRAE";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "PlayAudio: _isPlaying ";
        r2.append(r3);
        r3 = r1._isPlaying;
        r2.append(r3);
        r3 = " ";
        r2.append(r3);
        r3 = r1._audioTrack;
        r2.append(r3);
        r2 = r2.toString();
        com.tencent.rtmp.sharp.jni.QLog.e(r0, r6, r2);
    L_0x003d:
        return r4;
    L_0x003e:
        r0 = r1._playLock;
        r0.lock();
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x0397 }
        if (r0 != 0) goto L_0x004e;
    L_0x0047:
        r0 = -2;
        r2 = r1._playLock;
        r2.unlock();
        return r0;
    L_0x004e:
        r0 = r1._doPlayInit;	 Catch:{ Exception -> 0x0397 }
        if (r0 != r3) goto L_0x007c;
    L_0x0052:
        r0 = -19;
        android.os.Process.setThreadPriority(r0);	 Catch:{ Exception -> 0x0058 }
        goto L_0x007a;
    L_0x0058:
        r0 = move-exception;
        r7 = r0;
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0397 }
        if (r0 == 0) goto L_0x007a;
    L_0x0060:
        r0 = "TRAE";
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0397 }
        r8.<init>();	 Catch:{ Exception -> 0x0397 }
        r9 = "Set play thread priority failed: ";
        r8.append(r9);	 Catch:{ Exception -> 0x0397 }
        r7 = r7.getMessage();	 Catch:{ Exception -> 0x0397 }
        r8.append(r7);	 Catch:{ Exception -> 0x0397 }
        r7 = r8.toString();	 Catch:{ Exception -> 0x0397 }
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r6, r7);	 Catch:{ Exception -> 0x0397 }
    L_0x007a:
        r1._doPlayInit = r5;	 Catch:{ Exception -> 0x0397 }
    L_0x007c:
        r0 = _dumpEnable;	 Catch:{ Exception -> 0x0397 }
        if (r0 == 0) goto L_0x0090;
    L_0x0080:
        r0 = r1._play_out;	 Catch:{ Exception -> 0x0397 }
        if (r0 == 0) goto L_0x0090;
    L_0x0084:
        r0 = r1._play_out;	 Catch:{ IOException -> 0x008c }
        r7 = r1._tempBufPlay;	 Catch:{ IOException -> 0x008c }
        r0.write(r7, r5, r5);	 Catch:{ IOException -> 0x008c }
        goto L_0x0090;
    L_0x008c:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x0397 }
    L_0x0090:
        r0 = r1._audioRouteChanged;	 Catch:{ Exception -> 0x0397 }
        r7 = 3;
        if (r0 != 0) goto L_0x0097;
    L_0x0095:
        r0 = 0;
        goto L_0x00d1;
    L_0x0097:
        r0 = r1._audioManager;	 Catch:{ Exception -> 0x0397 }
        if (r0 != 0) goto L_0x00ab;
    L_0x009b:
        r0 = r1._context;	 Catch:{ Exception -> 0x0397 }
        if (r0 == 0) goto L_0x00ab;
    L_0x009f:
        r0 = r1._context;	 Catch:{ Exception -> 0x0397 }
        r8 = "audio";
        r0 = r0.getSystemService(r8);	 Catch:{ Exception -> 0x0397 }
        r0 = (android.media.AudioManager) r0;	 Catch:{ Exception -> 0x0397 }
        r1._audioManager = r0;	 Catch:{ Exception -> 0x0397 }
    L_0x00ab:
        r0 = r1._audioManager;	 Catch:{ Exception -> 0x0397 }
        r0 = r0.getMode();	 Catch:{ Exception -> 0x0397 }
        if (r0 != 0) goto L_0x00c0;
    L_0x00b3:
        r0 = r1._connectedDev;	 Catch:{ Exception -> 0x0397 }
        r8 = "DEVICE_SPEAKERPHONE";
        r0 = r0.equals(r8);	 Catch:{ Exception -> 0x0397 }
        if (r0 == 0) goto L_0x00c0;
    L_0x00bd:
        r1._streamType = r7;	 Catch:{ Exception -> 0x0397 }
        goto L_0x00c2;
    L_0x00c0:
        r1._streamType = r5;	 Catch:{ Exception -> 0x0397 }
    L_0x00c2:
        r0 = r1._streamType;	 Catch:{ Exception -> 0x0397 }
        r8 = r1._audioTrack;	 Catch:{ Exception -> 0x0397 }
        r8 = r8.getStreamType();	 Catch:{ Exception -> 0x0397 }
        if (r0 != r8) goto L_0x00ce;
    L_0x00cc:
        r0 = 0;
        goto L_0x00cf;
    L_0x00ce:
        r0 = 1;
    L_0x00cf:
        r1._audioRouteChanged = r5;	 Catch:{ Exception -> 0x0397 }
    L_0x00d1:
        r8 = r1._playBuffer;	 Catch:{ Exception -> 0x0397 }
        r9 = r1._tempBufPlay;	 Catch:{ Exception -> 0x0397 }
        r8.get(r9);	 Catch:{ Exception -> 0x0397 }
        if (r0 == 0) goto L_0x0303;
    L_0x00da:
        r0 = r1._playBuffer;	 Catch:{ Exception -> 0x0300 }
        r0.rewind();	 Catch:{ Exception -> 0x0300 }
        r8 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x0300 }
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0300 }
        if (r0 == 0) goto L_0x010f;
    L_0x00e9:
        r0 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0300 }
        r4.<init>();	 Catch:{ Exception -> 0x0300 }
        r10 = " track resting: _streamType:";
        r4.append(r10);	 Catch:{ Exception -> 0x0300 }
        r10 = r1._streamType;	 Catch:{ Exception -> 0x0300 }
        r4.append(r10);	 Catch:{ Exception -> 0x0300 }
        r10 = " at.st:";
        r4.append(r10);	 Catch:{ Exception -> 0x0300 }
        r10 = r1._audioTrack;	 Catch:{ Exception -> 0x0300 }
        r10 = r10.getStreamType();	 Catch:{ Exception -> 0x0300 }
        r4.append(r10);	 Catch:{ Exception -> 0x0300 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0300 }
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r6, r4);	 Catch:{ Exception -> 0x0300 }
    L_0x010f:
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x0300 }
        r0 = r0.getPlayState();	 Catch:{ Exception -> 0x0300 }
        r4 = 0;
        if (r0 != r7) goto L_0x0188;
    L_0x0118:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ IllegalStateException -> 0x017b }
        if (r0 == 0) goto L_0x0125;
    L_0x011e:
        r0 = "TRAE";
        r7 = "StopPlayback stoping...";
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r6, r7);	 Catch:{ IllegalStateException -> 0x017b }
    L_0x0125:
        r0 = r1._audioTrack;	 Catch:{ IllegalStateException -> 0x017b }
        r0.stop();	 Catch:{ IllegalStateException -> 0x017b }
        r0 = r1._audioTrack;	 Catch:{ IllegalStateException -> 0x017b }
        r0.flush();	 Catch:{ IllegalStateException -> 0x017b }
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ IllegalStateException -> 0x017b }
        if (r0 == 0) goto L_0x0151;
    L_0x0135:
        r0 = "TRAE";
        r7 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x017b }
        r7.<init>();	 Catch:{ IllegalStateException -> 0x017b }
        r10 = "StopPlayback flushing... state:";
        r7.append(r10);	 Catch:{ IllegalStateException -> 0x017b }
        r10 = r1._audioTrack;	 Catch:{ IllegalStateException -> 0x017b }
        r10 = r10.getPlayState();	 Catch:{ IllegalStateException -> 0x017b }
        r7.append(r10);	 Catch:{ IllegalStateException -> 0x017b }
        r7 = r7.toString();	 Catch:{ IllegalStateException -> 0x017b }
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r6, r7);	 Catch:{ IllegalStateException -> 0x017b }
    L_0x0151:
        r0 = r1._audioTrack;	 Catch:{ IllegalStateException -> 0x017b }
        r0.release();	 Catch:{ IllegalStateException -> 0x017b }
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ IllegalStateException -> 0x017b }
        if (r0 == 0) goto L_0x0178;
    L_0x015c:
        r0 = "TRAE";
        r7 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x017b }
        r7.<init>();	 Catch:{ IllegalStateException -> 0x017b }
        r10 = "StopPlayback releaseing... state:";
        r7.append(r10);	 Catch:{ IllegalStateException -> 0x017b }
        r10 = r1._audioTrack;	 Catch:{ IllegalStateException -> 0x017b }
        r10 = r10.getPlayState();	 Catch:{ IllegalStateException -> 0x017b }
        r7.append(r10);	 Catch:{ IllegalStateException -> 0x017b }
        r7 = r7.toString();	 Catch:{ IllegalStateException -> 0x017b }
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r6, r7);	 Catch:{ IllegalStateException -> 0x017b }
    L_0x0178:
        r1._audioTrack = r4;	 Catch:{ IllegalStateException -> 0x017b }
        goto L_0x0188;
    L_0x017b:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0300 }
        if (r0 == 0) goto L_0x0188;
    L_0x0181:
        r0 = "TRAE";
        r7 = "StopPlayback err";
        com.tencent.rtmp.sharp.jni.QLog.e(r0, r6, r7);	 Catch:{ Exception -> 0x0300 }
    L_0x0188:
        r0 = r1._playSamplerate;	 Catch:{ Exception -> 0x0300 }
        r7 = r1._channelOutType;	 Catch:{ Exception -> 0x0300 }
        r7 = android.media.AudioTrack.getMinBufferSize(r0, r7, r6);	 Catch:{ Exception -> 0x0300 }
        r10 = 4;
        r11 = new int[r10];	 Catch:{ Exception -> 0x0300 }
        r11 = {0, 0, 3, 1};	 Catch:{ Exception -> 0x0300 }
        r0 = r1._streamType;	 Catch:{ Exception -> 0x0300 }
        r11[r5] = r0;	 Catch:{ Exception -> 0x0300 }
        r0 = r1._playSamplerate;	 Catch:{ Exception -> 0x0300 }
        r0 = r0 * 20;
        r0 = r0 * 1;
        r0 = r0 * 2;
        r0 = r0 / 1000;
        r12 = r1._channelOutType;	 Catch:{ Exception -> 0x0300 }
        r13 = 12;
        if (r12 != r13) goto L_0x01ac;
    L_0x01aa:
        r0 = r0 * 2;
    L_0x01ac:
        r12 = r0;
    L_0x01ad:
        r0 = r11.length;	 Catch:{ Exception -> 0x0300 }
        if (r5 >= r0) goto L_0x02aa;
    L_0x01b0:
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x0300 }
        if (r0 != 0) goto L_0x02aa;
    L_0x01b4:
        r0 = r11[r5];	 Catch:{ Exception -> 0x0300 }
        r1._streamType = r0;	 Catch:{ Exception -> 0x0300 }
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0300 }
        if (r0 == 0) goto L_0x01e2;
    L_0x01be:
        r0 = "TRAE";
        r13 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0300 }
        r13.<init>();	 Catch:{ Exception -> 0x0300 }
        r14 = "InitPlayback: min play buf size is ";
        r13.append(r14);	 Catch:{ Exception -> 0x0300 }
        r13.append(r7);	 Catch:{ Exception -> 0x0300 }
        r14 = " hw_sr:";
        r13.append(r14);	 Catch:{ Exception -> 0x0300 }
        r14 = r1._streamType;	 Catch:{ Exception -> 0x0300 }
        r14 = android.media.AudioTrack.getNativeOutputSampleRate(r14);	 Catch:{ Exception -> 0x0300 }
        r13.append(r14);	 Catch:{ Exception -> 0x0300 }
        r13 = r13.toString();	 Catch:{ Exception -> 0x0300 }
        com.tencent.rtmp.sharp.jni.QLog.w(r0, r6, r13);	 Catch:{ Exception -> 0x0300 }
    L_0x01e2:
        r13 = 1;
    L_0x01e3:
        if (r13 > r6) goto L_0x02a1;
    L_0x01e5:
        r0 = r7 * r13;
        r14 = r12 * 4;
        if (r0 >= r14) goto L_0x01f1;
    L_0x01eb:
        if (r13 >= r6) goto L_0x01f1;
    L_0x01ed:
        r3 = r4;
        r4 = 1;
        goto L_0x029a;
    L_0x01f1:
        r15 = new android.media.AudioTrack;	 Catch:{ Exception -> 0x0267 }
        r14 = r1._streamType;	 Catch:{ Exception -> 0x0267 }
        r10 = r1._playSamplerate;	 Catch:{ Exception -> 0x0267 }
        r4 = r1._channelOutType;	 Catch:{ Exception -> 0x0267 }
        r18 = 2;
        r20 = 1;
        r16 = r14;
        r14 = r15;
        r3 = r15;
        r15 = r16;
        r16 = r10;
        r17 = r4;
        r19 = r0;
        r14.<init>(r15, r16, r17, r18, r19, r20);	 Catch:{ Exception -> 0x0267 }
        r1._audioTrack = r3;	 Catch:{ Exception -> 0x0267 }
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0300 }
        if (r3 == 0) goto L_0x022c;
    L_0x0214:
        r3 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0300 }
        r4.<init>();	 Catch:{ Exception -> 0x0300 }
        r10 = " _audioTrack:";
        r4.append(r10);	 Catch:{ Exception -> 0x0300 }
        r10 = r1._audioTrack;	 Catch:{ Exception -> 0x0300 }
        r4.append(r10);	 Catch:{ Exception -> 0x0300 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0300 }
        com.tencent.rtmp.sharp.jni.QLog.w(r3, r6, r4);	 Catch:{ Exception -> 0x0300 }
    L_0x022c:
        r3 = r1._audioTrack;	 Catch:{ Exception -> 0x0300 }
        r3 = r3.getState();	 Catch:{ Exception -> 0x0300 }
        r4 = 1;
        if (r3 == r4) goto L_0x0265;
    L_0x0235:
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0300 }
        if (r3 == 0) goto L_0x025b;
    L_0x023b:
        r3 = "TRAE";
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0300 }
        r10.<init>();	 Catch:{ Exception -> 0x0300 }
        r14 = "InitPlayback: play not initialized playBufSize:";
        r10.append(r14);	 Catch:{ Exception -> 0x0300 }
        r10.append(r0);	 Catch:{ Exception -> 0x0300 }
        r0 = " sr:";
        r10.append(r0);	 Catch:{ Exception -> 0x0300 }
        r0 = r1._playSamplerate;	 Catch:{ Exception -> 0x0300 }
        r10.append(r0);	 Catch:{ Exception -> 0x0300 }
        r0 = r10.toString();	 Catch:{ Exception -> 0x0300 }
        com.tencent.rtmp.sharp.jni.QLog.w(r3, r6, r0);	 Catch:{ Exception -> 0x0300 }
    L_0x025b:
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x0300 }
        r0.release();	 Catch:{ Exception -> 0x0300 }
        r3 = 0;
        r1._audioTrack = r3;	 Catch:{ Exception -> 0x0300 }
        r3 = 0;
        goto L_0x029a;
    L_0x0265:
        r3 = 0;
        goto L_0x02a3;
    L_0x0267:
        r0 = move-exception;
        r4 = 1;
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0300 }
        if (r3 == 0) goto L_0x028e;
    L_0x026f:
        r3 = "TRAE";
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0300 }
        r10.<init>();	 Catch:{ Exception -> 0x0300 }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0300 }
        r10.append(r0);	 Catch:{ Exception -> 0x0300 }
        r0 = " _audioTrack:";
        r10.append(r0);	 Catch:{ Exception -> 0x0300 }
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x0300 }
        r10.append(r0);	 Catch:{ Exception -> 0x0300 }
        r0 = r10.toString();	 Catch:{ Exception -> 0x0300 }
        com.tencent.rtmp.sharp.jni.QLog.w(r3, r6, r0);	 Catch:{ Exception -> 0x0300 }
    L_0x028e:
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x0300 }
        if (r0 == 0) goto L_0x0297;
    L_0x0292:
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x0300 }
        r0.release();	 Catch:{ Exception -> 0x0300 }
    L_0x0297:
        r3 = 0;
        r1._audioTrack = r3;	 Catch:{ Exception -> 0x0300 }
    L_0x029a:
        r13 = r13 + 1;
        r4 = r3;
        r3 = 1;
        r10 = 4;
        goto L_0x01e3;
    L_0x02a1:
        r3 = r4;
        r4 = 1;
    L_0x02a3:
        r5 = r5 + 1;
        r4 = r3;
        r3 = 1;
        r10 = 4;
        goto L_0x01ad;
    L_0x02aa:
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x0300 }
        if (r0 == 0) goto L_0x02d7;
    L_0x02ae:
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x02d7 }
        r0.play();	 Catch:{ Exception -> 0x02d7 }
        r0 = r1._as;	 Catch:{ Exception -> 0x02d7 }
        r3 = r1._audioManager;	 Catch:{ Exception -> 0x02d7 }
        r3 = r3.getMode();	 Catch:{ Exception -> 0x02d7 }
        r4 = r1._streamType;	 Catch:{ Exception -> 0x02d7 }
        r0.voiceCallAudioParamChanged(r3, r4);	 Catch:{ Exception -> 0x02d7 }
        r0 = r1._audioManager;	 Catch:{ Exception -> 0x02d7 }
        r3 = r1._connectedDev;	 Catch:{ Exception -> 0x02d7 }
        r4 = "DEVICE_BLUETOOTHHEADSET";
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x02d7 }
        if (r3 == 0) goto L_0x02ce;
    L_0x02cc:
        r3 = 6;
        goto L_0x02d4;
    L_0x02ce:
        r3 = r1._audioTrack;	 Catch:{ Exception -> 0x02d7 }
        r3 = r3.getStreamType();	 Catch:{ Exception -> 0x02d7 }
    L_0x02d4:
        com.tencent.rtmp.sharp.jni.TraeAudioManager.forceVolumeControlStream(r0, r3);	 Catch:{ Exception -> 0x02d7 }
    L_0x02d7:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0300 }
        if (r0 == 0) goto L_0x03b9;
    L_0x02dd:
        r0 = "TRAE";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0300 }
        r3.<init>();	 Catch:{ Exception -> 0x0300 }
        r4 = "  track reset used:";
        r3.append(r4);	 Catch:{ Exception -> 0x0300 }
        r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ Exception -> 0x0300 }
        r7 = 0;
        r4 = r4 - r8;
        r3.append(r4);	 Catch:{ Exception -> 0x0300 }
        r4 = "ms";
        r3.append(r4);	 Catch:{ Exception -> 0x0300 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0300 }
        com.tencent.rtmp.sharp.jni.QLog.e(r0, r6, r3);	 Catch:{ Exception -> 0x0300 }
        goto L_0x03b9;
    L_0x0300:
        r0 = move-exception;
        goto L_0x0399;
    L_0x0303:
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x0397 }
        r3 = r1._tempBufPlay;	 Catch:{ Exception -> 0x0397 }
        r3 = r0.write(r3, r5, r2);	 Catch:{ Exception -> 0x0397 }
        r0 = r1._playBuffer;	 Catch:{ Exception -> 0x0392 }
        r0.rewind();	 Catch:{ Exception -> 0x0392 }
        if (r3 >= 0) goto L_0x0341;
    L_0x0312:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0392 }
        if (r0 == 0) goto L_0x033b;
    L_0x0318:
        r0 = "TRAE";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0392 }
        r5.<init>();	 Catch:{ Exception -> 0x0392 }
        r7 = "Could not write data from sc (write = ";
        r5.append(r7);	 Catch:{ Exception -> 0x0392 }
        r5.append(r3);	 Catch:{ Exception -> 0x0392 }
        r7 = ", length = ";
        r5.append(r7);	 Catch:{ Exception -> 0x0392 }
        r5.append(r2);	 Catch:{ Exception -> 0x0392 }
        r2 = ")";
        r5.append(r2);	 Catch:{ Exception -> 0x0392 }
        r2 = r5.toString();	 Catch:{ Exception -> 0x0392 }
        com.tencent.rtmp.sharp.jni.QLog.e(r0, r6, r2);	 Catch:{ Exception -> 0x0392 }
    L_0x033b:
        r0 = r1._playLock;
        r0.unlock();
        return r4;
    L_0x0341:
        if (r3 == r2) goto L_0x036c;
    L_0x0343:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0392 }
        if (r0 == 0) goto L_0x036c;
    L_0x0349:
        r0 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0392 }
        r4.<init>();	 Catch:{ Exception -> 0x0392 }
        r7 = "Could not write all data from sc (write = ";
        r4.append(r7);	 Catch:{ Exception -> 0x0392 }
        r4.append(r3);	 Catch:{ Exception -> 0x0392 }
        r7 = ", length = ";
        r4.append(r7);	 Catch:{ Exception -> 0x0392 }
        r4.append(r2);	 Catch:{ Exception -> 0x0392 }
        r2 = ")";
        r4.append(r2);	 Catch:{ Exception -> 0x0392 }
        r2 = r4.toString();	 Catch:{ Exception -> 0x0392 }
        com.tencent.rtmp.sharp.jni.QLog.e(r0, r6, r2);	 Catch:{ Exception -> 0x0392 }
    L_0x036c:
        r0 = r1._bufferedPlaySamples;	 Catch:{ Exception -> 0x0392 }
        r2 = r3 >> 1;
        r0 = r0 + r2;
        r1._bufferedPlaySamples = r0;	 Catch:{ Exception -> 0x0392 }
        r0 = r1._audioTrack;	 Catch:{ Exception -> 0x0392 }
        r0 = r0.getPlaybackHeadPosition();	 Catch:{ Exception -> 0x0392 }
        r2 = r1._playPosition;	 Catch:{ Exception -> 0x0392 }
        if (r0 >= r2) goto L_0x037f;
    L_0x037d:
        r1._playPosition = r5;	 Catch:{ Exception -> 0x0392 }
    L_0x037f:
        r2 = r1._bufferedPlaySamples;	 Catch:{ Exception -> 0x0392 }
        r4 = r1._playPosition;	 Catch:{ Exception -> 0x0392 }
        r4 = r0 - r4;
        r2 = r2 - r4;
        r1._bufferedPlaySamples = r2;	 Catch:{ Exception -> 0x0392 }
        r1._playPosition = r0;	 Catch:{ Exception -> 0x0392 }
        r0 = r1._isRecording;	 Catch:{ Exception -> 0x0392 }
        if (r0 != 0) goto L_0x0390;
    L_0x038e:
        r0 = r1._bufferedPlaySamples;	 Catch:{ Exception -> 0x0392 }
    L_0x0390:
        r2 = r3;
        goto L_0x03b9;
    L_0x0392:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0399;
    L_0x0395:
        r0 = move-exception;
        goto L_0x03bf;
    L_0x0397:
        r0 = move-exception;
        r2 = 0;
    L_0x0399:
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ all -> 0x0395 }
        if (r3 == 0) goto L_0x03b9;
    L_0x039f:
        r3 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0395 }
        r4.<init>();	 Catch:{ all -> 0x0395 }
        r5 = "PlayAudio Exception: ";
        r4.append(r5);	 Catch:{ all -> 0x0395 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0395 }
        r4.append(r0);	 Catch:{ all -> 0x0395 }
        r0 = r4.toString();	 Catch:{ all -> 0x0395 }
        com.tencent.rtmp.sharp.jni.QLog.e(r3, r6, r0);	 Catch:{ all -> 0x0395 }
    L_0x03b9:
        r0 = r1._playLock;
        r0.unlock();
        return r2;
    L_0x03bf:
        r2 = r1._playLock;
        r2.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.AudioDeviceInterface.PlayAudio(int):int");
    }

    private int OpenslesNeedResetAudioTrack(boolean z) {
        try {
            if (!TraeAudioManager.isCloseSystemAPM(this._modePolicy)) {
                return -1;
            }
            if (this._audioRouteChanged || z) {
                if (this._audioManager == null && this._context != null) {
                    this._audioManager = (AudioManager) this._context.getSystemService("audio");
                }
                if (this._audioManager == null) {
                    return 0;
                }
                if (this._audioManager.getMode() == 0 && this._connectedDev.equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
                    this._audioStreamTypePolicy = 3;
                } else {
                    this._audioStreamTypePolicy = 0;
                }
                this._audioRouteChanged = false;
            }
            return this._audioStreamTypePolicy;
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("PlayAudio Exception: ");
                stringBuilder.append(e.getMessage());
                QLog.e("TRAE", 2, stringBuilder.toString());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00fa A:{Catch:{ all -> 0x00f0 }} */
    private int RecordAudio(int r8) {
        /*
        r7 = this;
        r0 = r7._isRecording;
        r1 = -1;
        r2 = 2;
        if (r0 != 0) goto L_0x0025;
    L_0x0006:
        r8 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r8 == 0) goto L_0x0024;
    L_0x000c:
        r8 = "TRAE";
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r3 = "RecordAudio: _isRecording ";
        r0.append(r3);
        r3 = r7._isRecording;
        r0.append(r3);
        r0 = r0.toString();
        com.tencent.rtmp.sharp.jni.QLog.e(r8, r2, r0);
    L_0x0024:
        return r1;
    L_0x0025:
        r0 = r7._recLock;
        r0.lock();
        r0 = 0;
        r3 = r7._audioRecord;	 Catch:{ Exception -> 0x00f2 }
        if (r3 != 0) goto L_0x0036;
    L_0x002f:
        r8 = -2;
        r0 = r7._recLock;
        r0.unlock();
        return r8;
    L_0x0036:
        r3 = r7._doRecInit;	 Catch:{ Exception -> 0x00f2 }
        r4 = 1;
        if (r3 != r4) goto L_0x0064;
    L_0x003b:
        r3 = -19;
        android.os.Process.setThreadPriority(r3);	 Catch:{ Exception -> 0x0041 }
        goto L_0x0062;
    L_0x0041:
        r3 = move-exception;
        r4 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x00f2 }
        if (r4 == 0) goto L_0x0062;
    L_0x0048:
        r4 = "TRAE";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00f2 }
        r5.<init>();	 Catch:{ Exception -> 0x00f2 }
        r6 = "Set rec thread priority failed: ";
        r5.append(r6);	 Catch:{ Exception -> 0x00f2 }
        r3 = r3.getMessage();	 Catch:{ Exception -> 0x00f2 }
        r5.append(r3);	 Catch:{ Exception -> 0x00f2 }
        r3 = r5.toString();	 Catch:{ Exception -> 0x00f2 }
        com.tencent.rtmp.sharp.jni.QLog.w(r4, r2, r3);	 Catch:{ Exception -> 0x00f2 }
    L_0x0062:
        r7._doRecInit = r0;	 Catch:{ Exception -> 0x00f2 }
    L_0x0064:
        r3 = r7._recBuffer;	 Catch:{ Exception -> 0x00f2 }
        r3.rewind();	 Catch:{ Exception -> 0x00f2 }
        r3 = r7._audioRecord;	 Catch:{ Exception -> 0x00f2 }
        r4 = r7._tempBufRec;	 Catch:{ Exception -> 0x00f2 }
        r3 = r3.read(r4, r0, r8);	 Catch:{ Exception -> 0x00f2 }
        if (r3 >= 0) goto L_0x00a4;
    L_0x0073:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x00a2 }
        if (r0 == 0) goto L_0x009c;
    L_0x0079:
        r0 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a2 }
        r4.<init>();	 Catch:{ Exception -> 0x00a2 }
        r5 = "Could not read data from sc (read = ";
        r4.append(r5);	 Catch:{ Exception -> 0x00a2 }
        r4.append(r3);	 Catch:{ Exception -> 0x00a2 }
        r5 = ", length = ";
        r4.append(r5);	 Catch:{ Exception -> 0x00a2 }
        r4.append(r8);	 Catch:{ Exception -> 0x00a2 }
        r8 = ")";
        r4.append(r8);	 Catch:{ Exception -> 0x00a2 }
        r8 = r4.toString();	 Catch:{ Exception -> 0x00a2 }
        com.tencent.rtmp.sharp.jni.QLog.e(r0, r2, r8);	 Catch:{ Exception -> 0x00a2 }
    L_0x009c:
        r8 = r7._recLock;
        r8.unlock();
        return r1;
    L_0x00a2:
        r8 = move-exception;
        goto L_0x00f4;
    L_0x00a4:
        r4 = r7._recBuffer;	 Catch:{ Exception -> 0x00a2 }
        r5 = r7._tempBufRec;	 Catch:{ Exception -> 0x00a2 }
        r4.put(r5, r0, r3);	 Catch:{ Exception -> 0x00a2 }
        r4 = _dumpEnable;	 Catch:{ Exception -> 0x00a2 }
        if (r4 == 0) goto L_0x00bf;
    L_0x00af:
        r4 = r7._rec_out;	 Catch:{ Exception -> 0x00a2 }
        if (r4 == 0) goto L_0x00bf;
    L_0x00b3:
        r4 = r7._rec_out;	 Catch:{ IOException -> 0x00bb }
        r5 = r7._tempBufRec;	 Catch:{ IOException -> 0x00bb }
        r4.write(r5, r0, r3);	 Catch:{ IOException -> 0x00bb }
        goto L_0x00bf;
    L_0x00bb:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x00a2 }
    L_0x00bf:
        if (r3 == r8) goto L_0x0114;
    L_0x00c1:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x00a2 }
        if (r0 == 0) goto L_0x00ea;
    L_0x00c7:
        r0 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a2 }
        r4.<init>();	 Catch:{ Exception -> 0x00a2 }
        r5 = "Could not read all data from sc (read = ";
        r4.append(r5);	 Catch:{ Exception -> 0x00a2 }
        r4.append(r3);	 Catch:{ Exception -> 0x00a2 }
        r5 = ", length = ";
        r4.append(r5);	 Catch:{ Exception -> 0x00a2 }
        r4.append(r8);	 Catch:{ Exception -> 0x00a2 }
        r8 = ")";
        r4.append(r8);	 Catch:{ Exception -> 0x00a2 }
        r8 = r4.toString();	 Catch:{ Exception -> 0x00a2 }
        com.tencent.rtmp.sharp.jni.QLog.e(r0, r2, r8);	 Catch:{ Exception -> 0x00a2 }
    L_0x00ea:
        r8 = r7._recLock;
        r8.unlock();
        return r1;
    L_0x00f0:
        r8 = move-exception;
        goto L_0x011a;
    L_0x00f2:
        r8 = move-exception;
        r3 = 0;
    L_0x00f4:
        r0 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ all -> 0x00f0 }
        if (r0 == 0) goto L_0x0114;
    L_0x00fa:
        r0 = "TRAE";
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00f0 }
        r1.<init>();	 Catch:{ all -> 0x00f0 }
        r4 = "RecordAudio Exception: ";
        r1.append(r4);	 Catch:{ all -> 0x00f0 }
        r8 = r8.getMessage();	 Catch:{ all -> 0x00f0 }
        r1.append(r8);	 Catch:{ all -> 0x00f0 }
        r8 = r1.toString();	 Catch:{ all -> 0x00f0 }
        com.tencent.rtmp.sharp.jni.QLog.e(r0, r2, r8);	 Catch:{ all -> 0x00f0 }
    L_0x0114:
        r8 = r7._recLock;
        r8.unlock();
        return r3;
    L_0x011a:
        r0 = r7._recLock;
        r0.unlock();
        throw r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.AudioDeviceInterface.RecordAudio(int):int");
    }

    private int SetPlayoutVolume(int i) {
        if (this._audioManager == null && this._context != null) {
            this._audioManager = (AudioManager) this._context.getSystemService("audio");
        }
        if (this._audioManager == null) {
            return -1;
        }
        this._audioManager.setStreamVolume(0, i, 0);
        return 0;
    }

    private int GetPlayoutVolume() {
        if (this._audioManager == null && this._context != null) {
            this._audioManager = (AudioManager) this._context.getSystemService("audio");
        }
        if (this._audioManager != null) {
            return this._audioManager.getStreamVolume(0);
        }
        return -1;
    }

    public static String getTraceInfo() {
        StringBuffer stringBuffer = new StringBuffer();
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int length = stackTrace.length;
        stringBuffer.append("");
        stringBuffer.append(stackTrace[2].getClassName());
        stringBuffer.append(".");
        stringBuffer.append(stackTrace[2].getMethodName());
        stringBuffer.append(": ");
        stringBuffer.append(stackTrace[2].getLineNumber());
        return stringBuffer.toString();
    }

    public static final void LogTraceEntry(String str) {
        if (_logEnable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getTraceInfo());
            stringBuilder.append(" entry:");
            stringBuilder.append(str);
            str = stringBuilder.toString();
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, str);
            }
        }
    }

    public static final void LogTraceExit() {
        if (_logEnable) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getTraceInfo());
            stringBuilder.append(" exit");
            String stringBuilder2 = stringBuilder.toString();
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, stringBuilder2);
            }
        }
    }

    private void onOutputChanage(String str) {
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" onOutputChanage:");
            stringBuilder.append(str);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
        setAudioRouteSwitchState(str);
        if (TraeAudioManager.isCloseSystemAPM(this._modePolicy) && this._sceneModeKey != 1 && this._sceneModeKey != 2 && this._sceneModeKey != 3) {
            String str2;
            StringBuilder stringBuilder2;
            this._connectedDev = str;
            if (QLog.isColorLevel()) {
                String str3;
                StringBuilder stringBuilder3;
                str2 = "TRAE";
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(" onOutputChanage:");
                stringBuilder2.append(str);
                if (this._audioManager == null) {
                    str3 = " am==null";
                } else {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(" mode:");
                    stringBuilder3.append(this._audioManager.getMode());
                    str3 = stringBuilder3.toString();
                }
                stringBuilder2.append(str3);
                stringBuilder2.append(" st:");
                stringBuilder2.append(this._streamType);
                if (this._audioTrack == null) {
                    str3 = "_audioTrack==null";
                } else {
                    stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(" at.st:");
                    stringBuilder3.append(this._audioTrack.getStreamType());
                    str3 = stringBuilder3.toString();
                }
                stringBuilder2.append(str3);
                QLog.w(str2, 2, stringBuilder2.toString());
            }
            try {
                if (this._audioManager == null) {
                    this._audioManager = (AudioManager) this._context.getSystemService("audio");
                }
                if (QLog.isColorLevel()) {
                    str2 = "TRAE";
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(" curr mode:");
                    stringBuilder2.append(str);
                    if (this._audioManager == null) {
                        str = "am==null";
                    } else {
                        StringBuilder stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(" mode:");
                        stringBuilder4.append(this._audioManager.getMode());
                        str = stringBuilder4.toString();
                    }
                    stringBuilder2.append(str);
                    QLog.w(str2, 2, stringBuilder2.toString());
                }
                if (this._audioManager != null && this._connectedDev.equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
                    this._audioManager.setMode(0);
                }
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 2, e.getMessage());
                }
            }
            this._audioRouteChanged = true;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0082 */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:6|7|8|(2:9|(3:11|12|(3:14|(2:16|29)(1:30)|17)(1:28))(1:27))|18|(1:20)(0)|21|22|31) */
    public int call_preprocess() {
        /*
        r7 = this;
        r0 = "";
        LogTraceEntry(r0);
        r0 = 0;
        r7.switchState = r0;
        r1 = r7._audioStreamTypePolicy;
        r1 = com.tencent.rtmp.sharp.jni.TraeAudioManager.getAudioStreamType(r1);
        r7._streamType = r1;
        r1 = r7._as;
        r2 = 2;
        if (r1 != 0) goto L_0x001c;
    L_0x0015:
        r1 = "TRAE";
        r3 = "TraeAudioSession-LeakCheck init: call_preprocess";
        com.tencent.rtmp.sharp.jni.QLog.e(r1, r2, r3);
    L_0x001c:
        r1 = new com.tencent.rtmp.sharp.jni.TraeAudioSession;
        r3 = r7._context;
        r4 = new com.tencent.rtmp.sharp.jni.AudioDeviceInterface$1;
        r4.<init>();
        r1.<init>(r3, r4);
        r7._as = r1;
        r7._preDone = r0;
        r1 = r7._as;
        if (r1 == 0) goto L_0x0094;
    L_0x0030:
        r1 = r7._prelock;
        r1.lock();
        r1 = r7._as;	 Catch:{ all -> 0x008d }
        r3 = r7._modePolicy;	 Catch:{ all -> 0x008d }
        r4 = r7._streamType;	 Catch:{ all -> 0x008d }
        r1.voiceCallPreprocess(r3, r4);	 Catch:{ all -> 0x008d }
        r1 = r7._as;	 Catch:{ all -> 0x008d }
        r1.connectHighestPriorityDevice();	 Catch:{ all -> 0x008d }
        r1 = 7;
    L_0x0044:
        r3 = r1 + -1;
        if (r1 <= 0) goto L_0x0075;
    L_0x0048:
        r1 = r7._preDone;	 Catch:{ InterruptedException -> 0x0082 }
        if (r1 != 0) goto L_0x0075;
    L_0x004c:
        r1 = r7._precon;	 Catch:{ InterruptedException -> 0x0082 }
        r4 = 1;
        r6 = java.util.concurrent.TimeUnit.SECONDS;	 Catch:{ InterruptedException -> 0x0082 }
        r1.await(r4, r6);	 Catch:{ InterruptedException -> 0x0082 }
        r1 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ InterruptedException -> 0x0082 }
        if (r1 == 0) goto L_0x0073;
    L_0x005b:
        r1 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x0082 }
        r4.<init>();	 Catch:{ InterruptedException -> 0x0082 }
        r5 = "call_preprocess waiting...  as:";
        r4.append(r5);	 Catch:{ InterruptedException -> 0x0082 }
        r5 = r7._as;	 Catch:{ InterruptedException -> 0x0082 }
        r4.append(r5);	 Catch:{ InterruptedException -> 0x0082 }
        r4 = r4.toString();	 Catch:{ InterruptedException -> 0x0082 }
        com.tencent.rtmp.sharp.jni.QLog.e(r1, r2, r4);	 Catch:{ InterruptedException -> 0x0082 }
    L_0x0073:
        r1 = r3;
        goto L_0x0044;
    L_0x0075:
        r1 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ InterruptedException -> 0x0082 }
        if (r1 == 0) goto L_0x0082;
    L_0x007b:
        r1 = "TRAE";
        r3 = "call_preprocess done!";
        com.tencent.rtmp.sharp.jni.QLog.e(r1, r2, r3);	 Catch:{ InterruptedException -> 0x0082 }
    L_0x0082:
        r1 = r7._as;	 Catch:{ all -> 0x008d }
        r1.getConnectedDevice();	 Catch:{ all -> 0x008d }
        r1 = r7._prelock;
        r1.unlock();
        goto L_0x0094;
    L_0x008d:
        r0 = move-exception;
        r1 = r7._prelock;
        r1.unlock();
        throw r0;
    L_0x0094:
        LogTraceExit();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.AudioDeviceInterface.call_preprocess():int");
    }

    public int call_postprocess() {
        LogTraceEntry("");
        this.switchState = 0;
        if (this._as != null) {
            this._as.voiceCallPostprocess();
            this._as.release();
            this._as = null;
            QLog.e("TRAE", 2, "TraeAudioSession-LeakCheck release: call_postprocess");
        }
        TraeAudioManager.IsUpdateSceneFlag = false;
        LogTraceExit();
        return 0;
    }

    public int call_preprocess_media() {
        LogTraceEntry("");
        this.switchState = 0;
        if (this._as == null) {
            QLog.e("TRAE", 2, "TraeAudioSession-LeakCheck init: call_preprocess_media");
        }
        this._as = new TraeAudioSession(this._context, new a() {
            public void a(int i) {
            }

            public void a(int i, int i2) {
            }

            public void a(int i, String str, boolean z) {
            }

            public void a(int i, boolean z) {
            }

            public void a(int i, String[] strArr, String str, String str2, String str3) {
            }

            public void a(String str, long j) {
            }

            public void a(String str, String str2) {
            }

            public void a(boolean z) {
            }

            public void b(int i) {
            }

            public void b(int i, String str) {
            }

            public void b(boolean z) {
            }

            public void c(int i, String str) {
            }

            public void a(String[] strArr, String str, String str2, String str3) {
                if (AudioDeviceInterface.this.usingJava) {
                    AudioDeviceInterface.this.onOutputChanage(str);
                }
            }

            public void a(int i, String str) {
                if (i == 0) {
                    AudioDeviceInterface.this.onOutputChanage(str);
                }
            }
        });
        if (this._as != null) {
            try {
                if (this._audioManager == null) {
                    this._audioManager = (AudioManager) this._context.getSystemService("audio");
                }
                if (this._audioManager != null) {
                    if (this._audioManager.getMode() == 2) {
                        int i = 5;
                        while (true) {
                            int i2 = i - 1;
                            if (i <= 0 || this._audioManager.getMode() != 2) {
                                break;
                            }
                            if (QLog.isColorLevel()) {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("media call_preprocess waiting...  mode:");
                                stringBuilder.append(this._audioManager.getMode());
                                QLog.e("TRAE", 2, stringBuilder.toString());
                            }
                            Thread.sleep(500);
                            i = i2;
                        }
                    }
                    if (this._audioManager.getMode() != 0) {
                        this._audioManager.setMode(0);
                    }
                }
                this._as.connectHighestPriorityDevice();
                this._as.getConnectedDevice();
                if (QLog.isColorLevel()) {
                    QLog.e("TRAE", 2, "call_preprocess done!");
                }
            } catch (InterruptedException unused) {
            }
        }
        LogTraceExit();
        return 0;
    }

    public int call_postprocess_media() {
        LogTraceEntry("");
        this.switchState = 0;
        if (this._as != null) {
            this._as.release();
            this._as = null;
            QLog.e("TRAE", 2, "TraeAudioSession-LeakCheck release: call_postprocess_media");
        }
        TraeAudioManager.IsUpdateSceneFlag = false;
        LogTraceExit();
        return 0;
    }

    public void setJavaInterface(int i) {
        if (i == 0) {
            this.usingJava = false;
        } else {
            this.usingJava = true;
        }
        if (QLog.isColorLevel()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setJavaInterface flg:");
            stringBuilder.append(i);
            QLog.w("TRAE", 2, stringBuilder.toString());
        }
    }

    private void setAudioRouteSwitchState(String str) {
        if (str.equals(TraeAudioManager.DEVICE_EARPHONE)) {
            this.switchState = 1;
        } else if (str.equals(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
            this.switchState = 2;
        } else if (str.equals(TraeAudioManager.DEVICE_WIREDHEADSET)) {
            this.switchState = 3;
        } else if (str.equals(TraeAudioManager.DEVICE_BLUETOOTHHEADSET)) {
            this.switchState = 4;
        } else {
            this.switchState = 0;
        }
    }

    public int getAudioRouteSwitchState() {
        return this.switchState;
    }

    private void initTRAEAudioManager() {
        if (this._context != null) {
            TraeAudioManager.init(this._context);
            if (this._init_as == null) {
                QLog.e("TRAE", 2, "TraeAudioSession-LeakCheck init: initTRAEAudioManager");
                this._init_as = new TraeAudioSession(this._context, null);
            }
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, "initTRAEAudioManager , TraeAudioSession startService");
            }
            this._init_as.startService(TraeAudioManager.MUSIC_CONFIG);
        }
    }

    private int getAndroidSdkVersion() {
        return VERSION.SDK_INT;
    }

    private void uninitTRAEAudioManager() {
        if (this._context != null) {
            if (this._init_as != null) {
                if (QLog.isColorLevel()) {
                    QLog.w("TRAE", 2, "uninitTRAEAudioManager , stopService");
                }
                this._init_as.stopService();
                this._init_as.release();
                this._init_as = null;
                QLog.e("TRAE", 2, "TraeAudioSession-LeakCheck release: uninitTRAEAudioManager");
            }
            if (QLog.isColorLevel()) {
                QLog.w("TRAE", 2, "uninitTRAEAudioManager , stopService");
            }
            TraeAudioManager.uninit();
        } else if (QLog.isColorLevel()) {
            QLog.w("TRAE", 2, "uninitTRAEAudioManager , context null");
        }
    }
}
