package com.toast.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.app.toast.ToastX
import com.app.toast.expand.dp
import com.app.toast.snackbar.SnackBarX
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var rootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.rootView)
    }

    fun onMainItemClick(view: View) {
        when (view.id) {
            R.id.topBtn -> {
                ToastX.with(this)
                    .text("我是在顶部的")
                    .margin(15.dp, 15.dp)
                    .backgroundColor(R.color.toast_background_color_config3_succeed)
                    .textColor(R.color.color_FFFFFF)
                    .position(ToastX.POSITION_TOP)
                    .offset(10.dp)
                    .show()
            }
            R.id.bottomBtn -> {
                ToastX.with(this)
                    .text("我是在底部的")
                    .margin(15.dp, 15.dp)
                    .backgroundColor(R.color.toast_background_color_config3_succeed)
                    .textColor(R.color.color_FFFFFF)
                    .position(ToastX.POSITION_BOTTOM)
                    .offset(10.dp)
                    .show()
            }
            R.id.warnBtn -> {
                ToastX.with(this)
                    .text("网络已断开，请检查网络")
                    .margin(15.dp, 15.dp)
                    .backgroundColor(R.color.toast_background_color_config2_warn)
                    .position(ToastX.POSITION_TOP)
                    .offset(10.dp)
                    .show()
            }
            R.id.tipsBtn -> {
                ToastX.with(this)
                    .text("数据正在上传中...")
                    .margin(15.dp, 15.dp)
                    .position(ToastX.POSITION_TOP)
                    .backgroundColor(R.color.toast_background_color_config2_succeed)
                    .offset(10.dp)
                    .show()
            }
            R.id.succeedBtn -> {
                ToastX.with(this)
                    .text("数据提交成功")
                    .margin(15.dp, 15.dp)
                    .position(ToastX.POSITION_TOP)
                    .backgroundColor(R.color.toast_background_color_config1_succeed)
                    .offset(10.dp)
                    .show()
            }
            R.id.errorBtn -> {
                ToastX.with(this)
                    .text("数据提交失败，请重试")
                    .margin(15.dp, 15.dp)
                    .position(ToastX.POSITION_TOP)
                    .backgroundColor(R.color.toast_background_color_config1_error)
                    .offset(10.dp)
                    .show()
            }
        }
    }
}