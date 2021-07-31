package com.toast.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.toast.ToastX
import com.app.toast.config.ToastConfig1
import com.app.toast.config.ToastConfig2

class Config2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config2)
    }

    fun onDefaultConfigClick(view: View) {
        when (view.id) {
            R.id.infoBtn -> ToastX.config(ToastConfig2()).showInfoToast(this, "信息正在提交中...")
            R.id.warnBtn -> ToastX.config(ToastConfig2()).showWarnToast(this, "输入的格式不正确，请重试！")
            R.id.errorBtn -> ToastX.config(ToastConfig2()).showErrorToast(this, "提交失败，请返回重试")
            R.id.succeedBtn -> ToastX.config(ToastConfig2()).showSucceedToast(this, "数据提交成功")
        }
    }
}