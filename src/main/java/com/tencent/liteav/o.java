package com.tencent.liteav;

import android.content.Context;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import com.tencent.ijk.media.player.IjkBitrateItem;
import com.tencent.ijk.media.player.MediaInfo;
import com.tencent.liteav.basic.d.a;
import com.tencent.liteav.basic.datareport.TXCDRApi;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.basic.util.TXCTimeUtil;
import com.tencent.liteav.basic.util.b;
import com.tencent.liteav.txcvodplayer.TXCVodVideoView;
import com.tencent.liteav.txcvodplayer.TextureRenderView;
import com.tencent.liteav.txcvodplayer.c;
import com.tencent.liteav.txcvodplayer.d;
import com.tencent.rtmp.TXBitrateItem;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXVodPlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: TXCVodPlayer */
public class o extends s {
    protected boolean a;
    private TXCVodVideoView f;
    private c g;
    private n h = null;
    private boolean i;
    private boolean j = true;
    private boolean k = true;
    private float l = 1.0f;
    private Surface m;
    private d n = new d() {
        public void a(int i, Bundle bundle) {
            Bundle bundle2 = new Bundle(bundle);
            int i2 = TXLiveConstants.PLAY_EVT_PLAY_END;
            if (i != TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER) {
                int i3 = 0;
                switch (i) {
                    case -3005:
                        i2 = TXLiveConstants.PLAY_ERR_HEVC_DECODE_FAIL;
                        if (!o.this.i) {
                            o.this.g.a(false);
                            break;
                        }
                        break;
                    case -3004:
                        i2 = TXLiveConstants.PLAY_ERR_HLS_KEY;
                        break;
                    case -3003:
                        i2 = TXLiveConstants.PLAY_ERR_FILE_NOT_FOUND;
                        break;
                    case -3002:
                    case -3001:
                        i2 = TXLiveConstants.PLAY_ERR_NET_DISCONNECT;
                        break;
                    default:
                        switch (i) {
                            case 3000:
                                i2 = TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED;
                                o.this.h.d();
                                break;
                            case 3001:
                                i2 = TXLiveConstants.PLAY_EVT_PLAY_BEGIN;
                                o.this.h.d();
                                break;
                            case 3002:
                                break;
                            case 3003:
                                i2 = TXLiveConstants.PLAY_EVT_PLAY_LOADING;
                                o.this.h.i();
                                break;
                            case TXLiveConstants.PUSH_WARNING_SERVER_DISCONNECT /*3004*/:
                                o.this.h.c();
                                if (o.this.a) {
                                    o.this.f.b();
                                    TXCLog.d(TXVodPlayer.TAG, "loop play");
                                    return;
                                }
                                break;
                            case 3005:
                                i2 = TXLiveConstants.PLAY_EVT_CHANGE_RESOLUTION;
                                break;
                            case 3006:
                                i2 = TXLiveConstants.PLAY_WARNING_RECONNECT;
                                break;
                            case 3007:
                                i2 = TXLiveConstants.PLAY_EVT_PLAY_PROGRESS;
                                o.this.h.a(bundle.getInt(TXLiveConstants.EVT_PLAY_DURATION, 0));
                                break;
                            case 3008:
                                if (!o.this.i) {
                                    i2 = 1;
                                    o.this.i = true;
                                    o.this.h.e();
                                    Bundle bundle3 = new Bundle();
                                    bundle3.putInt("EVT_ID", TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER);
                                    bundle3.putLong("EVT_TIME", TXCTimeUtil.getTimeTick());
                                    MediaInfo mediaInfo = o.this.f.getMediaInfo();
                                    if (!(mediaInfo == null || mediaInfo.mVideoDecoderImpl == null || !mediaInfo.mVideoDecoderImpl.contains("hevc"))) {
                                        i3 = 1;
                                    }
                                    if (o.this.f.getPlayerType() == 0) {
                                        if (i3 == 0) {
                                            bundle3.putCharSequence("description", o.this.g.a() ? "启动硬解" : "启动软解");
                                        } else {
                                            bundle3.putCharSequence("description", o.this.g.a() ? "启动硬解265" : "启动软解265");
                                        }
                                        String str = "EVT_PARAM1";
                                        if (!o.this.g.a()) {
                                            i2 = 2;
                                        }
                                        bundle3.putInt(str, i2);
                                        bundle3.putInt("hevc", i3);
                                    } else {
                                        bundle3.putCharSequence("description", "启动硬解");
                                        bundle3.putInt("EVT_PARAM1", 2);
                                    }
                                    a(TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER, bundle3);
                                    i2 = TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME;
                                    break;
                                }
                                return;
                            case 3009:
                                i2 = TXLiveConstants.PLAY_EVT_CHANGE_ROTATION;
                                bundle2.putInt("EVT_PARAM1", o.this.f.getMetaRotationDegree());
                                break;
                            case 3010:
                                i2 = TXLiveConstants.PLAY_WARNING_HW_ACCELERATION_FAIL;
                                if (!o.this.i) {
                                    o.this.g.a(false);
                                    break;
                                }
                                break;
                            default:
                                switch (i) {
                                    case 3012:
                                        o.this.h.f();
                                        return;
                                    case 3013:
                                        o.this.h.h();
                                        return;
                                    case 3014:
                                        o.this.h.g();
                                        return;
                                    case 3015:
                                        return;
                                    case 3016:
                                        i2 = TXLiveConstants.PLAY_EVT_VOD_LOADING_END;
                                        break;
                                    default:
                                        String str2 = TXVodPlayer.TAG;
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append("miss match event ");
                                        stringBuilder.append(i);
                                        TXCLog.d(str2, stringBuilder.toString());
                                        return;
                                }
                        }
                }
            }
            i2 = TXLiveConstants.PLAY_EVT_START_VIDEO_DECODER;
            bundle2.putString(TXLiveConstants.EVT_DESCRIPTION, bundle.getString("description", ""));
            if (o.this.e != null) {
                a aVar = (a) o.this.e.get();
                if (aVar != null) {
                    aVar.onNotifyEvent(i2, bundle2);
                }
            }
        }

        public void a(Bundle bundle) {
            Bundle bundle2 = new Bundle();
            int[] a = b.a();
            int intValue = Integer.valueOf(a[0]).intValue() / 10;
            int intValue2 = Integer.valueOf(a[1]).intValue() / 10;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(intValue);
            stringBuilder.append("/");
            stringBuilder.append(intValue2);
            stringBuilder.append("%");
            bundle2.putCharSequence(TXLiveConstants.NET_STATUS_CPU_USAGE, stringBuilder.toString());
            bundle2.putInt(TXLiveConstants.NET_STATUS_VIDEO_FPS, (int) bundle.getFloat("fps"));
            bundle2.putInt(TXLiveConstants.NET_STATUS_NET_SPEED, ((int) bundle.getLong("tcpSpeed")) / 1000);
            bundle2.putInt(TXLiveConstants.NET_STATUS_CODEC_CACHE, ((int) bundle.getLong("cachedBytes")) / 1000);
            bundle2.putInt(TXLiveConstants.NET_STATUS_VIDEO_WIDTH, o.this.f.getVideoWidth());
            bundle2.putInt(TXLiveConstants.NET_STATUS_VIDEO_HEIGHT, o.this.f.getVideoHeight());
            bundle2.putString(TXLiveConstants.NET_STATUS_SERVER_IP, o.this.f.getServerIp());
            if (o.this.e != null) {
                a aVar = (a) o.this.e.get();
                if (aVar != null) {
                    aVar.onNotifyEvent(15001, bundle2);
                }
            }
        }
    };

