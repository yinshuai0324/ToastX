package com.app.toast

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import com.app.toast.expand.dp
import com.app.toast.expand.getDeviceHeight
import java.util.concurrent.atomic.AtomicReference

object ToastX {
    private val decorView = AtomicReference<ViewGroup>()
    private val toastView = AtomicReference<View>()
    private val toastHeight = 40.dp
    private val rect = Rect()
    private val direction = Direction.BOTTOM
    private val showTime = 3000L
    private val offset: Float = 100f

    /**
     * 执行关闭-带动画
     */
    private const val EVENT_DISMISS_AMIN = 1001

    /**
     * 执行关闭-无动画
     */
    private const val EVENT_DISMISS = 1002

    fun show(activity: Activity) {
        val decorView = activity.window.decorView as FrameLayout
        this.decorView.set(decorView)
        decorView.getWindowVisibleDisplayFrame(rect)
        removeView()
        val toastView = View.inflate(activity, R.layout.toast_x_view, null)
        toastView.id = R.id.toast_view
        if (this.toastView.get() != null) {
            this.toastView.get().clearAnimation()
        }
        this.toastView.set(toastView)
        decorView.addView(toastView)
        val layoutParams = FrameLayout.LayoutParams(-2, toastHeight)
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL
        toastView.layoutParams = layoutParams
        startAnimIn()
    }


    /**
     * 开始进入动画
     */
    private fun startAnimIn() {
        val view = toastView.get()
        val startY = getInAnimParams().first
        val endY = getInAnimParams().second
        ObjectAnimator.ofFloat(view, "translationY", startY, endY).apply {
            duration = 1000
            doOnEnd {
                Log.i("===>>>", "anim play wc")
                // 指定时长后 走退出动画
                val message = Message()
                message.what = EVENT_DISMISS_AMIN
                handler.sendMessageDelayed(message, showTime)
            }
            start()
        }
    }

    /**
     * 退出动画
     */
    private fun startAnimOut() {
        val view = toastView.get()
        val startY = getOutAnimParams().first
        val endY = getOutAnimParams().second
        ObjectAnimator.ofFloat(view, "translationY", startY, endY).apply {
            duration = 1000
            addListener { doOnEnd { removeView() } }
            start()
        }
    }

    /**
     * 获取动画所需要的餐素
     */
    private fun getInAnimParams(): Pair<Float, Float> {
        var startY = 0f
        var endY = 0f
        when (direction) {
            Direction.BOTTOM -> {
                startY = (rect.bottom + toastHeight).toFloat()
                endY = rect.bottom - toastHeight - offset
            }
            Direction.TOP -> {
                startY = (rect.top - toastHeight).toFloat()
                endY = rect.top + toastHeight + offset
            }
        }
        return Pair(startY, endY)
    }

    /**
     * 获取动画所需要的餐素
     */
    private fun getOutAnimParams(): Pair<Float, Float> {
        var startY = 0f
        var endY = 0f
        when (direction) {
            Direction.BOTTOM -> {
                startY = rect.bottom - toastHeight - offset
                endY = (rect.bottom + toastHeight).toFloat()
            }
            Direction.TOP -> {
                startY = rect.top + toastHeight + offset
                endY = (rect.top - toastHeight).toFloat()
            }
        }
        return Pair(startY, endY)
    }


    /**
     * 移除View
     */
    private fun removeView() {
        val decorView = this.decorView.get()
        val toast = decorView.findViewById<View>(R.id.toast_view)
        toast?.let {
            decorView.removeView(it)
        }
    }

    private val handler = object : Handler(Looper.getMainLooper(), object : Callback {
        override fun handleMessage(p0: Message): Boolean {
            return when (p0.what) {
                EVENT_DISMISS_AMIN -> {
                    Log.i("===>>>", "关闭弹窗")
                    startAnimOut()
                    true
                }
                EVENT_DISMISS -> {
                    removeView()
                    true
                }
                else -> false
            }
        }
    }) {}

    enum class Direction {
        TOP,
        BOTTOM
    }

}