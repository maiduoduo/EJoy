package com.ejoy.tool.scaffold.view;
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
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;

import com.ejoy.tool.R;

/**
 * CN:      BezierView
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/29
 * Des:    TODO:
 */
public class BezierView extends View{

        private Paint mPaint;
        private Path mPath;
        private Point startPoint;
        private Point endPoint;
        private Point assistPoint;
        private int screenWidth;
        private int screenHeigh;
        private Activity _context;

        public BezierView(Activity context) {
            super(context);
            this._context = context;
            //获取屏幕的宽高
            getScreenArgs();
            //初始化画笔，path等
            initContext();

        }

    public void init(Activity context) {
        this._context = context;
        //获取屏幕的宽高
        getScreenArgs();
        //初始化画笔，path等
        initContext();

    }

        private void initContext() {
            mPaint = new Paint();
            mPaint.setColor(getResources().getColor(R.color.app_color_theme_1));
            mPaint.setStrokeWidth(6);//设置画笔的粗细
            mPath = new Path();
            startPoint = new Point(0,0);//设置起点（可以自定义）
            endPoint = new Point(screenWidth, 0);//设置终点（可以自己定）
            assistPoint = new Point(screenWidth/2,screenHeigh/10);//设置中间辅助切线点
            mPaint.setAntiAlias(true);// 抗锯齿
            mPaint.setDither(true);// 防抖动,更清晰
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //画笔重置
            mPath.reset();
            // 起点
            mPath.moveTo(startPoint.x, startPoint.y);
            // 开始画贝塞尔曲线
            mPath.quadTo(assistPoint.x, assistPoint.y, endPoint.x, endPoint.y);
            // 画路径
            canvas.drawPath(mPath, mPaint);
        }

        public void getScreenArgs() {
            DisplayMetrics dm = new DisplayMetrics();
            _context.getWindowManager().getDefaultDisplay().getMetrics(dm);
            screenWidth = dm.widthPixels;
            screenHeigh = dm.heightPixels;
        }
    }
