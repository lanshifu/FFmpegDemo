package com.tencent.liteav.txcvodplayer;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.tencent.ijk.media.exo.IjkExoMediaPlayer;
import com.tencent.ijk.media.player.AndroidMediaPlayer;
import com.tencent.ijk.media.player.IMediaPlayer;
import com.tencent.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener;
import com.tencent.ijk.media.player.IMediaPlayer.OnCompletionListener;
import com.tencent.ijk.media.player.IMediaPlayer.OnErrorListener;
import com.tencent.ijk.media.player.IMediaPlayer.OnHLSKeyErrorListener;
import com.tencent.ijk.media.player.IMediaPlayer.OnHevcVideoDecoderErrorListener;
import com.tencent.ijk.media.player.IMediaPlayer.OnInfoListener;
import com.tencent.ijk.media.player.IMediaPlayer.OnPreparedListener;
import com.tencent.ijk.media.player.IMediaPlayer.OnSeekCompleteListener;
import com.tencent.ijk.media.player.IMediaPlayer.OnTimedTextListener;
import com.tencent.ijk.media.player.IMediaPlayer.OnVideoDecoderErrorListener;
import com.tencent.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener;
import com.tencent.ijk.media.player.IjkBitrateItem;
import com.tencent.ijk.media.player.IjkLibLoader;
import com.tencent.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;
import com.tencent.ijk.media.player.IjkTimedText;
import com.tencent.ijk.media.player.MediaInfo;
import com.tencent.ijk.media.player.TextureMediaPlayer;
import com.tencent.liteav.basic.log.TXCLog;
import com.tencent.liteav.txcvodplayer.a.b;
import com.tencent.rtmp.TXLiveConstants;
import com.yalantis.ucrop.view.CropImageView;
import defpackage.gr;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Locale;
import org.slf4j.Marker;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class TXCVodVideoView extends FrameLayout {
    private String A;
    private float B = 1.0f;
    private com.tencent.liteav.txcvodplayer.a.a C;
    private b D = b.a();
    private int E;
    private long F;
    private int G;
    private int H;
    private long I;
    private boolean J = false;
    private int K = -1;
    private float L = 1.0f;
    private float M = 1.0f;
    private boolean N = false;
    private int O;
    private boolean P;
    private OnCompletionListener Q = new OnCompletionListener() {
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            if (TXCVodVideoView.this.c != 1 || TXCVodVideoView.this.j != -1) {
                TXCVodVideoView.this.i = 5;
                TXCVodVideoView.this.j = 5;
                TXCVodVideoView.this.a((int) TXLiveConstants.PUSH_WARNING_SERVER_DISCONNECT, "播放完成", "play end");
            }
        }
    };
    private OnInfoListener R = new OnInfoListener() {
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            String k;
            StringBuilder stringBuilder;
            switch (i) {
                case 3:
                    TXCLog.d(TXCVodVideoView.this.g, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (!TXCVodVideoView.this.P) {
                        TXCVodVideoView.this.a(3008, "点播显示首帧画面", "render start");
                        if (TXCVodVideoView.this.c == 1) {
                            new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        TXCVodVideoView.this.A = InetAddress.getByName(TXCVodVideoView.this.h.getHost()).getHostAddress();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }).start();
                        }
                    }
                    TXCVodVideoView.this.setRate(TXCVodVideoView.this.B);
                    if (TXCVodVideoView.this.c == 1) {
                        TXCVodVideoView.this.a(3016, "缓冲结束", "loading end");
                        if (TXCVodVideoView.this.j == 3 && TXCVodVideoView.this.P) {
                            TXCVodVideoView.this.a(3001, "播放开始", "playing");
                        }
                    }
                    TXCVodVideoView.this.P = true;
                    break;
                case 700:
                    TXCLog.d(TXCVodVideoView.this.g, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                    break;
                case tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_BUFFERING_START /*701*/:
                    TXCLog.d(TXCVodVideoView.this.g, "MEDIA_INFO_BUFFERING_START:");
                    TXCVodVideoView.this.a(3003, "缓冲开始", "loading start");
                    break;
                case tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_BUFFERING_END /*702*/:
                    String k2 = TXCVodVideoView.this.g;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("MEDIA_INFO_BUFFERING_END: eof ");
                    stringBuilder2.append(i2);
                    TXCLog.d(k2, stringBuilder2.toString());
                    if (i2 == 0 || TXCVodVideoView.this.h == null || TXCVodVideoView.this.h.getPath() == null || !TXCVodVideoView.this.h.getPath().endsWith("m3u8")) {
                        TXCVodVideoView.this.a(3016, "缓冲结束", "loading end");
                        if (TXCVodVideoView.this.j == 3) {
                            TXCVodVideoView.this.a(3001, "播放开始", "playing");
                            break;
                        }
                    }
                    break;
                case tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /*703*/:
                    k = TXCVodVideoView.this.g;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("MEDIA_INFO_NETWORK_BANDWIDTH: ");
                    stringBuilder.append(i2);
                    TXCLog.d(k, stringBuilder.toString());
                    break;
                case 800:
                    TXCLog.d(TXCVodVideoView.this.g, "MEDIA_INFO_BAD_INTERLEAVING:");
                    break;
                case tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_NOT_SEEKABLE /*801*/:
                    TXCLog.d(TXCVodVideoView.this.g, "MEDIA_INFO_NOT_SEEKABLE:");
                    break;
                case tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_METADATA_UPDATE /*802*/:
                    TXCLog.d(TXCVodVideoView.this.g, "MEDIA_INFO_METADATA_UPDATE:");
                    break;
                case tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_UNSUPPORTED_SUBTITLE /*901*/:
                    TXCLog.d(TXCVodVideoView.this.g, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    break;
                case tv.danmaku.ijk.media.player.IMediaPlayer.MEDIA_INFO_SUBTITLE_TIMED_OUT /*902*/:
                    TXCLog.d(TXCVodVideoView.this.g, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    break;
                case 10001:
                    k = TXCVodVideoView.this.g;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("MEDIA_INFO_VIDEO_ROTATION_CHANGED: ");
                    stringBuilder.append(i2);
                    TXCLog.d(k, stringBuilder.toString());
                    TXCVodVideoView.this.r = i2;
                    if (TXCVodVideoView.this.a && TXCVodVideoView.this.r > 0) {
                        TXCVodVideoView.this.q = TXCVodVideoView.this.r;
                        if (TXCVodVideoView.this.x != null) {
                            TXCVodVideoView.this.x.setVideoRotation(TXCVodVideoView.this.q);
                        }
                    }
                    TXCVodVideoView tXCVodVideoView = TXCVodVideoView.this;
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("视频角度 ");
                    stringBuilder3.append(TXCVodVideoView.this.r);
                    String stringBuilder4 = stringBuilder3.toString();
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append("rotation ");
                    stringBuilder5.append(TXCVodVideoView.this.r);
                    tXCVodVideoView.a(3009, stringBuilder4, stringBuilder5.toString());
                    break;
                case 10002:
                    TXCLog.d(TXCVodVideoView.this.g, "MEDIA_INFO_AUDIO_RENDERING_START:");
                    break;
                case 10011:
                    TXCVodVideoView.this.a(3013, "收到视频数据", "first video packet");
                    break;
            }
            return true;
        }
    };
    private int S;
    private OnErrorListener T = new OnErrorListener() {
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            String k = TXCVodVideoView.this.g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onError: ");
            stringBuilder.append(i);
            stringBuilder.append(",");
            stringBuilder.append(i2);
            TXCLog.e(k, stringBuilder.toString());
            TXCVodVideoView.this.i = -1;
            TXCVodVideoView.this.j = -1;
            if (i == -1004 && i2 == -3003) {
                TXCVodVideoView.this.a(i2, "文件不存在", "file not exist");
                TXCVodVideoView.this.c();
                return true;
            }
            if (TXCVodVideoView.this.I != ((long) TXCVodVideoView.this.getCurrentPosition())) {
                TXCVodVideoView.this.S = 0;
            }
            TXCVodVideoView.this.I = (long) TXCVodVideoView.this.getCurrentPosition();
            if (((float) TXCVodVideoView.this.S = TXCVodVideoView.this.S + 1) >= TXCVodVideoView.this.w.a) {
                TXCVodVideoView.this.a(-3002, "网络断开，播放错误", "disconnect");
                TXCVodVideoView.this.c();
            } else if (TXCVodVideoView.this.ag != null) {
                TXCVodVideoView.this.ag.sendEmptyMessageDelayed(102, (long) (TXCVodVideoView.this.w.b * 1000.0f));
            }
            return true;
        }
    };
    private OnHevcVideoDecoderErrorListener U = new OnHevcVideoDecoderErrorListener() {
        public void onHevcVideoDecoderError(IMediaPlayer iMediaPlayer) {
            Log.d(TXCVodVideoView.this.g, "onHevcVideoDecoderError");
            TXCVodVideoView.this.a(-3005, "点播H265解码失败", "hevc decode fail");
        }
    };
    private OnVideoDecoderErrorListener V = new OnVideoDecoderErrorListener() {
        public void onVideoDecoderError(IMediaPlayer iMediaPlayer) {
            Log.d(TXCVodVideoView.this.g, "onVideoDecoderError");
            if (TXCVodVideoView.this.i != 4) {
                TXCVodVideoView.this.a(3010, "点播解码失败", "decode fail");
            }
            if (!TXCVodVideoView.this.P && TXCVodVideoView.this.w.d) {
                TXCVodVideoView.this.w.d = false;
                TXCVodVideoView.this.g();
            }
        }
    };
    private OnBufferingUpdateListener W = new OnBufferingUpdateListener() {
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            TXCVodVideoView.this.s = i;
        }
    };
    protected boolean a = true;
    private OnSeekCompleteListener aa = new OnSeekCompleteListener() {
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            TXCLog.v(TXCVodVideoView.this.g, "seek complete");
            TXCVodVideoView.this.J = false;
            if (TXCVodVideoView.this.K >= 0) {
                TXCVodVideoView.this.a(TXCVodVideoView.this.K);
            }
        }
    };
    private OnTimedTextListener ab = new OnTimedTextListener() {
        public void onTimedText(IMediaPlayer iMediaPlayer, IjkTimedText ijkTimedText) {
        }
    };
    private OnNativeInvokeListener ac = new OnNativeInvokeListener() {
        public boolean onNativeInvoke(int i, Bundle bundle) {
            if (i == IjkMediaPlayer.OnNativeInvokeListener.CTRL_DID_TCP_OPEN) {
                TXCVodVideoView.this.A = bundle.getString(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP);
                TXCVodVideoView.this.a(3012, "CTRL_DID_TCP_OPEN", "tcp open");
                return true;
            } else if (i != 131106) {
                return false;
            } else {
                TXCVodVideoView.this.a(3014, "PLAYER_EVENT_DNS_RESOLVED", "dns resolved");
                return true;
            }
        }
    };
    private OnHLSKeyErrorListener ad = new OnHLSKeyErrorListener() {
        public void onHLSKeyError(IMediaPlayer iMediaPlayer) {
            Log.e(TXCVodVideoView.this.g, "onHLSKeyError");
            TXCVodVideoView.this.a(-3004, "HLS解密key获取失败", "hls key error");
            if (TXCVodVideoView.this.l != null) {
                TXCVodVideoView.this.l.stop();
                TXCVodVideoView.this.l.release();
                TXCVodVideoView.this.l = null;
            }
            TXCVodVideoView.this.i = -1;
            TXCVodVideoView.this.j = -1;
        }
    };
    private int ae = 0;
    private d af;
    private Handler ag;
    private boolean ah = false;
    protected boolean b = true;
    protected int c = 0;
    OnVideoSizeChangedListener d = new OnVideoSizeChangedListener() {
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            Object obj = ((TXCVodVideoView.this.n == i2 || Math.abs(TXCVodVideoView.this.n - i2) <= 16) && (TXCVodVideoView.this.m == i || Math.abs(TXCVodVideoView.this.m - i) <= 16)) ? null : 1;
            TXCVodVideoView.this.m = iMediaPlayer.getVideoWidth();
            TXCVodVideoView.this.n = iMediaPlayer.getVideoHeight();
            TXCVodVideoView.this.y = iMediaPlayer.getVideoSarNum();
            TXCVodVideoView.this.z = iMediaPlayer.getVideoSarDen();
            if (!(TXCVodVideoView.this.m == 0 || TXCVodVideoView.this.n == 0)) {
                if (TXCVodVideoView.this.x != null) {
                    TXCVodVideoView.this.x.setVideoSize(TXCVodVideoView.this.m, TXCVodVideoView.this.n);
                    TXCVodVideoView.this.x.setVideoSampleAspectRatio(TXCVodVideoView.this.y, TXCVodVideoView.this.z);
                }
                TXCVodVideoView.this.requestLayout();
            }
            if (obj != null) {
                Message message = new Message();
                message.what = 101;
                message.arg1 = 3005;
                Bundle bundle = new Bundle();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("分辨率改变:");
                stringBuilder.append(TXCVodVideoView.this.m);
                stringBuilder.append(Marker.ANY_MARKER);
                stringBuilder.append(TXCVodVideoView.this.n);
                bundle.putString("description", stringBuilder.toString());
                bundle.putInt("EVT_PARAM1", TXCVodVideoView.this.m);
                bundle.putInt("EVT_PARAM2", TXCVodVideoView.this.n);
                message.setData(bundle);
                if (TXCVodVideoView.this.ag != null) {
                    TXCVodVideoView.this.ag.sendMessage(message);
                }
            }
        }
    };
    OnPreparedListener e = new OnPreparedListener() {
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            if (TXCVodVideoView.this.i == 1) {
                TXCVodVideoView.this.a(3000, "点播准备完成", "prepared");
                if (!TXCVodVideoView.this.b) {
                    TXCVodVideoView.this.j = 4;
                    TXCVodVideoView.this.b = true;
                }
                TXCVodVideoView.this.i = 2;
            }
            TXCVodVideoView.this.t = 0;
            if (TXCVodVideoView.this.i == -1) {
                TXCVodVideoView.this.i = 3;
                TXCVodVideoView.this.j = 3;
            }
            if (TXCVodVideoView.this.ag != null) {
                TXCVodVideoView.this.ag.sendEmptyMessage(100);
                TXCVodVideoView.this.ag.sendEmptyMessage(103);
            }
            TXCVodVideoView.this.m = iMediaPlayer.getVideoWidth();
            TXCVodVideoView.this.n = iMediaPlayer.getVideoHeight();
            if (TXCVodVideoView.this.m == 0 || TXCVodVideoView.this.n == 0) {
                if (TXCVodVideoView.this.j == 3) {
                    TXCVodVideoView.this.b();
                }
            } else if (TXCVodVideoView.this.x != null) {
                TXCVodVideoView.this.x.setVideoSize(TXCVodVideoView.this.m, TXCVodVideoView.this.n);
                TXCVodVideoView.this.x.setVideoSampleAspectRatio(TXCVodVideoView.this.y, TXCVodVideoView.this.z);
                if ((!TXCVodVideoView.this.x.shouldWaitForResize() || (TXCVodVideoView.this.o == TXCVodVideoView.this.m && TXCVodVideoView.this.p == TXCVodVideoView.this.n)) && TXCVodVideoView.this.j == 3) {
                    TXCVodVideoView.this.b();
                }
            }
        }
    };
    com.tencent.liteav.txcvodplayer.a.a f = new com.tencent.liteav.txcvodplayer.a.a() {
        public void a(@NonNull a.b bVar, int i, int i2, int i3) {
            if (bVar.a() != TXCVodVideoView.this.x) {
                TXCLog.e(TXCVodVideoView.this.g, "onSurfaceChanged: unmatched render callback\n");
                return;
            }
            TXCLog.d(TXCVodVideoView.this.g, "onSurfaceChanged");
            TXCVodVideoView.this.o = i2;
            TXCVodVideoView.this.p = i3;
            Object obj = null;
            Object obj2 = TXCVodVideoView.this.j == 3 ? 1 : null;
            if (!TXCVodVideoView.this.x.shouldWaitForResize() || (TXCVodVideoView.this.m == i2 && TXCVodVideoView.this.n == i3)) {
                obj = 1;
            }
            if (!(TXCVodVideoView.this.l == null || obj2 == null || obj == null || TXCVodVideoView.this.j != 3)) {
                TXCVodVideoView.this.b();
            }
        }

        public void a(@NonNull a.b bVar, int i, int i2) {
            if (bVar.a() != TXCVodVideoView.this.x) {
                TXCLog.e(TXCVodVideoView.this.g, "onSurfaceCreated: unmatched render callback\n");
                return;
            }
            TXCLog.d(TXCVodVideoView.this.g, "onSurfaceCreated");
            TXCVodVideoView.this.k = bVar;
            if (TXCVodVideoView.this.l != null) {
                TXCVodVideoView.this.a(TXCVodVideoView.this.l, bVar);
            } else {
                TXCVodVideoView.this.f();
            }
        }

        public void a(@NonNull a.b bVar) {
            if (bVar.a() != TXCVodVideoView.this.x) {
                TXCLog.e(TXCVodVideoView.this.g, "onSurfaceDestroyed: unmatched render callback\n");
                return;
            }
            TXCLog.d(TXCVodVideoView.this.g, "onSurfaceDestroyed");
            TXCVodVideoView.this.k = null;
            if (TXCVodVideoView.this.l != null) {
                TXCVodVideoView.this.l.setSurface(null);
            }
            TXCVodVideoView.this.a();
        }
    };
    private String g = "TXCVodVideoView";
    private Uri h;
    private int i = 0;
    private int j = 0;
    private a.b k = null;
    private IMediaPlayer l = null;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private Context v;
    private c w;
    private a x;
    private int y;
    private int z;

    private static class a extends Handler {
        private final WeakReference<TXCVodVideoView> a;
        private final int b = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;

        public a(TXCVodVideoView tXCVodVideoView, Looper looper) {
            super(looper);
            this.a = new WeakReference(tXCVodVideoView);
        }

        public void handleMessage(Message message) {
            TXCVodVideoView tXCVodVideoView = (TXCVodVideoView) this.a.get();
            if (tXCVodVideoView != null && tXCVodVideoView.af != null) {
                long j;
                long tcpSpeed;
                long j2;
                switch (message.what) {
                    case 100:
                        float f = CropImageView.DEFAULT_ASPECT_RATIO;
                        IMediaPlayer unwrappedMediaPlayer = tXCVodVideoView.getUnwrappedMediaPlayer();
                        if (unwrappedMediaPlayer != null) {
                            j = 0;
                            if (unwrappedMediaPlayer instanceof com.tencent.ijk.media.player.IjkMediaPlayer) {
                                com.tencent.ijk.media.player.IjkMediaPlayer ijkMediaPlayer = (com.tencent.ijk.media.player.IjkMediaPlayer) unwrappedMediaPlayer;
                                f = ijkMediaPlayer.getVideoOutputFramesPerSecond();
                                long videoCachedBytes = ijkMediaPlayer.getVideoCachedBytes() + ijkMediaPlayer.getAudioCachedBytes();
                                long bitRate = ijkMediaPlayer.getBitRate();
                                tcpSpeed = ijkMediaPlayer.getTcpSpeed();
                                j = videoCachedBytes;
                                j2 = bitRate;
                            } else if (unwrappedMediaPlayer instanceof IjkExoMediaPlayer) {
                                IjkExoMediaPlayer ijkExoMediaPlayer = (IjkExoMediaPlayer) unwrappedMediaPlayer;
                                gr videoDecoderCounters = ijkExoMediaPlayer.getVideoDecoderCounters();
                                if (videoDecoderCounters != null) {
                                    tcpSpeed = System.currentTimeMillis() - tXCVodVideoView.F;
                                    int z = videoDecoderCounters.e - tXCVodVideoView.E;
                                    tXCVodVideoView.F = System.currentTimeMillis();
                                    tXCVodVideoView.E = videoDecoderCounters.e;
                                    if (tcpSpeed < 3000 && tcpSpeed > 0 && z < 120 && z > 0) {
                                        double d = (double) tcpSpeed;
                                        Double.isNaN(d);
                                        double d2 = 1000.0d / d;
                                        d = (double) z;
                                        Double.isNaN(d);
                                        tXCVodVideoView.G = (int) Math.ceil(d2 * d);
                                    }
                                }
                                f = (float) tXCVodVideoView.G;
                                j2 = (long) ijkExoMediaPlayer.getObservedBitrate();
                                tcpSpeed = j2 / 8;
                            } else {
                                j2 = 0;
                                tcpSpeed = j2;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putFloat("fps", f);
                            bundle.putLong("cachedBytes", j);
                            bundle.putLong("bitRate", j2);
                            bundle.putLong("tcpSpeed", tcpSpeed);
                            tXCVodVideoView.af.a(bundle);
                            removeMessages(100);
                            sendEmptyMessageDelayed(100, 500);
                            break;
                        }
                        return;
                    case 101:
                        tXCVodVideoView.af.a(message.arg1, message.getData());
                        break;
                    case 102:
                        tXCVodVideoView.g();
                        tXCVodVideoView.a(3006, "点播网络重连", "reconnect");
                        break;
                    case 103:
                        j2 = (long) tXCVodVideoView.getCurrentPosition();
                        Bundle bundle2 = new Bundle();
                        j = (long) tXCVodVideoView.getBufferDuration();
                        tcpSpeed = (long) tXCVodVideoView.getDuration();
                        bundle2.putInt(TXLiveConstants.EVT_PLAY_PROGRESS, (int) (j2 / 1000));
                        bundle2.putInt(TXLiveConstants.EVT_PLAY_DURATION, (int) (tcpSpeed / 1000));
                        bundle2.putInt(TXLiveConstants.NET_STATUS_PLAYABLE_DURATION, (int) (j / 1000));
                        bundle2.putInt(TXLiveConstants.EVT_PLAY_PROGRESS_MS, (int) j2);
                        bundle2.putInt(TXLiveConstants.EVT_PLAY_DURATION_MS, (int) tcpSpeed);
                        bundle2.putInt(TXLiveConstants.EVT_PLAYABLE_DURATION_MS, (int) j);
                        tXCVodVideoView.af.a(3007, bundle2);
                        if (tXCVodVideoView.l != null) {
                            removeMessages(103);
                            if (tXCVodVideoView.w.l <= 0) {
                                tXCVodVideoView.w.l = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
                            }
                            sendEmptyMessageDelayed(103, (long) tXCVodVideoView.w.l);
                            break;
                        }
                        break;
                }
            }
        }
    }

    public int getMetaRotationDegree() {
        return this.r;
    }

    public TXCVodVideoView(Context context) {
        super(context);
        a(context);
    }

    public TXCVodVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public TXCVodVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.v = context.getApplicationContext();
        this.w = new c();
        i();
        this.m = 0;
        this.n = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.i = 0;
        this.j = 0;
        Looper mainLooper = Looper.getMainLooper();
        if (mainLooper != null) {
            this.ag = new a(this, mainLooper);
        } else {
            this.ag = null;
        }
    }

    public void setRenderView(a aVar) {
        String str = this.g;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setRenderView ");
        stringBuilder.append(aVar);
        TXCLog.d(str, stringBuilder.toString());
        if (this.x != null) {
            if (this.l != null) {
                this.l.setDisplay(null);
            }
            View view = this.x.getView();
            this.x.removeRenderCallback(this.f);
            this.x = null;
            if (view.getParent() == this) {
                removeView(view);
            }
        }
        if (aVar != null) {
            this.x = aVar;
            aVar.setAspectRatio(this.ae);
            if (this.m > 0 && this.n > 0) {
                aVar.setVideoSize(this.m, this.n);
            }
            if (this.y > 0 && this.z > 0) {
                aVar.setVideoSampleAspectRatio(this.y, this.z);
            }
            View view2 = this.x.getView();
            view2.setLayoutParams(new LayoutParams(-2, -2, 17));
            if (view2.getParent() == null) {
                addView(view2);
            }
            this.x.addRenderCallback(this.f);
            this.x.setVideoRotation(this.q);
        }
    }

    public void setRender(int i) {
        switch (i) {
            case 0:
                setRenderView(null);
                return;
            case 1:
                setRenderView(new SurfaceRenderView(this.v));
                return;
            case 2:
                TextureRenderView textureRenderView = new TextureRenderView(this.v);
                if (this.l != null) {
                    textureRenderView.getSurfaceHolder().a(this.l);
                    textureRenderView.setVideoSize(this.l.getVideoWidth(), this.l.getVideoHeight());
                    textureRenderView.setVideoSampleAspectRatio(this.l.getVideoSarNum(), this.l.getVideoSarDen());
                    textureRenderView.setAspectRatio(this.ae);
                }
                setRenderView(textureRenderView);
                return;
            default:
                TXCLog.e(this.g, String.format(Locale.getDefault(), "invalid render %d\n", new Object[]{Integer.valueOf(i)}));
                return;
        }
    }

    public void setTextureRenderView(TextureRenderView textureRenderView) {
        String str = this.g;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setTextureRenderView ");
        stringBuilder.append(textureRenderView);
        TXCLog.d(str, stringBuilder.toString());
        if (this.l != null) {
            textureRenderView.getSurfaceHolder().a(this.l);
            textureRenderView.setVideoSize(this.l.getVideoWidth(), this.l.getVideoHeight());
            textureRenderView.setVideoSampleAspectRatio(this.l.getVideoSarNum(), this.l.getVideoSarDen());
            textureRenderView.setAspectRatio(this.ae);
        }
        setRenderView(textureRenderView);
    }

    public void setRenderSurface(final Surface surface) {
        this.k = new a.b() {
            public void a(IMediaPlayer iMediaPlayer) {
                iMediaPlayer.setSurface(surface);
            }

            @NonNull
            public a a() {
                return TXCVodVideoView.this.x;
            }
        };
        if (this.l != null) {
            a(this.l, this.k);
        }
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        this.h = uri;
        this.u = 0;
        this.H = 0;
        this.S = 0;
        this.A = null;
        String str = this.g;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setVideoURI ");
        stringBuilder.append(uri);
        TXCLog.d(str, stringBuilder.toString());
        f();
        requestLayout();
        invalidate();
    }

    @TargetApi(23)
    private boolean f() {
        TXCLog.d(this.g, "openVideo");
        if (this.h == null) {
            return false;
        }
        if (this.k == null && this.b) {
            return false;
        }
        a(false);
        ((AudioManager) this.v.getSystemService("audio")).requestAudioFocus(null, 3, 1);
        try {
            IMediaPlayer ijkExoMediaPlayer;
            String uri = this.h.toString();
            if (uri.startsWith("/")) {
                if (!new File(uri).exists()) {
                    throw new FileNotFoundException();
                }
            }
            String str;
            StringBuilder stringBuilder;
            switch (this.c) {
                case 1:
                    ijkExoMediaPlayer = new IjkExoMediaPlayer(this.v);
                    str = this.g;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("exo media player ");
                    stringBuilder.append(ijkExoMediaPlayer);
                    TXCLog.i(str, stringBuilder.toString());
                    break;
                case 2:
                    ijkExoMediaPlayer = new AndroidMediaPlayer();
                    str = this.g;
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("android media player ");
                    stringBuilder.append(ijkExoMediaPlayer);
                    TXCLog.i(str, stringBuilder.toString());
                    break;
                default:
                    String str2;
                    if (this.h != null) {
                        com.tencent.ijk.media.player.IjkMediaPlayer ijkMediaPlayer = new com.tencent.ijk.media.player.IjkMediaPlayer(new IjkLibLoader() {
                            public void loadLibrary(String str) throws UnsatisfiedLinkError, SecurityException {
                                com.tencent.liteav.basic.util.b.a(str);
                            }
                        });
                        com.tencent.ijk.media.player.IjkMediaPlayer.native_setLogLevel(3);
                        ijkMediaPlayer.setOnNativeInvokeListener(this.ac);
                        if (this.w.d) {
                            ijkMediaPlayer.setOption(4, "mediacodec", 1);
                            ijkMediaPlayer.setOption(4, "mediacodec-hevc", 1);
                        } else {
                            ijkMediaPlayer.setOption(4, "mediacodec", 0);
                        }
                        ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 0);
                        ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 0);
                        ijkMediaPlayer.setOption(4, "opensles", 0);
                        ijkMediaPlayer.setOption(4, "overlay-format", 842225234);
                        ijkMediaPlayer.setOption(4, "framedrop", 1);
                        ijkMediaPlayer.setOption(4, "soundtouch", 1);
                        ijkMediaPlayer.setOption(4, "max-fps", 30);
                        if (!this.b || this.j == 4) {
                            ijkMediaPlayer.setOption(4, "start-on-prepared", 0);
                        } else {
                            ijkMediaPlayer.setOption(4, "start-on-prepared", 1);
                        }
                        ijkMediaPlayer.setOption(4, "load-on-prepared", 1);
                        ijkMediaPlayer.setOption(1, "http-detect-range-support", 0);
                        ijkMediaPlayer.setOption(2, "skip_loop_filter", 0);
                        ijkMediaPlayer.setOption(2, "skip_frame", 0);
                        ijkMediaPlayer.setOption(1, "timeout", (long) ((int) ((this.w.c * 1000.0f) * 1000.0f)));
                        ijkMediaPlayer.setOption(1, "reconnect", 1);
                        ijkMediaPlayer.setOption(1, "analyzeduration", 90000000);
                        ijkMediaPlayer.setOption(4, "enable-accurate-seek", this.w.i ? 1 : 0);
                        ijkMediaPlayer.setOption(4, "disable-bitrate-sync", this.w.j ? 0 : 1);
                        ijkMediaPlayer.setOption(1, "dns_cache_timeout", 0);
                        ijkMediaPlayer.setOption(1, "cache_max_capacity", 2147483647L);
                        if (this.t > 0) {
                            ijkMediaPlayer.setOption(4, "seek-at-start", (long) this.t);
                            str2 = this.g;
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("ijk start time ");
                            stringBuilder2.append(this.t);
                            TXCLog.d(str2, stringBuilder2.toString());
                        }
                        if (this.w.h != null) {
                            String str3 = null;
                            for (String str4 : this.w.h.keySet()) {
                                if (str3 == null) {
                                    str3 = String.format("%s: %s", new Object[]{str4, this.w.h.get(str4)});
                                } else {
                                    StringBuilder stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append(str3);
                                    stringBuilder3.append("\r\n");
                                    stringBuilder3.append(String.format("%s: %s", new Object[]{str4, this.w.h.get(str4)}));
                                    str3 = stringBuilder3.toString();
                                }
                            }
                            ijkMediaPlayer.setOption(1, "headers", str3);
                        }
                        ijkMediaPlayer.setBitrateIndex(this.O);
                        com.tencent.ijk.media.player.IjkMediaPlayer.native_setLogLevel(5);
                        if (this.w.e != null && this.D.d(uri)) {
                            this.D.b(this.w.e);
                            this.D.a(this.w.f);
                            this.C = this.D.c(uri);
                            if (this.C != null) {
                                StringBuilder stringBuilder4;
                                if (this.C.a() != null) {
                                    ijkMediaPlayer.setOption(1, "cache_file_path", this.C.a());
                                    stringBuilder4 = new StringBuilder();
                                    stringBuilder4.append("ijkio:cache:ffio:");
                                    stringBuilder4.append(this.h.toString());
                                    uri = stringBuilder4.toString();
                                } else if (this.C.b() != null) {
                                    ijkMediaPlayer.setOption(1, "cache_db_path", this.C.b());
                                    stringBuilder4 = new StringBuilder();
                                    stringBuilder4.append("ijkhlscache:");
                                    stringBuilder4.append(this.h.toString());
                                    uri = stringBuilder4.toString();
                                }
                            }
                        }
                        ijkExoMediaPlayer = ijkMediaPlayer;
                    } else {
                        ijkExoMediaPlayer = null;
                    }
                    str2 = this.g;
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append("ijk media player ");
                    stringBuilder5.append(ijkExoMediaPlayer);
                    TXCLog.i(str2, stringBuilder5.toString());
                    break;
            }
            this.l = new TextureMediaPlayer(ijkExoMediaPlayer);
            this.l.setDataSource(uri);
            this.l.setOnPreparedListener(this.e);
            this.l.setOnVideoSizeChangedListener(this.d);
            this.l.setOnCompletionListener(this.Q);
            this.l.setOnErrorListener(this.T);
            this.l.setOnInfoListener(this.R);
            this.l.setOnBufferingUpdateListener(this.W);
            this.l.setOnSeekCompleteListener(this.aa);
            this.l.setOnTimedTextListener(this.ab);
            this.l.setOnHLSKeyErrorListener(this.ad);
            this.l.setOnHevcVideoDecoderErrorListener(this.U);
            this.l.setOnVideoDecoderErrorListener(this.V);
            this.s = 0;
            a(this.l, this.k);
            this.l.setAudioStreamType(3);
            this.l.setScreenOnWhilePlaying(true);
            this.l.prepareAsync();
            setMute(this.N);
            this.i = 1;
        } catch (FileNotFoundException unused) {
            this.i = -1;
            this.j = -1;
            this.T.onError(this.l, -1004, -3003);
        } catch (Exception e) {
            TXCLog.w(this.g, e.toString());
            this.i = -1;
            this.j = -1;
            this.T.onError(this.l, 1, 0);
        }
        return true;
    }

    private void a(IMediaPlayer iMediaPlayer, a.b bVar) {
        if (iMediaPlayer != null) {
            if (bVar == null) {
                iMediaPlayer.setDisplay(null);
                return;
            }
            TXCLog.d(this.g, "bindSurfaceHolder");
            bVar.a(iMediaPlayer);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a() {
        if (this.l != null) {
            this.l.setDisplay(null);
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(boolean z) {
        if (this.l != null) {
            String str = this.g;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("release player ");
            stringBuilder.append(this.l);
            TXCLog.d(str, stringBuilder.toString());
            this.l.reset();
            this.l.release();
            this.l = null;
            this.i = 0;
            if (z) {
                this.j = 0;
                this.m = 0;
                this.n = 0;
            }
            ((AudioManager) this.v.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    public void b() {
        TXCLog.i(this.g, "start");
        if (h()) {
            if (this.c == 1 && this.i == 5) {
                this.H = 0;
                this.l.seekTo(0);
            }
            this.l.start();
            if (!(this.i == 3 || this.J)) {
                this.i = 3;
                a(3001, "播放开始", "playing");
            }
        }
        this.j = 3;
    }

    private void g() {
        TXCLog.d(this.g, "replay");
        if (this.c == 0) {
            if (this.t == 0 && this.l != null && this.u > 0) {
                this.t = (int) this.l.getCurrentPosition();
            }
            if (!f()) {
                a(false);
            }
        } else if (this.c == 1) {
            j();
        }
    }

    public void c() {
        if (this.l != null) {
            if (this.C != null) {
                if (getDuration() <= 0) {
                    this.D.a(this.C.d(), true);
                } else {
                    this.D.a(this.C.d(), false);
                }
                this.C = null;
            }
            this.l.stop();
            this.l.release();
            this.l = null;
            this.h = null;
            this.m = 0;
            this.n = 0;
            this.B = 1.0f;
            this.J = false;
            this.K = -1;
            this.i = 0;
            this.j = 0;
            this.P = false;
            this.O = 0;
            ((AudioManager) this.v.getSystemService("audio")).abandonAudioFocus(null);
        }
        if (this.ag != null) {
            this.ag.removeMessages(102);
        }
        TXCLog.i(this.g, "stop");
    }

    public void d() {
        this.j = 4;
        TXCLog.i(this.g, "pause");
        if (h() && this.l.isPlaying()) {
            this.l.pause();
            this.i = 4;
        }
    }

    public int getDuration() {
        if (this.l != null && this.u < 1) {
            this.u = (int) this.l.getDuration();
        }
        return this.u;
    }

    public int getCurrentPosition() {
        if (this.c == 0) {
            if (this.J && this.K >= 0) {
                return this.K;
            }
            if (this.t > 0) {
                return this.t;
            }
        }
        if (this.l == null) {
            return 0;
        }
        int currentPosition = (int) this.l.getCurrentPosition();
        if (currentPosition <= 1) {
            return Math.max(currentPosition, this.H);
        }
        this.H = currentPosition;
        return currentPosition;
    }

    public void a(int i) {
        String str = this.g;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("seek to ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        if (getUrlPathExtention().equals("m3u8")) {
            i = Math.min(i, getDuration() - 1000);
        }
        if (i >= 0 && h()) {
            if (i > getDuration()) {
                i = getDuration();
            }
            if (this.J) {
                this.K = i;
            } else {
                this.K = -1;
                this.l.seekTo((long) i);
            }
            if (this.c == 0) {
                this.J = true;
            }
        }
    }

    public void setMute(boolean z) {
        this.N = z;
        if (this.l != null) {
            if (z) {
                this.l.setVolume(CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO);
            } else {
                this.l.setVolume(this.L, this.M);
            }
        }
    }

    public boolean e() {
        return h() && this.l.isPlaying() && this.i != 4;
    }

    public int getBufferDuration() {
        if (this.l == null) {
            return 0;
        }
        IMediaPlayer unwrappedMediaPlayer = getUnwrappedMediaPlayer();
        try {
            if (1 == this.c && (unwrappedMediaPlayer instanceof IjkExoMediaPlayer)) {
                this.s = ((IjkExoMediaPlayer) unwrappedMediaPlayer).getBufferedPercentage();
            }
        } catch (NoClassDefFoundError unused) {
        }
        int duration = (this.s * getDuration()) / 100;
        if (duration < getCurrentPosition()) {
            duration = getCurrentPosition();
        }
        return Math.abs(getDuration() - duration) < 1000 ? getDuration() : duration;
    }

    private boolean h() {
        return (this.l == null || this.i == -1 || this.i == 0 || this.i == 1) ? false : true;
    }

    public void setConfig(c cVar) {
        if (cVar != null) {
            this.w = cVar;
            this.D.a(this.w.k);
        }
    }

    public void setRenderMode(int i) {
        this.ae = i;
        if (this.x != null) {
            this.x.setAspectRatio(this.ae);
        }
        if (this.x != null) {
            this.x.setVideoRotation(this.q);
        }
    }

    public void setVideoRotationDegree(int i) {
        if (!(i == 0 || i == 90 || i == TXLiveConstants.RENDER_ROTATION_180 || i == 270)) {
            if (i != 360) {
                String str = this.g;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("not support degree ");
                stringBuilder.append(i);
                TXCLog.e(str, stringBuilder.toString());
                return;
            }
            i = 0;
        }
        this.q = i;
        if (this.x != null) {
            this.x.setVideoRotation(this.q);
        }
        if (this.x != null) {
            this.x.setAspectRatio(this.ae);
        }
    }

    private void i() {
        setRender(0);
    }

    public void setAutoPlay(boolean z) {
        this.b = z;
    }

    public void setRate(float f) {
        String str = this.g;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setRate ");
        stringBuilder.append(f);
        TXCLog.d(str, stringBuilder.toString());
        if (this.l != null) {
            this.l.setRate(f);
        }
        this.B = f;
    }

    public void setStartTime(float f) {
        this.t = (int) (f * 1000.0f);
    }

    public void setAutoRotate(boolean z) {
        this.a = z;
    }

    private void j() {
        IjkExoMediaPlayer ijkExoMediaPlayer = (IjkExoMediaPlayer) getUnwrappedMediaPlayer();
        if (1 == this.c && (ijkExoMediaPlayer instanceof IjkExoMediaPlayer)) {
            ijkExoMediaPlayer.getPlayer().a(ijkExoMediaPlayer.buildMediaSource(this.h, null), false, false);
            if (this.A == null) {
                ijkExoMediaPlayer.getPlayer().a(this.b);
            } else {
                ijkExoMediaPlayer.getPlayer().a(true);
            }
        }
    }

    private void a(int i, String str, String str2) {
        if ((i != -3005 && i != 3010) || !this.ah) {
            Message message = new Message();
            message.what = 101;
            Bundle bundle = new Bundle();
            message.arg1 = i;
            bundle.putString("description", str);
            message.setData(bundle);
            if (this.ag != null) {
                this.ag.sendMessage(message);
            }
            if (!(i == 3014 || i == 3012)) {
                str = this.g;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("sendSimpleEvent ");
                stringBuilder.append(i);
                stringBuilder.append(" ");
                stringBuilder.append(str2);
                TXCLog.d(str, stringBuilder.toString());
            }
            boolean z = i == -3005 || i == 3010;
            this.ah = z;
        }
    }

    public void setListener(d dVar) {
        this.af = dVar;
    }

    public int getVideoWidth() {
        return this.m;
    }

    public int getVideoHeight() {
        return this.n;
    }

    public String getServerIp() {
        return this.A;
    }

    public int getPlayerType() {
        return this.c;
    }

    public void setPlayerType(int i) {
        this.c = i;
    }

    /* Access modifiers changed, original: 0000 */
    @NonNull
    public String getUrlPathExtention() {
        if (this.h == null || this.h.getPath() == null) {
            return "";
        }
        String path = this.h.getPath();
        return path.substring(path.lastIndexOf(".") + 1, path.length());
    }

    public IMediaPlayer getUnwrappedMediaPlayer() {
        if (this.l instanceof TextureMediaPlayer) {
            return ((TextureMediaPlayer) this.l).getBackEndMediaPlayer();
        }
        return this.l;
    }

    public int getBitrateIndex() {
        if (this.l != null) {
            return this.l.getBitrateIndex();
        }
        return this.O;
    }

    public void setBitrateIndex(int i) {
        String str = this.g;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setBitrateIndex ");
        stringBuilder.append(i);
        TXCLog.d(str, stringBuilder.toString());
        if (this.O != i) {
            this.O = i;
            if (this.l != null) {
                if (this.w.j) {
                    this.l.setBitrateIndex(i);
                } else {
                    g();
                }
            }
        }
    }

    public ArrayList<IjkBitrateItem> getSupportedBitrates() {
        if (this.l != null) {
            return this.l.getSupportedBitrates();
        }
        return new ArrayList();
    }

    public MediaInfo getMediaInfo() {
        if (this.l == null) {
            return null;
        }
        return this.l.getMediaInfo();
    }
}
