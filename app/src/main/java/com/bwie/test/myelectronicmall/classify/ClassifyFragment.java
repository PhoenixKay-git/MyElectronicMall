package com.bwie.test.myelectronicmall.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.bwie.test.bean.CatagoryBean;
import com.bwie.test.bean.ProductCatagoryBean;
import com.bwie.test.component.DaggerHttpComponent;
import com.bwie.test.inter.OnItemClickListener;
import com.bwie.test.module.HttpModule;
import com.bwie.test.myelectronicmall.R;
import com.bwie.test.myelectronicmall.WebViewActivity;
import com.bwie.test.myelectronicmall.base.BaseFragment;
import com.bwie.test.myelectronicmall.classify.adapter.ElvAdapter;
import com.bwie.test.myelectronicmall.classify.adapter.RvLeftAdapter;
import com.bwie.test.myelectronicmall.classify.contract.ClassifyContract;
import com.bwie.test.myelectronicmall.classify.presenter.ClassifyPresenter;
import com.dash.zxinglibrary.activity.CaptureActivity;
import com.dash.zxinglibrary.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements ClassifyContract.View, View.OnClickListener{
    private RecyclerView mRvLeft;
    private ImageView mIv;
    private ExpandableListView mElv;
    private ImageView iivZxing;
    private ImageView imessage;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_class;
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
        mRvLeft = (RecyclerView) view.findViewById(R.id.rvLeft);
        mIv = (ImageView) view.findViewById(R.id.iv);
        mIv.setBackgroundResource(R.drawable.timg);
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mElv.setGroupIndicator(null);
        iivZxing=(ImageView)view.findViewById( R.id.ivZxing );
        imessage=(ImageView)view.findViewById( R.id.message );
        setListener();
        //请求数据
        initData();
    }

    private void setListener() {
        iivZxing.setOnClickListener(this);
        imessage.setOnClickListener(this);
    }

    private void initData() {
        mPresenter.getCatagory();
    }

    @Override
    public void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean) {
        List<String> groupList = new ArrayList<>();
        List<List<ProductCatagoryBean.DataBean.ListBean>> childList = new ArrayList<>();
        List<ProductCatagoryBean.DataBean> data = productCatagoryBean.getData();
        for (int i = 0; i < data.size(); i++) {
            groupList.add(data.get(i).getName());
            List<ProductCatagoryBean.DataBean.ListBean> list = data.get(i).getList();
            childList.add(list);
        }
        ElvAdapter elvAdapter = new ElvAdapter(getContext(), groupList, childList);
        mElv.setAdapter(elvAdapter);
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
    }

    @Override
    public void getCatagorySuccess(CatagoryBean catagoryBean) {
        final List<CatagoryBean.DataBean> data = catagoryBean.getData();
        mRvLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvLeft.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        final RvLeftAdapter adapter = new RvLeftAdapter(getContext(), data);
        mRvLeft.setAdapter(adapter);
        mPresenter.getProductCatagory(data.get(0).getCid()+"");
        adapter.changeCheck(0, true);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                adapter.changeCheck(position, true);
                mPresenter.getProductCatagory(data.get(position).getCid() + "");
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
            if (bundle.getInt( CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String string = bundle.getString(CodeUtils.RESULT_STRING);
                //拿到最终结果
                //Intent intent = new Intent(getContext(),WebViewActivity.class);
            }
        }
    }
}
