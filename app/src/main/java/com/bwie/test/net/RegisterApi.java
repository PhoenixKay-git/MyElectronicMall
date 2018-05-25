package com.bwie.test.net;

import com.bwie.test.bean.RegisterBean;

import io.reactivex.Observable;

public class RegisterApi {
    private static RegisterApi registerApi;
    private RegisterApiService registerApiService;
    public RegisterApi(RegisterApiService registerApiService) {
        this.registerApiService = registerApiService;
    }

    public static RegisterApi getRegisterApi(RegisterApiService registerApiService){
        if (registerApi==null){
            registerApi=new RegisterApi(registerApiService);
        }
        return registerApi;
    }
    public Observable<RegisterBean> register(String mobile, String password){
        return registerApiService.register(mobile,password);
    }
}
