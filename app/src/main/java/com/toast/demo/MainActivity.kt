package com.toast.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            R.id.defaultBtn -> startActivity(Intent(this, DefaultConfigActivity::class.java))
            R.id.config1Btn -> startActivity(Intent(this, Config1Activity::class.java))
            R.id.config2Btn -> startActivity(Intent(this, Config2Activity::class.java))
            R.id.config3Btn -> startActivity(Intent(this, Config3Activity::class.java))
            R.id.testBtn -> ToastX.with(this).offset(50.dp).position(SnackBarX.POSITION_TOP).duration(1000).text("网络已连接").show()
        }
    }
}