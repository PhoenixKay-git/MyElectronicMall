package com.bwie.test.myelectronicmall.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.bwie.test.bean.ProductsBean;
import com.bwie.test.component.DaggerHttpComponent;
import com.bwie.test.module.HttpModule;
import com.bwie.test.myelectronicmall.R;
import com.bwie.test.myelectronicmall.base.BaseActivity;
import com.bwie.test.myelectronicmall.classify.adapter.XrvListAdapter;
import com.bwie.test.myelectronicmall.classify.contract.ListContract;
import com.bwie.test.myelectronicmall.classify.presenter.ListPresenter;
import com.bwie.test.myelectronicmall.home.DetailActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListsActivity extends BaseActivity<ListPresenter> implements ListContract.View {
    @BindView(R.id.ivZxing)
    ImageView mIvZxing;
    @BindView(R.id.xrv)
    XRecyclerView mXrv;
    private int pscid;
    private boolean isRefresh = true;
    private XrvListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        //隐藏原有标题
        getSupportActionBar().hide();
        //获取pscid
        Intent intent = getIntent();
        pscid = intent.getIntExtra("pscid", 0);
        initView();
        mPresenter.getProducts(pscid + "");
    }

    private void initView(){
        mXrv = (XRecyclerView) findViewById(R.id.xrv);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mXrv.setLayoutManager(linearLayoutManager);
        //设置刷新和加载更多监听
        mXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //刷新
                isRefresh = true;
                mPresenter.getProducts(pscid + "");
            }

            @Override
            public void onLoadMore() {
                //加载更多
                isRefresh = false;
                mPresenter.getProducts(pscid + "");
            }
        });
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_lists;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void onSuccess(List<ProductsBean.DataBean> list) {
        List<ProductsBean.DataBean> tempList = new ArrayList<>();
        tempList.addAll(list);
        //创建适配器
        if(isRefresh){
            adapter = new XrvListAdapter(this, list);
            mXrv.setAdapter( adapter );
            adapter.refresh(tempList);
            mXrv.refreshComplete();//设置刷新完成
        } else {
            if (adapter != null){
                //判断适配器是否创建过
                adapter.loadMore(tempList);
                mXrv.loadMoreComplete();
            }
        }
        if (adapter == null){
            return;
        }
        adapter.setOnListItemClickListener(new XrvListAdapter.OnListItemClickListener() {
            @Override
            public void OnItemClick(ProductsBean.DataBean dataBean) {
                Intent intent = new Intent( ListsActivity.this, DetailActivity.class );
                intent.putExtra( "1","2" );
                intent.putExtra("bean",dataBean);
                startActivity(intent);
            }
        });
    }
}
