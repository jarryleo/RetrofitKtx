package cn.leo.retrofitktx.net

import cn.leo.retrofit_ktx.http.KInterceptor
import cn.leo.retrofit_ktx.view_model.KLiveData
import cn.leo.retrofitktx.bean.BaseBean
import cn.leo.retrofitktx.exceptions.BusinessException

/**
 * @author : ling luo
 * @date : 2020/4/29
 */
class MyInterceptor : KInterceptor() {
    override fun <T> intercept(extra: Any?, data: T, liveData: KLiveData<T>): Boolean {
        if (data is BaseBean<*>) {
            //根据服务器返回的错误码，进行对应业务处理
            if (data.errcode != 0) {
                liveData.failed(BusinessException(data.errcode, data.errmsg), extra)
            } else {
                liveData.success(data, extra)
            }
            return true
        }
        return super.intercept(extra, data, liveData)
    }
}