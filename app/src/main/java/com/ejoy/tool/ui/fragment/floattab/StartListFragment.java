package com.ejoy.tool.ui.fragment.floattab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ejoy.tool.R;
import com.ejoy.tool.app.AppConstant;
import com.ejoy.tool.common.bean.ListInfo;
import com.ejoy.tool.floattab.View.HorizontalListView;
import com.ejoy.tool.floattab.View.MultiColumnListView;
import com.ejoy.tool.floattab.tools.UiUtils;
import com.ejoy.tool.ui.data.adapter.SiDaListViewAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2015/11/9.
 */
public class StartListFragment extends Fragment {
    private ArrayList<ListInfo> datas;
    private MultiColumnListView mListView;
    private static HorizontalListView mIndicator = null;
    private static final String[] TITLE = new String[]{"第一个", "第二个", "第三个", "第四个", "第五个", "第六个", "第七个", "第八个",
            "第九个", "第十个", "第11个", "第12个", "第13个", "第14个", "第15个", "第16个", "第17个", "第18个",
            "第19个", "第20个", "第21个", "第22个", "第23个", "第24个", "第25个", "第26个", "第27个", "第28个",
            "第29个", "第30个"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(UiUtils.getContext(), R.layout.starlist_viewpager_item, null);
        mListView = (MultiColumnListView) view.findViewById(R.id.star_list_viewpage_listview);
        initData();
        mListView.setAdapter(new SiDaListViewAdapter(datas));
        return view;
    }

    private void initData() {
        datas = new ArrayList<ListInfo>();
        for (int i = 0; i < AppConstant.url3.length; i++) {
            String title = TITLE[i];
            String icon = AppConstant.url3[i];
            ListInfo info = new ListInfo(title, icon);
            datas.add(info);
        }

    }
}
