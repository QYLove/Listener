package com.sun.listener

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.sun.voice.DataUtil
import com.sun.voice.VoiceBean
import com.sun.voice.VoiceLog
import com.sun.voice.VoiceUtil

class MainActivity : AppCompatActivity() {

    private val text = "我们是祖国的花朵"
    private lateinit var beanList:List<VoiceBean>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beanList = DataUtil.getData(this)
        findViewById<TextView>(R.id.activity_main_test_tv).setOnClickListener { v ->text()
        }
    }

    fun text() {
        VoiceUtil.playVoiceString(this,1,text)
    }

}