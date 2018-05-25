package com.bwie.test.myelectronicmall.home;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.test.bean.AdBean;
import com.bwie.test.bean.CatagoryBean;
import com.bwie.test.component.DaggerHttpComponent;
import com.bwie.test.inter.OnItemClickListener;
import com.bwie.test.module.HttpModule;
import com.bwie.test.myelectronicmall.R;
import com.bwie.test.myelectronicmall.WebViewActivity;
import com.bwie.test.myelectronicmall.base.BaseFragment;
import com.bwie.test.myelectronicmall.home.adapter.RvClassAdapter;
import com.bwie.test.myelectronicmall.home.adapter.RvRecommendAdapter;
import com.bwie.test.myelectronicmall.home.adapter.RvSecKillAdapter;
import com.bwie.test.myelectronicmall.home.contract.HomPageContract;
import com.bwie.test.myelectronicmall.home.presenter.HomePagePresenter;
import com.bwie.test.utils.GlideImageLoader;
import com.dash.zxinglibrary.activity.CaptureActivity;
import com.dash.zxinglibrary.activity.CodeUtils;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends BaseFragment<HomePagePresenter> implements HomPageContract.View, View.OnClickListener{
    private Banner banner;
    private RecyclerView rvClass;
    private MarqueeView marqueeView;
    private RecyclerView rvSecKill;
    private RecyclerView rvRecommend;
    private ImageView ivZxing;
    private ImageView message;
    private int num=1;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        marqueeView = view.findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("不管在贴吧的哪个角落，海吧是你坚强的后盾");
        info.add("只要有树叶飞舞的地方，火就会燃烧。");
        info.add("我若要有，天不可无。我若要无，天不可有。");
        info.add("我命如妖  欲  封  天。");
        info.add("奇迹不是乞求来的，而是靠我们努力创造的");
        info.add("迪妮莎 :被紧紧抱住的人,原来是我。");
        marqueeView.startWithList(info);

        banner = (Banner) view.findViewById(R.id.banner);
        rvClass = view.findViewById(R.id.rvClass);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        rvClass.setLayoutManager(gridLayoutManager);

        rvSecKill = view.findViewById(R.id.rvSecKill);
        //设置布局管理器
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false);
        rvSecKill.setLayoutManager(gridLayoutManager1);

        //设置布局管理器
        rvRecommend = view.findViewById(R.id.rvRecommend);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        rvRecommend.setLayoutManager(gridLayoutManager2);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //二维码
        ivZxing = view.findViewById(R.id.ivZxing);
        //消息
        message=view.findViewById(R.id.message);
        ivZxing.setOnClickListener(this);
        message.setOnClickListener(this);
        //请求数据
        initData();
    }

    private void initData() {
        mPresenter.getAd();
        mPresenter.getCatagory();
    }

    @Override
    public void getAdSuccess(final AdBean adBean) {
        final List<AdBean.DataBean> data = adBean.getData();
        List<String> images = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            images.add(data.get(i).getIcon());
        }
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        //给Banner设置点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String url = data.get(position).getUrl();
                if (!TextUtils.isEmpty(url)) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("detailUrl", url);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(),"没有数据哟，亲！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        RvSecKillAdapter rvSecKillAdapter = new RvSecKillAdapter(getActivity(), adBean.getMiaosha().getList());
        rvSecKill.setAdapter(rvSecKillAdapter);
        rvSecKillAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转显示详情
                //获取地址
                String detailUrl = adBean.getMiaosha().getList().get(position).getDetailUrl();
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("detailUrl", detailUrl);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });

        final List<AdBean.TuijianBean.ListBean> list = adBean.getTuijian().getList();
        RvRecommendAdapter rvRecommendAdapter = new RvRecommendAdapter(getContext(), list);
        rvRecommend.setAdapter(rvRecommendAdapter);
        rvRecommendAdapter.setOnListItemClickListener( new RvRecommendAdapter.OnListItemClickListener() {
            @Override
            public void OnItemClick(AdBean.TuijianBean.ListBean listBean) {
                Intent intent=new Intent(getActivity(),DetailActivity.class);
                intent.putExtra( "1","1" );
                intent.putExtra("listBean",listBean );
                startActivity(intent);
            }
        } );
//        rvRecommendAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                Intent intent=new Intent(getActivity(),DetailActivity.class);
//                intent.putExtra( "1","1" );
//                intent.putExtra("pid",list.get(position).getPid());
//                startActivity(intent);
//            }
//
//            @Override
//            public void onLongItemClick(int position) {
//
//            }
//        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void getCatagorySuccess(CatagoryBean catagoryBean) {
        final List<CatagoryBean.DataBean> data = catagoryBean.getData();
        RvClassAdapter adapter = new RvClassAdapter(getActivity(),data);
        rvClass.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), data.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivZxing:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.message:
                Intent intent2 = new Intent(getContext(), WebViewActivity.class);
                String detailUrl="https://www.jd.com/intro/about.aspx";
                intent2.putExtra("detailUrl", detailUrl);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String string = bundle.getString(CodeUtils.RESULT_STRING);
                //拿到最终结果
                //Intent intent = new Intent(getContext(),WebViewActivity.class);
            }
        }
    }
}
