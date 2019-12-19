package com.imaidd.citypicker.style.citythreelist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.imaidd.citypicker.style.citylist.bean.CityInfoBean;
import com.imaidd.citypicker.style.citylist.utils.CityListLoader;
import com.imaidd.citypicker.style.citypickerview.R;
import com.imaidd.citypicker.widget.RecycleViewDividerForList;

import java.util.List;

import static com.imaidd.citypicker.style.citylist.utils.CityListLoader.BUNDATA;

public class ProvinceActivity extends Activity {
    
    private TextView mCityNameTv;
    
    private RecyclerView mCityRecyclerView;
    
    public static final int RESULT_DATA = 1001;
    
    private ICityBean provinceBean = new ICityBean();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        initView();
        setData();
        
    }
    
    private void setData() {
        
        final List<CityInfoBean> cityList = CityListLoader.getInstance().getProListData();
        if (cityList == null) {
            return;
        }
        
        CityAdapter cityAdapter = new CityAdapter(ProvinceActivity.this, cityList);
        mCityRecyclerView.setAdapter(cityAdapter);
        cityAdapter.setOnItemClickListener(new CityAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position) {
                
                provinceBean.setId(cityList.get(position).getId());
                provinceBean.setName(cityList.get(position).getName());
                Intent intent = new Intent(ProvinceActivity.this, CityActivity.class);
                intent.putExtra(BUNDATA, cityList.get(position));
                startActivityForResult(intent, RESULT_DATA);
                
            }
        });
        
    }
    
    private void initView() {
        mCityNameTv = (TextView) findViewById(R.id.cityname_tv);
        mCityNameTv.setText("选择省份");
        mCityRecyclerView = (RecyclerView) findViewById(R.id.city_recyclerview);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.addItemDecoration(new RecycleViewDividerForList(this, LinearLayoutManager.HORIZONTAL, true));
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_DATA && data != null) {
            ICityBean area = data.getParcelableExtra("area");
            ICityBean city = data.getParcelableExtra("city");
            Intent intent = new Intent();
            intent.putExtra("province", provinceBean);
            intent.putExtra("city", city);
            intent.putExtra("area", area);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    
}
