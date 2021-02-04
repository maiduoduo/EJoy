package com.ejoy.tool.ui.douyin.activity;

import android.widget.ImageView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;

import butterknife.BindView;


@Layout(R.layout.activity_douyin_show_image)
@DarkStatusBarTheme(true)
public class DouyinShowImageActivity extends IBaseActivity {
    @BindView(R.id.iv_head)
    ImageView ivHead;

    @Override
    public void initViews() {
        ivHead.setOnClickListener(v -> finish());

        int headRes = getIntent().getIntExtra("res", 0);
        ivHead.setImageResource(headRes);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }
}
