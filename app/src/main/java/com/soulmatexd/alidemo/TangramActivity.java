package com.soulmatexd.alidemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tmall.wireless.tangram.TangramBuilder;
import com.tmall.wireless.tangram.TangramEngine;
import com.tmall.wireless.tangram.util.IInnerImageSetter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TangramActivity extends AppCompatActivity {

    TangramEngine engine;
    TangramBuilder.InnerBuilder builder;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangram);

        recyclerView = findViewById(R.id.main_view);

        //初始化Tangram， 全局只用初始化一次，并配置图片加载器，我比较喜欢glide~
        TangramBuilder.init(this.getApplicationContext(), new IInnerImageSetter() {
            @Override
            public <IMAGE extends ImageView> void doLoadImageUrl(@NonNull IMAGE view,
                                                                 @Nullable String url) {
                Glide.with(TangramActivity.this.getApplicationContext()).load(url).into(view);
            }
        }, ImageView.class);

        //初始化builder
        builder = TangramBuilder.newInnerBuilder(this);
        //注册自定义的Cell
        builder.registerCell("testView", TestView.class);
        builder.registerCell("ratioTextView", RatioTextView.class);


        //获取实例
        engine = builder.build();

        //绑定业务
        //点击事件
        engine.addSimpleClickSupport(new ClickSupport());

        //懒汉模式
        engine.enableAutoLoadMore(true);

        //绑定recyclerView
        engine.bindView(recyclerView);

        //在 scroll 事件中触发 engine 的 onScroll，内部会触发需要异步加载的卡片去提前加载数据
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                engine.onScrolled();
            }
        });

        //由于没有接口。我的服务器方面的知识学的也不太好。所以直接加载本地数据，借用了官方的mock
        //具体的数据在assets文件夹下
        String json = new String(getAssertsFile(this, "data.json"));
        JSONArray data = null;
        try {
            data = new JSONArray(json);
            engine.setData(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static byte[] getAssertsFile(Context context, String fileName) {
        InputStream inputStream = null;
        AssetManager assetManager = context.getAssets();
        try {
            inputStream = assetManager.open(fileName);
            if (inputStream == null) {
                return null;
            }

            BufferedInputStream bis = null;
            int length;
            try {
                bis = new BufferedInputStream(inputStream);
                length = bis.available();
                byte[] data = new byte[length];
                bis.read(data);

                return data;
            } catch (IOException e) {

            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {

                    }
                }
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
