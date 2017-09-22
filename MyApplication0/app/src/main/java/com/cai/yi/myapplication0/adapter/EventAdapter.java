package com.cai.yi.myapplication0.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cai.yi.myapplication0.DetailActivity;
import com.cai.yi.myapplication0.MainActivity;
import com.cai.yi.myapplication0.R;
import com.cai.yi.myapplication0.bean.EventBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qf on 2017/9/14.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder>{

    private Context mContext;

    private List<EventBean.ResultBean> mData = new ArrayList<>();

    public EventAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_event, null));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final EventBean.ResultBean resultBean = mData.get(position);
        holder.mName.setText(resultBean.getTitle());
        Glide.with(mContext)
                .load(resultBean.getPic())
                .into(holder.mImg);
        holder.mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("id", resultBean.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        public  ImageView mImg;

        public  TextView mName;

        public View mConvertView;

        public MyViewHolder(View convertView) {
            super(convertView);

            mImg = (ImageView) convertView.findViewById(R.id.img_person);
            mName = (TextView) convertView.findViewById(R.id.tv_name);
            mConvertView = convertView;
        }
    }

    public void setData(List<EventBean.ResultBean> result) {
        mData.clear();
        mData.addAll(result);
        notifyDataSetChanged();
    }
}
