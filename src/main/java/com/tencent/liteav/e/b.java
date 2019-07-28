package com.tencent.liteav.e;

import android.media.AudioTrack;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.d.e;
import java.lang.ref.WeakReference;
import java.util.concurrent.LinkedBlockingDeque;

/* compiled from: AudioTrackRender */
public class b {
    private volatile AudioTrack a;
    private volatile e b;
    private LinkedBlockingDeque<e> c = new LinkedBlockingDeque();
    private b d;
    private volatile a e;
    private int f;
    private int g;

    /* compiled from: AudioTrackRender */
    public interface a {
        void a(int i);
    }

    /* compiled from: AudioTrackRender */
    private static class b extends Thread {
        private WeakReference<b> a;

        public b(b bVar) {
            super("PlayPCMThread for Video Editer");
            this.a = new WeakReference(bVar);
        }

        public void run() {
            super.run();
            while (!isInterrupted()) {
                try {
                    e b = b();
                    if (b != null) {
                        a(b);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

        private void a(e eVar) {
            c();
            ((b) this.a.get()).b(eVar);
        }

        private e b() throws InterruptedException {
            c();
            return (e) ((b) this.a.get()).c.peek();
        }

        private void c() {
            if (this.a.get() == null) {
                throw new RuntimeException("can't reach the object: AudioTrackRender");
            }
        }

        public void a() {
            interrupt();
            this.a.clear();
            this.a = null;
        }
    }

    private void b(e eVar) {
        if (this.b == null) {
            this.b = eVar;
        }
        if (eVar.f() == 4) {
            e();
            return;
        }
        byte[] array = eVar.b().array();
        int remaining = eVar.b().remaining();
        if (remaining != 0) {
            try {
                if (this.a != null && this.a.getPlayState() == 3) {
                    this.a.write(array, eVar.b().arrayOffset(), remaining);
                    this.c.remove();
                    if (this.e != null) {
                        this.e.a(this.c.size());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.b = eVar;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public void a() {
        try {
            if (this.a != null) {
                this.a.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b() {
        try {
            if (this.a != null && this.a.getPlayState() != 3) {
                this.a.play();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c() {
        a(this.g, this.f);
    }

    public void a(MediaFormat mediaFormat) {
        if (mediaFormat == null) {
            e();
            return;
        }
        if (VERSION.SDK_INT >= 16) {
            int integer = mediaFormat.getInteger("sample-rate");
            int integer2 = mediaFormat.getInteger("channel-count");
            if (!(this.f == integer && this.g == integer2)) {
                e();
            }
            this.f = integer;
            this.g = integer2;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setAudioFormat sampleRate=");
            stringBuilder.append(integer);
            stringBuilder.append(",channelCount=");
            stringBuilder.append(integer2);
            TXCLog.i("AudioTrackRender", stringBuilder.toString());
        }
    }

    public void a(e eVar) {
        if (this.d == null || !this.d.isAlive() || this.d.isInterrupted()) {
            this.d = new b(this);
            this.d.start();
        }
        this.c.add(eVar);
        if (this.e != null) {
            this.e.a(this.c.size());
        }
    }

    private boolean a(int i, int i2) {
        StringBuilder stringBuilder;
        int i3 = 4;
        if (i != 1) {
            i3 = (i == 2 || i == 3) ? 12 : (i == 4 || i == 5) ? 204 : (i == 6 || i == 7) ? 252 : i == 8 ? 6396 : 0;
        }
        if (this.a == null) {
            i = AudioTrack.getMinBufferSize(i2, i3, 2);
            try {
                this.a = new AudioTrack(3, i2, i3, 2, i, 1);
                this.a.play();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                stringBuilder = new StringBuilder();
                stringBuilder.append("new AudioTrack IllegalArgumentException: ");
                stringBuilder.append(e);
                stringBuilder.append(", sampleRate: ");
                stringBuilder.append(i2);
                stringBuilder.append(", channelType: ");
                stringBuilder.append(i3);
                stringBuilder.append(", minBufferLen: ");
                stringBuilder.append(i);
                TXCLog.e("AudioTrackRender", stringBuilder.toString());
                this.a = null;
                return true;
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
                stringBuilder = new StringBuilder();
                stringBuilder.append("new AudioTrack IllegalArgumentException: ");
                stringBuilder.append(e2);
                stringBuilder.append(", sampleRate: ");
                stringBuilder.append(i2);
                stringBuilder.append(", channelType: ");
                stringBuilder.append(i3);
                stringBuilder.append(", minBufferLen: ");
                stringBuilder.append(i);
                TXCLog.e("AudioTrackRender", stringBuilder.toString());
                if (this.a != null) {
                    this.a.release();
                }
                this.a = null;
                return true;
            }
        }
        return false;
    }

    private void e() {
        try {
            if (this.a != null) {
                this.a.stop();
                this.a.release();
            }
            this.a = null;
        } catch (Exception e) {
            this.a = null;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("audio track stop exception: ");
            stringBuilder.append(e);
            TXCLog.e("AudioTrackRender", stringBuilder.toString());
        }
    }

    public void d() {
        this.c.clear();
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mPlayPCMThread:");
        stringBuilder.append(this.d);
        TXCLog.d("AudioTrackRender", stringBuilder.toString());
        this.b = null;
        e();
    }
}
