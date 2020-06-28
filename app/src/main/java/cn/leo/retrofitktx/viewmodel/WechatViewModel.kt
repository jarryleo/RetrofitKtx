package cn.leo.retrofitktx.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.leo.retrofit_ktx.utils.onFailure
import cn.leo.retrofit_ktx.utils.onSuccess
import cn.leo.retrofitktx.bean.WechatUserBean
import cn.leo.retrofitktx.ext.getResult
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * @author : ling luo
 * @date : 2020/4/29
 */
class WechatViewModel : BaseViewModel() {

    val wechatUserInfo = MutableLiveData<WechatUserBean>()

    fun getData() {
        viewModelScope.launch {
            api.getWechatUserInfoAsync("123", "456")
                .getResult {
                    Log.e("loading", "$it")
                }
                .onSuccess {
                    wechatUserInfo.postValue(it)
                    Log.e("getWechatUserInfoAsync", "getData:请求成功")
                }
                .onFailure {
                    Log.e("getWechatUserInfoAsync", "getData:请求失败 ${it.toString()}")
                }
        }


        viewModelScope.launch {
            val a =
                async { api.getWechatUserInfoAsync("123", "456").await() }
            val b =
                async { api.getWechatUserInfoAsync("123", "456").await() }
            listOf(a, b)
                .getResult {
                    Log.e("loading", "$it")
                }.onSuccess {
                    Log.e("getWechatUserInfoAsync", "getData:请求成功")
                }.onFailure {
                    Log.e("WechatViewModel", "getData:${it.message} " )
                }

        }
    }


}