package com.bwie.test.myelectronicmall.loginandregist.contract;

import com.bwie.test.bean.UserBean;
import com.bwie.test.myelectronicmall.base.BaseContract;

public interface LoginContract {
    interface View extends BaseContract.BaseView {
        void loginSuccess(UserBean userBean);
//        void getLoginSuccessByQQ(UserBean userBean, String ni_cheng, String iconurl);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void login(String mobile, String password);
//        void onSuccessByQQ( UserBean userBean, String ni_cheng, String iconurl);
    }
}
