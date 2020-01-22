package com.module.iviews.view;
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

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * CN:      MyScrollView
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/22
 * Des:    TODO:自定义带滚动监听的scrollview
 */
public class IObserverScrollView extends ScrollView {

        /**
         * 接口回调
         */
        private ScrollViewListener scrollViewListener = null;
        public interface ScrollViewListener {
            void onScrollChanged(ScrollView scrollView, int x, int y,
                                 int oldx, int oldy);

        }
        public void setScrollViewListener(ScrollViewListener scrollViewListener) {
            this.scrollViewListener = scrollViewListener;
        }

        /**
         * 重写的onScrollChanged方法监听坐标
         * 这个监听在源码中没有写成回调的样子，
         * 只是写成了方法的样子，由于修饰这个方法的修饰符是protected，
         *（protected只能在本类，子类，同一包中调用），
         * 所以拿到ScrollView对象后在无法activity中调用此方法，
         * 所以只能重写后，子类中自动调用，
         * 所以要想在activity调用，
         * 就要写回调，
         * 上面就是我写的回调
         * 在Android源码中这种写法很多，在很多控件中都有
         */
        @Override
        protected void onScrollChanged(int x, int y, int oldx, int oldy) {
            super.onScrollChanged(x, y, oldx, oldy);
            if (scrollViewListener != null) {
                scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
            }
        }

        /**
         *构造方法（在这里没用）
         */
        public IObserverScrollView(Context context) {
            super(context);
        }

        public IObserverScrollView(Context context, AttributeSet attrs,
                                   int defStyle) {
            super(context, attrs, defStyle);
        }

        public IObserverScrollView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

    }