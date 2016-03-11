package com.example.administrator.jurgerybc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.jurgerybc.R;
import com.example.administrator.jurgerybc.beans.ContentBean;

/**
 * Created by Administrator on 2016/3/11.
 */
public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {
    private View itemView;
    private Context context;
    private ContentBean contentBean;

    public RvAdapter(Context context, ContentBean contentBean) {
        this.context = context;
        this.contentBean = contentBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(context).inflate(R.layout.item_newest, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_newest_title.setText(contentBean.getData().get(position).getPost_title());
        holder.tv_newest_excerpt.setText(contentBean.getData().get(position).getPost_excerpt());
    }

    @Override
    public int getItemCount() {
        return contentBean.getData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_newest_title;
        TextView tv_newest_excerpt;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_newest_title = (TextView) itemView.findViewById(R.id.tv_newest_title);
            tv_newest_excerpt = (TextView) itemView.findViewById(R.id.tv_newest_excerpt);
        }
    }
}
