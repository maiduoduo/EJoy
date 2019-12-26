
<br/>

![image](Screenshot/ico_app_theme.png)

<br/>

##### EJoy工具库

<br/>

> android工具库，加入主流工具，自定义、好的解决方案等
>

##### 工具分类

<br/>

![image](Screenshot/index_home.png)

<br/>

> TOAST
>

* **运行效果**

![image](Screenshot/toast/toast_a.png)
![image](Screenshot/toast/toast_b.png)
![image](Screenshot/toast/toast_c.png)
![image](Screenshot/toast/toast_d.png)
![image](Screenshot/toast/toast_e.png)
![image](Screenshot/toast/toast_f.png)

<br/>

> Dialog
>

* **运行效果**



<br/>

> ScrollView(自定义，根据屏幕文字长度进行限定开始滑动起点)
>

* **运行效果**

![image](Screenshot/scroll/scroll_a.png)
![image](Screenshot/scroll/scroll_b.png)

<br/>

> popupwindow(筛选菜单栏)
>

* **运行效果**

![image](Screenshot/popwindow/popwindow_a.png)
![image](Screenshot/popwindow/popwindow_b.png)
![image](Screenshot/popwindow/popwindow_c.png)
![image](Screenshot/popwindow/popwindow_d.png)
![image](Screenshot/popwindow/popwindow_e.png)
![image](Screenshot/popwindow/popwindow_f.png)



> 仿iosDialog
>

* **运行效果**

![image](Screenshot/iosdialog/img_iosdialog_a.jpg)
![image](Screenshot/iosdialog/img_iosdialog_b.jpg)
![image](Screenshot/iosdialog/img_iosdialog_c.jpg)
![image](Screenshot/iosdialog/img_iosdialog_d.jpg)


> 图片压缩：单张压缩/批量压缩/原生压缩
>

* **运行效果**

![image](Screenshot/iosdialog/img_iosdialog_e.jpg)
![image](Screenshot/compress/img_single_comress.jpg)
![image](Screenshot/compress/img_compress_before.jpg)
![image](Screenshot/compress/img_compress_ing.jpg)
![image](Screenshot/compress/img_compress_after.jpg)
![image](Screenshot/compress/img_system_a.jpg)
![image](Screenshot/compress/img_single_a.jpg)

<br/>


##### 待做日志

> 组件化

##### 更新日志

> 2019.12.26

* 下拉刷新

    + 基于SmartRefreshLayout实现自由定制头部：见项目IRefreshSmartActivty.java文件
    + 自定义视频下拉刷新示范：见项目IRefreshVideoActivty.java文件

* RecyclerView列表加载动画

<br/>

> 代码实现 详见IRefreshSmartActivty.java文件

<br/>

```java
    LayoutAnimationController controller = new LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.animation_item));
    //也可以通过此方法获得
    //LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.animation_recyclerview);
    controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
    controller.setDelay(0.2f);//前面还没结束，后面已经开始，实现连续
    mRecyclerView.setLayoutAnimation(controller);
    mRecyclerView.startLayoutAnimation();//貌似不加这句动画也会自动实现
```

<br/>

> animation_recyclerview.xml

```java
    <?xml version="1.0" encoding="utf-8"?>
    <layoutAnimation xmlns:android="http://schemas.android.com/apk/res/android"
        android:animation="@anim/animation_item"
        android:animationOrder="reverse"
        android:delay="0.2"
        android:interpolator="@android:anim/decelerate_interpolator"/>
```

<br/>

> animation_item.xml

```java
    <set xmlns:android="http://schemas.android.com/apk/res/android"
        android:duration="400">
        <translate
            android:fromYDelta="-20%"
            android:interpolator="@android:anim/decelerate_interpolator"
            android:toYDelta="0" />
        <alpha
            android:fromAlpha="0"
            android:interpolator="@android:anim/decelerate_interpolator"
            android:toAlpha="1" />
        <scale
            android:fromXScale="105%"
            android:fromYScale="105%"
            android:interpolator="@android:anim/decelerate_interpolator"
            android:pivotX="50%"
            android:pivotY="50%"
            android:toXScale="100%"
            android:toYScale="100%" />
    </set>

```





> 2019.12.20

* 新增自定义view组件库(imeiview-lib)

 > 集中存放自定义控件
 > 避免资源杂乱

* 新增资源文件组件库(iresku-lib)

 > 可以避免资源重复
 > 可以避免资源冲突
 > 可以避免资源杂乱
 > 组件化管理

* 信封样式的分割线


> 2019.12.19

**城市选择器**

* 城市列表（仿美团最新版城市选择）
* 仿IOS滚轮选择
* 三级列表选择


> 2019.12.13

* 日期时间选择器：支持日期选择、时间选择、日期时间选择。
* 省市区三级联动（待）
* 时间线（待）


> 2019.12.12

* 细节优化

> 2019.12.09

   > 仿iosDialog
   > 短篇消息
   > 列表条目
   > 长篇幅内容
   > 输入文本
   > 数字、城市选择
   > 极简布局

> 2019.12.06

* 图片压缩
   > 1.单图单压
   > 2.多图批量压缩
   > 3.系统API图片压缩


> 2019.12.04

* 相机，相册选择图片
* 图片压缩


> 2019.11.28

* WebView封装基类，避免大量使用，重复配置，造成代码冗余。
* 加入控件使用的详细HTML文档，webview加载展示


> 2019.11.27

* 沉浸式状态栏、PopupWindowFilter(筛选)、多级筛选、单级筛选等
* 修复机型沉浸式问题
* 修复fit

> 2019.11.26

* Toast优化
* IscrollView沉浸式解决方案梳理
















<br/><br/><br/><br/><br/>

> 本库会持续更新，持续维护