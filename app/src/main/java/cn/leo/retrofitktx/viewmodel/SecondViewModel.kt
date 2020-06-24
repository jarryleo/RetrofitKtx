package cn.leo.retrofitktx.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import cn.leo.retrofitktx.ext.result
import cn.leo.retrofitktx.net.onFailure
import cn.leo.retrofitktx.net.onSuccess
import kotlinx.coroutines.launch

/**
 * @author : ling luo
 * @date : 2020/4/29
 */
class SecondViewModel : NewBaseViewModel() {

    fun getData() {
        viewModelScope.launch {
            api.getWechatUserInfoAsync("123", "456")
                .result()
                .onSuccess {
                    Log.e("getWechatUserInfoAsync", "getData:请求成功 ")
                }
                .onFailure {
                    Log.e("getWechatUserInfoAsync", "getData:请求失败 ${it.toString()}")
                }
        }

    }


}