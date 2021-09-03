package com.app.toast.snackbar

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.app.toast.R
import com.app.toast.ToastX.ANIM_MODEL_SLIDE
import com.app.toast.ToastX.DURATION_SHORT
import com.app.toast.ToastX.POSITION_BOTTOM
import com.app.toast.ToastX.POSITION_TOP
import com.app.toast.expand.dp
import java.text.FieldPosition

class SnackBarX {
    private lateinit var snackBar: Snackbar
    private lateinit var snackBarLayout: ViewGroup
    private var viewGroup: ViewGroup
    private var messageValue: String = ""
    private var mContext: Context

    /******参数部分********/
    private var position: Int = POSITION_BOTTOM
    private var height: Int = 40.dp
    private var width: Int = FrameLayout.LayoutParams.WRAP_CONTENT
    private var textSize: Float = 14f
    private var textColor: Int = R.color.color_FFFFFF
    private var backgroundColor: Int = R.color.colorSurface
    private var radius: Float = 20f.dp
    private var animationMode: Int = ANIM_MODEL_SLIDE
    private var paddingLeft: Int = 15.dp
    private var paddingRight: Int = 15.dp
    private var marginLeft: Int = 0
    private var marginRight: Int = 0
    private var duration: Int = DURATION_SHORT
    private var offset: Int = 0
    private var textGravity: Int = Gravity.CENTER


    constructor(viewGroup: ViewGroup) {
        this.viewGroup = viewGroup
        this.mContext = this.viewGroup.context
    }


    fun gravity(position: Int): SnackBarX {
        this.position = position
        return this
    }


    fun height(height: Int): SnackBarX {
        this.height = height
        return this
    }

    fun textSize(textSize: Float): SnackBarX {
        this.textSize = textSize
        return this
    }

    fun textColor(textColor: Int): SnackBarX {
        this.textColor = textColor
        return this
    }

    fun backgroundColor(backgroundColor: Int): SnackBarX {
        this.backgroundColor = backgroundColor
        return this
    }

    fun radius(radius: Float): SnackBarX {
        this.radius = radius
        return this
    }

    fun animationMode(animationMode: Int): SnackBarX {
        this.animationMode = animationMode
        return this
    }

    fun text(text: String): SnackBarX {
        this.messageValue = text
        return this
    }

    fun padding(left: Int, right: Int): SnackBarX {
        this.paddingLeft = left
        this.paddingRight = right
        return this
    }

    fun margin(left: Int, right: Int): SnackBarX {
        this.marginLeft = left
        this.marginRight = right
        return this
    }

    fun duration(duration: Int): SnackBarX {
        this.duration = duration
        return this
    }

    fun offset(offset: Int): SnackBarX {
        this.offset = offset
        return this
    }

    fun position(position: Int): SnackBarX {
        this.position = position
        return this
    }

    fun width(width: Int): SnackBarX {
        this.width = width
        return this
    }

    fun textGravity(gravity: Int): SnackBarX {
        this.textGravity = gravity
        return this
    }


    fun show() {
        mContext = viewGroup.context
        //获取根布局
        snackBar = Snackbar.make(viewGroup, "", duration)
        snackBar.setPosition(position)
        snackBar.setOffset(offset)
        snackBarLayout = snackBar.view as ViewGroup
        snackBarLayout.removeAllViews()
        //重新设置宽高
        val layoutParams = FrameLayout.LayoutParams(width, height)
        layoutParams.gravity = when (position) {
            POSITION_TOP -> Gravity.TOP or Gravity.CENTER_HORIZONTAL
            POSITION_BOTTOM -> Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            else -> Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        }
        layoutParams.setMargins(marginLeft, 0, marginRight, 0)
        snackBarLayout.layoutParams = layoutParams
        snackBarLayout.setPadding(paddingLeft, 0, paddingRight, 0)
        //设置背景
        val background = GradientDrawable()
        background.shape = GradientDrawable.RECTANGLE
        background.setColor(ContextCompat.getColor(mContext, backgroundColor))
        background.cornerRadius = radius
        snackBarLayout.background = background
        //设置动画模式
        snackBar.animationMode = animationMode
        //添加Text
        val message = TextView(mContext)
        message.text = messageValue
        message.gravity = textGravity
        message.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        message.setTextColor(ContextCompat.getColor(mContext, textColor))
        snackBarLayout.addView(message)
        snackBar.show()
    }
}