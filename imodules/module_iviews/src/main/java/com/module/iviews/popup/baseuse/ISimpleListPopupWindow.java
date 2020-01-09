package com.module.iviews.popup.baseuse;
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

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;

import com.module.ires.bean.utils.EDensityUtils;
import com.module.ires.bean.utils.EResUtils;
import com.module.iviews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * CN:      ISimpleListPopupWindow
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/6
 * Des:    TODO:ListPopupWindow使用（根据使用体验不推荐使用此控件）
 *         TODO:对于下拉选择框，对于大多数需求建议使用Spinner，比较灵活可定义。
 *         TODO:当然可以自由选择封装
 */
public class ISimpleListPopupWindow extends ListPopupWindow {

    private Context mContext;
    private int mWidth;
    private int mHeight;
    private boolean mModal;
    private ISimpleListPopupWindow mListPop;
    private View mAnchorView;
//    private List<AdapterItem> mData;
    private List<String> mData;
    private IOnPopListItemListener onPopListItemListener = null;

    public ISimpleListPopupWindow(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }


    /**
     * 展示
     *
     * @param isTextItem true：简单文本展示，false:图标+文本
     */
    public void showPopup(final boolean isTextItem) {
        mListPop = new ISimpleListPopupWindow(mContext);
        mListPop.setAdapter(new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, mData));
        mListPop.setWidth(mWidth);
        mListPop.setHeight(mHeight);
        mListPop.setAnchorView(mAnchorView);
        mListPop.setBackgroundDrawable(EResUtils.getDrawable(mContext,R.drawable.shape_gray));
        mListPop.setModal(true);
        /* 设置列表的高度 ----- */
        mListPop.setHeight((int) (EDensityUtils.getScreenHeight(mContext) * 0.4));
        mListPop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (onPopListItemListener != null){
                    onPopListItemListener.OnItemClick(parent,view,position,id);
                }
                mListPop.dismiss();
            }
        });
        mListPop.show();
    }

//    public void dismiss(){
//        if (mListPop!=null){
//            mListPop.dismiss();
//        }
//    }


    public static class Builder {
        private Context context;
        private boolean modal;
        private View mAnchorView;
//        private List<AdapterItem> newData;
        private List<String> newData;
        private ISimpleListPopupWindow mBuildPopupWindow = null;
        private int width;
        private int height;


        public Builder(Context context) {
            this.context = context;
            if (this.mBuildPopupWindow == null) {
                synchronized (ISimpleListPopupWindow.class) {
                    if (this.mBuildPopupWindow == null) {
                        this.mBuildPopupWindow = new ISimpleListPopupWindow(context);
                    }
                }
            }
            this.modal = true;
            this.mAnchorView = null;
            this.width  = WindowManager.LayoutParams.WRAP_CONTENT;
            this.height = WindowManager.LayoutParams.WRAP_CONTENT;
            this.newData = new ArrayList<>();
        }


        /**
         * 数据
         * @param datas
         * @return
         */
        public Builder seListNewData(List<String> datas) {
            this.newData = datas;
            return this;
        }

        /**
         * 锚点
         *
         * @param anchorView 设置ListPopupWindow的锚点，即关联PopupWindow的显示位置和这个锚点
         * @return
         */
        public Builder setAnchorView(View anchorView) {
            this.mAnchorView = anchorView;
            return this;
        }


        /**
         * 设置是否是模式
         *
         * @param modal
         * @return
         */
        public Builder setModal(boolean modal) {
            this.modal = modal;
            return this;
        }

        public Builder setWidth(int width1) {
            this.width = width1;
            return this;
        }


        public ISimpleListPopupWindow build() {
            setPopupWindowConfig(mBuildPopupWindow);
            return mBuildPopupWindow;
        }

        private void setPopupWindowConfig(ISimpleListPopupWindow window) {
            if (mAnchorView == null) {
                throw new PopException("AnchorView can't be null.", "1");
            }
            if (context == null) {
                throw new PopException("context can't be null.", "2");
            } else {
                window.mContext = this.context;
            }
            window.mData = newData;
            window.mAnchorView = this.mAnchorView;
            window.mModal = this.modal;
            window.mListPop = this.mBuildPopupWindow;
            window.mWidth = this.width;
            window.mHeight = this.height;
        }
    }

    public static class PopException extends RuntimeException {

        private String errorCode;

        public PopException(String errorCode) {
            this.errorCode = errorCode;
        }

        public PopException(String message, String errorCode) {
            super(message);
            this.errorCode = errorCode;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        @Override
        public String toString() {
            return super.toString() + ",MException{" +
                    "errorCode='" + errorCode + '\'' + '}';
        }
    }

    public ISimpleListPopupWindow addOnPopListItemListener(IOnPopListItemListener onPopListItemListener){
        this.onPopListItemListener = onPopListItemListener;
        return this;
    }

    public interface IOnPopListItemListener{
        void OnItemClick(AdapterView<?> parent,View view, int position, long id);
    }

}
