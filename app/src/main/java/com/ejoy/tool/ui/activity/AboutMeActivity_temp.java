package com.ejoy.tool.ui.activity;
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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.utils.EBlurHelper;
import com.module.iviews.utils.WidgetUtils;

import org.jsoup.helper.StringUtil;

import butterknife.BindView;

/**
 * CN:      AboutMeActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/16
 * Des:    TODO:关于
 */
public class AboutMeActivity_temp extends BaseActivity {
    @BindView(R.id.version)
    TextView version;
    @BindView(R.id.about_list)
    RecyclerView aboutList;
    @BindView(R.id.copyright)
    TextView copyright;
    @BindView(R.id.llTopHeader)
    LinearLayout llTopHeader;

    private BaseQuickAdapter mAdapter;

    @Override
    protected boolean isRegistSatusbarFontDark() {
        return true;
    }

    @Override
    protected Object registSatusbarBgcolor() {
        return baseWhite;
    }

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_aboutme;
    }

    @Override
    protected void initView(View mRootView) {
        initList();
        //高斯模糊背景
        new EBlurHelper().setContainerView(llTopHeader)
                .setImageResourse(R.mipmap.img_bg_h)
                .setRadius(10f)
                .setContext(_mActivity)
                .build();
    }

    private void initList() {
        WidgetUtils.initDefaultRecyclerView(_mActivity,aboutList, R.color.black_11);
        aboutList.setAdapter(mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_common_list_item, strList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.group_list_item_textView, StringUtil.isBlank(item) ? "" : item);
            }
        });
    }

    @Override
    protected void initData() {
        strList.addAll(GlobalDataProvider.aboutListData());
    }

    @Override
    protected void addListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2://版本更新
//                        BuglyHelper.getInstance().doCheckUpgrade(_mActivity);
                        break;
                    case 3:
                        break;

                }
                iToast.showISimpleToast(strList.get(position));
            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    public void iBack(View view) {
        finish();
    }
}
