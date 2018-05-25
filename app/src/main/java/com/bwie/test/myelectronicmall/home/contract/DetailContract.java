package com.bwie.test.myelectronicmall.home.contract;

import com.bwie.test.bean.DeatilBean;
import com.bwie.test.myelectronicmall.base.BaseContract;

public interface DetailContract {
    interface View extends BaseContract.BaseView {
        void getProductDetail(DeatilBean deatilBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getProductDetail(String pid);
    }
}
