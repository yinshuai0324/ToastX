package com.app.toast.config

import android.util.Log
import com.app.toast.R
import com.general.widget.expand.dp
import com.general.widget.expand.sp

/**
 * 配置1
 */
class ToastConfig1 : ToastConfig {
    override fun toastHeight(): Int {
        //高度40dp
        return 40.dp
    }

    override fun toastRadius(): Float {
        //圆角20dp
        return 20f.dp
    }

    override fun toastBackgroundColor(type: Int): Int {
        //配置不同的Toast类型显示不同的背景颜色
        return when (type) {
            ToastType.TYPE_INFO -> R.color.toast_background_color_config1_info
            ToastType.TYPE_ERROR -> R.color.toast_background_color_config1_error
            ToastType.TYPE_WARN -> R.color.toast_background_color_config1_warn
            ToastType.TYPE_SUCCEED -> R.color.toast_background_color_config1_succeed
            else -> R.color.toast_background_color_config1_info
        }
    }

    override fun toastIconWidth(): Int {
        //Icon的宽度
        return 20.dp
    }

    override fun toastIconHeight(): Int {
        //Icon的高度
        return 20.dp
    }

    override fun toastIconRes(type: Int): Int {
        //配置不同的Toast类型显示不同的Icon 如果不需要显示Icon的话 返回0即可
        return when (type) {
            ToastType.TYPE_INFO -> R.drawable.ic_toast_default_info
            ToastType.TYPE_ERROR -> R.drawable.ic_toast_default_error
            ToastType.TYPE_WARN -> R.drawable.ic_toast_default_warn
            ToastType.TYPE_SUCCEED -> R.drawable.ic_toast_default_succeed
            else -> 0
        }
    }

    override fun toastTextSize(): Float {
        //文字大小 单位sp
        return 14f.sp
    }

    override fun toastTextColor(type: Int): Int {
        //可以根据Type 设置不同的颜色
        return R.color.color_FFFFFF
    }

    override fun isShowIcon(): Boolean {
        //是否显示Icon
        return true
    }
}