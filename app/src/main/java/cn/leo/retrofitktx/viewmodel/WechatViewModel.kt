package cn.leo.retrofitktx.viewmodel

import androidx.lifecycle.asLiveData
import cn.leo.retrofitktx.repository.WecahtRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * @author : ling luo
 * @date : 2020/4/29
 */
class WechatViewModel : BaseViewModel() {

    val repository = WecahtRepository()

    fun getData(access_token: String, openid: String) =
        repository.getWechatUserInfo(access_token, openid)
            .onStart {
                //showloading
                loadingLiveData.postValue(true)
            }
            .catch {
                //error

            }
            .onCompletion {
                //hideloading
                loadingLiveData.postValue(false)
            }
            .asLiveData()//没有绑定生命周期，每次调用都会创建新的liveData fixme

}