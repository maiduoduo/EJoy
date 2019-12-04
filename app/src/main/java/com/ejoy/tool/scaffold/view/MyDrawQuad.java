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

/**
 * CN:      MyDrawQuad
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/29
 * Des:    TODO:
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyDrawQuad extends View {
    private Paint paint;
    private Path path;
    public MyDrawQuad(Context context) {
        super(context);
        initData();
    }

    public MyDrawQuad(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public MyDrawQuad(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    public MyDrawQuad(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initData();
    }
    public void initData(){
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);//设置为不实心
        path=new Path();
        paint.setStrokeWidth(1);
        paint.setColor(Color.WHITE);
        paint.setTextSize(52);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x1=200,y1=200;
        int x3=400,y3=200;

        int x2=(x1+x3)/2,y2=y1+30;
        path.moveTo(x1,y1);//起点
        path.quadTo(x2,y2,x3,y3);//3点画弧

        int xa1=150,ya1=400;
        int xa3=450,ya3=400;
        int xa2=(xa1+xa3)/2,ya2=460;

        path.moveTo(xa1,ya1);
        path.quadTo(xa2,ya2,xa3,ya3);
        path.moveTo(x1,y1);
        path.lineTo(xa1,ya1);
        path.moveTo(x3,y3);
        path.lineTo(xa3,ya3);
        canvas.drawPath(path,paint);

    }
}


