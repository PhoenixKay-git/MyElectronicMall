package com.bwie.test.module;

import com.bwie.test.myelectronicmall.R;
import com.bwie.test.net.AdApi;
import com.bwie.test.net.AdApiService;
import com.bwie.test.net.AddCartApi;
import com.bwie.test.net.AddCartApiService;
import com.bwie.test.net.AddrsApi;
import com.bwie.test.net.AddrsApiService;
import com.bwie.test.net.Api;
import com.bwie.test.net.CatagoryApi;
import com.bwie.test.net.CatagoryApiService;
import com.bwie.test.net.CreateOrderApi;
import com.bwie.test.net.CreateOrderApiService;
import com.bwie.test.net.DeatiService;
import com.bwie.test.net.DeleteCartApi;
import com.bwie.test.net.DeleteCartApiService;
import com.bwie.test.net.DetailApi;
import com.bwie.test.net.GetCartApi;
import com.bwie.test.net.GetCartApiService;
import com.bwie.test.net.ListApi;
import com.bwie.test.net.ListService;
import com.bwie.test.net.LoginApi;
import com.bwie.test.net.LoginApiService;
import com.bwie.test.net.ProductCatagoryApi;
import com.bwie.test.net.ProductCatagoryApiService;
import com.bwie.test.net.RegisterApi;
import com.bwie.test.net.RegisterApiService;
import com.bwie.test.net.UpdateCartApi;
import com.bwie.test.net.UpdateCartApiService;
import com.bwie.test.net.UpdateHeaderApi;
import com.bwie.test.net.UpdateHeaderApiService;
import com.bwie.test.utils.MyInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class HttpModule {
    @Provides
    OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS);
    }

    @Provides
    Retrofit.Builder provideRetrofit(OkHttpClient.Builder builder){
        builder.addInterceptor(new MyInterceptor());
        return new Retrofit.Builder()
                .baseUrl(Api.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build());
    }

    @Provides
    AdApi provideAdApi(Retrofit.Builder builder) {
//        //builder.addInterceptor(new MyInterceptor());
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASEURL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(builder.build())
//                .build();
        Retrofit retrofit = builder.build();
        AdApiService adApiService = retrofit.create(AdApiService.class);
        return AdApi.getAdApi(adApiService);
    }

    @Provides
    CatagoryApi provideCatagoryApi(Retrofit.Builder builder) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASEURL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(builder.build())
//                .build();
        Retrofit retrofit = builder.build();
        CatagoryApiService catagoryApiService = retrofit.create(CatagoryApiService.class);
        return CatagoryApi.getCatagoryApi(catagoryApiService);
    }
    @Provides
    ProductCatagoryApi provideProductCatagoryApi(Retrofit.Builder builder) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Api.BASEURL)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(builder.build())
//                .build();
        Retrofit retrofit = builder.build();
        ProductCatagoryApiService productCatagoryApiService = retrofit.create(ProductCatagoryApiService.class);
        return ProductCatagoryApi.getProductCatagoryApi(productCatagoryApiService);
    }

    @Provides
    DetailApi provideDetailApi(Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();
        DeatiService deatiService = retrofit.create( DeatiService.class );
        return DetailApi.getDetailApi(deatiService);
    }

    @Provides
    ListApi provideListApi(Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();
        ListService listService = retrofit.create( ListService.class );
        return ListApi.getListApi(listService);
    }

    @Provides
    LoginApi provideLoginApi(Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();
        LoginApiService loginApiService = retrofit.create(LoginApiService.class);
        return LoginApi.getLoginApi(loginApiService);
    }

    @Provides
    RegisterApi provideRegisterApi(Retrofit.Builder builder){
        Retrofit retrofit = builder.build();
        RegisterApiService registerApiService = retrofit.create( RegisterApiService.class );
        return RegisterApi.getRegisterApi(registerApiService);
    }

    @Provides
    AddCartApi provideAddCartApi(Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();
        AddCartApiService addCartApiService = retrofit.create(AddCartApiService.class);
        return AddCartApi.getAddCartApi(addCartApiService);
    }

    @Provides
    GetCartApi provideGetCartApi(Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();
        GetCartApiService getCartApiService = retrofit.create(GetCartApiService.class);
        return GetCartApi.getGetCartApi(getCartApiService);
    }

    @Provides
    UpdateCartApi provideUpdateCartApi(Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();
        UpdateCartApiService updateCartApiService = retrofit.create(UpdateCartApiService.class);
        return UpdateCartApi.getUpdateCartApi(updateCartApiService);
    }

    @Provides
    DeleteCartApi provideDeleteCartApi(Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();
        DeleteCartApiService deleteCartApiService = retrofit.create(DeleteCartApiService.class);
        return DeleteCartApi.getDeleteCartApi(deleteCartApiService);
    }

    @Provides
    AddrsApi provideAddrsApi(Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();
        AddrsApiService addrsApiService = retrofit.create( AddrsApiService.class );
        return AddrsApi.getAddrsApi( addrsApiService );
    }

    @Provides
    CreateOrderApi provideCreateOrderApi(Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();
        CreateOrderApiService createOrderApiService = retrofit.create( CreateOrderApiService.class );
        return CreateOrderApi.getCreateOrderApi( createOrderApiService );
    }

    @Provides
    UpdateHeaderApi provideUpdateHeaderApi(Retrofit.Builder builder) {
        Retrofit retrofit = builder.build();
        UpdateHeaderApiService updateHeaderApiService = retrofit.create(UpdateHeaderApiService.class);
        return UpdateHeaderApi.getUpdateHeaderApi(updateHeaderApiService);
    }
}
