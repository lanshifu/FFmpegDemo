package com.tencent.liteav;

import android.graphics.Bitmap;
import com.tencent.liteav.audio.b;
import com.yalantis.ucrop.view.CropImageView;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;

/* compiled from: TXCLivePushConfig */
public class h implements Cloneable {
    public float A = CropImageView.DEFAULT_ASPECT_RATIO;
    public float B = CropImageView.DEFAULT_ASPECT_RATIO;
    public float C = -1.0f;
    public boolean D = true;
    public boolean E = false;
    public boolean F = false;
    public boolean G = true;
    public int H = 1;
    public boolean I = false;
    public boolean J = false;
    public int K = 0;
    public boolean L = false;
    public boolean M = true;
    public boolean N = false;
    public int a = 0;
    public int b = 0;
    public int c = 400;
    public int d = IjkMediaCodecInfo.RANK_LAST_CHANCE;
    public int e = IjkMediaCodecInfo.RANK_SECURE;
    public int f = 5;
    public boolean g = true;
    public int h = 20;
    public int i = 1;
    public int j = 2;
    public int k = 1;
    public int l = 1;
    public boolean m = true;
    public boolean n = true;
    public int o = 3;
    public int p = 3;
    public int q = b.a;
    public int r = b.b;
    public boolean s = true;
    public Bitmap t = null;
    public int u = IjkMediaCodecInfo.RANK_SECURE;
    public int v = 10;
    public int w = 1;
    public Bitmap x = null;
    public int y = 0;
    public int z = 0;

    public boolean a() {
        switch (this.k) {
            case 0:
                this.a = 368;
                this.b = 640;
                break;
            case 1:
                this.a = 544;
                this.b = 960;
                break;
            case 2:
                this.a = 720;
                this.b = 1280;
                break;
            case 3:
                this.a = 640;
                this.b = 368;
                return true;
            case 4:
                this.a = 960;
                this.b = 544;
                return true;
            case 5:
                this.a = 1280;
                this.b = 720;
                return true;
            case 6:
                this.a = 320;
                this.b = 480;
                break;
            case 7:
                this.a = 192;
                this.b = 320;
                break;
            case 8:
                this.a = 272;
                this.b = 480;
                break;
            case 9:
                this.a = 320;
                this.b = 192;
                return true;
            case 10:
                this.a = 480;
                this.b = 272;
                return true;
            case 11:
                this.a = 240;
                this.b = 320;
                break;
            case 12:
                this.a = 368;
                this.b = 480;
                break;
            case 13:
                this.a = 480;
                this.b = 640;
                break;
            case 14:
                this.a = 320;
                this.b = 240;
                return true;
            case 15:
                this.a = 480;
                this.b = 368;
                return true;
            case 16:
                this.a = 640;
                this.b = 480;
                return true;
            case 17:
                this.a = 480;
                this.b = 480;
                break;
            case 18:
                this.a = 272;
                this.b = 272;
                break;
            case 19:
                this.a = 160;
                this.b = 160;
                break;
            default:
                this.a = 368;
                this.b = 640;
                break;
        }
        return false;
    }

    /* Access modifiers changed, original: protected */
    public Object clone() throws CloneNotSupportedException {
        return (h) super.clone();
    }
}
