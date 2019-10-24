package self.zhangkang.widget

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
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
open class BaseDrawerLayoutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base_drawer_layout)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            real_status_bar.setBackgroundColor(window.statusBarColor)
            real_toolbar.setBackgroundColor(window.statusBarColor)
        }
        addStatusBarView(real_status_bar)
        initCommonTitle(real_toolbar)
        open_drawer_layout.setOnClickListener {
            drawer_layout_root.openDrawer(GravityCompat.START)
        }
    }


    override fun setContentView(layoutResID: Int) {
        LayoutInflater.from(this).inflate(layoutResID, real_content_view)
        title = mTitleText
    }

    override fun setContentView(view: View?) {
        real_content_view.removeAllViews()
        real_content_view.addView(view)
        title = mTitleText
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        real_content_view.removeAllViews()
        real_content_view.addView(view, params)
        title = mTitleText
    }


    /**
     * 初始化通用标题 只有左边的返回按钮和中间的标题
     *
     * @param toolbar 当前Toolbar
     */
    private fun initCommonTitle(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_left_black)
        //如果当前的TextView为null，则创建一个
        if (null == mTitleTextView) {
            mTitleTextView = createTitleTextView()
        } else {
            val parent = mTitleTextView.parent
            if (null != parent) {
                (parent as ViewGroup).removeView(mTitleTextView)
            }
        }
        val layoutParams = Toolbar.LayoutParams(
            Toolbar.LayoutParams.WRAP_CONTENT,
            Toolbar.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER
        mTitleTextView.layoutParams = layoutParams
        toolbar.addView(mTitleTextView)
        if (!TextUtils.isEmpty(mTitleText)) {
            mTitleTextView.text = mTitleText
        }
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun createTitleTextView(): TextView {
        val textView = TextView(this)
        //设置显示为1行
        textView.setSingleLine()
        //设置最多显示多少个字
        textView.setEms(10)
        //设置省略号在尾部
        textView.ellipsize = TextUtils.TruncateAt.END
        textView.gravity = Gravity.CENTER
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        textView.textSize = 18f
        return textView
    }
}