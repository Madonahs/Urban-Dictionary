package com.madonasyombua.dictionaryurban.data.repository

import com.madonasyombua.dictionaryurban.data.WordDataSource
import com.madonasyombua.dictionaryurban.data.response.BaseResponse
import com.madonasyombua.dictionaryurban.data.response.Results

/**
 * @author Madona
 */

class Repository(private val dictionaryDataSource: WordDataSource) {

    suspend fun definition(word: String): Results<BaseResponse> {
        return dictionaryDataSource.definition(word)
    }

}