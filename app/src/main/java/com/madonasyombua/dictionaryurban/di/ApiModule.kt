package com.madonasyombua.dictionaryurban.di

import com.madonasyombua.dictionaryurban.data.api.Api
import com.madonasyombua.dictionaryurban.data.api.ApiCreator
import org.koin.dsl.module

val apiModule = module {
    single { ApiCreator.get(Api::class.java) }
}