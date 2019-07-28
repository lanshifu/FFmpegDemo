package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.blankj.utilcode.util.k;
import com.tencent.rtmp.ITXLivePushListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.TXLivePusher.VideoCustomProcessListener;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tomatolive.library.R;
import com.tomatolive.library.faceunity.BeautyControlView;
import com.tomatolive.library.faceunity.a;
import com.tomatolive.library.utils.b;
import com.tomatolive.library.utils.m;
import com.tomatolive.library.utils.w;
import com.yalantis.ucrop.view.CropImageView;
import java.lang.ref.WeakReference;

/* compiled from: PushManager */
/* renamed from: sg */
public class sg {
    private Context a;
    private TXCloudVideoView b;
    private a c;
    private TXLivePushConfig d;
    private TXLivePusher e;
    private boolean f = true;
    private Bitmap g;
    private boolean h = true;
    private a i;
    private String j;
    private Bitmap k;
    private int l = 1;
    private boolean m;
    private ViewGroup n;
    private ITXLivePushListener o = new 1();
    private PhoneStateListener p = null;

    /* compiled from: PushManager */
    /* renamed from: sg$a */
    public interface a {
        void onCameraError();

        void onMicError();

        void onPushSuccess();

        void onRePush();
    }

    /* compiled from: PushManager */
    /* renamed from: sg$b */
    static class b extends PhoneStateListener {
        WeakReference<TXLivePusher> a;

