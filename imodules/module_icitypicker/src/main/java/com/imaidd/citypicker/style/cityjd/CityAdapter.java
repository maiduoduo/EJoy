package com.imaidd.citypicker.style.cityjd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.imaidd.citypicker.bean.CityBean;
import com.imaidd.citypicker.style.citypickerview.R;

import java.util.List;

import static com.imaidd.citypicker.style.cityjd.JDConst.INDEX_INVALID;

/**
 * 城市
 */
public class CityAdapter extends BaseAdapter {

    Context context;

    List<CityBean> mCityList;

    private int cityIndex = INDEX_INVALID;

    public CityAdapter(Context context, List<CityBean> mCityList) {
        this.context = context;
        this.mCityList = mCityList;
    }

    public int getSelectedPosition() {
        return this.cityIndex;
    }

    public void updateSelectedPosition(int index) {
        this.cityIndex = index;
    }

    @Override
    public int getCount() {
        return mCityList.size();
    }

    @Override
    public CityBean getItem(int position) {
        return mCityList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return Long.parseLong(mCityList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pop_jdcitypicker_item, parent, false);

            holder = new Holder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.selectImg = (ImageView) convertView.findViewById(R.id.selectImg);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        CityBean item = getItem(position);
        holder.name.setText(item.getName());

        boolean checked = cityIndex != INDEX_INVALID && mCityList.get(cityIndex).getId().equals(item.getId());
        holder.name.setEnabled(!checked);
        holder.selectImg.setVisibility(checked ? View.VISIBLE : View.GONE);


        return convertView;
    }


    class Holder {
        TextView name;
        ImageView selectImg;
    }
}
