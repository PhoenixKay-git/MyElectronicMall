package com.bwie.test.myelectronicmall.loginandregist.presenter;

import com.bwie.test.bean.RegisterBean;
import com.bwie.test.myelectronicmall.base.BasePresenter;
import com.bwie.test.myelectronicmall.loginandregist.contract.RegisterContract;
import com.bwie.test.net.RegisterApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {
    private RegisterApi registerApi;

    @Inject
    public RegisterPresenter(RegisterApi registerApi) {
        this.registerApi = registerApi;
    }

    @Override
    public void register(String mobile, String password) {
        registerApi.register(mobile, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterBean registerBean) {
                        mView.registerSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