    public int c(int i) {
        return 0;
    }

    public int e() {
        return 0;
    }

    public o(Context context) {
        super(context);
        this.f = new TXCVodVideoView(context);
        this.f.setListener(this.n);
        com.tencent.liteav.audio.impl.a.a().a(context);
    }

    public void a(i iVar) {
        super.a(iVar);
        if (this.g == null) {
            this.g = new c();
        }
        this.g.a((float) this.b.e);
        this.g.b((float) this.b.f);
        this.g.c((float) this.b.q);
        this.g.a(this.b.i);
        this.g.a(this.b.m);
        this.g.a(this.b.n);
        this.g.b(this.b.o);
        this.g.a(this.b.p);
        this.g.b(this.b.r);
        this.g.c(this.b.t);
        this.g.b(this.b.u);
        this.g.c(this.b.v);
        this.f.setConfig(this.g);
        this.k = iVar.s;
    }

    public int a(String str, int i) {
        if (this.d != null) {
            this.d.setVisibility(0);
            if (this.d.getVideoView() == null) {
                TextureView textureRenderView = new TextureRenderView(this.d.getContext());
                this.d.addVideoView(textureRenderView);
                this.f.setTextureRenderView(textureRenderView);
            }
            this.d.getVideoView().setVisibility(0);
        } else if (this.m != null) {
            this.f.setRenderSurface(this.m);
        }
        this.h = new n(this.c);
        this.h.a(str);
        this.h.b();
        this.i = false;
        this.f.setPlayerType(this.g.b());
        this.f.setVideoPath(str);
        this.f.setAutoPlay(this.j);
        this.f.setRate(this.l);
        this.f.setAutoRotate(this.k);
        if (this.g != null) {
            this.f.b();
            if (this.g.b() == 1) {
                this.h.b(3);
            } else {
                this.h.b(1);
            }
        } else {
            this.f.b();
            this.h.b(1);
        }
        String str2 = TXVodPlayer.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startPlay ");
        stringBuilder.append(str);
        TXCLog.d(str2, stringBuilder.toString());
        TXCDRApi.txReportDAU(this.c, com.tencent.liteav.basic.datareport.a.bp);
        try {
            if (Class.forName("com.tencent.liteav.demo.play.SuperPlayerView") != null) {
                TXCDRApi.txReportDAU(this.c, com.tencent.liteav.basic.datareport.a.bA);
            }
        } catch (Exception unused) {
        }
        return 0;
    }

