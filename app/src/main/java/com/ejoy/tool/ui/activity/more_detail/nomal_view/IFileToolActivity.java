package com.ejoy.tool.ui.activity.more_detail.nomal_view;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.FileUtils;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.ires.bean.utils.EFileUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName: INormalCustomViewActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/12/18
 * @des: 常规自定义View集合
 */
@Layout(R.layout.activity_file_tool)
@DarkStatusBarTheme(true)
public class IFileToolActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.tvFileSaveDes)
    TextView tvFileSaveDes;

    private final  int saveLoopSize = 500;


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
        tvFileSaveDes.setText("");
        tvFileSaveDes.setText("准备存储..");

    }


    @Override
    public void initDatas() {

    }


    @Override
    public void setEvents() {
    }


    @OnClick({
            R.id.btn_back
            , R.id.llFileSaveRootStorage
    })
    public void eventClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.llFileSaveRootStorage:
                tvFileSaveDes.setText("准备.. ");
                if (!isFastDoubleClick(4000)) {
                    saveFileToRoot();
                }else {
                    iToast.showISimpleToast("请勿重复点击，当前存储还未完成..");
                }
                break;

        }
    }


    private String content = "";
    private void saveFileToRoot() {
        for (int i = 0; i < saveLoopSize; i++) {
            tvFileSaveDes.setText("开始存储.. ");
            tvFileSaveDes.setText("存储 "+0 + " / " + (saveLoopSize-1));
            if (i == 0) {
                tvFileSaveDes.setText("存储 "+i + " / " + (saveLoopSize-1));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");// HH:mm:ss
                //获取当前时间
                Date date = new Date(System.currentTimeMillis());
                String time = simpleDateFormat.format(date);
//                file = new File(Environment.getExternalStorageDirectory(), "InputFile" + time + ".txt");
                content = "\n" + time + "\n";
            } else {
                tvFileSaveDes.setText("存储 "+i + " / " + (saveLoopSize-1));
                content = "写入流字段——" + i + "\n";
            }

            EFileUtils.writeFileToRootSpace(me,content.getBytes(),"APP_SAVE_FILE","in_app_log.txt",true,true);
            tvFileSaveDes.setText("存储 "+i + " / " + (saveLoopSize-1));

        }
    }

}


