package com.ejoy.tool.ui.activity.compress;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.scaffold.utils.PairHelp;
import com.ejoy.tool.scaffold.view.widget.PreviewPager;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.adapter.PreviewAdapter;
import com.ejoy.tool.ui.data.resource.ApiResource;
import com.ejoy.tool.ui.mvp.base.BasePresenter;

import java.util.List;


/**
 * 类  名 :  PreviewImageActivity
 * 描  述 :  选择图片预览
 */

public class PreviewImageActivity extends BaseActivity {

    private PreviewAdapter mAdapter;
    private PreviewPager mViewPager;
    private TextView mTvCurrentPage;
    private TextView mTvToatalPage;




//    @Override
//    protected void initUI() {
//        super.initUI();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            if (window == null) {
//                finish();
//                return;
//            }
//          window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }
//    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_preview_pager;
    }

    @Override
    protected void initView(View mRootView) {
        mViewPager = findViewById(R.id.vp);
        mTvCurrentPage = findViewById(R.id.tvCurrentPage);
        mTvToatalPage = findViewById(R.id.tvToatalPage);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            final List<String> imagePathList = intent.getStringArrayListExtra(ApiResource.IMAGE_PATH_KEY);
            int intExtra = intent.getIntExtra(ApiResource.CLICK_IMAGE_POSITION_KEY,0)+1;
            mTvCurrentPage.setText(intExtra+" ");
            mTvToatalPage.setText("/ "+imagePathList.size());
            if (imagePathList != null && imagePathList.size() > 0) {
                if (mAdapter == null){
                    mAdapter = new PreviewAdapter(imagePathList,this);
                    mViewPager.setOffscreenPageLimit(0);
                    mViewPager.setAdapter(mAdapter);
                    mViewPager.setCurrentItem(intent.getIntExtra(ApiResource.CLICK_IMAGE_POSITION_KEY,0),true);
                    mViewPager.setPageTransformer(true,new PreviewAdapter.PreviewPageTransformer());
                    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            PairHelp.setPreviewPosition(mViewPager.getCurrentItem());
                            int curPos = position += 1;
                            mTvCurrentPage.setText(curPos+" ");
                            mTvToatalPage.setText("/ "+imagePathList.size());
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                }else {
                    mAdapter.notifyDataSetChanged();
                }

            } else {
                finish();
            }
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
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();


    }
}
