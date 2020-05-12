# RetrofitKtx
Retrofit+Kotlin 封装 

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 ``` 
Step 2. Add the dependency
```
	dependencies {
          //RetrofitKtx 本库
	  implementation 'com.github.jarryleo:RetrofitKtx:v1.0.3'
          //androidX标准库
          implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
          implementation 'androidx.appcompat:appcompat:1.1.0'
          implementation 'androidx.core:core-ktx:1.2.0'
          //kotlin 协程
          implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0'
          //网络请求库
          implementation 'com.squareup.retrofit2:converter-gson:2.7.1'
          implementation 'com.squareup.retrofit2:retrofit:2.7.1'
          implementation 'com.squareup.okhttp3:okhttp:4.3.1'
          //生命周期辅助ktx
          implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0-alpha01'
          implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.0-alpha01'
          implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.0-alpha01'
	}
```

## 用法：（具体用法看范例）
```
//订阅网络请求
mViewModel.observe(this, Apis::getWechatUserInfo) {
            loading {
                btnTest.text = "loading状态：$it"
            }
            success {
                Toast.makeText(this@MainActivity, "请求成功", Toast.LENGTH_SHORT).show()
            }
            failed {
                val analysisException = FactoryException.analysisException(it)
                Toast.makeText(
                    this@MainActivity,
                    "请求失败：错误码：${analysisException.code} , ${analysisException.msg}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        
 //网络请求       
 mViewModel.apis.getWechatUserInfo("123", "456")
```
