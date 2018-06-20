package com.bwie.test.myelectronicmall.loginandregist.contract;

import com.bwie.test.bean.RegisterBean;
import com.bwie.test.myelectronicmall.base.BaseContract;

public interface RegisterContract {
    interface View extends BaseContract.BaseView{
        void registerSuccess(RegisterBean registerBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void register(String mobile, String password);
    }
}
