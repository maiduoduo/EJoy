package com.ejoy.tool.ui.data.adapter;


import android.app.Activity;
import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.IImmerseBean;
import com.ejoy.tool.common.bean.MainItemBean;
import com.ejoy.tool.scaffold.utils.IToast;
import com.imaidd.citypicker.style.citylist.bean.CityInfoBean;

import java.util.List;

/**
 * CN:      IImmerseBlurAdpter
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/03/09
 * Des:    模糊图片
 */
public class IImmerseBlurAdpter extends BaseMultiItemQuickAdapter<IImmerseBean, BaseViewHolder> {
    private static final String TAG = "adapter";
    private Context mContext;
    public IToast iToast;


    public IImmerseBlurAdpter(Activity context, List<IImmerseBean> data) {
        super(data);
        this.mContext = context;
        //初始化自定义Toast
        iToast = new IToast().builder();
        addItemType(IImmerseBean.TYPE_HEADER, R.layout.item_immerseblur_header);
        addItemType(IImmerseBean.TYPE_CONTENT, R.layout.item_immerseblur_content);
    }



    @Override
    protected void convert(BaseViewHolder helper, IImmerseBean item) {
        int position = helper.getAdapterPosition();
        switch (helper.getItemViewType()) {
            case IImmerseBean.TYPE_HEADER:
                bindHeaderData(helper, item);
                break;
            case IImmerseBean.TYPE_CONTENT:
                bindContentListData(helper, item);
                break;
            default:
                break;
        }
    }


    /**
     * header
     * @param helper
     * @param item
     */
    private void bindHeaderData(BaseViewHolder helper, IImmerseBean item) {

    }


    /**
     * list
     * @param helper
     * @param item
     */
    private void bindContentListData(BaseViewHolder helper, IImmerseBean item) {

    }
}

