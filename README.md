![](https://img.shields.io/badge/platform-Android-yellow.svg) ![](https://img.shields.io/badge/license-MIT-red)  ![](https://img.shields.io/badge/language-kotlin-brightgreen) ![](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat) 

### 项目介绍
    轻量级的Android吐司库，底层使用SnackBar实现，可以实现顶部弹出，底部弹出。
    UI可高度定制。支持自定义布局。可自定义显示时长。提供简洁的APi调用
    
### 项目集成
 项目当前最新版本：[![](https://jitpack.io/v/yinshuai0324/ToastX.svg)](https://jitpack.io/#yinshuai0324/ToastX)
     
 ```
     Step 1. 添加 JitPack 仓库到你的项目
     allprojects {
     	repositories {
     		maven { url 'https://jitpack.io' }
     	}
     }
 
     Step 2. 添加 Toast的依赖
     implementation 'com.github.yinshuai0324:ToastX:tag'
  ```

### 下载Demo体验
   [点击下载Demo](https://github.com/yinshuai0324/ToastX/blob/main/doc/app-release.apk)

### 演示效果

#### gif演示
<img src="https://github.com/yinshuai0324/ToastX/blob/main/doc/gifs.gif" width = "300" alt="" align=center />

#### 视频演示
[查看视频](https://github.com/yinshuai0324/ToastX/blob/main/doc/videos.mp4)

### 使用方法

#### 普通Toast用法
```
ToastX.with(this)
     .text("我是在顶部的") //文字
     .backgroundColor(R.color.toast_background_color_config3_succeed) //背景
     .animationMode(ToastX.ANIM_MODEL_SLIDE) //动画模式 弹出或者渐变
     .textColor(R.color.color_FFFFFF) //文字颜色
     .position(ToastX.POSITION_TOP) //显示的位置 顶部或者底部
     .textGravity(Gravity.CENTER) //文字的位置
     .duration(1000) //显示的时间 单位ms
     .textSize(14f) //文字大小 单位sp
     .padding(10.dp, 10.dp) //左右内边距
     .margin(15.dp, 15.dp) //左右外边距
     .height(40.dp) //整个的高度
     .width(100.dp) //整个的宽度
     .radius(10f.dp) //圆角半径
     .offset(10.dp) //距离顶部或者底部的偏移量
     .show() //显示
```

#### 自定义布局用法

使用自定义布局的话以下几个属性将失效：text、backgroundColor、textColor、textGravity
textSize、padding、height、width、radius

```
val view = LayoutInflater.from(this).inflate(R.layout.message_view, null)
ToastX.with(this)
     .customizeView(view) //自定义布局
     .animationMode(ToastX.ANIM_MODEL_SLIDE) //动画模式 弹出或者渐变
     .position(ToastX.POSITION_TOP) //显示的位置 顶部或者底部
     .duration(1000) //显示的时间 单位ms
     .margin(15.dp, 15.dp) //左右外边距
     .offset(10.dp) //距离顶部或者底部的偏移量
     .show() //显示
```


#### 自定义布局带交互用法

使用自定义布局的话以下几个属性将失效：text、backgroundColor、textColor、textGravity
textSize、padding、height、width、radius

```
val view = LayoutInflater.from(this).inflate(R.layout.message_view, null)
val toast = ToastX.with(this)
              .customizeView(view) //自定义布局
              .animationMode(ToastX.ANIM_MODEL_SLIDE) //动画模式 弹出或者渐变
              .position(ToastX.POSITION_TOP) //显示的位置 顶部或者底部
              .duration(ToastX.DURATION_INDEFINITE) //显示的时间 单位ms
              .margin(15.dp, 15.dp) //左右外边距
              .offset(10.dp) //距离顶部或者底部的偏移量

    toast.show() //显示
    view.setOnClickListener {
        toast.dismiss() //点击自定义View 关闭Toast
    }
```



