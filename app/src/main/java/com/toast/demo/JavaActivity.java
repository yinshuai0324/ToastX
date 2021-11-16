package com.toast.demo;

<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.toast.ToastX;
=======
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.app.toast.ToastX;
import com.app.toast.utils.ToastUtils;

>>>>>>> 090f7031da9088f4f1ae0c5702a95c1d71cd9d26

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

<<<<<<< HEAD
        ToastX.with(this).show();
=======
        findViewById(R.id.showBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastX.with(JavaActivity.this)
                        .text("我是在顶部的")
                        .backgroundColor(R.color.toast_background_color_config3_succeed)
                        .animationMode(ToastX.ANIM_MODEL_SLIDE)
                        .textColor(R.color.color_FFFFFF)
                        .position(ToastX.POSITION_TOP)
                        .textGravity(Gravity.CENTER)
                        .duration(1000)
                        .textSize(14f)
                        .padding(ToastUtils.toDip(20), ToastUtils.toDip(20))
                        .margin(ToastUtils.toDip(15), ToastUtils.toDip(15))
                        .height(ToastUtils.toDip(40))
                        .width(ToastUtils.toDip(100))
                        .radius(ToastUtils.toDip(10))
                        .offset(ToastUtils.toDip(10))
                        .show();
            }
        });
>>>>>>> 090f7031da9088f4f1ae0c5702a95c1d71cd9d26
    }
}