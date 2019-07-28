package com.tencent.liteav.audio.impl.Play;

import android.content.Context;
import android.media.AudioTrack;
import com.tencent.liteav.audio.TXEAudioDef;
import com.tencent.liteav.basic.log.TXCLog;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* compiled from: TXCMultAudioTrackPlayer */
public class d {
    static d a = new d();
    private static final String b;
    private a c;
    private boolean d = false;
    private volatile boolean e = false;
    private Context f = null;
    private int g = TXEAudioDef.TXE_AUDIO_MODE_SPEAKER;
    private volatile boolean h = false;
    private int i = com.tencent.liteav.basic.a.a.e;
    private int j = com.tencent.liteav.basic.a.a.g;
    private int k = com.tencent.liteav.basic.a.a.h;

    /* compiled from: TXCMultAudioTrackPlayer */
    class a extends Thread {
        volatile boolean b = false;

        public a(String str) {
            super(str);
        }

        public void a() {
            this.b = true;
        }

        public void b() {
            this.b = false;
        }
    }

    static {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AudioCenter:");
        stringBuilder.append(d.class.getSimpleName());
        b = stringBuilder.toString();
    }

    private d() {
    }

    public static d a() {
        return a;
    }

    public void b() {
        TXCLog.w(b, "mult-track-player start!");
        if (this.e) {
            TXCLog.e(b, "mult-track-player can not start because of has started!");
        } else if (this.i == 0 || this.j == 0) {
            String str = b;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("strat mult-track-player failed with invalid audio info , samplerate:");
            stringBuilder.append(this.i);
            stringBuilder.append(", channels:");
            stringBuilder.append(this.j);
            TXCLog.e(str, stringBuilder.toString());
        } else {
            this.e = true;
            if (this.c == null) {
                this.c = new a("AUDIO_TRACK") {
                    public void run() {
                        a();
                        try {
                            int i = d.this.j == 1 ? 2 : 3;
                            int i2 = d.this.k == 8 ? 3 : 2;
                            AudioTrack audioTrack = new AudioTrack(3, d.this.i, i, i2, AudioTrack.getMinBufferSize(d.this.i, i, i2), 1);
                            String e = d.b;
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("create audio track, samplerate:");
                            stringBuilder.append(d.this.i);
                            stringBuilder.append(", channels:");
                            stringBuilder.append(d.this.j);
                            stringBuilder.append(", bits:");
                            stringBuilder.append(d.this.k);
                            TXCLog.i(e, stringBuilder.toString());
                            try {
                                audioTrack.play();
                                d.this.h = true;
                                d.this.a(d.this.f, d.this.g);
                                int i3 = 100;
                                int i4 = 0;
                                while (this.b) {
                                    byte[] nativeGetMixedTracksData = TXCAudioBasePlayController.nativeGetMixedTracksData(d.this.j * IjkMediaMeta.FF_PROFILE_H264_INTRA);
                                    if (nativeGetMixedTracksData == null || nativeGetMixedTracksData.length <= 0) {
                                        try {
                                            AnonymousClass1.sleep(5);
                                        } catch (InterruptedException unused) {
                                        }
                                    } else {
                                        if (d.this.d) {
                                            Arrays.fill(nativeGetMixedTracksData, (byte) 0);
                                        }
                                        if (i3 != 0 && i4 < 800) {
                                            short[] sArr = new short[(nativeGetMixedTracksData.length / 2)];
                                            ByteBuffer.wrap(nativeGetMixedTracksData).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(sArr);
                                            for (i2 = 0; i2 < sArr.length; i2++) {
                                                sArr[i2] = (short) (sArr[i2] / i3);
                                            }
                                            ByteBuffer.wrap(nativeGetMixedTracksData).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().put(sArr);
                                            i4 += nativeGetMixedTracksData.length / ((d.this.i * 2) / 1000);
                                            i3 = (i3 * (800 - i4)) / 800;
                                        }
                                        audioTrack.write(nativeGetMixedTracksData, 0, nativeGetMixedTracksData.length);
                                    }
                                }
                                try {
                                    audioTrack.pause();
                                    audioTrack.flush();
                                    audioTrack.stop();
                                    audioTrack.release();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                                TXCLog.e(d.b, "mult-player thread stop finish!");
                            } catch (Exception e22) {
                                e22.printStackTrace();
                            }
                        } catch (Exception e222) {
                            e222.printStackTrace();
                        }
                    }
                };
                this.c.start();
            }
            TXCLog.w(b, "mult-track-player thread start finish!");
        }
    }

    public void c() {
        TXCLog.w(b, "mult-track-player stop!");
        if (this.e) {
            if (this.c != null) {
                this.c.b();
                this.c = null;
            }
            this.g = TXEAudioDef.TXE_AUDIO_MODE_SPEAKER;
            this.f = null;
            this.h = false;
            this.e = false;
            TXCLog.w(b, "mult-track-player stop finish!");
            return;
        }
        TXCLog.w(b, "mult-track-player can not stop because of not started yet!");
    }

    public synchronized void a(Context context, int i) {
        this.f = context;
        this.g = i;
        if (this.h) {
            com.tencent.liteav.audio.impl.a.a().a(i);
        }
    }

    public boolean d() {
        return this.e;
    }
}
