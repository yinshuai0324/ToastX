package com.toast.demo.extpand

import android.content.Context
import com.app.toast.ToastX

/**
 * 显示普通Toast
 */
fun Context.showInfoToast(msg: String?) {
    ToastX.showInfoToast(this, msg)
}

/**
 * 显示警告Toast
 */
fun Context.showWarnToast(msg: String?) {
    ToastX.showWarnToast(this, msg)
}

/**
 * 显示错误Toast
 */
fun Context.showErrorToast(msg: String?) {
    ToastX.showErrorToast(this, msg)
}

/**
 * 显示成功Toast
 */
fun Context.showSucceedToast(msg: String?) {
    ToastX.showSucceedToast(this, msg)
}