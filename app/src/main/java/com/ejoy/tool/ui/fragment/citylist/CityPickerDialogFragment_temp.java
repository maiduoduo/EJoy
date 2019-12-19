package com.ejoy.tool.ui.fragment.citylist;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;
import com.ejoy.tool.common.bean.CityCurrentAreaBean;
import com.ejoy.tool.common.db.CitysBean;
import com.ejoy.tool.common.db.engine.CitiesDaoHelper;
import com.ejoy.tool.common.listener.InnerListener;
import com.ejoy.tool.common.listener.OnPickListener;
import com.ejoy.tool.scaffold.utils.ScreenUtil;
import com.ejoy.tool.scaffold.view.decorator.DividerItemDecoration;
import com.ejoy.tool.scaffold.view.decorator.SectionItemDecoration;
import com.ejoy.tool.scaffold.view.widget.ExpandLayout;
import com.ejoy.tool.scaffold.view.widget.RecyclerViewNoBugLinearLayoutManager;
import com.ejoy.tool.scaffold.view.widget.SideIndexBar;
import com.ejoy.tool.ui.data.adapter.CpCitySelectListAdapter;
import com.ejoy.tool.ui.fragment.citylist.model.City;
import com.ejoy.tool.ui.fragment.citylist.model.HotCity;
import com.ejoy.tool.ui.fragment.citylist.model.LocateState;
import com.ejoy.tool.ui.fragment.citylist.model.LocatedCity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * CN:      CpCitySelectListAdapter
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/24
 *  城市选择DialogFragment
 */
public class CityPickerDialogFragment_temp extends DialogFragment implements TextWatcher, View.OnClickListener, SideIndexBar.OnIndexTouchedChangedListener, InnerListener {
    private static final String TAG = CityPickerDialogFragment_temp.class.getSimpleName();
    private View mContentView;
    private RecyclerView mRecyclerView;
    private RecyclerView mExpandRecyclerView;
    private ExpandLayout mExpandLayout;
    private LinearLayout toggleQX;
    private ImageView ivToggleArraw;
    private View mEmptyView;
    private TextView mOverlayTextView;
    private SideIndexBar mIndexBar;
    private EditText mSearchBox;
    private ImageView mCancelBtn;
    private ImageView mClearAllBtn;

    private LinearLayoutManager mLayoutManager;
    private CpCitySelectListAdapter mAdapter;
    private List<CitysBean> mAllCities;
    private List<HotCity> mHotCities;
    private List<CitysBean> mResults;


    private int height;
    private int width;

    private boolean enableAnim = false;
    private int mAnimStyle = R.style.DefaultCityPickerAnimation;
    private LocatedCity mLocatedCity;
    private int locateState;
    private OnPickListener mOnPickListener;
    private Animation rotate;
    private  Context mContext;

    /**
     * 获取实例
     * @param enable 是否启用动画效果
     * @return
     */
    public static CityPickerDialogFragment_temp newInstance(boolean enable){
        final CityPickerDialogFragment_temp fragment = new CityPickerDialogFragment_temp();
        Bundle args = new Bundle();
        args.putBoolean("cp_enable_anim", enable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CityPickerStyle);
        mContext = getActivity();
    }

    public void setLocatedCity(LocatedCity location){
        mLocatedCity = location;
    }

    public void setHotCities(List<HotCity> data){
        if (data != null && !data.isEmpty()){
            this.mHotCities = data;
        }
    }

