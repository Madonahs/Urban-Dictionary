package com.madonasyombua.dictionaryurban.di

import com.madonasyombua.dictionaryurban.api.Api
import com.madonasyombua.dictionaryurban.api.ApiCreator
import org.koin.dsl.module

val apiModule = module {
    single { ApiCreator.get(Api::class.java) }
}