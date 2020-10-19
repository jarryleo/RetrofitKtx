package cn.leo.retrofitktx.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @author : ling luo
 * @date : 2020/10/17
 * @description :
 */
class WecahtRepository : BaseRepository() {

    fun getWechatUserInfo(access_token: String, openid: String) =
        flow {
            emit(api.getWechatUserInfoAsync(access_token, openid))
        }.flowOn(Dispatchers.IO)
}