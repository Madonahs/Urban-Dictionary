package com.madonasyombua.dictionaryurban.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author madona
 * Basically if you need a singleton - a class that only has got one instance -
 * you can declare the class in the usual way, but use the object keyword instead of class:
 * Currently using Retrofit
 * */
object ApiCreator {
    private const val BASE_URL = "https://mashape-community-urban-dictionary.p.rapidapi.com/"
    fun<T> get(type: Class<T>) : T{
        val okBuilder = OkHttpClient.Builder()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()) // we can also use RXJavaAdapter Fac
            .client(okBuilder.build())
            .build()
            .create(type)
    }

}