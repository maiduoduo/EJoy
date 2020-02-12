package com.module.iviews.refresh.item;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import com.module.ires.bean.utils.log.ELog;


public class ERefreshViewPager extends ViewPager {

    private float mXDownPos;
    private float mYDownPos;
    private float mXDistance;
    private float mYDistance;
    private boolean mFirstMove;
    private boolean mShouldDeal;

    public ERefreshViewPager(Context context) {
        super(context);
    }

    public ERefreshViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                requestParentDisallowInterceptTouchEvent(true);
                mXDownPos = ev.getRawX();
                mYDownPos = ev.getRawY();
                mFirstMove = true;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mFirstMove) {
                    if (mShouldDeal) {
                        if (ELog.isEnabled())
                            ELog.v("ViewPager:disallow parent intercept touch event");
                        requestParentDisallowInterceptTouchEvent(true);
                        return super.onTouchEvent(ev);
                    } else {
                        if (ELog.isEnabled())
                            ELog.w("ViewPager:allow parent intercept touch event");
                        requestParentDisallowInterceptTouchEvent(false);
                        return false;
                    }
                }

                mXDistance = Math.abs(ev.getRawX() - mXDownPos);
                mYDistance = Math.abs(ev.getRawY() - mYDownPos);
                mFirstMove = false || mXDistance == 0 || mYDistance == 0;
                mShouldDeal = mXDistance > mYDistance;
                if (ELog.isEnabled())
                    ELog.v("xdistance = %f,ydistance = %f", mXDistance, mYDistance);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                requestParentDisallowInterceptTouchEvent(false);
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void requestParentDisallowInterceptTouchEvent(boolean b) {
        ViewParent vp = getParent();
        if (vp != null) {
            vp.requestDisallowInterceptTouchEvent(b);
        }
    }
}
