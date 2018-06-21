package com.bwie.test.myelectronicmall.loginandregist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bwie.test.bean.RegisterBean;
import com.bwie.test.component.DaggerHttpComponent;
import com.bwie.test.module.HttpModule;
import com.bwie.test.myelectronicmall.R;
import com.bwie.test.myelectronicmall.base.BaseActivity;
import com.bwie.test.myelectronicmall.loginandregist.contract.RegisterContract;
import com.bwie.test.myelectronicmall.loginandregist.presenter.RegisterPresenter;

public class RegistActivity extends BaseActivity<RegisterPresenter> implements RegisterContract.View, View.OnClickListener {
    private Button mBt;
    private ImageView mChaIamge;
    private RelativeLayout mLoginTitleRelative;
    private EditText mMobile;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_regist;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    private void initView() {
        mChaIamge = (ImageView) findViewById(R.id.cha_iamge);
        mChaIamge.setOnClickListener(this);
        mLoginTitleRelative = (RelativeLayout) findViewById(R.id.login_title_relative);
        mMobile = (EditText) findViewById(R.id.mobile);
        mPassword = (EditText) findViewById(R.id.password);
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
    }

    @Override
    public void registerSuccess(RegisterBean registerBean) {
        String msg = registerBean.getCode();
        if (msg.equals("0")){
            Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
            startActivity(intent);
            //finish();
            Toast.makeText(RegistActivity.this, registerBean.getMsg(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(RegistActivity.this, registerBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cha_iamge:
                finish();
                break;
            case R.id.bt:
                String mobile = mMobile.getText().toString();
                String password = mPassword.getText().toString();
                mPresenter.register(mobile, password);
                break;
        }
    }
}
