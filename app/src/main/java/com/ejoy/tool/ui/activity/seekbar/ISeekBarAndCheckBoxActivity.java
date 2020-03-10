package com.ejoy.tool.ui.activity.seekbar;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.qiujuer.genius.ui.widget.SeekBar;

import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * CN:      ISeekBarAndCheckBoxActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/3/10
 * Des:    TODO:SeekBar/CheckBox有关
 */
@Layout(R.layout.activity_iseekbar)
@DarkStatusBarTheme(true)
public class ISeekBarAndCheckBoxActivity extends IBaseActivity {

    @BindView(R.id.blur)
    BlurView blur;
    @BindView(R.id.edit_min)
    EditText mMin;
    @BindView(R.id.edit_max)
    EditText mMax;
    @BindView(R.id.tv_status)
    TextView mStatus;
    @BindView(R.id.seekBar)
    SeekBar mBar;
    @BindView(R.id.btnChange)
    Button mBtnChange;


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
    }

    @Override
    public void initDatas() {
        blur.setOverlayColor(Color.argb(200, 235, 235, 235));
        blur.setRadius(me, 0, 0);
    }

    @Override
    public void setEvents() {
        if (mBar != null) {
            mBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    showStatus();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
    }

    @OnClick({
            R.id.btn_back,
            R.id.btnChange
    })
    public void bindClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btnChange:
                int min = 0;
                try {
                    min = Integer.valueOf(mMin.getText().toString());
                } catch (NumberFormatException e) {
                    mMin.setText(String.valueOf(0));
                }
                mBar.setMin(min);

                int max = 0;
                try {
                    max = Integer.valueOf(mMax.getText().toString());
                } catch (NumberFormatException e) {
                    mMax.setText(String.valueOf(0));
                }
                mBar.setMax(max);

                showStatus();
                break;
        }
    }


    private void showStatus() {
        mStatus.setText(String.format("Min:%s, Max:%s, Value:%s", mBar.getMin(), mBar.getMax(), mBar.getProgress()));
    }


}
