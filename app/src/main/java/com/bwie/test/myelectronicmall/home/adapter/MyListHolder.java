package com.bwie.test.myelectronicmall.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwie.test.myelectronicmall.R;

public class MyListHolder extends RecyclerView.ViewHolder{
    public TextView textView;

    public MyListHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.item4);
    }
}
