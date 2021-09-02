package com.toast.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.toast.ToastXBack
import com.app.toast.config.ToastConfig3

class Config3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config3)
    }

    fun onDefaultConfigClick(view: View) {
        when (view.id) {
            R.id.infoBtn -> ToastXBack.config(ToastConfig3()).showInfoToast(this, "信息正在提交中...")
            R.id.warnBtn -> ToastXBack.config(ToastConfig3()).showWarnToast(this, "输入的格式不正确，请重试！")
            R.id.errorBtn -> ToastXBack.config(ToastConfig3()).showErrorToast(this, "提交失败，请返回重试")
            R.id.succeedBtn -> ToastXBack.config(ToastConfig3()).showSucceedToast(this, "数据提交成功")
        }
    }
}