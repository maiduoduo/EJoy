package com.ejoy.tool.ui.activity.more_detail.nomal_view;
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
import android.util.Log;
import android.view.View;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.google.gson.Gson;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.drag.IAnswerRange;
import com.module.iviews.drag.IDragFillBlankView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: IDragFillBlankViewActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/12/19
 * @des: 选词（拖拽）填空题-自定义
 */
@Layout(R.layout.activity_iui_drag_fill_blank_view)
@DarkStatusBarTheme(true)
public class IDragFillBlankViewActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.dfbv_content)
    IDragFillBlankView dfbvContent;


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
        blur.setOverlayColor(Color.argb(100, 235, 235, 235));
        blur.setRadius(me, 0, 0);
        setDarkNavigationBarTheme(false);

    }


    @Override
    public void initDatas() {
        String content = "纷纷扬扬的________下了半尺多厚。天地间________的一片。我顺着________工地走了四十多公里，" +
                "只听见各种机器的吼声，可是看不见人影，也看不见工点。一进灵官峡，我就心里发慌。________里的人们也已经睡熟了，寂静无声的雪夜，空荡荡的。";

        // 选项集合
        List<String> optionList = new ArrayList<>();
        optionList.add("白茫茫");
        optionList.add("雾蒙蒙");
        optionList.add("铁路");
        optionList.add("公路");
        optionList.add("大雪");
        optionList.add("村子");

        // 答案范围集合
        List<IAnswerRange> rangeList = new ArrayList<>();
        int count = lookUpStrKey(content, "________");
        //取出填空的数量
        if (indexCountAttr != null && indexCountAttr.size() > 0){
            //取出角标的位置
            for (int i = 0; i < count-1; i++) {
                rangeList.add(new IAnswerRange(indexCountAttr.get(i), (indexCountAttr.get(i)+8)));
            }

        }
        Log.e(_TAG, "所在字符串中的位置是 rangeList:"+new Gson().toJson(rangeList));
        dfbvContent.setData(content, optionList, rangeList);
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


    /**
     * 一个字符在某一个字符串中出现的位置和次数
     *
     * @param str
     * @param strKey
     */
    private List<Integer> indexCountAttr;
    public int lookUpStrKey(String str, String strKey) {
        indexCountAttr = (List<Integer>)initPublicArrayList(indexCountAttr);
        int count = 1;
        int index = 0;
        int strkeyLength = strKey.length();
        while (true) {
            index = str.indexOf(strKey, index);
            if (index != -1) {
                Log.e(_TAG, "第"+count+"次寻找，所在字符串中的位置是:"+index);
                indexCountAttr.add(index);
            } else {
                break;
            }
            index += strkeyLength;
            count++;
        }
        return count;
    }

}


