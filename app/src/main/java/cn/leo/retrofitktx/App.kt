package cn.leo.retrofitktx

import android.app.Application
import android.content.Context

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

    }
}