    public int a(boolean z) {
        this.f.c();
        if (!(this.d == null || this.d.getVideoView() == null || !z)) {
            this.d.getVideoView().setVisibility(8);
        }
        if (this.h != null) {
            this.h.c();
        }
        return 0;
    }

    public void a(Surface surface) {
        this.m = surface;
        if (this.f != null) {
            this.f.setRenderSurface(this.m);
        }
    }

    public void a() {
        this.f.d();
    }

    public void b() {
        this.f.b();
    }

    public void a_(int i) {
        this.f.a(i * 1000);
        if (this.i && this.h != null) {
            this.h.j();
        }
    }

    public void a(float f) {
        this.f.a((int) (f * 1000.0f));
        if (this.i && this.h != null) {
            this.h.j();
        }
    }

    public float h() {
        return this.f != null ? ((float) this.f.getCurrentPosition()) / 1000.0f : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float i() {
        return this.f != null ? ((float) this.f.getBufferDuration()) / 1000.0f : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float j() {
        return this.f != null ? ((float) this.f.getDuration()) / 1000.0f : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public float k() {
        return this.f != null ? ((float) this.f.getBufferDuration()) / 1000.0f : CropImageView.DEFAULT_ASPECT_RATIO;
    }

    public int l() {
        return this.f != null ? this.f.getVideoWidth() : 0;
    }

    public int m() {
        return this.f != null ? this.f.getVideoHeight() : 0;
    }

    public void b(boolean z) {
        this.f.setMute(z);
    }

    public void a(int i) {
        if (i == 1) {
            this.f.setRenderMode(0);
        } else {
            this.f.setRenderMode(1);
        }
    }

    public void b(int i) {
        this.f.setVideoRotationDegree(360 - i);
    }

    public void a(TXCloudVideoView tXCloudVideoView) {
        if (tXCloudVideoView != this.d) {
            if (this.d != null) {
                this.d.removeVideoView();
            }
            if (tXCloudVideoView != null) {
                tXCloudVideoView.removeVideoView();
            }
        }
        if (tXCloudVideoView != null) {
            tXCloudVideoView.setVisibility(0);
            if (tXCloudVideoView.getVideoView() == null) {
                TextureView textureRenderView = new TextureRenderView(tXCloudVideoView.getContext());
                tXCloudVideoView.addVideoView(textureRenderView);
                this.f.setTextureRenderView(textureRenderView);
            }
            tXCloudVideoView.getVideoView().setVisibility(0);
        }
        super.a(tXCloudVideoView);
    }

    public void a(TextureRenderView textureRenderView) {
        if (this.f != null) {
            this.f.setRenderView(textureRenderView);
        }
    }

    public TextureView d() {
        return this.d != null ? this.d.getVideoView() : null;
    }

    public boolean c() {
        return this.f.e();
    }

    public void c(boolean z) {
        this.j = z;
        if (this.f != null) {
            this.f.setAutoPlay(z);
        }
    }

    public void b(float f) {
        this.l = f;
        if (this.f != null) {
            this.f.setRate(f);
        }
        if (this.i && this.h != null) {
            this.h.l();
        }
    }

    public void c(float f) {
        if (this.f != null) {
            this.f.setStartTime(f);
        }
    }

    public int n() {
        return this.f != null ? this.f.getBitrateIndex() : 0;
    }

    public void e(int i) {
        if (this.f != null) {
            this.f.setBitrateIndex(i);
        }
        if (this.i && this.h != null) {
            this.h.k();
        }
    }

    public ArrayList<TXBitrateItem> o() {
        ArrayList arrayList = new ArrayList();
        if (this.f != null) {
            ArrayList supportedBitrates = this.f.getSupportedBitrates();
            if (supportedBitrates != null) {
                Iterator it = supportedBitrates.iterator();
                while (it.hasNext()) {
                    IjkBitrateItem ijkBitrateItem = (IjkBitrateItem) it.next();
                    TXBitrateItem tXBitrateItem = new TXBitrateItem();
                    tXBitrateItem.index = ijkBitrateItem.index;
                    tXBitrateItem.width = ijkBitrateItem.width;
                    tXBitrateItem.height = ijkBitrateItem.height;
                    tXBitrateItem.bitrate = ijkBitrateItem.bitrate;
                    arrayList.add(tXBitrateItem);
                }
            }
        }
        return arrayList;
    }

    public void d(boolean z) {
        this.a = z;
    }

    public void e(boolean z) {
        TextureView d = d();
        if (d != null) {
            d.setScaleX(z ? -1.0f : 1.0f);
        }
        if (this.h != null) {
            this.h.a(z);
        }
    }
}
