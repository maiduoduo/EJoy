package com.module.ires.bean.view;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ScrollView;

/**
 * CN:      IScrollView
 * Author： JSYL-DINGCL (1144286501@qq.com)
 * Date:   2019/11/14
 * Des:
 *      当ScrollView中显示内容量小的时候自适应高度不滚动，当ScrollView中显示内容量大的时候需要将其高度设置为屏幕高度的一半且可以滚动查看，
 *      由于ScrollView没有设置其最大高度的属性，所以自定义一个ScrollView来满足显示要求。
 *      自定义一个View继承ScrollView并重写其onMeasure方法，在此方法中设置控件最大高度不能超过屏幕高度的一半。
 */
public class IScrollView extends ScrollView {
    private Context mContext;

    public IScrollView(Context context) {
        this(context, null);
    }

    public IScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
            DisplayMetrics d = new DisplayMetrics();
            display.getMetrics(d);
            // 设置控件最大高度不能超过屏幕高度的一半
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(d.heightPixels / 2, MeasureSpec.AT_MOST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 重新计算控件的宽高
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
