package cn.leo.retrofitktx.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.leo.retrofit_ktx.http.OkHttp3Creator
import cn.leo.retrofit_ktx.http.ServiceCreator
import cn.leo.retrofitktx.App
import cn.leo.retrofitktx.interceptor.CacheInterceptor
import cn.leo.retrofitktx.interceptor.LoggerInterceptor
import cn.leo.retrofitktx.net.Apis
import cn.leo.retrofitktx.net.Urls

/**
 * @author : ling luo
 * @date : 2020/4/29
 */
open class BaseViewModel : ViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()

    companion object {
        val api by lazy {
            ServiceCreator.create(Apis::class.java) {
                baseUrl = Urls.BASE_URL
                httpClient = OkHttp3Creator.build {
                    //缓存文件夹
                    cacheDir = App.appContext?.cacheDir
                    //网络请求日志打印拦截器
                    addInterceptor(LoggerInterceptor())
                    //接口缓存拦截器
                    addInterceptor(CacheInterceptor())
                }
            }
        }
    }
}