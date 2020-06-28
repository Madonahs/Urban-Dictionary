package com.madonasyombua.dictionaryurban.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.madonasyombua.dictionaryurban.R
import com.madonasyombua.dictionaryurban.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //open Dictionary activity class to display the urban dictionary content
        binding.openDictionaryButton.setOnClickListener{
            val intent = Intent(this,DictionaryActivity::class.java )
            startActivity(intent)
        }
    }
}