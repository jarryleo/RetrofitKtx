package cn.leo.retrofitktx.ext

import cn.leo.retrofit_ktx.utils.withIO
import cn.leo.retrofitktx.bean.BaseBean
import cn.leo.retrofitktx.exceptions.BusinessException
import cn.leo.retrofit_ktx.utils.KResult
import kotlinx.coroutines.Deferred

/**
 * @author : ling luo
 * @date : 2020/6/23
 */

suspend fun <T> Deferred<BaseBean<T>>.result(): KResult<T> {
    return withIO {
        try {
            val data = this@result.await()
            if (data.errcode == 0) {
                KResult.success(data.data)
            } else {
                KResult.failure<T>(BusinessException(data.errcode, data.errmsg))
            }
        } catch (e: Exception) {
            KResult.failure<T>(e)
        }
    }
}