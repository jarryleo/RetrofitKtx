package cn.leo.retrofitktx.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import cn.leo.retrofit_ktx.view_model.ViewModelCreator
import cn.leo.retrofitktx.R
import cn.leo.retrofitktx.viewmodel.WechatViewModel
import kotlinx.android.synthetic.main.activity_secend.*

class SecondActivity : AppCompatActivity() {
    private val mViewModel by ViewModelCreator(WechatViewModel::class.java)

    private val loadingDialog by lazy {
        AlertDialog.Builder(this).setMessage("加载中").create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secend)
        btnGet.setOnClickListener {
            request()
        }
        mViewModel.loadingLiveData.observe(this, Observer {
            if (it) {
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        })
    }

    private fun request() {
        val data = mViewModel.getData("", "")
        println("data = ${data.hashCode()}")
        data.observe(this, {
            Toast.makeText(this, "${it.errcode}", Toast.LENGTH_SHORT).show()
        })
    }
}