package com.bwie.test.myelectronicmall.classify.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.test.bean.ProductCatagoryBean;
import com.bwie.test.inter.OnItemClickListener;
import com.bwie.test.myelectronicmall.R;

import java.util.List;

public class ElvRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<ProductCatagoryBean.DataBean.ListBean> listBeans;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public ElvRvAdapter(Context context, List<ProductCatagoryBean.DataBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvclass_item, parent, false);
        ElvRvAdapterViewHolder elvRvAdapterViewHolder = new ElvRvAdapterViewHolder(view);
        return elvRvAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ElvRvAdapterViewHolder elvRvAdapterViewHolder = (ElvRvAdapterViewHolder) holder;
        ProductCatagoryBean.DataBean.ListBean listBean = listBeans.get(position);
        //Glide.with(context).load(listBean.getIcon()).into(elvRvAdapterViewHolder.iv);
        String url = listBean.getIcon();
        elvRvAdapterViewHolder.iv.setImageURI(Uri.parse(url));
        elvRvAdapterViewHolder.tv.setText(listBean.getName());

        //给条目设置点击事件
        elvRvAdapterViewHolder.line_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    class ElvRvAdapterViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv;
        private final TextView tv;
        private final LinearLayout line_layout;

        public ElvRvAdapterViewHolder(View itemView) {
            super(itemView);
            line_layout = itemView.findViewById(R.id.line_layout);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
