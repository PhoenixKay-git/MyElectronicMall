package com.bwie.test.net;

import com.bwie.test.bean.DeatilBean;

import io.reactivex.Observable;

public class DetailApi {
    private static DetailApi detailApi;
    private DeatiService deatiService;

    private DetailApi(DeatiService deatiService) {
        this.deatiService=deatiService;
    }

    public static DetailApi getDetailApi(DeatiService deatiService){
        if (detailApi==null) {
            detailApi=new DetailApi(deatiService);
        }
        return detailApi;
    }

    public Observable<DeatilBean> getProductDetail(String pid){
        return deatiService.getProductDetail(pid );
    }
}
