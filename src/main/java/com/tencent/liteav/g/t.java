package com.tencent.liteav.g;

import android.media.MediaFormat;
import android.os.Build.VERSION;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.ugc.TXRecordCommon;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VideoSourceListConfig */
public class t {
    private static t a;
    private final ArrayList<i> b = new ArrayList();
    private ArrayList<String> c = new ArrayList();
    private int d = 0;
    private int e = 0;

    public static t a() {
        if (a == null) {
            a = new t();
        }
        return a;
    }

    private t() {
    }

    public void a(List<String> list) {
        this.b.clear();
        this.d = 0;
        this.c.clear();
        this.c.addAll(list);
    }

    public int b() {
        int i = 0;
        int i2 = 0;
        while (i < this.c.size()) {
            String str = (String) this.c.get(i);
            i iVar = new i();
            iVar.a(str);
            int b = iVar.b();
            this.b.add(iVar);
            if (b != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("checkLegality source:");
                stringBuilder.append(str);
                stringBuilder.append(" is illegal");
                TXCLog.i("VideoSourceListConfig", stringBuilder.toString());
                i2 = b;
                break;
            }
            i++;
            i2 = b;
        }
        if (i2 != 0) {
            i = this.b.size();
            for (int i3 = 0; i3 < i; i3++) {
                ((i) this.b.get(i3)).a();
            }
        }
        return i2;
    }

    public List<i> c() {
        return this.b;
    }

    public i d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getCurrentVideoExtractConfig mCurrentVideoIndex:");
        stringBuilder.append(this.d);
        TXCLog.i("VideoSourceListConfig", stringBuilder.toString());
        return (i) this.b.get(this.d);
    }

    public i e() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getCurrentAudioExtractConfig mCurrentAudioIndex:");
        stringBuilder.append(this.e);
        TXCLog.i("VideoSourceListConfig", stringBuilder.toString());
        return (i) this.b.get(this.e);
    }

    public boolean f() {
        this.d++;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("nextVideo mCurrentVideoIndex:");
        stringBuilder.append(this.d);
        TXCLog.i("VideoSourceListConfig", stringBuilder.toString());
        if (this.d >= this.b.size()) {
            TXCLog.i("VideoSourceListConfig", "nextVideo get fail");
            return false;
        }
        TXCLog.i("VideoSourceListConfig", "nextVideo get succ");
        return true;
    }

    public MediaFormat g() {
        int i;
        int i2 = 0;
        if (VERSION.SDK_INT >= 16) {
            i = 0;
            int i3 = 0;
            while (i2 < this.b.size()) {
                MediaFormat f = ((i) this.b.get(i2)).f();
                if (f != null) {
                    int integer = f.getInteger("sample-rate");
                    int integer2 = f.getInteger("channel-count");
                    if (integer > i3) {
                        i3 = integer;
                    }
                    if (integer2 > i) {
                        i = integer2;
                    }
                }
                i2++;
            }
            i2 = i3;
        } else {
            i = 0;
        }
        if (i == 0) {
            i = 2;
        }
        if (i2 == 0) {
            i2 = TXRecordCommon.AUDIO_SAMPLERATE_48000;
        }
        if (VERSION.SDK_INT >= 16) {
            return MediaFormat.createAudioFormat("audio/mp4a-latm", i2, i);
        }
        return null;
    }

    public boolean h() {
        if (VERSION.SDK_INT < 16) {
            return true;
        }
        boolean z = true;
        for (int i = 0; i < this.b.size(); i++) {
            i iVar = (i) this.b.get(i);
            MediaFormat e = iVar.e();
            int integer = e.getInteger("width");
            int integer2 = e.getInteger("height");
            int g = iVar.g();
            if (g == 0 || g == TXLiveConstants.RENDER_ROTATION_180) {
                if (integer2 <= integer) {
                }
            } else if (integer <= integer2) {
            }
            z = false;
        }
        return z;
    }

    public boolean i() {
        this.e++;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("nextAudio mCurrentAudioIndex:");
        stringBuilder.append(this.e);
        TXCLog.i("VideoSourceListConfig", stringBuilder.toString());
        if (this.e >= this.b.size()) {
            TXCLog.i("VideoSourceListConfig", "nextAudio get fail");
            return false;
        }
        TXCLog.i("VideoSourceListConfig", "nextAudio get succ");
        return true;
    }

    public boolean j() {
        return this.d == this.b.size() - 1;
    }

    public boolean k() {
        return this.e == this.b.size() - 1;
    }

    public void l() {
        this.d = 0;
        this.e = 0;
    }

    public long m() {
        long j = 0;
        if (VERSION.SDK_INT >= 16) {
            for (int i = 0; i < this.b.size(); i++) {
                j += ((i) this.b.get(i)).e().getLong("durationUs");
            }
        }
        return j;
    }

    public long n() {
        if (VERSION.SDK_INT < 16) {
            return 0;
        }
        long j = 0;
        for (int i = 0; i < this.b.size(); i++) {
            long j2 = ((i) this.b.get(i)).e().getLong("durationUs");
            if (j == 0) {
                j = j2;
            }
            if (j > j2) {
                j = j2;
            }
        }
        return j;
    }
}
