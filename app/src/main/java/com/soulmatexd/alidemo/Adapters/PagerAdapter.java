package com.soulmatexd.alidemo.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.soulmatexd.alidemo.R;
import com.soulmatexd.alidemo.SubAdapter;

public class PagerAdapter extends RecyclablePagerAdapter<SubAdapter.MyViewHolder> {
    public PagerAdapter(SubAdapter adapter, RecyclerView.RecycledViewPool pool) {
        super(adapter, pool);
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public void onBindViewHolder(SubAdapter.MyViewHolder viewHolder, int position) {
        // only vertical
        viewHolder.itemView.setLayoutParams(
                new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewHolder.textView.setText("Banner的第  " + position  + "  个");
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}