package com.bwie.test.myelectronicmall.classify.presenter;

import com.bwie.test.bean.ProductsBean;
import com.bwie.test.myelectronicmall.base.BasePresenter;
import com.bwie.test.myelectronicmall.classify.contract.ListContract;
import com.bwie.test.net.ListApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ListPresenter extends BasePresenter<ListContract.View> implements ListContract.Presenter {
    private ListApi listApi;

    @Inject
    public ListPresenter(ListApi listApi) {
        this.listApi = listApi;
    }

    @Override
    public void getProducts(String pscid) {
        listApi.getProducts(pscid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ProductsBean, List<ProductsBean.DataBean>>() {
                    @Override
                    public List<ProductsBean.DataBean> apply(ProductsBean productsBean) throws Exception {
                        return productsBean.getData();
                    }
                }).subscribe(new Consumer<List<ProductsBean.DataBean>>() {
            @Override
            public void accept(List<ProductsBean.DataBean> dataBeans) throws Exception {
                if (mView != null) {
                    mView.onSuccess(dataBeans);
                }
            }
        });
    }
}
