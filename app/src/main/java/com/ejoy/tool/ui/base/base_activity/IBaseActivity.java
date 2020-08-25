package com.ejoy.tool.ui.base.base_activity;
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

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.ejoy.tool.R;
import com.ejoy.tool.app.App;
import com.ejoy.tool.scaffold.utils.IToast;
import com.ejoy.tool.scaffold.utils.StatusBarTool;
import com.kongzue.baseframework.BaseActivity;
import com.kongzue.baseframework.interfaces.DarkNavigationBarTheme;
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme;
import com.kongzue.baseframework.interfaces.Layout;
import com.kongzue.baseframework.interfaces.NavigationBarBackgroundColor;
import com.kongzue.baseframework.util.AppManager;
import com.kongzue.baseframework.util.OnPermissionResponseListener;
import com.kongzue.baseframework.util.OnResponseListener;
import com.kongzue.baseframework.util.Parameter;
import com.kongzue.baseframework.util.ParameterCache;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.os.Build.VERSION_CODES.M;

/**
 * CN:      IBaseActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/1/21
 * Des:    TODO:IBaseActivity 参考自com.kongzue.baseframework.BaseActivity
 */
public abstract class IBaseActivity extends AppCompatActivity {
    public IToast iToast;
    private Unbinder unbinder;
    public static boolean DEBUGMODE = true;
    public boolean isActive = false;                                        //当前Activity是否处于前台
    public static final String _TAG = IBaseActivity.class.getSimpleName();
    private OnResponseListener onResponseListener;                          //jump跳转回调
    private OnPermissionResponseListener onPermissionResponseListener;      //权限申请回调

    public IBaseActivity me = this;
    public String baseThemeColor = "#FE6D4B";

    private boolean darkStatusBarThemeValue = false;
    private boolean darkNavigationBarThemeValue = false;
    private int navigationBarBackgroundColorValue = Color.BLACK;
    private int layoutResId = android.R.layout.list_content;

