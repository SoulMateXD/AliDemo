package com.soulmatexd.alidemo.Adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.soulmatexd.alidemo.SubAdapter;

public class LinearAdapter extends SubAdapter {
    public LinearAdapter(Context context, LayoutHelper layoutHelper, int total) {
        super(context, layoutHelper, total);
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (position % 2 == 0) {
            VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
            holder.itemView.setLayoutParams(layoutParams);
        }
    }
}
