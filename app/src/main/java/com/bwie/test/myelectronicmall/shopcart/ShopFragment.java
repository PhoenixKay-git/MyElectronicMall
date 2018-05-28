package com.bwie.test.myelectronicmall.shopcart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwie.test.bean.GetCartsBean;
import com.bwie.test.bean.SellerBean;
import com.bwie.test.bean.eventbus.MessageEvent;
import com.bwie.test.component.DaggerHttpComponent;
import com.bwie.test.mine.MakeSureOrderActivity;
import com.bwie.test.myelectronicmall.R;
import com.bwie.test.myelectronicmall.base.BaseFragment;
import com.bwie.test.myelectronicmall.classify.adapter.ElvShopcartAdapter;
import com.bwie.test.shop.contract.ShopcartContract;
import com.bwie.test.shop.presenter.ShopcartPresenter;
import com.bwie.test.utils.DialogUtil;
import com.bwie.test.utils.SharedPreferencesUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ShopFragment extends BaseFragment<ShopcartPresenter> implements ShopcartContract.View{
    private ExpandableListView mElv;
    /**
     * 全选
     */
    private CheckBox mCbAll;
    /**
     * 合计：
     */
    private TextView mTvMoney;
    /**
     * 去结算：
     */
    private TextView mTvTotal;
    private ProgressDialog progressDialog;
    private ElvShopcartAdapter adapter;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_shopcart;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        //初始化dialog
        progressDialog = DialogUtil.getProgressDialog(getActivity());
        String token = (String) SharedPreferencesUtils.getParam(getActivity(), "token", "");
        String uid = (String) SharedPreferencesUtils.getParam(getActivity(), "uid", "");
        mPresenter.getCarts(uid,token);

        mElv = (ExpandableListView) view.findViewById( R.id.elv );
        mCbAll = (CheckBox) view.findViewById( R.id.cbAll );
        mTvMoney = (TextView) view.findViewById( R.id.tvMoney );
        mTvTotal = (TextView) view.findViewById( R.id.tvTotal );
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter != null) {
                    progressDialog.show();
                    adapter.changeAllState(mCbAll.isChecked());
                }
            }
        });
        mTvTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MakeSureOrderActivity.class);
                startActivity(intent);
                //把用户选中的商品传过去
                List<SellerBean> gList = adapter.getGroupList();
                List<List<GetCartsBean.DataBean.ListBean>> cList = adapter.getchildList();
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setcList(cList);
                messageEvent.setgList(gList);
                EventBus.getDefault().postSticky(messageEvent);
            }
        });
    }

    @Override
    public void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList) {
        //判断所有商家是否全部选中
        mCbAll.setChecked(isSellerAddSelected(groupList));
        //创建适配器
        adapter = new ElvShopcartAdapter(getActivity(), groupList, childList, mPresenter,progressDialog);
        mElv.setAdapter(adapter);
        //获取数量和总价
        String[] strings = adapter.computeMoneyAndNum();
        mTvMoney.setText("总计：" + strings[0] + "元");
        mTvTotal.setText("去结算("+strings[1]+"个)");
        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }
        //关闭进度条
        progressDialog.dismiss();
    }

    @Override
    public void updateCartsSuccess(String msg) {
        if (adapter != null){
            adapter.updataSuccess();
        }
    }

    @Override
    public void deleteCartSuccess(String msg) {
        //调用适配器里的delSuccess方法
        if (adapter != null){
            adapter.delSuccess();
        }
    }

    /**
     * 判断所有商家是否全选选中
     * @param groupList
     * @return
     */
    private boolean isSellerAddSelected(List<SellerBean> groupList) {
        for (int i = 0; i < groupList.size(); i++) {
            SellerBean sellerBean = groupList.get(i);
            if (!sellerBean.isSelected()) {
                return false;
            }
        }
        return true;
    }
}