    //不再推荐重写onCreate创建Activity，新版本推荐直接在Activity上注解：@Layout(你的layout资源id)
    @Deprecated
    protected void onCreate(Bundle savedInstanceState, int layoutResId) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResId);
        initAttributes();
        //初始化自定义Toast
        iToast = new IToast().builder();
        unbinder = ButterKnife.bind(this);
        setTranslucentStatus(true);
        App.getInstance().pushActivity(me);

        initViews();
        initDatas();
        setEvents();
    }

    @Override
    @Deprecated
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initStatusbar(0x00000000);
        } else {
            initStatusbarUnder5(R.color.black);
        }
        initAttributes();
        if (layoutResId == android.R.layout.list_content) {
            Log.e("警告！", "请在您的Activity的Class上注解：@Layout(你的layout资源id)");
            return;
        }
        setContentView(layoutResId);
        //初始化自定义Toast
        iToast = new IToast().builder();
        unbinder = ButterKnife.bind(this);
        setTranslucentStatus(true);
        App.getInstance().pushActivity(me);
        setStatusbar();
        initViews();
        initDatas();
        setEvents();

    }

    private void initAttributes() {
        try {
            Layout layout = getClass().getAnnotation(Layout.class);
            DarkNavigationBarTheme darkNavigationBarTheme = getClass().getAnnotation(DarkNavigationBarTheme.class);
            DarkStatusBarTheme darkStatusBarTheme = getClass().getAnnotation(DarkStatusBarTheme.class);
            NavigationBarBackgroundColor navigationBarBackgroundColor = getClass().getAnnotation(NavigationBarBackgroundColor.class);
            if (layout != null) {
                if (layout.value() != -1) layoutResId = layout.value();
            }
            if (darkStatusBarTheme != null) darkStatusBarThemeValue = darkStatusBarTheme.value();
            if (darkNavigationBarTheme != null)
                darkNavigationBarThemeValue = darkNavigationBarTheme.value();
            if (navigationBarBackgroundColor != null) {
                if (navigationBarBackgroundColor.a() != -1 && navigationBarBackgroundColor.r() != -1 && navigationBarBackgroundColor.g() != -1 && navigationBarBackgroundColor.b() != -1) {
                    navigationBarBackgroundColorValue = Color.argb(navigationBarBackgroundColor.a(), navigationBarBackgroundColor.r(), navigationBarBackgroundColor.g(), navigationBarBackgroundColor.b());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        App.getInstance().killActivity(me);
    }

    public void finishActivity() {
        super.finish();
    }

    //可被重写的接口
    public abstract void initViews();

    public abstract void initDatas();

    public abstract void setEvents();

    /**
     * 透明状态栏
     * TODO:(子类界面重写此方法)
     *
     * @return
     */
    protected boolean isRegistSatusbarFullScreenTransluent() {
        return false;
    }

    /**
     * 状态栏背景颜色
     * 1.推荐使用：字符串类型颜色：eg:"#FFCF47",{@link android.support.annotation.ColorRes}
     * 2.不推荐使用：color.xml下颜色：R.color.LGray3   todo:---------注意:会出现混合色------
     * <p>
     * TODO:(子类界面重写此方法)
     *
     * @return
     */
    protected Object registSatusbarBgcolor() {
        return "";
    }

    /**
     * 状态栏字体颜色
     * 深色/浅色切换
     *
     * @return
     * @dark true 深色
     * @light false 浅色
     * TODO:(子类界面重写此方法)
     */
    protected boolean isRegistSatusbarFontDark() {
        return false;
    }

    public void setDarkStatusBarTheme(boolean value) {
        darkStatusBarThemeValue = value;
        setTranslucentStatus(true);
    }

    public void setDarkNavigationBarTheme(boolean value) {
        darkNavigationBarThemeValue = value;
        setTranslucentStatus(true);
    }

    public void setNavigationBarBackgroundColor(@ColorInt int color) {
        navigationBarBackgroundColorValue = color;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(navigationBarBackgroundColorValue);
        }
    }

    public void setNavigationBarBackgroundColor(int a, int r, int g, int b) {
        navigationBarBackgroundColorValue = Color.argb(a, r, g, b);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(navigationBarBackgroundColorValue);
        }
    }

    //状态栏主题
    protected void setTranslucentStatus(boolean on) {
        if (isMIUI()) setStatusBarDarkModeInMIUI(darkStatusBarThemeValue, this);
        if (isFlyme()) setStatusBarDarkIconInFlyme(getWindow(), darkStatusBarThemeValue);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            if (darkStatusBarThemeValue) {
                if (darkNavigationBarThemeValue) {
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                    );
                } else {
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    );
                }
            } else {
                if (darkNavigationBarThemeValue) {
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                    );
                } else {
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    );
                }
            }

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams winParams = window.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            window.setAttributes(winParams);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(navigationBarBackgroundColorValue);
        }
    }


    private void setStatusbar() {
        if (Build.VERSION.SDK_INT >= M) {
            if (isRegistSatusbarFullScreenTransluent()) {
                StatusBarTool.setTranslucentStatus(this);//透明状态栏
                //用来设置整体下移，状态栏沉浸
                StatusBarTool.setRootViewFitsSystemWindows(this, false);
            } else {
                StatusBarTool.setRootViewFitsSystemWindows(this, true);
            }
            if (isRegistSatusbarFontDark()) {
                //黑色字体
                StatusBarTool.setStatusBarDarkTheme(this, true);
            } else {
                //浅色字体
                StatusBarTool.setStatusBarDarkTheme(this, false);
            }
            if (registSatusbarBgcolor() instanceof String) {
                if (!TextUtils.isEmpty((String) registSatusbarBgcolor())) {
                    StatusBarTool.setStatusBarStringColor(me, (String) registSatusbarBgcolor());
                }
            } else if (registSatusbarBgcolor() instanceof Integer) {
                if ((Integer) registSatusbarBgcolor() > 0) {
                    StatusBarTool.setStatusBarColor(me, (Integer) registSatusbarBgcolor());
                }
            }
        }
    }


    protected void initStatusbar(int colorid) {
        //沉浸式代码配置
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarTool.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarTool.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarTool.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarTool.setStatusBarColor(this, colorid);
//            StatusBarTool.setStatusBarColor(this, 0x55000000);
//            StatusBarTool.setStatusBarColor(this, 0x00000000);
        }


        //用来设置整体下移，状态栏沉浸
        StatusBarTool.setRootViewFitsSystemWindows(this, false);
//        StatusBarUtil.setTranslucent(MainActivity.this, 0);
        StatusBarTool.setTranslucentStatus(this);//透明状态栏
        //黑色字体
//        StatusBarTool.setStatusBarDarkTheme(this, false);
        //黑色字体
//        StatusBarTool.setStatusBarDarkTheme(MainActivity.this, true);
        //设置白色字体，其他背景
