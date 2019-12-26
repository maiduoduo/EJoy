package com.ejoy.tool.ui.data.adapter;


import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.imaidd.citypicker.style.citylist.bean.CityInfoBean;

import java.util.List;

/**
 * CN:      CHMainAdpter
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/21
 * Des:    城市选择-三级-省级选择
 */
public class IThreeProvinceAdpter extends BaseQuickAdapter<CityInfoBean,BaseViewHolder> {
    private static final String TAG = "adapter";
    private Context mContext;
    public IThreeProvinceAdpter(int layoutResId, List<CityInfoBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CityInfoBean item) {
        int position = helper.getAdapterPosition();
        helper.setText(R.id.default_item_city_name_tv, item.getName() == null ? "" : item.getName());
    }
}

