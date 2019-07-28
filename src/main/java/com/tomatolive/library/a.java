package com.tomatolive.library;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.http.HttpResponseCache;
import android.text.TextUtils;
import com.blankj.utilcode.util.Utils;
import com.blankj.utilcode.util.k;
import com.tomatolive.library.download.CarDownLoadManager;
import com.tomatolive.library.download.GiftDownLoadManager;
import com.tomatolive.library.http.ApiRetrofit;
import com.tomatolive.library.http.HttpRxObserver;
import com.tomatolive.library.http.RequestParams;
import com.tomatolive.library.http.ResultCallBack;
import com.tomatolive.library.http.function.HttpResultFunction;
import com.tomatolive.library.http.function.ServerResultFunction;
import com.tomatolive.library.http.utils.EncryptUtil;
import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.model.event.LoginEvent;
import com.tomatolive.library.model.event.LogoutEvent;
import com.tomatolive.library.ui.view.task.TaskConstance;
import com.tomatolive.library.utils.litepal.LitePal;
import com.tomatolive.library.utils.u;
import com.tomatolive.library.utils.z;
import com.youdao.sdk.app.i;
import defpackage.wd;
import defpackage.wl;
import defpackage.xk;
import defpackage.xl;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/* compiled from: TomatoLiveSDK */
public class a {
    public d a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    private String k;
    private Application l;

    /* compiled from: TomatoLiveSDK */
    public interface a {
        void a(Context context);

        void b(Context context);
    }

    /* compiled from: TomatoLiveSDK */
    public interface b {
        void a(Context context);

        void b(Context context);
    }

    /* compiled from: TomatoLiveSDK */
    private static class c {
        private static final a a = new a();
    }

    /* compiled from: TomatoLiveSDK */
    public interface d {
        void a(Context context);

        void b(Context context);

        void c(Context context);
    }

    public void a(Context context, Map<String, Object> map) {
        if (context != null) {
        }
    }

    /* synthetic */ a(AnonymousClass1 anonymousClass1) {
        this();
    }

    private a() {
        this.k = "1fdd2bb053fdb5bf";
        this.a = null;
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "8zy8nbs9lyddx02slcz8ypmwcr2tlu72";
        this.h = "8zy8nbs9lyddx02slcz8ypmwcr2tlu72";
        this.i = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ4BUmnAVmr+0JE7zL195q1QIg1NAW/zln54MjEM6qv45OpTB/jp2zRdw9i4kNPk/IjrOPYPh4dv4+YgYWQkVs0vzVnzuse9rbfoEKDFbze9pyo3EfLnrSUhe3dJp9BWwnq105/zFPkgbx7Qv5T25CMbLTfD0cFHvOAN/HEN/l0HAgMBAAECgYBnFse1dacsgfqEd3a6v5UsyNaexPpGF7C97SAaPqox798zP185OSMrBG5OTQU5KvLVRltQt4seg5M2xzOjyc3TY4/XGZIQtRA35Sovx/s+NvSy6VD+L5SEJ4r8/SXR8mXKJfxKVg8jNOcWiRR4VuQmIozzPEFqZH9qIVJcVjWt0QJBAONHlIRN4OVFzsC3hhc1yv2vYZzzjXcTw8np9wlx1Qjup3rzVhbsAd1KXqpFCTZYlqSfrudDSwQCEXaFyu32uukCQQCx+LyJOoNbnMbPSkZOoQkQfPjgHqy0yAhqiNKT7ciIVyREFmpbB1Q9ptQWDmMwEBb2ksTBNCrgUOX/IE5KsYJvAkEA3Wz6Y5+gEJ7fHGBwUiKFXnxEZG3gD/gAkrHPjLMLMwWXw7BY2kIaWua+rbJOlFTghwhPlV25MvF04/zbRNVRKQJAE5Lv6Yft+p17oEDjCrLbdFzKYpv9EsUNZ+o0fuCgNZ6f9n0gpXJg6Yb3vJVIg3jBjc0Gptk9/f3nze+XrM9pMQJAQ2EroFmPUTuv86Ghwdjnh0z4DnJkr60a6ccoNiqNZpmTAND6og99djzJqGWFHGDGS/JLfbiVdafzj6QZ5TQBzQ==";
        this.j = "246887c3-ee20-4fe8-a320-1fde4a8d10b6";
        xk.a(new wl<Throwable>() {
            /* renamed from: a */
            public void accept(Throwable th) throws Exception {
            }
        });
    }

    public static a a() {
        return c.a;
    }

