package cn.leo.retrofitktx.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cn.leo.retrofit_ktx.view_model.ViewModelCreator
import cn.leo.retrofitktx.R
import cn.leo.retrofitktx.viewmodel.WechatViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mViewModel by ViewModelCreator(WechatViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObserver()
        init()
    }

    private fun initObserver() {
        mViewModel.loadingLiveData.observe(this, Observer {

        })
    }

    private fun init() {
        btnTest.setOnClickListener {
            request()
        }
        //跳转第二页面
        btnJump.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }

    private fun request() {
        mViewModel.getData("", "").observe(this, Observer {
            Toast.makeText(this, "${it.errcode}", Toast.LENGTH_SHORT).show()
        })
    }
}
