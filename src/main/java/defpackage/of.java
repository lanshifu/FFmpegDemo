package defpackage;

import com.one.tomato.entity.Ad;
import com.one.tomato.entity.BaseResponse;
import com.one.tomato.entity.ForgetPsSec;
import com.one.tomato.entity.LookTimes;
import com.one.tomato.entity.MainNotifyBean;
import com.one.tomato.entity.PostList;
import com.one.tomato.entity.PostSame;
import com.one.tomato.entity.TTSts;
import com.one.tomato.entity.UpdateInfo;
import com.one.tomato.entity.db.CountryDB;
import com.one.tomato.entity.db.LoginInfo;
import com.one.tomato.entity.db.LookTimeBean;
import com.one.tomato.entity.db.SystemParam;
import com.one.tomato.entity.db.UserInfo;
import com.one.tomato.mvp.base.okhttp.e;
import io.reactivex.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.f;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

/* compiled from: ApiImplService.kt */
/* renamed from: of */
public interface of {
    public static final a a = a.a;

    /* compiled from: ApiImplService.kt */
    /* renamed from: of$a */
    public static final class a {
        static final /* synthetic */ a a = new a();

        private a() {
        }

        public final of a() {
            Object a = e.a().a(of.class);
            f.a(a, "RetrofitClient.getInstan…iImplService::class.java)");
            return (of) a;
        }
    }

    @GET("/app/init/sysParam")
    k<BaseResponse<SystemParam>> a();

    @FormUrlEncoded
    @POST("/app/memberInfo/member")
    k<BaseResponse<UserInfo>> a(@Field("memberId") int i);

    @FormUrlEncoded
    @POST("/app/support/isFaver")
    k<BaseResponse<Object>> a(@Field("memberId") int i, @Field("articleId") int i2);

    @FormUrlEncoded
    @POST("/app/article/video/list")
    k<BaseResponse<ArrayList<PostList>>> a(@Field("pageNo") int i, @Field("pageSize") int i2, @Field("memberId") int i3);

    @FormUrlEncoded
    @POST("/app/article/download/save")
    k<BaseResponse<Object>> a(@Field("memberId") int i, @Field("articleId") Integer num, @Field("payType") int i2);

    @FormUrlEncoded
    @POST("/app/sts/getToken")
    k<BaseResponse<TTSts>> a(@Field("memberId") int i, @Field("md5") String str, @Field("fileSize") long j);

    @FormUrlEncoded
    @POST("/app/init/usedTime")
    @Headers({"serverType:report"})
    k<BaseResponse<Object>> a(@Field("time") long j, @Field("memberId") int i, @Field("userType") int i2);

    @FormUrlEncoded
    @POST("/app/version/list")
    k<BaseResponse<UpdateInfo>> a(@Field("versionNo") String str);

    @FormUrlEncoded
    @POST("/app/memberInfo/logout")
    k<BaseResponse<LoginInfo>> a(@Field("deviceNo") String str, @Field("memberId") int i);

    @FormUrlEncoded
    @POST("/app/memberInfo/sendVerifyCode")
    k<BaseResponse<Object>> a(@Field("countryCode") String str, @Field("phone") String str2);

    @FormUrlEncoded
    @POST("/app/memberInfo/userAccountLogin")
    k<BaseResponse<LoginInfo>> a(@Field("account") String str, @Field("countryCode") String str2, @Field("password") String str3);

    @FormUrlEncoded
    @POST("/app/memberInfo/getUserInfo")
    k<BaseResponse<LoginInfo>> a(@Field("deviceNo") String str, @Field("token") String str2, @Field("versionNo") String str3, @Field("parentInviteCode") String str4);

