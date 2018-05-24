package com.bwie.test.myelectronicmall;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.test.myelectronicmall.base.BaseActivity;
import com.bwie.test.myelectronicmall.classify.ClassifyFragment;
import com.bwie.test.myelectronicmall.find.FindFragment;
import com.bwie.test.myelectronicmall.home.HomePageFragment;
import com.bwie.test.myelectronicmall.mine.MyFragment;
import com.bwie.test.myelectronicmall.shopcart.ShopFragment;

public class MallActivity extends BaseActivity {
    //创建5个Fragment
    private FrameLayout mFlContent;
    private HomePageFragment homePageFragment;
    private ClassifyFragment classFragment;
    private FindFragment findFragment;
    private ShopFragment shopFragment;
    private MyFragment myFragment;
    private RadioGroup mRg;
    private FragmentManager fragmentManager;
    private RadioButton mRbHomepage;
    private RadioButton mRbClass;
    private RadioButton mRbFind;
    private RadioButton mRbShopCar;
    private RadioButton mRbMine;
    private int currentIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏原有标题
        getSupportActionBar().hide();
        initView();
        homePageFragment = new HomePageFragment();
        classFragment = new ClassifyFragment();
        shopFragment=new ShopFragment();
        findFragment=new FindFragment();
        myFragment=new MyFragment();
        fragmentManager = getSupportFragmentManager();
        //默认显示首页
        fragmentManager.beginTransaction().replace(R.id.flContent, homePageFragment).commit();
        mRbHomepage.setChecked(true);
        //设置点击事件
        setListener();
    }

    private void setListener() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbHomepage:
                        //首页
                        if (currentIndex == 1) {
                            return;
                        }
                        currentIndex = 1;
                        fragmentManager.beginTransaction().replace(R.id.flContent, homePageFragment).commit();
                        break;
                    case R.id.rbClass:
                        //分类
                        if (currentIndex == 2) {
                            return;
                        }
                        currentIndex = 2;
                        fragmentManager.beginTransaction().replace(R.id.flContent, classFragment).commit();
                        break;
                    case R.id.rbFind:
                        if (currentIndex == 3) {
                            return;
                        }
                        currentIndex = 3;
                        fragmentManager.beginTransaction().replace(R.id.flContent, findFragment).commit();
                        break;
                    case R.id.rbShopCar:
                        if (currentIndex == 4) {
                            return;
                        }
                        currentIndex = 4;
                        fragmentManager.beginTransaction().replace(R.id.flContent, shopFragment).commit();
                        break;
                    case R.id.rbMine:
                        if (currentIndex == 5) {
                            return;
                        }
                        currentIndex = 5;
                        fragmentManager.beginTransaction().replace(R.id.flContent, myFragment).commit();
                        break;
                }
            }
        });
    }

    private void initView() {
        mRg = (RadioGroup) findViewById(R.id.rg);
        mFlContent = (FrameLayout) findViewById(R.id.flContent);
        mRbHomepage = (RadioButton) findViewById(R.id.rbHomepage);
        mRbClass = (RadioButton) findViewById(R.id.rbClass);
        mRbFind = (RadioButton) findViewById(R.id.rbFind);
        mRbShopCar = (RadioButton) findViewById(R.id.rbShopCar);
        mRbMine = (RadioButton) findViewById(R.id.rbMine);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_mall;
    }

    @Override
    public void inject() {

    }
}
