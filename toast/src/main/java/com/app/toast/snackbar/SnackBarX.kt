package com.app.toast.snackbar

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.Gravity
import android.view.View
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
    private var viewGroup: ViewGroup? = null
    private var messageValue: String = ""
    private var mContext: Context? = null

    /******ćæ°éšć********/
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
    private var customizeView: View? = null


    constructor(viewGroup: ViewGroup?) {
        viewGroup?.let {
            this.viewGroup = it
            this.mContext = it.context
        }
    }


    fun position(position: Int): SnackBarX {
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


    fun width(width: Int): SnackBarX {
        this.width = width
        return this
    }

    fun textGravity(gravity: Int): SnackBarX {
        this.textGravity = gravity
        return this
    }


    fun customizeView(view: View): SnackBarX {
        customizeView = view
        return this
    }


    fun dismiss() {
        snackBar?.dismiss()
    }


    fun show() {
        viewGroup?.let {
            //è·ćæ čćžć±
            snackBar = Snackbar.make(it, "", duration)
            snackBar.setPosition(position)
            snackBar.setOffset(offset)
            snackBarLayout = snackBar.view
            snackBarLayout.removeAllViews()
            if (customizeView != null) {
                setCustomizeView(it.context)
            } else {
                setToastView(it.context, snackBarLayout)
            }
            snackBar.show()
        }
    }


    /**
     * èźŸçœźToastæ ·ćŒ
     */
    private fun setToastView(context: Context, snackBarLayout: ViewGroup) {
        //éæ°èźŸçœźćźœé«
        val layoutParams = FrameLayout.LayoutParams(width, height)
        layoutParams.gravity = when (position) {
            POSITION_TOP -> Gravity.TOP or Gravity.CENTER_HORIZONTAL
            POSITION_BOTTOM -> Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            else -> Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        }
        layoutParams.setMargins(marginLeft, 0, marginRight, 0)
        snackBarLayout.layoutParams = layoutParams
        snackBarLayout.setPadding(paddingLeft, 0, paddingRight, 0)
        //èźŸçœźèæŻ
        val background = GradientDrawable()
        background.shape = GradientDrawable.RECTANGLE
        background.setColor(ContextCompat.getColor(context, backgroundColor))
        background.cornerRadius = radius
        snackBarLayout.background = background
        //èźŸçœźćšç»æšĄćŒ
        snackBar.animationMode = animationMode
        //æ·»ć Text
        val message = TextView(context)
        message.text = messageValue
        message.gravity = textGravity
        message.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        message.setTextColor(ContextCompat.getColor(context, textColor))
        snackBarLayout.addView(message)
    }

    /**
     * èźŸçœźèȘćźäčView
     */
    private fun setCustomizeView(context: Context) {
        //èźŸçœźç¶ćźčćšæ ·ćŒ
        val layoutParams = FrameLayout.LayoutParams(-1, -2)
        layoutParams.gravity = when (position) {
            POSITION_TOP -> Gravity.TOP or Gravity.CENTER_HORIZONTAL
            POSITION_BOTTOM -> Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            else -> Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        }
        layoutParams.setMargins(marginLeft, 0, marginRight, 0)
        snackBarLayout.layoutParams = layoutParams
        //èźŸçœźèæŻ
        snackBarLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.color_00000000))
        //èźŸçœźćšç»æšĄćŒ
        snackBar.animationMode = animationMode
        //æ·»ć èȘćźäčView
        snackBarLayout.addView(customizeView)
    }
}