package com.madonasyombua.dictionaryurban.di

import com.madonasyombua.dictionaryurban.ui.viewmodels.DictionaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DictionaryViewModel(get()) }
}