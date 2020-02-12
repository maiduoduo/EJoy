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

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.app.App;
import com.ejoy.tool.app.bugly.BuglyHelper;
import com.ejoy.tool.scaffold.utils.ApkUtil;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.utils.WidgetUtils;

import org.jsoup.helper.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * CN:      AboutMeActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/16
 * Des:    TODO:关于
 */

@Layout(R.layout.activity_aboutme)
@DarkStatusBarTheme(true)
//@DarkNavigationBarTheme(true)
public class AboutMeActivity extends IBaseActivity {
    @BindView(R.id.about_list)
    RecyclerView aboutList;
    @BindView(R.id.copyright)
    TextView copyright;
    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.ivshare)
    ImageView ivshare;
    @BindView(R.id.tvVersion)
    TextView tvVersion;

    private BaseQuickAdapter mAdapter;
    public List<String> strList;

    @Override
    public void initViews() {
        initList();
        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);
        tvVersion.setText("E·享 v"+ApkUtil.getVersionName(me));
        //高斯模糊背景
//        new EBlurHelper().setContainerView(llTopHeader)
//                .setImageResourse(R.mipmap.img_bg_h)
//                .setRadius(10f)
//                .setContext(me)
//                .build();
    }

    @Override
    public void initDatas() {
        strList.addAll(GlobalDataProvider.aboutListData());
    }

    @Override
    public void setEvents() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2://版本更新
                        BuglyHelper.getInstance().doCheckUpgrade(me);
                        break;
                    case 3:
                        break;

                }
                iToast.showISimpleToast(strList.get(position));
            }
        });
    }

    private void initList() {
        if (strList != null) strList.clear();
        else strList = new ArrayList<>();
        WidgetUtils.initDefaultRecyclerView(me, aboutList, R.color.black_11);
        aboutList.setAdapter(mAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_common_list_item, strList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.group_list_item_textView, StringUtil.isBlank(item) ? "" : item);
            }
        });
    }


    @OnClick({
            R.id.ivshare,
            R.id.btn_back
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivshare:
                Intent share_intent = new Intent();
                share_intent.setAction(Intent.ACTION_SEND);
                share_intent.setType("text/plain");
                share_intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                share_intent.putExtra(Intent.EXTRA_TEXT, "发现了一款多功能的工具App「E·享」，集成常用的开发组件，展示最新的开发思想，给开发一点灵感，多一些使用体验，太赞了! " + getString(R.string.link_github));
                share_intent = Intent.createChooser(share_intent, "分享");
                startActivity(share_intent);
                break;
            case R.id.btn_back:
                App.getInstance().killActivity(me);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
