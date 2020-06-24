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

    }

    private fun init() {
        //跳转第二页面
        btnJump.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
