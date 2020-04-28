package cn.leo.retrofit_ktx.view_model

import cn.leo.retrofit_ktx.http.MJob
import kotlin.reflect.KFunction

/**
 * @author : ling luo
 * @date : 2019-12-11
 */

/**
 * 重载操作符协助订阅方法
 */
operator fun <R> KFunction<MJob<R>>.plus(obFunc: (R) -> Any): ViewModelSupport<R> {
    return ViewModelSupport(this, obFunc)
}

class ViewModelSupport<R>(val modelFuc: KFunction<MJob<R>>, val obFunc: (R) -> Any)