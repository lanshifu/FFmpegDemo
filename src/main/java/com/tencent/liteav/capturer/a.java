package com.tencent.liteav.capturer;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Area;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Build.VERSION;
import com.tencent.liteav.basic.f.b;
import com.tencent.liteav.basic.log.TXCLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Marker;

/* compiled from: TXCCameraCapturer */
public class a implements AutoFocusCallback, ErrorCallback {
    private static final String c = "a";
    private Matrix a = new Matrix();
    private int b = 0;
    private Camera d;
    private boolean e = true;
    private int f = 15;
    private int g;
    private int h = 1;
    private int i;
    private int j;
    private int k;
    private int l;
    private SurfaceTexture m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q = false;

    /* compiled from: TXCCameraCapturer */
    private class a {
        public int a = 1280;
        public int b = 720;

        a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public void a(SurfaceTexture surfaceTexture) {
        this.m = surfaceTexture;
    }

    public boolean a(boolean z) {
        this.p = z;
        if (this.d == null) {
            return false;
        }
        boolean z2 = true;
        Parameters parameters = this.d.getParameters();
        List supportedFlashModes = parameters.getSupportedFlashModes();
        if (z) {
            if (supportedFlashModes != null && supportedFlashModes.contains("torch")) {
                TXCLog.i(c, "set FLASH_MODE_TORCH");
                parameters.setFlashMode("torch");
                this.d.setParameters(parameters);
                return z2;
            }
        } else if (supportedFlashModes != null && supportedFlashModes.contains("off")) {
            TXCLog.i(c, "set FLASH_MODE_OFF");
            parameters.setFlashMode("off");
            this.d.setParameters(parameters);
            return z2;
        }
        z2 = false;
        try {
            this.d.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
            z2 = false;
        }
        return z2;
    }

    public void a(int i) {
        this.g = i;
    }

    public void b(int i) {
        this.f = i;
    }

