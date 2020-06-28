package com.madonasyombua.dictionaryurban.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.madonasyombua.dictionaryurban.R
import com.madonasyombua.dictionaryurban.data.response.Word
import com.madonasyombua.dictionaryurban.databinding.ActivityDefinitionBinding
import com.madonasyombua.dictionaryurban.ui.viewmodels.DictionaryViewModel
//import com.madonasyombua.dictionaryurban.ui.viewmodels.ViewModelFactory
import com.madonasyombua.dictionaryurban.utils.Constants
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DefinitionActivity : AppCompatActivity() {
    private lateinit var word: Word
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityDefinitionBinding = DataBindingUtil.setContentView(this, R.layout.activity_definition)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val gson = Gson()
        val wordJsonBody = intent.getStringExtra(Constants.INTENT_WORD_JSON)
        word = gson.fromJson(wordJsonBody, Word::class.java)
        binding.model = word
    }
}