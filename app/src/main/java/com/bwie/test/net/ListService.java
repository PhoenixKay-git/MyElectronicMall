package com.bwie.test.net;

import com.bwie.test.bean.ProductsBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ListService {
    @FormUrlEncoded
    @POST("product/getProducts")
    Observable<ProductsBean> getProducts(@Field("pscid") String pscid);
}
