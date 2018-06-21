package com.bwie.test.myelectronicmall.loginandregist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.test.bean.UserBean;
import com.bwie.test.component.DaggerHttpComponent;
import com.bwie.test.module.HttpModule;
import com.bwie.test.myelectronicmall.MainActivity;
import com.bwie.test.myelectronicmall.R;
import com.bwie.test.myelectronicmall.base.BaseActivity;
import com.bwie.test.myelectronicmall.loginandregist.contract.LoginContract;
import com.bwie.test.myelectronicmall.loginandregist.presenter.LoginPresenter;
import com.bwie.test.utils.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnClickListener, LoginContract.View {
    @BindView(R.id.cha)
    ImageView mCha;
    @BindView(R.id.login_title_relative)
    RelativeLayout mLoginTitleRelative;
    @BindView(R.id.mobile)
    EditText mMobile;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.btLogin)
    Button mBtLogin;
    @BindView(R.id.text_regist)
    TextView mTextRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mMobile = (EditText) findViewById(R.id.mobile);
        mPassword = (EditText) findViewById(R.id.password);
        mBtLogin = (Button) findViewById(R.id.btLogin);
        mBtLogin.setOnClickListener(this);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        Toast.makeText(LoginActivity.this, userBean.getMsg(), Toast.LENGTH_SHORT).show();
        Toast.makeText(LoginActivity.this, userBean.getMsg(), Toast.LENGTH_SHORT).show();
        SharedPreferencesUtils.setParam(LoginActivity.this,"uid",userBean.getData().getUid() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"name",userBean.getData().getUsername() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"iconUrl",userBean.getData().getIcon() + "");
        SharedPreferencesUtils.setParam(LoginActivity.this,"token",userBean.getData().getToken() + "");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }

    @OnClick({R.id.cha,R.id.btLogin, R.id.text_regist})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cha:
                finish();
                break;
            case R.id.btLogin:
                //需要调用P层，去完成接口调用
                String mobile = mMobile.getText().toString();
                String password = mPassword.getText().toString();
                mPresenter.login(mobile, password);
                break;
            case R.id.text_regist:
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
