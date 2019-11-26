package com.ejoy.tool.scaffold.utils;
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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.app.App;


/**
 * CN:      IToast
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/11/img_g
 * Des:    Toast工具
 */
public class IToastTest {
    /** 之前显示的内容 */
    private static String oldMsg;
    /** Toast对象 */
    private static Toast mToast = null ;
    private static Toast mNormalToast = null ;
    /** 第一次时间 */
    private static long oneTime = 0 ;
    /** 第二次时间 */
    private static long twoTime = 0 ;
    private Context mContext;

    /** Place the object in the center of its container in both the vertical
     *  and horizontal axis, not changing its size. */
    public static int TOAST_GRAVITY_FLAG = 100;
    public static final int TOAST_CENTER = Gravity.CENTER;
    public static final int TOAST_TOP = Gravity.TOP;
    public static final int TOAST_BOTTOM = Gravity.BOTTOM;
    public static final int TOAST_LEFT = Gravity.LEFT;
    public static final int TOAST_RIGHT = Gravity.RIGHT;
    public static final int TOAST_NO_GRAVITY = Gravity.NO_GRAVITY;
    public static final int TOAST_DURATION_SHORT = Toast.LENGTH_SHORT;
    public static final int TOAST_DURATION_LONG = Toast.LENGTH_LONG;

//    private boolean isShowDefault = true;
    private TextView mTipText;
    private ImageView mTipImageView;
    private View mView;


    /**
     * 在使用自定义IToast时要先初始化
     * 初始化示例：IToast itoast = new Itoast().builder();
     * 调用示例（成功）： iToast.doTipText("发送成功")
     *                         .doDuration(0)   //时长
     *                         .doGravity(0)    //位置
     *                         .showISuccessToast() //成功的弹窗
     *                         .showIt();   //显示
     */
    public IToastTest() {
        mContext = App.getAppContext();
    }

    public IToastTest builder() {
        if (mToast == null){
            synchronized (IToastTest.class){
                if (mToast == null){
                    mToast = new Toast(App.getAppContext());
                }
            }
        }
//        if (withCustom) {
//            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            mView = inflater.inflate(R.layout.layout_itoast, null);
//            mTipText = mView.findViewById(R.id.toast_msg);
//            mTipImageView = mView.findViewById(R.id.toast_iv);
//        }
        return this;
    }


//    public static Toast mToast;
//
//    public static void showToast( String msg) {
//        if (mToast == null) {
//            mToast = Toast.makeText(App.getAppContext(), "", Toast.LENGTH_SHORT);
//        }
//        mToast.setText(msg);
//        mToast.show();
//    }









  /*  *//**
     * 设置内容
     * @param tipText
     * @return
     *//*
    private IToast doTipText(String tipText) {
        ObjectUtil.requireNonNull(mContext, "context is null");
        if (tipText == null) {
            tipText = "提示信息为空";
        }
        if (mTipText != null) {
            mTipText.setText(tipText);
        }
        return this;
    }

    *//**
     * 设置显示位置
     * @param gravity
     *          0：屏幕中间
     *          1：屏幕顶部
     *          2：屏幕底部
     *          3：屏幕左边
     *          4：屏幕右边
     *          5：默认位置
     *//*
    public IToast doGravity(int gravity) {
        if (0 == gravity) TOAST_GRAVITY_FLAG = TOAST_CENTER;
        if (1 == gravity) TOAST_GRAVITY_FLAG = TOAST_TOP;
        if (2 == gravity) TOAST_GRAVITY_FLAG = TOAST_BOTTOM;
        if (3 == gravity) TOAST_GRAVITY_FLAG = TOAST_LEFT;
        if (4 == gravity) TOAST_GRAVITY_FLAG = TOAST_RIGHT;
        if (5 == gravity) TOAST_GRAVITY_FLAG = TOAST_NO_GRAVITY;
        mToast.setGravity(TOAST_GRAVITY_FLAG == 100 ? TOAST_CENTER : TOAST_GRAVITY_FLAG,0,0);
        return this;
    }

    *//**
     * 显示时长，
     * @param duration 对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     *                 0:Toast.LEHGTH_SHORT
     *                 1:Toast.LENGTH_LONG
     * @return
     *//*
    public IToast doDuration(int duration) {
        mToast.setDuration(duration);
        return this;
    }

    *//**
     * 添加自定义View到容器
     * @return
     *//*
    private IToast addIView() {
        if (mView != null) {
            mToast.setView(mView);
        }
        return this;
    }

    *//**
     * show
     * @return
     *//*
    private IToast showIt() {
        addIView();
        mToast.show();
        return this;
    }




    *//**
     * 成功
     * @return
     *      if (mNextView == null) {
     *         throw new RuntimeException("setView must have been called");
     *      }
     *//*
    public IToast showISuccessToast(String tipText) {
        doTipText(tipText);
        if (mTipImageView != null) mTipImageView.setImageResource(R.drawable.itoast_ico_done);
        showIt();
        return this;
    }

    *//**
     * 失败
     * @return
     *//*
    public IToast showIFailToast(String tipText) {
        doTipText(tipText);
        if (mTipImageView != null) mTipImageView.setImageResource(R.drawable.itoast_ico_fail);
        showIt();
        return this;
    }


    *//**
     * 错误
     * @return
     *//*
    public IToast showIErrorToast(String tipText) {
        doTipText(tipText);
        if (mTipImageView != null) mTipImageView.setImageResource(R.drawable.itoast_ico_error_bold);
        showIt();
        return this;
    }


    *//**
     * 提示
     * @return
     *//*
    public IToast showIInfoToast(String tipText) {
        doTipText(tipText);
        if (mTipImageView != null) mTipImageView.setImageResource(R.drawable.itoast_ico_info);
        showIt();
        return this;
    }*/





