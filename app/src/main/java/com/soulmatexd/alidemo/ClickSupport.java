package com.soulmatexd.alidemo;

import android.view.View;
import android.widget.Toast;

import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.support.SimpleClickSupport;

public class ClickSupport extends SimpleClickSupport {

    public ClickSupport() {
        setOptimizedMode(true);
    }

    @Override
    public void defaultClick(View targetView, BaseCell cell, int eventType) {
        super.defaultClick(targetView, cell, eventType);
        //由于是死的，个人觉得在实现跳转的时候，还需要和后台商量一个协议。是跳原生页面还是Tangram、接口数据等。
        Toast.makeText(targetView.getContext(), " 您点击了组件  pos(相对父容器的):"+cell.pos, Toast.LENGTH_SHORT).show();
    }
}
