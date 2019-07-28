package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tomatolive.library.utils.m;

/* compiled from: PlayManager */
/* renamed from: sf */
public class sf {
    private a a;
    private TXLivePlayer b = null;
    private TXCloudVideoView c;
    private int d = 0;
    private ViewGroup e;
    private Context f;
    private ITXLivePlayListener g = new 1();

    /* compiled from: PlayManager */
    /* renamed from: sf$a */
    public interface a {
        void onEndBuffering();

        void onNetError();

        void onPlaySuccess();

        void onStartBuffering();
    }

    /* compiled from: PlayManager */
    /* renamed from: sf$1 */
    class 1 implements ITXLivePlayListener {
        public void onNetStatus(Bundle bundle) {
        }

        1() {
        }

        public void onPlayEvent(int i, Bundle bundle) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("i:");
            stringBuilder.append(i);
            stringBuilder.append(" msg：");
            stringBuilder.append(bundle.getString(TXLiveConstants.EVT_DESCRIPTION));
            m.b(stringBuilder.toString());
            if (i != TXLiveConstants.PLAY_ERR_NET_DISCONNECT) {
                if (i != TXLiveConstants.PLAY_EVT_PLAY_LOADING) {
                    switch (i) {
                        case TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME /*2003*/:
                            if (sf.this.a != null) {
                                sf.this.a.onPlaySuccess();
                                return;
                            }
                            return;
                        case TXLiveConstants.PLAY_EVT_PLAY_BEGIN /*2004*/:
                            if (sf.this.a != null) {
                                sf.this.a.onEndBuffering();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                } else if (sf.this.a != null) {
                    sf.this.a.onStartBuffering();
                }
            } else if (sf.this.a != null) {
                sf.this.a.onNetError();
            }
        }
    }

    public sf(Context context, ViewGroup viewGroup) {
        this.e = viewGroup;
        this.f = context;
        this.c = new TXCloudVideoView(context);
        if (viewGroup != null) {
            viewGroup.addView(this.c, 0, new LayoutParams(-1, -1));
        }
    }

    private void b(String str) {
        if (str.startsWith("rtmp://")) {
            this.d = 0;
        } else if ((str.startsWith("http://") || str.startsWith("https://")) && str.contains(".flv")) {
            this.d = 1;
        } else if ((str.startsWith("http://") || str.startsWith("https://")) && str.contains(".m3u8")) {
            this.d = 3;
        }
    }

    public void setOnPlayListener(a aVar) {
        this.a = aVar;
    }

    public a a() {
        return this.a;
    }

    public void a(String str) {
        if (this.c != null) {
            this.b = new TXLivePlayer(this.f);
            TXLivePlayConfig tXLivePlayConfig = new TXLivePlayConfig();
            tXLivePlayConfig.setAutoAdjustCacheTime(true);
            tXLivePlayConfig.setMinAutoAdjustCacheTime(1.0f);
            tXLivePlayConfig.setMaxAutoAdjustCacheTime(1.0f);
            this.b.setConfig(tXLivePlayConfig);
            this.b.setPlayerView(this.c);
            this.b.setRenderRotation(0);
            this.b.setRenderMode(0);
            b(str);
            this.b.setPlayListener(this.g);
            this.b.startPlay(str, this.d);
        }
    }

    public void a(boolean z) {
        if (this.b != null && z) {
            this.b.resume();
        }
    }

    public void b() {
        if (this.b != null && this.b.isPlaying()) {
            this.b.pause();
        }
    }

    public void c() {
        if (this.c != null && this.b != null) {
            this.b.stopPlay(true);
            this.b.setPlayListener(null);
            this.c.onDestroy();
        }
    }

    public void d() {
        if (this.e != null) {
            this.e.removeView(this.c);
        }
        if (this.c != null) {
            this.c.onDestroy();
            this.c = null;
        }
    }
}
