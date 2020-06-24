package cn.leo.retrofitktx.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.leo.retrofit_ktx.view_model.ViewModelCreator
import cn.leo.retrofitktx.R
import cn.leo.retrofitktx.exceptions.FactoryException
import cn.leo.retrofitktx.net.Apis
import cn.leo.retrofitktx.viewmodel.WechatViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val mViewModel by ViewModelCreator(WechatViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObserver()
        init()
    }

    private fun initObserver() {
        mViewModel.observe(this, Apis::getWechatUserInfo) {
            loading {
                btnTest.text = "loading状态：$it"
            }
            success {
                Toast.makeText(this@MainActivity, "请求成功", Toast.LENGTH_SHORT).show()
            }
            failed {
                Log.e("请求携带的额外参数", extra.toString())
                val analysisException = FactoryException.analysisException(it)
                Toast.makeText(
                    this@MainActivity,
                    "请求失败：错误码：${analysisException.code} , ${analysisException.msg}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //测试model业务方法
        mViewModel.observe(this, mViewModel::getTitle) {
            success {
                title = it
            }
        }
    }

    private fun init() {
        btnTest.setOnClickListener {
            //请求网络（无携带额外参数）
            //mViewModel.apis.getWechatUserInfo("123", "456")mViewModel.apis.getWechatUserInfo("123", "456")
            val num = Random.nextInt(999)
            //请求携带额外数据，在返回结果中可以拿到，可用于通过请求结果修改列表单个条目等场景，携带position等
            mViewModel.apis(num).getWechatUserInfo("123", "456")
            //测试model业务
            mViewModel.getTitle()
        }
        //跳转第二页面
        btnJump.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
