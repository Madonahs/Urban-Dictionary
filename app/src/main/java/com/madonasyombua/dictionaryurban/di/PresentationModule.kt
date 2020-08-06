package com.madonasyombua.dictionaryurban.di


import com.madonasyombua.dictionaryurban.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single { Repository(get()) }
}