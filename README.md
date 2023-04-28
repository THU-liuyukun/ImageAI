## Chaquopy快速上手

+ [GitHub - SolitudeFate/library-reservation-for-android: SSPU Library Reservation](https://github.com/SolitudeFate/library-reservation-for-android#chaquopy)

## 去掉默认的顶部导航栏：

将`values\themes.xml`的`<style>`的parent属性的值从**DarkActionBar**修改成**NoActionBar**

## tips

> 解释一下`app/src/main/java/com.example.baiduai/utils/ImagePickerUtils.java`中为什么pickImage这个方法中`setImageView(imageView, null);`这里的bitmap值始终设为null，但选择图片后仍会显示在ImageView的原因：

这是因为在pickImage方法中，当选择图片成功后，会通过Glide将图片加载到ImageView中。具体来说，Glide会在后台异步加载图片，然后将其设置到ImageView中。在调用setImageView方法时，传入的是null作为Bitmap参数，这意味着Glide会从指定的Uri中读取图片，然后将其转换为Bitmap，并将其设置到ImageView中。因此，即使传入的Bitmap参数为null，Glide也能成功地加载并显示图片。

# 遇到的问题

> android studio最新版，由于Google 推荐使用 Kotlin 作为开发安卓的编程语言，后续相关的开发架构工具也会首先支持 Kotlin 开发。所以新建项目默认变成了使用Kotlin。

+ 解决方案：不要选择紫色title的例子作为开始模板就可以创建java项目的

> 虚拟机的相册没有存取的权限，即无法显示Device File Explorer的`storage\emulated`目录下的内容，不能在`storage\emulated\0\Download`目录下存取图片

+ 解决方案：关于权限问题，使用Google APIs而非Google Play的模拟器就可以避免权限的配置

> 
