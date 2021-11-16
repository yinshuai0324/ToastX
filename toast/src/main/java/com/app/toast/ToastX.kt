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
import android.widget.TextView
import androidx.core.animation.addListener
import androidx.core.animation.doOnEnd
import com.app.toast.expand.dp
import com.app.toast.expand.getDeviceHeight
import com.app.toast.snackbar.BaseTransientBottomBar
import com.app.toast.snackbar.SnackBarX
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.atomic.AtomicReference

object ToastX {

    /**
     * 动画从底下弹出
     */
    const val ANIM_MODEL_SLIDE = BaseTransientBottomBar.ANIMATION_MODE_SLIDE

    /**
     * 动画渐变和缩放
     */
    const val ANIM_MODEL_FADE = BaseTransientBottomBar.ANIMATION_MODE_FADE

    /**
     * 不消失
     */
    const val DURATION_INDEFINITE = 0

    /**
     * 短时间
     */
    const val DURATION_SHORT = 1500

    /**
     * 长时间
     */
    const val DURATION_LONG = 2750

    /**
     * 显示的位置-顶部
     */
    const val POSITION_TOP = 1

    /**
     * 显示的位置-底部
     */
    const val POSITION_BOTTOM = 0

    @JvmStatic
    fun with(context: Context): ToastX {
        if (context is Activity) {
            with(context)
        }
        return this
    }

    @JvmStatic
    fun with(activity: Activity): SnackBarX {
        val rootView = activity.findViewById<ViewGroup>(android.R.id.content)
        return SnackBarX(rootView)
    }

    @JvmStatic
    fun with(viewGroup: ViewGroup): SnackBarX {
        return SnackBarX(viewGroup)
    }

}