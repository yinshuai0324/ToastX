![](https://img.shields.io/badge/platform-Android-yellow.svg) ![](https://img.shields.io/badge/license-MIT-red)  ![](https://img.shields.io/badge/language-kotlin-brightgreen) ![](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat) 

### 项目介绍
    用于Android的吐司库，让各个平台表现统一。使用Kotlin编写，并且预置三套主题供使用。使用简单且灵活，按需配置。
    
### 项目集成
 项目当前最新版本：[![](https://jitpack.io/v/yinshuai0324/GeneralAppWidget.svg)](https://jitpack.io/#yinshuai0324/GeneralAppWidget)
     
 ```
     Step 1. 添加 JitPack 仓库到你的项目
     allprojects {
     	repositories {
     		maven { url 'https://jitpack.io' }
     	}
     }
 
     Step 2. 添加 当前UI库的依赖
     implementation 'com.github.yinshuai0324:GeneralAppWidget:tag'
  ```

### 演示效果

默认效果：
![](https://github.com/yinshuai0324/ToastX/doc/config_default.png)
主题配置1：
![](https://github.com/yinshuai0324/ToastX/doc/config_1.png)
主题配置2：
![](https://github.com/yinshuai0324/ToastX/doc/config_2.png)
主题配置3：
![](https://github.com/yinshuai0324/ToastX/doc/config_3.png)


### 使用方法

简单使用：

```
//不指定显示时长 默认使用Toast.LENGTH_SHORT
//显示普通Toast
ToastX.showInfoToast(this, "信息正在提交中...")
//显示警告Toast
ToastX.showWarnToast(this, "输入的格式不正确，请重试！")
//显示错误Toast
ToastX.showErrorToast(this, "提交失败，请返回重试")
//显示成功Toast
ToastX.showSucceedToast(this, "数据提交成功")


//指定显示时长
//显示普通Toast
ToastX.showInfoToast(this, "信息正在提交中...",ToastX.LENGTH_LONG)
//显示警告Toast
ToastX.showWarnToast(this, "输入的格式不正确，请重试！",ToastX.LENGTH_LONG)
//显示错误Toast
ToastX.showErrorToast(this, "提交失败，请返回重试",ToastX.LENGTH_LONG)
//显示成功Toast
ToastX.showSucceedToast(this, "数据提交成功",ToastX.LENGTH_LONG)
```

进阶使用：
```
//指定自定义配置
ToastX.config(ToastConfig1()).showInfoToast(this, "信息正在提交中...")

//如需使用自定义配置，请实现ToastConfig接口，自定义你的Toast表现
//比如：

/**
 * 配置1
 */
class ToastConfig1 : ToastConfig {
    override fun toastHeight(): Int {
        //高度40dp
        return 40.dp
    }

    override fun toastRadius(): Float {
        //圆角20dp
        return 20f.dp
    }

    override fun toastBackgroundColor(type: Int): Int {
        //配置不同的Toast类型显示不同的背景颜色
        return when (type) {
            ToastType.TYPE_INFO -> R.color.toast_background_color_config1_info
            ToastType.TYPE_ERROR -> R.color.toast_background_color_config1_error
            ToastType.TYPE_WARN -> R.color.toast_background_color_config1_warn
            ToastType.TYPE_SUCCEED -> R.color.toast_background_color_config1_succeed
            else -> R.color.toast_background_color_config1_info
        }
    }

    override fun toastIconWidth(): Int {
        //Icon的宽度
        return 20.dp
    }

    override fun toastIconHeight(): Int {
        //Icon的高度
        return 20.dp
    }

    override fun toastIconRes(type: Int): Int {
        //配置不同的Toast类型显示不同的Icon 如果不需要显示Icon的话 返回0即可
        return when (type) {
            ToastType.TYPE_INFO -> R.drawable.ic_toast_default_info
            ToastType.TYPE_ERROR -> R.drawable.ic_toast_default_error
            ToastType.TYPE_WARN -> R.drawable.ic_toast_default_warn
            ToastType.TYPE_SUCCEED -> R.drawable.ic_toast_default_succeed
            else -> 0
        }
    }

    override fun toastTextSize(): Float {
        //文字大小 单位sp
        return 14f.sp
    }

    override fun toastTextColor(type: Int): Int {
        //可以根据Type 设置不同的颜色
        return R.color.color_FFFFFF
    }

    override fun isShowIcon(): Boolean {
        //是否显示Icon
        return true
    }
}

如果对Type类型不满足的话 可以自定义Type，然后在配置里面去根据Type去指定不同的表现
/**
 * Toast类型
 */
class ToastType {
    companion object {
        /**
         * 普通Toast
         */
        const val TYPE_INFO = 10001

        /**
         * 错误Toast
         */
        const val TYPE_ERROR = 10002

        /**
         * 警告Toast
         */
        const val TYPE_WARN = 10003

        /**
         * 成功Toast
         */
        const val TYPE_SUCCEED = 10004
    }
}

//然后在调用的时候 指定Type即可
ToastX.type(ToastType.TYPE_INFO).config(ToastConfig1()).makeText(context,"需要显示的信息",ToastX.LENGTH_LONG).show()

```

### Kotlin
如果你的项目使用的是Kotlin的话，配合扩展方法使用起来更佳，比如：

```
/**
 * 显示普通Toast
 */
fun Context.showInfoToast(msg: String?) {
    ToastX.showInfoToast(this, msg)
}

/**
 * 显示警告Toast
 */
fun Context.showWarnToast(msg: String?) {
    ToastX.showWarnToast(this, msg)
}

/**
 * 显示错误Toast
 */
fun Context.showErrorToast(msg: String?) {
    ToastX.showErrorToast(this, msg)
}

/**
 * 显示成功Toast
 */
fun Context.showSucceedToast(msg: String?) {
    ToastX.showSucceedToast(this, msg)
}
```
