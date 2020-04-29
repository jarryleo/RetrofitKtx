package cn.leo.retrofit_ktx.view_model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import cn.leo.retrofit_ktx.http.KJob
import cn.leo.retrofit_ktx.http.create
import cn.leo.retrofit_ktx.utils.getSuperClassGenericType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlin.reflect.KFunction

/**
 * @author : ling luo
 * @date : 2019-07-03
 *
 * ViewModel 生命周期比 Activity 长，不应该持有任何view引用或者包含view的引用，
 */
@Suppress("UNUSED", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate")
abstract class KViewModel<T : Any> : ViewModel() {

    /**
     * 网络请求帮助类
     */
    private val mApiHelper by ViewModelApiHelper<T>()
    /**
     * liveData帮助类
     */
    private val mLiveDataHelper by lazy { KLiveDataHelper() }
    /**
     * 协程帮助类
     */
    private val mCoroutineHelper by ViewModelCoroutineHelper()

    /**
     * 非代理网络请求
     */
    internal val api by lazy { createApi() }

    /**
     * 代理网络请求
     */
    val apis get() = mApiHelper.apis<Any>(null)

    /**
     * 代理传参网络请求
     * @param extra 在订阅里原样返回
     */
    fun apis(extra: Any? = null) = mApiHelper.apis<Any>(extra)

    /**
     * 获取接口基础地址
     */
    abstract fun getBaseUrl(): String

    /**
     * 创建请求api
     * 可以重写，配置retrofit 和 okHttp3
     */
    open fun createApi(): T {
        return javaClass
            .getSuperClassGenericType<T>() //获取泛型类型
            .create {
                //创建retrofit，生成请求service
                baseUrl = getBaseUrl()
            }
    }


    /**
     * 释放资源
     */
    override fun onCleared() {
        mApiHelper.clear()
        mLiveDataHelper.clear()
    }

    /**
     * 协程执行网络请求，并把结果给上层的LiveData
     */
    fun <R> executeRequest(
        deferred: Deferred<R>,
        liveData: KLiveData<R>,
        extra: Any? = null
    ): Job = mCoroutineHelper.executeRequest(deferred, liveData, extra)

    /**
     * 异步方法 回调在主线程
     */
    fun <R> async(block: suspend CoroutineScope.() -> R): KJob<R> = mCoroutineHelper.async(block)

    /**
     * 获取liveData
     */
    fun <R> getLiveData(key: String? = null): KLiveData<R> {
        //获取当前方法名称
        val methodName = key ?: Thread.currentThread()
            .stackTrace
            .find {
                it.className == this::class.java.name
            }?.methodName ?: "no-name"
        return mLiveDataHelper.getLiveData(methodName)
    }

    /**
     * 订阅方法回调(本地方法和网络请求)
     * @param kFunction 参数写法 model::test
     */
    fun <R> observe(
        lifecycleOwner: LifecycleOwner,
        kFunction: KFunction<KJob<R>>,
        result: (Result<R>).() -> Unit = {}
    ) = getLiveData<R>(kFunction.name).observe(lifecycleOwner, result)

    /**
     * 订阅方法，正确结果直接传递
     * @param viewModelSupport 写法 model::setTitle + ::setActionBarTitle
     * 前面为订阅方法，后面为回调方法
     * 前面的方法必须是本model的方法，后面的方法参数必须是前面方法的返回值
     */
    fun <R> observe(
        lifecycleOwner: LifecycleOwner,
        viewModelSupport: ViewModelSupport<R>
    ) {
        observe(lifecycleOwner, viewModelSupport.modelFuc) {
            success { viewModelSupport.obFunc(it) }
        }
    }

    /**
     * 无生命周期的监听，谨慎使用，防止泄露
     * @param kFunction 参数写法 Api::test
     */
    fun <R> observeForever(
        kFunction: KFunction<KJob<R>>,
        result: (Result<R>).() -> Unit = {}
    ) = getLiveData<R>(kFunction.name).observeForever(result)

}