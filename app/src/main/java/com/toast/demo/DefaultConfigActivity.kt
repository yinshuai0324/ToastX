package com.toast.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.app.toast.ToastXBack

class DefaultConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_config)
    }

    fun onDefaultConfigClick(view: View) {
        when (view.id) {
            R.id.infoBtn -> ToastXBack.showInfoToast(this, "信息正在提交中...")
            R.id.warnBtn -> ToastXBack.showWarnToast(this, "输入的格式不正确，请重试！")
            R.id.errorBtn -> ToastXBack.showErrorToast(this, "提交失败，请返回重试")
            R.id.succeedBtn -> ToastXBack.showSucceedToast(this, "数据提交成功",ToastXBack.LENGTH_LONG)
        }
    }
}