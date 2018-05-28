package com.bwie.test.component;

import com.bwie.test.mine.MakeSureOrderActivity;
import com.bwie.test.module.HttpModule;
import com.bwie.test.myelectronicmall.classify.ClassifyFragment;
import com.bwie.test.myelectronicmall.classify.ListsActivity;
import com.bwie.test.myelectronicmall.find.FindFragment;
import com.bwie.test.myelectronicmall.home.DetailActivity;
import com.bwie.test.myelectronicmall.home.HomePageFragment;
import com.bwie.test.myelectronicmall.shopcart.ShopFragment;
import com.bwie.test.shop.ShopCartActivity;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(DetailActivity detailActivity);
    void inject(HomePageFragment homePageFragment);
    void inject(ClassifyFragment classifyFragment);
    void inject(ShopFragment shopFragment);
    void inject(FindFragment findFragment);
    void inject(ListsActivity listsActivity);
   // void inject(LoginActivity loginActivity);
   // void inject(RegistActivity registActivity);
    void inject(ShopCartActivity shopCartActivity);
    void inject(MakeSureOrderActivity makeSureOrderActivity);
   // void inject(UserInfoActivity userInfoActivity);
}
