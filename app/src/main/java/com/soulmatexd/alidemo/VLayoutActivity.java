package com.soulmatexd.alidemo;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.RangeGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.soulmatexd.alidemo.Adapters.BannerAdapter;
import com.soulmatexd.alidemo.Adapters.LinearAdapter;

import java.util.LinkedList;

public class VLayoutActivity extends AppCompatActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private Handler mHandler;

    private RecyclerView recyclerView;

    //子Adapter列表
    private LinkedList<DelegateAdapter.Adapter> adapters = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vlayout);

        mHandler = new Handler(Looper.getMainLooper());

        recyclerView = findViewById(R.id.main_view);

        //创建回收池，这里只对type为0的View进行回收池的设置，如果有多个type的View，应该每个都为其设置一个
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        //初始化负责管理Helper的layoutmanager
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //设置数据适配器
        final DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);


        //floating button  右下角， 改了下Sub里的大小参数
        FloatLayoutHelper layoutHelper = new FloatLayoutHelper();
        layoutHelper.setAlignType(FixLayoutHelper.BOTTOM_RIGHT);
        layoutHelper.setDefaultLocation(100, 400);
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(150, 150);
        adapters.add(new SubAdapter(this, layoutHelper, 1, layoutParams));

        //一个只有一行的Linear只是用来体验下layoutHelper不同属性的区别
        LinearLayoutHelper layoutHelper1 = new LinearLayoutHelper();
        layoutHelper1.setBgColor(Color.YELLOW);
        layoutHelper1.setAspectRatio(2.0f);
        layoutHelper1.setMargin(10, 10, 10, 10);
        layoutHelper1.setPadding(10, 10, 10, 10);

        adapters.add(new LinearAdapter(this, layoutHelper1, 1));

        //Linear
        LinearLayoutHelper layoutHelper2 = new LinearLayoutHelper();
        layoutHelper2.setAspectRatio(4.0f);
        layoutHelper2.setDividerHeight(10);
        layoutHelper2.setMargin(10, 0, 10, 10);
        layoutHelper2.setPadding(10, 0, 10, 10);
        layoutHelper2.setBgColor(0xFFF5A623);

        adapters.add(new LinearAdapter(this, layoutHelper2, 6));

        //banner
        adapters.add(
                new BannerAdapter(VLayoutActivity.this, new LinearLayoutHelper(),
                        1, viewPool));

        //两个GridView，分别设置其style
        RangeGridLayoutHelper gridLayoutHelper = new RangeGridLayoutHelper(2);
        //整体上GridViewLayout的属性设置，每行两个item的权重比例为 20：25
        gridLayoutHelper.setBgColor(Color.GREEN);
        gridLayoutHelper.setWeights(new float[]{20f, 25f});
        gridLayoutHelper.setPadding(15, 15, 15, 15);
        gridLayoutHelper.setMargin(15, 50, 15, 150);
        gridLayoutHelper.setHGap(10);
        gridLayoutHelper.setVGap(10);
        //第一个GridView内部设置
        RangeGridLayoutHelper.GridRangeStyle rangeStyle = new RangeGridLayoutHelper.GridRangeStyle();
        rangeStyle.setBgColor(Color.RED);
        rangeStyle.setSpanCount(2);
        rangeStyle.setWeights(new float[]{45f});
        rangeStyle.setPadding(15, 15, 15, 15);
        rangeStyle.setMargin(15, 15, 15, 15);
        rangeStyle.setHGap(5);
        rangeStyle.setVGap(5);
        gridLayoutHelper.addRangeStyle(0, 7, rangeStyle);
        //第二个GridView内部设置
        RangeGridLayoutHelper.GridRangeStyle rangeStyle1 = new RangeGridLayoutHelper.GridRangeStyle();
        rangeStyle1.setBgColor(Color.YELLOW);
        rangeStyle1.setSpanCount(2);
        rangeStyle1.setWeights(new float[]{45f});
        rangeStyle1.setPadding(15, 15, 15, 15);
        rangeStyle1.setMargin(15, 15, 15, 15);
        rangeStyle1.setHGap(5);
        rangeStyle1.setVGap(5);
        gridLayoutHelper.addRangeStyle(8, 13, rangeStyle1);
        //total： 0~13
        adapters.add(new SubAdapter(this, gridLayoutHelper, 14));

        //每行3个，一共4个，GridLayout有容错机制
        final GridLayoutHelper helper = new GridLayoutHelper(3, 4);
        helper.setBgColor(0xFF86345A);
        adapters.add(new SubAdapter(this, helper, 4) {
            @Override
            public void onBindViewHolder(MyViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                //必须用Recycler的
                RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300);
                holder.itemView.setLayoutParams(layoutParams);
            }
        });

        //一拖一
        OnePlusNLayoutHelper onePlusNHelper = new OnePlusNLayoutHelper();
        onePlusNHelper.setBgColor(0xff876384);
        onePlusNHelper.setAspectRatio(4.0f);
        onePlusNHelper.setColWeights(new float[]{40f, 45f});
        onePlusNHelper.setMargin(10, 20, 10, 20);
        onePlusNHelper.setPadding(10, 10, 10, 10);
        adapters.add(new SubAdapter(this, onePlusNHelper, 2));

        //一拖三
        OnePlusNLayoutHelper onePlusNHelper1 = new OnePlusNLayoutHelper();
        onePlusNHelper1.setBgColor(0xffef8ba3);
        onePlusNHelper1.setAspectRatio(2.0f);
        onePlusNHelper1.setColWeights(new float[]{40f});
        onePlusNHelper1.setRowWeight(30f);
        onePlusNHelper1.setMargin(10, 20, 10, 20);
        onePlusNHelper1.setPadding(10, 10, 10, 10);
        adapters.add(new SubAdapter(this, onePlusNHelper1, 4) {
            @Override
            public void onBindViewHolder(MyViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
                if (position == 0) {
                    lp.rightMargin = 1;
                } else if (position == 1) {

                } else if (position == 2) {
                    lp.topMargin = 1;
                    lp.rightMargin = 1;
                }
            }
        });

        //瀑布流效果
        final StaggeredGridLayoutHelper staggeredGridHelper = new StaggeredGridLayoutHelper(2, 10);
        staggeredGridHelper.setMargin(20, 10, 10, 10);
        staggeredGridHelper.setPadding(10, 10, 20, 10);
        staggeredGridHelper.setBgColor(0xFF86345A);
        adapters.add(new SubAdapter(this, staggeredGridHelper, 44) {
            @Override
            public void onBindViewHolder(MyViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 200);
                if (position % 2 == 0) {
                    layoutParams.mAspectRatio = 1.0f;
                } else {
                    layoutParams.height = 340 + position % 7 * 20;
                }
                holder.itemView.setLayoutParams(layoutParams);
            }
        });


        delegateAdapter.setAdapters(adapters);


        recyclerView.setAdapter(delegateAdapter);

        mSwipeRefreshLayout = findViewById(R.id.swipe_container);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //刷新处理
                        delegateAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000L);
            }
        });

    }
}
