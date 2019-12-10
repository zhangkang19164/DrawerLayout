package self.zhangkang.widget

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_base_drawer_layout.*


/**
 * Created on 2019/10/23
 * Title:
 * Description:
 *
 * @author Android-张康
 */
@SuppressLint("Registered")
open class BaseDrawerLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base_drawer_layout)
        setSupportActionBar(action_bar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            real_status_bar.setBackgroundColor(window.statusBarColor)
        } else {
            real_status_bar.setBackgroundColor(getStatusBarColor())
        }
        addStatusBarView(real_status_bar)
        open_drawer_layout.setOnClickListener {
            drawer_layout_root.openDrawer(GravityCompat.START)
        }
    }


    override fun setContentView(layoutResID: Int) {
        LayoutInflater.from(this).inflate(layoutResID, real_content_view)
    }

    override fun setContentView(view: View?) {
        real_content_view.removeAllViews()
        real_content_view.addView(view)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        real_content_view.removeAllViews()
        real_content_view.addView(view, params)
    }

    fun setDrawerContentView(layoutResID: Int) {
        LayoutInflater.from(this).inflate(layoutResID, drawer_layout)
    }

    fun setDrawerContentView(view: View?) {
        drawer_layout.removeAllViews()
        drawer_layout.addView(view)
    }

    fun setDrawerContentView(view: View?, params: ViewGroup.LayoutParams?) {
        drawer_layout.removeAllViews()
        drawer_layout.addView(view, params)
    }


    @ColorInt
    open fun getStatusBarColor(): Int {
        throw RuntimeException("如果SDK_INT小于21，必须重写该方法")
    }


    /**
     * 设置StatusBar的高度
     */
    private fun addStatusBarView(view: View) {
        view.layoutParams = view.layoutParams.apply {
            width = LinearLayout.LayoutParams.MATCH_PARENT
            height = getStatusBarHeightPx(view.context)
        }
    }

    /**
     * 获取当前设备状态栏高度
     *
     * @param context 外部调用环境
     * @return 当前设备状态栏高度，单位为：px
     */
    private fun getStatusBarHeightPx(context: Context): Int {
        var statusBarHeight = -1
        //获取status_bar_height资源的ID
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            //根据资源ID获取相应的尺寸值
            statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }
}