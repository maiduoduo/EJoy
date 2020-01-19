package com.ejoy.tool.ui.activity.bottomsheet;
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
import android.view.View;
import android.widget.TextView;

import com.ejoy.tool.R;
import com.ejoy.tool.ui.activity.popupwindow.IPopupWindowPopmenuActivity;
import com.ejoy.tool.ui.base.base_activity.BaseActivity;
import com.ejoy.tool.ui.data.resource.GlobalDataProvider;
import com.ejoy.tool.ui.mvp.base.BasePresenter;
import com.module.ires.bean.utils.EDensityUtils;
import com.module.iviews.popup.AdapterItem;
import com.module.iviews.popup.EUISimpleAdapter;
import com.module.iviews.popup.EUISimplePopup;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * CN:      IBottomSheetActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/26
 * Des:    TODO:BottomSheetDialog
 */
public class IBottomSheetActivity extends BaseActivity {
    public EUISimplePopup mListPopup;
    @BindView(R.id.tvBaseuse)
    TextView tvBaseuse;

    @Override
    protected void initRestore(@Nullable Bundle savedInstanceState) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_bottomsheet;
    }

    @Override
    protected Object registSatusbarBgcolor() {
        return defalutStatus3;
    }

    @Override
    protected void initView(View mRootView) {
        mListPopup = new EUISimplePopup(_mActivity, GlobalDataProvider.bottomsheetItems);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void addListener() {

    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    /**
     * Author： JSYL_Dingcl
     * Des  :   TODO: 自定义BottomSheet
     */
    public void customBS(View view) {
        showActivity(_mActivity, ICustomBottomSheetActivity.class);
    }

    /**
     * Author： JSYL_Dingcl
     * Des  :   TODO: 官方BottomSheet
     */
    public void gfBS(View view) {
        showActivity(_mActivity, IGFBottomSheetActivity.class);
    }


    /**
     * 结合BottomSheetBehavior使用
     *
     * @param view
     */
    public void bottomSheetBehavior(View view) {
        initListPopup();
    }

    public void ivBack(View view) {
        finish();
    }

    /**
     * 普通选择框
     */
    private void initListPopup() {
        mListPopup.create(EDensityUtils.dp2px(_mActivity, 250), new EUISimplePopup.OnPopupItemClickListener() {
            @Override
            public void onItemClick(EUISimpleAdapter adapter, AdapterItem item, int position) {
                iToast.showIDefaultImgResToast(item.getTitle().toString());
                switch (position) {
                    case 0:
                        View view = adapter.getView(position, null, null);
                        showActivity(_mActivity,IBottomSheetRecyclerActivity.class,R.anim.push_bottom_in, R.anim.push_bottom_out);
                        break;
                    case 1:
                        showActivity(_mActivity, IBottomShhetScrollActivity.class, R.anim.push_bottom_in, R.anim.push_bottom_out);
                        break;
                    default:
                        break;
                }
                mListPopup.dismiss();
            }
        })
                .setHasDivider(true);
        mListPopup.showDown(tvBaseuse);
    }

}
