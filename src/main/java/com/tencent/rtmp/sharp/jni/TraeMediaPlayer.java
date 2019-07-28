package com.tencent.rtmp.sharp.jni;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import java.util.Timer;
import java.util.TimerTask;

public class TraeMediaPlayer implements OnCompletionListener, OnErrorListener {
    public static final int TRAE_MEDIAPLAER_DATASOURCE_FILEPATH = 2;
    public static final int TRAE_MEDIAPLAER_DATASOURCE_RSID = 0;
    public static final int TRAE_MEDIAPLAER_DATASOURCE_URI = 1;
    public static final int TRAE_MEDIAPLAER_STOP = 100;
    private Context _context;
    private int _durationMS = -1;
    private boolean _hasCall = false;
    private boolean _loop = false;
    int _loopCount = 0;
    private int _prevVolume = -1;
    boolean _ringMode = false;
    private int _streamType = 0;
    private Timer _watchTimer = null;
    private TimerTask _watchTimertask = null;
    private a mCallback;
    private MediaPlayer mMediaPlay = null;

    public interface a {
        void a();
    }

    public TraeMediaPlayer(Context context, a aVar) {
        this._context = context;
        this.mCallback = aVar;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00cb */
    public boolean playRing(int r22, int r23, android.net.Uri r24, java.lang.String r25, boolean r26, int r27, boolean r28, boolean r29, int r30) {
        /*
        r21 = this;
        r1 = r21;
        r2 = r22;
        r3 = r23;
        r4 = r24;
        r5 = r25;
        r6 = r26;
        r7 = r27;
        r8 = r28;
        r9 = r29;
        r10 = r30;
        r11 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        r12 = 2;
        if (r11 == 0) goto L_0x007f;
    L_0x001b:
        r11 = "TRAE";
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r14 = "TraeMediaPlay | playRing datasource:";
        r13.append(r14);
        r13.append(r2);
        r14 = " rsid:";
        r13.append(r14);
        r13.append(r3);
        r14 = " uri:";
        r13.append(r14);
        r13.append(r4);
        r14 = " filepath:";
        r13.append(r14);
        r13.append(r5);
        r14 = " loop:";
        r13.append(r14);
        if (r6 == 0) goto L_0x004c;
    L_0x0049:
        r14 = "Y";
        goto L_0x004e;
    L_0x004c:
        r14 = "N";
    L_0x004e:
        r13.append(r14);
        r14 = " :loopCount";
        r13.append(r14);
        r13.append(r7);
        r14 = " ringMode:";
        r13.append(r14);
        if (r8 == 0) goto L_0x0063;
    L_0x0060:
        r14 = "Y";
        goto L_0x0065;
    L_0x0063:
        r14 = "N";
    L_0x0065:
        r13.append(r14);
        r14 = " hasCall:";
        r13.append(r14);
        r13.append(r9);
        r14 = " cst:";
        r13.append(r14);
        r13.append(r10);
        r13 = r13.toString();
        com.tencent.rtmp.sharp.jni.QLog.e(r11, r12, r13);
    L_0x007f:
        r11 = 0;
        if (r6 != 0) goto L_0x00b8;
    L_0x0082:
        if (r7 > 0) goto L_0x00b8;
    L_0x0084:
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r3 == 0) goto L_0x00b7;
    L_0x008a:
        r3 = "TRAE";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "TraeMediaPlay | playRing err datasource:";
        r4.append(r5);
        r4.append(r2);
        r2 = " loop:";
        r4.append(r2);
        if (r6 == 0) goto L_0x00a3;
    L_0x00a0:
        r2 = "Y";
        goto L_0x00a5;
    L_0x00a3:
        r2 = "N";
    L_0x00a5:
        r4.append(r2);
        r2 = " :loopCount";
        r4.append(r2);
        r4.append(r7);
        r2 = r4.toString();
        com.tencent.rtmp.sharp.jni.QLog.e(r3, r12, r2);
    L_0x00b7:
        return r11;
    L_0x00b8:
        r13 = 0;
        r14 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r14 == 0) goto L_0x00d3;
    L_0x00bd:
        r14 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r14 = r14.isPlaying();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r14 == 0) goto L_0x00c6;
    L_0x00c5:
        return r11;
    L_0x00c6:
        r14 = r1.mMediaPlay;	 Catch:{ Exception -> 0x00cb, all -> 0x00ce }
        r14.release();	 Catch:{ Exception -> 0x00cb, all -> 0x00ce }
    L_0x00cb:
        r1.mMediaPlay = r13;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        goto L_0x00d3;
    L_0x00ce:
        r0 = move-exception;
        r2 = r0;
        r1.mMediaPlay = r13;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        throw r2;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x00d3:
        r14 = r1._watchTimer;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r14 == 0) goto L_0x00e0;
    L_0x00d7:
        r14 = r1._watchTimer;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r14.cancel();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r1._watchTimer = r13;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r1._watchTimertask = r13;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x00e0:
        r14 = r1._context;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r15 = "audio";
        r14 = r14.getSystemService(r15);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r14 = (android.media.AudioManager) r14;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r15 = new android.media.MediaPlayer;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r15.<init>();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r1.mMediaPlay = r15;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r15 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r15 != 0) goto L_0x00fd;
    L_0x00f5:
        r2 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2.release();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r1.mMediaPlay = r13;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        return r11;
    L_0x00fd:
        r15 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r15.setOnCompletionListener(r1);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r15 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r15.setOnErrorListener(r1);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        switch(r2) {
            case 0: goto L_0x0158;
            case 1: goto L_0x0133;
            case 2: goto L_0x0110;
            default: goto L_0x010a;
        };	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x010a:
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        goto L_0x01b9;
    L_0x0110:
        r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r2 == 0) goto L_0x012c;
    L_0x0116:
        r2 = "TRAE";
        r3 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r3.<init>();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = "TraeMediaPlay | FilePath:";
        r3.append(r4);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r3.append(r5);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r3 = r3.toString();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        com.tencent.rtmp.sharp.jni.QLog.e(r2, r12, r3);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x012c:
        r2 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2.setDataSource(r5);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        goto L_0x01d8;
    L_0x0133:
        r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r2 == 0) goto L_0x014f;
    L_0x0139:
        r2 = "TRAE";
        r3 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r3.<init>();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = "TraeMediaPlay | uri:";
        r3.append(r5);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r3.append(r4);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r3 = r3.toString();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        com.tencent.rtmp.sharp.jni.QLog.e(r2, r12, r3);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x014f:
        r2 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r3 = r1._context;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2.setDataSource(r3, r4);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        goto L_0x01d8;
    L_0x0158:
        r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r2 == 0) goto L_0x0174;
    L_0x015e:
        r2 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.<init>();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = "TraeMediaPlay | rsid:";
        r4.append(r5);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.append(r3);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r4.toString();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        com.tencent.rtmp.sharp.jni.QLog.e(r2, r12, r4);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x0174:
        r2 = r1._context;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2 = r2.getResources();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2 = r2.openRawResourceFd(r3);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r2 != 0) goto L_0x01a4;
    L_0x0180:
        r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r2 == 0) goto L_0x019c;
    L_0x0186:
        r2 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.<init>();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = "TraeMediaPlay | afd == null rsid:";
        r4.append(r5);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.append(r3);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r3 = r4.toString();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        com.tencent.rtmp.sharp.jni.QLog.e(r2, r12, r3);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x019c:
        r2 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2.release();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r1.mMediaPlay = r13;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        return r11;
    L_0x01a4:
        r15 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r16 = r2.getFileDescriptor();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r17 = r2.getStartOffset();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r19 = r2.getLength();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r15.setDataSource(r16, r17, r19);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2.close();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        goto L_0x01d8;
    L_0x01b9:
        if (r3 == 0) goto L_0x01d1;
    L_0x01bb:
        r3 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.<init>();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = "TraeMediaPlay | err datasource:";
        r4.append(r5);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.append(r2);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2 = r4.toString();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        com.tencent.rtmp.sharp.jni.QLog.e(r3, r12, r2);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x01d1:
        r2 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2.release();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r1.mMediaPlay = r13;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x01d8:
        r2 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r2 != 0) goto L_0x01dd;
    L_0x01dc:
        return r11;
    L_0x01dd:
        r1._ringMode = r8;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2 = r1._ringMode;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r3 = 1;
        if (r2 == 0) goto L_0x01e8;
    L_0x01e4:
        r1._streamType = r12;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2 = 1;
        goto L_0x01f3;
    L_0x01e8:
        r1._streamType = r11;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2 = android.os.Build.VERSION.SDK_INT;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = 11;
        if (r2 < r4) goto L_0x01f2;
    L_0x01f0:
        r2 = 3;
        goto L_0x01f3;
    L_0x01f2:
        r2 = 0;
    L_0x01f3:
        r1._hasCall = r9;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r1._hasCall;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r4 == 0) goto L_0x01fb;
    L_0x01f9:
        r1._streamType = r10;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x01fb:
        r4 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = r1._streamType;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.setAudioStreamType(r5);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.prepare();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.setLooping(r6);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.start();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r1._loop = r6;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r1._loop;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r4 != r3) goto L_0x021d;
    L_0x0217:
        r1._loopCount = r3;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = -1;
        r1._durationMS = r4;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        goto L_0x022b;
    L_0x021d:
        r1._loopCount = r7;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r1._loopCount;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = r5.getDuration();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r4 * r5;
        r1._durationMS = r4;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x022b:
        r4 = r1._loopCount;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r4 - r3;
        r1._loopCount = r4;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r1._hasCall;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r4 != 0) goto L_0x0237;
    L_0x0234:
        r14.setMode(r2);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x0237:
        r2 = r1._durationMS;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r2 <= 0) goto L_0x0255;
    L_0x023b:
        r2 = new java.util.Timer;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2.<init>();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r1._watchTimer = r2;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2 = new com.tencent.rtmp.sharp.jni.TraeMediaPlayer$1;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2.<init>();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r1._watchTimertask = r2;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2 = r1._watchTimer;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r1._watchTimertask;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = r1._durationMS;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = r5 + 1000;
        r7 = (long) r5;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r2.schedule(r4, r7);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x0255:
        r2 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        if (r2 == 0) goto L_0x027f;
    L_0x025b:
        r2 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.<init>();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = "TraeMediaPlay | DurationMS:";
        r4.append(r5);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = r1.mMediaPlay;	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = r5.getDuration();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.append(r5);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r5 = " loop:";
        r4.append(r5);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4.append(r6);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        r4 = r4.toString();	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
        com.tencent.rtmp.sharp.jni.QLog.e(r2, r12, r4);	 Catch:{ IllegalStateException -> 0x0313, IOException -> 0x02e4, IllegalArgumentException -> 0x02b4, SecurityException -> 0x0284 }
    L_0x027f:
        return r3;
    L_0x0280:
        r0 = move-exception;
        r2 = r0;
        goto L_0x0342;
    L_0x0284:
        r0 = move-exception;
        r2 = r0;
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0280 }
        if (r3 == 0) goto L_0x036e;
    L_0x028c:
        r3 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0280 }
        r4.<init>();	 Catch:{ Exception -> 0x0280 }
        r5 = "TraeMediaPlay | SecurityException: ";
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r5 = r2.getLocalizedMessage();	 Catch:{ Exception -> 0x0280 }
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r5 = " ";
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r2 = r2.getMessage();	 Catch:{ Exception -> 0x0280 }
        r4.append(r2);	 Catch:{ Exception -> 0x0280 }
        r2 = r4.toString();	 Catch:{ Exception -> 0x0280 }
        com.tencent.rtmp.sharp.jni.QLog.d(r3, r12, r2);	 Catch:{ Exception -> 0x0280 }
        goto L_0x036e;
    L_0x02b4:
        r0 = move-exception;
        r2 = r0;
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0280 }
        if (r3 == 0) goto L_0x036e;
    L_0x02bc:
        r3 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0280 }
        r4.<init>();	 Catch:{ Exception -> 0x0280 }
        r5 = "TraeMediaPlay | IllegalArgumentException: ";
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r5 = r2.getLocalizedMessage();	 Catch:{ Exception -> 0x0280 }
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r5 = " ";
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r2 = r2.getMessage();	 Catch:{ Exception -> 0x0280 }
        r4.append(r2);	 Catch:{ Exception -> 0x0280 }
        r2 = r4.toString();	 Catch:{ Exception -> 0x0280 }
        com.tencent.rtmp.sharp.jni.QLog.d(r3, r12, r2);	 Catch:{ Exception -> 0x0280 }
        goto L_0x036e;
    L_0x02e4:
        r0 = move-exception;
        r2 = r0;
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0280 }
        if (r3 == 0) goto L_0x036e;
    L_0x02ec:
        r3 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0280 }
        r4.<init>();	 Catch:{ Exception -> 0x0280 }
        r5 = "TraeMediaPlay | IOException: ";
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r5 = r2.getLocalizedMessage();	 Catch:{ Exception -> 0x0280 }
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r5 = " ";
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r2 = r2.getMessage();	 Catch:{ Exception -> 0x0280 }
        r4.append(r2);	 Catch:{ Exception -> 0x0280 }
        r2 = r4.toString();	 Catch:{ Exception -> 0x0280 }
        com.tencent.rtmp.sharp.jni.QLog.d(r3, r12, r2);	 Catch:{ Exception -> 0x0280 }
        goto L_0x036e;
    L_0x0313:
        r0 = move-exception;
        r2 = r0;
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();	 Catch:{ Exception -> 0x0280 }
        if (r3 == 0) goto L_0x036e;
    L_0x031b:
        r3 = "TRAE";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0280 }
        r4.<init>();	 Catch:{ Exception -> 0x0280 }
        r5 = "TraeMediaPlay | IllegalStateException: ";
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r5 = r2.getLocalizedMessage();	 Catch:{ Exception -> 0x0280 }
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r5 = " ";
        r4.append(r5);	 Catch:{ Exception -> 0x0280 }
        r2 = r2.getMessage();	 Catch:{ Exception -> 0x0280 }
        r4.append(r2);	 Catch:{ Exception -> 0x0280 }
        r2 = r4.toString();	 Catch:{ Exception -> 0x0280 }
        com.tencent.rtmp.sharp.jni.QLog.d(r3, r12, r2);	 Catch:{ Exception -> 0x0280 }
        goto L_0x036e;
    L_0x0342:
        r3 = com.tencent.rtmp.sharp.jni.QLog.isColorLevel();
        if (r3 == 0) goto L_0x036e;
    L_0x0348:
        r3 = "TRAE";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "TraeMediaPlay | Except: ";
        r4.append(r5);
        r5 = r2.getLocalizedMessage();
        r4.append(r5);
        r5 = " ";
        r4.append(r5);
        r2 = r2.getMessage();
        r4.append(r2);
        r2 = r4.toString();
        com.tencent.rtmp.sharp.jni.QLog.d(r3, r12, r2);
    L_0x036e:
        r2 = r1.mMediaPlay;	 Catch:{ Exception -> 0x0373 }
        r2.release();	 Catch:{ Exception -> 0x0373 }
    L_0x0373:
        r1.mMediaPlay = r13;
        return r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.rtmp.sharp.jni.TraeMediaPlayer.playRing(int, int, android.net.Uri, java.lang.String, boolean, int, boolean, boolean, int):boolean");
    }

    public void stopRing() {
        if (QLog.isColorLevel()) {
            QLog.d("TRAE", 2, "TraeMediaPlay stopRing ");
        }
        if (this.mMediaPlay != null) {
            if (this.mMediaPlay.isPlaying()) {
                this.mMediaPlay.stop();
            }
            this.mMediaPlay.reset();
            try {
                if (this._watchTimer != null) {
                    this._watchTimer.cancel();
                    this._watchTimer = null;
                    this._watchTimertask = null;
                }
                this.mMediaPlay.release();
            } catch (Exception unused) {
            }
            this.mMediaPlay = null;
            this._durationMS = -1;
        }
    }

    public int getStreamType() {
        return this._streamType;
    }

    public int getDuration() {
        return this._durationMS;
    }

    public boolean hasCall() {
        return this._hasCall;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" cb:");
        stringBuilder.append(this.mCallback);
        stringBuilder.append(" loopCount:");
        stringBuilder.append(this._loopCount);
        stringBuilder.append(" _loop:");
        stringBuilder.append(this._loop);
        AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
        if (this._loop) {
            if (QLog.isColorLevel()) {
                QLog.d("TRAE", 2, "loop play,continue...");
            }
            return;
        }
        try {
            if (this._loopCount <= 0) {
                volumeUndo();
                if (this.mMediaPlay.isPlaying()) {
                    this.mMediaPlay.stop();
                }
                this.mMediaPlay.reset();
                this.mMediaPlay.release();
                this.mMediaPlay = null;
                if (this.mCallback != null) {
                    this.mCallback.a();
                }
            } else {
                this.mMediaPlay.start();
                this._loopCount--;
            }
        } catch (Exception unused) {
        }
        AudioDeviceInterface.LogTraceExit();
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" cb:");
        stringBuilder.append(this.mCallback);
        stringBuilder.append(" arg1:");
        stringBuilder.append(i);
        stringBuilder.append(" arg2:");
        stringBuilder.append(i2);
        AudioDeviceInterface.LogTraceEntry(stringBuilder.toString());
        try {
            this.mMediaPlay.release();
        } catch (Exception unused) {
        }
        this.mMediaPlay = null;
        if (this.mCallback != null) {
            this.mCallback.a();
        }
        AudioDeviceInterface.LogTraceExit();
        return false;
    }

    private void volumeDo() {
        if (this.mMediaPlay != null && this._ringMode && this._streamType != 2) {
            try {
                AudioManager audioManager = (AudioManager) this._context.getSystemService("audio");
                int streamVolume = audioManager.getStreamVolume(this._streamType);
                int streamMaxVolume = audioManager.getStreamMaxVolume(this._streamType);
                int streamVolume2 = audioManager.getStreamVolume(2);
                int streamMaxVolume2 = audioManager.getStreamMaxVolume(2);
                double d = (double) streamVolume2;
                Double.isNaN(d);
                d *= 1.0d;
                double d2 = (double) streamMaxVolume2;
                Double.isNaN(d2);
                d /= d2;
                d2 = (double) streamMaxVolume;
                Double.isNaN(d2);
                int i = (int) (d * d2);
                if (QLog.isColorLevel()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("TraeMediaPlay volumeDo currV:");
                    stringBuilder.append(streamVolume);
                    stringBuilder.append(" maxV:");
                    stringBuilder.append(streamMaxVolume);
                    stringBuilder.append(" currRV:");
                    stringBuilder.append(streamVolume2);
                    stringBuilder.append(" maxRV:");
                    stringBuilder.append(streamMaxVolume2);
                    stringBuilder.append(" setV:");
                    stringBuilder.append(i);
                    QLog.e("TRAE", 2, stringBuilder.toString());
                }
                int i2 = i + 1;
                if (i2 >= streamMaxVolume) {
                    i2 = streamMaxVolume;
                }
                audioManager.setStreamVolume(this._streamType, i2, 0);
                this._prevVolume = streamVolume;
            } catch (Exception unused) {
            }
        }
    }

    private void volumeUndo() {
        if (this.mMediaPlay != null && this._ringMode && this._streamType != 2 && this._prevVolume != -1) {
            try {
                if (QLog.isColorLevel()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("TraeMediaPlay volumeUndo _prevVolume:");
                    stringBuilder.append(this._prevVolume);
                    QLog.e("TRAE", 2, stringBuilder.toString());
                }
                ((AudioManager) this._context.getSystemService("audio")).setStreamVolume(this._streamType, this._prevVolume, 0);
            } catch (Exception unused) {
            }
        }
    }
}
