package com.tomatolive.library.http;

import android.support.annotation.NonNull;
import com.tomatolive.library.model.AnchorEntity;
import com.tomatolive.library.model.BackpackItemEntity;
import com.tomatolive.library.model.BannedEntity;
import com.tomatolive.library.model.BannerEntity;
import com.tomatolive.library.model.CarDownloadEntity;
import com.tomatolive.library.model.CarEntity;
import com.tomatolive.library.model.CarHistoryRecordEntity;
import com.tomatolive.library.model.CountryCodeEntity;
import com.tomatolive.library.model.CoverEntity;
import com.tomatolive.library.model.ExpenseCarDetailEntity;
import com.tomatolive.library.model.ExpenseMenuEntity;
import com.tomatolive.library.model.GiftDownloadListEntity;
import com.tomatolive.library.model.GiftIncomeExpenseDetail;
import com.tomatolive.library.model.GuardIncomeExpenseDetail;
import com.tomatolive.library.model.GuardItemEntity;
import com.tomatolive.library.model.IncomeMenuEntity;
import com.tomatolive.library.model.LabelEntity;
import com.tomatolive.library.model.LiveEndEntity;
import com.tomatolive.library.model.LiveEntity;
import com.tomatolive.library.model.LiveInitInfoEntity;
import com.tomatolive.library.model.LivePreNoticeEntity;
import com.tomatolive.library.model.MyCarEntity;
import com.tomatolive.library.model.MyLiveEntity;
import com.tomatolive.library.model.OnLineUsersEntity;
import com.tomatolive.library.model.PropsIncomeExpenseDetail;
import com.tomatolive.library.model.ReceiveGiftRecordEntity;
import com.tomatolive.library.model.StatisticsEntity;
import com.tomatolive.library.model.SysParamsInfoEntity;
import com.tomatolive.library.model.TaskBoxEntity;
import com.tomatolive.library.model.UploadFileEntity;
import com.tomatolive.library.model.UserEntity;
import com.tomatolive.library.model.db.GiftBoxEntity;
import io.reactivex.k;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface ApiService {
    public static final String BASE_ADV_SERVER_URL = "api/adv/";
    public static final String BASE_ANCHOR_SERVER_URL = "api/anchor/";
    public static final String BASE_CAR_SERVER_URL = "api/car/";
    public static final String BASE_EXPENSE_SERVER_URL = "api/mobile/user/expense/";
    public static final String BASE_GIFTBOX_SERVER_URL = "api/giftBox/";
    public static final String BASE_GUARD_SERVER_URL = "api/guard/";
    public static final String BASE_INCOME_SERVER_URL = "api/mobile/anchor/income/";
    public static final String BASE_LIVE_SERVER_URL = "api/live/";
    public static final String BASE_MOBILE_LIVE_SERVER_URL = "api/mobile/live/";
    public static final String BASE_MOBILE_SERVER_URL = "api/mobile/";
    public static final String BASE_MY_LIVE_SERVER_URL = "api/mobile/myLive/";
    public static final String BASE_SEARCH_SERVER_URL = "api/search/";
    public static final String BASE_STREAM_SERVER_URL = "api/stream/";
    public static final String BASE_TASKBOX_SERVER_URL = "api/taskBox/";
    public static final String BASE_USER_MOBILE_SERVER_URL = "userService/mobile/";
    public static final String BASE_VERSION = "/v02b06";

    @POST("api/mobile/live/broadcastClickCountUpdate")
    k<HttpResultModel<Object>> broadcastClickCountUpdate(@Body Map<String, Object> map);

    @POST("api/taskBox/finished")
    k<HttpResultModel<Object>> changeTaskState(@Body Map<String, Object> map);

    @Streaming
    @GET
    k<ResponseBody> downLoadFile(@Url @NonNull String str);

    @POST("api/mobile/live/addHerald")
    k<HttpResultModel<Object>> getAddLivePreNoticeService(@Body Map<String, Object> map);

    @POST("api/live/list")
    k<HttpResultModel<HttpResultPageModel<LiveEntity>>> getAllListService(@Body Map<String, Object> map);

    @POST("api/mobile/anchor/ispass")
    k<HttpResultModel<AnchorEntity>> getAnchorAuthService(@Body Map<String, Object> map);

    @POST("api/mobile/anchor/giftList")
    k<HttpResultModel<HttpResultPageModel<ReceiveGiftRecordEntity>>> getAnchorGiftRecordListService(@Body Map<String, Object> map);

    @POST("api/guard/anchor/list")
    k<HttpResultModel<HttpResultPageModel<AnchorEntity>>> getAnchorGuardListService(@Body Map<String, Object> map);

    @POST("api/anchor/info")
    k<HttpResultModel<AnchorEntity>> getAnchorInfoService(@Body Map<String, Object> map);

    @POST("api/mobile/live/detail")
    k<HttpResultModel<LiveEntity>> getAnchorLiveDetailService(@Body Map<String, Object> map);

    @POST("api/mobile/follow/followAnchorList")
    k<HttpResultModel<HttpResultPageModel<LiveEntity>>> getAttentionAnchorListService(@Body Map<String, Object> map);

    @POST("api/mobile/follow/addOrCancel")
    k<HttpResultModel<Object>> getAttentionAnchorService(@Body Map<String, Object> map);

    @POST("api/mobile/myLive/banPostSetting/list")
    k<HttpResultModel<HttpResultPageModel<BannedEntity>>> getBannedListService(@Body Map<String, Object> map);

    @POST("api/mobile/myLive/banPostSetting/banPost")
    k<HttpResultModel<Object>> getBannedSetService(@Body Map<String, Object> map);

    @POST("api/adv/list")
    k<HttpResultModel<List<BannerEntity>>> getBannerListService(@Body Map<String, Object> map);

    @POST("api/car/buy")
    k<HttpResultModel<Object>> getBuyCarService(@Body Map<String, Object> map);

    @POST("api/car/resource")
    k<HttpResultModel<List<CarDownloadEntity>>> getCarDownloadList(@Body Map<String, Object> map);

    @POST("api/car/record")
    k<HttpResultModel<List<CarHistoryRecordEntity>>> getCarHistoryRecordListService(@Body Map<String, Object> map);

    @POST("api/car/list")
    k<HttpResultModel<List<CarEntity>>> getCarListService(@Body Map<String, Object> map);

    @POST("api/mobile/statistics/income")
    k<HttpResultModel<List<AnchorEntity>>> getCharmTopListService(@Body Map<String, Object> map);

    @POST("api/sys/countryCode/findAllCountryCode")
    k<HttpResultModel<List<CountryCodeEntity>>> getCountryCodeService(@Body Map<String, Object> map);

    @POST("userService/mobile/live/getCurrentOnlineUserList")
    k<HttpResultModel<OnLineUsersEntity>> getCurrentOnlineUserListService(@Body Map<String, Object> map);

    @POST("api/mobile/statistics/expendByAnchorId")
    k<HttpResultModel<List<AnchorEntity>>> getDedicateTopListService(@Body Map<String, Object> map);

    @POST("api/mobile/watchHistory/clear")
    k<HttpResultModel<Object>> getDeleteWatchHistoryAllService(@Body Map<String, Object> map);

    @POST("api/mobile/watchHistory/delete")
    k<HttpResultModel<Object>> getDeleteWatchHistoryService(@Body Map<String, Object> map);

    @POST("api/live/enjoyList")
    k<HttpResultModel<List<LiveEntity>>> getEnjoyListService(@Body Map<String, Object> map);

    @POST("userService/mobile/index/leaveLiveModule")
    k<HttpResultModel<Object>> getExitSDKService(@Body Map<String, Object> map);

    @POST("api/mobile/user/expense/car")
    k<HttpResultModel<HttpResultPageModel<ExpenseCarDetailEntity>>> getExpenseCarListService(@Body Map<String, Object> map);

    @POST("api/mobile/user/expense/gift")
    k<HttpResultModel<HttpResultPageModel<GiftIncomeExpenseDetail>>> getExpenseGiftListService(@Body Map<String, Object> map);

    @POST("api/mobile/user/expense/guard")
    k<HttpResultModel<HttpResultPageModel<GuardIncomeExpenseDetail>>> getExpenseGuardListService(@Body Map<String, Object> map);

    @POST("api/mobile/props/expendRecords")
    k<HttpResultModel<HttpResultPageModel<PropsIncomeExpenseDetail>>> getExpensePropsListService(@Body Map<String, Object> map);

    @POST("api/mobile/user/expense/")
    k<HttpResultModel<ExpenseMenuEntity>> getExpenseStatisticsService(@Body Map<String, Object> map);

    @POST("api/giftBox/list")
    k<HttpResultModel<List<GiftBoxEntity>>> getGiftBoxListService(@Body Map<String, Object> map);

    @POST("api/guard/list")
    k<HttpResultModel<List<GuardItemEntity>>> getGuardListService(@Body Map<String, Object> map);

    @POST("api/mobile/statistics/indexRank")
    k<HttpResultModel<List<AnchorEntity>>> getHomeCharmTopListService(@Body Map<String, Object> map);

    @POST("api/search/hotKey")
    k<HttpResultModel<List<LabelEntity>>> getHotKeyListService(@Body Map<String, Object> map);

    @POST("api/live/hotList")
    k<HttpResultModel<HttpResultPageModel<LiveEntity>>> getHotListService(@Body Map<String, Object> map);

    @POST("api/mobile/myLive/liveManager/v02b06")
    k<HttpResultModel<HttpResultPageModel<BannedEntity>>> getHouseListService(@Body Map<String, Object> map);

    @POST("api/mobile/myLive/liveManager/set/v02b06")
    k<HttpResultModel<Object>> getHouseManagerSetService(@Body Map<String, Object> map);

    @POST("api/mobile/anchor/income/gift")
    k<HttpResultModel<HttpResultPageModel<GiftIncomeExpenseDetail>>> getIncomeGiftListService(@Body Map<String, Object> map);

    @POST("api/mobile/anchor/income/guard")
    k<HttpResultModel<HttpResultPageModel<GuardIncomeExpenseDetail>>> getIncomeGuardListService(@Body Map<String, Object> map);

    @POST("api/mobile/props/incomeRecords")
    k<HttpResultModel<HttpResultPageModel<PropsIncomeExpenseDetail>>> getIncomePropsListService(@Body Map<String, Object> map);

    @POST("api/mobile/anchor/income/")
    k<HttpResultModel<IncomeMenuEntity>> getIncomeStatisticsService(@Body Map<String, Object> map);

    @POST("api/mobile/statistics/getIndexRankConfig")
    k<HttpResultModel<List<String>>> getIndexRankConfigService(@Body Map<String, Object> map);

    @POST("api/mobile/labels")
    k<HttpResultModel<List<LabelEntity>>> getLabelListService(@Body Map<String, Object> map);

    @POST("api/exp/room/action")
    k<HttpResultModel<Object>> getLiveActionService(@Body Map<String, Object> map);

    @POST("api/mobile/live/enterClosedLive")
    k<HttpResultModel<LiveEndEntity>> getLiveEndInfoService(@Body Map<String, Object> map);

    @POST("api/mobile/live/getLiveInitInfo")
    k<HttpResultModel<LiveInitInfoEntity>> getLiveInitInfoService(@Body Map<String, Object> map);

    @POST("api/live/getLivePopularity")
    k<HttpResultModel<LiveEntity>> getLivePopularityService(@Body Map<String, Object> map);

    @POST("api/mobile/live/getHerald")
    k<HttpResultModel<LivePreNoticeEntity>> getLivePreNoticeService(@Body Map<String, Object> map);

    @POST("api/car/me")
    k<HttpResultModel<List<MyCarEntity>>> getMyCarListService(@Body Map<String, Object> map);

    @POST("api/guard/me")
    k<HttpResultModel<GuardItemEntity>> getMyGuardInfoService(@Body Map<String, Object> map);

    @POST("api/mobile/myLive/initData")
    k<HttpResultModel<MyLiveEntity>> getMyLiveInitDataService(@Body Map<String, Object> map);

    @POST("api/adv/notice")
    k<HttpResultModel<BannerEntity>> getNoticeListService(@Body Map<String, Object> map);

    @POST("api/guard/save")
    k<HttpResultModel<GuardItemEntity>> getOpenGuardService(@Body Map<String, Object> map);

    @POST("api/app/live/api")
    k<HttpResultModel<Object>> getPhoneCodeService(@Body Map<String, Object> map);

    @POST("api/mobile/live/getPreStartLiveInfo")
    k<HttpResultModel<CoverEntity>> getPreStartLiveInfoService(@Body Map<String, Object> map);

    @POST("api/anchor/commendatory")
    k<HttpResultModel<List<AnchorEntity>>> getRecommendAnchorService(@Body Map<String, Object> map);

    @POST("api/live/recommendList")
    k<HttpResultModel<HttpResultPageModel<LiveEntity>>> getRecommendListService(@Body Map<String, Object> map);

    @POST("userService/mobile/index/syncUserInfo2LiveModuleAndGetToken")
    k<HttpResultModel<UserEntity>> getSdkLoginService(@Body Map<String, Object> map);

    @POST("api/search/anchor")
    k<HttpResultModel<HttpResultPageModel<AnchorEntity>>> getSearchAnchorListService(@Body Map<String, Object> map);

    @POST("api/search/live")
    k<HttpResultModel<HttpResultPageModel<LiveEntity>>> getSearchLiveListService(@Body Map<String, Object> map);

    @POST("api/mobile/myLive/searchUsers")
    k<HttpResultModel<List<BannedEntity>>> getSearchUserListService(@Body Map<String, Object> map);

    @POST("api/live/searchByTag")
    k<HttpResultModel<HttpResultPageModel<LiveEntity>>> getSortListService(@Body Map<String, Object> map);

    @POST("api/mobile/live/startLive")
    k<HttpResultModel<LiveEntity>> getStartLiveService(@Body Map<String, Object> map);

    @POST("api/mobile/statistics/expend")
    k<HttpResultModel<List<AnchorEntity>>> getStrengthTopListService(@Body Map<String, Object> map);

    @POST("api/mobile/auth")
    k<HttpResultModel<Object>> getSubmitAnchorAuthService(@Body Map<String, Object> map);

    @POST("api/sysParamsInfo/config")
    k<HttpResultModel<SysParamsInfoEntity>> getSysParamsInfoService(@Body Map<String, Object> map);

    @POST("api/taskBox/list")
    k<HttpResultModel<List<TaskBoxEntity>>> getTaskBoxList(@Body Map<String, Object> map);

    @POST("api/taskBox/take")
    k<HttpResultModel<Object>> getTaskBoxTake(@Body Map<String, Object> map);

    @POST("userService/mobile/index/syncUserInfo2LiveModule")
    k<HttpResultModel<Object>> getUpdateUserInfoService(@Body Map<String, Object> map);

    @POST("api/stream/errorReport")
    k<HttpResultModel<Object>> getUploadErrorReportService(@Body Map<String, Object> map);

    @POST("api/mobile/live/liveCoverAuthAdd")
    k<HttpResultModel<Object>> getUploadLiveCoverService(@Body Map<String, Object> map);

    @POST("api/car/use")
    k<HttpResultModel<Object>> getUseCarService(@Body Map<String, Object> map);

    @POST("api/mobile/myLive/getUserBalance")
    k<HttpResultModel<AnchorEntity>> getUserBalanceService(@Body Map<String, Object> map);

    @POST("api/mobile/user/userInfo")
    k<HttpResultModel<AnchorEntity>> getUserInfoService(@Body Map<String, Object> map);

    @POST("api/app/live/api")
    k<HttpResultModel<UserEntity>> getUserOverService(@Body Map<String, Object> map);

    @POST("api/mobile/userProps/list")
    k<HttpResultModel<HttpResultPageModel<BackpackItemEntity>>> getUserPropsListService(@Body Map<String, Object> map);

    @POST("api/mobile/watchHistory/list")
    k<HttpResultModel<HttpResultPageModel<LiveEntity>>> getWatchHistoryListService(@Body Map<String, Object> map);

    @POST("api/mobile/live/wsaddress")
    k<HttpResultModel<LiveEntity>> getWebSocketAddressService(@Body Map<String, Object> map);

    @POST("api/common/giftList")
    k<HttpResultModel<GiftDownloadListEntity>> giftList(@Body Map<String, Object> map);

    @POST("statis/report")
    k<HttpResultModel<StatisticsEntity>> statisticsReport(@Body Map<String, Object> map);

    @POST
    @Headers({"urlName:upload"})
    k<HttpResultModel<UploadFileEntity>> uploadFile(@Url String str, @Body RequestBody requestBody);
}