    //-------------------------------------默认调用：不再主动代码设置Gravity/Duration-----------------------------------------------------
 /*   *//**
     * 成功（默认调用）
     * @param tipText
     * @return
     *//*
    public IToast showDefaultISuccessToast(String tipText) {
        doTipText(tipText);
        if (mTipImageView != null) mTipImageView.setImageResource(R.drawable.itoast_ico_done);
        mToast.setGravity(0,0,0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        showIt();
        return this;
    }

    *//**
     * 失败（默认调用）
     * @param tipText
     * @return
     *//*
    public IToast showDefaultIFailToast(String tipText) {
        doTipText(tipText);
        if (mTipImageView != null) mTipImageView.setImageResource(R.drawable.itoast_ico_fail);
        mToast.setGravity(0,0,0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        showIt();
        return this;
    }

    *//**
     * 错误（默认调用）
     * @return
     *//*
    public IToast showDefaultIErrorToast(String tipText) {
        doTipText(tipText);
        if (mTipImageView != null) mTipImageView.setImageResource(R.drawable.itoast_ico_error_bold);
        mToast.setGravity(0,0,0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        showIt();
        return this;
    }

    *//**
     * 提示（默认调用）
     * @return
     *//*
    public IToast showDefaultIInfoToast(String tipText) {
        doTipText(tipText);
        if (mTipImageView != null) mTipImageView.setImageResource(R.drawable.itoast_ico_info);
        mToast.setGravity(0,0,0);
        mToast.setDuration(Toast.LENGTH_SHORT);
        showIt();
        return this;
    }*/





    //------------------------原始Toast-------------------------
    /**
     * 显示Toast
     *   根据显示时间不重复创建实例
     * @param context
     * @param message
     */
    public static void showNoRepeatToast(Context context,String message){
        if(mNormalToast == null){
            mNormalToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mNormalToast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_SHORT){
                    mNormalToast.show() ;
                }
            }else{
                oldMsg = message ;
                mNormalToast.setText(message) ;
                mNormalToast.show() ;
            }
        }
        oneTime = twoTime ;
    }



    /**
     * 带图标的Toast
     *      默认图标
     *      默认时长
     * @param text 提示内容
     * @return
     */
    public static void showIImgToast(String text) {
        showIImgToast(text, TOAST_DURATION_SHORT);
    }

    /**
     * 带图标的Toast
     *      指定图标
     *      默认时长
     * @param text 提示内容
     * @param type 图片显示类型{@link IToastImageType}
     * @return
     */