    @SuppressLint("ResourceType")
    public void setAnimationStyle(@StyleRes int resId){
        this.mAnimStyle = resId <= 0 ? mAnimStyle : resId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.cp_dialog_city_picker, container, false);
        return mContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDBData();
        initData();
        initViews();
    }

    /**
     * 初始化数据库数据
     */
    private List<CitysBean> citysBeanList;
    private void initDBData() {
        if (citysBeanList != null)citysBeanList.clear();
        else citysBeanList = new ArrayList<>();
        List<CitysBean> citysBeans = CitiesDaoHelper.queryAll();
        citysBeanList.addAll(citysBeans);
        Log.e(TAG, "onCreate------------->: \n"+new Gson().toJson(citysBeans));
    }

    private void initViews() {
        mRecyclerView = mContentView.findViewById(R.id.cp_city_recyclerview);
        mExpandLayout = mContentView.findViewById(R.id.expandLayout);
        mExpandRecyclerView = mContentView.findViewById(R.id.recyclerViewExpand);
        toggleQX = mContentView.findViewById(R.id.toggleQX);
        ivToggleArraw = mContentView.findViewById(R.id.ivToggleArraw);
        //设置切换区县布局
        //初始化工时详情
        mExpandLayout.initExpand(false);
        //创建动画
        rotate = AnimationUtils.loadAnimation(mContext, R.anim.arrow_rotate);
        //设置为线性旋转
        rotate.setInterpolator(new LinearInterpolator());
        initRecyclerView();
        setAdapter();

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new SectionItemDecoration(getActivity(), mAllCities), 0);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),R.color.black_11), 1);
        mAdapter = new CpCitySelectListAdapter(getActivity(), mAllCities, mHotCities, locateState);
        mAdapter.autoLocate(true);
        mAdapter.setInnerListener(this);
        mAdapter.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //确保定位城市能正常刷新
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    mAdapter.refreshLocationItem();
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //加载更多功能的代码
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dx > 0) {
                    //大于0表示正在向右滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0表示停止或向左滚动
                    isSlidingToLast = false;
                }

                if (dy > 40){
                    if (mExpandLayout.isExpand()){
                        mExpandLayout.toggleExpand();
                        rotate.setFillAfter(!rotate.getFillAfter());//每次都取相反值，使得可以不恢复原位的旋转
                        ivToggleArraw.startAnimation(rotate);
                    }
                }
            }
        });

        mEmptyView = mContentView.findViewById(R.id.cp_empty_view);
        mOverlayTextView = mContentView.findViewById(R.id.cp_overlay);

        mIndexBar = mContentView.findViewById(R.id.cp_side_index_bar);
        mIndexBar.setNavigationBarHeight(ScreenUtil.getNavigationBarHeight(getActivity()));
        mIndexBar.setOverlayTextView(mOverlayTextView)
                .setOnIndexChangedListener(this);

        mSearchBox = mContentView.findViewById(R.id.cp_search_box);
        mSearchBox.addTextChangedListener(this);

        mCancelBtn = mContentView.findViewById(R.id.cp_cancel);
        mClearAllBtn = mContentView.findViewById(R.id.cp_clear_all);
        mCancelBtn.setOnClickListener(this);
        mClearAllBtn.setOnClickListener(this);

        toggleQX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandLayout.toggleExpand();
                rotate.setFillAfter(!rotate.getFillAfter());//每次都取相反值，使得可以不恢复原位的旋转
                ivToggleArraw.startAnimation(rotate);
            }
        });
    }

    private List<CityCurrentAreaBean> loseCardData;
    private List<CityCurrentAreaBean> getQXData() {

        loseCardData = new ArrayList<>();
        loseCardData.add(new CityCurrentAreaBean("全城",320100));
        loseCardData.add(new CityCurrentAreaBean("玄武区",320102));
        loseCardData.add(new CityCurrentAreaBean("六合区",320116));
        loseCardData.add(new CityCurrentAreaBean("秦淮区",320104));
        loseCardData.add(new CityCurrentAreaBean("建邺区",320105));
        loseCardData.add(new CityCurrentAreaBean("鼓楼区",320106));
        loseCardData.add(new CityCurrentAreaBean("浦口区",320111));
        loseCardData.add(new CityCurrentAreaBean("栖霞区",320113));
        loseCardData.add(new CityCurrentAreaBean("雨花台区",320114));
        loseCardData.add(new CityCurrentAreaBean("江宁区",320115));
        loseCardData.add(new CityCurrentAreaBean("溧水区",320117));
        loseCardData.add(new CityCurrentAreaBean("高淳区",320118));
        return loseCardData;
    }

    private void initRecyclerView() {
        mExpandRecyclerView.setHasFixedSize(true);
        RecyclerViewNoBugLinearLayoutManager layoutManager = new RecyclerViewNoBugLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false);
        mExpandRecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
        //添加item分割线
