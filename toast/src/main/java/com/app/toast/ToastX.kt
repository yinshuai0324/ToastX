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
import com.app.toast.snackbar.SnackBarX
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.atomic.AtomicReference

object ToastX {

    fun with(context: Context): ToastX {
        if (context is Activity) {
            with(context)
        }
        return this
    }

    fun with(activity: Activity): SnackBarX {
        val rootView = activity.findViewById<ViewGroup>(android.R.id.content)
        return SnackBarX(rootView)
    }

    fun with(viewGroup: ViewGroup): SnackBarX {
        return SnackBarX(viewGroup)
    }

}