//        StatusBarTool.setStatusBarColor(this, Color.parseColor("#58C087"));//设置背景颜色

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //系统版本大于19
//            setTranslucentStatus(true);
//        }
//        if (isSetStatusBarBg()) {
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(R.drawable.shape_statusbar);
//
//            //去除灰色遮罩
//            //Android5.0以上
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                Window window = getWindow();
//                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(Color.TRANSPARENT);
//            }else if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT){//Android4.4以上,5.0以下
//                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            }
//        }
    }


    private void initStatusbarUnder5(int colorId) {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(this.getResources().getColor(colorId));
    }


    private void setStatusBarDarkModeInMIUI(boolean darkmode, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean setStatusBarDarkIconInFlyme(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
                Log.e("MeiZu", "setStatusBarDarkIcon: failed");
            }
        }
        return result;
    }

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    //获取状态栏的高度
    public int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //MIUI判断
    public static boolean isMIUI() {
        try {
            final BaseActivity.BuildProperties prop = BaseActivity.BuildProperties.newInstance();
            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } catch (final IOException e) {
            return false;
        }
    }

    //Flyme判断
    public static boolean isFlyme() {
        try {
            final Method method = Build.class.getMethod("hasSmartBar");

            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }

    public static class BuildProperties {

        private final Properties properties;

        private BuildProperties() throws IOException {
            properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        }

        public boolean containsKey(final Object key) {
            return properties.containsKey(key);
        }

        public boolean containsValue(final Object value) {
            return properties.containsValue(value);
        }

        public Set<Map.Entry<Object, Object>> entrySet() {
            return properties.entrySet();
        }

        public String getProperty(final String name) {
            return properties.getProperty(name);
        }

        public String getProperty(final String name, final String defaultValue) {
            return properties.getProperty(name, defaultValue);
        }

        public boolean isEmpty() {
            return properties.isEmpty();
        }

        public Enumeration<Object> keys() {
            return properties.keys();
        }

        public Set<Object> keySet() {
            return properties.keySet();
        }

        public int size() {
            return properties.size();
        }

        public Collection<Object> values() {
            return properties.values();
        }

        public static IBaseActivity.BuildProperties newInstance() throws IOException {
            return new IBaseActivity.BuildProperties();
        }
    }

    protected final static String NULL = "";
    private Toast toast;

    protected void runOnMain(Runnable runnable) {
        runOnUiThread(runnable);
    }

    //简易吐司
    public void toast(final Object obj) {
        try {
            runOnMain(new Runnable() {

                @Override
                public void run() {
                    if (toast == null)
                        toast = Toast.makeText(IBaseActivity.this, NULL, Toast.LENGTH_SHORT);
                    toast.setText(obj.toString());
                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //简易Log
    public void log(final Object obj) {
        try {
            runOnMain(new Runnable() {

                @Override
                public void run() {
                    if (DEBUGMODE) {
                        Log.i("log", obj.toString());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //软键盘打开与收起
    public void setIMMStatus(boolean show, EditText editText) {
        if (show) {
            editText.requestFocus();
            editText.setFocusableInTouchMode(true);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
        } else {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    public static String StartFindWords = "";

    //用于进行dip和px转换
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //用于进行px和dip转换
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    //权限相关
    private final String TAG = "PermissionsUtil";
    private int REQUEST_CODE_PERMISSION = 0x00099;

    /**
     * 请求权限
     * <p>
     * 警告：此处除了用户拒绝外，唯一可能出现无法获取权限或失败的情况是在AndroidManifest.xml中未声明权限信息
     * Android6.0+即便需要动态请求权限（重点）但不代表着不需要在AndroidManifest.xml中进行声明。
     *
     * @param permissions                  请求的权限
     * @param onPermissionResponseListener 回调监听器
     */
    public void requestPermission(String[] permissions, OnPermissionResponseListener onPermissionResponseListener) {
        this.onPermissionResponseListener = onPermissionResponseListener;
        if (checkPermissions(permissions)) {
            if (onPermissionResponseListener != null)
                onPermissionResponseListener.onSuccess(permissions);
        } else {
            List<String> needPermissions = getDeniedPermissions(permissions);
            ActivityCompat.requestPermissions(this, needPermissions.toArray(new String[needPermissions.size()]), REQUEST_CODE_PERMISSION);
        }
    }

    /**
     * 检测所有的权限是否都已授权
     *
     * @param permissions
     * @return
     */
    public boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     */
    private List<String> getDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                needRequestPermissionList.add(permission);
            }
        }
        return needRequestPermissionList;
    }


    /**
     * 系统请求权限回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (verifyPermissions(grantResults)) {
                if (onPermissionResponseListener != null)
                    onPermissionResponseListener.onSuccess(permissions);
            } else {
                if (onPermissionResponseListener != null) onPermissionResponseListener.onFail();
                showTipsDialog();
            }
        }
    }

    /**
     * 确认所有的权限是否都已授权
     *
     * @param grantResults
     * @return
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setTitle("警告")
                .setMessage("需要必要的权限才可以正常使用该功能，您已拒绝获得该权限。\n如果需要重新授权，您可以点击“允许”按钮进入系统设置进行授权")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }


    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    //获取屏幕宽度
    public int getDisPlayWidth() {
        Display disp = getWindowManager().getDefaultDisplay();
        Point outP = new Point();
        disp.getSize(outP);
        return outP.x;
    }

    //获取屏幕可用部分高度（屏幕高度-状态栏高度-屏幕底栏高度）
    public int getDisPlayHeight() {
        Display disp = getWindowManager().getDefaultDisplay();
        Point outP = new Point();
        disp.getSize(outP);
        return outP.y;
    }

    public int getNavbarHeight() {
        int resourceId = 0;
        int rid = getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid != 0) {
            resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return getResources().getDimensionPixelSize(resourceId);
        } else
            return 0;
    }

    //位移动画
    public ObjectAnimator moveAnimation(Object obj, String perference, float aimValue, long time, long delay) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(obj, perference, aimValue);
        objectAnimator.setDuration(time);
        objectAnimator.setStartDelay(delay);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            objectAnimator.setAutoCancel(true);
        }
        objectAnimator.start();
        return objectAnimator;
    }

    public ObjectAnimator moveAnimation(Object obj, String perference, float aimValue, long time) {
        return moveAnimation(obj, perference, aimValue, time, 0);
    }

    public ObjectAnimator moveAnimation(Object obj, String perference, float aimValue) {
        return moveAnimation(obj, perference, aimValue, 300, 0);
    }

    //复制文本到剪贴板
    public boolean copy(String s){
        if (isNull(s)){
            log("要复制的文本为空");
            return false;
        }
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", s);
        cm.setPrimaryClip(mClipData);
        return true;
    }

    //网络传输文本判空规则
    public boolean isNull(String s) {
        if (s == null || s.trim().isEmpty() || s.equals("null")) {
            return true;
        }
        return false;
    }

    //更好用的跳转方式
    public boolean jump(Class<?> cls) {
        try {
            startActivity(new Intent(me, cls));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //可以传任何类型参数的跳转方式
    public boolean jump(Class<?> cls, Parameter parameter) {
        try {
            startActivity(new Intent(me, cls));
            ParameterCache.getInstance().set(cls.getName(), parameter);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //带返回值的跳转
    public boolean jump(Class<?> cls, OnResponseListener onResponseListener) {
        try {
            startActivity(new Intent(me, cls));
            ParameterCache.getInstance().cleanResponse(me.getClass().getName());
            ParameterCache.getInstance().set(cls.getName(), new Parameter()
                    .put("needResponse", true)
                    .put("responseClassName", me.getClass().getName())
            );
            this.onResponseListener = onResponseListener;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //带返回值的跳转
    public boolean jump(Class<?> cls, Parameter parameter, OnResponseListener onResponseListener) {
        try {
            startActivity(new Intent(me, cls));
            ParameterCache.getInstance().cleanResponse(me.getClass().getName());
            ParameterCache.getInstance().set(cls.getName(), parameter
                    .put("needResponse", true)
                    .put("responseClassName", me.getClass().getName())
            );
            this.onResponseListener = onResponseListener;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //目标Activity：设定要返回的数据
    public void setResponse(Parameter parameter) {
        ParameterCache.getInstance().setResponse((String) getParameter().get("responseClassName"), parameter);
    }

    //获取跳转参数
    public Parameter getParameter() {
        return ParameterCache.getInstance().get(me.getClass().getName());
    }

    @Override
    protected void onResume() {
        isActive = true;
        if (onResponseListener != null) {
            onResponseListener.OnResponse(ParameterCache.getInstance().getResponse(me.getClass().getName()));
            onResponseListener = null;
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        isActive = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (getParameter() != null) getParameter().cleanAll();
        App.getInstance().deleteActivity(me);
        try {
            unbinder.unbind();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public void jumpAnim(int enterAnim, int exitAnim) {
        int version = Integer.valueOf(android.os.Build.VERSION.SDK);
        if (version > 5) {
            overridePendingTransition(enterAnim, exitAnim);
        }
    }


}
