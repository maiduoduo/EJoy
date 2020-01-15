
##### 💥 详细更新日志

---

<br/>

###### 💥 万望细读

> android工具库，加入主流工具，自定义、好的解决方案等  <br/>
> 目前该库处于功能完备阶段，后期会将功能发布到Jfrog bintray,形成远程引用仓库 <br/>
> E·享 目的在于整理与集纳好的优秀方案和功能实现，有自定义功能，也有来源于网络的好的视图功能，如有侵权，请联系删除 <br/>
> 后期会加入组件化管理，便于更好演示。 <br/>
> 有好的建议或者指正的地方，请您不吝赐教，提请issue,谢谢。 <br/>
> 烦请看到的各位，共同维护创作者的知识产权，不必大肆滥用别人的成果，共建一个良好生态的技术环境，在此感谢。 <br/>


##### 💥 更新日志

###### 2020.01.15

> 类Bottomsheet 底部弹窗

* From Xml
* Without Icon
* Dark Theme
* Grid
* Style
* Style from Theme
* ShareAction
* FullScreen
* Menu Manipulate
* HeaderLayout"



###### 2020.01.10

> 选择图片相册列表及显示/BottomsheetBehavior

* BottomSheet Recyclerview列表
* BottomSheet ScrollView列表
* 仿微博发表图片/视频界面，相册展示列表及选择

###### 2020.01.10

> IPopupwindowTopEditActivity/UI调整

* 自定义依附在输入法之上的Bottom弹窗
* 布局优化与调整
* popupwindow界面实现收缩折叠布局

###### 2020.01.08

> BottomSheetDialog/BottomSheetDialogFragment

* 自定义BottomSheetDialog（类IOS最新版本网易云音乐歌单弹窗）
* 官方BottomSheetDialog（自定义布局）
* Dialog实现的带有动画的底部弹窗见于Dialog


###### 2020.01.04

> 新增弹窗集合

* Popupwindow筛选菜单
* Popupwindow仿微博弹簧弹窗
* Popupwindow基础封装
* 自定义依附在输入法之上的Bottom弹窗
* 仿qq右上角弹窗
* 多类型筛选弹框
* 相机胶卷弹窗
* 普通选择框（气泡背景）
* 弹出菜单
* 可伸缩选择框
* ECookiebar(顶部和底部信息显示条)

###### 2019.12.26

> 下拉刷新

    + 基于SmartRefreshLayout实现自由定制头部：见项目IRefreshSmartActivty.java文件
    + 自定义视频下拉刷新示范：见项目IRefreshVideoActivty.java文件

> RecyclerView列表加载动画

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


###### 2019.12.20

> 新增自定义view组件库(imeiview-lib)

 * 集中存放自定义控件
 * 避免资源杂乱

> 新增资源文件组件库(iresku-lib)

 * 可以避免资源重复
 * 可以避免资源冲突
 * 可以避免资源杂乱
 * 组件化管理

> 信封样式的分割线


###### 2019.12.19

> **城市选择器**

* 城市列表（仿美团最新版城市选择）
* 仿IOS滚轮选择
* 三级列表选择


###### 2019.12.13

* 日期时间选择器：支持日期选择、时间选择、日期时间选择。
* 省市区三级联动（待）
* 时间线（待）


###### 2019.12.12

> 细节优化

###### 2019.12.09

  * 仿iosDialog
  * 短篇消息
  * 列表条目
  * 长篇幅内容
  * 输入文本
  * 数字、城市选择
  * 极简布局

###### 2019.12.06

> 图片压缩
 * 1.单图单压
 * 2.多图批量压缩
 * 3.系统API图片压缩


###### 2019.12.04

* 相机，相册选择图片
* 图片压缩


###### 2019.11.28

* WebView封装基类，避免大量使用，重复配置，造成代码冗余。
* 加入控件使用的详细HTML文档，webview加载展示


###### 2019.11.27

* 沉浸式状态栏、PopupWindowFilter(筛选)、多级筛选、单级筛选等
* 修复机型沉浸式问题
* 修复fit

###### 2019.11.26

* Toast优化
* IscrollView沉浸式解决方案梳理






<br/><br/><br/><br/><br/>

> 本库会持续更新，持续维护