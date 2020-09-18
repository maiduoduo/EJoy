package com.ejoy.tool.ui.activity.textview;
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


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.IToastImageType;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.textview.spinner.IEditSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * CN:      ITextViewActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/16
 * Des:    TODO:TextView有关组件
 */
@Layout(R.layout.activity_itextview)
@DarkStatusBarTheme(true)
public class ITextViewActivity extends IBaseActivity {


    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.kcSpinner)
    IEditSpinner kcSpinner;
    @BindView(R.id.ksSpinner)
    IEditSpinner ksSpinner;


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
//        setDarkStatusBarTheme(false);
//        setNavigationBarBackgroundColor(EResUtils.getColor(me,R.color.red));
//        StatusBarTool.setStatusBarColor(me,EResUtils.getColor(me,R.color.green));
//        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
//        blur.setOverlayColor(Color.argb(200, 20, 20, 20));

//        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
//        blur.setOverlayColor(R.color.LGray3);
//        blur.setRadius(me, 0, 0);
    }

    @Override
    public void initDatas() {
        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
        blur.setRadius(me, 0, 0);

        loadSpinnerData();
    }

    private void loadSpinnerData() {
        List<String> kcData = getKcData();
        List<String> ksData = getKsData();
        if (kcData != null && kcData.size() > 0) {
            kcSpinner.setItemData(kcData);
            kcSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String trim1 = kcSpinner.getText().toString().trim();
                    iToast.showISimpleToast("课程："+trim1,0,IToastImageType.INFO);
                }
            });
        }else {
            iToast.showISimpleToast("暂无课程数据",0,IToastImageType.WARN);
        }


        if (ksData != null && ksData.size() > 0) {
            ksSpinner.setItemData(ksData);
            ksSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String trim = ksSpinner.getText().toString().trim();
                    iToast.showISimpleToast("课时："+trim,0,IToastImageType.INFO);
                }
            });
        }else {
            iToast.showISimpleToast("暂无课时数据",0,IToastImageType.WARN);
        }
    }

    @Override
    public void setEvents() {

    }

    @OnClick({
            R.id.btn_back
    })
    public void bindClick(View view){
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }


    private List<String> getKcData() {
        List<String> list = new ArrayList<>();
        list.add("高等数学");
        list.add("影视编导与表演");
        list.add("广告学");
        list.add("艺术学理论");
        list.add("数字设计学");
        list.add("艺术表演学");
        list.add("68956");
        list.add("视觉设计");
        list.add("14562987");
        list.add("动画设计");
        list.add("动画渲染");
        list.add("动画角色学");
        list.add("65987");
        list.add("编剧学");
        list.add("心理学");
        list.add("人物分析与刻画");
        list.add("LBRCHUIT");
        list.add("摄影艺术");
        list.add("如何剪辑");
        list.add("美术");
        list.add("临场表演与表现行为学");
        list.add("ILONEIMG");
        list.add("人物情绪细节课程");
        list.add("动物学研究");
        list.add("事物起源观察");

        return list;
    }

    private List<String> getKsData() {
        List<String> list = new ArrayList<>();
        list.add("10课时");
        list.add("20课时");
        list.add("40课时");
        list.add("30课时");
        list.add("60课时");
        list.add("100课时");
        list.add("试听课时");
        list.add("Vip课时");
        return list;
    }

}