        public b(TXLivePusher tXLivePusher) {
            this.a = new WeakReference(tXLivePusher);
        }

        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            TXLivePusher tXLivePusher = (TXLivePusher) this.a.get();
            switch (i) {
                case 0:
                    if (tXLivePusher != null) {
                        tXLivePusher.resumePusher();
                        return;
                    }
                    return;
                case 1:
                    if (tXLivePusher != null) {
                        tXLivePusher.pausePusher();
                        return;
                    }
                    return;
                case 2:
                    if (tXLivePusher != null) {
                        tXLivePusher.pausePusher();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* compiled from: PushManager */
    /* renamed from: sg$1 */
    class 1 implements ITXLivePushListener {
        public void onNetStatus(Bundle bundle) {
        }

        1() {
        }

        public void onPushEvent(int i, Bundle bundle) {
            String string = bundle.getString(TXLiveConstants.EVT_DESCRIPTION);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("i:");
            stringBuilder.append(i);
            stringBuilder.append("= msg:");
            stringBuilder.append(string);
            m.b(stringBuilder.toString());
            if (i != TXLiveConstants.PUSH_ERR_NET_DISCONNECT) {
                if (i != TXLiveConstants.PUSH_EVT_PUSH_BEGIN) {
                    if (i != TXLiveConstants.PUSH_WARNING_HW_ACCELERATION_FAIL) {
                        switch (i) {
                            case TXLiveConstants.PUSH_ERR_OPEN_MIC_FAIL /*-1302*/:
                                if (sg.this.c != null) {
                                    sg.this.c.onMicError();
                                    break;
                                }
                                break;
                            case TXLiveConstants.PUSH_ERR_OPEN_CAMERA_FAIL /*-1301*/:
                                if (sg.this.c != null) {
                                    sg.this.c.onCameraError();
                                    break;
                                }
                                break;
                            default:
                                return;
                        }
                    }
                    sg.this.d.setHardwareAcceleration(0);
                    sg.this.e.setConfig(sg.this.d);
                } else if (sg.this.c != null) {
                    sg.this.c.onPushSuccess();
                }
            } else if (sg.this.c != null) {
                sg.this.c.onRePush();
            }
        }
    }

    /* compiled from: PushManager */
    /* renamed from: sg$2 */
    class 2 implements VideoCustomProcessListener {
        public void onDetectFacePoints(float[] fArr) {
        }

        2() {
        }

        public int onTextureCustomProcess(int i, int i2, int i3) {
            if (sg.this.h) {
                sg.this.i.a();
                sg.this.h = false;
            }
            return sg.this.i.a(i, i2, i3);
        }

        public void onTextureDestoryed() {
            sg.this.i.b();
            sg.this.h = true;
        }
    }

    public sg(Context context) {
        this.a = context;
        this.k = w.a(this.a.getResources(), R.drawable.fq_ic_placeholder_anchor_push_open_mirror);
    }

    public void a(ViewGroup viewGroup) {
        this.n = viewGroup;
        if (viewGroup != null) {
            this.b = new TXCloudVideoView(this.a);
            viewGroup.addView(this.b, 0, new LayoutParams(-1, -1));
        }
        this.e = new TXLivePusher(this.a);
        this.d = new TXLivePushConfig();
        this.d.setVideoEncodeGop(1);
        this.d.setPauseImg(this.k);
        this.d.setPauseFlag(3);
        this.d.setFrontCamera(this.f);
        this.d.setTouchFocus(true);
        this.d.setVideoResolution(this.l);
        a();
        this.d.setWatermark(this.g, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        this.d.setBeautyFilter(7, 6, 5);
        this.d.setHardwareAcceleration(2);
        this.e.setConfig(this.d);
        this.e.startCameraPreview(this.b);
        this.e.setMirror(k.a().b("LIVE_MIRROR_KEY", false));
        this.e.setVideoQuality(2, false, false);
        this.e.setPushListener(this.o);
        this.p = new b(this.e);
        ((TelephonyManager) this.a.getApplicationContext().getSystemService("phone")).listen(this.p, 32);
    }

    public Bitmap a() {
        this.g = BitmapFactory.decodeFile(b.l());
        return this.g;
    }

    public void setOnPushListener(a aVar) {
        this.c = aVar;
    }

    public void b() {
        if (this.b != null) {
            this.b.setVisibility(4);
        }
    }

    public void c() {
        a();
        this.d.setWatermark(this.g, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        this.e.setConfig(this.d);
    }

    public void a(BeautyControlView beautyControlView) {
        if (beautyControlView != null) {
            this.i = new a.a(this.a).a(0).a(false).b(false).c(true).a();
            this.i.c();
            beautyControlView.setOnFaceUnityControlListener(this.i);
            this.e.setVideoProcessListener(new 2());
        }
    }

    public TXLivePusher d() {
        return this.e;
    }

    public void a(boolean z, boolean z2) {
        if (z) {
            m.b("mPreviewView.onResume()。。");
            this.b.onResume();
        }
        if (z2) {
            this.e.resumePusher();
            m.b("mLivePusher.resumePusher()。。");
        }
        if (this.m) {
            this.m = false;
            this.e.startCameraPreview(this.b);
        }
    }

    public void e() {
        this.m = true;
        m.b("onPause....");
        this.b.onPause();
        this.e.pausePusher();
    }

    public void a(boolean z) {
        this.e.setMirror(z);
    }

    public void f() {
        this.f ^= 1;
        this.e.switchCamera();
        this.d.setFrontCamera(this.f);
        if (this.i != null) {
            this.i.a(this.f, 0);
        }
    }

    public void a(String str) {
        this.j = str;
        this.e.startPusher(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startStream:");
        stringBuilder.append(str);
        m.b(stringBuilder.toString());
    }

    public void g() {
        this.e.stopCameraPreview(true);
        this.e.setPushListener(null);
        this.e.stopPusher();
        this.d.setPauseImg(null);
    }

    public void h() {
        g();
        this.e.setPushListener(this.o);
        this.d.setVideoEncodeGop(1);
        this.d.setPauseImg(this.k);
        this.d.setPauseFlag(3);
        this.d.setVideoResolution(this.l);
        this.d.setBeautyFilter(7, 6, 5);
        a();
        this.d.setWatermark(this.g, CropImageView.DEFAULT_ASPECT_RATIO, CropImageView.DEFAULT_ASPECT_RATIO, 1.0f);
        this.e.setConfig(this.d);
        this.e.startCameraPreview(this.b);
        this.e.setMirror(k.a().b("LIVE_MIRROR_KEY", false));
        this.e.startPusher(this.j);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("restartStream:");
        stringBuilder.append(this.j);
        m.b(stringBuilder.toString());
    }

    public void b(boolean z) {
        this.e.turnOnFlashLight(z);
    }

    public void c(boolean z) {
        this.e.setMute(z);
    }

    public void i() {
        if (!(this.k == null || this.k.isRecycled())) {
            this.k.recycle();
            this.k = null;
        }
        if (!(this.g == null || this.g.isRecycled())) {
            this.g.recycle();
            this.g = null;
        }
        this.e.stopCameraPreview(true);
        ((TelephonyManager) this.a.getApplicationContext().getSystemService("phone")).listen(this.p, 0);
        this.p = null;
        if (this.n != null) {
            this.n.removeView(this.b);
        }
        if (this.b != null) {
            this.b.onDestroy();
            this.b = null;
        }
        this.a = null;
        if (this.c != null) {
            this.c = null;
        }
    }
}
