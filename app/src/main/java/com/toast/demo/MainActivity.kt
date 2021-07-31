package com.toast.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onMainItemClick(view: View) {
        when (view.id) {
            R.id.defaultBtn -> startActivity(Intent(this, DefaultConfigActivity::class.java))
            R.id.config1Btn -> startActivity(Intent(this, Config1Activity::class.java))
            R.id.config2Btn -> startActivity(Intent(this, Config2Activity::class.java))
            R.id.config3Btn -> startActivity(Intent(this, Config3Activity::class.java))
        }
    }
}