package com.bwie.test.net;

import com.bwie.test.bean.DeatilBean;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DeatiService {
    @FormUrlEncoded
    @POST("product/getProductDetail")
    Observable<DeatilBean> getProductDetail(@Field("pid") String pid);
}
