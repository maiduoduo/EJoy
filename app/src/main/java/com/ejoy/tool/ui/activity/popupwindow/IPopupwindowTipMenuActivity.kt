package com.ejoy.tool.ui.activity.popupwindow

import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.ejoy.tool.R
import com.ejoy.tool.scaffold.utils.StatusBarTool
import com.ejoy.tool.ui.base.base_activity.IBaseActivity
import com.kongzue.baseframework.interfaces.DarkStatusBarTheme
import com.kongzue.baseframework.interfaces.Layout
import com.module.iviews.popup.qq.IQQPopupMenuListener
import com.module.iviews.popup.qq.IQQTipMenu
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_popupwindow_tipmenu.*

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

/**
 * CN:      IPopupwindowTipMenuActivity
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2020/3/5
 * Des:    TODO:自定义控件TipMenu 仿QQ长按弹出的提示窗口
 */
@Layout(R.layout.activity_popupwindow_tipmenu)
@DarkStatusBarTheme(true)
class IPopupwindowTipMenuActivity : IBaseActivity(), View.OnClickListener {

    private lateinit var tipMenuTop: IQQTipMenu
    private lateinit var tipMenuBottom: IQQTipMenu
    private lateinit var menus: ArrayList<String>


    override fun registSatusbarBgcolor(): Any {
        return "#FFFFFF"
    }

    override fun initViews() {
        StatusBarTool.setStatusBarDarkTheme(me,true)
        menus = ArrayList<String>()
        menus.add("回复")
        menus.add("点赞")
        menus.add("复制")
        menus.add("举报")
        tipMenuTop = IQQTipMenu(
                this,
                clickType = IQQTipMenu.TYPE_CLICK,
                cornerPosition = IQQTipMenu.CORNER_POSITION_TOP
        )

        tipMenuBottom = IQQTipMenu(
                this,
                clickType = IQQTipMenu.TYPE_CLICK,
                cornerPosition = IQQTipMenu.CORNER_POSITION_BOTTOM
        )


    }

    override fun initDatas() {

        tipMenuTop.bind(
                tipLeft,
                menus,
                popupMenuListener = object : IQQPopupMenuListener {
                    override fun onPopupMenuClick(position: Int) {
                        Toast.makeText(this@IPopupwindowTipMenuActivity, "${menus[position]} : $position", Toast.LENGTH_SHORT).show()
                    }

                }
        )


        tipMenuTop.bind(
                tipRight,
                menus,
                popupMenuListener = object : IQQPopupMenuListener {
                    override fun onPopupMenuClick(position: Int) {
                        Toast.makeText(this@IPopupwindowTipMenuActivity, "${menus[position]} : $position", Toast.LENGTH_SHORT).show()
                    }

                }
        )

        tipMenuTop.bind(
                tipCenter,
                menus,
                popupMenuListener = object : IQQPopupMenuListener {
                    override fun onPopupMenuClick(position: Int) {
                        Toast.makeText(this@IPopupwindowTipMenuActivity, "${menus[position]} : $position", Toast.LENGTH_SHORT).show()
                    }

                }
        )


        tipMenuBottom.bind(
                tipBottomLeft,
                menus,
                popupMenuListener = object : IQQPopupMenuListener {
                    override fun onPopupMenuClick(position: Int) {
                        Toast.makeText(this@IPopupwindowTipMenuActivity, "${menus[position]} : $position", Toast.LENGTH_SHORT).show()
                    }

                }
        )

        tipMenuBottom.bind(
                tipBottomRight,
                menus,
                popupMenuListener = object : IQQPopupMenuListener {
                    override fun onPopupMenuClick(position: Int) {
                        Toast.makeText(me, "${menus[position]} : $position", Toast.LENGTH_SHORT).show()
                    }

                }
        )
    }

    override fun setEvents() {
        longIvBack.setOnClickListener(this)
        Ptitle.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.longIvBack ->
                finishActivity()
            R.id.Ptitle ->
                iToast.showIImgToast("根据实际情况使用")

        }
    }


}


