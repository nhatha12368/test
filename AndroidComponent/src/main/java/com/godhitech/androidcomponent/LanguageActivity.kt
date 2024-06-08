package com.godhitech.androidcomponent

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.godhitech.androidcomponent.databinding.ActivityLanguageBinding
import java.util.Locale

class LanguageActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityLanguageBinding
    companion object {
        const val KEY_LANGUAGE = "language"
        const val KEY_NAME_LANGUAGE = "name_language"
    }

    private var languageAdapter: LanguageAdapter? = null
    private var listLanguage: ArrayList<LanguageModel>? = null
    private var sharedPreferencesManager: SharedPreferencesManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initControls()
    }

    private fun initControls() {
        sharedPreferencesManager = SharedPreferencesManager(this@LanguageActivity)
        onClickBack()
        insertDataLanguage()
        initRecyclerView()
        checkLanguageArab(mBinding.imgBack, this@LanguageActivity)
    }

    private fun insertDataLanguage() {
        listLanguage = ArrayList()
        listLanguage?.add(LanguageModel(R.drawable.flag_english, "English", "en"))
        listLanguage?.add(LanguageModel(R.drawable.flag_spain, "Spain", "es"))
        listLanguage?.add(LanguageModel(R.drawable.flag_portugal, "Portugal", "pt"))
        listLanguage?.add(LanguageModel(R.drawable.flag_france, "French", "fr"))
        listLanguage?.add(LanguageModel(R.drawable.flag_saudi_arabia, "Arab", "ar"))
        listLanguage?.add(LanguageModel(R.drawable.flag_china, "Chinese", "zh"))
        listLanguage?.add(LanguageModel(R.drawable.flag_germany, "German", "de"))
        listLanguage?.add(LanguageModel(R.drawable.flag_india, "India", "hi"))
        listLanguage?.add(LanguageModel(R.drawable.flag_south_korea, "Korea", "ko"))
        listLanguage?.add(LanguageModel(R.drawable.flag_indonesia, "Indonesian", "id"))
        listLanguage?.add(LanguageModel(R.drawable.flag_italy, "Italia", "it"))
        listLanguage?.add(LanguageModel(R.drawable.flag_vietnam, "Vietnamese", "vi"))
    }

    private fun initRecyclerView() {
        languageAdapter = listLanguage?.let { list ->
            LanguageAdapter(this@LanguageActivity, list, ::handClickItemLanguage)
        }
        mBinding.recyclerLanguage.layoutManager = LinearLayoutManager(this@LanguageActivity)
        mBinding.recyclerLanguage.adapter = languageAdapter
    }

    private fun handClickItemLanguage(position: Int) {
        val languages = listLanguage?.get(position)
        sharedPreferencesManager?.saveString(KEY_LANGUAGE, languages?.language.toString())
        sharedPreferencesManager?.saveString(
            KEY_NAME_LANGUAGE,
            languages?.nameLanguage.toString()
        )
        finish()

    }

    private fun onClickBack() {
        mBinding.imgBack.setOnClickListener {
            finish()
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        val sharedPreferencesManager = newBase?.let { SharedPreferencesManager(it) }
        val language = sharedPreferencesManager?.getString("language", "en") ?: "en"
        val localeToSwitch = Locale(language)
        val config = newBase?.resources?.configuration
        config?.setLocale(localeToSwitch)
        applyOverrideConfiguration(config)
    }

    private fun checkLanguageArab(imageView: ImageView, context: Context) {
        val language =
            SharedPreferencesManager(context).getString("language", "en") ?: "en"
        if (language == "ar") {
            imageView.setImageResource(R.drawable.ic_back_right)
        } else {
            imageView.setImageResource(R.drawable.ic_back)
        }
    }
}