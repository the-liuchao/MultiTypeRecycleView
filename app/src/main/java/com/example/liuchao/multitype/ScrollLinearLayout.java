package com.example.liuchao.multitype;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * Created by yangrenije on 2017/7/6.
 */

public class ScrollLinearLayout extends LinearLayout implements GestureDetector.OnGestureListener {
    protected OverScroller mScroller;               //滚动辅助类
    protected VelocityTracker mVelocityTracker;     //滑动速度跟踪器
    protected GestureDetector mGestureDetector;     //手势检测
    private int mMaxVelocity;                       //触发fling最大滑动速度
    private int mScrollRange;                       //可滚动最大距离（不包含最后一个子控件marginBottom）


    public ScrollLinearLayout(Context context) {
        this(context, null);
    }

    public ScrollLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ScrollLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ScrollLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        //这里设置长按事件可用，否则dispatchTouchEvent的Down和Up事件都不会执行(也可以采取事件拦截方式)
        setLongClickable(true);
        mScroller = new OverScroller(context);
        mGestureDetector = new GestureDetector(context, this);
        mMaxVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {                                 //当布局发生改变是计算滚动范围
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            View lastChild = getChildAt(getChildCount() - 1);
            if (null != lastChild)
                mScrollRange = lastChild.getBottom() - rect.bottom + rect.top;
        }
    }

    private float mYMove;
    private float mYLastMove;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (null == mVelocityTracker) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mYMove = event.getY();
                mYLastMove = mYMove;
                break;
            case MotionEvent.ACTION_MOVE:
                mYMove = event.getY();
                int scrolledY = (int) (mYLastMove - mYMove);
                if (scrolledY < 0 && getScrollY() + scrolledY < 0) {                      //向下滚动
                    scrolledY = -getScrollY();
                }
                if (scrolledY > 0 && getScrollY() + scrolledY > mScrollRange) {          //向上滚动
                    scrolledY = mScrollRange - getScrollY();
                }
                if (mScrollRange > 0) {                                                  //可滚动距离必须大于0
                    scrollBy(0, scrolledY);
                    invalidate();
                }
                mYLastMove = mYMove;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mVelocityTracker != null && mScrollRange > 0) {
                    mVelocityTracker.computeCurrentVelocity(1000, mMaxVelocity);
                    mScroller.fling(getScrollX()
                            , getScrollY()                                             //松开滑动垂直开始坐标
                            , (int) mVelocityTracker.getXVelocity(0)
                            , -(int) mVelocityTracker.getYVelocity(0)                  //垂直方向滑动加速度，0表示第一个按下手指
                            , 0                                                        //松开滑动水平最小坐标
                            , 0                                                        //松开滑动垂直最小坐标
                            , 0                                                        //松开滑动水平最大坐标
                            , mScrollRange);                                           //松开滑动垂直最大坐标(这里就是最大滑动范围)
                    invalidate();                                                      //这必须调用刷新否则看不到效果
                }
                releaseVelocityTracker();
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * mVelocityTracker回收
     */
    private void releaseVelocityTracker() {
        if (null != mVelocityTracker) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
            ObjectAnimator animator = ObjectAnimator.ofFloat(this, "translationY", 23);
            animator.setRepeatMode(ObjectAnimator.INFINITE);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();              //这必须调用刷新否则看不到效果
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