    @FormUrlEncoded
    @POST
    k<BaseResponse<LoginInfo>> a(@Url String str, @FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/app/advert/memberLogin")
    k<BaseResponse<Object>> a(@FieldMap HashMap<String, String> hashMap);

    @FormUrlEncoded
    @POST("/app/article/recommendArticle")
    k<BaseResponse<ArrayList<PostList>>> a(@FieldMap Map<String, Object> map);

    @GET("/app/advert/init")
    k<BaseResponse<Ad>> b();

    @FormUrlEncoded
    @POST("/app/article/initLookTimes")
    @Headers({"serverType:report"})
    k<BaseResponse<LookTimeBean>> b(@Field("memberId") int i);

    @FormUrlEncoded
    @POST("/app/favor/save")
    k<BaseResponse<Object>> b(@Field("memberId") int i, @Field("articleId") int i2);

    @FormUrlEncoded
    @POST("/app/advert/report")
    @Headers({"serverType:report"})
    k<BaseResponse<Object>> b(@Field("adId") int i, @Field("memberId") int i2, @Field("type") int i3);

    @FormUrlEncoded
    @POST("/app/report/saveReportApplication")
    k<BaseResponse<Object>> b(@Field("json") String str);

    @FormUrlEncoded
    @POST("/app/article/publishArticle")
    k<BaseResponse<PostSame>> b(@FieldMap HashMap<String, Object> hashMap);

    @FormUrlEncoded
    @POST("/app/article/follow/list")
    k<BaseResponse<ArrayList<PostList>>> b(@FieldMap Map<String, Object> map);

    @POST("/report/daiLi/success")
    @Headers({"serverType:report"})
    k<String> c();

    @FormUrlEncoded
    @POST("/app/memberInfo/saveQrCode")
    k<BaseResponse<Object>> c(@Field("memberId") int i);

    @FormUrlEncoded
    @POST("/app/publish/delete")
    k<BaseResponse<Object>> c(@Field("articleIds") int i, @Field("memberId") int i2);

    @FormUrlEncoded
    @POST("/app/memberFollow/saveAndCancel")
    k<BaseResponse<Object>> c(@Field("memberId") int i, @Field("attentionMemberId") int i2, @Field("flag") int i3);

    @FormUrlEncoded
    @POST("/app/article/list")
    k<BaseResponse<ArrayList<PostList>>> c(@FieldMap Map<String, Object> map);

    @POST("/report/daiLi/failure")
    @Headers({"serverType:report"})
    k<String> d();

    @FormUrlEncoded
    @POST("/app/notice/topNoticeList")
    k<BaseResponse<ArrayList<MainNotifyBean>>> d(@Field("type") int i);

    @FormUrlEncoded
    @POST("/app/article/browser")
    @Headers({"serverType:report"})
    k<BaseResponse<Object>> d(@Field("id") int i, @Field("memberId") int i2);

    @FormUrlEncoded
    @POST("/app/video/videoPay")
    k<BaseResponse<Object>> d(@Field("memberId") int i, @Field("articleId") int i2, @Field("payType") int i3);

    @FormUrlEncoded
    @POST("/app/article/member/list")
    k<BaseResponse<ArrayList<PostList>>> d(@FieldMap Map<String, Object> map);

    @POST("/app/memberInfo/country")
    k<BaseResponse<ArrayList<CountryDB>>> e();

    @FormUrlEncoded
    @POST("app/video/payInfo")
    k<BaseResponse<LookTimes>> e(@Field("memberId") int i);

    @FormUrlEncoded
    @POST("/app/search/article")
    k<BaseResponse<ArrayList<PostList>>> e(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/app/article/download/check")
    k<BaseResponse<LookTimes>> f(@Field("memberId") int i);

    @FormUrlEncoded
    @POST("/app/support/list")
    k<BaseResponse<ArrayList<PostList>>> f(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/app/article/group/list")
    k<BaseResponse<ArrayList<PostList>>> g(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/app/favor/list")
    k<BaseResponse<ArrayList<PostList>>> h(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/app/publish/list")
    k<BaseResponse<ArrayList<PostList>>> i(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/app/search/tag")
    k<BaseResponse<ArrayList<PostList>>> j(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/app/memberInfo/updateMemberInfo")
    k<BaseResponse<Object>> k(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/app/memberInfo/validatePhoneAndCode")
    k<BaseResponse<ForgetPsSec>> l(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/app/memberInfo/modify")
    k<BaseResponse<Object>> m(@FieldMap Map<String, Object> map);
}
