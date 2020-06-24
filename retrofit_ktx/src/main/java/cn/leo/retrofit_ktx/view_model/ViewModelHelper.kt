package cn.leo.retrofit_ktx.view_model

import cn.leo.retrofit_ktx.utils.KResult
import cn.leo.retrofit_ktx.utils.withIO
import kotlinx.coroutines.Deferred

/**
 * @author : ling luo
 * @date : 2020/6/24
 */
suspend fun <T> Deferred<T>.result() =
    withIO {
        try {
            val data = this@result.await()
            KResult.success(data)
        } catch (e: Exception) {
            KResult.failure<T>(e)
        }
    }