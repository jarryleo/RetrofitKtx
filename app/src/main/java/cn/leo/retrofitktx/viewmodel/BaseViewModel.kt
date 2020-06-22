package cn.leo.retrofitktx.viewmodel

import cn.leo.retrofit_ktx.http.OkHttp3Creator
import cn.leo.retrofit_ktx.http.create
import cn.leo.retrofit_ktx.utils.getSuperClassGenericType
import cn.leo.retrofit_ktx.view_model.KNetViewModel
import cn.leo.retrofitktx.App
import cn.leo.retrofitktx.interceptor.CacheInterceptor
import cn.leo.retrofitktx.interceptor.LoggerInterceptor
import cn.leo.retrofitktx.net.Urls

/**
 * @author : ling luo
 * @date : 2020/4/29
 * 建议弄一个ViewModel基类，把以下初始化代码写基类，子类写业务
 */
open class BaseViewModel<T : Any> : KNetViewModel<T>() {

    override fun getBaseUrl(): String {
        return Urls.BASE_URL
    }

    override fun createApi(): T {
        //通过泛型获取api字节码类型
        return javaClass.getSuperClassGenericType<T>()
            .create {
                baseUrl = getBaseUrl()
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