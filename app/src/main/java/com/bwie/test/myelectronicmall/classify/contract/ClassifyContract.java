package com.bwie.test.myelectronicmall.classify.contract;

import com.bwie.test.bean.CatagoryBean;
import com.bwie.test.bean.ProductCatagoryBean;
import com.bwie.test.myelectronicmall.base.BaseContract;

public interface ClassifyContract {
    interface View extends BaseContract.BaseView{
        void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean);
        void getCatagorySuccess(CatagoryBean catagoryBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getProductCatagory(String cid);
        void getCatagory();
    }
}
