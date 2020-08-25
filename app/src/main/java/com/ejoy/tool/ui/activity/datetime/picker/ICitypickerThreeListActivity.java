package com.ejoy.tool.ui.activity.datetime.picker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.imaidd.citypicker.style.citythreelist.ICityBean;

import butterknife.BindView;

/**
 * CN:      ICitypickerThreeListActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/13
 * Des:    城市三级选择器
 */
public class ICitypickerThreeListActivity extends BaseActivity {

    @BindView(R.id.list_tv) TextView mListTv;
    @BindView(R.id.result_tv) TextView mResultTv;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_icitypicker_three_list;
    }

    @Override
    protected void initView(View mRootView) {
        StatusBarTool.setRootViewFitsSystemWindows(_mActivity,true);
        StatusBarTool.setStatusBarColor(_mActivity,Color.parseColor(defalutGreen));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {
        mListTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list();
            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
    public void list() {
        Intent intent = new Intent(_mActivity, IThreeProvinceActivity.class);
        startActivityForResult(intent, IThreeProvinceActivity.RESULT_DATA);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IThreeProvinceActivity.RESULT_DATA) {
            if (resultCode == RESULT_OK) {
                if (data == null) { return;}
                ICityBean area = data.getParcelableExtra("area");
                ICityBean city = data.getParcelableExtra("city");
                ICityBean province = data.getParcelableExtra("province");
                mResultTv.setText("所选省市区城市： \n\n" + province.getName() + " " + province.getId() + "\n" + city.getName()
                        + " " + city.getId() + "\n" + area.getName() + " " + area.getId() + "\n");
            }
        }
    }

    public void ivback(View view) {
        finish();
    }
}
