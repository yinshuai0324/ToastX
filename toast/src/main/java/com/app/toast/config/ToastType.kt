package com.app.toast.config

/**
 * Toast类型
 */
class ToastType {
    companion object {
        /**
         * 普通Toast
         */
        const val TYPE_INFO = 10001

        /**
         * 错误Toast
         */
        const val TYPE_ERROR = 10002

        /**
         * 警告Toast
         */
        const val TYPE_WARN = 10003

        /**
         * 成功Toast
         */
        const val TYPE_SUCCEED = 10004
    }
}