    public void a(float f, float f2) {
        if (this.q) {
            try {
                ArrayList arrayList;
                this.d.cancelAutoFocus();
                Parameters parameters = this.d.getParameters();
                if (this.n) {
                    arrayList = new ArrayList();
                    arrayList.add(new Area(a(f, f2, 2.0f), 1000));
                    parameters.setFocusAreas(arrayList);
                }
                if (this.o) {
                    arrayList = new ArrayList();
                    arrayList.add(new Area(a(f, f2, 3.0f), 1000));
                    parameters.setMeteringAreas(arrayList);
                }
                try {
                    this.d.setParameters(parameters);
                    this.d.autoFocus(this);
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
            }
        }
    }

    public void b(boolean z) {
        this.q = z;
    }

    private Rect a(float f, float f2, float f3) {
        f3 *= 200.0f;
        if (this.e) {
            f = 1.0f - f;
        }
        int i = 0;
        while (i < this.k / 90) {
            i++;
            float f4 = (-(-(f2 - 0.5f))) + 0.5f;
            f2 = (-(f - 0.5f)) + 0.5f;
            f = f4;
        }
        int i2 = (int) ((f * 2000.0f) - 1000.0f);
        int i3 = (int) ((f2 * 2000.0f) - 1000.0f);
        if (i2 < -1000) {
            i2 = -1000;
        }
        if (i3 < -1000) {
            i3 = -1000;
        }
        int i4 = (int) f3;
        int i5 = i2 + i4;
        i4 += i3;
        if (i5 > 1000) {
            i5 = 1000;
        }
        if (i4 > 1000) {
            i4 = 1000;
        }
        return new Rect(i2, i3, i5, i4);
    }

    public int a() {
        if (this.d != null) {
            Parameters parameters = this.d.getParameters();
            if (parameters.getMaxZoom() > 0 && parameters.isZoomSupported()) {
                return parameters.getMaxZoom();
            }
        }
        return 0;
    }

    public boolean c(int i) {
        if (this.d == null) {
            return false;
        }
        Parameters parameters = this.d.getParameters();
        if (parameters.getMaxZoom() <= 0 || !parameters.isZoomSupported()) {
            TXCLog.e(c, "camera not support zoom!");
            return false;
        } else if (i < 0 || i > parameters.getMaxZoom()) {
            String str = c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("invalid zoom value : ");
            stringBuilder.append(i);
            stringBuilder.append(", while max zoom is ");
            stringBuilder.append(parameters.getMaxZoom());
            TXCLog.e(str, stringBuilder.toString());
            return false;
        } else {
            try {
                parameters.setZoom(i);
                this.d.setParameters(parameters);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public void a(float f) {
        if (this.d != null) {
            if (f > 1.0f) {
                f = 1.0f;
            }
            if (f < -1.0f) {
                f = -1.0f;
            }
            Parameters parameters = this.d.getParameters();
            int minExposureCompensation = parameters.getMinExposureCompensation();
            int maxExposureCompensation = parameters.getMaxExposureCompensation();
            if (minExposureCompensation == 0 || maxExposureCompensation == 0) {
                TXCLog.e(c, "camera not support setExposureCompensation!");
            } else {
                int d = b.a().d();
                float f2 = (float) maxExposureCompensation;
                f *= f2;
                if (d != 0 && d <= maxExposureCompensation && d >= minExposureCompensation) {
                    String str = c;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("camera setExposureCompensation: ");
                    stringBuilder.append(d);
                    TXCLog.d(str, stringBuilder.toString());
                    parameters.setExposureCompensation(d);
                } else if (f <= f2 && f >= ((float) minExposureCompensation)) {
                    String str2 = c;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("camera setExposureCompensation: ");
                    stringBuilder2.append(f);
                    TXCLog.d(str2, stringBuilder2.toString());
                    parameters.setExposureCompensation((int) f);
                }
            }
            try {
                this.d.setParameters(parameters);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void d(int i) {
        this.h = i;
        this.k = (((this.l - 90) + (this.h * 90)) + 360) % 360;
    }

    public int c(boolean z) {
        try {
            if (this.m == null) {
                return -2;
            }
            StringBuilder stringBuilder;
            if (this.d != null) {
                b();
            }
            CameraInfo cameraInfo = new CameraInfo();
            int i = -1;
            int i2 = -1;
            for (int i3 = 0; i3 < Camera.getNumberOfCameras(); i3++) {
                Camera.getCameraInfo(i3, cameraInfo);
                String str = c;
                stringBuilder = new StringBuilder();
                stringBuilder.append("camera index ");
                stringBuilder.append(i3);
                stringBuilder.append(", facing = ");
                stringBuilder.append(cameraInfo.facing);
                TXCLog.i(str, stringBuilder.toString());
                if (cameraInfo.facing == 1) {
                    i = i3;
                }
                if (cameraInfo.facing == 0) {
                    i2 = i3;
                }
            }
            String str2 = c;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("camera front, id = ");
            stringBuilder2.append(i);
            TXCLog.i(str2, stringBuilder2.toString());
            str2 = c;
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("camera back , id = ");
            stringBuilder2.append(i2);
            TXCLog.i(str2, stringBuilder2.toString());
            if (i == -1 && i2 != -1) {
                i = i2;
            }
            if (i2 == -1 && i != -1) {
                i2 = i;
            }
            this.e = z;
            if (this.e) {
                this.d = Camera.open(i);
            } else {
                this.d = Camera.open(i2);
            }
            Parameters parameters = this.d.getParameters();
            List supportedFocusModes = parameters.getSupportedFocusModes();
            if (this.q && supportedFocusModes != null && supportedFocusModes.contains("auto")) {
                TXCLog.i(c, "support FOCUS_MODE_AUTO");
                parameters.setFocusMode("auto");
            } else if (supportedFocusModes != null && supportedFocusModes.contains("continuous-video")) {
                TXCLog.i(c, "support FOCUS_MODE_CONTINUOUS_VIDEO");
                parameters.setFocusMode("continuous-video");
            }
            if (VERSION.SDK_INT >= 14) {
                if (parameters.getMaxNumFocusAreas() > 0) {
                    this.n = true;
                }
                if (parameters.getMaxNumMeteringAreas() > 0) {
                    this.o = true;
                }
            }
            str2 = "";
            List supportedPreviewSizes = parameters.getSupportedPreviewSizes();
            if (supportedPreviewSizes != null) {
                String str3 = str2;
                for (int i4 = 0; i4 < supportedPreviewSizes.size(); i4++) {
                    Size size = (Size) supportedPreviewSizes.get(i4);
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(str3);
                    stringBuilder3.append(String.format("camera supported preview size %d x %d\n", new Object[]{Integer.valueOf(size.width), Integer.valueOf(size.height)}));
                    str3 = stringBuilder3.toString();
                }
                str2 = str3;
            }
            a e = e(this.g);
            if (e == null) {
                this.d.release();
                this.d = null;
                TXCLog.d(c, "不支持的视频分辨率");
                return -3;
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(String.format("choose preview size %d x %d ", new Object[]{Integer.valueOf(e.a), Integer.valueOf(e.b)}));
            TXCLog.i(c, stringBuilder.toString());
            this.i = e.a;
            this.j = e.b;
            parameters.setPreviewSize(e.a, e.b);
            int[] g = g(this.f);
            if (g != null) {
                parameters.setPreviewFpsRange(g[0], g[1]);
            } else {
                parameters.setPreviewFrameRate(f(this.f));
            }
            if (!this.e) {
                i = i2;
            }
            this.l = h(i);
            this.k = (((this.l - 90) + (this.h * 90)) + 360) % 360;
            this.d.setDisplayOrientation(0);
            this.d.setPreviewTexture(this.m);
            this.d.setParameters(parameters);
            this.d.setErrorCallback(this);
            this.d.startPreview();
            return 0;
        } catch (IOException e2) {
            e2.printStackTrace();
            return -1;
        } catch (Exception e3) {
            e3.printStackTrace();
            return -1;
        }
    }

    public void b() {
        if (this.d != null) {
            try {
                this.d.setErrorCallback(null);
                this.d.setPreviewCallback(null);
                this.d.stopPreview();
                this.d.release();
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                this.d = null;
                this.m = null;
            }
            this.d = null;
            this.m = null;
        }
    }

    public int c() {
        return this.k;
    }

    public boolean d() {
        return this.e;
    }

    public int e() {
        return this.i;
    }

    public int f() {
        return this.j;
    }

    private a e(int i) {
        List supportedPreviewSizes = this.d.getParameters().getSupportedPreviewSizes();
        ArrayList arrayList = new ArrayList();
        switch (i) {
            case 1:
            case 2:
            case 4:
                arrayList.add(new a(640, 360));
                arrayList.add(new a(768, 432));
                arrayList.add(new a(960, 544));
                arrayList.add(new a(960, 540));
                arrayList.add(new a(800, 480));
                arrayList.add(new a(640, 480));
                arrayList.add(new a(960, 720));
                arrayList.add(new a(1280, 720));
                break;
            case 3:
                break;
            case 5:
                arrayList.add(new a(960, 544));
                arrayList.add(new a(960, 540));
                arrayList.add(new a(960, 720));
                arrayList.add(new a(1280, 720));
                arrayList.add(new a(800, 480));
                arrayList.add(new a(640, 360));
                arrayList.add(new a(640, 480));
                break;
            case 6:
                arrayList.add(new a(1280, 720));
                arrayList.add(new a(1920, 1088));
                arrayList.add(new a(1920, 1080));
                arrayList.add(new a(960, 544));
                arrayList.add(new a(960, 540));
                arrayList.add(new a(960, 720));
                arrayList.add(new a(800, 480));
                arrayList.add(new a(640, 360));
                arrayList.add(new a(640, 480));
                break;
            case 7:
                arrayList.add(new a(1920, 1088));
                arrayList.add(new a(1920, 1080));
                arrayList.add(new a(1280, 720));
                arrayList.add(new a(960, 544));
                arrayList.add(new a(960, 540));
                arrayList.add(new a(960, 720));
                arrayList.add(new a(800, 480));
                arrayList.add(new a(768, 432));
                arrayList.add(new a(640, 360));
                arrayList.add(new a(640, 480));
                break;
        }
        arrayList.add(new a(480, 320));
        arrayList.add(new a(640, 360));
        arrayList.add(new a(640, 480));
        arrayList.add(new a(768, 432));
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            a aVar = (a) arrayList.get(i2);
            for (int i3 = 0; i3 < supportedPreviewSizes.size(); i3++) {
                Size size = (Size) supportedPreviewSizes.get(i3);
                if (size.width == aVar.a && size.height == aVar.b) {
                    String str = c;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("wanted:");
                    stringBuilder.append(aVar.a);
                    stringBuilder.append(Marker.ANY_MARKER);
                    stringBuilder.append(aVar.b);
                    TXCLog.w(str, stringBuilder.toString());
                    return aVar;
                }
            }
        }
        return null;
    }

    public void onAutoFocus(boolean z, Camera camera) {
        if (z) {
            TXCLog.i(c, "AUTO focus success");
        } else {
            TXCLog.i(c, "AUTO focus failed");
        }
    }

    public void onError(int i, Camera camera) {
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("camera catch error ");
        stringBuilder.append(i);
        TXCLog.w(str, stringBuilder.toString());
    }

    private int f(int i) {
        List supportedPreviewFrameRates = this.d.getParameters().getSupportedPreviewFrameRates();
        if (supportedPreviewFrameRates == null) {
            TXCLog.e(c, "getSupportedFPS error");
            return 1;
        }
        int i2 = 0;
        int intValue = ((Integer) supportedPreviewFrameRates.get(0)).intValue();
        while (i2 < supportedPreviewFrameRates.size()) {
            int intValue2 = ((Integer) supportedPreviewFrameRates.get(i2)).intValue();
            if (Math.abs(intValue2 - i) - Math.abs(intValue - i) < 0) {
                intValue = intValue2;
            }
            i2++;
        }
        String str = c;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("choose fpts=");
        stringBuilder.append(intValue);
        TXCLog.i(str, stringBuilder.toString());
        return intValue;
    }

    private int[] g(int i) {
        i *= 1000;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("camera supported preview fps range: wantFPS = ");
        stringBuilder.append(i);
        stringBuilder.append("\n");
        String stringBuilder2 = stringBuilder.toString();
        List<int[]> supportedPreviewFpsRange = this.d.getParameters().getSupportedPreviewFpsRange();
        if (supportedPreviewFpsRange == null || supportedPreviewFpsRange.size() <= 0) {
            return null;
        }
        int[] iArr = (int[]) supportedPreviewFpsRange.get(0);
        Collections.sort(supportedPreviewFpsRange, new Comparator<int[]>() {
            /* renamed from: a */
            public int compare(int[] iArr, int[] iArr2) {
                return iArr[1] - iArr2[1];
            }
        });
        for (int[] iArr2 : supportedPreviewFpsRange) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(stringBuilder2);
            stringBuilder3.append("camera supported preview fps range: ");
            stringBuilder3.append(iArr2[0]);
            stringBuilder3.append(" - ");
            stringBuilder3.append(iArr2[1]);
            stringBuilder3.append("\n");
            stringBuilder2 = stringBuilder3.toString();
        }
        for (int[] iArr3 : supportedPreviewFpsRange) {
            if (iArr3[0] <= i && i <= iArr3[1]) {
                iArr = iArr3;
                break;
            }
        }
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append(stringBuilder2);
        stringBuilder4.append("choose preview fps range: ");
        stringBuilder4.append(iArr[0]);
        stringBuilder4.append(" - ");
        stringBuilder4.append(iArr[1]);
        TXCLog.i(c, stringBuilder4.toString());
        return iArr;
    }

    private int h(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        if (cameraInfo.facing == 1) {
            return (360 - cameraInfo.orientation) % 360;
        }
        return (cameraInfo.orientation + 360) % 360;
    }
}