    public void a(Application application, String str, String str2, String str3, String str4, String str5, boolean z, byte[] bArr, boolean z2, d dVar) {
        this.l = application;
        this.a = dVar;
        this.f = str;
        this.e = str2;
        this.b = str3;
        this.c = str4;
        this.d = str5;
        a(z2);
        Utils.a(application);
        i.a(application, this.k);
        LitePal.initialize(application);
        a(application);
        z.a().d(str);
        k.a().a("isOpenBeauty", z);
        if (z) {
            k.a().a("authEncryptStr", a(bArr));
        }
    }

    public void a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            this.b = str;
        }
        if (!TextUtils.isEmpty(str2)) {
            this.c = str2;
        }
        if (!TextUtils.isEmpty(str3)) {
            this.d = str3;
        }
    }

    public void b() {
        GiftDownLoadManager.getInstance().updateAnimOnlineRes();
        CarDownLoadManager.getInstance().updateAnimOnlineAllRes();
    }

    public Application c() {
        return this.l;
    }

    public void a(final Context context, final a aVar) {
        if (context != null && com.tomatolive.library.utils.b.d()) {
            ApiRetrofit.getInstance().getApiService().getSdkLoginService(new RequestParams().getSDKLoginParams()).map(new ServerResultFunction<UserEntity>() {
            }).onErrorResumeNext(new HttpResultFunction<UserEntity>() {
            }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new HttpRxObserver(context, new ResultCallBack<UserEntity>() {
                /* renamed from: a */
                public void onSuccess(UserEntity userEntity) {
                    if (userEntity != null) {
                        z.a().a(userEntity.getToken());
                        z.a().b(userEntity.getUserId());
                        org.greenrobot.eventbus.c.a().c(new LoginEvent());
                        if (aVar != null) {
                            aVar.a(context);
                        }
                    }
                }

                public void onError(int i, String str) {
                    z.a().a(null);
                    z.a().b(null);
                    if (aVar != null) {
                        aVar.b(context);
                    }
                }
            }));
        }
    }

    public void a(final Context context, final b bVar) {
        if (context != null && com.tomatolive.library.utils.b.d()) {
            ApiRetrofit.getInstance().getApiService().getExitSDKService(new RequestParams().getExitSDKParams()).map(new ServerResultFunction<Object>() {
            }).onErrorResumeNext(new HttpResultFunction<Object>() {
            }).subscribeOn(xl.b()).observeOn(wd.a()).subscribe(new HttpRxObserver(context, new ResultCallBack<Object>() {
                public void onSuccess(Object obj) {
                    org.greenrobot.eventbus.c.a().c(new LogoutEvent());
                    z.a().l();
                    TaskConstance.getInstance().clear();
                    if (bVar != null) {
                        bVar.a(context);
                    }
                }

                public void onError(int i, String str) {
                    if (bVar != null) {
                        bVar.b(context);
                    }
                }
            }));
        }
    }

    public void a(Context context, final String str) {
        if (context != null && com.tomatolive.library.utils.b.d()) {
            ApiRetrofit.getInstance().getApiService().getUpdateUserInfoService(new RequestParams().getUpdateAvatarParams(str)).map(new ServerResultFunction<Object>() {
            }).onErrorResumeNext(new HttpResultFunction<Object>() {
            }).subscribeOn(xl.b()).observeOn(xl.b()).subscribe(new HttpRxObserver(context, new ResultCallBack<Object>() {
                public void onError(int i, String str) {
                }

                public void onSuccess(Object obj) {
                    z.a().e(str);
                }
            }));
        }
    }

    public void b(Context context, final String str) {
        if (context != null && com.tomatolive.library.utils.b.d()) {
            ApiRetrofit.getInstance().getApiService().getUpdateUserInfoService(new RequestParams().getUpdateNicknameParams(str)).map(new ServerResultFunction<Object>() {
            }).onErrorResumeNext(new HttpResultFunction<Object>() {
            }).subscribeOn(xl.b()).observeOn(xl.b()).subscribe(new HttpRxObserver(context, new ResultCallBack<Object>() {
                public void onError(int i, String str) {
                }

                public void onSuccess(Object obj) {
                    z.a().f(str);
                }
            }));
        }
    }

    public void c(Context context, final String str) {
        if (context != null && com.tomatolive.library.utils.b.d()) {
            ApiRetrofit.getInstance().getApiService().getUpdateUserInfoService(new RequestParams().getUpdateSexParams(str)).map(new ServerResultFunction<Object>() {
            }).onErrorResumeNext(new HttpResultFunction<Object>() {
            }).subscribeOn(xl.b()).observeOn(xl.b()).subscribe(new HttpRxObserver(context, new ResultCallBack<Object>() {
                public void onError(int i, String str) {
                }

                public void onSuccess(Object obj) {
                    z.a().g(str);
                }
            }));
        }
    }

    public void d() {
        z.a().k();
    }

    private String a(byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            return "";
        }
        try {
            return u.c(EncryptUtil.DESEncrypt("246887c3-ee20-4fe8-a320-1fde4a8d10b6", u.b(bArr)));
        } catch (Exception unused) {
            return "";
        }
    }

    private void a(boolean z) {
        if (z) {
            this.g = "8zy8nbs9lyddx02slcz8ypmwcr2tlu72";
            this.h = "8zy8nbs9lyddx02slcz8ypmwcr2tlu72";
            this.i = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ4BUmnAVmr+0JE7zL195q1QIg1NAW/zln54MjEM6qv45OpTB/jp2zRdw9i4kNPk/IjrOPYPh4dv4+YgYWQkVs0vzVnzuse9rbfoEKDFbze9pyo3EfLnrSUhe3dJp9BWwnq105/zFPkgbx7Qv5T25CMbLTfD0cFHvOAN/HEN/l0HAgMBAAECgYBnFse1dacsgfqEd3a6v5UsyNaexPpGF7C97SAaPqox798zP185OSMrBG5OTQU5KvLVRltQt4seg5M2xzOjyc3TY4/XGZIQtRA35Sovx/s+NvSy6VD+L5SEJ4r8/SXR8mXKJfxKVg8jNOcWiRR4VuQmIozzPEFqZH9qIVJcVjWt0QJBAONHlIRN4OVFzsC3hhc1yv2vYZzzjXcTw8np9wlx1Qjup3rzVhbsAd1KXqpFCTZYlqSfrudDSwQCEXaFyu32uukCQQCx+LyJOoNbnMbPSkZOoQkQfPjgHqy0yAhqiNKT7ciIVyREFmpbB1Q9ptQWDmMwEBb2ksTBNCrgUOX/IE5KsYJvAkEA3Wz6Y5+gEJ7fHGBwUiKFXnxEZG3gD/gAkrHPjLMLMwWXw7BY2kIaWua+rbJOlFTghwhPlV25MvF04/zbRNVRKQJAE5Lv6Yft+p17oEDjCrLbdFzKYpv9EsUNZ+o0fuCgNZ6f9n0gpXJg6Yb3vJVIg3jBjc0Gptk9/f3nze+XrM9pMQJAQ2EroFmPUTuv86Ghwdjnh0z4DnJkr60a6ccoNiqNZpmTAND6og99djzJqGWFHGDGS/JLfbiVdafzj6QZ5TQBzQ==";
            this.j = "246887c3-ee20-4fe8-a320-1fde4a8d10b6";
            return;
        }
        this.g = "789";
        this.h = "456";
        this.i = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIQVlM6t9IBT6OxfQPhAhTAm/lCnzjy5K9mtrlYmlEXc/uNlhIqs0bCUvBPCWZ6UqWGK23LjPxwiC2jy9+i9SdkopsenLKhZV+yf+y4m02gV/wROQ2QsLng1/0IePiSyl0iGgmXsjR+LYcdAGSBBweZ/iNyiyEsnV5FYHS0cLb0XAgMBAAECgYADpmEFURNHlIoENiGieo3zpbAzZF+zl95ZVo5RvSEtyQyWFhESj/H/ciy8UwuM8Ui49FBaHWN6EIrGLKijGs/2kRcmx4mbnK9eQmkQBuRaTfgqc03XTK7LNU+pz3PVTRlfn7GkDfsSWaeDWNtbR1zK1mgoR+JnMfqbM8C0FqOaEQJBAMEzfzqEgkpmEtx9cUfyLPIw0pGviepZtp+lFO6PHQlPszwM/Xof9ZVhIR8oIR+mCJfqqCGoeoWQbAnoiQizoD8CQQCvBHnxnsxBITaq2Wrjod/rDeM3YHRDg6HET9cVKKIIvhSlLFx8KYw+ZbhPxdz219hdFmdjM3PYy1xibucsQi0pAkBDgKypU3b6a6OXajTUQGc3z5siz8ROHz5RlSo1F8e7Yx9qkddWfigeIyuhaTH5jtddzN0ltWnplMZKx/ZpFemdAkEAot86kHWkRZQgKLyucWpKVJeW9QjpCY9tMqDOWx12NUaXNeNjqhSMM+E7tdk/uePCsVZRHotaas1NizkEHzbyiQJAfC0aRuF5AdJ81o8GJ4j0FwnRUiqWS2DPT9n2x16cmhP2v2ik14nQzp2ihML2kE1I7WUtHzFkZv6NnxBthM4Xwg==";
        this.j = "c21d31be-4300-4881-a553-156ebb5df087";
    }

    private void a(Application application) {
        try {
            HttpResponseCache.install(new File(application.getCacheDir(), "http"), 41943040);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(Activity activity) {
        com.gyf.barlibrary.d.a(activity).b();
    }

    public void b(Activity activity) {
        com.gyf.barlibrary.d.a(activity).c();
    }
}
