package cn.leo.retrofitktx.net

import cn.leo.retrofitktx.bean.BaseBean
import cn.leo.retrofitktx.bean.WechatUserBean
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * @author : ling luo
 * @date : 2019-07-02
 */
interface TestApis {
    /**
     * 获取微信用户信息
     */
    @GET(Urls.GET_USER_INFO)
    fun getWechatUserInfoAsync(
        @Query("access_token") access_token: String,
        @Query("openid") openid: String
    ): Deferred<BaseBean<WechatUserBean>>


}