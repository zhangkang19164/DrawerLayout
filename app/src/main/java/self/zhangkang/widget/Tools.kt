package self.zhangkang.widget

import android.content.Context
import android.view.View
import android.widget.LinearLayout

/**
 * Created on 2019/10/23
 * Title:
 * Description:
 *
 * @author Android-张康
 */

fun addStatusBarView(view: View) {
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
fun getStatusBarHeightPx(context: Context): Int {
    var statusBarHeight = -1
    //获取status_bar_height资源的ID
    val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        //根据资源ID获取相应的尺寸值
        statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
    }
    return statusBarHeight
}