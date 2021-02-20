package com.ejoy.tool.ui.data.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejoy.tool.R;

import java.util.List;



public class ISLTabRecyclerViewAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private static final String TAG = "ISLTabRecyclerViewAdapter";
    private Context context;
    private String text;
    public ISLTabRecyclerViewAdapter(int layoutResId, List<String> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int position = helper.getAdapterPosition();
        TextView textView = helper.getView(R.id.texView);


        textView.setText(text + (position + 1)+"");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,textView.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }




}
