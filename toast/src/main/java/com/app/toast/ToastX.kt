package com.app.toast

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.app.toast.config.DefaultConfig
import com.app.toast.config.ToastConfig
import com.app.toast.config.ToastType
import java.lang.NullPointerException

object ToastX {

    const val LENGTH_LONG = Toast.LENGTH_LONG
    const val LENGTH_SHORT = Toast.LENGTH_SHORT

    /**
     * Toast配置
     */
    private lateinit var toastConfig: ToastConfig

    /**
     * 最后显示的Toast
     */
    private var lastToast: Toast? = null

    /**
     * Toast 类型
     */
    private var toastType: Int = -1

    /**
     * 显示普通Toast
     */
    fun showInfoToast(ctx: Context, msg: String?) {
        val config = if (::toastConfig.isInitialized) {
            toastConfig
        } else {
            DefaultConfig()
        }
        type(ToastType.TYPE_INFO).config(getCurrentConfig()).makeText(ctx, msg, LENGTH_SHORT).show()
    }


    /**
     * 显示警告Toast
     */
    fun showWarnToast(ctx: Context, msg: String?) {
        type(ToastType.TYPE_WARN).config(getCurrentConfig()).makeText(ctx, msg, LENGTH_SHORT).show()
    }

    /**
     * 显示错误Toast
     */
    fun showErrorToast(ctx: Context, msg: String?) {
        type(ToastType.TYPE_ERROR).config(getCurrentConfig()).makeText(ctx, msg, LENGTH_SHORT)
            .show()
    }

    /**
     * 显示成功Toast
     */
    fun showSucceedToast(ctx: Context, msg: String?) {
        type(ToastType.TYPE_SUCCEED).config(getCurrentConfig()).makeText(ctx, msg, LENGTH_SHORT)
            .show()
    }


    /**
     * 显示普通Toast
     */
    fun showInfoToast(ctx: Context, msg: String?, duration: Int) {
        type(ToastType.TYPE_INFO).config(getCurrentConfig()).makeText(ctx, msg, duration).show()
    }


    /**
     * 显示警告Toast
     */
    fun showWarnToast(ctx: Context, msg: String?, duration: Int) {
        type(ToastType.TYPE_WARN).config(getCurrentConfig()).makeText(ctx, msg, duration).show()
    }

    /**
     * 显示错误Toast
     */
    fun showErrorToast(ctx: Context, msg: String?, duration: Int) {
        type(ToastType.TYPE_ERROR).config(getCurrentConfig()).makeText(ctx, msg, duration).show()
    }

    /**
     * 显示成功Toast
     */
    fun showSucceedToast(ctx: Context, msg: String?, duration: Int) {
        type(ToastType.TYPE_SUCCEED).config(getCurrentConfig()).makeText(ctx, msg, duration).show()
    }

    /**
     * 配置
     */
    fun config(config: ToastConfig?): ToastX {
        config?.let {
            toastConfig = it
        }
        return this
    }

    /**
     * 指定Toast类型
     */
    fun type(type: Int): ToastX {
        toastType = type
        return this
    }

    /**
     * 创建Toast
     */
    fun makeText(cxt: Context?, msg: String?, duration: Int): Toast {
        if (cxt == null) {
            throw NullPointerException("context is null")
        }
        return createToast(cxt, toastType, msg, duration)
    }


    /**
     * 创建Toast
     */
    private fun createToast(ctx: Context, type: Int, msg: String?, duration: Int): Toast {
        if (!::toastConfig.isInitialized) {
            toastConfig = DefaultConfig()
        }
        val currentToast = Toast.makeText(ctx, "", duration)
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.toast_view, null)
        val iconView = view.findViewById<ImageView>(R.id.icon)
        val msgView = view.findViewById<TextView>(R.id.msg)
        //设置Toast高度
        val layoutParams = LinearLayout.LayoutParams(-2, toastConfig.toastHeight())
        view.layoutParams = layoutParams
        //设置Toast背景
        val backgroundDrawable = GradientDrawable().apply {
            setColor(getColor(ctx, toastConfig.toastBackgroundColor(type)))
            cornerRadius = toastConfig.toastRadius()
        }
        view.background = backgroundDrawable
        //设置ToastIcon
        val iconRes = toastConfig.toastIconRes(type)
        if (toastConfig.isShowIcon() && iconRes != 0) {
            iconView.visibility = View.VISIBLE
            //设置高度
            val width = toastConfig.toastIconWidth()
            val height = toastConfig.toastIconHeight()
            val iconParams = LinearLayout.LayoutParams(width, height)
            iconView.layoutParams = iconParams
            //设置Icon
            iconView.setImageResource(iconRes)
        }
        //设置msg
        msgView.text = msg ?: ""
        //设置msg文字颜色
        msgView.setTextColor(getColor(ctx, toastConfig.toastTextColor(type)))
        //设置msg文字大小
        msgView.setTextSize(TypedValue.COMPLEX_UNIT_PX, toastConfig.toastTextSize())
        //设置自定义样式
        currentToast.view = view

        if (lastToast != null) {
            lastToast?.cancel()
        }
        lastToast = currentToast
        return currentToast
    }


    /**
     * 获取颜色
     */
    private fun getColor(cxt: Context, color: Int): Int {
        return ContextCompat.getColor(cxt, color)
    }


    /**
     * 获取本次显示的Config
     */
    private fun getCurrentConfig(): ToastConfig {
        return if (::toastConfig.isInitialized) {
            toastConfig
        } else {
            DefaultConfig()
        }
    }
}