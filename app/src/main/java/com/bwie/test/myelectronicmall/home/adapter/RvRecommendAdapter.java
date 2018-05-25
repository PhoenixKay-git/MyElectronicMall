package com.bwie.test.myelectronicmall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.test.bean.AdBean;
import com.bwie.test.myelectronicmall.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class RvRecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<AdBean.TuijianBean.ListBean> list;
    private LayoutInflater inflater;
    private OnListItemClickListener onListItemClickListener;

    public interface OnListItemClickListener {
        void OnItemClick(AdBean.TuijianBean.ListBean listBean);
    }

    public RvRecommendAdapter(Context context, List<AdBean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rvrecommend_item, parent, false);
        RecommendViewHolder recommendViewHolder = new RecommendViewHolder(view);
        return recommendViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
        final AdBean.TuijianBean.ListBean listBean = list.get(position);
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        String url = split.length == 0 ? images : split[0];
        recommendViewHolder.iv.setImageURI(url);
        recommendViewHolder.tv.setText(listBean.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onListItemClickListener.OnItemClick(listBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder{
        private final SimpleDraweeView iv;
        private final TextView tv;

        public RecommendViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