//        mExpandRecyclerView.addItemDecoration(new DashlineItemDivider("#B2B2B2"));

    }

    private BaseQuickAdapter expandAdapter;
    private void setAdapter() {
        List<CityCurrentAreaBean> selectData = getQXData();
        mExpandRecyclerView.setAdapter(expandAdapter = new BaseQuickAdapter<CityCurrentAreaBean,
                BaseViewHolder>(R.layout.cp_item_qx, selectData) {
            @Override
            protected void convert(BaseViewHolder helper, CityCurrentAreaBean item) {
                helper.setText(R.id.cp_gird_qx,item.getAreaname());
                if ("全城".equals(item.getAreaname())){
                    ((TextView)helper.getView(R.id.cp_gird_qx)).setBackgroundResource(R.drawable.shape_select_item);
                }else {
                    ((TextView)helper.getView(R.id.cp_gird_qx)).setBackgroundResource(R.drawable.shape_item);
                }
            }

        });

        expandAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dismiss();
                List<CityCurrentAreaBean>  data = adapter.getData();
                CityCurrentAreaBean cityCurrentAreaBean = data.get(position);
                if (mOnPickListener != null){
                    mOnPickListener.onPick(position, new City(cityCurrentAreaBean.getAreaname(),"","",cityCurrentAreaBean.getAreacode()+"",""));
                }
            }
        });
    }



    private void initData() {
        Bundle args = getArguments();
        if (args != null) {
            enableAnim = args.getBoolean("cp_enable_anim");
        }
        //初始化热门城市
        if (mHotCities == null || mHotCities.isEmpty()) {
            mHotCities = new ArrayList<>();
            mHotCities.add(new HotCity("北京", "北京", "101010100",""));
            mHotCities.add(new HotCity("上海", "上海", "101020100",""));
            mHotCities.add(new HotCity("广州", "广东", "101280101",""));
            mHotCities.add(new HotCity("深圳", "广东", "101280601",""));
            mHotCities.add(new HotCity("天津", "天津", "101030100",""));
            mHotCities.add(new HotCity("杭州", "浙江", "101210101",""));
            mHotCities.add(new HotCity("南京", "江苏", "101190101",""));
            mHotCities.add(new HotCity("成都", "四川", "101270101",""));
            mHotCities.add(new HotCity("武汉", "湖北", "101200101",""));
        }
        //初始化定位城市，默认为空时会自动回调定位
        if (mLocatedCity == null){
            mLocatedCity = new LocatedCity(getString(R.string.cp_locating), "未知", "0","");
            locateState = LocateState.LOCATING;
        }else{
            locateState = LocateState.SUCCESS;
        }

//        dbManager = new DBManager(getActivity());
//        mAllCities = dbManager.getAllCities();
        mAllCities = new ArrayList<>();
        mAllCities.addAll(citysBeanList);
        mAllCities.add(0, mLocatedCity);
        mAllCities.add(1, new HotCity("热门城市", "未知", "0",""));
        mResults = mAllCities;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK){
                    if (mOnPickListener != null){
                        mOnPickListener.onCancel();
                    }
                }
                return false;
            }
        });

        measure();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(width, height - ScreenUtil.getStatusBarHeight(getActivity()));
            if (enableAnim) {
                window.setWindowAnimations(mAnimStyle);
            }
        }
    }

    //测量宽高
    private void measure() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            height = dm.heightPixels;
            width = dm.widthPixels;
        }else{
            DisplayMetrics dm = getResources().getDisplayMetrics();
            height = dm.heightPixels;
            width = dm.widthPixels;
        }
    }

    /** 搜索框监听 */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        String keyword = s.toString();
        if (TextUtils.isEmpty(keyword)){
            mClearAllBtn.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.GONE);
            mResults = mAllCities;
            ((SectionItemDecoration)(mRecyclerView.getItemDecorationAt(0))).setData(mResults);
            mAdapter.updateData(mResults);
        }else {
            mClearAllBtn.setVisibility(View.VISIBLE);
            //开始数据库查找
            //TODO:---------查找筛选--------------
            mResults = CitiesDaoHelper.queryCnameWithLike(keyword);
//            mResults = dbManager.searchCity(keyword);
//            mResults = new ArrayList<City>();
            ((SectionItemDecoration)(mRecyclerView.getItemDecorationAt(0))).setData(mResults);
            if (mResults == null || mResults.isEmpty()){
                mEmptyView.setVisibility(View.VISIBLE);
            }else {
                mEmptyView.setVisibility(View.GONE);
                mAdapter.updateData(mResults);
            }
        }
        mRecyclerView.scrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.cp_cancel) {
            dismiss();
            if (mOnPickListener != null){
                mOnPickListener.onCancel();
            }
        }else if(id == R.id.cp_clear_all){
            mSearchBox.setText("");
        }
    }


    @Override
    public void onIndexChanged(String index, int position) {
        //滚动RecyclerView到索引位置
        mAdapter.scrollToSection(index);
    }

    public void locationChanged(LocatedCity location, int state){
        mAdapter.updateLocateState(location, state);
    }


    @Override
    public void dismiss(int position, CitysBean data) {
        dismiss();
        if (mOnPickListener != null){
            mOnPickListener.onPick(position, data);
        }
    }

    @Override
    public void locate(){
        if (mOnPickListener != null){
            mOnPickListener.onLocate();
        }
    }

    public void setOnPickListener(OnPickListener listener){
        this.mOnPickListener = listener;
    }
}
