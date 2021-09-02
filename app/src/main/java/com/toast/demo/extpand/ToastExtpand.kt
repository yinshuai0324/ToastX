package com.toast.demo.extpand

import android.content.Context
import com.app.toast.ToastXBack

/**
 * 显示普通Toast
 */
fun Context.showInfoToast(msg: String?) {
    ToastXBack.showInfoToast(this, msg)
}

/**
 * 显示警告Toast
 */
fun Context.showWarnToast(msg: String?) {
    ToastXBack.showWarnToast(this, msg)
}

/**
 * 显示错误Toast
 */
fun Context.showErrorToast(msg: String?) {
    ToastXBack.showErrorToast(this, msg)
}

/**
 * 显示成功Toast
 */
fun Context.showSucceedToast(msg: String?) {
    ToastXBack.showSucceedToast(this, msg)
}