package com.ejoy.tool.ui.activity.more_detail;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.IImmerseBean;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.adapter.IImmerseBlurAdpter;
import com.kongzue.baseframework.interfaces.Layout;
import com.module.iviews.common.blur.BlurredView;
import com.module.iviews.utils.WidgetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
 * CN:      IImerseBlurViewActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/3/5
 * Des:    TODO:Android的动态模糊图像视图-沉浸渐变式
 */
@Layout(R.layout.activity_immerse_blurview)
public class IImerseBlurViewActivity extends IBaseActivity {

//    @BindView(R.id.blur)
//    BlurView blur;
    @BindView(R.id.immerse_blurredview)
    BlurredView mBlurredView;
    @BindView(R.id.mtoolbar)
    Toolbar mToolbar;
    @BindView(R.id.mrecyclerview)
    RecyclerView mRecyclerView;


    private BaseQuickAdapter mImmerseAdpter;
    private List<IImmerseBean> mData;
    private int mScrollerY;
    private int mAlpha;


    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    public void initViews() {
        mData = new ArrayList<>();
        initRecyclerView();
    }

    private void initRecyclerView() {
        WidgetUtils.initLineRecyclerView(me, mRecyclerView, mImmerseAdpter = new IImmerseBlurAdpter(me, mData), null,false,false);
    }

    @Override
    public void initDatas() {
//        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
//        blur.setRadius(me, 0, 0);

        for (int i = 0; i < 10; i++) {
            if (i == 0){
                mData.add(new IImmerseBean("",0));
            }else {
                mData.add(new IImmerseBean(i+".Show List info",1));
            }
        }
    }

    @Override
    public void setEvents() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollerY += dy;
                if (Math.abs(mScrollerY) > 1000) {
                    mBlurredView.setBlurredTop(100);
                    mAlpha = 100;
                } else {
                    mBlurredView.setBlurredTop(mScrollerY / 10);
                    mAlpha = Math.abs(mScrollerY) / 10;
                }
                mBlurredView.setBlurredLevel(mAlpha);
            }
        });

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({
    })
    public void bindClick(View view) {
    }

}
