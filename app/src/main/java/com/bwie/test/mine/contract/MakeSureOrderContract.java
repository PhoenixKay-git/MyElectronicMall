package com.bwie.test.mine.contract;

import com.bwie.test.bean.AddrsBean;
import com.bwie.test.myelectronicmall.base.BaseContract;

import java.util.List;

public interface MakeSureOrderContract {
    interface View extends BaseContract.BaseView {
        void addressSuccess(List<AddrsBean.DataBean> list);
        void updateCartsSuccess(String msg);
        void deleteCartSuccess(String msg);
        void createOderSuccess(String s);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAddrsess(String uid, String token);

        void createOrder(String uid, String price, String token);

        void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token);

        void deleteCart(String uid, String pid, String token);
    }
}
