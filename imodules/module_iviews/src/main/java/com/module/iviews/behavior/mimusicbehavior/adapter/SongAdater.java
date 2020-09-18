package com.module.iviews.behavior.mimusicbehavior.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.module.iviews.R;

import java.util.List;

public class SongAdater extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public SongAdater(@Nullable List<Integer> data) {
        super(R.layout.item_song_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        helper.setText(R.id.tv_item_muisc_no,String.valueOf(item+1));
    }
}
