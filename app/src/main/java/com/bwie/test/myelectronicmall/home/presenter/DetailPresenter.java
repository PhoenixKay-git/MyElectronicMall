package com.bwie.test.myelectronicmall.home.presenter;

import com.bwie.test.bean.DeatilBean;
import com.bwie.test.myelectronicmall.base.BasePresenter;
import com.bwie.test.myelectronicmall.home.contract.DetailContract;
import com.bwie.test.net.DetailApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter extends BasePresenter<DetailContract.View> implements DetailContract.Presenter{
    DetailApi detailApi;

    @Inject
    public DetailPresenter(DetailApi detailApi) {
        this.detailApi = detailApi;
    }

    @Override
    public void getProductDetail(String pid) {
        detailApi.getProductDetail(pid)
                .observeOn( AndroidSchedulers.mainThread())
                .subscribeOn( Schedulers.io())
                .subscribe(new Observer<DeatilBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeatilBean deatilBean) {
                        mView.getProductDetail(deatilBean);
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
