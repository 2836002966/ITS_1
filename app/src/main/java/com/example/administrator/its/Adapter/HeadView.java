package com.example.administrator.its.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.administrator.its.R;

/**
 * Created by Administrator on 2018/11/1.
 */

public class HeadView extends LinearLayout {
    public HeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.head,this);
    }
}
