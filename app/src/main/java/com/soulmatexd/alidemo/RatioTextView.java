package com.soulmatexd.alidemo;

import android.content.Context;
import android.util.AttributeSet;

import com.tmall.wireless.tangram.structure.BaseCell;
import com.tmall.wireless.tangram.structure.CellRender;

public class RatioTextView extends android.support.v7.widget.AppCompatTextView {

    public double ratio = 1.0;

    public RatioTextView(Context context) {
        super(context);
    }

    public RatioTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RatioTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, (int) (width * ratio));
    }

    @CellRender
    public void cellInited(BaseCell cell) {

    }
}
