package com.soulmatexd.alidemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;


//所有的Adapter方便管理，写了一个父类
public class SubAdapter extends DelegateAdapter.Adapter<SubAdapter.MyViewHolder>{

    public Context context;

    public LayoutHelper layoutHelper;

    //这里是模拟的，自建了一个View，高全为300
    public ViewGroup.LayoutParams layoutParams;

    public int total;

    public SubAdapter(Context context, LayoutHelper layoutHelper, int total) {
        this(context, layoutHelper, total, new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 300));
    }

    public SubAdapter(Context context, LayoutHelper layoutHelper, int total, RecyclerView.LayoutParams layoutParams) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.layoutParams = layoutParams;
        this.total = total;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(layoutParams));
    }

    @Override
    protected void onBindViewHolderWithOffset(MyViewHolder holder, int position, int offsetTotal) {
        holder.textView.setText(Integer.toString(offsetTotal));
    }

    @Override
    public int getItemCount() {
        return total;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.title);
        }
    }


}
