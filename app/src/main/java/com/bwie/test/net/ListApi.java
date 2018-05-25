package com.bwie.test.net;

import com.bwie.test.bean.ProductsBean;

import io.reactivex.Observable;

public class ListApi {
    private static ListApi listApi;
    private ListService listService;

    private ListApi(ListService listService)
    {
        this.listService=listService;
    }

    public static ListApi getListApi(ListService listService){
        if (listApi==null)
        {
            listApi=new ListApi(listService);
        }
        return listApi;
    }
    public Observable<ProductsBean> getProducts(String pscid){
        return listService.getProducts( pscid );
    }
}
