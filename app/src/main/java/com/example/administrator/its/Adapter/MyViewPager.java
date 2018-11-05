package com.example.administrator.its.Adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

import com.example.administrator.its.R;

/**
 * Created by Administrator on 2018/11/2.
 */

public class MyViewPager extends ViewGroup{
    private int[] image_id = {R.drawable.frist, R.drawable.guide,R.drawable.threed};
    private GestureDetector mDetector;
    private Scroller mScroller;

    private OnPagerChangeListener listener;
    public MyViewPager(Context context) {
        super(context);
        initView();
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView() {
        for (int i = 0; i < image_id.length; i++) {
            ImageView iv = new ImageView(getContext());
            iv.setBackgroundResource(image_id[i]);
            this.addView(iv);
        }
        mDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //scrollBy：相对滑动，相对我们当前的控件多少距离，就滑动多少距离
                //distanceX是我们手滑动的距离，即我们的手相对控件滑动了多少，所以X轴滑动这个距离，Y轴滑动0
                scrollBy((int) distanceX, 0);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
        mScroller = new Scroller(getContext());

    }
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        for (int j = 0; j < image_id.length; j++) {
            this.getChildAt(j).layout(j * getWidth(), i1, (j + 1) * getWidth(), i3);
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        //触摸事件处理
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:


                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                //你滑动的距离加上屏幕的一半，除以屏幕宽度，如果你滑动距离超过了屏幕的一半，这个pos就加1
                int pos = (scrollX + getWidth() / 2) / getWidth();
                //滑到最后一张的时候，不能出边界
                if (pos >= image_id.length) {
                    pos = image_id.length - 1;
                }
                //绝对滑动，直接滑到指定的x值
                //scrollTo(pos * getWidth(), 0);
                //自然滑动,从手滑到的地方开始，滑动距离是页面宽度减去滑到的距离，时间由路程的大小来决定
//                mScroller.startScroll(scrollX, 0, pos * getWidth() - scrollX, 0, Math.abs(pos * getWidth()));
//                invalidate();
                setCurrentItem(pos);
                break;
        }
        return true;
    }
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
    }
    public interface OnPagerChangeListener {
        void onPagerChange(int pos);
    }

    public void setOnPagerChangeListener(OnPagerChangeListener listener) {
        this.listener = listener;
    }
    public void setCurrentItem(int pos) {
        mScroller.startScroll(getScrollX(), 0, pos * getWidth() - getScrollX(), 0, Math.abs(pos * getWidth()));
        invalidate();
        //页面切换接口回调
        if (listener != null) {
            listener.onPagerChange(pos);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
