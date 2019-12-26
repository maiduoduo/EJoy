package com.ejoy.tool.ui.activity.picker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.adapter.IThreeProvinceAdpter;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.imaidd.citypicker.style.citylist.bean.CityInfoBean;
import com.imaidd.citypicker.style.citythreelist.ICityBean;
import com.imaidd.citypicker.widget.RecycleViewDividerForList;
import java.util.List;
import butterknife.BindView;
import static com.ejoy.tool.ui.activity.picker.IThreeProvinceActivity.RESULT_DATA;
import static com.imaidd.citypicker.style.citylist.utils.CityListLoader.BUNDATA;



/**
 * CN:      IThreeCityActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/13
 * Des:    城市三级选择器-市级
 */
public class IThreeCityActivity extends BaseActivity {

    @BindView(R.id.cityname_tv) TextView mCityNameTv;
    @BindView(R.id.city_recyclerview) RecyclerView mCityRecyclerView;
    private CityInfoBean mProInfo = null;
    private String cityName = "";
    private ICityBean cityBean = new ICityBean();
    private ICityBean area = new ICityBean();

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_three_citylist;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(_mActivity,true);
        StatusBarTool.setStatusBarColor(_mActivity,Color.parseColor(defalutGreen));
        mProInfo = this.getIntent().getParcelableExtra(BUNDATA);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.addItemDecoration(new RecycleViewDividerForList(this, LinearLayoutManager.HORIZONTAL, true));
    }

    @Override
    protected void initData() {
        if (mProInfo != null && mProInfo.getCityList().size() > 0) {
            mCityNameTv.setText("" + mProInfo.getName());
            final List<CityInfoBean> cityList = mProInfo.getCityList();
            if (cityList == null) {return;}

            IThreeProvinceAdpter cityAdapter = new IThreeProvinceAdpter(R.layout.item_three_citylist,cityList,_mActivity);
            mCityRecyclerView.setAdapter(cityAdapter);
            cityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    CityInfoBean data = (CityInfoBean) adapter.getData().get(position);
                    cityBean.setId(data.getId());
                    cityBean.setName(data.getName());
                    Intent intent = new Intent(IThreeCityActivity.this, IThreeAreaActivity.class);
                    intent.putExtra(BUNDATA, data);
                    startActivityForResult(intent, RESULT_DATA);
                }
            });
        }
    }

    @Override
    protected void addListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_DATA && data != null) {
            area = data.getParcelableExtra("area");
            Intent intent = new Intent();
            intent.putExtra("city", cityBean);
            intent.putExtra("area", area);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void ivback(View view) {
        finish();
    }
}
