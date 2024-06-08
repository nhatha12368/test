package com.godhitech.androidcomponent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.godhitech.androidcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mViewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
        mViewBinding.btnSettingLanguage.setOnClickListener {
            startActivity(Intent(this, LanguageActivity::class.java))
        }
    }
}