package com.ejoy.tool.ui.activity.more_detail.charts;

import android.graphics.Color;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.utils.EJsonUtils;
import com.module.iviews.charts.bean.IStockExtremeValue;
import com.module.iviews.charts.bean.KLineToDrawItem;
import com.module.iviews.charts.stocks.IKMasterChartView;
import com.module.iviews.charts.stocks.IKSubChartView;
import com.module.iviews.charts.stocks.IMarketFigureChart;
import com.module.iviews.charts.stocks.listener.IChartDataCountListener;
import com.module.iviews.charts.stocks.listener.IPressChangeListener;
import com.module.iviews.charts.stocks.tool.IStockChartDataSourceHelper;
import com.module.iviews.charts.stocks.tool.IStockKLineParser;
import com.module.iviews.charts.stocks.tool.IStockSubChartData;
import com.module.iviews.charts.stocks.tool.IStockTechParamType;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IChartsStockCandleActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2021/2/22
 * @des: 股票蜡烛图和K线
 */
@Layout(R.layout.activity_charts_stock_candle)
@DarkStatusBarTheme(true)
public class IChartsStockCandleActivity extends IBaseActivity implements IPressChangeListener,
        RadioGroup.OnCheckedChangeListener, IChartDataCountListener<List<KLineToDrawItem>> {

    @BindView(R.id.blur)
    BlurView blur;

    @BindView(R.id.progress_circular)
    ProgressBar mProgressBar;
    @BindView(R.id.rbtn_group)
    RadioGroup radioGroup;

    // 行情图容器
    @BindView(R.id.chart_container)
    IMarketFigureChart mMarketFigureChart;

    private IStockChartDataSourceHelper mHelper;
    private IKMasterChartView mKLineChartView;

    private IKSubChartView mVolumeView;
    private IKSubChartView mMacdView;

    private int MAX_COLUMNS = 160;
    private int MIN_COLUMNS = 20;



    @Override
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return true;
    }

    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }


    @Override
    public void initViews() {
        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);

        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.rbtn_15);

        // 行情图主图（蜡烛线）
        mKLineChartView = new IKMasterChartView(this);
        mMarketFigureChart.addChildChart(mKLineChartView, 200);

        // 行情图附图（成交量）
        mVolumeView = new IKSubChartView(this);
        mMarketFigureChart.addChildChart(mVolumeView, 100);

        // MACD
        mMacdView = new IKSubChartView(this);
        mMarketFigureChart.addChildChart(mMacdView, 100);

        // 容器的手势监听
        mMarketFigureChart.setPressChangeListener(this);


    }


    @Override
    public void initDatas() {
    }

    @Override
    public void setEvents() {

    }

    @OnClick({
            R.id.btn_back
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;

        }
    }


    private void initialData(final String json) {
        mProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initStockData(json);
            }
        }, 500);
    }

    /**
     * 解析行情图数据
     */
    public void initStockData(String json) {
        // 士兰微k线数据
        String kJson = EJsonUtils.getFromAssets(this, json);

        IStockKLineParser parser = new IStockKLineParser(kJson);
        parser.parseKlineData();

        if (mHelper == null) {
            mHelper = new IStockChartDataSourceHelper(this);
        }
        mProgressBar.setVisibility(View.GONE);
        mHelper.initKDrawData(parser.klineList, mKLineChartView, mVolumeView, mMacdView);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbtn_15:
                initialData("asset_stock_slw_k.json");
                break;
            case R.id.rbtn_1h:
                initialData("asset_stock_geli.json");
                break;
            case R.id.rbtn_4h:
                initialData("asset_stock_maotai.json");
                break;
            case R.id.rbtn_1d:
                initialData("asset_stock_pingan.json");
                break;
        }
    }

    /**
     * 对主图和附图进行数据填充
     */
    @Override
    public void onReady(List<KLineToDrawItem> data, IStockExtremeValue extremeValue,
                        IStockSubChartData subChartData) {
        mKLineChartView.initData(data, extremeValue,subChartData);
        mVolumeView.initData(data, extremeValue, IStockTechParamType.VOLUME,subChartData);
        mMacdView.initData(data, extremeValue, IStockTechParamType.MACD,subChartData);
    }

    /**
     * 主图的横向滑动
     */
    @Override
    public void onChartTranslate(MotionEvent me, float dX) {
        if (mHelper != null) {
            mHelper.initKMoveDrawData(dX, IStockChartDataSourceHelper.SourceType.MOVE);
        }
    }

    /**
     * 主图的手势fling
     */
    @Override
    public void onChartFling(float distanceX) {
        if (mHelper != null) {
            mHelper.initKMoveDrawData(distanceX, IStockChartDataSourceHelper.SourceType.FLING);
        }
    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
        IStockChartDataSourceHelper.K_D_COLUMNS = (int) (IStockChartDataSourceHelper.K_D_COLUMNS / scaleX);
        IStockChartDataSourceHelper.K_D_COLUMNS =
                Math.max(MIN_COLUMNS, Math.min(MAX_COLUMNS, IStockChartDataSourceHelper.K_D_COLUMNS));
        if (mHelper != null) {
            mHelper.initKMoveDrawData(0, IStockChartDataSourceHelper.SourceType.SCALE);
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
