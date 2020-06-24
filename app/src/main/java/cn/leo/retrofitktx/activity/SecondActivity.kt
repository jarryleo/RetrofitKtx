package cn.leo.retrofitktx.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.leo.retrofit_ktx.view_model.ViewModelCreator
import cn.leo.retrofitktx.R
import cn.leo.retrofitktx.viewmodel.SecondViewModel
import kotlinx.android.synthetic.main.activity_secend.*

class SecondActivity : AppCompatActivity() {
    private val mViewModel by ViewModelCreator(SecondViewModel::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secend)
        btnGet.setOnClickListener {
            mViewModel.getData()
        }
    }
}