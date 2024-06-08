package com.godhitech.androidcomponent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godhitech.androidcomponent.databinding.ItemLanguageBinding

class LanguageAdapter(
    private val context: Context,
    private val listLanguage: ArrayList<LanguageModel>,
    private val onClickItemLanguage: (position: Int) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    private var selectedLanguageCode: String? = null
    private var sharedPreferencesManager: SharedPreferencesManager? = null

    init {
        sharedPreferencesManager = SharedPreferencesManager(context)
        selectedLanguageCode =
            sharedPreferencesManager?.getString(LanguageActivity.KEY_LANGUAGE, "en") ?: "en"
    }

    inner class LanguageViewHolder(val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listLanguage.size
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val languages = listLanguage[position]
        holder.binding.imgLanguage.setImageResource(languages.imgFlags)
        holder.binding.txtLanguage.text = languages.nameLanguage
        if (languages.language == selectedLanguageCode) {
            holder.binding.imgChooseLanguage.visibility = View.VISIBLE
        } else {
            holder.binding.imgChooseLanguage.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            val previousSelectedLanguage = selectedLanguageCode
            selectedLanguageCode = languages.language
            notifyItemChanged(listLanguage.indexOfFirst { it.language == previousSelectedLanguage })
            notifyItemChanged(position)
            onClickItemLanguage(position)
        }
    }
}