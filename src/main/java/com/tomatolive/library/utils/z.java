package com.tomatolive.library.utils;

import android.text.TextUtils;
import com.blankj.utilcode.util.k;
import com.tomatolive.library.model.UserEntity;
import tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener;

/* compiled from: UserInfoManager */
public class z {
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;

    /* compiled from: UserInfoManager */
    private static class a {
        private static final z a = new z();
    }

    private z() {
        this.a = "fq_user";
        this.b = "isLogin";
        this.c = "userId";
        this.d = "appId";
        this.e = OnNativeInvokeListener.ARG_IP;
        this.f = "token";
        this.g = "sdkUserId";
        this.h = "userNickname";
        this.i = "userAvatar";
        this.j = "userSex";
        this.k = "userGradeMax";
    }

    public static z a() {
        return a.a;
    }

    public void a(UserEntity userEntity) {
        if (userEntity != null) {
            a(true);
            c(userEntity.getUserId());
            f(userEntity.getName());
            e(userEntity.getAvatar());
            g(userEntity.getSex());
            a(userEntity.getToken());
        }
    }

    public String b() {
        return k.a("fq_user").b("isLogin", false) ? k.a("fq_user").b("token", "") : "";
    }

    public void a(String str) {
        k.a("fq_user").a("token", str);
    }

    public String c() {
        return k.a("fq_user").b("isLogin", false) ? k.a("fq_user").b("sdkUserId", "") : "";
    }

    public void b(String str) {
        k.a("fq_user").a("sdkUserId", str);
    }

    public String d() {
        return k.a("fq_user").b("userId", "");
    }

    public void c(String str) {
        k.a("fq_user").a("userId", str);
    }

    public String e() {
        return k.a("fq_user").b("appId", "");
    }

    public void d(String str) {
        k.a("fq_user").a("appId", str);
    }

    public String f() {
        return k.a("fq_user").b("userNickname", "");
    }

    public String g() {
        return k.a("fq_user").b("userAvatar");
    }

    public String h() {
        return k.a("fq_user").b("userSex");
    }

    public void e(String str) {
        k.a("fq_user").a("userAvatar", str);
    }

    public void f(String str) {
        k.a("fq_user").a("userNickname", str);
    }

    public void g(String str) {
        k.a("fq_user").a("userSex", str);
    }

    public void a(boolean z) {
        k.a("fq_user").a("isLogin", z);
    }

    public boolean i() {
        if (!k.a("fq_user").b("isLogin", false) || TextUtils.isEmpty(d()) || TextUtils.isEmpty(c()) || TextUtils.isEmpty(b())) {
            return false;
        }
        return true;
    }

    public int j() {
        return k.a("fq_user").c("userGradeMax", 60);
    }

    public void a(int i) {
        k.a("fq_user").b("userGradeMax", i);
    }

    public void k() {
        a(false);
        a(null);
        k.a("fq_user").c("token", true);
        k.a("fq_user").c("isLogin", true);
    }

    public void l() {
        a(false);
        a(null);
        c(null);
        k.a("fq_user").c("token", true);
        k.a("fq_user").c("isLogin", true);
        k.a("fq_user").c("userId", true);
    }
}
