package com.madonasyombua.dictionaryurban.di

import com.madonasyombua.dictionaryurban.data.WordDataSource
import org.koin.dsl.module

val wordDataSourceModule = module {
    single { WordDataSource(get()) }
}