package cn.leo.retrofit_ktx.http

import kotlinx.coroutines.Job

/**
 * @author : ling luo
 * @date : 2019-11-18
 * Job委托类，只为去范型
 */
class MJob<T>(val job: Job) : Job by job