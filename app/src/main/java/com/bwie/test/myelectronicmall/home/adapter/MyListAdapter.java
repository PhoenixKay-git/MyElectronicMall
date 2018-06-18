package com.bwie.test.myelectronicmall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.test.bean.User;
import com.bwie.test.myelectronicmall.R;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListHolder>{
    private Context context;
    private List<User.DataBean> list;

    public MyListAdapter(Context context, List<User.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.mylist_item,null);
        //根据展示的条目的视图  创建viewHolder
        MyListHolder mylistHolder = new MyListHolder(view);
        return mylistHolder;
    }

    @Override
    public void onBindViewHolder(MyListHolder holder, int position) {
        //设置文本
        holder.textView.setText(list.get(position).getName().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
