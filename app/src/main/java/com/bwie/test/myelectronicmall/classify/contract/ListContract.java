package com.bwie.test.myelectronicmall.classify.contract;

import com.bwie.test.bean.ProductsBean;
import com.bwie.test.myelectronicmall.base.BaseContract;

import java.util.List;

public interface ListContract {
    interface View extends BaseContract.BaseView {
        void onSuccess(List<ProductsBean.DataBean> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getProducts(String pscid);
    }
}
