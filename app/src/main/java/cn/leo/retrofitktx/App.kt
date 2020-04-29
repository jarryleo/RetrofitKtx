package cn.leo.retrofitktx

import android.app.Application
import android.content.Context
import cn.leo.retrofit_ktx.http.KInterceptorManager
import cn.leo.retrofitktx.net.MyInterceptor

/**
 * @author : ling luo
 * @date : 2020/4/29
 */
class App : Application() {
    companion object {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        //添加业务拦截器
        KInterceptorManager.addInterceptor(MyInterceptor())
    }
}