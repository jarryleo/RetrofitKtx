package cn.leo.retrofit_ktx.view_model

import cn.leo.retrofit_ktx.http.MJob
import cn.leo.retrofit_ktx.refactor.create
import cn.leo.retrofit_ktx.utils.getSuperClassGenericType
import kotlinx.coroutines.Deferred
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @author : ling luo
 * @date : 2019-11-15
 */
@Suppress("UNUSED", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate")
class ViewModelApiHelper<T : Any> :
    ReadOnlyProperty<MViewModel<*>, ViewModelApiHelper<T>> {

    /**
     * viewModel
     */
    private lateinit var model: MViewModel<*>


    //请求代理相关
    private var mApiProxy: T? = null
    private var mApiHandler: InvocationHandler? = null
    //请求携带的额外数据
    @Volatile
    private var mObj: Any? = null

    override fun getValue(thisRef: MViewModel<*>, property: KProperty<*>): ViewModelApiHelper<T> {
        model = thisRef
        return this
    }

    /**
     * 网络接口实例化
     */
    val api: T by lazy {
        model.javaClass.getSuperClassGenericType<T>().create {
            baseUrl = model.getBaseUrl()
            httpClient = model.getOkHttpClient()
        }
    }


    /**
     * 代理请求接口
     */
    fun <R : Any> apis(obj: Any?): T {
        mObj = obj
        mApiHandler = mApiHandler ?: InvocationHandler { _, method, args ->
            val finalObj = mObj
            val mJob = method.invoke(api, *args ?: arrayOf()) as MJob<R>
            val deferred = mJob.job as Deferred<R>
            MJob<R>(
                model.executeRequest(
                    deferred,
                    model.getLiveData(method.name),
                    finalObj
                )
            )
        }
        mApiProxy = mApiProxy ?: Proxy.newProxyInstance(
            javaClass.classLoader,
            arrayOf(*api.javaClass.interfaces),
            mApiHandler
        ) as T
        return mApiProxy!!
    }

    /**
     * 释放资源
     */
    fun clear() {
        mApiProxy = null
        mApiHandler = null
        mObj = null
    }


}