//    public static void showIImgToast(String text,IToastImageType type) {
//        showIImgToast(text,TOAST_DURATION_SHORT);
//    }


    /**
     * 带图标的Toast
     *      默认图标
     *      指定时长
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @return
     */
    public static void showIImgToast(String text, int duration) {
        showIImgToast(text, duration, IToastImageType.DEFAULT);
    }


    /**
     * 带图标的Toast
     *      指定图标
     *      默认时长
     * @param text 提示内容
     * @param type 图片显示类型{@link IToastImageType}
     * @return
     */
    public static void showIImgToast(String text,IToastImageType type) {
        int imageViewResID = IToastImageType.getCommonImageResID(type);
        showIImgToast(text, TOAST_DURATION_SHORT, imageViewResID);
    }

    /**
     * 带图标的Toast
     *      指定图标
     *      指定时长
     * @param text 提示内容
     * @param type 图片显示类型{@link IToastImageType}
     * @return
     */
    public static void showIImgToast(String text, int duration, IToastImageType type) {
        int imageViewResID = IToastImageType.getCommonImageResID(type);
        showIImgToast(text, duration, imageViewResID);
    }

    /**
     * 带图标的Toast
     *      指定自己引入的图标
     *      指定时长
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @param imageViewResID 图片资源ID
     * @return
     */
    private static void showIImgToast(String text, int duration, int imageViewResID) {
        ObjectUtil.requireNonNull(App.getAppContext(),"context is null");
        if (text == null) {
            text = "提示信息为空";
        }
        LogUtils.e("--------------------imageViewResID----------------------:"+imageViewResID);
        LayoutInflater inflater = (LayoutInflater) App.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.layout_itoast, null);
        TextView mTextView = mView.findViewById(R.id.toast_msg);
        ImageView mImageView = mView.findViewById(R.id.toast_iv);
        mImageView.setImageResource(imageViewResID);
        mTextView.setText(text);
        mToast.setGravity(Gravity.CENTER,0,0);
        mToast.setDuration(duration);
        mToast.setView(mView);
        mToast.show();
    }



    /**
     *
     * @param context
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @return
     */
//    public void showFailTipToast(Context context, String text, int duration) {
//        ObjectUtil.requireNonNull(context, "context is null");
//        if (text == null) {
//            text = "提示信息为空";
//        }
//        Toast instance = IToast.getInstance();
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View mView = inflater.inflate(R.layout.layout_itoast, null);
//        TextView mTextView = mView.findViewById(R.id.toast_msg);
//        ImageView mImageView = mView.findViewById(R.id.toast_iv);
//        mImageView.setImageResource(R.drawable.itoast_ico_fail);
//        mTextView.setText(text);
////        Toast mToast = new Toast(context);
//        instance.setGravity(TOAST_GRAVITY_FLAG == 100 ? TOAST_CENTER : TOAST_GRAVITY_FLAG,0,0);
//        instance.setDuration(duration);
//        instance.setView(mView);
//        instance.show();
//    }

    /**
     *
     * @param context
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @return
     */
//    public void showSuccessTipToast(Context context, String text, int duration) {
//        ObjectUtil.requireNonNull(context, "context is null");
//        if (text == null) {
//            text = "提示信息为空";
//        }
//        Toast instance = IToast.getInstance();
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View mView = inflater.inflate(R.layout.layout_itoast, null);
//        TextView mTextView = mView.findViewById(R.id.toast_msg);
//        ImageView mImageView = mView.findViewById(R.id.toast_iv);
//        mImageView.setImageResource(R.drawable.itoast_ico_done);
//        mTextView.setText(text);
//        instance.setGravity(Gravity.CENTER,0,0);
//        instance.setDuration(duration);
//        instance.setView(mView);
//        instance.show();
//    }




    /**
     *
     * @param context
     * @param text 提示内容
     * @param duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @return
     */
//    public void showInfoTipToast(Context context, String text, int duration) {
//        ObjectUtil.requireNonNull(context, "context is null");
//        if (text == null) {
//            text = "提示信息为空";
//        }
//        Toast instance = IToast.getInstance();
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View mView = inflater.inflate(R.layout.layout_itoast, null);
//        TextView mTextView = mView.findViewById(R.id.toast_msg);
//        ImageView mImageView = mView.findViewById(R.id.toast_iv);
//        mImageView.setImageResource(R.drawable.itoast_ico_info);
//        mTextView.setText(text);
////        Toast mToast = new Toast(context);
//        instance.setGravity(Gravity.CENTER,0,0);
//        instance.setDuration(duration);
//        instance.setView(mView);
//        instance.show();
//
//
//
//    }


    /**
     *
     * @param context
     * @param text 提示内容
     * @duration duration 显示时长，对应 Toast.LEHGTH_SHORT和Toast.LENGTH_LONG
     * @return
     */
//    public void showErrorTipToast(Context context, String text,int duration) {
//        ObjectUtil.requireNonNull(context, "context is null");
//        if (text == null) {
//            text = "提示信息为空";
//        }
//        Toast instance = IToast.getInstance();
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View mView = inflater.inflate(R.layout.layout_itoast, null);
//        TextView mTextView = mView.findViewById(R.id.toast_msg);
//        ImageView mImageView = mView.findViewById(R.id.toast_iv);
//        mImageView.setImageResource(R.drawable.itoast_ico_fail);
//        mTextView.setText(text);
////        Toast mToast = new Toast(context);
//        instance.setGravity(Gravity.CENTER,0,0);
//        instance.setDuration(duration);
//        instance.setView(mView);
//        instance.show();
//
//
//    }
}
