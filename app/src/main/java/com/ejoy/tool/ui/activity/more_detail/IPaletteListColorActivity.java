package com.ejoy.tool.ui.activity.more_detail;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.ui.base.base_activity.IBaseActivity;
import com.ejoy.tool.ui.data.adapter.IPaletteViewpagerColorAdapter;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.dialog.util.BlurView;
import com.module.iviews.paletteimageview.IPaletteImageView;
import com.module.iviews.paletteimageview.OnParseColorListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @ClassName:  IPaletteListColorActivity
 * @Author: maiduoduo
 * @BLOG: https://blog.csdn.net/Maiduoudo
 * @Date: 2020/10/15
 * @des: 提取图片主题色-列表主题色吸取设置背景
 */
@Layout(R.layout.activity_paletteimage_listcolor)
@DarkStatusBarTheme(true)
public class IPaletteListColorActivity extends IBaseActivity {
    @BindView(R.id.blur) BlurView blur;
    @BindView(R.id.recycler) RecyclerView mRecycler;
    private List<Integer> imageList;
    private BaseQuickAdapter mAdapter;


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

        initRecyclerview();

    }


    @Override
    public void initDatas() {
        if (imageList != null) imageList.clear();
        else imageList = new ArrayList<>();

        for (int m : GlobalDataProvider.mImgs) {
            imageList.add(m);
        }
        mAdapter.setNewData(imageList);

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

    private void initRecyclerview() {
        mRecycler.setLayoutManager(new GridLayoutManager(this,2));
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(mAdapter = new BaseQuickAdapter<Integer, BaseViewHolder>(R.layout.item_paletteimage_list_color, imageList) {
            @Override
            protected void convert(BaseViewHolder helper, Integer item) {
                IPaletteImageView mPaletteImageView = helper.getView(R.id.palette);
                LinearLayout mLinearLayout = helper.getView(R.id.linear);
                TextView title = helper.getView(R.id.tv1);
                TextView content = helper.getView(R.id.tv2);

                mPaletteImageView.setBitmap(BitmapFactory.decodeResource(getResources(), item));

                initPaletteColor(mPaletteImageView,mLinearLayout,title,content);
            }
        });
    }


    private void initPaletteColor(IPaletteImageView mPaletteImageView, LinearLayout mLinearLayout, TextView title, TextView content) {
        mPaletteImageView.setOnParseColorListener(new OnParseColorListener() {
            @Override
            public void onComplete(IPaletteImageView paletteImageView) {
                int[] vibrant = paletteImageView.getVibrantColor();
                int[] vibrantDark = paletteImageView.getDarkVibrantColor();
                int[] vibrantLight = paletteImageView.getLightVibrantColor();
                int[] muted = paletteImageView.getMutedColor();
                int[] mutedDark = paletteImageView.getDarkMutedColor();
                int[] mutedLight = paletteImageView.getLightMutedColor();
                List<int[]> list = new ArrayList<int[]>();
                list.clear();
                list.add(vibrant);
                list.add(vibrantDark);
                list.add(vibrantLight);
                list.add(muted);
                list.add(mutedDark);
                list.add(mutedLight);
                for (int i = 0; i <list.size() ; i++) {
                    int[] arry = list.get(i);
                    if (arry == null) list.remove(arry);
                }
                int[] arry = list.get(new Random().nextInt(list.size()-1));
                if(arry != null) {
                    title.setTextColor(arry[1]);
                    content.setTextColor(arry[0]);
                    mLinearLayout.setBackgroundColor(arry[2]);
                }
            }

            @Override
            public void onFail() {

            }
        });
    }



}
