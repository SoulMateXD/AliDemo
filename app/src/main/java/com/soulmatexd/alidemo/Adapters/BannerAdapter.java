package com.soulmatexd.alidemo.Adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.soulmatexd.alidemo.R;
import com.soulmatexd.alidemo.SubAdapter;

//由于这个子View 是个Banner，所以在SubAdapter的基础里要使用PagerAdapter
public class BannerAdapter extends SubAdapter {
    RecyclerView.RecycledViewPool pool;

    public BannerAdapter(Context context, LayoutHelper layoutHelper, int total, RecyclerView.RecycledViewPool pool) {
        super(context, layoutHelper, total);
        this.pool = pool;
    }

    @Override
    public void onViewRecycled(MyViewHolder holder) {
        if (holder.itemView instanceof ViewPager) {
            ((ViewPager) holder.itemView).setAdapter(null);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1)
            return new MyViewHolder(
                    LayoutInflater.from(super.context).inflate(R.layout.view_pager, parent, false));

        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    protected void onBindViewHolderWithOffset(MyViewHolder holder, int position, int offsetTotal) {

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (holder.itemView instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) holder.itemView;

            viewPager.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200));

            // from position to get adapter
            viewPager.setAdapter(new PagerAdapter(this, pool));
        }
    }
}

