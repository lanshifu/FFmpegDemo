package com.tencent.liteav.g;

import com.tencent.liteav.basic.log.TXCLog;
import java.util.ArrayList;
import java.util.List;

/* compiled from: VideoExtractListConfig */
public class j {
    private List<i> a = new ArrayList();
    private int b = 0;
    private int c = 0;

    public List<i> a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getAllVideoExtractConfig mVideoExtractConfigList:");
        stringBuilder.append(this.a);
        TXCLog.i("VideoExtractListConfig", stringBuilder.toString());
        return this.a;
    }

    public i b() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getCurrentVideoExtractConfig mCurrentVideoIndex:");
        stringBuilder.append(this.b);
        TXCLog.i("VideoExtractListConfig", stringBuilder.toString());
        return (i) this.a.get(this.b);
    }

    public i c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getCurrentAudioExtractConfig mCurrentAudioIndex:");
        stringBuilder.append(this.c);
        TXCLog.i("VideoExtractListConfig", stringBuilder.toString());
        return (i) this.a.get(this.c);
    }

    public boolean d() {
        this.b++;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("nextVideo mCurrentVideoIndex:");
        stringBuilder.append(this.b);
        TXCLog.i("VideoExtractListConfig", stringBuilder.toString());
        if (this.b >= this.a.size()) {
            TXCLog.i("VideoExtractListConfig", "nextVideo get fail");
            return false;
        }
        TXCLog.i("VideoExtractListConfig", "nextVideo get succ");
        return true;
    }

    public boolean e() {
        this.c++;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("nextAudio mCurrentAudioIndex:");
        stringBuilder.append(this.c);
        TXCLog.i("VideoExtractListConfig", stringBuilder.toString());
        if (this.c >= this.a.size()) {
            TXCLog.i("VideoExtractListConfig", "nextAudio get fail");
            return false;
        }
        TXCLog.i("VideoExtractListConfig", "nextAudio get succ");
        return true;
    }

    public boolean f() {
        return this.b == this.a.size() - 1;
    }

    public boolean g() {
        return this.c == this.a.size() - 1;
    }

    public void h() {
        this.b = 0;
        this.c = 0;
    }

    public void a(List<i> list) {
        for (int i = 0; i < list.size(); i++) {
            i iVar = (i) list.get(i);
            i iVar2 = new i();
            iVar2.a = iVar.a;
            iVar2.a(iVar2.a);
            iVar2.b();
            this.a.add(iVar2);
        }
    }
}
