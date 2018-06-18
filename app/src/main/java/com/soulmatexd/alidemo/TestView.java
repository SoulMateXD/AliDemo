package com.soulmatexd.alidemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle;

public class TestView extends FrameLayout implements ITangramViewLifeCycle {
    private TextView textView;
    //组件通用模型
    private BaseCell cell;

    public TestView(Context context) {
        super(context);
        init();
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.textview_item, this);
        textView = (TextView) findViewById(R.id.title);
    }

    @Override
    public void cellInited(BaseCell cell) {
        setOnClickListener(cell);
        this.cell = cell;
    }

    @Override
    public void postBindView(BaseCell cell) {
        int pos = cell.pos;
        String parent = "";
        if (cell.parent != null) {
            parent = cell.parent.getClass().getSimpleName();
        }
        textView.setText(
                cell.id + " pos: " + pos + " " + parent + " " + cell
                        .optParam("msg"));
        textView.setTextSize(20);

        //换几个颜色
        if (pos > 10) {
            textView.setBackgroundColor(0x66cccf00 + (pos - 50) * 128);
        } else if (pos % 2 == 0) {
            textView.setBackgroundColor(0xaaaaff55);
        } else {
            textView.setBackgroundColor(0xcceeeeee);
        }
    }

    @Override
    public void postUnBindView(BaseCell cell) {
    }
}
