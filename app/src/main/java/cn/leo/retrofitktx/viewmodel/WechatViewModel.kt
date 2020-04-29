package cn.leo.retrofitktx.viewmodel

import cn.leo.retrofitktx.net.Apis

/**
 * @author : ling luo
 * @date : 2020/4/29
 */
class WechatViewModel : BaseViewModel<Apis>() {

    fun getTitle() = async {
        "测试业务代码"
    }
}