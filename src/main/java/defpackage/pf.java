package defpackage;

import com.one.tomato.entity.BaseResponse;
import com.one.tomato.entity.DomainJsonBean;
import io.reactivex.k;
import retrofit2.http.GET;
import retrofit2.http.Url;

/* compiled from: IDomainRequestService */
/* renamed from: pf */
public interface pf {
    @GET("/app/domain/listRsaThenAes")
    k<BaseResponse<DomainJsonBean>> a();

    @GET
    k<String> a(@Url String str);
}
