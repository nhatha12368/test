package com.godhitech.language_component

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.godhitech.language_component.databinding.ActivityLanguageBinding

class LanguageActivity : AppCompatActivity() {
    private lateinit var mViewBinding: ActivityLanguageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(mViewBinding.root)
    }
}