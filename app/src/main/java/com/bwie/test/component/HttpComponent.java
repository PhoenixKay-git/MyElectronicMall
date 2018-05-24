package com.bwie.test.component;

import com.bwie.test.module.HttpModule;
import com.bwie.test.myelectronicmall.classify.ClassifyFragment;
import com.bwie.test.myelectronicmall.find.FindFragment;
import com.bwie.test.myelectronicmall.home.HomePageFragment;
import com.bwie.test.myelectronicmall.shopcart.ShopFragment;

import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpComponent {
    //void inject(DetailActivity homePageFragment);
    void inject(HomePageFragment classifyFragment);
    void inject(ClassifyFragment detailActivity);
    void inject(ShopFragment shopFragment);
    void inject(FindFragment findFragment);
   // void inject(ListsActivity listsActivity);
   // void inject(LoginActivity2 loginActivity2);
   // void inject(RegistActivity2 registActivity2);
    //void inject(ShopCartActivity shopCartActivity);
    //void inject(MakeSureOrderActivity makeSureOrderActivity);
   // void inject(UserInfoActivity userInfoActivity);
}
