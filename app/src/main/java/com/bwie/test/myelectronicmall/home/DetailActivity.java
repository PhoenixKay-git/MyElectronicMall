package com.bwie.test.myelectronicmall.home;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.test.bean.AdBean;
import com.bwie.test.bean.ProductsBean;
import com.bwie.test.myelectronicmall.ImageScaleActivity;
import com.bwie.test.myelectronicmall.R;
import com.bwie.test.myelectronicmall.base.BaseActivity;
import com.bwie.test.myelectronicmall.classify.contract.AddCartContract;
import com.bwie.test.myelectronicmall.classify.presenter.AddCartPresenter;
import com.bwie.test.myelectronicmall.loginandregist.LoginActivity;
import com.bwie.test.shop.ShopCartActivity;
import com.bwie.test.utils.GlideImageLoader;
import com.bwie.test.utils.SharedPreferencesUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity<AddCartPresenter> implements AddCartContract.View, View.OnClickListener {
    @BindView(R.id.detail_image_back)
    ImageView mDetailImageBack;
    @BindView(R.id.detail_share)
    ImageView mDetailShare;
    @BindView(R.id.detai_relative)
    RelativeLayout mDetaiRelative;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.detail_title)
    TextView mDetailTitle;
    @BindView(R.id.detail_yuan_price)
    TextView mDetailYuanPrice;
    @BindView(R.id.detail_bargin_price)
    TextView mDetailBarginPrice;
    @BindView(R.id.watch_cart)
    TextView mWatchCart;
    @BindView(R.id.detai_add_cart)
    TextView mDetaiAddCart;
    private int pid;
    private ProductsBean.DataBean bean;
    private AdBean.TuijianBean.ListBean listBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //隐藏原有标题
        getSupportActionBar().hide();
        //接收传递的pid
        if("1".equals(getIntent().getStringExtra( "1" ) )){
//            pid = getIntent().getIntExtra("pid", -1);
            Intent intent = getIntent();
            listBean = (AdBean.TuijianBean.ListBean) intent.getSerializableExtra("listBean");
            initBanner();
            setData();

//            //如果不是默认值代表传递过来数据了
//            if (pid != -1){
//                //拿着传递的pid请求商品详情的接口,然后展示数据...MVP
//                mPresenter.getProductDetail( String.valueOf( pid ) );
//            }
        }
        else if("2".equals(getIntent().getStringExtra( "1" ) )){
            //获取JavaBean
            Intent intent = getIntent();
            bean = (ProductsBean.DataBean) intent.getSerializableExtra("bean");
            initBanner();
        }
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_detail;
    }

    @Override
    public void inject() {

    }

    private void setData() {
        mDetailYuanPrice.getPaint().setFlags( Paint.STRIKE_THRU_TEXT_FLAG);
        //设置数据显示
        mDetailTitle.setText(listBean.getTitle());
        mDetailBarginPrice.setText("优惠价:"+listBean.getBargainPrice());
        mDetailYuanPrice.setText("原价:"+listBean.getPrice());
        String[] strings = listBean.getImages().split("\\|");
        final ArrayList<String> imageUrls = new ArrayList<>();
        for (int i = 0;i<strings.length;i++){
            imageUrls.add(strings[i]);
        }
        //获取JavaBean
        mBanner.setImages(imageUrls);
        mBanner.start();
        //bannner点击事件进行跳转
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(DetailActivity.this,ImageScaleActivity.class);
                //传递的数据...整个轮播图数据的集合需要传递,,,当前展示的图片的位置需要传递postion
                //intent传递可以传的数据...基本数据类型...引用数据类型(必须序列化,所有的类,包括内部类实现serilizable接口)...bundle
                intent.putStringArrayListExtra("imageUrls",imageUrls);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    //banner样式展示
    private void initBanner() {
        //设置banner样式...CIRCLE_INDICATOR_TITLE包含标题
        mBanner.setBannerStyle( BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);

        if("2".equals(getIntent().getStringExtra( "1" ) )){
            //添加删除线
            mDetailYuanPrice.getPaint().setFlags( Paint.STRIKE_THRU_TEXT_FLAG);
            //设置数据显示
            mDetailTitle.setText(bean.getTitle());
            mDetailBarginPrice.setText("优惠价:"+bean.getBargainPrice());
            mDetailYuanPrice.setText("原价:"+bean.getPrice());
            String[] strings = bean.getImages().split("\\|");
            final ArrayList<String> imageUrls = new ArrayList<>();
            for (int i = 0;i<strings.length;i++){
                imageUrls.add(strings[i]);
            }
            //获取JavaBean
            mBanner.setImages(imageUrls);
            mBanner.start();
            //bannner点击事件进行跳转
            mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(DetailActivity.this,ImageScaleActivity.class);
                    intent.putStringArrayListExtra("imageUrls",imageUrls);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            });
        }
    }

    @OnClick({R.id.detail_image_back, R.id.detail_share, R.id.watch_cart, R.id.detai_add_cart})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.detail_image_back:
                finish();
                break;
          /**  case R.id.detail_share:
//                DetailActivity.this.deatilBean=deatilBean;
//                if("1".equals(getIntent().getStringExtra( "1" ) )){
                if (listBean != null) {
                    //final Activity activity 分享的activity的上下文,
                    // String WebUrl 分享的商品的链接,
                    // String title 分享的商品的标题,
                    // String description 商品的描述,
                    // String imageUrl 商品的图片...如果没有图片传"",
                    // int imageID 本地商品的图片
                    Toast.makeText( this, "点击了", Toast.LENGTH_SHORT ).show();
//                        DeatilBean.DataBean data = deatilBean.getData();
                    ShareUtil.shareWeb(DetailActivity.this,listBean.getDetailUrl(),listBean.getTitle(),"我在京东发现一个好的商品,赶紧来看看吧!",listBean.getImages().split("\\|")[0],R.mipmap.ic_launcher);
                }else{
                    Toast.makeText( this, "点击了", Toast.LENGTH_SHORT ).show();
                    if (bean != null) {
                        ShareUtil.shareWeb(DetailActivity.this,bean.getDetailUrl(),bean.getTitle(),"我在京东发现一个好的商品,赶紧来看看吧!",bean.getImages().split("\\|")[0],R.mipmap.ic_launcher);
                    }
                }
                break;*/
            case R.id.watch_cart:
                shopCart();
                break;
            case R.id.detai_add_cart:
                //先判断是否登录
                String  token=(String) SharedPreferencesUtils.getParam( DetailActivity.this,"token","" );
                if (TextUtils.isEmpty( token )) {
                    //还未登录
                    //跳转到登录页面
                    Intent intent = new Intent(DetailActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    //登录过了
                    String  uid=(String) SharedPreferencesUtils.getParam( DetailActivity.this,"uid","" );
                    if("1".equals(getIntent().getStringExtra( "1" ) )){
                        int pid1 = listBean.getPid();
                        mPresenter.addCart(uid,pid1+"",token);
                    }
                    else if ("2".equals(getIntent().getStringExtra( "1" ) )) {
                        int pid=bean.getPid();
                        mPresenter.addCart(uid,pid+"",token);
                    }
                }
                break;
        }
    }

    public void shopCart(){
        //跳转到购物车
        Intent intent = new Intent(DetailActivity.this, ShopCartActivity.class);
        startActivity(intent);
    }

    /**
    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            Toast.makeText(DetailActivity.this,"分享开始",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(DetailActivity.this,"分享成功",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Log.e("----",throwable.getMessage());
            Toast.makeText(DetailActivity.this,"分享失败",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(DetailActivity.this,"分享取消",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }*/

    @Override
    public void onSuccess(String str) {
        Toast.makeText(DetailActivity.this, str, Toast.LENGTH_LONG).show();
    }
}
