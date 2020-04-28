package cn.leo.retrofit_ktx.http

import cn.leo.retrofit_ktx.view_model.MLiveData

/**
 * @author : ling luo
 * @date : 2019-09-17
 */

open class MInterceptor {
    var priority: Int = 0
    /**
     * @return 返回true 拦截
     */
    open fun <T> intercept(obj: Any? = null, data: T, liveData: MLiveData<T>): Boolean {
        return false
    }
}