package com.app.toast.config

/**
 * Toast配置
 * @author yinshuai
 * @link https://github.com/yinshuai0324/ToastX
 */
interface ToastConfig {

    /**
     * Toast的高度
     */
    fun toastHeight(): Int

    /**
     * Toast的圆角
     */
    fun toastRadius(): Float

    /**
     * Toast的背景颜色
     */
    fun toastBackgroundColor(type: Int): Int

    /**
     * Toast icon宽度
     */
    fun toastIconWidth(): Int

    /**
     * Toast icon 高度
     */
    fun toastIconHeight(): Int

    /**
     * Toast icon 图标
     */
    fun toastIconRes(type: Int): Int

    /**
     * toast 文字大小 单位sp
     */
    fun toastTextSize(): Float

    /**
     * Toast文字颜色
     */
    fun toastTextColor(type: Int): Int

    /**
     * 是否显示Icon
     */
    fun isShowIcon(): Boolean
}